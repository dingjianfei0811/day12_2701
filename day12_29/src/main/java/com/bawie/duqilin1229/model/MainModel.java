package com.bawie.duqilin1229.model;

import com.bawie.duqilin1229.contract.IMainContract;
import com.bawie.duqilin1229.model.bean.Bean;
import com.bawie.duqilin1229.util.NetUtil;
import com.google.gson.Gson;


public class MainModel implements IMainContract.IModel{

    @Override
    public void getMainData(IModelCallBack iModelCallBack) {
        String httpUrl = "http://blog.zhaoliang5156.cn/api/shop/fulishe1.json";

        NetUtil.getInstance().getJsonGet(httpUrl, new NetUtil.MyCallBack() {
            @Override
            public void onGetJson(String json) {
                Bean bean = new Gson().fromJson(json, Bean.class);
                iModelCallBack.onMainSuccess(bean);
            }

            @Override
            public void onError(Throwable throwable) {
                iModelCallBack.onMainFailure(throwable);
            }
        });
    }
}
