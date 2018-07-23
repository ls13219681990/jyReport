/**
 *
 */
package com.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

/**
 * @author 明兴网络
 */
public class MD5 {

    public static String getMD52(String par) {
        if (par == null || par.trim().isEmpty()) {
            return "";
        }
        byte[] source = par.getBytes();
        String s = null;
        char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
                'e', 'f'};
        try {
            MessageDigest md = MessageDigest
                    .getInstance("MD5");
            md.update(source);
            byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
            // 用字节表示就是 16 个字节
            char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
            // 所以表示成 16 进制需要 32 个字符
            int k = 0; // 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
                // 转换成 16 进制字符的转换
                byte byte0 = tmp[i]; // 取第 i 个字节
                str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
                // >>>
                // 为逻辑右移，将符号位一起右移
                str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
            }
            s = new String(str); // 换后的结果转换为字符串

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * MD5加密
     *
     * @param str
     * @return
     */
    public static String getMD5(String str) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] byteArray = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                sb.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                sb.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return sb.toString();
    }

    // 建委md5加密
    public static String getMd5Password(String str) {
        String value = "";
        String passWord = "P%y2K&ja" + str;

        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("md5");
            md5.update(passWord.getBytes());

            value = new String(Base64.encodeBase64((md5.digest())));

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return value;
    }

    // 对文件名进行MD5加密
    /**
     * 对文件全文生成MD5摘要
     *
     * @param file
     * <p>
     * 要加密的文件
     * @return MD5摘要码
     */

    static char hexdigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8',

            '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String getFileMD5(File file) {

        FileInputStream fis = null;

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");

            fis = new FileInputStream(file);

            byte[] buffer = new byte[2048];

            int length = -1;

            long s = System.currentTimeMillis();

            while ((length = fis.read(buffer)) != -1) {

                md.update(buffer, 0, length);

            }

            byte[] b = md.digest();

            return byteToHexString(b);

            // 16位加密

            // return buf.toString().substring(8, 24);

        } catch (Exception ex) {

            ex.printStackTrace();

            return null;

        } finally {

            try {

                fis.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }

    }

    /** */

    /**
     * 把byte[]数组转换成十六进制字符串表示形式
     *
     * @param tmp 要转换的byte[]
     * @return 十六进制字符串表示形式
     */

    private static String byteToHexString(byte[] tmp) {

        String s;

        // 用字节表示就是 16 个字节

        char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，

        // 所以表示成 16 进制需要 32 个字符

        int k = 0; // 表示转换结果中对应的字符位置

        for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节

            // 转换成 16 进制字符的转换

            byte byte0 = tmp[i]; // 取第 i 个字节

            str[k++] = hexdigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,

            // >>> 为逻辑右移，将符号位一起右移

            str[k++] = hexdigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换

        }

        s = new String(str); // 换后的结果转换为字符串

        return s;

    }

}
