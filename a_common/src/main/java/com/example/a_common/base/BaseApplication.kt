package com.example.a_common.base

import android.app.Application
import com.kingja.loadsir.core.LoadSir
import com.example.a_common.callback.EmptyCallback
import com.example.a_common.callback.ErrorCallback
import com.example.a_common.callback.LoadingCallback
import com.example.a_common.utils.Preference
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        LoadSir.beginBuilder()
            .addCallback(ErrorCallback())
            .addCallback(LoadingCallback())
            .addCallback(EmptyCallback())
            .commit()

        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .tag("WanAndroid >>> ")
            .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))

        Preference.setContext(applicationContext)
    }
}