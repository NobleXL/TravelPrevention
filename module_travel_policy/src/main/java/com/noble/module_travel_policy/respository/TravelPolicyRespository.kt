package com.noble.module_travel_policy.respository

import com.noble.appbase.network.BaseReqData
import com.noble.appbase.network.RetrofitServiceBuilder
import com.noble.module_travel_policy.api.TravelPolicyApi
import com.noble.module_travel_policy.bean.reqbean.TravelPolicyReqBean

/**
 * @author：HQL
 * @desc：数据仓库层
 */
class TravelPolicyRespository {

    // 创建service实例
    private var netWork = RetrofitServiceBuilder.createService(
        TravelPolicyApi::class.java
    )

    /**
     * 查询检测机构信息
     * @param fromCityId 出发城市id
     * @param toCityId 目的地城市id
     */
    suspend fun queryTravelPolicy(
        fromCityId: String,
        toCityId: String
    ): BaseReqData<TravelPolicyReqBean>? {
        netWork?.let {
            return it.queryTravelPolicy(fromCityId, toCityId)
        }
        return null
    }
}