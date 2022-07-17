package com.noble.appbase.utils

import android.content.Context
import com.noble.appbase.expand.defaultDialogView
import com.noble.appbase.tools.LoadingDialog

/**
 * @author：HQL
 * @desc：加载对话框工具类
 */
object DialogLoadingUtils {

    private var dialog: LoadingDialog? = null

    /**
     * 显示等待框
     */
    fun showLoading(context: Context, mes: String) {

        dialog?.apply {
            if (isShowing) {
                cancel()
            }
        }

        dialog = LoadingDialog.Builder(context)
            .defaultDialogView()
            .setBootomDesc(mes)
            .create()
        dialog!!.show()
    }


    /**
     * 取消
     */
    fun cancel() {
        dialog?.cancel()
    }
}