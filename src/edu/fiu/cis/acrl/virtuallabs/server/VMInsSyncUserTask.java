package edu.fiu.cis.acrl.virtuallabs.server;

public class VMInsSyncUserTask {

	private int taskId;
	private String username;
	private String password;
	private boolean active;
	
	public VMInsSyncUserTask(
			int taskId,
			String username,
			String password,
			boolean active) {
		
		this.taskId = taskId;
		this.username = username;
		this.password = password;
		this.active = active;
		
	}
	
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public int getTaskId() {
		return taskId;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isActive() {
		return active;
	}
	
}
