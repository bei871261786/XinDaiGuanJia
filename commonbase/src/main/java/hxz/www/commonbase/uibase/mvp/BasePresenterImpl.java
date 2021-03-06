package hxz.www.commonbase.uibase.mvp;

import android.content.Context;

import hxz.www.commonbase.baseui.mvp.BasePresenter2;
import hxz.www.commonbase.baseui.mvp.BaseView2;

/**
 * Created by Circle on 2019-06-27
 */
public abstract class BasePresenterImpl<V extends BaseView2> implements BasePresenter2<V> {
    protected Context mContext;
    protected V mView;

    @Override
    public void attachView(Context context, V view) {
        mContext = context;
        mView = view;
    }

    @Override
    public void detachView() {
//        mView = null;
    }
}
