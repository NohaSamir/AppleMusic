package com.vama.applemusic.ui.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vama.applemusic.ui.model.UiIntent
import com.vama.applemusic.ui.model.UiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class ViewModelStateHandler<State : UiState, Intent : UiIntent> : ViewModel() {
    private val initialState: State by lazy { createInitialState() }

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _intent: MutableSharedFlow<Intent> = MutableSharedFlow()
    private val intent = _intent.asSharedFlow()

    init {
        subscribeIntents()
    }

    abstract fun createInitialState(): State
    protected abstract fun handleIntent(intent: Intent)

    fun setState(reduce: State.() -> State) {
        _uiState.update {
            it.reduce()
        }
    }

    fun setIntent(intent: Intent) {
        viewModelScope.launch { _intent.emit(intent) }
    }

    /**
     * Start listening to Intents
     */
    private fun subscribeIntents() {
        viewModelScope.launch {
            intent.collect {
                handleIntent(it)
            }
        }
    }
}