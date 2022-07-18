package com.noble.module_risk_level.bean.reqbean

import com.noble.appbase.expand.splitData
import com.noble.module_risk_level.bean.enum.DataTypeEnum

/**
 * @author：HQL
 * @desc：风险等级数据详细信息
 */
class RiskLevelDetailBean {
    var type: String? = null
    var province: String? = null
    var city: String? = null
    var county: String? = null
    var area_name: String? = null
    var communitys: List<String>? = null
    val communitysString: String
        get() {
            return communitys.splitData()
        }

    var county_code: String? = null

    //数据类型默认是0 为1 即为标题 高风险标题 2是中风险标题
    var dataType = DataTypeEnum.DATA_IS_RISKLEVEL.ordinal

}