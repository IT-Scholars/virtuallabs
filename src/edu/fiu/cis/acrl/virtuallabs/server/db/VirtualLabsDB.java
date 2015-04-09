package edu.fiu.cis.acrl.virtuallabs.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import edu.fiu.cis.acrl.vescheduler.server.Host;
import edu.fiu.cis.acrl.vescheduler.server.VESchedulerSettings;
import edu.fiu.cis.acrl.vescheduler.server.VMInstance;
import edu.fiu.cis.acrl.vescheduler.server.db.VESchedulerDB;
import edu.fiu.cis.acrl.virtuallabs.server.*;
import edu.fiu.cis.acrl.virtuallabs.server.tools.crypt.Crypt;
import edu.fiu.cis.acrl.virtuallabs.server.tools.debug.DebugTools;
import edu.fiu.cis.acrl.virtuallabs.ws.TodoType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class VirtualLabsDB {

	// Debug level for this class
	private static int DEBUG_LEVEL = 2;
	
	private VirtualLabsSettings settings;
	private Connection conn;

	/**
	 * Default Constructor.
	 */
	public VirtualLabsDB() {
		
		settings= VirtualLabsSettings.instance();
		connect(
				settings.getDbUser(), 
				settings.getDbPassword(), 
				settings.getDbHost(), 
				settings.getDbName());
		
	}

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private VirtualLabsDB _instance = null;
	
	/**
	 * @return The unique instance of this class.
	 */
	static public VirtualLabsDB instance() {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - instance] Inside!");
		
		if(null == _instance) {
			_instance = new VirtualLabsDB();
	    }
	    
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - instance] Ready to get out!");
		
		return _instance;
	   
	}

	public boolean connect(String user, String password, String host, String database) {

		try {
			
			Class.forName("org.postgresql.Driver");

			conn = DriverManager.getConnection("jdbc:postgresql://" + host + "/" + database, user, password);

		}

		catch(ClassNotFoundException cnfe) {

			System.err.println("Could not find JDBC driver");
			return false;
		
		}
		catch(SQLException se) {
		
			se.printStackTrace();
			return false;

		}


		return true;
	}


	public void close() {

		try {
			if(conn != null) {
				conn.close();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Get Appointments(list of appointments)
	 **/

	public List<DbAppointment> getAppointments(String affiliationId){

		DbAppointment app = new DbAppointment();
		List<DbAppointment> apps = new ArrayList<DbAppointment>();
		try {

			PreparedStatement ps = 
				conn.prepareStatement(
						"SELECT * FROM appointments WHERE affiliation_id = ?" );
			ps.setString(1, affiliationId);	 	    
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				app = 
					new DbAppointment(
							rs.getString("sch_id"), 
							rs.getString("quota_id"),
							rs.getString("affiliation_id"),
							rs.getString("username"),
							rs.getString("course"),
							rs.getString("resource_type"),
							rs.getBoolean("active"),
							null,
							null);					 	
				apps.add(app);						 	

			}

		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new Error(e.getMessage());
		}



		return apps;


	}



	/**
	 * Get Appointment(single appointment)
	 **/

	public DbAppointment getAppointment(String schId){


		// System.out.println("[DB.getAppointment("+schId+")]");
		DbAppointment app = null;

		try {

			PreparedStatement ps = 
				conn.prepareStatement("SELECT * FROM appointments WHERE sch_id = ?" );
			ps.setString(1, schId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				app = 
					new DbAppointment(
							rs.getString("sch_id"),
							rs.getString("quota_id"),
							rs.getString("affiliation_id"),
							rs.getString("username"),
							rs.getString("course"),
							rs.getString("resource_type"),
							rs.getBoolean("active"),
							null,
							null);					 	
				
			}

		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new Error(e.getMessage());
		}

		return app;

	}


	/**
	 * Set Appointment (single appointment)
	 **/

	public void setAppointment(DbAppointment app) {

		try {

			PreparedStatement ps = 
				conn.prepareStatement("UPDATE appointments SET " +
						"quota_id = ?," +
						"affiliation_id = ?," +
						"username = ?," +
						"course = ?," +
						"resource_type = ?," +
						"active = ? " +
						"WHERE sch_id = ?" );
			ps.setString(1, app.getQuotaId());
			ps.setString(2, app.getAffiliationId());
			ps.setString(3, app.getUsername());
			ps.setString(4, app.getCourse());
			ps.setString(5, app.getResourceType());
			ps.setBoolean(6, app.isActive());
			ps.setString(7, app.getSchId());

			ps.execute();

			ps.close();
			
		}
		catch(SQLException e) {
			
			e.printStackTrace();
			throw new Error(e.getMessage());
		
		}

		return;

	}

	public boolean cancelAppointment(String schId) {

		// System.out.println("[DB.getAppointment("+schId+")]");
		boolean retVal = false;

		DbAppointment app = getAppointment(schId);
		if (app != null) {
			
			app.setActive(false);
			setAppointment(app);
			retVal = true;

		}
		
		return retVal;

	}

	/**
	 * 
	 * Schedule Appointment
	 * @param quotaId 
	 */

	public void scheduleAppointment(DbAppointment app){
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - scheduleAppointment] Inside! \n" + app.toString());

		try {
			
			PreparedStatement ps = 
				conn.prepareStatement(
						"INSERT INTO appointments  " +
						"(sch_id, quota_id, affiliation_id, username, course, resource_type, active) " +
						"values(?,?,?,?,?,?,?)");
			ps.setString(1, app.getSchId());
			ps.setString(2, app.getQuotaId());
			ps.setString(3, app.getAffiliationId());
			ps.setString(4, app.getUsername());
			ps.setString(5, app.getCourse());
			ps.setString(6, app.getResourceType());
			ps.setBoolean(7, app.isActive());

			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - scheduleAppointment] ps: " + ps);

			ps.executeUpdate();
			ps.close();

		}
		catch(SQLException e) {
			
			e.printStackTrace();
			throw new Error("[DB.scheduleAppointment] SQLException: "+e.getMessage());

		}
		catch(Exception ex) {
			
			ex.printStackTrace();
			throw new Error("[DB.scheduleAppointment] "+ex.getMessage());

		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - scheduleAppointment] Ready to get out!");

	}

	/**
	 * 
	 * Modify Appointment
	 */

	// TODO: Does not seem right!!!
	public void modifyAppointment(DbAppointment app, String id) {

		System.out.println("[DB.modifyApointment("+app.getSchId()+")]");

		try{
			scheduleAppointment(app);			
			System.out.println("[DB.modifyApointment("+app.getSchId()+")]] Instance modified");


		}catch(Exception e)
		{
			e.printStackTrace();
			throw new Error("[DB.modifyApointment("+app.getSchId()+")]"+e.getMessage());

		}


	}


	/**
	 * Lookup if the user exists in the DB
	 */
	public boolean userExists(String user) {

		boolean exists = false;
		try {

			// Try to find the user by username
			PreparedStatement ps = conn.prepareStatement(
			"SELECT * FROM user_profile WHERE username = ?");
			ps.setString(1, user);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				exists = true;

			}

			rs.close();
			ps.close();

		}
		catch(SQLException e) {

			e.printStackTrace();
			throw new Error("[userExists-db "+user.toString()+"] SQLException" +e.getMessage());

		}

		return exists;

	}

	/**
	 * Lookup if the user exists in the DB
	 */
	public boolean appointmentExists(DbAppointment app) {

		boolean exists = false;
		try {
			// Try to find the user by username
			PreparedStatement ps = 
				conn.prepareStatement("SELECT * FROM appointments WHERE sch_id = ? and active = 't'");
			ps.setString(1, app.getSchId());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				exists = true;
			}


			rs.close();
			ps.close();


		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return exists;
	}

	/**
	 * Lookup if the course exists in the DB
	 */
	/*
    public boolean courseExists(String course) {

		boolean exists = false;
		try {
		    // Try to find the user by username
		    PreparedStatement ps = conn.prepareStatement("SELECT * FROM course WHERE course_name = ?");
		    ps.setString(1, course);

		    ResultSet rs = ps.executeQuery();

		    if (rs.next()) {

				exists = true;
			}


		    rs.close();
		    ps.close();

		}
		catch(SQLException e) {
		    e.printStackTrace();
		}

		return exists;
    }

	 */



	/**
	 * Retrieve a user's role
	 */
	public String getUserRole(String user ) {

		String role = null;

		try {
			// Try to find the user by username
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM user_profile WHERE username = ?");
			ps.setString(1, user);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				role = rs.getString("user_role");
			}


			rs.close();
			ps.close();

		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return role;

	}


	/**
	 * Update a user profile
	 */
	public void updateUser(User user) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - updateUser] Inside!");

		// Make sure the user does exist
		if(userExists(user.getUserName())) 
		{

			try {
				// update it
				// the only files that should not be updated is the username
				// PreparedStatement ps = conn.prepareStatement("UPDATE student SET first_name = ?, last_name = ?, email = ?, ip_address = ?, country = ?, state = ?, city = ?, time_zone_id = ?, company_name = ? WHERE username = ?");
				String sqlStatement = "UPDATE user_profile SET ";
				boolean first = true;

				if (user.getPassword() != null)
					if (user.getPassword().length() > 0) {
						if (!first)
							sqlStatement += ", ";
						else
							first = false;

						sqlStatement += "password = ?";
					}

				if (user.getFirstName() != null)
					if (user.getFirstName().length() > 0) {
						if (!first)
							sqlStatement += ", ";
						else
							first = false;

						sqlStatement += "first_name = ?";
					}

				if (user.getLastName() != null)
					if (user.getLastName().length() > 0) {
						if (!first)
							sqlStatement += ", ";
						else
							first = false;

						sqlStatement += "last_name = ?";
					}

				if (user.getEmailAddress() != null)
					if (user.getEmailAddress().length() > 0) {
						if (!first)
							sqlStatement += ", ";
						else
							first = false;

						sqlStatement += "email_address = ?";
					}

				if (user.getUserRole() != null)
					if (user.getUserRole().length() > 0) {
						if (!first)
							sqlStatement += ", ";
						else
							first = false;

						sqlStatement += "user_role = ?";
					}

				if (user.getTimeZone() != null)
					if (user.getTimeZone().length() > 0) {
						if (!first)
							sqlStatement += ", ";
						else
							first = false;

						sqlStatement += "time_zone_id = ?";
					}

				if (user.getContactInfo() != null)
					if (user.getContactInfo().length() > 0) {
						if (!first)
							sqlStatement += ", ";
						else
							first = false;

						sqlStatement += "contact_info = ?";
					}

				// check if nothing needs to be updated
				if (first) return;

				sqlStatement += " WHERE username = ?";
				// System.out.println("[DB.updateUser()] - sqlStatement: " + sqlStatement);

				PreparedStatement ps = conn.prepareStatement(sqlStatement);

				int counter = 1; 

				if (user.getPassword() != null) 
					if (user.getPassword().length() > 0) {

						ps.setString(counter, user.getPassword());
						counter++;
					}

				if (user.getFirstName() != null) 
					if (user.getFirstName().length() > 0) {

						ps.setString(counter, user.getFirstName());
						counter++;
					}

				if (user.getLastName() != null) 
					if (user.getLastName().length() > 0) {

						ps.setString(counter, user.getLastName());
						counter++;
					}

				if (user.getEmailAddress() != null) 
					if (user.getEmailAddress().length() > 0) {

						ps.setString(counter, user.getEmailAddress());
						counter++;
					}

				if (user.getUserRole() != null)
					if (user.getUserRole().length() > 0) {

						ps.setString(counter, user.getUserRole());
						counter++;
					}

				if (user.getTimeZone() != null)
					if (user.getTimeZone().length() > 0) {

						ps.setString(counter, user.getTimeZone());
						counter++;
					}

				if (user.getContactInfo() != null)
					if (user.getContactInfo().length() > 0) {

						ps.setString(counter, user.getContactInfo());
						counter++;
					}

				ps.setString(counter, user.getUserName());

				ps.executeUpdate();
				ps.close();

			}
			catch(SQLException e) {
				e.printStackTrace();
				throw new Error("[DB.updateUser()] "+e.getMessage());
			}
		}else
		{

			throw new Error("Record does not exist");

		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - updateUser] Ready to get out!");

	}

	/**
	 * Eliminate plain text password from the user profile
	 */
	public void eliminatePlaintextPassword(String username) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - eliminatePlaintextPassword] Inside!");

		try {
			
			String sqlStatement = "UPDATE user_profile SET password='' WHERE username=?";
			PreparedStatement ps = conn.prepareStatement(sqlStatement);
			ps.setString(1, username);

			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - eliminatePlaintextPassword] ps:" + ps);

			ps.executeUpdate();
			ps.close();

		} catch(SQLException e) {
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - eliminatePlaintextPassword] "
					+ "failed!");
			e.printStackTrace();
		} catch(Exception ex) {
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - eliminatePlaintextPassword] "
					+ "failed!");
			ex.printStackTrace();
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - eliminatePlaintextPassword] Ready to get out!");

	}

	/**
	 * Set the user's cached password temporarily
	 * If password is null or empty, it will remove the cached record
	 * else if a cached record already exists and the password is not the same, it updates the record
	 * else it will add a cached record
	 */
	public void setUserCachedPassword(String userName, String password) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - setUserCachedPassword] Inside!");

		if (password != null) {
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - setUserCachedPassword] "
					+ "password: " + password);
			String encryptedPassword = Crypt.encrypt(password);
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - setUserCachedPassword] "
					+ "encryptedPassword: " + encryptedPassword);
			String cachedPassword = getUserCachedPassword(userName);
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - setUserCachedPassword] "
					+ "cachedPassword: " + cachedPassword);
			try {
				if((password == null) || (password == "")) {
					// delete the cached record
					PreparedStatement ps = conn.prepareStatement(
							"DELETE FROM cached_password " +
							"	WHERE username=?");				
					ps.setString(1, userName);
					DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - setUserCachedPassword] ps:" + ps);
					ps.execute();
					ps.close();

				} else if (cachedPassword != null) {
					// else if a record for the user exists, then update the record
					PreparedStatement ps = conn.prepareStatement(
							"UPDATE cached_password SET password = ? WHERE username = ?");
					ps.setString(1, encryptedPassword);
					ps.setString(2, userName);
					DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - setUserCachedPassword] ps:" + ps);
					ps.executeUpdate();
					ps.close();
				} else {
					// else add it
					PreparedStatement ps = conn.prepareStatement(
							"INSERT INTO " +
									"	cached_password(username, password) " +
							"	VALUES(?,?)");
					ps.setString(1, userName);
					ps.setString(2, encryptedPassword);
					DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - setUserCachedPassword] ps:" + ps);
					ps.executeUpdate();
					ps.close();				
				}
			}
			catch(SQLException e) {
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - setUserCachedPassword] "
						+ "failed!");
				e.printStackTrace();
			}
			catch(Exception ex) {
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - setUserCachedPassword] "
						+ "failed!");
				ex.printStackTrace();
			}
		} else {
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - setUserCachedPassword] "
					+ "password is null!");
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - setUserCachedPassword] Ready to get out!");

	}

	public String getUserCachedPassword(String userName) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUserCachedPassword] Inside!");
		
		String password = null;

		try {

			// Try to find the cached password by userName
			PreparedStatement ps = conn.prepareStatement(
				"SELECT * FROM cached_password WHERE username = ?");
			ps.setString(1, userName);

			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUserCachedPassword] ps:" + ps);
			
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				String encyptedPassword = rs.getString("password");
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUserCachedPassword] "
						+ "encyptedPassword: " + encyptedPassword);
				password = Crypt.decrypt(encyptedPassword);
			}

			rs.close();
			ps.close();

		}
		catch(Exception e) {
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUserCachedPassword] "
					+ "failed!");
			e.printStackTrace();
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUserCachedPassword] "
				+ "password: " + password);

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUserCachedPassword] Ready to get out!");
		
		return password;

	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<UserCashedPassword> getUserCachedPassword() {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUsernamesFromUserCachedPassword] Inside!");
		
		ArrayList<UserCashedPassword> userCashedPasswords = new ArrayList<UserCashedPassword>();
		
		try {

			PreparedStatement ps = conn.prepareStatement(
				"SELECT * FROM cached_password");

			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUsernamesFromUserCachedPassword] ps:" + ps);
			
			ResultSet rs = ps.executeQuery();

			
			while (rs.next()) {

				String username = rs.getString("username");
				String encryptedPassword = rs.getString("password");
				Timestamp timestamp = rs.getTimestamp("update_ts");
				Calendar updateTs = Calendar.getInstance();
				updateTs.setTimeInMillis(timestamp.getTime());
				
				UserCashedPassword userCashedPassword = 
						new UserCashedPassword(
								username,
								encryptedPassword,
								updateTs);
								
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUsernamesFromUserCachedPassword] "
						+ "userCashedPassword: " + userCashedPassword);
				
				userCashedPasswords.add(userCashedPassword);
			}

			rs.close();
			ps.close();

		}
		catch(Exception e) {
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUsernamesFromUserCachedPassword] "
					+ "failed!");
			e.printStackTrace();
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUsernamesFromUserCachedPassword] Ready to get out!");
		
		return userCashedPasswords;

	}

	/**
	 * 
	 * @param username
	 */
	public void delUserCachedPassword(String username) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - delUserCachedPassword] Inside!");
		
		try {

			PreparedStatement ps = 
				conn.prepareStatement("DELETE FROM cached_password WHERE username = ?");
			ps.setString(1, username);
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - delUserCachedPassword] ps: " + ps);
			
			ps.execute();
			ps.close();
						
		} catch (SQLException e) {
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - delUserCachedPassword] SQL Exception " 
				+ e.getMessage());
			e.printStackTrace();
			throw new Error(e.getMessage());
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - delUserCachedPassword] Ready to get out!");
		
	}

	/**
	 * Create a user
	 */
	public void addUser(User user) {

		// Make sure the user doesn't exist
		if(userExists(user.getUserName())) {
			throw new Error("[addUser-db] User "+user.getUserName()+" already exists");
		}

		try {

			// add it
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO " +
					"	user_profile(username, password, first_name, last_name," +
					"		email_address, user_role, time_zone_id, contact_info) " +
					"	VALUES(?,?,?,?,?,?,?,?)");

			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFirstName());
			ps.setString(4, user.getLastName());
			ps.setString(5, user.getEmailAddress());
			ps.setString(6, user.getUserRole().toUpperCase());
			ps.setString(7, user.getTimeZone());
			ps.setString(8, user.getContactInfo());

			ps.executeUpdate();
			ps.close();

		}
		catch(SQLException e) {

			e.printStackTrace();
			throw new Error("[addUser-db: "+user.toString()+"] SQL  exception"+e.getMessage());

		}
		catch(Exception ex) {

			ex.printStackTrace();
			throw new Error("[addUser-db: "+user.toString()+"] Exception"+ex.getMessage());

		}

	}

	public User getUser(String userName) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUser] Inside!");
		
		User user = null;

		try {

			// Try to find the user by username
			PreparedStatement ps = conn.prepareStatement(
				"SELECT * FROM user_profile WHERE UPPER(username) = UPPER(?)");
			ps.setString(1, userName);

			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUser] ps:" + ps);
			
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				String realUserName = rs.getString("username");
				String password = rs.getString("password");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String emailAddress = rs.getString("email_address");
				String userRole = rs.getString("user_role");
				String timeZoneId = rs.getString("time_zone_id");
				String contactInfo = rs.getString("contact_info");

				String userCashedPassword = getUserCachedPassword(userName);
				if (userCashedPassword != null)
					password = userCashedPassword;
				
				user = new User(
						realUserName,
						password,
						firstName,
						lastName,
						emailAddress,
						userRole,
						timeZoneId,
						contactInfo);
			
			}

			rs.close();
			ps.close();

		}
		catch(Exception e) {

			e.printStackTrace();

		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUser] "
				+ "user: " + user);
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUser] Ready to get out!");
		
		return user;

	}

	public User getUserByEmail(String email) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUserByEmail] Inside!");
		
		User user = null;

		try {

			// Try to find the user by username
			PreparedStatement ps = conn.prepareStatement(
				"SELECT * FROM user_profile WHERE UPPER(email_address) = UPPER(?)");
			ps.setString(1, email);

			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUserByEmail] ps:" + ps);
			
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				String userName = rs.getString("username");
				String password = rs.getString("password");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String emailAddress = rs.getString("email_address");
				String userRole = rs.getString("user_role");
				String timeZoneId = rs.getString("time_zone_id");
				String contactInfo = rs.getString("contact_info");

				String userCashedPassword = getUserCachedPassword(userName);
				if (userCashedPassword != null)
					password = userCashedPassword;
				
				user = new User(
						userName,
						password,
						firstName,
						lastName,
						emailAddress,
						userRole,
						timeZoneId,
						contactInfo);
			
			}

			rs.close();
			ps.close();

		}
		catch(Exception e) {

			e.printStackTrace();

		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUserByEmail] "
				+ "user: " + user);
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUserByEmail] Ready to get out!");
		
		return user;

	}

	public boolean delUser(String userName) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - delUser] Inside!");
		
		User user = getUser(userName);

		if (user == null) 
			return false;
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(
					"DELETE FROM appointments " +
					"	WHERE username=?");
			
			ps.setString(1, userName);
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUser] ps:" + ps);
			
			ps.execute();
			
			ps.close();
			
			ps = conn.prepareStatement(
					"DELETE FROM user_ve_instances " +
					"	WHERE username=?");
			
			ps.setString(1, userName);
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUser] ps:" + ps);
			
			ps.execute();
			
			ps.close();
			
			ps = conn.prepareStatement(
					"DELETE FROM user_course " +
					"	WHERE username=?");
			
			ps.setString(1, userName);
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUser] ps:" + ps);
			
			ps.execute();
			
			ps.close();
			
			ps = conn.prepareStatement(
					"DELETE FROM user_profile " +
					"	WHERE username=?");
			
			ps.setString(1, userName);
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUser] ps:" + ps);
			
			ps.execute();
			
			ps.close();
			
		}
		catch(Exception e) {

			e.printStackTrace();

		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - delUser] Ready to get out!");
		
		return true;

	}

	/**
	 * Get user resources according to his role
	 */
	public Collection<String> getResources(String user) {

		ArrayList<String> resources = new ArrayList<String>();


		// Make sure the user doesn't exist
		if(userExists(user)) {

			try {

				String role = getUserRole(user);
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM role_resource WHERE user_role = ? ");
				ps.setString(1, role);

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					resources.add(rs.getString("resource"));
				}


			}
			catch(SQLException e) {
				e.printStackTrace();
				throw new Error(e.getMessage());
			}
		}

		return resources;

	}


	/**
	 * Enroll user in course
	 */
	public String enrollInCourse(String user, String course, boolean flag) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - enrollInCourse] Inside!");
		
		// Make sure the user doesn't exist
		if (!userExists(user)) {
			
			throw new Error("User "+user+" does not exist");
		
		}

		try {

			if (flag) {
				
				PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM user_course WHERE username = ? and course = ?");
				ps1.setString(1, user);
				ps1.setString(2, course);

				DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - enrollInCourse] " +
						"ps1: " + ps1);
				
				ResultSet rs1 = ps1.executeQuery();

				if (rs1.next()) {

					return "Record already exists!";

				}

				PreparedStatement ps = 
					conn.prepareStatement(
					"INSERT INTO user_course(username, course) VALUES(?,?)");

				ps.setString(1, user);
				ps.setString(2, course);

				DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - enrollInCourse] " +
						"ps: " + ps);
				
				ps.executeUpdate();
				ps.close();
				return "Success!";
			
			}
			else {
				
				PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM user_course WHERE username = ? and course = ?");
				ps1.setString(1, user);
				ps1.setString(2, course);

				DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - enrollInCourse] " +
						"ps1: " + ps1);
				
				ResultSet rs1 = ps1.executeQuery();

				if (!rs1.next()) {

					return "Record does not exist!";

				}

				PreparedStatement ps = 
					conn.prepareStatement(
					"DELETE FROM user_course WHERE username = ? and course = ?");

				ps.setString(1, user);
				ps.setString(2, course);

				DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - enrollInCourse] " +
						"ps: " + ps);
				
				ps.execute();
				ps.close();
			
				ps = 
					conn.prepareStatement("DELETE FROM appointments WHERE username = ? and course = ?");
				ps.setString(1, user);
				ps.setString(2, course);
				
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - enrollInCourse] ps: " + ps);
				
				ps.execute();
				ps.close();
				
				ps = 
					conn.prepareStatement("DELETE FROM user_ve_instances WHERE username = ? and course = ?");
				ps.setString(1, user);
				ps.setString(2, course);
				
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - enrollInCourse] ps: " + ps);
				
				ps.execute();
				ps.close();
				
			}

		}
		catch(SQLException e) {
		
			e.printStackTrace();
			throw new Error(e.getMessage());
		
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - enrollInCourse] Ready to get out!");
		
		return "Success!";
	}

	/**
	 * Get user courses
	 */
	public Collection<String> getCourses(String user) {

		ArrayList<String> courses = new ArrayList<String>();
		// Make sure the user doesn't exist

		if(userExists(user)) {

			try {

				PreparedStatement ps = null;
				
				if (user.equals(VirtualLabs.ALL_STUDENTS)) {
					
					ps = conn.prepareStatement("SELECT course FROM user_course GROUP BY course ORDER BY course");
				
				} else {

					ps = conn.prepareStatement("SELECT course FROM user_course WHERE username = ? ");
					ps.setString(1, user);
					
				}				

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {

					courses.add(rs.getString("course"));
				
				}

			}
			catch(SQLException e) {
			
				e.printStackTrace();
				throw new Error(e.getMessage());
			
			}

		}

		return courses;

	}

	//Ve_Instances methods

	public String getVEType(String course, String resourceType) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getVEInstance] Inside!");
		
		String path = null;

		try {

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ve_types WHERE course = ? and resource_type = ?" );
			ps.setString(1, course);
			ps.setString(2, resourceType.toUpperCase());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				path = rs.getString("ve_type");

			}

			rs.close();
			ps.close();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new Error(e.getMessage());
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getVEInstance] Ready to get out!");
		
		return path;
	}

	public String getVEInstanceId(
			String course, 
			String resourceType, 
			String username, 
			Boolean active) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getVEInstanceId] Inside!");
		
		//System.out.println("[getVEInstanceId]Course:"+course+"- Resource Type:"+resourceType.toUpperCase()+"-User: "+username+"-Status: "+active);
		String id = null;
		try {

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM user_ve_instances WHERE course = ? and resource_type = ? and active = ? and username = ?" );
			ps.setString(1, course);
			ps.setString(2, resourceType.toUpperCase());
			ps.setBoolean(3, active);
			ps.setString(4, username);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				id =  rs.getString("ve_id");
				//System.out.println("[getVEInstanceId]Returning instance "+id);
			
			}
			else {
				
				//System.out.println("[getVEInstanceId] Instance does not exist!");

			}
			
			rs.close();
			ps.close();

		}
		catch(SQLException e) {

			e.printStackTrace();
			throw new Error(e.getMessage());
		
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getVEInstanceId] Ready to get out!");
		
		return id;

	}

	public UserVEInstance getUserVEInstance(String veId) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUserVEInstance] Inside!");
		
		UserVEInstance userVeIns = null;
		try {

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM user_ve_instances WHERE ve_id = ?");
			ps.setString(1, veId);

			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUserVEInstance] ps: " + ps);
			
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				int id = rs.getInt("id");
				String username = rs.getString("username");
				String retVeId = rs.getString("ve_id");
				String course = rs.getString("course");
				String resourceType = rs.getString("resource_type");
				Boolean active = rs.getBoolean("active");
				int courseId = rs.getInt("course_id");
				String veInsUsername = rs.getString("ve_ins_username");
				userVeIns = 
					new UserVEInstance(
							id,
							username,
							retVeId,
							course,
							resourceType,
							active,
							courseId,
							veInsUsername);
			}
				
			rs.close();
			ps.close();

		} catch(SQLException e) {

			e.printStackTrace();
			throw new Error(e.getMessage());
		
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUserVEInstance] Ready to get out!");
		
		return userVeIns;

	}

	public String getResourceTypeFromVeInsId(String veInsId) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getResourceTypeFromVeInsId] Inside!");
		
		String resourceType = null;
		
		try {

			PreparedStatement ps = 
				conn.prepareStatement(
						"SELECT * FROM user_ve_instances WHERE ve_id = ?" );
			ps.setString(1, veInsId);

			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getResourceTypeFromVeInsId] ps: " + ps);
			
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				resourceType =  rs.getString("resource_type");
			
			}
					
			rs.close();
			ps.close();

		}
		catch(SQLException e) {

			e.printStackTrace();
			throw new Error(e.getMessage());
		
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getResourceTypeFromVeInsId] Ready to get out!");
		
		return resourceType;

	}

	public String [] getUserVEInsIds(String username) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUserVEInsIds] Inside!");
		
		ArrayList<String> veInsIdList = new ArrayList<String>();
		
		try {

			PreparedStatement ps = 
				conn.prepareStatement(
						"SELECT * FROM user_ve_instances " +
						"WHERE username = ?" );
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				veInsIdList.add(rs.getString("ve_id"));
			
			}
			
			rs.close();
			ps.close();

		}
		catch(SQLException e) {

			e.printStackTrace();
			throw new Error(e.getMessage());
		
		}

		Object [] userVEInsIdObjList = veInsIdList.toArray();
		String [] userVEInsIdList = new String[userVEInsIdObjList.length];
		for (int i=0; i<userVEInsIdObjList.length; i++) 
			userVEInsIdList[i] = (String) userVEInsIdObjList[i];
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUserVEInsIds] Ready to get out!");
		
		return userVEInsIdList;

	}

	public void cancelVEInstance(String id) {
		
		System.out.println("[DB.cancelVEInstance("+id+")]");
		
		try {
			
			String sql = "UPDATE user_ve_instances SET active = 'f' where ve_id = '"+id+"'"; 
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			System.out.println("[DB.cancelVEInstance] Instance cancelled");

		}
		catch(Exception e) {
			
			e.printStackTrace();
			throw new Error("[DB.cancelVEInstance("+id+")] "+e.getMessage());

		}

	}	

	public void addVEInstance(
			String id, 
			String username, 
			String course, 
			String resourceType) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtulaLabsDB - addVEInstance] Inside!" +
				"(" + id + ")");

		try {
	
			// SMS: Nov. 26, 2013
			// Fixing the length limitation for veInsUsername
			// The length of username cannot be longer than 20 for Windows XP
			int usernameMaxLength = 20;
			String veInsUsername = username;
			DebugTools.println(-2, "[VirtulaLabsDB - addVEInstance] 1 veInsUsername: " + veInsUsername);
			if (veInsUsername.length() > usernameMaxLength)
				// then the length of veInsUsername should be shortened
			 	veInsUsername = (String)veInsUsername.subSequence(0, veInsUsername.length() - (veInsUsername.length() - usernameMaxLength));
			DebugTools.println(-2, "[VirtulaLabsDB - addVEInstance] 2 veInsUsername: " + veInsUsername);

			if (resourceType.toUpperCase().equals("CERTIFICATE")) {
				String certAppendix = "-ct";
				if ((veInsUsername.length() + certAppendix.length()) > usernameMaxLength)
					// then the length of veInsUsername should be shortened
				 	veInsUsername = (String)veInsUsername.subSequence(0, veInsUsername.length() - (veInsUsername.length() + certAppendix.length() - usernameMaxLength));
				DebugTools.println(-2, "[VirtulaLabsDB - addVEInstance] 2.1 veInsUsername: " + veInsUsername);
				veInsUsername += certAppendix;
			}
			
			DebugTools.println(-2, "[VirtulaLabsDB - addVEInstance] 3 veInsUsername: " + veInsUsername);
			if (usernameExistsInUserVeIns(veInsUsername)) {
				veInsUsername = username;
				DebugTools.println(-2, "[VirtulaLabsDB - addVEInstance] 3.1 veInsUsername: " + veInsUsername);
				Course courseTemp = getCourseByFullName(course); 
				String courseShortNameAppendix = "-" + courseTemp.getShortName();
				if (resourceType.toUpperCase().equals("CERTIFICATE")) {
					String certAppendix = "-ct";
					if ((veInsUsername.length() + courseShortNameAppendix.length() + certAppendix.length()) > usernameMaxLength)
						// then the length of veInsUsername should be shortened
					 	veInsUsername = (String)veInsUsername.subSequence(0, veInsUsername.length() - (veInsUsername.length() + courseShortNameAppendix.length() + certAppendix.length() - usernameMaxLength));
					DebugTools.println(-2, "[VirtulaLabsDB - addVEInstance] 3.1.1 veInsUsername: " + veInsUsername);
					veInsUsername += courseShortNameAppendix;
					DebugTools.println(-2, "[VirtulaLabsDB - addVEInstance] 3.1.2 veInsUsername: " + veInsUsername);
					veInsUsername += certAppendix;
					DebugTools.println(-2, "[VirtulaLabsDB - addVEInstance] 3.1.3 veInsUsername: " + veInsUsername);
				} else {
					DebugTools.println(-2, "[VirtulaLabsDB - addVEInstance] " +
							"3.2.0 veInsUsername.length() - (veInsUsername.length() + courseShortNameAppendix.length() - usernameMaxLength): " + 
							(veInsUsername.length() - (veInsUsername.length() + courseShortNameAppendix.length() - usernameMaxLength)));
					DebugTools.println(-2, "[VirtulaLabsDB - addVEInstance] " +
							"3.2.0 veInsUsername.length(): " + 
							(veInsUsername.length()));
					DebugTools.println(-2, "[VirtulaLabsDB - addVEInstance] " +
							"3.2.0 courseShortNameAppendix.length(): " + 
							(courseShortNameAppendix.length()));
					DebugTools.println(-2, "[VirtulaLabsDB - addVEInstance] " +
							"3.2.0 usernameMaxLength: " + 
							(usernameMaxLength));
					if ((veInsUsername.length() + courseShortNameAppendix.length()) > usernameMaxLength)
						// then the length of veInsUsername should be shortened
					 	veInsUsername = (String)veInsUsername.subSequence(0, veInsUsername.length() - (veInsUsername.length() + courseShortNameAppendix.length() - usernameMaxLength));
					DebugTools.println(-2, "[VirtulaLabsDB - addVEInstance] 3.2.1 veInsUsername: " + veInsUsername);
					veInsUsername += courseShortNameAppendix;
					DebugTools.println(-2, "[VirtulaLabsDB - addVEInstance] 3.2.2 veInsUsername: " + veInsUsername);
				}
			}
			DebugTools.println(-2, "[VirtulaLabsDB - addVEInstance] veInsUsername: " + veInsUsername);

/*
			String veInsUsername = username;
			if (resourceType.toUpperCase().equals("CERTIFICATE")) {
				if (veInsUsername.length() > (20-3))
					veInsUsername = (String)veInsUsername.subSequence(0, 16);
				veInsUsername += "-ct";
			}
			if (usernameExistsInUserVeIns(veInsUsername)) {
				Course courseTemp = getCourseByFullName(course); 
				veInsUsername = username + "-" + courseTemp.getShortName(); 
				if (veInsUsername.length() > (20-4))
					veInsUsername = (String)veInsUsername.subSequence(0, (20-4));
				if (resourceType.toUpperCase().equals("CERTIFICATE")) {
					if (veInsUsername.length() > (20-4-3))
						veInsUsername = (String)veInsUsername.subSequence(0, (20-4-3));
					veInsUsername += "-ct";
				}
			}
*/			
			DebugTools.println(-2, "[VirtulaLabsDB - addVEInstance] $$$$$$$$$$$$$$$$$$$$$");
			DebugTools.println(-2, "[VirtulaLabsDB - addVEInstance] " +
					"(" + id + ")" +
					" veInsUsername is " + veInsUsername);
			
			String sql = "INSERT INTO user_ve_instances (username, ve_ins_username, ve_id, course, resource_type, active) VALUES(?,?,?,?,?,?)"; 
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,username);
			ps.setString(2,veInsUsername);
			ps.setString(3,id);
			ps.setString(4,course);
			ps.setString(5,resourceType.toUpperCase());
			ps.setBoolean(6,true);
			
			DebugTools.println(DEBUG_LEVEL, "[VirtulaLabsDB - addVEInstance] ps: " + ps);
			
			ps.executeUpdate();
			ps.close();

		} catch(Exception e) {
			e.printStackTrace();
			throw new Error("[DB.addVEInstance("+id+")] "+e.getMessage());
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtulaLabsDB - addVEInstance] Ready to get out!");

	}

	private boolean usernameExistsInUserVeIns(String veInsUsername) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - usernameExistsInUserVeIns] Inside!");
		
		boolean exists = false;
		
		try {

			PreparedStatement ps = 
				conn.prepareStatement(
						"SELECT * FROM user_ve_instances WHERE ve_ins_username = ?" );
			ps.setString(1, veInsUsername);

			DebugTools.println(DEBUG_LEVEL, "[VirtulaLabsDB - usernameExistsInUserVeIns] ps: " + ps);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) 
				exists = true;			
			
			rs.close();
			ps.close();

		}
		catch(SQLException e) {

			e.printStackTrace();
			throw new Error(e.getMessage());
		
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - usernameExistsInUserVeIns] Ready to get out!");
		
		return exists;

	}

	public void delVEInstance(String devaInsId) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtulaLabsDB - delVEInstance] Inside!");

		try {
			
			PreparedStatement ps = 
				conn.prepareStatement(
						"DELETE FROM user_ve_instances " +
						"	WHERE ve_id=?");
			ps.setString(1,devaInsId);
			
			DebugTools.println(DEBUG_LEVEL, "[VirtulaLabsDB - delVEInstance] ps: " + ps);
			
			ps.execute();
			
			ps.close();

		} catch(Exception e) {
			e.printStackTrace();
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtulaLabsDB - delVEInstance] Ready to get out!");

	}
	
	public ArrayList<VMInsTask> getVMInsTasksUsingMac(String macAddress) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getVMInsTasksUsingMac] Inside!");
		
		ArrayList<VMInsTask> vmInsTasks = new ArrayList<VMInsTask>();
	
		try {
			
			PreparedStatement ps = 
				conn.prepareStatement(
						"SELECT * FROM vm_ins_task " +
						"	WHERE mac_address=? and active='t' and execution_time<Now() " +
						"	ORDER BY execution_time");
			
			ps.setString(1, macAddress);
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getVMInsTasksUsingMac] ps: " + ps);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				int id = rs.getInt("id");
				String retMacAddress = rs.getString("mac_address");
				TodoType task = null;
				String taskStr = rs.getString("task");
				if (taskStr.equals(TodoType.CHANGE_CHECKIN_INTERVAL.toString()))
					task = TodoType.CHANGE_CHECKIN_INTERVAL;
				else if (taskStr.equals(TodoType.SYNC_USER_CREDENTIALS.toString()))
					task = TodoType.SYNC_USER_CREDENTIALS;
				Timestamp timestamp = rs.getTimestamp("execution_time");
				Calendar executionTime = Calendar.getInstance();
				executionTime.setTime(timestamp);
				boolean active = rs.getBoolean("active");
				
				VMInsTask vmInsTask = 
					new VMInsTask(
						id,
						retMacAddress,
						task,
						executionTime,
						active);

				vmInsTasks.add(vmInsTask);
				
			}
		
			rs.close();
			ps.close();
			
		} 
		catch (SQLException e) {
		
			e.printStackTrace();
		
		}
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getVMInsTasksUsingMac] Ready to get out!");
		
		return vmInsTasks;
	
	}
	
	public int addVMInsTask(VMInsTask vmInsTask) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - addVMInsTask] Inside!");
		
		int id = -1;
		
		try {
			
			PreparedStatement ps =
				conn.prepareStatement(
						"INSERT INTO " +
						"	vm_ins_task(mac_address,task," +
						"		execution_time,active) " +
						"   VALUES(?,?,?,?) " +
						"   RETURNING id");
		
			ps.setString(1, vmInsTask.getMacAddress());
			ps.setString(2, vmInsTask.getTask().toString());
			Timestamp timestamp = 
				new Timestamp(vmInsTask.getExecutionTime().getTime().getTime());
			ps.setTimestamp(3, timestamp);
			ps.setBoolean(4, vmInsTask.isActive());
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - addVMInsTask] ps: " + ps);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				id = rs.getInt("id");
			}
			
			rs.close();
			ps.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - addVMInsTask] Ready to get out!");
		
		return id;
	}

	public VMInsTask getVMInsTaskUsingMac(String macAddress, TodoType task) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getVMInsTaskUsingMac] Inside!");
		
		VMInsTask vmInsTask = null;
	
		try {
			
			PreparedStatement ps = 
				conn.prepareStatement(
						"SELECT * FROM vm_ins_task " +
						"	WHERE mac_address=? and active='t' " +
						"	ORDER BY execution_time");
			
			ps.setString(1, macAddress);
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getVMInsTaskUsingMac] ps: " + ps);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				int id = rs.getInt("id");
				String retMacAddress = rs.getString("mac_address");
				TodoType retTask = null;
				String taskStr = rs.getString("task");
				if (taskStr.equals(TodoType.CHANGE_CHECKIN_INTERVAL.toString()))
					retTask = TodoType.CHANGE_CHECKIN_INTERVAL;
				else if (taskStr.equals(TodoType.SYNC_USER_CREDENTIALS.toString()))
					retTask = TodoType.SYNC_USER_CREDENTIALS;
				Timestamp timestamp = rs.getTimestamp("execution_time");
				Calendar executionTime = Calendar.getInstance();
				executionTime.setTime(timestamp);
				boolean active = rs.getBoolean("active");
				
				vmInsTask = 
					new VMInsTask(
						id,
						retMacAddress,
						retTask,
						executionTime,
						active);

			}
			
			rs.close();
			ps.close();
		
		} 
		catch (SQLException e) {
		
			e.printStackTrace();
		
		}
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getVMInsTaskUsingMac] Ready to get out!");
		
		return vmInsTask;
	
	}
	
	public void setVMInsTask(VMInsTask vmInsTask) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - setVMInsTask] Inside!");
		
		try {
			
			PreparedStatement ps = 
				conn.prepareStatement(
						"UPDATE vm_ins_task " +
						"	SET mac_address=?,task=?,execution_time=?,active=? " +
						"	WHERE id=?");
			
			ps.setString(1, vmInsTask.getMacAddress());
			ps.setString(2, vmInsTask.getTask().toString());
			Timestamp timestamp =
				new Timestamp(vmInsTask.getExecutionTime().getTime().getTime());
			ps.setTimestamp(3, timestamp);
			ps.setBoolean(4, vmInsTask.isActive());
			ps.setInt(5, vmInsTask.getId());
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - setVMInsTask] ps: " + ps);
			
			ps.execute();
			
			ps.close();
			
		} 
		catch (SQLException e) {
		
			e.printStackTrace();
		
		}
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - setVMInsTask] Ready to get out!");
		
	}

	public void addVMInsSyncUserTask(VMInsSyncUserTask vmInsSyncUserTask) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - addVMInsSyncUserTask] Inside!");
		
		try {
			
			PreparedStatement ps =
				conn.prepareStatement(
						"INSERT INTO " +
						"	vm_ins_sync_user_task(task_id,username,password,active) " +
						"   VALUES(?,?,?,?) ");
		
			ps.setInt(1, vmInsSyncUserTask.getTaskId());
			ps.setString(2, vmInsSyncUserTask.getUsername());
			ps.setString(3, vmInsSyncUserTask.getPassword());
			ps.setBoolean(4, vmInsSyncUserTask.isActive());
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - addVMInsSyncUserTask] ps: " + ps);
			
			ps.execute();
			
			ps.close();
				
		} 
		catch (SQLException e) {
		
			e.printStackTrace();
		
		}
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - addVMInsSyncUserTask] Ready to get out!");
		
	}

	public VMInsSyncUserTask getVMInsSyncUserTask(int taskId) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getVMInsSyncUserTask] Inside!");
		
		VMInsSyncUserTask vmInsTask = null;
	
		try {
			
			PreparedStatement ps = 
				conn.prepareStatement(
						"SELECT * FROM vm_ins_sync_user_task " +
						"	WHERE task_id=? ");
			
			ps.setInt(1, taskId);
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getVMInsSyncUserTask] ps: " + ps);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				int retTaskId = rs.getInt("task_id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				boolean active = rs.getBoolean("active");
				
				vmInsTask = 
					new VMInsSyncUserTask(
						retTaskId,
						username,
						password,
						active);
				
			}
			
			rs.close();
			ps.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getVMInsSyncUserTask] Ready to get out!");
		
		return vmInsTask;
	
	}
	
	public void setVMInsSyncUserTask(VMInsSyncUserTask vmInsSyncUserTask) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - setVMInsSyncUserTask] Inside!");
		
		try {
			
			PreparedStatement ps = 
				conn.prepareStatement(
						"UPDATE vm_ins_sync_user_task " +
						"	SET username=?,password=?,active=? " +
						"	WHERE task_id=?");
			
			ps.setString(1, vmInsSyncUserTask.getUsername());
			ps.setString(2, vmInsSyncUserTask.getPassword());
			ps.setBoolean(3, vmInsSyncUserTask.isActive());
			ps.setInt(4, vmInsSyncUserTask.getTaskId());
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - setVMInsSyncUserTask] ps: " + ps);
			
			ps.execute();
			
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - setVMInsSyncUserTask] Ready to get out!");
		
	}

	public User getUserUsingVEInsId(String veInsId) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUserUsingVEInsId] Inside!");
		
		User user = null;
		
		try {
			
			PreparedStatement ps = 
				conn.prepareStatement(
						"SELECT profile.* FROM user_profile as profile, " +
						"		user_ve_instances as ins " +
						"	WHERE profile.username=ins.username AND " +
						"		ins.active='t' AND ins.ve_id=?");
		
			ps.setString(1, veInsId);
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUserUsingVEInsId] ps: " + ps);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				
				String username = rs.getString("username");
				String password = rs.getString("password");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String emailAddress = rs.getString("email_address");
				String userRole = rs.getString("user_role");
				String timeZoneId = rs.getString("time_zone_id");
				String contactInfo = rs.getString("contact_info");
				
				String userCashedPassword = getUserCachedPassword(username);
				if (userCashedPassword != null)
					password = userCashedPassword;
				
				user = 
					new User(
							username,
							password,
							firstName,
							lastName,
							emailAddress,
							userRole,
							timeZoneId,
							contactInfo);
			
			}
				
		} 
		catch (SQLException e) {
		
			e.printStackTrace();
		
		}
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUserUsingVEInsId] "
				+ "user: " + user);
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUserUsingVEInsId] Ready to get out!");
		
		return user;
	
	}

	public boolean addCourseVeType(String courseName) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - addCourse] Inside!");

		boolean retVal = false;

		try {
			
			PreparedStatement ps = 
				conn.prepareStatement(
						"SELECT * FROM ve_types WHERE course=?");
			
			ps.setString(1, courseName);
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - addCourse] ps: " + ps);

			ResultSet rs = ps.executeQuery();
			
			if (!rs.next()) {
				
				PreparedStatement ps2 = 
					conn.prepareStatement(
							"INSERT INTO " +
							"	ve_types(ve_type,course,resource_type,active) " +
							"	values(?,?,?,?)");
				
				// TODO
				ps2.setString(1, "ve_sample.xml");
				ps2.setString(2, courseName);
				// TODO
				ps2.setString(3, "VIRTUAL LAB");
				ps2.setBoolean(4, true);
				
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - addCourse] ps2: " + ps2);

				ps2.execute();
				
				ps2 = 
					conn.prepareStatement(
							"INSERT INTO " +
							"	ve_types(ve_type,course,resource_type,active) " +
							"	values(?,?,?,?)");
				
				// TODO
				ps2.setString(1, "ve_sample.xml");
				ps2.setString(2, courseName);
				// TODO
				ps2.setString(3, "CERTIFICATE");
				ps2.setBoolean(4, true);
				
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - addCourse] ps2: " + ps2);

				ps2.execute();
				
				ps2.close();
				
				retVal = true;
				
			}
			
			rs.close();
			ps.close();
			
		} 
		catch (SQLException e) {
		
			e.printStackTrace();
		
		}
			
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - addCourse] Ready to get out!");

		return retVal;

	}

	public ArrayList<String> getResourceTypes(String userRole) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getResourceTypes] Inside!");
		
		ArrayList<String> resourceTypes = new ArrayList<String>();
	
		try {
			
			PreparedStatement ps = 
				conn.prepareStatement(
						"SELECT * FROM role_resource WHERE user_role=? ");
			
			ps.setString(1, userRole);
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getResourceTypes] ps: " + ps);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				String resourceType = rs.getString("resource");
				resourceTypes.add(resourceType);

			}
			
			rs.close();
			ps.close();
		
		} 
		catch (SQLException e) {
		
			e.printStackTrace();
		
		}
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getResourceTypes] Ready to get out!");
		
		return resourceTypes;
	
	}

	public User getUserByVeInsId(String veInsId) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUserByVeInsId] Inside!");
		
		User user = null;

		try {

			
			// Try to find the username using the veInsId
			PreparedStatement ps = conn.prepareStatement(
				"SELECT * FROM user_ve_instances WHERE ve_id=?");
			
			ps.setString(1, veInsId);

			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUserByVeInsId] ps:" + ps);
			
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				
				String username = rs.getString("username");
				user = getUser(username);
			
			}

			rs.close();
			ps.close();

		}
		catch(Exception e) {

			e.printStackTrace();

		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getUserByVeInsId] Ready to get out!");
		
		return user;

	}

	public String getVeInsUsernameByVeInsId(String veInsId) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getVeInsUsernameByVeInsId] Inside!");
		
		String veInsUsername = "";

		try {

			
			// Try to find the username using the veInsId
			PreparedStatement ps = conn.prepareStatement(
				"SELECT * FROM user_ve_instances WHERE ve_id=?");
			
			ps.setString(1, veInsId);

			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getVeInsUsernameByVeInsId] ps:" + ps);
			
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				
				veInsUsername = rs.getString("ve_ins_username");
			
			}

			rs.close();
			ps.close();

		}
		catch(Exception e) {

			e.printStackTrace();

		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getVeInsUsernameByVeInsId] Ready to get out!");
		
		return veInsUsername;

	}

	public void deleteVMTasks(String[] macAddress) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - deleteVMTasks] Inside!");
		
		if (macAddress != null) {
			
			for (int i=0; i<macAddress.length; i++) {
		
				try {
					PreparedStatement ps = 
						conn.prepareStatement(
								"SELECT id FROM vm_ins_task " +
								"	WHERE mac_address=?");
					ps.setString(1, macAddress[i]);
					DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - deleteVMTasks] ps: " + ps);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						int taskId = rs.getInt("id");
						PreparedStatement ps2 =
							conn.prepareStatement(
									"DELETE FROM vm_ins_sync_user_task " +
									"	WHERE task_id=?");
						ps2.setInt(1, taskId);
						DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - deleteVMTasks] ps2: " + ps2);
						ps2.execute();
						ps2.close();
					}
					rs.close();
					ps.close();
					
					PreparedStatement ps3 = 
						conn.prepareStatement(
								"DELETE FROM vm_ins_task " +
								"	WHERE mac_address=?");
					ps3.setString(1, macAddress[i]);
					DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - deleteVMTasks] ps3: " + ps3);
					ps3.execute();
					ps3.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - deleteVMTasks] Ready to get out!");
		
	}

	public ArrayList<String> getVEInstanceIds(
			String course, 
			String resourceType, 
			String username, 
			Boolean active) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getVEInstanceIds] Inside!");
		
		ArrayList<String> ids = new ArrayList<String>();
		
		try {

			PreparedStatement ps = null; 
			
			if (username.equals(VirtualLabs.ALL_STUDENTS)) {
				
				ps = conn.prepareStatement("SELECT * FROM user_ve_instances WHERE course = ? and resource_type = ? and active = ? ORDER BY username" );
				ps.setString(1, course);
				ps.setString(2, resourceType.toUpperCase());
				ps.setBoolean(3, active);
			
			} else {
				
				ps = conn.prepareStatement("SELECT * FROM user_ve_instances WHERE course = ? and resource_type = ? and active = ? and username = ?" );
				ps.setString(1, course);
				ps.setString(2, resourceType.toUpperCase());
				ps.setBoolean(3, active);
				ps.setString(4, username);

			}

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				String id =  rs.getString("ve_id");
				ids.add(id);
				
			} 
			
			rs.close();
			ps.close();

		} catch(SQLException e) {

			e.printStackTrace();
			throw new Error(e.getMessage());
		
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getVEInstanceIds] Ready to get out!");
		
		return ids;

	}

	public ArrayList<String> getCourseUsers(String course) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getCourseUsers] Inside!");
		
		ArrayList<String> users = new ArrayList<String>();

		try {

			PreparedStatement ps = 
				conn.prepareStatement("SELECT c.username, p.user_role FROM user_course as c,user_profile as p WHERE course=? AND c.username=p.username AND user_role=? ORDER BY username");

			ps.setString(1, course);
			ps.setString(2, "STUDENT");

			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getCourseUsers] " +
					"ps: " + ps);
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				users.add(rs.getString("username"));

			}

		}
		catch(SQLException e) {

			e.printStackTrace();
			throw new Error(e.getMessage());

		}


		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getCourseUsers] Ready to get out!");
		
		return users;

	}

	/**
     * Adds a course record to the course table.
     * @param course
     */
    public void addCourse(Course course) {
    	
    	DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - addCourse] Inside!");
    	
    	try {
    		
    		PreparedStatement ps = conn.prepareStatement(
    				"INSERT INTO course(id,full_name,short_name,promo_code) " +
    				"VALUES(?,?,?,?)");
    		
    		ps.setInt(1, course.getId());
    		ps.setString(2, course.getFullName());
    		ps.setString(3, course.getShortName());
    		ps.setString(4, course.getPromoCode());
    		
    		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB] ps: " + ps);
    	
    		ps.execute();
    		
    		ps.close();
    		
    	}
    	catch (Exception e) {
    		
    		e.printStackTrace();
    		
    	}
    	
    	// TODO
    	addCourseVeType(course.getFullName());
    	
    	DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - addCourse] Ready to get out!");
    	
    }
    
    /**
	 * Gets a course record using its fullName
	 * @param fullName
	 * @return
	 */
	public Course getCourseByFullName(String fullName) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB -getCourseByUsername] Inside!");
		
		Course course = null; 
		
		try {
	    
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM course WHERE full_name=?");
	    
			ps.setString(1, fullName);
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB] ps: " + ps);
			
			ResultSet rs = ps.executeQuery();
	    
			if (rs.next()) {
		
				int id = rs.getInt("id");
				String retFullName = rs.getString("full_name");
				String shortName = rs.getString("short_name");
				String promoCode = rs.getString("promo_code");
				
				course = new Course(id, retFullName, shortName, promoCode);
				
			}
	
			rs.close();
			ps.close();
	       
		}
	
		catch(SQLException e) {
	    
			e.printStackTrace();
	
		}
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB -getCourseByVeInsId] " +
				"course: " + course);
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB -getCourseByUsername] Ready to get out!");
		
		return course;
	
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Course getCourseById(int id) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getCourseById] Inside!");
		
		Course course = null;
		try {

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM course WHERE id = ?");
			ps.setInt(1, id);

			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB] ps: " + ps);
			
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				int retId = rs.getInt("id");
				String fullName = rs.getString("full_name");
				String shortName = rs.getString("short_name");
				String promoCode = rs.getString("promo_code");
				
				course = new Course(retId, fullName, shortName, promoCode);
			
			}
			ps.close();

		} catch (SQLException e) {
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getCourseById] SQL Exception " + e.getMessage());
			e.printStackTrace();
			throw new Error(e.getMessage());
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getCourseById] Ready to get out!");
		
		return course;
	}

	/**
	 * 
	 * @param course
	 */
	public void modifyCourse(Course course) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - modifyCourse] Inside!");
		
		try {

			Course oldCourse = getCourseById(course.getId());
			if (!oldCourse.getFullName().equals(course.getFullName())) {
				
				PreparedStatement ps = 
					conn.prepareStatement("UPDATE ve_types SET course = ? WHERE course = ?");
				ps.setString(1, course.getFullName());
				ps.setString(2, oldCourse.getFullName());
				
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB] ps: " + ps);
				
				ps.executeUpdate();
				ps.close();
				
				ps = 
					conn.prepareStatement("UPDATE appointments SET course = ? WHERE course = ?");
				ps.setString(1, course.getFullName());
				ps.setString(2, oldCourse.getFullName());
				
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB] ps: " + ps);
				
				ps.executeUpdate();
				ps.close();
				
				ps = 
					conn.prepareStatement("UPDATE user_ve_instances SET course = ? WHERE course = ?");
				ps.setString(1, course.getFullName());
				ps.setString(2, oldCourse.getFullName());
				
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB] ps: " + ps);
				
				ps.executeUpdate();
				ps.close();
				
				ps = 
					conn.prepareStatement("UPDATE user_course SET course = ? WHERE course = ?");
				ps.setString(1, course.getFullName());
				ps.setString(2, oldCourse.getFullName());
				
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB] ps: " + ps);
				
				ps.executeUpdate();
				ps.close();
				
			}
			
			PreparedStatement ps = 
				conn.prepareStatement("UPDATE course SET full_name =?, short_name=?, promo_code=? WHERE id = ?");
			ps.setString(1, course.getFullName());
			ps.setString(2, course.getShortName());
			ps.setString(3, course.getPromoCode());
			ps.setInt(4, course.getId());
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB] ps: " + ps);
			
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - modifyCourse] SQL Exception " + e.getMessage());
			e.printStackTrace();
			throw new Error(e.getMessage());
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - modifyCourse] Ready to get out!");
		
	}

	/**
	 * 
	 * @param course
	 */
	public void delCourse(Course course) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - delCourse] Inside!");
		
		try {

			PreparedStatement ps = 
				conn.prepareStatement("DELETE FROM ve_types WHERE course = ?");
			ps.setString(1, course.getFullName());
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB] ps: " + ps);
			
			ps.execute();
			ps.close();
			
			ps = 
				conn.prepareStatement("DELETE FROM appointments WHERE course = ?");
			ps.setString(1, course.getFullName());
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB] ps: " + ps);
			
			ps.execute();
			ps.close();
			
			ps = 
				conn.prepareStatement("DELETE FROM user_ve_instances WHERE course = ?");
			ps.setString(1, course.getFullName());
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB] ps: " + ps);
			
			ps.execute();
			ps.close();
			
			ps = 
				conn.prepareStatement("DELETE FROM user_course WHERE course = ?");
			ps.setString(1, course.getFullName());
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB] ps: " + ps);
			
			ps.execute();
			ps.close();
			
			ps = 
				conn.prepareStatement("DELETE FROM course WHERE id = ?");
			ps.setInt(1, course.getId());
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB] ps: " + ps);
			
			ps.execute();
			ps.close();
			
		} catch (SQLException e) {
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - delCourse] SQL Exception " + e.getMessage());
			e.printStackTrace();
			throw new Error(e.getMessage());
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - delCourse] Ready to get out!");
		
	}

	/**
	 * 
	 * @param veInsId
	 * @return
	 */
	public String getPromoCode(String veInsId) {
		// TODO Auto-generated method stub
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getPromoCode] Inside!");
		
		String promoCode = null;
		
		User user = null;

		try {
			
			// Try to find the username using the veInsId
			PreparedStatement ps = conn.prepareStatement(
				"SELECT * FROM user_ve_instances WHERE ve_id=?");
			
			ps.setString(1, veInsId);

			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB] ps:" + ps);
			
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				
				String courseFullName = rs.getString("course");
				Course course = getCourseByFullName(courseFullName);
				promoCode = course.getPromoCode();
				
				// sms: 11/12/2013 added for one day test promoCode
				String resourceType = rs.getString("resource_type");
				if (resourceType.toUpperCase().equals("CERTIFICATE"))
					promoCode += "-1D";			
				
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getPromoCode] " +
						" resourceType: " + resourceType +
						" promoCode: " + promoCode );
			}

			rs.close();
			ps.close();

		}
		catch(Exception e) {

			e.printStackTrace();

		}
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getPromoCode] Ready to get out!");
		
		return promoCode;
	}

	public void updateUserVEInsID(String newVeInsId, String oldVeInsId) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - updateUserVEInsID] Inside!");
		
		try {

			PreparedStatement ps = 
				conn.prepareStatement("UPDATE appointments SET sch_id = ? WHERE sch_id = ?");
			ps.setString(1, newVeInsId);
			ps.setString(2, oldVeInsId);

			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - updateUserVEInsID] ps: " + ps);

			ps.executeUpdate();
			ps.close();

			ps = 
				conn.prepareStatement("UPDATE appointments SET affiliation_id = ? WHERE affiliation_id = ?");
			ps.setString(1, newVeInsId);
			ps.setString(2, oldVeInsId);

			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - updateUserVEInsID] ps: " + ps);

			ps.executeUpdate();
			ps.close();

			ps = 
				conn.prepareStatement("UPDATE user_ve_instances SET ve_id = ? WHERE ve_id = ?");
			ps.setString(1, newVeInsId);
			ps.setString(2, oldVeInsId);

			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - updateUserVEInsID] ps: " + ps);

			ps.executeUpdate();
			ps.close();
				
		} catch (SQLException e) {
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - updateUserVEInsID] SQL Exception " + e.getMessage());
			e.printStackTrace();
			throw new Error(e.getMessage());
		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - updateUserVEInsID] Ready to get out!");
		
	}

	public int getPromoId(String promoCode) {
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getPromoId] Inside!");
		
		int promoId = -1;
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(
				"SELECT * FROM promo WHERE promo_code=?");
			
			ps.setString(1, promoCode);

			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getPromoId] ps:" + ps);
			
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				
				promoId = rs.getInt("id");
				
			}

			rs.close();
			ps.close();

		}
		catch(Exception e) {

			e.printStackTrace();

		}
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - getPromoId] Ready to get out!");
		
		return promoId;
	}

    /**
	 * Gets the first active htpasswdHost record.
	 * @return
	 */
	public HtpasswdHost getFirstActiveHtpasswdHost() {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB -getHtpasswdHost] Inside!");
		
		HtpasswdHost htpasswdHost = null; 
		
		try {
	    
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM htpasswd_host WHERE active='t' order by id");
	    
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB] ps: " + ps);
			
			ResultSet rs = ps.executeQuery();
	    
			if (rs.next()) {
		
				int retId = rs.getInt("id");
				String name = rs.getString("name");
				int sshPort = rs.getInt("ssh_port");
				String username = rs.getString("username");
				String password = rs.getString("password");
				boolean active = rs.getBoolean("active");
				
				htpasswdHost = new HtpasswdHost(retId, name, sshPort, username, password, 
						active); 
				
			}
	
			rs.close();
			ps.close();
	       
		}
	
		catch(SQLException e) {
	    
			e.printStackTrace();
	
		}
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB -getHtpasswdHost] Ready to get out!");
		
		return htpasswdHost;
	
	}

    /**
	 * Gets a htpasswdHost record using its unique id.
	 * @param id
	 * @return
	 */
	public HtpasswdHost getHtpasswdHost(int id) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB -getHtpasswdHost] Inside!");
		
		HtpasswdHost htpasswdHost = null; 
		
		try {
	    
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM htpasswd_host WHERE id=?");
	    
			ps.setInt(1, id);
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB] ps: " + ps);
			
			ResultSet rs = ps.executeQuery();
	    
			if (rs.next()) {
		
				int retId = rs.getInt("id");
				String name = rs.getString("name");
				int sshPort = rs.getInt("ssh_port");
				String username = rs.getString("username");
				String password = rs.getString("password");
				boolean active = rs.getBoolean("active");
				
				htpasswdHost = new HtpasswdHost(retId, name, sshPort, username, password, 
						active); 
				
			}
	
			rs.close();
			ps.close();
	       
		}
	
		catch(SQLException e) {
	    
			e.printStackTrace();
	
		}
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB -getHtpasswdHost] Ready to get out!");
		
		return htpasswdHost;
	
	}

	/**
	 * Gets a htpasswdHost record using its name.
	 * @param id
	 * @return
	 */
	public HtpasswdHost getHtpasswdHostUsingName(String htpasswdHostName) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB -getHtpasswdHostUsingName] Inside!");
		
		HtpasswdHost htpasswdHost = null; 
		
		try {
	    
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM htpasswd_host WHERE name=?");
	    
			ps.setString(1, htpasswdHostName);
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB] ps: " + ps);
			
			ResultSet rs = ps.executeQuery();
	    
			if (rs.next()) {
		
				int retId = rs.getInt("id");
				String name = rs.getString("name");
				int sshPort = rs.getInt("ssh_port");
				String username = rs.getString("username");
				String password = rs.getString("password");
				boolean active = rs.getBoolean("active");
				
				htpasswdHost = new HtpasswdHost(retId, name, sshPort, username, password, 
						active); 
				
			}
	
			rs.close();
			ps.close();
	       
		}
	
		catch(SQLException e) {
	    
			e.printStackTrace();
	
		}
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB -getHtpasswdHost] Ready to get out!");
		
		return htpasswdHost;
	
	}

	/**
	 * Sets a htpasswdHost record using its unique id.
	 * @param htpasswdHost
	 * @return
	 */
	public boolean setHtpasswdHost(HtpasswdHost htpasswdHost) {
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - setHtpasswdHost] Inside!");
		
		boolean retVal = false;
		
		try {
	
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE htpasswd_host SET name=?,ssh_port=?,username=?,password=?," +
					"active=?," +
					"WHERE id=?");
	
			ps.setString(1, htpasswdHost.getName());
			ps.setInt(2, htpasswdHost.getSshPort());
			ps.setString(3, htpasswdHost.getUsername());
			ps.setString(4, htpasswdHost.getPassword());
			ps.setBoolean(5, htpasswdHost.isActive());
			ps.setInt(6, htpasswdHost.getId());
			
			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB] ps: " + ps);
			
			ps.execute();
			int count = ps.getUpdateCount();
		    if (count > 0)
		    	retVal = true;
			
		    DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - setHtpasswdHost] update count: " + count +
		    		" retVal is " + retVal);
		    
		    ps.close();
		
		    
		}
		catch(SQLException e) {
	    
			e.printStackTrace();
	
		}
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - setHtpasswdHost] Ready to get out!");
		
		return retVal;
		
	}

	public boolean delHtpasswdHost(int id) {
	
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - delHtpasswdHost] Inside!");
		
		boolean retVal = false;
		
		try {
				PreparedStatement ps = conn.prepareStatement(
				"DELETE from htpasswd_host where id=?");
		
				ps.setInt(1, id);
				
				DebugTools.println(DEBUG_LEVEL, "VEScheduler] ps: " + ps);
				
				ps.execute();
				
				retVal = true;
									
			ps.close();
					
		}
		catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - delHtpasswdHost] Ready to get out!");
		
		return retVal;
	}
	
	/**
     * Adds a htpasswdHost record to the htpasswdHost table.
     * @param htpasswdHost
     * @return returns the auto-generated unique htpasswdHost id.
     */
    public int addHtpasswdHost(HtpasswdHost htpasswdHost) {
    	
    	DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - addHtpasswdHost] Inside!");
    	
    	int id = 0;
    	
    	try {
    		
    		PreparedStatement ps = conn.prepareStatement(
    				"INSERT INTO htpasswd_host(name,ssh_port,username,password,ve_num_cap," +
    				"active) " +
    				"VALUES(?,?,?,?,?) " +
    				"RETURNING id");
    		
    		ps.setString(1, htpasswdHost.getName());
    		ps.setInt(2, htpasswdHost.getSshPort());
    		ps.setString(3, htpasswdHost.getUsername());
    		ps.setString(4, htpasswdHost.getPassword());
    		ps.setBoolean(5, htpasswdHost.isActive());
    		
    		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB] ps: " + ps);
    	
    		ResultSet rs = ps.executeQuery();
    		
    		if (rs.next()) {
    			
    			id = rs.getInt("id");
    			
    		}
    		
    		rs.close();
    		ps.close();
    		
    	}
    	catch (Exception e) {
    		
    		e.printStackTrace();
    		
    	}
    	
    	DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - addHtpasswdHost] Ready to get out!");
    	
    	return id;
    }

	public boolean isThereAnyActiveVMInsSyncUserTask(
			ArrayList<VMInstance> vmInstances) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - isThereAnyActiveVMInsSyncUserTask] Inside!");
		
		boolean anyActiveVMInsSyncUserTask = false;
		
		ArrayList<VMInsTask> vmInsTasks = new ArrayList<VMInsTask>();
		ArrayList<VMInsSyncUserTask> vmInsSyncUserTasks = new ArrayList<VMInsSyncUserTask>();

		if (!vmInstances.isEmpty()) {
			
			try {

				String queryStr = "SELECT * FROM vm_ins_task AS t, vm_ins_sync_user_task AS st "
						+ "WHERE t.active=\'t\' AND st.active=\'t\' AND t.id=st.task_id "
						+ "AND t.task=\'" + TodoType.SYNC_USER_CREDENTIALS.toString() + "\'"
						+ "AND t.mac_address in (";
				int counter = 0;
				for (VMInstance vmIns: vmInstances) {
					if (counter > 0)
						queryStr += ",";
					queryStr += "\'" + vmIns.getMacAddress() + "\'";
					counter++;
				}
				queryStr += ") ORDER BY t.execution_time";
						;
				PreparedStatement ps = conn.prepareStatement(queryStr);

				DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - isThereAnyActiveVMInsSyncUserTask] "
						+ "ps: " + ps);

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					anyActiveVMInsSyncUserTask = true;

					int id = rs.getInt("id");
					String retMacAddress = rs.getString("mac_address");
					TodoType task = null;
					String taskStr = rs.getString("task");
					if (taskStr.equals(TodoType.CHANGE_CHECKIN_INTERVAL.toString()))
						task = TodoType.CHANGE_CHECKIN_INTERVAL;
					else if (taskStr.equals(TodoType.SYNC_USER_CREDENTIALS.toString()))
						task = TodoType.SYNC_USER_CREDENTIALS;
					Timestamp timestamp = rs.getTimestamp("execution_time");
					Calendar executionTime = Calendar.getInstance();
					executionTime.setTime(timestamp);
					boolean active1 = rs.getBoolean("active");

					VMInsTask vmInsTask = 
							new VMInsTask(
									id,
									retMacAddress,
									task,
									executionTime,
									active1);

					DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - isThereAnyActiveVMInsSyncUserTask] "
							+ "vmInsTask.getId() is " + vmInsTask.getId());

					vmInsTasks.add(vmInsTask);

					int retTaskId = rs.getInt("task_id");
					String username = rs.getString("username");
					String password = rs.getString("password");
					boolean active2 = rs.getBoolean("active");
					
					VMInsSyncUserTask vmInsSyncUserTask = 
						new VMInsSyncUserTask(
							retTaskId,
							username,
							password,
							active2);
					
					DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - isThereAnyActiveVMInsSyncUserTask] "
							+ "vmInsSyncUserTask.getTaskId() is " + vmInsSyncUserTask.getTaskId());

					vmInsSyncUserTasks.add(vmInsSyncUserTask);
				}

				rs.close();
				ps.close();

			} 
			catch (SQLException e) {

				e.printStackTrace();

			}
		}
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - isThereAnyActiveVMInsSyncUserTask] "
				+ "vmInsTasks are " + vmInsTasks);
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - isThereAnyActiveVMInsSyncUserTask] "
				+ "vmInsSyncUserTasks are " + vmInsSyncUserTasks);

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - isThereAnyActiveVMInsSyncUserTask] Ready to get out!");
		
		return anyActiveVMInsSyncUserTask;
	}

	public boolean isNoPlainTextPasswordInEffect4ThisUser(String userName) {
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - isNoPlainTextPasswordInEffect4ThisUser] Inside!");
		
		boolean retVal = false;

		try {

			// Try to find the user by username
			PreparedStatement ps = conn.prepareStatement(
				"SELECT * FROM user_profile WHERE UPPER(username) = UPPER(?)");
			ps.setString(1, userName);

			DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - isNoPlainTextPasswordInEffect4ThisUser] ps:" + ps);
			
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				String password = rs.getString("password");
				DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - isNoPlainTextPasswordInEffect4ThisUser] "
						+ "password: " + password);

				int length = password.length();
				if (length > 0) {
					DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - isNoPlainTextPasswordInEffect4ThisUser] "
							+ "password length is " + password + ", which is greater than zero!");
					retVal = false;
				} else {
					DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - isNoPlainTextPasswordInEffect4ThisUser] "
							+ "password length is " + password + ", which is NOT greater than zero!");
					retVal = true;
				}
			}

			rs.close();
			ps.close();

		}
		catch(Exception e) {

			e.printStackTrace();

		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - isNoPlainTextPasswordInEffect4ThisUser] "
				+ "retVal: " + retVal);
		
		DebugTools.println(DEBUG_LEVEL, "[VirtualLabsDB - isNoPlainTextPasswordInEffect4ThisUser] Ready to get out!");
		
		return retVal;

	}

	public void updateUserTimeZone(User user) {

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - updateUserTimeZone] Inside!");

		// Make sure the user does exist
		if(userExists(user.getUserName())) 
		{

			try {

				String sqlStatement = "UPDATE user_profile SET time_zone_id = ? WHERE username = ?";
				PreparedStatement ps = conn.prepareStatement(sqlStatement);
				ps.setString(1, user.getTimeZone());
				ps.setString(2, user.getUserName());
				ps.executeUpdate();
				ps.close();

			}
			catch(SQLException e) {
				e.printStackTrace();
				throw new Error("[VirtualLabs - updateUserTimeZone] "+e.getMessage());
			}
		} else {

			throw new Error("[VirtualLabs - updateUserTimeZone] Record does not exist");

		}

		DebugTools.println(DEBUG_LEVEL, "[VirtualLabs - updateUserTimeZone] Ready to get out!");

	}

}

