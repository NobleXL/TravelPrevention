package com.noble.module_test_agency.api

import com.noble.appbase.network.BaseApi
import com.noble.appbase.network.BaseReqData
import com.noble.module_test_agency.bean.reqbean.AgencyMessageReqData
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author：HQL
 * @desc：检测机构api
 */
interface AgencyApi {

    /**
     * 加载检测机构信息
     * @param key 接口key值 无须传
     * @param city_id 城市id，从城市选择页面返回
     */
    @GET("hsjg")
    suspend fun loadTestAgencyMessage(
        @Query("city_id") city_id: String,
        @Query("key") key: String = BaseApi.KEY
    ): BaseReqData<AgencyMessageReqData>
}