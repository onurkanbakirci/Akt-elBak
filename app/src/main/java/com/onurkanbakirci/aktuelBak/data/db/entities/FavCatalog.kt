package com.onurkanbakirci.aktuelBak.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favCatalogs")
class FavCatalog (

    @PrimaryKey(autoGenerate = true)
    var uID:Int?=null,

    @ColumnInfo(name = "date")
    var date : String ? =null,

    @ColumnInfo(name = "image")
    var cImage : String ? = null,

    @ColumnInfo(name = "market")
    var marketName : String ? = null
)