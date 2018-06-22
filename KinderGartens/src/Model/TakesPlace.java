package Model;

import java.sql.Time;
import java.util.HashMap;

public class TakesPlace {
	
	private Class cLasss;
	private Activity activity;
	private int dayInWeek;
	private Time startTime;
	private Operator operator;
	private HashMap<String,Kid> kids;
	
	public TakesPlace(Class cLasss, Activity activity) {
		this.cLasss = cLasss;
		this.activity = activity;
	}

	public TakesPlace(Class cLasss, Activity activity, Operator operator, int dayInWeek, Time startTime) {
		kids = new HashMap<>();
		this.cLasss = cLasss;
		this.activity = activity;
		this.operator = operator;
		this.dayInWeek = dayInWeek;
		this.startTime = startTime;
	}
	
	
	public HashMap<String, Kid> getKids() {
		return kids;
	}

	public void setKids(HashMap<String, Kid> kids) {
		this.kids = kids;
	}

	public Class getcLasss() {
		return cLasss;
	}

	public void setcLasss(Class cLasss) {
		this.cLasss = cLasss;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public int getDayInWeek() {
		return dayInWeek;
	}

	public void setDayInWeek(int dayInWeek) {
		this.dayInWeek = dayInWeek;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activity == null) ? 0 : activity.hashCode());
		result = prime * result + ((cLasss == null) ? 0 : cLasss.hashCode());
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
		TakesPlace other = (TakesPlace) obj;
		if (activity == null) {
			if (other.activity != null)
				return false;
		} else if (!activity.equals(other.activity))
			return false;
		if (cLasss == null) {
			if (other.cLasss != null)
				return false;
		} else if (!cLasss.equals(other.cLasss))
			return false;
		return true;
	}
	
	
	
	
}
