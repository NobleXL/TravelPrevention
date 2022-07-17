package com.noble.appbase.utils

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue

/**
 * @author：HQL
 * @desc：设备相关工具类
 */
object DeviceUtil {

    /**
     * dp转为px
     * @param context  上下文
     * @param dipValue dp值
     * @return
     */
    fun dip2px(context: Context, dipValue: Float): Int {
        val r: Resources = context.applicationContext.resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dipValue, r.displayMetrics
        ).toInt()
    }
}