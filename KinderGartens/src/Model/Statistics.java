package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Statistics {

	private static SysData data = SysData.getInstance();

	public static HashMap<Activity, Double> KidsPctInActivities(int kinID) {
		int sum = 0;
		int nkinc = 0;

		Kindergarden k = data.getKindergartens().get(kinID);
		HashMap<Activity, Integer> acts = new HashMap<>();
		HashMap<Activity, Double> avgActs = new HashMap<>();
		
		if (k != null) {
			for (Class c : k.getClasses().values()) {
				nkinc += c.getKids().size();
			}

			for (Activity act : data.getActivities().values()) {
				acts.put(act, 0);
			}

			for (TakesPlace tp : data.getActsInClasses()) {
				sum = 0;
				if (tp.getcLasss().getKindergarden().equals(k)) {
					sum = acts.get(tp.getActivity()).intValue() + tp.getKids().size();
					acts.put(tp.getActivity(), sum);
				}
			}

			for (Map.Entry<Activity, Integer> map : acts.entrySet()) {
				int val = map.getValue().intValue();
				double d = ((double)val / (double)nkinc);
				
				avgActs.put(map.getKey(), new Double(d));
				
			}
			return avgActs;
		}
		return avgActs;
	}

public static HashMap<String,Integer> numKidsInKins(){ // new
		
		HashMap<String,Integer> pct = new HashMap<>();
		
		pct.put("private", 0);
		pct.put("public", 0);
		
		for(Person kid : data.getPersons().values()){
			if(kid instanceof Kid){
				if(((Kid) kid).getcLass().getKindergarden().isPrivate()){
					int sum = pct.get("private") + 1;
					pct.put("private",sum);
				}
				else{
					int sum = pct.get("public") + 1;
					pct.put("public",sum);
				}
			}
		}
		return pct;
		
	}
	public static HashMap<String,Double>  KidsPctInActivitiesInClasse(int kinID, int cID){ // new
		
		int  numKidsImC = 0, actCounter = 0, kidsNum = 0, kidsInAct = 0;
		double one = 0.25, two = 0.5, three = 0.75;
		
		Kindergarden k = data.getKindergartens().get(kinID);
		HashMap<Activity, Integer> actkids = new HashMap<>();
		HashMap<Kid,Double> kid = new HashMap<>();
		HashMap<String,Double> pct = new HashMap<>();
		
		if (k != null) {
			
			numKidsImC = k.getClasses().size();
			Class c = k.getClasses().get(cID);
			
			pct.put("0-0.25 from activities", (double) 0);
			pct.put("0.25-0.5 from activities", (double) 0);
			pct.put("0.5-0.75 from activities", (double) 0);
			
			for(Kid kd : c.getKids().values()){
				kid.put(kd, (double) 0);
			}
			
			for(TakesPlace tp : data.getActsInClasses()){
				if(tp.getcLasss().equals(c)){			
					actCounter++;
				}
			}
			
			for(String kd : c.getKids().keySet()){
				for(TakesPlace tp : data.getActsInClasses()){
					if(tp.getcLasss().equals(c)){
						if(tp.getKids().containsKey(kd)){
							
							double d = ((double)1/(double)actCounter) + kid.get(tp.getKids().get(kd));
							kid.put(tp.getKids().get(kd), d);
						}
					}
				}
			}
			
			for(Map.Entry<Kid, Double> map : kid.entrySet()){
				
				if(map.getValue() < one){
					double d = ((double)1/(double)numKidsImC) + pct.get("0-0.25 from activities");
					pct.put("0-0.25 from activities", d );
				}
				if(map.getValue() < two && map.getValue() > one){
					double d = ((double)1/(double)numKidsImC) + pct.get("0-0.25 from activities");
					pct.put("0.25-0.5 from activities", d );
				}
				if(map.getValue() > two){
					double d = ((double)1/(double)numKidsImC) + pct.get("0-0.25 from activities");
					pct.put("0.5-0.75 from activities", d );
				}
			}
			return pct;
		}
		return pct;
	}
	
}
