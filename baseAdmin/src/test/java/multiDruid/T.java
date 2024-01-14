package multiDruid;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baseAdmin.util.ExcelUtils;
import com.baseAdmin.util.NumberUtil;

public class T {
	@Test
	public void t10() {
		String a = "love23next234#csdn3423javaeye";
		String regEx = "[^0-9#]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(a);
		System.out.println(m.replaceAll("").trim());
	}

	@Test
	public void t11() {
		String s = "33333A876#X3333#";
		// 把要匹配的字符串写成正则表达式，然后要提取的字符使用括号括起来
		// 在这里，我们要提取最后一个数字，正则规则就是“一个数字加上大于等于0个非数字再加上结束符”
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(s);
		System.out.println(matcher.groupCount());
		if (matcher.find())
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
		s2 = s1;
		s1 = "3";
		System.out.println("s1:" + s1);
		System.out.println("s2:" + s2);
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
		List<Map<String, Object>> list = ExcelUtils.excelToList("D:\\upload\\管网，积水点.xls");
		for (Map<String, Object> map : list) {
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

	@Test
	public void test4() {
		List<User> userList = Arrays.asList(new User("alice", 18, "female"), new User("bob", 19, "male"),
				new User("carl", 18, "male"), new User("dasy", 19, "female"));

		Map<String, List<User>> genderMap = userList.stream().collect(Collectors.groupingBy(User::getGender));
		Map<String, Long> genderMap2 = userList.stream()
				.collect(Collectors.groupingBy(User::getGender, Collectors.counting()));

		Map<String, Long> genderMap3 = userList.stream()
				.collect(Collectors.groupingBy(User::getGender, LinkedHashMap::new, Collectors.counting()));

		for (String key : genderMap.keySet()) {
			System.out.println(key);
			for (User u : genderMap.get(key)) {
				System.out.println("    " + u.toString());
			}
		}
		for (String key : genderMap2.keySet()) {
			System.out.println(key + "::" + genderMap2.get(key));
		}
		for (String key : genderMap3.keySet()) {
			System.out.println(key + "::" + genderMap3.get(key));
		}

	}

	@Test
	public void test5() {
		List<User> users = Arrays.asList(new User("Jack", 9, "female"), new User("Kreas", 10, "male"),
				new User("Marry", 13, "male"), new User("Timi", 14, "female"));

		Map<String, List<User>> map = users.stream().collect(Collectors.groupingBy(User::getGender));
		Map<String, List<User>> map1 = users.stream().collect(Collectors.groupingBy(i -> i.getGender()));
		Map<String, List<User>> map2 = users.stream().collect(Collectors.groupingBy(i -> {
			if (i.getAge() < 10) {
				return "young";
			} else {
				return "old";
			}
		}));

		System.out.println(map);
		System.out.println(map1);
		System.out.println(map2);

	}

	@Test
	public void test6() {
		goShow(new UserService() {
			@Override
			public void show() {
				System.out.println("show 方法执行了...");
			}

		});
		System.out.println("----------");
		goShow(() -> {
			System.out.println("Lambda show 方法执行了...");
		});
	}

	public static void goShow(UserService userService) {
		userService.show();
	}

	@Test
	public void test7() {
		List<Person> list = new ArrayList<>();
		list.add(new Person("周杰伦", 33, 175));
		list.add(new Person("刘德华", 43, 185));
		list.add(new Person("周星驰", 38, 177));
		list.add(new Person("郭富城", 23, 170));

		System.out.println("------");
		Collections.sort(list, (Person o1, Person o2) -> {
			return o1.getAge() - o2.getAge();
		});
		for (Person person : list) {
			System.out.println(person);
		}
	}

	@Test
	public void test8() {
		List<String> list = Arrays.asList("张三", "张三丰", "成龙", "周星驰");
		list.stream().filter(s -> s.startsWith("张")).filter(s -> s.length() == 3).forEach(s -> {
			System.out.println(s);
		});
		List<String> list2 = list.stream().filter(s -> s.startsWith("张")).collect(Collectors.toList());
		;
		System.out.println("----------");
		list2.stream().forEach(System.out::println);
		System.out.println(list2.stream().count());
	}

	@Test
	public void test9() {
		List<Integer> l = Stream.of("1", "2", "3", "4", "5", "6", "7").map(Integer::parseInt)
				.collect(Collectors.toList());
		List<String> l2 = Stream.of("1", "2", "3", "4", "5", "6", "7").map(s -> s).collect(Collectors.toList());
		Stream.of("1", "2", "3", "4", "5", "6", "7", "11").sorted((s1, s2) -> {
			return s1.compareTo(s2);
		}).forEach(s -> System.out.println(s));
	}

	@Test
	public void test10() {
		Optional<String> first = Stream.of("s", "1", "3", "3", "4", "5", "1", "7").findFirst();
		System.out.println(first.get());
		Optional<String> any = Stream.of("1", "3", "3", "4", "5", "1", "7").findAny();
		System.out.println(any.get());
	}

	@Test
	public void test11() {
		String s1 = Stream
				.of(new Person("张三", 18, 175), new Person("李四", 22, 177), new Person("张三", 14, 165),
						new Person("李四", 15, 166), new Person("张三", 19, 182))
				.map(Person::getName).collect(Collectors.joining());
		System.out.println(s1);
		String s2 = Stream
				.of(new Person("张三", 18, 175), new Person("李四", 22, 177), new Person("张三", 14, 165),
						new Person("李四", 15, 166), new Person("张三", 19, 182))
				.map(Person::getName).collect(Collectors.joining("_"));

		System.out.println(s2);

		String s3 = Stream
				.of(new Person("张三", 18, 175), new Person("李四", 22, 177), new Person("张三", 14, 165),
						new Person("李四", 15, 166), new Person("张三", 19, 182))
				.map(Person::getName).collect(Collectors.joining("_", "###", "$$$"));

		System.out.println(s3);
	}

	@Test
	public void test13() {
		Map<String, List<Person>> map1 = Stream
				.of(new Person("张三", 18, 175), new Person("李四", 22, 177), new Person("张三", 14, 165),
						new Person("李四", 15, 166), new Person("张三", 19, 182))
				.collect(Collectors.groupingBy(s -> s.getName()));
		System.out.println("---------------");
		map1.forEach((k, v) -> System.out.println("k=" + k + "\t" + "v=" + v));
		Map<String, List<Person>> map2 = Stream
				.of(new Person("张三", 18, 175), new Person("李四", 22, 177), new Person("张三", 14, 165),
						new Person("李四", 15, 166), new Person("张三", 19, 182))
				.collect(Collectors.groupingBy(Person::getName));
		System.out.println("++++++++++++++");
		map2.forEach((k, v) -> System.out.println("k=" + k + "\t" + "v=" + v));
	}

	@Test
	public void test14() {
		FunctionInterface f = new FunctionInterface() {
			@Override
			public void fun() {
				System.out.println("hello world");
			}
		};
		FunctionInterface ff = () -> System.out.println("lambda表达式调用func()");
		f.fun();
		ff.fun();
	}

	@Test
	public void test15() {
		Integer[] a = {1, 2, 3, 0};
		 Arrays.sort(a, new Comparator<Integer>() {
	            @Override
	            public int compare(Integer o1, Integer o2) {
	                return o2 - o1;
	            }
	        });
		String[] strs = "write code with Lambda in JAVA8".split(" ");
		Arrays.sort(strs, new Comparator<String>(){
		    public int compare(String s1, String s2){
		        return s1.toLowerCase().compareTo(s2.toLowerCase());
		    }
		});
		Arrays.sort(strs, (s1, s2) -> s1.toLowerCase().compareTo(s2.toLowerCase()));
	}

	@Test
	public void test16() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
		for (Integer n : list) {
		    System.out.println(n);
		}
		list.forEach(n -> System.out.println(n));
		list.forEach(System.out::println);
		
		Map<String, List<Person>> map2 = Stream
				.of(new Person("张三", 18, 175), new Person("李四", 22, 177), new Person("张三", 14, 165),
						new Person("李四", 15, 166), new Person("张三", 19, 182))
				.collect(Collectors.groupingBy(Person::getName));
		
	}
	public static void evaluate(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n : list) {
            if (predicate.test(n)) {
                System.out.print(n + " ");
            }
        }
        System.out.println();
    }
}

@FunctionalInterface
interface FunctionInterface {
	void fun();
}
