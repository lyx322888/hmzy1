package com.huiminsheng.app.bean;

import java.util.List;

public class BeanHomeFlzq {
    /**
     * result : {"code":"10000","msg":"请求成功"}
     * link :
     * data : {"list":[{"id":"1","trade_type":"1","type_name":"厂家直发","top_color":"#000000","image":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190401/c503e7c562588abbfdf05eb1dcbe0baf.png","details":"厂家批量购车6.6折","details_color":"#80ffff","url":"/goods/goodlist?"},{"id":"2","trade_type":"2","type_name":"二手车","top_color":"#000000","image":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190401/680765c46d71f3d5fde6cc3e35b3fd05.png","details":"超低月供 轻松还款","details_color":"#ffa042","url":"/goods/goodslist?"},{"id":"3","trade_type":"3","type_name":"上牌全新零公里","top_color":"#000000","image":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190401/224cc95d74dc281b9b0fde53dcfbba6b.png","details":"送购置税,保险,包提档,","details_color":"#96fed1","url":"/goods/goodslist?"},{"id":"4","trade_type":"4","type_name":"零首付","top_color":"#000000","image":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190401/6fc381de11b645251c08046d03b8dd9a.png","details":"0成首付 购大牌无压力","details_color":"#9999cc","url":"/goods/goodlist?"}],"count":"4","psize":"","banner_Middle":{"Middle_top":"福利专区","banner_ary":[{"images_url":"http://hmsadmin.zhuoranxiaoming.cn/uploads/baner/banner11.png","link_url":""}]}}
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
         * list : [{"id":"1","trade_type":"1","type_name":"厂家直发","top_color":"#000000","image":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190401/c503e7c562588abbfdf05eb1dcbe0baf.png","details":"厂家批量购车6.6折","details_color":"#80ffff","url":"/goods/goodlist?"},{"id":"2","trade_type":"2","type_name":"二手车","top_color":"#000000","image":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190401/680765c46d71f3d5fde6cc3e35b3fd05.png","details":"超低月供 轻松还款","details_color":"#ffa042","url":"/goods/goodslist?"},{"id":"3","trade_type":"3","type_name":"上牌全新零公里","top_color":"#000000","image":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190401/224cc95d74dc281b9b0fde53dcfbba6b.png","details":"送购置税,保险,包提档,","details_color":"#96fed1","url":"/goods/goodslist?"},{"id":"4","trade_type":"4","type_name":"零首付","top_color":"#000000","image":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190401/6fc381de11b645251c08046d03b8dd9a.png","details":"0成首付 购大牌无压力","details_color":"#9999cc","url":"/goods/goodlist?"}]
         * count : 4
         * psize :
         * banner_Middle : {"Middle_top":"福利专区","banner_ary":[{"images_url":"http://hmsadmin.zhuoranxiaoming.cn/uploads/baner/banner11.png","link_url":""}]}
         */

        private String count;
        private String psize;
        private BannerMiddleBean banner_Middle;
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

        public BannerMiddleBean getBanner_Middle() {
            return banner_Middle;
        }

        public void setBanner_Middle(BannerMiddleBean banner_Middle) {
            this.banner_Middle = banner_Middle;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class BannerMiddleBean {
            /**
             * Middle_top : 福利专区
             * banner_ary : [{"images_url":"http://hmsadmin.zhuoranxiaoming.cn/uploads/baner/banner11.png","link_url":""}]
             */

            private String Middle_top;
            private List<BannerAryBean> banner_ary;

            public String getMiddle_top() {
                return Middle_top;
            }

            public void setMiddle_top(String Middle_top) {
                this.Middle_top = Middle_top;
            }

            public List<BannerAryBean> getBanner_ary() {
                return banner_ary;
            }

            public void setBanner_ary(List<BannerAryBean> banner_ary) {
                this.banner_ary = banner_ary;
            }

            public static class BannerAryBean {
                /**
                 * images_url : http://hmsadmin.zhuoranxiaoming.cn/uploads/baner/banner11.png
                 * link_url :
                 * goods_id  当链接为空的时候跳详情
                 */

                private String images_url;
                private String link_url;

                public String getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(String goods_id) {
                    this.goods_id = goods_id;
                }

                private String goods_id;

                public String getImages_url() {
                    return images_url;
                }

                public void setImages_url(String images_url) {
                    this.images_url = images_url;
                }

                public String getLink_url() {
                    return link_url;
                }

                public void setLink_url(String link_url) {
                    this.link_url = link_url;
                }
            }
        }

        public static class ListBean {
            /**
             * id : 1
             * trade_type : 1
             * type_name : 厂家直发
             * top_color : #000000
             * image : http://hmsadmin.zhuoranxiaoming.cn/uploads/20190401/c503e7c562588abbfdf05eb1dcbe0baf.png
             * details : 厂家批量购车6.6折
             * details_color : #80ffff
             * url : /goods/goodlist?
             */

            private String id;
            private String trade_type;
            private String type_name;
            private String top_color;
            private String image;
            private String details;
            private String details_color;
            private String url;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTrade_type() {
                return trade_type;
            }

            public void setTrade_type(String trade_type) {
                this.trade_type = trade_type;
            }

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
            }

            public String getTop_color() {
                return top_color;
            }

            public void setTop_color(String top_color) {
                this.top_color = top_color;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getDetails() {
                return details;
            }

            public void setDetails(String details) {
                this.details = details;
            }

            public String getDetails_color() {
                return details_color;
            }

            public void setDetails_color(String details_color) {
                this.details_color = details_color;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
