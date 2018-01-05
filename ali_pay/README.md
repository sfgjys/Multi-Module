**导入本Module**:

 1. 本Module需要依赖baselibrary,所以要先导入baselibrary
 2. 然后在导入本Module(将整个文件夹直接复制到根目录下，然后打开根目录下的setting.gradle文件，添加':ali_pay')
 3. 接着在 主Module 的build.gradle文件中添加 compile project(':ali_pay')




使用步骤
----

 1. client包下的内容是给Android断使用的直接调用静态方法 aliPay() 就行,
 2.  aliPay() 返回的结果有可能不对，所以结果还是得让后台从支付宝服务器获取
 3.  server包下的内容是给java后台使用的，先使用 `OrderFormBusinessInfoBuild` 建造者类去创建订单信息对象，在将该对象转换为json数据，使用json数据作为参数调用`OrderFormInfoTool`类的`getOrderFormPublicInfoMap()`静态方法，获取Map数据作为参数，调用`OrderFormInfoTool`类的`getUltimatelyPayInfoString()`静态方法，获得最终客户端调用支付sdk所需要的参数信息