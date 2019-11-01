package com.huiminsheng.app.bean;

import java.util.List;

public class BeanCashlist {

    /**
     * result : {"code":"10000","msg":"请求成功"}
     * link :
     * data : {"list":[{"id":"20","sn":"ON90005201905271714410001","ct_id":"11","ct_name":"T+1 下个工作日到帐","pc_id":"-1","pc_name":"分润提现","money":"100.00","total":"0.00","real_money":"99.00","fee_rate":"0.0000","fee_static":"1.00","status":"0","addtime":"1558948481","dispostime":"0"}],"count":"1","psize":"10","total_money":"0"}
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
         * list : [{"id":"20","sn":"ON90005201905271714410001","ct_id":"11","ct_name":"T+1 下个工作日到帐","pc_id":"-1","pc_name":"分润提现","money":"100.00","total":"0.00","real_money":"99.00","fee_rate":"0.0000","fee_static":"1.00","status":"0","addtime":"1558948481","dispostime":"0"}]
         * count : 1
         * psize : 10
         * total_money : 0
         */

        private String count;
        private String psize;
        private String total_money;
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

        public String getTotal_money() {
            return total_money;
        }

        public void setTotal_money(String total_money) {
            this.total_money = total_money;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 20
             * sn : ON90005201905271714410001
             * ct_id : 11
             * ct_name : T+1 下个工作日到帐
             * pc_id : -1
             * pc_name : 分润提现
             * money : 100.00
             * total : 0.00
             * real_money : 99.00
             * fee_rate : 0.0000
             * fee_static : 1.00
             * status : 0
             * addtime : 1558948481
             * dispostime : 0
             */

            private String id;
            private String sn;
            private String ct_id;
            private String ct_name;
            private String pc_id;
            private String pc_name;
            private String money;
            private String total;
            private String real_money;
            private String fee_rate;
            private String fee_static;
            private String status;
            private String addtime;
            private String dispostime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }

            public String getCt_id() {
                return ct_id;
            }

            public void setCt_id(String ct_id) {
                this.ct_id = ct_id;
            }

            public String getCt_name() {
                return ct_name;
            }

            public void setCt_name(String ct_name) {
                this.ct_name = ct_name;
            }

            public String getPc_id() {
                return pc_id;
            }

            public void setPc_id(String pc_id) {
                this.pc_id = pc_id;
            }

            public String getPc_name() {
                return pc_name;
            }

            public void setPc_name(String pc_name) {
                this.pc_name = pc_name;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getReal_money() {
                return real_money;
            }

            public void setReal_money(String real_money) {
                this.real_money = real_money;
            }

            public String getFee_rate() {
                return fee_rate;
            }

            public void setFee_rate(String fee_rate) {
                this.fee_rate = fee_rate;
            }

            public String getFee_static() {
                return fee_static;
            }

            public void setFee_static(String fee_static) {
                this.fee_static = fee_static;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getDispostime() {
                return dispostime;
            }

            public void setDispostime(String dispostime) {
                this.dispostime = dispostime;
            }
        }
    }
}
