package com.longer.creditManager.notice
import hxz.www.commonbase.baseui.mvp.BaseView2
import hxz.www.commonbase.model.NoticeListModel


/**
@Author  :rickBei
@Date    :2019/8/9 16:10
@Descripe: From bug to bugs
 **/
class NoticeListContract {
    interface View : BaseView2 {
        fun onQueryBlackList(list: NoticeListModel?)
    }


}