package com.cop.mobile.test.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class AccountActionTest {

	public static void main(String[] args) {
		String baseUrl = "http://localhost:8080/test/account/login";
		List<NameValuePair> params = new LinkedList<NameValuePair>();
		params.add(new BasicNameValuePair("name", "Post1"));
		params.add(new BasicNameValuePair("email", "Post方法"));
		params.add(new BasicNameValuePair("pwd", "第二个参数"));

		HttpClient httpClient;
		HttpPost httpPost;
		try {
			httpClient = new DefaultHttpClient();
			httpPost = new HttpPost(baseUrl);
			httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // 将参数填入POST
																			// Entity中

			HttpResponse response = httpClient.execute(httpPost); // 执行POST方法
			System.out.println("resCode = "
					+ response.getStatusLine().getStatusCode()); // 获取响应码
			System.out.println("result = "
					+ URLDecoder.decode(EntityUtils.toString(response.getEntity(), "gbk"), "gbk")); // 获取响应内容
			
			String test = "%7B%22status%22%3A400%2C%22data%22%3A%7B%22title%22%3A%22login+error%22%2C%22content%22%3A%22%3F%3F%3F%3F%3F%22%7D%7D";
			System.out.println(URLDecoder.decode(test, "gbk"));

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
