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
	private static final String AUTORECONNET = "?autoReconnect=true";
	private static Connection dbConn;
	
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
		            + "volume INT(20))";
			
			statement.execute(createTableQuery);
			
			System.out.println("Table has created");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Connection SignUserConnection() { // get : 이미 올라가 있으니 가져다 쓰기만 할 때는 보통 get을 사용

		if (dbConn == null) {

			try {

				// Driver를 로딩
				dbConn = DriverManager.getConnection(DB_URL + DATABASE_NAME + AUTORECONNET, USER, PASSWORD);

				// 연결된거를 dbConn에 넣어놓음

			} catch (Exception e) {

				e.printStackTrace();

			}

		}

		return dbConn;

	}


	// DDL, 테이블 생성 메서드
	public static void UserTbldropAndCreateTable() {

		// Java와 MySQL 연결
		// try - with resources 구문 - 자원을 자동으로 해제
		try (Connection connection = getConnection(); 
			Statement statement = connection.createStatement();) {

			// 테이블 제거 SQL, 만약 todo 테이블이 존재할 경우, 삭제. 그렇지 않으면 그냥 지나가~
			final String dropTableQueryIfExists = "DROP TABLE IF EXISTS naverMember";

			// Query 실행 진행시켜
			statement.execute(dropTableQueryIfExists);

			// 테이블 생성 SQL
			final String createTableQuery = 
					"CREATE TABLE naverMember (" + 
					"id varchar(15) NOT NULL,"+
					"pw varchar(15) NOT NULL," + 
					"name varchar(20) NOT NULL," + 
					"gender varchar(10),"+ 
					"birth varchar(10),"+
					"email varchar(25),"+ 
					"tel varchar(13)," + 
					"PRIMARY KEY (id))";

			// Query 실행 진행시켜
			statement.execute(createTableQuery);
			System.out.println("Table has created.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	// DB는 연결을 하면 항상 끊어주는 작업을 해야한다

	public static void close() {

		if (dbConn != null) {

			try {

				if (!dbConn.isClosed()) {

					dbConn.close();

				}

			} catch (Exception e) {

				System.out.println(e.toString());

			}

		}
		// 연결을 끊어주면 그 안에 쓰레기 값이 남아 두번째 연결을 할때는 Adapter에러가 발생, 접속이 안된다
		// ★항상 연결을 끊으면 dbConn을 null로 초기화 해야함

		dbConn = null;

	}

	
}
