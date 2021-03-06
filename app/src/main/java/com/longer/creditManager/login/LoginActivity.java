package com.longer.creditManager.login;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.longer.creditManager.MainActivity;
import com.longer.creditManager.R;
import com.longer.creditManager.activity.ForgetThePasswordActivity;

import hxz.www.commonbase.base.mvp.BaseMvpActivity;
import hxz.www.commonbase.cache.Cache;
import hxz.www.commonbase.cache.UserInfo;
import hxz.www.commonbase.model.BaseUrlModel;
import hxz.www.commonbase.model.LoginBody;
import hxz.www.commonbase.util.EncryptUtil;
import hxz.www.commonbase.util.ToastUtil;
import hxz.www.commonbase.util.log.LogShow;

public class LoginActivity extends BaseMvpActivity<LoginPreseenter> implements View.OnClickListener, LoginContracr.View {

    private EditText e_username, ev_pwd, ev_company;
    private Button b_login;
    private ImageView iv_cancel, iv_estop, iv_logo;
    private TextView tv_forgetpwd;
    private boolean flag = true;
    //  private Token token;


    @Override
    public void onClick(View v) {
        if (v.getId() == b_login.getId()) {

            if (checkEnable()) {

                mPresenter.getBaseUrl(ev_company.getText().toString());

            } else {
                ToastUtil.show("请完善信息");
            }


            //  loginYun();

        } else if (v.getId() == iv_cancel.getId()) {
            ev_pwd.setText("");


        } else if (v.getId() == iv_estop.getId()) {

            if (flag) {

                ev_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                flag = false;
                iv_estop.setImageResource(R.mipmap.login_visible);
            } else {
                ev_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                flag = true;
                iv_estop.setImageResource(R.mipmap.login_hide);
            }


        } else if (v.getId() == tv_forgetpwd.getId()) {
            startActivity(new Intent(this, ForgetThePasswordActivity.class));
        }
//
    }

    private boolean checkEnable() {
        return !TextUtils.isEmpty(e_username.getText().toString()) && !TextUtils.isEmpty(ev_pwd.getText().toString()) && !TextUtils.isEmpty(ev_company.getText().toString());
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void initEvent() {
        b_login.setOnClickListener(this);
        iv_estop.setOnClickListener(this);
        iv_cancel.setOnClickListener(this);
        tv_forgetpwd.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        e_username = findViewById(R.id.ev_username);
        ev_company = findViewById(R.id.ev_company);
        ev_pwd = findViewById(R.id.ev_pwd);
        iv_cancel = findViewById(R.id.iv_cancel);

        iv_estop = findViewById(R.id.iv_estop);
        tv_forgetpwd = findViewById(R.id.tv_forgetpwd);
        b_login = findViewById(R.id.login_btn);
        iv_logo = findViewById(R.id.iv_logo);

    }

    @Override
    protected LoginPreseenter getPresenter() {
        return new LoginPreseenter();
    }

    @Override
    protected boolean checkDeviceHasNavigationBar(Context context) {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void showMsg(String msg) {
        ToastUtil.show(msg);
    }

    @Override
    public void showData(UserInfo userInfo) {
        if (userInfo != null) {

            Cache.setUserInfo(userInfo);
            LogShow.i("LoginActivity  showData", e_username.getText().toString());
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onQueryBaseUrl(BaseUrlModel model) {
        if (model != null) {
            String name = e_username.getText().toString();
            String pwd = ev_pwd.getText().toString();
            LoginBody loginBody = new LoginBody(name, EncryptUtil.MD5(pwd).toLowerCase());
            mPresenter.getLoginBeae(loginBody);
        }
    }


}
