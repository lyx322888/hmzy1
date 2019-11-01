package com.huiminsheng.app.bean;


import java.util.List;

public class Login {

    /**
     * result : {"code":"10000","msg":"请求成功"}
     * link :
     * data : {"access_token":"cacbedab382e51fd3141ea22be2fa6d4","profile":{"mchid":"2000000021","mobile":"15060856259","name":"","nick_name":"李亚雄","headpic":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLMe6WqLoBqGvwghubMLESMmYxm69HknLfy5VrRjJ3icbrgr4ib0icXz1yhMSvIa8YibHaPrt9ibHFUxWQ/132","card_no":"","bank":"","bank_city":"","bank_no":"","bank_address":"","status":"1","status_shop":"0","status_data":{"info":"1","img":"1"},"level":{"id":"1","oemid":"90005","lf_id":"1","name":"庄主","level":"1","image_url":"http://hmsapi.zhuoranxiaoming.cn/uploads/level/user_level1@2x.png"},"auth_file":{"card_front":"","card_back":"","card_hand":"","bank_img":""},"day_max":"","is_pay":"1","verify_ag":"0","id":"2000000021","card_expiry":"","bank_logo":"http://hmsapi.zhuoranxiaoming.cn","audit_memo":"","trade_pwd_set":"0","level_name":{"level_1":"一星庄主","level_2":"二星庄主","level_3":"三星庄主"},"invite_name":"","invite_mobile":""},"global":{"share_title":"惠民庄园","share_msg":"惠民庄园-刷卡实时到账最低0.28%，分润当天秒结。世界在身边，支付在指尖。","share_url":"http://m.tugou.ren/reg/index2/invite_mobile/15060856259/oemid/90005","op_tel":"400-699-8890","op_qq":"33000000","op_weixin":"j000000","hint":"0","hint_title":"惠民庄园提示","hint_msg":"我要还款--新增完美账单落地商户上线啦！\r\n最新费率：粉丝0.78%，会员0.68%、店长0.58%，超低费率，自动还款。\r\n推荐升级返佣:直接60%，间接10%，间接5%\r\n新增多家银行快速办卡，最高返佣80元。","hint_btn":"我知道了"},"mall":"http://139.196.105.196:8038/wap/","merchant":"http://m.tugou.ren/reg/index2/invite_mobile/15060856259/oemid/90005","about_url":"","help_url":"http://hms.zhuoranxiaoming.cn/wxother/guide.html","credit_url":"http://hzcapi.richwealth.cn/merchant/judge?mchid=2000000021","shop_status":"3","shop_audit_memo":"","shop_code":"-1","version":"0","ttfver":"no","rookie_url":"http://m.tugou.ren/home/novice","strategy_url":"http://m.tugou.ren/home/strategy","ad":{"is_full":"-1","url":"http://m.tugou.ren/help/add?oemid=80000&mchid=0","image":"\\image\\ad\\ruleother.png","width":"618","height":"649"},"ttfgs":"","ttfbk":"办卡","ttfdk":"贷款","ad_img":{"img":"http://hmsapi.zhuoranxiaoming.cn/image/banner/ad20180226.png","url":"http://hzcapi.richwealth.cn/merchant/judge?mchid=2000000021","left":"费率0.5% 立即到帐","lev1":"0.35","lev2":"0.28"},"service_qq":["2253196423","1961139784"],"fee_url":"http://www.ipandata.com/pingan/insurance.html?channel=tyyd","wallet":["收款、还款返佣","升级返佣","信用卡返佣","贷款返佣"],"xyk_oem":"44cc98f8c65f902e","collecting":[{"img":"http://hmsapi.zhuoranxiaoming.cn/image/banner/collecting01.png","url":"https://one.zhongan.com/fcp/msj-h5/#/?channel=Zy5yls052!dty"},{"img":"http://hmsapi.zhuoranxiaoming.cn/image/banner/banner20180619.png","url":"http://m.tugou.ren/help/add?oemid=80000&mchid=2000000021"}],"obligate":{"logo":"","name":"会员信息","url":"","status":"1"},"menus":[{"name":"海外直邮","url":"http://hzc.richwealth.cn/index/goodstap?mchid=2000000021/tap/1","logo":"http://hzcapi.richwealth.cn/uploads/icons/hwzy@2x.png","is_view":"1"},{"name":"品牌直达","url":"http://hzc.richwealth.cn/index/goodstap?mchid=2000000021/tap/2","logo":"http://hzcapi.richwealth.cn/uploads/icons/ppzd@2x.png","is_view":"1"},{"name":"特价专区","url":"http://hzc.richwealth.cn/index/goodstap?mchid=2000000021/tap/3","logo":"http://hzcapi.richwealth.cn/uploads/icons/bxzg@2x.png","is_view":"1"},{"name":"福利红包","url":"http://hzc.richwealth.cn/index/grabredenvelope?mchid=2000000021","logo":"http://hzcapi.richwealth.cn/uploads/icons/hongbao@3x.png","is_view":"1"},{"name":"境外消费","url":"http://hzc.richwealth.cn/index/starlist?mchid=2000000021","logo":"http://hzcapi.richwealth.cn/uploads/icons/jwxf@3x.png","is_view":"1"}],"member_url":"","city_owners":"http://hzc.richwealth.cn/index/cityownerphone/mchid/0","alipay_account":"","alipay_name":"","check_day":"7","category":[{"cate_id":"1","cate_name":"限时特价"},{"cate_id":"9","cate_name":"店长"},{"cate_id":"3","cate_name":"限时8.8折"},{"cate_id":"4","cate_name":"首付"},{"cate_id":"7","cate_name":"100元充值卡"},{"cate_id":"8","cate_name":"9.9折"}],"bank_str":"","is_Shopowner":"0","shopowner_id":"33","sales_usable":"0.00","is_mobile":"1","Superior_moblie":""}
     */

    private ResultBean result;
    private String link;
    private DataBean data;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class ResultBean {
        /**
         * code : 10000
         * msg : 请求成功
         */

        private String code;
        private String msg;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    public static class DataBean {
        /**
         * access_token : cacbedab382e51fd3141ea22be2fa6d4
         * profile : {"mchid":"2000000021","mobile":"15060856259","name":"","nick_name":"李亚雄","headpic":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLMe6WqLoBqGvwghubMLESMmYxm69HknLfy5VrRjJ3icbrgr4ib0icXz1yhMSvIa8YibHaPrt9ibHFUxWQ/132","card_no":"","bank":"","bank_city":"","bank_no":"","bank_address":"","status":"1","status_shop":"0","status_data":{"info":"1","img":"1"},"level":{"id":"1","oemid":"90005","lf_id":"1","name":"庄主","level":"1","image_url":"http://hmsapi.zhuoranxiaoming.cn/uploads/level/user_level1@2x.png"},"auth_file":{"card_front":"","card_back":"","card_hand":"","bank_img":""},"day_max":"","is_pay":"1","verify_ag":"0","id":"2000000021","card_expiry":"","bank_logo":"http://hmsapi.zhuoranxiaoming.cn","audit_memo":"","trade_pwd_set":"0","level_name":{"level_1":"一星庄主","level_2":"二星庄主","level_3":"三星庄主"},"invite_name":"","invite_mobile":""}
         * global : {"share_title":"惠民庄园","share_msg":"惠民庄园-刷卡实时到账最低0.28%，分润当天秒结。世界在身边，支付在指尖。","share_url":"http://m.tugou.ren/reg/index2/invite_mobile/15060856259/oemid/90005","op_tel":"400-699-8890","op_qq":"33000000","op_weixin":"j000000","hint":"0","hint_title":"惠民庄园提示","hint_msg":"我要还款--新增完美账单落地商户上线啦！\r\n最新费率：粉丝0.78%，会员0.68%、店长0.58%，超低费率，自动还款。\r\n推荐升级返佣:直接60%，间接10%，间接5%\r\n新增多家银行快速办卡，最高返佣80元。","hint_btn":"我知道了"}
         * mall : http://139.196.105.196:8038/wap/
         * merchant : http://m.tugou.ren/reg/index2/invite_mobile/15060856259/oemid/90005
         * about_url :
         * help_url : http://hms.zhuoranxiaoming.cn/wxother/guide.html
         * credit_url : http://hzcapi.richwealth.cn/merchant/judge?mchid=2000000021
         * shop_status : 3
         * shop_audit_memo :
         * shop_code : -1
         * version : 0
         * ttfver : no
         * rookie_url : http://m.tugou.ren/home/novice
         * strategy_url : http://m.tugou.ren/home/strategy
         * ad : {"is_full":"-1","url":"http://m.tugou.ren/help/add?oemid=80000&mchid=0","image":"\\image\\ad\\ruleother.png","width":"618","height":"649"}
         * ttfgs :
         * ttfbk : 办卡
         * ttfdk : 贷款
         * ad_img : {"img":"http://hmsapi.zhuoranxiaoming.cn/image/banner/ad20180226.png","url":"http://hzcapi.richwealth.cn/merchant/judge?mchid=2000000021","left":"费率0.5% 立即到帐","lev1":"0.35","lev2":"0.28"}
         * service_qq : ["2253196423","1961139784"]
         * fee_url : http://www.ipandata.com/pingan/insurance.html?channel=tyyd
         * wallet : ["收款、还款返佣","升级返佣","信用卡返佣","贷款返佣"]
         * xyk_oem : 44cc98f8c65f902e
         * collecting : [{"img":"http://hmsapi.zhuoranxiaoming.cn/image/banner/collecting01.png","url":"https://one.zhongan.com/fcp/msj-h5/#/?channel=Zy5yls052!dty"},{"img":"http://hmsapi.zhuoranxiaoming.cn/image/banner/banner20180619.png","url":"http://m.tugou.ren/help/add?oemid=80000&mchid=2000000021"}]
         * obligate : {"logo":"","name":"会员信息","url":"","status":"1"}
         * menus : [{"name":"海外直邮","url":"http://hzc.richwealth.cn/index/goodstap?mchid=2000000021/tap/1","logo":"http://hzcapi.richwealth.cn/uploads/icons/hwzy@2x.png","is_view":"1"},{"name":"品牌直达","url":"http://hzc.richwealth.cn/index/goodstap?mchid=2000000021/tap/2","logo":"http://hzcapi.richwealth.cn/uploads/icons/ppzd@2x.png","is_view":"1"},{"name":"特价专区","url":"http://hzc.richwealth.cn/index/goodstap?mchid=2000000021/tap/3","logo":"http://hzcapi.richwealth.cn/uploads/icons/bxzg@2x.png","is_view":"1"},{"name":"福利红包","url":"http://hzc.richwealth.cn/index/grabredenvelope?mchid=2000000021","logo":"http://hzcapi.richwealth.cn/uploads/icons/hongbao@3x.png","is_view":"1"},{"name":"境外消费","url":"http://hzc.richwealth.cn/index/starlist?mchid=2000000021","logo":"http://hzcapi.richwealth.cn/uploads/icons/jwxf@3x.png","is_view":"1"}]
         * member_url :
         * city_owners : http://hzc.richwealth.cn/index/cityownerphone/mchid/0
         * alipay_account :
         * alipay_name :
         * check_day : 7
         * category : [{"cate_id":"1","cate_name":"限时特价"},{"cate_id":"9","cate_name":"店长"},{"cate_id":"3","cate_name":"限时8.8折"},{"cate_id":"4","cate_name":"首付"},{"cate_id":"7","cate_name":"100元充值卡"},{"cate_id":"8","cate_name":"9.9折"}]
         * bank_str :
         * is_Shopowner : 0
         * shopowner_id : 33
         * sales_usable : 0.00
         * is_mobile : 1
         * Superior_moblie :
         */

        private String access_token;
        private ProfileBean profile;
        private GlobalBean global;
        private String mall;
        private String merchant;
        private String about_url;
        private String help_url;
        private String credit_url;
        private String shop_status;
        private String shop_audit_memo;
        private String shop_code;
        private String version;
        private String ttfver;
        private String rookie_url;
        private String strategy_url;
        private AdBean ad;
        private String ttfgs;
        private String ttfbk;
        private String ttfdk;
        private AdImgBean ad_img;
        private String fee_url;
        private String xyk_oem;
        private ObligateBean obligate;
        private String member_url;
        private String city_owners;
        private String alipay_account;
        private String alipay_name;
        private String check_day;
        private String bank_str;
        private String is_Shopowner;
        private String shopowner_id;
        private String sales_usable;
        private String is_mobile;
        private String Superior_moblie;
        private List<String> service_qq;
        private List<String> wallet;
        private List<CollectingBean> collecting;
        private List<MenusBean> menus;
        private List<CategoryBean> category;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public ProfileBean getProfile() {
            return profile;
        }

        public void setProfile(ProfileBean profile) {
            this.profile = profile;
        }

        public GlobalBean getGlobal() {
            return global;
        }

        public void setGlobal(GlobalBean global) {
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

        public String getShop_audit_memo() {
            return shop_audit_memo;
        }

        public void setShop_audit_memo(String shop_audit_memo) {
            this.shop_audit_memo = shop_audit_memo;
        }

        public String getShop_code() {
            return shop_code;
        }

        public void setShop_code(String shop_code) {
            this.shop_code = shop_code;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getTtfver() {
            return ttfver;
        }

        public void setTtfver(String ttfver) {
            this.ttfver = ttfver;
        }

        public String getRookie_url() {
            return rookie_url;
        }

        public void setRookie_url(String rookie_url) {
            this.rookie_url = rookie_url;
        }

        public String getStrategy_url() {
            return strategy_url;
        }

        public void setStrategy_url(String strategy_url) {
            this.strategy_url = strategy_url;
        }

        public AdBean getAd() {
            return ad;
        }

        public void setAd(AdBean ad) {
            this.ad = ad;
        }

        public String getTtfgs() {
            return ttfgs;
        }

        public void setTtfgs(String ttfgs) {
            this.ttfgs = ttfgs;
        }

        public String getTtfbk() {
            return ttfbk;
        }

        public void setTtfbk(String ttfbk) {
            this.ttfbk = ttfbk;
        }

        public String getTtfdk() {
            return ttfdk;
        }

        public void setTtfdk(String ttfdk) {
            this.ttfdk = ttfdk;
        }

        public AdImgBean getAd_img() {
            return ad_img;
        }

        public void setAd_img(AdImgBean ad_img) {
            this.ad_img = ad_img;
        }

        public String getFee_url() {
            return fee_url;
        }

        public void setFee_url(String fee_url) {
            this.fee_url = fee_url;
        }

        public String getXyk_oem() {
            return xyk_oem;
        }

        public void setXyk_oem(String xyk_oem) {
            this.xyk_oem = xyk_oem;
        }

        public ObligateBean getObligate() {
            return obligate;
        }

        public void setObligate(ObligateBean obligate) {
            this.obligate = obligate;
        }

        public String getMember_url() {
            return member_url;
        }

        public void setMember_url(String member_url) {
            this.member_url = member_url;
        }

        public String getCity_owners() {
            return city_owners;
        }

        public void setCity_owners(String city_owners) {
            this.city_owners = city_owners;
        }

        public String getAlipay_account() {
            return alipay_account;
        }

        public void setAlipay_account(String alipay_account) {
            this.alipay_account = alipay_account;
        }

        public String getAlipay_name() {
            return alipay_name;
        }

        public void setAlipay_name(String alipay_name) {
            this.alipay_name = alipay_name;
        }

        public String getCheck_day() {
            return check_day;
        }

        public void setCheck_day(String check_day) {
            this.check_day = check_day;
        }

        public String getBank_str() {
            return bank_str;
        }

        public void setBank_str(String bank_str) {
            this.bank_str = bank_str;
        }

        public String getIs_Shopowner() {
            return is_Shopowner;
        }

        public void setIs_Shopowner(String is_Shopowner) {
            this.is_Shopowner = is_Shopowner;
        }

        public String getShopowner_id() {
            return shopowner_id;
        }

        public void setShopowner_id(String shopowner_id) {
            this.shopowner_id = shopowner_id;
        }

        public String getSales_usable() {
            return sales_usable;
        }

        public void setSales_usable(String sales_usable) {
            this.sales_usable = sales_usable;
        }

        public String getIs_mobile() {
            return is_mobile;
        }

        public void setIs_mobile(String is_mobile) {
            this.is_mobile = is_mobile;
        }

        public String getSuperior_moblie() {
            return Superior_moblie;
        }

        public void setSuperior_moblie(String Superior_moblie) {
            this.Superior_moblie = Superior_moblie;
        }

        public List<String> getService_qq() {
            return service_qq;
        }

        public void setService_qq(List<String> service_qq) {
            this.service_qq = service_qq;
        }

        public List<String> getWallet() {
            return wallet;
        }

        public void setWallet(List<String> wallet) {
            this.wallet = wallet;
        }

        public List<CollectingBean> getCollecting() {
            return collecting;
        }

        public void setCollecting(List<CollectingBean> collecting) {
            this.collecting = collecting;
        }

        public List<MenusBean> getMenus() {
            return menus;
        }

        public void setMenus(List<MenusBean> menus) {
            this.menus = menus;
        }

        public List<CategoryBean> getCategory() {
            return category;
        }

        public void setCategory(List<CategoryBean> category) {
            this.category = category;
        }

        public static class ProfileBean {
            /**
             * mchid : 2000000021
             * mobile : 15060856259
             * name :
             * nick_name : 李亚雄
             * headpic : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLMe6WqLoBqGvwghubMLESMmYxm69HknLfy5VrRjJ3icbrgr4ib0icXz1yhMSvIa8YibHaPrt9ibHFUxWQ/132
             * card_no :
             * bank :
             * bank_city :
             * bank_no :
             * bank_address :
             * status : 1
             * status_shop : 0
             * status_data : {"info":"1","img":"1"}
             * level : {"id":"1","oemid":"90005","lf_id":"1","name":"庄主","level":"1","image_url":"http://hmsapi.zhuoranxiaoming.cn/uploads/level/user_level1@2x.png"}
             * auth_file : {"card_front":"","card_back":"","card_hand":"","bank_img":""}
             * day_max :
             * is_pay : 1
             * verify_ag : 0
             * id : 2000000021
             * card_expiry :
             * bank_logo : http://hmsapi.zhuoranxiaoming.cn
             * audit_memo :
             * trade_pwd_set : 0
             * level_name : {"level_1":"一星庄主","level_2":"二星庄主","level_3":"三星庄主"}
             * invite_name :
             * invite_mobile :
             */

            private String mchid;
            private String mobile;
            private String name;
            private String nick_name;
            private String headpic;
            private String card_no;
            private String bank;
            private String bank_city;
            private String bank_no;
            private String bank_address;
            private String status;
            private String status_shop;
            private StatusDataBean status_data;
            private LevelBean level;
            private AuthFileBean auth_file;
            private String day_max;
            private String is_pay;
            private String verify_ag;
            private String id;
            private String card_expiry;
            private String bank_logo;
            private String audit_memo;
            private String trade_pwd_set;
            private LevelNameBean level_name;
            private String invite_name;
            private String invite_mobile;

            public String getMchid() {
                return mchid;
            }

            public void setMchid(String mchid) {
                this.mchid = mchid;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

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

            public String getCard_no() {
                return card_no;
            }

            public void setCard_no(String card_no) {
                this.card_no = card_no;
            }

            public String getBank() {
                return bank;
            }

            public void setBank(String bank) {
                this.bank = bank;
            }

            public String getBank_city() {
                return bank_city;
            }

            public void setBank_city(String bank_city) {
                this.bank_city = bank_city;
            }

            public String getBank_no() {
                return bank_no;
            }

            public void setBank_no(String bank_no) {
                this.bank_no = bank_no;
            }

            public String getBank_address() {
                return bank_address;
            }

            public void setBank_address(String bank_address) {
                this.bank_address = bank_address;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getStatus_shop() {
                return status_shop;
            }

            public void setStatus_shop(String status_shop) {
                this.status_shop = status_shop;
            }

            public StatusDataBean getStatus_data() {
                return status_data;
            }

            public void setStatus_data(StatusDataBean status_data) {
                this.status_data = status_data;
            }

            public LevelBean getLevel() {
                return level;
            }

            public void setLevel(LevelBean level) {
                this.level = level;
            }

            public AuthFileBean getAuth_file() {
                return auth_file;
            }

            public void setAuth_file(AuthFileBean auth_file) {
                this.auth_file = auth_file;
            }

            public String getDay_max() {
                return day_max;
            }

            public void setDay_max(String day_max) {
                this.day_max = day_max;
            }

            public String getIs_pay() {
                return is_pay;
            }

            public void setIs_pay(String is_pay) {
                this.is_pay = is_pay;
            }

            public String getVerify_ag() {
                return verify_ag;
            }

            public void setVerify_ag(String verify_ag) {
                this.verify_ag = verify_ag;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCard_expiry() {
                return card_expiry;
            }

            public void setCard_expiry(String card_expiry) {
                this.card_expiry = card_expiry;
            }

            public String getBank_logo() {
                return bank_logo;
            }

            public void setBank_logo(String bank_logo) {
                this.bank_logo = bank_logo;
            }

            public String getAudit_memo() {
                return audit_memo;
            }

            public void setAudit_memo(String audit_memo) {
                this.audit_memo = audit_memo;
            }

            public String getTrade_pwd_set() {
                return trade_pwd_set;
            }

            public void setTrade_pwd_set(String trade_pwd_set) {
                this.trade_pwd_set = trade_pwd_set;
            }

            public LevelNameBean getLevel_name() {
                return level_name;
            }

            public void setLevel_name(LevelNameBean level_name) {
                this.level_name = level_name;
            }

            public String getInvite_name() {
                return invite_name;
            }

            public void setInvite_name(String invite_name) {
                this.invite_name = invite_name;
            }

            public String getInvite_mobile() {
                return invite_mobile;
            }

            public void setInvite_mobile(String invite_mobile) {
                this.invite_mobile = invite_mobile;
            }

            public static class StatusDataBean {
                /**
                 * info : 1
                 * img : 1
                 */

                private String info;
                private String img;

                public String getInfo() {
                    return info;
                }

                public void setInfo(String info) {
                    this.info = info;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }
            }

            public static class LevelBean {
                /**
                 * id : 1
                 * oemid : 90005
                 * lf_id : 1
                 * name : 庄主
                 * level : 1
                 * image_url : http://hmsapi.zhuoranxiaoming.cn/uploads/level/user_level1@2x.png
                 */

                private String id;
                private String oemid;
                private String lf_id;
                private String name;
                private String level;
                private String image_url;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getOemid() {
                    return oemid;
                }

                public void setOemid(String oemid) {
                    this.oemid = oemid;
                }

                public String getLf_id() {
                    return lf_id;
                }

                public void setLf_id(String lf_id) {
                    this.lf_id = lf_id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getLevel() {
                    return level;
                }

                public void setLevel(String level) {
                    this.level = level;
                }

                public String getImage_url() {
                    return image_url;
                }

                public void setImage_url(String image_url) {
                    this.image_url = image_url;
                }
            }

            public static class AuthFileBean {
                /**
                 * card_front :
                 * card_back :
                 * card_hand :
                 * bank_img :
                 */

                private String card_front;
                private String card_back;
                private String card_hand;
                private String bank_img;

                public String getCard_front() {
                    return card_front;
                }

                public void setCard_front(String card_front) {
                    this.card_front = card_front;
                }

                public String getCard_back() {
                    return card_back;
                }

                public void setCard_back(String card_back) {
                    this.card_back = card_back;
                }

                public String getCard_hand() {
                    return card_hand;
                }

                public void setCard_hand(String card_hand) {
                    this.card_hand = card_hand;
                }

                public String getBank_img() {
                    return bank_img;
                }

                public void setBank_img(String bank_img) {
                    this.bank_img = bank_img;
                }
            }

            public static class LevelNameBean {
                /**
                 * level_1 : 一星庄主
                 * level_2 : 二星庄主
                 * level_3 : 三星庄主
                 */

                private String level_1;
                private String level_2;
                private String level_3;

                public String getLevel_1() {
                    return level_1;
                }

                public void setLevel_1(String level_1) {
                    this.level_1 = level_1;
                }

                public String getLevel_2() {
                    return level_2;
                }

                public void setLevel_2(String level_2) {
                    this.level_2 = level_2;
                }

                public String getLevel_3() {
                    return level_3;
                }

                public void setLevel_3(String level_3) {
                    this.level_3 = level_3;
                }
            }
        }

        public static class GlobalBean {
            /**
             * share_title : 惠民庄园
             * share_msg : 惠民庄园-刷卡实时到账最低0.28%，分润当天秒结。世界在身边，支付在指尖。
             * share_url : http://m.tugou.ren/reg/index2/invite_mobile/15060856259/oemid/90005
             * op_tel : 400-699-8890
             * op_qq : 33000000
             * op_weixin : j000000
             * hint : 0
             * hint_title : 惠民庄园提示
             * hint_msg : 我要还款--新增完美账单落地商户上线啦！
             最新费率：粉丝0.78%，会员0.68%、店长0.58%，超低费率，自动还款。
             推荐升级返佣:直接60%，间接10%，间接5%
             新增多家银行快速办卡，最高返佣80元。
             * hint_btn : 我知道了
             */

            private String share_title;
            private String share_msg;
            private String share_url;
            private String op_tel;
            private String op_qq;
            private String op_weixin;
            private String hint;
            private String hint_title;
            private String hint_msg;
            private String hint_btn;

            public String getShare_title() {
                return share_title;
            }

            public void setShare_title(String share_title) {
                this.share_title = share_title;
            }

            public String getShare_msg() {
                return share_msg;
            }

            public void setShare_msg(String share_msg) {
                this.share_msg = share_msg;
            }

            public String getShare_url() {
                return share_url;
            }

            public void setShare_url(String share_url) {
                this.share_url = share_url;
            }

            public String getOp_tel() {
                return op_tel;
            }

            public void setOp_tel(String op_tel) {
                this.op_tel = op_tel;
            }

            public String getOp_qq() {
                return op_qq;
            }

            public void setOp_qq(String op_qq) {
                this.op_qq = op_qq;
            }

            public String getOp_weixin() {
                return op_weixin;
            }

            public void setOp_weixin(String op_weixin) {
                this.op_weixin = op_weixin;
            }

            public String getHint() {
                return hint;
            }

            public void setHint(String hint) {
                this.hint = hint;
            }

            public String getHint_title() {
                return hint_title;
            }

            public void setHint_title(String hint_title) {
                this.hint_title = hint_title;
            }

            public String getHint_msg() {
                return hint_msg;
            }

            public void setHint_msg(String hint_msg) {
                this.hint_msg = hint_msg;
            }

            public String getHint_btn() {
                return hint_btn;
            }

            public void setHint_btn(String hint_btn) {
                this.hint_btn = hint_btn;
            }
        }

        public static class AdBean {
            /**
             * is_full : -1
             * url : http://m.tugou.ren/help/add?oemid=80000&mchid=0
             * image : \image\ad\ruleother.png
             * width : 618
             * height : 649
             */

            private String is_full;
            private String url;
            private String image;
            private String width;
            private String height;

            public String getIs_full() {
                return is_full;
            }

            public void setIs_full(String is_full) {
                this.is_full = is_full;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
            }
        }

        public static class AdImgBean {
            /**
             * img : http://hmsapi.zhuoranxiaoming.cn/image/banner/ad20180226.png
             * url : http://hzcapi.richwealth.cn/merchant/judge?mchid=2000000021
             * left : 费率0.5% 立即到帐
             * lev1 : 0.35
             * lev2 : 0.28
             */

            private String img;
            private String url;
            private String left;
            private String lev1;
            private String lev2;

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

            public String getLeft() {
                return left;
            }

            public void setLeft(String left) {
                this.left = left;
            }

            public String getLev1() {
                return lev1;
            }

            public void setLev1(String lev1) {
                this.lev1 = lev1;
            }

            public String getLev2() {
                return lev2;
            }

            public void setLev2(String lev2) {
                this.lev2 = lev2;
            }
        }

        public static class ObligateBean {
            /**
             * logo :
             * name : 会员信息
             * url :
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
        }

        public static class CollectingBean {
            /**
             * img : http://hmsapi.zhuoranxiaoming.cn/image/banner/collecting01.png
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
        }

        public static class MenusBean {
            /**
             * name : 海外直邮
             * url : http://hzc.richwealth.cn/index/goodstap?mchid=2000000021/tap/1
             * logo : http://hzcapi.richwealth.cn/uploads/icons/hwzy@2x.png
             * is_view : 1
             */

            private String name;
            private String url;
            private String logo;
            private String is_view;

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

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getIs_view() {
                return is_view;
            }

            public void setIs_view(String is_view) {
                this.is_view = is_view;
            }
        }

        public static class CategoryBean {
            /**
             * cate_id : 1
             * cate_name : 限时特价
             */

            private String cate_id;
            private String cate_name;

            public String getCate_id() {
                return cate_id;
            }

            public void setCate_id(String cate_id) {
                this.cate_id = cate_id;
            }

            public String getCate_name() {
                return cate_name;
            }

            public void setCate_name(String cate_name) {
                this.cate_name = cate_name;
            }
        }
    }
}