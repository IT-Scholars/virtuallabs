package edu.fiu.cis.acrl.virtuallabs.server;

import edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsWithEncryptedPasswordResponse;
import edu.fiu.cis.acrl.virtuallabs.ws.ReserveResourceRequest;
import edu.fiu.cis.acrl.virtuallabs.ws.ReserveResourceResponse;
import edu.fiu.cis.acrl.virtuallabs.ws.GetCheckinIntervalRequest;
import edu.fiu.cis.acrl.virtuallabs.ws.GetCheckinIntervalResponse;
import edu.fiu.cis.acrl.virtuallabs.ws.TodoType;
import edu.fiu.cis.acrl.virtuallabs.ws.CheckinRequest;
import edu.fiu.cis.acrl.virtuallabs.ws.CheckinResponse;
import edu.fiu.cis.acrl.virtuallabs.ws.GetUserCredentialsRequest;
import edu.fiu.cis.acrl.virtuallabs.ws.GetUserCredentialsResponse;
import edu.fiu.cis.acrl.vescheduler.server.VEInstance;
import edu.fiu.cis.acrl.vescheduler.server.VEScheduler;
import edu.fiu.cis.acrl.vescheduler.server.VMInstance;
import edu.fiu.cis.acrl.vescheduler.server.VMInstance.VMInsStatusType;
import edu.fiu.cis.acrl.vescheduler.server.agent.SSHCommand;
import edu.fiu.cis.acrl.vescheduler.server.agent.SchedulingAgent;
import edu.fiu.cis.acrl.vescheduler.server.db.VESchedulerDB;
import edu.fiu.cis.acrl.vescheduler.ws.GetVEMacsRequest;
import edu.fiu.cis.acrl.vescheduler.ws.GetVEMacsResponse;
import edu.fiu.cis.acrl.quotasystem.server.QuotaSystem;
import edu.fiu.cis.acrl.quotasystem.ws.CreditTypeNotFoundFault;
import edu.fiu.cis.acrl.quotasystem.ws.QuotaSystemStub;
import edu.fiu.cis.acrl.quotasystem.ws.ScheduleAppointmentsResponse;
import edu.fiu.cis.acrl.quotasystem.ws.UserNotFoundFault;
import edu.fiu.cis.acrl.tools.timeperiod.TimePeriod;
import edu.fiu.cis.acrl.tools.timeperiod.ScheduledEvent;
import edu.fiu.cis.acrl.mentorscheduler.server.MentorScheduler;
import edu.fiu.cis.acrl.mentorscheduler.ws.AddMentorRequest;
import edu.fiu.cis.acrl.mentorscheduler.ws.AddMentorResponse;
import edu.fiu.cis.acrl.mentorscheduler.ws.AssignMentorToCourseRequest;
import edu.fiu.cis.acrl.mentorscheduler.ws.AssignMentorToCourseResponse;
import edu.fiu.cis.acrl.mentorscheduler.ws.CancelScheduledMentorUnallocatedWorktimeRequest;
import edu.fiu.cis.acrl.mentorscheduler.ws.CancelScheduledMentorUnallocatedWorktimeResponse;
import edu.fiu.cis.acrl.mentorscheduler.ws.CancelScheduledMentoringRequest;
import edu.fiu.cis.acrl.mentorscheduler.ws.CancelScheduledMentoringResponse;
import edu.fiu.cis.acrl.mentorscheduler.ws.DelMentorRequest;
import edu.fiu.cis.acrl.mentorscheduler.ws.DelMentorResponse;
import edu.fiu.cis.acrl.mentorscheduler.ws.GetMentorScheduledUnallocatedWorktimeRequest;
import edu.fiu.cis.acrl.mentorscheduler.ws.GetMentorScheduledUnallocatedWorktimeResponse;
import edu.fiu.cis.acrl.mentorscheduler.ws.GetMentoringAvailabilityRequest;
import edu.fiu.cis.acrl.mentorscheduler.ws.GetMentoringAvailabilityResponse;
import edu.fiu.cis.acrl.mentorscheduler.ws.GetMentoringScheduleBySchIdRequest;
import edu.fiu.cis.acrl.mentorscheduler.ws.GetMentoringScheduleBySchIdResponse;
import edu.fiu.cis.acrl.mentorscheduler.ws.GetMentoringScheduleRequest;
import edu.fiu.cis.acrl.mentorscheduler.ws.GetMentoringScheduleResponse;
import edu.fiu.cis.acrl.mentorscheduler.ws.GetRandomMentorRequest;
import edu.fiu.cis.acrl.mentorscheduler.ws.GetRandomMentorResponse;
import edu.fiu.cis.acrl.mentorscheduler.ws.MentorSchedulerStub;
import edu.fiu.cis.acrl.mentorscheduler.ws.ModifyScheduledMentorUnallocatedWorktimeRequest;
import edu.fiu.cis.acrl.mentorscheduler.ws.ModifyScheduledMentorUnallocatedWorktimeResponse;
import edu.fiu.cis.acrl.mentorscheduler.ws.ModifyScheduledMentoringRequest;
import edu.fiu.cis.acrl.mentorscheduler.ws.ModifyScheduledMentoringResponse;
import edu.fiu.cis.acrl.mentorscheduler.ws.ScheduleMentorUnallocatedWorktimeRequest;
import edu.fiu.cis.acrl.mentorscheduler.ws.ScheduleMentorUnallocatedWorktimeResponse;
import edu.fiu.cis.acrl.mentorscheduler.ws.ScheduleMentoringRequest;
import edu.fiu.cis.acrl.mentorscheduler.ws.ScheduleMentoringResponse;
import edu.fiu.cis.acrl.virtuallabs.ws.*;
import edu.fiu.cis.acrl.virtuallabs.server.db.VirtualLabsDB;
import edu.fiu.cis.acrl.virtuallabs.server.tools.crypt.Crypt;
import edu.fiu.cis.acrl.virtuallabs.server.tools.debug.DebugTools;
import edu.fiu.cis.acrl.virtuallabs.server.tools.timezone.TimeZoneTools;
import edu.fiu.cis.acrl.virtuallabs.server.translators.AppointmentTranslator;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.UUID;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

import edu.fiu.cis.acrl.vescheduler.ws.VESchedulerStub;
import edu.fiu.cis.acrl.vescheduler.ws.vetypes.UserType;
import edu.fiu.cis.acrl.vescheduler.ws.vetypes.UsersType;
import edu.fiu.cis.acrl.vescheduler.ws.vetypes.VENodeListType;
import edu.fiu.cis.acrl.vescheduler.ws.vetypes.VENodeType;
import edu.fiu.cis.acrl.vescheduler.ws.vetypes.VirtualEnvironmentType;
import edu.fiu.cis.acrl.vescheduler.ws.CancelScheduledHostMaintenanceRequest;
import edu.fiu.cis.acrl.vescheduler.ws.CancelScheduledHostMaintenanceResponse;
import edu.fiu.cis.acrl.vescheduler.ws.CancelScheduledVERequest;
import edu.fiu.cis.acrl.vescheduler.ws.CancelScheduledVEResponse;
import edu.fiu.cis.acrl.vescheduler.ws.DelVEInsRequest;
import edu.fiu.cis.acrl.vescheduler.ws.DelVEInsResponse;
import edu.fiu.cis.acrl.vescheduler.ws.DisableKaseyaAccountRequest;
import edu.fiu.cis.acrl.vescheduler.ws.DisableKaseyaAccountResponse;
import edu.fiu.cis.acrl.vescheduler.ws.GetHostMaintenanceAvailabilityRequest;
import edu.fiu.cis.acrl.vescheduler.ws.GetHostMaintenanceAvailabilityResponse;
import edu.fiu.cis.acrl.vescheduler.ws.GetHostMaintenanceScheduleRequest;
import edu.fiu.cis.acrl.vescheduler.ws.GetHostMaintenanceScheduleResponse;
import edu.fiu.cis.acrl.vescheduler.ws.GetHostUsingNameRequest;
import edu.fiu.cis.acrl.vescheduler.ws.GetHostUsingNameResponse;
import edu.fiu.cis.acrl.vescheduler.ws.GetVEAvailabilityRequest;
import edu.fiu.cis.acrl.vescheduler.ws.GetVEAvailabilityResponse;
import edu.fiu.cis.acrl.vescheduler.ws.GetVEInsIdUsingMacRequest;
import edu.fiu.cis.acrl.vescheduler.ws.GetVEInsIdUsingMacResponse;
import edu.fiu.cis.acrl.vescheduler.ws.GetVEInsScheduleBySchIdRequest;
import edu.fiu.cis.acrl.vescheduler.ws.GetVEInsScheduleBySchIdResponse;
import edu.fiu.cis.acrl.vescheduler.ws.GetVEInsScheduleRequest;
import edu.fiu.cis.acrl.vescheduler.ws.GetVEInsScheduleResponse;
import edu.fiu.cis.acrl.vescheduler.ws.GetVEUnavailabilityRequest;
import edu.fiu.cis.acrl.vescheduler.ws.GetVEUnavailabilityResponse;
import edu.fiu.cis.acrl.vescheduler.ws.GetVMMacRequest;
import edu.fiu.cis.acrl.vescheduler.ws.GetVMMacResponse;
import edu.fiu.cis.acrl.vescheduler.ws.ModifyScheduledHostMaintenanceRequest;
import edu.fiu.cis.acrl.vescheduler.ws.ModifyScheduledHostMaintenanceResponse;
import edu.fiu.cis.acrl.vescheduler.ws.ModifyScheduledVERequest;
import edu.fiu.cis.acrl.vescheduler.ws.ModifyScheduledVEResponse;
import edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMRequest;
import edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMResponse;
import edu.fiu.cis.acrl.vescheduler.ws.ScheduleHostMaintenanceRequest;
import edu.fiu.cis.acrl.vescheduler.ws.ScheduleHostMaintenanceResponse;
import edu.fiu.cis.acrl.vescheduler.ws.ScheduleVERequest;
import edu.fiu.cis.acrl.vescheduler.ws.ScheduleVEResponse;

import java.util.concurrent.*;

public class VirtualLabs { 

	public static String ITSKFW 	= "ITS-KFW";
	public static String ITSKSDW 	= "ITS-KSDW";
	public static String FIUSP12 	= "FIU-SP12";
	
	public static String ALL_STUDENTS = "ALL_STUDENTS";
	
	// Debug level for this class
	private static int DEBUG_LEVEL = 1;
	
	private VESchedulerDB veSchDB;
	private VirtualLabsDB virtualLabsDB;
	private VirtualLabsSettings settings;
	// private VESchedulerStub veStub;
	private VEScheduler veStub;
	// private MentorSchedulerStub mentorStub;
	private MentorScheduler mentorStub;
    // private KaseyaWSClient kaseyaWSClient;
    // private QuotaSystemStub qsStub;
    private QuotaSystem qsStub;
    
    private UnneededPasswordTerminator passTerminator = null;
	
	/**
	 * Constructor is protected
	 * 
	 */
	@SuppressWarnings("static-access")
	protected VirtualLabs() {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - Constructor] Inside!");

		veSchDB = VESchedulerDB.instance();
		
		virtualLabsDB = VirtualLabsDB.instance();
		
		settings = VirtualLabsSettings.instance();

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs] Settings!" + settings);

		/*
		this.virtualLabsDB = new VirtualLabsDB();

		virtualLabsDB.connect(
				settings.getDbUser(), 
				settings.getDbPassword(), 
				settings.getDbHost(), 
				settings.getDbName());
		*/

		try {

			// veStub = new VESchedulerStub(settings.getVeSchedulerEPR());
			veStub = VEScheduler.instance();

			// mentorStub = new MentorSchedulerStub(settings.getMentorSchedulerEPR());
			mentorStub = MentorScheduler.instance();
			
			// kaseyaWSClient = KaseyaWSClient.instance();
			
			// qsStub = new QuotaSystemStub(settings.getQuotaSystemEPR());
			qsStub = QuotaSystem.instance();
			
		} 
		catch (Exception e) {

			e.printStackTrace();

		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - Constructor] "
				+ "Starting the pass terminator ... ");
		passTerminator = new UnneededPasswordTerminator();
		passTerminator.start();
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - Constructor] "
				+ "The pass terminator has started");

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - Constructor] Ready to get out!");

	}

	/**
	 * 
	 */
	public void startUp() {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - startUp] Inside!");

		System.out.println(">> Starting VirtualLabs ...");

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - startUp] Ready to get out!");

	}

	/**
	 * Free resources
	 */
	public void shutDown() {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - shutDown] Inside!");

		virtualLabsDB.close();

		System.out.println(">> Shutting Down VirtualLabs ...");

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - shutDown] Ready to get out!");

	}

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private VirtualLabs _instance = null;

	/**
	 * @return The unique instance of this class.
	 */
	static public VirtualLabs instance() {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - instance] Inside!");

		if(null == _instance) {
			_instance = new VirtualLabs();
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - instance] Ready to get out!");

		return _instance;

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
	 * 
	 * @param addInitialTasks4NewVMsRequest
	 * @return
	 */
	public AddInitialTasks4NewVMsResponse addInitialTasks4NewVMs(
			AddInitialTasks4NewVMsRequest addInitialTasks4NewVMsRequest) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4NewVMs] Inside!");

		AddInitialTasks4NewVMsResponse response = new AddInitialTasks4NewVMsResponse();
		response.setSuccess(false);
		response.setReason("Not Successful!");

		addInitialTasks4NewVMs(addInitialTasks4NewVMsRequest.getDevaInsId());
		
		response.setSuccess(true);
		response.setReason("Successful!");

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4NewVMs] Ready to get out!");

		return response;
		
	}
	
	/**
	 * 
	 * @param addInitialTasks4NewVMsRequest
	 * @return
	 */
	public AddInitialTasks4NewVMsWithEncryptedPasswordResponse addInitialTasks4NewVMsWithEncryptedPassword(
			AddInitialTasks4NewVMsWithEncryptedPasswordRequest addInitialTasks4NewVMsWithEncryptedPasswordRequest) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4NewVMsWithEncryptedPassword] Inside!");

		AddInitialTasks4NewVMsWithEncryptedPasswordResponse resp = new AddInitialTasks4NewVMsWithEncryptedPasswordResponse();

		EncryptedCredential encryptedCredential  = addInitialTasks4NewVMsWithEncryptedPasswordRequest.getEncryptedCredential();
		String username = encryptedCredential.getUserName();
		String password = Crypt.decrypt(encryptedCredential.getEncryptedPassword());
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - editUserWithEncryptedPasswordProfile] "
				+ "userName: " + encryptedCredential.getUserName() 
				+ " encryptedPassword: " + encryptedCredential.getEncryptedPassword()
				+ " password: " + password);
		setUserCachedPassword(username, password);
		
		AddInitialTasks4NewVMsRequest wrappedReq = new AddInitialTasks4NewVMsRequest();
		wrappedReq.setDevaInsId(addInitialTasks4NewVMsWithEncryptedPasswordRequest.getDevaInsId());
		AddInitialTasks4NewVMsResponse wrappedResp = addInitialTasks4NewVMs(wrappedReq);
		
		resp.setSuccess(wrappedResp.getSuccess());
		resp.setReason(wrappedResp.getReason());

		// passTerminator.notifyAll();

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4NewVMsWithEncryptedPassword] Ready to get out!");

		return resp;
		
	}
	
	/**
	 * 
	 * @param refreshVMRequest
	 * @return
	 */
	public RefreshVMResponse refreshVM(
			RefreshVMRequest refreshVMRequest) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - refreshVM] Inside!");

		RefreshVMResponse response = new RefreshVMResponse();
		response.setSuccess(false);
		response.setReason("Not Successful!");

		edu.fiu.cis.acrl.vescheduler.ws.RefreshVMRequest req =
			new edu.fiu.cis.acrl.vescheduler.ws.RefreshVMRequest();
		req.setDevaInsId(refreshVMRequest.getDevaInsId());
		req.setVmName(refreshVMRequest.getVmName());
		
		edu.fiu.cis.acrl.vescheduler.ws.RefreshVMResponse resp = null;
		try {
			resp = veStub.refreshVM(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		addInitialTasks4RefreshedVM(
				refreshVMRequest.getDevaInsId(), 
				refreshVMRequest.getVmName());
		
		response.setSuccess(resp.getSuccess());
		response.setReason(resp.getReason());

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - refreshVM] Ready to get out!");

		return response;
		
	}
	
	/**
	 * 
	 * @param refreshVMRequest
	 * @return
	 */
	public RefreshVMWithEncryptedPasswordResponse refreshVMWithEncryptedPassword(
			RefreshVMWithEncryptedPasswordRequest refreshVMWithEncryptedPasswordRequest) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - refreshVMWithEncryptedPassword] Inside!");

		RefreshVMWithEncryptedPasswordResponse resp = new RefreshVMWithEncryptedPasswordResponse();

		String veInsId = refreshVMWithEncryptedPasswordRequest.getDevaInsId();
		User user = virtualLabsDB.getUserByVeInsId(veInsId);
		String username = user.getUserName();
		String encryptedPassword = refreshVMWithEncryptedPasswordRequest.getEncryptedPassword();
		String password = Crypt.decrypt(encryptedPassword);
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - refreshVMWithEncryptedPassword] "
				+ "userName: " + username 
				+ " encryptedPassword: " + encryptedPassword
				+ " password: " + password);
		setUserCachedPassword(username, password);
		
		RefreshVMRequest wrappedReq = new RefreshVMRequest();
		wrappedReq.setDevaInsId(refreshVMWithEncryptedPasswordRequest.getDevaInsId());
		wrappedReq.setVmName(refreshVMWithEncryptedPasswordRequest.getVmName());
		RefreshVMResponse wrappedResp = refreshVM(wrappedReq);
		
		setUserCachedPassword(username, password);

		resp.setSuccess(wrappedResp.getSuccess());
		resp.setReason(wrappedResp.getReason());

		// passTerminator.notifyAll();

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - refreshVMWithEncryptedPassword] Ready to get out!");

		return resp;

	}
	
	public void addCourse(
			AddCourseRequest addCourseRequest)
			throws DuplicateCourseFault, InvalidDataFault {

		edu.fiu.cis.acrl.virtuallabs.ws.Course courseReq = 
			addCourseRequest.getAddCourseRequest();
		Course course = null;

		try {
			course = virtualLabsDB.getCourseById(courseReq.getId());
		} catch (Error e) {
			throw new InvalidDataFault(e.getMessage());
		}

		if (course == null) {
			try {
				
				course = new Course(
						courseReq.getId(), 
						courseReq.getFullname(),
						courseReq.getShortname(),
						courseReq.getPromoCode());
				virtualLabsDB.addCourse(course);

			} catch (Error e) {
				throw new InvalidDataFault(e.getMessage());
			}

		} else {
			throw new DuplicateCourseFault("Duplicate Course Fault");
		}

	}

	/**
	 * 
	 * @param modifyCourseRequest
	 * @throws DuplicateCourseFault
	 * @throws CourseNotFoundFault
	 * @throws InvalidDataFault
	 */
	public void modifyCourse(
			ModifyCourseRequest modifyCourseRequest)
			throws DuplicateCourseFault, CourseNotFoundFault, InvalidDataFault {

		edu.fiu.cis.acrl.virtuallabs.ws.Course courseReq = 
			modifyCourseRequest.getModifyCourseRequest();
		Course course = null;

		try {
			course = virtualLabsDB.getCourseById(courseReq.getId());
		} catch (Error e) {
			throw new InvalidDataFault(e.getMessage());
		}

		if (course != null) {
			try {
				Course modCourse = 
					new Course(
							courseReq.getId(), 
							courseReq.getFullname(), 
							courseReq.getShortname(), 
							courseReq.getPromoCode());

				virtualLabsDB.modifyCourse(modCourse);

			} catch (Error e) {
				throw new InvalidDataFault(e.getMessage());
			}

		} else {
			edu.fiu.cis.acrl.virtuallabs.ws.AddCourseRequest addCourseRequest =
				new edu.fiu.cis.acrl.virtuallabs.ws.AddCourseRequest();
			addCourseRequest.setAddCourseRequest(modifyCourseRequest.getModifyCourseRequest());
			addCourse(addCourseRequest);
			// throw new CourseNotFoundFault("Course not Found Fault");
		}

	}

	public void deleteCourse(
			DeleteCourseRequest deleteCourseRequest)
			throws CourseNotFoundFault {
		try {
			
			int courseId = deleteCourseRequest.getDeleteCourseRequest();
			
			Course course = virtualLabsDB.getCourseById(courseId);
			if (course == null) 
				throw new CourseNotFoundFault("Course not found! course id: " + courseId);
			
			virtualLabsDB.delCourse(course);

		} catch (Error e) {
			DebugTools.println(DEBUG_LEVEL, "[QuotaSystem - Skeleton]" + e.getMessage());
			throw new CourseNotFoundFault(e.getMessage());
		} catch (Exception e) {
			DebugTools.println(DEBUG_LEVEL, "[QuotaSystem - Skeleton]" + e.getMessage());

		}

	}

	/**
	 * 
	 * @param runVMCmdRequest
	 * @return
	 */
	/*
	public RunVMCmdResponse runVMCmd(
			RunVMCmdRequest runVMCmdRequest) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - runVMCmd] Inside!");

		RunVMCmdResponse response = new RunVMCmdResponse();
		response.setSuccess(false);
		response.setReason("Not Successful!");

		edu.fiu.cis.acrl.vescheduler.ws.RunVMCmdRequest req =
			new edu.fiu.cis.acrl.vescheduler.ws.RunVMCmdRequest();
		req.setDevaInsId(runVMCmdRequest.getDevaInsId());
		req.setVmName(runVMCmdRequest.getVmName());
		req.setCmdParam1(runVMCmdRequest.getCmdParam1());
		req.setCmdParam2(runVMCmdRequest.getCmdParam2());
		
		edu.fiu.cis.acrl.vescheduler.ws.RunVMCmdResponse resp = null;
		try {
			resp = veStub.runVMCmd(req);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		response.setReturnValue(resp.getReturnValue());
		response.setSuccess(resp.getSuccess());
		response.setReason(resp.getReason());

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - runVMCmd] Ready to get out!");

		return response;
		
	}
	*/
	
	/**
	 * 
	 * @param runVMCmdRequest
	 * @return
	 */
	public RunVMCmdResponse runVMCmd(
			RunVMCmdRequest runVMCmdRequest) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - runVMCmd] Inside!");

		String RUN_VM_CMD_SCRIPT = "run_vm_cmd.py";
		
		RunVMCmdResponse response = new RunVMCmdResponse();
		response.setSuccess(false);
		response.setReason("Not Successful!");

		String veInsId = runVMCmdRequest.getDevaInsId();
		VEInstance veIns = veSchDB.getVEInstance(veInsId);
		// SMS: 5/7/2013
		int hostId = veSchDB.getHostIdOfScheduledVEIns(veInsId);
		edu.fiu.cis.acrl.vescheduler.server.Host host = veSchDB.getHost(hostId);

		VMInstance vmIns = veSchDB.getVMInstance(veInsId, runVMCmdRequest.getVmName());

		String veInsUsername = virtualLabsDB.getVeInsUsernameByVeInsId(veIns.getId());
		String command = 
			RUN_VM_CMD_SCRIPT + " " + 
			// veIns.getUsername() + " " + 
			veInsUsername + " " +
			veIns.getVeType().getVEName() + " " + 
			veIns.getFirstPort() + " " + 
			veIns.getStorageId() + " " +
			vmIns.getDir() + " " +
			runVMCmdRequest.getCmdParam1() + " " +
			runVMCmdRequest.getCmdParam2();
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - runVMCmd] " +
				"command to be executed: " + command);
		// Added this check to make sure if a vm is paused, no command is executed for it.
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - runVMCmd] " +
				"veIns.getStatus(): " + veIns.getStatus());
		String returnValue = "";
		if ((veIns.getStatus().toString().toUpperCase() != "PAUSED") && 
			(veIns.getStatus().toString().toUpperCase() != "PAUSING")) {
			SSHCommand cmd = 
				new SSHCommand(
						command, 
						host.getName(), 
						host.getSshPort(), 
						host.getUsername(), 
						host.getPassword(),
						0);
			// (new Thread(cmd)).start();
			int exitCode = cmd.run();

			switch (exitCode) {
			case 1:
				returnValue = "off";
				break;
			case 2:
				returnValue = "suspended";
				break;
			case 3:
				returnValue = "on";
				break;
			default:
				break;				
			}
		} else {
			returnValue = "suspended";
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - runVMCmd] " +
					"command will not be executed as the vm is paused!" + command);
		}
		response.setReturnValue(returnValue);
		response.setSuccess(true);
		response.setReason("Successful!");

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - runVMCmd] Ready to get out!");

		return response;
	}

	/**
	 * 
	 * @param isRDPReadyRequest
	 * @return
	 */
	public IsRDPReadyResponse isRDPReady(
			IsRDPReadyRequest isRDPReadyRequest) {
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isRDPReady] Inside!");

		String IS_RDP_READY_SCRIPT = "is_rdp_ready.py";
		
		IsRDPReadyResponse response = new IsRDPReadyResponse();
		response.setReady(false);
		response.setSuccess(true);
		response.setReason("Successful!");

		// edu.fiu.cis.acrl.vescheduler.server.Host selectedHost = veSchDB.getHost(1);
		// edu.fiu.cis.acrl.vescheduler.server.Host selectedHost = null;
		ArrayList<edu.fiu.cis.acrl.vescheduler.server.Host> hosts = veSchDB.getHostList();
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isRDPReady] " +
				"hosts size: " + hosts.size());
		String hostName = isRDPReadyRequest.getHostName();
		edu.fiu.cis.acrl.vescheduler.server.Host selectedHost = veSchDB.getHostUsingName(hostName);
		int exitCode = -101; // indicates that the host is not reachable
		int attempt = 0;
		int maxAttempt = hosts.size()-1;
		do {
			attempt++;
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isRDPReady] " +
					"\n\tattempt: " + attempt + " hosts size is " + hosts.size());
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isRDPReady] " +
					"\n\tBefore removing the target host, hosts size is " + hosts.size());
			hosts.remove(selectedHost);
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isRDPReady] " +
					"\n\tAfter removing the target host, hosts size is " + hosts.size());
			Random generator = new Random(Calendar.getInstance().getTimeInMillis());
			// boolean selected = false;
			// do {
				int randomIndex = generator.nextInt(hosts.size());
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isRDPReady] " +
					"\n\trandom number: " + randomIndex);
				selectedHost = hosts.get(randomIndex);
				// if (!selectedHost.getName().equals(isRDPReadyRequest.getHostName()))
				//	selected = true;
			
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isRDPReady] " +
					"selecting the host! " +
					"\n\trandom number: " + randomIndex +
					"\n\tselectedHost: " + selectedHost.getName() +
					"\n\thost: " + isRDPReadyRequest.getHostName());
//				+
//					"\n\tselected: " + selected);
			// } while(!selected);
		
			String command = 
				IS_RDP_READY_SCRIPT + " " + 
				isRDPReadyRequest.getHostName() + " " +
				isRDPReadyRequest.getHostport();
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isRDPReady] " +
				"command to be executed: " + command);

			SSHCommand cmd = 
				new SSHCommand(
					command, 
					selectedHost.getName(), 
					selectedHost.getSshPort(), 
					selectedHost.getUsername(), 
					selectedHost.getPassword(),
					0);
			// (new Thread(cmd)).start();
			exitCode = cmd.run();
		} while ((exitCode == -101) && (attempt < maxAttempt));
/*
		SSHCommandThread cmd = 
			new SSHCommandThread(
					command, 
					host.getName(), 
					host.getSshPort(), 
					host.getUsername(), 
					host.getPassword(),
					0);
		// (new Thread(cmd)).start();
		int exitCode = -1;
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isRDPReady] " +
				"\t before calling cmd.run() exitCode is: " + exitCode);
		
		try{ java.util.concurrent.TimeUnit.SECONDS.timedJoin(cmd, 2);}
		catch(Exception e) { e.printStackTrace(); }
		
		exitCode = cmd.getExitCode(1000);
		
  		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isRDPReady] " +
				"\tafter calling cmd.run(), before switch exitCode is: " + exitCode);
*/
  		switch (exitCode) {
		case 0:
			response.setReady(true);
			response.setSuccess(true);
			response.setReason("Call was successful and the RDP server is ready!");
			break;
		case 1:
			response.setReady(false);
			response.setSuccess(true);
			response.setReason("Call was successful, but the RDP server is NOT yet ready!");
			break;
		case 255:
			response.setReady(false);
			response.setSuccess(true);
			response.setReason("Call was successful, but the virtual machine is NOT up yet!");
			break;
		case -101:
			response.setReady(false);
			response.setSuccess(true);
			response.setReason("No other host was reachable to run this command on it!");
			break;
		default:
			break;				
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isRDPReady] Ready to get out!");

		return response;
	}
	
	/**
	 * 
	 * @param destroyDevaInsRequest
	 * @return
	 */
	public DestroyDevaInsResponse destroyDevaIns(
			DestroyDevaInsRequest destroyDevaInsRequest) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - destroyDevaIns] Inside!");

		DestroyDevaInsResponse response = new DestroyDevaInsResponse();
		response.setSuccess(false);
		response.setReason("Not Successful!");

		// Cancelling user appointment for this veIns
		String veIns = destroyDevaInsRequest.getDevaInsId();
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - destroyDevaIns] "
				+ "Cancelling user appointment for " + veIns);
		CancelUserAppointmentRequest cancelAppReq = new CancelUserAppointmentRequest();
		cancelAppReq.setId(veIns);
		cancelAppReq.setRequestingUser("admin");
		CancelUserAppointmentResponse cancelAppRes = cancelUserAppointment(cancelAppReq);
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - destroyDevaIns] "
				+ "Cancelling the user appointment was " + cancelAppRes.getSuccess());
		
		/*
		vescheduler2
		select * from ve_ins where id='be713787-8b1c-4fc1-8987-488675d9535d';
		delete from ve_ins where id='be713787-8b1c-4fc1-8987-488675d9535d';
		select * from ve_ins_sch where ve_ins_id='be713787-8b1c-4fc1-8987-488675d9535d';
		delete from ve_ins_sch where ve_ins_id='be713787-8b1c-4fc1-8987-488675d9535d';
		select * from ve_ins_host where ve_ins_id='be713787-8b1c-4fc1-8987-488675d9535d';
		delete from ve_ins_host where ve_ins_id='be713787-8b1c-4fc1-8987-488675d9535d';
		select * from vm_ins where ve_ins_id='be713787-8b1c-4fc1-8987-488675d9535d';
		delete from vm_ins where ve_ins_id='be713787-8b1c-4fc1-8987-488675d9535d';

		virtuallabs
		delete from user_ve_instances where ve_id='be713787-8b1c-4fc1-8987-488675d9535d';
		select id from vm_ins_task where mac_address>='00:50:56:00:01:03' and mac_address<='00:50:56:00:01:08';
		delete from vm_ins_sync_user_task where task_id>=481 and task_id<=495;
		delete from vm_ins_task where mac_address>='00:50:56:00:01:03' and mac_address<='00:50:56:00:01:08';
			 * 
			 */
		GetVEMacsRequest vmMacsReq = new GetVEMacsRequest();
		vmMacsReq.setVeInsId(destroyDevaInsRequest.getDevaInsId());
		GetVEMacsResponse vmMacsResp = null;
		try {
			vmMacsResp = veStub.getVEMacs(vmMacsReq);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (vmMacsResp != null) {
			edu.fiu.cis.acrl.vescheduler.ws.DestroyDevaInsRequest req =
				new edu.fiu.cis.acrl.vescheduler.ws.DestroyDevaInsRequest();
			req.setDevaInsId(destroyDevaInsRequest.getDevaInsId());
			edu.fiu.cis.acrl.vescheduler.ws.DestroyDevaInsResponse resp = null;
			try {
				resp = veStub.destroyDevaIns(req);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (resp != null) {
				if (resp.getSuccess()) {
					
					String veInsId = destroyDevaInsRequest.getDevaInsId();
					virtualLabsDB.deleteVMTasks(vmMacsResp.getMacAddress());
					virtualLabsDB.delVEInstance(veInsId);
				
					response.setSuccess(true);
					response.setReason("Successful!");

				} else {
					response.setSuccess(false);
					response.setReason("Not Successful! " +
							"veStub.destroyDevaIns was not successful! " + 
							resp.getReason());
				}
				
			} else {
				response.setSuccess(false);
				response.setReason("Not Successful! veStub.destroyDevaIns returned null!");
			}
					
		} else {
			response.setSuccess(false);
			response.setReason("Not Successful! getVEMacs returned null!");
		}
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - destroyDevaIns] Ready to get out!");

		return response;
		
	}
	
	/**
	 * 
	 * @param getDevaInsInfoRequest
	 * @return
	 */
	public GetDevaInsInfoResponse getDevaInsInfo(
			GetDevaInsInfoRequest getDevaInsInfoRequest) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getDevaInsInfo] Inside!");

		GetDevaInsInfoResponse response = new GetDevaInsInfoResponse();
		response.setSuccess(false);
		response.setReason("Not Successful!");

		String veInsId = 
			virtualLabsDB.getVEInstanceId(
					getDevaInsInfoRequest.getCourse(),
					getDevaInsInfoRequest.getResourceType(),
					getDevaInsInfoRequest.getUsername(),
					true);
		
		if (veInsId != null) {
			edu.fiu.cis.acrl.vescheduler.ws.GetDevaInsInfoRequest req =
				new edu.fiu.cis.acrl.vescheduler.ws.GetDevaInsInfoRequest();
			req.setVeInsId(veInsId);
			edu.fiu.cis.acrl.vescheduler.ws.GetDevaInsInfoResponse resp = null;
			try {
				resp = veStub.getDevaInsInfo(req);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (resp.getSuccess()) {
				
				String resourceType = virtualLabsDB.getResourceTypeFromVeInsId(veInsId);
				
				User user =	virtualLabsDB.getUser(getDevaInsInfoRequest.getUsername());
				String veInsUsername = virtualLabsDB.getVeInsUsernameByVeInsId(veInsId);
				
				edu.fiu.cis.acrl.vescheduler.ws.KServer kserver = resp.getKserver();
				edu.fiu.cis.acrl.virtuallabs.ws.KServer convertedKServer = 
					new edu.fiu.cis.acrl.virtuallabs.ws.KServer();
				convertedKServer.setId(kserver.getId());
				// SMS: Enabling certification test for SD on saas12. 7/22/2012
				String promoCodeTemp = virtualLabsDB.getPromoCode(veInsId);
				// SMS: 2013-05-04
				// No more on-premise Kaseya server. We will be using only saas servers.
				// if (resourceType.equals("CERTIFICATE") && (promoCodeTemp.equals("ITS-FW") || promoCodeTemp.equals("FIU-SP12")))
				// if (resourceType.equals("CERTIFICATE"))
				// 	convertedKServer.setName("kaseya2.cis.fiu.edu");
				// else
				// 	convertedKServer.setName(kserver.getName());				
				convertedKServer.setName(kserver.getName());				
				convertedKServer.setHttpPort(kserver.getHttpPort());
				// if (resourceType.equals("CERTIFICATE"))
				// 	convertedKServer.setUsername(user.getUserName()+"-ct");
				// else
				// 	convertedKServer.setUsername(user.getUserName());
				convertedKServer.setUsername(veInsUsername);
				// convertedKServer.setUsername(kserver.getUsername());
				convertedKServer.setPassword(user.getPassword());
				// convertedKServer.setPassword(kserver.getPassword());
				convertedKServer.setTotalCap(kserver.getTotalCap());
				convertedKServer.setActiveCap(kserver.getActiveCap());
				convertedKServer.setActive(kserver.getActive());
				convertedKServer.setNewAssignment(kserver.getNewAssignment());
				response.setKserver(convertedKServer);
				
				edu.fiu.cis.acrl.vescheduler.ws.VMInfo[] vmInstances =
					resp.getVmInfo();
				
				if (vmInstances != null) {
					for (int i=0; i<vmInstances.length; i++) {
	
						VMInfo vmInfo = new VMInfo();
						vmInfo.setId(vmInstances[i].getId());
						vmInfo.setVeInsId(vmInstances[i].getVeInsId());
						// vmInfo.setUsername(user.getUserName());
						// TODO 
						String endUserName = veInsUsername;
						String promoCode = virtualLabsDB.getPromoCode(veInsId);
						DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getDevaInsInfo] " +
								"Setting end users based on the promoCode: " + promoCode);
						// SMS: To accommodate ITS-KSDW-14-1D
						// if (promoCode.equals(ITSKSDW)) {
						if (promoCode.contains("KSDW")) {
							String vmName = vmInstances[i].getName();
							int beginIndex = vmName.indexOf('(');
							int endIndex = vmName.indexOf(')');
							String vmShortName = null;
							if ((beginIndex > 0) && (endIndex > 0) && (beginIndex < endIndex))
								vmShortName = vmName.substring(beginIndex+1, endIndex);
							if (vmShortName != null) {
								endUserName = getEndUserName(vmShortName);
								 
								DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getDevaInsInfo] " +
										"Creatint user for " + endUserName + " on " + vmName);
								endUserName += "-" + veInsUsername;
								DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getDevaInsInfo] " +
										"Creatint user for " + endUserName + " on " + vmName);
								// endUserName = "user-" + vmShortName + "-" + user.getUserName();
							} else {
								DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getDevaInsInfo] " +
										"The short name for vm could not be found. vmName: " + vmName);
							}
						}
						vmInfo.setUsername(endUserName);
						vmInfo.setPassword(user.getPassword());
						vmInfo.setName(vmInstances[i].getName());
						vmInfo.setDomain(vmInstances[i].getDomain());
						vmInfo.setOs(vmInstances[i].getOs());
						vmInfo.setInternalAddress(vmInstances[i].getInternalAddress());
						vmInfo.setAccessAddress(vmInstances[i].getAccessAddress());
						vmInfo.setAccessPort(vmInstances[i].getAccessPort());
						vmInfo.setMacAddress(vmInstances[i].getMacAddress());
						vmInfo.setStatus(convertStatus(vmInstances[i].getStatus()));
						vmInfo.setLastCheckin(vmInstances[i].getLastCheckin());
						vmInfo.setAppName(vmInstances[i].getAppName());
						vmInfo.setAppDir(vmInstances[i].getAppDir());
						response.addVmInfo(vmInfo);
						
					}

					response.setSuccess(true);
					response.setReason("Successful!");
				
				} else {
					response.setSuccess(false);
					response.setReason("The vmInstances is null!");
				}
			} else {
				response.setSuccess(resp.getSuccess());
				response.setReason(resp.getReason());
			}
		} else {
			response.setSuccess(false);
			response.setReason("This virtual environment has never been scheduled!");
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getDevaInsInfo] Ready to get out!");

		return response;
		
	}
	
	/**
	 * 
	 * @param getUserCurAppIdRequest
	 * @return
	 */
	public GetUserCurAppIdResponse getUserCurAppId(
			GetUserCurAppIdRequest getUserCurAppIdRequest) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getUserCurAppId] Inside!");

		GetUserCurAppIdResponse response = new GetUserCurAppIdResponse();
		response.setSuccess(false);
		response.setReason("Not Successful!");

		String veInsId = 
			virtualLabsDB.getVEInstanceId(
					getUserCurAppIdRequest.getCourse(),
					getUserCurAppIdRequest.getResourceType(),
					getUserCurAppIdRequest.getUsername(),
					true);
		
		if (veInsId != null) {
			edu.fiu.cis.acrl.vescheduler.ws.GetVEInsCurScheduleRequest req =
				new edu.fiu.cis.acrl.vescheduler.ws.GetVEInsCurScheduleRequest();
			req.setVeInsId(veInsId);
			edu.fiu.cis.acrl.vescheduler.ws.GetVEInsCurScheduleResponse resp = null;
			try {
				resp = veStub.getVEInsCurSchedule(req);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (resp.getSuccess()) {
				response.setAppId(resp.getSchedule().getSchId());
				response.setSuccess(true);
				response.setReason("Successful!");
			} else {
				response.setSuccess(resp.getSuccess());
				response.setReason(resp.getMessage());
			}
		} else {
			response.setSuccess(false);
			response.setReason("This virtual environment is not currently scheduled!");
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getUserCurAppId] Ready to get out!");

		return response;
		
	}
	
	/**
	 * 
	 * @param getEndDate4CurrentDevaInsRequest
	 * @return
	 */
	public GetEndDate4CurrentDevaInsResponse getEndDate4CurrentDevaIns(
			GetEndDate4CurrentDevaInsRequest getEndDate4CurrentDevaInsRequest) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getEndDate4CurrentDevaIns] Inside!");

		GetEndDate4CurrentDevaInsResponse response = new GetEndDate4CurrentDevaInsResponse();
		response.setSuccess(false);
		response.setReason("Not Successful!");

		edu.fiu.cis.acrl.vescheduler.ws.GetEndDate4CurrentDevaInsRequest req =
			new edu.fiu.cis.acrl.vescheduler.ws.GetEndDate4CurrentDevaInsRequest();
		req.setDevaInsId(getEndDate4CurrentDevaInsRequest.getDevaInsId());
		edu.fiu.cis.acrl.vescheduler.ws.GetEndDate4CurrentDevaInsResponse resp = null;
		try {
			resp = veStub.getEndDate4CurrentDevaIns(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (resp != null) {
			if (resp.getSuccess()) {

				String resourceType = 
					virtualLabsDB.getResourceTypeFromVeInsId(getEndDate4CurrentDevaInsRequest.getDevaInsId());
				
				response.setVeInsSchId(resp.getVeInsSchId());
				response.setCurDate(resp.getCurDate());
				response.setEndDate(resp.getEndDate());
				response.setResourceType(resourceType);
				response.setSuccess(true);
				response.setReason("Successful!");

			} else {

				response.setSuccess(false);
				response.setReason("Not Successful! " +
						"veStub.getEndDate4CurrentDevaIns was not successful! " + 
						resp.getReason());
			}

		} else {
			response.setSuccess(false);
			response.setReason("Not Successful! veStub.getEndDate4CurrentDevaIns returned null!");
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getEndDate4CurrentDevaIns] Ready to get out!");

		return response;
		
	}
	
	/**
	 * 
	 * @param getVMInsInfoRequest
	 * @return
	 */
	public GetVMInsInfoResponse getVMInsInfo(
			GetVMInsInfoRequest getVMInsInfoRequest) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getVMInsInfo] Inside!");

		GetVMInsInfoResponse response = new GetVMInsInfoResponse();
		response.setSuccess(false);
		response.setReason("Not Successful!");
		
		String vmInsId = getVMInsInfoRequest.getId();
		
		if (vmInsId != null) {
			edu.fiu.cis.acrl.vescheduler.ws.GetVMInsInfoRequest req =
				new edu.fiu.cis.acrl.vescheduler.ws.GetVMInsInfoRequest();
			req.setVmInsId(getVMInsInfoRequest.getId());
			edu.fiu.cis.acrl.vescheduler.ws.GetVMInsInfoResponse resp = null;
			try {
				resp = veStub.getVMInsInfo(req);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (resp.getSuccess()) {
				
				edu.fiu.cis.acrl.vescheduler.ws.VMInfo vmInstance =
					resp.getVmInfo();
				
				if (vmInstance != null) {
	
					String veInsId = vmInstance.getVeInsId();
					User user =	virtualLabsDB.getUserByVeInsId(veInsId);
					
					VMInfo vmInfo = new VMInfo();
					vmInfo.setId(vmInstance.getId());
					vmInfo.setVeInsId(vmInstance.getVeInsId());
					vmInfo.setUsername(user.getUserName());
					vmInfo.setPassword(user.getPassword());
					vmInfo.setName(vmInstance.getName());
					vmInfo.setDomain(vmInstance.getDomain());
					vmInfo.setOs(vmInstance.getOs());
					vmInfo.setInternalAddress(vmInstance.getInternalAddress());
					vmInfo.setAccessAddress(vmInstance.getAccessAddress());
					vmInfo.setAccessPort(vmInstance.getAccessPort());
					vmInfo.setMacAddress(vmInstance.getMacAddress());
					vmInfo.setStatus(convertStatus(vmInstance.getStatus()));
					vmInfo.setLastCheckin(vmInstance.getLastCheckin());
					vmInfo.setAppName(vmInstance.getAppName());
					vmInfo.setAppDir(vmInstance.getAppDir());
						
					response.setVmInfo(vmInfo);
					response.setSuccess(true);
					response.setReason("Successful!");
				
				} else {
					response.setSuccess(false);
					response.setReason("The vmInstance is null!");
				}
			} else {
				response.setSuccess(resp.getSuccess());
				response.setReason(resp.getReason());
			}
		} else {
			response.setSuccess(false);
			response.setReason("The vmInsId cannot be null!");
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getVMInsInfo] Ready to get out!");

		return response;
		
	}
	
	private VmInsStatusType convertStatus(
			edu.fiu.cis.acrl.vescheduler.ws.VmInsStatusType status) {

		VmInsStatusType retStatus = null;
		
		if (status == edu.fiu.cis.acrl.vescheduler.ws.VmInsStatusType.NOT_PROVISIONED)
			retStatus = VmInsStatusType.NOT_PROVISIONED;
		else if (status == edu.fiu.cis.acrl.vescheduler.ws.VmInsStatusType.PROVISIONING_AND_STARTING)
			retStatus = VmInsStatusType.PROVISIONING_AND_STARTING;
		else if (status == edu.fiu.cis.acrl.vescheduler.ws.VmInsStatusType.RUNNING)
			retStatus = VmInsStatusType.RUNNING;
		else if (status == edu.fiu.cis.acrl.vescheduler.ws.VmInsStatusType.PAUSING)
			retStatus = VmInsStatusType.PAUSING;
		else if (status == edu.fiu.cis.acrl.vescheduler.ws.VmInsStatusType.PAUSED)
			retStatus = VmInsStatusType.PAUSED;
		else if (status == edu.fiu.cis.acrl.vescheduler.ws.VmInsStatusType.STARTING)
			retStatus = VmInsStatusType.STARTING;
		else if (status == edu.fiu.cis.acrl.vescheduler.ws.VmInsStatusType.DESTROYED)
			retStatus = VmInsStatusType.DESTROYED;

		return retStatus;		
	}

	private Calendar getUserCurrentTime(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public IsResourceAvailableResponse isResourceAvailable(
			IsResourceAvailableRequest isResourceAvailableRequest) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isResourceAvailable] Inside!");

		IsResourceAvailableResponse response = new IsResourceAvailableResponse();
		
		// TODO Needs to be implemented at a later time.
		response.setSuccess(true);
		response.setReason("Successful!");

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isResourceAvailable] Ready to get out!");

		return response;
		
	}
	
	public ReserveResourceResponse reserveResource(
			ReserveResourceRequest reserveResourceRequest) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - reserveResource] Inside!");

		ReserveResourceResponse response = new ReserveResourceResponse();
		response.setSuccess(false);
		response.setReason("Not Successful!");

		if (reserveResourceRequest.getResourceType().equals("MENTORING")) {
		
			edu.fiu.cis.acrl.mentorscheduler.ws.ReserveResourceRequest req =
				new edu.fiu.cis.acrl.mentorscheduler.ws.ReserveResourceRequest ();
			req.setCourseId(reserveResourceRequest.getCourse());
			TimePeriod timePeriod = new TimePeriod();
			timePeriod.setStartTime(reserveResourceRequest.getStart());
			timePeriod.setEndTime(reserveResourceRequest.getEnd());
			req.setTimePeriod(timePeriod);
			req.setQuota(reserveResourceRequest.getQuota());
			req.setCancel(reserveResourceRequest.getCancel());
			
			edu.fiu.cis.acrl.mentorscheduler.ws.ReserveResourceResponse resp = null;
			try {
				
				resp = mentorStub.reserveResource(req);
				response.setSuccess(resp.getSuccess());
				response.setReason(resp.getReason());
			
			} catch (Exception e) {
			
				e.printStackTrace();
			
			}
		
		}
		else if (reserveResourceRequest.getResourceType().equals("VIRTUAL LAB") ||
				 reserveResourceRequest.getResourceType().equals("CERTIFICATE")) {
		
			edu.fiu.cis.acrl.vescheduler.ws.ReserveResourceRequest req =
				new edu.fiu.cis.acrl.vescheduler.ws.ReserveResourceRequest ();
			String veTypeName = 
				virtualLabsDB.getVEType(
						reserveResourceRequest.getCourse(), 
						reserveResourceRequest.getResourceType());
			VirtualEnvironmentType veType = null;
			try {
			
				veType = readVE(new File(veTypeName));
			
			} catch (Exception e) {
			
				e.printStackTrace();
			
			}
			req.setVe(veType);
			TimePeriod timePeriod = new TimePeriod();
			timePeriod.setStartTime(reserveResourceRequest.getStart());
			timePeriod.setEndTime(reserveResourceRequest.getEnd());
			req.setTimePeriod(timePeriod);
			req.setQuota(reserveResourceRequest.getQuota());
			req.setCancel(reserveResourceRequest.getCancel());
			
			edu.fiu.cis.acrl.vescheduler.ws.ReserveResourceResponse resp = null;
			try {
				
				resp = veStub.reserveResource(req);
				response.setSuccess(resp.getSuccess());
				response.setReason(resp.getReason());
			
			} catch (Exception e) {
			
				e.printStackTrace();
			
			}
		
		}
		else {
			
			response.setSuccess(false);
			response.setReason("Not Successful! Wrong resouce type!");

		}
			
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - reserveResource] Ready to get out!");

		return response;
		
	}
	
	public GetResourceTypesResponse getResourceTypes(
			GetResourceTypesRequest getResourceTypesRequest) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getResourceTypes] Inside!");

		GetResourceTypesResponse response = new GetResourceTypesResponse();
		response.setSuccess(false);
		response.setReason("Not Successful!");

		ArrayList<String> resourceTypes = 
			virtualLabsDB.getResourceTypes(getResourceTypesRequest.getUserRole());
		
		for (int i=0; i<resourceTypes.size(); i++)
			response.addResourceType(resourceTypes.get(i));
		
		if (resourceTypes != null) {
			
			response.setSuccess(true);
			response.setReason("Successful!");

		}
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getResourceTypes] Ready to get out!");

		return response;
		
	}
	
	public CheckinResponse checkin(
			CheckinRequest checkinRequest) {
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - checkinRequest] Inside!");

		CheckinResponse response = new CheckinResponse();
		
		edu.fiu.cis.acrl.vescheduler.ws.CheckinRequest req =
			new edu.fiu.cis.acrl.vescheduler.ws.CheckinRequest();
		req.setMacAddress(checkinRequest.getMacAddress());
		edu.fiu.cis.acrl.vescheduler.ws.CheckinResponse resp = null;
		
		try {
			
			resp = veStub.checkin(req);
		
		} catch (Exception e) {
		
			e.printStackTrace();
		
		}
		
		ArrayList<VMInsTask> vmInsTasks = 
			virtualLabsDB.getVMInsTasksUsingMac(checkinRequest.getMacAddress());
		
		for (VMInsTask vmInsTask : vmInsTasks) {
			
			response.addTodo(vmInsTask.getTask());
			
		}

		response.addTodo(TodoType.CHANGE_CHECKIN_INTERVAL);
		
		response.setSuccess(resp.getSuccess());
		response.setReason(resp.getReason());
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - checkinRequest] Ready to get out!");

		return response;
		
	}

	public GetCheckinIntervalResponse getCheckinInterval(
			GetCheckinIntervalRequest getCheckinIntervalRequest) {
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getCheckinInterval] Inside!");

		GetCheckinIntervalResponse response = new GetCheckinIntervalResponse();

		/* TODO
		VMInsTask task = virtualLabsDB.getVMInsTaskUsingMac(
				getCheckinIntervalRequest.getMacAddress(), 
				TodoType.CHANGE_CHECKIN_INTERVAL);
		task.setActive(false);
		virtualLabsDB.setVMInsTask(task);
		*/
		
		// response.setCheckinInterval(3600); // 30 seconds
		response.setCheckinInterval(VirtualLabsSettings.instance().getCheckinInterval()); // 30 seconds
		response.setSuccess(true);
		response.setReason("Successful!");
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getCheckinInterval] Ready to get out!");

		return response;
		
	}

	public GetUserCredentialsResponse getUserCredentials(
			GetUserCredentialsRequest getUserCredentialsRequest) {
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getUserCredentials] Inside!");

		GetUserCredentialsResponse response = new GetUserCredentialsResponse();

		VMInsTask task = virtualLabsDB.getVMInsTaskUsingMac(
				getUserCredentialsRequest.getMacAddress(), 
				TodoType.SYNC_USER_CREDENTIALS);
		task.setActive(false);
		virtualLabsDB.setVMInsTask(task);
		
		try {
			
			GetVEInsIdUsingMacRequest req = new GetVEInsIdUsingMacRequest();
			req.setMacAddress(getUserCredentialsRequest.getMacAddress());
			GetVEInsIdUsingMacResponse resp = veStub.getVEInsIdUsingMac(req);
		
			if (resp.getSuccess()) {
				
				String veInsId = resp.getVeInsId();
				User user = virtualLabsDB.getUserUsingVEInsId(veInsId);
				String veInsUsername = virtualLabsDB.getVeInsUsernameByVeInsId(veInsId);
				
				UserCredential userCred = new UserCredential();
				// userCred.setUserName(user.getUserName());
				userCred.setUserName(veInsUsername);
				userCred.setPassword(user.getPassword());					
				
				VMInsSyncUserTask vmInsSyncUserTask = 
					virtualLabsDB.getVMInsSyncUserTask(task.getId());
				if (vmInsSyncUserTask != null) {
					if (vmInsSyncUserTask.getUsername() != null) 
						if (vmInsSyncUserTask.getUsername().trim().length() > 0)
							userCred.setUserName(vmInsSyncUserTask.getUsername());
					if (vmInsSyncUserTask.getPassword() != null) 
						if (vmInsSyncUserTask.getPassword().trim().length() > 0)
							userCred.setPassword(vmInsSyncUserTask.getPassword());
					
					vmInsSyncUserTask.setActive(false);
					virtualLabsDB.setVMInsSyncUserTask(vmInsSyncUserTask);
				} 
				
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getUserCredentials] "
						+ "userCred.getUserName(): " + userCred.getUserName() + " "
						+ "userCred.getPassword(): " + userCred.getPassword());
				
				response.addUserCredential(userCred);
				response.setSuccess(true);
				response.setReason("Successful!");
			} else {
				response.setSuccess(false);
				response.setReason(resp.getReason());
			}
		} 
		catch (Exception e) {
		
			e.printStackTrace();
		
		}
		
		// passTerminator.notifyAll();
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getUserCredentials] Ready to get out!");

		return response;
				
	}

	/**
	 * 
	 * @param getUserAppointmentsRequest
	 * @return
	 */
	public GetUserAppointmentsResponse getUserAppointments(
			GetUserAppointmentsRequest getUserAppointmentsRequest) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getUserAppointment] Inside!");

		// Prepare response
		List<Appointment> allAppointments = new ArrayList<Appointment>();
		GetUserAppointmentsResponse resp = new GetUserAppointmentsResponse();

		//Get start and end date for the request
		Calendar start = getUserAppointmentsRequest.getStart();
		Calendar end = getUserAppointmentsRequest.getEnd();

		// Fix the time zone based on the requesting user's time zone
		User reqUser = virtualLabsDB.getUser(getUserAppointmentsRequest.getRequestingUser());
		TimeZone timeZone = TimeZone.getTimeZone(reqUser.getTimeZone());
		start = TimeZoneTools.changeTimeZone(start, timeZone);
		end = TimeZoneTools.changeTimeZone(end, timeZone);
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getUserAppointment] " +
				"start: " + start.getTime());
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getUserAppointment] " +
				"end: " + end.getTime());

		// Return an empty array of appointments in case the request is invalid
		try {

			String requestingUser = getUserAppointmentsRequest.getRequestingUser();
			String username = getUserAppointmentsRequest.getUserName();

			if (!virtualLabsDB.userExists(username) && !username.equals("")) {

				System.out.println("User does not exist");

			} else if (end.before(start)  || !start.before(end)) {

				System.out.println("Start date must be before end date");

			}
			else {

				// If the request is valid, get availability and schedule

				List<Appointment> availableSlots = new ArrayList<Appointment>();
				if (!username.equals(ALL_STUDENTS)) {

					availableSlots = getUserAvailability(username, start, end);
					// Fixing the appointments' time zone based on the requesting user's default time zone.
					availableSlots = fixTimeZone(reqUser.getUserName(), availableSlots);
					// Divide appointment by day
					availableSlots = divideByDay(availableSlots);
				
				}
				
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getUserAppointment] " +
						"availableSlots.size(): " + availableSlots.size());

				List<Appointment> scheduledApps = getScheduledUserAppointments(requestingUser, username, start, end);
				// Fixing the appointments' time zone based on the requesting user's default time zone.
				scheduledApps = fixTimeZone(reqUser.getUserName(), scheduledApps);
				
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getUserAppointment] " +
						"scheduledApps.size(): " + scheduledApps.size());

				// Merge both lists which means checking overlapping
				if (!username.equals(ALL_STUDENTS)) {

					allAppointments = MergeLists(availableSlots, scheduledApps);

				} else {
					
					allAppointments = scheduledApps;
					
				}
				
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getUserAppointment] " +
						"allAppointments.size(): " + allAppointments.size());
				// Add appointments to the response list
				Iterator<Appointment> itrAllApps = allAppointments.iterator();
				while (itrAllApps.hasNext()) {
					Appointment app = itrAllApps.next();
					DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getUserAppointment] " +
						"\n\t\t\t\t username: " + app.getUserName() +
						"\n\t\t\t\t course: " + app.getCourse() +
						"\n\t\t\t\t resource type: " + app.getResourceType() +
						"\n\t\t\t\t start: " + app.getStart().getTime() +
						"\n\t\t\t\t end: " + app.getEnd().getTime() 
						);
					resp.addAppointments(app);
				}
			}

		} catch (Error e) {
			e.printStackTrace();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getUserAppointment] Ready to get out!");

		return resp;

	}

	private List<Appointment> divideByDay(List<Appointment> allAppointments) {
		
		List<Appointment> divApps = new ArrayList<Appointment>();
		
		Iterator<Appointment> itrAvailApp = allAppointments.iterator();
		while (itrAvailApp.hasNext()) {
		
			Appointment app = itrAvailApp.next();

			List<Appointment> availableAppByDays = new ArrayList<Appointment>();
			availableAppByDays = getAppointmentSplittedInDays(app);
			Iterator<Appointment> itrByDay = availableAppByDays.iterator();
			while (itrByDay.hasNext()) {

				Appointment appByDay = itrByDay.next();
				divApps.add(appByDay);

			}
			
		}
		
		return divApps;

	}

	private List<Appointment> fixTimeZone(
			String username,
			List<Appointment> appointments) {

		List<Appointment> fixedApps = new ArrayList<Appointment>();
		
		// Change the times to the users time zone
		User user = virtualLabsDB.getUser(username);
		TimeZone userTimeZone = TimeZone.getTimeZone( user.getTimeZone() );
		
		for (Appointment app : appointments) {
			
	        Calendar startTemp = app.getStart();
	        Calendar endTemp = app.getEnd();

			// The following two functions has to be called so that the complete method on Calendar is called after setTimeZone()
	        // This is a known bug or "undocumented behavior!!!"
	        startTemp.getTime();
	        endTemp.getTime();

	        startTemp.setTimeZone( userTimeZone );
	        endTemp.setTimeZone( userTimeZone );

	        app.setStart(startTemp);
	        app.setEnd(endTemp);
	        
	        fixedApps.add(app);
	        
		}

		return fixedApps;
	
	}

	/**
	 * Get available slots from ve scheduler and returns them
	 * 
	 * @param username
	 * @param start
	 * @param end
	 * @return
	 */
	private List<Appointment> getUserAvailability(String username, Calendar start, Calendar end) {
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getUserAvailability] Inside!");
	
		//Prepare response
		List<Appointment> availableAppointments = new ArrayList<Appointment>();
	
		try {
			
			// Get courses and resources of the user
			Collection<String> resources = virtualLabsDB.getResources(username);
			Collection<String> courses = virtualLabsDB.getCourses(username);
	
			// Get every ve_type of every course-resource combination for this user
			Iterator<String> itrRes = resources.iterator();
			while (itrRes.hasNext()) {
	
				String resource = itrRes.next();
	
				if (resource.toUpperCase().equals("VIRTUAL LAB") ||
						resource.toUpperCase().equals("CERTIFICATE")) {

					// Set the request
					GetVEAvailabilityRequest request = new GetVEAvailabilityRequest();
					TimePeriod period = new TimePeriod();
					period.setStartTime(start);
					period.setEndTime(end);
					request.setTimePeriod(period);
			
					Iterator<String> itrCourse = courses.iterator();
					while (itrCourse.hasNext()) {

						String course = itrCourse.next();

						String ve_type = virtualLabsDB.getVEType(course,resource);

						// If the ve_type exists, get the active ve_instance for this user
						if (ve_type != null) {

							request.setVe(readVE(new File(ve_type)));

							String ve_id = virtualLabsDB.getVEInstanceId(
									course,
									resource, 
									username, 
									true);

							if (ve_id != null) {

								request.setVeInsId(ve_id);

							} 
							else {

								request.setVeInsId(null);

							}

							// Call getVEAvailability
							GetVEAvailabilityResponse veResp = veStub.getVEAvailability(request);
							TimePeriod[] slots = veResp.getAvailabilityRange();
							List<Appointment> availableAppTempList = new ArrayList<Appointment>();

							// Translate the time slots into appointments
							if (slots != null) {

								Appointment[] availableAppTempArr = 
									AppointmentTranslator.toAxisRepresentation(
											slots, 
											resource, 
											course, 
											"AVAILABLE", 
											null, 
											null);			
								availableAppTempList = Arrays.asList(availableAppTempArr);

							}

							Iterator<Appointment> itrAvailApp = availableAppTempList.iterator();
							while (itrAvailApp.hasNext()) {

								Appointment appAxis = itrAvailApp.next();	
								appAxis = addActionsToAvailableSlot(appAxis);
								availableAppointments.add(appAxis);

							}

						}

					}
	
				}
				else if (resource.toUpperCase().equals("MENTORING")) {

					// Set the request
					GetMentoringAvailabilityRequest request = 
						new GetMentoringAvailabilityRequest();
					TimePeriod period = new TimePeriod();
					period.setStartTime(start);
					period.setEndTime(end);
					request.setTimePeriod(period);
			
					Iterator<String> itrCourse = courses.iterator();
					while (itrCourse.hasNext()) {

						String course = itrCourse.next();

						request.setCourseId(course);
						request.setStudentUsername(username);
						
						// Call getMentoringAvailability
						GetMentoringAvailabilityResponse mentoringResp = 
							mentorStub.getMentoringAvailability(request);
						TimePeriod[] slots = mentoringResp.getAvailabilityRange();
						List<Appointment> availableAppTempList = new ArrayList<Appointment>();

						// Translate the time slots into appointments
						if (slots != null) {

							Appointment[] availableAppTempArr = 
								AppointmentTranslator.toAxisRepresentation(
										slots, 
										resource, 
										course, 
										"AVAILABLE", 
										null, 
										null);			
							availableAppTempList = Arrays.asList(availableAppTempArr);

						}

						Iterator<Appointment> itrAvailApp = availableAppTempList.iterator();
						while (itrAvailApp.hasNext()) {

							Appointment appAxis = itrAvailApp.next();	
							appAxis = addActionsToAvailableSlot(appAxis);
							availableAppointments.add(appAxis);

						}

					}

				}
				
			}
	
		} 
		catch (Error e) {
		
			e.printStackTrace();
	
		} 
		catch (Exception ex) {
		
			ex.printStackTrace();
	
		}
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getUserAvailability] Ready to get out!");
	
		return availableAppointments;
	
	}

	/*
	 * Get all scheduled appointments for this user
	 * 
	 */
	private List<Appointment> getScheduledUserAppointments(
			String requestingUser, String username, Calendar start, Calendar end) {	
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getScheduledUserAppointments] Inside!");
		
		//If there is no end date, set it up as two months from now
		if(end==null)
		{
			end = Calendar.getInstance();
			end.set(Calendar.MONTH, end.get(Calendar.MONTH)+2);
		}
	
		List<Appointment> scheduledApps = new ArrayList<Appointment>();	
		List<Appointment> veScheduleApps = getScheduledVE(username, start, end);
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getScheduledUserAppointments] " +
				"veScheduleApps.size(): " + veScheduleApps.size());
		List<Appointment> mentoringScheduleApps = getScheduledMentoring(username, start, end);
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getScheduledUserAppointments] " +
				"mentoringScheduleApps.size(): " + mentoringScheduleApps.size());
	
		Iterator<Appointment> itrVE = veScheduleApps.iterator();
		
		while (itrVE.hasNext()) {
	
			Appointment veApp = itrVE.next();				
			DbAppointment dbApp = virtualLabsDB.getAppointment(veApp.getId());
			veApp = addActionsToAppointment(requestingUser, veApp);
	
			if(dbApp != null) {
				
				veApp.setAffiliationId(dbApp.getAffiliationId());
			
			} else {
				
				UUID affiliationId = UUID.randomUUID();
				veApp.setAffiliationId(affiliationId.toString());
	
				// To Local representation means to create an instance of 
				// DbAppointment which is used in db methods
	
				DbAppointment app = AppointmentTranslator.toLocalRepresentation(veApp);
				virtualLabsDB.scheduleAppointment(app);
	
			}
	
			if (!username.equals(ALL_STUDENTS))
				veApp.setUserName(username);
			scheduledApps.add(veApp);

		}
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getScheduledUserAppointments] " +
				"After adding VE apps, scheduledApps.size(): " + scheduledApps.size());

		Iterator<Appointment> itrMentoring = mentoringScheduleApps.iterator();
		
		while (itrMentoring.hasNext()) {
	
			Appointment mentoringApp = itrMentoring.next();				
			DbAppointment dbApp = virtualLabsDB.getAppointment(mentoringApp.getId());
			mentoringApp = addActionsToAppointment(requestingUser, mentoringApp);
	
			if(dbApp != null) {
				
				mentoringApp.setAffiliationId(dbApp.getAffiliationId());
			
			}
			else {
				
				UUID affiliationId = UUID.randomUUID();
				mentoringApp.setAffiliationId(affiliationId.toString());
	
				// To Local representation means to create an instance of 
				// DbAppointment which is used in db methods
	
				DbAppointment app = AppointmentTranslator.toLocalRepresentation(mentoringApp);
				virtualLabsDB.scheduleAppointment(app);
	
			}
	
			mentoringApp.setUserName(username);
			scheduledApps.add(mentoringApp);

		}
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getScheduledUserAppointments] " +
				"After adding Mentoring apps, scheduledApps.size(): " + scheduledApps.size());

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getScheduledUserAppointments] Ready to get out!");
		
		return scheduledApps;
	
	}

	/*
	 * Calls GetVEInsSchedule 
	 * 
	 */
	private List<Appointment> getScheduledVE(String username, Calendar start, Calendar end) {
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getScheduledVE] Inside!");
		
		List<Appointment> scheduledAppointments = new ArrayList<Appointment>();
	
		try {
	
			// Set up the request
			GetVEInsScheduleRequest request = new GetVEInsScheduleRequest();
	
			TimePeriod period = new TimePeriod();
			period.setStartTime(start);
			period.setEndTime(end);
			request.setTimePeriod(period);
	
			Collection<String> resources = virtualLabsDB.getResources(username);
			Collection<String> courses = virtualLabsDB.getCourses(username);
	
			// Get all the resource-course combinations for this user
			Iterator<String> itrRes = resources.iterator();
			while (itrRes.hasNext()) {
				
				String resource = itrRes.next();
	
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getScheduledVE] " +
						"\n\t resource: " + resource);

				if (resource.toUpperCase().equals("VIRTUAL LAB") ||
					resource.toUpperCase().equals("CERTIFICATE")) {

					Iterator<String> itrCourse = courses.iterator();
					while (itrCourse.hasNext()) {

						String course = itrCourse.next();

						DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getScheduledVE] " +
								"\n\t\t course: " + course);

						// Get instance ids for this combination
						ArrayList<String> ve_ids = 
							virtualLabsDB.getVEInstanceIds(
									course,
									resource, 
									username, 
									true);
						
						// Get all the ve instances for this user
						Iterator<String> itrVEIns = ve_ids.iterator();
						while (itrVEIns.hasNext()) {

							String ve_id = itrVEIns.next();
							
							DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getScheduledVE] " +
									"\n\t\t\t ve_id: " + ve_id);

							request.setVeInsId(ve_id);							
							GetVEInsScheduleResponse veResp = veStub.getVEInsSchedule(request);

							User tempUser = virtualLabsDB.getUserUsingVEInsId(ve_id);
							
							if ((veResp.getSchedule() != null) && (tempUser != null)) {

								ScheduledEvent[] schedule = veResp.getSchedule();								

								// Translate the schedule into an array of appointments
								Appointment[] scheduledAppTempArr = 
									AppointmentTranslator.toAxisRepresentation(
											schedule, 
											resource, 
											course, 
											"SCHEDULED", 
											tempUser.getUserName(), 
											null);

								// Add appointments in the new array to the response
								List<Appointment> scheduledAppTempList = 
									Arrays.asList(scheduledAppTempArr);
								Iterator<Appointment> itrAvailApp = 
									scheduledAppTempList.iterator();
								while (itrAvailApp.hasNext()) {

									Appointment appAxis = itrAvailApp.next();

									scheduledAppointments.add(appAxis);									

									DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getScheduledVE] " +
											"\n\t\t\t\t scheduledAppointments.size(): " + scheduledAppointments.size() +
											"\n\t\t\t\t username: " + appAxis.getUserName() +
											"\n\t\t\t\t course: " + appAxis.getCourse() +
											"\n\t\t\t\t resource type: " + appAxis.getResourceType() +
											"\n\t\t\t\t start: " + appAxis.getStart().getTime() +
											"\n\t\t\t\t end: " + appAxis.getEnd().getTime() 
											);

								}

							}

						}

					}
					
				}
	
			}
	
		} 
		catch (Error e){
	
			e.printStackTrace();
	
		} 
		catch (Exception ex) {
	
			ex.printStackTrace();
	
		}
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getScheduledVE] Ready to get out!");
		
		return scheduledAppointments;
	
	}

	/*
	 * Calls GetMentoringSchedule 
	 * 
	 */
	private List<Appointment> getScheduledMentoring(
			String username, Calendar start, Calendar end) {
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getScheduledMentoring] Inside!");
		
		List<Appointment> scheduledAppointments = new ArrayList<Appointment>();
	
		try {
	
			Collection<String> resources = virtualLabsDB.getResources(username);
			Collection<String> courses = virtualLabsDB.getCourses(username);
	
			// Get all the resource-course combinations for this user
			Iterator<String> itrRes = resources.iterator();
			while (itrRes.hasNext()) {
				
				String resource = itrRes.next();
	
				if (resource.toUpperCase().equals("MENTORING")) {
					
					Iterator<String> itrCourse = courses.iterator();
					while (itrCourse.hasNext()) {

						// Set up the request
						GetMentoringScheduleRequest request = new GetMentoringScheduleRequest();
				
						TimePeriod period = new TimePeriod();
						period.setStartTime(start);
						period.setEndTime(end);
						request.setTimePeriod(period);
				
						String course = itrCourse.next();
						request.setCourseId(course);
						request.setStudentUsername(username);
						
						DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getScheduledMentoring] " +
								"request: \n" +
								"\tusername : " + request.getStudentUsername() + "\n" +
								"\tcourse id : " + request.getCourseId() + "\n" +
								"\tmentor username : " + request.getMentorUsername() + "\n" +
								"\tstart : " + request.getTimePeriod().getStartTime().getTime() + "\n" +
								"\tend : " + request.getTimePeriod().getEndTime().getTime());
						
						GetMentoringScheduleResponse mentoringResp = 
							mentorStub.getMentoringSchedule(request);

						if (mentoringResp.getSchedule() != null) {

							ScheduledEvent[] schedule = mentoringResp.getSchedule();								

							// Translate the schedule into an array of appointments
							Appointment[] scheduledAppTempArr = 
								AppointmentTranslator.toAxisRepresentation(
										schedule, 
										resource, 
										course, 
										"SCHEDULED", 
										username, 
										null);

							// Add appointments in the new array to the response
							List<Appointment> scheduledAppTempList = 
								Arrays.asList(scheduledAppTempArr);
							Iterator<Appointment> itrAvailApp = 
								scheduledAppTempList.iterator();
							while (itrAvailApp.hasNext()) {

								Appointment appAxis = itrAvailApp.next();
								scheduledAppointments.add(appAxis);

							}

						}

					}

				}
	
			}
			
		} 
		catch (Error e){
	
			e.printStackTrace();
	
		} 
		catch (Exception ex) {
	
			ex.printStackTrace();
	
		}
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getScheduledMentoring] Ready to get out!");
		
		return scheduledAppointments;
	
	}

	/**
	 * Add actions instances to the appointment that is being passed,
	 * depending on their availability type
	 * 
	 */
	private Appointment addActionsToAppointment(
			String requestingUser, Appointment appAxis) {
	
		Calendar now = Calendar.getInstance();
	
		User reqUser = virtualLabsDB.getUser(requestingUser);
		
		boolean resourceTypeIsCertificate = false;
		if (appAxis.getResourceType().equals("CERTIFICATE"))
			resourceTypeIsCertificate = true;
		boolean requestingUserIsAdmin = false;
		if (reqUser.getUserRole().equals("ADMIN"))
			requestingUserIsAdmin = true;
		
		boolean noAction = false;
		if (resourceTypeIsCertificate && !requestingUserIsAdmin)
			noAction = true;
		
		// Check if appointments is an old one
		if (!appAxis.getEnd().before(now) && !noAction) {
	
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
	 * 
	 * Merges schedule and availability so there is no overlapping and 
	 * availability is returned id day periods
	 */
	private List<Appointment> MergeLists(
			List<Appointment> available,
			List<Appointment> scheduled) {
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - MergeLists] Inside!");
		
		List<Appointment> mergedApps = new ArrayList<Appointment>();
	
		if (available == null || scheduled == null) {
			// Don't do anything; just return the mergedApps
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - MergeLists] " +
					"Either available or scheduled were null!");
		} else if (available.size() == 0 && scheduled.size() == 0) {
			// Don't do anything; just return the mergedApps
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - MergeLists] " +
					"available.size(): " + available.size());
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - MergeLists] " +
					"scheduled.size(): " + scheduled.size());
		} else if (available.size() == 0) {
			mergedApps = scheduled;
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - MergeLists] " +
					"available.size(): " + available.size());
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - MergeLists] " +
					"scheduled.size(): " + scheduled.size());
		} else if (scheduled.size() == 0) {
			mergedApps = available;
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - MergeLists] " +
					"available.size(): " + available.size());
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - MergeLists] " +
					"scheduled.size(): " + scheduled.size());
		} else {
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - MergeLists] " +
					"available.size(): " + available.size());
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - MergeLists] " +
					"scheduled.size(): " + scheduled.size());
			
			// Flag to add schedule apps just once
			boolean firstLoop = true;

			//Go over available slots first
			Iterator<Appointment> itrAvail = available.iterator();
			while (itrAvail.hasNext()) {

				Appointment availApp = itrAvail.next();

				if (availApp.getResourceType().toUpperCase().equals("MENTORING")) {

					mergedApps.add(availApp);
					continue;

				}

				boolean isAvailable = true;
				List<Appointment> overlappingApps = new ArrayList<Appointment>();

				//Now go over schedule apps
				Iterator<Appointment> itrSch = scheduled.iterator();
				while (itrSch.hasNext()) {

					Appointment schApp = itrSch.next();

					if (schApp.getResourceType().toUpperCase().equals("MENTORING")) {

						// Add just once of it is the first loop
						if (firstLoop)
							mergedApps.add(schApp);

						continue;

					}

					// Add just once of it is the first loop
					if (firstLoop)
						mergedApps.add(schApp);

					// If they overlap, set available slot as unavailable(!isAvailable)
					if (areOverlapping(schApp, availApp)) {

						if (isAvailable)
							isAvailable = false;

						//Keep track of the apps that overlap
						overlappingApps.add(schApp);

					} 

				}

				firstLoop = false;

				// If any schedule overlap with the available slot, add it to the list as it is
				if (isAvailable) {

					mergedApps.add(availApp);

				} 
				// If not, remove the slot the overlaps and return a list of remaining available slots
				else {

					List<Appointment> availableRemains = new ArrayList<Appointment>();
					availableRemains = getNewAvailableSlots(overlappingApps,availApp);

					Iterator<Appointment> itrRem = availableRemains.iterator();
					while (itrRem.hasNext()) {

						Appointment newAvalableSlot = itrRem.next();
						mergedApps.add(newAvalableSlot);

					}

				}

				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - MergeLists] " +
						"mergedApps.size(): " + mergedApps.size());
			}
		}	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - MergeLists] " +
				"mergedApps.size(): " + mergedApps.size());
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - MergeLists] Ready to get out!");
		
		return mergedApps;
	}

	/*
	 *Remove the time periods that are overlapping from the availability slots 
	 * 
	 */
	private List<Appointment> getNewAvailableSlots(
			List<Appointment> overlappingApps,
			Appointment availableSlot) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getNetAvailableSlots] Inside!");
		
		List<Appointment> availableRemains = new ArrayList<Appointment>();
	
		// In order to do this, the Axis generated appointment class was altered
		// No, I changed it back! ;) Masoud
		Collections.sort(overlappingApps, new SortByStartDate());
	
		//Create a copy of this appointment
		Appointment availApp = AppointmentTranslator.copy(availableSlot);
	
		Iterator<Appointment> itrApp = overlappingApps.iterator();
		while (itrApp.hasNext()) {
	
			//This condition is necessary when the available slot being analyzed
			//has been "absorbed" by the overlapping scheduled apps
			if (availApp == null) break;
	
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
	
		Calendar temp1 = overlappingApps.get(overlappingApps.size()-1).getEnd();
		Calendar temp2 = availableSlot.getEnd();
		if (temp1.before(temp2)) {
			
			Appointment lastRemain = new Appointment();
			lastRemain = AppointmentTranslator.copy(availApp);
			Calendar start = (Calendar)temp1.clone();
			Calendar end   = (Calendar)temp2.clone();
			lastRemain.setStart(start);
			lastRemain.setEnd(end);
			
			availableRemains.add(lastRemain);
			
		}
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getNetAvailableSlots] Ready to get out!");
		
		return availableRemains;
	}

	/**
	 * 
	 * @param scheduleUserAppointmentsRequest
	 * @return
	 */
	public ScheduleUserAppointmentsResponse scheduleUserAppointments(
			ScheduleUserAppointmentsRequest scheduleUserAppointmentsRequest) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleUserAppointments] Inside!");

		ScheduleUserAppointmentsResponse resp = new ScheduleUserAppointmentsResponse();

		//Get the request with the list of appointments
		Appointment[] apps = scheduleUserAppointmentsRequest.getAppointment();

		if (apps != null) {
		
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleUserAppointments] " +
					"apps[0].getUserName(): " + apps[0].getUserName());

			if (apps[0].getUserName().equals(ALL_STUDENTS)) {
			
				//Create a UUID for this batch of appointments
				UUID affiliationId = UUID.randomUUID();

				Appointment[] allApps = null;
				String course = apps[0].getCourse();

				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleUserAppointments] " +
						"Appointment for all students of course " + course +
						" was requested!");

				ArrayList<String> users = virtualLabsDB.getCourseUsers(course);
				for (int i=0; i<users.size(); i++) {
					String user = users.get(i);
					
					DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleUserAppointments] " +
							apps.length + " appointments for user " + user + " is being requested!");

					Appointment[] newApps = new Appointment[apps.length];
					for (int j=0; j<apps.length; j++) {
						newApps[j] = apps[j];
						newApps[j].setUserName(user);
						newApps[j].setAffiliationId(affiliationId.toString());
					}
					ScheduleUserAppointmentsRequest newReq = new ScheduleUserAppointmentsRequest();
					newReq.setAppointment(newApps);
					newReq.setRequestingUser(scheduleUserAppointmentsRequest.getRequestingUser());
					ScheduleUserAppointmentsResponse newResp = scheduleUserAppointments(newReq);
					Appointment[] retApps = newResp.getAppointment();

					DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleUserAppointments] " +
							retApps.length + " appointments for user " + user + " was scheduled!");

					if (allApps != null) {
						Appointment[] tempApps = new Appointment[allApps.length+retApps.length];
						for (int k=0; k<allApps.length; k++) {
							tempApps[k] = allApps[k];
						}
						int allAppLen = allApps.length;
						for (int k=0; k<retApps.length; k++) {
							tempApps[allAppLen+k] = retApps[k];
						}
						allApps = tempApps;
					} else {
						allApps = retApps;
					}
				}
				
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleUserAppointments] " +
						allApps.length + " appointments for all user of course " + course + " was scheduled!");

				resp.setAppointment(allApps);

				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleUserAppointments] Ready to get out!");

				return resp;
			}
		}
		
		// Fixing the time zone based on the default time zone for the requesting user.
		apps = addTimeZone(
				scheduleUserAppointmentsRequest.getRequestingUser(),
				apps);

		//Create a UUID for this batch of appointments
		UUID affiliationId = UUID.randomUUID();

		if (apps[0].getAffiliationId() != null) {
			if (apps[0].getAffiliationId().length() > 0) {
				affiliationId = UUID.fromString(apps[0].getAffiliationId());
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleUserAppointments] " +
						"Affiliation ID was set to " + affiliationId);
			}
		}

		try {

			// For every appointments in the request: 
			// First, check its validity.
			// Second, check whether there is enough resources.
			// Third, allocate quota, if available.
			// Fourth, allocate resources, if available.
			List<Appointment> appsList = Arrays.asList(apps);
			Iterator<Appointment> itrApp = appsList.iterator();
			while (itrApp.hasNext()) {
				
				Appointment origApp = itrApp.next();
				Appointment appointment = duplicateAppointment(origApp);

				appointment.setAffiliationId(affiliationId.toString());
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleUserAppointments] " +
						"Appointment: " + "\n" +
						"\tid: " + appointment.getId() + "\n" +
						"\taffilication id: " + appointment.getAffiliationId() + "\n" +
						"\tusername: " + appointment.getUserName() + "\n" +
						"\tcourse: " + appointment.getCourse() + "\n" +
						"\tresource type: " + appointment.getResourceType() + "\n" +
						"\tstart: " + appointment.getStart().getTime() + "\n" +
						"\tend: " + appointment.getEnd().getTime() + "\n" +
						"\tappointment.getResourceType(): ");

				
				// First, check to see whether the appointment is a valid one
				if (!isAppointmentValid(appointment)) {
					origApp.setAvailabilityStatus("The appointment is not valid!");
					resp.addAppointment(origApp);
					continue;
				}

				// Second, check if there are available resources for this appointment.
				if (!isResourceAvailable(appointment)) {
					origApp.setAvailabilityStatus("The required resources are not available!");
					resp.addAppointment(origApp);
					continue;
				}
				
				// Third, try to allocate the quota.
				UUID quotaId = UUID.randomUUID();
				
				if (appointment.getResourceType().toUpperCase().equals("VIRTUAL LAB") ||
					appointment.getResourceType().toUpperCase().equals("CERTIFICATE") ||
					appointment.getResourceType().toUpperCase().equals("MENTORING")) {
					if (!allocateQuota(appointment, quotaId)) {
						origApp.setAvailabilityStatus("The user does not have enough quota!");
						resp.addAppointment(origApp);
						continue;
					}
				} else if (appointment.getResourceType().toUpperCase().equals("PHYSICAL")) {			
					// no need for allocating quota
				} else {
					// no need for allocating quota					
				}
				
				String requestingUser = scheduleUserAppointmentsRequest.getRequestingUser();
				// Fourth, try to allocate the resources.
				if (!allocateResource(requestingUser, appointment, quotaId)) {
					// If the resources were not successfully allocated, we will 
					// cancel the quota allocation.
					if (appointment.getResourceType().toUpperCase().equals("VIRTUAL LAB") ||
							appointment.getResourceType().toUpperCase().equals("CERTIFICATE") ||
							appointment.getResourceType().toUpperCase().equals("MENTORING")) {
						if (!releaseQuota(quotaId)) {
							resp.setAppointment(apps);
							DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleUserAppointments] " +
									"ERROR: Resource could not be scheduled and the attempt to" +
							"release the quota failed! Please inform the developer!");
						}
					} else if (appointment.getResourceType().toUpperCase().equals("PHYSICAL")) {			
						// no need for releasing quota
					} else {
						// no need for releasing quota					
					}
					origApp.setAvailabilityStatus("The required resources could not be allocated!");
					resp.addAppointment(origApp);
					continue;
				}
			
				// Add the appointment to the response list of appointments
				appointment.setAvailabilityStatus("SCHEDULED");
				resp.addAppointment(appointment);
			} 
		} catch (Exception ex) {
			resp.setAppointment(apps);
			ex.printStackTrace();
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleUserAppointments] Ready to get out!");

		return resp;
	}
	
	/**
	 * 
	 * @param scheduleUserAppointmentsRequest
	 * @return
	 */
	public ScheduleUserAppointmentsWithEncryptedPasswordResponse scheduleUserAppointmentsWithEncryptedPassword(
			ScheduleUserAppointmentsWithEncryptedPasswordRequest scheduleUserAppointmentsWithEncryptedPasswordRequest) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleUserAppointmentsWithEncryptedPassword] Inside!");

		ScheduleUserAppointmentsWithEncryptedPasswordResponse resp = new ScheduleUserAppointmentsWithEncryptedPasswordResponse();

		EncryptedCredential[] creds = scheduleUserAppointmentsWithEncryptedPasswordRequest.getEncryptedCredential();
		for (EncryptedCredential cred : creds) {
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleUserAppointmentsWithEncryptedPassword] "
					+ "userName: " + cred.getUserName() 
					+ " encryptedPassword: " + cred.getEncryptedPassword()
					+ " password: " + Crypt.decrypt(cred.getEncryptedPassword()));
			setUserCachedPassword(
					cred.getUserName(), 
					Crypt.decrypt(cred.getEncryptedPassword()));
		}

		ScheduleUserAppointmentsRequest wrappedReq = new ScheduleUserAppointmentsRequest();
		wrappedReq.setRequestingUser(scheduleUserAppointmentsWithEncryptedPasswordRequest.getRequestingUser());
		wrappedReq.setAppointment(scheduleUserAppointmentsWithEncryptedPasswordRequest.getAppointment());
		ScheduleUserAppointmentsResponse wrappedResp = scheduleUserAppointments(wrappedReq);
		
		Appointment[] apps = wrappedResp.getAppointment();
		for (Appointment app : apps)
			resp.addAppointment(app);
		
		// passTerminator.notifyAll();

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleUserAppointmentsWithEncryptedPassword] Ready to get out!");

		return resp;
	}

	private void setUserCachedPassword(String username, String password) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - setUserCachedPassword] Inside!");
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - setUserCachedPassword] "
				+ "username: " + username + " "
				+ "password: " + password);
		
		virtualLabsDB.setUserCachedPassword(username, password);		
		virtualLabsDB.eliminatePlaintextPassword(username);
		// passTerminator.notifyAll();
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - setUserCachedPassword] Ready to get out!");
	
	}

	/**
	 * 
	 * @param scheduleCourseAppointmentRequest
	 * @return
	 */
	public ScheduleCourseAppointmentResponse scheduleCourseAppointment(
			ScheduleCourseAppointmentRequest scheduleCourseAppointmentRequest) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleCourseAppointment] Inside!");

		ScheduleCourseAppointmentResponse resp = new ScheduleCourseAppointmentResponse();

		ArrayList<String> users = 
			virtualLabsDB.getCourseUsers(scheduleCourseAppointmentRequest.getCourse());

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleCourseAppointment] " +
				"users.size(): " + users.size());

		ScheduleUserAppointmentsRequest userAppsReq = 
			new ScheduleUserAppointmentsRequest();
		userAppsReq.setRequestingUser(scheduleCourseAppointmentRequest.getRequestingUser());
		Appointment [] apps = null;
		
		try {

			ArrayList<Appointment> userApps = new ArrayList<Appointment>();
			
			// For every user in this course, create an appointment
			Iterator<String> itrUser = users.iterator();
			while (itrUser.hasNext()) {
				
				String username = itrUser.next();
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleCourseAppointment] " +
						"\tusername: " + username);
				Appointment app = new Appointment();
				app.setUserName(username);
				app.setCourse(scheduleCourseAppointmentRequest.getCourse());
				app.setStart(scheduleCourseAppointmentRequest.getStart());
				app.setEnd(scheduleCourseAppointmentRequest.getEnd());
				app.setResourceType(scheduleCourseAppointmentRequest.getResourceType());
				app.setAction(new Action[1]);
				app.setAffiliationId("");
				app.setAvailabilityStatus("");

				userApps.add(app);
				
			} 

			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleCourseAppointment] " +
					"userApps.size(): " + userApps.size());

			apps = new Appointment[userApps.size()];
			/* 
			for (int i=0; i<userApps.size(); i++) {
				apps[i] = userApps.get(i);
			}
			*/
			userApps.toArray(apps);
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleCourseAppointment] " +
					"apps.length: " + apps.length);

			userAppsReq.setAppointment(apps);
			
			ScheduleUserAppointmentsResponse userAppResp = 
				scheduleUserAppointments(userAppsReq);
			
			resp.setAppointment(userAppResp.getAppointment());
			
		} catch (Exception ex) {
			resp.setAppointment(apps);
			ex.printStackTrace();
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleCourseAppointment] Ready to get out!");

		return resp;
	}

	private boolean releaseQuota(UUID quotaId) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - releaseQuota] Inside!");

		edu.fiu.cis.acrl.quotasystem.ws.CancelAppointmentRequest req =
			new edu.fiu.cis.acrl.quotasystem.ws.CancelAppointmentRequest();
		req.setAppointmentId(quotaId.toString());
		edu.fiu.cis.acrl.quotasystem.ws.CancelAppointmentResponse resp = null;
		try {
			resp = qsStub.cancelAppointment(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - releaseQuota] Ready to get out!");

		return resp.getSuccess();
	}

	private Appointment duplicateAppointment(Appointment appointment) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - duplicateAppointment] Inside!");

		Appointment dupApp = new Appointment();
		dupApp.setAction(appointment.getAction());
		dupApp.setAffiliationId(appointment.getAffiliationId());
		dupApp.setAvailabilityStatus(appointment.getAvailabilityStatus());
		dupApp.setCourse(appointment.getCourse());
		dupApp.setEnd(appointment.getEnd());
		dupApp.setId(appointment.getId());
		dupApp.setResourceType(appointment.getResourceType());
		dupApp.setStart(appointment.getStart());
		dupApp.setUserName(appointment.getUserName());
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - duplicateAppointment] Ready to get out!");

		return dupApp;
	}

	private boolean isAppointmentValid(Appointment appointment) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isAppointmentValid] Inside!");

		boolean valid = true;
		
		// Check if the start is before the end date
		if (appointment.getEnd().before(appointment.getStart()) || 
			!appointment.getStart().before(appointment.getEnd())) 
			valid = false;
		// Check if the start is before now
		else if (appointment.getStart().before(Calendar.getInstance())) 
			appointment.setStart(Calendar.getInstance());

		// Check if the user exist
		if (!virtualLabsDB.userExists(appointment.getUserName())) 
			valid = false;
		
		if (appointment.getResourceType().toUpperCase().equals("VIRTUAL LAB") ||
			appointment.getResourceType().toUpperCase().equals("CERTIFICATE")) {
			// Get the ve type for the course and resource type of 
			// this scheduling request
			String ve_type = 
				virtualLabsDB.getVEType(
						appointment.getCourse(),
						appointment.getResourceType());
			// Check if ve type does not exist
			if (ve_type == null) 
				valid = false;
		}
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isAppointmentValid] " +
				"Right before calling isOverlappingWithUserScheduledAppointments! " +
				"valid is: " + valid);

		// vLab->Cert is allowed, but not vice versa.
		if (appointment.getResourceType().toUpperCase().equals("CERTIFICATE")) {
			if(!cancelAllOtherOverlappingAppointments(appointment)) {
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isAppointmentValid] " +
						"cancelAllOtherOverlappingAppointments was not successful!");
				valid = false;
			}
		}	
		
		// Check if the new appointment does not overlap with existing
		// scheduled appointments
		if(isOverlappingWithUserScheduledAppointments(appointment))
			valid = false;

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isAppointmentValid] " +
				"Right after calling isOverlappingWithUserScheduledAppointments! " +
				"valid is: " + valid);

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isAppointmentValid] Ready to get out!");

		return valid;
		
	}

	private boolean allocateResource(
			String requestingUser,
			Appointment appointment, 
			UUID quotaId) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - allocateResource] Inside!");

		boolean retVal = false;
		
		if (appointment.getResourceType().toUpperCase().equals("VIRTUAL LAB") ||
			appointment.getResourceType().toUpperCase().equals("CERTIFICATE")) {
			// Get VE Instance Id
			String veInsId = 
				virtualLabsDB.getVEInstanceId(
					appointment.getCourse(), 
					appointment.getResourceType().toUpperCase(),
					appointment.getUserName(), 
					true);

			retVal = scheduleUserAppointment(
					requestingUser,
					appointment, 
					veInsId, 
					quotaId);
		} else if (appointment.getResourceType().toUpperCase().equals("MENTORING")) {
			retVal = scheduleMentoring(appointment, quotaId);
		} else if (appointment.getResourceType().toUpperCase().equals("PHYSICAL")) {			
			retVal = scheduleHostMaintenanceAppointment(appointment);
		} else {
			retVal = false;
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - allocateResource] retVal: " + retVal);

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - allocateResource] Ready to get out!");

		return retVal;
	}

	private boolean allocateQuota(
			Appointment appointment, 
			UUID quotaId) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - allocateQuota] Inside!");

		edu.fiu.cis.acrl.quotasystem.ws.ScheduleAppointmentsRequest qsReq =
			new edu.fiu.cis.acrl.quotasystem.ws.ScheduleAppointmentsRequest();
		edu.fiu.cis.acrl.quotasystem.ws.Appointment qsApp =
			convertVLApp2QSApp(appointment);
		qsApp.setId(quotaId.toString());
		qsReq.addAppointment(qsApp);
		edu.fiu.cis.acrl.quotasystem.ws.ScheduleAppointmentsResponse qsResp = null;
		try {
			qsResp = qsStub.scheduleAppointments(qsReq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		boolean quotaIsAvailable = false;
		if (qsResp != null) {
			
			edu.fiu.cis.acrl.quotasystem.ws.Appointment[] qsRetApps = 
				qsResp.getAppointment();
			
			if (qsRetApps != null) {
				
				if (qsRetApps.length > 0) {
					
					edu.fiu.cis.acrl.quotasystem.ws.Appointment qsRetApp =
						qsRetApps[0];
					
					// The availability status can be AVAILABLE or UNAVAILABLE
					if (qsRetApp.getAvailabilityStatus().equals("AVAILABLE")) {
						
						quotaIsAvailable = true;
						
					}
				}
			}
		}
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - allocateQuota] Ready to get out!");

		return quotaIsAvailable;		
	}

	private edu.fiu.cis.acrl.quotasystem.ws.Appointment convertVLApp2QSApp(
			Appointment appointment) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - convertVLApp2QSApp] Inside!");

		edu.fiu.cis.acrl.quotasystem.ws.Appointment qsApp =
			new edu.fiu.cis.acrl.quotasystem.ws.Appointment();

		qsApp.setId(appointment.getId());
		qsApp.setAffiliationId(appointment.getAffiliationId());
		qsApp.setUsername(appointment.getUserName());
		qsApp.setResource(appointment.getResourceType());
		qsApp.setCourse(appointment.getCourse());
		qsApp.setStartTime(appointment.getStart());
		qsApp.setEndTime(appointment.getEnd());
		qsApp.setAvailabilityStatus(appointment.getAvailabilityStatus());

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - convertVLApp2QSApp] Ready to get out!");

		return qsApp;		
	}

	private boolean isResourceAvailable(Appointment appointment) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isResourceAvailable] Inside!");
		
		/*
		if (appointment.getResourceType().toUpperCase().equals("VIRTUAL LAB") ||
				appointment.getResourceType().toUpperCase().equals("CERTIFICATE")) {
		} else if (appointment.getResourceType().toUpperCase().equals("MENTORING")) {
		} else if (appointment.getResourceType().toUpperCase().equals("PHYSICAL")) {			
		}
		*/
		
		boolean available = false;
		
		if (appointment.getResourceType().toUpperCase().equals("VIRTUAL LAB") ||
				appointment.getResourceType().toUpperCase().equals("CERTIFICATE")) {
			available = isVEResourceAvailable(appointment);
		} else if (appointment.getResourceType().toUpperCase().equals("MENTORING")) {
			available = isMentoringResourceAvailable(appointment);
		} else if (appointment.getResourceType().toUpperCase().equals("PHYSICAL")) {			
			available = isPhysicalResourceAvailable(appointment);
		} else {
			available = false;
		}
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isResourceAvailable] Ready to get out!");
		
		return available;
	}
	
	private boolean isVEResourceAvailable(Appointment appointment) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isVEResourceAvailable] Inside!");

		GetVEAvailabilityRequest req = new GetVEAvailabilityRequest();
	
		// Get the ve type for the course and resource type 
		String ve_type = 
			virtualLabsDB.getVEType(
					appointment.getCourse(),
					appointment.getResourceType());
		try {
			req.setVe(readVE(new File(ve_type)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Get VE Instance Id
		String veInsId = 
			virtualLabsDB.getVEInstanceId(
				appointment.getCourse(), 
				appointment.getResourceType().toUpperCase(),
				appointment.getUserName(), 
				true);
		req.setVeInsId(veInsId);
		
		TimePeriod timePeriod = new TimePeriod();
		timePeriod.setStartTime(appointment.getStart());
		timePeriod.setEndTime(appointment.getEnd());
		req.setTimePeriod(timePeriod);
		
		GetVEAvailabilityResponse resp = null;
		try {
			resp = veStub.getVEAvailability(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		boolean available = false;
		TimePeriod[] timePeriods = resp.getAvailabilityRange();
		if (timePeriods != null) {
			if (timePeriods.length > 0) {

				Calendar availStart = timePeriods[0].getStartTime();
				Calendar availEnd   = timePeriods[0].getEndTime()  ;
				Calendar appStart   = appointment.getStart()       ;
				Calendar appEnd     = appointment.getEnd()         ; 

				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isVEResourceAvailable] " +
						"availStart.getTime().equals(appStart.getTime()): " + availStart.getTime().equals(appStart.getTime()) + " " +
						"availEnd.getTime().equals(appEnd.getTime()): " + availEnd.getTime().equals(appEnd.getTime()));
				
				if (availStart.getTime().equals(appStart.getTime()) &&
					availEnd.getTime().equals(appEnd.getTime())) {
					available = true;
				}
			}
		}
			
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isVEResourceAvailable] available: " + available);

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isVEResourceAvailable] Ready to get out!");

		return available;
	}

	private boolean isMentoringResourceAvailable(Appointment appointment) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isMentoringResourceAvailable] Inside!");

		GetMentoringAvailabilityRequest req = new GetMentoringAvailabilityRequest();
	
		req.setCourseId(appointment.getCourse());
		req.setMentorUsername(null);
		req.setStudentUsername(appointment.getUserName());
		TimePeriod timePeriod = new TimePeriod();
		timePeriod.setStartTime(appointment.getStart());
		timePeriod.setEndTime(appointment.getEnd());
		req.setTimePeriod(timePeriod);
		
		GetMentoringAvailabilityResponse resp = null;
		try {
			resp = mentorStub.getMentoringAvailability(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		boolean available = false;
		TimePeriod[] timePeriods = resp.getAvailabilityRange();
		if (timePeriods != null) {
			if (timePeriods.length > 0) {

				Calendar availStart = timePeriods[0].getStartTime();
				Calendar availEnd   = timePeriods[0].getEndTime()  ;
				Calendar appStart   = appointment.getStart()       ;
				Calendar appEnd     = appointment.getEnd()         ; 

				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isMentoringResourceAvailable] " +
						"availStart.getTime().equals(appStart.getTime()): " + availStart.getTime().equals(appStart.getTime()) + " " +
						"availEnd.getTime().equals(appEnd.getTime()): " + availEnd.getTime().equals(appEnd.getTime()));
				
				if (availStart.getTime().equals(appStart.getTime()) &&
					availEnd.getTime().equals(appEnd.getTime())) {
					available = true;
				}
			}
		}
			
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isMentoringResourceAvailable] available: " + available);

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isMentoringResourceAvailable] Ready to get out!");

		return available;
	}

	private boolean isPhysicalResourceAvailable(Appointment appointment) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isPhysicalResourceAvailable] Inside!");

		GetHostMaintenanceAvailabilityRequest req = new GetHostMaintenanceAvailabilityRequest();
	
		GetHostUsingNameRequest reqHost = new GetHostUsingNameRequest();
		reqHost.setHostName(appointment.getCourse());
		GetHostUsingNameResponse respHost = null;
		try {
			respHost = veStub.getHostUsingName(reqHost);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		req.setHostId(respHost.getHost().getId());
		
		TimePeriod timePeriod = new TimePeriod();
		timePeriod.setStartTime(appointment.getStart());
		timePeriod.setEndTime(appointment.getEnd());
		req.setTimePeriod(timePeriod);
		
		GetHostMaintenanceAvailabilityResponse resp = null;
		try {
			resp = veStub.getHostMaintenanceAvailability(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		boolean available = false;
		TimePeriod[] timePeriods = resp.getAvailabilityRange();
		if (timePeriods != null) {
			if (timePeriods.length > 0) {

				Calendar availStart = timePeriods[0].getStartTime();
				Calendar availEnd   = timePeriods[0].getEndTime()  ;
				Calendar appStart   = appointment.getStart()       ;
				Calendar appEnd     = appointment.getEnd()         ; 

				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isPhysicalResourceAvailable] " +
						"availStart.getTime().equals(appStart.getTime()): " + availStart.getTime().equals(appStart.getTime()) + " " +
						"availEnd.getTime().equals(appEnd.getTime()): " + availEnd.getTime().equals(appEnd.getTime()));
				
				if (availStart.getTime().equals(appStart.getTime()) &&
					availEnd.getTime().equals(appEnd.getTime())) {
					available = true;
				}
			}
		}
			
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isPhysicalResourceAvailable] available: " + available);

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isPhysicalResourceAvailable] Ready to get out!");

		return available;
	}

	private Appointment[] addTimeZone(
			String requestingUser,
			Appointment[] appointments) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addTimeZone] Inside!");

		Appointment[] fixedApps = new Appointment[appointments.length];
		
		User user = virtualLabsDB.getUser(requestingUser);
		TimeZone timeZone = TimeZone.getTimeZone(user.getTimeZone());
		
		int i = 0;
		for (Appointment app : appointments) {
			
			Calendar start = app.getStart();
			if (start == null) {	
				start = Calendar.getInstance();
				start.add(Calendar.SECOND, 10);
				start.setTimeZone(timeZone);
			} else {
				start = TimeZoneTools.changeTimeZone(start, timeZone);
			}

			Calendar end = app.getEnd();
			if (end == null || end.equals("")) {	
				end = (Calendar) start.clone();
				end.add(Calendar.MINUTE, 120);
				end.setTimeZone(timeZone);
			} else {
				end = TimeZoneTools.changeTimeZone(end, timeZone);
			}
			
			app.setStart(start);
			app.setEnd(end);
			fixedApps[i] = app;
			i++;
			
		}
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addTimeZone] Ready to get out!");

		return fixedApps;		
	}

	/*
	 * Function that cancels any overlapping appointments.
	 * returns true if nothing is overlapping and all overlapping are canceled.
	 */
	private boolean cancelAllOtherOverlappingAppointments(Appointment app)
	{
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - cancelAllOtherOverlappingAppointments] Inside!");
	
		boolean retVal = true;
		
		List<Appointment> scheduledApps = new ArrayList<Appointment>();
		
		scheduledApps = 
			getScheduledVE(
					app.getUserName(), 
					app.getStart(),
					app.getEnd());   				

		Iterator<Appointment> itrApp = scheduledApps.iterator();
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - cancelAllOtherOverlappingAppointments] " +
				"\tapp.getUserName()" + app.getUserName() + "\n" +
				"\tapp.getffilicationId()" + app.getAffiliationId() + "\n" +
				"\tapp.getId()" + app.getId() + "\n" +
				"\tapp.getStart()" + app.getStart().getTime() + "\n" +
				"\tapp.getEnd()" + app.getEnd().getTime());

		while(itrApp.hasNext()) {
			
			boolean cancelApp = false;
			
			Appointment schApp = itrApp.next();
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - cancelAllOtherOverlappingAppointments] " +
					"\tschApp.getffilicationId(): " + schApp.getAffiliationId() + "\n" +
					"\tschApp.getId(): " + schApp.getId() + "\n" +
					"\tschApp.getResourceType(): " + schApp.getResourceType() + "\n" +
					"\tschApp.getStart(): " + schApp.getStart().getTime() + "\n" +
					"\tschApp.getEnd(): " + schApp.getEnd().getTime());

			if (app.getId() != null) {				
				if (!app.getId().equals(schApp.getId())) {
					if (areOverlapping(app, schApp))
						cancelApp = true;
				}
			} else {
				if (areOverlapping(app, schApp))
					cancelApp = true;
			}
			
			if (cancelApp) {
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - cancelAllOtherOverlappingAppointments] " +
						"Overlapping app is being cancelled! schApp.getId(): " + schApp.getId());
				CancelUserAppointmentRequest req = new CancelUserAppointmentRequest();
				req.setId(schApp.getId());
				req.setRequestingUser(app.getUserName());
				CancelUserAppointmentResponse resp = cancelUserAppointment(req);
				if (!resp.getSuccess()) { 
					DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - cancelAllOtherOverlappingAppointments] " +
							"Cancelling app id " + schApp.getId() + " was not successful!");
					DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - cancelAllOtherOverlappingAppointments] " +
							"Reason was: " + resp.getReason());
					retVal = false;
				}
			}
		}
			
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - cancelAllOtherOverlappingAppointments] retVal: " + retVal);
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - cancelAllOtherOverlappingAppointments] Ready to get out!");
		
		return retVal;
}

	/*
	 * Function that checks id an scheduled appointment overlaps with app.
	 * 	
	 */
	private boolean isOverlappingWithUserScheduledAppointments(Appointment app)
	{
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isOverlappingWithUserScheduledAppointments] Inside!");
	
		boolean retVal = false;
		
		List<Appointment> scheduledApps = new ArrayList<Appointment>();
		
		if (app.getResourceType().toUpperCase().equals("MENTORING"))
			scheduledApps = 
				getScheduledMentoring(
						app.getUserName(), 
						app.getStart(),
						app.getEnd());   				
		else
			scheduledApps = 
				getScheduledVE(
						app.getUserName(), 
						app.getStart(),
						app.getEnd());   				

		Iterator<Appointment> itrApp = scheduledApps.iterator();
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isOverlappingWithUserScheduledAppointments] " +
				"\tapp.getUserName()" + app.getUserName() + "\n" +
				"\tapp.getffilicationId()" + app.getAffiliationId() + "\n" +
				"\tapp.getId()" + app.getId() + "\n" +
				"\tapp.getStart()" + app.getStart().getTime() + "\n" +
				"\tapp.getEnd()" + app.getEnd().getTime());

		while(itrApp.hasNext()) {
			
			Appointment schApp = itrApp.next();
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isOverlappingWithUserScheduledAppointments] " +
					"\tschApp.getffilicationId(): " + schApp.getAffiliationId() + "\n" +
					"\tschApp.getId(): " + schApp.getId() + "\n" +
					"\tschApp.getResourceType(): " + schApp.getResourceType() + "\n" +
					"\tschApp.getStart(): " + schApp.getStart().getTime() + "\n" +
					"\tschApp.getEnd(): " + schApp.getEnd().getTime());

			if (app.getId() != null) {				
				if (!app.getId().equals(schApp.getId())) {
					if (areOverlapping(app, schApp))
						retVal = true;
				}
			} else {
				if (areOverlapping(app, schApp))
					retVal = true;
			}
		}
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isOverlappingWithUserScheduledAppointments] retVal: " + retVal);
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isOverlappingWithUserScheduledAppointments] Ready to get out!");
		
		return retVal;
	}

	private boolean scheduleMentoring(
			Appointment appointment,
			UUID quotaId) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleMentoring] Inside!");
		
		boolean retVal = false;
		
		ScheduleMentoringRequest request = new ScheduleMentoringRequest();
		request.setStudentUsername(appointment.getUserName());
		request.setCourseId(appointment.getCourse());

		TimePeriod period = new TimePeriod();
		period.setStartTime(appointment.getStart());
		period.setEndTime(appointment.getEnd());
		request.setTimePeriod(period);

		GetRandomMentorRequest mentorReq = 
			new GetRandomMentorRequest();
		mentorReq.setCourseId(appointment.getCourse());
		mentorReq.setTimePeriod(period);
		GetRandomMentorResponse mentorResp = null;
		try {
			mentorResp = mentorStub.getRandomMentor(mentorReq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setMentorUsername(
				mentorResp.getMentorUsername());

		try {
			
			ScheduleMentoringResponse response = mentorStub.scheduleMentoring(request);
	
			if (response.getSuccess()) {
	
				String id = response.getId();
				appointment.setId(id);
				appointment.setAvailabilityStatus("SCHEDULED");
				
				/**To Local representation means to create an instance of 
				 * DbAppointment which is used in db methods
				 */
				DbAppointment app = AppointmentTranslator.toLocalRepresentation(appointment);
				app.setActive(true);
				app.setQuotaId(quotaId.toString());
				virtualLabsDB.scheduleAppointment(app);
				// TODO
				appointment = addActionsToAppointment("", appointment);
			
			} else {
			
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleMentoring] Appointment has not been scheduled!");
			
			}
	
			retVal = response.getSuccess();
		} 
		catch (Error e) {
			e.printStackTrace();
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleMentoring] Ready to get out!");
		
		return retVal;
	}

	/**
	 * scheduleAppointment
	 */
	private boolean scheduleUserAppointment(
			String requestingUser,
			Appointment appointment,
			String veInsId, 
			UUID quotaId) {
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleUserAppointment] Inside!");
		
		boolean retVal = false;
		
		ScheduleVERequest request = new ScheduleVERequest();
		
		request.setUsername(appointment.getUserName());

		// Get the ve type for the course and resource type of 
		// this scheduling request
		String ve_type = 
			virtualLabsDB.getVEType(
					appointment.getCourse(),
					appointment.getResourceType());
		try {
			request.setVe(readVE(new File(ve_type)));
		} catch (Exception e) {
			e.printStackTrace();
		}

		TimePeriod period = new TimePeriod();
		period.setStartTime(appointment.getStart());
		period.setEndTime(appointment.getEnd());
		request.setTimePeriod(period);

		request.setStore(true);

		// Certificates are not persistent
		if (appointment.getResourceType().toUpperCase().equals("CERTIFICATE"))
			request.setStore(false);
		
		request.setVeInsId(veInsId);
		
		try {
			
			// Make the call
			ScheduleVEResponse veResp = veStub.scheduleVE(request);
			if (veResp.getSuccess()) {
	
				String schId = veResp.getSchId();

				// If veInsId is null, then it is the first time this veIns is bein scheduled.
				if (veInsId == null) {
					
					veInsId = schId;
					
					virtualLabsDB.addVEInstance(
							veInsId, 
							appointment.getUserName(),
							appointment.getCourse(), 
							appointment.getResourceType());
					String promoCode = virtualLabsDB.getPromoCode(veInsId);
					int promoId = virtualLabsDB.getPromoId(promoCode);
					int kServerId = veSchDB.getKServerIdByPromoId(promoId);
					veSchDB.setKServer4VEIns(veInsId, kServerId);
				
					addInitialTasks4NewVMs(veInsId);
					
				}

				appointment.setId(schId);
				appointment.setAvailabilityStatus("SCHEDULED");
				DbAppointment app = 
					AppointmentTranslator.toLocalRepresentation(appointment);
				app.setActive(true);
				app.setQuotaId(quotaId.toString());
				virtualLabsDB.scheduleAppointment(app);
				appointment = addActionsToAppointment(requestingUser, appointment);
	
			} else {
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleUserAppointment] Appointment has not been scheduled!");
			}
			
			retVal = veResp.getSuccess();
	
		} catch (Error e) {
			e.printStackTrace();
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleUserAppointment] Ready to get out!");
		
		return retVal;		
	}

	private boolean scheduleHostMaintenanceAppointment(Appointment appointment) {
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleHostMaintenanceAppointment] Inside!");
		
		boolean retVal = false;
		
		ScheduleHostMaintenanceRequest request = new ScheduleHostMaintenanceRequest();
		
		GetHostUsingNameRequest reqHost = new GetHostUsingNameRequest();
		reqHost.setHostName(appointment.getCourse());
		GetHostUsingNameResponse respHost = null;
		try {
			respHost = veStub.getHostUsingName(reqHost);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		request.setHostId(respHost.getHost().getId());
		
		TimePeriod period = new TimePeriod();
		period.setStartTime(appointment.getStart());
		period.setEndTime(appointment.getEnd());
		request.setTimePeriod(period);

		try {
			
			// Make the call
			ScheduleHostMaintenanceResponse veResp = veStub.scheduleHostMaintenance(request);
			if (veResp.getSuccess()) {
				String schId = veResp.getSchId();

				appointment.setId(schId);
				appointment.setAvailabilityStatus("SCHEDULED");
				DbAppointment app = 
					AppointmentTranslator.toLocalRepresentation(appointment);
				app.setActive(true);
				app.setQuotaId(null);
				virtualLabsDB.scheduleAppointment(app);
				// TODO
				appointment = addActionsToAppointment("", appointment);
			} else {
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleHostMaintenanceAppointment] Appointment has not been scheduled!");
			}
			
			retVal = veResp.getSuccess();	
		} catch (Error e) {
			e.printStackTrace();
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleHostMaintenanceAppointment] Ready to get out!");
		
		return retVal;		
	}

	private void addInitialTasks4NewVMs(String veInsId) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4NewVMs] Inside!");
		
		UserVEInstance userVEIns = virtualLabsDB.getUserVEInstance(veInsId);
		String veTypeName = 
			virtualLabsDB.getVEType(
					userVEIns.getCourse(), 
					userVEIns.getResourceType());
		VirtualEnvironmentType veType = null;
		try {
		
			veType = readVE(new File(veTypeName));
		
		} catch (Exception e) {
		
			e.printStackTrace();
			return;
		}
		
		GetVEMacsRequest getVEMacsReq = new GetVEMacsRequest();
		getVEMacsReq.setVeInsId(veInsId);
		GetVEMacsResponse getVEMacsResp = null;
		try {
			getVEMacsResp = veStub.getVEMacs(getVEMacsReq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] veInsMacs = getVEMacsResp.getMacAddress();

		int counter = 0;
		for (String macAddress : veInsMacs) {

			counter++;
			
			VMInsTask task = 
				new VMInsTask(
						0,
						macAddress,
						TodoType.CHANGE_CHECKIN_INTERVAL,
						Calendar.getInstance(),
						true);
			virtualLabsDB.addVMInsTask(task);

			int taskId = 0;
			VMInsSyncUserTask syncTask = null;
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4NewVMs] " +
					"###############################################################"); 
			// Creating users for this vm
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4NewVMs] " +
					"Create end users based on the users listed in the ve type: " + veType.getVEName());

			User user = virtualLabsDB.getUserByVeInsId(veInsId);
			VMInstance vmIns = veSchDB.getVMInstanceUsingMac(macAddress);
			VENodeListType veNodeList = veType.getVENodeList();
			if (veNodeList != null) {
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4NewVMs] " +
				"veNodeList is not null!"); 
				VENodeType[] veNodes = veNodeList.getVENode();
				if (veNodes != null) {
					DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4NewVMs] " +
					"veNodes is not null! veNodes.length is " + veNodes.length); 
					int cnt = 0;
					for (VENodeType veNode : veNodes) {
						DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4NewVMs] " +
						"cnt is " + cnt); 
						if (veNode.getVirtualAppliance().getName().equals(vmIns.getName()))
							break;
						cnt++;
					}
					DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4NewVMs] " +
					"cnt after break is " + cnt); 
					if (cnt < veNodes.length) {
						VENodeType veNode = veNodes[cnt];
						if (veNode != null) {
							DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4NewVMs] " +
							"veNode is not null!"); 
							UsersType usersType = veNode.getVirtualAppliance().getUsers();
							if (usersType != null) {
								DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4NewVMs] " +
								"usersType is not null!"); 
								UserType[] users = usersType.getUser();
								if (users != null) {
									DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4NewVMs] " +
									"users is not null! user.length is " + users.length); 
									if (users.length > 0) {
										for (UserType newUser : users) {
											String username  = newUser.getUsername();
											String password  = newUser.getPassword();
											String firstname = newUser.getFirstname();
											String lastname  = newUser.getLastname();
											String vmName = vmIns.getName();
											String endUserName = username + "-" + user.getUserName();
											DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4NewVMs] " +
													"Creatint username " + endUserName + " " +
													"on " + vmName + " " +
													"for "+ firstname + " " + lastname + "." 
											);
											task = 
												new VMInsTask(
														0,
														macAddress,
														TodoType.SYNC_USER_CREDENTIALS,
														Calendar.getInstance(),
														true);
											taskId = virtualLabsDB.addVMInsTask(task);
											syncTask =
												new VMInsSyncUserTask(
														taskId,
														endUserName,
														"",
														true);
											virtualLabsDB.addVMInsSyncUserTask(syncTask);
											DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4NewVMs] " +
											"task sync user was added for " + endUserName); 
										}
									}
								}
							}	
						}
					}				
				}
			}
			
			// TODO: this is specific to the ve type that we have currently.
			String promoCode = virtualLabsDB.getPromoCode(veInsId);
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4NewVMs] " +
					"Create end users based on the promoCode: " + promoCode);
			// SMS: To accommodate ITS-KSDW-14-1D
			// if (promoCode.equals(ITSKSDW)) {
			if (promoCode.contains("KSDW")) {
				
				// User user = virtualLabsDB.getUserByVeInsId(veInsId);
				// VMInstance vmIns = veSchDB.getVMInstanceUsingMac(macAddress);
				String vmName = vmIns.getName();
				int beginIndex = vmName.indexOf('(');
				int endIndex = vmName.indexOf(')');
				String vmShortName = null;
				if ((beginIndex > 0) && (endIndex > 0) && (beginIndex < endIndex))
					vmShortName = vmName.substring(beginIndex+1, endIndex);
				if (vmShortName != null) {
					String endUserName = getEndUserName(vmShortName);
					
					DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4NewVMs] " +
							"Creatint user for " + endUserName + " on " + vmName);
					String veInsUsername = virtualLabsDB.getVeInsUsernameByVeInsId(veInsId);
					endUserName += "-" + veInsUsername;
					DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4NewVMs] " +
							"Creatint user for " + endUserName + " on " + vmName);
					task = 
						new VMInsTask(
								0,
								macAddress,
								TodoType.SYNC_USER_CREDENTIALS,
								Calendar.getInstance(),
								true);
					taskId = virtualLabsDB.addVMInsTask(task);
					syncTask =
						new VMInsSyncUserTask(
								taskId,
								endUserName,
								"",
								true);
					virtualLabsDB.addVMInsSyncUserTask(syncTask);

					// TODO: Awful coding practice! :(
					if (vmShortName.equals("dc")) {
						// endUserName = "user-" + "ws1" + "-" + user.getUserName();
						endUserName = getEndUserName("ws1");
						DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4RefreshedVM] " +
								"Creatint user for " + endUserName + " on " + vmName);
						veInsUsername = virtualLabsDB.getVeInsUsernameByVeInsId(veInsId);
						endUserName += "-" + veInsUsername;
						DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4RefreshedVM] " +
								"Creatint user for " + endUserName + " on " + vmName);
						task = 
							new VMInsTask(
									0,
									macAddress,
									TodoType.SYNC_USER_CREDENTIALS,
									Calendar.getInstance(),
									true);
						taskId = virtualLabsDB.addVMInsTask(task);
						syncTask =
							new VMInsSyncUserTask(
									taskId,
									endUserName,
									"",
									true);
						virtualLabsDB.addVMInsSyncUserTask(syncTask);
						
						// endUserName = "user-" + "guest1" + "-" + user.getUserName();
						endUserName = getEndUserName("guest1");
						DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4RefreshedVM] " +
								"Creatint user for " + endUserName + " on " + vmName);
						veInsUsername = virtualLabsDB.getVeInsUsernameByVeInsId(veInsId);
						endUserName += "-" + veInsUsername;
						DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4RefreshedVM] " +
								"Creatint user for " + endUserName + " on " + vmName);
						task = 
							new VMInsTask(
									0,
									macAddress,
									TodoType.SYNC_USER_CREDENTIALS,
									Calendar.getInstance(),
									true);
						taskId = virtualLabsDB.addVMInsTask(task);
						syncTask =
							new VMInsSyncUserTask(
									taskId,
									endUserName,
									"",
									true);
						virtualLabsDB.addVMInsSyncUserTask(syncTask);
					}
				} else {
					DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4NewVMs] " +
							"The short name for vm could not be found. vmName: " + vmName);
				}
			}
			
			task = 
				new VMInsTask(
						0,
						macAddress,
						TodoType.SYNC_USER_CREDENTIALS,
						Calendar.getInstance(),
						true);
			taskId = virtualLabsDB.addVMInsTask(task);
			syncTask =
				new VMInsSyncUserTask(
						taskId,
						"",
						"",
						true);
			virtualLabsDB.addVMInsSyncUserTask(syncTask);

			// SMS 6/10/2014
			// No addition of Student username by default anymore
			/*
			// TODO
			task = 
				new VMInsTask(
						0,
						macAddress,
						TodoType.SYNC_USER_CREDENTIALS,
						Calendar.getInstance(),
						true);
			taskId = virtualLabsDB.addVMInsTask(task);
			syncTask =
				new VMInsSyncUserTask(
						taskId,
						"Student",
						"",
						true);
			virtualLabsDB.addVMInsSyncUserTask(syncTask);
			 */
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4NewVMs] Ready to get out!");
			
	}

	/*
	private void setVMsPassword(String veInsId) {
		
		GetVEMacsRequest getVEMacsReq = new GetVEMacsRequest();
		getVEMacsReq.setVeInsId(veInsId); 
		GetVEMacsResponse getVEMacsResp = null;
		
		try { getVEMacsResp = veStub.getVEMacs(getVEMacsReq); }
		catch(Exception e) { e.printStackTrace(); }
		
		String[] veInsMacs = getVEMacsResp.getMacAddress();

		for (String macAddress : veInsMacs) {

			VMInsTask task = 
				new VMInsTask(
						0,
						macAddress,
						TodoType.SYNC_USER_CREDENTIALS,
						Calendar.getInstance(),
						true);
			int taskId = virtualLabsDB.addVMInsTask(task);
			VMInsSyncUserTask syncTask =
				new VMInsSyncUserTask(
						taskId,
						"",
						"",
						true);
			virtualLabsDB.addVMInsSyncUserTask(syncTask);

			// TODO
			task = 
				new VMInsTask(
						0,
						macAddress,
						TodoType.SYNC_USER_CREDENTIALS,
						Calendar.getInstance(),
						true);
			taskId = virtualLabsDB.addVMInsTask(task);
			syncTask =
				new VMInsSyncUserTask(
						taskId,
						"Student",
						"",
						true);
			virtualLabsDB.addVMInsSyncUserTask(syncTask);
		}

	}
	*/
	
	private String getEndUserName(String vmShortName) {
		
		String endUserName = "";
		if (vmShortName.equals("ws1")) 
			endUserName = "eli";
		else if (vmShortName.equals("dc")) 
			endUserName = "ena";
		else if (vmShortName.equals("guest1")) 
			endUserName = "eve";
		else if (vmShortName.equals("pc1")) 
			endUserName = "evy";
		else if (vmShortName.equals("laptop1")) 
			endUserName = "ewa";
		
		return endUserName;
	}
	
	private void addInitialTasks4RefreshedVM(String veInsId, String vmName) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4RefreshedVM] Inside!");
		
		GetVMMacRequest getVMMacReq = new GetVMMacRequest();
		getVMMacReq.setVeInsId(veInsId);
		getVMMacReq.setVmName(vmName);
		GetVMMacResponse getVMMacResp = null;
		try {
			getVMMacResp = veStub.getVMMac(getVMMacReq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String macAddress = getVMMacResp.getMacAddress();

		VMInsTask task = 
			new VMInsTask(
					0,
					macAddress,
					TodoType.CHANGE_CHECKIN_INTERVAL,
					Calendar.getInstance(),
					true);
		virtualLabsDB.addVMInsTask(task);
		
		int taskId = 0;
		VMInsSyncUserTask syncTask = null;

		// TODO: This is specific to the ve type that we have currently.
		String promoCode = virtualLabsDB.getPromoCode(veInsId);
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4RefreshedVM] " +
				"Create end users based on the promoCode: " + promoCode);
		// SMS: To accommodate ITS-KSDW-14-1D
		// if (promoCode.equals(ITSKSDW)) {
		if (promoCode.contains("KSDW")) {
			
			User user = virtualLabsDB.getUserByVeInsId(veInsId);
			int beginIndex = vmName.indexOf('(');
			int endIndex = vmName.indexOf(')');
			String vmShortName = null;
			if ((beginIndex > 0) && (endIndex > 0) && (beginIndex < endIndex))
				vmShortName = vmName.substring(beginIndex+1, endIndex);
			if (vmShortName != null) {
				String endUserName = getEndUserName(vmShortName);
				 
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4RefreshedVM] " +
						"Creatint user for " + endUserName + " on " + vmName);
				String veInsUsername = virtualLabsDB.getVeInsUsernameByVeInsId(veInsId);
				endUserName += "-" + veInsUsername;
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4RefreshedVM] " +
						"Creatint user for " + endUserName + " on " + vmName);
				task = 
					new VMInsTask(
							0,
							macAddress,
							TodoType.SYNC_USER_CREDENTIALS,
							Calendar.getInstance(),
							true);
				taskId = virtualLabsDB.addVMInsTask(task);
				syncTask =
					new VMInsSyncUserTask(
							taskId,
							endUserName,
							"",
							true);
				virtualLabsDB.addVMInsSyncUserTask(syncTask);
				
				// TODO: Awful coding practice! :(
				if (vmShortName.equals("dc")) {
					// endUserName = "user-" + "ws1" + "-" + user.getUserName();
					endUserName = getEndUserName("ws1");
					DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4RefreshedVM] " +
							"Creatint user for " + endUserName + " on " + vmName);
					veInsUsername = virtualLabsDB.getVeInsUsernameByVeInsId(veInsId);
					endUserName += "-" + veInsUsername;
					DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4RefreshedVM] " +
							"Creatint user for " + endUserName + " on " + vmName);
					task = 
						new VMInsTask(
								0,
								macAddress,
								TodoType.SYNC_USER_CREDENTIALS,
								Calendar.getInstance(),
								true);
					taskId = virtualLabsDB.addVMInsTask(task);
					syncTask =
						new VMInsSyncUserTask(
								taskId,
								endUserName,
								"",
								true);
					virtualLabsDB.addVMInsSyncUserTask(syncTask);
					
					// endUserName = "user-" + "guest1" + "-" + user.getUserName();
					endUserName = getEndUserName("guest1");
					DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4RefreshedVM] " +
							"Creatint user for " + endUserName + " on " + vmName);
					veInsUsername = virtualLabsDB.getVeInsUsernameByVeInsId(veInsId);
					endUserName += "-" + veInsUsername;
					DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4RefreshedVM] " +
							"Creatint user for " + endUserName + " on " + vmName);
					task = 
						new VMInsTask(
								0,
								macAddress,
								TodoType.SYNC_USER_CREDENTIALS,
								Calendar.getInstance(),
								true);
					taskId = virtualLabsDB.addVMInsTask(task);
					syncTask =
						new VMInsSyncUserTask(
								taskId,
								endUserName,
								"",
								true);
					virtualLabsDB.addVMInsSyncUserTask(syncTask);
				}
			} else {
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4RefreshedVM] " +
						"The short name for vm could not be found. vmName: " + vmName);
			}
		}

		task = 
			new VMInsTask(
					0,
					macAddress,
					TodoType.SYNC_USER_CREDENTIALS,
					Calendar.getInstance(),
					true);
		taskId = virtualLabsDB.addVMInsTask(task);
		syncTask =
			new VMInsSyncUserTask(
					taskId,
					"",
					"",
					true);
		virtualLabsDB.addVMInsSyncUserTask(syncTask);

		// TODO
		task = 
			new VMInsTask(
					0,
					macAddress,
					TodoType.SYNC_USER_CREDENTIALS,
					Calendar.getInstance(),
					true);
		taskId = virtualLabsDB.addVMInsTask(task);
		syncTask =
			new VMInsSyncUserTask(
					taskId,
					"Student",
					"",
					true);
		virtualLabsDB.addVMInsSyncUserTask(syncTask);

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addInitialTasks4RefreshedVM] Ready to get out!");
		
	}

	/**
	 * 
	 * @param modifyUserAppointmentRequest
	 * @return
	 */
	/*
	public ModifyUserAppointmentResponse modifyUserAppointment(
			ModifyUserAppointmentRequest modifyUserAppointmentRequest) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - modifyUserAppointment] Inside!");

		ModifyUserAppointmentResponse resp = new ModifyUserAppointmentResponse();

		try {

			Calendar start = modifyUserAppointmentRequest.getStart();
			Calendar end = modifyUserAppointmentRequest.getEnd();
			Calendar now = Calendar.getInstance();
			
			// Fixing the time zone based on the default time zone for the requesting user.
			User user = virtualLabsDB.getUser(modifyUserAppointmentRequest.getRequestingUser());
			TimeZone timeZone = TimeZone.getTimeZone(user.getTimeZone());
			start = TimeZoneTools.changeTimeZone(start, timeZone);
			end = TimeZoneTools.changeTimeZone(end, timeZone);

			// Check validity of dates
			if (end.before(start) || !start.before(end)) {
			
				resp.setSuccess(false);
				resp.setReason("End date must be before start date!");
				return resp;
			
			} 
			else if (start.before(now)) {

				resp.setSuccess(false);
				resp.setReason("Dates are not valid!");
				return resp;
				
			}

			// Check that the modification does not overlap with existing appointments
			DbAppointment app = 
				virtualLabsDB.getAppointment(modifyUserAppointmentRequest.getId());
			app.setStart(start);
			app.setEnd(end);
			Appointment axisApp = AppointmentTranslator.toAxisRepresentation(app);

			if (!isOverlappingWithUserScheduledAppointments(axisApp)) {

				boolean success = false;
				String retId = "";
				
				if (!app.getResourceType().toUpperCase().equals("MENTORING")) {
					
					// Set request to call modifyScheduledVE
					ModifyScheduledVERequest request = new ModifyScheduledVERequest();
					request.setSchId(app.getSchId());
					TimePeriod period = new TimePeriod();
					period.setStartTime(start);
					period.setEndTime(end);
					request.setTimePeriod(period);

					ModifyScheduledVEResponse response = veStub.modifyScheduledVE(request);
					if (response.getSuccess()) {
						
						success = true;
						retId = response.getSchId();
						
					}

				}
				else {
					
					// Set request to call modifyScheduledMentoring
					ModifyScheduledMentoringRequest request = new ModifyScheduledMentoringRequest();
					request.setId(app.getSchId());
					TimePeriod period = new TimePeriod();
					period.setStartTime(start);
					period.setEndTime(end);
					request.setTimePeriod(period);

					ModifyScheduledMentoringResponse response = mentorStub.modifyScheduledMentoring(request);
					if (response.getSuccess()) {
						
						success = true;
						retId = response.getId();
						
					}

				}
					
				// If response was successful, get the new ve_id and add it to the db
				if (success) {
					
					virtualLabsDB.cancelAppointment(app.getSchId());
			
					DbAppointment newApp = 
						new DbAppointment(
								retId,
								app.getQuotaId(),
								app.getAffiliationId(),
								app.getUsername(),
								app.getCourse(),
								app.getResourceType(),
								true,
								start,
								end);
					virtualLabsDB.scheduleAppointment(newApp);
					
					resp.setSuccess(true);
					resp.setReason("Appointment has been updated!");
		
				} 
				else {
				
					resp.setSuccess(false);
					resp.setReason("Appointment could NOT be modified!");
				
				}

			} 
			else {
				
				resp.setSuccess(false);
				resp.setReason("Dates overlap with an existing appointment");

			}

		} 
		catch (Error e) {
			
			e.printStackTrace();
			resp.setSuccess(false);
			resp.setReason("Error:"+e.getMessage());

		} 
		catch (Exception e) {

			e.printStackTrace();
			resp.setSuccess(false);
			resp.setReason("Exception:"+e.getMessage());

		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - modifyUserAppointment] Ready to get out!");

		return resp;

	}
*/
	/**
	 * 
	 * @param extendUserAppointmentRequest
	 * @return
	 */
	public ExtendUserAppointmentResponse extendUserAppointment(
			ExtendUserAppointmentRequest extendUserAppointmentRequest) {
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - extendUserAppointment] Inside!");
		
		ExtendUserAppointmentResponse resp = new ExtendUserAppointmentResponse();
		resp.setSuccess(true);
		resp.setReason("Successful!");

		try {
			// First, check if the modified appointment is valid.
			DbAppointment app = 
				virtualLabsDB.getAppointment(extendUserAppointmentRequest.getId());
			GetVEInsScheduleBySchIdRequest veReq = 
					new GetVEInsScheduleBySchIdRequest();
			veReq.setVeInsSchId(app.getSchId());
			GetVEInsScheduleBySchIdResponse veResp = 
					veStub.getVEInsScheduleBySchId(veReq);
			if (!veResp.getSuccess()) {
				resp.setSuccess(false);
				resp.setReason("The schedule id cannot be found in the scheduling records!");
				return resp;				
			}

			ModifyUserAppointmentRequest modifyReq = new ModifyUserAppointmentRequest();
			// modifyReq.setRequestingUser(extendUserAppointmentRequest.getRequestingUser());
			modifyReq.setRequestingUser("DontFixTimeZone");
			modifyReq.setId(extendUserAppointmentRequest.getId());
			Calendar startTime = veResp.getSchedule().getTimePeriod().getStartTime();
			modifyReq.setStart(startTime);
			Calendar endTime = veResp.getSchedule().getTimePeriod().getEndTime();
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - extendUserAppointment] "
					+ "Original startTime is " + startTime.getTime()
					+ " and original endTime is " + endTime.getTime());			
			endTime.add(Calendar.MINUTE, extendUserAppointmentRequest.getMinutes());
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - extendUserAppointment] "
					+ "Original startTime is " + startTime.getTime()
					+ " and extended endTime is " + endTime.getTime());
			modifyReq.setEnd(endTime);
			
			ModifyUserAppointmentResponse modifyResp = modifyUserAppointment(modifyReq);
			if (!modifyResp.getSuccess()) {
				resp.setSuccess(false);
				resp.setReason("The extension cannot be reflected!");
				return resp;				
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

		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - extendUserAppointment] Ready to get out!");
		return resp;
	}
	
	/**
	 * 
	 * @param modifyUserAppointmentRequest
	 * @return
	 */
	public ModifyUserAppointmentResponse modifyUserAppointment(
			ModifyUserAppointmentRequest modifyUserAppointmentRequest) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - modifyUserAppointment] Inside!");
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - modifyUserAppointment] "
				+ "==========================================================");
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - modifyUserAppointment] ");
		DebugTools.println(DEBUG_LEVEL, "	modifyUserAppointmentRequest: ");
		if (modifyUserAppointmentRequest.getStart() != null)
			DebugTools.println(DEBUG_LEVEL, "		start: " + modifyUserAppointmentRequest.getStart().getTime());
		if (modifyUserAppointmentRequest.getEnd() != null)
			DebugTools.println(DEBUG_LEVEL, "		end: " + modifyUserAppointmentRequest.getEnd().getTime());
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - modifyUserAppointment] "
				+ "==========================================================");

		ModifyUserAppointmentResponse resp = new ModifyUserAppointmentResponse();
		resp.setSuccess(true);
		resp.setReason("Successful!");

		try {
			// First, check if the modified appointment is valid.
			DbAppointment app = 
				virtualLabsDB.getAppointment(modifyUserAppointmentRequest.getId());
			GetVEInsScheduleBySchIdRequest veReq = new GetVEInsScheduleBySchIdRequest();
			veReq.setVeInsSchId(app.getSchId());
			GetVEInsScheduleBySchIdResponse veResp = veStub.getVEInsScheduleBySchId(veReq);
			if (!veResp.getSuccess()) {
				resp.setSuccess(false);
				resp.setReason("The schedule id cannot be found in the scheduling records!");
				return resp;				
			}
			app.setStart(veResp.getSchedule().getTimePeriod().getStartTime());
			app.setEnd(veResp.getSchedule().getTimePeriod().getEndTime());
			Appointment appointment = AppointmentTranslator.toAxisRepresentation(app);

			// Added by Masoud Sadjadi on Nov. 24, 2014
			// This part was added to make sure when this function is called with requesting user
			// as "DontFixTimeZone", the time zone is not fixed and the requesting user is "admin"
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - modifyUserAppointment] "
					+ "requestingUser: " + modifyUserAppointmentRequest.getRequestingUser());
			boolean DontFixTimeZone = false;
			if (modifyUserAppointmentRequest.getRequestingUser() == "DontFixTimeZone") {
				DontFixTimeZone = true;
				modifyUserAppointmentRequest.setRequestingUser("admin");
			}
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - modifyUserAppointment] "
					+ "DontFixTimeZone: " + DontFixTimeZone);
			
			// Fixing the time zone based on the default time zone for the requesting user.
			User user = virtualLabsDB.getUser(modifyUserAppointmentRequest.getRequestingUser());
			TimeZone timeZone = TimeZone.getTimeZone(user.getTimeZone());

			Calendar modStart = null;
			if (modifyUserAppointmentRequest.getStart() == null) {
				modStart = app.getStart();
			} else {
				modStart = modifyUserAppointmentRequest.getStart();
				if (!DontFixTimeZone) {
					modStart = TimeZoneTools.changeTimeZone(modStart, timeZone);
				}
			}
			
			Calendar modEnd = null;
			if (modifyUserAppointmentRequest.getEnd() == null) {
				modEnd = app.getEnd();
			} else {
				modEnd = modifyUserAppointmentRequest.getEnd();
				if (!DontFixTimeZone) {
					modEnd = TimeZoneTools.changeTimeZone(modEnd, timeZone);
				}
			}
			
			Calendar now = Calendar.getInstance();

			// if the appointment being modified is an ongoing appointment, then its 
			// start time cannot be modified. 
			// TODO we need to make sure that on the Moodle side, the user will not be
			// able to change the start time of an ongoing appointment, so to avoid any
			// confusions.
			boolean isCurrentApp = isCurrentAppointment(app.getStart(), app.getEnd());
			if (isCurrentApp) {
				modStart = app.getStart();
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - modifyUserAppointment] " +
						"The appointment being modified is an ongoing appointment!");
			}
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - modifyUserAppointment] " +
					"appointment original start: " + appointment.getStart().getTime() + "\n" +
					"appointment original end  : " + appointment.getEnd().getTime() + "\n" +
					"appointment modified start: " + modStart.getTime() + "\n" +
					"appointment modified end  : " + modEnd.getTime() + "\n" +
					"");

			// Check validity of dates
			if (modEnd.before(modStart) || !modStart.before(modEnd)) {
				resp.setSuccess(false);
				resp.setReason("End date must be after start date!");
				return resp;
			} else if (modStart.before(now) && (!isCurrentApp)) {
				resp.setSuccess(false);
				resp.setReason("Dates are not valid!");
				return resp;
			}

			TimePeriod oldPeriod = new TimePeriod();
			oldPeriod.setStartTime(appointment.getStart());
			oldPeriod.setEndTime(appointment.getEnd());
			TimePeriod modPeriod = new TimePeriod();
			modPeriod.setStartTime(modStart);
			modPeriod.setEndTime(modEnd);
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - modifyUserAppointment] " +
					"\noldPeriod: " + oldPeriod.getStartTime().getTime() + " to " + oldPeriod.getEndTime().getTime() +
					"\nmodPeriod: " + modPeriod.getStartTime().getTime() + " to " + modPeriod.getEndTime().getTime());
			ArrayList<TimePeriod> newTimePeriods = getNewTimePeriods(oldPeriod, modPeriod);
			if (newTimePeriods == null) {
				resp.setSuccess(false);
				resp.setReason("Appointment could NOT be modified! The new dates are not valid!");
				return resp;
			}
			// Check if all the new portions are valid
			for (int i=0; i<newTimePeriods.size(); i++) {
				Appointment newPortionApp = duplicateAppointment(appointment);
				newPortionApp.setStart(newTimePeriods.get(i).getStartTime());
				newPortionApp.setEnd(newTimePeriods.get(i).getEndTime());
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - modifyUserAppointment] " +
						"newPortionApp: " + "\n" +
						"\tid: " + newPortionApp.getId() + "\n" +
						"\taffilication id: " + newPortionApp.getAffiliationId() + "\n" +
						"\tusername: " + newPortionApp.getUserName() + "\n" +
						"\tcourse: " + newPortionApp.getCourse() + "\n" +
						"\tresource type: " + newPortionApp.getResourceType() + "\n" +
						"\tstart: " + newPortionApp.getStart().getTime() + "\n" +
						"\tend: " + newPortionApp.getEnd().getTime());
				if (!isAppointmentValid(newPortionApp)) {
					resp.setSuccess(false);
					resp.setReason("Appointment could NOT be modified! The new dates are not valid!");
					return resp;
				}
			}
			
			// Second, check whether there is enough resources.
			for (int i=0; i<newTimePeriods.size(); i++) {
				Appointment newPortionApp = duplicateAppointment(appointment);
				newPortionApp.setStart(newTimePeriods.get(i).getStartTime());
				newPortionApp.setEnd(newTimePeriods.get(i).getEndTime());
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - modifyUserAppointment] " +
						"newPortionApp: " + "\n" +
						"\tid: " + newPortionApp.getId() + "\n" +
						"\taffilication id: " + newPortionApp.getAffiliationId() + "\n" +
						"\tusername: " + newPortionApp.getUserName() + "\n" +
						"\tcourse: " + newPortionApp.getCourse() + "\n" +
						"\tresource type: " + newPortionApp.getResourceType() + "\n" +
						"\tstart: " + newPortionApp.getStart().getTime() + "\n" +
						"\tend: " + newPortionApp.getEnd().getTime());
				if (!isResourceAvailable(newPortionApp)) {
					resp.setSuccess(false);
					resp.setReason("Not enough resources are available for this modified request!");
					return resp;
				}
			}

			// Third, modify the quota, if possible.
			Appointment modAppointment = duplicateAppointment(appointment);
			modAppointment.setStart(modStart);
			modAppointment.setEnd(modEnd);
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - modifyUserAppointment] " +
					"before calling modifyQuota, modAppointment.getEnd().getTime() is: " +
					modAppointment.getEnd().getTime());
			if (!modifyQuota(modAppointment, app.getQuotaId())) {
				resp.setSuccess(false);
				resp.setReason("Not enough quota is available for this modified request!");
				return resp;
			}

			// Fourth, modify resource allocation, if possible.
			if (!modifyResource(modAppointment, app.getQuotaId())) {
				// If the resources were not successfully allocated, we will 
				// reverse the quota allocation.
				if (!modifyQuota(appointment, app.getQuotaId())) {
					resp.setSuccess(false);
					resp.setReason("[VirtualLabs - modifyUserAppointment] " +
							"ERROR: Resource could not be scheduled and the attempt to" +
							"reverse the quota allocation failed! " +
							"Please inform the developer!");
					return resp;
				} else {
					resp.setSuccess(false);
					resp.setReason("[VirtualLabs - modifyUserAppointment] " +
							"There was not sufficient resources available!");
					return resp;
				}
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

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - modifyUserAppointment] Ready to get out!");

		return resp;
	}	

	private boolean modifyResource(Appointment appointment, String quotaId) {
		
		boolean retVal = false;
		String retId = "";

		if (!appointment.getResourceType().toUpperCase().equals("MENTORING")) {
			// Set request to call modifyScheduledVE
			ModifyScheduledVERequest request = new ModifyScheduledVERequest();
			request.setSchId(appointment.getId());
			TimePeriod period = new TimePeriod();
			period.setStartTime(appointment.getStart());
			period.setEndTime(appointment.getEnd());
			request.setTimePeriod(period);

			ModifyScheduledVEResponse response = null;
			try {
				response = veStub.modifyScheduledVE(request);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (response.getSuccess()) {
				retVal = true;
				retId = response.getSchId();
			}
		} else {
			// Set request to call modifyScheduledMentoring
			ModifyScheduledMentoringRequest request = new ModifyScheduledMentoringRequest();
			request.setId(appointment.getId());
			TimePeriod period = new TimePeriod();
			period.setStartTime(appointment.getStart());
			period.setEndTime(appointment.getEnd());
			request.setTimePeriod(period);
			
			ModifyScheduledMentoringResponse response = null;
			try {
				response = mentorStub.modifyScheduledMentoring(request);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (response.getSuccess()) {
				retVal = true;
				retId = response.getId();
			}
		}

		/*
		// If response was successful, get the new ve_id and add it to the db
		if (retVal) {
			virtualLabsDB.cancelAppointment(appointment.getId());

			DbAppointment newApp = 
				new DbAppointment(
						retId,
						quotaId,
						appointment.getAffiliationId(),
						appointment.getUserName(),
						appointment.getCourse(),
						appointment.getResourceType(),
						true,
						appointment.getStart(),
						appointment.getEnd());
			virtualLabsDB.scheduleAppointment(newApp);
		}
		*/
		return retVal;
	}

	private boolean modifyQuota(Appointment appointment, String quotaId) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - modifyQuota] Inside!");

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - modifyQuota] " +
				"appointment.getEnd().getTime() is: " +
				appointment.getEnd().getTime());
		edu.fiu.cis.acrl.quotasystem.ws.ModifyAppointmentRequest qsReq =
			new edu.fiu.cis.acrl.quotasystem.ws.ModifyAppointmentRequest();
		edu.fiu.cis.acrl.quotasystem.ws.Appointment qsApp =
			convertVLApp2QSApp(appointment);
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - modifyQuota] " +
				"qsApp.getEndTime().getTime() is: " +
				qsApp.getEndTime().getTime());
		qsApp.setId(quotaId);
		qsReq.setAppointment(qsApp);
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - modifyQuota] " +
				"qsReq.getAppointment().getEndTime().getTime() is: " +
				qsReq.getAppointment().getEndTime().getTime());
		edu.fiu.cis.acrl.quotasystem.ws.ModifyAppointmentResponse qsResp = null;
		try {
			qsResp = qsStub.modifyAppointment(qsReq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - modifyQuota] Ready to get out!");

		return qsResp.getSuccess();
	}

	private ArrayList<TimePeriod> getNewTimePeriods(TimePeriod oldPeriod,
			TimePeriod modPeriod) {

		if ((oldPeriod == null) || (modPeriod == null))
			return null;
		
		// The assumption is that start <= end
		// If it is not true, then this function returns null.
		if ((oldPeriod.getEndTime().getTime().getTime() < oldPeriod.getStartTime().getTime().getTime()) ||
			(modPeriod.getEndTime().getTime().getTime() < modPeriod.getStartTime().getTime().getTime())) 
			return null;
		
		ArrayList<TimePeriod> newPeriods = new ArrayList<TimePeriod>();
		if ((modPeriod.getEndTime().getTime().getTime() <= oldPeriod.getStartTime().getTime().getTime()) ||
			(oldPeriod.getEndTime().getTime().getTime() <= modPeriod.getStartTime().getTime().getTime())) {
			TimePeriod newPeriod = new TimePeriod();
			newPeriod.setStartTime(modPeriod.getStartTime());
			newPeriod.setEndTime(modPeriod.getEndTime());
			newPeriods.add(newPeriod);
		} else 
		if ((modPeriod.getStartTime().getTime().getTime() <= oldPeriod.getStartTime().getTime().getTime()) &&
			(oldPeriod.getStartTime().getTime().getTime() <= modPeriod.getEndTime().getTime().getTime()  ) &&
			(modPeriod.getEndTime().getTime().getTime()   <= oldPeriod.getEndTime().getTime().getTime()  )) {
			TimePeriod newPeriod = new TimePeriod();
			newPeriod.setStartTime(modPeriod.getStartTime());
			newPeriod.setEndTime(oldPeriod.getStartTime());
			if (newPeriod.getStartTime().getTime().getTime() != newPeriod.getEndTime().getTime().getTime())
				newPeriods.add(newPeriod);
		} else
		if ((oldPeriod.getStartTime().getTime().getTime() <= modPeriod.getStartTime().getTime().getTime()) &&
			(modPeriod.getStartTime().getTime().getTime() <= oldPeriod.getEndTime().getTime().getTime()  ) &&
			(oldPeriod.getEndTime().getTime().getTime()   <= modPeriod.getEndTime().getTime().getTime()  )) {
			TimePeriod newPeriod = new TimePeriod();
			newPeriod.setStartTime(oldPeriod.getEndTime());
			newPeriod.setEndTime(modPeriod.getEndTime());
			if (newPeriod.getStartTime().getTime().getTime() != newPeriod.getEndTime().getTime().getTime())
				newPeriods.add(newPeriod);
		} else
		if ((modPeriod.getStartTime().getTime().getTime() < oldPeriod.getStartTime().getTime().getTime()) &&
			(oldPeriod.getEndTime().getTime().getTime()   < modPeriod.getEndTime().getTime().getTime()  )) {
				TimePeriod newPeriod = new TimePeriod();
				newPeriod.setStartTime(modPeriod.getStartTime());
				newPeriod.setEndTime(oldPeriod.getStartTime());
				if (newPeriod.getStartTime().getTime().getTime() != newPeriod.getEndTime().getTime().getTime())
					newPeriods.add(newPeriod);
				newPeriod = new TimePeriod();
				newPeriod.setStartTime(oldPeriod.getEndTime());
				newPeriod.setEndTime(modPeriod.getEndTime());
				if (newPeriod.getStartTime().getTime().getTime() != newPeriod.getEndTime().getTime().getTime())
					newPeriods.add(newPeriod);
		} // else
		// no more new period.
		
		return newPeriods;
	}

	public CancelUserAppointmentResponse cancelUserAppointment(
			CancelUserAppointmentRequest cancelUserAppointmentRequest) {

		// TODO: If the appoinment is current, then only the remaining period should be canceled.
		// This is equivalent to modifying the appointment to have now as its end time.
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - cancelUserAppointments] Inside!");

		CancelUserAppointmentResponse resp = new CancelUserAppointmentResponse();

		// Get id from the request
		String id = cancelUserAppointmentRequest.getId();

		try {
			
			DbAppointment app = virtualLabsDB.getAppointment(id);
			boolean success = false;
			ScheduledEvent event = null;
			
			if (app.getResourceType().toUpperCase().equals("VIRTUAL LAB") ||
				app.getResourceType().toUpperCase().equals("CERTIFICATE")) {
				GetVEInsScheduleBySchIdRequest reqGet = 
					new GetVEInsScheduleBySchIdRequest();
				reqGet.setVeInsSchId(id);
				GetVEInsScheduleBySchIdResponse respGet = 
					veStub.getVEInsScheduleBySchId(reqGet);
				event = respGet.getSchedule();
				
				CancelScheduledVERequest request = new CancelScheduledVERequest();
				request.setSchId(id);
				CancelScheduledVEResponse response = veStub.cancelScheduledVE(request);
				if (response.getSuccess())
					success = true;
			} else if (app.getResourceType().toUpperCase().equals("MENTORING")) {
				GetMentoringScheduleBySchIdRequest reqGet = 
					new GetMentoringScheduleBySchIdRequest();
				reqGet.setMentoringSchId(id);
				GetMentoringScheduleBySchIdResponse respGet = 
					mentorStub.getMentoringScheduleBySchId(reqGet);
				event = respGet.getSchedule();
				
				CancelScheduledMentoringRequest request = new CancelScheduledMentoringRequest();
				request.setId(id);
				CancelScheduledMentoringResponse response = 
					mentorStub.cancelScheduledMentoring(request);
				if (response.getSuccess())
					success = true;
			} else if (app.getResourceType().toUpperCase().equals("PHYSICAL")) {
				// TODO
			} else {
			}
			
			if (success) {
				edu.fiu.cis.acrl.quotasystem.ws.CancelAppointmentRequest qsReq =
					new edu.fiu.cis.acrl.quotasystem.ws.CancelAppointmentRequest();
				qsReq.setAppointmentId(app.getQuotaId());
				edu.fiu.cis.acrl.quotasystem.ws.CancelAppointmentResponse qsResp =
					qsStub.cancelAppointment(qsReq);
				
				if (qsResp.getSuccess()) {
					if (isCurrentAppointment(
							event.getTimePeriod().getStartTime(),
							event.getTimePeriod().getEndTime())) {
						resp.setSuccess(true);
						resp.setReason("Appointment has been cancelled!");
					} else if (virtualLabsDB.cancelAppointment(id)) {
						resp.setSuccess(true);
						resp.setReason("Appointment has been cancelled!");
					} else {
						resp.setSuccess(false);
						resp.setReason("Appointment could not be removed from virtuallabs DB! " +
						"Please inform the developer!");
					}
				} else {					
					resp.setSuccess(false);
					resp.setReason("The quota for the appointment could not be released!" +
						"Also, the appoinment is still in virtuallabs DB! " +
						"Please inform the developer!");
				}
			} else {
				resp.setSuccess(false);
				resp.setReason("Appointment could not be cancelled!");
			}
		} catch (Error e) {
			resp.setSuccess(false);
			resp.setReason(e.getMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			resp.setSuccess(false);
			resp.setReason(ex.getMessage());
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - cancelUserAppointments] ready to get out!");

		return resp;
	}

	private boolean isCurrentAppointment(Calendar startTime, Calendar endTime) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isCurrentAppointment] Inside!");

		boolean current = false;
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isCurrentAppointment] " );
		DebugTools.println(DEBUG_LEVEL, "\tstart: " + startTime.getTime());
		DebugTools.println(DEBUG_LEVEL, "\tend: " + endTime.getTime());

		Calendar now = Calendar.getInstance();
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isCurrentAppointment] " +
				"Now: " + now.getTime());

		if (now.after(startTime) && now.before(endTime))
			current = true;
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isCurrentAppointment] current: " + current);

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isCurrentAppointment] ready to get out!");

		return current;
	}

	private edu.fiu.cis.acrl.quotasystem.ws.Appointment convertDbApp2QSApp(
			DbAppointment appointment) {
		
		edu.fiu.cis.acrl.quotasystem.ws.Appointment qsApp =
			new edu.fiu.cis.acrl.quotasystem.ws.Appointment();

		qsApp.setId(appointment.getSchId());
		qsApp.setAffiliationId(appointment.getAffiliationId());
		qsApp.setUsername(appointment.getUsername());
		qsApp.setResource(appointment.getResourceType());
		qsApp.setCourse(appointment.getCourse());
		qsApp.setStartTime(appointment.getStart());
		qsApp.setEndTime(appointment.getEnd());
		qsApp.setAvailabilityStatus("");

		return qsApp;
		
	}

	/**
	 * 
	 * @param cancelAllUserAppointmentsRequest
	 * @return
	 */
	public CancelAllUserAppointmentsResponse cancelAllUserAppointments(
			CancelAllUserAppointmentsRequest cancelAllUserAppointmentsRequest) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - cancelAllUserAppointments] Inside!");

		String affiliationId = cancelAllUserAppointmentsRequest.getAffiliationId();

		CancelAllUserAppointmentsResponse resp = new CancelAllUserAppointmentsResponse();
		List<DbAppointment> dbapps = virtualLabsDB.getAppointments(affiliationId);

		Appointment[] apps = AppointmentTranslator.toAxisRepresentation(dbapps);
		List<Appointment> appsList = Arrays.asList(apps);

		try {
			
			int cancelled = 0;

			Iterator<Appointment> itrApp = appsList.iterator();
			while (itrApp.hasNext()) {

				Appointment app = itrApp.next();
				
				CancelUserAppointmentRequest cancelReq = new CancelUserAppointmentRequest();
				cancelReq.setRequestingUser(cancelAllUserAppointmentsRequest.getRequestingUser());
				cancelReq.setId(app.getId());
				CancelUserAppointmentResponse cancelResp = 
					cancelUserAppointment(cancelReq);
				if (cancelResp.getSuccess())
					cancelled++;

				/*
				boolean success = false;

				if (!app.getResourceType().toUpperCase().equals("MENTORING")) {
					
					CancelScheduledVERequest request = new CancelScheduledVERequest();
					request.setSchId(app.getId());
					CancelScheduledVEResponse response = veStub.cancelScheduledVE(request);
					if (response.getSuccess())
						success = true;
					
				}
				else {
					
					CancelScheduledMentoringRequest request = new CancelScheduledMentoringRequest();
					request.setId(app.getId());
					CancelScheduledMentoringResponse response = 
						mentorStub.cancelScheduledMentoring(request);
					if (response.getSuccess())
						success = true;

				}

				if (success) {

					if (virtualLabsDB.cancelAppointment(app.getId())) {
						
						resp.setSuccess(true);
						resp.setReason("Appointment has been cancelled!");

					}
					else {
						
						resp.setSuccess(false);
						resp.setReason("Appointment could not be removed from virtuallabs DB! " +
								"Please inform the developer!");

					}
		
					cancelled++;
				}
				*/

			}

			if (cancelled < appsList.size()) {
				
				resp.setSuccess(false);
				resp.setReason(appsList.size() - cancelled+ " appointments could not be cancelled");

			} 
			else {

				resp.setSuccess(true);
				resp.setReason("Appointments cancelled!");
				System.out.println("Appointments cancelled!");

			}

		} 
		catch (Error e) {
			
			resp.setSuccess(false);
			resp.setReason(e.getMessage());

		} 
		catch (Exception ex) {

			ex.printStackTrace();
			resp.setSuccess(false);
			resp.setReason(ex.getMessage());

		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - cancelAllUserAppointments] Ready to get out!");

		return resp;

	}

	/**
	 * 
	 * @param getHostAppointmentsRequest
	 * @return
	 */
	public GetHostAppointmentsResponse getHostAppointments(
			GetHostAppointmentsRequest getHostAppointmentsRequest) 
	{

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getUserAppointment] Inside!");
		
		// Prepare response
		List<Appointment> allAppointments = new ArrayList<Appointment>();
		GetHostAppointmentsResponse resp = new GetHostAppointmentsResponse();

		// Get start and end date for the request
		Calendar start = getHostAppointmentsRequest.getStart();
		Calendar end = getHostAppointmentsRequest.getEnd();

		// Fix the time zone based on the requesting user's time zone
		User reqUser = virtualLabsDB.getUser(getHostAppointmentsRequest.getRequestingUser());
		TimeZone timeZone = TimeZone.getTimeZone(reqUser.getTimeZone());
		start = TimeZoneTools.changeTimeZone(start, timeZone);
		end = TimeZoneTools.changeTimeZone(end, timeZone);
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getHostAppointment] " +
				"start: " + start.getTime());
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getHostAppointment] " +
				"end: " + end.getTime());

		// Return an empty array of appointments in case the request is invalid
		try {

			String username = getHostAppointmentsRequest.getUserName();

			if (!virtualLabsDB.userExists(username)) {

				System.out.println("User does not exist");

			}
			else if (end.before(start)  || !start.before(end)) {

				System.out.println("Start date must be before end date");

			}
			else {

				// If the request is valid, get availability and schedule
				List<Appointment> availableSlots = 
					getHostAvailability(username, start, end);
				// Fixing the appointments' time zone based on the requesting user's default time zone.
				availableSlots = fixTimeZone(reqUser.getUserName(), availableSlots);
				// Divide appointment by day
				availableSlots = divideByDay(availableSlots);

				List<Appointment> scheduledApps = 
					getScheduledHostAppointments(username, start, end);
				// Fixing the appointments' time zone based on the requesting user's default time zone.
				scheduledApps = fixTimeZone(reqUser.getUserName(), scheduledApps);
				
				allAppointments.addAll(availableSlots);
				allAppointments.addAll(scheduledApps);
				
				// Add appointments to the response list
				Iterator<Appointment> itrAllApps = allAppointments.iterator();
				while (itrAllApps.hasNext()) {
					
					Appointment app = itrAllApps.next();
					resp.addAppointments(app);
				
				}
			
			}

		} 
		catch (Error e) {
		
			e.printStackTrace();

		} 
		catch (Exception ex) {
		
			ex.printStackTrace();

		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getHostAppointment] Ready to get out!");

		return resp;

	}

	/**
	 * Get available slots from ve scheduler and returns them
	 * 
	 * @param username
	 * @param start
	 * @param end
	 * @return
	 */
	private List<Appointment> getHostAvailability(String username, Calendar start, Calendar end) {
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getHostAvailability] Inside!");
	
		//Prepare response
		List<Appointment> availableAppointments = new ArrayList<Appointment>();
	
		try {
			
			// Set the request
			GetHostMaintenanceAvailabilityRequest request = new GetHostMaintenanceAvailabilityRequest();
			TimePeriod period = new TimePeriod();
			period.setStartTime(start);
			period.setEndTime(end);
			request.setTimePeriod(period);
	
			//Get courses and resources of the user
			Collection<String> resources = virtualLabsDB.getResources(username);
			
			//Get host list
			GetHostListRequest hostListReq = new GetHostListRequest();
			hostListReq.setRequestingUser(username);
			hostListReq.setActive(true);
			GetHostListResponse hostListResp = getHostList(hostListReq);
			Host[] hosts = hostListResp.getHost();
	
			Iterator<String> itrRes = resources.iterator();
			while (itrRes.hasNext()) {

				String resource = itrRes.next();

				for (Host host : hosts) {
					
					request.setHostId(host.getId());
					GetHostMaintenanceAvailabilityResponse hostResp = 
						veStub.getHostMaintenanceAvailability(request);
					TimePeriod[] slots = hostResp.getAvailabilityRange();
					List<Appointment> availableAppTempList = new ArrayList<Appointment>();

					//Translate the time slots into appointments
					if (slots != null) {

						Appointment[] availableAppTempArr = 
							AppointmentTranslator.toAxisRepresentation(
									slots, 
									resource, 
									host.getName(), 
									"AVAILABLE", 
									null, 
									null);

						availableAppTempList = Arrays.asList(availableAppTempArr);

					}

					Iterator<Appointment> itrAvailApp = availableAppTempList.iterator();
					while (itrAvailApp.hasNext()) {
						Appointment appAxis = itrAvailApp.next();

						appAxis = addActionsToAvailableSlot(appAxis);

						availableAppointments.add(appAxis);

					}

				}

			}

		} catch (Error e) {
			e.printStackTrace();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getHostAvailability] Ready to get out!");
	
		return availableAppointments;
	
	}
	
	/*
	 * Get all scheduled appointments for all users
	 * 
	 */
	private List<Appointment> getScheduledHostAppointments(
			String username, Calendar start, Calendar end) {
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getScheduledHostAppointments] Inside!");
		
		// If there is no end date, set it up as two months from now
		if (end == null) {
			
			end = Calendar.getInstance();
			end.set(Calendar.MONTH, end.get(Calendar.MONTH)+2);
		
		}
	
		List<Appointment> scheduledApps = new ArrayList<Appointment>();	
		List<Appointment> hostScheduleApps = getScheduledHosts(username, start, end);
	
		Iterator<Appointment> itrHost = hostScheduleApps.iterator();
	
		while (itrHost.hasNext()) {
	
			Appointment hostApp = itrHost.next();				
			DbAppointment dbApp = virtualLabsDB.getAppointment(hostApp.getId());
			// TODO
			hostApp = addActionsToAppointment("", hostApp);
	
			if (dbApp != null) {
				
				hostApp.setAffiliationId(dbApp.getAffiliationId());
			
			}
			else {
				
				UUID affiliationId = UUID.randomUUID();
				hostApp.setAffiliationId(affiliationId.toString());
	
				// To Local representation means to create an instance of 
				// DbAppointment which is used in db methods
	
				DbAppointment app = AppointmentTranslator.toLocalRepresentation(hostApp);
				virtualLabsDB.scheduleAppointment(app);
	
			}
	
			hostApp.setUserName(dbApp.getUsername());
			scheduledApps.add(hostApp);

		}
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getScheduledHostAppointments] Ready to get out!");
		
		return scheduledApps;
	
	}

	/*
	 * 
	 */
	private List<Appointment> getScheduledHosts(String username, Calendar start, Calendar end) {
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getScheduledHosts] Inside!");
		
		List<Appointment> scheduledAppointments = new ArrayList<Appointment>();

		try {

			// Set up the request
			GetHostMaintenanceScheduleRequest request = new GetHostMaintenanceScheduleRequest();

			TimePeriod period = new TimePeriod();
			period.setStartTime(start);
			period.setEndTime(end);
			request.setTimePeriod(period);

			Collection<String> resources = virtualLabsDB.getResources(username);

			// Get host list
			GetHostListRequest hostListReq = new GetHostListRequest();
			hostListReq.setRequestingUser(username);
			hostListReq.setActive(true);
			GetHostListResponse hostListResp = getHostList(hostListReq);
			Host[] hosts = hostListResp.getHost();

			Iterator<String> itrRes = resources.iterator();
			while (itrRes.hasNext()) {
				String resource = itrRes.next();

				for (Host host: hosts) {

					request.setHostId(host.getId());
					GetHostMaintenanceScheduleResponse hostResp = 
						veStub.getHostMaintenanceSchedule(request);

					if(hostResp.getSchedule()!= null) {

						ScheduledEvent[] schedule = hostResp.getSchedule();								

						// Translate the schedule into an array of appointments
						Appointment[] scheduledAppTempArr = 
							AppointmentTranslator.toAxisRepresentation(
									schedule, 
									resource, 
									host.getName(), 
									"SCHEDULED", 
									username, 
									null);

						// Add appointments in the new array to the response
						List<Appointment> scheduledAppTempList = 
							Arrays.asList(scheduledAppTempArr);
						Iterator<Appointment> itrAvailApp = 
							scheduledAppTempList.iterator();
						while (itrAvailApp.hasNext()) {

							Appointment appAxis = itrAvailApp.next();
							scheduledAppointments.add(appAxis);

						}

					}

				}

			}

		} 
		catch (Error e) {

			e.printStackTrace();
	
		} 
		catch (Exception ex) {
		
			ex.printStackTrace();
	
		}
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getScheduledHosts] Ready to get out!");
		
		return scheduledAppointments;
	}

	/**
	 * 
	 * @param scheduleHostAppointmentsRequest
	 * @return
	 */
	public ScheduleHostAppointmentsResponse scheduleHostAppointments(
			ScheduleHostAppointmentsRequest scheduleHostAppointmentsRequest) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleHostAppointments] Inside!");

		ScheduleHostAppointmentsResponse resp = new ScheduleHostAppointmentsResponse();

		//Get the request with the list of appointments
		Appointment[] apps = scheduleHostAppointmentsRequest.getAppointment();

		// Fixing the time zone based on the default time zone for the requesting user.
		apps = addTimeZone(
				scheduleHostAppointmentsRequest.getRequestingUser(),
				apps);

		//Create a UUID for this batch of appointments
		UUID affiliationId = UUID.randomUUID();

		try {

			//	If request fields are incomplete, return the request as it is
			if (!virtualLabsDB.userExists(apps[0].getUserName())) {
				resp.setAppointment(apps);
				System.out.println("[VirtualLabs - scheduleHostAppointments] Some request parameters do not exist");
				return resp;
			}

			// For every appointments in the request, make a call to ScheduleHostMaintenance
			List<Appointment> appsList = Arrays.asList(apps);
			Iterator<Appointment> itrApp = appsList.iterator();
			while (itrApp.hasNext()) {

				Appointment appAxis = itrApp.next();

				ScheduleHostMaintenanceRequest request = new ScheduleHostMaintenanceRequest();
				Calendar start = appAxis.getStart();
				Calendar end = appAxis.getEnd();
				Calendar now = Calendar.getInstance();

				// Check dates are correct first.
				if (end.before(start)  || !start.before(end)) {
					
					System.out.println("[VirtualLabs - scheduleHostAppointments] Start date must be before end date");
					resp.addAppointment(appAxis);

				} 
				else if (start.before(now)) {

					System.out.println("[VirtualLabs - scheduleHostAppointments] Dates are not valid");
					resp.addAppointment(appAxis);

				}
				else { // If the times are ok, set up the request and call

					// TODO this is a hack! We are using course for host name!
					GetHostUsingNameRequest hostReq = new GetHostUsingNameRequest();
					hostReq.setHostName(appAxis.getCourse());
					GetHostUsingNameResponse hostResp = veStub.getHostUsingName(hostReq);
					TimePeriod period = new TimePeriod();
					period.setStartTime(start);
					period.setEndTime(end);
					request.setTimePeriod(period);
					request.setHostId(hostResp.getHost().getId());

					scheduleHostAppointment(appAxis, request, affiliationId);

					// Add the appointment to the response list of appointments
					resp.addAppointment(appAxis);
					DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleHostAppointments] " +
							"Appointment that was tried to be scheduled: " + appAxis);
					
				}

			}

		} catch (Exception ex) {

			resp.setAppointment(apps);
			ex.printStackTrace();
		
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleHostAppointments] Ready to get out!");

		return resp;

	}

	private void scheduleHostAppointment(
			Appointment appAxis,
			ScheduleHostMaintenanceRequest request, 
			UUID affiliationId) {
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleHostAppointment] Inside!");
		
		try {
	
			ScheduleHostMaintenanceResponse hostResp = veStub.scheduleHostMaintenance(request);
	
			if (hostResp.getSuccess()) {
	
				String id = hostResp.getSchId().toString();
				appAxis.setId(id);
				appAxis.setAvailabilityStatus("SCHEDULED");
				appAxis.setAffiliationId(affiliationId.toString());
	
				/**
				 * To Local representation means to create an instance of 
				 * DbAppointment which is used in db methods
				 */
				DbAppointment app = AppointmentTranslator.toLocalRepresentation(appAxis);
				app.setActive(true);
				virtualLabsDB.scheduleAppointment(app);
				// TODO
				appAxis = addActionsToAppointment("", appAxis);
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleHostAppointment] " +
						"Appointment scheduled!");
			
			} 
			else {
			
				System.out.println("Appointment has not been scheduled");
			
			}
	
		} 
		catch (Error e) {
		
			e.printStackTrace();
	
		} 
		catch (Exception ex) {
	
			ex.printStackTrace();
	
		}
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleUserAppointmentWithoutVEId] Ready to get out!");
		
	}

	/**
	 * 
	 * @param modifyUserAppointmentRequest
	 * @return
	 */
	public ModifyHostAppointmentResponse modifyHostAppointment(
			ModifyHostAppointmentRequest modifyHostAppointmentRequest) {
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - modifyHostAppointments] Inside!");
	
		ModifyHostAppointmentResponse resp = new ModifyHostAppointmentResponse();
	
		try {
			
			String id = modifyHostAppointmentRequest.getId();
			Calendar start = modifyHostAppointmentRequest.getStart();
			Calendar end = modifyHostAppointmentRequest.getEnd();
			Calendar now = Calendar.getInstance();
			
			// Fixing the time zone based on the default time zone for the requesting user.
			User user = virtualLabsDB.getUser(modifyHostAppointmentRequest.getRequestingUser());
			TimeZone timeZone = TimeZone.getTimeZone(user.getTimeZone());
			start = TimeZoneTools.changeTimeZone(start, timeZone);
			end = TimeZoneTools.changeTimeZone(end, timeZone);
	
			// Check validity of dates
			if (end.before(start) || !start.before(end)) {
	
				resp.setSuccess(false);
				resp.setReason("End date must be after start date!");
				return resp;
	
			} 
			else if (start.before(now)) {
	
				resp.setSuccess(false);
				resp.setReason("Dates are not valid!");
				return resp;
	
			}
			
			// Set request 
			ModifyScheduledHostMaintenanceRequest request = 
				new ModifyScheduledHostMaintenanceRequest();
			request.setSchId(id);
			TimePeriod period = new TimePeriod();
			period.setStartTime(start);
			period.setEndTime(end);
			request.setTimePeriod(period);
	
			ModifyScheduledHostMaintenanceResponse hostResp = 
				veStub.modifyScheduledHostMaintenance(request);
	
			// If response was successful, get the new ve_id and add it to the db
			if (hostResp.getSuccess()) {
	
				DbAppointment app = 
					virtualLabsDB.getAppointment(modifyHostAppointmentRequest.getId());
				virtualLabsDB.cancelAppointment(app.getSchId());
		
				DbAppointment newApp = 
					new DbAppointment(
							hostResp.getSchId(),
							app.getQuotaId(),
							app.getAffiliationId(),
							app.getUsername(),
							app.getCourse(),
							app.getResourceType(),
							true,
							start,
							end);
				virtualLabsDB.scheduleAppointment(newApp);
				
				resp.setSuccess(true);
				resp.setReason("Appointment has been updated!");
	
			} 
			else {
	
				resp.setSuccess(false);
				resp.setReason(hostResp.getMessage());
	
			}
	
		} 
		catch (Error e) {
	
			e.printStackTrace();
			resp.setSuccess(false);
			resp.setReason("Error:"+e.getMessage());
	
		} 
		catch (Exception e) {
	
			e.printStackTrace();
			resp.setSuccess(false);
			resp.setReason("Exception:"+e.getMessage());
	
		}
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - modifyHostAppointments] Ready to get out!");
	
		return resp;
	
	}

	public CancelHostAppointmentResponse cancelHostAppointment(
			CancelHostAppointmentRequest cancelHostAppointmentRequest) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - cancelUserAppointments] Inside!");

		CancelHostAppointmentResponse resp = new CancelHostAppointmentResponse();

		//Get id from the request
		String id = cancelHostAppointmentRequest.getId();

		try {

			// Set up request to call cancelScheduledVE		
			// veStub = new VESchedulerStub(settings.getVeSchedulerEPR());
			CancelScheduledHostMaintenanceRequest request = 
				new CancelScheduledHostMaintenanceRequest();
			request.setSchId(id);
			CancelScheduledHostMaintenanceResponse hostResp = 
				veStub.cancelScheduledHostMaintenance(request);

			if (hostResp.getSuccess()) {

				if (virtualLabsDB.cancelAppointment(id)) {
					
					resp.setSuccess(true);
					resp.setReason("Appointment has been cancelled!");

				}
				else {
					
					resp.setSuccess(false);
					resp.setReason("Host appointment has been cancelled in vescheduler2 DB, " +
							"but it could not be removed from virtuallabs DB! " +
							"Please inform the developer!");

				}
	
			} 
			else {
				
				resp.setSuccess(false);
				resp.setReason(hostResp.getMessage());

			}


		} catch (Error e) {
			
			resp.setSuccess(false);
			resp.setReason(e.getMessage());

		} catch (Exception ex) {

			ex.printStackTrace();
			resp.setSuccess(false);
			resp.setReason(ex.getMessage());

		}
		 
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - cancelHostAppointments] ready to get out!");

		return resp;

	}

	/**
	 * 
	 * @param cancelAllHostAppointmentsRequest
	 * @return
	 */
	public CancelAllHostAppointmentsResponse cancelAllHostAppointments(
			CancelAllHostAppointmentsRequest cancelAllHostAppointmentsRequest) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - cancelAllHostAppointments] Inside!");

		String affiliationId = cancelAllHostAppointmentsRequest.getAffiliationId();

		CancelAllHostAppointmentsResponse resp = new CancelAllHostAppointmentsResponse();
		List<DbAppointment> dbapps = virtualLabsDB.getAppointments(affiliationId);
		
		Appointment[] apps = AppointmentTranslator.toAxisRepresentation(dbapps);
		List<Appointment> appsList = Arrays.asList(apps);

		try {
			
			int cancelled = 0;

			Iterator<Appointment> itrApp = appsList.iterator();
			while (itrApp.hasNext()) {

				Appointment app = itrApp.next();

				//Set up request to call cancelScheduledVE
				// veStub = new VESchedulerStub(settings.getVeSchedulerEPR());
				CancelScheduledHostMaintenanceRequest request = 
					new CancelScheduledHostMaintenanceRequest();
				request.setSchId(app.getId());
				CancelScheduledHostMaintenanceResponse hostResp = 
					veStub.cancelScheduledHostMaintenance(request);

				if (hostResp.getSuccess()) {
					
					if (virtualLabsDB.cancelAppointment(app.getId())) {
						
						resp.setSuccess(true);
						resp.setReason("Appointment has been cancelled!");

					}
					else {
						
						resp.setSuccess(false);
						resp.setReason("Host appointment has been cancelled in vescheduler2 DB, " +
								"but it could not be removed from virtuallabs DB! " +
								"Please inform the developer!");

					}

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
		 
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - cancelAllHostAppointments] Ready to get out!");

		return resp;

	}

	/**
	 * 
	 * @param getMentorAppointmentsRequest
	 * @return
	 */
	public GetMentorAppointmentsResponse getMentorAppointments(
			GetMentorAppointmentsRequest getMentorAppointmentsRequest) 
	{
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getMentorAppointment] Inside!");
		//Prepare response
		List<Appointment> allAppointments = new ArrayList<Appointment>();
		GetMentorAppointmentsResponse resp = new GetMentorAppointmentsResponse();
	
		//Get start and end date for the request
		Calendar start = getMentorAppointmentsRequest.getStart();
		Calendar end = getMentorAppointmentsRequest.getEnd();
	
		// Fix the time zone based on the requesting user's time zone
		User reqUser = virtualLabsDB.getUser(getMentorAppointmentsRequest.getRequestingUser());
		TimeZone timeZone = TimeZone.getTimeZone(reqUser.getTimeZone());
		start = TimeZoneTools.changeTimeZone(start, timeZone);
		end = TimeZoneTools.changeTimeZone(end, timeZone);
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getMentorAppointment] " +
				"start: " + start.getTime());
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getMentorAppointment] " +
				"end: " + end.getTime());
	
		// Return an empty array of appointments in case the request is invalid
		try {
	
			String username = getMentorAppointmentsRequest.getUserName();
	
			if (!virtualLabsDB.userExists(username)) {
	
				System.out.println("User does not exist");
	
			} 
			else if (end.before(start)  || !start.before(end)) {
	
				System.out.println("Start date must be before end date");
	
			}
			else {
	
				// If the request is valid, get availability and schedule
				
				/*
				List<Appointment> availableSlots = 
					getMentorAvailability(username, start, end);
				// Fixing the appointments' time zone based on the requesting user's default time zone.
				availableSlots = fixTimeZone(reqUser.getUserName(), availableSlots);
				// Divide appointment by day
				availableSlots = divideByDay(availableSlots);
				*/
				
				List<Appointment> scheduledApps = 
					getScheduledMentorAppointments(username, start, end);
				// Fixing the appointments' time zone based on the requesting user's default time zone.
				scheduledApps = fixTimeZone(reqUser.getUserName(), scheduledApps);
				
				// Merge both lists 
				// allAppointments.addAll(availableSlots);
				allAppointments.addAll(scheduledApps);
				
				// Add appointments to the response list
				Iterator<Appointment> itrAllApps = allAppointments.iterator();
				while (itrAllApps.hasNext()) {
				
					Appointment app = itrAllApps.next();
					resp.addAppointments(app);
				
				}
	
			}
	
		} 
		catch (Error e) {
	
			e.printStackTrace();
	
		} 
		catch (Exception ex) {
			
			ex.printStackTrace();
		
		}
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getMentorAppointment] Ready to get out!");
	
		return resp;
	
	}

	/**
	 * Get available slots from ve scheduler and returns them
	 * 
	 * @param username
	 * @param start
	 * @param end
	 * @return
	 */
	/*
	private List<Appointment> getMentorAvailability(
			String username, Calendar start, Calendar end) {
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getMentorAvailability] Inside!");
	
		//Prepare response
		List<Appointment> availableAppointments = new ArrayList<Appointment>();
	
		try {
			
			// Set the request
			GetMentorUnallocatedWorktimeAvailabilityRequest request = 
				new GetMentorUnallocatedWorktimeAvailabilityRequest();
			TimePeriod period = new TimePeriod();
			period.setStartTime(start);
			period.setEndTime(end);
			request.setTimePeriod(period);
			request.setMentorUsername(username);
	
			// Call getMentorUnallocatedWorktimeAvailability
			GetMentorUnallocatedWorktimeAvailabilityResponse veResp = 
				mentorStub.getMentorUnallocatedWorktimeAvailability(request);
	
			//Get courses and resources of the user
			Collection<String> resources = virtualLabsDB.getResources(username);
			Collection<String> courses = virtualLabsDB.getCourses(username);
	
			// Get every ve_type of every course-resource combination for this user
			Iterator<String> itrRes = resources.iterator();
			while (itrRes.hasNext()) {
	
				String resource = itrRes.next();
	
				Iterator<String> itrCourse = courses.iterator();
				while (itrCourse.hasNext()) {
	
					String course = itrCourse.next();
	
					if (course.equals("All Courses")) {
						
						TimePeriod[] slots = 
							veResp.getAvailabilityRange();
						List<Appointment> availableAppTempList = 
							new ArrayList<Appointment>();

						// Translate the time slots into appointments
						if (slots != null) {

							Appointment[] availableAppTempArr = 
								AppointmentTranslator.toAxisRepresentation(
										slots, 
										resource, 
										course, 
										"AVAILABLE", 
										null, 
										null);

							availableAppTempList = Arrays.asList(availableAppTempArr);

						}

						Iterator<Appointment> itrAvailApp = availableAppTempList.iterator();
						while (itrAvailApp.hasNext()) {
							Appointment appAxis = itrAvailApp.next();

							appAxis = addActionsToAvailableSlot(appAxis);

							availableAppointments.add(appAxis);

						}

					}
	
				}
				
			}
	
		} 
		catch (Error e) {
	
			e.printStackTrace();
	
		} 
		catch (Exception ex) {
		
			ex.printStackTrace();
	
		}
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getMentorAvailability] Ready to get out!");
	
		return availableAppointments;
	
	}
*/
	/**
	 * Get all scheduled appointments for this user
	 * 
	 */
	private List<Appointment> getScheduledMentorAppointments(
			String username, Calendar start, Calendar end) {	
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getScheduledMentorAppointments] Inside!");
		
		//If there is no end date, set it up as two months from now
		if (end == null) {
			end = Calendar.getInstance();
			end.set(Calendar.MONTH, end.get(Calendar.MONTH)+2);
		}
	
		List<Appointment> scheduledApps = 
			new ArrayList<Appointment>();	
		List<Appointment> mentorSchUWApps = 
			getMentorSchUW(username, start, end);
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getScheduledMentorAppointments] " +
				"mentorSchUWApps.size(): " + mentorSchUWApps.size());
		List<Appointment> mentorSchMentoringApps = 
			getMentorSchMentoring(username, start, end);
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getScheduledMentorAppointments] " +
				"mentorSchMentoringApps.size(): " + mentorSchMentoringApps.size());
	
		Iterator<Appointment> itrMentorSchUW = mentorSchUWApps.iterator();	
		while (itrMentorSchUW.hasNext()) {
	
			Appointment mentorApp = itrMentorSchUW.next();				
			DbAppointment dbApp = virtualLabsDB.getAppointment(mentorApp.getId());
			// TODO
			mentorApp = addActionsToAppointment("", mentorApp);
	
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getScheduledMentorAppointments] " +
					"mentorApp.getId(): " + mentorApp.getId());
			
			if (dbApp != null) {
				
				mentorApp.setAffiliationId(dbApp.getAffiliationId());
			
			}
			else {
				
				UUID affiliationId = UUID.randomUUID();
				mentorApp.setAffiliationId(affiliationId.toString());
	
				// To Local representation means to create an instance of 
				// DbAppointment which is used in db methods
	
				DbAppointment app = AppointmentTranslator.toLocalRepresentation(mentorApp);
				virtualLabsDB.scheduleAppointment(app);
	
			}
	
			mentorApp.setUserName(username);
			scheduledApps.add(mentorApp);
		
		}
	
		Iterator<Appointment> itrMentorSchMentoring = mentorSchMentoringApps.iterator();	
		while (itrMentorSchMentoring.hasNext()) {
	
			Appointment mentorApp = itrMentorSchMentoring.next();				
			DbAppointment dbApp = virtualLabsDB.getAppointment(mentorApp.getId());
			mentorApp = addActionsToMentorSchMentoringAppointment(mentorApp);
	
			if (dbApp != null) {
				
				mentorApp.setAffiliationId(dbApp.getAffiliationId());
			
			}
			else {
				
				UUID affiliationId = UUID.randomUUID();
				mentorApp.setAffiliationId(affiliationId.toString());
	
				// To Local representation means to create an instance of 
				// DbAppointment which is used in db methods
	
				DbAppointment app = AppointmentTranslator.toLocalRepresentation(mentorApp);
				virtualLabsDB.scheduleAppointment(app);
	
			}
	
			mentorApp.setUserName(username);
			scheduledApps.add(mentorApp);
			
		}
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getScheduledMentorAppointments] Ready to get out!");
		
		return scheduledApps;
	
	}

	/**
	 * Add actions instances to the appointment that is being passed,
	 * depending on their availability type
	 * 
	 */
	private Appointment addActionsToMentorSchMentoringAppointment(
			Appointment appAxis) {
	
		Calendar now = Calendar.getInstance();
	
		// Check if appointments is an old one
		if (!appAxis.getEnd().before(now)) {
	
			Action cancel = new Action();
			cancel.setType("cancel");
			cancel.setContent(appAxis.getId());
			appAxis.addAction(cancel);
	
			Action cancelAll = new Action();
			cancelAll.setType("cancel all");
			cancelAll.setContent(appAxis.getAffiliationId());
			appAxis.addAction(cancelAll);
	
		}
	
		Action info = new Action();
		info.setType("info");
		info.setContent("");
		appAxis.addAction(info);
	
		return appAxis;
	
	}

	private List<Appointment> getMentorSchUW(
			String username, Calendar start, Calendar end) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getMentorSchUW] Inside!");
		
		List<Appointment> scheduledAppointments = new ArrayList<Appointment>();
	
		try {
	
			// Set up the request
			GetMentorScheduledUnallocatedWorktimeRequest request = 
				new GetMentorScheduledUnallocatedWorktimeRequest();
	
			TimePeriod period = new TimePeriod();
			period.setStartTime(start);
			period.setEndTime(end);
			request.setTimePeriod(period);
			request.setMentorUsername(username);
	
			GetMentorScheduledUnallocatedWorktimeResponse resp =
				mentorStub.getMentorScheduledUnallocatedWorktime(request);
	
			if (resp.getScheduledRange() != null) {
				Collection<String> resources = virtualLabsDB.getResources(username);
	
				// Get all the resource-course combinations for this user
				Iterator<String> itrRes = resources.iterator();
				while (itrRes.hasNext()) {

					String resource = itrRes.next();

					String course = "All Courses";

					ScheduledEvent[] schedule = resp.getScheduledRange();								

					// Translate the schedule into an array of appointments
					Appointment[] scheduledAppTempArr = 
						AppointmentTranslator.toAxisRepresentation(
								schedule, 
								resource, 
								course, 
								"AVAILABLE", 
								username, 
								null);

					// Add appointments in the new array to the response
					List<Appointment> scheduledAppTempList = 
						Arrays.asList(scheduledAppTempArr);
					Iterator<Appointment> itrAvailApp = 
						scheduledAppTempList.iterator();
					while (itrAvailApp.hasNext()) {

						Appointment appAxis = itrAvailApp.next();
						scheduledAppointments.add(appAxis);

					}

				}
	
			}
			
		} 
		catch (Error e) {
			
			e.printStackTrace();
	
		} 
		catch (Exception ex) {
		
			ex.printStackTrace();
	
		}
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getMentorSchUW] Ready to get out!");
		
		return scheduledAppointments;
	}

	private List<Appointment> getMentorSchMentoring(
			String username, Calendar start, Calendar end) {
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getMentorSchMentoring] Inside!");
		
		List<Appointment> scheduledAppointments = new ArrayList<Appointment>();
	
		try {
	
			// Set up the request
			GetMentoringScheduleRequest request = new GetMentoringScheduleRequest();
	
			TimePeriod period = new TimePeriod();
			period.setStartTime(start);
			period.setEndTime(end);
			request.setTimePeriod(period);
			request.setMentorUsername(username);
	
			Collection<String> resources = virtualLabsDB.getResources(username);
			Collection<String> courses = virtualLabsDB.getCourses(username);
	
			// Get all the resource-course combinations for this user
			Iterator<String> itrRes = resources.iterator();
			while (itrRes.hasNext()) {
				
				String resource = itrRes.next();
	
				Iterator<String> itrCourse = courses.iterator();
				while (itrCourse.hasNext()) {
				
					String course = itrCourse.next();
	
					request.setCourseId(course);
					GetMentoringScheduleResponse mentorResp = 
						mentorStub.getMentoringSchedule(request);
	
					if (mentorResp.getSchedule() != null) {
						
						ScheduledEvent[] schedule = mentorResp.getSchedule();								
	
						// Translate the schedule into an array of appointments
						Appointment[] scheduledAppTempArr = 
							AppointmentTranslator.toAxisRepresentation(
									schedule, 
									resource, 
									course, 
									"SCHEDULED", 
									username, 
									null);
	
						// Add appointments in the new array to the response
						List<Appointment> scheduledAppTempList = 
							Arrays.asList(scheduledAppTempArr);
						Iterator<Appointment> itrAvailApp = 
							scheduledAppTempList.iterator();
						while (itrAvailApp.hasNext()) {
	
							Appointment appAxis = itrAvailApp.next();
							scheduledAppointments.add(appAxis);
	
						}
					
					}
				
				}
	
			}
	
		} 
		catch (Error e) {
			
			e.printStackTrace();
	
		} 
		catch (Exception ex) {
		
			ex.printStackTrace();
	
		}
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getMentorSchMentoring] Ready to get out!");
		
		return scheduledAppointments;
	}

	/**
	 * 
	 * @param scheduleMentorAppointmentsRequest
	 * @return
	 */
	public ScheduleMentorAppointmentsResponse scheduleMentorAppointments(
			ScheduleMentorAppointmentsRequest scheduleMentorAppointmentsRequest) {
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleMentorAppointments] Inside!");
	
		ScheduleMentorAppointmentsResponse resp = new ScheduleMentorAppointmentsResponse();
	
		//Get the request with the list of appointments
		Appointment[] apps = scheduleMentorAppointmentsRequest.getAppointment();
		
		// Fixing the time zone based on the default time zone for the requesting user.
		apps = addTimeZone(
				scheduleMentorAppointmentsRequest.getRequestingUser(),
				apps);
	
		//Create a UUID for this batch of appointments
		UUID affiliationId = UUID.randomUUID();
	
		try {
	
			//	If request fields are incomplete, return the request as it is
			if (!virtualLabsDB.userExists(apps[0].getUserName())) {
				
				resp.setAppointment(apps);
				System.out.println("[VirtualLabs - scheduleUserAppointments] " +
						"The username " + apps[0].getUserName() + " does not exist!");
				return resp;
			
			}
	
			// For every appointments in the request, make a call to ScheduleMentoring
			List<Appointment> appsList = Arrays.asList(apps);
			Iterator<Appointment> itrApp = appsList.iterator();
			while (itrApp.hasNext()) {
	
				Appointment appAxis = itrApp.next();
				
				ScheduleMentorUnallocatedWorktimeRequest request =
					new ScheduleMentorUnallocatedWorktimeRequest();
				Calendar start = appAxis.getStart();
				Calendar end = appAxis.getEnd();
				Calendar now = Calendar.getInstance();
	
				// Check dates are correct first.
				if (end.before(start)  || !start.before(end)) {
	
					System.out.println("[VirtualLabs - scheduleAppointments] Start date must be before end date");
					resp.addAppointment(appAxis);
	
				} 
				else if (start.before(now)) {
	
					System.out.println("[VirtualLabs - scheduleAppointments] Dates are not valid");
					resp.addAppointment(appAxis);
	
				} 
				else { // If the times are ok, set up the request and call
	
					TimePeriod period = new TimePeriod();
					period.setStartTime(start);
					period.setEndTime(end);
					request.setTimePeriod(period);
	
					request.setMentorUsername(appAxis.getUserName());
	
					scheduleMentorUWAppointment(
							appAxis, 
							request, 
							affiliationId);
	
					// Add the appointment to the response list of appointments
					resp.addAppointment(appAxis);

				}
	
			}
	
		} catch (Exception ex) {
	
			resp.setAppointment(apps);
			ex.printStackTrace();
		}
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleMentorAppointments] Ready to get out!");
	
		return resp;
	
	}

	/*
		 * Function that checks id an scheduled appointment overlaps with app.
		 * 	
		 */
		/*
		private boolean isOverlappingWithMentorScheduledAppointments(Appointment app)
		{
		
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isOverlappingWithMentorScheduledAppointments] Inside!");
		
			boolean retVal = false;
			
			List<Appointment> scheduledApps = 
				getScheduledMentorAppointments(
						app.getUserName(), 
						app.getStart(),
						app.getEnd());   				
			Iterator<Appointment> itrApp = scheduledApps.iterator();
		
			while(itrApp.hasNext()) {
				
				Appointment schApp = itrApp.next();
		
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isOverlappingWithMentorScheduledAppointments] " +
						"app.getId(): " + app.getId());
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isOverlappingWithMentorScheduledAppointments] " +
						"schApp.getId(): " + schApp.getId());
				
				if (app.getId() != null) {
					
					if (!app.getId().equals(schApp.getId())) {
						
						if(AreOverlapping(app, schApp))
							retVal = true;
					
					}
				
				}
		
			}
		
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isOverlappingWithMentorScheduledAppointments] " + retVal);
	
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - isOverlappingWithMentorScheduledAppointments] Ready to get out!");
			
			return retVal;
		
		}
	*/
		private void scheduleMentorUWAppointment(
				Appointment appAxis,
				ScheduleMentorUnallocatedWorktimeRequest request, 
				UUID affiliationId) {
		
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleMentorUWAppointment] Inside!");
			
			try {
				
				ScheduleMentorUnallocatedWorktimeResponse mentorResp = 
					mentorStub.scheduleMentorUnallocatedWorktime(request);
		
				if (mentorResp.getSuccess()) {
		
					String id = mentorResp.getId();
					appAxis.setId(id);
					appAxis.setAvailabilityStatus("SCHEDULED");
					appAxis.setAffiliationId(affiliationId.toString());
		
					// To Local representation means to create an instance of 
					// DbAppointment which is used in db methods
					
					DbAppointment app = AppointmentTranslator.toLocalRepresentation(appAxis);
					app.setActive(true);
					virtualLabsDB.scheduleAppointment(app);
					// TODO
					appAxis = addActionsToAppointment("", appAxis);
					DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleMentorUWAppointment] Appointment scheduled");
					
				} 
				else {
					
					System.out.println("[VirtualLabs - scheduleMentorUWAppointment] Appointment has not been scheduled");
				
				}
		
			} 
			catch (Error e) {
				
				e.printStackTrace();
		
			} 
			catch (Exception ex) {
		
				ex.printStackTrace();
		
			}
		
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleMentorUWAppointment] Ready to get out!");
			
		}

	/**
	 * scheduleAppointmentWithVEId
	 * In case the appointment schedule belong to a existing ve instance,
	 * this creates a request with that id included.
	 * 
	 */
	/*
	private void scheduleUserAppointmentWithVEId(
			Appointment appAxis,
			ScheduleVERequest request, String ve_id, UUID affiliationId) {
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleUserAppointmentWithVEId] Inside!");
		
		try {
			// Create request
			// veStub = new VESchedulerStub(settings.getVeSchedulerEPR());
			request.setVeInsId(ve_id);
	
			// Make the call
			ScheduleVEResponse veResp = veStub.scheduleVE(request);
			if (veResp.getSuccess()) {
	
				String id = veResp.getSchId().toString();
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
				scheduleUserAppointmentWithoutVEId(appAxis, request, affiliationId);
			}
	
		} catch (Error e) {
			e.printStackTrace();
	
		} catch (Exception ex) {
	
			ex.printStackTrace();
	
		}
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - scheduleUserAppointmentWithVEId] Ready to get out!");
		
	}
	*/
	
	/**
	 * 
	 * @param modifyMentorAppointmentRequest
	 * @return
	 */
	public ModifyMentorAppointmentResponse modifyMentorAppointment(
			ModifyMentorAppointmentRequest modifyMentorAppointmentRequest) {
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - modifyMentorAppointments] Inside!");
	
		ModifyMentorAppointmentResponse resp = new ModifyMentorAppointmentResponse();
	
		try {
	
			String id = modifyMentorAppointmentRequest.getId();
			Calendar start = modifyMentorAppointmentRequest.getStart();
			Calendar end = modifyMentorAppointmentRequest.getEnd();
			Calendar now = Calendar.getInstance();
			
			// Fixing the time zone based on the default time zone for the requesting user.
			User user = virtualLabsDB.getUser(modifyMentorAppointmentRequest.getRequestingUser());
			TimeZone timeZone = TimeZone.getTimeZone(user.getTimeZone());
			start = TimeZoneTools.changeTimeZone(start, timeZone);
			end = TimeZoneTools.changeTimeZone(end, timeZone);
	
			// Check validity of dates
			if (end.before(start) || !start.before(end)) {
	
				resp.setSuccess(false);
				resp.setReason("End date must be after start date!");
				return resp;
	
			} 
			else if (start.before(now)) {
	
				resp.setSuccess(false);
				resp.setReason("Dates are not valid!");
				return resp;
	
			}
			
			// Set request 
			ModifyScheduledMentorUnallocatedWorktimeRequest request = 
				new ModifyScheduledMentorUnallocatedWorktimeRequest();
			request.setId(id);
			TimePeriod period = new TimePeriod();
			period.setStartTime(start);
			period.setEndTime(end);
			request.setTimePeriod(period);
	
			ModifyScheduledMentorUnallocatedWorktimeResponse mentorResp = 
				mentorStub.modifyScheduledMentorUnallocatedWorktime(request);
	
			// If response was successful, get the new ve_id and add it to the db
			if (mentorResp.getSuccess()) {
	
				DbAppointment app = 
					virtualLabsDB.getAppointment(modifyMentorAppointmentRequest.getId());
				virtualLabsDB.cancelAppointment(app.getSchId());
		
				DbAppointment newApp = 
					new DbAppointment(
							mentorResp.getId(),
							app.getQuotaId(),
							app.getAffiliationId(),
							app.getUsername(),
							app.getCourse(),
							app.getResourceType(),
							true,
							start,
							end);
				virtualLabsDB.scheduleAppointment(newApp);
				
				resp.setSuccess(true);
				resp.setReason("Appointment has been updated");
	
			} 
			else {
	
				resp.setSuccess(false);
				resp.setReason(mentorResp.getMessage());
	
			}
	
		} 
		catch (Error e) {
	
			e.printStackTrace();
			resp.setSuccess(false);
			resp.setReason("Error:"+e.getMessage());
	
		} 
		catch (Exception e) {
	
			e.printStackTrace();
			resp.setSuccess(false);
			resp.setReason("Exception:"+e.getMessage());
	
		}
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - modifyMentorAppointments] Ready to get out!");
	
		return resp;
	
	}

	public CancelMentorAppointmentResponse cancelMentorAppointment(
			CancelMentorAppointmentRequest cancelMentorAppointmentRequest) {
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - cancelMentorAppointments] Inside!");
	
		CancelMentorAppointmentResponse resp = new CancelMentorAppointmentResponse();
	
		// Get id from the request
		String id = cancelMentorAppointmentRequest.getId();
	
		try {
	
			//Set up request to call cancelScheduledVE		
			// veStub = new VESchedulerStub(settings.getVeSchedulerEPR());
			CancelScheduledMentorUnallocatedWorktimeRequest mentorReq = 
				new CancelScheduledMentorUnallocatedWorktimeRequest();
			mentorReq.setId(id);
			CancelScheduledMentorUnallocatedWorktimeResponse mentorResp = 
				mentorStub.cancelScheduledMentorUnallocatedWorktime(mentorReq);
	
			if (mentorResp.getSuccess()) {
	
				if (virtualLabsDB.cancelAppointment(id)) {
					
					resp.setSuccess(true);
					resp.setReason("Appointment has been cancelled!");
	
				}
				else {
					
					resp.setSuccess(false);
					resp.setReason("Appointment has been cancelled in mentorscheduler DB, " +
							"but it could not be removed from virtuallabs DB! " +
							"Please inform the developer!");
	
				}
	
			} 
			else {
			
				resp.setSuccess(false);
				resp.setReason(mentorResp.getMessage());
	
			}
	
		} 
		catch (Error e) {
			
			resp.setSuccess(false);
			resp.setReason(e.getMessage());
	
		} 
		catch (Exception ex) {
	
			ex.printStackTrace();
			resp.setSuccess(false);
			resp.setReason(ex.getMessage());
	
		}
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - cancelMentorAppointments] ready to get out!");
	
		return resp;
	
	}

	/**
	 * 
	 * @param cancelAllMentorAppointmentsRequest
	 * @return
	 */
	public CancelAllMentorAppointmentsResponse cancelAllMentorAppointments(
			CancelAllMentorAppointmentsRequest cancelAllMentorAppointmentsRequest) {
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - cancelAllMentorAppointments] Inside!");
	
		String affiliationId = cancelAllMentorAppointmentsRequest.getAffiliationId();
	
		CancelAllMentorAppointmentsResponse resp = new CancelAllMentorAppointmentsResponse();
		List<DbAppointment> dbapps = virtualLabsDB.getAppointments(affiliationId);
	
		Appointment[] apps = AppointmentTranslator.toAxisRepresentation(dbapps);
		List<Appointment> appsList = Arrays.asList(apps);
	
		try {
			
			int cancelled = 0;
	
			Iterator<Appointment> itrApp = appsList.iterator();
			while (itrApp.hasNext()) {
	
				Appointment app = itrApp.next();
	
				// Set up request to call cancelScheduledMentorUnallocatedWorktime
				CancelScheduledMentorUnallocatedWorktimeRequest request = 
					new CancelScheduledMentorUnallocatedWorktimeRequest();
				request.setId(app.getId());
				CancelScheduledMentorUnallocatedWorktimeResponse veResp = 
					mentorStub.cancelScheduledMentorUnallocatedWorktime(request);
	
				if (veResp.getSuccess()) {
	
					if (virtualLabsDB.cancelAppointment(app.getId())) {
						
						resp.setSuccess(true);
						resp.setReason("Appointment has been cancelled!");
	
					}
					else {
						
						resp.setSuccess(false);
						resp.setReason("Appointment has been cancelled in mentorscheduler DB, " +
								"but it could not be removed from virtuallabs DB! " +
								"Please inform the developer!");
	
					}
	
					cancelled++;
	
				}
	
			}
	
			if (cancelled < appsList.size()) {
				resp.setSuccess(false);
				resp.setReason(appsList.size() - cancelled+ " appointments could not be cancelled");
	
			} 
			else {
	
				resp.setSuccess(true);
				resp.setReason("Appointments cancelled");
				System.out.println("Appointments cancelled");
	
			}
	
		} 
		catch (Error e) {
		
			resp.setSuccess(false);
			resp.setReason(e.getMessage());
	
		} 
		catch (Exception ex) {
	
			ex.printStackTrace();
			resp.setSuccess(false);
			resp.setReason(ex.getMessage());
	
		}
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - cancelAllMentorAppointments] Ready to get out!");
	
		return resp;
	
	}

	/**
	 * getAvailableActions 
	 * 
	 */
	public GetAvailableActionsResponse getAvailableActions(
			GetAvailableActionsRequest getAvailableActionsRequest) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getAvailableActions] Inside!");

		GetAvailableActionsResponse resp = new GetAvailableActionsResponse();
		try {

			resp.addActions("EDIT");
			resp.addActions("CONFIRM");
			resp.addActions("INFO");
			resp.addActions("CANCEL");


		} catch (Exception e) {
			e.printStackTrace();

		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getAvailableActions] Ready to get out!");

		return resp;

	}

	/*
	 * Check if two appointments overlap
	 * 
	 */
	private boolean areOverlapping(
			Appointment schApp,
			Appointment availApp) {
		
		// DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - areOverlapping] Inside!");
		
		boolean retVal = false;
		
		Calendar startSch = schApp.getStart();
		Calendar endSch = schApp.getEnd();

		Calendar startAvail = availApp.getStart();
		Calendar endAvail = availApp.getEnd();

		if (startSch.before(endAvail) && startSch.before(endSch) && 
			startAvail.before(endSch) && startAvail.before(endAvail))
			retVal = true;
		
		// DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - areOverlapping] retVal: " + retVal);
		
		// DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - areOverlapping] Ready to get out!");
		
		return retVal;

	}

	/*
	 * 	This function divides the appointment passed as an arguments as a list of
	 * appointments divided in days
	 * 
	 */
	private List<Appointment> getAppointmentSplittedInDays(Appointment app) {
		
		List<Appointment> splittedApps = new ArrayList<Appointment>();

		// Calendar start = Calendar.getInstance();
		// start.setTime(app.getStart().getTime());
		Calendar start = (Calendar)app.getStart().clone();
		
		// Calendar end = Calendar.getInstance();
		// end.setTime(app.getEnd().getTime());				
		Calendar end = (Calendar)app.getEnd().clone();
		
		boolean continues = true;

		while(continues) {
			
			Appointment newApp = AppointmentTranslator.copy(app);
			// Calendar newStart = Calendar.getInstance();
			// newStart.setTime(start.getTime());
			Calendar newStart = (Calendar)start.clone();
			
			// Calendar newEnd = Calendar.getInstance();
			Calendar newEnd = (Calendar)start.clone();
			
			newApp.setStart(newStart);

			// newEnd.setTime(start.getTime());
			newEnd.add(Calendar.DATE, 1);
			newEnd.set(Calendar.HOUR, 00);
			newEnd.set(Calendar.MINUTE, 00);
			newEnd.set(Calendar.SECOND, 00);
			// newEnd.set(Calendar.DAY_OF_MONTH, newEnd.get(Calendar.DAY_OF_MONTH)+1);
			newEnd.set(Calendar.AM_PM , Calendar.AM);

			if (newEnd.before(end)) {
				
				newApp.setEnd(newEnd);

			}
			else {

				continues = false;				
			
			}

			splittedApps.add(newApp);

			// start.setTime(newApp.getEnd().getTime());
			start = (Calendar)newApp.getEnd().clone();
			
			if(start.after(end)) {
				
				continues = false;
			
			}

		}

		return splittedApps;		

	}

	private Appointment addActionsToAvailableSlot(
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
	private List<Appointment> getUnavailability(
			String username, Calendar start, Calendar end) {

		List<Appointment> availableAppointments = new ArrayList<Appointment>();

		try {

			// VESchedulerClient client = new VESchedulerClient(settings.getVeSchedulerEPR());
			// veStub = new VESchedulerStub(settings.getVeSchedulerEPR());
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
					String ve_type = virtualLabsDB.getVEType(course,
							resource);
					request.setVe(readVE(new File(ve_type)));

					String ve_id = virtualLabsDB.getVEInstanceId(course,
							resource, username, true);
					if (!ve_type.equals("")) {
						request.setId(ve_type);
					}

					GetVEUnavailabilityResponse veResp = veStub.getVEUnavailability(request);
					TimePeriod[] slots = veResp.getUnavailabilityRange();
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
	 * The assumption is that the username is unique.
	 * 
	 * @param createUserProfileRequest
	 * @return
	 */
	public CreateUserProfileResponse createUserProfile(
			CreateUserProfileRequest createUserProfileRequest) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - createUserProfile] Inside!");

		CreateUserProfileResponse resp = new CreateUserProfileResponse();

		String userRole = createUserProfileRequest.getUserRole().toUpperCase();
		if ((userRole == null) || (userRole == ""))
			userRole = "STUDENT";
		
		// If the user exists, just edit its profile according to this request.
		if (virtualLabsDB.userExists(createUserProfileRequest.getUserName())) {
			
			EditUserProfileRequest userReq = new EditUserProfileRequest();
			userReq.setRequestingUser(createUserProfileRequest.getRequestingUser());
			userReq.setUserName(createUserProfileRequest.getUserName());
			userReq.setPassword(createUserProfileRequest.getPassword());
			userReq.setFirstName(createUserProfileRequest.getFirstName());
			userReq.setLastName(createUserProfileRequest.getLastName());
			userReq.setEmailAddress(createUserProfileRequest.getEmailAddress());
			userReq.setUserRole(userRole);
			userReq.setTimeZone(createUserProfileRequest.getTimeZone());
			userReq.setContactInfo(createUserProfileRequest.getContactInfo());

			EditUserProfileResponse userResp = 
				editUserProfile(userReq);

			resp.setSuccess(userResp.getSuccess());
			resp.setReason(
					"Instead of creating a new user, the user profile was modified according to this request! " +
					userResp.getReason());

			updateHtpasswd(createUserProfileRequest.getUserName(), createUserProfileRequest.getPassword());
			
			return resp;

		}
		
		String tzStr = createUserProfileRequest.getTimeZone();
		tzStr = TimeZoneTools.getTimeZoneIdInJava(tzStr);

		User user = 
			new User(
					createUserProfileRequest.getUserName(),
					createUserProfileRequest.getPassword(),
					createUserProfileRequest.getFirstName(),
					createUserProfileRequest.getLastName(),
					createUserProfileRequest.getEmailAddress(),
					userRole,
					tzStr,
					createUserProfileRequest.getContactInfo());

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - createUserProfile] " +
				"User: " + user);

		try {

			virtualLabsDB.addUser(user);
			resp.setSuccess(true);
			resp.setReason(user.toString() + " has been added");

			createHtpasswd(createUserProfileRequest.getUserName(), createUserProfileRequest.getPassword());
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - createUserProfile] " +
					"user.getUserRole().toUpperCase().equals('MENTOR'): " + 
					user.getUserRole().toUpperCase().equals("MENTOR") +
					" user.getUserRole().toUpperCase(): " +
					user.getUserRole().toUpperCase());

			if (user.getUserRole().toUpperCase().equals("MENTOR")) {
				
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - createUserProfile] " +
						"Ready to call addMentor()!");
				
				AddMentorRequest mentorReq = new AddMentorRequest();
				mentorReq.setMentorUsername(user.getUserName());
				AddMentorResponse mentorResp = mentorStub.addMentor(mentorReq);
				if (!mentorResp.getSuccess()) {
					
					resp.setSuccess(false);
					resp.setReason(user.toString() + " has been added to the VirtualLabs DB " +
							"but could not be added to the MentorScheduler DB! " +
							"The DBs are inconsistent now! " +
							"Please inform the developer!");

				}

			}
			
			// TODO
			/*
			// Create the user in Kaseya too
			if (kaseyaWSClient.createAdmin(
					user.getUserName(),
					user.getPassword(),
					user.getEmailAddress(),
					user.getFirstName(),
					user.getLastName())) {
			
				// Disable the admin until there is a schedule
				if (!kaseyaWSClient.disableAdmin(user.getUserName())) {
					
					resp.setSuccess(false);
					resp.setReason("The admin created in Kaseya could not be disabled!");
				}
				
			}
			else {
				
				resp.setSuccess(false);
				resp.setReason("The admin in Kaseya could not be created!");
		
			}
			*/
			
		} catch (Error e) {
			resp.setSuccess(false);
			resp.setReason(e.getMessage());
			e.printStackTrace();
		} catch (Exception ex) {
			resp.setSuccess(false);
			resp.setReason(ex.getMessage());
			ex.printStackTrace();
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - createUserProfile] Ready to get out!");

		return resp;
	}

	/**
	 * The assumption is that the username is unique.
	 * 
	 * @param createUserProfileWithEncryptedPasswordRequest
	 * @return
	 */
	public CreateUserProfileWithEncryptedPasswordResponse createUserProfileWithEncryptedPassword(
			CreateUserProfileWithEncryptedPasswordRequest createUserProfileWithEncryptedPasswordRequest) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - createUserProfileWithEncryptedPassword] Inside!");

		CreateUserProfileWithEncryptedPasswordResponse resp = new CreateUserProfileWithEncryptedPasswordResponse();

		String encryptedPassword = createUserProfileWithEncryptedPasswordRequest.getEncryptedPassword();
		String username = createUserProfileWithEncryptedPasswordRequest.getUserName();
		String password = Crypt.decrypt(encryptedPassword);
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - createUserWithEncryptedPasswordProfile] "
				+ "userName: " + createUserProfileWithEncryptedPasswordRequest.getUserName() 
				+ " encryptedPassword: " + encryptedPassword
				+ " password: " + password);
		
		CreateUserProfileRequest wrappedReq = new CreateUserProfileRequest();
		wrappedReq.setRequestingUser(createUserProfileWithEncryptedPasswordRequest.getRequestingUser());
		wrappedReq.setUserName(createUserProfileWithEncryptedPasswordRequest.getUserName());
		wrappedReq.setPassword(password);
		wrappedReq.setFirstName(createUserProfileWithEncryptedPasswordRequest.getFirstName());
		wrappedReq.setLastName(createUserProfileWithEncryptedPasswordRequest.getLastName());
		wrappedReq.setEmailAddress(createUserProfileWithEncryptedPasswordRequest.getEmailAddress());
		wrappedReq.setUserRole(createUserProfileWithEncryptedPasswordRequest.getUserRole());
		wrappedReq.setTimeZone(createUserProfileWithEncryptedPasswordRequest.getTimeZone());
		wrappedReq.setContactInfo(createUserProfileWithEncryptedPasswordRequest.getContactInfo());
		CreateUserProfileResponse wrappedResp = createUserProfile(wrappedReq);
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - createUserProfileWithEncryptedPassword] "
				+ "wrappedResp.getSuccess(): " + wrappedResp.getSuccess());
		if (wrappedResp.getSuccess())
			setUserCachedPassword(username, password);
		
		resp.setSuccess(wrappedResp.getSuccess());
		resp.setReason(wrappedResp.getReason());

		// passTerminator.notifyAll();

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - createUserProfileWithEncryptedPassword] Ready to get out!");

		return resp;
	}

	private void updateHtpasswd(String userName, String password) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - updateHtpasswd] Inside!");

		if (password != null) {
			String escapedPassword = escapeString(password);
			String command = 
					"htpasswd -b .htpasswd " + 
							"'" + userName + "' " +
							"'" + escapedPassword + "'";

			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - updateHtpasswd ] " +
					"command to be executed: " + command);

			HtpasswdHost htpasswdHost = virtualLabsDB.getFirstActiveHtpasswdHost();
			int wait = 0;
			SSHCommand cmd = 
					new SSHCommand(
							command, 
							htpasswdHost.getName(), 
							htpasswdHost.getSshPort(), 
							htpasswdHost.getUsername(), 
							htpasswdHost.getPassword(),
							wait);
			int retCode = 0;
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - updateHtpasswd ] " +
					"command that is going to be dispatched: " + command + " " +
					"on host " + htpasswdHost.getName() + " " +
					"and is in the waiting list for " + wait + " seconds!" +
					" retCode is " + retCode);
			retCode = cmd.run();
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - updateHtpasswd ] " +
					"command that was executed: " + command + " " +
					"on host " + htpasswdHost.getName() + " " +
					" retCode is " + retCode);
		} else {
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - updateHtpasswd] "
					+ "password is " + password);			
		}
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - updateHtpasswd] Ready to get out!");

	}

	private String escapeString(String password) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - escapeString] Inside!");

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - escapeString] " +
				"password: " + password);

		String escapeString = password;
		
		if (password != null) {
		
			final StringBuilder result = new StringBuilder();
			final StringCharacterIterator iterator = new StringCharacterIterator(password);
			char character =  iterator.current();
			while (character != CharacterIterator.DONE ){
				if (character == '!') {
					result.append("\\!");
				}
				else {
					//the char is not a special one
					//add it to the result as is
					result.append(character);
				}
				character = iterator.next();
			}
			escapeString = result.toString(); 
		}
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - escapeString] " +
				"escapeString: " + escapeString);

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - escapeString] Ready to get out!");

		return escapeString;

	}

	private void createHtpasswd(String userName, String password) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - createHtpasswd] Inside!");

		updateHtpasswd(userName, password);
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - createHtpasswd] Ready to get out!");

	}

	/**
	 * 
	 * @param editUserProfileRequest
	 * @return
	 */
	public EditUserProfileResponse editUserProfile(
			EditUserProfileRequest editUserProfileRequest) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - editUserProfile] Inside!");

		EditUserProfileResponse resp = new EditUserProfileResponse();

		String role = editUserProfileRequest.getUserRole();
		if (role != null)
			role = role.toUpperCase();
		
		// User chkUser = virtualLabsDB.getUser(editUserProfileRequest.getUserName());
		// if (chkUser == null) {
		if (!virtualLabsDB.userExists(editUserProfileRequest.getUserName())) {
			
			CreateUserProfileRequest userReq = new CreateUserProfileRequest();
			userReq.setRequestingUser(editUserProfileRequest.getRequestingUser());
			userReq.setUserName(editUserProfileRequest.getUserName());
			userReq.setPassword(editUserProfileRequest.getPassword());
			userReq.setFirstName(editUserProfileRequest.getFirstName());
			userReq.setLastName(editUserProfileRequest.getLastName());
			userReq.setEmailAddress(editUserProfileRequest.getEmailAddress());
			userReq.setUserRole(role);
			userReq.setTimeZone(editUserProfileRequest.getTimeZone());
			userReq.setContactInfo(editUserProfileRequest.getContactInfo());
			
			CreateUserProfileResponse userResp = 
				createUserProfile(userReq);
			
			resp.setSuccess(userResp.getSuccess());
			resp.setReason(
					"Instead of editing, the user profile was being added! " +
					userResp.getReason());
			
			updateHtpasswd(editUserProfileRequest.getUserName(), editUserProfileRequest.getPassword());

			return resp;
			
		}
		
		try {

			User oldUser = virtualLabsDB.getUser(editUserProfileRequest.getUserName());
			
			String tzStr = editUserProfileRequest.getTimeZone();
			tzStr = TimeZoneTools.getTimeZoneIdInJava(tzStr);

			User user = 
				new User(editUserProfileRequest.getUserName(),
						editUserProfileRequest.getPassword(),
						editUserProfileRequest.getFirstName(),
						editUserProfileRequest.getLastName(),
						editUserProfileRequest.getEmailAddress(),
						role,
						tzStr,
						editUserProfileRequest.getContactInfo());
			virtualLabsDB.updateUser(user);

			resp.setSuccess(true);
			resp.setReason(user.toString() + " has been updated");

			createHtpasswd(editUserProfileRequest.getUserName(), editUserProfileRequest.getPassword());

			try {

				if (role != null) {
					
					if ((!oldUser.getUserRole().toUpperCase().equals("MENTOR")) &&
							(user.getUserRole().toUpperCase().equals("MENTOR"))) {

						AddMentorRequest mentorReq = new AddMentorRequest();
						mentorReq.setMentorUsername(user.getUserName());
						AddMentorResponse mentorResp;
						mentorResp = mentorStub.addMentor(mentorReq);

						if (mentorResp.getSuccess()) {

							// TODO 
							/*
							// make the user a master admin in Kaseya
							if (!kaseyaWSClient.addAdminToMasterRole(user.getUserName())) {

								resp.setSuccess(false);
								resp.setReason(
										user.toString() + 
										" has been added/modified in the VirtualLabs DB" +
										" and was added to the MentorScheduler DB," +
										" but it could not become a Master admin in Kaseya! " +
										"The DBs are inconsistent now! " +
								"Please inform the developer!");

							}
							*/

						}
						else {

							resp.setSuccess(false);
							resp.setReason(
									user.toString() + 
									" has been added/modified in the VirtualLabs DB " +
									"but could not be added to the MentorScheduler DB! " +
									"The DBs are inconsistent now! " +
							"Please inform the developer!");

						}

					}
					else if ((oldUser.getUserRole().toUpperCase().equals("MENTOR")) &&
							(!user.getUserRole().toUpperCase().equals("MENTOR"))) {

						DelMentorRequest mentorReq = new DelMentorRequest();
						mentorReq.setMentorUsername(user.getUserName());
						DelMentorResponse mentorResp = mentorStub.delMentor(mentorReq);
						if (mentorResp.getSuccess()) {

							// TODO 
							/*
							// the user should no longer be a Master admin in Kaseya
							if (!kaseyaWSClient.removeAdminFromMasterRole(user.getUserName())) {

								resp.setSuccess(false);
								resp.setReason(
										user.toString() + 
										" has been added/modified in the VirtualLabs DB" +
										" and was deleted from the MentorScheduler DB," +
										" but could not be removed from Master admin role in Kaseya! " +
										"The DBs are inconsistent now! " +
								"Please inform the developer!");

							}
							*/

						}
						else {

							resp.setSuccess(false);
							resp.setReason(
									user.toString() + 
									" has been added/modified in the VirtualLabs DB " +
									"but could not be deleted from the MentorScheduler DB! " +
									"The DBs are inconsistent now! " +
							"Please inform the developer!");

						}

					}

				}
				
				// Change the password, if there is one provided and if it is 
				// different from what was set before.
				if (user.getPassword() != null) {

					// if (user.getPassword().length() > 0) {
						
						// if (!oldUser.getPassword().equals(user.getPassword())) {

							String [] veInsIds = virtualLabsDB.getUserVEInsIds(user.getUserName());
							for (String veInsId : veInsIds) {

								addInitialTasks4NewVMs(veInsId);
								
							}

							// TODO
							// Fix the user password in Kaseya too.
							// kaseyaWSClient.resetPassword(user.getUserName(), user.getPassword());
						// }
					// }
				}

			} catch (Exception e) {

				resp.setSuccess(false);
				resp.setReason(
						user.toString() + 
						" has been added/modified in the VirtualLabs DB " +
						"but could not be deleted/added to the MentorScheduler DB! " +
						"The DBs are inconsistent now! " +
						"Please inform the developer!");

				e.printStackTrace();
			
			}
			
		} catch (Error e) {

			// System.out.println(e.getMessage());
			resp.setSuccess(false);
			resp.setReason(e.getMessage());

		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - editUserProfile] Ready to get out!");

		return resp;

	}

	/**
	 * 
	 * @param editUserProfileWithEncryptedPasswordRequest
	 * @return
	 */
	public EditUserProfileWithEncryptedPasswordResponse editUserProfileWithEncryptedPassword(
			EditUserProfileWithEncryptedPasswordRequest editUserProfileWithEncryptedPasswordRequest) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - editUserProfileWithEncryptedPassword] Inside!");

		EditUserProfileWithEncryptedPasswordResponse resp = new EditUserProfileWithEncryptedPasswordResponse();

		String encryptedPassword = editUserProfileWithEncryptedPasswordRequest.getEncryptedPassword();
		String username = editUserProfileWithEncryptedPasswordRequest.getUserName();
		String password = Crypt.decrypt(encryptedPassword);
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - editUserProfileWithEncryptedPassword] "
				+ "userName: " + editUserProfileWithEncryptedPasswordRequest.getUserName() 
				+ " encryptedPassword: " + encryptedPassword
				+ " password: " + password);
		setUserCachedPassword(username, password);
		
		EditUserProfileRequest wrappedReq = new EditUserProfileRequest();
		wrappedReq.setRequestingUser(editUserProfileWithEncryptedPasswordRequest.getRequestingUser());
		wrappedReq.setUserName(editUserProfileWithEncryptedPasswordRequest.getUserName());
		wrappedReq.setPassword(password);
		wrappedReq.setFirstName(editUserProfileWithEncryptedPasswordRequest.getFirstName());
		wrappedReq.setLastName(editUserProfileWithEncryptedPasswordRequest.getLastName());
		wrappedReq.setEmailAddress(editUserProfileWithEncryptedPasswordRequest.getEmailAddress());
		wrappedReq.setUserRole(editUserProfileWithEncryptedPasswordRequest.getUserRole());
		wrappedReq.setTimeZone(editUserProfileWithEncryptedPasswordRequest.getTimeZone());
		wrappedReq.setContactInfo(editUserProfileWithEncryptedPasswordRequest.getContactInfo());
		EditUserProfileResponse wrappedResp = editUserProfile(wrappedReq);
		
		setUserCachedPassword(username, password);

		resp.setSuccess(wrappedResp.getSuccess());
		resp.setReason(wrappedResp.getReason());

		// passTerminator.notifyAll();

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - editUserProfileWithEncryptedPassword] Ready to get out!");

		return resp;

	}

	/**
	 * 
	 * @param delUserProfileRequest
	 * @return
	 */
	public DelUserProfileResponse delUserProfile(
			DelUserProfileRequest delUserProfileRequest) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - delUserProfile] Inside!");

		DelUserProfileResponse resp = new DelUserProfileResponse();
		
		User user = virtualLabsDB.getUser(delUserProfileRequest.getUserName());
		if (user == null) {
			
			resp.setSuccess(true);
			resp.setReason("User " + delUserProfileRequest.getUserName() +
					" did not exist in VirtualLabs DB!");

			return resp;
			
		}
		
		deleteHtpasswd(user.getUserName());
		
		if (user.getUserRole().toUpperCase().equals("MENTOR")) {

			try {

				DelMentorRequest mentorReq = new DelMentorRequest();
				mentorReq.setMentorUsername(delUserProfileRequest.getUserName());
				DelMentorResponse mentorResp = 
					mentorStub.delMentor(mentorReq);
				if (!mentorResp.getSuccess()) {
					
					resp.setSuccess(mentorResp.getSuccess());
					resp.setReason("Mentor user could not be deleted! " +
							mentorResp.getMessage());
					return resp;
					
				}
			
			} catch (Exception e) {

				e.printStackTrace();
			
				resp.setSuccess(false);
				resp.setReason("Mentor user could not be deleted! " +
						e.getMessage());
				return resp;
				
			}
		}

		// hotfix-DeleteUserMustDeleteVIns
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - delUserProfile] "
				+ "Checking to see if " + user.getUserName() + " is enrolled in any course!");
		Collection<String> userCourses = virtualLabsDB.getCourses(user.getUserName());
		for (Iterator<String> iterator = userCourses.iterator(); iterator.hasNext();) {
	        String userCourse = (String) iterator.next();
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - delUserProfile] "
					+ "Unenrolling " + user.getUserName() + " from " + userCourse + "!");
	        EnrollUserInCourseRequest userCourseUnenrollReq = new EnrollUserInCourseRequest();
	        userCourseUnenrollReq.setUserName(user.getUserName());
	        userCourseUnenrollReq.setCourseName(userCourse);
	        userCourseUnenrollReq.setFlag(false);
	        userCourseUnenrollReq.setRequestingUser("admin");
	        EnrollUserInCourseResponse userCourseUnenrollResp = enrollUserInCourse(userCourseUnenrollReq);
	        if (!userCourseUnenrollResp.getSuccess()) {
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - delUserProfile] "
						+ "Unenrolling " + user.getUserName() + " from " + userCourse + " was not successful!");
	        	resp.setSuccess(false);
				resp.setReason(resp.getReason() + user.getUserName() + " could not be unenrolled from "
						+ userCourse + "! ");
	        } else {
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - delUserProfile] "
						+ "Unenrolling " + user.getUserName() + " from " + userCourse + " was successful!");
	        }
		}
		/*
		String [] veInsIds = virtualLabsDB.getUserVEInsIds(delUserProfileRequest.getUserName());
		for (int i=0; i<veInsIds.length; i++) {
			
			try {

				DelVEInsRequest delVEInsReq = new DelVEInsRequest();
				delVEInsReq.setVeInsId(veInsIds[i]);
				DelVEInsResponse delVEInsResp = veStub.delVEIns(delVEInsReq);
				if (!delVEInsResp.getSuccess()) {
					
					resp.setSuccess(false);
					resp.setReason("VE instances for user " + delUserProfileRequest.getUserName() +
							"cound not be deleted! The DBs may be inconsistent now! " +
							"Plesae inform the developer! \n");
					return resp;
					
				}
			
			} catch (Exception e) {

				e.printStackTrace();
			
				resp.setSuccess(false);
				resp.setReason("VE instances for user " + delUserProfileRequest.getUserName() +
						"cound not be deleted! The DBs may be inconsistent now! " +
						"Plesae inform the developer! \n" +
						e.getMessage());
				return resp;
				
			}
			
			
		}

		// TODO
		// Delete the user from Kaseya too
		// kaseyaWSClient.deleteAdmin(user.getUserName());
		
		*/
		
		if (virtualLabsDB.delUser(delUserProfileRequest.getUserName())) {
			
			resp.setSuccess(true);
			resp.setReason("User " + delUserProfileRequest.getUserName() +
					" has been deleted!");

		} else {

			resp.setSuccess(false);
			if (user.getUserRole().toUpperCase().equals("MENTOR"))
				resp.setReason("User " + delUserProfileRequest.getUserName() +
						" could not be deleted, but it was removed from the " +
						"mentorscheduler DB! The DBs are inconsistent now! " +
						"Plesae inform the developer!");
			else
				resp.setReason("User " + delUserProfileRequest.getUserName() +
				" could not be deleted!");
			
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - delUserProfile] Ready to get out!");

		return resp;

	}

	private void deleteHtpasswd(String userName) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - deleteHtpasswd] Inside!");

		String command = 
			"htpasswd -D .htpasswd " + 
			"'" + userName + "'";
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - deleteHtpasswd ] " +
				"command to be executed: " + command);

		HtpasswdHost htpasswdHost = virtualLabsDB.getFirstActiveHtpasswdHost();
		int wait = 0;
		SSHCommand cmd = 
			new SSHCommand(
					command, 
					htpasswdHost.getName(), 
					htpasswdHost.getSshPort(), 
					htpasswdHost.getUsername(), 
					htpasswdHost.getPassword(),
					wait);
		int retCode = 0;
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - deleteHtpasswd ] " +
				"command that is going to be dispatched: " + command + " " +
				"on host " + htpasswdHost.getName() + " " +
				"and is in the waiting list for " + wait + " seconds!" +
				" retCode is " + retCode);
		retCode = cmd.run();
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - deleteHtpasswd ] " +
				"command that was executed: " + command + " " +
				"on host " + htpasswdHost.getName() + " " +
				" retCode is " + retCode);

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - deleteHtpasswd] Ready to get out!");

	}

	/**
	 * 
	 * @param getUserResourceTypesRequest
	 * @return
	 */
	public GetUserResourceTypesResponse getUserResourceTypes(
			GetUserResourceTypesRequest getUserResourceTypesRequest) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getUserResourceTypes] Inside!");

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

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getUserResourceTypes] Ready to get out!");

		return resp;

	}

	/**
	 * 
	 * @param enrollUserInCourseRequest
	 * @return
	 */
	public EnrollUserInCourseResponse enrollUserInCourse(
			EnrollUserInCourseRequest enrollUserInCourseRequest) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - enrollUserInCourse] Inside!");

		EnrollUserInCourseResponse resp = new EnrollUserInCourseResponse();

		// virtualLabsDB.addCourseVeType(enrollUserInCourseRequest.getCourseName());
		
		try {
			
			String username = enrollUserInCourseRequest.getUserName();
			// clean up 
			if (enrollUserInCourseRequest.getFlag() == false) {
				String courseName = enrollUserInCourseRequest.getCourseName();
				Collection<String> resources = virtualLabsDB.getResources(username);
				for (String resource : resources) {
					for (int i=0; i<2; i++) {
						boolean flag = true;
						if (i == 1)
							flag = false;
						String veInsId = virtualLabsDB.getVEInstanceId(courseName, resource, username, flag);
						if (veInsId != null) {
							DestroyDevaInsRequest desReq = new DestroyDevaInsRequest();
							desReq.setDevaInsId(veInsId);
							DestroyDevaInsResponse desResp = destroyDevaIns(desReq);
							if (!desResp.getSuccess())
								System.out.println("Error: deva instance " + veInsId + " counld not be destroyed!");
						}				

					}
				}
			}
			String message = 
				virtualLabsDB.enrollInCourse(
						username,
						enrollUserInCourseRequest.getCourseName(),
						enrollUserInCourseRequest.getFlag());
			
			User user = virtualLabsDB.getUser(username);
			if (user.getUserRole().toUpperCase().equals("MENTOR")) {
				
				AssignMentorToCourseRequest mentorReq = new AssignMentorToCourseRequest();
				mentorReq.setMentorUsername(username);
				mentorReq.setCourseId(enrollUserInCourseRequest.getCourseName());
				mentorReq.setFlag(enrollUserInCourseRequest.getFlag());
				AssignMentorToCourseResponse mentorResp =
					mentorStub.assignMentorToCourse(mentorReq);
				resp.setSuccess(mentorResp.getSuccess());
				resp.setReason(message + " " + mentorResp.getMessage());
				
			}
			else {
		
				resp.setSuccess(true);
				resp.setReason(message);

			}
			
		} catch (Exception e) {
			
			resp.setSuccess(false);
			resp.setReason(e.getMessage());

		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - enrollUserInCourse] Ready to get out!");

		return resp;

	}

	/**
	 * 
	 * @param suspendUserVLabRequest
	 * @return
	 */
	public SuspendUserVLabResponse suspendUserVLab(
			SuspendUserVLabRequest suspendUserVLabRequest) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - suspendUserVLab] Inside!");

		SuspendUserVLabResponse resp = new SuspendUserVLabResponse();
		resp.setSuccess(true);
		resp.setReason("");

		try {
			
			String username = suspendUserVLabRequest.getUserName();
			String courseName = suspendUserVLabRequest.getCourseName();
			Collection<String> resources = virtualLabsDB.getResources(username);
			for (String resource : resources) {
				for (int i=0; i<2; i++) {
					boolean flag = true;
					if (i == 1)
						flag = false;
					String veInsId = virtualLabsDB.getVEInstanceId(courseName, resource, username, flag);
					if (veInsId != null) {
						GetUserCurAppIdRequest getUserCurAppIdReq = new GetUserCurAppIdRequest();
						getUserCurAppIdReq.setUsername(username);
						getUserCurAppIdReq.setCourse(courseName);
						getUserCurAppIdReq.setResourceType(resource);
						GetUserCurAppIdResponse getUserCurAppIdRes = getUserCurAppId(getUserCurAppIdReq);
						if (getUserCurAppIdRes.getSuccess()) {
							String appId = getUserCurAppIdRes.getAppId();
							
							CancelUserAppointmentRequest cancelUserAppointmentReq = new CancelUserAppointmentRequest();
							cancelUserAppointmentReq.setRequestingUser("admin");
							cancelUserAppointmentReq.setId(appId);
							CancelUserAppointmentResponse cancelUserAppointmentRes = cancelUserAppointment(cancelUserAppointmentReq);
							if (!cancelUserAppointmentRes.getSuccess()) {
								String reason = "Error: User " + username + " current appointment  " + appId + " counld not be cancelled!";
								System.out.println(reason);
								resp.setSuccess(false);
								resp.setReason(resp.getReason() + reason + "\n");								
							}
						}						
						DisableKaseyaAccountRequest disableReq = new DisableKaseyaAccountRequest();
						disableReq.setUsername(username);
						disableReq.setVeInsId(veInsId);
						DisableKaseyaAccountResponse disableResp = veStub.disableKaseyaAccount(disableReq);
						if (!disableResp.getSuccess()) {
							String reason = "Error: Kaseya account for username " + username + " veInsId " + veInsId + " counld not be disabled!";
							System.out.println(reason);
							resp.setSuccess(false);
							resp.setReason(resp.getReason() + reason + "\n");
						}
					}				

				}
			}
						
		} catch (Exception e) {
			
			resp.setSuccess(false);
			resp.setReason(e.getMessage());

		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - suspendUserVLab] Ready to get out!");

		return resp;

	}

	/**
	 * 
	 * @return
	 */
	public GetAvailableTimeZoneIdsResponse getAvailableTimeZoneIds() {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getAvailableTimeZoneIds] Inside!");

		GetAvailableTimeZoneIdsResponse resp = new GetAvailableTimeZoneIdsResponse();

		ArrayList<String> tzids = TimeZoneTools.getAvailableTimeZoneIds();

		for (String tzid : tzids) {

			resp.addTimeZoneId(tzid);

		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getAvailableTimeZoneIds] Ready to get out!");

		return resp;

	}

	/**
	 * 
	 * @param getUserDefaultTimeZoneIdRequest
	 * @return
	 */
	public GetUserDefaultTimeZoneIdResponse getUserDefaultTimeZoneId(
			GetUserDefaultTimeZoneIdRequest getUserDefaultTimeZoneIdRequest) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getUserDefaultTimeZoneId] Inside!");

		GetUserDefaultTimeZoneIdResponse resp = new GetUserDefaultTimeZoneIdResponse();

		User user = virtualLabsDB.getUser(getUserDefaultTimeZoneIdRequest.getUserName());

		if (user != null) {

			String tzStr = user.getTimeZone();

			if (TimeZoneTools.needTimeZoneOffsetStr(tzStr)) {

				tzStr = TimeZoneTools.getTimeZoneOffsetStr(tzStr) + " " + tzStr;		

			}

			resp.setTimeZoneId(tzStr);

		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getUserDefaultTimeZoneId] Ready to get out!");

		return resp;

	}

	/**
	 * 
	 * @param getUserCurrentTimeRequest
	 * @return
	 */
	public GetUserCurrentTimeResponse getUserCurrentTime(
			GetUserCurrentTimeRequest getUserCurrentTimeRequest) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getUserCurrentTime] Inside!");

		GetUserCurrentTimeResponse resp = new GetUserCurrentTimeResponse();

		User user = virtualLabsDB.getUser(getUserCurrentTimeRequest.getUserName());

		if (user != null) {

			String tzStr = user.getTimeZone();
			TimeZone tz = TimeZone.getTimeZone(tzStr);

			Calendar time = Calendar.getInstance();
			time.setTimeZone(tz);
			
			resp.setTime(time);

		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getUserDefaultTimeZoneId] Ready to get out!");

		return resp;

	}

	/**
	 * 
	 * @param getUserProfileByUsernameRequest
	 * @return
	 */
	public GetUserProfileByUsernameResponse getUserProfileByUsername(
			GetUserProfileByUsernameRequest getUserProfileByUsernameRequest) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getUserProfileByUsername] Inside!");

		GetUserProfileByUsernameResponse resp = new GetUserProfileByUsernameResponse();

		User user = virtualLabsDB.getUser(getUserProfileByUsernameRequest.getUserName());
		if (user != null) {
			
			resp.setUserName(user.getUserName());
			resp.setPassword(user.getPassword());
			resp.setFirstName(user.getFirstName());
			resp.setLastName(user.getLastName());
			resp.setEmailAddress(user.getEmailAddress());
			resp.setUserRole(user.getUserRole());
			resp.setTimeZone(user.getTimeZone());
			resp.setContactInfo(user.getContactInfo());
			resp.setSuccess(true);
			resp.setReason("User " + getUserProfileByUsernameRequest.getUserName() +
					" did exist in VirtualLabs DB!");

		} else {
			
			resp.setSuccess(false);
			resp.setReason("User " + getUserProfileByUsernameRequest.getUserName() +
					" did not exist in VirtualLabs DB!");

			return resp;
			
		}
		

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getUserDefaultTimeZoneId] Ready to get out!");

		return resp;

	}

	/**
	 * 
	 * @param getUserProfileByEmailRequest
	 * @return
	 */
	public GetUserProfileByEmailResponse getUserProfileByEmail(
			GetUserProfileByEmailRequest getUserProfileByEmailRequest) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getUserProfileByEmail] Inside!");

		GetUserProfileByEmailResponse resp = new GetUserProfileByEmailResponse();

		User user = virtualLabsDB.getUserByEmail(getUserProfileByEmailRequest.getEmailAddress());
		if (user != null) {
			
			resp.setUserName(user.getUserName());
			resp.setPassword(user.getPassword());
			resp.setFirstName(user.getFirstName());
			resp.setLastName(user.getLastName());
			resp.setEmailAddress(user.getEmailAddress());
			resp.setUserRole(user.getUserRole());
			resp.setTimeZone(user.getTimeZone());
			resp.setContactInfo(user.getContactInfo());
			resp.setSuccess(true);
			resp.setReason("User " + getUserProfileByEmailRequest.getEmailAddress() +
					" did exist in VirtualLabs DB!");

		} else {
			
			resp.setSuccess(false);
			resp.setReason("User " + getUserProfileByEmailRequest.getEmailAddress() +
					" did not exist in VirtualLabs DB!");

			return resp;
			
		}
		

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getUserDefaultTimeZoneId] Ready to get out!");

		return resp;

	}

	public SetUserDefaultTimeZoneIdResponse setUserDefaultTimeZoneId(
			SetUserDefaultTimeZoneIdRequest setUserDefaultTimeZoneIdRequest) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - setUserDefaultTimeZoneId] Inside!");

		SetUserDefaultTimeZoneIdResponse resp = new SetUserDefaultTimeZoneIdResponse();

		User user = virtualLabsDB.getUser(setUserDefaultTimeZoneIdRequest.getUserName());

		if (user != null) {

			String tzStr = setUserDefaultTimeZoneIdRequest.getTimeZoneId();
			tzStr = TimeZoneTools.getTimeZoneIdInJava(tzStr);
			if (tzStr.compareTo(user.getTimeZone()) != 0) {
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getUserDefaultTimeZoneId] "
						+ "time zone " + tzStr + " is different from the user's time zone " + user.getTimeZone());

				user.setTimeZone(tzStr);
				virtualLabsDB.updateUserTimeZone(user);
				resp.setSuccess(true);
				resp.setMessage(user + " has been updated");
			} else {
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getUserDefaultTimeZoneId] "
						+ "time zone " + tzStr + " is the same as user's time zone " + user.getTimeZone());				
			}

		}
		else {

			resp.setSuccess(false);
			resp.setMessage(
					"The username " + 
					setUserDefaultTimeZoneIdRequest.getUserName() + 
			" could not be found in the DB!");

		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getUserDefaultTimeZoneId] Ready to get out!");

		return resp;

	}

	//Administrator functions

	/**
	 * 
	 * @return
	 */
	public GetConfigurationResponse getConfiguration(
			GetConfigurationRequest getConfigurationRequest) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getConfiguration] Inside!");

		GetConfigurationResponse resp = 
			new GetConfigurationResponse();

		try
		{

			edu.fiu.cis.acrl.vescheduler.ws.GetConfigurationResponse  veResp = 
				new edu.fiu.cis.acrl.vescheduler.ws.GetConfigurationResponse();
			veResp = veStub.getConfiguration();

			if(veResp.getSuccess())
			{
				User admin = virtualLabsDB.getUser(getConfigurationRequest.getRequestingUser());
				TimeZone timeZone = TimeZone.getTimeZone(admin.getTimeZone());
				Calendar tempCal = null;
				
				tempCal = veResp.getUserTimePeriod().getStartTime();
				tempCal.getTime();
				tempCal.setTimeZone(timeZone);
				tempCal = TimeZoneTools.changeTimeZone(tempCal, TimeZone.getDefault());
				resp.setUserStartTime(tempCal);	

				tempCal = veResp.getUserTimePeriod().getEndTime();
				tempCal.getTime();
				tempCal.setTimeZone(timeZone);
				tempCal = TimeZoneTools.changeTimeZone(tempCal, TimeZone.getDefault());
				resp.setUserEndTime(tempCal);

				tempCal = veResp.getAdminTimePeriod().getStartTime();
				tempCal.getTime();
				tempCal.setTimeZone(timeZone);
				tempCal = TimeZoneTools.changeTimeZone(tempCal, TimeZone.getDefault());
				resp.setAdminStartTime(tempCal);

				tempCal = veResp.getAdminTimePeriod().getEndTime();
				tempCal.getTime();
				tempCal.setTimeZone(timeZone);
				tempCal = TimeZoneTools.changeTimeZone(tempCal, TimeZone.getDefault());
				resp.setAdminEndTime(tempCal);

				resp.setSuccess(true);

			}else{

				resp.setSuccess(false);
				resp.setMessage(veResp.getMessage());

			}

		}catch(Exception e)
		{
			e.printStackTrace();
			resp.setSuccess(false);
			resp.setMessage(e.getMessage());
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getConfiguration] Ready to get out!");

		return resp;
	}

	/**
	 * 
	 * @param setConfigurationRequest
	 * @return
	 */
	public SetConfigurationResponse setConfiguration(
			SetConfigurationRequest setConfigurationRequest) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - setConfiguration] Inside!");

		SetConfigurationResponse resp = 
			new SetConfigurationResponse();

		try
		{

			edu.fiu.cis.acrl.vescheduler.ws.SetConfigurationRequest veReq = 
				new edu.fiu.cis.acrl.vescheduler.ws.SetConfigurationRequest();

			User admin = virtualLabsDB.getUser(setConfigurationRequest.getRequestingUser());
			TimeZone timeZone = TimeZone.getTimeZone(admin.getTimeZone());
			
			TimePeriod userTimePeriod = new TimePeriod();
			userTimePeriod.setStartTime(
					TimeZoneTools.changeTimeZone(
							setConfigurationRequest.getUserStartTime(),
							timeZone));
			userTimePeriod.setEndTime(
					TimeZoneTools.changeTimeZone(
							setConfigurationRequest.getUserEndTime(),
							timeZone));
			veReq.setUserTimePeriod(userTimePeriod);

			TimePeriod adminTimePeriod = new TimePeriod();
			adminTimePeriod.setStartTime(
					TimeZoneTools.changeTimeZone(
							setConfigurationRequest.getAdminStartTime(),
							timeZone));
			adminTimePeriod.setEndTime(
					TimeZoneTools.changeTimeZone(
							setConfigurationRequest.getAdminEndTime(),
							timeZone));
			veReq.setAdminTimePeriod(adminTimePeriod);

			edu.fiu.cis.acrl.vescheduler.ws.SetConfigurationResponse veResp = 
				veStub.setConfiguration(veReq);

			edu.fiu.cis.acrl.mentorscheduler.ws.SetConfigurationRequest mentorReq = 
				new edu.fiu.cis.acrl.mentorscheduler.ws.SetConfigurationRequest();

			mentorReq.setUserTimePeriod(userTimePeriod);
			mentorReq.setAdminTimePeriod(adminTimePeriod);

			edu.fiu.cis.acrl.mentorscheduler.ws.SetConfigurationResponse mentorResp = 
				mentorStub.setConfiguration(mentorReq);

			resp.setSuccess(veResp.getSuccess());
			resp.setMessage(veResp.getMessage());

		}catch(Exception e)
		{
			e.printStackTrace();
			resp.setSuccess(false);
			resp.setMessage(e.getMessage());
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - setConfiguration] Ready to get out!");

		return resp;

	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	public GetHostListResponse getHostList(GetHostListRequest request) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getHostList] Inside!");

		GetHostListResponse resp = new  GetHostListResponse();

		try {

			edu.fiu.cis.acrl.vescheduler.ws.GetHostListRequest veReq =
				new edu.fiu.cis.acrl.vescheduler.ws.GetHostListRequest();
			veReq.setActive(request.getActive());
			edu.fiu.cis.acrl.vescheduler.ws.GetHostListResponse veResp = 
				veStub.getHostList(veReq);

			if (veResp.getSuccess()) {

				edu.fiu.cis.acrl.vescheduler.ws.Host[] veHosts = veResp.getHost();

				if (veHosts != null) {

					Host[] hosts = new Host[veHosts.length];

					for(int i=0; i<veHosts.length; i++) {

						edu.fiu.cis.acrl.vescheduler.ws.Host veHost = veHosts[i];
						hosts[i] = new Host();                 		
						hosts[i].setId(veHost.getId());
						hosts[i].setName(veHost.getName());
						hosts[i].setSshPort(veHost.getSshPort());
						hosts[i].setUsername(veHost.getUsername());
						hosts[i].setPassword(veHost.getPassword());
						hosts[i].setVeNumCap(veHost.getVeNumCap());
						hosts[i].setVeFirstFreePort(veHost.getVeFirstFreePort());
						hosts[i].setVePortNum(veHost.getVePortNum());
						hosts[i].setActive(veHost.getActive());

					}

					resp.setHost(hosts);
					// resp.setSuccess(veResp.getSuccess());
					// resp.setMessage(veResp.getMessage());

				}
				else {

					resp.addHost(null);
					// resp.setSuccess(veResp.getSuccess());
					// resp.setMessage(veResp.getMessage() + " List is empty!");

				}

			}

		} catch(Exception e) {

			e.printStackTrace();
			// resp.setSuccess(false);
			// resp.setMessage(e.getMessage());

		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getHostList] Ready to get out!");

		return resp;

	}

	/**
	 * 
	 * @param delHostRequest
	 * @return
	 */
	public DelHostResponse delHost(DelHostRequest delHostRequest) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - delHost] Inside!");

		DelHostResponse resp =
			new  DelHostResponse();

		try
		{

			edu.fiu.cis.acrl.vescheduler.ws.DelHostRequest req = 
				new edu.fiu.cis.acrl.vescheduler.ws.DelHostRequest();
			req.setHostId(delHostRequest.getId());

			edu.fiu.cis.acrl.vescheduler.ws.DelHostResponse veResp = veStub.delHost(req);

			resp.setSuccess(veResp.getSuccess());
			resp.setMessage(veResp.getMessage());

		}catch(Exception e)
		{
			e.printStackTrace();
			resp.setSuccess(false);
			resp.setMessage(e.getMessage());
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - delHost] Ready to get out!");

		return resp;

	}

	/**
	 * 
	 * @param getHostRequest
	 * @return
	 */
	public GetHostResponse getHost(GetHostRequest getHostRequest) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getHost] Inside!");

		GetHostResponse resp = new  GetHostResponse();

		try {

			edu.fiu.cis.acrl.vescheduler.ws.GetHostRequest req = 
				new edu.fiu.cis.acrl.vescheduler.ws.GetHostRequest();
			req.setHostId(getHostRequest.getId());

			edu.fiu.cis.acrl.vescheduler.ws.GetHostResponse veResp = 
				veStub.getHost(req);

			DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getHost] " +
					"veResp.getHost(): " + veResp.getHost());

			if(veResp.getSuccess()) {

				edu.fiu.cis.acrl.vescheduler.ws.Host host = veResp.getHost();
				Host hostResp = new Host();
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

			resp.setSuccess(veResp.getSuccess());
			resp.setMessage(veResp.getMessage());

		} 
		catch(Exception e) {

			e.printStackTrace();
			resp.setSuccess(false);
			resp.setMessage(e.getMessage());

		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - getHost] Ready to get out!");

		return resp;

	}

	/**
	 * 
	 * @param setHostRequest
	 * @return
	 */
	public SetHostResponse setHost(SetHostRequest setHostRequest) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - setHost] Inside!");

		SetHostResponse resp =
			new  SetHostResponse();

		try
		{

			edu.fiu.cis.acrl.vescheduler.ws.SetHostRequest veReq = 
				new edu.fiu.cis.acrl.vescheduler.ws.SetHostRequest();
			edu.fiu.cis.acrl.vescheduler.ws.Host veHost = 
				new edu.fiu.cis.acrl.vescheduler.ws.Host();
			Host host = 
				setHostRequest.getHost();

			veHost.setId(host.getId());
			veHost.setName(host.getName());
			veHost.setSshPort(host.getSshPort());
			veHost.setUsername(host.getUsername());
			veHost.setPassword(host.getPassword());
			veHost.setVeNumCap(host.getVeNumCap());
			veHost.setVeFirstFreePort(host.getVeFirstFreePort());
			veHost.setVePortNum(host.getVePortNum());
			veHost.setActive(host.getActive());
			veReq.setHost(veHost);


			edu.fiu.cis.acrl.vescheduler.ws.SetHostResponse veResp = 
				veStub.setHost(veReq);

			resp.setSuccess(veResp.getSuccess());
			resp.setMessage(veResp.getMessage());

		}catch(Exception e)
		{
			e.printStackTrace();
			resp.setSuccess(false);
			resp.setMessage(e.getMessage());
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - setHost] Ready to get out!");

		return resp;

	}

	/**
	 * 
	 * @param addHostRequest
	 * @return
	 */
	public AddHostResponse addHost
	(
			AddHostRequest addHostRequest
	)
	{

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addHostRequest] Inside!");

		AddHostResponse resp =
			new  AddHostResponse();

		try
		{

			edu.fiu.cis.acrl.vescheduler.ws.AddHostRequest veReq = 
				new edu.fiu.cis.acrl.vescheduler.ws.AddHostRequest();
			edu.fiu.cis.acrl.vescheduler.ws.Host veHost = 
				new edu.fiu.cis.acrl.vescheduler.ws.Host();
			Host host = 
				addHostRequest.getHost();

			veHost.setId(host.getId());
			veHost.setName(host.getName());
			veHost.setSshPort(host.getSshPort());
			veHost.setUsername(host.getUsername());
			veHost.setPassword(host.getPassword());
			veHost.setVeNumCap(host.getVeNumCap());
			veHost.setVeFirstFreePort(host.getVeFirstFreePort());
			veHost.setVePortNum(host.getVePortNum());
			veHost.setActive(host.getActive());
			veReq.setHost(veHost);

			edu.fiu.cis.acrl.vescheduler.ws.AddHostResponse veResp = 
				veStub.addHost(veReq);

			resp.setSuccess(veResp.getSuccess());
			resp.setMessage(veResp.getMessage());

		}catch(Exception e)
		{
			e.printStackTrace();
			resp.setSuccess(false);
			resp.setMessage(e.getMessage());
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - addHostRequest] Ready to get out!");

		return resp;
	}

}