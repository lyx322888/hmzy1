package com.huiminsheng.app.bean;

import java.util.ArrayList;
import java.util.List;

public class BeanBypromoting {
    /**
     * result : {"code":"10000","msg":"请求成功"}
     * link :
     * data : {"list":[{"cid":"2","content":"2019款 【行驶里程】0万公里 /【首次上牌】2019-04 /【挡位／排量】自动／2L /【所在地】厦门 2019款 【行驶里程】0万公里 /【首次上牌】2019-04 /【挡位／排量】自动／2L /【所在地】厦门","addtime":"1558507280","counts":"0","imgs":["http://hmsadmin.zhuoranxiaoming.cn/uploads/20190522/a20a6000c44ba64d1d067c3102dd039b.png","http://hmsadmin.zhuoranxiaoming.cn/uploads/20190522/af89f2b5298f56f14db9d29880a5339d.png"]}],"count":"1","psize":"10"}
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
         * list : [{"cid":"2","content":"2019款 【行驶里程】0万公里 /【首次上牌】2019-04 /【挡位／排量】自动／2L /【所在地】厦门 2019款 【行驶里程】0万公里 /【首次上牌】2019-04 /【挡位／排量】自动／2L /【所在地】厦门","addtime":"1558507280","counts":"0","imgs":["http://hmsadmin.zhuoranxiaoming.cn/uploads/20190522/a20a6000c44ba64d1d067c3102dd039b.png","http://hmsadmin.zhuoranxiaoming.cn/uploads/20190522/af89f2b5298f56f14db9d29880a5339d.png"]}]
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
             * cid : 2
             * content : 2019款 【行驶里程】0万公里 /【首次上牌】2019-04 /【挡位／排量】自动／2L /【所在地】厦门 2019款 【行驶里程】0万公里 /【首次上牌】2019-04 /【挡位／排量】自动／2L /【所在地】厦门
             * addtime : 1558507280
             * counts : 0
             * imgs : ["http://hmsadmin.zhuoranxiaoming.cn/uploads/20190522/a20a6000c44ba64d1d067c3102dd039b.png","http://hmsadmin.zhuoranxiaoming.cn/uploads/20190522/af89f2b5298f56f14db9d29880a5339d.png"]
             */

            private String cid;
            private String content;
            private String addtime;
            private String counts;
            private ArrayList<String> imgs;

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getCounts() {
                return counts;
            }

            public void setCounts(String counts) {
                this.counts = counts;
            }

            public ArrayList<String> getImgs() {
                return imgs;
            }

            public void setImgs(ArrayList<String> imgs) {
                this.imgs = imgs;
            }
        }
    }
}
