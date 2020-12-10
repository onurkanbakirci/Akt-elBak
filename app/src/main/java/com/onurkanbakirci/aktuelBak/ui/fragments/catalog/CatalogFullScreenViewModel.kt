package com.onurkanbakirci.aktuelBak.ui.fragments.catalog

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CatalogFullScreenViewModel(val mContext: Context):ViewModel() {

    val img  = MutableLiveData<String>()

}