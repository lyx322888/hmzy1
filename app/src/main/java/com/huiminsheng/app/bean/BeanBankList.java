package com.huiminsheng.app.bean;

import java.util.List;

public class BeanBankList {
    /**
     * result : {"code":"10000","msg":"请求成功"}
     * link :
     * data : {"list":[{"id":"102","name":"中国工商银行","status":"1","img":"/uploads/banklist/bankground.png","logo":"/uploads/banklist/bank_gs@2x.png"},{"id":"103","name":"中国农业银行","status":"1","img":"/uploads/banklist/bankground.png","logo":"/uploads/banklist/bank_ny@2x.png"},{"id":"104","name":"中国银行","status":"1","img":"/uploads/banklist/bankground.png","logo":"/uploads/banklist/bank_zg@2x.png"},{"id":"105","name":"中国建设银行","status":"1","img":"/uploads/banklist/bankground.png","logo":"/uploads/banklist/bank_js@2x.png"},{"id":"301","name":"交通银行","status":"1","img":"/uploads/banklist/bankground.png","logo":"/uploads/banklist/bank_jt@2x.png"},{"id":"302","name":"中信银行","status":"1","img":"/uploads/banklist/bankground.png","logo":"/uploads/banklist/bank_zx@2x.png"},{"id":"303","name":"中国光大银行","status":"1","img":"/uploads/banklist/bankground.png","logo":"/uploads/banklist/bank_gd@2x.png"},{"id":"304","name":"华夏银行","status":"1","img":"/uploads/banklist/bankground.png","logo":"/uploads/banklist/bank_hx@2x.png"},{"id":"305","name":"中国民生银行","status":"1","img":"/uploads/banklist/bankground.png","logo":"/uploads/banklist/bank_ms@2x.png"},{"id":"306","name":"广发银行","status":"1","img":"/uploads/banklist/bankground.png","logo":"/uploads/banklist/bank_gf@2x.png"},{"id":"308","name":"招商银行","status":"1","img":"/uploads/banklist/bankground.png","logo":"/uploads/banklist/bank_zs@2x.png"},{"id":"309","name":"兴业银行","status":"1","img":"/uploads/banklist/bankground.png","logo":"/uploads/banklist/bank_xy@2x.png"},{"id":"310","name":"浦东发展银行","status":"1","img":"/uploads/banklist/bankground.png","logo":"/uploads/banklist/bank_pf@2x.png"},{"id":"403","name":"中国邮政储蓄银行","status":"1","img":"/uploads/banklist/bankground.png","logo":"/uploads/banklist/bank_yz@2x.png"},{"id":"410","name":"平安银行","status":"1","img":"/uploads/banklist/bankground.png","logo":"/uploads/banklist/bank_pa@2x.png"}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 102
             * name : 中国工商银行
             * status : 1
             * img : /uploads/banklist/bankground.png
             * logo : /uploads/banklist/bank_gs@2x.png
             */

            private String id;
            private String name;
            private String status;
            private String img;
            private String logo;

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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }
        }
    }
}
