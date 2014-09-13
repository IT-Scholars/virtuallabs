package edu.fiu.cis.acrl.virtuallabs.server.mail;

/**
 * Used to signal an exception in the CalendarMail such as an invalid email address, etc
 */
public class CalendarMailException extends Exception {

    public CalendarMailException() {}
    public CalendarMailException(String msg) { super(msg); }

}
