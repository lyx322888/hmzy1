package com.huiminsheng.app.bean;

import java.util.List;

public class BeanUserinfo {
    /**
     * result : {"code":"10000","msg":"请求成功"}
     * link :
     * data : {"list":{"count1":"3","count2":"0","count3":"0","count4":"0","count5":"0"},"mylist":[{"id":"1","name":"会员中心","images_url":"http://hms.zhuoranxiaoming.cn/public/mylist/user_member@2x.png","link_url":"http://hms.zhuoranxiaoming.cn/wxother/vipcore.html","type":"0"},{"id":"2","name":"我的团队","images_url":"http://hms.zhuoranxiaoming.cn/public/mylist/user_team@2x.png","link_url":"http://hms.zhuoranxiaoming.cn/index/team.html","type":"0"},{"id":"3","name":"我的收益","images_url":"http://hms.zhuoranxiaoming.cn/public/mylist/user_earning@2x.png","link_url":"http://hms.zhuoranxiaoming.cn/index/profit.html","type":"0"},{"id":"4","name":"立即提现","images_url":"http://hms.zhuoranxiaoming.cn/public/mylist/user_withdraw@2x.png","link_url":"http://hms.zhuoranxiaoming.cn/index/extract.html","type":"0"}],"banner":{"banner_url":"http://hms.zhuoranxiaoming.cn/public/mylist/user_banner1@2x.png","link_url":"#"}}
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
         * list : {"count1":"3","count2":"0","count3":"0","count4":"0","count5":"0"}
         * mylist : [{"id":"1","name":"会员中心","images_url":"http://hms.zhuoranxiaoming.cn/public/mylist/user_member@2x.png","link_url":"http://hms.zhuoranxiaoming.cn/wxother/vipcore.html","type":"0"},{"id":"2","name":"我的团队","images_url":"http://hms.zhuoranxiaoming.cn/public/mylist/user_team@2x.png","link_url":"http://hms.zhuoranxiaoming.cn/index/team.html","type":"0"},{"id":"3","name":"我的收益","images_url":"http://hms.zhuoranxiaoming.cn/public/mylist/user_earning@2x.png","link_url":"http://hms.zhuoranxiaoming.cn/index/profit.html","type":"0"},{"id":"4","name":"立即提现","images_url":"http://hms.zhuoranxiaoming.cn/public/mylist/user_withdraw@2x.png","link_url":"http://hms.zhuoranxiaoming.cn/index/extract.html","type":"0"}]
         * banner : {"banner_url":"http://hms.zhuoranxiaoming.cn/public/mylist/user_banner1@2x.png","link_url":"#"}
         */

        private ListBean list;
        private BannerBean banner;
        private List<MylistBean> mylist;

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public BannerBean getBanner() {
            return banner;
        }

        public void setBanner(BannerBean banner) {
            this.banner = banner;
        }

        public List<MylistBean> getMylist() {
            return mylist;
        }

        public void setMylist(List<MylistBean> mylist) {
            this.mylist = mylist;
        }

        public static class ListBean {
            /**
             * count1 : 3
             * count2 : 0
             * count3 : 0
             * count4 : 0
             * count5 : 0
             */

            private String count1;
            private String count2;
            private String count3;
            private String count4;
            private String count5;

            public String getCount1() {
                return count1;
            }

            public void setCount1(String count1) {
                this.count1 = count1;
            }

            public String getCount2() {
                return count2;
            }

            public void setCount2(String count2) {
                this.count2 = count2;
            }

            public String getCount3() {
                return count3;
            }

            public void setCount3(String count3) {
                this.count3 = count3;
            }

            public String getCount4() {
                return count4;
            }

            public void setCount4(String count4) {
                this.count4 = count4;
            }

            public String getCount5() {
                return count5;
            }

            public void setCount5(String count5) {
                this.count5 = count5;
            }
        }

        public static class BannerBean {
            /**
             * banner_url : http://hms.zhuoranxiaoming.cn/public/mylist/user_banner1@2x.png
             * link_url : #
             */

            private String banner_url;
            private String link_url;

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            private String goods_id;

            public String getBanner_url() {
                return banner_url;
            }

            public void setBanner_url(String banner_url) {
                this.banner_url = banner_url;
            }

            public String getLink_url() {
                return link_url;
            }

            public void setLink_url(String link_url) {
                this.link_url = link_url;
            }
        }

        public static class MylistBean {
            /**
             * id : 1
             * name : 会员中心
             * images_url : http://hms.zhuoranxiaoming.cn/public/mylist/user_member@2x.png
             * link_url : http://hms.zhuoranxiaoming.cn/wxother/vipcore.html
             * type : 0
             */

            private String id;
            private String name;
            private String images_url;
            private String link_url;
            private String type;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
