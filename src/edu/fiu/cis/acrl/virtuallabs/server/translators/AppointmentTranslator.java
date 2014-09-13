package edu.fiu.cis.acrl.virtuallabs.server.translators;

import edu.fiu.cis.acrl.virtuallabs.server.*;
import edu.fiu.cis.acrl.virtuallabs.ws.Appointment;
import edu.fiu.cis.acrl.tools.timeperiod.TimePeriod;
import edu.fiu.cis.acrl.tools.timeperiod.ScheduledEvent;

import java.util.List;
import java.util.Collection;
import java.util.Iterator;


/**
 * Translate to and from the Appointment class
 */ 
public class AppointmentTranslator {



    
    public static Appointment toAxisRepresentation(edu.fiu.cis.acrl.virtuallabs.ws.ModifyUserAppointmentRequest req) {
	
	    Appointment app = new Appointment();
		app.setId(req.getId());
		app.setStart(req.getStart());
		app.setEnd(req.getEnd());
	
	
		return app;

    }
    
    
    public static  DbAppointment toLocalRepresentation(edu.fiu.cis.acrl.virtuallabs.ws.ModifyUserAppointmentRequest req) {

	    DbAppointment app = new DbAppointment();
		app.setSchId(req.getId());
		app.setStart(req.getStart());
		app.setEnd(req.getEnd());
	
		return app;

    }
    
    
    public static  Appointment copy(Appointment app) {

        Appointment copy = new Appointment();  
    	copy.setStart(app.getStart());
    	copy.setEnd(app.getEnd());
    	copy.setUserName(app.getUserName());
    	copy.setResourceType(app.getResourceType());
    	copy.setCourse(app.getCourse());
    	copy.setAffiliationId(app.getAffiliationId());
    	copy.setId(app.getId());
    	copy.setAvailabilityStatus(app.getAvailabilityStatus());
    	copy.setAction(app.getAction());
    	return copy;

    }

    public static  Appointment[] toAxisRepresentation(Collection<DbAppointment> apps) {

    	Appointment[] axisApps = new Appointment[apps.size()] ;
    	
    	if(apps.size()>0)
    	{
	    	 Iterator itr = apps.iterator();
	    	 Appointment axisapp = null;
	    	 int cont = 0;
	         while (itr.hasNext()) {
	        	DbAppointment app = (DbAppointment) itr.next();     
	        	axisapp =  new Appointment();
	        	axisapp.setStart(app.getStart());
	        	axisapp.setEnd(app.getEnd());
	        	axisapp.setUserName(app.getUsername());
	        	axisapp.setResourceType(app.getResourceType());
	        	axisapp.setCourse(app.getCourse());
	        	axisapp.setAffiliationId(app.getAffiliationId());
	        	axisapp.setId(app.getSchId());
	        	axisapp.setAvailabilityStatus("SCHEDULED");
	        	
	        	
	        	axisApps[cont] = axisapp ;
	        	cont++;
	        }
    	}


    	return axisApps;

    }
    
    public static  Appointment toAxisRepresentation(DbAppointment app) {

    
    	Appointment axisapp =  new Appointment();
    	axisapp.setStart(app.getStart());
    	axisapp.setEnd(app.getEnd());
    	axisapp.setUserName(app.getUsername());	
    	axisapp.setResourceType(app.getResourceType());
    	axisapp.setCourse(app.getCourse());
    	axisapp.setAffiliationId(app.getAffiliationId());
    	axisapp.setId(app.getSchId());
    	axisapp.setAvailabilityStatus("SCHEDULED");
    	return axisapp;

    }
    
    public static  DbAppointment toLocalRepresentation(Appointment axisapp) {

        
    	DbAppointment app =  new DbAppointment();
    	app.setSchId(axisapp.getId());
    	app.setStart(axisapp.getStart());
    	app.setEnd(axisapp.getEnd());
    	app.setUsername(axisapp.getUserName());
    	app.setResourceType(axisapp.getResourceType());
    	app.setCourse(axisapp.getCourse());
    	app.setAffiliationId(axisapp.getAffiliationId());
    	return app;

    }
    
    
    
    public static  Appointment[] toAxisRepresentation(
    		ScheduledEvent[] schedule, 
    		String resourceType, 
    		String course, 
    		String status, 
    		String username, 
    		String id) {

    	Appointment[] axisApps = new Appointment[schedule.length] ;
    	
    	for(int i = 0; i<schedule.length;i++)
    	{

    	 	Appointment axisapp = null;
    	 	ScheduledEvent slot = schedule[i];   
        	axisapp =  new Appointment();
        	axisapp.setId(slot.getSchId());
        	axisapp.setStart(slot.getTimePeriod().getStartTime());
        	axisapp.setEnd(slot.getTimePeriod().getEndTime());
        	axisapp.setResourceType(resourceType);
        	axisapp.setCourse(course);
        	if(status!=null)
        		axisapp.setAvailabilityStatus(status);
        	if(username!=null)
            	axisapp.setUserName(username);
        	if(id!=null)
        		axisapp.setId(id);
  	
        	axisApps[i] = axisapp ;

    	}

    	return axisApps;
    	
    }

    public static  Appointment[] toAxisRepresentation(
    		TimePeriod[] slots, 
    		String resourceType, 
    		String course, 
    		String status, 
    		String username, 
    		String id) {

    	Appointment[] axisApps = new Appointment[slots.length] ;
    	
    	for(int i = 0; i<slots.length;i++)
    	{

    	 	Appointment axisapp = null;
    	 	TimePeriod slot = slots[i];   
        	axisapp =  new Appointment();
        	axisapp.setStart(slot.getStartTime());
        	axisapp.setEnd(slot.getEndTime());
        	axisapp.setResourceType(resourceType);
        	axisapp.setCourse(course);
        	if(status!=null)
        		axisapp.setAvailabilityStatus(status);
        	if(username!=null)
            	axisapp.setUserName(username);
        	if(id!=null)
        		axisapp.setId(id);
  	
        	axisApps[i] = axisapp ;

    	}

    	return axisApps;
    	
    }
    
    
   public static boolean AreEqual
   (
		   Appointment app1,
		   Appointment app2
   )
   {
	   
	   
	   if(app1.getEnd().getTime().equals(app2.getEnd().getTime()))
	   {
		   if(app1.getResourceType().equals(app2.getResourceType()))
		   {
			   if(app1.getCourse().equals(app2.getCourse()))
			   {
				   return true;
			   }
		   }
	   }
	   
	   
	   return false;
	   
   }
    
    public static Appointment[] toArray(List<Appointment> listapps)
    {
    	Appointment[] axisApps = new Appointment[listapps.size()] ;
    	
    	if(listapps.size()>0)
    	{
	    	 Iterator itr = listapps.iterator();
	    	 Appointment axisapp = null;
	    	 int cont = 0;
	         while (itr.hasNext()) {
	        	Appointment app = (Appointment) itr.next();             	
	        	axisApps[cont++] = app ;

	        }
    	}


    	return axisApps;
    	
    	
    	
    	
    }
    
    
    public static Appointment[] MergeAppointmentsArrays(Appointment[] a, Appointment[] b)
    {
    	int totalLength = a.length + b.length;
    	int i=0, j=0, cont = 0;
    	Appointment[] axisApps = new Appointment[totalLength] ;
    	for(i = 0; i < a.length ; i++)
    	{
    		axisApps[i] = a[i];
	
    	}

    	for(j = i; j < totalLength ; j++)
    	{  		
    		axisApps[j] = b[cont++];
    	}

    	return axisApps;
	
    }


}