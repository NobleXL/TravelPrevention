package com.noble.appbase.expand

/**
 * @author：HQL
 * @desc：List 扩展函数
 */
fun List<String>?.splitData(): String {
    val stringBuffer = StringBuffer()
    if (!isNullOrEmpty()) {
        for (i in this.indices) {
            stringBuffer.append(this[i] + "\n")
        }
    }
    return stringBuffer.toString()
}