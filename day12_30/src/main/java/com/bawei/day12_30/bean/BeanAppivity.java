package com.bawei.day12_30.bean;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 作者：丁建飞
 * 时间：2019/12/30  8:52
 * 类名：com.bawei.day12_30.bean
 */
public  abstract class BeanAppivity<P extends  BeanPresenter> extends AppCompatActivity {
    public  P  mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        mPresenter=priPresenter();
        if (mPresenter != null) {
            mPresenter.attach(this);
        }
        initView();
        initDate();
    }

    protected abstract void initDate();

    protected abstract void initView();

    protected abstract P priPresenter();

    protected abstract int layoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.dttdch();
        }
    }
}
