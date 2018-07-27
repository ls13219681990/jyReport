package com.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;


public class CopyFile {

    private static Logger mylogger = LoggerFactory.getLogger(CopyFile.class);

    //private static Logger mylogger = Logger.getLogger("mylogger");
    //Logger.getLogger("mylogger");
    public static void copy(File src, File dst) {
        try {
            InputStream in = null;
            OutputStream out = null;
            try {
                in = new BufferedInputStream(new FileInputStream(src), 1024);
                out = new BufferedOutputStream(new FileOutputStream(dst), 1024);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
            } finally {
                if (null != in) {
                    in.close();
                }
                if (null != out) {
                    out.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyeExcel(String sourceFile, String TargetFile) {

        File file1 = new File(sourceFile);// 源文件
        if (file1.exists()) {// 文件是否存在
            File file2 = new File(TargetFile);// 目标文件
            if (!file2.exists()) {
                mylogger.info(sourceFile + "源文件存在执行复制");
                // 先得到文件的上级目录，并创建上级目录，在创建文件
                file2.getParentFile().mkdir();
                // 将字符串转化为字节
                byte[] byteArr = "FileInputStream Test".getBytes();
                try {
                    // 创建文件
                    file2.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                FileOutputStream out = null;
                try {
                    out = new FileOutputStream(file2);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        out.write(byteArr);
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    copy(file1, file2);
                    File file23 = new File(TargetFile);// 源文件
                    if (file1.exists()) {
                        mylogger.info(TargetFile + "复制过后文件存在");
                    } else {

                        mylogger.info(TargetFile + "复制过后文件丢失");
                        throw new BusinessException("复制过后文件丢失！", "");
                    }
                }
            }
        } else {
            mylogger.info(sourceFile + "源文件不存在");
            throw new BusinessException("源文件不存在！", "");
        }
    }

    public static void main(String[] args) {


        String a = "D:\\apache-tomcat-7.0.29\\webapps\\reportManage\\report\\2B01713617A74124978F8A517C61E1AB\1C085EDEF3104334B61CF2E9EA9BC2F9\2018-05-24\02F99E.xlsx";
        String b = "D:\\apache-tomcat-7.0.29\\webapps\\reportManage\\report\\2B01713617A74124978F8A517C61E1AB\1C085EDEF3104334B61CF2E9EA9BC2F9\2018-05-24\1.xlsx";
        CopyFile.copyeExcel(a, b);
		/*String s = "report/2B01713617A74124978F8A517C61E1AB/1C085EDEF3104334B61CF2E9EA9BC2F9/2018-05-11\\996EF42FA9DC4377A6CC29E3364EDB55.xlsx";
		String as = CopyFile.class.getResource("").getPath();
		System.out.println(as);
		String jieguo = as.substring(1,as.indexOf("reportManage")+12);
		System.out.println(jieguo);
		//拼接地址 
				String id = "2FDAF2BEB49345889271618E4713BA7C";
				String file1 = s.replace("report", "/report");// 源文件
				String reportIdPath =null;
				if (s.substring(s.length() - 4).equalsIgnoreCase("xlsx")) {
					reportIdPath = s.substring(s.length()-37, s.length()-5);
					reportIdPath = s.replace(reportIdPath, id);
				} else {
					reportIdPath = s.substring(s.length()-36, s.length()-4);
					reportIdPath = s.replace(reportIdPath, id);
				}
				File a = new File(s);
				File sd = new File(reportIdPath);
				//复制excel文件
				 copy(a, sd);	*/
    }
}