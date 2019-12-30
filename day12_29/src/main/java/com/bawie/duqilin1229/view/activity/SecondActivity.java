package com.bawie.duqilin1229.view.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawie.duqilin1229.R;
import com.bawie.duqilin1229.base.BaseActivity;
import com.bawie.duqilin1229.base.BasePresenter;
import com.bawie.duqilin1229.model.bean.Bean;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends BaseActivity {

    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.imageview)
    ImageView imageview;

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
    // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        CodeUtils.init(this);
        imageview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CodeUtils.analyzeByImageView(imageview, new CodeUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        Toast.makeText(SecondActivity.this,"成功"+result,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnalyzeFailed() {
                        Toast.makeText(SecondActivity.this,"失败",Toast.LENGTH_SHORT).show();
                    }
                });
                return true;
            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_second;
    }

    @OnClick({R.id.text, R.id.button,R.id.button2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text:
                Bitmap image = CodeUtils.createImage(String.valueOf(text), 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round));
                imageview.setImageBitmap(image);
                break;
            case R.id.button:
                EventBus.getDefault().post("这是数据");
                break;
            case  R.id.button2:
                EventBus.getDefault().post(Bean.BannerBean.class);
                break;
        }
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

    @Subscribe(threadMode=ThreadMode.MAIN)
    public void onGetString(String string){
        Toast.makeText(SecondActivity.this,"成功"+string,Toast.LENGTH_SHORT).show();
    }
    @Subscribe(threadMode =ThreadMode.ASYNC)
    public void onGetBean(Bean bean){
        Toast.makeText(SecondActivity.this,"成功"+bean,Toast.LENGTH_SHORT).show();
    }
}
