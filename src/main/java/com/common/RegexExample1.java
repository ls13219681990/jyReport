package com.common;

import java.util.regex.*;

class RegexExample1 {
    public static void main(String args[]) {
//	   Matcher m3=m.matcher("2223bb"); 
//	   m.matches();   //匹配整个字符串 
//	   m.start();   //返回0,原因相信大家也清楚了 
//	   m.end();   //返回6,原因相信大家也清楚了,因为matches()需要匹配所有字符串 
//	   m.group();   //返回2223bb 


        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher("2223bb");
        m.matches();

    }
}