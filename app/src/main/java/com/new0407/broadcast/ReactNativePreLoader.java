package com.new0407.broadcast;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactRootView;

import java.util.HashMap;
import java.util.Map;

/**
 * 预加载工具类
 * Created by Song on 2017/5/10.
 */
public class ReactNativePreLoader {

    private static final Map<String, ReactRootView> CACHE = new HashMap<>();

    public static void initLoad(Activity activity, String componentName) {
        initLoad(activity,componentName,null);
    }
    /**
     * 初始化ReactRootView，并添加到缓存
     *
     * @param activity
     * @param componentName
     */
    public static void initLoad(Activity activity, String componentName, Bundle bundle) {
        if (CACHE.get(componentName) != null) {
            return;
        }


        try {

            // 1.创建ReactRootView
            ReactRootView rootView = new ReactRootView(activity);
            rootView.startReactApplication(
                    ((ReactApplication) activity.getApplication()).getReactNativeHost().getReactInstanceManager(),
                    componentName,
                    bundle);

            // 2.添加到缓存
            CACHE.put(componentName, rootView);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 获取ReactRootView
     *
     * @param componentName
     * @return
     */
    public static ReactRootView getReactRootView(String componentName) {
        return CACHE.get(componentName);
    }

    /**
     * 从当前界面移除 ReactRootView
     *
     * @param component
     */
    public static void onDestroy(String component) {
        try {
            ReactRootView rootView = getReactRootView(component);
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        } catch (Throwable e) {
            Log.e("ReactNativePreLoader", e.getMessage());
        }
    }
}
