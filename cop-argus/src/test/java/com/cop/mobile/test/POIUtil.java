package com.cop.mobile.test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 
 * @author chris.liu
 * 
 */
public class POIUtil {

	// 聚合数据key: bd7c9a07e35ff7d18fc2ba7dcadae0b3

	private static final String JUHE_KEY = "bd7c9a07e35ff7d18fc2ba7dcadae0b3";
	private static final String JUHE_URL = "http://apis.juhe.cn/oil/region?key=#key#&city=#city#&page=#page#";

	public static int[] getPOI(String city, int curPage) {
		// http://apis.juhe.cn/oil/region?key=bd7c9a07e35ff7d18fc2ba7dcadae0b3&city=%E8%8B%8F%E5%B7%9E
		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
		HttpConnectionParams.setSoTimeout(httpParams, 5000);
		HttpClient httpClient = new DefaultHttpClient(httpParams);
		HttpGet httpGet = null;

		int pnums = -1;
		int cur = -1;

		int[] pointer = new int[2];

		InputStream instream = null;
		try {
			String url = JUHE_URL.replaceAll("#key#", JUHE_KEY)
					.replaceAll("#city#", URLEncoder.encode(city, "utf-8"))
					.replaceAll("#page#", curPage + "");
			httpGet = new HttpGet(url);
			// long begin = System.currentTimeMillis();
			HttpResponse httpResp = httpClient.execute(httpGet);
			// long end = System.currentTimeMillis();
			// long duration = end - begin;
			// System.out.println(duration);
			if (httpResp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				instream = httpResp.getEntity().getContent();
				String result = instream2String(instream);

				JSONObject jo = new JSONObject(result);
				try {
					String resultCode = jo.getString("resultcode");
					if (resultCode != null && "200".equals(resultCode)) {
						JSONObject joResult = jo.getJSONObject("result");
						JSONObject joPageInfo = joResult
								.getJSONObject("pageinfo");
						JSONArray jaData = joResult.getJSONArray("data");
						if (joPageInfo != null) {
							pnums = joPageInfo.getInt("pnums");
							cur = joPageInfo.getInt("current");

							pointer[0] = pnums;
							pointer[1] = cur;
						}

						if (jaData != null) {
							for (int i = 0; i < jaData.length(); ++i) {
								JSONObject joData = jaData.getJSONObject(i);
								String name = joData.getString("name");
								String addr = joData.getString("address");
								String tmp = joData.getString("type");
								int type = 0;
								if ("直营店".equals(tmp)) {
									type = 1;
								} else {
									type = 1;
								}
								tmp = joData.getString("discount");
								int discount = 0;
								if ("非打折加油站".equals(tmp)) {
									discount = 0;
								} else {
									discount = 1;
								}
								double lng = Double.parseDouble(joData
										.getString("lon"));
								double lat = Double.parseDouble(joData
										.getString("lat"));
								JSONObject joPrice = joData
										.getJSONObject("price");
								double E90 = Double.parseDouble(joPrice
										.getString("E90"));
								double E93 = Double.parseDouble(joPrice
										.getString("E93"));
								double E97 = Double.parseDouble(joPrice
										.getString("E97"));
								double E0 = Double.parseDouble(joPrice
										.getString("E0"));

								String line = String
										.format("%s\t%s\t%d\t%d\t%f\t%f\t%f\t%f\t%f\t%f",
												name, addr, type, discount,
												lat, lng, E90, E93, E97, E0);

								System.out.println(line);
							}
						}

						// System.out.println(pnums);
						// System.out.println(cur);
					}

				} catch (Exception e) {

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (instream != null) {
				try {
					instream.close();
				} catch (Exception e) {
				}
			}
			if (httpGet != null) {
				httpGet = null;
			}
			if (httpClient != null) {
				httpClient.getConnectionManager().shutdown();
				httpClient = null;
			}
		}

		return pointer;
	}

	public static void appendMethodB(String fileName, String content) {
		try {
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			FileWriter writer = new FileWriter(fileName, true);
			writer.write(content);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		String city = "太仓";
		int[] pointer = getPOI(city, 1);
		for (int i = 2; i <= pointer[0]; ++i) {
			pointer = getPOI(city, i);
		}
	}

	public static String instream2String(InputStream in) throws IOException {
		StringBuffer sb = new StringBuffer();
		byte[] b = new byte[4096];
		for (int n; (n = in.read(b)) != -1;) {
			sb.append(new String(b, 0, n));
		}
		return sb.toString();
	}
}
