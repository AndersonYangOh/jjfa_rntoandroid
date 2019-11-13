# 基于reactnative的安卓集成系统
## 一、安装依赖
* 必须安装的依赖有：Node、React Native 命令行工具、Python2 以及 JDK 和 Android Studio。
* 虽然你可以使用任何编辑器来开发应用（编写 js 代码），但你仍然必须安装 Android Studio 来获得编译 Android 应用所需的工具和环境。
### 1.Node, Python2, JDK
* 我们建议直接使用搜索引擎搜索下载 Node 、Python2 和Java SE Development Kit (JDK)
### 2.Yarn、React Native 的命令行工具（react-native-cli）
* Yarn是 Facebook 提供的替代 npm 的工具，可以加速 node 模块的下载。React Native 的命令行工具用于执行创建、初始化、更新项目、运行打包服务（packager）等任务。
## 二、Android 开发环境
### 1. 安装 Android Studio
### 2. 安装 Android SDK
### 3. 配置 ANDROID_HOME 环境变量
### 4. 把 platform-tools 目录添加到环境变量 Path 中
## 三、功能性说明
### 1.安卓推送
* MQTT协议实现
* XMPP协议实现
* C2DM云端推送功能(google官方提供,系统内置,但是国内用不了......)
* 中国统一推送(工信部牵头成立,但是目前只是开了几次会议,并没有什么实际的接口出来,不过以后应该会是中国境内的首选方案)
### 2.安卓分享
* 通过scheme跳转的本系统，并传递参数
### 3.安卓与rn交互
* 初始化传参
    `protected Bundle getLaunchOptions() {`
                                 ` //获取传入的参数`
                                   ` Intent intent     = getIntent();`
                                   ` String data_str = intent.getStringExtra("data");`
                                    `Bundle bundle = new Bundle();`
                                    `//bundle.putString("bundle","android传递的初始化参数");`
                                  `  bundle.putString("data",data_str);`
                                 `   return bundle;`
                        `    }`
* 广播传参
* Android原生可以使用RCTEventEmitter来注册事件,然后这里需要指定事件的名字,然后在js那端进行监听同样事件的名字监听,就可以收到消息得到数据
* Android注册关键代码
`reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, params);`

