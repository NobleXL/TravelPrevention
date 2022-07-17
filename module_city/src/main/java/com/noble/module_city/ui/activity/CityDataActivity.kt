package com.noble.module_city.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.noble.appbase.config.ArouteConfig
import com.noble.appbase.config.ParametersConfig
import com.noble.appbase.config.ResultCodeConfig
import com.noble.appbase.ui.BaseActivity
import com.noble.appbase.utils.DialogLoadingUtils
import com.noble.module_city.R
import com.noble.module_city.bean.reqbean.CityDataReqData
import com.noble.module_city.databinding.ActivityCityDataBinding
import com.noble.module_city.ui.adapter.CityDataAdapter
import com.noble.module_city.ui.view.IndexView
import com.noble.module_city.viewmodel.CityDataViewModel

/**
 * @author：HQL
 * @desc：城市数据Activity
 */
@Route(path = ArouteConfig.CITY_DATA)
class CityDataActivity : BaseActivity<ActivityCityDataBinding>() {

    /**
     * viewModel
     */
    private val cityDataViewModel by viewModels<CityDataViewModel>()

    /**
     * 城市数据
     */
    val cityList = mutableListOf<CityDataReqData.CitysData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    /**
     * 初始化
     */
    private fun init() {
        initView()
        loadData()
    }

    /**
     * 初始化view
     */
    private fun initView() {
        mViewBinding.toolbar.setOnClickListener {
            finish()
        }
        // 索引滚动
        mViewBinding.indexView.setOnIndexChangeListener(object : IndexView.OnIndexChangeListener {
            override fun onIndexChange(word: String?) {
                mViewBinding.tvIndex.visibility = View.VISIBLE
                mViewBinding.tvIndex.text = word
                word?.let {
                    cityList.let {
                        it.forEachIndexed { position, data ->
                            if (word == data.firstLetter) {
                                mViewBinding.rvData.scrollToPosition(position)
                                return
                            }
                        }

                    }
                }
            }

            override fun uplift() {
                mViewBinding.tvIndex.visibility = View.GONE
            }
        })
        val layoutManager = LinearLayoutManager(this)
        mViewBinding.rvData.layoutManager = layoutManager
    }

    /**
     * 加载数据
     */
    private fun loadData() {
        DialogLoadingUtils.showLoading(this, getString(R.string.wait_please))
        cityDataViewModel.loadCityData().observe(this) {
            DialogLoadingUtils.cancel()
            it?.let {
                if (it.error_code == 0) {
                    // 请求帮助
                    it.result?.let { list ->
                        for (i in list.indices) {
                            val data = list[i].citys
                            data?.let { data ->
                                for (index in data.indices) {
                                    cityList.add(data[index])
                                }
                            }
                        }
                        cityList.sort()
                        val cityDataAdapter = CityDataAdapter(cityList,
                            callback = { position, data ->
                                //选择数据回调
                                val intent = Intent()
                                intent.putExtra(ParametersConfig.CITY_ID, data.city_id)
                                intent.putExtra(ParametersConfig.CITY_NAME, data.city)
                                setResult(
                                    ResultCodeConfig.RESULE_CODE_SELECT_CITY_SUCCESS,
                                    intent
                                )
                                finish()
                            })
                        mViewBinding.rvData.adapter = cityDataAdapter
                    }
                }
            }
        }
    }

    override fun getViewBinding(): ActivityCityDataBinding {
        return ActivityCityDataBinding.inflate(layoutInflater)
    }
}