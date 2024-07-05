package com.vama.domain.usecase

import com.vama.domain.model.Album
import com.vama.domain.model.AlbumsFeed
import com.vama.domain.model.Result
import com.vama.domain.repository.AlbumRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class GetMostPlayedAlbumsInUSUseCaseTest {

    @MockK
    lateinit var repository: AlbumRepository

    @MockK
    lateinit var album: Album

    private lateinit var getMostPlayedAlbumsInUSUseCase: GetMostPlayedAlbumsInUSUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getMostPlayedAlbumsInUSUseCase = GetMostPlayedAlbumsInUSUseCase(repository)
    }

    @Test
    fun `verify get albums feed return the top 100 music albums in the US on success`() {
        val albums = List(100) { album }
        getAlbumsFeedUseCaseTestSkeleton(
            expectedResult = flow {
                Result.Success(AlbumsFeed(feeds = albums, copyright = "Apple"))
            }
        )

    }

    @Test
    fun `verify get albums feed return error on failure`() =
        getAlbumsFeedUseCaseTestSkeleton(
            expectedResult = flow { Result.Error(IOException()) }
        )

    private fun getAlbumsFeedUseCaseTestSkeleton(
        expectedResult: Flow<Result<AlbumsFeed>>,
    ) = runTest {
        //Given
        coEvery {
            repository.fetchAndUpdateMostPlayedAlbumsFeed(
                any(),
                any()
            )
        } returns expectedResult

        //When
        val result = getMostPlayedAlbumsInUSUseCase()

        //Then
        coVerify {
            repository.fetchAndUpdateMostPlayedAlbumsFeed("us", 100)
        }
        assertEquals(/* expected = */ expectedResult, /* actual = */ result)
    }
}