package Model;

public class Shift {
	
	private int number;
	private int dayInWeek;
	private String shiftType;
	
	
	public Shift(int number) {
		this.number = number;
	}

	public Shift(int number, int dayInWeek, String shiftType) {
		this.number = number;
		this.dayInWeek = dayInWeek;
		this.shiftType = shiftType;
	}
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getDayInWeek() {
		return dayInWeek;
	}

	public void setDayInWeek(int dayInWeek) {
		this.dayInWeek = dayInWeek;
	}

	public String getShiftType() {
		return shiftType;
	}

	public void setShiftType(String shiftType) {
		this.shiftType = shiftType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Shift other = (Shift) obj;
		if (number != other.number)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Shift [number=" + number + ", dayInWeek=" + dayInWeek + ", shiftType=" + shiftType + "]";
	}
	
	
}
