package com.vama.database.model

import io.realm.kotlin.ext.realmSetOf
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.RealmSet
import io.realm.kotlin.types.annotations.PrimaryKey

class AlbumDbModel : RealmObject, DatabaseModel {
    @PrimaryKey
    var id: String = ""
    var name: String = ""
    var artistName: String = ""
    var artworkUrl: String = ""
    var releaseDate: String = ""
    var genres: RealmSet<GenreDbModel> = realmSetOf()
    var url: String = ""
}