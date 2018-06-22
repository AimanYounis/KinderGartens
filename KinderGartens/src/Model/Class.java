package Model;

import java.util.HashMap;

public class Class { 
	
	private Kindergarden kindergarden;
	private int number;
	private String name;
	private int minimumAge;
	private int maximumAge;
	private int maximumKids;
	private Teacher teacher;
	private HashMap<String,Kid> kids;
	
	public Class(Kindergarden kindergarden, int number) {
		this.kindergarden = kindergarden;
		this.number = number;
	}

	public Class(Kindergarden kindergarden, int number, String name, int minimumAge, int maximumAge, int maximumKids,
			Teacher teacher) {
		kids = new HashMap<>();
		this.kindergarden = kindergarden;
		this.number = number;
		this.name = name;
		this.minimumAge = minimumAge;
		this.maximumAge = maximumAge;
		this.maximumKids = maximumKids;
		this.teacher = teacher;
	}
	
	
	
	public HashMap<String, Kid> getKids() {
		return kids;
	}

	public void setKids(HashMap<String, Kid> kids) {
		this.kids = kids;
	}

	public Kindergarden getKindergarden() {
		return kindergarden;
	}

	public void setKindergarden(Kindergarden kindergarden) {
		this.kindergarden = kindergarden;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMinimumAge() {
		return minimumAge;
	}

	public void setMinimumAge(int minimumAge) {
		this.minimumAge = minimumAge;
	}

	public int getMaximumAge() {
		return maximumAge;
	}

	public void setMaximumAge(int maximumAge) {
		this.maximumAge = maximumAge;
	}

	public int getMaximumKids() {
		return maximumKids;
	}

	public void setMaximumKids(int maximumKids) {
		this.maximumKids = maximumKids;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kindergarden == null) ? 0 : kindergarden.hashCode());
		result = prime * result + number;
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
		Class other = (Class) obj;
		if (kindergarden == null) {
			if (other.kindergarden != null)
				return false;
		} else if (!kindergarden.equals(other.kindergarden))
			return false;
		if (number != other.number)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return kindergarden.getName() + ", Class number " + number + " ";
	}

	
	
	
}
