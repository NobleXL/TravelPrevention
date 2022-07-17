package com.noble.appbase

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter

/**
 * @author 小寒
 * @version 1.0
 * @date 2022/7/16 20:23
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