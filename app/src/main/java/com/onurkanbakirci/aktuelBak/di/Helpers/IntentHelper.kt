package com.onurkanbakirci.aktuelBak.di.Helpers

import android.os.Handler

class IntentHelper {

    companion object {
        const val DEFAULT_HANDLER_DURATION = 2000L
    }

    fun providePostDelayedHandler(callback: IIntentHelper, duration: Long) {
        Handler().postDelayed({
            callback.onCompleted()
        }, duration)
    }
}