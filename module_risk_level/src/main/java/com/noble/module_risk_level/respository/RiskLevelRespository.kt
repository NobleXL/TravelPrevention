package com.noble.module_risk_level.respository

import com.noble.appbase.network.BaseReqData
import com.noble.appbase.network.RetrofitServiceBuilder
import com.noble.module_risk_level.api.RiskLevelApi
import com.noble.module_risk_level.bean.reqbean.RiskLevelReqData

/**
 * @author：HQL
 * @desc：数据仓库层
 */
class RiskLevelRespository {

    // 创建service实例
    private var netWork = RetrofitServiceBuilder.createService(
        RiskLevelApi::class.java
    )

    /**
     * 查询风险等级数据
     */
    suspend fun loadRiskLevelMessage(): BaseReqData<RiskLevelReqData>? {
        netWork?.let {
            return it.loadRiskLevelMessage()
        }
        return null
    }
}