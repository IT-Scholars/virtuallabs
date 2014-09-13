package edu.fiu.cis.acrl.virtuallabs.server;


/**
 * Store User data
 */
public class User {

    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String userRole;
    private String timeZone;
    private String contactInfo;


	public User(String userName) {
	
		setUserName(userName);

    }
	
    public User(
    		String userName, 
    		String password, 
    		String firstName,
    		String lastName,
    		String emailAddress,
    		String userRole, 
    		String timeZone, 
    		String contactInfo) {
	
		setUserName(userName);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setEmailAddress(emailAddress);
		setUserRole(userRole);
		setTimeZone(timeZone);
		setContactInfo(contactInfo);
    
    }
    
    public String toString() {

    	return 
    	"	userName: " + userName + "\n" +
    	"	password: " + password + "\n" +
    	"	firstName: " + firstName + "\n" +
    	"	lastName: " + lastName + "\n" +
    	"	emailAddress: " + emailAddress + "\n" +
    	"	userRole: " + userRole+ "\n" +
    	"	timeZone: " + timeZone + "\n" +
    	"	contactInfo: " + contactInfo ;
    }


    /* Accessors */
    public String getUserName() { return userName; }
    public String getPassword() { return password; }
    public String getTimeZone() { return timeZone; }
    public String getUserRole() { return userRole; }
    public String getContactInfo() {return contactInfo;}


    public void setUserName(String userName) { 
        
    	this.userName = userName; 
    	if (this.userName == null)
    		this.userName = "";
    	
    }
    
    public void setPassword(String password) { 
        
    	this.password = password; 
    	if (this.password == null)
    		this.password = "";
    	
    }
    
    public void setTimeZone(String timeZone) { 
    	
    	this.timeZone = timeZone; 
    	if (this.timeZone == null)
    		this.timeZone = "";
    
    }
    
    public void setUserRole(String userRole) { 
    	
    	this.userRole = userRole; 
    	if (this.userRole == null)
    		this.userRole = "";
    	
    }

    public void setContactInfo(String contactInfo) { 
    	
    	this.contactInfo = contactInfo; 
    	if (this.contactInfo == null)
    		this.contactInfo = "";
    	
    }

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setEmailAddress(String emailAddr) {
		this.emailAddress = emailAddr;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

}
