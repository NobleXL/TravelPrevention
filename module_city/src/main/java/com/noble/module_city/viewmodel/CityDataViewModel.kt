package com.noble.module_city.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.noble.appbase.network.HttpErrorDeal
import com.noble.module_city.respository.CityRespository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * @author：HQL
 * @desc：CityDataViewModel
 */
class CityDataViewModel: ViewModel() {

    /**
     * 加载城市清单
     */
    fun loadCityData() = flow {
        val data = CityRespository().loadCityData()
        emit(data)
    }.catch {
        if (it is Exception) {
            HttpErrorDeal.dealHttpError(it)
        }
        emit(null)
    }.asLiveData()
}