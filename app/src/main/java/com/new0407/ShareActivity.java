package com.new0407;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.new0407.model.PushModel;
import com.umeng.message.UmengNotifyClickActivity;

import org.android.agoo.common.AgooConstants;

/**
 * share
 * @author  longl
 */
public class ShareActivity extends Activity {

    private static String TAG = ShareActivity.class.getName();
    TextView tv;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_push);
        tv=findViewById(R.id.activity_push_id);
        Intent i_getvalue = getIntent();
//        tv.setText("请重启APP，这是隐藏功能，运营状况=1");
        String action = i_getvalue.getAction();
        if(Intent.ACTION_VIEW.equals(action)){
            Uri uri = i_getvalue.getData();
            if(uri != null){
                String userId = uri.getQueryParameter("自定义");
                String baseId= uri.getQueryParameter("自定义");
                String param="{\"自定义\":\""+userId+"\",\"自定义\":\""+baseId+"\"}";
                getAndroidToRN(param);
            }
        }


    }


    public void getAndroidToRN(String param){
        if(MainActivity.mainActivity==null) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("param", param);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }else{
            MainApplication.getReactPackage().mModule.sendDataToJS(param);

        }
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.finish();
    }
}
