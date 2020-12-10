package com.onurkanbakirci.aktuelBak.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.onurkanbakirci.aktuelBak.R
import com.onurkanbakirci.aktuelBak.ui.fragments.*
import com.onurkanbakirci.aktuelBak.ui.fragments.catalog.CatalogFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import android.content.Intent
import android.widget.Toast
import com.onurkanbakirci.aktuelBak.BuildConfig

class MainActivityViewModel (var mContext : Context) : ViewModel() ,NavigationView.OnNavigationItemSelectedListener,
    BottomNavigationView.OnNavigationItemSelectedListener{

    companion object {
        var currentTagName :String?=LastProductsFragment::class.java.simpleName
    }

    var icDrawer = ObservableField(Drawable.createFromStream(mContext.assets.open("logo/Group 7 (1).png"),null))

    var mIDrawer: IDrawer? = null

    fun toggleBtn(view : View){
        mIDrawer?.checkDrawerStatus()
    }

    /**
     * drawer navigation view
     */
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.drawerClose->{}
            R.id.drawerHome->{
                lastProductFragmentTransaction()
            }
            R.id.drawerCatalog->{
                catalogFragmentTransaction()
            }
            R.id.drawerSearchProducts->{
                marketFragmentTransaction()
            }
            R.id.drawerStarProducts->{
                starProductFragmentTransaction()
            }
            R.id.drawerStarCatalogs->{
                searchProductFragmentTransaction()
            }
            R.id.drawerInfo->{
                infoFragmentTransaction()
            }
            R.id.drawerShare->{
                try {
                    val shareIntent = Intent(Intent.ACTION_SEND)
                    shareIntent.type = "text/plain"
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Aktüel Bak - Aktüel Ürün ve Kataloglar")
                    //shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("android.resource://" + mContext.packageName
                      //      + "/drawable/" + "ic_app_img"))
                    var shareMessage = "\nLet me recommend you this application\n\n"
                    shareMessage =
                        shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n"
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                    mContext.startActivity(Intent.createChooser(shareIntent, "Seçiniz"))
                } catch (e: Exception) {
                    Toast.makeText(mContext,mContext.resources.getString(R.string.con_err_str),Toast.LENGTH_LONG).show()
                }
            }
        }
        mIDrawer?.closeDrawer()
        return true
    }
    /**
     * bottom navigation view
     */
    fun onBottomMenuClicked(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.bottom_nav_home->{
                lastProductFragmentTransaction()
            }
            R.id.bottom_nav_catalog->{
                catalogFragmentTransaction()
            }
            R.id.bottom_nav_search_products->{
                marketFragmentTransaction()
            }
            R.id.bottom_nav_fav_catalogs->{
                searchProductFragmentTransaction()
            }
            R.id.bottom_nav_star_products->{
                starProductFragmentTransaction()
            }
        }
        return false
    }
    fun lastProductFragmentTransaction(){
        if((mContext as AppCompatActivity).supportFragmentManager.findFragmentByTag(LastProductsFragment::class.java.simpleName)==null){
            (mContext as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container,LastProductsFragment(),LastProductsFragment::class.java.simpleName)
                .addToBackStack(LastProductsFragment::class.java.simpleName)
                .commit()
        }
        else{
            (mContext as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                .hide((mContext as AppCompatActivity).supportFragmentManager.findFragmentByTag(currentTagName)!!)
                .show((mContext as AppCompatActivity).supportFragmentManager.findFragmentByTag(LastProductsFragment::class.java.simpleName)!!)
                .addToBackStack(null)
                .commit()
        }
        currentTagName = (LastProductsFragment::class.java.simpleName)
    }
    fun catalogFragmentTransaction(){
        if((mContext as AppCompatActivity).supportFragmentManager.findFragmentByTag(CatalogFragment::class.java.simpleName)==null){
            (mContext as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                //.setCustomAnimations(R.anim.fade_in,R.anim.fade_out)
                .add(R.id.fragment_container,CatalogFragment(),CatalogFragment::class.java.simpleName)
                .hide((mContext as AppCompatActivity).supportFragmentManager.findFragmentByTag(currentTagName)!!)
                .addToBackStack(CatalogFragment::class.java.simpleName)
                .commit()
        }else{
            (mContext as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                .hide((mContext as AppCompatActivity).supportFragmentManager.findFragmentByTag(currentTagName)!!)
                .show((mContext as AppCompatActivity).supportFragmentManager.findFragmentByTag(CatalogFragment::class.java.simpleName)!!)
                .addToBackStack(null)
                .commit()
        }
        currentTagName =(CatalogFragment::class.java.simpleName)
    }
    fun marketFragmentTransaction(){
        if((mContext as AppCompatActivity).supportFragmentManager.findFragmentByTag(MarketFragment::class.java.simpleName)==null){
            (mContext as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                //.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragment_container,MarketFragment(),MarketFragment::class.java.simpleName)
                .hide((mContext as AppCompatActivity).supportFragmentManager.findFragmentByTag(currentTagName)!!)
                .addToBackStack(null)
                .commit()
        }else{
            (mContext as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                .hide((mContext as AppCompatActivity).supportFragmentManager.findFragmentByTag(currentTagName)!!)
                .show((mContext as AppCompatActivity).supportFragmentManager.findFragmentByTag(MarketFragment::class.java.simpleName)!!)
                .addToBackStack(null)
                .commit()
        }
        currentTagName =(MarketFragment::class.java.simpleName)
    }
    fun searchProductFragmentTransaction(){
        if((mContext as AppCompatActivity).supportFragmentManager.findFragmentByTag(FavCatalogFragment::class.java.simpleName)==null){
            (mContext as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                //.setCustomAnimations(R.anim.fade_in,R.anim.fade_out)
                .add(R.id.fragment_container,FavCatalogFragment(),FavCatalogFragment::class.java.simpleName)
                .hide((mContext as AppCompatActivity).supportFragmentManager.findFragmentByTag(currentTagName)!!)
                .addToBackStack(null)
                .commit()
        }else{
            (mContext as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                .hide((mContext as AppCompatActivity).supportFragmentManager.findFragmentByTag(currentTagName)!!)
                .show((mContext as AppCompatActivity).supportFragmentManager.findFragmentByTag(FavCatalogFragment::class.java.simpleName)!!)
                .addToBackStack(null)
                .commit()
        }
        currentTagName =(FavCatalogFragment::class.java.simpleName)
    }
    fun starProductFragmentTransaction(){
        if((mContext as AppCompatActivity).supportFragmentManager.findFragmentByTag(FavoriteProductFragment::class.java.simpleName)==null)
            (mContext as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                //.setCustomAnimations(R.anim.fade_in,R.anim.fade_out)
                .add(R.id.fragment_container,FavoriteProductFragment(),FavoriteProductFragment::class.java.simpleName)
                .hide((mContext as AppCompatActivity).supportFragmentManager.findFragmentByTag(currentTagName)!!)
                .addToBackStack(FavoriteProductFragment::class.java.simpleName)
                .commit()
        else{
            (mContext as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                //.setCustomAnimations(R.anim.fade_in,R.anim.fade_out)
                .hide((mContext as AppCompatActivity).supportFragmentManager.findFragmentByTag(currentTagName)!!)
                .show((mContext as AppCompatActivity).supportFragmentManager.findFragmentByTag(FavoriteProductFragment::class.java.simpleName)!!)
                .commit()
        }
        currentTagName =(FavoriteProductFragment::class.java.simpleName)
    }
    fun infoFragmentTransaction(){
        if((mContext as AppCompatActivity).supportFragmentManager.findFragmentByTag(InfoFragment::class.java.simpleName)==null)
            (mContext as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                //.setCustomAnimations(R.anim.fade_in,R.anim.fade_out)
                .add(R.id.fragment_container,InfoFragment(),InfoFragment::class.java.simpleName)
                .hide((mContext as AppCompatActivity).supportFragmentManager.findFragmentByTag(currentTagName)!!)
                .addToBackStack(InfoFragment::class.java.simpleName)
                .commit()
        else{
            (mContext as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                //.setCustomAnimations(R.anim.fade_in,R.anim.fade_out)
                .hide((mContext as AppCompatActivity).supportFragmentManager.findFragmentByTag(currentTagName)!!)
                .show((mContext as AppCompatActivity).supportFragmentManager.findFragmentByTag(InfoFragment::class.java.simpleName)!!)
                .commit()
        }
        currentTagName =(InfoFragment::class.java.simpleName)
    }
}