package edu.fiu.cis.acrl.virtuallabs.server;

import java.util.Calendar;


/**
 * Store User Cashed Password data
 */
public class UserCashedPassword {

    private String userName;
    private String encryptedPassword;
    private Calendar updateTs;

    public UserCashedPassword() {
	
    }

    public UserCashedPassword(
    		String userName, 
    		String password, 
    		Calendar updateTs) {
	
		setUserName(userName);
		setPassword(password);
		setUpdateTs(updateTs);
    
    }
    
    public String toString() {

    	return 
    	"	userName: " + userName + "\n" +
    	"	password: " + encryptedPassword + "\n" +
    	"	updateTs: " + updateTs.getTime();
    }


    /* Accessors */
    public String getUserName() { 
    	return userName; 
    }
    
    public void setUserName(String userName) { 
    	this.userName = userName; 
    }
    
    public String getPassword() { 
    	return encryptedPassword; 
    }

    public void setPassword(String password) {         
    	this.encryptedPassword = password; 
    }
    
	public Calendar getUpdateTs() {
		return updateTs;
	}

	public void setUpdateTs(Calendar updateTs) {
		this.updateTs = updateTs;
	}

}
