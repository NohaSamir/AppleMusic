package com.vama.domain.usecase

import com.vama.domain.model.Album
import com.vama.domain.model.AlbumsFeed
import com.vama.domain.model.Result
import com.vama.domain.repository.AlbumRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class GetAlbumsFeedUseCaseTest {

    @MockK
    lateinit var repository: AlbumRepository

    @MockK
    lateinit var album: Album

    private lateinit var getAlbumsFeedUseCase: GetAlbumsFeedUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getAlbumsFeedUseCase = GetAlbumsFeedUseCase(repository)
    }

    @Test
    fun `verify get albums feet return the top 100 music albums in the US on success`() {
        val albums = List(100) { album }
        getAlbumsFeedUseCaseTestSkeleton(
            expectedResult = Result.Success(AlbumsFeed(feeds = albums, copyright = "Apple"))
        )

    }

    @Test
    fun `verify get albums feet return error on failure`() =
        getAlbumsFeedUseCaseTestSkeleton(
            expectedResult = Result.Error(IOException())
        )

    private fun getAlbumsFeedUseCaseTestSkeleton(
        expectedResult: Result<AlbumsFeed>,
    ) = runTest {
        //Given
        coEvery { repository.getAlbumsFeed(any(), any()) } returns expectedResult

        //When
        val result = getAlbumsFeedUseCase()

        //Then
        coVerify {
            repository.getAlbumsFeed("us", 100)
        }
        assertEquals(/* expected = */ expectedResult, /* actual = */ result)
    }
}