package com.noble.module_test_agency.ui.activity


import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.noble.appbase.config.ArouteConfig
import com.noble.appbase.config.ParametersConfig
import com.noble.appbase.config.RequsetCodeConfig.REQUSET_CODE_SELECT_CITY
import com.noble.appbase.config.ResultCodeConfig
import com.noble.appbase.ui.BaseActivity
import com.noble.appbase.utils.DialogLoadingUtils
import com.noble.module_test_agency.R
import com.noble.module_test_agency.databinding.ActivityAgencyMessageBinding
import com.noble.module_test_agency.ui.adapter.AgencyMessageAdapter
import com.noble.module_test_agency.viewmodel.AgencyViewModel

/**
 * @author：HQL
 * @desc：检测机构信息
 */
@Route(path = ArouteConfig.AGENCY_MESSAGE)
class AgencyMessageActivity : BaseActivity<ActivityAgencyMessageBinding>() {

    /**
     * viewModel
     */
    private val agencyViewModel by viewModels<AgencyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    /**
     * 初始化
     */
    private fun init() {
        initView()
    }

    /**
     * 初始化view
     */
    private fun initView() {
        mViewBinding.toolbar.setOnClickListener {
            finish()
        }
        // 选择城市监听事件
        mViewBinding.tvCity.setOnClickListener {
            ARouter.getInstance().build(ArouteConfig.CITY_DATA)
                .navigation(this, REQUSET_CODE_SELECT_CITY)
        }
        val layoutManager = LinearLayoutManager(this)
        mViewBinding.rvData.layoutManager = layoutManager
    }

    private fun loadData(cityId: String) {
        DialogLoadingUtils.showLoading(this, getString(R.string.wait_please))
        agencyViewModel.loadTestAgencyMessage(cityId).observe(this) {
            DialogLoadingUtils.cancel()
            it?.let {
                if (it.error_code == 0) {
                    //请求成功
                    it.result?.data?.let { data ->
                        val agencyMessageAdapter = AgencyMessageAdapter(
                            data,
                            callback = { position, data ->
                                //以后可以在这里做扩展 如拨打电话、导航等
                            })
                        mViewBinding.rvData.adapter = agencyMessageAdapter
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUSET_CODE_SELECT_CITY -> {
                when (resultCode) {
                    ResultCodeConfig.RESULE_CODE_SELECT_CITY_SUCCESS -> {
                        val cityId = data?.getStringExtra(ParametersConfig.CITY_ID)
                        val cityName = data?.getStringExtra(ParametersConfig.CITY_NAME)
                        mViewBinding.tvCity.text = cityName
                        cityId?.let {
                            loadData(it)
                        }
                    }
                }
            }
        }
    }

    override fun getViewBinding(): ActivityAgencyMessageBinding {
        return ActivityAgencyMessageBinding.inflate(layoutInflater)
    }
}