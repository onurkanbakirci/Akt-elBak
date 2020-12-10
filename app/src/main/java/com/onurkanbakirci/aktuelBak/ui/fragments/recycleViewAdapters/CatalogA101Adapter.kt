package com.onurkanbakirci.aktuelBak.ui.fragments.recycleViewAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.onurkanbakirci.aktuelBak.R
import com.onurkanbakirci.aktuelBak.data.model.Catalog
import com.onurkanbakirci.aktuelBak.databinding.CatalogOverviewBinding
import com.onurkanbakirci.aktuelBak.ui.fragments.catalog.CatalogFragmentViewModel

class CatalogA101Adapter (var mCatalogs:List<Catalog>,var mContext: Context): RecyclerView.Adapter<CatalogA101Adapter.CatalogViewHolder>() {

    inner class CatalogViewHolder( val recyleViewCatalogBinding : CatalogOverviewBinding):
        RecyclerView.ViewHolder(recyleViewCatalogBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :CatalogViewHolder{

        val catalogDetail = CatalogFragmentViewModel(mContext)

        val catalogViewHolder =
            CatalogViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.catalog_overview,
                    parent,
                    false
                )
            )
        catalogViewHolder.recyleViewCatalogBinding.catalogFragmentViewModel=catalogDetail
        return catalogViewHolder
    }

    override fun getItemCount(): Int {
        return mCatalogs.size
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        holder.recyleViewCatalogBinding.catalogs = mCatalogs[position]
    }

}