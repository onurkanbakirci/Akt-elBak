package com.onurkanbakirci.aktuelBak.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProviders
import com.onurkanbakirci.aktuelBak.R
import com.onurkanbakirci.aktuelBak.databinding.ActivityMainBinding
import com.onurkanbakirci.aktuelBak.ui.fragments.LastProductsFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() ,IDrawer{


    private lateinit var mDrawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataBinding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this,MainActivityViewModelFactory(this)).get(MainActivityViewModel::class.java)
        dataBinding.mainActivityDataBinding = viewModel
        mDrawerLayout = dataBinding.mainActivityDrawerLayout
        viewModel.mIDrawer = this

        supportFragmentManager.beginTransaction().add(R.id.fragment_container, LastProductsFragment(),LastProductsFragment::class.java.simpleName).commit()

    }

    override fun openDrawer() {
        mDrawerLayout.openDrawer(GravityCompat.END)
    }

    override fun closeDrawer() {
        mDrawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun checkDrawerStatus() {
        if (!mDrawerLayout.isDrawerOpen(GravityCompat.START))
            mDrawerLayout.openDrawer(GravityCompat.START)
        else
            mDrawerLayout.closeDrawer(GravityCompat.END)
    }

    override fun showProgress() {
        progress_bar.visibility=View.VISIBLE
    }

    override fun hideProgress() {
        progress_bar.visibility=View.INVISIBLE
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}
