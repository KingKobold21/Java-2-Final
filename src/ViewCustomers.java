import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * @author Chad A Hammons
 * @version 0.1
 * @since 4/29/2025
 */

/**
 * Name: ViewCustomers
 * Abstract: Generates an XML file 
 */
public class ViewCustomers {

	/*
	 * Step 5: Enhancement.
	 * I'm choosing to do loggers, to get another feel for them since I didn't understand
	 * them very well the first time round. Plus loggers need exceptions, so two birds with one stone.
	 */
	//File Handler
	private static FileHandler fileHandler;
	
	//Create the logger with name of the class
	private static Logger logger = Logger.getLogger(ViewCustomers.class.getName());
		
	/**
	 * Name: Main (ViewCustomers)
	 * Abstract: Calls Methods
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creating new DBFactory instance
		String customerFile = "customers.xml";
		File xmlFile = new File(customerFile);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		int intIndex = 0;
		
		try 
		{
			//File Logger
			fileHandler = new FileHandler("viewCustomerLog.xml");
			
			//logging all levels
			logger.setLevel(Level.ALL);
			
			//add handler
			logger.addHandler(fileHandler);
			
			
			//New Document builder instance
			dBuilder = dbFactory.newDocumentBuilder();
			Document customerDoc = dBuilder.parse(xmlFile);
			
			//Make sure the value matches the validity constraint
			customerDoc.getDocumentElement().normalize();
			
			//Root Element node name
			System.out.println("Root Element is: " + customerDoc.getDocumentElement().getNodeName());
			
			//Return a collection of elements with the tag name as a node list.
			NodeList nodeList = customerDoc.getElementsByTagName("Customer");
			
			//Convert the node list into an Object list
			List<Customers> custList = new ArrayList<Customers>();
			for (intIndex = 0; intIndex < nodeList.getLength(); intIndex += 1)
			{
				custList.add(getCustomer(nodeList.item(intIndex)));
			}
			
			//Print that list
			for(Customers cust: custList) 
			{
				//Maybe have to do it line by line to get the formatting right, we'll see.
				System.out.println(cust.toString());
			}
		} catch(ParserConfigurationException | SecurityException | IOException | SAXException e){
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
		
		
	}
	
	
	
	/**
	 * Name: getCustomer
	 * Abstract: Loads customer info from the xml into a customer class
	 * @param node
	 * @return
	 */
	private static Customers getCustomer(Node node)	
	{
		
		Customers cust = new Customers();
		if(node.getNodeType() == Node.ELEMENT_NODE) 
		{
			Element element = (Element) node;
			cust.setID(Integer.parseInt(element.getAttribute("id")));
			cust.setName(getTagValue("name", element));
			cust.setType(getTagValue("type", element));
			cust.setAddress(getTagValue("address", element));
			cust.setCity(getTagValue("city", element));
			cust.setState(getTagValue("state", element));
			cust.setZipcode(getTagValue("zipcode", element));
		}
		
		return cust;
	}
	
	
	
	/**
	 * Name:getTagValue
	 * Abstract: Returns the value of a tag from an XML file.
	 * @param tag
	 * @param element
	 * @return
	 */
	private static String getTagValue(String tag, Element element) 
	{
		NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodeList.item(0);
		return node.getNodeValue();
	}

}
