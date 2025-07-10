
/**
 * Name: Customers
 * Abstract: A class used to Store the info gotten from the customers.xml file
 */
public class Customers {
	private int intID;
	private String strName;
	private String strType;
	private String strAddress;
	private String strCity;
	private String strState;
	private String strZipcode;
	
	
	public int getID() {
		return intID;
	}
	
	public void setID(int intID) {
		this.intID = intID;
	}
	
	public String getName() {
		return strName;
	}
	
	public void setName(String strName) {
		this.strName = strName;
	}
	
	public String getType() {
		return strType;
	}
	
	public void setType(String strType) {
		this.strType = strType;
	}
	
	public String getAddress() {
		return strAddress;
	}
	
	public void setAddress(String strAddress) {
		this.strAddress = strAddress;
	}
	
	public String getCity() {
		return strCity;
	}
	
	public void setCity(String strCity) {
		this.strCity = strCity;
	}
	
	public String getState() {
		return strState;
	}
	
	public void setState(String strState) {
		this.strState = strState;
	}
	
	public String getZipcode() {
		return strZipcode;
	}
	
	public void setZipcode(String strZipcode) {
		this.strZipcode = strZipcode;
	}
	
	@Override
	public String toString() {
		return String.format("\nCustomer ID " + this.intID + "\nName:\t " + this.strName + "\nType:\t " + this.strType +
				"\nAddress: " + this.strAddress + "\n\t " + this.strCity + ", " + this.strState + " " + this.strZipcode);
	}
}
