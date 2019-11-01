package com.huiminsheng.app.bean;

import java.util.List;

public class BeanScreenMenu {
    /**
     * result : {"code":"10000","msg":"请求成功"}
     * link :
     * data : {"list":[{"id":"8","good_class":"9.9折"},{"id":"3","good_class":"限时8.8折"},{"id":"1","good_class":"限时特价"},{"id":"4","good_class":"首付"}],"count":"4","psize":""}
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
         * list : [{"id":"8","good_class":"9.9折"},{"id":"3","good_class":"限时8.8折"},{"id":"1","good_class":"限时特价"},{"id":"4","good_class":"首付"}]
         * count : 4
         * psize :
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
             * id : 8
             * good_class : 9.9折
             */

            private String id;
            private String good_class;
            //是否选择
            public boolean isChecked = false;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getGood_class() {
                return good_class;
            }

            public void setGood_class(String good_class) {
                this.good_class = good_class;
            }
        }
    }
}
