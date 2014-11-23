
/**
 * InvalidDataFault.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

package edu.fiu.cis.acrl.virtuallabs.ws;

public class InvalidDataFault extends java.lang.Exception{
    
    private edu.fiu.cis.acrl.virtuallabs.ws.InvalidDataError faultMessage;

    
        public InvalidDataFault() {
            super("InvalidDataFault");
        }

        public InvalidDataFault(java.lang.String s) {
           super(s);
        }

        public InvalidDataFault(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public InvalidDataFault(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(edu.fiu.cis.acrl.virtuallabs.ws.InvalidDataError msg){
       faultMessage = msg;
    }
    
    public edu.fiu.cis.acrl.virtuallabs.ws.InvalidDataError getFaultMessage(){
       return faultMessage;
    }
}
    