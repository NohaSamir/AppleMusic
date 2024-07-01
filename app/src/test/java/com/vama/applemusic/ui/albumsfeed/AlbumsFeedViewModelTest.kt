package com.vama.applemusic.ui.albumsfeed

import com.vama.applemusic.ui.mapper.AlbumsFeedDomainMapper.mapFromDomainModel
import com.vama.applemusic.ui.viewModelTestSkeleton
import com.vama.domain.model.Album
import com.vama.domain.model.AlbumsFeed
import com.vama.domain.model.Result
import com.vama.domain.usecase.GetMostPlayedAlbumsInUSUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import java.io.IOException

class AlbumsFeedViewModelTest {

    @MockK
    lateinit var getMostPlayedAlbumsInUSUseCase: GetMostPlayedAlbumsInUSUseCase

    @RelaxedMockK
    lateinit var emptyfeeds: AlbumsFeed

    @RelaxedMockK
    lateinit var album: Album

    private lateinit var feeds: AlbumsFeed


    private val initializeViewModel = {
        AlbumsFeedViewModel(getMostPlayedAlbumsInUSUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(StandardTestDispatcher())
        feeds = AlbumsFeed(
            feeds = listOf(album, album, album),
            copyright = ""
        )
    }

    @Test
    fun `verify ui state is loading then success when view model initialized and use case succeed`() {
        viewModelTestSkeleton(
            mockDependency = {
                coEvery { getMostPlayedAlbumsInUSUseCase.invoke() } returns flow {
                    emit(Result.Success(feeds))
                }
            },
            initializeViewModel = initializeViewModel,
            givenInitialState = null,
            givenIntent = null,
            expectedStates = listOf(
                AlbumsFeedUiState.Loading,
                AlbumsFeedUiState.Success(feeds.mapFromDomainModel())
            ),
            verificationCalls = {
                coVerify { getMostPlayedAlbumsInUSUseCase() }
            }
        )
    }

    @Test
    fun `verify ui state is loading then error when view model initialized and use case succeed with empty list`() {
        viewModelTestSkeleton(
            mockDependency = {
                coEvery { getMostPlayedAlbumsInUSUseCase.invoke() } returns flow {
                    emit(Result.Success(emptyfeeds))
                }
            },
            initializeViewModel = initializeViewModel,
            givenInitialState = null,
            givenIntent = null,
            expectedStates = listOf(
                AlbumsFeedUiState.Loading,
                AlbumsFeedUiState.Error("")
            ),
            verificationCalls = {
                coVerify { getMostPlayedAlbumsInUSUseCase() }
            }
        )
    }

    @Test
    fun `verify ui state is loading then error when view model initialized and use case failed`() {
        viewModelTestSkeleton(
            mockDependency = {
                coEvery { getMostPlayedAlbumsInUSUseCase.invoke() } returns flow {
                    emit(Result.Error(IOException("")))
                }
            },
            initializeViewModel = initializeViewModel,
            givenInitialState = null,
            givenIntent = null,
            expectedStates = listOf(
                AlbumsFeedUiState.Loading,
                AlbumsFeedUiState.Error("")
            ),
            verificationCalls = {
                coVerify { getMostPlayedAlbumsInUSUseCase() }
            }
        )
    }

    @Test
    fun `verify ui state is retrying when retry clicked`() {
        val initialStates = listOf(
            AlbumsFeedUiState.Success(feeds.mapFromDomainModel()),
            AlbumsFeedUiState.Error(""),
            AlbumsFeedUiState.Loading
        )

        initialStates.forEach { initialState ->
            viewModelTestSkeleton(
                mockDependency = {
                    coEvery { getMostPlayedAlbumsInUSUseCase.invoke() } coAnswers {
                        delay(100)
                        flow { emit(Result.Success(feeds)) }
                    }
                },
                initializeViewModel = initializeViewModel,
                givenInitialState = initialState,
                givenIntent = AlbumsFeedUiIntent.OnRetryClicked,
                expectedStates = listOf(
                    AlbumsFeedUiState.Retrying,
                    AlbumsFeedUiState.Success(feeds.mapFromDomainModel())
                ),
                verificationCalls = {
                    coVerify { getMostPlayedAlbumsInUSUseCase() }
                }
            )
        }
    }
}