package edu.fiu.cis.acrl.virtuallabs.server;

public class UserVEInstance {

	private int id;
	private String username;
	private String veId;
	private String course;
	private String resourceType;
	private Boolean active;
	private int courseId;
	private String veInsUsername;
	
	public UserVEInstance(
			int id,
			String username,
			String veId,
			String course,
			String resourceType,
			Boolean active,
			int courseId,
			String veInsUsername) {
		
		this.setId(id);
		this.setUsername(username);
		this.setVeId(veId);
		this.setCourse(course);
		this.setResourceType(resourceType);
		this.setActive(active);
		this.setCourseId(courseId);
		this.setVeInsUsername(veInsUsername);
		
	}

	public String toString() {
		
		return "UserVeInstance: " +
		"id: " + id + 
		"username: " +username + " " +
		"veId: " + veId + " " +
		"course: " + course + " " +
		"resourceType: " + resourceType + " " +
		"active: " + active + " " +
		"courseId: " + courseId + " " +
		"veInsUsername: " + veInsUsername;

	}
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setVeId(String veId) {
		this.veId = veId;
	}

	public String getVeId() {
		return veId;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getCourse() {
		return course;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getActive() {
		return active;
	}

	public void setVeInsUsername(String veInsUsername) {
		this.veInsUsername = veInsUsername;
	}

	public String getVeInsUsername() {
		return veInsUsername;
	}
}
