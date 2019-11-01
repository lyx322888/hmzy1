package com.huiminsheng.app.bean;

import java.util.List;

public class BeanCollect {
    /**
     * result : {"code":"10000","msg":"请求成功"}
     * link :
     * data : {"list":[{"id":"36","mchid":"2000000001","goods_id":"3","shop_id":"","addtime":"1555655513","result":{"goods_id":"3","name":"大众捷达","desc":"2017款 1.5L 自动时尚型","is_special":"0","price":"12800.00","ori_price":"95000.00","prime":"2000.00","shop_id":"1000","Shopowner_price":"11000.00","logo":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/0ffcc76b6149b289d56c3a2ba4e09c25.png","images":["http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/aa05ab7c9cde7c2901dc0af06ee2ec8f.png","http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/302a9a979913986c0cadbae3b32b2c52.png","http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/8acee3925e62953056ffdc955c25b285.png","http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/c004dbacb8bea4faf971cef73ef6ec8e.png"],"class_id":"1","tag_name":"限时特价"}},{"id":"35","mchid":"2000000001","goods_id":"2","shop_id":"","addtime":"1555655506","result":{"goods_id":"2","name":"传祺GS42017款 235T 自动两驱精英版 235T 自动两驱精英版","desc":"2017款 政采版 2017款 政采版 2017款 政采版 2017款 政采版 传祺GS42017款 235T 自动两驱精英版 235T 自动两驱精英版","is_special":"0","price":"102879.00","ori_price":"144900.00","prime":"2000.00","shop_id":"1000","Shopowner_price":"99000.00","logo":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/89232092bd38b8965e23a14235a2a60c.png","images":["http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/8274f98e583b0154c17b9e31ce61dea0.png","http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/95fd6c4360d8e5504aa1d3b5d7976b75.png","http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/4f622adfdfe2a22addff625a0371b8be.jpg"],"class_id":"1","tag_name":"限时特价"}}],"count":"2","psize":"10"}
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
         * list : [{"id":"36","mchid":"2000000001","goods_id":"3","shop_id":"","addtime":"1555655513","result":{"goods_id":"3","name":"大众捷达","desc":"2017款 1.5L 自动时尚型","is_special":"0","price":"12800.00","ori_price":"95000.00","prime":"2000.00","shop_id":"1000","Shopowner_price":"11000.00","logo":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/0ffcc76b6149b289d56c3a2ba4e09c25.png","images":["http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/aa05ab7c9cde7c2901dc0af06ee2ec8f.png","http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/302a9a979913986c0cadbae3b32b2c52.png","http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/8acee3925e62953056ffdc955c25b285.png","http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/c004dbacb8bea4faf971cef73ef6ec8e.png"],"class_id":"1","tag_name":"限时特价"}},{"id":"35","mchid":"2000000001","goods_id":"2","shop_id":"","addtime":"1555655506","result":{"goods_id":"2","name":"传祺GS42017款 235T 自动两驱精英版 235T 自动两驱精英版","desc":"2017款 政采版 2017款 政采版 2017款 政采版 2017款 政采版 传祺GS42017款 235T 自动两驱精英版 235T 自动两驱精英版","is_special":"0","price":"102879.00","ori_price":"144900.00","prime":"2000.00","shop_id":"1000","Shopowner_price":"99000.00","logo":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/89232092bd38b8965e23a14235a2a60c.png","images":["http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/8274f98e583b0154c17b9e31ce61dea0.png","http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/95fd6c4360d8e5504aa1d3b5d7976b75.png","http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/4f622adfdfe2a22addff625a0371b8be.jpg"],"class_id":"1","tag_name":"限时特价"}}]
         * count : 2
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
             * id : 36
             * mchid : 2000000001
             * goods_id : 3
             * shop_id :
             * addtime : 1555655513
             * result : {"goods_id":"3","name":"大众捷达","desc":"2017款 1.5L 自动时尚型","is_special":"0","price":"12800.00","ori_price":"95000.00","prime":"2000.00","shop_id":"1000","Shopowner_price":"11000.00","logo":"http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/0ffcc76b6149b289d56c3a2ba4e09c25.png","images":["http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/aa05ab7c9cde7c2901dc0af06ee2ec8f.png","http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/302a9a979913986c0cadbae3b32b2c52.png","http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/8acee3925e62953056ffdc955c25b285.png","http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/c004dbacb8bea4faf971cef73ef6ec8e.png"],"class_id":"1","tag_name":"限时特价"}
             */

            private String id;
            private String mchid;
            private String goods_id;
            private String shop_id;
            private String addtime;
            private ResultBeanX result;

            public boolean isChoose = false; //是否选择

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMchid() {
                return mchid;
            }

            public void setMchid(String mchid) {
                this.mchid = mchid;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getShop_id() {
                return shop_id;
            }

            public void setShop_id(String shop_id) {
                this.shop_id = shop_id;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public ResultBeanX getResult() {
                return result;
            }

            public void setResult(ResultBeanX result) {
                this.result = result;
            }

            public static class ResultBeanX {
                /**
                 * goods_id : 3
                 * name : 大众捷达
                 * desc : 2017款 1.5L 自动时尚型
                 * is_special : 0
                 * price : 12800.00
                 * ori_price : 95000.00
                 * prime : 2000.00
                 * shop_id : 1000
                 * Shopowner_price : 11000.00
                 * logo : http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/0ffcc76b6149b289d56c3a2ba4e09c25.png
                 * images : ["http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/aa05ab7c9cde7c2901dc0af06ee2ec8f.png","http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/302a9a979913986c0cadbae3b32b2c52.png","http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/8acee3925e62953056ffdc955c25b285.png","http://hmsadmin.zhuoranxiaoming.cn/uploads/20190404/c004dbacb8bea4faf971cef73ef6ec8e.png"]
                 * class_id : 1
                 * tag_name : 限时特价
                 */

                private String goods_id;
                private String name;
                private String desc;
                private String is_special;
                private String price;
                private String ori_price;
                private String prime;
                private String shop_id;
                private String Shopowner_price;
                private String logo;
                private String class_id;
                private String tag_name;
                private List<String> images;

                public String getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(String goods_id) {
                    this.goods_id = goods_id;
                }

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

                public String getIs_special() {
                    return is_special;
                }

                public void setIs_special(String is_special) {
                    this.is_special = is_special;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getOri_price() {
                    return ori_price;
                }

                public void setOri_price(String ori_price) {
                    this.ori_price = ori_price;
                }

                public String getPrime() {
                    return prime;
                }

                public void setPrime(String prime) {
                    this.prime = prime;
                }

                public String getShop_id() {
                    return shop_id;
                }

                public void setShop_id(String shop_id) {
                    this.shop_id = shop_id;
                }

                public String getShopowner_price() {
                    return Shopowner_price;
                }

                public void setShopowner_price(String Shopowner_price) {
                    this.Shopowner_price = Shopowner_price;
                }

                public String getLogo() {
                    return logo;
                }

                public void setLogo(String logo) {
                    this.logo = logo;
                }

                public String getClass_id() {
                    return class_id;
                }

                public void setClass_id(String class_id) {
                    this.class_id = class_id;
                }

                public String getTag_name() {
                    return tag_name;
                }

                public void setTag_name(String tag_name) {
                    this.tag_name = tag_name;
                }

                public List<String> getImages() {
                    return images;
                }

                public void setImages(List<String> images) {
                    this.images = images;
                }
            }
        }
    }
}
