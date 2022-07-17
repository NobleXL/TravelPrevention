package com.noble.module_city.api

import com.noble.appbase.network.BaseApi
import com.noble.appbase.network.BaseReqData
import com.noble.module_city.bean.reqbean.CityDataReqData
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author：HQL
 * @desc：城市清单api
 */
interface CityApi {
    /**
     * 加载城市数据
     * @param key 此处使用 BaseApi 中的默认值
     */
    @GET("citys")
    suspend fun loadCityData(@Query("key") key: String = BaseApi.KEY): BaseReqData<List<CityDataReqData>>
}