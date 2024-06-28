package com.vama.domain.usecase

import com.vama.domain.model.Album
import com.vama.domain.model.Result
import com.vama.domain.repository.AlbumRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class GetAlbumByIdUseCaseTest {

    @MockK
    lateinit var repository: AlbumRepository

    private lateinit var getAlbumByIdUseCase: GetAlbumByIdUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getAlbumByIdUseCase = GetAlbumByIdUseCase(repository)
    }

    @Test
    fun `verify get albums feet return the top 100 music albums in the US on success`() {
        val album = mockk<Album>(relaxed = true)

        getAlbumByIdUseCaseTestSkeleton(
            expectedResult = Result.Success(album)
        )

    }

    @Test
    fun `verify get albums feet return error on failure`() =
        getAlbumByIdUseCaseTestSkeleton(
            expectedResult = Result.Error(IOException())
        )

    private fun getAlbumByIdUseCaseTestSkeleton(
        givenId: String = "id",
        expectedResult: Result<Album>,
    ) = runTest {
        //Given
        coEvery { repository.getAlbum(givenId) } returns expectedResult

        //When
        val result = getAlbumByIdUseCase(givenId)

        //Then
        coVerify {
            repository.getAlbum(givenId)
        }
        assertEquals(/* expected = */ expectedResult, /* actual = */ result)
    }
}