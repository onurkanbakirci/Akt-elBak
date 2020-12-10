package com.onurkanbakirci.aktuelBak.ui.fragments

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.onurkanbakirci.aktuelBak.R

class InfoFragment:Fragment() {

    private var sendEmail :LinearLayout?=null
    private var appLogo:ImageView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view:View = inflater.inflate(R.layout.info_fragment,container,false)
        sendEmail = view.findViewById(R.id.sendEmail)
        appLogo = view.findViewById(R.id.app_logo_for_info)
        appLogo?.setImageDrawable(Drawable.createFromStream(requireActivity().assets.open("logo/Group 7 (1).png"),null))
        sendEmail?.setOnClickListener{
            var emailIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + "onurkan_bkrc@hotmail.com"))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Aktüel Ürün ve Kataloglar")
            //emailIntent.putExtra(Intent.EXTRA_TEXT, body)
            startActivity(Intent.createChooser(emailIntent, "Chooser Title"))
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}