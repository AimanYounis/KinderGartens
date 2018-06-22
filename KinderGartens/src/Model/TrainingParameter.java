package Model;

public class TrainingParameter {
	
	private int number;
	private String name;
	private int minimumTrainings;
	private int maximumTraining;
	
	
	public TrainingParameter(int number) {
		this.number = number;
	}

	public TrainingParameter(int number, String name, int minimumTrainings, int maximumTraining) {
		this.number = number;
		this.name = name;
		this.minimumTrainings = minimumTrainings;
		this.maximumTraining = maximumTraining;
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

	public int getMinimumTrainings() {
		return minimumTrainings;
	}

	public void setMinimumTrainings(int minimumTrainings) {
		this.minimumTrainings = minimumTrainings;
	}

	public int getMaximumTraining() {
		return maximumTraining;
	}

	public void setMaximumTraining(int maximumTraining) {
		this.maximumTraining = maximumTraining;
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
		TrainingParameter other = (TrainingParameter) obj;
		if (number != other.number)
			return false;
		return true;
	}
	
	
}
