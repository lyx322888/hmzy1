package com.huiminsheng.app.bean;

import java.util.List;

public class BeanProductList {

    /**
     * result : {"code":"10000","msg":"请求成功"}
     * link :
     * data : {"list":[{"goods_id":"31","name":"奥迪","desc":"这是一张图片","tag":"首付","class_id":"4","is_special":"0","price":"80000.00","Shopowner_price":"77000.00","ori_price":"110000.00","prime":"2000.00","volume":"100","logo":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190401/09af20be880f067f0726de18695987c6.png","images":["http://hmsadmin.zhuoranxiaoming.cn/uploads/20190401/4523d32c45f850d2a57bc7960fc0becd.png"],"rest":"2","isUp":"0","tag_name":"首付","earn":"11000.00","isVip":"0","share_img":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190401/4523d32c45f850d2a57bc7960fc0becd.png","share_url":"http://hmsapi.zhuoranxiaoming.cn/index/commoditydetails.html?goods_id=31&mch_id=2000000001"}],"count":"1","psize":"10"}
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
         * list : [{"goods_id":"31","name":"奥迪","desc":"这是一张图片","tag":"首付","class_id":"4","is_special":"0","price":"80000.00","Shopowner_price":"77000.00","ori_price":"110000.00","prime":"2000.00","volume":"100","logo":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190401/09af20be880f067f0726de18695987c6.png","images":["http://hmsadmin.zhuoranxiaoming.cn/uploads/20190401/4523d32c45f850d2a57bc7960fc0becd.png"],"rest":"2","isUp":"0","tag_name":"首付","earn":"11000.00","isVip":"0","share_img":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190401/4523d32c45f850d2a57bc7960fc0becd.png","share_url":"http://hmsapi.zhuoranxiaoming.cn/index/commoditydetails.html?goods_id=31&mch_id=2000000001"}]
         * count : 1
         * psize : 10
         */

        private String count;
        private String psize;
        private List<ListBean> list;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getPsize() {
            return psize;
        }

        public void setPsize(String psize) {
            this.psize = psize;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * goods_id : 31
             * name : 奥迪
             * desc : 这是一张图片
             * tag : 首付
             * class_id : 4
             * is_special : 0
             * price : 80000.00
             * Shopowner_price : 77000.00
             * ori_price : 110000.00
             * prime : 2000.00
             * volume : 100
             * logo : http://hmsadmin.zhuoranxiaoming.cn/uploads/20190401/09af20be880f067f0726de18695987c6.png
             * images : ["http://hmsadmin.zhuoranxiaoming.cn/uploads/20190401/4523d32c45f850d2a57bc7960fc0becd.png"]
             * rest : 2
             * isUp : 0
             * tag_name : 首付
             * earn : 11000.00
             * isVip : 0
             * share_img : http://hmsadmin.zhuoranxiaoming.cn/uploads/20190401/4523d32c45f850d2a57bc7960fc0becd.png
             * share_url : http://hmsapi.zhuoranxiaoming.cn/index/commoditydetails.html?goods_id=31&mch_id=2000000001
             */

            private String goods_id;
            private String name;
            private String desc;
            private String tag;
            private String class_id;
            private String is_special;
            private String price;
            private String Shopowner_price;
            private String ori_price;
            private String prime;
            private String volume;
            private String logo;
            private String rest;
            private String isUp;
            private String tag_name;
            private String earn;
            private String isVip;
            private String share_img;
            private String share_url;
            private List<String> images;

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
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

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getClass_id() {
                return class_id;
            }

            public void setClass_id(String class_id) {
                this.class_id = class_id;
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

            public String getOri_price() {
                return ori_price;
            }

            public void setOri_price(String ori_price) {
                this.ori_price = ori_price;
            }

            public String getPrime() {
                return prime;
            }

            public void setPrime(String prime) {
                this.prime = prime;
            }

            public String getVolume() {
                return volume;
            }

            public void setVolume(String volume) {
                this.volume = volume;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getRest() {
                return rest;
            }

            public void setRest(String rest) {
                this.rest = rest;
            }

            public String getIsUp() {
                return isUp;
            }

            public void setIsUp(String isUp) {
                this.isUp = isUp;
            }

            public String getTag_name() {
                return tag_name;
            }

            public void setTag_name(String tag_name) {
                this.tag_name = tag_name;
            }

            public String getEarn() {
                return earn;
            }

            public void setEarn(String earn) {
                this.earn = earn;
            }

            public String getIsVip() {
                return isVip;
            }

            public void setIsVip(String isVip) {
                this.isVip = isVip;
            }

            public String getShare_img() {
                return share_img;
            }

            public void setShare_img(String share_img) {
                this.share_img = share_img;
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
        }
    }
}
