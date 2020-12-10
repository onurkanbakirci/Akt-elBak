package com.onurkanbakirci.aktuelBak.ui

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.onurkanbakirci.aktuelBak.R
import com.onurkanbakirci.aktuelBak.di.Components.DaggerSplashScreenComponent
import com.onurkanbakirci.aktuelBak.di.Helpers.IIntentHelper
import com.onurkanbakirci.aktuelBak.di.Helpers.IntentHelper
import com.onurkanbakirci.aktuelBak.di.Modules.IntentModule
import com.onurkanbakirci.aktuelBak.util.goToMainActivity
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var mIntentHelper:IntentHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splashLogo: ImageView = findViewById(R.id.splashLogo)
        splashLogo.setImageDrawable(Drawable.createFromStream(this.assets.open("logo/Group 5.png"),null))

        val beginningOfText:TextView = findViewById(R.id.beginningOfText)
        beginningOfText.typeface = Typeface.createFromAsset(assets,"font/Baloo.ttf")

        val endOfText:TextView = findViewById(R.id.endOfText)
        endOfText.typeface = Typeface.createFromAsset(assets,"font/Baloo.ttf")

        DaggerSplashScreenComponent.builder()
            .intentModule(IntentModule())
            .build()
            .inject(this)

        this.mIntentHelper
            .providePostDelayedHandler(object :IIntentHelper{
                override fun onCompleted() {
                    goToMainActivity(this@SplashActivity)
                }
            },IntentHelper.DEFAULT_HANDLER_DURATION)
    }
}
