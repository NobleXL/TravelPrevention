package com.noble.module_test_agency.respository

import com.noble.appbase.network.BaseReqData
import com.noble.appbase.network.RetrofitServiceBuilder
import com.noble.module_test_agency.api.AgencyApi
import com.noble.module_test_agency.bean.reqbean.AgencyMessageReqData

/**
 * @author：HQL
 * @desc：数据仓库层
 */
class AgencyRespository {

    // 创建service实例
    private var netWork = RetrofitServiceBuilder.createService(
        AgencyApi::class.java
    )

    /**
     * 查询检测机构信息
     * @param cityId 城市id
     */
    suspend fun loadTestAgencyMessage(cityId: String): BaseReqData<AgencyMessageReqData>? {
        netWork?.let {
            return it.loadTestAgencyMessage(cityId)
        }
        return null
    }
}