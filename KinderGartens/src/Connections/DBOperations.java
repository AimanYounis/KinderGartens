package Connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBOperations {
	
	private DBconn db;
	private Connection conn;
	private static DBOperations instance;
	
	private DBOperations() throws Exception{
		db = DBconn.getInstance();
	    conn = db.getConnection();
	}
	
	public static DBOperations getInstance() throws Exception{
		if(instance == null){
			instance = new DBOperations();
			 
			return instance;
		}else{
			return instance;
		}
	}
	
	public boolean insertToDB(String...str){
		PreparedStatement cs;
		ResultSet RS;
		try {

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
			int affectedRows = cs.executeUpdate();
			System.out.println("affected rows "+ affectedRows);
			if(affectedRows > 0)
				return true;
			else 
				return false;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public int insrtToDBWithResult(String...str){
		PreparedStatement cs;
		ResultSet RS;
		try {

			String query = "exec ";
			
			for(int i = 0; i < str.length; i++){
				if(str[i].isEmpty())
					str[i] = "null";
				if(i == str.length-1 || i == 0)
					query += str[i] + " ";
				else
					query += str[i] + ", ";
			}

			cs = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			cs.setEscapeProcessing(true);
			cs.setQueryTimeout(90);
			int affectedRows = cs.executeUpdate();
			int rid = -1;
			if(affectedRows > 0){
				RS = cs.getGeneratedKeys();
				if(RS.next())
					rid = RS.getInt(1);
				
				return rid;
			}
			else 
				return -1;
		} catch (SQLException e) {
			System.out.println(e.toString());
			return -1;
		}
	}
}
