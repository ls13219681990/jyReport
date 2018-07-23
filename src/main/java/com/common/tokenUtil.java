package com.common;

public class tokenUtil {
    public static String getToken() {
        String loginId = "jygccs";
        String passWord = "123456";
        passWord = MD5.getMd5Password(passWord);
        String util = BuildCommitteeUtil
                .Util("http://118.122.92.139:8021/Service/SampleManagementWebSvr.assx/Login?loginId="
                        + loginId + "&passWord=" + passWord);
        String result = util.toString().substring(102, 138);
        return result;
    }


    public static String getTokenutil() {
        String loginId = "jygccs";
        String passWord = "123456";
        passWord = MD5.getMd5Password(passWord);
        for (; ; ) {
            String util = BuildCommitteeUtil
                    .Util("http://118.122.92.139:8021/Service/SampleManagementWebSvr.assx/Login?loginId="
                            + loginId + "&passWord=" + passWord);
            String result = util.toString().substring(102, 138);
            try {
                Thread.sleep(1800000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return result;
        }
    }

    public static void main(String[] args) {
        String token = getToken();
        System.out.println(token);
    }
}
