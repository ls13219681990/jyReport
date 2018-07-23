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

    // //上传文件的请求
    // public static void upload() {
    // CloseableHttpClient httpclient = HttpClients.createDefault();
    // //CloseableHttpClient httpclient = HttpClientBuilder.create().build();
    // try {
    // HttpPost httppost = new
    // HttpPost("http://localhost:8080/WEY.WebApp/auth/right/right/receiveFile.html");
    //
    // RequestConfig requestConfig =
    // RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000).build();
    // httppost.setConfig(requestConfig);
    //
    // FileBody bin = new FileBody(new File("F:\\Ken\\abc.pdf"));
    // StringBody comment = new StringBody("This is comment",
    // ContentType.TEXT_PLAIN);
    //
    // HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("file",
    // bin).addPart("comment", comment).build();
    //
    // httppost.setEntity(reqEntity);
    //
    // System.out.println("executing request " + httppost.getRequestLine());
    // CloseableHttpResponse response = httpclient.execute(httppost);
    // try {
    // System.out.println(response.getStatusLine());
    // HttpEntity resEntity = response.getEntity();
    // if (resEntity != null) {
    // String responseEntityStr = EntityUtils.toString(response.getEntity());
    // System.out.println(responseEntityStr);
    // System.out.println("Response content length: " +
    // resEntity.getContentLength());
    // }
    // EntityUtils.consume(resEntity);
    // } finally {
    // response.close();
    // }
    // } catch (ClientProtocolException e) {
    // e.printStackTrace();
    // } catch (IOException e) {
    // e.printStackTrace();
    // } finally {
    // try {
    // httpclient.close();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }
    // }

    // public static void main(String[] args) {
    // CloseableHttpClient httpclient = HttpClients.createDefault();
    // // HttpClient httpclient = new DefaultHttpClient();
    // HttpPost httpPost = new HttpPost(
    // "http://118.122.92.139:8021/Service/SampleManagementWebSvr.assx/Login");
    // List<NameValuePair> nvps = new ArrayList<NameValuePair>();
    // String password = MD5.getMd5Password("123456");
    // nvps.add(new BasicNameValuePair("loginId", "jygccs"));
    // nvps.add(new BasicNameValuePair("password", password));
    // try {
    // // httpPost.setEntity(new UrlEncodedFormEntity(nvps));
    // HttpResponse execute = httpclient.execute(httpPost);
    //
    // httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
    // HttpEntity httpEntity = execute.getEntity();
    //
    // if (httpEntity != null) {
    //
    // // 响应输入流
    // InputStream is = httpEntity.getContent();
    // // 转换为字符输入流
    // BufferedReader br = new BufferedReader(new InputStreamReader(
    // is, Consts.UTF_8));
    //
    // String line = null;
    // while ((line = br.readLine()) != null) {
    // System.out.println(line);
    // }
    // // for (String string : ls) {
    // // System.out.println(string);
    // // }
    // // 关闭输入流
    // is.close();
    // }
    // } catch (UnsupportedEncodingException e) {
    // e.printStackTrace();
    // } catch (ClientProtocolException e) {
    // e.printStackTrace();
    // } catch (IOException e) {
    // e.printStackTrace();
    // } finally {
    //
    // }
    // }
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


// public static void main(String[] args) {
// String str = "";
// str += "<送检单>";
// str += "  <送检编号>469037201209701-SJ010002</送检编号>";
// str += "     <送检单类型>初期支护系统锚杆原材检测</送检单类型>";
// str += "   <工程名称>南城都会（四期）</工程名称>";
// str += "     <工程地址>四川省成都市武侯区益州大道北段</工程地址>";
// str += "     <检测性质>送样委托检测</检测性质>";
// str += "    <组数>1</组数>";
// str += "     <是否复检>否</是否复检>";
// str += "     <见证人>刘晓峰</见证人>";
// str += "     <见证编号>330350810500710</见证编号>";
// str += "     <见证单位>四川省中冶建设工程监理有限责任公司</见证单位>";
// str += "    <进场数量>60</进场数量>";
// str += "    <送检单位名称>四川蓝灵交通设施工程有限责任公司</送检单位名称>";
// str += "     <送检日期>2015-10-10 10:03:13</送检日期>";
// str += "     <样品信息>";
// str += "     <样品>";
// str += "     <样品编号>469037201209701-SJ010002-01</样品编号>";
// str += "      <样品名称>xxxx</样品名称>";
// str += "      <生产厂家> XXXXXXX </生产厂家>";
// str += "     <工程部位> XXXXXXX </工程部位>";
// str += "     <规格型号>XXXXXXX</规格型号>   ";
// str += "    <批号> XXXXXXX </批号>";
// str += "    <代表量>xxx</代表量>";
// str += "    <数量>xxx</数量>";
// str += "     <取样日期>2015-10-9 20:32:49</取样日期>";
// str += "     </样品>";
// str += "   </样品信息>";
// str += " </送检单>";
//
// String token = Client.getToken("sjyzljc", "1234");
// System.out.println(token);
// StringBuilder sampleInfo = Client
// .sampleInfo(
// "http://118.122.92.139:8021/Service/SampleManagementWebSvr.assx/Logout?token=",
// token, "469037201209701-SJ010002-01");
// System.out.println(sampleInfo);
// str = str.replace("<送检单>", "<sendinspect>");
// str = str.replace("</送检单>", "</sendinspect>");
// str = str.replace("送检编号", "sendInspectNumber");
// str = str.replace("送检单类型", "sendInspectType");
// str = str.replace("工程名称", "projectName");
// str = str.replace("工程地址", "inspectNature");
// str = str.replace("组数", "groupCount");
// str = str.replace("是否复检", "reexamination");
// str = str.replace("检测性质", "whetherRecheck");
// str = str.replace("见证人", "wiTnessMan");
// str = str.replace("见证编号", "wiTnessNumber");
// str = str.replace("见证单位", "wiTnessUnit");
// str = str.replace("进场数量", "entranceNumber");
// str = str.replace("送检单位名称", "sendInspectNuit");
// str = str.replace("送检日期", "sendInspectDate");
//
// str = str.replace("<样品信息>", "");
// str = str.replace("</样品信息>", "");
// str = str.replace("</样品>", "");
// str = str.replace("<样品>", "");
//
// str = str.replace("样品编号", "sampleReportId");
// str = str.replace("样品名称", "sampleName");
// str = str.replace("生产厂家", "manufacturer");
// str = str.replace("工程部位", "projectPart");
// str = str.replace("规格型号", "specificationModel");
// str = str.replace("批号", "federalApprova");
// str = str.replace("代表量", "representativeMeasure");
// str = str.replace("数量", "amount");
// String result = str.replace("取样日期", "samplingDate");
// System.out.println(result);
// System.out.println();
// String sendInspectNumber = result.substring(34, 58);
// JSONObject xml2json;
// try {
// // 将字符串转换成json格式
// xml2json = XmlUtil.xml2JSON(result.getBytes());
// // 将josn格式数据转换成bean
// /*
// * JSONObject pJsonObject1 = JSONObject.fromObject(xml2json);
// * SendInspect srr = (SendInspect) JSONObject.toBean(pJsonObject1,
// * SendInspect.class); System.out.println(srr);
// */
//
// SendInspectPage srrPage = new SendInspectPage();
// JSONObject pJsonObject = JSONObject.fromObject(xml2json);
// srrPage = (SendInspectPage) JSONObject.toBean(pJsonObject,
// SendInspectPage.class);
//
// JSONObject pJsonObject1 = JSONObject.fromObject(srrPage
// .getSendinspect());
// SendInspectObject srr = (SendInspectObject) JSONObject.toBean(
// pJsonObject1, SendInspectObject.class);
//
// // SendInspect sample = new SendInspect();
// AbstractSendInspect sampleinspect = new AbstractSendInspect();
// try {
// sampleinspect = getToSendInspect(srr);
// } catch (Exception e) {
// e.printStackTrace();
// }
// // sample = srr;
//
// System.out.println(sampleinspect.getAmount());
//
// } catch (JDOMException e) {
// e.printStackTrace();
// } catch (IOException e) {
// e.printStackTrace();
// }
// //
//
// }
// }
