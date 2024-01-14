package multiDruid.lambda;

import java.util.Date;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class T {
	@Test
	public void t01() {
		A<String, Integer> a = (s, s2) -> s.length();
		System.out.println(a.apply2("22222222", "333"));
	}

	@Test
	public void t02() {
		Date now = new Date();
		Supplier<Long> supplier = () -> {
			return now.getTime();
		};
		System.out.println(supplier.get());

		Supplier<Long> supplier1 = now::getTime;
		System.out.println(supplier1.get());
	}
	@Test
	public void t03() {
		Function<String,Integer> function = (s)->{
            return s.length();
        };
        System.out.println(function.apply("hello"));
        Function<String,Integer> function1 = String::length;
        System.out.println(function1.apply("hahahaha"));
        BiFunction<String,Integer,String> function2 = String::substring;
        String msg = function2.apply("HelloWorld", 3);
        System.out.println(msg);
	}
	@Test
	public void t04(){
	    Optional<String> op1 = Optional.of("zhangsan");
	    Optional<String> op2 = Optional.empty();
	    if(op1.isPresent()){
	        String s1 = op1.get();
	        System.out.println("用户名称:" +s1);
	    }
	    if(op2.isPresent()){
	        System.out.println(op2.get());
	    }else{
	        System.out.println("op2是一个空Optional对象");
	    }

	    String s3 = op1.orElse("李四");
	    System.out.println(s3);
	    String s4 = op2.orElse("王五");
	    System.out.println(s4);

	    String s5 = op1.orElseGet(()->{
	        return "Hello";
	    });
	    System.out.println(s5);
	}
	@Test
	public void t05() {
		 Stream.of("a1", "a2", "a3","bb","cc","aa","dd")
		 .sorted((o1,o2)->o1.compareTo(o2))
		 .skip(1)
		 .limit(9)
		 .filter((a)->a.equals("a3"))
		 .forEach(System.out::println);
		 String s = "ssssssss";
		 StringBuffer sb = new StringBuffer("ssssssss");
		 System.out.println(s.contentEquals(sb));
	}
}
