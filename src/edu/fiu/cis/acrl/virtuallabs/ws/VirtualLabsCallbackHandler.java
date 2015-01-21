
/**
 * VirtualLabsCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

    package edu.fiu.cis.acrl.virtuallabs.ws;

    /**
     *  VirtualLabsCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class VirtualLabsCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public VirtualLabsCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public VirtualLabsCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for setConfiguration method
            * override this method for handling normal response from setConfiguration operation
            */
           public void receiveResultsetConfiguration(
                    edu.fiu.cis.acrl.virtuallabs.ws.SetConfigurationResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from setConfiguration operation
           */
            public void receiveErrorsetConfiguration(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getHost method
            * override this method for handling normal response from getHost operation
            */
           public void receiveResultgetHost(
                    edu.fiu.cis.acrl.virtuallabs.ws.GetHostResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getHost operation
           */
            public void receiveErrorgetHost(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for modifyUserAppointment method
            * override this method for handling normal response from modifyUserAppointment operation
            */
           public void receiveResultmodifyUserAppointment(
                    edu.fiu.cis.acrl.virtuallabs.ws.ModifyUserAppointmentResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from modifyUserAppointment operation
           */
            public void receiveErrormodifyUserAppointment(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getUserProfileByUsername method
            * override this method for handling normal response from getUserProfileByUsername operation
            */
           public void receiveResultgetUserProfileByUsername(
                    edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByUsernameResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getUserProfileByUsername operation
           */
            public void receiveErrorgetUserProfileByUsername(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getUserDefaultTimeZoneId method
            * override this method for handling normal response from getUserDefaultTimeZoneId operation
            */
           public void receiveResultgetUserDefaultTimeZoneId(
                    edu.fiu.cis.acrl.virtuallabs.ws.GetUserDefaultTimeZoneIdResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getUserDefaultTimeZoneId operation
           */
            public void receiveErrorgetUserDefaultTimeZoneId(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for setUserDefaultTimeZoneId method
            * override this method for handling normal response from setUserDefaultTimeZoneId operation
            */
           public void receiveResultsetUserDefaultTimeZoneId(
                    edu.fiu.cis.acrl.virtuallabs.ws.SetUserDefaultTimeZoneIdResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from setUserDefaultTimeZoneId operation
           */
            public void receiveErrorsetUserDefaultTimeZoneId(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for modifyMentorAppointment method
            * override this method for handling normal response from modifyMentorAppointment operation
            */
           public void receiveResultmodifyMentorAppointment(
                    edu.fiu.cis.acrl.virtuallabs.ws.ModifyMentorAppointmentResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from modifyMentorAppointment operation
           */
            public void receiveErrormodifyMentorAppointment(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for createUserProfileWithEncryptedPassword method
            * override this method for handling normal response from createUserProfileWithEncryptedPassword operation
            */
           public void receiveResultcreateUserProfileWithEncryptedPassword(
                    edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileWithEncryptedPasswordResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from createUserProfileWithEncryptedPassword operation
           */
            public void receiveErrorcreateUserProfileWithEncryptedPassword(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for runVMCmd method
            * override this method for handling normal response from runVMCmd operation
            */
           public void receiveResultrunVMCmd(
                    edu.fiu.cis.acrl.virtuallabs.ws.RunVMCmdResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from runVMCmd operation
           */
            public void receiveErrorrunVMCmd(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for extendUserAppointment method
            * override this method for handling normal response from extendUserAppointment operation
            */
           public void receiveResultextendUserAppointment(
                    edu.fiu.cis.acrl.virtuallabs.ws.ExtendUserAppointmentResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from extendUserAppointment operation
           */
            public void receiveErrorextendUserAppointment(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addInitialTasks4NewVMsWithEncryptedPassword method
            * override this method for handling normal response from addInitialTasks4NewVMsWithEncryptedPassword operation
            */
           public void receiveResultaddInitialTasks4NewVMsWithEncryptedPassword(
                    edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsWithEncryptedPasswordResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addInitialTasks4NewVMsWithEncryptedPassword operation
           */
            public void receiveErroraddInitialTasks4NewVMsWithEncryptedPassword(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for modifyHostAppointment method
            * override this method for handling normal response from modifyHostAppointment operation
            */
           public void receiveResultmodifyHostAppointment(
                    edu.fiu.cis.acrl.virtuallabs.ws.ModifyHostAppointmentResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from modifyHostAppointment operation
           */
            public void receiveErrormodifyHostAppointment(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for isRDPReady method
            * override this method for handling normal response from isRDPReady operation
            */
           public void receiveResultisRDPReady(
                    edu.fiu.cis.acrl.virtuallabs.ws.IsRDPReadyResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from isRDPReady operation
           */
            public void receiveErrorisRDPReady(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for modifyCourse method
            * override this method for handling normal response from modifyCourse operation
            */
           public void receiveResultmodifyCourse(
                    ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from modifyCourse operation
           */
            public void receiveErrormodifyCourse(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for setHost method
            * override this method for handling normal response from setHost operation
            */
           public void receiveResultsetHost(
                    edu.fiu.cis.acrl.virtuallabs.ws.SetHostResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from setHost operation
           */
            public void receiveErrorsetHost(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for scheduleMentorAppointments method
            * override this method for handling normal response from scheduleMentorAppointments operation
            */
           public void receiveResultscheduleMentorAppointments(
                    edu.fiu.cis.acrl.virtuallabs.ws.ScheduleMentorAppointmentsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from scheduleMentorAppointments operation
           */
            public void receiveErrorscheduleMentorAppointments(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cancelAllHostAppointments method
            * override this method for handling normal response from cancelAllHostAppointments operation
            */
           public void receiveResultcancelAllHostAppointments(
                    edu.fiu.cis.acrl.virtuallabs.ws.CancelAllHostAppointmentsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cancelAllHostAppointments operation
           */
            public void receiveErrorcancelAllHostAppointments(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for destroyDevaIns method
            * override this method for handling normal response from destroyDevaIns operation
            */
           public void receiveResultdestroyDevaIns(
                    edu.fiu.cis.acrl.virtuallabs.ws.DestroyDevaInsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from destroyDevaIns operation
           */
            public void receiveErrordestroyDevaIns(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getUserProfileByEmail method
            * override this method for handling normal response from getUserProfileByEmail operation
            */
           public void receiveResultgetUserProfileByEmail(
                    edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByEmailResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getUserProfileByEmail operation
           */
            public void receiveErrorgetUserProfileByEmail(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for delHost method
            * override this method for handling normal response from delHost operation
            */
           public void receiveResultdelHost(
                    edu.fiu.cis.acrl.virtuallabs.ws.DelHostResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from delHost operation
           */
            public void receiveErrordelHost(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for scheduleUserAppointments method
            * override this method for handling normal response from scheduleUserAppointments operation
            */
           public void receiveResultscheduleUserAppointments(
                    edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from scheduleUserAppointments operation
           */
            public void receiveErrorscheduleUserAppointments(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getUserCredentials method
            * override this method for handling normal response from getUserCredentials operation
            */
           public void receiveResultgetUserCredentials(
                    edu.fiu.cis.acrl.virtuallabs.ws.GetUserCredentialsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getUserCredentials operation
           */
            public void receiveErrorgetUserCredentials(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for scheduleHostAppointments method
            * override this method for handling normal response from scheduleHostAppointments operation
            */
           public void receiveResultscheduleHostAppointments(
                    edu.fiu.cis.acrl.virtuallabs.ws.ScheduleHostAppointmentsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from scheduleHostAppointments operation
           */
            public void receiveErrorscheduleHostAppointments(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for delUserProfile method
            * override this method for handling normal response from delUserProfile operation
            */
           public void receiveResultdelUserProfile(
                    edu.fiu.cis.acrl.virtuallabs.ws.DelUserProfileResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from delUserProfile operation
           */
            public void receiveErrordelUserProfile(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for refreshVMWithEncryptedPassword method
            * override this method for handling normal response from refreshVMWithEncryptedPassword operation
            */
           public void receiveResultrefreshVMWithEncryptedPassword(
                    edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMWithEncryptedPasswordResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from refreshVMWithEncryptedPassword operation
           */
            public void receiveErrorrefreshVMWithEncryptedPassword(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for scheduleCourseAppointment method
            * override this method for handling normal response from scheduleCourseAppointment operation
            */
           public void receiveResultscheduleCourseAppointment(
                    edu.fiu.cis.acrl.virtuallabs.ws.ScheduleCourseAppointmentResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from scheduleCourseAppointment operation
           */
            public void receiveErrorscheduleCourseAppointment(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getDevaInsInfo method
            * override this method for handling normal response from getDevaInsInfo operation
            */
           public void receiveResultgetDevaInsInfo(
                    edu.fiu.cis.acrl.virtuallabs.ws.GetDevaInsInfoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getDevaInsInfo operation
           */
            public void receiveErrorgetDevaInsInfo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for deleteCourse method
            * override this method for handling normal response from deleteCourse operation
            */
           public void receiveResultdeleteCourse(
                    ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from deleteCourse operation
           */
            public void receiveErrordeleteCourse(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getHostList method
            * override this method for handling normal response from getHostList operation
            */
           public void receiveResultgetHostList(
                    edu.fiu.cis.acrl.virtuallabs.ws.GetHostListResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getHostList operation
           */
            public void receiveErrorgetHostList(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getAvailableTimeZoneIds method
            * override this method for handling normal response from getAvailableTimeZoneIds operation
            */
           public void receiveResultgetAvailableTimeZoneIds(
                    edu.fiu.cis.acrl.virtuallabs.ws.GetAvailableTimeZoneIdsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAvailableTimeZoneIds operation
           */
            public void receiveErrorgetAvailableTimeZoneIds(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cancelUserAppointment method
            * override this method for handling normal response from cancelUserAppointment operation
            */
           public void receiveResultcancelUserAppointment(
                    edu.fiu.cis.acrl.virtuallabs.ws.CancelUserAppointmentResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cancelUserAppointment operation
           */
            public void receiveErrorcancelUserAppointment(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cancelHostAppointment method
            * override this method for handling normal response from cancelHostAppointment operation
            */
           public void receiveResultcancelHostAppointment(
                    edu.fiu.cis.acrl.virtuallabs.ws.CancelHostAppointmentResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cancelHostAppointment operation
           */
            public void receiveErrorcancelHostAppointment(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addInitialTasks4NewVMs method
            * override this method for handling normal response from addInitialTasks4NewVMs operation
            */
           public void receiveResultaddInitialTasks4NewVMs(
                    edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addInitialTasks4NewVMs operation
           */
            public void receiveErroraddInitialTasks4NewVMs(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for enrollUserInCourse method
            * override this method for handling normal response from enrollUserInCourse operation
            */
           public void receiveResultenrollUserInCourse(
                    edu.fiu.cis.acrl.virtuallabs.ws.EnrollUserInCourseResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from enrollUserInCourse operation
           */
            public void receiveErrorenrollUserInCourse(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getUserCurAppId method
            * override this method for handling normal response from getUserCurAppId operation
            */
           public void receiveResultgetUserCurAppId(
                    edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurAppIdResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getUserCurAppId operation
           */
            public void receiveErrorgetUserCurAppId(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getHostAppointments method
            * override this method for handling normal response from getHostAppointments operation
            */
           public void receiveResultgetHostAppointments(
                    edu.fiu.cis.acrl.virtuallabs.ws.GetHostAppointmentsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getHostAppointments operation
           */
            public void receiveErrorgetHostAppointments(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getCheckinInterval method
            * override this method for handling normal response from getCheckinInterval operation
            */
           public void receiveResultgetCheckinInterval(
                    edu.fiu.cis.acrl.virtuallabs.ws.GetCheckinIntervalResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCheckinInterval operation
           */
            public void receiveErrorgetCheckinInterval(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addCourse method
            * override this method for handling normal response from addCourse operation
            */
           public void receiveResultaddCourse(
                    ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addCourse operation
           */
            public void receiveErroraddCourse(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cancelMentorAppointment method
            * override this method for handling normal response from cancelMentorAppointment operation
            */
           public void receiveResultcancelMentorAppointment(
                    edu.fiu.cis.acrl.virtuallabs.ws.CancelMentorAppointmentResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cancelMentorAppointment operation
           */
            public void receiveErrorcancelMentorAppointment(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getConfiguration method
            * override this method for handling normal response from getConfiguration operation
            */
           public void receiveResultgetConfiguration(
                    edu.fiu.cis.acrl.virtuallabs.ws.GetConfigurationResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getConfiguration operation
           */
            public void receiveErrorgetConfiguration(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getAvailableActions method
            * override this method for handling normal response from getAvailableActions operation
            */
           public void receiveResultgetAvailableActions(
                    edu.fiu.cis.acrl.virtuallabs.ws.GetAvailableActionsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAvailableActions operation
           */
            public void receiveErrorgetAvailableActions(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getUserCurrentTime method
            * override this method for handling normal response from getUserCurrentTime operation
            */
           public void receiveResultgetUserCurrentTime(
                    edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurrentTimeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getUserCurrentTime operation
           */
            public void receiveErrorgetUserCurrentTime(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cancelAllUserAppointments method
            * override this method for handling normal response from cancelAllUserAppointments operation
            */
           public void receiveResultcancelAllUserAppointments(
                    edu.fiu.cis.acrl.virtuallabs.ws.CancelAllUserAppointmentsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cancelAllUserAppointments operation
           */
            public void receiveErrorcancelAllUserAppointments(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for suspendUserVLab method
            * override this method for handling normal response from suspendUserVLab operation
            */
           public void receiveResultsuspendUserVLab(
                    edu.fiu.cis.acrl.virtuallabs.ws.SuspendUserVLabResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from suspendUserVLab operation
           */
            public void receiveErrorsuspendUserVLab(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getEndDate4CurrentDevaIns method
            * override this method for handling normal response from getEndDate4CurrentDevaIns operation
            */
           public void receiveResultgetEndDate4CurrentDevaIns(
                    edu.fiu.cis.acrl.virtuallabs.ws.GetEndDate4CurrentDevaInsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getEndDate4CurrentDevaIns operation
           */
            public void receiveErrorgetEndDate4CurrentDevaIns(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getResourceTypes method
            * override this method for handling normal response from getResourceTypes operation
            */
           public void receiveResultgetResourceTypes(
                    edu.fiu.cis.acrl.virtuallabs.ws.GetResourceTypesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getResourceTypes operation
           */
            public void receiveErrorgetResourceTypes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for scheduleUserAppointmentsWithEncryptedPassword method
            * override this method for handling normal response from scheduleUserAppointmentsWithEncryptedPassword operation
            */
           public void receiveResultscheduleUserAppointmentsWithEncryptedPassword(
                    edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsWithEncryptedPasswordResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from scheduleUserAppointmentsWithEncryptedPassword operation
           */
            public void receiveErrorscheduleUserAppointmentsWithEncryptedPassword(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for refreshVM method
            * override this method for handling normal response from refreshVM operation
            */
           public void receiveResultrefreshVM(
                    edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from refreshVM operation
           */
            public void receiveErrorrefreshVM(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for createUserProfile method
            * override this method for handling normal response from createUserProfile operation
            */
           public void receiveResultcreateUserProfile(
                    edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from createUserProfile operation
           */
            public void receiveErrorcreateUserProfile(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for isResourceAvailable method
            * override this method for handling normal response from isResourceAvailable operation
            */
           public void receiveResultisResourceAvailable(
                    edu.fiu.cis.acrl.virtuallabs.ws.IsResourceAvailableResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from isResourceAvailable operation
           */
            public void receiveErrorisResourceAvailable(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addHost method
            * override this method for handling normal response from addHost operation
            */
           public void receiveResultaddHost(
                    edu.fiu.cis.acrl.virtuallabs.ws.AddHostResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addHost operation
           */
            public void receiveErroraddHost(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getMentorAppointments method
            * override this method for handling normal response from getMentorAppointments operation
            */
           public void receiveResultgetMentorAppointments(
                    edu.fiu.cis.acrl.virtuallabs.ws.GetMentorAppointmentsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getMentorAppointments operation
           */
            public void receiveErrorgetMentorAppointments(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getUserAppointments method
            * override this method for handling normal response from getUserAppointments operation
            */
           public void receiveResultgetUserAppointments(
                    edu.fiu.cis.acrl.virtuallabs.ws.GetUserAppointmentsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getUserAppointments operation
           */
            public void receiveErrorgetUserAppointments(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for reserveResource method
            * override this method for handling normal response from reserveResource operation
            */
           public void receiveResultreserveResource(
                    edu.fiu.cis.acrl.virtuallabs.ws.ReserveResourceResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from reserveResource operation
           */
            public void receiveErrorreserveResource(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getVMInsInfo method
            * override this method for handling normal response from getVMInsInfo operation
            */
           public void receiveResultgetVMInsInfo(
                    edu.fiu.cis.acrl.virtuallabs.ws.GetVMInsInfoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getVMInsInfo operation
           */
            public void receiveErrorgetVMInsInfo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for editUserProfileWithEncryptedPassword method
            * override this method for handling normal response from editUserProfileWithEncryptedPassword operation
            */
           public void receiveResulteditUserProfileWithEncryptedPassword(
                    edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileWithEncryptedPasswordResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from editUserProfileWithEncryptedPassword operation
           */
            public void receiveErroreditUserProfileWithEncryptedPassword(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for editUserProfile method
            * override this method for handling normal response from editUserProfile operation
            */
           public void receiveResulteditUserProfile(
                    edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from editUserProfile operation
           */
            public void receiveErroreditUserProfile(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for checkin method
            * override this method for handling normal response from checkin operation
            */
           public void receiveResultcheckin(
                    edu.fiu.cis.acrl.virtuallabs.ws.CheckinResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from checkin operation
           */
            public void receiveErrorcheckin(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cancelAllMentorAppointments method
            * override this method for handling normal response from cancelAllMentorAppointments operation
            */
           public void receiveResultcancelAllMentorAppointments(
                    edu.fiu.cis.acrl.virtuallabs.ws.CancelAllMentorAppointmentsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cancelAllMentorAppointments operation
           */
            public void receiveErrorcancelAllMentorAppointments(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getUserResourceTypes method
            * override this method for handling normal response from getUserResourceTypes operation
            */
           public void receiveResultgetUserResourceTypes(
                    edu.fiu.cis.acrl.virtuallabs.ws.GetUserResourceTypesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getUserResourceTypes operation
           */
            public void receiveErrorgetUserResourceTypes(java.lang.Exception e) {
            }
                


    }
    