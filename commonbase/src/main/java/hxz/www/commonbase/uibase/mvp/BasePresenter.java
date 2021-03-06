package hxz.www.commonbase.uibase.mvp;

import android.content.Context;

import hxz.www.commonbase.baseui.mvp.BaseView2;

/**
 * Created by Circle on 2019-06-27
 * Presenter基类
 */
public interface BasePresenter<V extends BaseView2> {
    void attachView(Context context, V view);

    void detachView();
}
