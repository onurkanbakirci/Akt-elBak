package com.onurkanbakirci.aktuelBak.ui.fragments.catalog

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CatalogFragmentViewModelFactory (val mContext : Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CatalogFragmentViewModel(mContext) as T
    }
}