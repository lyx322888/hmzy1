package com.huiminsheng.app.bean;

public class BeanUpApp {
    /**
     * result : {"code":"10000","msg":"请求成功"}
     * link :
     * data : {"is_update":"0"}
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
        private String id;
        private String os;
        private String version_code;
        private String version_name;
        private String version_desc;
        private String down_url;
        private String is_update;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOs() {
            return os;
        }

        public void setOs(String os) {
            this.os = os;
        }

        public String getVersion_code() {
            return version_code;
        }

        public void setVersion_code(String version_code) {
            this.version_code = version_code;
        }

        public String getVersion_name() {
            return version_name;
        }

        public void setVersion_name(String version_name) {
            this.version_name = version_name;
        }

        public String getVersion_desc() {
            return version_desc;
        }

        public void setVersion_desc(String version_desc) {
            this.version_desc = version_desc;
        }

        public String getDown_url() {
            return down_url;
        }

        public void setDown_url(String down_url) {
            this.down_url = down_url;
        }

        public String getIs_update() {
            return is_update;
        }

        public void setIs_update(String is_update) {
            this.is_update = is_update;
        }
    }
}
