package com.common;

import net.sf.json.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zkn 2016-06-05
 */
public class BuildCommitteeUtil {
    /*
     * 发送请求获取数据
     */
    public static String Util(String url) {
        // 创建HttpClient对象
        CloseableHttpClient closeHttpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        // 添加
        List<String> result = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();

        // 发送请求
        HttpPost httpPost = new HttpPost(url);
        // 设置Post参数
        // List<NameValuePair> params = Lists.newArrayList();
        // params.add(new BasicNameValuePair("loginId", "user"));
        // params.add(new BasicNameValuePair("powssWord", "123456"));
        try {
            // 转换参数并设置编码格式
            // httpPost.setEntity(new
            // UrlEncodedFormEntity(params,Consts.UTF_8));
            // 执行Post请求 得到Response对象
            httpResponse = closeHttpClient.execute(httpPost);
            // httpResponse.getStatusLine() 响应头信息
            // 返回对象 向上造型
            HttpEntity httpEntity = httpResponse.getEntity();

            if (httpEntity != null) {
                // 响应输入流
                InputStream is = httpEntity.getContent();
                // 转换为字符输入流
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        is, Consts.UTF_8));

                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                // for (String string : ls) {
                // System.out.println(string);
                // }
                // 关闭输入流
                is.close();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (closeHttpClient != null) {
                try {
                    closeHttpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    // utl地址gb2312编码
    public static String Utilgb2312(String url) {
        // 创建HttpClient对象
        CloseableHttpClient closeHttpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        // 添加
        List<String> result = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();

        // 发送请求
        HttpPost httpPost = new HttpPost(url);
        // 设置Post参数
        // List<NameValuePair> params = Lists.newArrayList();
        // params.add(new BasicNameValuePair("loginId", "user"));
        // params.add(new BasicNameValuePair("powssWord", "123456"));
        try {
            // 转换参数并设置编码格式
            StringEntity entity = new StringEntity(url, "gb2312");
            entity.setContentType("application/json;charset=gb2312");
            httpPost.setEntity(entity);
            // 执行Post请求 得到Response对象
            httpResponse = closeHttpClient.execute(httpPost);
            // httpResponse.getStatusLine() 响应头信息
            // 返回对象 向上造型
            HttpEntity httpEntity = httpResponse.getEntity();

            if (httpEntity != null) {
                // 响应输入流
                InputStream is = httpEntity.getContent();
                // 转换为字符输入流
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        is, Consts.UTF_8));

                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                // for (String string : ls) {
                // System.out.println(string);
                // }
                // 关闭输入流
                is.close();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (closeHttpClient != null) {
                try {
                    closeHttpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    // 这个请求是带参数的
    // 上传报告的请求
    public static String uploadingReports(String url, String token,
                                          String inspectionNumber, String data) throws Exception {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        String message = null;
        String password = MD5.getMd5Password("123456");
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        JSONObject arg = new JSONObject().element("age", 44).element("job",
                "cooker");
        // byte[] bytes = inspectionNumber.getBytes();
        // System.out.println(Base64EncodeAndDecode.encodeBase64(bytes));
        String queryCase = "token=" + token + "&inspectionNumber="
                + URLEncoder.encode(inspectionNumber, "GB2312") + "&data="
                + URLEncoder.encode(data, "GB2312");
        ;
        StringEntity reqEntity = new StringEntity(queryCase);
        reqEntity.setContentType("application/x-www-form-urlencoded");
        post.setEntity(reqEntity);
        HttpResponse response = client.execute(post);

        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            message = EntityUtils.toString(entity, "utf-8");
        } else {
            System.out.println("请求失败");
        }
        return message;
    }

    //上传报告和文件
    public static String uploadingReportsAndFile(String url, String token, String reportGuid, String fileName, String md5, String data) throws Exception {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        String message = null;
        String password = MD5.getMd5Password("123456");
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        JSONObject arg = new JSONObject().element("age", 44).element("job", "cooker");
//				byte[] bytes = inspectionNumber.getBytes();
//				System.out.println(Base64EncodeAndDecode.encodeBase64(bytes));
        String queryCase = "token=" + token + "&reportGuid=" + reportGuid + "&fileName=" + fileName + "&md5=" + md5 + "&data=" + data;
        ;
        StringEntity reqEntity = new StringEntity(queryCase);
        reqEntity.setContentType("application/x-www-form-urlencoded");
        post.setEntity(reqEntity);
        HttpResponse response = client.execute(post);

        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            message = EntityUtils.toString(entity, "utf-8");
        } else {
            System.out.println("请求失败");
        }
        return message;
    }
}
