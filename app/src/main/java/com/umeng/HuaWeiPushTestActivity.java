package com.umeng;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.huawei.updatesdk.service.appmgr.bean.UpgradeRequest;
import com.new0407.MainActivity;
import com.new0407.MainApplication;
import com.new0407.broadcast.NativeToRN;
import com.new0407.broadcast.PreLoadReactDelegate;
import com.new0407.model.PushModel;
import com.umeng.message.UmengNotifyClickActivity;
import com.umeng.message.entity.UMessage;

import org.android.agoo.common.AgooConstants;

import java.util.Map;
public class HuaWeiPushTestActivity extends UmengNotifyClickActivity {

    private static String TAG = HuaWeiPushTestActivity.class.getName();
    public  static HuaWeiPushTestActivity pushTestActivity;
    TextView tv;
    private boolean isShare=false;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_push);
        tv=findViewById(R.id.activity_push_id);
        pushTestActivity=this;


    }

    @Override
    public void onMessage(Intent intent) {
        super.onMessage(intent);  //此方法必须调用，否则无法统计打开数
        if(!isShare){
            String body = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
            JSONObject infoJson = JSONObject.parseObject(body);
            PushModel pushModel = JSON.toJavaObject(infoJson, PushModel.class);
            String param=pushModel.toString();
            getAndroidToRN("push", param);
        }else{
            pushTestActivity=null;
            this.finish();
        }
    }
    public void getAndroidToRN(String infofrom,String param){
        if(MainActivity.mainActivity==null) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("param", param);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }else{
            MainApplication.getReactPackage().mModule.sendDataToJS(param);

        }
        pushTestActivity=null;
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pushTestActivity=null;
        this.finish();
    }
}
