package com.vama.data.mapper.databasemapper

import com.vama.database.model.DatabaseModel
import com.vama.domain.model.DomainModel

interface DatabaseMapper<DB : DatabaseModel, D : DomainModel> {
    fun DB.mapFromDatabaseModel(): D {
        throw UnsupportedOperationException()
    }

    fun D.mapToDatabaseModel(): DB {
        throw UnsupportedOperationException()
    }
}