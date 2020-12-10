package com.onurkanbakirci.aktuelBak.di.Modules

import com.onurkanbakirci.aktuelBak.di.Helpers.IntentHelper
import dagger.Module
import dagger.Provides

@Module
class IntentModule {

    @Provides
    fun  provideIntentHelper() : IntentHelper{
        return IntentHelper()
    }

}