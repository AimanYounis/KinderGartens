package Model;

import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JComboBox;

import Connections.DBOperations;
import Connections.DBqueries;
import Connections.InitDB;
import Utills.Constants;

public class Model {
	
	private static Model instance;
	private SysData data;
	private DBOperations operation;
	
	private Model() throws Exception{
		
		InitDB.makeInstance();
		data = SysData.getInstance();
		operation = DBOperations.getInstance();
	}
	
	public static Model getInstance() throws Exception {
		if(instance == null)
			instance = new Model();
		return instance;
	}
	
	public boolean AddActivitytoKids(String KidID, int ActID) throws Exception{
		if(KidID != null &&  ActID > 0){
			if(!data.getActivities().containsKey(ActID)){
				throw new Exception("No Such Activity in the Database");
			}
			
			if(!data.getPersons().containsKey(KidID)){
				throw new Exception("No Such Person in Database");
			}
			
			Kid k = (Kid) data.getPersons().get(KidID);
			int classnumber = k.getcLass().getNumber();
			int kindergardenid = k.getcLass().getKindergarden().getID();
			
			boolean id ;
			id = operation.insertToDB("AddActivityToKid","'"+KidID+"'",String.valueOf(ActID),String.valueOf(classnumber),String.valueOf(kindergardenid));
			if(id){
				TakesPlace Tp = new TakesPlace(k.getcLass(), data.getActivities().get(ActID));
				for(TakesPlace TPe : data.getActsInClasses()){
					if(TPe.equals(Tp)){
						TPe.getKids().put(KidID, k);
						k.getActivities().add(TPe);
						break;
					}
				}
				return true;
			}
		}
		return false;
		
	}
	public HashMap<String,Integer> getNumKinderGartens(){ // new
		return Statistics.numKidsInKins();
	}
	public ArrayList<Object[]> getElevenReport(int kinID,int classID) throws SQLException, Exception{
		return DBqueries.getInstance().getElevenReport(kinID, classID);
	}
	public ArrayList<Object[]> getSecondReport() throws SQLException, Exception{
		return DBqueries.getInstance().GetSecondReport();
	}
	public ArrayList<Object[]> getThreeReport() throws SQLException, Exception{
		return DBqueries.getInstance().GetThreeReport();
	}
	public ArrayList<Object[]> getFourReport() throws SQLException, Exception{
		return DBqueries.getInstance().GetFourReport();
	}
	public ArrayList<Object[]> getFiveReport() throws SQLException, Exception{
		return DBqueries.getInstance().GetFiveReport();
	}
	public ArrayList<Object[]> getSixReport() throws SQLException, Exception{
		return DBqueries.getInstance().GetSixReport();
	}
	public ArrayList<Object[]> getSevenReport() throws SQLException, Exception{
		return DBqueries.getInstance().GetSevenReport();
	}
	public ArrayList<Object[]> getEightReport() throws SQLException, Exception{
		return DBqueries.getInstance().GetEightReport();
	}
	public boolean AddKidToClass(String iD, String firstName, String surName, Date dateOfBirth, String street, String houseNumber, String city, float latitude, float longitude,
			String fatherName, String motherName, int placeInFamily, int kindergartenId, int cLassId ) throws Exception{
		
		if(iD != null && firstName != null && surName != null 
			&& dateOfBirth != null && street != null && houseNumber != null 
			&& city != null && fatherName != null && motherName != null && placeInFamily > 0){
			
			String EXCEC = "addKidToClass";
			SimpleDateFormat sdf = new SimpleDateFormat(Constants.DF);
			String dateStr = sdf.format(dateOfBirth);
			int age = getAge(dateOfBirth);
			
			if(data.getPersons().containsKey(iD)){
				EXCEC = "updateKidDetails";
				
				cLassId = ((Kid)data.getPersons().get(iD)).getcLass().getNumber();
				kindergartenId = ((Kid)data.getPersons().get(iD)).getcLass().getKindergarden().getID();
			}
			
			// parents picked to register their kid in a public kindergarten
			if(kindergartenId < 0){
				
				ArrayList<Kindergarden> ks = new ArrayList<>();
				ArrayList<Class> cs;
				double minDist = 0, tmpDist;
				int tmpkID = 0, tmpCID = 0, i = 0;
				boolean notFound = true;
				
				age = getAge(dateOfBirth);
				
				for(Kindergarden k : data.getKindergartens().values()){
					if(!k.isPrivate()){
						ks.add(k);
					}
				}
				
				// look for closest kindergarten and assign kid to appropriate class
				while(notFound){
					
					if(ks.isEmpty())
						throw new Exception("Couldn't find a suitable public kindergarten for your kid.");
					
					for(Kindergarden k : ks){
						tmpDist = getDistance(latitude, k.getLatitude(), longitude, k.getLongitude());
						if(i == 0){ // determine weather this is the first object or not
							minDist = tmpDist;
							tmpkID = k.getID();
							i++;
						}
						if(tmpDist > minDist){
							tmpkID = k.getID();
							minDist = tmpDist;
						}
					}
					cs = new ArrayList<>(data.getKindergartens().get(tmpkID).getClasses().values());
					for(Class c : cs){
						if(c.getKids().size() < c.getMaximumKids() &&  age <= c.getMaximumAge() && age >= c.getMinimumAge() ){
							notFound = false;
							tmpCID = c.getNumber();
							break;
						}
					}
					// all classes are not suitable for the kid in this kindergarten 
					if(notFound){
						ks.remove(ks.get(ks.indexOf(new Kindergarden(tmpkID)))); //eliminate this kindergarten from consideration
					}else
						break;
				}
				System.out.println( iD +" "+  firstName+" "+ surName+" "+  dateStr+" "+  street+" "+  houseNumber+" "+  city+" "+  latitude+" "+ longitude+" "+
						 fatherName+" "+  motherName+" "+placeInFamily+" "+ tmpkID+" "+  tmpCID+" Age "+age+" Dist "+ minDist);
				/* send info to database and try to insert if 
				 * insertion is done return true else return false*/
				if(operation.insertToDB(EXCEC, "'"+iD+"'", "'"+firstName+"'", "'"+surName+"'", "'"+dateStr+"'", "'"+street+"'", "'"+houseNumber+"'", "'"+city+"'", String.valueOf(latitude), String.valueOf(longitude),
						"'"+fatherName+"'", "'"+motherName+"'", String.valueOf(placeInFamily),
						String.valueOf(tmpkID), String.valueOf(tmpCID))){
					
					Class c = data.getKindergartens().get(tmpkID).getClasses().get(tmpCID);
					Kid kid = new Kid(iD, firstName, surName, dateOfBirth, street, houseNumber, city, latitude, longitude,
								fatherName, motherName, placeInFamily, c);
					data.getPersons().put(iD,kid);
					c.getKids().put(iD, kid);
				
					return true;
				}
				
			}else if(kindergartenId > 0 && cLassId > 0){ // registering for private class
				
				if(!data.getKindergartens().containsKey(kindergartenId) || !data.getKindergartens().get(kindergartenId).getClasses().containsKey(cLassId))
					throw new Exception("such kindergarten or class does not exist in database!");
				
				Class cc = data.getKindergartens().get(kindergartenId).getClasses().get(cLassId);
				
				if(cc.getKids().size() == cc.getMaximumKids())
					throw new Exception("Picked Class is full!");
				if(age < cc.getMinimumAge() || age > cc.getMaximumAge())
					throw new Exception("Your kid is under/over age limit for this class!");		
				
				if(operation.insertToDB(EXCEC,"'"+iD+"'", "'"+firstName+"'", "'"+surName+"'", "'"+dateStr+"'", "'"+street+"'", "'"+houseNumber+"'", "'"+city+"'", String.valueOf(latitude), String.valueOf(longitude),
						"'"+fatherName+"'", "'"+motherName+"'", String.valueOf(placeInFamily)
						, String.valueOf(kindergartenId), String.valueOf(cLassId))){
					
					Kid kid = new Kid(iD, firstName, surName, dateOfBirth, street, houseNumber, city, latitude, longitude,
								fatherName, motherName, placeInFamily, cc);
					data.getPersons().put(iD,kid);
					cc.getKids().put(iD, kid);
				
					return true;
				}
			}
		}
		return false;
	}

	public boolean RemoveKidFromActivity(String KidID, int ActID) throws Exception{
		if(KidID != null && ActID > 0){
			if(!data.getActivities().containsKey(ActID)){
				throw new Exception("No Such Activity in the Database");}
			
			if(!data.getPersons().containsKey(KidID)){
				throw new Exception("No Such Person in Database");}
			
			Kid k = (Kid) data.getPersons().get(KidID);
			int classnumber = k.getcLass().getNumber();
			int kindergardenid = k.getcLass().getKindergarden().getID();
			boolean id ;
			id = operation.insertToDB("DeleteActivityFromKid","'"+KidID+"'",String.valueOf(ActID),String.valueOf(classnumber),String.valueOf(kindergardenid));
			if(id){
				for(TakesPlace tp : data.getActsInClasses()){
					if(tp.equals(new TakesPlace(k.getcLass(),data.getActivities().get(ActID)))){
						tp.getKids().remove(KidID);
						k.getActivities().remove(tp);
					}
				}
				return true;
			}
		}
		return false;
	}
	
	public boolean RemoveKidFromClass(String kID) throws Exception{
		if(kID != null){
			if(!data.getPersons().containsKey(kID))
				throw new Exception("Kid does not exist or already removed");
			
			if(operation.insertToDB("deleteKidFromDB","'"+kID+"'")){
				for(TakesPlace tp : data.getActsInClasses())
					tp.getKids().remove(kID);
			
				Kid k = (Kid)data.getPersons().remove(kID);
				if(k != null)
					k.getcLass().getKids().remove(kID);
		
				return true;
			}
		}
		return false;
	}
	
	public boolean AddTrainingToData(int id, String name){
		if(id > 0 && name != null){
			if(data.getTrainingList().containsKey(id)){
				if(operation.insertToDB("updateTraining", String.valueOf(id), "'"+name+"'")){
					data.getTrainingList().get(id).setName(name);
					return true;
				}
			}
			else if(!data.getTrainingList().containsKey(id)){
				if(operation.insertToDB("addTraining", String.valueOf(id), "'"+name+"'")){
					data.getTrainingList().put(id, new Training(id,name));
					return true;
				}
			}
		}
		return false;
	}
	
	
	public boolean AddTrainingToAssistant(String aID, int tID, String date) throws Exception{
		if(aID != null && tID > 0){
			if(data.getTrainingList().containsKey(tID) && data.getPersons().containsKey(aID)){
				DateFormat df = new SimpleDateFormat(Constants.DF);
				Date dd = df.parse(date);
				Assistant ast = (Assistant) data.getPersons().get(aID);
				Training tr = data.getTrainingList().get(tID);
				TrainingForAssistant tfa = new TrainingForAssistant(ast,tr);
				
				if(data.getAssistantsInTrainings().contains(tfa)){
					if(operation.insertToDB("updateTrainingForAssistant","'"+aID+"'", String.valueOf(tID), "'"+date+"'")){
						int index = data.getAssistantsInTrainings().indexOf(tr);
						tfa = data.getAssistantsInTrainings().get(index);
						tfa.settDate(dd);
						tfa.setAssistant(ast);
						tfa.setTraining(tr);
						return true;
					}
				}
				else if(!data.getAssistantsInTrainings().contains(tfa)){
					if(operation.insertToDB("addTrainingForAssistant","'"+aID+"'", String.valueOf(tID), "'"+date+"'")){
						tfa.settDate(dd);
						return data.getAssistantsInTrainings().add(tfa);
					}
					
				}
			}
		}
		return false;
	}

	public boolean addOpinion(String desc, int rating, int kindergartenId, int activityId) throws Exception{
		if(desc != null && rating >= Constants.MIN_RATING && rating <= Constants.MAX_RATING){
			boolean a = kindergartenId > 0, b = activityId > 0;
			if(a^b){
				int id = 0 ;
				if(kindergartenId > 0){
					
					if(!data.getKindergartens().containsKey(kindergartenId))
						throw new Exception("such kindergarten does not exist in database!");
					
					id = operation.insrtToDBWithResult("addOpinion","'"+desc+"'",String.valueOf(rating),String.valueOf(kindergartenId),"null");
					if(id > 0){
						Opinion op = data.getOpinions().put(id, new Opinion(id, desc, rating, data.getKindergartens().get(kindergartenId)));
						data.getKindergartens().get(kindergartenId).getOpinions().put(id, op);
						return true;
					}
				}
				else if(activityId > 0){
					
					if(!data.getActivities().containsKey(activityId))
						throw new Exception("such activity does not exist in database!");
					
					id = operation.insrtToDBWithResult("addOpinion","'"+desc+"'",String.valueOf(rating),"null",String.valueOf(activityId));
					if(id > 0){
						Opinion op = data.getOpinions().put(id, new Opinion(id, desc, rating, data.getActivities().get(activityId)));
						data.getActivities().get(activityId).getOpinions().put(id, op);
						return true;
				 	}
					
				}
			}
		}
		return false;
	}
	
	public boolean removeOpinion(int id, int activityId, int KindergartenId) throws Exception{
		if(id > 0){
			if(!data.getOpinions().containsKey(id))
				throw new Exception("Opinion does not exist or already removed");
			
			if(operation.insertToDB("removeOpinion",String.valueOf(id))){
				if(activityId > 0){
				
					if(data.getActivities().get(activityId).getOpinions().containsKey(id)){
						data.getActivities().get(activityId).getOpinions().remove(id);
					}
				}else if(KindergartenId > 0){
					if(data.getKindergartens().get(KindergartenId).getOpinions().containsKey(id)){
						data.getKindergartens().get(KindergartenId).getOpinions().remove(id);
					}
				}
				data.getOpinions().remove(id);
				return true;
			}
		}
		return false;
	}
	
	public boolean addActivityToClass (int kID, int cID, int aID, int day, String time, String opID , boolean b) throws Exception{ 
		if(aID > 0 && kID > 0 && cID > 0 && day > 0 && day < 7 && time != null && opID != null){
			String EXEC = "addActivityToClass";
			if(!b)
				EXEC = "updateAtivityInClass";
			
			time +=":00";
			
			if(!data.getPersons().containsKey(opID))
				throw new Exception("Selected Operator does not exist in database");
			
			if(!data.getKindergartens().containsKey(kID))
				throw new Exception("Kindergarten does not exist");
			
			if(!data.getKindergartens().get(kID).getClasses().containsKey(cID))
				throw new Exception("Class does not exist");
			
			if(!data.getActivities().containsKey(aID))
				throw new Exception("Activity does not eeist");
			
			Activity a = data.getActivities().get(aID);
			Class c = data.getKindergartens().get(kID).getClasses().get(cID);
			Operator op = (Operator)data.getPersons().get(opID);
			Time t = Time.valueOf(time);
			
			if(!data.getActsInClasses().contains(new TakesPlace(c,a)) || (!b) ){
				if(operation.insertToDB(EXEC, String.valueOf(kID), String.valueOf(cID), 
										String.valueOf(aID), String.valueOf(day), "'"+opID+"'", "'"+time+"'")){
					TakesPlace tp ;
					if(!b){
						int index = data.getActsInClasses().indexOf(new TakesPlace(c,a));
						if(index >= 0){
							tp = data.getActsInClasses().get(index);
							tp.setDayInWeek(day);
							tp.setStartTime(t);
							tp.setOperator((Operator)data.getPersons().get(opID));
							return true;
						}
					}
					else{
						 tp = new TakesPlace(c,a,op,day,t);
						 return data.getActsInClasses().add(tp);
					}
				}
			}else
				throw new Exception("Activity already got added to class for this week");
			
		}
		return false;
	}
	
	public boolean removeActivityFromClass(int kID, int cID, int aID){ 
		if(aID > 0 && kID > 0 && cID > 0){
			boolean good1 = true, good2 = true, good3 = true;
			
			if(data.getKindergartens().containsKey(kID)){
				if(data.getKindergartens().get(kID).getClasses().containsKey(cID)){
					Class c = data.getKindergartens().get(kID).getClasses().get(cID);
						if(operation.insertToDB("removeActivityFromClass",String.valueOf(aID))){
							Activity a = new Activity(aID);
							TakesPlace tmp = new TakesPlace(c,a);
							for(Kid k : c.getKids().values()){
								good1 = k.getActivities().remove(tmp);	
							}
							for(Person p : data.getPersons().values())
								if(p instanceof Kid){
									good2 = ((Kid)p).getActivities().remove(tmp);
								}
							good3 = data.getActsInClasses().remove(tmp);
							return good1 && good2 && good3;
					}
				}	
			}
		}
		return false;
	}
	
	public ArrayList<TakesPlace> getActivitiesInClassInDay(int kID, int cID, int d) {
		ArrayList<TakesPlace> tps = new ArrayList<>();

		if (kID > 0 && cID > 0 && d > 0 && d < 7) {
			Class c = data.getKindergartens().get(kID).getClasses().get(cID);
			for (TakesPlace tp : data.getActsInClasses()) {
				if (tp.getcLasss().equals(c) && tp.getDayInWeek() == d) {
					tps.add(tp);
				}
				
			}
		}
		return tps;
	}
	
	public Person getKidById(String txtID) {
		if(txtID != null)
			return data.getPersons().get(txtID);
		return null;
	}
	
	public HashMap<Activity, Double> getPctKidsInActivities(int kID){
		if(kID > 0)
			return Statistics.KidsPctInActivities(kID);
		
		return null;
	}
	
	public HashMap<String, Double> getPctKidsInActsPct(int kID, int cID) {
		if(kID > 0 && cID > 0)
			return Statistics.KidsPctInActivitiesInClasse( kID,  cID);
		return null;
	}
	
	public ArrayList<TakesPlace> getTakesPlaceForClass(int kID, int cID, int day) throws SQLException, Exception{ 
		if(kID > 0 && cID > 0 && day > 0 && day < 7){
			return DBqueries.getInstance().getTakesPlaceForClass(kID, cID, day);
		}
		return null;
	}
	
	public HashMap<Kindergarden, Double> getPrivKindsWithRating() throws SQLException, Exception {
		return DBqueries.getInstance().privateKindergarten();
	}
	
	public ArrayList<Kid> getAllKids() throws Exception{ 
		return DBqueries.getInstance().getAllKids();
	}
	
	public ArrayList<Kid> getAllKidsInClass(int kID, int cID) throws Exception{ 
		if(kID > 0 && cID > 0)
			return DBqueries.getInstance().getAllKidsInClass(kID, cID);
		return null;
	}
	
	public ArrayList<TakesPlace> getSignedFor(String kidID) throws Exception{ 
		if(kidID != null){
			if(data.getPersons().containsKey(kidID)){
				Kid k = (Kid)data.getPersons().get(kidID);
				return DBqueries.getInstance().getSignedFor(kidID, k.getcLass().getKindergarden().getID(), k.getcLass().getNumber());
			}
		}
		return null;
	}
	
	public HashMap<Opinion,Object> getOpinionsForActsOrKins(int kID, int aID) throws Exception{
		if(kID > 0)
			return DBqueries.getInstance().getOpinionsForActsOrKins(kID, -1);
		else if(aID > 0)
			return DBqueries.getInstance().getOpinionsForActsOrKins(-1, aID);
		
		return null;
	}
	
	
	public ArrayList <TakesPlace> activitiesInClass(int kID, int cID) throws SQLException, Exception{ 		
		if(kID > 0 && cID > 0){
			return DBqueries.getInstance().activitiesInClass(kID, cID);
		}
		return null;
	}
	
	
	public ArrayList<TakesPlace> getActivitesforKid(String KidID,int KindergardenID,int classnumber) throws SQLException, Exception{
		if(KidID != null && KindergardenID > 0 && classnumber > 0){
			return DBqueries.getInstance().getActivitiesforKid(KidID, KindergardenID, classnumber);
		}
		return null;
		
	}
	
	public ArrayList<Activity> getAllActivities(){ 
		return new ArrayList<>(data.getActivities().values());
	}
	
	
	public int getClassNumberForKid(String KidID){ 
		if(KidID != null)
			if(data.getPersons().containsKey(KidID)){
				Kid i = (Kid) data.getPersons().get(KidID);
				return i.getcLass().getNumber();
			}
		return 0;
	}
	
	public int getKinderGardenIDForKid(String KidID){
		if(KidID != null)
			if(data.getPersons().containsKey(KidID)){
				Kid i = (Kid) data.getPersons().get(KidID);
				return i.getcLass().getKindergarden().getID();
			}
		
		return 0;
	}

	public void getKidsIDs(JComboBox comboBox) {
		for(Entry<String, Person> mk : data.getPersons().entrySet()){
			if(mk.getValue() instanceof Kid)
				comboBox.addItem(mk.getKey());
		}
		
	}
	
	public void getOpinsNums(JComboBox getoID, int kinder, int actkinder) {
		getoID.removeAllItems();
		if(kinder > 0){
			for(Integer a : data.getKindergartens().get(kinder).getOpinions().keySet()){
				getoID.addItem(a.intValue());
			}
		}else if(actkinder > 0){
			for(Integer a : data.getActivities().get(actkinder).getOpinions().keySet()){
				getoID.addItem(a.intValue());
			}
		}
	}
	
	public void getKinNums(JComboBox jComboBox) {
		for(Integer a : data.getKindergartens().keySet()){
			jComboBox.addItem(a.intValue());
		}
	}
	
	public void getActnNums(JComboBox jComboBox) {
		for(Integer a : data.getActivities().keySet()){
			jComboBox.addItem(a.intValue());
		}
	}
	
	public void getClassNums(JComboBox jComboBox, int kinder) {
		jComboBox.removeAllItems();
		for(Integer a : data.getKindergartens().get(kinder).getClasses().keySet()){
			jComboBox.addItem(a.intValue());
		}
	}
	
	public Opinion getOpinionById(int oID) {
		if(oID > 0 ){
			return data.getOpinions().get(oID);
		}
		return null;
	}
	
	public void getOperatorsIDs(JComboBox opID) { 
		for(Entry<String, Person> mk : data.getPersons().entrySet()){
			if(mk.getValue() instanceof Operator)
				opID.addItem(mk.getKey());
		}
		
	}

	public void getDaysOfWeek(JComboBox dow) { 
		String day;
		for(int i = 1; i <= 6; i++){
			switch(i){
        	
    		case 1: day = "SUNDAY";
    			break;
    		case 2: day = "MONDAY";
    			break;
    		case 3: day = "TUESDAY";
    			break;
    		case 4: day = "WEDNESDAY";
    			break;
    		case 5: day = "THURSDAY";
    			break;
    		case 6: day = "FRIDAY";
    			break;
    		default :
    			day = "Not Available";
    			break;
			}
			dow.addItem(day);
		}
		
	}
	
	public int getDay(String day){
		switch(day){
    	
		case "SUNDAY": return 1;
			
		case "MONDAY": return 2;
			
		case "TUESDAY": return 3;
			
		case "WEDNESDAY": return 4;
			
		case "THURSDAY": return 5;
			
		case "FRIDAY": return 6;
			
		default :
			return -1;
		}
	}

	private double getDistance(float lat1, float lat2, float long1, float long2){
		
		double angle, dlat, dlon, c, dist;
		final int R = 6371;
		dlat = Math.toRadians(lat2 - lat1);
		dlon = Math.toRadians(long2 - long1);
		
		angle  = Math.sin(dlat / 2)*Math.sin(dlat / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(dlon / 2)*Math.sin(dlon / 2);
		c = 2 * Math.atan2(Math.sqrt(angle), Math.sqrt(1 - angle));
		dist = R * c * 1000;
		
		return dist;
	}
	
	/* calculatingAge in an old method */
	private int getAge(Date dateOfBirth) {
		
		int years = 0, months = 0, days = 0;
		Calendar bdate = Calendar.getInstance();
		Calendar now = Calendar.getInstance();
		long currentDate = System.currentTimeMillis();
		
		now.setTimeInMillis(currentDate);
		bdate.setTimeInMillis(dateOfBirth.getTime());
		
		years = now.get(Calendar.YEAR) - bdate.get(Calendar.YEAR);
	    int currMonth = now.get(Calendar.MONTH) + 1;
	    int birthMonth = bdate.get(Calendar.MONTH) + 1;
	      
	    months = currMonth - birthMonth;

	    if (months < 0){
	       years--;
	       months = 12 - birthMonth + currMonth;
	       if (now.get(Calendar.DATE) < bdate.get(Calendar.DATE))
	          months--;
	         
	    }else if(months == 0 && now.get(Calendar.DATE) < bdate.get(Calendar.DATE)){
	       years--;
	       months = 11;
	    }

	    if(now.get(Calendar.DATE) > bdate.get(Calendar.DATE))
	       days = now.get(Calendar.DATE) - bdate.get(Calendar.DATE);
	      
	    else if (now.get(Calendar.DATE) < bdate.get(Calendar.DATE)){
	       int today = now.get(Calendar.DAY_OF_MONTH);
	       now.add(Calendar.MONTH, -1);
	       days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - bdate.get(Calendar.DAY_OF_MONTH) + today;
	         
	    }else{
	       days = 0;
	       if(months == 12){
	          years++;
	          months = 0;
	       }
	    }
				
		return years;
	}





	

}
