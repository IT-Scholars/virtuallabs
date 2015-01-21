
/**
 * VirtualLabsMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */
        package edu.fiu.cis.acrl.virtuallabs.ws;

        /**
        *  VirtualLabsMessageReceiverInOut message receiver
        */

        public class VirtualLabsMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver{


        public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault{

        try {

        // get the implementation class for the Web Service
        Object obj = getTheImplementationObject(msgContext);

        VirtualLabsSkeleton skel = (VirtualLabsSkeleton)obj;
        //Out Envelop
        org.apache.axiom.soap.SOAPEnvelope envelope = null;
        //Find the axisOperation that has been set by the Dispatch phase.
        org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext().getAxisOperation();
        if (op == null) {
        throw new org.apache.axis2.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
        }

        java.lang.String methodName;
        if((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJavaIdentifier(op.getName().getLocalPart())) != null)){

        

            if("setConfiguration".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.SetConfigurationResponse setConfigurationResponse5 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.SetConfigurationRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.SetConfigurationRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.SetConfigurationRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               setConfigurationResponse5 =
                                                   
                                                   
                                                         skel.setConfiguration(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), setConfigurationResponse5, false);
                                    } else 

            if("getHost".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.GetHostResponse getHostResponse7 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.GetHostRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.GetHostRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.GetHostRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               getHostResponse7 =
                                                   
                                                   
                                                         skel.getHost(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), getHostResponse7, false);
                                    } else 

            if("modifyUserAppointment".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.ModifyUserAppointmentResponse modifyUserAppointmentResponse9 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.ModifyUserAppointmentRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.ModifyUserAppointmentRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.ModifyUserAppointmentRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               modifyUserAppointmentResponse9 =
                                                   
                                                   
                                                         skel.modifyUserAppointment(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), modifyUserAppointmentResponse9, false);
                                    } else 

            if("getUserProfileByUsername".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByUsernameResponse getUserProfileByUsernameResponse11 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByUsernameRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByUsernameRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByUsernameRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               getUserProfileByUsernameResponse11 =
                                                   
                                                   
                                                         skel.getUserProfileByUsername(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), getUserProfileByUsernameResponse11, false);
                                    } else 

            if("getUserDefaultTimeZoneId".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.GetUserDefaultTimeZoneIdResponse getUserDefaultTimeZoneIdResponse13 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.GetUserDefaultTimeZoneIdRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.GetUserDefaultTimeZoneIdRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.GetUserDefaultTimeZoneIdRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               getUserDefaultTimeZoneIdResponse13 =
                                                   
                                                   
                                                         skel.getUserDefaultTimeZoneId(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), getUserDefaultTimeZoneIdResponse13, false);
                                    } else 

            if("setUserDefaultTimeZoneId".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.SetUserDefaultTimeZoneIdResponse setUserDefaultTimeZoneIdResponse15 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.SetUserDefaultTimeZoneIdRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.SetUserDefaultTimeZoneIdRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.SetUserDefaultTimeZoneIdRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               setUserDefaultTimeZoneIdResponse15 =
                                                   
                                                   
                                                         skel.setUserDefaultTimeZoneId(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), setUserDefaultTimeZoneIdResponse15, false);
                                    } else 

            if("modifyMentorAppointment".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.ModifyMentorAppointmentResponse modifyMentorAppointmentResponse17 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.ModifyMentorAppointmentRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.ModifyMentorAppointmentRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.ModifyMentorAppointmentRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               modifyMentorAppointmentResponse17 =
                                                   
                                                   
                                                         skel.modifyMentorAppointment(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), modifyMentorAppointmentResponse17, false);
                                    } else 

            if("createUserProfileWithEncryptedPassword".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileWithEncryptedPasswordResponse createUserProfileWithEncryptedPasswordResponse19 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileWithEncryptedPasswordRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileWithEncryptedPasswordRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileWithEncryptedPasswordRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               createUserProfileWithEncryptedPasswordResponse19 =
                                                   
                                                   
                                                         skel.createUserProfileWithEncryptedPassword(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), createUserProfileWithEncryptedPasswordResponse19, false);
                                    } else 

            if("runVMCmd".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.RunVMCmdResponse runVMCmdResponse21 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.RunVMCmdRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.RunVMCmdRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.RunVMCmdRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               runVMCmdResponse21 =
                                                   
                                                   
                                                         skel.runVMCmd(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), runVMCmdResponse21, false);
                                    } else 

            if("extendUserAppointment".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.ExtendUserAppointmentResponse extendUserAppointmentResponse23 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.ExtendUserAppointmentRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.ExtendUserAppointmentRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.ExtendUserAppointmentRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               extendUserAppointmentResponse23 =
                                                   
                                                   
                                                         skel.extendUserAppointment(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), extendUserAppointmentResponse23, false);
                                    } else 

            if("addInitialTasks4NewVMsWithEncryptedPassword".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsWithEncryptedPasswordResponse addInitialTasks4NewVMsWithEncryptedPasswordResponse25 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsWithEncryptedPasswordRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsWithEncryptedPasswordRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsWithEncryptedPasswordRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               addInitialTasks4NewVMsWithEncryptedPasswordResponse25 =
                                                   
                                                   
                                                         skel.addInitialTasks4NewVMsWithEncryptedPassword(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), addInitialTasks4NewVMsWithEncryptedPasswordResponse25, false);
                                    } else 

            if("modifyHostAppointment".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.ModifyHostAppointmentResponse modifyHostAppointmentResponse27 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.ModifyHostAppointmentRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.ModifyHostAppointmentRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.ModifyHostAppointmentRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               modifyHostAppointmentResponse27 =
                                                   
                                                   
                                                         skel.modifyHostAppointment(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), modifyHostAppointmentResponse27, false);
                                    } else 

            if("isRDPReady".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.IsRDPReadyResponse isRDPReadyResponse29 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.IsRDPReadyRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.IsRDPReadyRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.IsRDPReadyRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               isRDPReadyResponse29 =
                                                   
                                                   
                                                         skel.isRDPReady(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), isRDPReadyResponse29, false);
                                    } else 

            if("modifyCourse".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.ModifyCourseRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.ModifyCourseRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.ModifyCourseRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               
                                                         skel.modifyCourse(wrappedParam)
                                                    ;
                                            
                                        envelope = getSOAPFactory(msgContext).getDefaultEnvelope();
                                    } else 

            if("setHost".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.SetHostResponse setHostResponse33 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.SetHostRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.SetHostRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.SetHostRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               setHostResponse33 =
                                                   
                                                   
                                                         skel.setHost(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), setHostResponse33, false);
                                    } else 

            if("scheduleMentorAppointments".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.ScheduleMentorAppointmentsResponse scheduleMentorAppointmentsResponse35 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.ScheduleMentorAppointmentsRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.ScheduleMentorAppointmentsRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.ScheduleMentorAppointmentsRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               scheduleMentorAppointmentsResponse35 =
                                                   
                                                   
                                                         skel.scheduleMentorAppointments(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), scheduleMentorAppointmentsResponse35, false);
                                    } else 

            if("cancelAllHostAppointments".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.CancelAllHostAppointmentsResponse cancelAllHostAppointmentsResponse37 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.CancelAllHostAppointmentsRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.CancelAllHostAppointmentsRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.CancelAllHostAppointmentsRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               cancelAllHostAppointmentsResponse37 =
                                                   
                                                   
                                                         skel.cancelAllHostAppointments(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), cancelAllHostAppointmentsResponse37, false);
                                    } else 

            if("destroyDevaIns".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.DestroyDevaInsResponse destroyDevaInsResponse39 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.DestroyDevaInsRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.DestroyDevaInsRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.DestroyDevaInsRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               destroyDevaInsResponse39 =
                                                   
                                                   
                                                         skel.destroyDevaIns(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), destroyDevaInsResponse39, false);
                                    } else 

            if("getUserProfileByEmail".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByEmailResponse getUserProfileByEmailResponse41 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByEmailRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByEmailRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByEmailRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               getUserProfileByEmailResponse41 =
                                                   
                                                   
                                                         skel.getUserProfileByEmail(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), getUserProfileByEmailResponse41, false);
                                    } else 

            if("delHost".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.DelHostResponse delHostResponse43 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.DelHostRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.DelHostRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.DelHostRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               delHostResponse43 =
                                                   
                                                   
                                                         skel.delHost(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), delHostResponse43, false);
                                    } else 

            if("scheduleUserAppointments".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsResponse scheduleUserAppointmentsResponse45 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               scheduleUserAppointmentsResponse45 =
                                                   
                                                   
                                                         skel.scheduleUserAppointments(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), scheduleUserAppointmentsResponse45, false);
                                    } else 

            if("getUserCredentials".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.GetUserCredentialsResponse getUserCredentialsResponse47 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.GetUserCredentialsRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.GetUserCredentialsRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.GetUserCredentialsRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               getUserCredentialsResponse47 =
                                                   
                                                   
                                                         skel.getUserCredentials(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), getUserCredentialsResponse47, false);
                                    } else 

            if("scheduleHostAppointments".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.ScheduleHostAppointmentsResponse scheduleHostAppointmentsResponse49 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.ScheduleHostAppointmentsRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.ScheduleHostAppointmentsRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.ScheduleHostAppointmentsRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               scheduleHostAppointmentsResponse49 =
                                                   
                                                   
                                                         skel.scheduleHostAppointments(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), scheduleHostAppointmentsResponse49, false);
                                    } else 

            if("delUserProfile".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.DelUserProfileResponse delUserProfileResponse51 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.DelUserProfileRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.DelUserProfileRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.DelUserProfileRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               delUserProfileResponse51 =
                                                   
                                                   
                                                         skel.delUserProfile(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), delUserProfileResponse51, false);
                                    } else 

            if("refreshVMWithEncryptedPassword".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMWithEncryptedPasswordResponse refreshVMWithEncryptedPasswordResponse53 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMWithEncryptedPasswordRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMWithEncryptedPasswordRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMWithEncryptedPasswordRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               refreshVMWithEncryptedPasswordResponse53 =
                                                   
                                                   
                                                         skel.refreshVMWithEncryptedPassword(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), refreshVMWithEncryptedPasswordResponse53, false);
                                    } else 

            if("scheduleCourseAppointment".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.ScheduleCourseAppointmentResponse scheduleCourseAppointmentResponse55 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.ScheduleCourseAppointmentRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.ScheduleCourseAppointmentRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.ScheduleCourseAppointmentRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               scheduleCourseAppointmentResponse55 =
                                                   
                                                   
                                                         skel.scheduleCourseAppointment(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), scheduleCourseAppointmentResponse55, false);
                                    } else 

            if("getDevaInsInfo".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.GetDevaInsInfoResponse getDevaInsInfoResponse57 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.GetDevaInsInfoRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.GetDevaInsInfoRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.GetDevaInsInfoRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               getDevaInsInfoResponse57 =
                                                   
                                                   
                                                         skel.getDevaInsInfo(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), getDevaInsInfoResponse57, false);
                                    } else 

            if("deleteCourse".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.DeleteCourseRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.DeleteCourseRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.DeleteCourseRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               
                                                         skel.deleteCourse(wrappedParam)
                                                    ;
                                            
                                        envelope = getSOAPFactory(msgContext).getDefaultEnvelope();
                                    } else 

            if("getHostList".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.GetHostListResponse getHostListResponse61 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.GetHostListRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.GetHostListRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.GetHostListRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               getHostListResponse61 =
                                                   
                                                   
                                                         skel.getHostList(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), getHostListResponse61, false);
                                    } else 

            if("getAvailableTimeZoneIds".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.GetAvailableTimeZoneIdsResponse getAvailableTimeZoneIdsResponse63 = null;
	                        getAvailableTimeZoneIdsResponse63 =
                                                     
                                                 skel.getAvailableTimeZoneIds()
                                                ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), getAvailableTimeZoneIdsResponse63, false);
                                    } else 

            if("cancelUserAppointment".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.CancelUserAppointmentResponse cancelUserAppointmentResponse65 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.CancelUserAppointmentRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.CancelUserAppointmentRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.CancelUserAppointmentRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               cancelUserAppointmentResponse65 =
                                                   
                                                   
                                                         skel.cancelUserAppointment(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), cancelUserAppointmentResponse65, false);
                                    } else 

            if("cancelHostAppointment".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.CancelHostAppointmentResponse cancelHostAppointmentResponse67 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.CancelHostAppointmentRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.CancelHostAppointmentRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.CancelHostAppointmentRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               cancelHostAppointmentResponse67 =
                                                   
                                                   
                                                         skel.cancelHostAppointment(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), cancelHostAppointmentResponse67, false);
                                    } else 

            if("addInitialTasks4NewVMs".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsResponse addInitialTasks4NewVMsResponse69 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               addInitialTasks4NewVMsResponse69 =
                                                   
                                                   
                                                         skel.addInitialTasks4NewVMs(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), addInitialTasks4NewVMsResponse69, false);
                                    } else 

            if("enrollUserInCourse".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.EnrollUserInCourseResponse enrollUserInCourseResponse71 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.EnrollUserInCourseRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.EnrollUserInCourseRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.EnrollUserInCourseRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               enrollUserInCourseResponse71 =
                                                   
                                                   
                                                         skel.enrollUserInCourse(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), enrollUserInCourseResponse71, false);
                                    } else 

            if("getUserCurAppId".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurAppIdResponse getUserCurAppIdResponse73 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurAppIdRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurAppIdRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurAppIdRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               getUserCurAppIdResponse73 =
                                                   
                                                   
                                                         skel.getUserCurAppId(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), getUserCurAppIdResponse73, false);
                                    } else 

            if("getHostAppointments".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.GetHostAppointmentsResponse getHostAppointmentsResponse75 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.GetHostAppointmentsRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.GetHostAppointmentsRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.GetHostAppointmentsRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               getHostAppointmentsResponse75 =
                                                   
                                                   
                                                         skel.getHostAppointments(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), getHostAppointmentsResponse75, false);
                                    } else 

            if("getCheckinInterval".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.GetCheckinIntervalResponse getCheckinIntervalResponse77 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.GetCheckinIntervalRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.GetCheckinIntervalRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.GetCheckinIntervalRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               getCheckinIntervalResponse77 =
                                                   
                                                   
                                                         skel.getCheckinInterval(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), getCheckinIntervalResponse77, false);
                                    } else 

            if("addCourse".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.AddCourseRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.AddCourseRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.AddCourseRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               
                                                         skel.addCourse(wrappedParam)
                                                    ;
                                            
                                        envelope = getSOAPFactory(msgContext).getDefaultEnvelope();
                                    } else 

            if("cancelMentorAppointment".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.CancelMentorAppointmentResponse cancelMentorAppointmentResponse81 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.CancelMentorAppointmentRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.CancelMentorAppointmentRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.CancelMentorAppointmentRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               cancelMentorAppointmentResponse81 =
                                                   
                                                   
                                                         skel.cancelMentorAppointment(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), cancelMentorAppointmentResponse81, false);
                                    } else 

            if("getConfiguration".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.GetConfigurationResponse getConfigurationResponse83 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.GetConfigurationRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.GetConfigurationRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.GetConfigurationRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               getConfigurationResponse83 =
                                                   
                                                   
                                                         skel.getConfiguration(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), getConfigurationResponse83, false);
                                    } else 

            if("getAvailableActions".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.GetAvailableActionsResponse getAvailableActionsResponse85 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.GetAvailableActionsRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.GetAvailableActionsRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.GetAvailableActionsRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               getAvailableActionsResponse85 =
                                                   
                                                   
                                                         skel.getAvailableActions(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), getAvailableActionsResponse85, false);
                                    } else 

            if("getUserCurrentTime".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurrentTimeResponse getUserCurrentTimeResponse87 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurrentTimeRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurrentTimeRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurrentTimeRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               getUserCurrentTimeResponse87 =
                                                   
                                                   
                                                         skel.getUserCurrentTime(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), getUserCurrentTimeResponse87, false);
                                    } else 

            if("cancelAllUserAppointments".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.CancelAllUserAppointmentsResponse cancelAllUserAppointmentsResponse89 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.CancelAllUserAppointmentsRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.CancelAllUserAppointmentsRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.CancelAllUserAppointmentsRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               cancelAllUserAppointmentsResponse89 =
                                                   
                                                   
                                                         skel.cancelAllUserAppointments(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), cancelAllUserAppointmentsResponse89, false);
                                    } else 

            if("suspendUserVLab".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.SuspendUserVLabResponse suspendUserVLabResponse91 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.SuspendUserVLabRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.SuspendUserVLabRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.SuspendUserVLabRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               suspendUserVLabResponse91 =
                                                   
                                                   
                                                         skel.suspendUserVLab(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), suspendUserVLabResponse91, false);
                                    } else 

            if("getEndDate4CurrentDevaIns".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.GetEndDate4CurrentDevaInsResponse getEndDate4CurrentDevaInsResponse93 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.GetEndDate4CurrentDevaInsRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.GetEndDate4CurrentDevaInsRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.GetEndDate4CurrentDevaInsRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               getEndDate4CurrentDevaInsResponse93 =
                                                   
                                                   
                                                         skel.getEndDate4CurrentDevaIns(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), getEndDate4CurrentDevaInsResponse93, false);
                                    } else 

            if("getResourceTypes".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.GetResourceTypesResponse getResourceTypesResponse95 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.GetResourceTypesRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.GetResourceTypesRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.GetResourceTypesRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               getResourceTypesResponse95 =
                                                   
                                                   
                                                         skel.getResourceTypes(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), getResourceTypesResponse95, false);
                                    } else 

            if("scheduleUserAppointmentsWithEncryptedPassword".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsWithEncryptedPasswordResponse scheduleUserAppointmentsWithEncryptedPasswordResponse97 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsWithEncryptedPasswordRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsWithEncryptedPasswordRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsWithEncryptedPasswordRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               scheduleUserAppointmentsWithEncryptedPasswordResponse97 =
                                                   
                                                   
                                                         skel.scheduleUserAppointmentsWithEncryptedPassword(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), scheduleUserAppointmentsWithEncryptedPasswordResponse97, false);
                                    } else 

            if("refreshVM".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMResponse refreshVMResponse99 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               refreshVMResponse99 =
                                                   
                                                   
                                                         skel.refreshVM(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), refreshVMResponse99, false);
                                    } else 

            if("createUserProfile".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileResponse createUserProfileResponse101 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               createUserProfileResponse101 =
                                                   
                                                   
                                                         skel.createUserProfile(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), createUserProfileResponse101, false);
                                    } else 

            if("isResourceAvailable".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.IsResourceAvailableResponse isResourceAvailableResponse103 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.IsResourceAvailableRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.IsResourceAvailableRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.IsResourceAvailableRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               isResourceAvailableResponse103 =
                                                   
                                                   
                                                         skel.isResourceAvailable(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), isResourceAvailableResponse103, false);
                                    } else 

            if("addHost".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.AddHostResponse addHostResponse105 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.AddHostRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.AddHostRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.AddHostRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               addHostResponse105 =
                                                   
                                                   
                                                         skel.addHost(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), addHostResponse105, false);
                                    } else 

            if("getMentorAppointments".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.GetMentorAppointmentsResponse getMentorAppointmentsResponse107 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.GetMentorAppointmentsRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.GetMentorAppointmentsRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.GetMentorAppointmentsRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               getMentorAppointmentsResponse107 =
                                                   
                                                   
                                                         skel.getMentorAppointments(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), getMentorAppointmentsResponse107, false);
                                    } else 

            if("getUserAppointments".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.GetUserAppointmentsResponse getUserAppointmentsResponse109 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.GetUserAppointmentsRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.GetUserAppointmentsRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.GetUserAppointmentsRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               getUserAppointmentsResponse109 =
                                                   
                                                   
                                                         skel.getUserAppointments(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), getUserAppointmentsResponse109, false);
                                    } else 

            if("reserveResource".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.ReserveResourceResponse reserveResourceResponse111 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.ReserveResourceRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.ReserveResourceRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.ReserveResourceRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               reserveResourceResponse111 =
                                                   
                                                   
                                                         skel.reserveResource(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), reserveResourceResponse111, false);
                                    } else 

            if("getVMInsInfo".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.GetVMInsInfoResponse getVMInsInfoResponse113 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.GetVMInsInfoRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.GetVMInsInfoRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.GetVMInsInfoRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               getVMInsInfoResponse113 =
                                                   
                                                   
                                                         skel.getVMInsInfo(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), getVMInsInfoResponse113, false);
                                    } else 

            if("editUserProfileWithEncryptedPassword".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileWithEncryptedPasswordResponse editUserProfileWithEncryptedPasswordResponse115 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileWithEncryptedPasswordRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileWithEncryptedPasswordRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileWithEncryptedPasswordRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               editUserProfileWithEncryptedPasswordResponse115 =
                                                   
                                                   
                                                         skel.editUserProfileWithEncryptedPassword(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), editUserProfileWithEncryptedPasswordResponse115, false);
                                    } else 

            if("editUserProfile".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileResponse editUserProfileResponse117 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               editUserProfileResponse117 =
                                                   
                                                   
                                                         skel.editUserProfile(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), editUserProfileResponse117, false);
                                    } else 

            if("checkin".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.CheckinResponse checkinResponse119 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.CheckinRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.CheckinRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.CheckinRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               checkinResponse119 =
                                                   
                                                   
                                                         skel.checkin(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), checkinResponse119, false);
                                    } else 

            if("cancelAllMentorAppointments".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.CancelAllMentorAppointmentsResponse cancelAllMentorAppointmentsResponse121 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.CancelAllMentorAppointmentsRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.CancelAllMentorAppointmentsRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.CancelAllMentorAppointmentsRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               cancelAllMentorAppointmentsResponse121 =
                                                   
                                                   
                                                         skel.cancelAllMentorAppointments(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), cancelAllMentorAppointmentsResponse121, false);
                                    } else 

            if("getUserResourceTypes".equals(methodName)){
                
                edu.fiu.cis.acrl.virtuallabs.ws.GetUserResourceTypesResponse getUserResourceTypesResponse123 = null;
	                        edu.fiu.cis.acrl.virtuallabs.ws.GetUserResourceTypesRequest wrappedParam =
                                                             (edu.fiu.cis.acrl.virtuallabs.ws.GetUserResourceTypesRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    edu.fiu.cis.acrl.virtuallabs.ws.GetUserResourceTypesRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               getUserResourceTypesResponse123 =
                                                   
                                                   
                                                         skel.getUserResourceTypes(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), getUserResourceTypesResponse123, false);
                                    
            } else {
              throw new java.lang.RuntimeException("method not found");
            }
        

        newMsgContext.setEnvelope(envelope);
        }
        } catch (DuplicateCourseFault e) {

            msgContext.setProperty(org.apache.axis2.Constants.FAULT_NAME,"DuplicateCourseError");
            org.apache.axis2.AxisFault f = createAxisFault(e);
            if (e.getFaultMessage() != null){
                f.setDetail(toOM(e.getFaultMessage(),false));
            }
            throw f;
            }
         catch (CourseNotFoundFault e) {

            msgContext.setProperty(org.apache.axis2.Constants.FAULT_NAME,"CourseNotFoundError");
            org.apache.axis2.AxisFault f = createAxisFault(e);
            if (e.getFaultMessage() != null){
                f.setDetail(toOM(e.getFaultMessage(),false));
            }
            throw f;
            }
         catch (InvalidDataFault e) {

            msgContext.setProperty(org.apache.axis2.Constants.FAULT_NAME,"InvalidDataError");
            org.apache.axis2.AxisFault f = createAxisFault(e);
            if (e.getFaultMessage() != null){
                f.setDetail(toOM(e.getFaultMessage(),false));
            }
            throw f;
            }
        
        catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
        }
        
        //
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.SetConfigurationRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.SetConfigurationRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.SetConfigurationResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.SetConfigurationResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetHostRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetHostRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetHostResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetHostResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.ModifyUserAppointmentRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.ModifyUserAppointmentRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.ModifyUserAppointmentResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.ModifyUserAppointmentResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByUsernameRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByUsernameRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByUsernameResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByUsernameResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetUserDefaultTimeZoneIdRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetUserDefaultTimeZoneIdRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetUserDefaultTimeZoneIdResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetUserDefaultTimeZoneIdResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.SetUserDefaultTimeZoneIdRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.SetUserDefaultTimeZoneIdRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.SetUserDefaultTimeZoneIdResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.SetUserDefaultTimeZoneIdResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.ModifyMentorAppointmentRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.ModifyMentorAppointmentRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.ModifyMentorAppointmentResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.ModifyMentorAppointmentResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileWithEncryptedPasswordRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileWithEncryptedPasswordRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileWithEncryptedPasswordResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileWithEncryptedPasswordResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.RunVMCmdRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.RunVMCmdRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.RunVMCmdResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.RunVMCmdResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.ExtendUserAppointmentRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.ExtendUserAppointmentRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.ExtendUserAppointmentResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.ExtendUserAppointmentResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsWithEncryptedPasswordRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsWithEncryptedPasswordRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsWithEncryptedPasswordResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsWithEncryptedPasswordResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.ModifyHostAppointmentRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.ModifyHostAppointmentRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.ModifyHostAppointmentResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.ModifyHostAppointmentResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.IsRDPReadyRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.IsRDPReadyRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.IsRDPReadyResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.IsRDPReadyResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.ModifyCourseRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.ModifyCourseRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.DuplicateCourseError param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.DuplicateCourseError.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.CourseNotFoundError param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.CourseNotFoundError.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.InvalidDataError param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.InvalidDataError.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.SetHostRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.SetHostRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.SetHostResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.SetHostResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.ScheduleMentorAppointmentsRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.ScheduleMentorAppointmentsRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.ScheduleMentorAppointmentsResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.ScheduleMentorAppointmentsResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.CancelAllHostAppointmentsRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.CancelAllHostAppointmentsRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.CancelAllHostAppointmentsResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.CancelAllHostAppointmentsResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.DestroyDevaInsRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.DestroyDevaInsRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.DestroyDevaInsResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.DestroyDevaInsResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByEmailRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByEmailRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByEmailResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByEmailResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.DelHostRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.DelHostRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.DelHostResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.DelHostResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetUserCredentialsRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetUserCredentialsRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetUserCredentialsResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetUserCredentialsResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.ScheduleHostAppointmentsRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.ScheduleHostAppointmentsRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.ScheduleHostAppointmentsResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.ScheduleHostAppointmentsResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.DelUserProfileRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.DelUserProfileRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.DelUserProfileResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.DelUserProfileResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMWithEncryptedPasswordRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMWithEncryptedPasswordRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMWithEncryptedPasswordResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMWithEncryptedPasswordResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.ScheduleCourseAppointmentRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.ScheduleCourseAppointmentRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.ScheduleCourseAppointmentResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.ScheduleCourseAppointmentResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetDevaInsInfoRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetDevaInsInfoRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetDevaInsInfoResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetDevaInsInfoResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.DeleteCourseRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.DeleteCourseRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetHostListRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetHostListRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetHostListResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetHostListResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetAvailableTimeZoneIdsResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetAvailableTimeZoneIdsResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.CancelUserAppointmentRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.CancelUserAppointmentRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.CancelUserAppointmentResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.CancelUserAppointmentResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.CancelHostAppointmentRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.CancelHostAppointmentRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.CancelHostAppointmentResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.CancelHostAppointmentResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.EnrollUserInCourseRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.EnrollUserInCourseRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.EnrollUserInCourseResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.EnrollUserInCourseResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurAppIdRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurAppIdRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurAppIdResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurAppIdResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetHostAppointmentsRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetHostAppointmentsRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetHostAppointmentsResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetHostAppointmentsResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetCheckinIntervalRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetCheckinIntervalRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetCheckinIntervalResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetCheckinIntervalResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.AddCourseRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.AddCourseRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.CancelMentorAppointmentRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.CancelMentorAppointmentRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.CancelMentorAppointmentResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.CancelMentorAppointmentResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetConfigurationRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetConfigurationRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetConfigurationResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetConfigurationResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetAvailableActionsRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetAvailableActionsRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetAvailableActionsResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetAvailableActionsResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurrentTimeRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurrentTimeRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurrentTimeResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurrentTimeResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.CancelAllUserAppointmentsRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.CancelAllUserAppointmentsRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.CancelAllUserAppointmentsResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.CancelAllUserAppointmentsResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.SuspendUserVLabRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.SuspendUserVLabRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.SuspendUserVLabResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.SuspendUserVLabResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetEndDate4CurrentDevaInsRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetEndDate4CurrentDevaInsRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetEndDate4CurrentDevaInsResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetEndDate4CurrentDevaInsResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetResourceTypesRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetResourceTypesRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetResourceTypesResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetResourceTypesResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsWithEncryptedPasswordRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsWithEncryptedPasswordRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsWithEncryptedPasswordResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsWithEncryptedPasswordResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.IsResourceAvailableRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.IsResourceAvailableRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.IsResourceAvailableResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.IsResourceAvailableResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.AddHostRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.AddHostRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.AddHostResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.AddHostResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetMentorAppointmentsRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetMentorAppointmentsRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetMentorAppointmentsResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetMentorAppointmentsResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetUserAppointmentsRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetUserAppointmentsRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetUserAppointmentsResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetUserAppointmentsResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.ReserveResourceRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.ReserveResourceRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.ReserveResourceResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.ReserveResourceResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetVMInsInfoRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetVMInsInfoRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetVMInsInfoResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetVMInsInfoResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileWithEncryptedPasswordRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileWithEncryptedPasswordRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileWithEncryptedPasswordResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileWithEncryptedPasswordResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.CheckinRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.CheckinRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.CheckinResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.CheckinResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.CancelAllMentorAppointmentsRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.CancelAllMentorAppointmentsRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.CancelAllMentorAppointmentsResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.CancelAllMentorAppointmentsResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetUserResourceTypesRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetUserResourceTypesRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(edu.fiu.cis.acrl.virtuallabs.ws.GetUserResourceTypesResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetUserResourceTypesResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.SetConfigurationResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.SetConfigurationResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.SetConfigurationResponse wrapsetConfiguration(){
                                edu.fiu.cis.acrl.virtuallabs.ws.SetConfigurationResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.SetConfigurationResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.GetHostResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetHostResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.GetHostResponse wrapgetHost(){
                                edu.fiu.cis.acrl.virtuallabs.ws.GetHostResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.GetHostResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.ModifyUserAppointmentResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.ModifyUserAppointmentResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.ModifyUserAppointmentResponse wrapmodifyUserAppointment(){
                                edu.fiu.cis.acrl.virtuallabs.ws.ModifyUserAppointmentResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.ModifyUserAppointmentResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByUsernameResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByUsernameResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByUsernameResponse wrapgetUserProfileByUsername(){
                                edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByUsernameResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByUsernameResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.GetUserDefaultTimeZoneIdResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetUserDefaultTimeZoneIdResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.GetUserDefaultTimeZoneIdResponse wrapgetUserDefaultTimeZoneId(){
                                edu.fiu.cis.acrl.virtuallabs.ws.GetUserDefaultTimeZoneIdResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.GetUserDefaultTimeZoneIdResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.SetUserDefaultTimeZoneIdResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.SetUserDefaultTimeZoneIdResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.SetUserDefaultTimeZoneIdResponse wrapsetUserDefaultTimeZoneId(){
                                edu.fiu.cis.acrl.virtuallabs.ws.SetUserDefaultTimeZoneIdResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.SetUserDefaultTimeZoneIdResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.ModifyMentorAppointmentResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.ModifyMentorAppointmentResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.ModifyMentorAppointmentResponse wrapmodifyMentorAppointment(){
                                edu.fiu.cis.acrl.virtuallabs.ws.ModifyMentorAppointmentResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.ModifyMentorAppointmentResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileWithEncryptedPasswordResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileWithEncryptedPasswordResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileWithEncryptedPasswordResponse wrapcreateUserProfileWithEncryptedPassword(){
                                edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileWithEncryptedPasswordResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileWithEncryptedPasswordResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.RunVMCmdResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.RunVMCmdResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.RunVMCmdResponse wraprunVMCmd(){
                                edu.fiu.cis.acrl.virtuallabs.ws.RunVMCmdResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.RunVMCmdResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.ExtendUserAppointmentResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.ExtendUserAppointmentResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.ExtendUserAppointmentResponse wrapextendUserAppointment(){
                                edu.fiu.cis.acrl.virtuallabs.ws.ExtendUserAppointmentResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.ExtendUserAppointmentResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsWithEncryptedPasswordResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsWithEncryptedPasswordResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsWithEncryptedPasswordResponse wrapaddInitialTasks4NewVMsWithEncryptedPassword(){
                                edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsWithEncryptedPasswordResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsWithEncryptedPasswordResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.ModifyHostAppointmentResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.ModifyHostAppointmentResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.ModifyHostAppointmentResponse wrapmodifyHostAppointment(){
                                edu.fiu.cis.acrl.virtuallabs.ws.ModifyHostAppointmentResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.ModifyHostAppointmentResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.IsRDPReadyResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.IsRDPReadyResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.IsRDPReadyResponse wrapisRDPReady(){
                                edu.fiu.cis.acrl.virtuallabs.ws.IsRDPReadyResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.IsRDPReadyResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.SetHostResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.SetHostResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.SetHostResponse wrapsetHost(){
                                edu.fiu.cis.acrl.virtuallabs.ws.SetHostResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.SetHostResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.ScheduleMentorAppointmentsResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.ScheduleMentorAppointmentsResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.ScheduleMentorAppointmentsResponse wrapscheduleMentorAppointments(){
                                edu.fiu.cis.acrl.virtuallabs.ws.ScheduleMentorAppointmentsResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.ScheduleMentorAppointmentsResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.CancelAllHostAppointmentsResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.CancelAllHostAppointmentsResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.CancelAllHostAppointmentsResponse wrapcancelAllHostAppointments(){
                                edu.fiu.cis.acrl.virtuallabs.ws.CancelAllHostAppointmentsResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.CancelAllHostAppointmentsResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.DestroyDevaInsResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.DestroyDevaInsResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.DestroyDevaInsResponse wrapdestroyDevaIns(){
                                edu.fiu.cis.acrl.virtuallabs.ws.DestroyDevaInsResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.DestroyDevaInsResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByEmailResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByEmailResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByEmailResponse wrapgetUserProfileByEmail(){
                                edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByEmailResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByEmailResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.DelHostResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.DelHostResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.DelHostResponse wrapdelHost(){
                                edu.fiu.cis.acrl.virtuallabs.ws.DelHostResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.DelHostResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsResponse wrapscheduleUserAppointments(){
                                edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.GetUserCredentialsResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetUserCredentialsResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.GetUserCredentialsResponse wrapgetUserCredentials(){
                                edu.fiu.cis.acrl.virtuallabs.ws.GetUserCredentialsResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.GetUserCredentialsResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.ScheduleHostAppointmentsResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.ScheduleHostAppointmentsResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.ScheduleHostAppointmentsResponse wrapscheduleHostAppointments(){
                                edu.fiu.cis.acrl.virtuallabs.ws.ScheduleHostAppointmentsResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.ScheduleHostAppointmentsResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.DelUserProfileResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.DelUserProfileResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.DelUserProfileResponse wrapdelUserProfile(){
                                edu.fiu.cis.acrl.virtuallabs.ws.DelUserProfileResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.DelUserProfileResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMWithEncryptedPasswordResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMWithEncryptedPasswordResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMWithEncryptedPasswordResponse wraprefreshVMWithEncryptedPassword(){
                                edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMWithEncryptedPasswordResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMWithEncryptedPasswordResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.ScheduleCourseAppointmentResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.ScheduleCourseAppointmentResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.ScheduleCourseAppointmentResponse wrapscheduleCourseAppointment(){
                                edu.fiu.cis.acrl.virtuallabs.ws.ScheduleCourseAppointmentResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.ScheduleCourseAppointmentResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.GetDevaInsInfoResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetDevaInsInfoResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.GetDevaInsInfoResponse wrapgetDevaInsInfo(){
                                edu.fiu.cis.acrl.virtuallabs.ws.GetDevaInsInfoResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.GetDevaInsInfoResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.GetHostListResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetHostListResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.GetHostListResponse wrapgetHostList(){
                                edu.fiu.cis.acrl.virtuallabs.ws.GetHostListResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.GetHostListResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.GetAvailableTimeZoneIdsResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetAvailableTimeZoneIdsResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.GetAvailableTimeZoneIdsResponse wrapgetAvailableTimeZoneIds(){
                                edu.fiu.cis.acrl.virtuallabs.ws.GetAvailableTimeZoneIdsResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.GetAvailableTimeZoneIdsResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.CancelUserAppointmentResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.CancelUserAppointmentResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.CancelUserAppointmentResponse wrapcancelUserAppointment(){
                                edu.fiu.cis.acrl.virtuallabs.ws.CancelUserAppointmentResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.CancelUserAppointmentResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.CancelHostAppointmentResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.CancelHostAppointmentResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.CancelHostAppointmentResponse wrapcancelHostAppointment(){
                                edu.fiu.cis.acrl.virtuallabs.ws.CancelHostAppointmentResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.CancelHostAppointmentResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsResponse wrapaddInitialTasks4NewVMs(){
                                edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.EnrollUserInCourseResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.EnrollUserInCourseResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.EnrollUserInCourseResponse wrapenrollUserInCourse(){
                                edu.fiu.cis.acrl.virtuallabs.ws.EnrollUserInCourseResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.EnrollUserInCourseResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurAppIdResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurAppIdResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurAppIdResponse wrapgetUserCurAppId(){
                                edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurAppIdResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurAppIdResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.GetHostAppointmentsResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetHostAppointmentsResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.GetHostAppointmentsResponse wrapgetHostAppointments(){
                                edu.fiu.cis.acrl.virtuallabs.ws.GetHostAppointmentsResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.GetHostAppointmentsResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.GetCheckinIntervalResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetCheckinIntervalResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.GetCheckinIntervalResponse wrapgetCheckinInterval(){
                                edu.fiu.cis.acrl.virtuallabs.ws.GetCheckinIntervalResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.GetCheckinIntervalResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.CancelMentorAppointmentResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.CancelMentorAppointmentResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.CancelMentorAppointmentResponse wrapcancelMentorAppointment(){
                                edu.fiu.cis.acrl.virtuallabs.ws.CancelMentorAppointmentResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.CancelMentorAppointmentResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.GetConfigurationResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetConfigurationResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.GetConfigurationResponse wrapgetConfiguration(){
                                edu.fiu.cis.acrl.virtuallabs.ws.GetConfigurationResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.GetConfigurationResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.GetAvailableActionsResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetAvailableActionsResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.GetAvailableActionsResponse wrapgetAvailableActions(){
                                edu.fiu.cis.acrl.virtuallabs.ws.GetAvailableActionsResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.GetAvailableActionsResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurrentTimeResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurrentTimeResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurrentTimeResponse wrapgetUserCurrentTime(){
                                edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurrentTimeResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurrentTimeResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.CancelAllUserAppointmentsResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.CancelAllUserAppointmentsResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.CancelAllUserAppointmentsResponse wrapcancelAllUserAppointments(){
                                edu.fiu.cis.acrl.virtuallabs.ws.CancelAllUserAppointmentsResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.CancelAllUserAppointmentsResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.SuspendUserVLabResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.SuspendUserVLabResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.SuspendUserVLabResponse wrapsuspendUserVLab(){
                                edu.fiu.cis.acrl.virtuallabs.ws.SuspendUserVLabResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.SuspendUserVLabResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.GetEndDate4CurrentDevaInsResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetEndDate4CurrentDevaInsResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.GetEndDate4CurrentDevaInsResponse wrapgetEndDate4CurrentDevaIns(){
                                edu.fiu.cis.acrl.virtuallabs.ws.GetEndDate4CurrentDevaInsResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.GetEndDate4CurrentDevaInsResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.GetResourceTypesResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetResourceTypesResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.GetResourceTypesResponse wrapgetResourceTypes(){
                                edu.fiu.cis.acrl.virtuallabs.ws.GetResourceTypesResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.GetResourceTypesResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsWithEncryptedPasswordResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsWithEncryptedPasswordResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsWithEncryptedPasswordResponse wrapscheduleUserAppointmentsWithEncryptedPassword(){
                                edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsWithEncryptedPasswordResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsWithEncryptedPasswordResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMResponse wraprefreshVM(){
                                edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileResponse wrapcreateUserProfile(){
                                edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.IsResourceAvailableResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.IsResourceAvailableResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.IsResourceAvailableResponse wrapisResourceAvailable(){
                                edu.fiu.cis.acrl.virtuallabs.ws.IsResourceAvailableResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.IsResourceAvailableResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.AddHostResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.AddHostResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.AddHostResponse wrapaddHost(){
                                edu.fiu.cis.acrl.virtuallabs.ws.AddHostResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.AddHostResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.GetMentorAppointmentsResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetMentorAppointmentsResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.GetMentorAppointmentsResponse wrapgetMentorAppointments(){
                                edu.fiu.cis.acrl.virtuallabs.ws.GetMentorAppointmentsResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.GetMentorAppointmentsResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.GetUserAppointmentsResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetUserAppointmentsResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.GetUserAppointmentsResponse wrapgetUserAppointments(){
                                edu.fiu.cis.acrl.virtuallabs.ws.GetUserAppointmentsResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.GetUserAppointmentsResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.ReserveResourceResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.ReserveResourceResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.ReserveResourceResponse wrapreserveResource(){
                                edu.fiu.cis.acrl.virtuallabs.ws.ReserveResourceResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.ReserveResourceResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.GetVMInsInfoResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetVMInsInfoResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.GetVMInsInfoResponse wrapgetVMInsInfo(){
                                edu.fiu.cis.acrl.virtuallabs.ws.GetVMInsInfoResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.GetVMInsInfoResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileWithEncryptedPasswordResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileWithEncryptedPasswordResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileWithEncryptedPasswordResponse wrapeditUserProfileWithEncryptedPassword(){
                                edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileWithEncryptedPasswordResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileWithEncryptedPasswordResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileResponse wrapeditUserProfile(){
                                edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.CheckinResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.CheckinResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.CheckinResponse wrapcheckin(){
                                edu.fiu.cis.acrl.virtuallabs.ws.CheckinResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.CheckinResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.CancelAllMentorAppointmentsResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.CancelAllMentorAppointmentsResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.CancelAllMentorAppointmentsResponse wrapcancelAllMentorAppointments(){
                                edu.fiu.cis.acrl.virtuallabs.ws.CancelAllMentorAppointmentsResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.CancelAllMentorAppointmentsResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, edu.fiu.cis.acrl.virtuallabs.ws.GetUserResourceTypesResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(edu.fiu.cis.acrl.virtuallabs.ws.GetUserResourceTypesResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private edu.fiu.cis.acrl.virtuallabs.ws.GetUserResourceTypesResponse wrapgetUserResourceTypes(){
                                edu.fiu.cis.acrl.virtuallabs.ws.GetUserResourceTypesResponse wrappedElement = new edu.fiu.cis.acrl.virtuallabs.ws.GetUserResourceTypesResponse();
                                return wrappedElement;
                         }
                    


        /**
        *  get the default envelope
        */
        private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory){
        return factory.getDefaultEnvelope();
        }


        private  java.lang.Object fromOM(
        org.apache.axiom.om.OMElement param,
        java.lang.Class type,
        java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault{

        try {
        
                if (edu.fiu.cis.acrl.virtuallabs.ws.SetConfigurationRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.SetConfigurationRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.SetConfigurationResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.SetConfigurationResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetHostRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetHostRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetHostResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetHostResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.ModifyUserAppointmentRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.ModifyUserAppointmentRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.ModifyUserAppointmentResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.ModifyUserAppointmentResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByUsernameRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByUsernameRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByUsernameResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByUsernameResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetUserDefaultTimeZoneIdRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetUserDefaultTimeZoneIdRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetUserDefaultTimeZoneIdResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetUserDefaultTimeZoneIdResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.SetUserDefaultTimeZoneIdRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.SetUserDefaultTimeZoneIdRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.SetUserDefaultTimeZoneIdResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.SetUserDefaultTimeZoneIdResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.ModifyMentorAppointmentRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.ModifyMentorAppointmentRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.ModifyMentorAppointmentResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.ModifyMentorAppointmentResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileWithEncryptedPasswordRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileWithEncryptedPasswordRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileWithEncryptedPasswordResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileWithEncryptedPasswordResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.RunVMCmdRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.RunVMCmdRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.RunVMCmdResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.RunVMCmdResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.ExtendUserAppointmentRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.ExtendUserAppointmentRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.ExtendUserAppointmentResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.ExtendUserAppointmentResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsWithEncryptedPasswordRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsWithEncryptedPasswordRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsWithEncryptedPasswordResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsWithEncryptedPasswordResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.ModifyHostAppointmentRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.ModifyHostAppointmentRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.ModifyHostAppointmentResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.ModifyHostAppointmentResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.IsRDPReadyRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.IsRDPReadyRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.IsRDPReadyResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.IsRDPReadyResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.ModifyCourseRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.ModifyCourseRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.DuplicateCourseError.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.DuplicateCourseError.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.CourseNotFoundError.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.CourseNotFoundError.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.InvalidDataError.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.InvalidDataError.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.SetHostRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.SetHostRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.SetHostResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.SetHostResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.ScheduleMentorAppointmentsRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.ScheduleMentorAppointmentsRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.ScheduleMentorAppointmentsResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.ScheduleMentorAppointmentsResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.CancelAllHostAppointmentsRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.CancelAllHostAppointmentsRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.CancelAllHostAppointmentsResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.CancelAllHostAppointmentsResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.DestroyDevaInsRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.DestroyDevaInsRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.DestroyDevaInsResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.DestroyDevaInsResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByEmailRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByEmailRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByEmailResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetUserProfileByEmailResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.DelHostRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.DelHostRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.DelHostResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.DelHostResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetUserCredentialsRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetUserCredentialsRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetUserCredentialsResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetUserCredentialsResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.ScheduleHostAppointmentsRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.ScheduleHostAppointmentsRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.ScheduleHostAppointmentsResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.ScheduleHostAppointmentsResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.DelUserProfileRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.DelUserProfileRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.DelUserProfileResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.DelUserProfileResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMWithEncryptedPasswordRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMWithEncryptedPasswordRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMWithEncryptedPasswordResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMWithEncryptedPasswordResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.ScheduleCourseAppointmentRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.ScheduleCourseAppointmentRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.ScheduleCourseAppointmentResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.ScheduleCourseAppointmentResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetDevaInsInfoRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetDevaInsInfoRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetDevaInsInfoResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetDevaInsInfoResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.DeleteCourseRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.DeleteCourseRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.CourseNotFoundError.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.CourseNotFoundError.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetHostListRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetHostListRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetHostListResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetHostListResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetAvailableTimeZoneIdsResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetAvailableTimeZoneIdsResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.CancelUserAppointmentRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.CancelUserAppointmentRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.CancelUserAppointmentResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.CancelUserAppointmentResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.CancelHostAppointmentRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.CancelHostAppointmentRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.CancelHostAppointmentResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.CancelHostAppointmentResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.AddInitialTasks4NewVMsResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.EnrollUserInCourseRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.EnrollUserInCourseRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.EnrollUserInCourseResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.EnrollUserInCourseResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurAppIdRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurAppIdRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurAppIdResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurAppIdResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetHostAppointmentsRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetHostAppointmentsRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetHostAppointmentsResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetHostAppointmentsResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetCheckinIntervalRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetCheckinIntervalRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetCheckinIntervalResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetCheckinIntervalResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.AddCourseRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.AddCourseRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.DuplicateCourseError.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.DuplicateCourseError.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.InvalidDataError.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.InvalidDataError.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.CancelMentorAppointmentRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.CancelMentorAppointmentRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.CancelMentorAppointmentResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.CancelMentorAppointmentResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetConfigurationRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetConfigurationRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetConfigurationResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetConfigurationResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetAvailableActionsRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetAvailableActionsRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetAvailableActionsResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetAvailableActionsResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurrentTimeRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurrentTimeRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurrentTimeResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetUserCurrentTimeResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.CancelAllUserAppointmentsRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.CancelAllUserAppointmentsRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.CancelAllUserAppointmentsResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.CancelAllUserAppointmentsResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.SuspendUserVLabRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.SuspendUserVLabRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.SuspendUserVLabResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.SuspendUserVLabResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetEndDate4CurrentDevaInsRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetEndDate4CurrentDevaInsRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetEndDate4CurrentDevaInsResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetEndDate4CurrentDevaInsResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetResourceTypesRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetResourceTypesRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetResourceTypesResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetResourceTypesResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsWithEncryptedPasswordRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsWithEncryptedPasswordRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsWithEncryptedPasswordResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.ScheduleUserAppointmentsWithEncryptedPasswordResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.RefreshVMResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.CreateUserProfileResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.IsResourceAvailableRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.IsResourceAvailableRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.IsResourceAvailableResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.IsResourceAvailableResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.AddHostRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.AddHostRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.AddHostResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.AddHostResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetMentorAppointmentsRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetMentorAppointmentsRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetMentorAppointmentsResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetMentorAppointmentsResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetUserAppointmentsRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetUserAppointmentsRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetUserAppointmentsResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetUserAppointmentsResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.ReserveResourceRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.ReserveResourceRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.ReserveResourceResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.ReserveResourceResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetVMInsInfoRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetVMInsInfoRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetVMInsInfoResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetVMInsInfoResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileWithEncryptedPasswordRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileWithEncryptedPasswordRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileWithEncryptedPasswordResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileWithEncryptedPasswordResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.EditUserProfileResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.CheckinRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.CheckinRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.CheckinResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.CheckinResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.CancelAllMentorAppointmentsRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.CancelAllMentorAppointmentsRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.CancelAllMentorAppointmentsResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.CancelAllMentorAppointmentsResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetUserResourceTypesRequest.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetUserResourceTypesRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (edu.fiu.cis.acrl.virtuallabs.ws.GetUserResourceTypesResponse.class.equals(type)){
                
                           return edu.fiu.cis.acrl.virtuallabs.ws.GetUserResourceTypesResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
        } catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
           return null;
        }



    

        /**
        *  A utility method that copies the namepaces from the SOAPEnvelope
        */
        private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env){
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
        while (namespaceIterator.hasNext()) {
        org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
        returnMap.put(ns.getPrefix(),ns.getNamespaceURI());
        }
        return returnMap;
        }

        private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
        org.apache.axis2.AxisFault f;
        Throwable cause = e.getCause();
        if (cause != null) {
            f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
        } else {
            f = new org.apache.axis2.AxisFault(e.getMessage());
        }

        return f;
    }

        }//end of class
    