package addressBook;

import java.io.File;
import java.util.*;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class Test {

	public static void main(String[] args) throws Exception {
		AddressBook book = new AddressBook();
		XML xml = new XML();
		
		//파일을 읽어와 메모리에 저장
		xml.load(book);
		
		Scanner sc = new Scanner(System.in);
		int n = 0;
		do {
			menu();
			n = sc.nextInt();
			sc.nextLine();
			switch(n) {
			case 1:
				Info person = new Info();
				person.putInfo();
				//book.people[Info.count-1]=person;
				xml.addNew(person);
				System.out.println("저장되었습니다.");
				break;
			case 2:
				String node, contents;
				int index;
				System.out.println("수정하려는 항목을 입력하세요: ");
				node = sc.nextLine();
				System.out.println("수정하려는 주소록 번호를 입력하세요: ");
				index = sc.nextInt();
				sc.nextLine();
				System.out.println("내용을 입력하세요: ");
				contents = sc.nextLine();
				xml.change(node, index, contents);
				break;
			case 3:
				xml.load(book);
				book.printBook(book);
				break;
			case 4:
				break;
			case 5:
				Arrays.sort(book.people);
				book.giveIndex(book);
				break;
			case 6:
				System.out.println("주소록을 종료합니다.");
				break;
			}
		} while (n != 6);
	}
	
	public static void menu() {
		System.out.println("메뉴를 선택하시오");
		System.out.println("1.정보 입력");
		System.out.println("2.기존 주소록 수정");
		System.out.println("3.주소록 조회");
		System.out.println("4.주소록 삭제");
		System.out.println("5.주소록 정렬");
		System.out.println("6.프로그램 종료");
		System.out.print(">> ");
	}
}