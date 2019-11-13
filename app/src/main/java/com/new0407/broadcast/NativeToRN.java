package com.new0407.broadcast;

import android.content.Intent;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.new0407.MainActivity;

public class NativeToRN extends ReactContextBaseJavaModule {
    private ReactApplicationContext reactContext;
    public NativeToRN(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "new0407";
    }
    /**
     从RN界面里面调用该方法，跳转到原生Activity
     **/
    @ReactMethod
    public void selectPhoto(){
        Intent intent = new Intent(getCurrentActivity(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        reactContext.startActivity(intent);

    }
    /**
     从原生Activity里面调用该方法，回传数据给RN界面
     **/
    public void sendDataToJS(String path){
        this.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit("自定义",path);
    }

}
