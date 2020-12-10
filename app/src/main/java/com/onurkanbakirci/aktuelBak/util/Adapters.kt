package com.onurkanbakirci.aktuelBak.util


import android.graphics.drawable.Drawable
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.onurkanbakirci.aktuelBak.R

@BindingAdapter("image")
fun loadImage(view:ImageView , url: String){
    Glide
        .with(view)
        .load(url)
        //.thumbnail(Glide.with(view.context).load(R.drawable.animated_loading))
        .placeholder(R.drawable.ic_placeholder_image)
        .into(view)
}
@BindingAdapter("marketImage")
fun loadMarketImage(view: ImageView,marketName:String){
    when(marketName){
        "BIM"->{
            view.setImageDrawable(Drawable.createFromStream(view.context.assets.open("marketLogos/BIM.png"),null))
        }
        "A101"->{
            view.setImageDrawable(Drawable.createFromStream(view.context.assets.open("marketLogos/A101.png"),null))
        }
        "HAKMAR"->{
            view.setImageDrawable(Drawable.createFromStream(view.context.assets.open("marketLogos/HAKMAR.png"),null))
        }
        "MADAMECOCO"->{
            view.setImageDrawable(Drawable.createFromStream(view.context.assets.open("marketLogos/madame-coco.png"),null))
        }
        "KARACA"->{
            view.setImageDrawable(Drawable.createFromStream(view.context.assets.open("marketLogos/karaca.png"),null))
        }
        "ENGLISHHOME"->{
            view.setImageDrawable(Drawable.createFromStream(view.context.assets.open("marketLogos/english-home.png"),null))
        }
    }
}
@BindingAdapter("onEditorEnterAction")
fun EditText.onEditorEnterAction(f: Function2<View,String,Unit>?) {

    if (f == null) setOnEditorActionListener(null)
    else setOnEditorActionListener { v, actionId, event ->

        val imeAction = when (actionId) {
            EditorInfo.IME_ACTION_SEARCH -> true
            else -> false
        }

        val keydownEvent = event?.keyCode == KeyEvent.KEYCODE_ENTER
                && event.action == KeyEvent.ACTION_DOWN

        if (imeAction or keydownEvent)
            true.also { f(v,v.editableText.toString()) }
        else false
    }
}
