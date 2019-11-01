package com.huiminsheng.app.bean;

import java.util.ArrayList;
import java.util.List;

public class BeanShare {
    /**
     * result : {"code":"10000","msg":"请求成功"}
     * link :
     * data : {"banner":"","title":"分享","url":"","list":[{"logo":"http://hmsapi.zhuoranxiaoming.cn/image/share/share_hzc4.png","name":"二维码推广","type":"1","desc":"邀请好友注册","title":"惠民庄园","msg":"惠民庄园","url":"http://www.baidu.com","invite_url":"","rule":"1","rule_url":"","images":["http://hmsapi.zhuoranxiaoming.cn/image/share/shahzc_bg40.png","http://hmsapi.zhuoranxiaoming.cn/image/share/shahzc_bg41.png","http://hmsapi.zhuoranxiaoming.cn/image/share/shahzc_bg42.png","http://hmsapi.zhuoranxiaoming.cn/image/share/shahzc_bg43.png","http://hmsapi.zhuoranxiaoming.cn/image/share/shahzc_bg44.png"],"share_desc":"这段话要放在分享页面"},{"logo":"http://hmsapi.zhuoranxiaoming.cn/image/share/share_hzc1.png","name":"图文推广","type":"2","desc":"文案宣传资料下载","title":"惠民庄园","msg":"惠民庄园","url":"","invite_url":"","rule":"0","rule_url":"","images":[],"share_desc":"这段话要放在分享页面"}]}
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
         * banner :
         * title : 分享
         * url :
         * list : [{"logo":"http://hmsapi.zhuoranxiaoming.cn/image/share/share_hzc4.png","name":"二维码推广","type":"1","desc":"邀请好友注册","title":"惠民庄园","msg":"惠民庄园","url":"http://www.baidu.com","invite_url":"","rule":"1","rule_url":"","images":["http://hmsapi.zhuoranxiaoming.cn/image/share/shahzc_bg40.png","http://hmsapi.zhuoranxiaoming.cn/image/share/shahzc_bg41.png","http://hmsapi.zhuoranxiaoming.cn/image/share/shahzc_bg42.png","http://hmsapi.zhuoranxiaoming.cn/image/share/shahzc_bg43.png","http://hmsapi.zhuoranxiaoming.cn/image/share/shahzc_bg44.png"],"share_desc":"这段话要放在分享页面"},{"logo":"http://hmsapi.zhuoranxiaoming.cn/image/share/share_hzc1.png","name":"图文推广","type":"2","desc":"文案宣传资料下载","title":"惠民庄园","msg":"惠民庄园","url":"","invite_url":"","rule":"0","rule_url":"","images":[],"share_desc":"这段话要放在分享页面"}]
         */

        private String banner;
        private String title;
        private String url;
        private List<ListBean> list;

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * logo : http://hmsapi.zhuoranxiaoming.cn/image/share/share_hzc4.png
             * name : 二维码推广
             * type : 1
             * desc : 邀请好友注册
             * title : 惠民庄园
             * msg : 惠民庄园
             * url : http://www.baidu.com
             * invite_url :
             * rule : 1
             * rule_url :
             * images : ["http://hmsapi.zhuoranxiaoming.cn/image/share/shahzc_bg40.png","http://hmsapi.zhuoranxiaoming.cn/image/share/shahzc_bg41.png","http://hmsapi.zhuoranxiaoming.cn/image/share/shahzc_bg42.png","http://hmsapi.zhuoranxiaoming.cn/image/share/shahzc_bg43.png","http://hmsapi.zhuoranxiaoming.cn/image/share/shahzc_bg44.png"]
             * share_desc : 这段话要放在分享页面
             */

            private String logo;
            private String name;
            private String type;
            private String desc;
            private String title;
            private String msg;
            private String url;
            private String invite_url;
            private String rule;
            private String rule_url;
            private String share_desc;
            private ArrayList<String> images;

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getInvite_url() {
                return invite_url;
            }

            public void setInvite_url(String invite_url) {
                this.invite_url = invite_url;
            }

            public String getRule() {
                return rule;
            }

            public void setRule(String rule) {
                this.rule = rule;
            }

            public String getRule_url() {
                return rule_url;
            }

            public void setRule_url(String rule_url) {
                this.rule_url = rule_url;
            }

            public String getShare_desc() {
                return share_desc;
            }

            public void setShare_desc(String share_desc) {
                this.share_desc = share_desc;
            }

            public ArrayList<String> getImages() {
                return images;
            }

            public void setImages(ArrayList<String> images) {
                this.images = images;
            }
        }
    }
}
