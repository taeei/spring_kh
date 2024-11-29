package vo;

public class PersonVO {
	private String name, tel;
	private int age;
	
	public PersonVO( String name, int age, String tel ) {
		this.name = name;
		this.age = age;
		this.tel = tel;
		System.out.println("이름, 나이, 전화번호를 받는 PersonVO의 생성자");
	}
	
	public PersonVO() {
		System.out.println("PersonVO의 기본생성자");
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		System.out.println("setName() 호출됨");
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
		System.out.println("setTel() 호출됨");
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
		System.out.println("setAge() 호출됨");
	}
	
	
	
}
