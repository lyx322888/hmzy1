package com.huiminsheng.app.bean;

import java.util.List;

public class BeanMessage {
    /**
     * result : {"code":"10000","msg":"请求成功"}
     * link :
     * data : {"list":[{"icon":"http://hmsapi.zhuoranxiaoming.cn/image/icons/message_active@3x.png","title":"系统消息","type":"12","msg":"","time":""},{"icon":"http://hmsapi.zhuoranxiaoming.cn/image/icons/message_bill@3x.png","title":"账单消息","type":"13","msg":"您申请余额:20元的提现","time":"2019-04-28 10:11:48"},{"icon":"http://hmsapi.zhuoranxiaoming.cn/image/icons/message_treasure@3x.png","title":"财富消息","type":"14","msg":"您好，您推荐的小k成功注册成为粉丝。","time":"2019-04-12 14:04:30"}]}
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
             * icon : http://hmsapi.zhuoranxiaoming.cn/image/icons/message_active@3x.png
             * title : 系统消息
             * type : 12
             * msg :
             * time :
             */

            private String icon;
            private String title;
            private String type;
            private String msg;
            private String time;

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }
    }
}
