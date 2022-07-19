package com.noble.module_travel_policy.bean.reqbean

import com.noble.module_travel_policy.bean.enum.RiskLevelEnum

/**
 * @author：HQL
 * @desc：政策详细信息
 */
class PolicyDetailReqBean {
    var province_id: String? = null
    var city_id: String? = null
    var city_name: String? = null
    var health_code_desc: String? = null
    var health_code_gid: String? = null
    var health_code_name: String? = null
    var health_code_picture: String? = null
    var health_code_style: String? = null
    var high_in_desc: String? = null
    var low_in_desc: String? = null
    var out_desc: String? = null
    var province_name: String? = null
    var risk_level: String? = null
    val isLowRisk: Boolean
        get() {
            risk_level?.let {
                if (it == "0" || it == "1") {
                    return true
                }
            }
            return false
        }
    val riskLevelDesc: String
        get() {
            risk_level?.let {
                return RiskLevelEnum.getRiskLevelDesc(it)
            }
            return "未知"
        }
}