package com.noble.travelprevention

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import com.alibaba.android.arouter.launcher.ARouter
import com.noble.appbase.config.ArouteConfig
import com.noble.appbase.network.BaseApi
import com.noble.appbase.ui.BaseActivity
import com.noble.appbase.utils.ToastUtil
import com.noble.travelprevention.databinding.ActivityMainBinding

/**
 * @author HQL
 * @desc APP主页面入口
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initClick()
        checkKey()
    }

    /**
     * 检测key值
     */
    private fun checkKey(){
        if (TextUtils.isEmpty(BaseApi.KEY)){
            ToastUtil.shortShow(getString(R.string.enter_key_please))
        }
    }

    /**
     * 监听事件
     */
    private fun initClick() {
        //查询核酸检测机构
        mViewBinding.llAgency.setOnClickListener {
            ARouter.getInstance().build(ArouteConfig.AGENCY_MESSAGE)
                .navigation()
        }
        //查询风险等级地区
        mViewBinding.llRiskLevel.setOnClickListener {
            ARouter.getInstance().build(ArouteConfig.RISK_LEVEL)
                .navigation()
        }
        //查询出行政策
        mViewBinding.llTravel.setOnClickListener {
            ARouter.getInstance().build(ArouteConfig.TRAVEL_POLICY)
                .navigation()
        }
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(
            layoutInflater
        )
    }
}