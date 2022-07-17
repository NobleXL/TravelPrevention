package com.noble.appbase.network

/**
 * @author HQL
 * @desc：基础数据返回格式
 */
class BaseReqData<T> {
    val result: T? = null
    val reason: String = ""
    val error_code: Int = 0
}