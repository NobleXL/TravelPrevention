package com.noble.module_risk_level.api

import com.noble.appbase.network.BaseApi
import com.noble.appbase.network.BaseReqData
import com.noble.module_risk_level.bean.reqbean.RiskLevelReqData
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author：HQL
 * @desc：风险等级api
 */
interface RiskLevelApi {

    /**
     * 查询风险等级数据
     * @param key 接口key值 无须传
     */
    @GET("risk")
    suspend fun loadRiskLevelMessage(
        @Query("key") key: String = BaseApi.KEY
    ): BaseReqData<RiskLevelReqData>
}