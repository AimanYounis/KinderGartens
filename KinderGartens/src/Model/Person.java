package Model;

import java.util.Date;

public class Person {
	
	private String ID;
	private String firstName;
	private String surName;
	private Date dateOfBirth;
	
	
	public Person(String iD) {
		ID = iD;
	}
	public Person(String iD, String firstName, String surName, Date dateOfBirth) {
		ID = iD;
		this.firstName = firstName;
		this.surName = surName;
		this.dateOfBirth = dateOfBirth;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Person [ID=" + ID + ", firstName=" + firstName + ", surName=" + surName + ", dateOfBirth=" + dateOfBirth
				+ "]";
	}
	
	
	
	
	

}
