package edu.fiu.cis.acrl.virtuallabs.server;

import java.lang.Thread;
import java.util.ArrayList;
import java.util.Calendar;

import edu.fiu.cis.acrl.vescheduler.server.VEInstance;
import edu.fiu.cis.acrl.vescheduler.server.VMInstance;
import edu.fiu.cis.acrl.vescheduler.server.db.VESchedulerDB;
import edu.fiu.cis.acrl.virtuallabs.server.tools.debug.DebugTools;
import edu.fiu.cis.acrl.virtuallabs.server.db.VirtualLabsDB;

public class UnneededPasswordTerminator extends Thread {

	// Debug level for this class
	private static int DEBUG_LEVEL = 1;

	private VirtualLabsDB virtualLabsDB;
	// private VirtualLabsSettings vLabsSettings;
	
	private VESchedulerDB veSchDB;
	// private VESchedulerSettings veSchSettings;
	
    private boolean running;
    private boolean waiting;
    
	private long freshnessPeriod = VirtualLabsSettings.instance().getCachedPasswordTimeout() * 1000; 

    public UnneededPasswordTerminator() {
		this.running = false;
    	this.waiting = false;
    }
    
    // This method is no longer being used as some thread were stuck!!! :(
	public synchronized void notifyThread() {
		
		DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - notifyThread] Inside! " +
				"waiting: " + waiting);
		
		if (waiting) {
			waiting = false;
			notifyAll();
			DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - notifyThread] notified! ");
		}

		DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - notifyThread] Ready to get out!");
		
	}
	
	public void run() {

		DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - run] Inside! ");

		running = true;

		while(running) {
			DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - run] " +
					" before calling executeTask()");
			executeTasks();
			DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - run] " +
					" after calling executeTask()");
				
		}
	}
		

	private synchronized void executeTasks() {
		
		DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - executeTasks] Inside! ");
		
		virtualLabsDB = VirtualLabsDB.instance();
		
		veSchDB = VESchedulerDB.instance();
		
		/*
		virtualLabsDB = new VirtualLabsDB();

		vLabsSettings = VirtualLabsSettings.instance();
			
		DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - executeTasks] "
				+ "vLabsSettings!" + vLabsSettings);

		virtualLabsDB.connect(
				vLabsSettings.getDbUser(), 
				vLabsSettings.getDbPassword(), 
				vLabsSettings.getDbHost(), 
				vLabsSettings.getDbName());

		DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - executeTasks] "
				+ "virtualLabsDB is connected!");
		*/
		
		boolean running = true;
		int countTheNeededOnes = 0;
		int countTheFreshOnes  = 0;
		while (running) {
			waiting = false;
			ArrayList<UserCashedPassword> userCashedPasswords = virtualLabsDB.getUserCachedPassword();
			if (userCashedPasswords.size() == countTheNeededOnes) {
				DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - executeTasks] "
						+ "There are " + countTheNeededOnes + " needed records in "
						+ "the User Cached Password table! Waiting to be notified ... ");
				waiting = true;
				try {
					// Just to make sure that no thread will be stuck! :(
					this.notifyAll();
					Thread.sleep(freshnessPeriod);
					// this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				countTheNeededOnes = 0;
			} else if (userCashedPasswords.size() == (countTheNeededOnes + countTheFreshOnes)) {
				DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - executeTasks] "
						+ "There are " + countTheNeededOnes + " needed records and "
						+ countTheFreshOnes + " fresh records in the User Cached Password table! "
						+ "Sleeping for " + freshnessPeriod/1000 + " seconds ...");
				try {
					this.notifyAll();
					Thread.sleep(freshnessPeriod);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				countTheFreshOnes = 0;
			} else {
				for (UserCashedPassword userCashedPassword: userCashedPasswords) {
					DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - executeTasks] "
							+ "Checking on " + userCashedPassword);
					if (!isUserCachedPasswordStillFresh(userCashedPassword)) {
						if (!isUserCachedPasswordNeeded(userCashedPassword)) {
							DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - executeTasks] "
									+ "Deleting the user cached password for " + userCashedPassword);
							virtualLabsDB.delUserCachedPassword(userCashedPassword.getUserName());
						} else {
							DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - executeTasks] "
									+ "The user cached password for " + userCashedPassword + " is still needed!");
							countTheNeededOnes++;
						}
					} else {
						countTheFreshOnes++;
					}
				}
			}
		}

		DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - executeTasks] Ready to get out! ");
		
	}

	private boolean isUserCachedPasswordStillFresh(UserCashedPassword userCashedPassword) {

		DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - isUserCachedPasswordStillFresh] Inside! ");
		
		boolean needed = false;
		
		DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - isUserCachedPasswordStillFresh] "
				+ "userCashedPassword: " + userCashedPassword);

		DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - isUserCachedPasswordStillFresh] "
				+ "freshnessPeriod: " + freshnessPeriod);
				DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - isUserCachedPasswordStillFresh] "
				+ "Calendar.getInstance().getTime().getTime() > "
				+ "userCashedPassword.getUpdateTs().getTime().getTime() + " + freshnessPeriod + ": "
				+ Calendar.getInstance().getTime().getTime() + " <= " 
				+ userCashedPassword.getUpdateTs().getTime().getTime() + " + " + freshnessPeriod + " and it is "
				+ (Calendar.getInstance().getTime().getTime() <=
					userCashedPassword.getUpdateTs().getTime().getTime() + freshnessPeriod));

		if (Calendar.getInstance().getTime().getTime() <= 
			userCashedPassword.getUpdateTs().getTime().getTime() + freshnessPeriod) {
			DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - isUserCachedPasswordStillFresh] "
					+ "The cached password for " + userCashedPassword + " is still fresh!");
			needed = true;
		} else {
			DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - isUserCachedPasswordStillFresh] "
					+ "The cached password for " + userCashedPassword + " is no longer fresh!");
		}
		
		DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - isUserCachedPasswordStillFresh] Ready to get out! ");
		
		return needed;
	}

	private boolean isUserCachedPasswordNeeded(UserCashedPassword userCashedPassword) {

		DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - isUserCachedPasswordNeeded] Inside! ");
		
		boolean needed = false;
		
		DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - isUserCachedPasswordNeeded] "
				+ "userCashedPassword: " + userCashedPassword);

		if (!isThereAnyActiveVMInsSyncUserTask(userCashedPassword.getUserName())) {
			DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - isUserCachedPasswordNeeded] "
					+ "The cached password for " + userCashedPassword + " is no longer needed!");
			needed = false;
		} else {
			DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - isUserCachedPasswordNeeded] "
					+ "The cached password for " + userCashedPassword + " is still needed for some VM instance sync user tasks!");
			needed = true;
		}
		
		DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - isUserCachedPasswordNeeded] Ready to get out! ");
		
		return needed;
	}

	private boolean isThereAnyActiveVMInsSyncUserTask(String username) {
		
		DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - isThereAnyActiveVMInsSyncUserTask] Inside! ");
		
		boolean activeVMInsSyncUserTask = false;
		
		ArrayList<VEInstance> veInstances = veSchDB.getVEInstances(username);
		DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - isThereAnyActiveVMInsSyncUserTask] "
				+ "These are the veInstances " + veInstances);
		
		ArrayList<VMInstance> allVmInstances = new ArrayList<VMInstance>();
		
		for (VEInstance veIns: veInstances) {
			ArrayList<VMInstance> vmInstances = veSchDB.getVMInstancesByVEInsId(veIns.getId());
			allVmInstances.addAll(vmInstances);
			DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - isThereAnyActiveVMInsSyncUserTask] "
					+ "For veIns " + veIns.getId() + " "
					+ "these are the vmInstances " + vmInstances);
		}
		
		DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - isThereAnyActiveVMInsSyncUserTask] "
				+ "For username" + username + " "
				+ "these are all the vmInstances " + allVmInstances);

		if (!allVmInstances.isEmpty()) {
			if (virtualLabsDB.isThereAnyActiveVMInsSyncUserTask(allVmInstances)) {
				DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - isThereAnyActiveVMInsSyncUserTask] "
						+ "There are some active vm instance sync user tasks!");
				activeVMInsSyncUserTask = true;
			} else {
				DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - isThereAnyActiveVMInsSyncUserTask] "
						+ "There is no active vm instance sync task.");
				activeVMInsSyncUserTask = false;
			}
		} else {
			DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - isThereAnyActiveVMInsSyncUserTask] "
					+ "There is no vm instance; therefore, there is no active vm instance sync task either.");
			activeVMInsSyncUserTask = false;
		}

		DebugTools.println(DEBUG_LEVEL, "[UnneededPasswordTerminator - isThereAnyActiveVMInsSyncUserTask] Ready to get out! ");
		
		return activeVMInsSyncUserTask;
	}
}