package com.new0407;

import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.facebook.react.ReactApplication;
import com.github.yamill.orientation.OrientationPackage;
import com.microsoft.codepush.react.CodePush;
import com.RNFetchBlob.RNFetchBlobPackage;
import com.new0407.broadcast.NativeToRNPackage;
import com.oblador.vectoricons.VectorIconsPackage;
import com.horcrux.svg.SvgPackage;
import com.BV.LinearGradient.LinearGradientPackage;
import com.swmansion.gesturehandler.react.RNGestureHandlerPackage;
import com.rnfs.RNFSPackage;
import com.reactlibrary.RNReactNativeDocViewerPackage;
import com.cmcewen.blurview.BlurViewPackage;
import com.reactnativecommunity.asyncstorage.AsyncStoragePackage;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;
import com.brentvatne.react.ReactVideoPackage;
import com.umeng.common.DplusReactPackage;
import com.umeng.common.RNUMConfigure;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;
import com.umeng.socialize.PlatformConfig;

import org.android.agoo.huawei.HuaWeiRegister;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MainApplication extends Application implements ReactApplication {

  private static final NativeToRNPackage mCommPackage = new NativeToRNPackage();
  public static NativeToRNPackage getReactPackage() {
    return mCommPackage;
  }
  private Handler handler;

  private static MainApplication application;
  public static MainApplication getInstance(){
    return application;
  }

  private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {

        @Override
        protected String getJSBundleFile() {
        return CodePush.getJSBundleFile();
        }

    
    @Override
    public boolean getUseDeveloperSupport() {
      return BuildConfig.DEBUG;
    }

    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
          new MainReactPackage(),
            new CodePush(getResources().getString(R.string.reactNativeCodePush_androidDeploymentKey), getApplicationContext(), BuildConfig.DEBUG),
            new RNFetchBlobPackage(),
            new VectorIconsPackage(),
            new SvgPackage(),
            new LinearGradientPackage(),
            new RNGestureHandlerPackage(),
            new RNFSPackage(),
            new RNReactNativeDocViewerPackage(),
            new BlurViewPackage(),
            new AsyncStoragePackage(),
              new DplusReactPackage(),
              new ReactVideoPackage(),
              new OrientationPackage(),
              mCommPackage
      );
    }

    @Override
    protected String getJSMainModuleName() {
      return "index";
    }
  };

  @Override
  public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }

  @Override
  public void onCreate() {
    super.onCreate();

//    application = this;
    /**
     * 设置组件化的Log开关
     * 参数: boolean 默认为false，如需查看LOG设置为true
     */
    UMConfigure.setLogEnabled(true);

    SoLoader.init(this, /* native exopackage */ false);
    RNUMConfigure.init(this, "申请", "Umeng", UMConfigure.DEVICE_TYPE_PHONE,
            "申请");
    HuaWeiRegister.register(this);
    initUpush();


  }
  private void initUpush() {
    PushAgent mPushAgent = PushAgent.getInstance(this);
    mPushAgent.setResourcePackageName(R.class.getPackage().getName());
    handler = new Handler(getMainLooper());

    //sdk开启通知声音
    mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);

    UmengMessageHandler messageHandler = new UmengMessageHandler() {

      /**
       * 自定义消息的回调方法
       */
      @Override
      public void dealWithCustomMessage(final Context context, final UMessage msg) {

        handler.post(new Runnable() {

          @Override
          public void run() {
            // TODO Auto-generated method stub
            // 对自定义消息的处理方式，点击或者忽略
            boolean isClickOrDismissed = true;
            if (isClickOrDismissed) {
              //自定义消息的点击统计
              UTrack.getInstance(getApplicationContext()).trackMsgClick(msg);
            } else {
              //自定义消息的忽略统计
              UTrack.getInstance(getApplicationContext()).trackMsgDismissed(msg);
            }
            Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
          }
        });
      }

      /**
       * 自定义通知栏样式的回调方法
       */
      @Override
      public Notification getNotification(Context context, UMessage msg) {
        switch (msg.builder_id) {
          case 1:
            Notification.Builder builder = new Notification.Builder(context);
            RemoteViews myNotificationView = new RemoteViews(context.getPackageName(), R.layout.notification_view);
            myNotificationView.setTextViewText(R.id.notification_title, msg.title);
            myNotificationView.setTextViewText(R.id.notification_text, msg.text);
            myNotificationView.setImageViewBitmap(R.id.notification_large_icon, getLargeIcon(context, msg));
            myNotificationView.setImageViewResource(R.id.notification_small_icon, getSmallIconId(context, msg));
            builder.setContent(myNotificationView)
                    .setSmallIcon(getSmallIconId(context, msg))
                    .setTicker(msg.ticker)
                    .setAutoCancel(true);

            return builder.getNotification();
          default:
            //默认为0，若填写的builder_id并不存在，也使用默认。
            return super.getNotification(context, msg);
        }
      }
    };
    mPushAgent.setMessageHandler(messageHandler);

    /**
     * 该Handler是在BroadcastReceiver中被调用，故
     * 如果需启动Activity，需添加Intent.FLAG_ACTIVITY_NEW_TASK
     * */
    UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
      @Override
      public void dealWithCustomAction(Context context, UMessage msg) {
        Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
      }

      @Override
      public void launchApp(Context context, UMessage msg) {
        if (msg.extra != null) {
          String url = msg.extra.get("weic");
        }
        super.launchApp(context, msg);
      }

      @Override
      public void openUrl(Context context, UMessage msg) {
        super.openUrl(context, msg);
      }

      @Override
      public void openActivity(Context context, UMessage msg) {
        Map extra = msg.extra;
        String param=extra..toString();
        MainApplication.getReactPackage().mModule.sendDataToJS(param);
      }
    };

    //使用自定义的NotificationHandler，来结合友盟统计处理消息通知，参考http://bbs.umeng.com/thread-11112-1-1.html
    mPushAgent.setNotificationClickHandler(notificationClickHandler);


    //注册推送服务 每次调用register都会回调该接口
    mPushAgent.register(new IUmengRegisterCallback() {
      @Override
      public void onSuccess(String deviceToken) {
        System.out.println("device token11: " + deviceToken);
      }

      @Override
      public void onFailure(String s, String s1) {
        System.out.println( "register failed11: " + s + " " + s1);
      }
    });
  }
  {

    PlatformConfig.setWeixin("申请", "申请");
    PlatformConfig.setQQZone("申请", "申请");
    PlatformConfig.setDing("申请");


  }
}
