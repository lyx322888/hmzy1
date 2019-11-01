package com.huiminsheng.app.bean;

import java.util.List;

public class BeanMyOrder {
    /**
     * result : {"code":"10000","msg":"请求成功"}
     * link :
     * data : {"list":[{"order_sn":"hms200000000420190423111641","all_price":"0.01","status":"3","post_no":"","s_time":"","e_time":"","postFee":"0.00","hb_money":"0.00","is_overseas":"0","cardAccount":"112222222","cardPassword":"2222","shop_name":"厦门翔安店","shop_id":"1017","appraise_tag":"0","goods_count":"1","list":[{"appraise_time":"","appraise_type":"","appraise":"","order_id":"1045","goods_id":"32","count":"1","price":"0.01","name":"移动、联通、电信三网全国通用100元话费充值卡","desc":"移动、联通、电信三网全国通用100元话费充值卡","thumb":"http://hmsadmin.zhuoranxiaoming.cn/uploads/thumb/1554953396thumb.png","weight":"","colors":"","trade_type":"5","appraise_tag":"0","titles":["规格","颜色"]}]},{"order_sn":"hms200000000420190423111527","all_price":"0.01","status":"3","post_no":"","s_time":"","e_time":"","postFee":"0.00","hb_money":"0.00","is_overseas":"0","cardAccount":"","cardPassword":"","shop_name":"厦门翔安店","shop_id":"1017","appraise_tag":"0","goods_count":"1","list":[{"appraise_time":"","appraise_type":"","appraise":"","order_id":"1043","goods_id":"1","count":"1","price":"0.01","name":"东风风度MX5","desc":"2016款 1.4T 手动豪华版","thumb":"http://hmsadmin.zhuoranxiaoming.cn/uploads/thumb/1554343549thumb.png","weight":"","colors":"","trade_type":"1","appraise_tag":"0","titles":["规格","颜色"]}]}],"count":"2","psize":"10"}
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
         * list : [{"order_sn":"hms200000000420190423111641","all_price":"0.01","status":"3","post_no":"","s_time":"","e_time":"","postFee":"0.00","hb_money":"0.00","is_overseas":"0","cardAccount":"112222222","cardPassword":"2222","shop_name":"厦门翔安店","shop_id":"1017","appraise_tag":"0","goods_count":"1","list":[{"appraise_time":"","appraise_type":"","appraise":"","order_id":"1045","goods_id":"32","count":"1","price":"0.01","name":"移动、联通、电信三网全国通用100元话费充值卡","desc":"移动、联通、电信三网全国通用100元话费充值卡","thumb":"http://hmsadmin.zhuoranxiaoming.cn/uploads/thumb/1554953396thumb.png","weight":"","colors":"","trade_type":"5","appraise_tag":"0","titles":["规格","颜色"]}]},{"order_sn":"hms200000000420190423111527","all_price":"0.01","status":"3","post_no":"","s_time":"","e_time":"","postFee":"0.00","hb_money":"0.00","is_overseas":"0","cardAccount":"","cardPassword":"","shop_name":"厦门翔安店","shop_id":"1017","appraise_tag":"0","goods_count":"1","list":[{"appraise_time":"","appraise_type":"","appraise":"","order_id":"1043","goods_id":"1","count":"1","price":"0.01","name":"东风风度MX5","desc":"2016款 1.4T 手动豪华版","thumb":"http://hmsadmin.zhuoranxiaoming.cn/uploads/thumb/1554343549thumb.png","weight":"","colors":"","trade_type":"1","appraise_tag":"0","titles":["规格","颜色"]}]}]
         * count : 2
         * psize : 10
         */

        private String count;
        private String psize;
        private List<ListBeanX> list;

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

        public List<ListBeanX> getList() {
            return list;
        }

        public void setList(List<ListBeanX> list) {
            this.list = list;
        }

        public static class ListBeanX {
            /**
             * order_sn : hms200000000420190423111641
             * all_price : 0.01
             * status : 3
             * post_no :
             * s_time :
             * e_time :
             * postFee : 0.00
             * hb_money : 0.00
             * is_overseas : 0
             * cardAccount : 112222222
             * cardPassword : 2222
             * shop_name : 厦门翔安店
             * shop_id : 1017
             * appraise_tag : 0
             * goods_count : 1
             * list : [{"appraise_time":"","appraise_type":"","appraise":"","order_id":"1045","goods_id":"32","count":"1","price":"0.01","name":"移动、联通、电信三网全国通用100元话费充值卡","desc":"移动、联通、电信三网全国通用100元话费充值卡","thumb":"http://hmsadmin.zhuoranxiaoming.cn/uploads/thumb/1554953396thumb.png","weight":"","colors":"","trade_type":"5","appraise_tag":"0","titles":["规格","颜色"]}]
             */

            private String order_sn;
            private String all_price;
            private String status;
            private String post_no;
            private String s_time;
            private String e_time;
            private String postFee;
            private String hb_money;
            private String is_overseas;
            private String cardAccount;
            private String cardPassword;
            private String shop_name;
            private String shop_id;
            private String appraise_tag;
            private String goods_count;
            private List<ListBean> list;

            public String getOrder_sn() {
                return order_sn;
            }

            public void setOrder_sn(String order_sn) {
                this.order_sn = order_sn;
            }

            public String getAll_price() {
                return all_price;
            }

            public void setAll_price(String all_price) {
                this.all_price = all_price;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getPost_no() {
                return post_no;
            }

            public void setPost_no(String post_no) {
                this.post_no = post_no;
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

            public String getPostFee() {
                return postFee;
            }

            public void setPostFee(String postFee) {
                this.postFee = postFee;
            }

            public String getHb_money() {
                return hb_money;
            }

            public void setHb_money(String hb_money) {
                this.hb_money = hb_money;
            }

            public String getIs_overseas() {
                return is_overseas;
            }

            public void setIs_overseas(String is_overseas) {
                this.is_overseas = is_overseas;
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

            public String getShop_name() {
                return shop_name;
            }

            public void setShop_name(String shop_name) {
                this.shop_name = shop_name;
            }

            public String getShop_id() {
                return shop_id;
            }

            public void setShop_id(String shop_id) {
                this.shop_id = shop_id;
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

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * appraise_time :
                 * appraise_type :
                 * appraise :
                 * order_id : 1045
                 * goods_id : 32
                 * count : 1
                 * price : 0.01
                 * name : 移动、联通、电信三网全国通用100元话费充值卡
                 * desc : 移动、联通、电信三网全国通用100元话费充值卡
                 * thumb : http://hmsadmin.zhuoranxiaoming.cn/uploads/thumb/1554953396thumb.png
                 * weight :
                 * colors :
                 * trade_type : 5
                 * appraise_tag : 0
                 * titles : ["规格","颜色"]
                 */

                private String appraise_time;
                private String appraise_type;
                private String appraise;
                private String order_id;
                private String goods_id;
                private String count;
                private String price;
                private String name;
                private String desc;
                private String thumb;
                private String weight;
                private String colors;
                private String trade_type;
                private String appraise_tag;
                private List<String> titles;

                public String getAppraise_time() {
                    return appraise_time;
                }

                public void setAppraise_time(String appraise_time) {
                    this.appraise_time = appraise_time;
                }

                public String getAppraise_type() {
                    return appraise_type;
                }

                public void setAppraise_type(String appraise_type) {
                    this.appraise_type = appraise_type;
                }

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

                public String getTrade_type() {
                    return trade_type;
                }

                public void setTrade_type(String trade_type) {
                    this.trade_type = trade_type;
                }

                public String getAppraise_tag() {
                    return appraise_tag;
                }

                public void setAppraise_tag(String appraise_tag) {
                    this.appraise_tag = appraise_tag;
                }

                public List<String> getTitles() {
                    return titles;
                }

                public void setTitles(List<String> titles) {
                    this.titles = titles;
                }
            }
        }
    }
}
