package com.vama.data.mapper.remotemapper

import com.vama.data.mapper.remotemapper.AlbumRemoteMapper.mapFromRemoteModel
import com.vama.domain.model.AlbumsFeed
import com.vama.remote.model.AlbumsFeedResponse

object AlbumsFeedsRemoteMapper : RemoteMapper<AlbumsFeedResponse, AlbumsFeed> {
    override fun AlbumsFeedResponse.mapFromRemoteModel(): AlbumsFeed {
        return AlbumsFeed(
            feeds = results.map { it.mapFromRemoteModel() },
            copyright = copyright
        )
    }
}