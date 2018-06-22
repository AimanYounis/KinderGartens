package Connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import Model.Activity;
import Model.AssistInClass;
import Model.Assistant;
import Model.AssistantJoinsActivity;
import Model.Kid;
import Model.Kindergarden;
import Model.Operator;
import Model.Opinion;
import Model.Shift;
import Model.SysData;
import Model.TakesPlace;
import Model.Teacher;
import Model.Training;
import Model.TrainingForAssistant;
import Model.TrainingParameter;

public class InitDB {
	
	private SysData data;
	private DBconn db;
	private static InitDB instance;
	private Connection conn;
	
	private InitDB() throws Exception{
		data = SysData.getInstance();
		db = DBconn.getInstance();
		conn = db.getConnection();
		
		initActivities();
		getKindergartens();
		initTeachers();
		initTParams();
		initSifts();
		initOperators();
		intAssistants();
		initTrainings();
		getClasses();
		getTakesPlace();
		initAssistInClass();
		initKids();
		iniKidsInActivities();
		TsForAssistants();
		getAssistantJoinsActivity();
		initOpinions();
	}
	
	// for use only once
	public static void makeInstance() throws Exception{
		if(instance == null)
			instance = new InitDB();
	}
	
	private void initTeachers() throws SQLException {
		PreparedStatement cs;
		ResultSet RS;
		cs = conn.prepareStatement("exec getTeachers");
		cs.setEscapeProcessing(true);
		cs.setQueryTimeout(90);
		RS = cs.executeQuery();
		
		Teacher tmp;
		String adrs;
		while(RS.next()){
			
			tmp = new Teacher(RS.getString(1),RS.getString(2),RS.getString(3),RS.getDate(4));
			adrs = RS.getString(5);
			if(RS.wasNull())
				adrs = null;
			tmp.setAddress(adrs);
			data.getPersons().put(tmp.getID(), tmp);
				
		}
		
	}
	
	private void initOperators() throws SQLException {
		PreparedStatement cs;
		ResultSet RS;
		cs = conn.prepareStatement("exec getOperators");
		cs.setEscapeProcessing(true);
		cs.setQueryTimeout(90);
		RS = cs.executeQuery();
		
		Operator tmp;
		
		while(RS.next()){
			
			tmp = new Operator(RS.getString(1),RS.getString(2),RS.getString(3));
			data.getPersons().put(tmp.getID(), tmp);
		}	
		
	}
	

	private void intAssistants() throws SQLException {
		PreparedStatement cs;
		ResultSet RS;
		cs = conn.prepareStatement("exec getAssistants");
		cs.setEscapeProcessing(true);
		cs.setQueryTimeout(90);
		RS = cs.executeQuery();
		
		Assistant tmp;
		
		while(RS.next()){
			
			tmp = new Assistant(RS.getString(1),RS.getString(2),RS.getString(3),RS.getDate(4));
			data.getPersons().put(tmp.getID(), tmp);
		}	
		
	}
	
	private void initKids() throws SQLException {
		PreparedStatement cs;
		ResultSet RS;
		cs = conn.prepareStatement("exec getKids");
		cs.setEscapeProcessing(true);
		cs.setQueryTimeout(90);
		RS = cs.executeQuery();
		
		Kid tmp;
		Model.Class c;
		
		while(RS.next()){
			
			tmp = new Kid(RS.getString(1),RS.getString(2),RS.getString(3)
						,RS.getDate(4),RS.getString(5),RS.getString(6),RS.getString(7)
						,RS.getFloat(8),RS.getFloat(9),RS.getString(10),RS.getString(11)
						,RS.getInt(12),null);
			
			// get class from Kindergarten object that has class list
			c = data.getKindergartens().get(RS.getInt(13)).getClasses().get(RS.getInt(14));
			tmp.setcLass(c);
			c.getKids().put(tmp.getID(),tmp);
			data.getPersons().put(tmp.getID(), tmp);
		}	
		
	}
	private void initSifts() throws SQLException{
		PreparedStatement cs;
		ResultSet RS;
		cs = conn.prepareStatement("exec getShifts");
		cs.setEscapeProcessing(true);
		cs.setQueryTimeout(90);
		RS = cs.executeQuery();
		
		Shift tmp;
		int val;
		while(RS.next()){
			val = RS.getInt(2);

			if(RS.wasNull())
				val = 0;
			
			tmp = new Shift(RS.getInt(1),val,RS.getString(3));
			data.getShifts().put(tmp.getNumber(), tmp);
		}
	}
	
	private void initOpinions() throws SQLException{
		PreparedStatement cs;
		ResultSet RS;
		cs = conn.prepareStatement("exec getOpinions");
		cs.setEscapeProcessing(true);
		cs.setQueryTimeout(90);
		RS = cs.executeQuery();
		
		Opinion tmp;
		int val;
		
		while(RS.next()){
			val = RS.getInt(4);
			// if kindergartenID was returned to be null then activityID must not be null 
			if(RS.wasNull()){
				tmp = new Opinion(RS.getInt(1),RS.getString(2),RS.getInt(3),data.getActivities().get(RS.getInt(5)));
				data.getActivities().get(RS.getInt(5)).getOpinions().put(tmp.getID(), tmp);
			}
			else{
				tmp = new Opinion(RS.getInt(1),RS.getString(2),RS.getInt(3),data.getKindergartens().get(RS.getInt(4)));
				data.getKindergartens().get(RS.getInt(4)).getOpinions().put(tmp.getID(), tmp);
			}
			data.getOpinions().put(tmp.getID(), tmp);
		}
	}
	
	private void initActivities() throws SQLException{
		PreparedStatement cs;
		ResultSet RS;
		cs = conn.prepareStatement("exec getActivities");
		cs.setEscapeProcessing(true);
		cs.setQueryTimeout(90);
		RS = cs.executeQuery();
		
		Activity tmp;
		while(RS.next()){
			tmp = new Activity(RS.getInt(1),RS.getString(2),RS.getInt(3),RS.getInt(4));
			
			data.getActivities().put(tmp.getID(), tmp);
		}
	}
	
	private void initTrainings() throws SQLException{
		PreparedStatement cs;
		ResultSet RS;
		cs = conn.prepareStatement("exec getTrainings");
		cs.setEscapeProcessing(true);
		cs.setQueryTimeout(90);
		RS = cs.executeQuery();
		
		Training tmp;
		while(RS.next()){
			tmp = new Training(RS.getInt(1),RS.getString(2));
			
			data.getTrainingList().put(tmp.getID(), tmp);
		}
	}
	
	private void initTParams() throws SQLException{
		PreparedStatement cs;
		ResultSet RS;
		cs = conn.prepareStatement("exec getTParams");
		cs.setEscapeProcessing(true);
		cs.setQueryTimeout(90);
		RS = cs.executeQuery();
		
		TrainingParameter tmp;
		
		while(RS.next()){
			tmp = new TrainingParameter(RS.getInt(1),RS.getString(2),RS.getInt(3),RS.getInt(4));
			
			data.gettParams().put(tmp.getNumber(), tmp);
		}
	}
	
	private void TsForAssistants() throws SQLException{
		PreparedStatement cs;
		ResultSet RS;
		cs = conn.prepareStatement("exec getTsForAssistants");
		cs.setEscapeProcessing(true);
		cs.setQueryTimeout(90);
		RS = cs.executeQuery();
		
		TrainingForAssistant tmp;
		Assistant ast;
		Training tr;
		
		while(RS.next()){
			ast = (Assistant)data.getPersons().get(RS.getString(1));
			tr = data.getTrainingList().get(RS.getInt(2));
			tmp = new TrainingForAssistant(ast,tr,RS.getDate(3));
			
			data.getAssistantsInTrainings().add(tmp);
		}
	}
	
	private void getKindergartens() throws SQLException{
		/*
		 * 
		 * GETTING THE DATA FROM SQL SERVER TO THE SYSTEM
		 * 
		 * */
		PreparedStatement cs;
		ResultSet RS;
		cs=conn.prepareStatement("exec Get_Kindergardens");
		cs.setEscapeProcessing(true);
		cs.setQueryTimeout(90);
		RS = cs.executeQuery();		

		while(RS.next()){	
			int ID = RS.getInt(1);
			String name = RS.getString(2);
			String street = RS.getString(3);
			String houseNumber = RS.getString(4);
			String city = RS.getString(5);
			float latitude = RS.getFloat(6);
			float longitude = RS.getFloat(7);
			boolean bit = RS.getBoolean(8);
			int price = RS.getInt(9);
			Kindergarden kin = new Kindergarden(ID, name, street, houseNumber, city, latitude, longitude, bit, price);
			data.getKindergartens().put(ID, kin);
			}
		}
	
	private void getClasses() throws SQLException{
		
		PreparedStatement cs;
		ResultSet RS;
		cs=conn.prepareStatement("exec Get_Classes");
		cs.setEscapeProcessing(true);
		cs.setQueryTimeout(90);
		RS = cs.executeQuery();		
		
		while(RS.next()){
				
			int KindergardenID = RS.getInt(1);
			int Number = RS.getInt(2);
			String name = RS.getString(3);
			int miniAge = RS.getInt(4);
			int maxAge = RS.getInt(5);
			int maxKids = RS.getInt(6);
			String Teacher = RS.getString(7);
				
			data.getKindergartens().get(KindergardenID).getClasses().put(Number,new Model.Class(data.getKindergartens().get(KindergardenID), Number, name, miniAge, maxAge, maxKids,(Teacher) data.getPersons().get(Teacher)));	
		}
	}
	
	private void initAssistInClass() throws SQLException{
		
		PreparedStatement cs;
		ResultSet RS;
		cs=conn.prepareStatement("exec getAssistInClass");
		cs.setEscapeProcessing(true);
		cs.setQueryTimeout(90);
		RS = cs.executeQuery();		
		
		AssistInClass tmp;
		Assistant ast;
		Model.Class c;
		Shift sf;
		
		while(RS.next()){
			
			ast = (Assistant) data.getPersons().get(RS.getString(1));
			c = data.getKindergartens().get(RS.getInt(2)).getClasses().get(RS.getInt(3));
			sf = data.getShifts().get(RS.getInt(4));
			
			tmp = new AssistInClass(ast,c,sf);
			
			data.getAssistInClasss().add(tmp);
			
		}
	}
	
	private void iniKidsInActivities() throws SQLException{
		PreparedStatement cs;
		ResultSet RS;
		cs=conn.prepareStatement("exec getKidsInActivities");
		cs.setEscapeProcessing(true);
		cs.setQueryTimeout(90);
		RS = cs.executeQuery();
		
		Kid k;
		TakesPlace tp;
		Model.Class c;
		Activity act;
		int index;
		
		while(RS.next()){
			k = (Kid)data.getPersons().get(RS.getString(1));
			c = data.getKindergartens().get(RS.getInt(2)).getClasses().get(RS.getInt(3));
			act = data.getActivities().get(RS.getInt(4));
			
			tp = new TakesPlace(c,act);
			
			index = data.getActsInClasses().indexOf(tp);
			data.getActsInClasses().get(index).getKids().put(k.getID(), k);
			((Kid)data.getPersons().get(RS.getString(1))).getActivities().add(data.getActsInClasses().get(index));
		}
		
	}
		
	private void getTakesPlace() throws SQLException{

		PreparedStatement cs;
		ResultSet RS;
		cs=conn.prepareStatement("exec Get_TakePlace");
		cs.setEscapeProcessing(true);
		cs.setQueryTimeout(90);
		RS = cs.executeQuery();		
			
		while(RS.next()){
				
			int KindergardenID = RS.getInt(1);
			int classNumber = RS.getInt(2);
			int activityID = RS.getInt(3);
			String operatorID = RS.getString(4);
			int dayInWeek = RS.getInt(5);
			Time time = RS.getTime(6);
				
			Kindergarden K = data.getKindergartens().get(KindergardenID);
			Model.Class C = data.getKindergartens().get(KindergardenID).getClasses().get(classNumber);
			Activity A = data.getActivities().get(activityID);
			Operator O = (Operator) data.getPersons().get(operatorID);
				
			data.getActsInClasses().add(new TakesPlace(C, A, O, dayInWeek, time));
				
		}
	}
		
		
	private void getAssistantJoinsActivity() throws SQLException{
		TakesPlace takesplace = null ;
		TrainingForAssistant TFA = null;
		PreparedStatement cs;
		ResultSet RS;
		cs=conn.prepareStatement("exec AssistantInActivity");
		cs.setEscapeProcessing(true);
		cs.setQueryTimeout(90);
		RS = cs.executeQuery();	
		
		while(RS.next()){
			int KindergardenID = RS.getInt(1);
			int classNumber = RS.getInt(2);
			int activityID = RS.getInt(3);
			String assistantID = RS.getString(4);
			int TrainingID = RS.getInt(5);
				
				
			for(TakesPlace TP : data.getActsInClasses()){
				if(TP.equals(new TakesPlace(data.getKindergartens().get(KindergardenID).getClasses().get(classNumber), data.getActivities().get(activityID)))){
					takesplace = TP;
				}
			}
			for(TrainingForAssistant T : data.getAssistantsInTrainings()){
				if(T.equals(new TrainingForAssistant((Assistant) data.getPersons().get(assistantID), data.getTrainingList().get(TrainingID)))){
					TFA=T;
				}
			}
				
			data.getAssistantJoinsActivites().add(new AssistantJoinsActivity(takesplace, TFA));
				
				
		}	
			
	}
}
