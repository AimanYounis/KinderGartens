package Model;

import java.util.ArrayList;
import java.util.Date;

public class Kid extends Person{
	
	private String street;
	private String houseNumber;
	private String city;
	private float latitude;
	private float longitude;
	private String fatherName;
	private String motherName;
	private int placeInFamily;
	private Class cLass;
	private ArrayList<TakesPlace> activities;
	
	public Kid(String iD) {
		super(iD);
	}

	public Kid(String iD, String firstName, String surName, Date dateOfBirth, String street, String houseNumber, String city, float latitude, float longitude,
			String fatherName, String motherName, int placeInFamily, Class cLass) {
		
		super(iD,firstName,surName,dateOfBirth);
		activities = new ArrayList<>();
		this.street = street;
		this.houseNumber = houseNumber;
		this.city = city;
		this.latitude = latitude;
		this.longitude = longitude;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.placeInFamily = placeInFamily;
		this.cLass = cLass;
	}
	
	public ArrayList<TakesPlace> getActivities() {
		return activities;
	}

	public void setActivities(ArrayList<TakesPlace> activities) {
		this.activities = activities;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public int getPlaceInFamily() {
		return placeInFamily;
	}

	public void setPlaceInFamily(int placeInFamily) {
		this.placeInFamily = placeInFamily;
	}

	public Class getcLass() {
		return cLass;
	}

	public void setcLass(Class cLass) {
		this.cLass = cLass;
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
		return super.toString()+" Kid placeInFamily=" + placeInFamily + "]";
	}
	
}
