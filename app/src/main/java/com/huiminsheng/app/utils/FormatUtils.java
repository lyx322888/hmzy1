package com.huiminsheng.app.utils;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.Context;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 作者：hongkl
 * 时间：2018/6/16
 * the name of the current project：ch_android_project
 */

public class FormatUtils {

    /**
     * 实现文本复制功能
     */
    @TargetApi(11)
    public static void copy(Context context, String str, String toast) {
        if (Build.VERSION.SDK_INT > 11) {
            android.content.ClipboardManager c = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            c.setPrimaryClip(ClipData.newPlainText("order", str));
            ToastUtils.showToast(context,toast);
        } else {
            android.text.ClipboardManager c = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            c.setText(str);
            ToastUtils.showToast(context,toast);
        }
    }

    /**
     * 实现文本复制功能
     */
    @TargetApi(11)
    public static void copy(Context context, String str) {
        copy(context, str, "已复制");
    }

    /**
     * .可使用官方提供的DateUtils工具类
     * yyyy-MM-dd
     * 将获取到的php的long时间格式用String转成Java的long时间格式,再输出合适的String类型时间
     * php为10位,Java为13位
     */
    public static String getTime2S(long time) {
        if (0 == time)
            return "";
        String str = time + "000";
        long timeLong = Long.valueOf(str).longValue();
        Date date = new Date(timeLong);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * yyyy-MM-dd
     * 将获取到的php的long时间格式用String转成Java的long时间格式,再输出合适的String类型时间
     * php为10位,Java为13位
     */
    public static String getTime2S(String time) {
        if (TextUtils.isEmpty(time))
            return "";
        String str = time + "000";
        long timeLong = Long.valueOf(str).longValue();
        Date date = new Date(timeLong);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * 将str类型的时间戳1466757568193转成2016-6-24 16:39:18
     */
    public static String getTime(String time) {
        try {
            long timeLong = Long.valueOf(time).longValue();
            Date date = new Date(timeLong);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(date);
        } catch (NumberFormatException e) {
            return "时间异常";
        }
    }

    /**
     * 将获取到的long时间格式加工传给php服务端
     */
    public static String setTimePhp(long time) {
        if (0 == time || time < 999)
            return "";
        String str = time + "";
        str = str.substring(0, str.length() - 3);
        return str;
    }

    /**
     * 将获取到的long时间格式加工传给php服务端
     */
    public static String setTimePhp(String time) {
        if (TextUtils.isEmpty(time) || time.length() <= 3)
            return "";
        time = time.substring(0, time.length() - 3);
        return time;
    }

    /**
     * (包含 时分秒)yyyy-MM-dd HH:mm:ss
     * 将获取到的php的long时间格式用String转成Java的long时间格式,再输出合适的String类型时间
     * php为10位,Java为13位
     */
    public static String getTime2SH(long time) {
        if (0 == time)
            return "";
        String str = time + "000";
        long timeLong = Long.valueOf(str).longValue();
        Date date = new Date(timeLong);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * (包含 时分秒)yyyy-MM-dd HH:mm:ss
     * 将获取到的php的long时间格式用String转成Java的long时间格式,再输出合适的String类型时间
     * php为10位,Java为13位
     */
    public static String getTime2SH(String time) {
        if (TextUtils.isEmpty(time))
            return "";
        String str = time + "000";
        long timeLong = Long.valueOf(str).longValue();
        Date date = new Date(timeLong);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 将long类型时间转为String类型  yyyy-MM-dd
     */
    public static String timeL2S(long time) {
        if (0 == time)
            return "";
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * 将时间选择对话框获取到的未处理的int类型年/月/日转成long类型  yyyy-MM-dd
     */
    public static Long timeI2L(int year, int monthOfYear, int dayOfMonth) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = sdf.parse(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            return parse.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return (long) 0;
        }
    }

    /**
     * 将传入的价格加上'￥'输出
     */
    public static String money(String price) {
        return TextUtils.isEmpty(price) || "null".equals(price) || "0".equals(price) ? "￥0.00" : '￥' + price;
    }

    /**
     * 将传入的价格加上'￥'输出
     */
    public static String getMoney(String price) {
        return TextUtils.isEmpty(price) || "null".equals(price) || "0".equals(price) ? "￥0.00" : '￥' + price;
    }

    /**
     * 将传入的价格用中文输出（如：3万）
     * 注意int长度限制
     */
    public static String getMoney2Chinese(String price) {
        int i = 0;//金额
        try {
            i = Double.valueOf(price).intValue();
        } catch (NumberFormatException e) {
            return "0元";
        }
        int j = 0;//下标
        if (i <= 0) return "0元";
        if (i == 10) return "10元";
        StringBuilder sb = new StringBuilder();
        while (i > 0) {
            if (i % 10 > 0) {
                if (j == 2) {
                    sb.append("百" + (i % 10));

                } else if (j == 3) {
                    sb.append("千" + (i % 10));

                } else if (j > 4) {
                    sb.append((i % 10));
                }
            }
            if (j == 4) {
                sb.append("万" + (i % 10));
            }
            j++;
            i /= 10;
        }
        return sb.reverse().toString();
    }

    /**
     * 检查输入的文字,按价格输出,不带'￥'
     */
    public static String moneyNoSymbol(String price) {
        return TextUtils.isEmpty(price) || "null".equals(price) || "0".equals(price) ? "0.00" : price;
    }

    /**
     * 输出前先判断是否为空,再输出
     */
    public static String getText(String str) {
        return TextUtils.isEmpty(str) || "null".equals(str) ? "" : str;
    }

    /**
     * 确保获取的String能转成int
     */
    public static int getInt(String str) {
        return getInt(str, 0);

    }

    /**
     * 确保获取的String能转成int
     */
    public static int getInt(String str, int defaultInt) {
        try {
            return Integer.valueOf(str).intValue();
        } catch (NumberFormatException e) {
            return defaultInt;
        }

    }

    /**
     * 输出前先判断是否为空,再输出
     */
    public static String getTextOrNum(String str) {
        return TextUtils.isEmpty(str) || "null".equals(str) || "0".equals(str) ? "" : str;
    }

    /**
     * 将传入的图片路径加上前缀
     */
    public static String getImageUrl(String str) {
//		return TextUtils.isEmpty(str)||"null".equals(str)?"":Port.PATH_IMAGE+str;
        return TextUtils.isEmpty(str) || "null".equals(str) ? "" : str;
    }

    /**
     * 传入的数据如果为空,则转为0输出
     */
    public static String getInt0(String str) {
        return TextUtils.isEmpty(str) || "null".equals(str) ? "0" : str;
    }

    /**
     * 显示驼峰价格格式,前面加货币符号￥
     */
    public static void setPriceaddSymbol(TextView textview, String price) {
        if (TextUtils.isEmpty(price) || "null".equals(price)) {
            price = "￥0.01";
        } else {
            price = "￥" + price;
        }
        int indexOf1 = price.indexOf("￥");
        int indexOf2 = price.indexOf(".");
        if (indexOf2 == -1) {
            price = price + ".00";
            indexOf2 = price.indexOf(".");
        }
        SpannableString builder = new SpannableString(price);
        builder.setSpan(new AbsoluteSizeSpan(ToolsSize.getSizeBySp(18)), indexOf1 + 1, indexOf2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textview.setText(builder);
    }

    /**
     * 限定double格式小数点及小数点之后是否有数字根据实际情况显示
     */
    public static String getPriceFormat(double price) {
        String value = getPriceFormat00(price);
        int indexOf = value.indexOf('.');
//		Log.e("", indexOf+"金额"+value);
        if (indexOf >= 0) {
            int last = value.length() - 1;
            if ('0' == value.charAt(last)) {
                int last2 = last - 1;
                if ('0' == value.charAt(last2)) {
                    value = value.substring(0, last2 - 1);
                } else {
                    value = value.substring(0, last);
                }
            }
        }
        return value;
    }

    /**
     * 限定double格式小数点之后有两位
     */
    public static String getPriceFormat00(double price) {
        DecimalFormat df = new DecimalFormat("0.00");
//			Log.e("", price+"___"+df.format(price));
        return df.format(price);
    }

    /**
     * 显示价格
     */
    public static void setPrice(TextView textview, double price) {
        String money = "￥" + getPriceFormat(price);
        int indexOf1 = money.indexOf("￥");
        int indexOf2 = money.indexOf(".");
        if (indexOf2 == -1) {
            indexOf2 = money.length();
        }
        SpannableString builder = new SpannableString(money);
        builder.setSpan(new AbsoluteSizeSpan(ToolsSize.getSizeBySp(18)), indexOf1 + 1, indexOf2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textview.setText(builder);
    }

    /**
     * 显示价格
     */
    public static void setPrice(TextView textview, String price) {
        int indexOf1 = price.indexOf("￥");
        int indexOf2 = price.indexOf(".");
        if (indexOf2 == -1) {
            indexOf2 = price.length();
        }
        SpannableString builder = new SpannableString(price);
        builder.setSpan(new AbsoluteSizeSpan(ToolsSize.getSizeBySp(18)), indexOf1 + 1, indexOf2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textview.setText(builder);
    }

    /**
     * 验证姓名
     */
    public static boolean isName(String name) {
        return !Pattern.matches("(([\u4E00-\u9FA5.•]{2,10})|([a-zA-Z]{3,10}))", name);
    }

    /**
     * 验证身份证
     */
    public static boolean isCode(String code) {
        return !(Pattern.matches("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])", code));
    }

    /**
     * 验证手机号码
     */
    public static boolean isPhoneNum(String Phonenum) {
        return !(Pattern.matches("1([\\d]{10})|((\\+[0-9]{2,4})?\\(?[0-9]+\\)?-?)?[0-9]{7,8}", Phonenum));
    }

    /**
     * 是否大陆手机
     * @return
     */
//    public static boolean isChinaPhoneLegal(String mobiles) {
//        /*
//        移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
//        联通：130、131、132、152、155、156、185、186
//        电信：133、153、180、189、（1349卫通）
//        总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
//        */
//        String telRegex = "[1]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
//        if (TextUtils.isEmpty(mobiles)) return false;
//        else return mobiles.matches(telRegex);
//    }


    public static boolean isChinaPhoneLegal(String str)
            throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(166)|(17[0-8])|(18[0-9])|(19[8-9])|(147,145))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 匹配手机号的规则：[3578]是手机号第二位可能出现的数字
     */
    public static final String REGEX_MOBILE = "^[1][23456789][0-9]{9}$";

    /**
     * 校验手机号
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * 限定价格输入editText的输入条件
     *
     * @param s        editText在onTextChanged方法中的CharSequence s
     * @param editText 当前editMoney.isFocused()为true的editText控件
     */
    public static void edit(CharSequence s, EditText editText) {
        if (s.toString().contains(".")) {
            if (s.length() - 1 - s.toString().indexOf(".") > 2) {//小数点之后只能输入两位
                s = s.subSequence(0, s.toString().indexOf(".") + 3);
                editText.setText(s);
                editText.setSelection(s.length());
            }
        }
        if (s.length() > 0 && s.charAt(0) == '.') {//小数点前至少有一位
            editText.setText("0" + s);
            editText.setSelection(s.length() + 1);
        }
        if (s.length() > 1 && s.charAt(0) == '0' && s.charAt(1) != '.') {//大于1的数前面不能有0
            editText.setText(s.subSequence(1, s.length()));
            editText.setSelection(s.length() - 1);
        }
    }

    public static String formatPhone(String mobiles){
        if (isChinaPhoneLegal(mobiles)){
            String maskNumber = mobiles.substring(0,3)+"****"+mobiles.substring(7,mobiles.length());
            return maskNumber;
        }else {
            return mobiles;
        }
    }

}
