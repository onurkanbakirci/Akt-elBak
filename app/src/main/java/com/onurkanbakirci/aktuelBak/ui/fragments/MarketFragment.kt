package com.onurkanbakirci.aktuelBak.ui.fragments

import android.os.Bundle
import android.view.KeyEvent
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
import com.onurkanbakirci.aktuelBak.databinding.MarketFragmentBinding
import com.onurkanbakirci.aktuelBak.ui.fragments.recycleViewAdapters.SearchProductAdapter
import kotlinx.android.synthetic.main.market_fragment.*

class MarketFragment : Fragment() {


    private var mMarketViewModel : MarketViewModel? = null
    private var mmarketFragmentBinding : MarketFragmentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // mMarketViewModel =
         //       ViewModelProviders.of(requireActivity(),MarketViewModelFactory(requireContext())).get(MarketViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mMarketViewModel =
            ViewModelProviders.of(requireActivity(),MarketViewModelFactory(requireContext())).get(MarketViewModel::class.java)

        mmarketFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.market_fragment,container,false)
        mmarketFragmentBinding!!.marketViewModel = mMarketViewModel
        mmarketFragmentBinding!!.inpText.setImeActionLabel("Ara", KeyEvent.KEYCODE_ENTER)
        return mmarketFragmentBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var divider = DividerItemDecoration(activity, DividerItemDecoration.HORIZONTAL)
        divider.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.layer)!!)
        mMarketViewModel?.mProductsByName?.removeObservers(this)
        mMarketViewModel?.mProductsByName?.observe(viewLifecycleOwner, Observer { products ->
            search_result_recycle_view.also {
                it.layoutManager = GridLayoutManager(requireContext(),2)
                it.setHasFixedSize(true)
                it.addItemDecoration(divider)
                if(products.isNotEmpty()){
                    mMarketViewModel?.notFounTextVisibility?.set(8)
                    mMarketViewModel?.searchResultRecycleViewVisibility?.set(0)
                    it.adapter=
                        SearchProductAdapter(products,requireContext())
                }else{
                    mMarketViewModel?.notFounTextVisibility?.set(0)
                    mMarketViewModel?.searchResultRecycleViewVisibility?.set(8)
                }
            }
        mMarketViewModel?.waitingProgress?.set(8)
        mMarketViewModel?.searchResultRecycleViewVisibility?.set(0)
        mMarketViewModel?.search_product_visibility?.set(8)
        })
    }
}