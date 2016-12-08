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

public class XML {
	private int count = 1;
	
	private void setCount() throws Exception {
		File fXmlFile = new File("contacts.xml");
		  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		  Document doc = dBuilder.parse(fXmlFile);
		  doc.getDocumentElement().normalize();
		this.count = doc.getElementsByTagName("person").getLength();
	}
	
	public int getCount() throws Exception {
		setCount();
		return count;
	}
	
	public void addNew(Info address) throws Exception {
		
		File fXmlFile = new File("contacts.xml");
		  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		  Document doc = dBuilder.parse(fXmlFile);
		  doc.getDocumentElement().normalize();
		
		//------------------------노드 하위 원소 증가
		//고칠 노드 이름 찾을 수 있는 모든 노드 똑같은 이름의 노드 큐 받고
		NodeList nodes1 = doc.getElementsByTagName("contacts");
		//지금 대기 중 선택 고칠 노드
		Node n1 = nodes1.item(0);
		//증가 노드 원소 만들기
		Element nn = doc.createElement("person");
		//노드 속성 설정
		Attr attr = doc.createAttribute("index");
		attr.setValue(String.valueOf(getCount() + 1));
		nn.setAttributeNode(attr);
		//person 하위 원소 증가
		//이름
		Element nn1 = doc.createElement("name");
		nn1.setTextContent(address.getName());
		nn.appendChild(nn1);
		n1.appendChild(nn);
		//전화번호
		Element nn2 = doc.createElement("number");
		nn2.setTextContent(address.getNumber());
		nn.appendChild(nn2);
		n1.appendChild(nn);
		//생일
		Element nn3 = doc.createElement("birthday");
		nn3.setTextContent(address.getBirthday());
		nn.appendChild(nn3);
		n1.appendChild(nn);
		//메모
		Element nn4 = doc.createElement("memo");
		nn4.setTextContent(address.getMemo());
		nn.appendChild(nn4);
		n1.appendChild(nn);
		
		System.out.println("현재 주소록 사람 수 : " + Info.count);
		
		//-------------------------파일로 저장
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer t = factory.newTransformer();
		DOMSource xml = new DOMSource(doc);
		StreamResult s = new StreamResult(new File("contacts.xml"));
		t.transform(xml, s);
	}
	
	public void change(String node, int index, String contents) throws Exception{
		
		File fXmlFile = new File("contacts.xml");
		  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		  Document doc = dBuilder.parse(fXmlFile);
		  doc.getDocumentElement().normalize();
		//------------------------수정 내용 중 요소 노드
		  
				//고칠 노드 이름 찾을 수 있는 모든 노드 똑같은 이름의 노드 큐 받고
				NodeList nodes = doc.getElementsByTagName(node);
				//지금 대기 중 선택 고칠 노드
				Node n = nodes.item(index - 1);
				//이 노드의 텍스트 수정
				n.setTextContent(contents);
				
				//-------------------------파일로 저장
				TransformerFactory factory = TransformerFactory.newInstance();
				Transformer t = factory.newTransformer();
				DOMSource xml = new DOMSource(doc);
				StreamResult s = new StreamResult(new File("contacts.xml"));
				t.transform(xml, s);
	}
	/*
	public int printAll() {
	   try {
				 
			File fXmlFile = new File("contacts.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
				  
			count = doc.getElementsByTagName("person").getLength();
			if (getCount() == 0) {
				System.out.println("주소록이 비어있습니다.");
				return 1;
				}
			
			System.out.println(doc.getDocumentElement().getNodeName() +" - " + getCount() + "명");
			NodeList nList = doc.getElementsByTagName("person");
			System.out.println("-----------------------");
				 
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					
				    System.out.println("Index : " + eElement.getAttribute("index"));
				    System.out.println("Name : " + getTagValue("name", eElement));
				    System.out.println("Phone Number : " + getTagValue("number", eElement));
				    System.out.println("Birthday : " + getTagValue("birthday", eElement));
				    System.out.println("-----------------------");
				    }
				}
			} catch (Exception e) {
				e.printStackTrace();
				}
	   return 0;
	}
	*/
	
	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		if(nValue == null || "".equals(nValue))
			return "none";
		else
			return nValue.getNodeValue();
	}
	
	public int load(AddressBook book) {
		   try {
					 
				File fXmlFile = new File("contacts.xml");
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);
				doc.getDocumentElement().normalize();
					  
				count = doc.getElementsByTagName("person").getLength();
				if (getCount() == 0) {
					System.out.println("주소록이 비어있습니다.");
					return 1;
					}

				NodeList nList = doc.getElementsByTagName("person");
					 
				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element)nNode;
						book.people[temp] = new Info();
						book.people[temp].setChecker(true);
						book.people[temp].setIndex(temp);
						book.people[temp].setName(getTagValue("name", eElement));
						book.people[temp].setNumber(getTagValue("number", eElement));
						book.people[temp].setBirthday(getTagValue("birthday", eElement));
						book.people[temp].setMemo(getTagValue("memo", eElement));
					    }
					}
				} catch (Exception e) {
					e.printStackTrace();
					}
		   return 0;
		}
	/*
	public void delete(AddressBook book) {
		System.out.println
	}
	*/
	
}