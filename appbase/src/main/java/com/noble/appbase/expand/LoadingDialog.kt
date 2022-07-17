package com.noble.appbase.expand

import com.noble.appbase.tools.LoadingDialog

/**
 * @author：HQL
 * @desc：LoadingDialog 的扩展函数
 */

/**
 * 弹窗默认样式
 */
fun LoadingDialog.Builder.defaultDialogView(): LoadingDialog.Builder {
    setDialogSize(
        120f, 112f
    )
    return this
}