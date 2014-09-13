package edu.fiu.cis.acrl.virtuallabs.server.mail;

// import edu.fiu.cis.acrl.common.types.ScheduledEvent;

import java.io.File;
import java.io.IOException;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.TimeZone;

import java.text.SimpleDateFormat;

import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Transport;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.AddressException;

/**
 * Send emails to new users
 */
public class CalendarMail {

	private static final String TEMPLATE_EXT = ".template";
	private static final String PROPERTIES_EXT = ".properties";

	private static final String EXAM_ALLOCATION_TEMPLATE = "exam_allocation";
	private static final String EXAM_CANCELLATION_TEMPLATE = "exam_cancellation";

	private static final String PROTOCOL = "smtp";
	private static final String SENDER_STRING = "Masoud Sadjadi <sadjadi@cs.fiu.edu>";
	private static final String ADMIN_STRING = "Certification Admin <sadjadi@cs.fiu.edu>";

	private static final String CONTENT_TYPE = "text/plain";


	private String host;
	private String user;
	private String password;
	private File templateDir;

	private Session session;
	private Address fromAddress;
	private Address adminAddress;

	private boolean notifyAdmin;

	/**
	 * Constructs the CalendarMail object and initializes the java mail session
	 */
	public CalendarMail(File templateDir, boolean notifyAdmin) {
		/*
		this.templateDir = templateDir;

		// Initialize session
		Properties systemProperties = System.getProperties();

		this.session = Session.getInstance(systemProperties, null);

		try {

			this.fromAddress = new InternetAddress(SENDER_STRING);
			this.adminAddress = new InternetAddress(ADMIN_STRING);

		}
		catch(AddressException e) {

			throw new Error(
					"Could not create default mail addresses: " + 
					e.getMessage());

		}

		this.notifyAdmin = notifyAdmin;
*/
	}

	/**
	 * Send a message to a given recipient by loading a template from disk and populating it
	 *
	 */
	/*
	private void sendMessageFromTemplate(
			Address recipient, 
			String template, 
			Properties context) 
	throws MessagingException, IOException {
		// Build template file
		File templateFile = new File(templateDir, template + TEMPLATE_EXT);

		// Get email body by replacing variables in the template text
		String body = "";
		body = TemplateReader.processTemplate(templateFile, context);

		Message msg = new MimeMessage(session);

		msg.setFrom(fromAddress);
		msg.setRecipient(Message.RecipientType.TO, recipient);

		// send BCC to the administrator if the option was set up
		if (notifyAdmin) {
	
			msg.setRecipient(Message.RecipientType.CC, adminAddress);
		
		}

		// msg.setSubject("Test email from certification");
		msg.setSubject("Confirmation from IT Automation scheduling system.");
		msg.setContent(body, CONTENT_TYPE);

		Transport transport = session.getTransport(PROTOCOL);

		transport.connect();
		transport.sendMessage(msg, msg.getAllRecipients());

		transport.close();	

	}
*/
	/**
	 * Send an exam allocation success mail.
	 *
	 */
/*
	public void sendExamAllocationMail(
			ExamAttempt attempt, 
			ExamTime preparationTime, 
			ExamTime orientationTime, 
			String password) 
	throws CalendarMailException {


		User user = attempt.getUser();
		// System.out.println("user: " + user);

		try {
			Address recipient = buildAddress(user);

			// System.out.println("attempt: " + attempt);
			// System.out.println("quiz: " + attempt.getQuiz());
			// System.out.println("slot: " + attempt.getSlot());
			// System.out.println("prep time: " + preparationTime);
			// System.out.println("orient time: " + orientationTime);

			// Create the context
			Properties context = new Properties();
			context.setProperty("student-firstname", user.getFirstName());
			context.setProperty("student-lastname", user.getLastName());
			context.setProperty("from-email", SENDER_STRING);
			context.setProperty("quiz-name", attempt.getQuiz().getName());
			// context.setProperty("begin-time", attempt.getSlot().getStart().getTime().toString());
			SimpleDateFormat formatter = new SimpleDateFormat( "EEE, MMM'.' dd, yyyy 'at' hh:mm aaa z" );
			formatter.setTimeZone( TimeZone.getTimeZone( user.getTimeZoneId() ));
			String beginTime = formatter.format( attempt.getSlot().getStart().getTime() );
			context.setProperty("begin-time", beginTime);
			// context.setProperty("end-time", attempt.getSlot().getEnd().getTime().toString());
			String endTime = formatter.format( attempt.getSlot().getEnd().getTime() );
			context.setProperty("end-time", endTime);
			context.setProperty("preparation-time", preparationTime.toHumanReadableString());
			context.setProperty("username", user.getUserName().toLowerCase());
			context.setProperty("orientation-time", orientationTime.toHumanReadableString());

			String passwordMessage;
			if (password == null) {

				passwordMessage = "(previously generated password)";
			
			}
			else {
			
				passwordMessage = password;
			
			}

			context.setProperty("password", passwordMessage);

			// send the email
			sendMessageFromTemplate(recipient, EXAM_ALLOCATION_TEMPLATE, context);

		}
		catch(AddressException ae) {

			throw new CalendarMailException("Could not construct user address: " + ae.getMessage());
		
		}
		catch(IOException ie) {
		
			throw new CalendarMailException("Could not find the email template: " + ie.getMessage());
		
		}
		catch(MessagingException me) {

			throw new CalendarMailException("Error sending email: " + me.getMessage());
		
		}

	}
*/

	/** 
	 * Send an exam cancellation success mail
	 *
	 */
/*
	public void sendExamCancellationMail(
			ExamAttempt attempt) 
	throws CalendarMailException {

		try{

			Address recipient = buildAddress(attempt.getUser());

			Properties context = new Properties();
			context.setProperty("student-firstname", attempt.getUser().getFirstName());
			context.setProperty("student-lastname", attempt.getUser().getLastName());
			context.setProperty("quiz-name", attempt.getQuiz().getName());
			// context.setProperty("begin-time", attempt.getSlot().getStart().getTime().toString());
			SimpleDateFormat formatter = new SimpleDateFormat( "EEE, MMM'.' dd, yyyy 'at' hh:mm aaa z" );
			formatter.setTimeZone( TimeZone.getTimeZone( attempt.getUser().getTimeZoneId() ));
			String beginTime = formatter.format( attempt.getSlot().getStart().getTime() );
			context.setProperty("begin-time", beginTime);
			// context.setProperty("end-time", attempt.getSlot().getEnd().getTime().toString());
			String endTime = formatter.format( attempt.getSlot().getEnd().getTime() );
			context.setProperty("end-time", endTime);
			context.setProperty("from-email", SENDER_STRING);

			// send the email
			sendMessageFromTemplate(recipient, EXAM_CANCELLATION_TEMPLATE, context);

		}
		catch(AddressException ae) {
			
			throw new CalendarMailException("Could not construct user address: " + ae.getMessage());
		
		}
		catch(IOException ie) {
		
			throw new CalendarMailException("Could not find the email template: " + ie.getMessage());
		
		}
		catch(MessagingException me) {
	
			throw new CalendarMailException("Error sending email: " + me.getMessage());
		
		}

	}
*/
	/**
	 * Build an email address of the form "First Last < email >" for a given user
	 *
	 */
/*
	private Address buildAddress(
			User user) 
	throws AddressException {
	
		return new InternetAddress(user.getFirstName() + " " + user.getLastName() + "<" + user.getEmail() + ">");
	
	}
*/

	/**** FOR TESTING PURPOSES ****/
/*	
	public static void main(String [] args) throws Exception  {


		CalendarMail mail = new CalendarMail(new File("/tmp/templates"), true);

		Calendar start = new GregorianCalendar(2009, 9, 19, 17, 0);
		Calendar end = new GregorianCalendar(2009, 9, 19, 19, 0);

		User user = new User("david", "David", "Villegas", "david@no-sync.com", "192.168.0.1", "ES", "", "Barcelona", "GMT+6", "FIU", "");
		ExamSlot slot = new ExamSlot(start, end, false);
		Quiz quiz = new Quiz(1, "Kaseya VSA Basic Quiz", null);
		ExamAttempt attempt = new ExamAttempt(1, slot, quiz, user, ExamAttempt.AttemptState.SCHEDULED, "http://64.77.83.36/moodle", Calendar.getInstance());

		ExamTime preparationTime = new ExamTime(1,30);
		ExamTime orientationTime = new ExamTime(0,15);

		//mail.sendExamAllocationMail(user, attempt, new ExamTime(1,0), new ExamTime(0,30));

		//File templateFile = new File("/tmp/templates", "exam_allocation.template");
		//Properties context = new Properties();
		//context.setProperty("student-firstname", user.getFirstName());
		//context.setProperty("student-lastname", user.getLastName());
		//context.setProperty("from-email", SENDER_STRING);
		//context.setProperty("quiz-name", attempt.getQuiz().getName());
		//context.setProperty("begin-time", attempt.getSlot().getStart().getTime().toString());
		//context.setProperty("end-time", attempt.getSlot().getEnd().getTime().toString());
		//context.setProperty("preparation-time", preparationTime.toHumanReadableString());
		//context.setProperty("username", user.getUserName().toLowerCase());
		//context.setProperty("password", "here-goes-the-password");
		//context.setProperty("orientation-time", orientationTime.toHumanReadableString());
		//context.setProperty("student-firstname", user.getFirstName());


		File templateFile = new File("/tmp/templates", "exam_cancellation.template");
		Properties context = new Properties();
		context.setProperty("student-firstname", attempt.getUser().getFirstName());
		context.setProperty("student-lastname", attempt.getUser().getLastName());
		context.setProperty("quiz-name", attempt.getQuiz().getName());
		context.setProperty("begin-time", attempt.getSlot().getStart().getTime().toString());
		context.setProperty("end-time", attempt.getSlot().getEnd().getTime().toString());
		context.setProperty("from-email", SENDER_STRING);

		System.out.println(TemplateReader.processTemplate(templateFile, context));

	}
*/
}
