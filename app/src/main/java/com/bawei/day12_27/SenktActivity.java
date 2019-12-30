package com.bawei.day12_27;

import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SenktActivity extends AppCompatActivity {

    @BindView(R.id.but_e)
    Button butE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senkt);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.but_e)
    public void onViewClicked() {

        EventBus.getDefault().post("数据");
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode =ThreadMode.MAIN)
    public  void  getstring(String name){
        Toast.makeText(this, "吐司"+name, Toast.LENGTH_SHORT).show();
    }
}
