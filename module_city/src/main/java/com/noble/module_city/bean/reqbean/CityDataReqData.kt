package com.noble.module_city.bean.reqbean

import com.noble.appbase.utils.Cn2SpellUtil
import java.util.*

/**
 * @author：HQL
 * @desc：城市清单数据格式
 */
class CityDataReqData {
    private val provinceId: String? = null
    private val province: String? = null
    val citys: List<CitysData>? = null

    class CitysData : Comparable<CitysData> {
        var cityNamePinyin: String? = null
            get() {
                field = city?.let {
                    return Cn2SpellUtil.getPinYin(it)
                }
                return ""
            }

        var firstLetter: String? = null
            get() {
                val letter = Regex("[a-zA-Z]")
                city?.let {
                    field = Cn2SpellUtil.getPinYinFirstLetter(it)?.uppercase(Locale.getDefault())
                    if (!field?.matches(letter)!!) {
                        field = "#"
                    }
                    return field
                }
                return "#"
            }

        val city_id: String? = null
        val city: String? = null
        override fun compareTo(other: CitysData): Int {
            return if (firstLetter.equals("#") && !other.firstLetter.equals("#")) {
                1
            } else if (!firstLetter.equals("#") && other.firstLetter.equals("#")) {
                -1
            } else {
                if (cityNamePinyin != null && other.cityNamePinyin != null) {
                    cityNamePinyin!!.compareTo(other.cityNamePinyin!!, false)
                } else {
                    1
                }
            }
        }

    }
}