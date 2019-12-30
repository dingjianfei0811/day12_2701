package com.bawei.day12_30.netutil;

import android.os.BaseBundle;
import android.os.Handler;
import android.text.BidiFormatter;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 作者：丁建飞
 * 时间：2019/12/30  8:55
 * 类名：com.bawei.day12_30.netutil
 */
public class Netutil  {
    private  static  Netutil netutil;
    private final Handler handler;
    private final OkHttpClient okHttpClient;

    public Netutil() {
        handler = new Handler();
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();

    }

    public static Netutil getInstance() {
        if (netutil==null){
            synchronized (Netutil.class){
                if (netutil==null){
                    netutil=new Netutil();
                }
            }
        }
        return netutil;
    }

    public  interface  Mycallback{
        void getjaon(String name);
        void  onError(Throwable throwable);

    }



}
