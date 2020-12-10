package com.onurkanbakirci.aktuelBak.ui.fragments.catalog

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onurkanbakirci.aktuelBak.data.db.AppDatabase
import com.onurkanbakirci.aktuelBak.data.db.entities.FavCatalog
import com.onurkanbakirci.aktuelBak.data.model.Catalog
import com.onurkanbakirci.aktuelBak.data.repository.CatalogRepository
import io.reactivex.CompletableObserver
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CatalogFragmentViewModel(val mContext:Context) :ViewModel(){

    var mCatalogBIM  =MutableLiveData<List<Catalog>>()
    //var mCatalogA101  =MutableLiveData<List<Catalog>>()
    var mCatalogHakmar  =MutableLiveData<List<Catalog>>()

    //var resIDA101 : Drawable?=null
    var resIDBIM : Drawable?=null
    var resIDHAKMAR : Drawable?=null

    var visibilityOfBIMCatProgress  = ObservableField(0)
    //var visibilityOfA101CatProgress = ObservableField(0)
    var visibilityOfHAKMARCatProgress = ObservableField(0)
    var errorVisibility = ObservableField(8)
    var mainCatVisibility = ObservableField(0)

    init {

        resIDBIM = Drawable.createFromStream(mContext.assets.open("marketLogos/BIM.png"),null)
        //resIDA101 = Drawable.createFromStream(mContext.assets.open("marketLogos/A101.png"),null)
        resIDHAKMAR = Drawable.createFromStream(mContext.assets.open("marketLogos/HAKMAR.png"),null)
    }

    fun fetchCatalogs(){

        CatalogRepository().getBIMCatalogs()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<Catalog>> {
                override fun onComplete() {
                }
                override fun onSubscribe(d: Disposable) {
                }
                override fun onNext(t: List<Catalog>) {
                    mCatalogBIM.postValue(t)
                    mainCatVisibility.set(0)
                    errorVisibility.set(8)
                }
                override fun onError(e: Throwable) {
                    Toast.makeText(mContext,"İnternet Bağlantınızı Kontrol Ediniz!",Toast.LENGTH_LONG).show()
                    errorVisibility.set(0)
                    mainCatVisibility.set(8)
                }
            })
/*
        CatalogRepository().getA101Catalogs()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<Catalog>> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: List<Catalog>) {
                    mCatalogA101.postValue(t)
                }

                override fun onError(e: Throwable) {
                }
            })

 */
        CatalogRepository().getHAKMARCatalogs()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<Catalog>> {
                override fun onComplete() {
                }
                override fun onSubscribe(d: Disposable) {
                }
                override fun onNext(t: List<Catalog>) {
                    mCatalogHakmar.postValue(t)
                }
                override fun onError(e: Throwable) {
                }
            })
    }
    fun fullScreenBanner(image:Catalog){
        val intent = Intent(mContext,CatalogFullScreenActivity::class.java)
        intent.putExtra("bannerURL", image.img)
        mContext.startActivity(intent)
    }
    fun isClickedCatalog(catalog: Catalog){
        AppDatabase.getAppDataBase(mContext)?.catalogDao()
            ?.GetCatalogByImageUri(catalog.img!!)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe(object :SingleObserver<FavCatalog>{
                override fun onSuccess(t: FavCatalog) {
                    Toast.makeText(mContext,"Bu katalog daha önceden favorilere eklendi.",Toast.LENGTH_SHORT).show()
                }
                override fun onSubscribe(d: Disposable) {
                }
                override fun onError(e: Throwable) {
                    insertCatalogToDatabase(catalog)
                }

            })
    }
    fun insertCatalogToDatabase(catalog: Catalog){
        var clickedCatalog=FavCatalog(null,catalog.date,catalog.img,catalog.marketName)
        AppDatabase.getAppDataBase(mContext)
            ?.catalogDao()
            ?.InsertFavCatalog(clickedCatalog)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object :CompletableObserver{
                override fun onComplete() {
                    Toast.makeText(mContext,clickedCatalog.date+" tarihli katalog favorilere eklendi",Toast.LENGTH_SHORT).show()
                }
                override fun onSubscribe(d: Disposable) {}
                override fun onError(e: Throwable) {}
        })
    }
    fun againFetch(view: View){
        fetchCatalogs()
    }
}