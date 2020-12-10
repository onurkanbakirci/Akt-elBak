package com.onurkanbakirci.aktuelBak.ui.fragments

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onurkanbakirci.aktuelBak.BuildConfig
import com.onurkanbakirci.aktuelBak.data.db.AppDatabase
import com.onurkanbakirci.aktuelBak.data.db.entities.FavCatalog
import com.onurkanbakirci.aktuelBak.data.model.Catalog
import com.onurkanbakirci.aktuelBak.ui.fragments.catalog.CatalogFullScreenActivity
import io.reactivex.CompletableObserver
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FavCatalogViewModel (var mContext: Context): ViewModel() {

    var mFavoriteCatalogs   = MutableLiveData<List<Catalog>>()

    var fav_catalog_not_found = ObservableField(8)


    init {
        getFavCatalogs()
    }

    fun getFavCatalogs(){
        AppDatabase.getAppDataBase(mContext)
            ?.catalogDao()
            ?.GetAllFavCatalogs()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object :Observer<List<FavCatalog>>{
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: List<FavCatalog>) {
                    var listOfPCatalogs = arrayListOf<Catalog>()
                    for(catalog:FavCatalog? in t){
                        listOfPCatalogs.add(Catalog(catalog?.marketName,catalog?.date,catalog?.cImage))
                    }
                    mFavoriteCatalogs.postValue(listOfPCatalogs)
                }
                override fun onError(e: Throwable) {
                }
            })
    }
    fun fullScreenBanner(image:Catalog){
        val intent = Intent(mContext, CatalogFullScreenActivity::class.java)
        intent.putExtra("bannerURL", image.img)
        mContext.startActivity(intent)
    }

    fun deleteFavProduct(catalog: Catalog){
        AppDatabase.getAppDataBase(mContext)
            ?.catalogDao()
            ?.DeleteFavCatalog(catalog.img!!)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : CompletableObserver{
                override fun onComplete() {
                    Toast.makeText(mContext,catalog.marketName+" "+catalog.date+" tarihli katalog favorilerden silindi.",Toast.LENGTH_SHORT).show()
                }
                override fun onSubscribe(d: Disposable) {
                }
                override fun onError(e: Throwable) {
                }
            })
    }
    fun shareFavProduct(catalog: Catalog){
        val shareIntent = Intent().apply {
            this.action = Intent.ACTION_SEND
            this.type = "text/plain"
            this.putExtra(Intent.EXTRA_TEXT,"Aktüel Bak - Aktüel Ürün ve Kataloglar"+"\n"+"https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n"+"Market İsmi: "+catalog.marketName+"\nKatalog tarihi : "+catalog.date+"\nKatalog resmi: "+catalog.img)
        }
        mContext.startActivity(Intent.createChooser(shareIntent, "Kataloğu şununla paylaş"))
    }
}