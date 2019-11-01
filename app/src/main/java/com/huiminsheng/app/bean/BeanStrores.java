package com.huiminsheng.app.bean;

import java.util.List;

public class BeanStrores {
    /**
     * result : {"code":"10000","msg":"请求成功"}
     * link :
     * data : {"list":[{"id":"1017","name":"享个车正定店","contact":"张经理","mobile":"15613355788","address":"正定县燕赵北大街与育才路交汇处50米"},{"id":"1000","name":"正定享个车4s店","contact":"张经理","mobile":"15613355788","address":"正定县燕赵北大街与育才路交汇处"}],"count":"2","psize":"20"}
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
         * list : [{"id":"1017","name":"享个车正定店","contact":"张经理","mobile":"15613355788","address":"正定县燕赵北大街与育才路交汇处50米"},{"id":"1000","name":"正定享个车4s店","contact":"张经理","mobile":"15613355788","address":"正定县燕赵北大街与育才路交汇处"}]
         * count : 2
         * psize : 20
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
             * id : 1017
             * name : 享个车正定店
             * contact : 张经理
             * mobile : 15613355788
             * address : 正定县燕赵北大街与育才路交汇处50米
             */

            private String id;
            private String name;
            private String contact;
            private String mobile;
            private String address;
            public boolean isChecked = false; //是否选择

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

            public String getContact() {
                return contact;
            }

            public void setContact(String contact) {
                this.contact = contact;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }
    }
}
