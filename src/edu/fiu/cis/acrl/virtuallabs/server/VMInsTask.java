package edu.fiu.cis.acrl.virtuallabs.server;

import java.util.Calendar;

import edu.fiu.cis.acrl.virtuallabs.ws.TodoType;

public class VMInsTask {

	private int id;
	private String macAddress;
	private TodoType task;
	private Calendar executionTime;
	private boolean active;
	
	public VMInsTask(
			int id,
			String macAddress,
			TodoType task,
			Calendar executionTime,
			boolean active) {
		
		this.id = id;
		this.macAddress = macAddress;
		this.task = task;
		this.executionTime = executionTime;
		this.active = active;
		
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public String getMacAddress() {
		return macAddress;
	}
	
	public void setTask(TodoType task) {
		this.task = task;
	}
	public TodoType getTask() {
		return task;
	}
	
	public void setExecutionTime(Calendar executionTime) {
		this.executionTime = executionTime;
	}
	public Calendar getExecutionTime() {
		return executionTime;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isActive() {
		return active;
	}

}
