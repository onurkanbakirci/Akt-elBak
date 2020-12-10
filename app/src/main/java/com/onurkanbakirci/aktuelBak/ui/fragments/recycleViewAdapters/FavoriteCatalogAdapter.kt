package com.onurkanbakirci.aktuelBak.ui.fragments.recycleViewAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.onurkanbakirci.aktuelBak.R
import com.onurkanbakirci.aktuelBak.data.model.Catalog
import com.onurkanbakirci.aktuelBak.databinding.FavCatalogOverviewBinding
import com.onurkanbakirci.aktuelBak.ui.fragments.FavCatalogViewModel

class FavoriteCatalogAdapter (val mCatalogs : List<Catalog>, val mContext: Context): RecyclerView.Adapter<FavoriteCatalogAdapter.FavCatalogsViewHolder>() {

    inner class FavCatalogsViewHolder( val recycleViewFavCatalogOverviewBinding : FavCatalogOverviewBinding):
        RecyclerView.ViewHolder(recycleViewFavCatalogOverviewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : FavCatalogsViewHolder{

        val favCatalogViewModel = FavCatalogViewModel(mContext)

        val favCatalogsViewHolder = FavCatalogsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.fav_catalog_overview,
                parent,
                false
            )
        )
        favCatalogsViewHolder.recycleViewFavCatalogOverviewBinding.favCatalogViewModel = favCatalogViewModel

        return favCatalogsViewHolder
    }

    override fun getItemCount(): Int {
        return mCatalogs.size
    }

    override fun onBindViewHolder(holder: FavCatalogsViewHolder, position: Int) {
        holder.recycleViewFavCatalogOverviewBinding.catalogFav  = mCatalogs[position]
    }
}