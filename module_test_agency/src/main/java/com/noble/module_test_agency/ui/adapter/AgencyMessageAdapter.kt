package com.noble.module_test_agency.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.noble.module_test_agency.bean.reqbean.AgencyMessageReqData
import com.noble.module_test_agency.databinding.ItemAgencymessageBinding

/**
 * @author：HQL
 * @desc：显示核酸检测机构信息
 */
class AgencyMessageAdapter(
    var data: List<AgencyMessageReqData.Data>,
    val callback: (Int, AgencyMessageReqData.Data) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemAgencymessageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataHolder = holder as DataViewHolder
        dataHolder.binding.bean = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }

    internal class DataViewHolder(val binding: ItemAgencymessageBinding) :
        RecyclerView.ViewHolder(binding.root)
}