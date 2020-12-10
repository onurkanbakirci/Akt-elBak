package com.onurkanbakirci.aktuelBak.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.onurkanbakirci.aktuelBak.data.db.entities.FavCatalog
import com.onurkanbakirci.aktuelBak.data.db.entities.FavProduct

@Database(entities = [FavProduct::class,FavCatalog::class], version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun catalogDao(): CatalogDao

    companion object {

        @Volatile
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "appDB")
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}