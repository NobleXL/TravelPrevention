package com.noble.appbase.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.core.view.ViewCompat
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding

/**
 * @author 小寒
 * @version 1.0
 * @date 2022/7/16 20:22
 */
abstract class BaseActivity<T : ViewBinding> : FragmentActivity() {

    lateinit var mViewBinding: T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = getViewBinding()
        setContentView(mViewBinding.root)

        val displayMetrics = resources.displayMetrics
        displayMetrics.scaledDensity = displayMetrics.density
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val controller = ViewCompat.getWindowInsetsController(mViewBinding.root)
        controller?.isAppearanceLightNavigationBars = true
        controller?.isAppearanceLightStatusBars = true

    }

    abstract fun getViewBinding(): T
}