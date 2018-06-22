package Model;

import java.util.HashMap;

public class Kindergarden {
	
	private int ID;
	private String name;
	private String street;
	private String houseNumber;
	private String city;
	private float latitude;
	private float longitude;
	private boolean isPrivate;
	private int price;
	private HashMap<Integer,Class> classes;
	private HashMap<Integer,Opinion> opinions;
	
	public Kindergarden(int ID) {
		this.ID = ID;
	}
	
	public Kindergarden(int iD, String name, String street, String houseNumber, String city, float latitude,
			float longitude, boolean isPrivate, int price) {
		classes = new HashMap<>();
		opinions = new HashMap<>();
		ID = iD;
		this.name = name;
		this.street = street;
		this.houseNumber = houseNumber;
		this.city = city;
		this.latitude = latitude;
		this.longitude = longitude;
		this.isPrivate = isPrivate;
		this.price = price;
	}
	
	
	
	public HashMap<Integer, Opinion> getOpinions() {
		return opinions;
	}

	public void setOpinions(HashMap<Integer, Opinion> opinions) {
		this.opinions = opinions;
	}

	public HashMap<Integer, Class> getClasses() {
		return classes;
	}

	public void setClasses(HashMap<Integer, Class> classes) {
		this.classes = classes;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
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
		Kindergarden other = (Kindergarden) obj;
		if (ID != other.ID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Kindergarden [ID=" + ID + ", name=" + name + ", street=" + street + ", houseNumber=" + houseNumber
				+ ", city=" + city + ", latitude=" + latitude + ", longitude=" + longitude + ", isPrivate=" + isPrivate
				+ ", price=" + price + ", classes=" + classes +  "]";
	}
	
	
}
