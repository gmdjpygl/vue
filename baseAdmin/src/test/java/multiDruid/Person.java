package multiDruid;

public class Person {
	private String name;
	private int age;
	private int gender;

	public Person(String name, int age, int gender) {
		this.gender = gender;
		this.age = age;
		this.name = name;

	}
	public Person(String name, int age) {
		this.age = age;
		this.name = name;
		
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		System.out.println(name);
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name + "::" + this.age + "::" + this.gender;
	}
}
