package edu.fiu.cis.acrl.virtuallabs.server;

import java.util.Calendar;

public class DbAppointment  {

	private String schId;
	private String quotaId;
	private String affiliationId;
	private String username;
	private String course;
	private String resourceType;
	private Calendar start;
	private Calendar end;
	private boolean active;

	public DbAppointment() {}

	public DbAppointment(String schId, String affiliationId) {

		this.schId = schId;
		this.affiliationId = affiliationId;

	}

	public DbAppointment(
			String schId, 
			String quotaId,
			String affiliationId, 
			String userName, 
			String course, 
			String resourceType, 
			boolean active,
			Calendar startDate, 
			Calendar endDate) {

		this.schId = schId;
		this.quotaId = quotaId;
		this.affiliationId = affiliationId;
		this.username = userName;
		this.course = course;
		this.resourceType = resourceType;
		this.active = active;
		this.start = startDate;
		this.end = endDate;

	}

	public String toString(){
		return ">>Appointment[sch id: "+this.schId+
		";quota id: "+this.quotaId+
		";affiliation id: "+this.affiliationId+
		";username: "+this.username+
		";course: "+this.course+
		";resource: "+this.resourceType+
		";active: "+this.active+
		";start: "+this.start.getTime()+
		";end: "+this.end.getTime()+
		"]";

	}

	/* Accessors */
	public String getSchId() { return schId; }
	public String getQuotaId() { return quotaId; }
	public String getAffiliationId() { return affiliationId; }
	public String getUsername() { return username; }
	public String getCourse() { return course; }
	public String getResourceType() { return resourceType; }
	public boolean isActive() { return active; }
	public Calendar getStart(){return start;}
	public Calendar getEnd(){return end;}


	/*Setters*/
	public void setSchId(String veId) { this.schId = veId;}
	public void setQuotaId(String quotaId) { this.quotaId = quotaId;}
	public void setAffiliationId(String affiliationId) { this.affiliationId = affiliationId;}
	public void setUsername(String username) { this.username = username;}
	public void setCourse(String course) { this.course = course;}
	public void setResourceType(String resourceType) { this.resourceType = resourceType;}
	public void setActive(boolean active) { this.active = active;}
	public void setStart(Calendar start) {this.start = start;}
	public void setEnd(Calendar end) {this.end = end;}

}
