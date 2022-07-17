package com.noble.appbase

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter

/**
 * @author：HQL
 * @desc：application
 */
class BaseApplication : Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        ARouter.init(this)
        ARouter.openDebug()
    }
}