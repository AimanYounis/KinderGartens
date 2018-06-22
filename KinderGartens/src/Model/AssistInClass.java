package Model;

public class AssistInClass {
	
	private Assistant assistant;
	private Class cLass;
	private Shift shift;
	
	public AssistInClass(Assistant assistant, Class cLass, Shift shift) {
		this.assistant = assistant;
		this.cLass = cLass;
		this.shift = shift;
	}
	
	public Assistant getAssistant() {
		return assistant;
	}

	public void setAssistant(Assistant assistant) {
		this.assistant = assistant;
	}

	public Class getcLass() {
		return cLass;
	}

	public void setcLass(Class cLass) {
		this.cLass = cLass;
	}

	public Shift getShift() {
		return shift;
	}

	public void setShift(Shift shift) {
		this.shift = shift;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assistant == null) ? 0 : assistant.hashCode());
		result = prime * result + ((cLass == null) ? 0 : cLass.hashCode());
		result = prime * result + ((shift == null) ? 0 : shift.hashCode());
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
		AssistInClass other = (AssistInClass) obj;
		if (assistant == null) {
			if (other.assistant != null)
				return false;
		} else if (!assistant.equals(other.assistant))
			return false;
		if (cLass == null) {
			if (other.cLass != null)
				return false;
		} else if (!cLass.equals(other.cLass))
			return false;
		if (shift == null) {
			if (other.shift != null)
				return false;
		} else if (!shift.equals(other.shift))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AssistInClass [assistant=" + assistant + ", cLass=" + cLass + ", shift=" + shift + "]";
	}
	
	
	
}
