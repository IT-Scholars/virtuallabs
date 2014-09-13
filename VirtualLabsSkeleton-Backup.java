
/**
 * VirtualLabsSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */
    package edu.fiu.cis.acrl.virtuallabs.ws; 
    
    import edu.fiu.cis.acrl.virtuallabs.server.debug.DebugTools;
    
    import java.io.File;

    import java.io.FileInputStream;
    import java.util.Calendar;
    import java.util.GregorianCalendar;
    import java.util.Arrays;
    import java.util.List;
    import java.util.Iterator;
    import javax.xml.stream.XMLInputFactory;
    import javax.xml.stream.XMLStreamReader;

    import edu.fiu.cis.acrl.vescheduler.client.*;
    import edu.fiu.cis.acrl.vescheduler.*;
    import edu.fiu.cis.acrl.vescheduler.ws.*;

    import java.util.Calendar;
    import java.util.Collection;
    import java.util.Collections;
    import java.util.ArrayList;
    import java.util.Date;
    import java.util.TimeZone;
    import java.sql.Timestamp;
    import org.apache.axis2.databinding.types.Time;
    import java.math.BigDecimal;
    import java.math.BigInteger;
    import java.text.DecimalFormat;
    import java.util.UUID;

    import org.apache.axis2.description.AxisService;
    import org.apache.axis2.context.ConfigurationContext;
    import org.apache.axis2.engine.ServiceLifeCycle;

    import edu.fiu.cis.acrl.virtuallabs.*;
    import edu.fiu.cis.acrl.virtuallabs.server.*;
    import edu.fiu.cis.acrl.virtuallabs.server.db.VirtualLabsDB;
    import edu.fiu.cis.acrl.virtuallabs.server.translators.*;
    import edu.fiu.cis.acrl.virtuallabs.server.db.*;

    import edu.fiu.cis.acrl.vescheduler.ws.*;
    import edu.fiu.cis.acrl.vescheduler.ws.vetypes.*;
    
    /**
     *  VirtualLabsSkeleton java skeleton for the axisService
     */
    public class VirtualLabsSkeleton implements ServiceLifeCycle {

    	private static VirtualLabsDB virtualLabsDB;
    	private static ExpiredAppRemoverAgent removerAgent;
    	// private String epr = "http://localhost:8080/axis2/services/VEScheduler";
    	private String epr = "http://ita-provisioner.cis.fiu.edu:8080/axis2/services/VEScheduler";

    	public void startUp(ConfigurationContext ignore, AxisService service) {

    		System.out.println(">> Starting VirtualLabs");

    		VirtualLabsSettings settings = null;

    		try {
    			settings = VirtualLabsSettings.instance();
    		} catch (Exception e) {
    			System.out.println(">> Error in settings");
    			throw new Error(e.getMessage());

    		}

    		virtualLabsDB = new VirtualLabsDB();
    		System.out.println(">> Starting VirtualLabs: Connecting to the vldb");
    		virtualLabsDB.connect(settings.getDbUser(), settings.getDbPassword(),
    				settings.getDbHost(), settings.getDbName());

    		// this thread checks that the expired appointments are cancelled
    		// removerAgent = new ExpiredAppRemoverAgent(settings.getDbUser(),
    		// settings.getDbPassword(), settings.getDbHost(), settings.getDbName(),
    		// settings.getPeriod());
    		// (new Thread(removerAgent)).start();

    	}

    	public void shutDown(ConfigurationContext cc, AxisService service) {
    		virtualLabsDB.close();
    	}

    	/**
    	 * Reads a VE description from an XML file and returns its AXIOM
    	 * representation
    	 */
    	public static VirtualEnvironmentType readVE(File veFile) throws Exception {

    		XMLInputFactory xif = XMLInputFactory.newInstance();
    		XMLStreamReader reader = xif.createXMLStreamReader(new FileInputStream(
    				veFile));
    		return VirtualEnvironmentType.Factory.parse(reader);

    	}
    	
    	/**
    	 * Auto generated method signature
    	 * 
    	 * @param cancelAppointmentRequest
    	 */

    	public CancelAppointmentResponse cancelAppointment(
    			CancelAppointmentRequest cancelAppointmentRequest) {
    		
    		System.out.println("[cancelAppointment]");
    		
    		CancelAppointmentResponse resp = new CancelAppointmentResponse();
    		
    		
    		//Get id from the request
    		String id = cancelAppointmentRequest.getId();

			try {

				//Set up request to call cancelScheduledVE		
				VESchedulerStub stub = new VESchedulerStub(epr);
				CancelScheduledVERequest request = new CancelScheduledVERequest();
				request.setSchId(id);
				CancelScheduledVEResponse veresp = stub.cancelScheduledVE(request);

				if (veresp.getSuccess()) {

					resp.setSuccess(true);
					resp.setReason("Appointment has been cancelled");

				} else {
					resp.setSuccess(false);
					resp.setReason(veresp.getMessage());

				}

	
			} catch (Error e) {
				resp.setSuccess(false);
				resp.setReason(e.getMessage());
	
			} catch (Exception ex) {
	
				ex.printStackTrace();
				resp.setSuccess(false);
				resp.setReason(ex.getMessage());
	
			}
    		
    		
    		return resp;
    	}

    	/**
    	 * Auto generated method signature
    	 * 
    	 * @param cancelAllAppointmentsRequest
    	 */

    	public CancelAllAppointmentsResponse cancelAllAppointments(
    			CancelAllAppointmentsRequest cancelAllAppointmentsRequest) {
    		
    		System.out.println("[cancelAllAppointments]");
    		
    		String affiliationId = cancelAllAppointmentsRequest.getAffiliationId();

    		CancelAllAppointmentsResponse resp = new CancelAllAppointmentsResponse();
    		List<DbAppointment> dbapps = virtualLabsDB.getAppointments(affiliationId);

    		Appointment[] apps = AppointmentTranslator.toAxisRepresentation(dbapps);
    		List<Appointment> appsList = Arrays.asList(apps);
    		
    		try {
    			int cont = 0;
    			int cancelled = 0;
    			
    			Iterator<Appointment> itrApp = appsList.iterator();
    			while (itrApp.hasNext()) {
    				
    				Appointment app = itrApp.next();

    				//Set up request to call cancelScheduledVE
					VESchedulerStub stub = new VESchedulerStub(epr);
					CancelScheduledVERequest request = new CancelScheduledVERequest();
					request.setSchId(app.getId());
					CancelScheduledVEResponse veresp = stub.cancelScheduledVE(request);

					if (veresp.getSuccess()) {
						cancelled++;
					}

    			}

    			if (cancelled < appsList.size()) {
    				resp.setSuccess(false);
    				resp.setReason(appsList.size() - cancelled+ " appointments could not be cancelled");

    			} else {
    				
    				resp.setSuccess(true);
    				resp.setReason("Appointments cancelled");
    				System.out.println("Appointments cancelled");

    			}

    		} catch (Error e) {
    			resp.setSuccess(false);
    			resp.setReason(e.getMessage());

    		} catch (Exception ex) {

    			ex.printStackTrace();
    			resp.setSuccess(false);
    			resp.setReason(ex.getMessage());

    		}

    		return resp;
    	}

    	/**
    	 * Auto generated method signature
    	 * 
    	 * @param getUserAppointmentsRequest
    	 */

    	public GetUserAppointmentsResponse getUserAppointments(
    			GetUserAppointmentsRequest getUserAppointmentsRequest) 
    	{
    		
    		System.out.println("[getUserAppointments]");
    		
    		//Prepare response
    		List<Appointment> allAppointments = new ArrayList<Appointment>();
    		GetUserAppointmentsResponse resp = new GetUserAppointmentsResponse();
    		
    		
    		//Get start and end date for the request
    		Calendar start = getUserAppointmentsRequest.getStart();
    		Calendar end = getUserAppointmentsRequest.getEnd();

    		
    		
    		//Return an empty array of appointments in case the request is invalid
    		try {
    			String username = getUserAppointmentsRequest.getUserName();
    			
    			if (!virtualLabsDB.userExists(username)) {
    				System.out.println("User does not exist");
    				
    			}else if (end.before(start)  || !start.before(end)) {
    				System.out.println("Start date must be before end date");

    			}else{
    			
    				//If the request is valid, get availability and schedule
    				List<Appointment> availableSlots = getAvailability(username, start, end);
    				List<Appointment> scheduledApps = getScheduledAppointments(username, start, end);
    				
    				//Merge both lists which means checking overapping and split availability in days
    				allAppointments = MergeLists(availableSlots, scheduledApps);
    	
    				// Add appointments to the response list
    				Iterator<Appointment> itrAllApps = allAppointments.iterator();
    				while (itrAllApps.hasNext()) {
    					Appointment app = itrAllApps.next();
    					resp.addAppointments(app);
    				}
    			}

    		} catch (Error e) {
    			e.printStackTrace();

    		} catch (Exception ex) {
    			ex.printStackTrace();
    		}

    		return resp;
    	}
    	/**
    	 * Auto generated method signature
    	 * 
    	 * @param modifyAppointmentRequest
    	 */

    	public ModifyAppointmentResponse modifyAppointment(
    			ModifyAppointmentRequest modifyAppointmentRequest) {


    		ModifyAppointmentResponse resp = new ModifyAppointmentResponse();

    		try {

    			System.out.println("[modifyAppointment]");

    			DbAppointment app = virtualLabsDB.getAppointment(modifyAppointmentRequest.getId());

    			Calendar start = modifyAppointmentRequest.getStart();
    			Calendar end = modifyAppointmentRequest.getEnd();
    			Calendar now = Calendar.getInstance();
    			
    			//Check validity of dates
    			if (end.before(start) || !start.before(end)) {
    				resp.setSuccess(false);
    				resp.setReason("End date must be before start date");
    				return resp;
    			} else if (start.before(now)) {

    				resp.setSuccess(false);
    				resp.setReason("Dates are not valid");
    				return resp;
    			}

    			
    			//Check that the modification does not overlap with existing appointments
    			app.setStart(start);
    			app.setEnd(end);
    			Appointment axisApp = AppointmentTranslator.toAxisRepresentation(app);
    			
    			if(!isOverlappingWithScheduledAppointments(axisApp))
    			{

    				//Set request to  call modifyScheduledVE
    				VESchedulerStub stub = new VESchedulerStub(epr);
    				ModifyScheduledVERequest request = new ModifyScheduledVERequest();
    				request.setSchId(app.getVeId());
    				TimePeriod period = new TimePeriod();
        			period.setStartTime(start);
        			period.setEndTime(end);
    				request.setTimePeriod(period);
    	
    				ModifyScheduledVEResponse veresp = stub.modifyScheduledVE(request);
    				
    				//If response was successful, get the new ve_id and add it to the db
    				if (veresp.getSuccess()) {
    					String id = veresp.getSchId().toString();
    					app.setVeId(id);
    					virtualLabsDB.scheduleAppointment(app);
    					resp.setSuccess(true);
    					resp.setReason("Appointment has been updated");
    					System.out.println(">> Sending response");
    	
    				} else {
    					resp.setSuccess(false);
    					resp.setReason(veresp.getMessage());
    				}
    				
    			}else
    			{
    				resp.setSuccess(false);
    				resp.setReason("Dates overlap with an existing appointment");

    				
    			}

    		} catch (Error e) {
    			e.printStackTrace();
    			resp.setSuccess(false);
    			resp.setReason("Error:"+e.getMessage());

    		} catch (Exception e) {

    			e.printStackTrace();
    			resp.setSuccess(false);
    			resp.setReason("Exception:"+e.getMessage());

    		}
    		return resp;

    	}
    	

    	/**
    	 * Auto generated method signature
    	 * 
    	 * @param scheduleAppointmentsRequest
    	 */

    	public ScheduleAppointmentsResponse scheduleAppointments(
    			ScheduleAppointmentsRequest scheduleAppointmentsRequest) {

    		ScheduleAppointmentsResponse resp = new ScheduleAppointmentsResponse();
    		
    		//Get the request with the list of appointments
    		Appointment[] apps = scheduleAppointmentsRequest.getAppointment();
    		
    		//Create a UUID for this batch of appointments
    		UUID affiliationId = UUID.randomUUID();

    		try {
    			
    			//Get the instance id for the course and resource type of these scheduling request
    			String ve_type = virtualLabsDB.getVEInstance(apps[0].getCourse(),apps[0].getResourceType());

    			//	If request fields are incomplete, return the request as it is
    			if (!virtualLabsDB.userExists(apps[0].getUserName())|| ve_type == null) {
    				resp.setAppointment(apps);
    				System.out.println("[scheduleAppointments] Some request parameters do not exist");
    				return resp;
    			}
    			
    			
    			// For every appointments in the request, make a call to ScheduleVE
    			List<Appointment> appsList = Arrays.asList(apps);
    			Iterator<Appointment> itrApp = appsList.iterator();
    			while (itrApp.hasNext()) {

    				Appointment appAxis = itrApp.next();
    				
    				//First check that the new appointment does not overlap with existing
    				//scheduled appointments
    				if(!isOverlappingWithScheduledAppointments(appAxis))
    				{
    					
    					ScheduleVERequest request = new ScheduleVERequest();
    					Calendar start = appAxis.getStart();
    					Calendar end = appAxis.getEnd();
    					Calendar now = Calendar.getInstance();
    					
    					//Check dates are correct first.
    					if (end.before(start)  || !start.before(end)) {
    						System.out.println("[scheduleAppointments] Start date must be before end date");
    						resp.addAppointment(appAxis);
    	
    					} else if (start.before(now)) {
    	
    						System.out.println("[scheduleAppointments] Dates are not valid");
    						resp.addAppointment(appAxis);
    	
    					}else{//If the times are ok, set up the request and call
    						
    						request.setVe(readVE(new File(ve_type)));
    						
    	    				TimePeriod period = new TimePeriod();
    	        			period.setStartTime(start);
    	        			period.setEndTime(end);
    	    				request.setTimePeriod(period);
    	    				
    						request.setStore(true);
    	
    						// Certificates are not persistent
    						if (appAxis.getResourceType().toUpperCase().equals("CERTIFICATE")) {
    							request.setStore(false);
    	
    						}
    						// Get VE Instance Id
    						String ve_id = virtualLabsDB.getVEInstanceId(appAxis.getCourse(), 
																		appAxis.getResourceType().toUpperCase(),
																		appAxis.getUserName(), 
																		"ACTIVE");
    						
    						//If aninstance id exists for this appointment, use it to schedule
    						if (ve_id != null) {
    							scheduleAppointmentWithVEId(appAxis, request, ve_id, affiliationId);
    	
    						} else {//If not, schedule wihtout id
    							scheduleAppointmentWithoutVEId(appAxis, request, affiliationId);
    	
    						}
	
    						//Add the appointment to the responde list of appointments
    						resp.addAppointment(appAxis);
    					}
    				}else//If it overlaps, return the new appointment without an id
    				{
    					
    					resp.addAppointment(appAxis);
    				}


    			}
    		} catch (Exception ex) {

    			resp.setAppointment(apps);
    			ex.printStackTrace();
    		}

    		return resp;

    	}
    	
    	
    	/*
    	 *scheduleAppointmentWithoutVEId
    	 *This functions creates a request for ScheduleVE knowing that there is no
    	 *an instance id for the user in question. The scheduleVE response return 
    	 *a new id
    	 */

    	public void scheduleAppointmentWithoutVEId(Appointment appAxis,
    												ScheduleVERequest request, 
    												UUID affiliationId) 
    	{
    		
    		
    		try {
    			// Create request
    			VESchedulerStub stub = new VESchedulerStub(epr);
    			// Make the call again WITHOUT ID
    			request.setVeInsId(null);
    			ScheduleVEResponse veresp = stub.scheduleVE(request);

    			if (veresp.getSuccess()) {

    				String id = veresp.getSchId().toString();
    				// Add new instance in DB
    				virtualLabsDB.addVEInstance(id, appAxis.getUserName(),
    						appAxis.getCourse(), appAxis.getResourceType());
    				appAxis.setId(id);
    				appAxis.setAvailabilityStatus("SCHEDULED");
    				appAxis.setAffiliationId(affiliationId.toString());
    				
    				/**To Local representation means to create an instance of 
    				 * DbAppointment which is used in db methods
    				 */
    				DbAppointment app = AppointmentTranslator.toLocalRepresentation(appAxis);
    				virtualLabsDB.scheduleAppointment(app);
    				appAxis = addActionsToAppointment(appAxis);
    				System.out.println("[scheduleAppointments] Appointment scheduled");
    			} else {
    				System.out.println("Appointment has not been scheduled");
    			}

    		} catch (Error e) {
    			e.printStackTrace();

    		} catch (Exception ex) {

    			ex.printStackTrace();

    		}

    	}

    	
    	/**
    	 * scheduleAppointmentWithVEId
    	 * In case the appintmente sto schedule belong to a existing ve instance,
    	 * this creates a request with that id included.
    	 * 
    	 */
    	public void scheduleAppointmentWithVEId(
    			Appointment appAxis,
    			ScheduleVERequest request, String ve_id, UUID affiliationId) {

    		try {
    			// Create request
    			VESchedulerStub stub = new VESchedulerStub(epr);
    			request.setVeInsId(ve_id);

    			// Make the call
    			ScheduleVEResponse veresp = stub.scheduleVE(request);
    			if (veresp.getSuccess()) {

    				String id = veresp.getSchId().toString();
    				if (ve_id == null)
    					virtualLabsDB.addVEInstance(id, appAxis.getUserName(),
    							appAxis.getCourse(), appAxis.getResourceType());
    				appAxis.setId(id);
    				appAxis.setAvailabilityStatus("SCHEDULED");
    				appAxis.setAffiliationId(affiliationId.toString());
    				DbAppointment app = AppointmentTranslator.toLocalRepresentation(appAxis);
    				virtualLabsDB.scheduleAppointment(app);
    				appAxis = addActionsToAppointment(appAxis);
    				System.out
    				.println("[scheduleAppointments] Appointment scheduled");

    			} else {
    				scheduleAppointmentWithoutVEId(appAxis, request, affiliationId);
    			}

    		} catch (Error e) {
    			e.printStackTrace();

    		} catch (Exception ex) {

    			ex.printStackTrace();

    		}

    	}

    	/**
    	 * Add actions instances to the appointment that is being passed,
    	 * depending on their availabilty type
    	 * 
    	 */
    	public Appointment addActionsToAppointment(
    			Appointment appAxis) {

    		Calendar now = Calendar.getInstance();
    	
    		if (!appAxis.getEnd().before(now))// Check if appointments is an old one
    		{

    			Action cancel = new Action();
    			cancel.setType("cancel");
    			cancel.setContent(appAxis.getId());
    			appAxis.addAction(cancel);

    			Action cancelAll = new Action();
    			cancelAll.setType("cancel all");
    			cancelAll.setContent(appAxis.getAffiliationId());
    			appAxis.addAction(cancelAll);

    			Action edit = new Action();
    			edit.setType("edit");
    			edit.setContent(appAxis.getId());
    			appAxis.addAction(edit);
    		}

    		Action info = new Action();
    		info.setType("info");
    		info.setContent("");
    		appAxis.addAction(info);

    		return appAxis;

    	}
    	
    	/*
    	 * Function that checks id an scheduled appointment overlaps with app.
    	 * 	
    	 */
    	
    	public boolean isOverlappingWithScheduledAppointments(Appointment app)
    	{
    		  		
    		List<Appointment> scheduledApps = getScheduledAppointments(app.getUserName(), Calendar.getInstance(), null);   				
    		Iterator<Appointment> itrApp = scheduledApps.iterator();
    		
    		while(itrApp.hasNext())
    		{
    			Appointment schApp = itrApp.next();
    			
    			if(app.getId()!=null)
    			{
    				if(!app.getId().equals(schApp.getId()))
    				{
    					if(AreOverlapping(app, schApp))
    						return true;
    				}
    			}
    			
    		}
    		
    		return false;
    	}
  
    
    	/*
    	 * 
    	 * Merges schedule and availability so there is no overlapping and 
    	 * availability is returned id day periods
    	 */
    	

    	public List<Appointment> MergeLists(List<Appointment> available,
	    									List<Appointment> scheduled
	    									) 
    	{

    		List<Appointment> mergedApps = new ArrayList<Appointment>();

    		//Flag to add schedule apps just once
    		boolean firstLoop = true;
    		
    		//Go over available slots first
    		Iterator<Appointment> itrAvail = available.iterator();
    		while (itrAvail.hasNext()) {
    			
    			Appointment availApp = itrAvail.next();
    			
    			boolean isAvailable = true;
    			List<Appointment> overlappingApps = new ArrayList<Appointment>();
    		
    			//Now go over schedule apps
    			Iterator<Appointment> itrSch = scheduled.iterator();
    			while (itrSch.hasNext()) {
    				
    				Appointment schApp = itrSch.next();
    				
    				
    				//Add just once of it is the first loop
    				if (firstLoop)
    					mergedApps.add(schApp);

    				//If they overlap, set available slot as unavailable(!isAvailable)
    				if (AreOverlapping(schApp, availApp)) {

    					if (isAvailable)
    						isAvailable = false;

    					//Keep track of the apps that overlap
    					overlappingApps.add(schApp);

    				} 

    			}
    			
    			
    			firstLoop = false;
    			
    			//If any schedule overlap with the available slot, add it to the list as it is
    			if (isAvailable) {
    				mergedApps.add(availApp);

    			} 
    			//If not, remove the slot the overlaps and return a list of remaining available slots
    			else {
    				List<Appointment> availableRemains = new ArrayList<Appointment>();
    				availableRemains = getNewAvailableSlots(overlappingApps,availApp);

    				Iterator<Appointment> itrRem = availableRemains.iterator();
    				while (itrRem.hasNext()) {

    					Appointment newAvalableSlot = itrRem.next();
    					mergedApps.add(newAvalableSlot);

    				}
    			}

    		}

    		return mergedApps;
    	}

    	
    	/*
    	 *Remove the time periods that are overlapping from the availability slots 
    	 * 
    	 */
    	public List<Appointment> getNewAvailableSlots(List<Appointment> overlappingApps,
    												  Appointment availableSlot
    												  ) 
	  {
    		List<Appointment> availableRemains = new ArrayList<Appointment>();
    		
    		//In order to do this, the Axis generated appointment class was altered
    		Collections.sort(overlappingApps);

    		//Create a copy of this appointment
    		Appointment availApp = AppointmentTranslator.copy(availableSlot);

    		Iterator<Appointment> itrApp = overlappingApps.iterator();
    		while (itrApp.hasNext()) {
    			
    			//This condition is necessary when the available slot being analyzed
    			//has been "absorbed" by the overlapping scheduled apps
    			if(availApp == null) break;
    			    			
    			Appointment app = itrApp.next();

    			Appointment remain = new Appointment();
    			remain = AppointmentTranslator.copy(availApp);

    			Calendar startSch = app.getStart();
    			Calendar endSch = app.getEnd();
    			Calendar startAvail = availApp.getStart();
    			Calendar endAvail = availApp.getEnd();

    			//Check that the schedule app starts before or at the same time that the available slot
    			if (startSch.before(startAvail) || startSch.equals(startAvail)) {
    				
    				//Check that the schedule app ends before the available slot
    				if (endSch.before(endAvail))
    				{
    					//Set new start and end date that would start when the schedule app ends
    					availApp.setStart(endSch);
    					availApp.setEnd(endAvail);
    					
    				} else {
    					//This means that the schedule app ate the available slot so set it as null
    					availApp = null;
    				}
    			//If it got here, it means that the available slot starts before the schedule app
    			} else {
    				
    				//Check now that the available slot ends after or exactly when the schedule app starts
    				if (endAvail.after(startSch) || endAvail.equals(startSch)) {
    					
    					//Here a new slot was created that ends when the schedule appointments starts
    					remain.setStart(startAvail);
    					remain.setEnd(startSch);
    					availableRemains.add(remain);

    					//If the original available slot ends after the schedule app, set the new
    					//start and end for the available slot that is being analyzed
    					if (endAvail.after(endSch)) {
    						availApp.setStart(endSch);
    						availApp.setEnd(endAvail);

    					} else {
    						//The available slot ends exactly when the schedule app ended so it was absorbed.
    						availApp = null;
    					}
    				}

    			}

    		}

    		return availableRemains;
    	}

    	/*
    	 * Check if two appointments overlap
    	 * 
    	 */
    	public boolean AreOverlapping(
    			Appointment schApp,
    			Appointment availApp) {
    		Calendar startSch = schApp.getStart();
    		Calendar endSch = schApp.getEnd();

    		Calendar startAvail = availApp.getStart();
    		Calendar endAvail = availApp.getEnd();

    		if ((startSch.before(endAvail) || startSch.equals(endAvail))
    				&& (startAvail.before(endSch) || startAvail.equals(endSch)))
    			return true;
    		return false;

    	}

    	/*
    	 * Get all scheduled appointments for this user
    	 * 
    	 */
    	public List<Appointment> getScheduledAppointments(String username, Calendar start, Calendar end) {
    		
    		
    		//If there is no end date, set it up as two months from now
    		if(end==null)
    		{
    			end = Calendar.getInstance();
    			end.set(Calendar.MONTH, end.get(Calendar.MONTH)+2);
    		}
    		
    		System.out.println("[getScheduledAppointments]");
    		
    		List<Appointment> scheduledApps = new ArrayList<Appointment>();	
    		List<Appointment> veScheduleApps = getScheduledVE(username, start, end);
    		    			
    		Iterator<Appointment> itrVE = veScheduleApps.iterator();
    			    			
			boolean exists = false;
			
			while (itrVE.hasNext()) {
				
				Appointment veApp = itrVE.next();				
				DbAppointment dbApp = virtualLabsDB.getAppointment(veApp.getId());
				veApp = addActionsToAppointment(veApp);
				
				if(dbApp!=null)
				{
					veApp.setUserName(username);
					veApp.setAffiliationId(dbApp.getAffiliationId());
					scheduledApps.add(veApp);
				}

			}

    		return scheduledApps;

    	}
    	
    	/*
    	 * Calls GetVEInsSchedule 
    	 * 
    	 */
    	
    	public List<Appointment> getScheduledVE(String username, Calendar start, Calendar end) {

    		List<Appointment> scheduledAppointments = new ArrayList<Appointment>();

    		try {
    			
    			//Set up the request
    			VESchedulerClient client = new VESchedulerClient(epr);
    			VESchedulerStub stub = new VESchedulerStub(epr);
    			GetVEInsScheduleRequest request = new GetVEInsScheduleRequest();
    			
				TimePeriod period = new TimePeriod();
    			period.setStartTime(start);
    			period.setEndTime(end);
				request.setTimePeriod(period);

    			Collection<String> resources = virtualLabsDB.getResources(username);
    			Collection<String> courses = virtualLabsDB.getCourses(username);

    			List<String> ve_instance_id = new ArrayList<String>();
    			
    			//Get all the resource-course combinations for this user
    			Iterator<String> itrRes = resources.iterator();
    			while (itrRes.hasNext()) {
    				String resource = itrRes.next();
    				
    				Iterator<String> itrCourse = courses.iterator();
    				while (itrCourse.hasNext()) {
    					String course = itrCourse.next();
    					
    					//Get instance id for this combination
    					String ve_id = virtualLabsDB.getVEInstanceId(course,resource, username, "ACTIVE");
   					
    					//If instance exists, call the ws
						if(ve_id!=null) {
							
							request.setVeInsId(ve_id);							
							GetVEInsScheduleResponse veresp = stub.getVEInsSchedule(request);
							
							if(veresp.getSchedule()!= null)
							{
								ScheduledEvent[] schedule = veresp.getSchedule();								
								
								//Translate the schedule into an array of appointments
								Appointment[] scheduledAppTempArr = AppointmentTranslator.toAxisRepresentation(schedule, resource, course, "SCHEDULED", username, null);
								
								//Add appointments in the new array to the response
								List<Appointment> scheduledAppTempList = Arrays.asList(scheduledAppTempArr);
								Iterator<Appointment> itrAvailApp = scheduledAppTempList.iterator();
								while (itrAvailApp.hasNext()) {
									Appointment appAxis = itrAvailApp.next();
									scheduledAppointments.add(appAxis);
								}
							}
						}

    				}

    			}

    		} catch (Error e){
    			e.printStackTrace();
    			
    		} catch (Exception ex) {
    			ex.printStackTrace();
    			
    		}

    		return scheduledAppointments;
    	}

    	/*
    	 * Get available slots from ve scheduler and returns it in day slots
    	 * 
    	 */
    	public List<Appointment> getAvailability(String username, Calendar start, Calendar end) {
    		
    		System.out.println("[getAvailability]");
    		
    		
    		//Prepare response
    		List<Appointment> availableAppointments = new ArrayList<Appointment>();

    		try {
    			
    			
    			//Create client instance
    			VESchedulerClient client = new VESchedulerClient(epr);
    			VESchedulerStub stub = new VESchedulerStub(epr);
    			
    			//Set the request
    			GetVEAvailabilityRequest request = new GetVEAvailabilityRequest();
				TimePeriod period = new TimePeriod();
    			period.setStartTime(start);
    			period.setEndTime(end);
				request.setTimePeriod(period);

    			//Get courses and resources of the user
    			Collection<String> resources = virtualLabsDB.getResources(username);
    			Collection<String> courses = virtualLabsDB.getCourses(username);

    			
    			List<String> ve_instance_id = new ArrayList<String>();
    			
    			//Get every ve_type of every course-resourse combination for this user
    			Iterator<String> itrRes = resources.iterator();
    			while (itrRes.hasNext()) {
    				
    				String resource = itrRes.next();
    				
    				Iterator<String> itrCourse = courses.iterator();
    				while (itrCourse.hasNext()) {
    					
    					String course = itrCourse.next();
    					
    					String ve_type = virtualLabsDB.getVEInstance(course,resource);
    					
    					
    					//If the ve_type exists, get the active ve_instance for this user
    					if(ve_type!=null)
    					{
    						request.setVe(client.readVE(new File(ve_type)));

    						String ve_id = virtualLabsDB.getVEInstanceId(course,resource, username, "ACTIVE");
    						
    						if (ve_id!=null) {
    							request.setVeInsId(ve_id);
    						} else {
    							request.setVeInsId(null);
    						}

    						//Call getVEAvailability
    						GetVEAvailabilityResponse veresp = stub.getVEAvailability(request);
    						TimePeriod[] slots = veresp.getAvailabilityRange();
    						List<Appointment> availableAppTempList = new ArrayList<Appointment>();
    						
    						
    						//Translate the time slots into appointments
    						if (slots != null) {

    							Appointment[] availableAppTempArr = 
    								AppointmentTranslator.toAxisRepresentation(slots, resource, course, "AVAILABLE", null, null);
    							availableAppTempList = Arrays.asList(availableAppTempArr);

    						}

    						
    						Iterator<Appointment> itrAvailApp = availableAppTempList.iterator();
    						while (itrAvailApp.hasNext()) {
    							Appointment appAxis = itrAvailApp.next();
    							
    							appAxis = addActionsToAvailableSlot(appAxis);

    							//Divide appointments in days
    							List<Appointment> availableAppByDays = new ArrayList<Appointment>();
    							availableAppByDays = getAppointmentSplittedInDays(appAxis);
    							Iterator<Appointment> itrByDay = availableAppByDays.iterator();
    							while (itrByDay.hasNext()) {
    								Appointment appByDay = itrByDay.next();
    								availableAppointments.add(appByDay);
    							}

    						}
    						
    					}


    				}

    			}

    		} catch (Error e) {
    			e.printStackTrace();

    		} catch (Exception ex) {
    			ex.printStackTrace();
    		}

    		
    		return availableAppointments;
    	}
    	
    	/*
    	 * 	This function divides the appointment passed as an arguments as a list of
    	 * appointments divided in days
    	 * 
    	 */
    	public List<Appointment> getAppointmentSplittedInDays(Appointment app)
    	{
    		List<Appointment> splittedApps = new ArrayList<Appointment>();
    		
    		Calendar start = Calendar.getInstance();
    		start.setTime(app.getStart().getTime());
    		
    		Calendar end = Calendar.getInstance();
    		end.setTime(app.getEnd().getTime());				
    		
    		boolean continues = true;
    		
    		while(continues)
    		{		
    			Appointment newApp = AppointmentTranslator.copy(app);
    			Calendar newStart = Calendar.getInstance();
    			newStart.setTime(start.getTime());
    			
    			Calendar newEnd = Calendar.getInstance();
			
    			newApp.setStart(newStart);

    			newEnd.setTime(start.getTime());
    			newEnd.set(Calendar.HOUR, 00);
    			newEnd.set(Calendar.MINUTE, 00);
    			newEnd.set(Calendar.SECOND, 00);
    			newEnd.set(Calendar.DAY_OF_MONTH, newEnd.get(Calendar.DAY_OF_MONTH)+1);
    			newEnd.set(Calendar.AM_PM , Calendar.AM);
    			
    			if(newEnd.before(end))
    			{
    				newApp.setEnd(newEnd);
    							
    			}
    			else
    			{
    			
    				continues = false;				
    			}

    			splittedApps.add(newApp);
	
    			start.setTime(newApp.getEnd().getTime());

    			if(start.after(end))
    			{
    				continues = false;
    			}
    		
    		}
    		
    		return splittedApps;		
    		
    	}

    	public Appointment addActionsToAvailableSlot(
    			Appointment appAxis) {

    		Action confirm = new Action();
    		confirm.setType("confirm");
    		confirm.setContent("");
    		appAxis.addAction(confirm);

    		Action edit = new Action();
    		edit.setType("edit");
    		edit.setContent("");
    		appAxis.addAction(edit);

    		Action info = new Action();
    		info.setType("info");
    		info.setContent("");
    		appAxis.addAction(info);

    		return appAxis;
    	}
    	

    	/*
    	 * Function that has not been used yet
    	 * 
    	 */
    	public List<Appointment> getUnavailability(
    			String username, Calendar start, Calendar end) {

    		List<Appointment> availableAppointments = new ArrayList<Appointment>();

    		try {

    			VESchedulerClient client = new VESchedulerClient(epr);
    			VESchedulerStub stub = new VESchedulerStub(epr);
    			GetVEUnavailabilityRequest request = new GetVEUnavailabilityRequest();
				TimePeriod period = new TimePeriod();
    			period.setStartTime(start);
    			period.setEndTime(end);
				request.setTimePeriod(period);

    			
    			Collection<String> resources = virtualLabsDB.getResources(username);
    			Collection<String> courses = virtualLabsDB.getCourses(username);

    			List<String> ve_instance_id = new ArrayList<String>();
    			Iterator<String> itrRes = resources.iterator();
    			while (itrRes.hasNext()) {
    				String resource = itrRes.next();
    				Iterator<String> itrCourse = courses.iterator();
    				while (itrCourse.hasNext()) {
    					String course = itrCourse.next();
    					String ve_type = virtualLabsDB.getVEInstance(course,
    							resource);
    					request.setVe(client.readVE(new File(ve_type)));

    					String ve_id = virtualLabsDB.getVEInstanceId(course,
    							resource, username, "ACTIVE");
    					if (!ve_type.equals("")) {
    						request.setId(ve_type);
    					}

    					GetVEUnavailabilityResponse veresp = stub.getVEUnavailability(request);
    					TimePeriod[] slots = veresp.getUnavailabilityRange();
    					Appointment[] availableAppTempArr = 
    						AppointmentTranslator.toAxisRepresentation(slots, resource, course, "SCHEDULED", null, null);
    					List<Appointment> availableAppTempList = Arrays.asList(availableAppTempArr);
    					Iterator<Appointment> itrAvailApp = availableAppTempList.iterator();
    					while (itrAvailApp.hasNext()) {
    						Appointment appAxis = itrAvailApp
    						.next();
    						appAxis = addActionsToAvailableSlot(appAxis);
    						availableAppointments.add(appAxis);

    					}

    				}

    			}

    		} catch (Error e)

    		{
    			e.printStackTrace();
    		} catch (Exception ex) {
    			ex.printStackTrace();
    		}

    		return availableAppointments;
    	}

        
    	
      	
    	/**
    	 * Auto generated method signature
    	 * 
    	 * @param editUserProfileRequest
    	 */

    	public EditUserProfileResponse editUserProfile(
    			EditUserProfileRequest editUserProfileRequest) {
    		System.out.println("editUserProfile");
    		EditUserProfileResponse resp = new EditUserProfileResponse();
    		try {
    			User user = new User(editUserProfileRequest.getUserName(),
    					editUserProfileRequest.getPassword(),
    					editUserProfileRequest.getUserRole(),
    					editUserProfileRequest.getTimeZone(),
    					editUserProfileRequest.getContactInfo());
    			virtualLabsDB.updateUser(user);
    			resp.setSuccess(true);
    			resp.setReason(user.toString() + " has been updated");

    		} catch (Error e) {
    			// System.out.println(e.getMessage());
    			resp.setSuccess(false);
    			resp.setReason(e.getMessage());

    		}

    		return resp;
    	}

    	/**
    	 * Auto generated method signature
    	 * 
    	 * @param getUserResourceTypesRequest
    	 */

    	public GetUserResourceTypesResponse getUserResourceTypes(
    			GetUserResourceTypesRequest getUserResourceTypesRequest) {
    		System.out.println("[getUserResourceTypes]");
    		GetUserResourceTypesResponse resp = new GetUserResourceTypesResponse();
    		try {
    			String username = getUserResourceTypesRequest.getUserName();
    			Collection<String> resources = virtualLabsDB.getResources(username);
    			System.out.println(resources);

    			for (String r : resources) {
    				resp.addResource(r);
    			}


    		} catch (Exception e) {
    			e.printStackTrace();
    			throw new Error(e.getMessage());

    		}

    		return resp;
    	}

    	/**
    	 * Auto generated method signature
    	 * 
    	 * @param enrollUserInCourseRequest
    	 */

    	public EnrollUserInCourseResponse enrollUserInCourse(
    			EnrollUserInCourseRequest enrollUserInCourseRequest) {
    		
    		System.out.println("[enrollUserInCourse]");
    		
    		EnrollUserInCourseResponse resp = new EnrollUserInCourseResponse();
    		
    		try {
    			String username = enrollUserInCourseRequest.getUserName();
    			String message = virtualLabsDB.enrollInCourse(username,enrollUserInCourseRequest.getCourseName());
    			resp.setSuccess(true);
    			resp.setReason(message);

    		} catch (Exception e) {
    			resp.setSuccess(false);
    			resp.setReason(e.getMessage());

    		}

    		return resp;
    	}

    	/**
    	 * getAvailableActions 
    	 * 
    	 */

    	public GetAvailableActionsResponse getAvailableActions(

    	) {
    		System.out.println("[getAvailableActions]");
    		GetAvailableActionsResponse resp = new GetAvailableActionsResponse();
    		try {

    			resp.addActions("EDIT");
    			resp.addActions("CONFIRM");
    			resp.addActions("INFO");
    			resp.addActions("CANCEL");


    		} catch (Exception e) {
    			e.printStackTrace();

    		}

    		return resp;
    	}


    	/**
    	 * Auto generated method signature
    	 * 
    	 * @param createUserProfileRequest
    	 */

    	public CreateUserProfileResponse createUserProfile(
    			CreateUserProfileRequest createUserProfileRequest) {
    		System.out.println("[createUserProfile]");
    		CreateUserProfileResponse resp = new CreateUserProfileResponse();
    		User user = new User(createUserProfileRequest.getUserName(),
    				createUserProfileRequest.getPassword(),
    				createUserProfileRequest.getUserRole(),
    				createUserProfileRequest.getTimeZone(),
    				createUserProfileRequest.getContactInfo());
    		try {

    			virtualLabsDB.addUser(user);
    			resp.setSuccess(true);
    			resp.setReason(user.toString() + " has been added");


    		} catch (Error e) {
    			resp.setSuccess(false);
    			resp.setReason(e.getMessage());

    		} catch (Exception ex) {
    			resp.setSuccess(false);
    			resp.setReason(ex.getMessage());

    		}

    		return resp;
    	}

    	/**
    	 * 
    	 * To make the list of available time zone ids more readable, this helper
    	 * funtion returns a string with GMT??? format that indicates the offset fo
    	 * the tiem zone id passed to this function.
    	 */
    	private String getTimeZoneOffsetStr(String timeZoneId) {

    		TimeZone tz = TimeZone.getTimeZone(timeZoneId);
    		String sign = "+";
    		int offset = tz.getRawOffset();
    		if (offset < 0) {
    			sign = "-";
    			offset = -(offset);
    		}
    		int min = offset / (1000 * 60);
    		int hour = min / 60;
    		min = min % 60;
    		DecimalFormat formatter = new DecimalFormat("00");
    		String tzStr = "GMT" + sign + formatter.format(hour) + ":"
    		+ formatter.format(min);
    		return tzStr;

    	}

    	/**
    	 * Auto generated method signature
    	 * 
    	 */

    	public GetAvailableTimeZoneIdsResponse getAvailableTimeZoneIds(

    	) {
    		GetAvailableTimeZoneIdsResponse resp = new GetAvailableTimeZoneIdsResponse();

    		TimeZone tZone = java.util.SimpleTimeZone.getDefault();
    		String[] tzids = tZone.getAvailableIDs();
    		System.out.println("[Time Zone, Get Available IDs]: ");
    		// just an initial value which always needs to be changed for the first
    		// time.
    		int preOffset = -1000;
    		for (String tzid : tzids) {

    			TimeZone tz = TimeZone.getTimeZone(tzid);
    			int offset = tz.getRawOffset();
    			String tzStr = getTimeZoneOffsetStr(tzid);
    			// Adding GMT??? before the time zone ids with the same offset
    			if (offset != preOffset) {
    				resp.addTimeZoneId(tzStr);
    				preOffset = offset;
    			}
    			tzStr += " " + tzid;
    			resp.addTimeZoneId(tzStr);
    		}

    		return resp;
    	}

    	
    	//Administrator functions
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param delHostRequest
         */
        
             public DelHostResponse delHost
              (
              DelHostRequest delHostRequest
              )
            {
                	 DelHostResponse resp =
                		 new  DelHostResponse();
                	 
                	 try
                	 {
               	 
             			VESchedulerClient client = new VESchedulerClient(epr);
             			VESchedulerStub stub = new VESchedulerStub(epr);
             			edu.fiu.cis.acrl.vescheduler.ws.DelHostRequest req = 
             				new edu.fiu.cis.acrl.vescheduler.ws.DelHostRequest();
                    	req.setHostId(delHostRequest.getId());
                    	   	 
                    	edu.fiu.cis.acrl.vescheduler.ws.DelHostResponse veresp = stub.delHost(req);

        				resp.setSuccess(resp.getSuccess());
        				resp.setMessage(resp.getMessage());
        				
                	 }catch(Exception e)
                	 {
                		e.printStackTrace();
         				resp.setSuccess(false);
        				resp.setMessage(e.getMessage());
                	 }
        				
        			return resp;
                     	 
                }
             
             
             
             
         /**
          * Auto generated method signature
          * 
                                      * @param getHostRequest
          */
         
                  public GetHostResponse getHost
                   (
                   GetHostRequest getHostRequest
                   )
             {
                	  DebugTools.println(DEBUG_LEVEL, "[VirtualLabsSkeleton - getHost] Inside!");
                	  
                 	 GetHostResponse resp =
                 		 new  GetHostResponse();
                 	 
                 	 try
                 	 {
                	 
              			VESchedulerClient client = new VESchedulerClient(epr);
              			VESchedulerStub stub = new VESchedulerStub(epr);
              			edu.fiu.cis.acrl.vescheduler.ws.GetHostRequest req = 
              				new edu.fiu.cis.acrl.vescheduler.ws.GetHostRequest();
                     	req.setHostId(getHostRequest.getId());
                     	   	 
                     	edu.fiu.cis.acrl.vescheduler.ws.GetHostResponse veresp = 
                     		stub.getHost(req);
                     	if(veresp.getSuccess())
                     	{
                     		
                     		edu.fiu.cis.acrl.vescheduler.ws.Host host = 
                     			veresp.getHost();
                     		Host hostResp = 
                     			new Host();
                     		hostResp.setId(host.getId());
                     		hostResp.setName(host.getName());
                     		hostResp.setSshPort(host.getSshPort());
                     		hostResp.setUsername(host.getUsername());
                     		hostResp.setPassword(host.getPassword());
                     		hostResp.setVeNumCap(host.getVeNumCap());
                     		hostResp.setVeFirstFreePort(host.getVeFirstFreePort());
                     		hostResp.setVePortNum(host.getVePortNum());
                     		hostResp.setActive(host.getActive());
                     		resp.setHost(hostResp);
                          	
                     	}
                     	
                     	

         				resp.setSuccess(veresp.getSuccess());
         				resp.setMessage(veresp.getMessage());
         				
                 	 }catch(Exception e)
                 	 {
                 		e.printStackTrace();
          				resp.setSuccess(false);
         				resp.setMessage(e.getMessage());
                 	 }
         				
                 	DebugTools.println(DEBUG_LEVEL, "[VirtualLabsSkeleton - getHost] Ready to get out!");
              	  
         			return resp;
            
             
             }
          
          /**
           * Auto generated method signature
           * 
                                       * @param setHostRequest
           */
          
               public SetHostResponse setHost
                (
                SetHostRequest setHostRequest
                )
          {
            	   DebugTools.println(DEBUG_LEVEL, "[VirtualLabsSkeleton - setHostRequest] Inside!");
            	   
            		 SetHostResponse resp =
                 		 new  SetHostResponse();
                 	 
                 	 try
                 	 {
                	 
              			VESchedulerClient client = new VESchedulerClient(epr);
              			VESchedulerStub stub = new VESchedulerStub(epr);
              			edu.fiu.cis.acrl.vescheduler.ws.SetHostRequest req = 
              				new edu.fiu.cis.acrl.vescheduler.ws.SetHostRequest();
              			
              			
              			edu.fiu.cis.acrl.vescheduler.ws.Host hostReq = 
              				new edu.fiu.cis.acrl.vescheduler.ws.Host();
              			
                 		Host host = 
                 			setHostRequest.getHost();
                 		
                 		hostReq.setId(host.getId());
                 		hostReq.setName(host.getName());
                 		hostReq.setSshPort(host.getSshPort());
                 		hostReq.setUsername(host.getUsername());
                 		hostReq.setPassword(host.getPassword());
                 		hostReq.setVeNumCap(host.getVeNumCap());
                 		hostReq.setVeFirstFreePort(host.getVeFirstFreePort());
                 		hostReq.setVePortNum(host.getVePortNum());
                 		hostReq.setActive(host.getActive());
                 		req.setHost(hostReq);
                 		

                     	edu.fiu.cis.acrl.vescheduler.ws.SetHostResponse veresp = 
                     		stub.setHost(req);

         				resp.setSuccess(veresp.getSuccess());
         				resp.setMessage(veresp.getMessage());
         				
                 	 }catch(Exception e)
                 	 {
                 		e.printStackTrace();
          				resp.setSuccess(false);
         				resp.setMessage(e.getMessage());
                 	 }
         				
                 	DebugTools.println(DEBUG_LEVEL, "[VirtualLabsSkeleton - setHostRequest] Ready to get out!");
             	   
            		return resp;
 
              
          }
               
               
       /**
        * Auto generated method signature
        * 
                                    * @param addHostRequest
        */
       
            public AddHostResponse addHost
             (
             AddHostRequest addHostRequest
             )
           {
            	 AddHostResponse resp =
             		 new  AddHostResponse();
             	 
             	 try
             	 {
            	 
          			VESchedulerClient client = new VESchedulerClient(epr);
          			VESchedulerStub stub = new VESchedulerStub(epr);
          			edu.fiu.cis.acrl.vescheduler.ws.AddHostRequest req = 
          				new edu.fiu.cis.acrl.vescheduler.ws.AddHostRequest();
          			
          			
          			edu.fiu.cis.acrl.vescheduler.ws.Host hostReq = 
          				new edu.fiu.cis.acrl.vescheduler.ws.Host();
          			
             		Host host = 
             			addHostRequest.getHost();
             		
             		hostReq.setId(host.getId());
             		hostReq.setName(host.getName());
             		hostReq.setSshPort(host.getSshPort());
             		hostReq.setUsername(host.getUsername());
             		hostReq.setPassword(host.getPassword());
             		hostReq.setVeNumCap(host.getVeNumCap());
             		hostReq.setVeFirstFreePort(host.getVeFirstFreePort());
             		hostReq.setVePortNum(host.getVePortNum());
             		hostReq.setActive(host.getActive());
             		req.setHost(hostReq);
             		

                 	edu.fiu.cis.acrl.vescheduler.ws.AddHostResponse veresp = 
                 		stub.addHost(req);

     				resp.setSuccess(veresp.getSuccess());
     				resp.setMessage(veresp.getMessage());
     				
             	 }catch(Exception e)
             	 {
             		e.printStackTrace();
      				resp.setSuccess(false);
     				resp.setMessage(e.getMessage());
             	 }
     				
     			return resp;
          }
            
        
        /**
         * Auto generated method signature
         * 
         */

         public GetHostListResponse getHostList
          (
          
          )
         {
        	 GetHostListResponse resp =
         		 new  GetHostListResponse();
         	 
         	 try
         	 {
        	 
      			VESchedulerClient client = new VESchedulerClient(epr);
      			VESchedulerStub stub = new VESchedulerStub(epr);

             	edu.fiu.cis.acrl.vescheduler.ws.GetHostListResponse veresp = 
             		stub.getHostList();
             	
             	if(veresp.getSuccess())
             	{
             		edu.fiu.cis.acrl.vescheduler.ws.Host[] hosts = veresp.getHost();
             		Host[] hostsResp = new Host[hosts.length];
             		for(int i=0;i<hosts.length;i++)
             		{
             			edu.fiu.cis.acrl.vescheduler.ws.Host host = hosts[i];
             			hostsResp[i] = new Host();                 		
             			hostsResp[i].setId(host.getId());
             			hostsResp[i].setName(host.getName());
             			hostsResp[i].setSshPort(host.getSshPort());
             			hostsResp[i].setUsername(host.getUsername());
             			hostsResp[i].setPassword(host.getPassword());
             			hostsResp[i].setVeNumCap(host.getVeNumCap());
             			hostsResp[i].setVeFirstFreePort(host.getVeFirstFreePort());
                 		hostsResp[i].setVePortNum(host.getVePortNum());
                 		hostsResp[i].setActive(host.getActive());

             			
             		}
             		
             		resp.setHost(hostsResp);
             	}

             	
 				resp.setSuccess(veresp.getSuccess());
 				resp.setMessage(veresp.getMessage());
 				
         	 }catch(Exception e)
         	 {
         		e.printStackTrace();
  				resp.setSuccess(false);
 				resp.setMessage(e.getMessage());
         	 }
 				
 			return resp;
        
         }      
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param setConfigurationRequest
         */

         public SetConfigurationResponse setConfiguration
          (
          SetConfigurationRequest setConfigurationRequest
          )
          {

     	 	SetConfigurationResponse resp = 
     	 			new SetConfigurationResponse();
     	 	
        	 try
        	 {
       	 
     			VESchedulerClient client = new VESchedulerClient(epr);
     			VESchedulerStub stub = new VESchedulerStub(epr);
     			edu.fiu.cis.acrl.vescheduler.ws.SetConfigurationRequest request = 
     				new edu.fiu.cis.acrl.vescheduler.ws.SetConfigurationRequest();
     			
     			TimePeriod userTimePeriod = new TimePeriod();
     			userTimePeriod.setStartTime(setConfigurationRequest.getUserStartTime());
     			userTimePeriod.setEndTime(setConfigurationRequest.getUserEndTime());
				request.setUserTimePeriod(userTimePeriod);
				
				TimePeriod adminTimePeriod = new TimePeriod();
				adminTimePeriod.setStartTime(setConfigurationRequest.getAdminStartTime());
				adminTimePeriod.setEndTime(setConfigurationRequest.getAdminEndTime());
				request.setAdminTimePeriod(adminTimePeriod);
         	 
            	edu.fiu.cis.acrl.vescheduler.ws.SetConfigurationResponse veresp = 
            		stub.setConfiguration(request);

				resp.setSuccess(resp.getSuccess());
				resp.setMessage(resp.getMessage());
				
        	 }catch(Exception e)
        	 {
        		e.printStackTrace();
 				resp.setSuccess(false);
				resp.setMessage(e.getMessage());
        	 }
				
			return resp;
             	 
        }
     

    
    
    /**
     * Auto generated method signature
     * 
     */
    
             public GetConfigurationResponse getConfiguration
              (
              
              )
        {

          	 	GetConfigurationResponse resp = 
          	 			new GetConfigurationResponse();
          	 	
             	 try
             	 {
            	 
          			VESchedulerClient client = new VESchedulerClient(epr);
          			VESchedulerStub stub = new VESchedulerStub(epr);
          			edu.fiu.cis.acrl.vescheduler.ws.GetConfigurationResponse  veresp= 
          				new edu.fiu.cis.acrl.vescheduler.ws.GetConfigurationResponse();
          			veresp = stub.getConfiguration();

     				if(veresp.getSuccess())
     				{
     					resp.setUserStartTime(veresp.getUserTimePeriod().getStartTime());
                     	resp.setUserEndTime(veresp.getUserTimePeriod().getEndTime());
                     	
                     	resp.setAdminStartTime(veresp.getAdminTimePeriod().getStartTime());
                     	resp.setAdminEndTime(veresp.getAdminTimePeriod().getEndTime()); 
                     	
                     	resp.setSuccess(true);
     					
     				}else{
     					resp.setSuccess(false);
     					resp.setMessage(veresp.getMessage());
     					
     				}
                 	         	              	
             	 }catch(Exception e)
             	 {
             		e.printStackTrace();
      				resp.setSuccess(false);
     				resp.setMessage(e.getMessage());
             	 }
     				
     			return resp;
    }
       
             
             /**
              * Auto generated method signature
              * 
                                          * @param getHostMaintenanceScheduleRequest
              */
             
                      public GetHostMaintenanceScheduleResponse getHostMaintenanceSchedule
                       (
                       GetHostMaintenanceScheduleRequest getHostMaintenanceScheduleRequest
                       )
                 {
                     //TODO : fill this with the necessary business logic
                     throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getHostMaintenanceSchedule");
             }
          


          
              
             /**
              * Auto generated method signature
              * 
                                          * @param modifyScheduledHostMaintenanceRequest
              */
             
                      public ModifyScheduledHostMaintenanceResponse modifyScheduledHostMaintenance
                       (
                       ModifyScheduledHostMaintenanceRequest modifyScheduledHostMaintenanceRequest
                       )
                 {
                     //TODO : fill this with the necessary business logic
                     throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#modifyScheduledHostMaintenance");
             }
          
              
             /**
              * Auto generated method signature
              * 
                                          * @param cancelScheduledHostMaintenanceRequest
              */
             
                      public CancelScheduledHostMaintenanceResponse cancelScheduledHostMaintenance
                       (
                       CancelScheduledHostMaintenanceRequest cancelScheduledHostMaintenanceRequest
                       )
                 {
                     //TODO : fill this with the necessary business logic
                     throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#cancelScheduledHostMaintenance");
             }
          
         
        
              
             /**
              * Auto generated method signature
              * 
                                          * @param getHostMaintenanceUnavailabilityRequest
              */
             
                      public GetHostMaintenanceUnavailabilityResponse getHostMaintenanceUnavailability
                       (
                       GetHostMaintenanceUnavailabilityRequest getHostMaintenanceUnavailabilityRequest
                       )
                 {
                     //TODO : fill this with the necessary business logic
                     throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getHostMaintenanceUnavailability");
             }
          
          
              
             /**
              * Auto generated method signature
              * 
                                          * @param scheduleHostMaintenanceRequest
              */
             
                      public ScheduleHostMaintenanceResponse scheduleHostMaintenance
                       (
                       ScheduleHostMaintenanceRequest scheduleHostMaintenanceRequest
                       )
                 {
                     //TODO : fill this with the necessary business logic
                     throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#scheduleHostMaintenance");
             }
          
       

        
              
             /**
              * Auto generated method signature
              * 
                                          * @param getHostMaintenanceAvailabilityRequest
              */
             
                      public GetHostMaintenanceAvailabilityResponse getHostMaintenanceAvailability
                       (
                       GetHostMaintenanceAvailabilityRequest getHostMaintenanceAvailabilityRequest
                       )
                 {
                     //TODO : fill this with the necessary business logic
                     throw new  java.lang.UnsupportedOperationException("Please implement " + this.getClass().getName() + "#getHostMaintenanceAvailability");
             }
  
  	public static void main(String [] args) {
    	
  		VirtualLabsSkeleton stub = new VirtualLabsSkeleton();
  		
		int hostId = 1;
    	GetHostRequest request = new GetHostRequest();
    	request.setId(hostId);
    	GetHostResponse getHostResp = stub.getHost(request);
		if (getHostResp.getSuccess()) 
			System.out.println("Host " + hostId + " exists. Its name is: " + getHostResp.getHost().getName());
		else
			System.out.println("Host " + hostId + " does NOT exist!");
		
  	}
 }
    