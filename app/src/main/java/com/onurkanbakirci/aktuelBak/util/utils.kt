package com.onurkanbakirci.aktuelBak.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.onurkanbakirci.aktuelBak.ui.MainActivity

fun Context.toast(message: String){
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
}
fun Context.goToMainActivity(context: Context){
    context.startActivity(Intent(context, MainActivity::class.java))
}
fun Context.goToFullScreenBannerActivity(context: Context){
    context.startActivity(Intent())
}
fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}