package com.noble.module_travel_policy.api

import com.noble.appbase.network.BaseApi
import com.noble.appbase.network.BaseReqData
import com.noble.module_travel_policy.bean.reqbean.TravelPolicyReqBean
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author：HQL
 * @desc：出行政策api
 */
interface TravelPolicyApi {
    /**
     * 查询出行政策
     * @param key 接口key值 无须传
     * @param fromCityId 出发地城市id
     * @param toCityId 目的地城市id
     */
    @GET("query")
    suspend fun queryTravelPolicy(
        @Query("from") fromCityId: String,
        @Query("to") toCityId: String,
        @Query("key") key: String = BaseApi.KEY
    ): BaseReqData<TravelPolicyReqBean>
}