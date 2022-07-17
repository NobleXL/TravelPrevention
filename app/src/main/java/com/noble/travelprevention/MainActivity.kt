package com.noble.travelprevention

import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.noble.appbase.config.ArouteConfig
import com.noble.appbase.ui.BaseActivity
import com.noble.travelprevention.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initClick()
    }

    /**
     * 监听事件
     */
    private fun initClick() {
        //查询核酸检测机构
        mViewBinding.llAgency.setOnClickListener {
            ARouter.getInstance().build(ArouteConfig.AGENCY_MESSAGE)
                //传递String类型的值
                .withString("a", "a")
                //传递布尔类型的值
                .withBoolean("b", false)
                //指定启动模式
                .withFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
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