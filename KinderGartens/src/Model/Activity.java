package Model;

import java.util.HashMap;

public class Activity {
	
	private int ID;
	private String name;
	private int length;
	private int cost;
	private HashMap<Integer,Opinion> opinions; 
	
	public Activity(int iD) {
		ID = iD;
	}

	public Activity(int iD, String name, int length, int cost) {
		ID = iD;
		this.name = name;
		this.length = length;
		this.cost = cost;
		opinions = new HashMap<>();
	}

	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	public HashMap<Integer, Opinion> getOpinions() {
		return opinions;
	}

	public void setOpinions(HashMap<Integer, Opinion> opinions) {
		this.opinions = opinions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
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
		Activity other = (Activity) obj;
		if (ID != other.ID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Activity [ID=" + ID + ", name=" + name + ", length=" + length + ", cost=" + cost + ", opinions="
				+ opinions + "]";
	}

	
	
	
}
