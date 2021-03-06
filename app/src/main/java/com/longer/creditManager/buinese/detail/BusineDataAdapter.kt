package com.longer.creditManager.buinese.detail

import android.util.TypedValue
import android.widget.LinearLayout
import android.widget.TextView
import com.longer.creditManager.R
import getColor
import hxz.www.commonbase.adapter.BaseAdapter
import hxz.www.commonbase.adapter.ViewHolder
import hxz.www.commonbase.util.log.LogShow
import isVisible
import value

class BusineDataAdapter : BaseAdapter<MutableMap<String, String>>() {

    var titleList = mutableListOf<String>()
    var tvList = mutableListOf<TextView>()
    val MODE_BUSINESE = 0
    val MODE_REPAY = 1
    private var mode = MODE_BUSINESE

    fun setMode(isBusinese: Boolean) {
        mode = if (!isBusinese) MODE_REPAY else MODE_BUSINESE
    }

    override fun getItemLayoutResId(data: MutableMap<String, String>?, position: Int) = R.layout.adapter_simple
    override fun bindData(holder: ViewHolder, data: MutableMap<String, String>?, position: Int) {
        var container = holder.getView<LinearLayout>(R.id.ll_container)
        LogShow.i("BusineDataAdapter   ", data?.get("Status"), data.toString());
        tvList.clear()
        for (i in 0 until container.childCount) {
            container.getChildAt(i)?.let {
                tvList.add(it as TextView)
            }
        }

        if (position == 0) {
            container.setBackgroundColor(R.color.gray_E5E5EA.getColor())
            titleList.forEachIndexed { index, titleBean ->
                run {
                    if (tvList.size > index) {
                        var tv = tvList.get(index)
                        tv.text = getTitleName(titleBean)
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16F)
                        tv.isVisible = true
                    }
                }
            }
        } else {
            container.setBackgroundColor(R.color.white.getColor())
            titleList.forEachIndexed { index, titleBean ->
                run {
                    if (tvList.size > index) {
                        var tv = tvList.get(index)
//                        tv.text =titleBean.substringBefore("fieldNote\":").apply { substring(1,length-1) }
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12F)
                        tv.isVisible = true
                        var value = data?.get(getTitleKey(titleBean))
                        LogShow.i("BusineDataAdaptekt  bindData >0", titleBean, tv.text, getTitleKey(titleBean), value)
                        tv.text = value?.trim('"').value()
                    }
                }

            }
        }
    }

    private fun getTitleName(titleBean: String): String {
        if (mode==MODE_BUSINESE)
        {
            return titleBean.substringAfter("fieldNote\":").trim('"').value()
        }
        else{
            return titleBean.substringAfter("feeName\":").trim('"').value()
        }

    }

    private fun getTitleKey(titleBean: String): String {
        if (mode==MODE_BUSINESE)
        {
            return titleBean.substringAfter("fieldName\":").substringBefore(",")
        }
        else{
            return titleBean.substringAfter("feeId\":").substringBefore(",")
        }

    }


    public fun setTitleList(json: String) {
        var list = json.split("},{")
        titleList.addAll(list)
    }
}