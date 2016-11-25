package de.mountainsmc.server.plugin.lobby.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.PreparedStatement;
import java.sql.Statement;

import org.bukkit.event.Listener;

public class MySQL implements Listener{
	
	private Connection con = null;
	private String dbHost;
	private String dbPort;
	private String dbName;
	private String dbUser;
	private String dbPass;

	public String STANDART_HOST = "localhost"; //Your ip or address
	public String STANDART_PORT = "3306"; //Your port (default: 3306)
	public String STANDART_NAME_STATS = "stats"; //Your database name
	public String STANDART_NAME_WEBSITE = "website"; //Your second database name, if you have one
	public String STANDART_USER = "root"; //Your username
	public String STANDART_PASS = ""; //Your password
	
	public enum Standart{
		STANDART_STATS,
		STANDART_WEBSITE,
    //You can define your standards here
	}
	
  /**
   * Creates a new custom mysql instance for handling database actions
   * @param host your host
   * @param port your port
   * @param database your database
   * @param username your username
   * @param password your password
   */
	public MySQL(String host, String port, String database, String username, String password){
		this.dbHost = host;
		this.dbPort = port;
		this.dbName = database;
		this.dbUser = username;
		this.dbPass = password;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://"+dbHost+":"+ dbPort+"/"+dbName+"?"+"user="+dbUser+"&"+"password="+dbPass);
		} catch (ClassNotFoundException e) {
			System.out.println("Treiber nicht gefunden");
		} catch (SQLException e) {
			System.out.println("Verbindung nicht moglich");
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
	}
	
  /**
   * Creates a new instance of mysql using {@link Standart Standart} information
   */
	public MySQL(Standart s){
		if(s == Standart.STANDART_STATS){
			this.dbHost = STANDART_HOST;
			this.dbPort = STANDART_PORT;
			this.dbName = STANDART_NAME_STATS;
			this.dbUser = STANDART_USER;
			this.dbPass = STANDART_PASS;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://"+dbHost+":"+ dbPort+"/"+dbName+"?"+"user="+dbUser+"&"+"password="+dbPass);
			} catch (ClassNotFoundException e) {
				System.out.println("Treiber nicht gefunden");
			} catch (SQLException e) {
				System.out.println("Verbindung nicht moglich");
				System.out.println("SQLException: " + e.getMessage());
				System.out.println("SQLState: " + e.getSQLState());
				System.out.println("VendorError: " + e.getErrorCode());
			}
		}else if(s == Standart.STANDART_WEBSITE){
			this.dbHost = STANDART_HOST;
			this.dbPort = STANDART_PORT;
			this.dbName = STANDART_NAME_WEBSITE;
			this.dbUser = STANDART_USER;
			this.dbPass = STANDART_PASS;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://"+dbHost+":"+ dbPort+"/"+dbName+"?"+"user="+dbUser+"&"+"password="+dbPass);
			} catch (ClassNotFoundException e) {
				System.out.println("Treiber nicht gefunden");
			} catch (SQLException e) {
				System.out.println("Verbindung nicht moglich");
				System.out.println("SQLException: " + e.getMessage());
				System.out.println("SQLState: " + e.getSQLState());
				System.out.println("VendorError: " + e.getErrorCode());
			}
		}
	}
	
	public String getHost(){
		return this.dbHost;
	}
	
	public String getPort(){
		return this.dbPort;
	}
	
	public String getDatabase(){
		return this.dbName;
	}
	
	public String getUsername(){
		return this.dbUser;
	}
	
	public String getPassword(){
		return this.dbPass;
	}
	
	public void setHost(String host){
		this.dbHost = host;
	}
	
	public void setPort(String port){
		this.dbPort = port;
	}
	
	public void setDatabase(String database){
		this.dbName = database;
	}
	
	public void setUsername(String username){
		this.dbUser = username;
	}
	
	public void setPassword(String password){
		this.dbPass = password;
	}
	
	public ResultSet returnQuery(String sql){
		Statement query;
		ResultSet result = null;
		try {
			query = con.createStatement();
			result = query.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("Verbindung nicht moglich");
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
		
		return result;
	}
	
	public void updateQuery(String sql){
		Statement query;
		try {
			query = con.createStatement();
			query.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Verbindung nicht moglich");
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
	}
	
	public void insertQuery(String sql){
		Statement query;
		try {
			query = con.createStatement();
			query.execute(sql);
		} catch (SQLException e) {
			System.out.println("Verbindung nicht moglich");
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
	}
}
