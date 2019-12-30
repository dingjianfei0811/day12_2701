package com.bawie.duqilin1229.view.activity;

import android.content.Intent;
import android.os.Bundle;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawie.duqilin1229.R;
import com.bawie.duqilin1229.base.BaseActivity;
import com.bawie.duqilin1229.contract.IMainContract;
import com.bawie.duqilin1229.model.bean.Bean;
import com.bawie.duqilin1229.presenter.MainPresenter;
import com.bawie.duqilin1229.view.adapter.MyAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainPresenter> implements IMainContract.IView {


    @BindView(R.id.rv)
    RecyclerView recyclerView;

    @Override
    protected MainPresenter providePresenter() {
        return new MainPresenter();
    }

    @Override
    protected void initData() {
        mPresenter.getMainData();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onMainSuccess(Bean bean) {
        List<Bean.DataBean> data = bean.getData();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        MyAdapter myAdapter = new MyAdapter(data);
        recyclerView.setAdapter(myAdapter);
        myAdapter.setOnItemClickLintLner(new MyAdapter.OnItemClickLintLner() {
            @Override
            public void OnItemClick(String str) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMainFailure(Throwable throwable) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
