package com.baseAdmin.util;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class SignUtils {
	public static void main(String[] args) {
		Map<String, String> params = new LinkedHashMap();
		params.put("data",
				"{\"recID\":1634,\"eventDesc\":\"测试2\",\"taskNum\":\"202005300001\",\"address\":\"无位置描述\",\"coordinateX\":1.2739269482671E7,\"coordinateY\":3539806.902057,\"Lontitude\":1.2739269482671E7,\"Latitude\":3539806.902057,\"eventSrcID\":11,\"districtCode\":3,\"districtName\":\"东湖高新区\",\"streetCode\":104,\"streetName\":\"东湖高新街道1\",\"communityCode\":10003,\"communityName\":\"东湖高新社区2\",\"recTypeID\":1,\"eventLevelID\":1,\"eventTypeCode\":1,\"mainTypeCode\":351,\"subTypeCode\":411,\"humanName\":\"高志远\",\"medias\":[{\"content\":\"\",\"mediaName\":\"再次核查.png\",\"mediaType\":\"IMAGE\",\"mediaURL\":\"http://localhost:8080/eUrbanMIS/mediadl/media/getdata/Media2/rec/20200515/1596/f4e8ba85-78c8-44fb-86dc-cf31215c6da2/b0a7f76a-78a7-4418-b29e-62a5336a71e2.png\",\"mediaUsage\":\"上报\",\"taskNum\":\"202005300001\"}]}");
		params.put("dataId", "2202005300001");
		params.put("operateType", "ADD");
		String timestamp = String.valueOf((new Date().getTime()));
		params.put("timestamp", timestamp);
		String secret = "iSp5s7v5uGPyOOmMyWU79RJf";
		String sign = createSign(params, secret);
		System.out.println(sign);
	}
	public static String getSign(String data,String dataId,String dataType,String operateType,String timestamp,String secret) {
		Map<String, String> params = new LinkedHashMap();
		params.put("data",data);
		params.put("dataId", dataId);
		params.put("dataType", dataType);
		params.put("operateType", operateType);
		params.put("timestamp", timestamp);
		String sign = createSign(params, secret);
		return sign;
	}
	public static String getSign(Map<String, String>params,String secret) {
		String sign = createSign(params, secret);
		return sign;
	}
	public static String createSign(String signString, String secret) {
		signString = signString + "&" + secret;
		String sign = Md5Utils.encrypt(signString);
		//System.out.println(signString);
		return sign;
	}

	public static String createSign(Map<String, String> params, String secret) {
		if (params != null && params.size() > 0) {
			return createSign(getSignString(params, secret), secret);
		}
		return null;
	}

	private static String getSignString(Map<String, String> params, String secret) {
		String signString = "";
		for (String key : params.keySet()) {
			signString += key + "=" + params.get(key) + "&";
		}
		return signString.substring(0, signString.length() - 1);
	}
}
