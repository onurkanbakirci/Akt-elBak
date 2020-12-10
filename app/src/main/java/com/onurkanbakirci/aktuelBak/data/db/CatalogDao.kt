package com.onurkanbakirci.aktuelBak.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.onurkanbakirci.aktuelBak.data.db.entities.FavCatalog
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface CatalogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun InsertFavCatalog(catalog: FavCatalog) : Completable

    @Query("SELECT * FROM favCatalogs")
    fun GetAllFavCatalogs() : Observable<List<FavCatalog>>

    @Query("SELECT * FROM favCatalogs WHERE image=:imageUri")
    fun GetCatalogByImageUri(imageUri : String) : Single<FavCatalog>

    @Query("DELETE FROM favCatalogs WHERE image = :imageUrl")
    fun DeleteFavCatalog(imageUrl: String) : Completable
}