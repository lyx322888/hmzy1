package com.huiminsheng.app.unify;


/**
 *  errcode 转中文
 * Created by Lyx on 2017/11/29.
 */

public class ErrMsg {
    public static String getErrMsg(int errCode){
        String err;
        switch (errCode) {
            case 3002:
                err = "请输入正确手机号";
                break;
            case 4204:
                err = "请输入正确手机号";
                break;
            case 3001:
                err = "网络有点慢哦，稍后试试？"+"(3001)";
                break;
            case 4018:
                err = "重复验证";
                break;
            case 2993:
                err = "验证码校验失败";
                break;
            case 2996:
                err = "两次请求间隔不超过 1 分钟";
                break;
            case 4015:
                err = "验证码不正确";
                break;
            case 2997:
                err = "未获取验证码";
                break;
            case 4016:
                err = "服务器繁忙，请稍后再试哦"+"(4016)";
                break;
            case 4007:
                err = "验证码不正确";
                break;
            case 2998:
                err = "网络有点慢哦，稍后试试？"+"(2998)";
                break;
            case 4017:
                err = "验证码已失效，请重新获取";
                break;
            default:
                err = "网络有点慢哦，稍后试试？";
                break;
        }
        return err;
    }
}
