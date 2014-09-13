package edu.fiu.cis.acrl.virtuallabs.server;


import edu.fiu.cis.acrl.virtuallabs.server.*;
import edu.fiu.cis.acrl.virtuallabs.server.db.VirtualLabsDB;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.lang.Integer;

/**
 * This class wakes up periodically and starts/stops the VEs in the queue
 *
 */
public class ExpiredAppRemoverAgent implements Runnable {

    private VirtualLabsDB vlabsDB;
    private int schedulingPeriod;

    private boolean running;

    /**
     * Create the agent by passing the DB connection information and the schedulingPeriod (in seconds)
     *
     */
    public ExpiredAppRemoverAgent(String dbuser, String dbpwd, String dbhost, String dbname, int schedulingPeriod) {
	vlabsDB = new VirtualLabsDB();
	vlabsDB.connect(dbuser, dbpwd, dbhost, dbname);
	running = false;
	this.schedulingPeriod = schedulingPeriod * 1000;
    }

    public void run() {
	
	running = true;

		while(running) {
        
	    System.out.println(">> ExpiredAppRemoverAgent execution: " + Calendar.getInstance().getTime());

		try
		{
	    Collection<DbAppointment> expiredApps = new ArrayList<DbAppointment>();
	    //expiredApps = vlabsDB.getExpiredDbAppointments(Calendar.getInstance());
	    
	    System.out.println(">>List of Expired DbAppointments:"+expiredApps.size()); 
	    //if(expiredApps.size()== 0) System.out.println(">>[Empty]"); 
	    	for (DbAppointment a : expiredApps) {
                                    
        		//TODO:Cancel DbAppointment calling web service here                                
        		//vlabsDB.cancelDbAppointment(a.getVeId());//Remove it from DB
      	 	 	System.out.println("\t"+a);

        
        		}
        		
         }catch(Exception e)
         {
          	e.printStackTrace();
          	throw new Error(e.getMessage());
         }
         
         	    // Calculate how many seconds to wait until the next half hour
	    Calendar c = Calendar.getInstance();
	    long t = c.getTime().getTime();

	    int offset = (int)(t % schedulingPeriod);

	    int wait = schedulingPeriod - offset;

	    System.out.println("t: " + t + ", offset: " + offset + ", wait: " + wait);

	    try {
		Thread.sleep(wait);
	    }
	    catch(Exception e) {
		e.printStackTrace();
	    }

		}
	    
	   
	}



}
