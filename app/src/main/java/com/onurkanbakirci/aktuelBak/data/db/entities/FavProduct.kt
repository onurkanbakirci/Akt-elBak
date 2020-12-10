package com.onurkanbakirci.aktuelBak.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favProducts")
class FavProduct (

    @PrimaryKey(autoGenerate = true)
    var uID:Int?=null,

    @ColumnInfo(name = "name")
    var name : String ? = null,

    @ColumnInfo(name = "subtitle")
    var subtitle : String ? =null,

    @ColumnInfo(name = "price")
    var price : String ? =null,

    @ColumnInfo(name = "image")
    var pImage : String ? = null,

    @ColumnInfo(name = "market")
    var marketName : String ? = null

){

}