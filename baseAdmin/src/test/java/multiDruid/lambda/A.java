package multiDruid.lambda;

@FunctionalInterface
public interface A <IN,OUT>{
	OUT apply2(IN in,IN in2);
}
