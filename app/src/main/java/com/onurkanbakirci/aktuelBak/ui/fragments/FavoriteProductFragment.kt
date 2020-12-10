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
import androidx.recyclerview.widget.RecyclerView
import com.onurkanbakirci.aktuelBak.R
import com.onurkanbakirci.aktuelBak.databinding.FavoriteProductFragmentBinding
import com.onurkanbakirci.aktuelBak.ui.fragments.recycleViewAdapters.FavoriteProductsAdapter
import kotlinx.android.synthetic.main.favorite_product_fragment.*

class FavoriteProductFragment : Fragment() {

    private var mFavoriteProductViewModel : FavoriteProductViewModel? = null

    private var mFavoriteProductFragmentBinding : FavoriteProductFragmentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFavoriteProductViewModel =
                ViewModelProviders.of(requireActivity(),FavoriteProductViewModelFactory(requireContext())).get(FavoriteProductViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mFavoriteProductFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.favorite_product_fragment,container,false)
        mFavoriteProductFragmentBinding?.favoriteProductViewModel = mFavoriteProductViewModel
        return mFavoriteProductFragmentBinding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var divider = DividerItemDecoration(activity,DividerItemDecoration.HORIZONTAL)
        divider.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.layer)!!)
        mFavoriteProductViewModel?.mFavoriteProducts?.observe(viewLifecycleOwner, Observer { products ->
            recycle_view_fav_products.also {
                it.layoutManager = GridLayoutManager(requireContext(),2)
                it.addItemDecoration(divider)
                it.setHasFixedSize(true)
                it.adapter=
                    FavoriteProductsAdapter(products,requireContext())
                if(products.size.equals(0)){
                    mFavoriteProductViewModel?.fav_product_not_found?.set(0)
                }else{
                    mFavoriteProductViewModel?.fav_product_not_found?.set(8)
                }
            }
        }
        )
    }
}