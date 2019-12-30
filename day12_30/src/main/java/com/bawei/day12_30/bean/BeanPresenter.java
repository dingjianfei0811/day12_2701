package com.bawei.day12_30.bean;

/**
 * 作者：丁建飞
 * 时间：2019/12/30  8:51
 * 类名：com.bawei.day12_30.bean
 */
public abstract class BeanPresenter <V>{
    protected  V  view;

    public void attach(V view) {
        this.view = view;
    }
    public void dttdch() {
        view=null;
    }

    public BeanPresenter() {
        initModel();

    }

    protected abstract void initModel();
}
