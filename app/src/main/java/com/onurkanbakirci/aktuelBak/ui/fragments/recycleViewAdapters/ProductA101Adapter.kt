package com.onurkanbakirci.aktuelBak.ui.fragments.recycleViewAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.onurkanbakirci.aktuelBak.R
import com.onurkanbakirci.aktuelBak.data.model.Product
import com.onurkanbakirci.aktuelBak.databinding.RecycleviewProductOverviewBinding
import com.onurkanbakirci.aktuelBak.ui.fragments.LastProductViewModel

class ProductA101Adapter ( private val mProducts : List<Product>,val mContext: Context): RecyclerView.Adapter<ProductA101Adapter.ProductsViewHolder>() {


    inner class ProductsViewHolder( val recyleViewProductBinding : RecycleviewProductOverviewBinding):
        RecyclerView.ViewHolder(recyleViewProductBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :ProductsViewHolder{
        val LastProductViewModel = LastProductViewModel(mContext)

        var productsViewHolder = ProductsViewHolder(
                DataBindingUtil.inflate<RecycleviewProductOverviewBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.recycleview_product_overview,
                    parent,
                    false
                )

            )
        productsViewHolder.recyleViewProductBinding.lastProductViewModel=LastProductViewModel

        return productsViewHolder
    }

    override fun getItemCount(): Int {
        return mProducts.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.recyleViewProductBinding.products = mProducts[position]
    }
}