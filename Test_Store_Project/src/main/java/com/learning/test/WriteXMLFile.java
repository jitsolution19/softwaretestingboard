package com.learning.test;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteXMLFile {

	public static void main(String[] args) {
		ExcelConnection objexcelvalue = new ExcelConnection("C:\\Users\\mamta\\Desktop\\scenario.xls");
		String value= objexcelvalue.GetDatafromExcel("Sheet1", 1, "StockName_UI");
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Equity");
			doc.appendChild(rootElement);
			String [] data= {"ABC","DEF","GHI","JKL","MNO"};
			for(int i=1;i<5;i++)
			{
				Element staff = doc.createElement(data[i]);
				rootElement.appendChild(staff);	
			}
			// staff elements
			
//
//			// set attribute to staff element
//			Attr attr = doc.createAttribute("ISIN_Number");
//			attr.setValue("1");
//			staff.setAttributeNode(attr);
//
//			// shorten way
//			// staff.setAttribute("id", "1");
//
//			// firstname elements
//			Element firstname = doc.createElement("WeekHigh52");
//			firstname.appendChild(doc.createTextNode("473.50"));
//			staff.appendChild(firstname);
//
//			// lastname elements
//			Element lastname = doc.createElement("Weeklow52");
//			lastname.appendChild(doc.createTextNode("210.50"));
//			staff.appendChild(lastname);
//
//			// nickname elements
//			Element nickname = doc.createElement("MarketCap");
//			nickname.appendChild(doc.createTextNode("500000"));
//			staff.appendChild(nickname);
//
//			// salary elements
//			Element salary = doc.createElement("Beta");
//			salary.appendChild(doc.createTextNode("1.2"));
//			staff.appendChild(salary);
//
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(System.getProperty("user.dir")+"\\Results\\file.xml"));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");

		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		  } catch (TransformerException tfe) {
			tfe.printStackTrace();
		  }

	}

}
