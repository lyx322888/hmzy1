package com.huiminsheng.app.bean;

import java.util.List;

public class BeanMybank {
    /**
     * result : {"code":"10000","msg":"请求成功"}
     * link :
     * data : {"deposit":[{"bank":"105","bank_name":"中国建设银行","bank_img":"http://hmsapi.zhuoranxiaoming.cn/uploads/bank_img/ccb.png","bank_no":"6217 **** **** 9377","name":"黄俊炜","idcard":"350322199403052530","addtime":"1554344021","bank_logo":"http://hmsapi.zhuoranxiaoming.cn/uploads/banklist/bank_js@2x.png"}]}
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
        private List<DepositBean> deposit;

        public List<DepositBean> getDeposit() {
            return deposit;
        }

        public void setDeposit(List<DepositBean> deposit) {
            this.deposit = deposit;
        }

        public static class DepositBean {
            /**
             * bank : 105
             * bank_name : 中国建设银行
             * bank_img : http://hmsapi.zhuoranxiaoming.cn/uploads/bank_img/ccb.png
             * bank_no : 6217 **** **** 9377
             * name : 黄俊炜
             * idcard : 350322199403052530
             * addtime : 1554344021
             * bank_logo : http://hmsapi.zhuoranxiaoming.cn/uploads/banklist/bank_js@2x.png
             */

            private String bank;
            private String bank_name;
            private String bank_img;
            private String bank_no;
            private String name;
            private String idcard;
            private String addtime;
            private String bank_logo;

            public String getBank() {
                return bank;
            }

            public void setBank(String bank) {
                this.bank = bank;
            }

            public String getBank_name() {
                return bank_name;
            }

            public void setBank_name(String bank_name) {
                this.bank_name = bank_name;
            }

            public String getBank_img() {
                return bank_img;
            }

            public void setBank_img(String bank_img) {
                this.bank_img = bank_img;
            }

            public String getBank_no() {
                return bank_no;
            }

            public void setBank_no(String bank_no) {
                this.bank_no = bank_no;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIdcard() {
                return idcard;
            }

            public void setIdcard(String idcard) {
                this.idcard = idcard;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getBank_logo() {
                return bank_logo;
            }

            public void setBank_logo(String bank_logo) {
                this.bank_logo = bank_logo;
            }
        }
    }
}
