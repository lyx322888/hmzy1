package com.huiminsheng.app.bean;

public class BeanOrder {

    /**
     * result : {"code":"10000","msg":"请求成功"}
     * link :
     * data : {"url":"alipay_sdk=alipay-sdk-php-20180705&app_id=2019040963866130&biz_content=%7B%22body%22%3A%22%5Cu8d2d%5Cu4e70%5Cu5546%5Cu54c1%22%2C%22subject%22%3A%22%5Cu5546%5Cu54c1+%5B%5Cu4e1c%5Cu98ce%5Cu98ce%5Cu5ea6MX5%5D%2C%5Cu6570%5Cu91cf1%2C%5Cu603b%5Cu4ef70.01%5Cu5143%5Cuff0c%5Cu5907%5Cu6ce8%5Cuff08%5Cuff09%5Cu3002%22%2C%22timeout_express%22%3A%2230m%22%2C%22out_trade_no%22%3A%22hms200000002120190513171449%22%2C%22total_amount%22%3A%220.01%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22cardAccount%22%3Anull%2C%22cardPassword%22%3Anull%7D&charset=UTF-8&format=JSON&method=alipay.trade.app.pay¬ify_url=http%3A%2F%2Fhmsapi.zhuoranxiaoming.cn%2Fulinepay%2FnotifyAli&sign_type=RSA2×tamp=2019-05-13+17%3A14%3A49&version=1.0&sign=axSAC6PH1A4Elk5ZoXZRSh7FS%2BE%2B8hGMNmC056LOfkjR4Ci22olTVFlbRUbKa6ogmlXzuhm4G8cYA6AK%2BGTKUEDWYujM25FSSqo4kw6vL2Qqh0S6Qcwi1z8NrOXqBW2YNVOpxa8Y62VoCJYC82x6kjarf%2BrNnC1IUXVSaXjLwCuTNu%2FdnZHaf6Nl5lrtZL8kIwOfDTNGQNankBHHPK%2FRxPNvqm2KCB2LrFV5IIvRer1JzB3Kd3AXIkRP3bB8C0k2l1SAfMhelsJMsHdPoIwA9vfGoWcJatD3cYiS%2FI2kGZF%2BZZGZ0ywVfgxPEGrE2So4baD6IyNamUH8Q2d6lJotsQ%3D%3D","price":"0.01","share_title":"扫一扫,轻松付款","share_desc":"惠民庄园:向您发起一笔金额为￥0.01的收款","sn":"hms200000002120190513171449"}
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
         * url : alipay_sdk=alipay-sdk-php-20180705&app_id=2019040963866130&biz_content=%7B%22body%22%3A%22%5Cu8d2d%5Cu4e70%5Cu5546%5Cu54c1%22%2C%22subject%22%3A%22%5Cu5546%5Cu54c1+%5B%5Cu4e1c%5Cu98ce%5Cu98ce%5Cu5ea6MX5%5D%2C%5Cu6570%5Cu91cf1%2C%5Cu603b%5Cu4ef70.01%5Cu5143%5Cuff0c%5Cu5907%5Cu6ce8%5Cuff08%5Cuff09%5Cu3002%22%2C%22timeout_express%22%3A%2230m%22%2C%22out_trade_no%22%3A%22hms200000002120190513171449%22%2C%22total_amount%22%3A%220.01%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22cardAccount%22%3Anull%2C%22cardPassword%22%3Anull%7D&charset=UTF-8&format=JSON&method=alipay.trade.app.pay¬ify_url=http%3A%2F%2Fhmsapi.zhuoranxiaoming.cn%2Fulinepay%2FnotifyAli&sign_type=RSA2×tamp=2019-05-13+17%3A14%3A49&version=1.0&sign=axSAC6PH1A4Elk5ZoXZRSh7FS%2BE%2B8hGMNmC056LOfkjR4Ci22olTVFlbRUbKa6ogmlXzuhm4G8cYA6AK%2BGTKUEDWYujM25FSSqo4kw6vL2Qqh0S6Qcwi1z8NrOXqBW2YNVOpxa8Y62VoCJYC82x6kjarf%2BrNnC1IUXVSaXjLwCuTNu%2FdnZHaf6Nl5lrtZL8kIwOfDTNGQNankBHHPK%2FRxPNvqm2KCB2LrFV5IIvRer1JzB3Kd3AXIkRP3bB8C0k2l1SAfMhelsJMsHdPoIwA9vfGoWcJatD3cYiS%2FI2kGZF%2BZZGZ0ywVfgxPEGrE2So4baD6IyNamUH8Q2d6lJotsQ%3D%3D
         * price : 0.01
         * share_title : 扫一扫,轻松付款
         * share_desc : 惠民庄园:向您发起一笔金额为￥0.01的收款
         * sn : hms200000002120190513171449
         */

        private String url;
        private String price;
        private String share_title;
        private String share_desc;
        private String sn;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getShare_title() {
            return share_title;
        }

        public void setShare_title(String share_title) {
            this.share_title = share_title;
        }

        public String getShare_desc() {
            return share_desc;
        }

        public void setShare_desc(String share_desc) {
            this.share_desc = share_desc;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }
    }
}
