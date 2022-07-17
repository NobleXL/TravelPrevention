package com.noble.module_test_agency.bean.reqbean

/**
 * @author：HQL
 * @desc：检测机构数据返回
 */
class AgencyMessageReqData {

    val data: List<Data>? = null

    class Data {
        val cityId: String? = null
        val name: String? = null
        val province: String? = null
        val city: String? = null
        val phone: String? = null
        val address: String? = null
    }

}