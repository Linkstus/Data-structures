package prob1;

public class Person {
	String address;
	String name;
	long phoneNumber;
	
	Person(String address, String name, long phoneNumber){
		this.address = address;
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	void setName(String replacement) {
		name = replacement;
	}
	
	String getName() {
		return name;
	}
	
	void setAddress(String replacement) {
		address = replacement;
	}
	
	String getAddress() {
		return address;
	}
	
	void setPhoneNumber(long replacement) {
		phoneNumber = replacement;
	}
	
	long getPhoneNumber() {
		return phoneNumber;
	}
	

}
