package com.huiminsheng.app.bean;

import java.util.List;
/*首页*/
public class BeanHome {

    /**
     * result : {"code":"10000","msg":"请求成功"}
     * link :
     * data : {"list":[{"id":"1","image":"http://hmsadmin.zhuoranxiaoming.cn/uploads/image/function1@3x.png","url":"http://www.baidu.com","name":"广惠通"},{"id":"2","image":"http://hmsadmin.zhuoranxiaoming.cn/uploads/image/function2@3x.png","url":"http://www.baidu.com","name":"享有车"},{"id":"3","image":"http://hmsadmin.zhuoranxiaoming.cn/uploads/image/function3@3x.png","url":"","name":"看电影"},{"id":"4","image":"http://hmsadmin.zhuoranxiaoming.cn/uploads/image/function4@3x.png","url":"","name":"新手指南"}],"count":"4","psize":"","banner":[{"banner_url":"http://hmsadmin.zhuoranxiaoming.cn/uploads/baner/banre1.png","link_url":""},{"banner_url":"http://hmsadmin.zhuoranxiaoming.cn/uploads/baner/banre2.png","link_url":""}]}
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
         * list : [{"id":"1","image":"http://hmsadmin.zhuoranxiaoming.cn/uploads/image/function1@3x.png","url":"http://www.baidu.com","name":"广惠通"},{"id":"2","image":"http://hmsadmin.zhuoranxiaoming.cn/uploads/image/function2@3x.png","url":"http://www.baidu.com","name":"享有车"},{"id":"3","image":"http://hmsadmin.zhuoranxiaoming.cn/uploads/image/function3@3x.png","url":"","name":"看电影"},{"id":"4","image":"http://hmsadmin.zhuoranxiaoming.cn/uploads/image/function4@3x.png","url":"","name":"新手指南"}]
         * count : 4
         * 1 详情 2 搜索页
         * psize :
         * banner : [{"banner_url":"http://hmsadmin.zhuoranxiaoming.cn/uploads/baner/banre1.png","link_url":""},{"banner_url":"http://hmsadmin.zhuoranxiaoming.cn/uploads/baner/banre2.png","link_url":""}]
         */

        private String count;
        private String psize;
        private String type;
        private List<ListBean> list;
        private List<BannerBean> banner;

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

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public static class ListBean {
            /**
             * id : 1
             * image : http://hmsadmin.zhuoranxiaoming.cn/uploads/image/function1@3x.png
             * url : http://www.baidu.com
             * name : 广惠通
             */

            private String id;
            private String image;
            private String url;
            private String name;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            private String type;

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            private String goods_id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class BannerBean {
            /**
             * banner_url : http://hmsadmin.zhuoranxiaoming.cn/uploads/baner/banre1.png
             * link_url :
             */

            private String banner_url;
            private String link_url;

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
    }
}
