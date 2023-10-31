package com.baseAdmin.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
	
	
	public static String doHttpGet(String url,Map<String, String> headerMap) {
		String msg = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpGet = new HttpGet(url);
			if (headerMap != null) {
				for (String key : headerMap.keySet())
					httpGet.setHeader(key, headerMap.get(key));
			}
			
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1000 * 10)// 创建连接的最长时间，单位是毫秒
					.setConnectionRequestTimeout(1000 * 10)// 设置获取连接的最长时间，单位毫秒
					.setSocketTimeout(1000 * 10)// 设置数据传输的最长时间，单位毫秒
					.build();

			httpGet.setConfig(requestConfig);
			CloseableHttpResponse response = httpclient.execute(httpGet);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					msg = EntityUtils.toString(entity);
				}
			} finally {
				response.getEntity().getContent().close();
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return msg;
	}
	public static String doHttpGet(String url) {
		String msg = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpGet = new HttpGet(url);
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1000 * 10)// 创建连接的最长时间，单位是毫秒
					.setConnectionRequestTimeout(1000 * 10)// 设置获取连接的最长时间，单位毫秒
					.setSocketTimeout(1000 * 10)// 设置数据传输的最长时间，单位毫秒
					.build();

			httpGet.setConfig(requestConfig);
			CloseableHttpResponse response = httpclient.execute(httpGet);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					msg = EntityUtils.toString(entity);
				}
			} finally {
				response.getEntity().getContent().close();
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return msg;
	}
	
	public static String doHttpPost(String url, String json) {
		return doHttpPost(url,json,null);
	}
	public static String doHttpPost(String url, String json, Map<String, String> headerMap) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String msg = "";
		StringEntity entity;
		try {
			HttpPost post = new HttpPost(url);
			if (headerMap != null) {
				for (String key : headerMap.keySet())
					post.setHeader(key, headerMap.get(key));
			}
			// 设置发送消息的参数
			entity = new StringEntity(json, Charset.forName("utf-8"));
			// 解决中文乱码的问题
			entity.setContentType("application/json; charset=UTF-8");
			entity.setContentEncoding("utf-8");
			post.setEntity(entity);
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1000 * 30)// 创建连接的最长时间，单位是毫秒
					.setConnectionRequestTimeout(1000 * 30)// 设置获取连接的最长时间，单位毫秒
					.setSocketTimeout(1000 * 30)// 设置数据传输的最长时间，单位毫秒
					.build();
			post.setConfig(requestConfig);
			CloseableHttpResponse httpResponse = httpclient.execute(post);
			HttpEntity e = httpResponse.getEntity();
			if (e != null) {
				msg = EntityUtils.toString(e, "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				entity = null;
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return msg;
	}

	public static String doHttpsPost(String url, String json) {
		CloseableHttpClient httpclient = getClient();
		String msg = "";
		try {
			HttpPost post = new HttpPost(url);
			// 设置发送消息的参数
			StringEntity entity;
			entity = new StringEntity(json, Charset.forName("utf-8"));
			// 解决中文乱码的问题
			entity.setContentType("application/json; charset=UTF-8");
			entity.setContentEncoding("utf-8");
			post.setEntity(entity);
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1000 * 20)// 创建连接的最长时间，单位是毫秒
					.setConnectionRequestTimeout(1000 * 20)// 设置获取连接的最长时间，单位毫秒
					.setSocketTimeout(1000 * 20)// 设置数据传输的最长时间，单位毫秒
					.build();
			post.setConfig(requestConfig);
			CloseableHttpResponse httpResponse = httpclient.execute(post);
			HttpEntity e = httpResponse.getEntity();
			if (e != null) {
				msg = EntityUtils.toString(e, "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return msg;
	}
	public static String doHttpsPost(String url, Map<String,String> params) {
		CloseableHttpClient httpclient = getClient();
		String msg = "";
		try {
			HttpPost post = new HttpPost(url);
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			for(String key: params.keySet()) {
				parameters.add(new BasicNameValuePair(key,params.get(key)));
			}
	        //构建form表单实体  
	        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters);
			post.setEntity(formEntity);
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1000 * 10)// 创建连接的最长时间，单位是毫秒
					.setConnectionRequestTimeout(1000 * 10)// 设置获取连接的最长时间，单位毫秒
					.setSocketTimeout(1000 * 10)// 设置数据传输的最长时间，单位毫秒
					.build();
			post.setConfig(requestConfig);
			CloseableHttpResponse httpResponse = httpclient.execute(post);
			HttpEntity e = httpResponse.getEntity();
			if (e != null) {
				msg = EntityUtils.toString(e, "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return msg;
	}
	
	
	public static CloseableHttpClient getClient() {
		SSLContext sslContext = null;
		try {
			sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		return httpClient;
	}
}