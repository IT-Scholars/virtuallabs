package edu.fiu.cis.acrl.virtuallabs.server;

import com.jcraft.jsch.*;

import edu.fiu.cis.acrl.vescheduler.server.tools.debug.DebugTools;

import java.io.*;


/**
 * Establishes an ssh connection and executes a command
 */
public class SSHCommandThread extends Thread {

	// Debug level for this class
	private static int DEBUG_LEVEL = 3;
	
	//    private static final String USER = "portal";
	//    private static final String PASSWORD = "k4se*prt4l";
	//    private static final String HOST = "dolphin.cis.fiu.edu";
	//    private static final int PORT = 22;

	private String command; 
	private String host; 
	private int port;
	private String user;
	private String password;
	private int wait;
	private int exitCode = -1;
	private boolean cmdReturned;

	public SSHCommandThread(
			String command, 
			String host, 
			int port, 
			String user, 
			String password,
			int wait) {

		this.command = command;
		this.host = host;
		this.port = port;
		this.user = user;
		this.password = password;
		this.wait = wait;
		this.exitCode = -1;
	}

	// @   Override
	public void run() {
		
		cmdReturned = false;
		
		try { if (wait > 0) Thread.sleep(wait*1000); } catch(Exception e) {}

		// int exitCode = -1;

		try {

			JSch jsch = new JSch();
			Session session = jsch.getSession(user, host, port);

			java.util.Properties config = new java.util.Properties(); 
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.setPassword(password);
			session.connect();

			Channel channel = session.openChannel("exec");
			((ChannelExec)channel).setCommand(command);

			InputStream in = channel.getInputStream();

			channel.connect();

			while(!channel.isClosed()) {

				try { Thread.sleep(100); } catch(Exception e) {}
		
			}

			exitCode = channel.getExitStatus();
			cmdReturned = true;
			
			channel.disconnect();
			session.disconnect();

			
			DebugTools.println(DEBUG_LEVEL, "[SSHCommand - run] " +
					"Command " + command + "\n\t exitCode: " + exitCode);
			if (exitCode < 0) {
				Exception e =
					new Exception(
					"Command " + command + " failed. Status: " + exitCode);
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return exitCode;
	}
	
	/**
	 * 
	 * @param timeout must me more than DELAY milliseconds
	 * @return
	 */
	public int getExitCode(int timeout) {
		
		DebugTools.println(DEBUG_LEVEL, "[SSHCommand - getExitCode] Inside!");

		DebugTools.println(DEBUG_LEVEL, "[SSHCommand - getExitCode] " +
				"timeout is " + timeout);

		int DELAY = 100;
		int waiting = 0; // 100 milliseconds
		
		while (!cmdReturned) {
			
			DebugTools.println(DEBUG_LEVEL, "[SSHCommand - getExitCode] " +
					"existCode is " + exitCode);
			DebugTools.println(DEBUG_LEVEL, "[SSHCommand - getExitCode] " +
					"waiting " + waiting + " milliseconds!");

			waiting += DELAY;
			
			if (waiting > timeout)
				break;
			
			try { Thread.sleep(DELAY); } catch(Exception e) {}
			
		}
		
		DebugTools.println(DEBUG_LEVEL, "[SSHCommand - getExitCode] " +
				"existCode is " + exitCode);

		DebugTools.println(DEBUG_LEVEL, "[SSHCommand - getExitCode] Ready to get out!");

		return this.exitCode;
	}
}
