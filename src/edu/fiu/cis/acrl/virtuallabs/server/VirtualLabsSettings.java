package edu.fiu.cis.acrl.virtuallabs.server;

import java.io.InputStream;
import java.util.Properties;

import org.apache.axis2.context.ConfigurationContext;

public class VirtualLabsSettings {

	// VEScheduler settings
	private String veSchedulerEPR;
	
	// MentorScheduler settings
	private String mentorSchedulerEPR;
	
	// QuotaSystem settings
	private String quotaSystemEPR;
	
    // database settings
    private String dbUser;
    private String dbPassword;
    private String dbHost;
    private String dbName;
    private int debugLevel;
    
    private int checkinInterval;
    private int cachedPasswordTimeout;
  
    /**
     * Read settings from configuration file
     */
    private VirtualLabsSettings(String configFile) throws VirtualLabsSettingsException {

		Properties settings = new Properties();
		
		// try to find the configuration file
		try {
			
		    InputStream is = this.getClass().getClassLoader().getResourceAsStream(configFile);
		    settings.load(is);
		
		} catch (Exception e) {
		
			throw new VirtualLabsSettingsException("Could not find " + configFile + " file");
		
		}
	
		veSchedulerEPR = settings.getProperty("vescheduler_epr");
		
		mentorSchedulerEPR = settings.getProperty("mentorscheduler_epr");
		
		quotaSystemEPR = settings.getProperty("quotasystem_epr");
		
		dbUser = settings.getProperty("virtuallabs_db_user");
		dbPassword = settings.getProperty("virtuallabs_db_password");
		dbHost = settings.getProperty("virtuallabs_db_host");
		dbName = settings.getProperty("virtuallabs_db_name");
		debugLevel = Integer.valueOf(settings.getProperty("debug_level")).intValue();
		
		checkinInterval = Integer.valueOf(settings.getProperty("checkin_interval")).intValue();
		cachedPasswordTimeout = Integer.valueOf(settings.getProperty("cached_password_timeout")).intValue();

    }

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private VirtualLabsSettings _instance = null;
	
	/**
	 * @return The unique instance of this class.
	 */
	static public VirtualLabsSettings instance() {
		
		if(null == _instance) {
		
			try {
				
				_instance = new VirtualLabsSettings("virtuallabs.conf");
			
			}
			catch (Exception e) {
				
				e.printStackTrace();
				
			}
				
		}
	    
		return _instance;
	   
	}


    // database settings
	public String getVeSchedulerEPR() { return veSchedulerEPR; }
	public String getMentorSchedulerEPR() { return mentorSchedulerEPR; }
	public String getQuotaSystemEPR() { return quotaSystemEPR; }
    public String getDbUser() { return dbUser; }
    public String getDbPassword() { return dbPassword; }
    public String getDbHost() { return dbHost; }
    public String getDbName() { return dbName; }
    public int getDebugLevel() { return debugLevel; }
    public int getCheckinInterval() { return checkinInterval; }
    public int getCachedPasswordTimeout() { return cachedPasswordTimeout; }
    
	public String toString() {
		
		return
			"veSchedulerEPR: " + veSchedulerEPR + "\n" +
			"mentorSchedulerEPR: " + mentorSchedulerEPR + "\n" +
			"quotaSystemEPR: " + quotaSystemEPR + "\n" +
			"dbUser: " + dbUser + "\n" +
			"dbPassword: " + dbPassword + "\n" + 
			"dbHost: " + dbHost + "\n" +
			"dbName: " + dbName + "\n" +
			"checkinInterval: " + checkinInterval + "\n" +
			"cachedPasswordTimeout: " + cachedPasswordTimeout;
	
	}

}

/*
package edu.fiu.cis.acrl.virtuallabs.server;

import java.util.Properties;
import java.io.InputStream;

public class VirtualLabsSettings {

    public static final int DEFAULT_PERIOD = 1800;
    //public static final int DEFAULT_PERIOD = 300;

    private String dbUser;
    private String dbPassword;
    private String dbHost;
    private String dbName;

    private boolean enabled;
    private int period;

    public VirtualLabsSettings(String configFile) throws Exception {

	Properties settings = new Properties();
	
	// try to find the configuration file
	try {
	    InputStream is = this.getClass().getClassLoader().getResourceAsStream(configFile);
	    settings.load(is);
	} catch (Exception e) {
		e.printStackTrace();
	    throw new Exception("Could not find "+configFile);
	}

	dbUser = settings.getProperty("virtuallabs_db_user");
	dbPassword = settings.getProperty("virtuallabs_db_password");
	dbHost = settings.getProperty("virtuallabs_db_host");
	dbName = settings.getProperty("virtuallabs_db_name");
	
	//enabled = Boolean.valueOf(settings.getProperty("enable_virtuallabs"));
	
	period = DEFAULT_PERIOD;
	try {
	    period = Integer.valueOf(settings.getProperty("virtuallabs_period"));
	}
	catch(Exception e) {
	    System.err.println("Incorrect period in configuration file. Set to default: " + DEFAULT_PERIOD); 
	}
    }

    public String getDbUser() { return dbUser; }
    public String getDbPassword() { return dbPassword; }
    public String getDbHost() { return dbHost; }
    public String getDbName() { return dbName; }
    //public boolean isVirtualLabsEnabled() { return enabled; }
    public int getPeriod() { return period; }
}
*/
