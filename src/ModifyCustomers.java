import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * Name: ModifyCustomers
 * Abstract: Modifies the customers.xml file.
 */
public class ModifyCustomers {
	
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
	 * Name: Main (ModifyCustomers)
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Filepath to the file we're going to modify
		String customerPath = "customers.xml";
		File xmlFile = new File(customerPath);

		//Document builder
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;

		try {
			//File Logger
			fileHandler = new FileHandler("viewCustomerLog.xml");
			
			//logging all levels
			logger.setLevel(Level.ALL);
			
			//add handler
			logger.addHandler(fileHandler);
			
			
			//New DocumentBuilder instance
			dBuilder = dbFactory.newDocumentBuilder();
			
			Document customerDoc = dBuilder.parse(xmlFile);
			customerDoc.getDocumentElement().normalize();

			//Add a New Element
			addElement(customerDoc);
			
			//Now we write to a new file/console
			customerDoc.getDocumentElement().normalize();
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			
			//Indenting line by line
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			
			DOMSource source = new DOMSource(customerDoc);
			
			StreamResult console = new StreamResult(System.out);
			StreamResult file = new StreamResult(new File("customers_modified.xml"));
			
			transformer.transform(source, console);
			transformer.transform(source, file);
			System.out.print("File Updated successfully");
			
		} catch(ParserConfigurationException | SecurityException | IOException | SAXException | TransformerException e){
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}
	
	
	
	/**
	 * Name: addElement
	 * Abstract: Adds  new elements to existing XML
	 * @param doc
	 */
	private static void addElement(Document doc) {
		//Making a nodelist to hold employee attributes
		NodeList Customers = doc.getElementsByTagName("Customer");
		Element customer = null;
		int intIndex = 0;
		
		//Looping for each student
		for (intIndex = 0; intIndex < Customers.getLength(); intIndex += 1) {
			customer = (Element) Customers.item(intIndex);
			
			//Adding three new fields: Phone, Contact Name, Email
			
			//Phone
			Element phoneElement = doc.createElement("phonenumber");
			phoneElement.appendChild(doc.createTextNode("555-555-5555"));
			customer.appendChild(phoneElement);
			
			//ContactName
			Element contactElement = doc.createElement("contact");
			contactElement.appendChild(doc.createTextNode("John Doe"));
			customer.appendChild(contactElement);
			
			//Email
			Element emailElement = doc.createElement("email");
			emailElement.appendChild(doc.createTextNode("hotjava@yahoo.com"));
			customer.appendChild(emailElement);
		}
	}
}

