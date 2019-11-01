package com.huiminsheng.app.bean;

import java.util.List;

public class BeanDetails {

    /**
     * result : {"code":"10000","msg":"请求成功"}
     * link :
     * data : {"goods_id":"2","logo":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/89232092bd38b8965e23a14235a2a60c.png","name":"传祺GS42017款 235T 自动两驱精英版 235T 自动两驱精英版","desc":"2017款 政采版","is_special":"0","price":"102879.00","Shopowner_price":"99000.00","prime":"2000.00","images":["http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/8274f98e583b0154c17b9e31ce61dea0.png","http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/95fd6c4360d8e5504aa1d3b5d7976b75.png","http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/4f622adfdfe2a22addff625a0371b8be.jpg"],"ori_price":"144900.00","tag":"首付","details":"<p>传祺GS42017款 235T 自动两驱精英版&nbsp;&nbsp;<\/p><p>2017款 政采版<\/p><p><img src=\"http://hmsadmin.zhuoranxiaoming.cn/ueditor/php/upload/image/20190404/1554343805746444.png\" title=\"1554343805746444.png\" alt=\"传祺 (1).png\"/><img src=\"http://hmsadmin.zhuoranxiaoming.cn/ueditor/php/upload/image/20190404/1554343808968576.png\" title=\"1554343808968576.png\" alt=\"传祺 (2).png\"/><img src=\"http://hmsadmin.zhuoranxiaoming.cn/ueditor/php/upload/image/20190404/1554343812729343.jpg\" title=\"1554343812729343.jpg\" alt=\"传祺(3).jpg\"/><\/p>","weight":"LK100","is_post":"0","volume":"100","trade_type":"1","shop_id":"1000","thumb":"http://hmsadmin.zhuoranxiaoming.cn/uploads/thumb/1554343815thumb.png","limit":"1","inventory":"100","colors":["黑色"],"is_overseas":"1","isUp":"0","class_id":"1","automatic":"0","tag_name":"限时特价","Collection":"0","Shopowner_ary":{"Shopowner_price":"99000.00","Shopowner_url":"http://hmsadmin.zhuoranxiaoming.cn/uploads/dianzhang/diangzhang.png","Shopowner_desc":"全国可做0首付申请只需身份证不看流水上个人户","goods_id":"33"},"earn":"14490.00","is_shop_crad":"0","isVip":"0","weights":["LK100"],"shop_name":"正定享个车4s店","is_Shopowner":"0","titles":["规格","颜色"],"isNeedPic":"1","ensure_info":"48小时内发货·交易保障","earn_desc":"http://hzc.richwealth.cn/index/bonusnote","share_url":"http://hms.zhuoranxiaoming.cn/index/gooddata.html?goods_id=2&mch_id=2000000021"}
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
         * goods_id : 2
         * logo : http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/89232092bd38b8965e23a14235a2a60c.png
         * name : 传祺GS42017款 235T 自动两驱精英版 235T 自动两驱精英版
         * desc : 2017款 政采版
         * is_special : 0
         * price : 102879.00
         * Shopowner_price : 99000.00
         * prime : 2000.00
         * images : ["http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/8274f98e583b0154c17b9e31ce61dea0.png","http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/95fd6c4360d8e5504aa1d3b5d7976b75.png","http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/4f622adfdfe2a22addff625a0371b8be.jpg"]
         * ori_price : 144900.00
         * tag : 首付
         * details : <p>传祺GS42017款 235T 自动两驱精英版&nbsp;&nbsp;</p><p>2017款 政采版</p><p><img src="http://hmsadmin.zhuoranxiaoming.cn/ueditor/php/upload/image/20190404/1554343805746444.png" title="1554343805746444.png" alt="传祺 (1).png"/><img src="http://hmsadmin.zhuoranxiaoming.cn/ueditor/php/upload/image/20190404/1554343808968576.png" title="1554343808968576.png" alt="传祺 (2).png"/><img src="http://hmsadmin.zhuoranxiaoming.cn/ueditor/php/upload/image/20190404/1554343812729343.jpg" title="1554343812729343.jpg" alt="传祺(3).jpg"/></p>
         * weight : LK100
         * is_post : 0
         * volume : 100
         * trade_type : 1
         * shop_id : 1000
         * thumb : http://hmsadmin.zhuoranxiaoming.cn/uploads/thumb/1554343815thumb.png
         * limit : 1
         * inventory : 100
         * colors : ["黑色"]
         * is_overseas : 1
         * isUp : 0
         * class_id : 1
         * automatic : 0
         * tag_name : 限时特价
         * Collection : 0
         * Shopowner_ary : {"Shopowner_price":"99000.00","Shopowner_url":"http://hmsadmin.zhuoranxiaoming.cn/uploads/dianzhang/diangzhang.png","Shopowner_desc":"全国可做0首付申请只需身份证不看流水上个人户","goods_id":"33"}
         * earn : 14490.00
         * is_shop_crad : 0
         * isVip : 0
         * weights : ["LK100"]
         * shop_name : 正定享个车4s店
         * is_Shopowner : 0
         * titles : ["规格","颜色"]
         * isNeedPic : 1
         * ensure_info : 48小时内发货·交易保障
         * earn_desc : http://hzc.richwealth.cn/index/bonusnote
         * share_url : http://hms.zhuoranxiaoming.cn/index/gooddata.html?goods_id=2&mch_id=2000000021
         */

        private String goods_id;
        private String logo;
        private String name;
        private String desc;
        private String is_special;
        private String price;
        private String Shopowner_price;
        private String prime;
        private String ori_price;
        private String tag;
        private String details;
        private String weight;
        private String is_post;
        private String volume;
        private String trade_type;
        private String shop_id;
        private String thumb;
        private String limit;
        private String inventory;
        private String is_overseas;
        private String isUp;
        private String class_id;
        private String automatic;
        private String tag_name;
        private String Collection;
        private ShopownerAryBean Shopowner_ary;
        private String earn;
        private String is_shop_crad;
        private String isVip;
        private String shop_name;
        private String is_Shopowner; //0不是店长 1是
        private String isNeedPic;
        private String ensure_info;
        private String earn_desc;
        private String share_url;
        private List<String> images;
        private List<String> colors;
        private List<String> weights;
        private List<String> titles;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

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

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getIs_special() {
            return is_special;
        }

        public void setIs_special(String is_special) {
            this.is_special = is_special;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getShopowner_price() {
            return Shopowner_price;
        }

        public void setShopowner_price(String Shopowner_price) {
            this.Shopowner_price = Shopowner_price;
        }

        public String getPrime() {
            return prime;
        }

        public void setPrime(String prime) {
            this.prime = prime;
        }

        public String getOri_price() {
            return ori_price;
        }

        public void setOri_price(String ori_price) {
            this.ori_price = ori_price;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getIs_post() {
            return is_post;
        }

        public void setIs_post(String is_post) {
            this.is_post = is_post;
        }

        public String getVolume() {
            return volume;
        }

        public void setVolume(String volume) {
            this.volume = volume;
        }

        public String getTrade_type() {
            return trade_type;
        }

        public void setTrade_type(String trade_type) {
            this.trade_type = trade_type;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getLimit() {
            return limit;
        }

        public void setLimit(String limit) {
            this.limit = limit;
        }

        public String getInventory() {
            return inventory;
        }

        public void setInventory(String inventory) {
            this.inventory = inventory;
        }

        public String getIs_overseas() {
            return is_overseas;
        }

        public void setIs_overseas(String is_overseas) {
            this.is_overseas = is_overseas;
        }

        public String getIsUp() {
            return isUp;
        }

        public void setIsUp(String isUp) {
            this.isUp = isUp;
        }

        public String getClass_id() {
            return class_id;
        }

        public void setClass_id(String class_id) {
            this.class_id = class_id;
        }

        public String getAutomatic() {
            return automatic;
        }

        public void setAutomatic(String automatic) {
            this.automatic = automatic;
        }

        public String getTag_name() {
            return tag_name;
        }

        public void setTag_name(String tag_name) {
            this.tag_name = tag_name;
        }

        public String getCollection() {
            return Collection;
        }

        public void setCollection(String Collection) {
            this.Collection = Collection;
        }

        public ShopownerAryBean getShopowner_ary() {
            return Shopowner_ary;
        }

        public void setShopowner_ary(ShopownerAryBean Shopowner_ary) {
            this.Shopowner_ary = Shopowner_ary;
        }

        public String getEarn() {
            return earn;
        }

        public void setEarn(String earn) {
            this.earn = earn;
        }

        public String getIs_shop_crad() {
            return is_shop_crad;
        }

        public void setIs_shop_crad(String is_shop_crad) {
            this.is_shop_crad = is_shop_crad;
        }

        public String getIsVip() {
            return isVip;
        }

        public void setIsVip(String isVip) {
            this.isVip = isVip;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getIs_Shopowner() {
            return is_Shopowner;
        }

        public void setIs_Shopowner(String is_Shopowner) {
            this.is_Shopowner = is_Shopowner;
        }

        public String getIsNeedPic() {
            return isNeedPic;
        }

        public void setIsNeedPic(String isNeedPic) {
            this.isNeedPic = isNeedPic;
        }

        public String getEnsure_info() {
            return ensure_info;
        }

        public void setEnsure_info(String ensure_info) {
            this.ensure_info = ensure_info;
        }

        public String getEarn_desc() {
            return earn_desc;
        }

        public void setEarn_desc(String earn_desc) {
            this.earn_desc = earn_desc;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public List<String> getColors() {
            return colors;
        }

        public void setColors(List<String> colors) {
            this.colors = colors;
        }

        public List<String> getWeights() {
            return weights;
        }

        public void setWeights(List<String> weights) {
            this.weights = weights;
        }

        public List<String> getTitles() {
            return titles;
        }

        public void setTitles(List<String> titles) {
            this.titles = titles;
        }

        public static class ShopownerAryBean {
            /**
             * Shopowner_price : 99000.00
             * Shopowner_url : http://hmsadmin.zhuoranxiaoming.cn/uploads/dianzhang/diangzhang.png
             * Shopowner_desc : 全国可做0首付申请只需身份证不看流水上个人户
             * goods_id : 33
             */

            private String Shopowner_price;
            private String Shopowner_url;
            private String Shopowner_desc;
            private String goods_id;

            public String getShopowner_price() {
                return Shopowner_price;
            }

            public void setShopowner_price(String Shopowner_price) {
                this.Shopowner_price = Shopowner_price;
            }

            public String getShopowner_url() {
                return Shopowner_url;
            }

            public void setShopowner_url(String Shopowner_url) {
                this.Shopowner_url = Shopowner_url;
            }

            public String getShopowner_desc() {
                return Shopowner_desc;
            }

            public void setShopowner_desc(String Shopowner_desc) {
                this.Shopowner_desc = Shopowner_desc;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }
        }
    }
}
