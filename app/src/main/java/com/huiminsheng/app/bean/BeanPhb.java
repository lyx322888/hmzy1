package com.huiminsheng.app.bean;

import java.util.List;

public class BeanPhb {
    /**
     * result : {"code":"10000","msg":"请求成功"}
     * link :
     * data : {"list":[{"mchid":"2000000013","split_total":"120.000","commission_split_total":"0.00","ranking":"1","wxnickname":"&why","mobile":"","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/f4zdfDKgVImX3WSuVoPjMkEhOXpbHz5dzicWOSuibbv56f00qVvPvOgol5QJskn5yjlKLQvYaUemoS58EfZyLdQg/132"},{"mchid":"2000000012","split_total":"100.000","commission_split_total":"0.00","ranking":"2","wxnickname":"洪志升-91【金融渠道】","mobile":"","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqhkxW4icQZYdM6NibnK2bzwoRV4js9ic67Ibv8WxRGn6emS4SlxamiaSwrg5LlXPYW6BF5TzXC1648Vw/132"},{"mchid":"2000000011","split_total":"90.000","commission_split_total":"0.00","ranking":"3","wxnickname":"总有贱刁民想害朕","mobile":"181****8930","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIPwQsOmrSqkvSumEohz6IQZId6Vuia1ougzV496MdYxBich6psH1b6Csb4tcdiaYZibw1FMHia6jw2p0g/132"},{"mchid":"2000000008","split_total":"80.000","commission_split_total":"0.00","ranking":"4","wxnickname":"林乃煊","mobile":"","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJrc1tbia4qjfzw5ibY6FSibjNfBSzWPLIkib8d5ibic2EO3baHSJy4JdG710Agv3oBpAnmVF8H89JwqbSQ/132"},{"mchid":"2000000007","split_total":"70.000","commission_split_total":"0.00","ranking":"5","wxnickname":"杨金亮","mobile":"","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/3WZU0h8T4cLlwvjib7zQ8EgY0ibMYuh4KUiaSS5XStgiafcJWefcGH2ocLpoxG2qPytiaOt7k6QEiaiaOjdn7GAcAsmWA/132"},{"mchid":"2000000006","split_total":"60.000","commission_split_total":"0.00","ranking":"6","wxnickname":"林芸","mobile":"","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqGEXwafndOicc3deVRzMtRIKibrVo5NMqmyNvRON20QBhcVa4MupaQRbf9o4saLyrPlUgALwtKauLw/132"},{"mchid":"2000000005","split_total":"50.000","commission_split_total":"0.00","ranking":"7","wxnickname":"孙赫鑫","mobile":"","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/99LkSYc2iaUeMs8Fz7dkHDicTqyRwPS8Rvian7RrPpAPCFOeUCy1ANic2dRkvwSpyUiabI1pOUPbmydBJ5ypVwVw0Nw/132"},{"mchid":"2000000004","split_total":"40.000","commission_split_total":"0.00","ranking":"8","wxnickname":"炜","mobile":"159****1196","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/Hh6DI7DBTiccThPXfy0X88whe82YfMpsLrRhpyibCYciaeEtIPMmuRMznQWhSGAH3brDdZwlFiaNicCfgq8ocm86poQ/132"},{"mchid":"2000000003","split_total":"30.000","commission_split_total":"0.00","ranking":"9","wxnickname":"丽娟","mobile":"","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/mXU49icGo1o3Qasjibtm3LoFA6XyV01ubLR1FqDVqlf4xPj8331E8LACuO2iaheQl6nc8uYhhQz9iafUaz2rQUguzQ/132"},{"mchid":"2000000001","split_total":"20.000","commission_split_total":"0.00","ranking":"10","wxnickname":"joker","mobile":"","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJyESxqxibFMfClTPxlhdhicmm2NLticuS3fyPdJ0NWicQAGLo5Nxb6OfQlTIS5FiaIpznAhmfLyvJjZlw/132"}],"count":"17","psize":"10","this_mchid":[{"mchid":"2000000001","split_total":"20.000","commission_split_total":"0.00","ranking":"10","wxnickname":"joker","mobile":"","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJyESxqxibFMfClTPxlhdhicmm2NLticuS3fyPdJ0NWicQAGLo5Nxb6OfQlTIS5FiaIpznAhmfLyvJjZlw/132"}]}
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
         * list : [{"mchid":"2000000013","split_total":"120.000","commission_split_total":"0.00","ranking":"1","wxnickname":"&why","mobile":"","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/f4zdfDKgVImX3WSuVoPjMkEhOXpbHz5dzicWOSuibbv56f00qVvPvOgol5QJskn5yjlKLQvYaUemoS58EfZyLdQg/132"},{"mchid":"2000000012","split_total":"100.000","commission_split_total":"0.00","ranking":"2","wxnickname":"洪志升-91【金融渠道】","mobile":"","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqhkxW4icQZYdM6NibnK2bzwoRV4js9ic67Ibv8WxRGn6emS4SlxamiaSwrg5LlXPYW6BF5TzXC1648Vw/132"},{"mchid":"2000000011","split_total":"90.000","commission_split_total":"0.00","ranking":"3","wxnickname":"总有贱刁民想害朕","mobile":"181****8930","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIPwQsOmrSqkvSumEohz6IQZId6Vuia1ougzV496MdYxBich6psH1b6Csb4tcdiaYZibw1FMHia6jw2p0g/132"},{"mchid":"2000000008","split_total":"80.000","commission_split_total":"0.00","ranking":"4","wxnickname":"林乃煊","mobile":"","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJrc1tbia4qjfzw5ibY6FSibjNfBSzWPLIkib8d5ibic2EO3baHSJy4JdG710Agv3oBpAnmVF8H89JwqbSQ/132"},{"mchid":"2000000007","split_total":"70.000","commission_split_total":"0.00","ranking":"5","wxnickname":"杨金亮","mobile":"","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/3WZU0h8T4cLlwvjib7zQ8EgY0ibMYuh4KUiaSS5XStgiafcJWefcGH2ocLpoxG2qPytiaOt7k6QEiaiaOjdn7GAcAsmWA/132"},{"mchid":"2000000006","split_total":"60.000","commission_split_total":"0.00","ranking":"6","wxnickname":"林芸","mobile":"","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqGEXwafndOicc3deVRzMtRIKibrVo5NMqmyNvRON20QBhcVa4MupaQRbf9o4saLyrPlUgALwtKauLw/132"},{"mchid":"2000000005","split_total":"50.000","commission_split_total":"0.00","ranking":"7","wxnickname":"孙赫鑫","mobile":"","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/99LkSYc2iaUeMs8Fz7dkHDicTqyRwPS8Rvian7RrPpAPCFOeUCy1ANic2dRkvwSpyUiabI1pOUPbmydBJ5ypVwVw0Nw/132"},{"mchid":"2000000004","split_total":"40.000","commission_split_total":"0.00","ranking":"8","wxnickname":"炜","mobile":"159****1196","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/Hh6DI7DBTiccThPXfy0X88whe82YfMpsLrRhpyibCYciaeEtIPMmuRMznQWhSGAH3brDdZwlFiaNicCfgq8ocm86poQ/132"},{"mchid":"2000000003","split_total":"30.000","commission_split_total":"0.00","ranking":"9","wxnickname":"丽娟","mobile":"","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/mXU49icGo1o3Qasjibtm3LoFA6XyV01ubLR1FqDVqlf4xPj8331E8LACuO2iaheQl6nc8uYhhQz9iafUaz2rQUguzQ/132"},{"mchid":"2000000001","split_total":"20.000","commission_split_total":"0.00","ranking":"10","wxnickname":"joker","mobile":"","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJyESxqxibFMfClTPxlhdhicmm2NLticuS3fyPdJ0NWicQAGLo5Nxb6OfQlTIS5FiaIpznAhmfLyvJjZlw/132"}]
         * count : 17
         * psize : 10
         * this_mchid : [{"mchid":"2000000001","split_total":"20.000","commission_split_total":"0.00","ranking":"10","wxnickname":"joker","mobile":"","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJyESxqxibFMfClTPxlhdhicmm2NLticuS3fyPdJ0NWicQAGLo5Nxb6OfQlTIS5FiaIpznAhmfLyvJjZlw/132"}]
         */

        private String count;
        private String psize;
        private List<ListBean> list;
        private List<ThisMchidBean> this_mchid;

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

        public List<ThisMchidBean> getThis_mchid() {
            return this_mchid;
        }

        public void setThis_mchid(List<ThisMchidBean> this_mchid) {
            this.this_mchid = this_mchid;
        }

        public static class ListBean {
            /**
             * mchid : 2000000013
             * split_total : 120.000
             * commission_split_total : 0.00
             * ranking : 1
             * wxnickname : &why
             * mobile :
             * headimgurl : http://thirdwx.qlogo.cn/mmopen/vi_32/f4zdfDKgVImX3WSuVoPjMkEhOXpbHz5dzicWOSuibbv56f00qVvPvOgol5QJskn5yjlKLQvYaUemoS58EfZyLdQg/132
             */

            private String mchid;
            private String split_total;
            private String commission_split_total;
            private String ranking;
            private String wxnickname;
            private String mobile;
            private String headimgurl;

            public String getMchid() {
                return mchid;
            }

            public void setMchid(String mchid) {
                this.mchid = mchid;
            }

            public String getSplit_total() {
                return split_total;
            }

            public void setSplit_total(String split_total) {
                this.split_total = split_total;
            }

            public String getCommission_split_total() {
                return commission_split_total;
            }

            public void setCommission_split_total(String commission_split_total) {
                this.commission_split_total = commission_split_total;
            }

            public String getRanking() {
                return ranking;
            }

            public void setRanking(String ranking) {
                this.ranking = ranking;
            }

            public String getWxnickname() {
                return wxnickname;
            }

            public void setWxnickname(String wxnickname) {
                this.wxnickname = wxnickname;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getHeadimgurl() {
                return headimgurl;
            }

            public void setHeadimgurl(String headimgurl) {
                this.headimgurl = headimgurl;
            }
        }

        public static class ThisMchidBean {
            /**
             * mchid : 2000000001
             * split_total : 20.000
             * commission_split_total : 0.00
             * ranking : 10
             * wxnickname : joker
             * mobile :
             * headimgurl : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJyESxqxibFMfClTPxlhdhicmm2NLticuS3fyPdJ0NWicQAGLo5Nxb6OfQlTIS5FiaIpznAhmfLyvJjZlw/132
             */

            private String mchid;
            private String split_total;
            private String commission_split_total;
            private String ranking;
            private String wxnickname;
            private String mobile;
            private String headimgurl;

            public String getMchid() {
                return mchid;
            }

            public void setMchid(String mchid) {
                this.mchid = mchid;
            }

            public String getSplit_total() {
                return split_total;
            }

            public void setSplit_total(String split_total) {
                this.split_total = split_total;
            }

            public String getCommission_split_total() {
                return commission_split_total;
            }

            public void setCommission_split_total(String commission_split_total) {
                this.commission_split_total = commission_split_total;
            }

            public String getRanking() {
                return ranking;
            }

            public void setRanking(String ranking) {
                this.ranking = ranking;
            }

            public String getWxnickname() {
                return wxnickname;
            }

            public void setWxnickname(String wxnickname) {
                this.wxnickname = wxnickname;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getHeadimgurl() {
                return headimgurl;
            }

            public void setHeadimgurl(String headimgurl) {
                this.headimgurl = headimgurl;
            }
        }
    }
}
