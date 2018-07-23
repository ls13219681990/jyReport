package com.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpTests {
    public static void main(String args[]) throws IOException {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        String password = MD5.getMd5Password("123456");
//		nvps.add(new BasicNameValuePair("loginId", "jygccs"));
//		nvps.add(new BasicNameValuePair("password", password));
        CloseableHttpClient client = HttpClients.createDefault();
        String url = "http://118.122.92.139:8021/Service/SampleManagementWebSvr.assx/Login";
        HttpPost post = new HttpPost(url);
        String name = "jygccs";
        JSONObject arg = new JSONObject().element("age", 44).element("job", "cooker");
        String queryCase = "loginId=" + name + "&password=" + password;
        StringEntity reqEntity = new StringEntity(queryCase);
        System.out.println(reqEntity);
        reqEntity.setContentType("application/x-www-form-urlencoded");
        post.setEntity(reqEntity);
        HttpResponse response = client.execute(post);

        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            String message = EntityUtils.toString(entity, "utf-8");
            System.out.println(message);
        } else {
            System.out.println("请求失败");
        }
    }
}
