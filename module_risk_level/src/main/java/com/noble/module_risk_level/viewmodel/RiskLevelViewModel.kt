package com.noble.module_risk_level.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.noble.appbase.network.HttpErrorDeal
import com.noble.module_risk_level.respository.RiskLevelRespository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * @author：HQL
 * @desc：风险等级查询ViewModel
 */
class RiskLevelViewModel : ViewModel() {

    /**
     * 查询风险等级数据
     */
    fun loadRiskLevelMessage() = flow {
        val data = RiskLevelRespository().loadRiskLevelMessage()
        emit(data)
    }.catch {
        if (it is Exception) {
            HttpErrorDeal.dealHttpError(it)
        }
        emit(null)
    }.asLiveData()
}