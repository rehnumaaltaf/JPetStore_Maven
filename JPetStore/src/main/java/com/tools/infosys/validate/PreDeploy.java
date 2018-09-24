package com.tools.infosys.validate;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PreDeploy {
	
	  
	   public static void main(String argv[]) throws Exception{

			File fXmlFile = new File(argv[0]);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
		 
			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
		 
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		 
			NodeList nList = doc.getElementsByTagName("Resource");
		 
			System.out.println("----------------------------");
		 
			for (int temp = 0; temp < nList.getLength(); temp++) {
		 
				Node nNode = nList.item(temp);
		 
				System.out.println("\nCurrent Element :" + nNode.getNodeName());
				Element eElement = (Element) nNode;
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		            System.out.println("Input");
					//Element eElement = (Element) nNode;
		            
					System.out.println("UserName : " + eElement.getAttribute("username"));
					System.out.println("Password : " + eElement.getAttribute("password"));
					System.out.println("DriverClassName : " + eElement.getAttribute("driverClassName"));
					System.out.println("URL : " + eElement.getAttribute("url"));
					
					/*System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
					System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
					System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
					System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());*/
		 
				}
				if((eElement.getAttribute("username").equals("javauser"))&& (eElement.getAttribute("password").equals("javadude"))&& (eElement.getAttribute("driverClassName").equals("com.mysql.jdbc.Driver"))&& (eElement.getAttribute("url").equals("jdbc:mysql://localhost:3306/javatest"))){
					//return 1;
					System.out.println("Server Details are matched");
				}
				else{
					System.out.println("false");
					throw new Exception();
				}
			} 
		  
		}

}
