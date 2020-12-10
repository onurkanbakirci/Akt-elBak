package com.onurkanbakirci.aktuelBak.ui.fragments.catalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.onurkanbakirci.aktuelBak.R
import com.onurkanbakirci.aktuelBak.databinding.ActivityCatalogFullScreenBinding

class CatalogFullScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val databinding : ActivityCatalogFullScreenBinding = DataBindingUtil
            .setContentView(this,R.layout.activity_catalog_full_screen)
        val viewModel = ViewModelProviders.of(this,CatalogFullScreenActivityViewModelFactory(this)).get(CatalogFullScreenViewModel::class.java)

        val intent = intent
        viewModel.img.value = intent.getStringExtra("bannerURL")

        databinding.catalogFullScreen = viewModel

    }
}
