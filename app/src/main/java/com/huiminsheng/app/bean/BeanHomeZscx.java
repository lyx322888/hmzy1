package com.huiminsheng.app.bean;

import java.util.List;
/*
* 在售车型*/
public class BeanHomeZscx {

    /**
     * result : {"code":"10000","msg":"请求成功"}
     * link :
     * data : {"list":[{"goods_id":"73","name":"天籁 2.0L XL 舒适版","desc":"2019款 【行驶里程】0万公里 /【首次上牌】2019-04 /【挡位／排量】自动／2L /【所在地】厦门","is_special":"0","tag":"","class_id":"8","price":"196800.00","Shopowner_price":"192000.00","ori_price":"202800.00","prime":"2000.00","volume":"100","trade_type":"3","logo":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/061cfeab4d736d92f6a7f39cea1930fc.jpg","rest":"2","isUp":"0","tag_name":"9.9折","earn":"20280.00","isVip":"0","share_img":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/a2ee2e1d83a26456bbe8d72759ca19b0.jpg","share_url":"http://hms.zhuoranxiaoming.cn/index/gooddata.html?goods_id=73&mch_id=2000000021"},{"goods_id":"72","name":"阳光 1.5XE CVT舒适版","desc":"2015款 【行驶里程】0万公里 /【首次上牌】2019-04 /【挡位／排量】自动／1.5L /【所在地】厦门","is_special":"0","tag":"","class_id":"8","price":"75800.00","Shopowner_price":"71200.00","ori_price":"97500.00","prime":"2000.00","volume":"100","trade_type":"3","logo":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/caa27974cd3243e51e75040c1ecc76b3.jpg","rest":"4","isUp":"0","tag_name":"9.9折","earn":"9750.00","isVip":"0","share_img":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/f1bf76907bf0271139396344350dce71.jpg","share_url":"http://hms.zhuoranxiaoming.cn/index/gooddata.html?goods_id=72&mch_id=2000000021"},{"goods_id":"71","name":"YARiS L 致享 1.5E CVT魅动版","desc":"2017款 【行驶里程】0万公里 /【首次上牌】2019-03 /【挡位／排量】自动／1.5L /【所在地】厦门","is_special":"0","tag":"","class_id":"3","price":"85800.00","Shopowner_price":"81000.00","ori_price":"95300.00","prime":"2000.00","volume":"100","trade_type":"3","logo":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/54647a8c5bacdab978d2f47d5fc97d6a.jpg","rest":"2","isUp":"0","tag_name":"限时8.8折","earn":"9530.00","isVip":"0","share_img":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/b87f5d5ea3b3a86befd39d63a3e08eae.jpg","share_url":"http://hms.zhuoranxiaoming.cn/index/gooddata.html?goods_id=71&mch_id=2000000021"},{"goods_id":"70","name":"凯美瑞 2.0E 领先版 国V","desc":"2019款 【行驶里程】0万公里 /【首次上牌】2019-04 /【挡位／排量】自动／2L /【所在地】厦门","is_special":"0","tag":"","class_id":"1","price":"199800.00","Shopowner_price":"195600.00","ori_price":"206000.00","prime":"2000.00","volume":"100","trade_type":"3","logo":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/dfc1c369785d2cb472fc9da73a1055fd.jpg","rest":"3","isUp":"0","tag_name":"限时特价","earn":"20600.00","isVip":"0","share_img":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/ce764f5849915c565ac79716c3f5e51b.jpg","share_url":"http://hms.zhuoranxiaoming.cn/index/gooddata.html?goods_id=70&mch_id=2000000021"},{"goods_id":"69","name":"奥迪Q2L 35TFSI 上市专享版","desc":"2018款 【行驶里程】0万公里 /【首次上牌】2019-04 /【挡位／排量】自动／1.4L /【所在地】厦门","is_special":"0","tag":"","class_id":"3","price":"197600.00","Shopowner_price":"192000.00","ori_price":"237600.00","prime":"2000.00","volume":"100","trade_type":"3","logo":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/aeec7a71820b9245b697cfcb605bea5e.jpg","rest":"3","isUp":"0","tag_name":"限时8.8折","earn":"23760.00","isVip":"0","share_img":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/0ee72e0bd29396245b265c70e644854a.jpg","share_url":"http://hms.zhuoranxiaoming.cn/index/gooddata.html?goods_id=69&mch_id=2000000021"},{"goods_id":"68","name":"途达 2.5L 自动两驱豪华版","desc":"2018款 【行驶里程】0万公里 /【首次上牌】2019-04 /【挡位／排量】自动／2.5L /【所在地】厦门","is_special":"0","tag":"","class_id":"8","price":"205800.00","Shopowner_price":"201000.00","ori_price":"216900.00","prime":"2000.00","volume":"100","trade_type":"3","logo":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/ac88ef5fec8661fe44c8da4b5261d8a5.jpg","rest":"2","isUp":"0","tag_name":"9.9折","earn":"21690.00","isVip":"0","share_img":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/4c0a8b1ceb03465950aa613a0a0e918b.jpg","share_url":"http://hms.zhuoranxiaoming.cn/index/gooddata.html?goods_id=68&mch_id=2000000021"},{"goods_id":"67","name":"奥迪A6L新能源 40 e-tron","desc":"2018款 【行驶里程】0万公里 /【首次上牌】2019-01 /【挡位／排量】自动／2L /【所在地】厦门","is_special":"0","tag":"","class_id":"8","price":"395800.00","Shopowner_price":"390000.00","ori_price":"486700.00","prime":"2000.00","volume":"100","trade_type":"3","logo":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/77e3afadae2c3541213312f97d2f06be.jpg","rest":"2","isUp":"0","tag_name":"9.9折","earn":"48670.00","isVip":"0","share_img":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/2380063996a6ea9ea079ca8101ba7d8d.jpg","share_url":"http://hms.zhuoranxiaoming.cn/index/gooddata.html?goods_id=67&mch_id=2000000021"},{"goods_id":"66","name":"启辰T90 2.0L CVT风尚版","desc":"2017款 【行驶里程】0万公里 /【首次上牌】2019-03 /【挡位／排量】自动／2L /【所在地】厦门","is_special":"0","tag":"","class_id":"1","price":"113800.00","Shopowner_price":"109000.00","ori_price":"138700.00","prime":"2000.00","volume":"100","trade_type":"3","logo":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/5c0fc0cd0bd8d39d682b72ad0853c8e9.jpg","rest":"2","isUp":"0","tag_name":"限时特价","earn":"13870.00","isVip":"0","share_img":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/0b5b11ce97d976d850d681f58659baad.jpg","share_url":"http://hms.zhuoranxiaoming.cn/index/gooddata.html?goods_id=66&mch_id=2000000021"},{"goods_id":"65","name":"奥迪A3 Limousine 35 TFSI 时尚型 国VI","desc":"2019款 【行驶里程】0万公里 /【首次上牌】2019-03 /【挡位／排量】自动／1.4L /【所在地】厦门","is_special":"0","tag":"","class_id":"8","price":"183900.00","Shopowner_price":"180000.00","ori_price":"237500.00","prime":"2000.00","volume":"100","trade_type":"3","logo":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/b5f95cbfae2f8f423367570158c79d5c.jpg","rest":"6","isUp":"0","tag_name":"9.9折","earn":"23750.00","isVip":"0","share_img":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/d32caf936f5118b488ec7e1c5732638c.jpg","share_url":"http://hms.zhuoranxiaoming.cn/index/gooddata.html?goods_id=65&mch_id=2000000021"},{"goods_id":"64","name":"雪铁龙C3-XR 1.6L 自动时尚型","desc":"2018款 【行驶里程】0万公里 /【首次上牌】2019-04 /【挡位／排量】自动／1.6L /【所在地】厦门","is_special":"0","tag":"","class_id":"8","price":"95800.00","Shopowner_price":"91000.00","ori_price":"139000.00","prime":"2000.00","volume":"100","trade_type":"3","logo":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/ad47e443b35aa29881f18d96f0737c07.jpg","rest":"3","isUp":"0","tag_name":"9.9折","earn":"13900.00","isVip":"0","share_img":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/4a58eec625e12e35e3642a2e325fe416.jpg","share_url":"http://hms.zhuoranxiaoming.cn/index/gooddata.html?goods_id=64&mch_id=2000000021"}],"count":"43","psize":"10","index_goodslist":{"name_top":"在售车型","name_left":"查看更多","link_url":""}}
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
         * list : [{"goods_id":"73","name":"天籁 2.0L XL 舒适版","desc":"2019款 【行驶里程】0万公里 /【首次上牌】2019-04 /【挡位／排量】自动／2L /【所在地】厦门","is_special":"0","tag":"","class_id":"8","price":"196800.00","Shopowner_price":"192000.00","ori_price":"202800.00","prime":"2000.00","volume":"100","trade_type":"3","logo":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/061cfeab4d736d92f6a7f39cea1930fc.jpg","rest":"2","isUp":"0","tag_name":"9.9折","earn":"20280.00","isVip":"0","share_img":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/a2ee2e1d83a26456bbe8d72759ca19b0.jpg","share_url":"http://hms.zhuoranxiaoming.cn/index/gooddata.html?goods_id=73&mch_id=2000000021"},{"goods_id":"72","name":"阳光 1.5XE CVT舒适版","desc":"2015款 【行驶里程】0万公里 /【首次上牌】2019-04 /【挡位／排量】自动／1.5L /【所在地】厦门","is_special":"0","tag":"","class_id":"8","price":"75800.00","Shopowner_price":"71200.00","ori_price":"97500.00","prime":"2000.00","volume":"100","trade_type":"3","logo":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/caa27974cd3243e51e75040c1ecc76b3.jpg","rest":"4","isUp":"0","tag_name":"9.9折","earn":"9750.00","isVip":"0","share_img":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/f1bf76907bf0271139396344350dce71.jpg","share_url":"http://hms.zhuoranxiaoming.cn/index/gooddata.html?goods_id=72&mch_id=2000000021"},{"goods_id":"71","name":"YARiS L 致享 1.5E CVT魅动版","desc":"2017款 【行驶里程】0万公里 /【首次上牌】2019-03 /【挡位／排量】自动／1.5L /【所在地】厦门","is_special":"0","tag":"","class_id":"3","price":"85800.00","Shopowner_price":"81000.00","ori_price":"95300.00","prime":"2000.00","volume":"100","trade_type":"3","logo":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/54647a8c5bacdab978d2f47d5fc97d6a.jpg","rest":"2","isUp":"0","tag_name":"限时8.8折","earn":"9530.00","isVip":"0","share_img":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/b87f5d5ea3b3a86befd39d63a3e08eae.jpg","share_url":"http://hms.zhuoranxiaoming.cn/index/gooddata.html?goods_id=71&mch_id=2000000021"},{"goods_id":"70","name":"凯美瑞 2.0E 领先版 国V","desc":"2019款 【行驶里程】0万公里 /【首次上牌】2019-04 /【挡位／排量】自动／2L /【所在地】厦门","is_special":"0","tag":"","class_id":"1","price":"199800.00","Shopowner_price":"195600.00","ori_price":"206000.00","prime":"2000.00","volume":"100","trade_type":"3","logo":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/dfc1c369785d2cb472fc9da73a1055fd.jpg","rest":"3","isUp":"0","tag_name":"限时特价","earn":"20600.00","isVip":"0","share_img":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/ce764f5849915c565ac79716c3f5e51b.jpg","share_url":"http://hms.zhuoranxiaoming.cn/index/gooddata.html?goods_id=70&mch_id=2000000021"},{"goods_id":"69","name":"奥迪Q2L 35TFSI 上市专享版","desc":"2018款 【行驶里程】0万公里 /【首次上牌】2019-04 /【挡位／排量】自动／1.4L /【所在地】厦门","is_special":"0","tag":"","class_id":"3","price":"197600.00","Shopowner_price":"192000.00","ori_price":"237600.00","prime":"2000.00","volume":"100","trade_type":"3","logo":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/aeec7a71820b9245b697cfcb605bea5e.jpg","rest":"3","isUp":"0","tag_name":"限时8.8折","earn":"23760.00","isVip":"0","share_img":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/0ee72e0bd29396245b265c70e644854a.jpg","share_url":"http://hms.zhuoranxiaoming.cn/index/gooddata.html?goods_id=69&mch_id=2000000021"},{"goods_id":"68","name":"途达 2.5L 自动两驱豪华版","desc":"2018款 【行驶里程】0万公里 /【首次上牌】2019-04 /【挡位／排量】自动／2.5L /【所在地】厦门","is_special":"0","tag":"","class_id":"8","price":"205800.00","Shopowner_price":"201000.00","ori_price":"216900.00","prime":"2000.00","volume":"100","trade_type":"3","logo":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/ac88ef5fec8661fe44c8da4b5261d8a5.jpg","rest":"2","isUp":"0","tag_name":"9.9折","earn":"21690.00","isVip":"0","share_img":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/4c0a8b1ceb03465950aa613a0a0e918b.jpg","share_url":"http://hms.zhuoranxiaoming.cn/index/gooddata.html?goods_id=68&mch_id=2000000021"},{"goods_id":"67","name":"奥迪A6L新能源 40 e-tron","desc":"2018款 【行驶里程】0万公里 /【首次上牌】2019-01 /【挡位／排量】自动／2L /【所在地】厦门","is_special":"0","tag":"","class_id":"8","price":"395800.00","Shopowner_price":"390000.00","ori_price":"486700.00","prime":"2000.00","volume":"100","trade_type":"3","logo":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/77e3afadae2c3541213312f97d2f06be.jpg","rest":"2","isUp":"0","tag_name":"9.9折","earn":"48670.00","isVip":"0","share_img":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/2380063996a6ea9ea079ca8101ba7d8d.jpg","share_url":"http://hms.zhuoranxiaoming.cn/index/gooddata.html?goods_id=67&mch_id=2000000021"},{"goods_id":"66","name":"启辰T90 2.0L CVT风尚版","desc":"2017款 【行驶里程】0万公里 /【首次上牌】2019-03 /【挡位／排量】自动／2L /【所在地】厦门","is_special":"0","tag":"","class_id":"1","price":"113800.00","Shopowner_price":"109000.00","ori_price":"138700.00","prime":"2000.00","volume":"100","trade_type":"3","logo":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/5c0fc0cd0bd8d39d682b72ad0853c8e9.jpg","rest":"2","isUp":"0","tag_name":"限时特价","earn":"13870.00","isVip":"0","share_img":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/0b5b11ce97d976d850d681f58659baad.jpg","share_url":"http://hms.zhuoranxiaoming.cn/index/gooddata.html?goods_id=66&mch_id=2000000021"},{"goods_id":"65","name":"奥迪A3 Limousine 35 TFSI 时尚型 国VI","desc":"2019款 【行驶里程】0万公里 /【首次上牌】2019-03 /【挡位／排量】自动／1.4L /【所在地】厦门","is_special":"0","tag":"","class_id":"8","price":"183900.00","Shopowner_price":"180000.00","ori_price":"237500.00","prime":"2000.00","volume":"100","trade_type":"3","logo":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/b5f95cbfae2f8f423367570158c79d5c.jpg","rest":"6","isUp":"0","tag_name":"9.9折","earn":"23750.00","isVip":"0","share_img":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/d32caf936f5118b488ec7e1c5732638c.jpg","share_url":"http://hms.zhuoranxiaoming.cn/index/gooddata.html?goods_id=65&mch_id=2000000021"},{"goods_id":"64","name":"雪铁龙C3-XR 1.6L 自动时尚型","desc":"2018款 【行驶里程】0万公里 /【首次上牌】2019-04 /【挡位／排量】自动／1.6L /【所在地】厦门","is_special":"0","tag":"","class_id":"8","price":"95800.00","Shopowner_price":"91000.00","ori_price":"139000.00","prime":"2000.00","volume":"100","trade_type":"3","logo":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/ad47e443b35aa29881f18d96f0737c07.jpg","rest":"3","isUp":"0","tag_name":"9.9折","earn":"13900.00","isVip":"0","share_img":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/4a58eec625e12e35e3642a2e325fe416.jpg","share_url":"http://hms.zhuoranxiaoming.cn/index/gooddata.html?goods_id=64&mch_id=2000000021"}]
         * count : 43
         * psize : 10
         * index_goodslist : {"name_top":"在售车型","name_left":"查看更多","link_url":""}
         */

        private String count;
        private String psize;
        private IndexGoodslistBean index_goodslist;
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

        public IndexGoodslistBean getIndex_goodslist() {
            return index_goodslist;
        }

        public void setIndex_goodslist(IndexGoodslistBean index_goodslist) {
            this.index_goodslist = index_goodslist;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class IndexGoodslistBean {
            /**
             * name_top : 在售车型
             * name_left : 查看更多
             * link_url :
             */

            private String name_top;
            private String name_left;
            private String link_url;

            public String getName_top() {
                return name_top;
            }

            public void setName_top(String name_top) {
                this.name_top = name_top;
            }

            public String getName_left() {
                return name_left;
            }

            public void setName_left(String name_left) {
                this.name_left = name_left;
            }

            public String getLink_url() {
                return link_url;
            }

            public void setLink_url(String link_url) {
                this.link_url = link_url;
            }
        }

        public static class ListBean {
            /**
             * goods_id : 73
             * name : 天籁 2.0L XL 舒适版
             * desc : 2019款 【行驶里程】0万公里 /【首次上牌】2019-04 /【挡位／排量】自动／2L /【所在地】厦门
             * is_special : 0
             * tag :
             * class_id : 8
             * price : 196800.00
             * Shopowner_price : 192000.00
             * ori_price : 202800.00
             * prime : 2000.00
             * volume : 100
             * trade_type : 3
             * logo : http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/061cfeab4d736d92f6a7f39cea1930fc.jpg
             * rest : 2
             * isUp : 0
             * tag_name : 9.9折
             * earn : 20280.00
             * isVip : 0
             * share_img : http://hmsadmin.zhuoranxiaoming.cn/uploads/20190510/a2ee2e1d83a26456bbe8d72759ca19b0.jpg
             * share_url : http://hms.zhuoranxiaoming.cn/index/gooddata.html?goods_id=73&mch_id=2000000021
             */

            private String goods_id;
            private String name;
            private String desc;
            private String is_special;
            private String tag;
            private String class_id;
            private String price;
            private String Shopowner_price;
            private String ori_price;
            private String prime;
            private String volume;
            private String trade_type;
            private String logo;
            private String rest;
            private String isUp;
            private String tag_name;
            private String earn;
            private String isVip;
            private String share_img;
            private String share_url;

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

            public String getIs_special() {
                return is_special;
            }

            public void setIs_special(String is_special) {
                this.is_special = is_special;
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

            public String getTrade_type() {
                return trade_type;
            }

            public void setTrade_type(String trade_type) {
                this.trade_type = trade_type;
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
        }
    }
}
