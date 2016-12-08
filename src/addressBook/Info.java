package addressBook;

import java.util.Scanner;

public class Info implements Comparable{
	static Scanner sc = new Scanner(System.in);
	
	static int count = 0;
	private boolean checker = false;
	private int index;
	private String name;
	private String number;
	private String birthday;
	private String memo;

	public Info(){
		this.checker = false;
		this.index = 0;
		this.name = "None";
		this.number = "None";
		this.birthday = "None";
		this.memo = "None";
		Info.count++;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public boolean isChecker() {
		return checker;
	}
	public void setChecker(boolean checker) {
		this.checker = checker;
	}
	
	public void putInfo(){
		System.out.print("이름을 입력하시오: ");
		this.setName(sc.nextLine());
		System.out.print("번호를 입력하시오: ");
		this.setNumber(sc.nextLine());
		System.out.print("생년월일을 입력하시오: ");
		this.setBirthday(sc.nextLine());
		System.out.print("메모를 입력하시오: ");
		this.setMemo(sc.nextLine());
		this.setChecker(true);
	}
	
	public int compareTo(Object obj) {
		Info other = (Info)obj;
		return name.compareTo(other.name);
	}
}
