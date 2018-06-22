package Model;

import java.util.Date;

public class Teacher extends Person{
	
	private String address;

	public Teacher(String iD) {
		super(iD);
	}
	public Teacher(String iD, String firstName, String surName, Date dateOfBirth) {
		super(iD, firstName, surName, dateOfBirth);
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}
