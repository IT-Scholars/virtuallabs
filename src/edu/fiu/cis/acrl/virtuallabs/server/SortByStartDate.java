package edu.fiu.cis.acrl.virtuallabs.server;

import java.util.Comparator;

import edu.fiu.cis.acrl.virtuallabs.ws.Appointment;

public class SortByStartDate implements Comparator<Appointment>{

	public int compare(Appointment app1, Appointment app2) {

		int comp = 0;
		
		if( app1.getStart().equals(app2.getStart()) && app1.getEnd().equals(app2.getEnd())) {
			
			comp = 0;

		} else if (app1.getStart().before(app2.getStart())) {
			
			comp = -1;

		} else if (app2.getStart().equals(app1.getStart())) {
			
			if (app1.getEnd().before(app2.getEnd()))
				comp = -1;
			else
				comp = 1;

		} else if (app1.getStart().after(app2.getStart())) {
			
			comp = 1;

		}
		
		return comp;
		
	}
}