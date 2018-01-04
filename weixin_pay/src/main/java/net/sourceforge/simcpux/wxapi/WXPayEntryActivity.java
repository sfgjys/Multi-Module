package net.sourceforge.simcpux.wxapi;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.zhq.baselibrary.base.MyBaseActivity;

/**
 * 类的描述: 接收微信支付结果
 */
public class WXPayEntryActivity extends MyBaseActivity implements IWXAPIEventHandler {

    /**
     * 变量的描述: 成功	展示成功页面
     */
    private final int PAY_SUCCESS = 0;
    /**
     * 变量的描述: 错误	可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。
     */
    private final int PAY_FAILED = -1;
    /**
     * 变量的描述: 用户取消	无需处理。发生场景：用户不支付了，点击取消，返回APP。
     */
    private final int USER_CANCEL = -2;

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            switch (resp.errCode) {
                case PAY_SUCCESS:
                    System.out.println();
                    break;
                case PAY_FAILED:
                    System.out.println();
                    break;
                case USER_CANCEL:
                    System.out.println();
                    break;
            }
        }
    }
}
