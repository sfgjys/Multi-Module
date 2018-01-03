package com.zhq.ali_pay.server;

import java.util.concurrent.BlockingDeque;

/**
 * 类的描述: 支付宝支付功能模块的字段配置
 */
public class AliPayFieldsConfig {
    /**
     * 变量的描述: 测试用户的APP_ID
     */
    public static final String APP_ID = "2016082600314329";
    /**
     * 变量的描述: 签名算法类型
     */
    public static final String SIGN_TYPE = "RSA2";
    /**
     * 变量的描述: RSA2签名算法的私钥
     */
    public static final String RSA2_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCmA592KmPH8lBzEkeon8uSv/NYZwOC5u1Tmoq52xmEIfM3ETUiA2CFpz4kV2nizX3acfIYBkFojJEKhrwTwNr/ziQhcWg+YxKCmPQYecu//5ixnHQSN7DEuv3QHW9DpBWu0ia6IF5QLnO0/OStDr4a7sT/QEU5zsleNE/LjV1PTfsewjuIoJndpjt/eu3AY+t4pg+QNjqVioUviv7/4FYx+mENElMrfNOEoB86hCm9nJpCPu/WGOUkKSTRMWxC9v/AebRa0InyrW+XoGOez4Et0xlkJHuvMESbqn4oNFlys03SpVJAFcwS4DrATf49Me0Jfe7Nt6jniVZeOgq7AHCzAgMBAAECggEAVgZ6kxjUK7/8wCQwzchADQM6bgyzXRmc0T27tSM7zaRqC5b3GXD2i0tmgB5r2ALzvEZfVOOo8Swl3HaE7xd4ZCXFwy6o/esIpHPtyOEUG6Gu1hpkayxAhTEAZN9b0IkuUzDCsKAlPxdTDe4d8S86g2pG2p3LQXFOF2q0Dp5qQ7Uk9LBPl5aWQORN/TskC1eYgiqQAtKpT4mSwi/0+gL+a+IRyeq/kS+o3FtGUvFkA9wL2dabLgrugHCZ4KVI6CU2a/EFGJGxTw7BK91mxoxyrCtvGxbSz/eEAXbZuesC7VXXnzeG6f6Asuo1SEo2OH3T6QmKycT5GHBifs6huml/wQKBgQDvBTJ4HEebejNLQd4y6pVxN2MzJLNsQ8boVL/zVVUuz86UzuNntznEUSSmMfnEWkitSO/NNJOFg5IJcgPllsH9npZKTZY6r5mxQo6CcyOl7mK5JKzxGngbyleNNOITj1VNpJZ81Mbz2doFHflMhlHAmpOkHLhZP8cwoRnEBodi5QKBgQCxzr38adQYUvdRrxmdS+tGYNanu2WGuCRyfcfLmyprW2m/+H7Qd7SKUPDea+1tP6BGi2mh15HSmxvxsNa70mUkvU0+cDMPB7w8xlXyBszGQa9ALRbTcKOBOB/l6JyIpQE2W2omwsWByxBmV7xfMe4hpaHVyv24unExvu1AyX7TtwKBgBhvg5Vb7sYqXAwZk8nIybZlXzLfUaiD1VXzNByAYNEEVa5I6F62EKXNVd3Xxh0Yzc1JY5qrAVno9YkZquRR4Us4Z0z5WZ5uTVwd3cvgUYipMlaTNUsA1b6hlQnOmj49rQ7fALOKXTyYzP0w70evg7QZGXbxqLcXGhoSnju4HycxAoGBAIwb90VBNyn7pnstWSfDvR92C2+ckUH5NG8rghaErIZwQAJPk849pXwgj80UisC9lFRCYvhu369vHjPzPyI9hgnPSWDcFL/RPpk9SFgw14eaFrihTw1KAu0+BpSqGT7rlur4Wcs1S2EdXEzo8bypoZXN6JqJHClGcQ/b/SRCIrg9AoGBAJaaMkiR0OPeWRPIw7p335Heyw2Fuj6pXsxDunhVGdUife7vDHKH0IOyb2XugBg44UkxwmmQcxDDwTCMM0yUD250WEjtQm80TEkTdFwA2t5+06dC5c9c5lPjeCw24M1KQaPe/1FhtlVd0CqgnM5x7PqmH7gGVO3HjutwyEq7b5p4";
    /**
     * 变量的描述: 支付结果通知回调Url
     */
    public static final String PAY_RESULT_NOTIFY_CALLBACK_URL = "";
}
