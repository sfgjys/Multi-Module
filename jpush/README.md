**注意**: res文件夹下的所有 jpush_ 开头的资源文件可以换颜色和图片内容，但是不能修改所有的文件名，和布局文件中的组件 id。

**导入本Module**:

 1. 本Module需要依赖baselibrary,所以要先导入baselibrary
 2. 然后在导入本Module(将整个文件夹直接复制到根目录下，然后打开根目录下的setting.gradle文件，添加':jpush')
 3. 接着在 主Module 的build.gradle文件中添加 compile project(':jpush')




使用步骤
----


 1. 在jpush Module的清单文件中，将写有“your.app.packet.name”替换为 主Module 的包名
 2. 在jpush Module的清单文件中，将写有“your.set.jpush.app.key”替换为您设置的极光推送应用的AppKey
 3. 在  主Module  中的自定义的Application的onCreate方法中写入如下代码，初始化并开启极光推送:
 ```
  // 极光推送初始化
 JPUSHLogTools.LOG_ENABLE = true;      // 设置是否开启极光推送自定义日志打印
 JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
 JPushInterface.init(this);            // 初始化 JPush  参数为应用的 ApplicationContext
 ```
 4. 要使用标签或者别名设置功能调用AliasTagOperatorHelper类的handleAction方法
 5. 要接收  用户注册SDK获取Registration，用户接收SDK自定义消息，用户接收SDK通知栏信息，用户点击通知栏时，网络变化 连接/断开。那就需要按照MyReceiver类的格式在  主Module  中创建广播类，并且该广播类在清单文件中进行注册时，需要按照如下格式来

```
        <receiver
            android:name="类所在包名路径.MyReceiver"
            android:enabled="true"
            android:exported="false">
            <intent-filter>
                <!--Required  用户注册SDK获取Registration ID的intent-->
                <action android:name="cn.jpush.android.intent.REGISTRATION" />
                <!--Required  用户接收SDK自定义消息的intent-->
                <action android:name="cn.jpush.android.intent.MESSAGE_RECEIVED" />
                <!--Required  用户接收SDK通知栏信息的intent-->
                <action android:name="cn.jpush.android.intent.NOTIFICATION_RECEIVED" />
                <!--Required  用户点击通知栏时，自定义使用的Intent跳转(如果开发者未配置此 receiver action，那么，SDK 会默认打开应用程序的主 Activity，相当于用户点击桌面图标的效果。)-->
                <action android:name="cn.jpush.android.intent.NOTIFICATION_OPENED" />
                <!-- 接收网络变化 连接/断开 since 1.6.3 -->
                <action android:name="cn.jpush.android.intent.CONNECTION" />
                <category android:name="您的应用的包名" />
            </intent-filter>
        </receiver>
```