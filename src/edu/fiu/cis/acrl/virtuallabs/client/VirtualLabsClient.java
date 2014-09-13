package edu.fiu.cis.acrl.virtuallabs.client;

import java.util.GregorianCalendar;

import edu.fiu.cis.acrl.virtuallabs.ws.Appointment;
import edu.fiu.cis.acrl.virtuallabs.ws.GetHostListRequest;
import edu.fiu.cis.acrl.virtuallabs.ws.Host;
import edu.fiu.cis.acrl.virtuallabs.ws.AddHostRequest;
import edu.fiu.cis.acrl.virtuallabs.ws.AddHostResponse;
import edu.fiu.cis.acrl.virtuallabs.ws.DelHostRequest;
import edu.fiu.cis.acrl.virtuallabs.ws.DelHostResponse;
import edu.fiu.cis.acrl.virtuallabs.ws.GetHostListResponse;
import edu.fiu.cis.acrl.virtuallabs.ws.GetHostRequest;
import edu.fiu.cis.acrl.virtuallabs.ws.GetHostResponse;
import edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsRequest;
import edu.fiu.cis.acrl.virtuallabs.ws.SetHostRequest;
import edu.fiu.cis.acrl.virtuallabs.ws.SetHostResponse;
import edu.fiu.cis.acrl.virtuallabs.ws.VirtualLabsStub;

import org.apache.axis2.AxisFault;

public class VirtualLabsClient {

    private VirtualLabsStub stub;

    public VirtualLabsClient(String epr) throws AxisFault {
	
    	stub = new VirtualLabsStub(epr);	
    
    }

    private GetHostResponse getHost(int hostId) throws Exception {
    	
    	GetHostRequest request = new GetHostRequest();
    	request.setId(hostId);
    	
    	return stub.getHost(request);
    	
    }
    
    private GetHostListResponse getHostList(boolean active) throws Exception {
    	
    	GetHostListRequest request = new GetHostListRequest();
    	request.setActive(active);
    	
    	return stub.getHostList(request);
    	
    }
    
    private AddHostResponse addHost(Host host) throws Exception {
    	
    	System.out.println("[AddHost] host name: " + host.getName());
    	
    	AddHostRequest request = new AddHostRequest();
    	request.setHost(host);
    	
    	return stub.addHost(request);
    	
    }
    
    private SetHostResponse setHost(Host host) throws Exception {
    	
    	SetHostRequest request = new SetHostRequest();
    	request.setHost(host);
    	
    	return stub.setHost(request);
    	
    }
    
    private DelHostResponse delHost(int hostId) throws Exception {
    	
    	DelHostRequest request = new DelHostRequest();
    	request.setId(hostId);
    	
    	return stub.delHost(request);
    	
    }
    

    /**********************
     * For testing purposes

     **********************/

    
    private void test() throws Exception {
	
    	System.out.println("Hello!");
    	ScheduleUserAppointmentsRequest schAppReq = new ScheduleUserAppointmentsRequest();
    	Appointment appointment = new Appointment();
    	appointment.setAction(null);
    	appointment.setAffiliationId(null);
    	appointment.setAvailabilityStatus("AVAILABLE");
    	appointment.setCourse("Kaseya 1");
    	appointment.setStart(new GregorianCalendar(2010,7-1,30,8,0));
    	appointment.setEnd(new GregorianCalendar(2010,7-1,30,10,0));
    	appointment.setId("");
    	appointment.setUserName("student");
    	appointment.setResourceType("CERTIFICATE");
    	Appointment[] appointments = new Appointment[1];
    	appointments[0] = appointment;
    	schAppReq.setAppointment(appointments);
    	stub.scheduleUserAppointments(schAppReq);
    	System.out.println("Bye!");
    	
    	/*
		int hostId = 1;
		GetHostResponse getHostResp = getHost(hostId);
		if (getHostResp.getSuccess()) 
			System.out.println("Host " + hostId + " exists. Its name is: " + getHostResp.getHost().getName());
		else
			System.out.println("Host " + hostId + " does NOT exist!");
		
		hostId = 3;
		getHostResp = getHost(hostId);
		if (getHostResp.getSuccess()) 
			System.out.println("Host " + hostId + " exists. Its name is: " + getHostResp.getHost().getName());
		else
			System.out.println("Host " + hostId + " does NOT exist!");
		
		GetHostListResponse getHostListResp = getHostList(true);
		if (getHostListResp.getSuccess()) {
			
			System.out.println("Host List: ");
			for (int i=0; i<getHostListResp.getHost().length; i++)
				System.out.println("  Host #" + getHostListResp.getHost()[i].getId() + " " +
						getHostListResp.getHost()[i].getName());
			
		}
		else
			System.out.println("Not host exists!");
		
		Host host = new Host();
		host.setId(0);
		host.setName("ita-host.cis.fiu.edu");
		host.setSshPort(22);
		host.setUsername("portal");
		host.setPassword("k4se*prt4l");
		host.setVeNumCap(8);
		host.setVeFirstFreePort(10000);
		host.setVePortNum(20);
		host.setActive(true);
		AddHostResponse addHostResp = addHost(host);
		if (addHostResp.getSuccess()) 
			System.out.println("New host was added successfully!");
		else
			System.out.println("New host was NOT added!");
		
		host = new Host();
		host.setId(0);
		host.setName("ita-host-2.cis.fiu.edu");
		host.setSshPort(22);
		host.setUsername("portal");
		host.setPassword("k4se*prt4l");
		host.setVeNumCap(8);
		host.setVeFirstFreePort(10000);
		host.setVePortNum(20);
		host.setActive(true);
		AddHostResponse addHostResp2 = addHost(host);
		if (addHostResp2.getSuccess()) 
			System.out.println("New host was added successfully!");
		else
			System.out.println("New host was NOT added!");
		
		host.setId(4);
		host.setUsername("portal2");
		host.setActive(false);
		SetHostResponse setHostResp = setHost(host);
		if (setHostResp.getSuccess()) 
			System.out.println("Host #" + host.getId() + " was modified successfully!");
		else
			System.out.println("Host #" + host.getId() + " was NOT modified successfully!");
		
		hostId = 1;
		DelHostResponse delHostResp = delHost(hostId);
		if (delHostResp.getSuccess())
			System.out.println("Host #" + hostId + " was deleted successfully!");
		else
			System.out.println("Host #" + hostId + " was NOT deleted successfully!");
		
		hostId = 3;
		DelHostResponse delHostResp2 = delHost(hostId);
		if (delHostResp2.getSuccess())
			System.out.println("Host #" + hostId + " was deleted successfully!");
		else
			System.out.println("Host #" + hostId + " was NOT deleted successfully!");
		*/
    }


	public static void main(String [] args) {
    	
    	if(args.length != 1) {
    		System.out.println("Usage: ./runclient.sh <port_numver>");
    		System.out.println("  Example: ./runclient.sh 8080");
    		return;
    	} 
    		
    	try {
		
    		String epr = "http://localhost:" + args[0] + "/axis2/services/VirtualLabs";
    		VirtualLabsClient client = new VirtualLabsClient(epr);

    		client.test();
    	}
    	catch(Exception e) {
    		System.out.println("Exception: " + e);
    		System.out.println("Message: " + e.getMessage());
    		e.printStackTrace();
    	}

    }

}
