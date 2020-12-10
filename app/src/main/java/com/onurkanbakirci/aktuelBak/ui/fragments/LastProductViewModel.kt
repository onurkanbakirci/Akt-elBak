package com.onurkanbakirci.aktuelBak.ui.fragments

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.onurkanbakirci.aktuelBak.data.model.Product
import com.onurkanbakirci.aktuelBak.data.repository.ProductRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import androidx.lifecycle.MutableLiveData
import com.onurkanbakirci.aktuelBak.data.db.AppDatabase
import com.onurkanbakirci.aktuelBak.data.db.entities.FavProduct
import io.reactivex.*
import kotlinx.coroutines.runBlocking

class LastProductViewModel (val mContext: Context): ViewModel(){

    var mProducts       = MutableLiveData<List<Product>>()
    var mProductsHakmar = MutableLiveData<List<Product>>()
    //var mProductsA101  = MutableLiveData<List<Product>>()
    var mProductsKaraca  = MutableLiveData<List<Product>>()
    var mProductsEnglishHome  = MutableLiveData<List<Product>>()
    var mProductsMadameCoco  = MutableLiveData<List<Product>>()

   // var resIDA101 :Drawable?=null
    var resIDBIM :Drawable?=null
    var resIDHAKMAR :Drawable?=null
    var resIDKARACA:Drawable?=null
    var resIDENGLISHHOME:Drawable?=null
    var resIDMADAMECOCO:Drawable?=null

    var progressVisibileBIM =ObservableField(0)
    var progressVisibileHAKMAR=ObservableField(0)
    var progressVisibileA101=ObservableField(0)
    var progressVisibileKARACA=ObservableField(0)
    var progressVisibileENGLISHHOME=ObservableField(0)
    var progressVisibileMADAMECOCO=ObservableField(0)
    var mainVisibility=ObservableField(0)
    var errorVisibility = ObservableField(8)

    var progressMain:MutableLiveData<Int>?=null

    init {
        progressMain?.value=0

        resIDBIM = Drawable.createFromStream(mContext.assets.open("marketLogos/BIM.png"),null)
        //resIDA101 = Drawable.createFromStream(mContext.assets.open("marketLogos/A101.png"),null)
        resIDHAKMAR = Drawable.createFromStream(mContext.assets.open("marketLogos/HAKMAR.png"),null)
        resIDKARACA = Drawable.createFromStream(mContext.assets.open("marketLogos/karaca.png"),null)
        resIDENGLISHHOME = Drawable.createFromStream(mContext.assets.open("marketLogos/english-home.png"),null)
        resIDMADAMECOCO = Drawable.createFromStream(mContext.assets.open("marketLogos/madame-coco.png"),null)
    }

    fun fetchProducts(){
        runBlocking{
            ProductRepository().getProducts()
            .subscribeOn(Schedulers.io())
            .take(1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<Product>> {
                override fun onComplete() {
                }
                override fun onSubscribe(d: Disposable) {
                }
                override fun onNext(t: List<Product>) {
                    mProducts.postValue(t)
                    errorVisibility.set(8)
                    mainVisibility.set(0)
                }
                override fun onError(e: Throwable) {
                    Toast.makeText(mContext,"İnternet Bağlantınızı Kontrol Ediniz!",Toast.LENGTH_LONG).show()
                    mainVisibility.set(8)
                    errorVisibility.set(0)
                }
            })
        }
        runBlocking{
            ProductRepository().getHAKMARProducts()
            .subscribeOn(Schedulers.io())
            .take(1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :Observer<List<Product>>{
                override fun onSubscribe(d: Disposable) {
                }
                override fun onNext(t: List<Product>) {
                    mProductsHakmar.postValue(t)
                }
                override fun onError(e: Throwable) {
                }
                override fun onComplete() {
                }
            }
            )
        }
        /*
        runBlocking {
            ProductRepository().getA101Products()
                .subscribeOn(Schedulers.io())
                .take(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<Product>> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: List<Product>) {
                        mProductsA101.postValue(t)
                    }

                    override fun onError(e: Throwable) {
                    }

                    override fun onComplete() {
                    }
                }
                )
        }
         */
        runBlocking{
            ProductRepository().getKARACAProducts()
                .subscribeOn(Schedulers.io())
                .take(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Observer<List<Product>>{
                    override fun onSubscribe(d: Disposable) {
                    }
                    override fun onNext(t: List<Product>) {
                        mProductsKaraca.postValue(t)
                    }
                    override fun onError(e: Throwable) {
                    }
                    override fun onComplete() {
                    }
                }
                )
        }
        runBlocking{
            ProductRepository().getENGLISHHHOMEProducts()
                .subscribeOn(Schedulers.io())
                .take(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Observer<List<Product>>{
                    override fun onSubscribe(d: Disposable) {
                    }
                    override fun onNext(t: List<Product>) {
                        mProductsEnglishHome.postValue(t)
                    }
                    override fun onError(e: Throwable) {
                    }
                    override fun onComplete() {
                    }
                }
                )
        }
        runBlocking{
            ProductRepository().getMADAMECOCOProducts()
                .subscribeOn(Schedulers.io())
                .take(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Observer<List<Product>>{
                    override fun onSubscribe(d: Disposable) {
                    }
                    override fun onNext(t: List<Product>) {
                        mProductsMadameCoco.postValue(t)
                    }
                    override fun onError(e: Throwable) {
                    }
                    override fun onComplete() {
                    }
                }
                )
        }
    }
    fun startClickedEvent(product: Product){
            AppDatabase.getAppDataBase(mContext)
                ?.productDao()
                ?.GetProductByName(product.name!!)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object :SingleObserver<FavProduct>{
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
    fun insertDatabase(product: Product){
        progressMain?.value = 8
        var clickedProduct = FavProduct(null,product.name,product.subtitle,product.price,product.img,product.market)
        AppDatabase.getAppDataBase(mContext)
            ?.productDao()
            ?.InsertFavProduct(clickedProduct)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object :CompletableObserver{
                override fun onComplete() {
                    Toast.makeText(mContext,clickedProduct.name+" favorilere eklendi",Toast.LENGTH_LONG).show()
                    progressMain?.value = 0
                }
                override fun onSubscribe(d: Disposable) {}
                override fun onError(e: Throwable) {
                }
            })
    }
    fun moveToProductDetail(view:View,product: Product){

        ProductDetailFragment.newInstance(  product.name!!,
                                            product.price!!,
                                            product.img!!,
                                            product.market!!)
            .show((mContext as AppCompatActivity).supportFragmentManager, ProductDetailFragment.TAG)
    }

    fun againFetch(view:View){
        fetchProducts()
    }
}