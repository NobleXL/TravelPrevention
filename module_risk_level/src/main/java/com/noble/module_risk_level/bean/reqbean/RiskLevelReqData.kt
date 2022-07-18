package com.noble.module_risk_level.bean.reqbean

/**
 * @author：HQL
 * @desc：风险等级返回数据结构
 */
class RiskLevelReqData {
    var updated_date: String? = null
    var high_count: String? = null
    var middle_count: String? = null
    var high_list: List<RiskLevelDetailBean>? = null
    var middle_list: List<RiskLevelDetailBean>? = null
    var list: List<RiskLevelDetailBean>? = null
}