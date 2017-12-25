package com.zhq.jpush;


import com.zhq.baselibrary.jxl.OperationExcel;
import com.zhq.jpush.tool.JPUSHLogTools;

public class JPushErrorCode {
    private static final String TAG = "JPushErrorCode";

    public void filtrateErrorCode(int errorCode) {
        switch (errorCode) {
            case 6001:
                JPUSHLogTools.e(TAG, "Code: "+errorCode+" ,描述: 无效的设置，tag/alias 不应参数都为 null,3.0.7开始的新tag/alias接口此错误码表示 tag/alias参数不能为空" + " ,详细解释: ");
                break;
            case 6002:
                JPUSHLogTools.e(TAG, "Code: "+errorCode+" ,描述: 设置超时" + " ,详细解释: 建议重试");
                break;
            case 6003:
                JPUSHLogTools.e(TAG, "Code: "+errorCode+" ,描述: " + " ,详细解释: ");
                break;
            case 6004:
                JPUSHLogTools.e(TAG, "Code: "+errorCode+" ,描述: " + " ,详细解释: ");
                break;
            case 6005:
                JPUSHLogTools.e(TAG, "Code: "+errorCode+" ,描述: " + " ,详细解释: ");
                break;
            case 6006:
                JPUSHLogTools.e(TAG, "Code: "+errorCode+" ,描述: " + " ,详细解释: ");
                break;
            case 6007:
                JPUSHLogTools.e(TAG, "Code: "+errorCode+" ,描述: " + " ,详细解释: ");
                break;
            case 6008:
                JPUSHLogTools.e(TAG, "Code: "+errorCode+" ,描述: " + " ,详细解释: ");
                break;
            case 6009:
                JPUSHLogTools.e(TAG, "Code: "+errorCode+" ,描述: " + " ,详细解释: ");
                break;
            case 6010:
                JPUSHLogTools.e(TAG, "Code: "+errorCode+" ,描述: " + " ,详细解释: ");
                break;
            case 6011:
                JPUSHLogTools.e(TAG, "Code: "+errorCode+" ,描述: " + " ,详细解释: ");
                break;
            case 6012:
                JPUSHLogTools.e(TAG, "Code: "+errorCode+" ,描述: " + " ,详细解释: ");
                break;
            case 6013:
                JPUSHLogTools.e(TAG, "Code: "+errorCode+" ,描述: " + " ,详细解释: ");
                break;
            case 6014:
                JPUSHLogTools.e(TAG, "Code: "+errorCode+" ,描述: " + " ,详细解释: ");
                break;
            case 6015:
                JPUSHLogTools.e(TAG, "Code: "+errorCode+" ,描述: " + " ,详细解释: ");
                break;
            case 6016:
                JPUSHLogTools.e(TAG, "Code: "+errorCode+" ,描述: " + " ,详细解释: ");
                break;
            case 6017:
                JPUSHLogTools.e(TAG, "Code: "+errorCode+" ,描述: " + " ,详细解释: ");
                break;
            case 6018:
                JPUSHLogTools.e(TAG, "Code: "+errorCode+" ,描述: " + " ,详细解释: ");
                break;
            case 6019:
                JPUSHLogTools.e(TAG, "Code: "+errorCode+" ,描述: " + " ,详细解释: ");
                break;
            case 6020:
                JPUSHLogTools.e(TAG, "Code: "+errorCode+" ,描述: " + " ,详细解释: ");
                break;
            case 6021:
                JPUSHLogTools.e(TAG, "Code: "+errorCode+" ,描述: " + " ,详细解释: ");
                break;
            case 6022:
                JPUSHLogTools.e(TAG, "Code: "+errorCode+" ,描述: " + " ,详细解释: ");
                break;
            case 1005:
                JPUSHLogTools.e(TAG, "Code: "+errorCode+" ,描述: " + " ,详细解释: ");
                break;
            case 1008:
                JPUSHLogTools.e(TAG, "Code: "+errorCode+" ,描述: " + " ,详细解释: ");
                break;
            case 1009:
                JPUSHLogTools.e(TAG, "Code: "+errorCode+" ,描述: " + " ,详细解释: ");
                break;
            case -994:
                JPUSHLogTools.e(TAG, "Code: "+errorCode+" ,描述: " + " ,详细解释: ");
                break;
            case -996:
                JPUSHLogTools.e(TAG, "Code: "+errorCode+" ,描述: " + " ,详细解释: ");
                break;
            case -997:
                JPUSHLogTools.e(TAG, "Code: "+errorCode+" ,描述: " + " ,详细解释: ");
                break;
        }
    }
}
