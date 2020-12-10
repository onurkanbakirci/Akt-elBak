package com.onurkanbakirci.aktuelBak.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.onurkanbakirci.aktuelBak.R
import com.onurkanbakirci.aktuelBak.databinding.FavCatalogFragmentBinding
import com.onurkanbakirci.aktuelBak.databinding.FavCatalogOverviewBinding
import com.onurkanbakirci.aktuelBak.ui.fragments.recycleViewAdapters.FavoriteCatalogAdapter
import kotlinx.android.synthetic.main.fav_catalog_fragment.*

class FavCatalogFragment : Fragment() {

    private var mFavCatalogViewModel : FavCatalogViewModel? = null

    private var mFavCatalogFragmentBinding : FavCatalogFragmentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFavCatalogViewModel =
                ViewModelProviders.of(requireActivity(),FavCatalogViewModelFactory(requireContext())).get(FavCatalogViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mFavCatalogFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fav_catalog_fragment,container,false)
        mFavCatalogFragmentBinding?.favCatalogViewModel = mFavCatalogViewModel
        return mFavCatalogFragmentBinding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var divider = DividerItemDecoration(activity, DividerItemDecoration.HORIZONTAL)
        divider.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.layer)!!)
        mFavCatalogViewModel?.mFavoriteCatalogs?.observe(viewLifecycleOwner, Observer { catalogs ->
            recycle_view_fav_catalogs.also {
                it.layoutManager = GridLayoutManager(requireContext(),2)
                it.setHasFixedSize(true)
                it.addItemDecoration(divider)
                it.adapter=
                    FavoriteCatalogAdapter(catalogs,requireContext())
                if(catalogs.size.equals(0)){
                    mFavCatalogViewModel?.fav_catalog_not_found?.set(0)
                }else{
                    mFavCatalogViewModel?.fav_catalog_not_found?.set(8)
                }
            }
        })
    }
}