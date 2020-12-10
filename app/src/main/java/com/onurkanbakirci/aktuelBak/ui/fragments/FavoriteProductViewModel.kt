package com.onurkanbakirci.aktuelBak.ui.fragments

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onurkanbakirci.aktuelBak.BuildConfig
import com.onurkanbakirci.aktuelBak.data.db.AppDatabase
import com.onurkanbakirci.aktuelBak.data.db.entities.FavProduct
import com.onurkanbakirci.aktuelBak.data.model.Product
import io.reactivex.CompletableObserver
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FavoriteProductViewModel (val mContext:Context): ViewModel() {

    var mFavoriteProducts   = MutableLiveData<List<Product>>()

    var fav_product_not_found = ObservableField(8)


    init {
        getFavProduct()
    }

    fun getFavProduct(){
        AppDatabase.getAppDataBase(mContext)
            ?.productDao()
            ?.GetAllFavProducts()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : Observer<List<FavProduct>> {
                override fun onComplete() {
                }
                override fun onSubscribe(d: Disposable) {
                }
                override fun onNext(t: List<FavProduct>) {
                    var listOfProduct = arrayListOf<Product>()
                    for(product:FavProduct? in t){
                        listOfProduct.add(Product(product?.marketName,product?.name,product?.subtitle,product?.price,product?.pImage))
                    }
                    mFavoriteProducts.postValue(listOfProduct)
                }
                override fun onError(e: Throwable) {
                }
            })
    }
    fun deleteFavProduct(product: Product){
        AppDatabase.getAppDataBase(mContext)
        ?.productDao()
        ?.DeleteFavProduct(product.name!!)
        ?.subscribeOn(Schedulers.io())
        ?.observeOn(AndroidSchedulers.mainThread())
        ?.subscribe(object : CompletableObserver{
            override fun onComplete() {
                Toast.makeText(mContext,product.name+" ürünü favorilerden silindi.",Toast.LENGTH_SHORT).show()
            }
            override fun onSubscribe(d: Disposable) {
            }
            override fun onError(e: Throwable) {
            }
        })
    }
    fun shareFavProduct(product: Product){
        val shareIntent = Intent().apply {
            this.action = Intent.ACTION_SEND
            this.type = "text/plain"
            this.putExtra(Intent.EXTRA_TEXT,"Aktüel Bak - Aktüel Ürün ve Kataloglar"+"\n"+"https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n"+"Market İsmi: "+product.market+"\nÜrün ismi : "+product.name+"\nÜrün fiyatı: "+product.price+"\nÜrün resmi: "+product.img)
           // this.putExtra(Intent.EXTRA_TEXT,product.price)
           // this.putExtra(Intent.EXTRA_TEXT,product.img)
        }
        mContext.startActivity(Intent.createChooser(shareIntent, "Ürünü şununla paylaş"))
    }
    fun moveToProductDetail(view:View,product: Product){
        ProductDetailFragment.newInstance(  product.name!!,
            product.price!!,
            product.img!!,
            product.market!!)
            .show((mContext as AppCompatActivity).supportFragmentManager, ProductDetailFragment.TAG)
    }
}