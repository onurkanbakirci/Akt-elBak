package com.onurkanbakirci.aktuelBak.di.Components

import com.onurkanbakirci.aktuelBak.di.Modules.IntentModule
import com.onurkanbakirci.aktuelBak.ui.SplashActivity
import dagger.Component

@Component(modules = [
    (IntentModule::class)
    ])
interface SplashScreenComponent {
    fun inject(splashActivity: SplashActivity)
}