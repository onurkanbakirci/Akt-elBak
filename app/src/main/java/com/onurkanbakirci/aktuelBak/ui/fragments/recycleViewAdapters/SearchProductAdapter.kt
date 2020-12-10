package com.onurkanbakirci.aktuelBak.ui.fragments.recycleViewAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.onurkanbakirci.aktuelBak.R
import com.onurkanbakirci.aktuelBak.data.model.Product
import com.onurkanbakirci.aktuelBak.databinding.FavProductOverviewBinding
import com.onurkanbakirci.aktuelBak.databinding.MarketFragmentBinding
import com.onurkanbakirci.aktuelBak.databinding.SearchProductRecycleviewBinding
import com.onurkanbakirci.aktuelBak.ui.fragments.MarketViewModel

class SearchProductAdapter (val mProducts : List<Product>, val mContext: Context): RecyclerView.Adapter<SearchProductAdapter.MarketProductsViewHolder>() {

    inner class MarketProductsViewHolder( val recycleViewSearchProductRecycleviewBinding: SearchProductRecycleviewBinding):
        RecyclerView.ViewHolder(recycleViewSearchProductRecycleviewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MarketProductsViewHolder{

        val marketProductViewModel = MarketViewModel(mContext)

        val marketProductsViewHolder = MarketProductsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.search_product_recycleview,
                parent,
                false
            )
        )
        marketProductsViewHolder.recycleViewSearchProductRecycleviewBinding.marketViewModel = marketProductViewModel

        return marketProductsViewHolder
    }

    override fun getItemCount(): Int {
        return mProducts.size
    }

    override fun onBindViewHolder(holder: MarketProductsViewHolder, position: Int) {
        holder.recycleViewSearchProductRecycleviewBinding.productSearch  = mProducts[position]
    }
    override fun getItemViewType(position: Int): Int {
        return position
    }
}