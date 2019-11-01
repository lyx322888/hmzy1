package com.huiminsheng.app.bean;

import java.util.List;

public class BeanTeamDetail {

    /**
     * result : {"code":"10000","msg":"请求成功"}
     * link :
     * data : {"list":[{"mchid":"2000000015","mobile":"","name":"小k","lfid":"1","status":"1","addtime":"1555049069","headpic":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJoKvSibLf1M1ffakWeTU1MPKXZPrLgIQLBT8CmSicU57MJbiaKeqV5OfGAuCL5Qo6aM7aicdW7ZzEcicw/132","status_info":"1","status_img":"1","level_image":"http://hmsapi.zhuoranxiaoming.cn/image/level/vip1.png","direct":"0","indirect":"0"}],"count":"1","psize":"10"}
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
         * list : [{"mchid":"2000000015","mobile":"","name":"小k","lfid":"1","status":"1","addtime":"1555049069","headpic":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJoKvSibLf1M1ffakWeTU1MPKXZPrLgIQLBT8CmSicU57MJbiaKeqV5OfGAuCL5Qo6aM7aicdW7ZzEcicw/132","status_info":"1","status_img":"1","level_image":"http://hmsapi.zhuoranxiaoming.cn/image/level/vip1.png","direct":"0","indirect":"0"}]
         * count : 1
         * psize : 10
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
             * mchid : 2000000015
             * mobile :
             * name : 小k
             * lfid : 1
             * status : 1
             * addtime : 1555049069
             * headpic : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJoKvSibLf1M1ffakWeTU1MPKXZPrLgIQLBT8CmSicU57MJbiaKeqV5OfGAuCL5Qo6aM7aicdW7ZzEcicw/132
             * status_info : 1
             * status_img : 1
             * level_image : http://hmsapi.zhuoranxiaoming.cn/image/level/vip1.png
             * direct : 0
             * indirect : 0
             */

            private String mchid;
            private String mobile;
            private String name;
            private String lfid;
            private String status;
            private String addtime;
            private String headpic;
            private String status_info;
            private String status_img;
            private String level_image;
            private String direct;
            private String indirect;

            public String getMchid() {
                return mchid;
            }

            public void setMchid(String mchid) {
                this.mchid = mchid;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLfid() {
                return lfid;
            }

            public void setLfid(String lfid) {
                this.lfid = lfid;
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

            public String getHeadpic() {
                return headpic;
            }

            public void setHeadpic(String headpic) {
                this.headpic = headpic;
            }

            public String getStatus_info() {
                return status_info;
            }

            public void setStatus_info(String status_info) {
                this.status_info = status_info;
            }

            public String getStatus_img() {
                return status_img;
            }

            public void setStatus_img(String status_img) {
                this.status_img = status_img;
            }

            public String getLevel_image() {
                return level_image;
            }

            public void setLevel_image(String level_image) {
                this.level_image = level_image;
            }

            public String getDirect() {
                return direct;
            }

            public void setDirect(String direct) {
                this.direct = direct;
            }

            public String getIndirect() {
                return indirect;
            }

            public void setIndirect(String indirect) {
                this.indirect = indirect;
            }
        }
    }
}
