package addressBook;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AddressBook {
	final int MAX_SIZE = 300;
	Info[] people = new Info[MAX_SIZE];
	XML xml = new XML();
	
	public void giveIndex(AddressBook book) {
		for (int i = 0; i < MAX_SIZE; ++i) {
			if(book.people[i].isChecker() != true) continue;
			else
				book.people[i].setIndex(i);
		}
	}
	
	//정렬, 검색 함수 여기에 추가
	
	public void printBook(AddressBook book) {
		/*if(count == 0) {
			System.out.println("주소록이 비어있습니다.");
			return;
		}
		
		else {
		*/
			System.out.println("-----------------------");
			for (int i = 0; i < Info.count; ++i) {
				if(book.people[i].isChecker() != true) continue;
				else {
					System.out.println("Index : " + book.people[i].getIndex());
				    System.out.println("Name : " + book.people[i].getName());
				    System.out.println("Phone Number : " + book.people[i].getNumber());
				    System.out.println("Birthday : " + book.people[i].getBirthday());
				    System.out.println("Memo : " + book.people[i].getMemo());
				    System.out.println("-----------------------");
				}
				
			}
			
		}
	}
