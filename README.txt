This file is no longer in use! 7/13/2010

* Virtual labs
--------------
/home/portal/virtuallabs/

	
	+--- virtuallabs.wsdl		VirtualLabs WS description file

	+--- ws/					Generated code for VirtualLabs WS

Steps to re-generate the WS

	* Move existing ws/ to ws.old/ 	(mv ws ws.old)
	* Generate the code 			(./genclient.sh)
	* Copy skeleton					(cp ws.old/src/ws/VirtualLabsSkeleton.java ws/src/ws)
	
	*If new calls has been added, add 'class="ws.VirtualLabsSkeleton"' in the sew service xml file 
	as shown below. If not then just copy the service.xml (cp ws.old/resources/services.xml ws/resources)
	
		<serviceGroup>
   			 <service name="VirtualLabs" class="ws.VirtualLabsSkeleton">
        			<messageReceivers>

	
	
	* Copy translators				(mv ws.old/src/edu/fiu/cis/acrl/virtuallabs/translators ws/src/edu/fiu/cis/acrl/virtuallabs)
	* Copy Appointments class		(cp  ws.old/src/edu/fiu/cis/acrl/virtuallabs/Appointment.java ws/src/edu/fiu/cis/acrl/virtuallabs/
)
	
 	+---./deployall.sh 		This compiles and erases the .aar file  from the axis lib)

	+--- deploy.sh			Copy the VirtualLabs.aar to $AXIS2_HOME/repository/services



