package com.onurkanbakirci.aktuelBak.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.onurkanbakirci.aktuelBak.R
import com.onurkanbakirci.aktuelBak.databinding.LastProductFragmentBinding
import com.onurkanbakirci.aktuelBak.ui.fragments.recycleViewAdapters.*
import kotlinx.android.synthetic.main.last_product_fragment.*

class LastProductsFragment : Fragment(){

    private var mLastProductsViewModel : LastProductViewModel? =null

    private var mLastProductFragmentBinding : LastProductFragmentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       /* mLastProductsViewModel =
            ViewModelProviders.of(requireActivity(),LastProductViewModelFactory(requireContext())).get(LastProductViewModel::class.java)
        mLastProductsViewModel!!.fetchProducts()*/
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mLastProductsViewModel =
            ViewModelProviders.of(requireActivity(),LastProductViewModelFactory(requireContext())).get(LastProductViewModel::class.java)
        mLastProductFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.last_product_fragment,container,false)
        mLastProductFragmentBinding?.lastProductViewModel = mLastProductsViewModel
        mLastProductsViewModel!!.fetchProducts()
        return mLastProductFragmentBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mLastProductsViewModel?.mProducts?.observe(viewLifecycleOwner, Observer { products ->
            recycle_view_product.also {
                it.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
                it.setHasFixedSize(true)
                it.adapter=
                    ProductAdapter(products,requireContext())
                mLastProductsViewModel?.progressVisibileBIM?.set(8)
            }
        })

        mLastProductsViewModel?.mProductsHakmar?.observe(viewLifecycleOwner, Observer { products ->
            recycle_view_product_hakmar.also {
                it.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
                it.setHasFixedSize(true)
                it.adapter=
                    ProductHakmarAdapter(
                        products,requireContext()
                    )
                mLastProductsViewModel?.progressVisibileHAKMAR?.set(8)

            }
        })
        /*
        mLastProductsViewModel?.mProductsA101?.observe(viewLifecycleOwner, Observer { products ->
            recycle_view_product_a101.also {
                it.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
                it.setHasFixedSize(true)
                it.adapter=
                    ProductA101Adapter(
                        products,requireContext()
                    )
                mLastProductsViewModel?.progressVisibileA101?.set(8)
            }
        })
         */

        //karaca products
        mLastProductsViewModel?.mProductsKaraca?.observe(viewLifecycleOwner, Observer { products ->
            recycle_view_product_karaca.also {
                it.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
                it.setHasFixedSize(true)
                it.adapter=
                    ProductKaracaAdapter(
                        products,requireContext()
                    )
                mLastProductsViewModel?.progressVisibileKARACA?.set(8)
            }
        })

        //english home products
        mLastProductsViewModel?.mProductsEnglishHome?.observe(viewLifecycleOwner, Observer { products ->
            recycle_view_product_english_home.also {
                it.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
                it.setHasFixedSize(true)
                it.adapter=
                    ProductEnglishHomeAdapter(
                        products,requireContext()
                    )
                mLastProductsViewModel?.progressVisibileENGLISHHOME?.set(8)
            }
        })

        //madame coco products
        mLastProductsViewModel?.mProductsMadameCoco?.observe(viewLifecycleOwner, Observer { products ->
            recycle_view_product_madame_coco.also {
                it.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
                it.setHasFixedSize(true)
                it.adapter=
                    ProductMadameCocoAdapter(
                        products,requireContext()
                    )
                mLastProductsViewModel?.progressVisibileMADAMECOCO?.set(8)
            }
        })
    }
}