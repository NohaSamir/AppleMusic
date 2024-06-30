package com.vama.applemusic.ui.mapper

import com.vama.applemusic.ui.mapper.AlbumDomainMapper.mapFromDomainModel
import com.vama.applemusic.ui.model.AlbumsFeedUiModel
import com.vama.domain.model.AlbumsFeed
import kotlinx.collections.immutable.toPersistentList

object AlbumsFeedDomainMapper : DomainMapper<AlbumsFeed, AlbumsFeedUiModel> {
    override fun AlbumsFeed.mapFromDomainModel(): AlbumsFeedUiModel {
        return AlbumsFeedUiModel(
            feeds = feeds.map { it.mapFromDomainModel() }.toPersistentList(),
            copyright = copyright
        )
    }
}