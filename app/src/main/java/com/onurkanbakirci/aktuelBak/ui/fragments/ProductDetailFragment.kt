package com.onurkanbakirci.aktuelBak.ui.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.onurkanbakirci.aktuelBak.R
import com.onurkanbakirci.aktuelBak.ui.fragments.catalog.CatalogFullScreenActivity

class ProductDetailFragment : DialogFragment() {

    private var title: String? = null
    private var message: String? = null
    private var image: String? = null
    private var marketImage: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_TITLE)
            message = it.getString(ARG_MESSAGE)
            image=it.getString(ARG_IMAGE)
            marketImage=it.getString(ARG_MARKET_IMAGE)
        }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val view = getActivity()?.getLayoutInflater()?.inflate(R.layout.product_detail_fragment, null)
        view?.findViewById<TextView>(R.id.textOfProductName)?.setText(title)
        view?.findViewById<TextView>(R.id.textOfProductPrice)?.setText(message)
        Glide.with(requireContext()).load(image).into(view?.findViewById(R.id.productDetailImage)!!)

        //when image is clicked , show it as a big screen
        view?.findViewById<ImageView>(R.id.productDetailImage)?.setOnClickListener {
            val intent = Intent(requireContext(), CatalogFullScreenActivity::class.java)
            intent.putExtra("bannerURL",image)
            requireContext().startActivity(intent)
        }

        when(marketImage){
            "BIM"-> Glide.with(requireContext()).load(Drawable.createFromStream(requireContext().assets.open("marketLogos/BIM.png"),null)).into(view?.findViewById(R.id.marketLogo)!!)
            "HAKMAR"-> Glide.with(requireContext()).load(Drawable.createFromStream(requireContext().assets.open("marketLogos/HAKMAR.png"),null)).into(view?.findViewById(R.id.marketLogo)!!)
            "A101"-> Glide.with(requireContext()).load(Drawable.createFromStream(requireContext().assets.open("marketLogos/A101.png"),null)).into(view?.findViewById(R.id.marketLogo)!!)
            "KARACA"-> Glide.with(requireContext()).load(Drawable.createFromStream(requireContext().assets.open("marketLogos/karaca.png"),null)).into(view?.findViewById(R.id.marketLogo)!!)
            "ENGLISHHOME"-> Glide.with(requireContext()).load(Drawable.createFromStream(requireContext().assets.open("marketLogos/english-home.png"),null)).into(view?.findViewById(R.id.marketLogo)!!)
            "MADAMECOCO"-> Glide.with(requireContext()).load(Drawable.createFromStream(requireContext().assets.open("marketLogos/madame-coco.png"),null)).into(view?.findViewById(R.id.marketLogo)!!)
        }

        return activity.let {
            val myBuilder = AlertDialog.Builder(it)
            myBuilder
                .setView(view)
                .setPositiveButton("Paylaş") { _, _ ->
                    var share = Intent()
                    share.action = Intent.ACTION_SEND
                    share.type = "text/plain"
                    share.putExtra(Intent.EXTRA_TEXT,image)
                    this.startActivity(Intent.createChooser(share, "Dosyayı Paylaş"))
                }
            myBuilder.create()
        }
    }

    companion object {
        const val TAG = "myDialog"
        private const val ARG_TITLE = "argTitle"
        private const val ARG_MESSAGE = "argMessage"
        private const val ARG_IMAGE = "argImage"
        private const val ARG_MARKET_IMAGE = "argImageMarket"


        fun newInstance(title: String, message: String,image:String,marketImage:String) = ProductDetailFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_TITLE, title)
                putString(ARG_MESSAGE, message)
                putString(ARG_IMAGE, image)
                putString(ARG_MARKET_IMAGE, marketImage)

            }
        }
    }

}