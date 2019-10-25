package com.longer.creditManager.specificmodel;
/**
 *
 * 填写网络方法
 */

import com.longer.creditManager.basemodel.BaseModel;
import hxz.www.commonbase.model.Ba1;
import hxz.www.commonbase.model.ExamineBean;
import hxz.www.commonbase.model.LoginBeae;
import hxz.www.commonbase.model.ListBody;
import hxz.www.commonbase.model.LoginBody;
import hxz.www.commonbase.net.BaseResultObserver;

import io.reactivex.disposables.Disposable;

public class MainModel extends BaseModel {

    public Disposable getBa1(String a, String b, String c, BaseResultObserver<Ba1> observer){

        return getApiService().getBa1(a,b,c).subscribeWith(observer);
    }


    // 登录

    public Disposable getLoginBeae (LoginBody loginBody , BaseResultObserver<LoginBeae> observer){

        return getApiService().getLoginBeae(loginBody).subscribeWith(observer);
    }


    // 审批待办列表

    public Disposable getExamineBean(String token, ListBody listBody, BaseResultObserver<ExamineBean> observer){
        return getApiService().getExamineList(token,listBody).subscribeWith(observer);
    }




}
