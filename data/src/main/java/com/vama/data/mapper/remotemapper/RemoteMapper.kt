package com.vama.data.mapper.remotemapper

import com.vama.domain.model.DomainModel
import com.vama.remote.model.RemoteModel

interface RemoteMapper<R : RemoteModel, D : DomainModel> {
    fun R.mapFromRemoteModel(): D {
        throw UnsupportedOperationException()
    }

    fun D.mapToRemoteModel(): R {
        throw UnsupportedOperationException()
    }
}