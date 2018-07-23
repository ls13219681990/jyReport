package com.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import org.apache.commons.codec.binary.Base64;

public class UploadReportUtil {

//			byte[] byteArray3 = UploadReport(new File("C:/Users/STKJ-12/Desktop/工作资料/报告/招标文件.pdf"));
//			String encode = encode(byteArray3);
//			System.out.println(encode);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

    //pdf内容Base64编码
    public static byte[] UploadReport(File filePath) throws IOException {

        FileInputStream is = new FileInputStream(filePath);
        try {
            MappedByteBuffer byteBuffer = is.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, filePath.length()).load();
            byte[] result = new byte[(int) filePath.length()];
            if (byteBuffer.remaining() > 0) {
                byteBuffer.get(result, 0, byteBuffer.remaining());
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String toBase64(final byte[] bytes) {
        return new String(Base64.encodeBase64(bytes)).toUpperCase();
    }

}

