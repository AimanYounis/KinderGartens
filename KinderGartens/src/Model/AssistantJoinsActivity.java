package Model;

public class AssistantJoinsActivity {
	
	private TakesPlace activitiesInclass;
	private TrainingForAssistant trainingForAssistant;
	
	public AssistantJoinsActivity(TakesPlace activitiesInclass, TrainingForAssistant trainingForAssistant) {
		this.activitiesInclass = activitiesInclass;
		this.trainingForAssistant = trainingForAssistant;
	}
	
	
	public TakesPlace getActivitiesInclass() {
		return activitiesInclass;
	}

	public void setActivitiesInclass(TakesPlace activitiesInclass) {
		this.activitiesInclass = activitiesInclass;
	}

	public TrainingForAssistant getTrainingForAssistant() {
		return trainingForAssistant;
	}

	public void setTrainingForAssistant(TrainingForAssistant trainingForAssistant) {
		this.trainingForAssistant = trainingForAssistant;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activitiesInclass == null) ? 0 : activitiesInclass.hashCode());
		result = prime * result + ((trainingForAssistant == null) ? 0 : trainingForAssistant.hashCode());
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
		AssistantJoinsActivity other = (AssistantJoinsActivity) obj;
		if (activitiesInclass == null) {
			if (other.activitiesInclass != null)
				return false;
		} else if (!activitiesInclass.equals(other.activitiesInclass))
			return false;
		if (trainingForAssistant == null) {
			if (other.trainingForAssistant != null)
				return false;
		} else if (!trainingForAssistant.equals(other.trainingForAssistant))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "AssistantJoinsActivity [activitiesInclass=" + activitiesInclass + ", trainingForAssistant="
				+ trainingForAssistant + "]";
	}
	
	
	
}
