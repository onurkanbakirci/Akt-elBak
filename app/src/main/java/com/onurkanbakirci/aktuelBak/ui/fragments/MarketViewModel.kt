package com.onurkanbakirci.aktuelBak.ui.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onurkanbakirci.aktuelBak.BuildConfig
import com.onurkanbakirci.aktuelBak.data.db.AppDatabase
import com.onurkanbakirci.aktuelBak.data.db.entities.FavProduct
import com.onurkanbakirci.aktuelBak.data.model.Product
import com.onurkanbakirci.aktuelBak.data.repository.ProductRepository
import io.reactivex.CompletableObserver
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MarketViewModel (val mContext: Context) : ViewModel() {

    var productName= String()
    var mProductsByName  = MutableLiveData<List<Product>>()

    var waitingProgress = ObservableField(8)

    var notFounTextVisibility = ObservableField(8)
    var search_product_visibility = ObservableField(0)
    var searchResultRecycleViewVisibility = ObservableField(0)

    var edittextText = MutableLiveData<String>()


    fun searchProductByName(view: View,text:String) {

        val inputMethodManager = mContext.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

        waitingProgress.set(0)
        search_product_visibility.set(8)
        notFounTextVisibility.set(8)
        ProductRepository().getProductByName(text)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : io.reactivex.Observer<List<Product>> {
                override fun onComplete() {
                }
                override fun onSubscribe(d: Disposable) {
                }
                override fun onNext(t: List<Product>) {
                    mProductsByName.postValue(t)
                }
                override fun onError(e: Throwable) {
                    Toast.makeText(mContext,"İnternet bağlantınızı kontrol ediniz.",Toast.LENGTH_LONG).show()
                }
            })
    }
    fun shareFavProduct(product: Product){
        val shareIntent = Intent().apply {
            this.action = Intent.ACTION_SEND
            this.type = "text/plain"
            this.putExtra(Intent.EXTRA_TEXT,"Aktüel Bak - Aktüel Ürün ve Kataloglar"+"\n"+"https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n"+"Market İsmi: "+product.market+"\nÜrün ismi : "+product.name+"\nÜrün fiyatı: "+product.price+"\nÜrün resmi: "+product.img)
        }
        mContext.startActivity(Intent.createChooser(shareIntent, "Ürünü şununla paylaş"))
    }
    fun getTextFroInputField(view:View,text:String) {
        searchProductByName(view,text)
    }

    fun moveToProductDetail(view:View,product:Product){
        ProductDetailFragment.newInstance(  product.name!!,
            product.price!!,
            product.img!!,
            product.market!!)
            .show((mContext as AppCompatActivity).supportFragmentManager, ProductDetailFragment.TAG)
    }

    //Android Keyboard'daki arama butonuna basıldığında "searchProductById" methodunun tetikelemek için yazıldı.
    //Adapters.kt ' de tanımlandı.
    val getTextFroInputField: Function2<View,String,Unit> = this::getTextFroInputField

    fun startClickedEvent(product: Product){
        AppDatabase.getAppDataBase(mContext)
            ?.productDao()
            ?.GetProductByName(product.name!!)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : SingleObserver<FavProduct> {
                override fun onSuccess(t: FavProduct) {
                    Toast.makeText(mContext,"Bu ürün daha önceden favorilere eklendi.",Toast.LENGTH_LONG).show()
                }
                override fun onSubscribe(d: Disposable) {
                }
                override fun onError(e: Throwable) {
                    insertDatabase(product)
                }
            })
    }

    fun insertDatabase(product:Product){
        var clickedProduct = FavProduct(null,product.name,product.subtitle,product.price,product.img,product.market)
        AppDatabase.getAppDataBase(mContext)
            ?.productDao()
            ?.InsertFavProduct(clickedProduct)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : CompletableObserver {
                override fun onComplete() {
                    Toast.makeText(mContext,clickedProduct.name+" favorilere eklendi",Toast.LENGTH_LONG).show()
                }
                override fun onSubscribe(d: Disposable) {}
                override fun onError(e: Throwable) {
                }
        })
    }
}