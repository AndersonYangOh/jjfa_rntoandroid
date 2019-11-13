package com.new0407;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.WindowManager;



public class SplashActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        setContentView(R.layout.splash);
        Thread myThread=new Thread(){//创建子线程
            @Override
            public void run() {
                try{
                    sleep(500);//使程序休眠0.5秒
                    Intent it=new Intent(SplashActivity.this
                            ,MainActivity.class);//启动MainActivity
                    startActivity(it);
                    finish();//关闭当前活动
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        myThread.start();//启动线程

    }
}
