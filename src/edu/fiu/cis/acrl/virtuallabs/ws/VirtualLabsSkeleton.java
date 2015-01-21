package edu.fiu.cis.acrl.virtuallabs.ws;

import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.description.AxisService;
import org.apache.axis2.engine.ServiceLifeCycle;

import edu.fiu.cis.acrl.virtuallabs.ws.AddCourseRequest;
import edu.fiu.cis.acrl.virtuallabs.ws.AddHostRequest;
import edu.fiu.cis.acrl.virtuallabs.ws.AddHostResponse;
import edu.fiu.cis.acrl.virtuallabs.ws.CancelAllUserAppointmentsRequest;
import edu.fiu.cis.acrl.virtuallabs.ws.CancelUserAppointmentRequest;
import edu.fiu.cis.acrl.virtuallabs.ws.CancelUserAppointmentResponse;
import edu.fiu.cis.acrl.virtuallabs.ws.CourseNotFoundFault;
import edu.fiu.cis.acrl.virtuallabs.ws.DelHostRequest;
import edu.fiu.cis.acrl.virtuallabs.ws.DelHostResponse;
import edu.fiu.cis.acrl.virtuallabs.ws.DeleteCourseRequest;
import edu.fiu.cis.acrl.virtuallabs.ws.DuplicateCourseFault;
import edu.fiu.cis.acrl.virtuallabs.ws.GetConfigurationResponse;
import edu.fiu.cis.acrl.virtuallabs.ws.GetHostListRequest;
import edu.fiu.cis.acrl.virtuallabs.ws.GetHostListResponse;
import edu.fiu.cis.acrl.virtuallabs.ws.GetHostRequest;
import edu.fiu.cis.acrl.virtuallabs.ws.GetHostResponse;
import edu.fiu.cis.acrl.virtuallabs.ws.InvalidDataFault;
import edu.fiu.cis.acrl.virtuallabs.ws.ModifyCourseRequest;
import edu.fiu.cis.acrl.virtuallabs.ws.ModifyUserAppointmentRequest;
import edu.fiu.cis.acrl.virtuallabs.ws.ModifyUserAppointmentResponse;
import edu.fiu.cis.acrl.virtuallabs.ws.ExtendUserAppointmentRequest;
import edu.fiu.cis.acrl.virtuallabs.ws.ExtendUserAppointmentResponse;
import edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsRequest;
import edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsResponse;
import edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsWithEncryptedPasswordRequest;
import edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsWithEncryptedPasswordResponse;
import edu.fiu.cis.acrl.virtuallabs.ws.SetConfigurationRequest;
import edu.fiu.cis.acrl.virtuallabs.ws.SetConfigurationResponse;
import edu.fiu.cis.acrl.virtuallabs.ws.SetHostRequest;
import edu.fiu.cis.acrl.virtuallabs.ws.SetHostResponse;
import edu.fiu.cis.acrl.virtuallabs.server.Course;
import edu.fiu.cis.acrl.virtuallabs.server.VirtualLabs;
import edu.fiu.cis.acrl.virtuallabs.server.tools.debug.DebugTools;

public class VirtualLabsSkeleton implements ServiceLifeCycle {

	private VirtualLabs virtualLabs = null;
	
	public VirtualLabsSkeleton() {
	
		virtualLabs = VirtualLabs.instance();
		
	}
    
    public void shutDown(ConfigurationContext arg0, AxisService arg1) {
		
		virtualLabs.shutDown();
		
	}

	public void startUp(ConfigurationContext arg0, AxisService arg1) {
		
		virtualLabs.startUp();
		
	}

	public void addCourse(
			AddCourseRequest addCourseRequest)
			throws DuplicateCourseFault, InvalidDataFault {

		virtualLabs.addCourse(addCourseRequest);

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

		virtualLabs.modifyCourse(modifyCourseRequest);

	}

	public void deleteCourse(
			DeleteCourseRequest deleteCourseRequest)
			throws CourseNotFoundFault {

		virtualLabs.deleteCourse(deleteCourseRequest);
		
	}

	public AddInitialTasks4NewVMsResponse addInitialTasks4NewVMs(
			AddInitialTasks4NewVMsRequest addInitialTasks4NewVMsRequest) {
		
		return virtualLabs.addInitialTasks4NewVMs(addInitialTasks4NewVMsRequest);
		
	}
	
	public AddInitialTasks4NewVMsWithEncryptedPasswordResponse addInitialTasks4NewVMsWithEncryptedPassword(
			AddInitialTasks4NewVMsWithEncryptedPasswordRequest addInitialTasks4NewVMsWithEncryptedPasswordRequest) {
		
		return virtualLabs.addInitialTasks4NewVMsWithEncryptedPassword(addInitialTasks4NewVMsWithEncryptedPasswordRequest);
		
	}
	
	public RefreshVMResponse refreshVM(
			RefreshVMRequest refreshVMRequest) {
		
		return virtualLabs.refreshVM(refreshVMRequest);
		
	}
	
	public RefreshVMWithEncryptedPasswordResponse refreshVMWithEncryptedPassword(
			RefreshVMWithEncryptedPasswordRequest refreshVMWithEncryptedPasswordRequest) {
		
		return virtualLabs.refreshVMWithEncryptedPassword(refreshVMWithEncryptedPasswordRequest);
		
	}
	
	public RunVMCmdResponse runVMCmd(
			RunVMCmdRequest runVMCmdRequest) {
		
		return virtualLabs.runVMCmd(runVMCmdRequest);
		
	}
	
	public IsRDPReadyResponse isRDPReady(
			IsRDPReadyRequest isRDPReadyRequest) {
		
		return virtualLabs.isRDPReady(isRDPReadyRequest);
		
	}
	
	public DestroyDevaInsResponse destroyDevaIns(
			DestroyDevaInsRequest destroyDevaInsRequest) {
		
		return virtualLabs.destroyDevaIns(destroyDevaInsRequest);
		
	}
	
	public GetDevaInsInfoResponse getDevaInsInfo(
			GetDevaInsInfoRequest getDevaInsInfoRequest) {
		
		return virtualLabs.getDevaInsInfo(getDevaInsInfoRequest);
		
	}
	
	public GetUserCurAppIdResponse getUserCurAppId(
			GetUserCurAppIdRequest getUserCurAppIdRequest) {
		
		return virtualLabs.getUserCurAppId(getUserCurAppIdRequest);
		
	}
	
	public GetEndDate4CurrentDevaInsResponse getEndDate4CurrentDevaIns(
			GetEndDate4CurrentDevaInsRequest getEndDate4CurrentDevaInsRequest) {
		
		return virtualLabs.getEndDate4CurrentDevaIns(getEndDate4CurrentDevaInsRequest);
		
	}
	
	public GetVMInsInfoResponse getVMInsInfo(
			GetVMInsInfoRequest getVMInsInfoRequest) {
		
		return virtualLabs.getVMInsInfo(getVMInsInfoRequest);
		
	}
	
	public IsResourceAvailableResponse isResourceAvailable(
			IsResourceAvailableRequest isResourceAvailableRequest) {
		
		return virtualLabs.isResourceAvailable(isResourceAvailableRequest);
		
	}
	
	public ReserveResourceResponse reserveResource(
			ReserveResourceRequest reserveResourceRequest) {
		
		return virtualLabs.reserveResource(reserveResourceRequest);
		
	}
	
	public GetResourceTypesResponse getResourceTypes(
			GetResourceTypesRequest getResourceTypesRequest) {
		
		return virtualLabs.getResourceTypes(getResourceTypesRequest);
		
	}
	
	public CheckinResponse checkin(
			CheckinRequest checkinRequest) {
	
		return virtualLabs.checkin(checkinRequest);
		
	}

	public GetCheckinIntervalResponse getCheckinInterval(
			GetCheckinIntervalRequest getCheckinIntervalRequest) {
	
		return virtualLabs.getCheckinInterval(getCheckinIntervalRequest);
		
	}

	public GetUserCredentialsResponse getUserCredentials(
			GetUserCredentialsRequest getUserCredentialsRequest) {
	
		return virtualLabs.getUserCredentials(getUserCredentialsRequest);
		
	}

	public GetUserAppointmentsResponse getUserAppointments(
			GetUserAppointmentsRequest getUserAppointmentsRequest) {
	
		return virtualLabs.getUserAppointments(getUserAppointmentsRequest);
		
	}

	public ScheduleUserAppointmentsResponse scheduleUserAppointments(
			ScheduleUserAppointmentsRequest scheduleUserAppointmentsRequest) {
	
		return virtualLabs.scheduleUserAppointments(scheduleUserAppointmentsRequest);
		
	}

	public ScheduleUserAppointmentsWithEncryptedPasswordResponse scheduleUserAppointmentsWithEncryptedPassword(
			ScheduleUserAppointmentsWithEncryptedPasswordRequest scheduleUserAppointmentsWithEncryptedPasswordRequest) {
	
		return virtualLabs.scheduleUserAppointmentsWithEncryptedPassword(scheduleUserAppointmentsWithEncryptedPasswordRequest);
		
	}

	public ScheduleCourseAppointmentResponse scheduleCourseAppointment(
			ScheduleCourseAppointmentRequest scheduleCourseAppointmentRequest) {
	
		return virtualLabs.scheduleCourseAppointment(scheduleCourseAppointmentRequest);
		
	}

	public ModifyUserAppointmentResponse modifyUserAppointment(
			ModifyUserAppointmentRequest modifyUserAppointmentRequest) {
	
		return virtualLabs.modifyUserAppointment(modifyUserAppointmentRequest);
		
	}

	public ExtendUserAppointmentResponse extendUserAppointment(
			ExtendUserAppointmentRequest extendUserAppointmentRequest) {
	
		return virtualLabs.extendUserAppointment(extendUserAppointmentRequest);
		
	}

	public CancelUserAppointmentResponse cancelUserAppointment(
			CancelUserAppointmentRequest cancelUserAppointmentRequest) {
		
		return virtualLabs.cancelUserAppointment(cancelUserAppointmentRequest);
		
	}

	public CancelAllUserAppointmentsResponse cancelAllUserAppointments(
			CancelAllUserAppointmentsRequest cancelAllUserAppointmentsRequest) {

		return virtualLabs.cancelAllUserAppointments(cancelAllUserAppointmentsRequest);
		
	}

	public GetMentorAppointmentsResponse getMentorAppointments(
			GetMentorAppointmentsRequest getMentorAppointmentsRequest) {
	
		return virtualLabs.getMentorAppointments(getMentorAppointmentsRequest);
		
	}

	public ScheduleMentorAppointmentsResponse scheduleMentorAppointments(
			ScheduleMentorAppointmentsRequest scheduleMentorAppointmentsRequest) {
	
		return virtualLabs.scheduleMentorAppointments(scheduleMentorAppointmentsRequest);
		
	}

	public ModifyMentorAppointmentResponse modifyMentorAppointment(
			ModifyMentorAppointmentRequest modifyMentorAppointmentRequest) {
	
		return virtualLabs.modifyMentorAppointment(modifyMentorAppointmentRequest);
		
	}

	public CancelMentorAppointmentResponse cancelMentorAppointment(
			CancelMentorAppointmentRequest cancelMentorAppointmentRequest) {
		
		return virtualLabs.cancelMentorAppointment(cancelMentorAppointmentRequest);
		
	}

	public CancelAllMentorAppointmentsResponse cancelAllMentorAppointments(
			CancelAllMentorAppointmentsRequest cancelAllMentorAppointmentsRequest) {

		return virtualLabs.cancelAllMentorAppointments(cancelAllMentorAppointmentsRequest);
		
	}

	public GetHostAppointmentsResponse getHostAppointments(
			GetHostAppointmentsRequest getHostAppointmentsRequest) {
	
		return virtualLabs.getHostAppointments(getHostAppointmentsRequest);
		
	}

	public ScheduleHostAppointmentsResponse scheduleHostAppointments(
			ScheduleHostAppointmentsRequest scheduleHostAppointmentsRequest) {
	
		return virtualLabs.scheduleHostAppointments(scheduleHostAppointmentsRequest);
		
	}

	public ModifyHostAppointmentResponse modifyHostAppointment(
			ModifyHostAppointmentRequest modifyHostAppointmentRequest) {
	
		return virtualLabs.modifyHostAppointment(modifyHostAppointmentRequest);
		
	}

	public CancelHostAppointmentResponse cancelHostAppointment(
			CancelHostAppointmentRequest cancelHostAppointmentRequest) {
		
		return virtualLabs.cancelHostAppointment(cancelHostAppointmentRequest);
		
	}

	public CancelAllHostAppointmentsResponse cancelAllHostAppointments(
			CancelAllHostAppointmentsRequest cancelAllHostAppointmentsRequest) {

		return virtualLabs.cancelAllHostAppointments(cancelAllHostAppointmentsRequest);
		
	}

	public EditUserProfileResponse editUserProfile(
			EditUserProfileRequest editUserProfileRequest) {

		return virtualLabs.editUserProfile(editUserProfileRequest);
		
	}
	
	public EditUserProfileWithEncryptedPasswordResponse editUserProfileWithEncryptedPassword(
			EditUserProfileWithEncryptedPasswordRequest editUserProfileWithEncryptedPasswordRequest) {

		return virtualLabs.editUserProfileWithEncryptedPassword(editUserProfileWithEncryptedPasswordRequest);
		
	}
	
	public DelUserProfileResponse delUserProfile(
			DelUserProfileRequest delUserProfileRequest) {

		return virtualLabs.delUserProfile(delUserProfileRequest);
		
	}
	
	public GetUserResourceTypesResponse getUserResourceTypes(
			GetUserResourceTypesRequest getUserResourceTypesRequest) {

		return virtualLabs.getUserResourceTypes(getUserResourceTypesRequest);
		
	}
	
	public EnrollUserInCourseResponse enrollUserInCourse(
			EnrollUserInCourseRequest enrollUserInCourseRequest) {

		return virtualLabs.enrollUserInCourse(enrollUserInCourseRequest);
		
	}
	
	public SuspendUserVLabResponse suspendUserVLab(
			SuspendUserVLabRequest suspendUserVLabRequest) {

		return virtualLabs.suspendUserVLab(suspendUserVLabRequest);
		
	}
	
	public GetAvailableActionsResponse getAvailableActions(
			GetAvailableActionsRequest getAvailableActionsRequest) {

		return virtualLabs.getAvailableActions(getAvailableActionsRequest);
		
	}
	
	public CreateUserProfileResponse createUserProfile(
			CreateUserProfileRequest createUserProfileRequest) {

		return virtualLabs.createUserProfile(createUserProfileRequest);
		
	}
	
	public CreateUserProfileWithEncryptedPasswordResponse createUserProfileWithEncryptedPassword(
			CreateUserProfileWithEncryptedPasswordRequest createUserProfileWithEncryptedPasswordRequest) {

		return virtualLabs.createUserProfileWithEncryptedPassword(createUserProfileWithEncryptedPasswordRequest);
		
	}
	
	public GetAvailableTimeZoneIdsResponse getAvailableTimeZoneIds() {

		return virtualLabs.getAvailableTimeZoneIds();
		
	}
	
	public GetUserDefaultTimeZoneIdResponse getUserDefaultTimeZoneId(
			GetUserDefaultTimeZoneIdRequest getUserDefaultTimeZoneIdRequest) {
		
		return virtualLabs.getUserDefaultTimeZoneId(getUserDefaultTimeZoneIdRequest);
		
	}
	
	public GetUserCurrentTimeResponse getUserCurrentTime(
			GetUserCurrentTimeRequest getUserCurrentTimeRequest) {
		
		return virtualLabs.getUserCurrentTime(getUserCurrentTimeRequest);
		
	}
	         
	public GetUserProfileByUsernameResponse getUserProfileByUsername(
			GetUserProfileByUsernameRequest getUserProfileByUsernameRequest) {
		
		return virtualLabs.getUserProfileByUsername(getUserProfileByUsernameRequest);
		
	}
	         
	public GetUserProfileByEmailResponse getUserProfileByEmail(
			GetUserProfileByEmailRequest getUserProfileByEmailRequest) {
		
		return virtualLabs.getUserProfileByEmail(getUserProfileByEmailRequest);
		
	}
	         
	public SetUserDefaultTimeZoneIdResponse setUserDefaultTimeZoneId(
			SetUserDefaultTimeZoneIdRequest setUserDefaultTimeZoneIdRequest) {
		
		return virtualLabs.setUserDefaultTimeZoneId(setUserDefaultTimeZoneIdRequest);
		
	}
         
	public DelHostResponse delHost(
			DelHostRequest delHostRequest) {

		return virtualLabs.delHost(delHostRequest);
		
	}
	

	public GetHostResponse getHost(
			GetHostRequest getHostRequest) {

		return virtualLabs.getHost(getHostRequest);
		
	}
	
    public SetHostResponse setHost(
    		SetHostRequest setHostRequest) {

		return virtualLabs.setHost(setHostRequest);
		
	}
	

    public AddHostResponse addHost(
    		AddHostRequest addHostRequest) {

		return virtualLabs.addHost(addHostRequest);
		
	}
	

    public GetHostListResponse getHostList(GetHostListRequest request) {

		return virtualLabs.getHostList(request);
		
	}
	

    public SetConfigurationResponse setConfiguration(
    		SetConfigurationRequest setConfigurationRequest) {

		return virtualLabs.setConfiguration(setConfigurationRequest);
		
	}
	

    public GetConfigurationResponse getConfiguration(
    		GetConfigurationRequest getConfigurationRequest) {

		return virtualLabs.getConfiguration(getConfigurationRequest);
		
	}
	
}
    