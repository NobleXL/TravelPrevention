package com.noble.module_city.respository

import com.noble.appbase.network.BaseReqData
import com.noble.appbase.network.RetrofitServiceBuilder
import com.noble.module_city.api.CityApi
import com.noble.module_city.bean.reqbean.CityDataReqData

/**
 * @author：HQL
 * @desc：数据仓库层
 */
class CityRespository {

    // 创建service实例
    private var netWork = RetrofitServiceBuilder.createService(
        CityApi::class.java
    )

    /**
     * 加载城市清单
     */
    suspend fun loadCityData(): BaseReqData<List<CityDataReqData>>? {
        netWork?.let {
            return it.loadCityData()
        }
        return null
    }
}