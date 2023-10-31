package multiDruid;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baseAdmin.util.ExcelUtils;
import com.baseAdmin.util.NumberUtil;

public class T {
	@Test
	public void t10() {
		String a="love23next234#csdn3423javaeye";
		String regEx="[^0-9#]";  
		Pattern p = Pattern.compile(regEx);  
		Matcher m = p.matcher(a);  
		System.out.println( m.replaceAll("").trim());
	}
	@Test
	public void t11() {
		String s = "33333A876#X3333#";
		// 把要匹配的字符串写成正则表达式，然后要提取的字符使用括号括起来
		// 在这里，我们要提取最后一个数字，正则规则就是“一个数字加上大于等于0个非数字再加上结束符”
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(s);
		System.out.println(matcher.groupCount());
		if(matcher.find())
		System.out.println(matcher.group(0));
	}
	@Test
	public void t1() {
		String msg = "{\"success\":true,\"code\":\"200\",\"message\":\"处理成功\",\"data\":true}";
		Map map = JSON.parseObject(msg, Map.class);
		boolean b = false;
		if (map != null && "200".equals(map.get("code"))) {
			b = true;
		}
		System.out.println(b);
	}

	@Test
	public void t2() {
		String s1 = "1";
		String s2 = "";
		s2=s1;
		s1="3";
		System.out.println("s1:"+s1);
		System.out.println("s2:"+s2);
	}

	@Test
	public void t3() {

	}

	@Test
	public void test1() {
		String urlBuffer = "https//ssss/sssajkdkfhaldfaksjdflas";
		int i = urlBuffer.lastIndexOf(".");
		String suffix = null;
		if (i > 0) {
			suffix = urlBuffer.substring(urlBuffer.lastIndexOf("."));
		}
		System.out.println(suffix);
		if (".css".equals(suffix) || ".js".equals(suffix)) {
			System.out.println("...");
		} else {
			System.out.println("-----");
		}
	}
	@Test
	public void test2() {
		List<Map<String,Object>> list = ExcelUtils.excelToList("D:\\upload\\管网，积水点.xls");
		for(Map<String,Object> map : list) {
			System.out.print(map.get("V"));
			System.out.print(map.get("W"));
			System.out.print(map.get("X"));
			System.out.print(map.get("AA"));
			System.out.print(map.get("AB"));
			System.out.println(map.get("AC"));
			
		}
	}
	@Test
	public void test3() {
		System.out.println(NumberUtil.format(2.3333232323, 3));
	}
}
