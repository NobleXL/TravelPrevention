package com.noble.module_travel_policy.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.noble.appbase.config.ArouteConfig
import com.noble.appbase.config.ParametersConfig
import com.noble.appbase.config.RequsetCodeConfig
import com.noble.appbase.config.ResultCodeConfig
import com.noble.appbase.ui.BaseActivity
import com.noble.appbase.utils.DialogLoadingUtils
import com.noble.module_travel_policy.databinding.ActivityTravelPolicyBinding
import com.noble.module_travel_policy.viewmodel.TravelPolicyViewModel

/**
 * @author：HQL
 * @desc：出行政策Activity
 */
@Route(path = ArouteConfig.TRAVEL_POLICY)
class TravelPolicyActivity : BaseActivity<ActivityTravelPolicyBinding>() {

    /**
     * viewModel
     */
    private val travelPolicyViewModel by viewModels<TravelPolicyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    /**
     * 初始化
     */
    private fun init() {
        mViewBinding.toolbar.setNavigationOnClickListener {
            finish()
        }
        //选择出发地
        mViewBinding.tvFromCity.setOnClickListener {
            ARouter.getInstance().build(ArouteConfig.CITY_DATA)
                .navigation(this, RequsetCodeConfig.REQUSET_CODE_SELECT_FROM_CITY)
        }
        //选择目的地
        mViewBinding.tvToCity.setOnClickListener {
            ARouter.getInstance().build(ArouteConfig.CITY_DATA)
                .navigation(this, RequsetCodeConfig.REQUSET_CODE_SELECT_TO_CITY)
        }
    }

    var fromCityId: String? = null
    var toCityId: String? = null

    /**
     * 查询数据
     */
    private fun loadData() {
        fromCityId?.let { fromCityId ->
            toCityId?.let { toCityId ->
                DialogLoadingUtils.showLoading(this, "请稍后")
                travelPolicyViewModel.queryTravelPolicy(fromCityId, toCityId)
                    .observe(this, Observer {
                        DialogLoadingUtils.cancel()
                        it?.let {
                            if (it.error_code == 0) {
                                mViewBinding.bean = it.result
                            }
                        }
                    })
            }
        }
    }

    override fun getViewBinding(): ActivityTravelPolicyBinding {
        return ActivityTravelPolicyBinding.inflate(
            layoutInflater
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            RequsetCodeConfig.REQUSET_CODE_SELECT_FROM_CITY -> {
                //选择出发地
                if (resultCode == ResultCodeConfig.RESULE_CODE_SELECT_CITY_SUCCESS) {
                    fromCityId = data?.getStringExtra(ParametersConfig.CITY_ID)
                    mViewBinding.tvFromCity.text = data?.getStringExtra(ParametersConfig.CITY_NAME)
                    loadData()
                }
            }
            RequsetCodeConfig.REQUSET_CODE_SELECT_TO_CITY -> {
                //选择目的地
                if (resultCode == ResultCodeConfig.RESULE_CODE_SELECT_CITY_SUCCESS) {
                    toCityId = data?.getStringExtra(ParametersConfig.CITY_ID)
                    mViewBinding.tvToCity.text = data?.getStringExtra(ParametersConfig.CITY_NAME)
                    loadData()
                }
            }
        }
    }
}