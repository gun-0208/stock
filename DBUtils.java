package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/";
	
	//	사용하고자 하는 스키마(DATABASE)이름
	private static final String DATABASE_NAME = "testdb";
	private static final String USER = "root";
	private static final String PASSWORD = "1234";
	
	
	//			Java와 MySQL 데이터베이스를 연결하는 메서드
	public static Connection getConnection() throws SQLException {
		
		Connection connection = DriverManager.getConnection(DB_URL + DATABASE_NAME,USER,PASSWORD);
		
		return connection;
	}
	public static void dropAndCreateTable() {
		try (Connection connection = getConnection();
			Statement statement = connection.createStatement()){
			
//			테이블 제거 SQL, 만약 todo 테이블이 존재 할 경우, 삭제.
//			final String dropTableQueryIfExists = "DROP TABLE IF EXISTS stock";
//			
////			Query 실행 진행시켜.
//			statement.execute(dropTableQueryIfExists);
			
			final String createTableQuery =  
					"CREATE TABLE IF NOT EXISTS stock (" +
		            "id VARCHAR(10) NOT NULL," +
		            "date DATE," +
		            "close INT(10)," +
		            "diff INT(10)," +
		            "open INT(10),"
		            + "high INT(10),"
		            + "low INT(10),"
		            + "volume INT(20),"
		            + "PRIMARY KEY (id,date))";
			
			statement.execute(createTableQuery);
			
			System.out.println("Table has created");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
