package com.onurkanbakirci.aktuelBak.data.db

import androidx.room.*
import com.onurkanbakirci.aktuelBak.data.db.entities.FavProduct
import io.reactivex.*

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun InsertFavProduct(product: FavProduct) : Completable

    @Query("SELECT * FROM favProducts")
    fun GetAllFavProducts() : Observable<List<FavProduct>>

    @Query("SELECT * FROM favProducts WHERE name=:productName")
    fun GetProductByName(productName : String) : Single<FavProduct>

    @Query("DELETE FROM favProducts WHERE name = :name")
    fun DeleteFavProduct(name: String) : Completable

}