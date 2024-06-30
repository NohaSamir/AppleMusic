package com.vama.applemusic.ui.albumsfeed

import androidx.lifecycle.viewModelScope
import com.vama.applemusic.ui.mapper.AlbumsFeedDomainMapper.mapFromDomainModel
import com.vama.applemusic.ui.util.ViewModelStateHandler
import com.vama.domain.model.Result
import com.vama.domain.usecase.GetAlbumsFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsFeedViewModel @Inject constructor(
    private val getAlbumsFeedUseCase: GetAlbumsFeedUseCase
) : ViewModelStateHandler<AlbumsFeedUiState, AlbumsFeedUiIntent>() {

    init {
        getAlbumsFeed()
    }

    override fun createInitialState(): AlbumsFeedUiState {
        return AlbumsFeedUiState.Loading
    }

    override fun handleIntent(intent: AlbumsFeedUiIntent) {
        when (intent) {
            AlbumsFeedUiIntent.OnRetryClicked -> {
                setState {
                    AlbumsFeedUiState.Retrying
                }
                getAlbumsFeed()
            }
        }
    }

    private fun getAlbumsFeed() {
        viewModelScope.launch {
            val result = getAlbumsFeedUseCase()
            setState {
                when (result) {
                    is Result.Success -> AlbumsFeedUiState.Success(result.data.mapFromDomainModel())
                    is Result.Error -> AlbumsFeedUiState.Error(result.exception.message.orEmpty())
                }
            }
        }
    }
}