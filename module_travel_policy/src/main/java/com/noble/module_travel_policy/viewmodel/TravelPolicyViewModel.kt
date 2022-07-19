package com.noble.module_travel_policy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.noble.appbase.network.HttpErrorDeal
import com.noble.module_travel_policy.respository.TravelPolicyRespository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * @author：HQL
 * @desc：TravelPolicyViewModel
 */
class TravelPolicyViewModel : ViewModel() {

    /**
     * 查询检测机构信息
     * @param fromCityId 出发地城市
     * @param toCityId 目的地城市
     */
    fun queryTravelPolicy(
        fromCityId: String,
        toCity: String
    ) = flow {
        val data = TravelPolicyRespository().queryTravelPolicy(
            fromCityId, toCity
        )
        emit(data)
    }.catch {
        if (it is Exception) {
            HttpErrorDeal.dealHttpError(it)
        }
        emit(null)
    }.asLiveData()
}