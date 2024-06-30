package com.vama.applemusic.ui.mapper

import com.vama.applemusic.ui.model.UiModel
import com.vama.domain.model.DomainModel

interface DomainMapper<D : DomainModel, UI : UiModel> {
    fun D.mapFromDomainModel(): UI {
        throw UnsupportedOperationException()
    }

    fun UI.mapToDomainModel(): D {
        throw UnsupportedOperationException()
    }
}