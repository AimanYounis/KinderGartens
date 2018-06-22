package Utills;

public class Check {
	
	public static boolean isTime(String input1){ 
		if(!input1.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]"))
			return false;
		return true;
	}
	
	public static boolean isName(String s){
		char c;
		
		if(s.length() > Constants.NAME_LENGTH)
			return false;
		
		if(!s.isEmpty()){
			for(int i = 0; i < s.length(); i++){
				c = s.charAt(i);
				if((c < 'a' || c > 'z') && (c < 'A' || c > 'Z')){
					return false;
				}
			}
		}
		return true;
	}
	public static boolean isID(String s){
		char c;
		
		if(s.length() != Constants.ID_LENGHTH)
			return false;
		if(!s.isEmpty()){
			for(int i = 0; i < s.length(); i++){
				c = s.charAt(i);
				if(c < '0' || c > '9'){
					return false;
				}
			}
		}
		return true; 
	}
	
	public static boolean isName2(String s){
		char c;
		
		if(s.length() > Constants.NAME_LENGTH)
			return false;
		
		
		for(int i = 0; i < s.length(); i++){
			c = s.charAt(i);
			if((c < 'a' || c > 'z') && (c < 'A' || c > 'Z')){
				return false;
			}
		}
		return true;
	}
}
