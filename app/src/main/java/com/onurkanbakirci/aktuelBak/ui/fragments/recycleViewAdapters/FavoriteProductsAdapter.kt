package com.onurkanbakirci.aktuelBak.ui.fragments.recycleViewAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.onurkanbakirci.aktuelBak.R
import com.onurkanbakirci.aktuelBak.data.model.Product
import com.onurkanbakirci.aktuelBak.databinding.FavProductOverviewBinding
import com.onurkanbakirci.aktuelBak.ui.fragments.FavoriteProductViewModel

class FavoriteProductsAdapter( val mProducts : List<Product>,val mContext: Context): RecyclerView.Adapter<FavoriteProductsAdapter.FavProductsViewHolder>() {

    inner class FavProductsViewHolder( val recycleViewFavProductOverviewBinding: FavProductOverviewBinding):
        RecyclerView.ViewHolder(recycleViewFavProductOverviewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : FavProductsViewHolder{

        val favProductViewModel = FavoriteProductViewModel(mContext)

        val favProductsViewHolder = FavProductsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.fav_product_overview,
                parent,
                false
            )
        )
        favProductsViewHolder.recycleViewFavProductOverviewBinding.favProductViewModel = favProductViewModel

        return favProductsViewHolder
    }

    override fun getItemCount(): Int {
        return mProducts.size
    }

    override fun onBindViewHolder(holder: FavProductsViewHolder, position: Int) {
        holder.recycleViewFavProductOverviewBinding.productFav  = mProducts[position]
    }
}