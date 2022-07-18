package com.noble.module_risk_level.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.noble.module_risk_level.R
import com.noble.module_risk_level.bean.enum.DataTypeEnum
import com.noble.module_risk_level.bean.reqbean.RiskLevelDetailBean
import com.noble.module_risk_level.databinding.ItemErrorBinding
import com.noble.module_risk_level.databinding.ItemRisklevelMessageBinding
import com.noble.module_risk_level.databinding.ItemTitleBinding

/**
 * @author：HQL
 * @desc：显示核酸检测机构信息
 */
class RiskLevelMessageAdapter(
    var data: List<RiskLevelDetailBean>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            DataTypeEnum.DATA_IS_RISKLEVEL.ordinal -> {
                val binding = ItemRisklevelMessageBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return DataViewHolder(binding)
            }
            DataTypeEnum.DATA_IS_HIGH_TITLE.ordinal,
            DataTypeEnum.DATA_IS_MIDDLE_TITLE.ordinal -> {
                val binding = ItemTitleBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return DataTitleHolder(binding)
            }
            else -> {
                // 处理未知类型
                val binding = ItemErrorBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return UnknowDataType(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DataViewHolder -> {
                holder.binding.bean = data[position]
            }
            is DataTitleHolder -> {
                if (data[position].dataType == DataTypeEnum.DATA_IS_HIGH_TITLE.ordinal) {
                    holder.img.setImageResource(R.mipmap.img_high)
                    holder.title.text = "高风险地区信息"
                }else{
                    holder.img.setImageResource(R.mipmap.img_middle)
                    holder.title.text = "中风险地区信息"
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].dataType
    }

    internal class DataViewHolder(val binding: ItemRisklevelMessageBinding) :
        RecyclerView.ViewHolder(binding.root)

    internal class DataTitleHolder(val binding: ItemTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var img = binding.imgSrc
        var title = binding.tvTitle
    }

    internal class UnknowDataType(val binding: ItemErrorBinding) :
        RecyclerView.ViewHolder(binding.root)
}