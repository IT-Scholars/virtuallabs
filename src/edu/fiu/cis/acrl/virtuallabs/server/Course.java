package edu.fiu.cis.acrl.virtuallabs.server;

public class Course {

	private int id;
	private String fullName;
	private String shortName;
	private String promoCode;
	
	public Course(
			int id,
			String fullName,
			String shortName,
			String promoCode) {
		
		this.id = id;
		this.fullName = fullName;
		this.shortName = shortName;
		this.promoCode = promoCode;
		
	}

	public String toString() {
		
		String tenantStr = 
			"id: " + id + " " +
			"fullName: " + fullName + " " +
			"shorName: " + shortName + " " +
			"promoCode: " + promoCode ;
		
		return tenantStr;
		
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public String getPromoCode() {
		return promoCode;
	}
}
