package com.huiminsheng.app.net;


import android.os.Environment;

//请求地址
public class ApiUrls {
        //请求地址头部
        public static String UrlHead  = "http://hmsapi.zhuoranxiaoming.cn/";
        //微信请求
        public static String WXURl = "https://api.weixin.qq.com/sns/oauth2/access_token";
        //微信获取用户信息
        public static String WXLOGIN = "https://api.weixin.qq.com/sns/userinfo";
        /**
         * 常用字段
         */
        public static final class KEY{
                //账户电话
                public static final String PHONE = "phone";
                //登录状态
                public static final String LOGGING_STATUS = "logging_status";
                //是否第一次登录
                public static final String FIRST = "first";
                //密码
                public static final String PSW = "password";
                //用户id
                public static final String MCHID = "mchid";
                //微信id
                public static final String WXID = "wxid";
                //头像
                public static final String HEADPIC = "headpic";
                //昵称
                public static final String NICK_NAME = "nick_name";
                //姓名
                public static final String NAME = "name";
                //等级
                public static final String LEVELNAME = "levelname";
                //token
                public static final String ACCESS_TOKEN = "access_token";
                //保存地址
                public static  final String PATH_SAVE= Environment.getExternalStorageDirectory() + "/hmzy/";//.
                //refresh
                public static final String REFRESH = "refresh";
        }


        /*首页模块*/
        public static final class HOME{
                /*首页菜单以及头部banner  get*/
                public static String HONMEBANNER = UrlHead+"goods/indexList";
                /*首页福利专区  get*/
                public static String HAPPTEMPLATEOME  = UrlHead+"goods/appTemplate";
                /*首页在售车型  get*/
                public static String SELLCAR  = UrlHead+"goods/getall";
                /*排行榜GET*/
                public static String RANKINGLIST  = UrlHead+"/rankinglist/rankinglist";

                //
        }

        //商品
        public static final class COMMODITY{
                //筛选
                public static String GOODSCLASS  = UrlHead+"goods/goodsclass";
                //列表
                public static String GOODLIST  = UrlHead+"goods/goodlist";
                //商品详情 post
                public static String DETAILS  = UrlHead+"goods/getDetails";
                //收藏
                public static String COLLECTION  = UrlHead+"goods/Collection";
                //客服二维码
                public static String CONTACT  = UrlHead+"account/contact";
                //我的收藏
                public static String MYCOLLECTION  = UrlHead+"goods/MyCollection";
                //删除我的收藏
                public static String DELETECOLLECTION  = UrlHead+"goods/deleteCollection";
                //选择门店地址
                public static String STORES  = UrlHead+"goods/stores";
                //用户直接下单
                public static String ORDER  = UrlHead+"goods/order";
                // 用户结算订单 http://hmsapi.zhuoranxiaoming.cn/goods/calture?PRO=1&app_version=1.0.0&mchid=2000000216&oemid=90005&order_sn=hms200000021620190507163149&os=2
                public static String CALTURE  = UrlHead+"goods/calture";
                //确认订单
//                public static final String CONFIRMORDERS = UrlHead + "goods/confirm";
                //订单详情
                public static final String GETORDERINFO = UrlHead + "goods/getOrderInfo";
                //我的订单
                public static final String GETORDERS = UrlHead + "goods/getOrders";
                //取消订单
                public static final String CANCELORDERS = UrlHead + "goods/cancel";
                //移除订单
                public static final String REMOVEORDERS = UrlHead + "goods/remove";
        }

        /**
         * 登录模块
         */
        public static final class LOGIN{
                /**
                 *登录
                 */
                public static String LOGIN  = UrlHead+"merchant/login";
                /**
                 * 判断平台是否已有账号绑定过微信号
                 */
                public static final String JUDGE_BINDING_WX = UrlHead + "merchant/isbindwx";

                /**
                 * 绑定界面发送短信
                 * http://hmsapi.zhuoranxiaoming.cn/sms/regsms?oemid=90005&mchid=2000000004&os=3&mobile=15980991196
                 */
                public static final String REGSMS = UrlHead + "sms/regsms";
                /**
                 * 绑定手机号
                 * http://hmsapi.zhuoranxiaoming.cn/password/bind?mchid=2000000004&oemid=90005&os=2&password=123456&mobile=15980991196&code=3495
                 */
                public static final String BIND = UrlHead + "password/bind";
                /**
                 * 修改登录密码
                 */
                public static final String MODIFY_LOGIN_PWD = UrlHead + "merchant/changepwd";
                /**
                 * 1.1.5 重设密码验证码下发
                 */
                public static final String CODE_RETRIEVE = UrlHead + "sms/resetpwdsms";
                /**
                 * 1.1.4 重设密码
                 */
                public static final String RETRIEVE =UrlHead + "merchant/resetpwd";
                /**
                 * 1.1.6 版本更新
                 */
                public static final String UPGRADE = UrlHead + "version/upgrade";

        }

        /**
         * 我的模块
         */
        public static final class MY{
                /*我的 get*/
                public static String GETCOUNTS  = UrlHead+"goods/getCounts";
                /*头像相关 get*/
                public static String INFO  = UrlHead+"merchant/info";
                //联系我们
                public static final String CONNETIONUS = UrlHead + "account/contact";
                /**
                 * 消息中心
                 * http://hmsapi.zhuoranxiaoming.cn/msg/getMsgHeader?oemid=90005&mchid=2000000004&os=2&p=1&psize=10
                 */
                public static final String GETMSGHEADER = UrlHead + "msg/getMsgHeader";
                /**
                 * 消息详情
                 * http://hmsapi.zhuoranxiaoming.cn/msg/jList?oemid=90005&mchid=2000000004&os=2&p=1&psize=10&type=13
                 */
                public static final String JLIST = UrlHead + "msg/jList";
                //上传二维码 wxcode
                public static final String UPWXCODE = UrlHead + "merchant/upWxCode";
                //支付宝
                public static final String SETALIACCOUNT = UrlHead + "Merchant/setAliAccount";
                //选择银行列表
                public static final String BANKLIST = UrlHead + "bank/bankList";
                //添加银行卡
                public static final String BANKADD = UrlHead + "bank/bankAdd";
                //我的银行卡 http://hmsapi.zhuoranxiaoming.cn/bank/myBank?mchid=2000000004
                public static final String MYBANK = UrlHead + "bank/myBank";
                /**
                 * 设置支付密码
                 */
                public static final String SET_PAY_PWD = UrlHead + "merchant/settradepwd";
                /**
                 * 忘记支付密码  短信验证
                 */
                public static final String FORGET_PWD_SMS = UrlHead + "sms/resettradepwdsms";
                /**
                 * 重置交易密码
                 */
                public static final String FORGET_PAY_PWD = UrlHead + "sms/resettradepwd";

                //提现 http://hmsapi.zhuoranxiaoming.cn/Account/splitCash?oemid=90005&mchid=2000000004&os=3&money=20
                public static final String SPLITCASH = UrlHead + "Account/splitCash";
                //提现记录 http://hmsapi.zhuoranxiaoming.cn/Account/cashList?oemid=90005&mchid=2000000004&os=3&type=-2
                public static final String CASHLIST = UrlHead + "Account/cashList";
                //我的收益 http://hmsapi.zhuoranxiaoming.cn/account/myIncome?oemid=90005&mchid=2000000004&os=3
                public static final String ACCOUNT = UrlHead + "account/myIncome";

                /**
                 * 验证输入的支付密码  trade_pwd
                 */
                public static final String VERIFY_PAY_PWD = UrlHead + "merchant/checktradepwd";
                /**
                 * 返佣
                 * http://hmsapi.zhuoranxiaoming.cn/account/splitList?oemid=90005&mchid=2000000004&os=3&p=1&psize=10&type=33&month=2019-04&date=1555991119916
                 */
                public static final String SPLITLIST = UrlHead + "account/splitList";
                /**
                 * 我的团队
                 */
                public static final String CHILDLEVELSCOUNT = UrlHead + "merchant/childLevelsCount";
                /**
                 * 我的团队 明细
                 */
                public static final String CHILDINFOLIST = UrlHead + "merchant/childInfoList";


        }

        /**
         * 分享模块
         */
        public static final class SHARE{
                /**
                 * 分享
                 */
                public static final String SHARE = UrlHead + "version/getshare";//version/getShareMsg
                /**
                 * 文案下载 图文推广
                 */
                public static final String SHARE_DOWNLOAD = UrlHead + "version/getDocuments";
                /**
                 * 文案下载数量统计
                 */
                public static final String DOWN_COUNT = UrlHead + "version/documentsCount";

        }
}
