package Connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import Model.Activity;
import Model.Kid;
import Model.Kindergarden;
import Model.Opinion;
import Model.SysData;
import Model.TakesPlace;

public class DBqueries {
	
	private DBconn db;
	private Connection conn;
	private static DBqueries instance;
	private SysData data;
	
	private DBqueries() throws Exception{
		db = DBconn.getInstance();
	    conn = db.getConnection();
	    data = SysData.getInstance();
	}
	
	public static DBqueries getInstance() throws Exception{
		if(instance == null){
			instance = new DBqueries();
			 
			return instance;
		}else{
			return instance;
		}
	}
	
	public ArrayList<Object[]> GetSecondReport() throws SQLException{
		
		ArrayList<Object[]> hm = new ArrayList<Object[]>();
		PreparedStatement cs;
		Object[] m=null;
		ResultSet RS = getFromDB("Query2");
		while(RS.next()){
			m = new Object[]{RS.getString(1),RS.getString(2),RS.getInt(3),RS.getInt(4),RS.getString(5),RS.getString(6),RS.getInt(7)};
			hm.add(m);
		}
		return hm;
		
	}
	
	public ArrayList<Object[]> GetThreeReport() throws SQLException{
		
		ArrayList<Object[]> hm = new ArrayList<Object[]>();
		PreparedStatement cs;
		Object[] m=null;
		ResultSet RS = getFromDB("Query3");
		while(RS.next()){
			m = new Object[]{RS.getString(1),RS.getString(2),RS.getInt(3),RS.getDouble(4)};
			hm.add(m);
		}
		return hm;
	}
	
	public ArrayList<Object[]> GetFourReport() throws SQLException{
		
		ArrayList<Object[]> hm = new ArrayList<Object[]>();
		PreparedStatement cs;
		Object[] m=null;
		ResultSet RS = getFromDB("Query4");
		while(RS.next()){
			m = new Object[]{RS.getInt(1),RS.getString(2),RS.getString(3)};
			hm.add(m);
		}
		return hm;
		
	}
	public ArrayList<Object[]> GetFiveReport() throws SQLException{
		
		ArrayList<Object[]> hm = new ArrayList<Object[]>();
		PreparedStatement cs;
		Object[] m=null;
		ResultSet RS = getFromDB("Query5");
		while(RS.next()){
			m = new Object[]{RS.getString(1)};
			hm.add(m);
		}
		return hm;
		
	}
	public ArrayList<Object[]> GetSixReport() throws SQLException{
		
		ArrayList<Object[]> hm = new ArrayList<Object[]>();
		PreparedStatement cs;
		Object[] m=null;
		ResultSet RS = getFromDB("Query6");
		while(RS.next()){
			m = new Object[]{RS.getString(1),RS.getString(2),RS.getInt(3),
					RS.getString(4),RS.getInt(5),RS.getInt(6),RS.getString(7),RS.getTime(8),RS.getTime(9)};
			hm.add(m);
		}
		return hm;
	}
	public ArrayList<Object[]> GetSevenReport() throws SQLException{
		
		ArrayList<Object[]> hm = new ArrayList<Object[]>();
		PreparedStatement cs;
		Object[] m=null;
		ResultSet RS = getFromDB("Query7");
		while(RS.next()){
			m = new Object[]{RS.getInt(1),RS.getInt(2),RS.getDouble(3)};
			hm.add(m);
		}
		return hm;
	}
	public ArrayList<Object[]> GetEightReport() throws SQLException{
		
		ArrayList<Object[]> hm = new ArrayList<Object[]>();
		PreparedStatement cs;
		Object[] m=null;
		ResultSet RS = getFromDB("Query8");
		while(RS.next()){
			m = new Object[]{RS.getString(1),RS.getString(2)};
			hm.add(m);
		}
		return hm;
	}
	public HashMap <Kindergarden,Double> privateKindergarten() throws SQLException{ 

		HashMap <Kindergarden,Double> hm = new HashMap<>();
		
		PreparedStatement cs;
		ResultSet RS = getFromDB("privateKinsNotFull");
		
		while(RS.next()){
			if(data.getKindergartens().containsKey(RS.getInt(1))){
				double val = RS.getDouble(4);
				if(RS.wasNull())
					val = -1;
				hm.put(data.getKindergartens().get(RS.getInt(1)), val);
			}
		}
		return hm;
		
	}
	
	
	public ArrayList<TakesPlace> getActivitiesforKid(String kidID,int KindergardenID,int ClassID) throws SQLException{
		ArrayList<TakesPlace> ActivitiesID = new ArrayList<>();
		PreparedStatement cs;
		ResultSet RS = getFromDB("getActivitiesForKid","'"+kidID+"'", String.valueOf(KindergardenID),String.valueOf(ClassID));
		while(RS.next()){
			int kint = RS.getInt(1);
			if (RS.wasNull())
				kint = -1;
			int cint = RS.getInt(2);
			if (RS.wasNull())
				cint = -1;
			int aint = RS.getInt(3);
			if (RS.wasNull())
				aint = -1;
			
			if(data.getKindergartens().containsKey(kint) && data.getActivities().containsKey(aint)){
				if(data.getKindergartens().get(kint).getClasses().containsKey(cint)){
					Model.Class c = data.getKindergartens().get(kint).getClasses().get(cint);
					Activity a = data.getActivities().get(aint);
					
					TakesPlace tp = new TakesPlace(c,a), tc;
					if(data.getActsInClasses().contains(tp)){
					    int index = data.getActsInClasses().indexOf(tp);
						tc = data.getActsInClasses().get(index);
						ActivitiesID.add(tc);
					}
				}
			}
		}	
	
		return ActivitiesID;
	}
	
	public ArrayList<Kid> getAllKids() throws SQLException{ 
		
		ArrayList<Kid> kids = new ArrayList<>();
		PreparedStatement cs;
		ResultSet RS = getFromDB("getKids");
		
		while(RS.next()){
			if(data.getPersons().containsKey(RS.getString(1)));
				if(data.getPersons().get(RS.getString(1)) instanceof Kid)
					kids.add((Kid)data.getPersons().get(RS.getString(1)));
		}
		
		return kids ;
	}
	
	public ArrayList<Kid> getAllKidsInClass(int kID, int cID) throws SQLException{ 
		ArrayList<Kid> kids = new ArrayList<>();
		PreparedStatement cs;
		ResultSet RS = getFromDB("getKidsInClass", String.valueOf(kID),String.valueOf(cID));
		
		while(RS.next()){
			if(data.getPersons().containsKey(RS.getString(1)));
				if(data.getPersons().get(RS.getString(1)) instanceof Kid)
					kids.add((Kid)data.getPersons().get(RS.getString(1)));
		}
		
		return kids ;
	}
	
	public ArrayList<TakesPlace> getSignedFor(String kidID,int kID, int cID) throws SQLException{ 
		ArrayList<TakesPlace> acts = new ArrayList<>();
		PreparedStatement cs;
		ResultSet RS = getFromDB("getSignedFor", "'"+kidID+"'",String.valueOf(kID),String.valueOf(cID));
		ArrayList<TakesPlace> hm = new ArrayList<>();
		
		while(RS.next()){
			int kint = RS.getInt(1);
			if (RS.wasNull())
				kint = -1;
			int cint = RS.getInt(2);
			if (RS.wasNull())
				cint = -1;
			int aint = RS.getInt(3);
			if (RS.wasNull())
				aint = -1;
			
			if(data.getKindergartens().containsKey(kint) && data.getActivities().containsKey(aint)){
				if(data.getKindergartens().get(kint).getClasses().containsKey(cint)){
					Model.Class c = data.getKindergartens().get(kint).getClasses().get(cint);
					Activity a = data.getActivities().get(aint);
					
					TakesPlace tp = new TakesPlace(c,a), tc;
					if(data.getActsInClasses().contains(tp)){
					    int index = data.getActsInClasses().indexOf(tp);
						tc = data.getActsInClasses().get(index);
						hm.add(tc);
					}
				}
			}
		}	
		return hm;
	}
	
	public HashMap<Opinion,Object> getOpinionsForActsOrKins(int kID, int aID) throws SQLException{ 
		HashMap<Opinion,Object> opins = new HashMap<>();;
		PreparedStatement cs;
		ResultSet RS;
		
		if(kID > 0){
			 RS = getFromDB("getOpins",String.valueOf(kID),"");
			 while(RS.next()){
					if(data.getKindergartens().containsKey(RS.getInt("kindergardenID")) 
							&& data.getOpinions().containsKey(RS.getInt("OpinID")));
					opins.put(data.getOpinions().get(RS.getInt("OpinID")),data.getKindergartens().get(RS.getInt("kindergardenID")));
				}
		}
		else if(aID > 0){
			RS = getFromDB("getOpins","",String.valueOf(aID));
			if(data.getActivities().containsKey(RS.getInt("activityID")) 
					&& data.getOpinions().containsKey(RS.getInt("OpinID")));
			opins.put(data.getOpinions().get(RS.getInt("OpinID")),data.getActivities().get(RS.getInt("activityID")));
		}
		
		return opins;
		
	}
	
	public ArrayList <TakesPlace> activitiesInClass(int kID, int cID) throws SQLException{ 
		
		PreparedStatement cs;
		ResultSet RS = getFromDB("activsInClass",String.valueOf(kID), String.valueOf(cID));
		
		ArrayList<TakesPlace> hm = new ArrayList<>();
		
		while(RS.next()){
			int kint = RS.getInt(1);
			if (RS.wasNull())
				kint = -1;
			int cint = RS.getInt(2);
			if (RS.wasNull())
				cint = -1;
			int aint = RS.getInt(3);
			if (RS.wasNull())
				aint = -1;
			
			if(data.getKindergartens().containsKey(kint) && data.getActivities().containsKey(aint)){
				if(data.getKindergartens().get(kint).getClasses().containsKey(cint)){
					Model.Class c = data.getKindergartens().get(kint).getClasses().get(cint);
					Activity a = data.getActivities().get(aint);
					
					TakesPlace tp = new TakesPlace(c,a), tc;
					if(data.getActsInClasses().contains(tp)){
					    int index = data.getActsInClasses().indexOf(tp);
						tc = data.getActsInClasses().get(index);
						hm.add(tc);
					}
				}
			}
		}	
		return hm;
		
	}
	public ArrayList<Object[]> getElevenReport(int kindergardenID,int classNumber) throws SQLException{
		ArrayList<Object[]> ok = new ArrayList<>();
		Object[] m=null;
		ResultSet RS = getFromDB("assistanceInClass",String.valueOf(kindergardenID),String.valueOf(classNumber));
		while(RS.next()){
			m =new Object[]{RS.getString(1),RS.getString(2),RS.getInt(3)};
			ok.add(m);
		}
		return ok;
	}
	public ArrayList<TakesPlace> getTakesPlaceForClass(int kID, int cID, int day) throws SQLException{ 
		ArrayList<TakesPlace> tplaces = new ArrayList<>();
		ResultSet RS = getFromDB("getTakesPlaceForClass",String.valueOf(kID), String.valueOf(cID), String.valueOf(day));
		Model.Class c;
		Activity a;
		int index = -1;
		TakesPlace tp;
		
		while(RS.next()){
			if(data.getKindergartens().containsKey(RS.getInt(1))){
					
				c = data.getKindergartens().get(RS.getInt(1)).getClasses().get(RS.getInt(2));
				a = data.getActivities().get(RS.getInt(3));
				index = data.getActsInClasses().indexOf(new TakesPlace(c,a));
				if(index >= 0){
					tp = data.getActsInClasses().get(index);
					tplaces.add(tp);
				}
			}
		}
		return tplaces;
	}
	
	private ResultSet getFromDB(String...str) throws SQLException{
		PreparedStatement cs;
		String query = "exec ";
			
		for(int i = 0; i < str.length; i++){
			if(str[i].isEmpty())
				str[i] = "null";
			if(i == str.length-1 || i == 0)
				query += str[i] + " ";
			else
				query += str[i] + ", ";
		}

		cs = conn.prepareStatement(query);
		cs.setEscapeProcessing(true);
		cs.setQueryTimeout(90);
		return cs.executeQuery();
			
	}



	
	
}
