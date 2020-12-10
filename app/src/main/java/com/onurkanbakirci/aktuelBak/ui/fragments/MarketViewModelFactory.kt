package com.onurkanbakirci.aktuelBak.ui.fragments

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MarketViewModelFactory (val mContext : Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MarketViewModel(mContext) as T
    }
}