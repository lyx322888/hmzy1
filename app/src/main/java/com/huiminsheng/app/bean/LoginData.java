package com.huiminsheng.app.bean;

import java.io.Serializable;
import java.util.List;

public class LoginData {

    private String access_token;
    private LoginProFile profile;
    private LoginGlobal global;
    private String mall;
    private String merchant;
    private String about_url;
    private String help_url;
    private String credit_url;
    private String shop_status;
    private String shop_code;
    private String shop_audit_memo;
    private String rookie_url;//首页新手指南url
    private String policy_url;//办卡政策
    private String strategy_url;
    private LoginAd ad;
    private PaymentInfo ad_img;
    private List<String> service_qq;
    private String fee_url;
    private List<String> wallet;
    private String xyk_oem;//用于信用卡进度查询的字段
    private String up_url;//我要代理接口
    private List<Collecting> collecting;//移动收款的图片和链接
    private String wxcode;//微信二维码字段
    private String area;//地区
    private String address;//详细地址
    private Obligate obligate;//积分兑换
    private String member_url;
    private String is_mobile;//是否绑定
    private String Superior_moblie;//推荐人

    public String getSuperior_moblie() {
        return Superior_moblie;
    }

    public void setSuperior_moblie(String superior_moblie) {
        Superior_moblie = superior_moblie;
    }


    public String getIs_mobile() {
        return is_mobile;
    }

    public void setIs_mobile(String is_mobile) {
        this.is_mobile = is_mobile;
    }


    public String getMember_url() {
        return member_url;
    }

    public void setMember_url(String member_url) {
        this.member_url = member_url;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public LoginProFile getProfile() {
        return profile;
    }

    public void setProfile(LoginProFile profile) {
        this.profile = profile;
    }

    public LoginGlobal getGlobal() {
        return global;
    }

    public void setGlobal(LoginGlobal global) {
        this.global = global;
    }

    public String getMall() {
        return mall;
    }

    public void setMall(String mall) {
        this.mall = mall;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getAbout_url() {
        return about_url;
    }

    public void setAbout_url(String about_url) {
        this.about_url = about_url;
    }

    public String getHelp_url() {
        return help_url;
    }

    public void setHelp_url(String help_url) {
        this.help_url = help_url;
    }

    public String getCredit_url() {
        return credit_url;
    }

    public void setCredit_url(String credit_url) {
        this.credit_url = credit_url;
    }

    public String getShop_status() {
        return shop_status;
    }

    public void setShop_status(String shop_status) {
        this.shop_status = shop_status;
    }

    public String getShop_code() {
        return shop_code;
    }

    public void setShop_code(String shop_code) {
        this.shop_code = shop_code;
    }

    public String getShop_audit_memo() {
        return shop_audit_memo;
    }

    public void setShop_audit_memo(String shop_audit_memo) {
        this.shop_audit_memo = shop_audit_memo;
    }

    public String getRookie_url() {
        return rookie_url;
    }

    public void setRookie_url(String rookie_url) {
        this.rookie_url = rookie_url;
    }

    public String getPolicy_url() {
        return policy_url;
    }

    public void setPolicy_url(String policy_url) {
        this.policy_url = policy_url;
    }

    public String getStrategy_url() {
        return strategy_url;
    }

    public void setStrategy_url(String strategy_url) {
        this.strategy_url = strategy_url;
    }

    public LoginAd getAd() {
        return ad;
    }

    public void setAd(LoginAd ad) {
        this.ad = ad;
    }

    public PaymentInfo getAd_img() {
        return ad_img;
    }

    public void setAd_img(PaymentInfo ad_img) {
        this.ad_img = ad_img;
    }

    public List<String> getService_qq() {
        return service_qq;
    }

    public void setService_qq(List<String> service_qq) {
        this.service_qq = service_qq;
    }

    public String getFee_url() {
        return fee_url;
    }

    public void setFee_url(String fee_url) {
        this.fee_url = fee_url;
    }

    public List<String> getWallet() {
        return wallet;
    }

    public void setWallet(List<String> wallet) {
        this.wallet = wallet;
    }

    public String getXyk_oem() {
        return xyk_oem;
    }

    public void setXyk_oem(String xyk_oem) {
        this.xyk_oem = xyk_oem;
    }

    public List<Collecting> getCollecting() {
        return collecting;
    }

    public void setCollecting(List<Collecting> collecting) {
        this.collecting = collecting;
    }

    public String getUp_url() {
        return up_url;
    }

    public void setUp_url(String up_url) {
        this.up_url = up_url;
    }

    public String getWxcode() {
        return wxcode;
    }

    public void setWxcode(String wxcode) {
        this.wxcode = wxcode;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Obligate getObligate() {
        return obligate;
    }

    public void setObligate(Obligate obligate) {
        this.obligate = obligate;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "access_token='" + access_token + '\'' +
                ", profile=" + profile +
                ", global=" + global +
                ", mall='" + mall + '\'' +
                ", merchant='" + merchant + '\'' +
                ", about_url='" + about_url + '\'' +
                ", help_url='" + help_url + '\'' +
                ", credit_url='" + credit_url + '\'' +
                ", shop_status='" + shop_status + '\'' +
                ", shop_code='" + shop_code + '\'' +
                ", shop_audit_memo='" + shop_audit_memo + '\'' +
                ", rookie_url='" + rookie_url + '\'' +
                ", policy_url='" + policy_url + '\'' +
                ", strategy_url='" + strategy_url + '\'' +
                ", ad=" + ad +
                ", ad_img=" + ad_img +
                ", service_qq=" + service_qq +
                ", fee_url='" + fee_url + '\'' +
                ", wallet=" + wallet +
                ", xyk_oem='" + xyk_oem + '\'' +
                ", up_url='" + up_url + '\'' +
                ", collecting=" + collecting +
                ", wxcode='" + wxcode + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                ", obligate=" + obligate +
                '}';
    }

    public static class Collecting implements Serializable {

        /**
         * img : http://api.richwealth.cn/image/banner/collecting01.png
         * url : https://one.zhongan.com/fcp/msj-h5/#/?channel=Zy5yls052!dty
         */

        private String img;
        private String url;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "Collecting{" +
                    "img='" + img + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    public static class Obligate implements Serializable {
        /**
         * logo :
         * name : 积分兑换
         * url : http://m.tugou.ren/integral/index?mchid=1000019708
         * status : 1
         */

        private String logo;
        private String name;
        private String url;
        private String status;

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }


        @Override
        public String toString() {
            return "Obligate{" +
                    "logo='" + logo + '\'' +
                    ", name='" + name + '\'' +
                    ", url='" + url + '\'' +
                    ", status='" + status + '\'' +
                    '}';
        }
    }
}