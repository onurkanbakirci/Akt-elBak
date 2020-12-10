package com.onurkanbakirci.aktuelBak.data.repository

import com.onurkanbakirci.aktuelBak.data.db.entities.FavProduct
import com.onurkanbakirci.aktuelBak.data.db.ProductDao

class FavProductRepository(private val prodcutDao : ProductDao) {

    fun addProduct(product : FavProduct){
        prodcutDao.InsertFavProduct(product)
    }

    fun deleteProduct(productName: String){
        prodcutDao.DeleteFavProduct(productName)
    }

}