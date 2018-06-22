package Model;

import java.util.Date;

public class Assistant extends Person{
	
	public Assistant(String iD) {
		super(iD);
	}
	
	
	public Assistant(String iD, String firstName, String surName, Date dateOfBirth) {
		super(iD, firstName, surName, dateOfBirth);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}


	@Override
	public String toString() {
		return super.toString();
	}
	
	
}
