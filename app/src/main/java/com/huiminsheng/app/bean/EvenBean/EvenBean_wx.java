package com.huiminsheng.app.bean.EvenBean;

public class EvenBean_wx {
    private String message;
    private String nick_name;

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }

    public String getWxid() {
        return wxid;
    }

    public void setWxid(String wxid) {
        this.wxid = wxid;
    }

    private String headpic;
    private String wxid;
    public  EvenBean_wx(String message,String nick_name,String headpic,String wxid){
        this.message=message;
        this.nick_name=nick_name;
        this.headpic=headpic;
        this.wxid=wxid;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
