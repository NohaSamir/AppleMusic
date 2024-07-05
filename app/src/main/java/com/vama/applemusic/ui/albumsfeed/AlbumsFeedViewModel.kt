package com.vama.applemusic.ui.albumsfeed

import androidx.lifecycle.viewModelScope
import com.vama.applemusic.ui.mapper.AlbumsFeedDomainMapper.mapFromDomainModel
import com.vama.applemusic.ui.util.ViewModelStateHandler
import com.vama.domain.model.Result
import com.vama.domain.usecase.GetMostPlayedAlbumsInUSUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsFeedViewModel @Inject constructor(
    private val getMostPlayedAlbumsInUSUseCase: GetMostPlayedAlbumsInUSUseCase
) : ViewModelStateHandler<AlbumsFeedUiState, AlbumsFeedUiIntent>() {

    init {
        getMostPlayedAlbumsFeed()
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
                getMostPlayedAlbumsFeed()
            }
        }
    }

    private fun getMostPlayedAlbumsFeed() {
        viewModelScope.launch {
            getMostPlayedAlbumsInUSUseCase().collectLatest { albumsFeed ->
                setState {
                    if (albumsFeed is Result.Success && albumsFeed.data.feeds.isNotEmpty()) {
                        AlbumsFeedUiState.Success(albumsFeed.data.mapFromDomainModel())
                    } else if (albumsFeed is Result.Error) {
                        if (this !is AlbumsFeedUiState.Success)
                            AlbumsFeedUiState.Error(albumsFeed.exception.message.orEmpty())
                        else {
                            this
                        }
                    } else {
                        AlbumsFeedUiState.Error("")
                    }
                }
            }
        }
    }
}