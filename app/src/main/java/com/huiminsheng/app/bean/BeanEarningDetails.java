package com.huiminsheng.app.bean;

import java.util.List;

public class BeanEarningDetails {
    /**
     * result : {"code":"10000","msg":"请求成功"}
     * link :
     * data : {"list":[{"id":"47","money":"12.74","addtime":"1556092161","status":"0","type":"33","mch_name":"炜","mch_mobile":"15980991196","lfid":"1","mchid":"2000000004","typeinfo":"代理收益,来自炜","enable_call":"0","lf_name":"小赚","lf_id":"1"},{"id":"46","money":"12.74","addtime":"1556091015","status":"0","type":"33","mch_name":"炜","mch_mobile":"15980991196","lfid":"1","mchid":"2000000004","typeinfo":"代理收益,来自炜","enable_call":"0","lf_name":"小赚","lf_id":"1"},{"id":"45","money":"12.74","addtime":"1555989408","status":"0","type":"33","mch_name":"炜","mch_mobile":"15980991196","lfid":"1","mchid":"2000000004","typeinfo":"代理收益,来自炜","enable_call":"0","lf_name":"小赚","lf_id":"1"},{"id":"43","money":"392.00","addtime":"1555578982","status":"0","type":"33","mch_name":"炜","mch_mobile":"15980991196","lfid":"1","mchid":"2000000004","typeinfo":"代理收益,来自炜","enable_call":"0","lf_name":"小赚","lf_id":"1"}],"count":"4","psize":"10","total":"430.22"}
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
         * list : [{"id":"47","money":"12.74","addtime":"1556092161","status":"0","type":"33","mch_name":"炜","mch_mobile":"15980991196","lfid":"1","mchid":"2000000004","typeinfo":"代理收益,来自炜","enable_call":"0","lf_name":"小赚","lf_id":"1"},{"id":"46","money":"12.74","addtime":"1556091015","status":"0","type":"33","mch_name":"炜","mch_mobile":"15980991196","lfid":"1","mchid":"2000000004","typeinfo":"代理收益,来自炜","enable_call":"0","lf_name":"小赚","lf_id":"1"},{"id":"45","money":"12.74","addtime":"1555989408","status":"0","type":"33","mch_name":"炜","mch_mobile":"15980991196","lfid":"1","mchid":"2000000004","typeinfo":"代理收益,来自炜","enable_call":"0","lf_name":"小赚","lf_id":"1"},{"id":"43","money":"392.00","addtime":"1555578982","status":"0","type":"33","mch_name":"炜","mch_mobile":"15980991196","lfid":"1","mchid":"2000000004","typeinfo":"代理收益,来自炜","enable_call":"0","lf_name":"小赚","lf_id":"1"}]
         * count : 4
         * psize : 10
         * total : 430.22
         */

        private String count;
        private String psize;
        private String total;
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

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 47
             * money : 12.74
             * addtime : 1556092161
             * status : 0
             * type : 33
             * mch_name : 炜
             * mch_mobile : 15980991196
             * lfid : 1
             * mchid : 2000000004
             * typeinfo : 代理收益,来自炜
             * enable_call : 0
             * lf_name : 小赚
             * lf_id : 1
             */

            private String id;
            private String money;
            private String addtime;
            private String status;
            private String type;
            private String mch_name;
            private String mch_mobile;
            private String lfid;
            private String mchid;
            private String typeinfo;
            private String enable_call;
            private String lf_name;
            private String lf_id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getMch_name() {
                return mch_name;
            }

            public void setMch_name(String mch_name) {
                this.mch_name = mch_name;
            }

            public String getMch_mobile() {
                return mch_mobile;
            }

            public void setMch_mobile(String mch_mobile) {
                this.mch_mobile = mch_mobile;
            }

            public String getLfid() {
                return lfid;
            }

            public void setLfid(String lfid) {
                this.lfid = lfid;
            }

            public String getMchid() {
                return mchid;
            }

            public void setMchid(String mchid) {
                this.mchid = mchid;
            }

            public String getTypeinfo() {
                return typeinfo;
            }

            public void setTypeinfo(String typeinfo) {
                this.typeinfo = typeinfo;
            }

            public String getEnable_call() {
                return enable_call;
            }

            public void setEnable_call(String enable_call) {
                this.enable_call = enable_call;
            }

            public String getLf_name() {
                return lf_name;
            }

            public void setLf_name(String lf_name) {
                this.lf_name = lf_name;
            }

            public String getLf_id() {
                return lf_id;
            }

            public void setLf_id(String lf_id) {
                this.lf_id = lf_id;
            }
        }
    }
}
