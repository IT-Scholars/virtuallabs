package edu.fiu.cis.acrl.virtuallabs.server;

public class VirtualLabsSettingsException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VirtualLabsSettingsException(String msg) {
    	
    	super(msg);
        
    }
        
    public VirtualLabsSettingsException(String key, String value) {
    	
    	this(key, value, null);
       
    }
        
    public VirtualLabsSettingsException(String key, String value, String expected) {
    	
    	super(key + " has incorrect value: " + value + ". " + (expected != null ? 
    			("Expected: " + expected) : ""));
    
    }

}
