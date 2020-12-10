package com.onurkanbakirci.aktuelBak.ui

interface IDrawer {
    fun openDrawer()
    fun closeDrawer()
    fun checkDrawerStatus()

    fun showProgress()
    fun hideProgress()
}