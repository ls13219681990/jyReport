package com.common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class UuidUtil {
    public static String getUUID() {
        /*
         * UUID uuid = UUID.randomUUID(); String str = uuid.toString(); //
         * 去掉"-"符号 String temp = str.substring(0, 8) + str.substring(9, 13) +
         * str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
         * return temp;
         */

        return UUID.randomUUID().toString().replace("-", "");
    }
}
		

