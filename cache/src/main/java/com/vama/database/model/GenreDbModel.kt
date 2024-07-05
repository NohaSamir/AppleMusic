package com.vama.database.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class GenreDbModel : RealmObject, DatabaseModel {
    @PrimaryKey
    var genreId: String = ""
    var name: String = ""
    var url: String = ""
}