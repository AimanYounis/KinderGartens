package Model;

public class Opinion {
	
	private int ID;
	private String talk;
	private int grade;
	private Kindergarden kindergarden;
	private Activity activity;
	
	public Opinion(int iD) {
		ID = iD;
	}

	public Opinion(int iD, String talk, int grade, Kindergarden kindergarden) {
		ID = iD;
		this.talk = talk;
		this.grade = grade;
		this.kindergarden = kindergarden;
	}

	public Opinion(int iD, String talk, int grade, Activity activity) {
		ID = iD;
		this.talk = talk;
		this.grade = grade;
		this.activity = activity;
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTalk() {
		return talk;
	}

	public void setTalk(String talk) {
		this.talk = talk;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public Kindergarden getKindergarden() {
		return kindergarden;
	}

	public void setKindergarden(Kindergarden kindergarden) {
		this.kindergarden = kindergarden;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
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
		Opinion other = (Opinion) obj;
		if (ID != other.ID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Opinion [ID=" + ID + ", talk=" + talk + ", grade=" + grade + ", kindergarden=" + kindergarden
				+ ", activity=" + activity + "]";
	}

	
	
	

}
