package Model;

import java.util.ArrayList;
import java.util.HashMap;

import Connections.DBconn;

public class SysData {
	
	private static SysData data;
	private ArrayList<TakesPlace> actsInClasses;
	private ArrayList<TrainingForAssistant> assistantsInTrainings;
	private ArrayList<AssistantJoinsActivity> assistantJoinsActivites;
	private ArrayList<AssistInClass> assistInClasss;
	private HashMap<String,Person> persons;
	private HashMap<Integer,Kindergarden> kindergartens;
	private HashMap<Integer,Opinion> opinions;
	private HashMap<Integer,Shift> shifts;
	private HashMap<Integer,Training> trainingList;
	private HashMap<Integer,Activity> activities;
	private HashMap<Integer,TrainingParameter> tParams;
	
	
	
	private SysData() {
		
		actsInClasses = new ArrayList<>();
		assistantsInTrainings = new ArrayList<>();
		assistantJoinsActivites = new ArrayList<>();
		assistInClasss = new ArrayList<>();
		persons = new HashMap<>();
		kindergartens = new HashMap<>();
		opinions = new HashMap<>();
		shifts = new HashMap<>();
		trainingList = new HashMap<>();
		activities = new HashMap<>();
		tParams = new HashMap<>(); 
	}
	
	public static SysData getInstance(){
		if(data == null)
			data = new SysData();
		return data;
	}
	
	
	public ArrayList<AssistantJoinsActivity> getAssistantJoinsActivites() {
		return assistantJoinsActivites;
	}

	public void setAssistantJoinsActivites(ArrayList<AssistantJoinsActivity> assistantJoinsActivites) {
		this.assistantJoinsActivites = assistantJoinsActivites;
	}

	public ArrayList<AssistInClass> getAssistInClasss() {
		return assistInClasss;
	}

	public void setAssistInClasss(ArrayList<AssistInClass> assistInClasss) {
		this.assistInClasss = assistInClasss;
	}

	public HashMap<Integer, TrainingParameter> gettParams() {
		return tParams;
	}

	public void settParams(HashMap<Integer, TrainingParameter> tParams) {
		this.tParams = tParams;
	}

	public HashMap<Integer, Activity> getActivities() {
		return activities;
	}

	public void setActivities(HashMap<Integer, Activity> activities) {
		this.activities = activities;
	}

	public ArrayList<TakesPlace> getActsInClasses() {
		return actsInClasses;
	}

	public void setActsInClasses(ArrayList<TakesPlace> actsInClasses) {
		this.actsInClasses = actsInClasses;
	}

	public HashMap<String, Person> getPersons() {
		return persons;
	}

	public void setPersons(HashMap<String, Person> persons) {
		this.persons = persons;
	}

	public HashMap<Integer, Kindergarden> getKindergartens() {
		return kindergartens;
	}

	public void setKindergartens(HashMap<Integer, Kindergarden> kindergartens) {
		this.kindergartens = kindergartens;
	}

	public HashMap<Integer, Opinion> getOpinions() {
		return opinions;
	}

	public void setOpinions(HashMap<Integer, Opinion> opinions) {
		this.opinions = opinions;
	}

	public HashMap<Integer, Shift> getShifts() {
		return shifts;
	}

	public void setShifts(HashMap<Integer, Shift> shifts) {
		this.shifts = shifts;
	}

	public HashMap<Integer, Training> getTrainingList() {
		return trainingList;
	}

	public void setTrainingList(HashMap<Integer, Training> trainingList) {
		this.trainingList = trainingList;
	}

	/*public ArrayList<Class> getClasses() {
		return classes;
	}

	public void setClasses(ArrayList<Class> classes) {
		this.classes = classes;
	}*/

	public ArrayList<TrainingForAssistant> getAssistantsInTrainings() {
		return assistantsInTrainings;
	}

	public void setAssistantsInTrainings(ArrayList<TrainingForAssistant> assistantsInTrainings) {
		this.assistantsInTrainings = assistantsInTrainings;
	}
	
	
	
}
