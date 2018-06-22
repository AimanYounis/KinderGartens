package Connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBconn {
	
	private static final String Trainings = null;
	private DriverManager dm;
	private static Connection conn;
	private static Statement s;
	private static DBconn instance;
	
	private DBconn() throws Exception{
		initConn();
		}
	
	public static DBconn getInstance() throws Exception{
		if(instance == null){
			instance = new DBconn();
			 
			return instance;
		}else{
			return instance;
		}
	}
	
	private void initConn() throws Exception{
			
		DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			
		final String serverName = "localhost";
		final String dbName = "KindergardenSolution";
		final String userName = "sa";
		final String pass = "sa1234";
		final String port = "1433";
		final String url = "jdbc:sqlserver://"+serverName+":"+port+";databasename="+dbName+";integratedSecurity=false";
			
		conn = DriverManager.getConnection(url,userName,pass);
		s = conn.createStatement();
			
			
			
	}
	
	public Connection getConnection(){
        return conn;
    }
	
	
	
}
