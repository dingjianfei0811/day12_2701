package com.bawie.duqilin1229.presenter;

import com.bawie.duqilin1229.base.BasePresenter;
import com.bawie.duqilin1229.contract.IMainContract;
import com.bawie.duqilin1229.model.MainModel;
import com.bawie.duqilin1229.model.bean.Bean;


public class MainPresenter extends BasePresenter<IMainContract.IView> implements IMainContract.IPresenter{

    private MainModel mainModel;

    @Override
    protected void initModel() {
        mainModel = new MainModel();
    }

    @Override
    public void getMainData() {
        mainModel.getMainData(new IMainContract.IModel.IModelCallBack() {
            @Override
            public void onMainSuccess(Bean bean) {
                view.onMainSuccess(bean);
            }

            @Override
            public void onMainFailure(Throwable throwable) {
                view.onMainFailure(throwable);
            }
        });
    }
}
