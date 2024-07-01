package com.vama.remote.service

import com.vama.remote.model.AlbumsFeedResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumService {

    @GET("{country}/music/most-played/{page-size}/albums.json")
    suspend fun getMostPlayedAlbumsFeed(
        @Path("country") country: String,
        @Path("page-size") pageSize: Int
    ): AlbumsFeedResponse
}