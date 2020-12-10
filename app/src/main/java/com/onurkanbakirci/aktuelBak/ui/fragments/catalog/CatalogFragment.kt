package com.onurkanbakirci.aktuelBak.ui.fragments.catalog

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
import com.onurkanbakirci.aktuelBak.databinding.CatalogFragmentBinding
import com.onurkanbakirci.aktuelBak.ui.fragments.recycleViewAdapters.CatalogAdapter
import com.onurkanbakirci.aktuelBak.ui.fragments.recycleViewAdapters.CatalogHakmarAdapter
import kotlinx.android.synthetic.main.catalog_fragment.*

class CatalogFragment : Fragment() {

    private var mFragmentViewModel: CatalogFragmentViewModel? = null
    private var mCatalogFragmentBinding : CatalogFragmentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      /*  mFragmentViewModel =
            ViewModelProviders.of(requireActivity(),
                CatalogFragmentViewModelFactory(
                    requireContext()
                )
            ).get(CatalogFragmentViewModel::class.java)

       */
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mFragmentViewModel =
            ViewModelProviders.of(requireActivity(),
                CatalogFragmentViewModelFactory(
                    requireContext()
                )
            ).get(CatalogFragmentViewModel::class.java)
        mCatalogFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.catalog_fragment,container,false)
        mCatalogFragmentBinding?.catalogFragmentViewModel = mFragmentViewModel
        mFragmentViewModel?.fetchCatalogs()
        return mCatalogFragmentBinding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mFragmentViewModel?.mCatalogBIM?.observe(viewLifecycleOwner, Observer {

            catalogs->
                recycle_view_catalog_bim.also {
                    it.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
                    it.setHasFixedSize(true)
                    it.adapter=
                        CatalogAdapter(
                            catalogs,requireContext()
                        )
                    mFragmentViewModel?.visibilityOfBIMCatProgress?.set(8)
                }
        }
        )
        /*
        mFragmentViewModel?.mCatalogA101?.observe(viewLifecycleOwner, Observer {
                catalogs->
            recycle_view_catalog_a101.also {
                it.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
                it.setHasFixedSize(true)
                it.adapter=
                    CatalogA101Adapter(
                        catalogs,requireContext()
                    )
                mFragmentViewModel?.visibilityOfA101CatProgress?.set(8)
            }
        }
    )
         */
        mFragmentViewModel?.mCatalogHakmar?.observe(viewLifecycleOwner, Observer {
                catalogs->
            recycle_view_catalog_hakmar.also {
                it.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
                it.setHasFixedSize(true)
                it.adapter=
                    CatalogHakmarAdapter(
                        catalogs,requireContext()
                    )
                mFragmentViewModel?.visibilityOfHAKMARCatProgress?.set(8)
                }
            }
        )
    }
}