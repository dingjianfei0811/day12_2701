package com.bawei.day12_27;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.but1)
    Button but1;
    @BindView(R.id.but2)
    Button but2;
    @BindView(R.id.img)
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        CodeUtils.init(this);


        String http="http://blog.zhaoliang5156.cn/api/student/clazzstudent.json\n";
        NetUtile.getInstance().getjsonGet(http, new NetUtile.MycallBack() {
            @Override
            public void callback(String json) {
                Toast.makeText(MainActivity.this, "成功"+json, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onerror(Throwable throwable) {
                Toast.makeText(MainActivity.this, "失败"+throwable.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


        img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
//                CodeUtils.analyzeByImageView(img, new CodeUtils.AnalyzeCallback() {
//                    @Override
//                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
//                        Toast.makeText(MainActivity.this, ""+result, Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onAnalyzeFailed() {
//
//                    }
//                });


                return true;
            }
        });
    }

    @OnClick({R.id.name, R.id.but1, R.id.but2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.name:
//                String s = name.getText().toString();
//                Bitmap image = CodeUtils.createImage(s, 200, 200, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
//                img.setImageBitmap(image);
                String s = name.getText().toString();
                Bitmap image = CodeUtils.createImage(s, 200, 200, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                img.setImageBitmap(image);
                break;
            case R.id.but1:
                CodeUtils.analyzeByCamera(this);
                break;
            case R.id.but2:
                CodeUtils.analyzeByPhotos(this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CodeUtils.onActivityResult(this, requestCode, resultCode, data, new CodeUtils.AnalyzeCallback() {
            @Override
            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                Toast.makeText(MainActivity.this, ""+result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnalyzeFailed() {

            }
        });

    }
}
