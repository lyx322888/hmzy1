package com.huiminsheng.app.bean;

import java.util.List;

public class BeanMyTeam {
    /**
     * result : {"code":"10000","msg":"请求成功"}
     * link :
     * data : {"total":"0","list":[{"lf_id":"1","lf_name":"一星庄主","direct_count":"0","indirect_count":"0","count":"0","image_url":"http://hmsapi.zhuoranxiaoming.cn/image/team/vip_1.png"},{"lf_id":"2","lf_name":"二星庄主","direct_count":"0","indirect_count":"0","count":"0","image_url":"http://hmsapi.zhuoranxiaoming.cn/image/team/vip_2.png"},{"lf_id":"3","lf_name":"三星庄主","direct_count":"0","indirect_count":"0","count":"0","image_url":"http://hmsapi.zhuoranxiaoming.cn/image/team/vip_3.png"}],"direct_count":"0","indirect_count":"0"}
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
         * total : 0
         * list : [{"lf_id":"1","lf_name":"一星庄主","direct_count":"0","indirect_count":"0","count":"0","image_url":"http://hmsapi.zhuoranxiaoming.cn/image/team/vip_1.png"},{"lf_id":"2","lf_name":"二星庄主","direct_count":"0","indirect_count":"0","count":"0","image_url":"http://hmsapi.zhuoranxiaoming.cn/image/team/vip_2.png"},{"lf_id":"3","lf_name":"三星庄主","direct_count":"0","indirect_count":"0","count":"0","image_url":"http://hmsapi.zhuoranxiaoming.cn/image/team/vip_3.png"}]
         * direct_count : 0
         * indirect_count : 0
         */

        private String total;
        private String direct_count;
        private String indirect_count;
        private List<ListBean> list;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getDirect_count() {
            return direct_count;
        }

        public void setDirect_count(String direct_count) {
            this.direct_count = direct_count;
        }

        public String getIndirect_count() {
            return indirect_count;
        }

        public void setIndirect_count(String indirect_count) {
            this.indirect_count = indirect_count;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * lf_id : 1
             * lf_name : 一星庄主
             * direct_count : 0
             * indirect_count : 0
             * count : 0
             * image_url : http://hmsapi.zhuoranxiaoming.cn/image/team/vip_1.png
             */

            private String lf_id;
            private String lf_name;
            private String direct_count;
            private String indirect_count;
            private String count;
            private String image_url;

            public String getLf_id() {
                return lf_id;
            }

            public void setLf_id(String lf_id) {
                this.lf_id = lf_id;
            }

            public String getLf_name() {
                return lf_name;
            }

            public void setLf_name(String lf_name) {
                this.lf_name = lf_name;
            }

            public String getDirect_count() {
                return direct_count;
            }

            public void setDirect_count(String direct_count) {
                this.direct_count = direct_count;
            }

            public String getIndirect_count() {
                return indirect_count;
            }

            public void setIndirect_count(String indirect_count) {
                this.indirect_count = indirect_count;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }
        }
    }
}
