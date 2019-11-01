package com.huiminsheng.app.bean;

import java.util.List;

public class BeanMyEarnings {

    /**
     * result : {"code":"10000","msg":"请求成功"}
     * link :
     * data : {"list":{"all_profit":"630.22","today_profit":"0.00","yesterday_profit":"0.00","channels":[{"name":"商品返佣","desc":"用户分享返佣","type":"31","images_url":"http://hms.zhuoranxiaoming.cn/public/mylist/user.png","price_profit":"200","link_url":"http://hms.zhuoranxiaoming.cn/index/profit_sharingbackcommission.html?type=31"},{"name":"商品和信用卡返佣","desc":"代理分享返佣","type":"33","images_url":"http://hms.zhuoranxiaoming.cn/public/mylist/agent.png","price_profit":"430.22","link_url":"http://hms.zhuoranxiaoming.cn/index/profit_sharingbackcommission.html?type=33"},{"name":"信用卡返佣","desc":"信用卡返佣","type":"39","images_url":"http://hms.zhuoranxiaoming.cn/public/mylist/xyk.png","price_profit":"0.00","link_url":"http://hms.zhuoranxiaoming.cn/index/profit_sharingbackcommission.html?type=39"}],"profit_url":"http://hms.zhuoranxiaoming.cn/wxother/profit_explain.html"}}
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
         * list : {"all_profit":"630.22","today_profit":"0.00","yesterday_profit":"0.00","channels":[{"name":"商品返佣","desc":"用户分享返佣","type":"31","images_url":"http://hms.zhuoranxiaoming.cn/public/mylist/user.png","price_profit":"200","link_url":"http://hms.zhuoranxiaoming.cn/index/profit_sharingbackcommission.html?type=31"},{"name":"商品和信用卡返佣","desc":"代理分享返佣","type":"33","images_url":"http://hms.zhuoranxiaoming.cn/public/mylist/agent.png","price_profit":"430.22","link_url":"http://hms.zhuoranxiaoming.cn/index/profit_sharingbackcommission.html?type=33"},{"name":"信用卡返佣","desc":"信用卡返佣","type":"39","images_url":"http://hms.zhuoranxiaoming.cn/public/mylist/xyk.png","price_profit":"0.00","link_url":"http://hms.zhuoranxiaoming.cn/index/profit_sharingbackcommission.html?type=39"}],"profit_url":"http://hms.zhuoranxiaoming.cn/wxother/profit_explain.html"}
         */

        private ListBean list;

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * all_profit : 630.22
             * today_profit : 0.00
             * yesterday_profit : 0.00
             * channels : [{"name":"商品返佣","desc":"用户分享返佣","type":"31","images_url":"http://hms.zhuoranxiaoming.cn/public/mylist/user.png","price_profit":"200","link_url":"http://hms.zhuoranxiaoming.cn/index/profit_sharingbackcommission.html?type=31"},{"name":"商品和信用卡返佣","desc":"代理分享返佣","type":"33","images_url":"http://hms.zhuoranxiaoming.cn/public/mylist/agent.png","price_profit":"430.22","link_url":"http://hms.zhuoranxiaoming.cn/index/profit_sharingbackcommission.html?type=33"},{"name":"信用卡返佣","desc":"信用卡返佣","type":"39","images_url":"http://hms.zhuoranxiaoming.cn/public/mylist/xyk.png","price_profit":"0.00","link_url":"http://hms.zhuoranxiaoming.cn/index/profit_sharingbackcommission.html?type=39"}]
             * profit_url : http://hms.zhuoranxiaoming.cn/wxother/profit_explain.html
             */

            private String all_profit;
            private String today_profit;
            private String yesterday_profit;
            private String profit_url;
            private List<ChannelsBean> channels;

            public String getAll_profit() {
                return all_profit;
            }

            public void setAll_profit(String all_profit) {
                this.all_profit = all_profit;
            }

            public String getToday_profit() {
                return today_profit;
            }

            public void setToday_profit(String today_profit) {
                this.today_profit = today_profit;
            }

            public String getYesterday_profit() {
                return yesterday_profit;
            }

            public void setYesterday_profit(String yesterday_profit) {
                this.yesterday_profit = yesterday_profit;
            }

            public String getProfit_url() {
                return profit_url;
            }

            public void setProfit_url(String profit_url) {
                this.profit_url = profit_url;
            }

            public List<ChannelsBean> getChannels() {
                return channels;
            }

            public void setChannels(List<ChannelsBean> channels) {
                this.channels = channels;
            }

            public static class ChannelsBean {
                /**
                 * name : 商品返佣
                 * desc : 用户分享返佣
                 * type : 31
                 * images_url : http://hms.zhuoranxiaoming.cn/public/mylist/user.png
                 * price_profit : 200
                 * link_url : http://hms.zhuoranxiaoming.cn/index/profit_sharingbackcommission.html?type=31
                 */

                private String name;
                private String desc;
                private String type;
                private String images_url;
                private String price_profit;
                private String link_url;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getImages_url() {
                    return images_url;
                }

                public void setImages_url(String images_url) {
                    this.images_url = images_url;
                }

                public String getPrice_profit() {
                    return price_profit;
                }

                public void setPrice_profit(String price_profit) {
                    this.price_profit = price_profit;
                }

                public String getLink_url() {
                    return link_url;
                }

                public void setLink_url(String link_url) {
                    this.link_url = link_url;
                }
            }
        }
    }
}
