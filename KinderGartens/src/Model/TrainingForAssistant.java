package Model;

import java.util.Date;

public class TrainingForAssistant {
	
	private Assistant assistant;
	private Training training;
	private Date tDate;
	
	
	public TrainingForAssistant(Assistant assistant, Training training) {
		this.assistant = assistant;
		this.training = training;
	}

	public TrainingForAssistant(Assistant assistant, Training training, Date tDate) {
		this.assistant = assistant;
		this.training = training;
		this.tDate = tDate;
	}
	
	public Assistant getAssistant() {
		return assistant;
	}


	public void setAssistant(Assistant assistant) {
		this.assistant = assistant;
	}


	public Training getTraining() {
		return training;
	}


	public void setTraining(Training training) {
		this.training = training;
	}


	public Date gettDate() {
		return tDate;
	}


	public void settDate(Date tDate) {
		this.tDate = tDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assistant == null) ? 0 : assistant.hashCode());
		result = prime * result + ((training == null) ? 0 : training.hashCode());
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
		TrainingForAssistant other = (TrainingForAssistant) obj;
		if (assistant == null) {
			if (other.assistant != null)
				return false;
		} else if (!assistant.equals(other.assistant))
			return false;
		if (training == null) {
			if (other.training != null)
				return false;
		} else if (!training.equals(other.training))
			return false;
		return true;
	}
	
}
