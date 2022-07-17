package com.noble.module_test_agency.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.noble.appbase.network.HttpErrorDeal
import com.noble.module_test_agency.respository.AgencyRespository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * @author：HQL
 * @desc：AgencyViewModel
 */
class AgencyViewModel : ViewModel() {

    /**
     * 查询检测机构信息
     * @param cityId 城市id
     */
    fun loadTestAgencyMessage(cityId: String) = flow {
        val data = AgencyRespository().loadTestAgencyMessage(cityId)
        emit(data)
    }.catch {
        if (it is Exception) {
            HttpErrorDeal.dealHttpError(it)
        }
        emit(null)
    }.asLiveData()
}