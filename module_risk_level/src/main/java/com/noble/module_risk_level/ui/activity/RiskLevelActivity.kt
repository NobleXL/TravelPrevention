package com.noble.module_risk_level.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.noble.appbase.config.ArouteConfig
import com.noble.appbase.ui.BaseActivity
import com.noble.appbase.utils.DialogLoadingUtils
import com.noble.module_risk_level.bean.enum.DataTypeEnum
import com.noble.module_risk_level.bean.reqbean.RiskLevelDetailBean
import com.noble.module_risk_level.databinding.ActivityRiskLevelBinding
import com.noble.module_risk_level.ui.adapter.RiskLevelMessageAdapter
import com.noble.module_risk_level.viewmodel.RiskLevelViewModel

/**
 * @author：HQL
 * @desc：风险等级查询Activity
 */
@Route(path = ArouteConfig.RISK_LEVEL)
class RiskLevelActivity : BaseActivity<ActivityRiskLevelBinding>() {

    /**
     * viewModel
     */
    private val riskLevelViewModel by viewModels<RiskLevelViewModel>()

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
        val layoutManager = LinearLayoutManager(this)
        mViewBinding.rvData.layoutManager = layoutManager
        loadData()
    }

    /**
     * 查询数据
     */
    private fun loadData() {
        DialogLoadingUtils.showLoading(this, "请稍后")
        riskLevelViewModel.loadRiskLevelMessage().observe(this, Observer {
            DialogLoadingUtils.cancel()
            it?.let {
                if (it.error_code == 0) {
                    mViewBinding.bean = it.result
                    val list = mutableListOf<RiskLevelDetailBean>()
                    val highTitle = RiskLevelDetailBean()
                    highTitle.dataType = DataTypeEnum.DATA_IS_HIGH_TITLE.ordinal
                    list.add(highTitle)
                    it.result?.high_list?.let {
                        list.addAll(it)
                    }
                    val middleTitle = RiskLevelDetailBean()
                    middleTitle.dataType = DataTypeEnum.DATA_IS_MIDDLE_TITLE.ordinal
                    list.add(middleTitle)
                    it.result?.middle_list?.let { middle ->
                        list.addAll(middle)
                    }
                    val riskLevelAdapter = RiskLevelMessageAdapter(list)
                    mViewBinding.rvData.adapter = riskLevelAdapter
                }
            }
        })
    }

    override fun getViewBinding(): ActivityRiskLevelBinding {
        return ActivityRiskLevelBinding.inflate(
            layoutInflater
        )
    }
}