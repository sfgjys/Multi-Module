**注意**: res文件夹下的所有的资源文件可以换颜色和图片内容，但是不能修改所有的文件名，和布局文件中的组件 id。

**导入本Module**:

 1. 本Module需要依赖baselibrary,所以要先导入baselibrary
 2. 然后在导入本Module(将整个文件夹直接复制到根目录下，然后打开根目录下的setting.gradle文件，添加':umeng_share')
 3. 接着在 主Module 的build.gradle文件中添加 compile project(':umeng_share')




使用步骤
----


 1. 在umeng_share Module的清单文件中，将写有“your.package_name”替换为 主Module 的包名
 2. 在umeng_share Module的java文件夹下，修改"your.package_name"为 主Module 的包名
 3. 在  主Module  中的自定义的Application的onCreate方法中写入如下代码，初始化并开启友盟社会哈分享:

```
    // 参数二：友盟APP_KEY    参数三：是否开启日志打印    参数四：是否开启ShareSDK debug模式
    ShareTools.initializeShareFeature(this,"59892f08310c9307b60023d0",true,true);
     /*  代码块的描述: 各个平台的配置 配置三方平台的appkey：，建议放在全局Application或者程序入口 */
    static {
        PlatformConfig.setAlipay("2015111700822536");
        PlatformConfig.setDing("dingoalmlnohc0wggfedpk");
        // 豆瓣RENREN平台目前只能在服务器端配置
        PlatformConfig.setDropbox("oz8v5apet3arcdy", "h7p2pjbzkkxt02a");
        // 邮件
        // 印象笔记
        // facebook
        // flickr
        // foursquare
        // gplus
        // instagram
        PlatformConfig.setKakao("e4f60e065048eb031e235c806b31c70f");
        // line
        // linkedin
        PlatformConfig.setPinterest("1439206");
        // pocket
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        // renren
        // 短信
        // 腾讯微博
        // tumblr
        PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
        PlatformConfig.setVKontakte("5764965", "5My6SNliAaLxEm3Lyd9J");
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        // whatsapp
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
        PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
        // ynote
    }
```

配置签名文件：
	 在主Module的 build.gradle 文件同一目录下放入签名文件
	 然后在 build.gradle 文件中的   android｛｝中进行编辑：


```
// 注意： 签名文件如果不加，部分平台的授权会受到影响。
    buildTypes {
        debug {
            minifyEnabled false
            signingConfig signingConfigs.debug// debug模式下使用signingConfigs配置中叫“debug”的配置
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
        release {
            minifyEnabled false
            signingConfig signingConfigs.debug// release模式下使用signingConfigs配置中叫“debug”的配置
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
    signingConfigs {// 配置签名文件的信息
        // debug 是签名文件的自定义变量名
        debug {
            storeFile file('debug.keystore')
            storePassword "android"
            keyAlias "androiddebugkey"
            keyPassword "android"
        }
    }
```
最后就可以使用封装的方法了，在  ShareTools	 GetPlatformUserInfoActivity	ShareSundryContentActivity  这个三个类中
