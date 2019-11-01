package com.huiminsheng.app.bean;

import java.util.List;

public class BeanOrderDetails {
    /**
     * result : {"code":"10000","msg":"请求成功"}
     * link :
     * data : {"province":"","city":"","zone":"","address":"","pay_type":"1","shop_id":"1017","order_sn":"hms200000002120190513171756","addtime":"1557739076","s_time":"","e_time":"","recv_name":"了","recv_mobile":"85588","status":"3","all_price":"0.01","pay_time":"1557739081","cardAccount":"","cardPassword":"","hb_money":"0.00","postFee":"0.00","idCard":"","shop_name":"享个车正定店","shop_address":"正定县燕赵北大街与育才路交汇处50米","appraise_tag":"0","goods_count":"1","list":[{"appraise":"","order_id":"1210","goods_id":"1","count":"1","price":"0.01","name":"东风风度MX5","trade_type":"1","is_overseas":"1","desc":"2016款 1.4T 手动豪华版","thumb":"http://hmsadmin.zhuoranxiaoming.cn/uploads/thumb/1554343549thumb.png","beizhu":"","weight":"","colors":"","appraise_tag":"0","is_shop_crad":"0"}],"beizhu":""}
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
         * province :
         * city :
         * zone :
         * address :
         * pay_type : 1
         * shop_id : 1017
         * order_sn : hms200000002120190513171756
         * addtime : 1557739076
         * s_time :
         * e_time :
         * recv_name : 了
         * recv_mobile : 85588
         * status : 3
         * all_price : 0.01
         * pay_time : 1557739081
         * cardAccount :
         * cardPassword :
         * hb_money : 0.00
         * postFee : 0.00
         * idCard :
         * shop_name : 享个车正定店
         * shop_address : 正定县燕赵北大街与育才路交汇处50米
         * appraise_tag : 0
         * goods_count : 1
         * list : [{"appraise":"","order_id":"1210","goods_id":"1","count":"1","price":"0.01","name":"东风风度MX5","trade_type":"1","is_overseas":"1","desc":"2016款 1.4T 手动豪华版","thumb":"http://hmsadmin.zhuoranxiaoming.cn/uploads/thumb/1554343549thumb.png","beizhu":"","weight":"","colors":"","appraise_tag":"0","is_shop_crad":"0"}]
         * beizhu :
         */

        private String province;
        private String city;
        private String zone;
        private String address;
        private String pay_type;
        private String shop_id;
        private String order_sn;
        private String addtime;
        private String s_time;
        private String e_time;
        private String recv_name;
        private String recv_mobile;
        private String status;
        private String all_price;
        private String pay_time;
        private String cardAccount;
        private String cardPassword;
        private String hb_money;
        private String postFee;
        private String idCard;
        private String shop_name;
        private String shop_address;
        private String appraise_tag;
        private String goods_count;
        private String beizhu;
        private List<ListBean> list;

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getZone() {
            return zone;
        }

        public void setZone(String zone) {
            this.zone = zone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getS_time() {
            return s_time;
        }

        public void setS_time(String s_time) {
            this.s_time = s_time;
        }

        public String getE_time() {
            return e_time;
        }

        public void setE_time(String e_time) {
            this.e_time = e_time;
        }

        public String getRecv_name() {
            return recv_name;
        }

        public void setRecv_name(String recv_name) {
            this.recv_name = recv_name;
        }

        public String getRecv_mobile() {
            return recv_mobile;
        }

        public void setRecv_mobile(String recv_mobile) {
            this.recv_mobile = recv_mobile;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAll_price() {
            return all_price;
        }

        public void setAll_price(String all_price) {
            this.all_price = all_price;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public String getCardAccount() {
            return cardAccount;
        }

        public void setCardAccount(String cardAccount) {
            this.cardAccount = cardAccount;
        }

        public String getCardPassword() {
            return cardPassword;
        }

        public void setCardPassword(String cardPassword) {
            this.cardPassword = cardPassword;
        }

        public String getHb_money() {
            return hb_money;
        }

        public void setHb_money(String hb_money) {
            this.hb_money = hb_money;
        }

        public String getPostFee() {
            return postFee;
        }

        public void setPostFee(String postFee) {
            this.postFee = postFee;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getShop_address() {
            return shop_address;
        }

        public void setShop_address(String shop_address) {
            this.shop_address = shop_address;
        }

        public String getAppraise_tag() {
            return appraise_tag;
        }

        public void setAppraise_tag(String appraise_tag) {
            this.appraise_tag = appraise_tag;
        }

        public String getGoods_count() {
            return goods_count;
        }

        public void setGoods_count(String goods_count) {
            this.goods_count = goods_count;
        }

        public String getBeizhu() {
            return beizhu;
        }

        public void setBeizhu(String beizhu) {
            this.beizhu = beizhu;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * appraise :
             * order_id : 1210
             * goods_id : 1
             * count : 1
             * price : 0.01
             * name : 东风风度MX5
             * trade_type : 1
             * is_overseas : 1
             * desc : 2016款 1.4T 手动豪华版
             * thumb : http://hmsadmin.zhuoranxiaoming.cn/uploads/thumb/1554343549thumb.png
             * beizhu :
             * weight :
             * colors :
             * appraise_tag : 0
             * is_shop_crad : 0
             */

            private String appraise;
            private String order_id;
            private String goods_id;
            private String count;
            private String price;
            private String name;
            private String trade_type;
            private String is_overseas;
            private String desc;
            private String thumb;
            private String beizhu;
            private String weight;
            private String colors;
            private String appraise_tag;
            private String is_shop_crad;

            public String getAppraise() {
                return appraise;
            }

            public void setAppraise(String appraise) {
                this.appraise = appraise;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTrade_type() {
                return trade_type;
            }

            public void setTrade_type(String trade_type) {
                this.trade_type = trade_type;
            }

            public String getIs_overseas() {
                return is_overseas;
            }

            public void setIs_overseas(String is_overseas) {
                this.is_overseas = is_overseas;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getBeizhu() {
                return beizhu;
            }

            public void setBeizhu(String beizhu) {
                this.beizhu = beizhu;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getColors() {
                return colors;
            }

            public void setColors(String colors) {
                this.colors = colors;
            }

            public String getAppraise_tag() {
                return appraise_tag;
            }

            public void setAppraise_tag(String appraise_tag) {
                this.appraise_tag = appraise_tag;
            }

            public String getIs_shop_crad() {
                return is_shop_crad;
            }

            public void setIs_shop_crad(String is_shop_crad) {
                this.is_shop_crad = is_shop_crad;
            }
        }
    }
}
