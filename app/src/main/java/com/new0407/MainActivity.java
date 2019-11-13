package com.new0407;

import android.content.Intent;
import android.os.Bundle;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.umeng.HuaWeiPushTestActivity;
import com.umeng.PushModule;
import com.umeng.ShareModule;
import com.umeng.message.PushAgent;
import com.umeng.socialize.UMShareAPI;

public class MainActivity extends ReactActivity {
    public static MainActivity mainActivity;
    private String param="0";
    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "new0407";
    }



    // 添加以下代码
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity=this;
        ShareModule.initSocialSDK(this);
        PushModule.initPushSDK(this);
        PushAgent.getInstance(this).onAppStart();

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainActivity=null;
    }

    @Override
    protected ReactActivityDelegate createReactActivityDelegate() {
        return new ReactActivityDelegate(this, getMainComponentName()) {
            @Override
            protected Bundle getLaunchOptions() {
                Bundle bundle=new Bundle();
                if(HuaWeiPushTestActivity.pushTestActivity!=null){
                    HuaWeiPushTestActivity.pushTestActivity.finish();
                    HuaWeiPushTestActivity.pushTestActivity=null;
                }
                String str=getIntent().getStringExtra("param");
                if(str!=null&&str.length()>0){
                    param=str;
                }
                bundle.putString("param",param);
                return bundle;
            }
        };
    }
}
