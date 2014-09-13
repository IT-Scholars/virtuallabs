package edu.fiu.cis.acrl.virtuallabs.server;

import edu.fiu.cis.acrl.virtuallabs.server.tools.debug.DebugTools;

public class HtpasswdHost {

	private int id;
	private String name;
	private int sshPort;
	private String username;
	private String password;
	private boolean active;
	
	public HtpasswdHost(int id,	String name, int sshPort, String username, String password,
			boolean active) {
		
		this.id = id;
		this.name = name;
		this.sshPort = sshPort;
		this.username = username;
		this.password = password;
		this.active = active;
		
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setSshPort(int sshPort) {
		this.sshPort = sshPort;
	}
	public int getSshPort() {
		return sshPort;
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

	public edu.fiu.cis.acrl.virtuallabs.ws.Host getConvertedHost() {
		
		edu.fiu.cis.acrl.virtuallabs.ws.Host convertedHost = 
			new edu.fiu.cis.acrl.virtuallabs.ws.Host();
		
		convertedHost.setId(id);
		convertedHost.setName(name);
		convertedHost.setSshPort(sshPort);
		convertedHost.setUsername(username);
		convertedHost.setPassword(password);
		convertedHost.setActive(active);
		
		return convertedHost;

	}

	public static HtpasswdHost getHost(edu.fiu.cis.acrl.virtuallabs.ws.Host convertedHost) {

		HtpasswdHost host = new HtpasswdHost(
				convertedHost.getId(),
				convertedHost.getName(),
				convertedHost.getSshPort(),
				convertedHost.getUsername(),
				convertedHost.getPassword(),
				convertedHost.getActive()
				);
		
		return host;

	}

}
