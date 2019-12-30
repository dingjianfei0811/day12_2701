package com.bawei.day12_30;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bawei.day12_30.model.base.Base;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.but)
    Button but;
    private Object Base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.but)
    public void onViewClicked() {


        Toast.makeText(this, "点击了"+ "{\"result\":[{\"imageUrl\":\"http://172.17.8.100/images/small/banner/cj.png\",\"jumpUrl\":\"http://172.17.8.100/htm/lottery/index.html\",\"rank\":5,\"title\":\"抽奖\"},{\"imageUrl\":\"http://172.17.8.100/images/small/banner/hzp.png\",\"jumpUrl\":\"wd://commodity_list?arg=1001007005\",\"rank\":5,\"title\":\"美妆工具\"},{\"imageUrl\":\"http://172.17.8.100/images/small/banner/lyq.png\",\"jumpUrl\":\"wd://commodity_info?arg=83\",\"rank\":5,\"title\":\"连衣裙\"},{\"imageUrl\":\"http://172.17.8.100/images/small/banner/px.png\",\"jumpUrl\":\"wd://commodity_info?arg=165\",\"rank\":5,\"title\":\"跑鞋\"},{\"imageUrl\":\"http://172.17.8.100/images/small/banner/wy.png\",\"jumpUrl\":\"wd://commodity_list?arg=1001002004\",\"rank\":5,\"title\":\"卫衣\"}],\"message\":\"查询成功\",\"status\":\"0000\"}", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        EventBus.getDefault().register(this);
    }
    @Override
    protected void onStop() {
        super.onStop();
//        EventBus.getDefault().unregister(this);
    }


}
