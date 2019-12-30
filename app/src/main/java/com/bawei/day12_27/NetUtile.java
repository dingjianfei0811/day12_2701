package com.bawei.day12_27;

import android.os.Handler;
import android.widget.Toast;

import java.io.IOException;
import java.text.Format;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：丁建飞
 * 时间：2019/12/27  19:34
 * 类名：com.bawei.day12_27
 */
public class NetUtile  {
    private  static  NetUtile netUtile;
    private final Handler handler;
    private final OkHttpClient okHttpClient;

    private NetUtile() {
        handler = new Handler();
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();

    }

    public static NetUtile getInstance() {
        if (netUtile==null){
            synchronized (NetUtile.class){
                if (netUtile==null){
                    netUtile=new NetUtile();
                }
            }
        }
        return netUtile;
    }


    public interface  MycallBack{
        void  callback(String json);
        void  onerror(Throwable throwable);
    }

public  void  getjsonGet(String http,MycallBack mycallBack){


    Request request = new Request.Builder()
            .url(http)
            .get()
            .build();

    Call call = okHttpClient.newCall(request);

    call.enqueue(new Callback() {
        @Override
         public void onFailure(Call call, IOException e) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mycallBack.onerror(e);
                }
            });
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {

            if (response != null&&response.isSuccessful()) {
                String string = response.body().string();
//转成主线程
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mycallBack.callback(string);
                    }
                });
            }else {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                   mycallBack.onerror(new Exception("失败"));
                    }
                });
            }
        }
    });

}




    public  void  getjsonPost(String http, Map<String ,String > map,MycallBack mycallBack){

        FormBody.Builder builder = new FormBody.Builder();
        for (String key : map.keySet()) {
            String s = map.get(key);
            builder.add(key,s);
        }

        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .url(http)
                .post(formBody)
                .build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mycallBack.onerror(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response != null&&response.isSuccessful()) {
                    String string = response.body().string();

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            mycallBack.callback(string);
                        }
                    });
                }else {

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            mycallBack.onerror(new Exception("失败"));
                        }
                    });
                }
            }
        });

    }
}
