
/**
 * DuplicateCourseFault.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

package edu.fiu.cis.acrl.virtuallabs.ws;

public class DuplicateCourseFault extends java.lang.Exception{
    
    private edu.fiu.cis.acrl.virtuallabs.ws.DuplicateCourseError faultMessage;

    
        public DuplicateCourseFault() {
            super("DuplicateCourseFault");
        }

        public DuplicateCourseFault(java.lang.String s) {
           super(s);
        }

        public DuplicateCourseFault(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public DuplicateCourseFault(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(edu.fiu.cis.acrl.virtuallabs.ws.DuplicateCourseError msg){
       faultMessage = msg;
    }
    
    public edu.fiu.cis.acrl.virtuallabs.ws.DuplicateCourseError getFaultMessage(){
       return faultMessage;
    }
}
    