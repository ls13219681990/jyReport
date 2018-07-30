/**
 *
 */
package com.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author 明兴网络
 */
public class CommonMethod {

    // 常量
    /**
     * 道路
     */
    public final static Integer RUSHHOUR_BELONG_ROAD = 1;
    /**
     * 路段
     */
    public final static Integer RUSHHOUR_BELONG_ROADSECTION = 2;
    /**
     * 路口
     */
    public final static Integer RUSHHOUR_BELONG_CROSSING = 3;

    /**
     * 获取当前时间
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getDate() {
        String returnStr = null;
        returnStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return returnStr;
    }

    /**
     * 获取当前时间戳yyyyMMddHHmmssSSS
     *
     * @return
     */
    public static String getCurrentSystemDate() {
        String returnStr = null;
        returnStr = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        return returnStr;
    }

    /**
     * 获取当前时间数字yyMMdd（6位）的16进制字符串5位
     *
     * @return
     */
    public static String getCurrentYMD() {
        String returnStr = null;
        returnStr = new SimpleDateFormat("yyMMdd").format(new Date());
        return Integer.toHexString(Integer.parseInt(returnStr));
    }

    /**
     * 获取当前时间
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static Date getTime() {
        String returnStr = null;
        returnStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        // System.out.println(returnStr);
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(returnStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取当前时间
     *
     * @return yyyy-MM-dd
     */
    public static String getCurrentDate() {
        String returnStr = null;
        returnStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        // System.out.println(returnStr);
        try {
            return returnStr;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取当前月
     *
     * @return yyyy-MM
     */
    public static String getCurrentMonth() {
        String returnStr = null;
        returnStr = new SimpleDateFormat("yyyy-MM").format(new Date());
        try {
            return returnStr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取当前年
     *
     * @return yyyy
     */
    public static String getCurrentYear() {
        String returnStr = null;
        returnStr = new SimpleDateFormat("yyyy").format(new Date());
        try {
            return returnStr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取当前时间
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public final static Timestamp getTimeStamp() {
        Timestamp dateTime = new Timestamp(getTime().getTime());// Timestamp类型,timeDate.getTime()返回一个long型
        return dateTime;
    }

    /**
     * 将时间转换为字段
     *
     * @param s
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String s) {
        try {
            if (s == null)
                s = "";
            return !s.trim().equals("") ? new SimpleDateFormat("yyyy-MM-dd").parse(s) : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * method 将字符串类型的日期转换为一个timestamp（时间戳记java.sql.Timestamp）
     *
     * @param dateString 需要转换为timestamp的字符串 yyyy-MM-dd kk:mm:ss.SSS
     * @return dataTime timestamp
     */
    public final static Timestamp string2Time(String dateString) {
        try {
            DateFormat dateFormat;
            dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss.SSS", Locale.ENGLISH);// 设定格式
            // dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss",
            // Locale.ENGLISH);
//			dateFormat.setLenient(false);
            Date timeDate = (Date) dateFormat.parse(dateString);// util类型
            Timestamp dateTime = new Timestamp(timeDate.getTime());// Timestamp类型,timeDate.getTime()返回一个long型
            return dateTime;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }

    /**
     * method 将字符串类型的日期转换为一个timestamp（时间戳记java.sql.Timestamp）
     *
     * @param dateString 需要转换为timestamp的字符串yyyy-MM-dd kk:mm:ss
     * @return dataTime timestamp
     */
    public final static Timestamp string2Time1(String dateString) {
        try {
//			logger.debug("日期0: "+dateString);
//			DateFormat dateFormat;
//			dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.ENGLISH);// 设定格式
            if (!dateString.contains(":")) {
                dateString += " 00:00:00";
            }
            Date dt = new Date();
            String[] parts = dateString.replace(" ", "-").replace(":", "-").replace(".", "-").split("-");

            if (parts.length >= 3) {
                int years = Integer.parseInt(parts[0]);
                int months = Integer.parseInt(parts[1]) - 1;
                int days = Integer.parseInt(parts[2]);
                int hours = Integer.parseInt(parts[3]);
                int minutes = Integer.parseInt(parts[4]);
                int seconds = Integer.parseInt(parts[5]);

                GregorianCalendar gc = new GregorianCalendar(years, months,
                        days, hours, minutes, seconds);

                dt = gc.getTime();
            }
//			return dt;
            // dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss",
            // Locale.ENGLISH);
//			dateFormat.setLenient(false);
//			java.util.Date timeDate = dateFormat.parse(dateString);// util类型
//			java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());// Timestamp类型,timeDate.getTime()返回一个long型
            Timestamp dateTime = new Timestamp(dt.getTime());// Timestamp类型,timeDate.getTime()返回一个long型
            return dateTime;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }

    /**
     * yyyy-MM-dd 00:00:00
     *
     * @param submitTimeFrom
     * @return
     */
    public static Timestamp toStartTime(Timestamp submitTimeFrom) {
        String start = timeToString(submitTimeFrom);
        start += " 00:00:00";
        return string2Time1(start);
    }

    /**
     * yyyy-MM-dd 23:59:59
     *
     * @param submitTimeFrom
     * @return
     */
    public static Timestamp toEndTime(Timestamp submitTimeFrom) {
        String end = timeToString(submitTimeFrom);
        end += " 23:59:59";
        return string2Time1(end);
    }

    /**
     * yyyy-MM-dd HH:mm:ss method 将字符串类型的日期转换为一个Date（java.sql.Date）
     *
     * @param //dateString 需要转换为Date的字符串
     * @return dataTime Date
     */
    public final static Date string2Date(String s) {
        try {
            if (s == null)
                s = "";
            return !s.trim().equals("") ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s) : null;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }


    /**
     * @param d yyyy-MM-dd
     * @return yyyy-MM-dd
     */
    public static String dateToString(Date d) {
        String result = null;
        if (d != null) {
            SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            result = bartDateFormat.format(d);
        }
        return result;
    }

    /**
     * @param d yyyy-MM-dd HH:mm:ss
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String dateToString2(Date d) {
        String result = null;
        if (d != null) {
            SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            result = bartDateFormat.format(d);
        }
        return result;
    }

    /**
     * @param t yyyy-MM-dd
     * @return yyyy-MM-dd
     */
    public static String timeToString(Timestamp t) {
        Date d = new Date(t.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        String dStr = dateToString(cal.getTime());
        return dStr;
    }

    /**
     * @param t yyyy-MM-dd HH:mm:ss
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String timeToString2(Timestamp t) {
        Date d = new Date(t.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        String dStr = dateToString2(cal.getTime());
        return dStr;
    }

    /**
     * @param input
     * @return 空返回true，非空返回false
     */
    public static boolean isNull(String input) {
        if (input != null && !input.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 时间增加n天
     *
     * @param s 初始时间
     * @param n 增加天数
     * @return
     */
    public static String addDay(String s, int n) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Calendar cd = Calendar.getInstance();
            cd.setTime(sdf.parse(s));
            cd.add(Calendar.DATE, n);// 增加一天
            // cd.add(Calendar.MONTH, n);//增加一个月
            return sdf.format(cd.getTime());
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 时间增加n月
     *
     * @param s 初始时间
     * @param n 增加月数
     * @return
     */
    public static String addMonth(String s, int n) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Calendar cd = Calendar.getInstance();
            cd.setTime(sdf.parse(s));
//			cd.add(Calendar.DATE, n);// 增加一天
            cd.add(Calendar.MONTH, n);//增加一个月
            return sdf.format(cd.getTime());
        } catch (Exception e) {
            return null;
        }

    }


    /**
     * 时间增加n年
     *
     * @param s 初始时间
     * @param n 增加天数
     * @return
     */
    public static String addYear(String s, int n) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Calendar cd = Calendar.getInstance();
            cd.setTime(sdf.parse(s));
            cd.add(Calendar.YEAR, n);// 增加一年
            // cd.add(Calendar.MONTH, n);//增加一个月
            return sdf.format(cd.getTime());
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 时间增加n天
     *
     * @param s 初始时间
     * @param n 增加天数
     * @return
     */
    public static Timestamp addDay(Timestamp s, int n) {
        try {

            Calendar cd = Calendar.getInstance();
            cd.setTime(s);
            cd.add(Calendar.DATE, n);// 增加一天
            // cd.add(Calendar.MONTH, n);//增加一个月
            return string2Time(dateToString(cd.getTime()) + " 00:00:00.0");
        } catch (Exception e) {
            return null;
        }

    }

    public static String addHour(String s, int n) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            if (!s.contains(":")) {
                s += " 00:00:00";
            }

            Calendar cd = Calendar.getInstance();
            cd.setTime(sdf.parse(s));
            cd.add(Calendar.HOUR, n);// 增加一小时
            // cd.add(Calendar.MONTH, n);//增加一个月
            return sdf.format(cd.getTime());
        } catch (Exception e) {
            return null;
        }

    }

    public static Timestamp addHour(Timestamp s, int n) {
        try {

            Calendar cd = Calendar.getInstance();
            cd.setTime(s);
            cd.add(Calendar.HOUR, n);// 增加一天
            // cd.add(Calendar.MONTH, n);//增加一个月
            return string2Time1(dateToString2(cd.getTime()));
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 计算两个时间相差？小时
     *
     * @param startT
     * @param endT
     * @return
     */
    public static Long diffHour(Timestamp startT, Timestamp endT) {

        long l = endT.getTime() - startT.getTime();
        long hour = (l / (60 * 60 * 1000));

        return hour;
    }

    /**
     * 计算两个时间相差？分
     *
     * @param startT
     * @param endT
     * @return
     */
    public static Long diffMin(Timestamp startT, Timestamp endT) {

        long l = endT.getTime() - startT.getTime();
        long min = l / (60 * 1000);

        return min;
    }

    /**
     * 计算两个时间相差？秒
     *
     * @param startT
     * @param endT
     * @return
     */
    public static Long diffSec(Timestamp startT, Timestamp endT) {

        long l = endT.getTime() - startT.getTime();
        long min = l / (1000);

        return min;
    }

    public static List getSelectList(String listName) {
        HashMap<String, List> hs = new HashMap<String, List>();
        if (hs.get(listName) == null) {
            setSelectList(listName);
        }
        return hs.get(listName);
    }

    private static void setSelectList(String listName) {

    }

    /**
     * @param hj  被加数字，会被修改
     * @param obj 加数 不会被修改
     * @return 和
     */
    public static BigDecimal getSumByDecimal(BigDecimal hj, Object obj) {
        if (obj != null && !obj.equals("")) {
            hj = hj.add(new BigDecimal(obj.toString()));
        }
        return hj;
    }

    /**
     * 验证是否整数
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static List copyObject(List target, List source) {
        for (int i = 0; i < source.size(); i++) {
            target.add(source.get(i));
        }
        return target;
    }

    public static String getClassFields(Object msg) {
        try {
            java.lang.reflect.Field[] flds = msg.getClass().getDeclaredFields();
            String returnValue = "";
            if (flds != null) {
                for (int i = 0; i < flds.length; i++) {
                    String getMethod = "get" + flds[i].getName().substring(0, 1).toUpperCase() + flds[i].getName().substring(1);
                    Class[] methodParam = null;
                    Object[] params = {};
                    Object retValue = null;
                    // 这里就是调用Bean的get方法，很爽哦，适合写在基类里！！！
                    retValue = msg.getClass().getMethod(getMethod, methodParam).invoke(msg, params);
                    returnValue += flds[i].getName() + ":" + retValue + "；";
                    if (i == 3) {
                        break;
                    }
                }
            }
            return returnValue;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 为null的话返回""
     *
     * @param text
     * @return
     */
    public static String formatString(String text) {
        if (text == null) {
            return "";
        }
        return text;
    }

    /**
     * double四舍五入为int
     *
     * @param d
     * @return
     */
    public static int doubleToInt(double d) {
        return Integer.valueOf(new BigDecimal(String.valueOf(d)).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
    }

    /**
     * double四舍五入保留小数位数
     *
     * @param d
     * @param scale 需要保留的小数位数
     * @return
     */
    public static String formatdb(double d, int scale) {

        //DecimalFormat df = new DecimalFormat("#0.00");
        //return df.format(d);
        return new BigDecimal(String.valueOf(d)).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * double四舍五入保留2位小数
     *
     * @param d
     * @return
     */
    public static String format2db(double d) {

        //DecimalFormat df = new DecimalFormat("#0.00");
        //return df.format(d);
        return new BigDecimal(String.valueOf(d)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * double四舍五入保留3位小数
     *
     * @param d
     * @return
     */
    public static String format3db(double d) {
//		 DecimalFormat df = new DecimalFormat("#0.000"); 
//		 return df.format(d);

        return new BigDecimal(String.valueOf(d)).setScale(3, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 转换字符编码格式,原字符必须为utf-8
     *
     * @param s
     * @param charset
     * @return
     */
    public static String getNewString(String s, String charset) {
        try {
            return new String(s.getBytes("utf-8"), charset);
        } catch (Exception e) {
            return s;
        }
    }

    /**
     * 将字符串类型的日期转换为一个timestamp（时间戳记java.sql.Timestamp）
     *
     * @param dateString 需要转换为timestamp的字符串yyyyMMddkkmmss
     * @return
     */
    public static Timestamp string2Time3(String dateString) {
        try {
            DateFormat dateFormat;
            dateFormat = new SimpleDateFormat("yyyyMMddkkmmss", Locale.ENGLISH);// 设定格式
            // dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss",
            // Locale.ENGLISH);
            dateFormat.setLenient(false);
            if (!dateString.contains(":")) {
                dateString += " 00:00:00";
            }
            Date timeDate = dateFormat.parse(dateString);// util类型
            Timestamp dateTime = new Timestamp(timeDate.getTime());// Timestamp类型,timeDate.getTime()返回一个long型
            return dateTime;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 统一易宝支付宝参数格式
     *
     * @param notify_time yyyy-mm-dd kk:mm:ss
     * @return yyyyMMddkkmmss
     */
    public static String formartNotifyTime(String notify_time) {
        return notify_time.replaceAll(":", "").replaceAll("-", "").replaceAll(" ", "");
    }

    /**
     * 计算日期为星期几
     *
     * @param t
     * @return 1.星期日、2.星期一、3.星期二、4.星期三、5.星期四、6.星期五、7.星期六、
     */
    public static int getWeekByTimeStamp(Timestamp t) {
        Date d = new Date(t.getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        int w = cal.get(Calendar.DAY_OF_WEEK);
        return w;
    }

    /**
     * 获取时间段中间的所有日期
     *
     * @param //phones
     * @param //content
     */
    public static List<Timestamp> getTimeStampList(Timestamp startT, Timestamp endT) {

        List<Timestamp> tList = new ArrayList<Timestamp>();
        startT = toStartTime(startT);
        endT = toEndTime(endT);

        Timestamp tempT = startT;
        boolean tag = tempT.before(endT);

        while (tag) {

            tList.add(tempT);

            Date d = new Date(tempT.getTime());
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.DATE, 1);
            String dStr = dateToString(cal.getTime());
            tempT = string2Time1(dStr);

            tag = tempT.before(endT);
        }
        ;

        return tList;
    }

    /**
     * 去除select 到from
     *
     * @param sql
     * @return
     */
    public static String removeSelect(String sql) {
        final int beginPos = sql.toLowerCase().indexOf("from");
        return sql.substring(beginPos);
    }

    private static String removeOrders(String sql) {
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",
                Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(sql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return sb.toString();
    }

//	/**
//	 * 
//	 * @param phones
//	 * @param content
//	 * @param choosePlatfrom  true:用新地址，false:用旧地址
//	 */
//	public static void sendSms(String phones,String content,String choosePlatfrom){
//		if(phones != null && !phones.isEmpty()){
//			content = content.replaceAll("'", "");
//			if(!content.endsWith("【西岭雪山电子商务平台】")){
//				content = content+"【西岭雪山电子商务平台】";
//			}
//			System.out.println("发送短信手机号码为 ："+phones+",内容："+content);
//			if("1".equals(BookingConfig.getInstance().getValue("is_send"))){
//				if("false".equals(choosePlatfrom)){
//					sendSmsOld(phones,content);
//					//System.out.println("sendSmsOld");
//				}else{
//					sendSmsNew(phones,content);
//					//System.out.println("sendSmsNew");
//				}
//			}
//		}
//	}

//	public static void sendSmsOld(String phones,String content){
//			SendSMSThread thread = new SendSMSThread();
//			thread.setContent(content);
//			thread.setMobile(phones);
//			thread.run();
//	}
//	
//	public static void sendSmsNew(String phones,String content){
//		SendSMSNewThread thread = new SendSMSNewThread();
//		thread.setContent(content);
//		thread.setMobile(phones);
//		thread.run();
//}

    public static void sendMail(String mail, String uName, String content) {

        if (mail != null && !mail.isEmpty()) {
//			mail = "xyogu@qq.com";
            String mailStyle = "<table border='0' cellspacing='0' cellpadding='0' style='width: 800px;'>" +
                    "<tr>" +
                    "<td>" +
                    "&nbsp;" +
                    "</td>" +
                    "<td >" +
                    "<img src='http://www.xilingxueshan.cn/booking/img-new/xlLog.gif' style='margin: 0px 0px 0px 0px;'>" +
                    "</td>" +
                    "<td>" +
                    "&nbsp;" +
                    "</td>" +
                    "</tr>" +
                    "<tr>" +
                    "<td>" +
                    "&nbsp;" +
                    "</td>" +
                    "<td >" +
                    "<div style='width: 720px;" +
                    "border-bottom: 2px solid #AE096F;" +
                    //"height: 30px;"+
                    "margin-bottom: 15px;" +
                    "text-align: left;" +
                    "padding-left: 20px;" +
                    "color: #AE096F;" +
                    "font-size:20px;" +
                    "font-weight:bold;" +
                    "font-family:\'幼圆\';'>" +
                    //"<b>《电子商务平台特殊人群优惠政策》</b>"+
                    "</div>" +
                    "</td>" +
                    "<td>" +
                    "&nbsp;" +
                    "</td>" +
                    "</tr>" +
                    "<tr height='450px'>" +
                    "<td width='20px' style='background: url(http://www.xilingxueshan.cn/booking/img-new/leftWin.gif) repeat-x right top;'>&nbsp;</td>" +
                    "<td style='padding-left: 20px;padding-right: 20px;padding-top: 30px;font-size:16px;' valign='top'>";
            if (uName == null || "".equals(uName.trim())) {
                mailStyle = mailStyle +
                        "亲爱的用户您好：<br/><br/>";
            } else {
                mailStyle = mailStyle +
                        "亲爱的用户&nbsp;&nbsp;<b>" + uName + "</b>&nbsp;&nbsp;您好：<br/><br/>";
            }
            mailStyle = mailStyle +
                    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + content +
                    "<br/><br/>" +
                    "<font style='font-size:10px;color:#5D5D5D;'>此邮件为西岭电子商务平台系统发出，请勿回复邮件。<br/>" +
                    "如需帮助，请致电：028-68721722。节假日请拨打：028-85189996</font>" +
                    "</td>" +
                    "<td width='20px' style='background: url(http://www.xilingxueshan.cn/booking/img-new/rightWin.gif) repeat-x left top;'>&nbsp;</td>" +
                    "</tr>" +
                    "</table>";

            if ("1".equals(BookingConfig.getInstance().getValue("is_send"))) {
                SendMail sendMail = new SendMail("西岭电子商务平台", mail, "西岭电子商务平台", mailStyle);
                sendMail.start();
            }
        }
    }

    /**
     * 读取url内容
     *
     * @param strUrl
     * @return
     */
    public static StringBuffer readUrl(String strUrl) {
        URL url = null;
        HttpURLConnection connection = null;

        try {
            url = new URL(strUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }


    /**
     * 验证时间段是否交叉
     *
     * @param //startDate开始时间
     * @param //endDate结束时间
     * @param //oldTimes已有的时间段List<Map<>>,[{startDate= , endDate= },{startDate= , endDate= }]
     * @return 交叉的时间段{startDate= , endDate= }，如没有交叉则返回null;
     */
    public static Map CrossValidationPeriod(Timestamp startDate, Timestamp endDate, List oldTimes) {

        Map reMap = new HashMap();

        for (Object o : oldTimes) {
            Map temp = (Map) o;
            Timestamp oldStartDate = toStartTime(string2Time1(temp.get("startDate").toString()));
            Timestamp oldEndDate = toEndTime(string2Time1(temp.get("endDate").toString()));

            if (oldStartDate.after(startDate) && oldStartDate.before(endDate)) {
                reMap.put("startDate", oldStartDate);
                reMap.put("endDate", oldEndDate);
                return reMap;
            } else if (oldEndDate.after(startDate) && oldEndDate.before(endDate)) {
                reMap.put("startDate", oldStartDate);
                reMap.put("endDate", oldEndDate);
                return reMap;
            } else if (startDate.before(oldStartDate) && endDate.after(oldEndDate)) {
                reMap.put("startDate", oldStartDate);
                reMap.put("endDate", oldEndDate);
                return reMap;
            } else if (oldStartDate.before(startDate) && oldEndDate.after(endDate)) {
                reMap.put("startDate", oldStartDate);
                reMap.put("endDate", oldEndDate);
                return reMap;
            } else if (oldStartDate.equals(startDate)) {
                reMap.put("startDate", oldStartDate);
                reMap.put("endDate", oldEndDate);
                return reMap;
            } else if (oldEndDate.equals(endDate)) {
                reMap.put("startDate", oldStartDate);
                reMap.put("endDate", oldEndDate);
                return reMap;
            }

        }

        return null;

    }

    /**
     * 返回指定日期的月的最后一天
     */
    public static String getLastDayOfMonth(String date) {
        SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = CommonMethod.stringToDate(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), 1);
        calendar.roll(Calendar.DATE, -1);
        return datef.format(calendar.getTime());
    }

    /**
     * 返回指定日期的月的第一天
     */
    public static String getFirstDayOfMonth(String date) {
        SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = CommonMethod.stringToDate(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), 1);
        return datef.format(calendar.getTime());
    }

    public static String getNewKey() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    public static String getOrderDate(Date date, int scale) {
        String orderDate = "";
        int dateSum = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(date);
        System.out.println(dateStr);
        int year = Integer.valueOf(dateStr.substring(0, 4));
        int month = Integer.valueOf(dateStr.substring(5, 7));
        int day = Integer.valueOf(dateStr.substring(8, 10));
        for (int i = 1; i < month; i++) {
            switch (i) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    dateSum += 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    dateSum += 30;
                    break;
                case 2:
                    if (((year % 4 == 0) & (year % 100 != 0)) | (year % 400 == 0))
                        dateSum += 29;
                    else dateSum += 28;
            }
        }
        dateSum = dateSum + day;
        orderDate = String.format("%0" + scale + "d", dateSum);
        return orderDate;
    }

    public static void main(String[] args) {
//		RefundParams refundParams = new RefundParams();
//		refundParams.setPb_TrxId("09876543211234567890");
//		
//		refundParams.setP3_Amt("100");
//		refundParams.setP4_Cur("CNY");
//		refundParams.setP5_Desc("退票退款!");
//		RefundResult rr = PaymentForOnlineService.refundByTrxId(refundParams.getPb_TrxId(),refundParams.getP3_Amt(),refundParams.getP4_Cur(),refundParams.getP5_Desc());
//		if(rr.getR1_Code().equals("1")){//退款成功
//			System.out.println(1111);
//		}

//		Date d = new Date(getTimeStamp().getTime());
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(d);
//		int w = cal.get(Calendar.DAY_OF_WEEK);
//		String startTimeStr = addHour(getDate(), -3);
//		
//		System.out.println(getTimeStamp());
//		System.out.println(string2Time1("2012-11-12 15:20:15"));
//		System.out.println(diffHour(getTimeStamp(), string2Time1("2012-11-12 15:20:15")));
//		System.out.println(string2Time("2012-11-29 00:00:00.0"));

//		System.out.println(isNumber(""));
        //sendMail("xyogu@qq.com","尊敬的客户您好，您注册的西岭雪山电子商务平台的账户已成功开通！账户号为：11111，登录的网址为：http://www.xilingxueshan.cn/booking。");

//		sendSms("13678123871", "1111dddd");
//		System.out.println(string2Time1("20140725100252"));
//		Timestamp ts = CommonMethod.getTimeStamp();
//		System.out.println(ts);
//		System.out.println(CommonMethod.addDay(ts, -1));
//		System.out.println(ts.after(CommonMethod.addDay(ts, -1)));

//		System.out.println(MD5.getMD5("350909"));
//		try {
//			System.out.println(java.net.InetAddress.getByName("192.168.4.160").isReachable(1000));
//		} catch (UnknownHostException e) {//无法找到主机
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

//		System.out.println("2013070475489027");
//		String trxId = "2013070475489027^0.01^SUCCESS^true^P$alipay-test12@alipay.com^2088201564809153^0.00^SUCCESS";
//		trxId = trxId.substring(0, trxId.indexOf("^"));
//		System.out.println(trxId);
//		System.out.println(CommonMethod.getOrderDate(CommonMethod.string2Time1("2017-01-01"), 3));
        System.out.println("2017-01-01".substring(0, 4));
    }


}
