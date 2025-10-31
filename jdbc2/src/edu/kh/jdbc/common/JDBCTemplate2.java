package edu.kh.jdbc.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/* JDBCTemlate :
 * JDBC 관련 작업을 위한 코드를 
 * 미리 작성해서 제공하는 클래스
 * 
 * - Connection 생성
 * - AutoCommit false
 * - commit / rollback
 * - 각종 자원 반환 close()
 * 
 * ***** 중요 *********
 * 어디서든지 JDBCTemplate 클래스를
 * 객체로 만들지 않고도 메서드를 사용 할 수 있도록
 * 모든 메서드를 public static 으로 선언
 * 
 * */
public class JDBCTemplate2 {
	
	
	// 필드
	private static Connection conn = null;
	
	// 메서드 
	
	/** 호출 시 Connection 객체를 생성하여 호출한 곳으로 반환하는 메서드
	 * + AutoCommit 끄기
	 * 
	 * @return conn
	 */
	public static Connection getConnection() {
		
		
		try {
			
			// 이전에 Connection 객체가 만들어졌고(존재하고)
			// 아직 close() 된 상태가 아니라면
			// 새로 만들지 않고, 기존 Connection 반환
			if( conn != null && !conn.isClosed() ) return conn;
			
			// 1. Properties 객체 생성
			Properties prop = new Properties();
			
			// 2. Properties가 제공하는 메서드를 이용해서 driver.xml 파일 내용을 읽어오기
			prop.loadFromXML(new FileInputStream("driver.xml"));
			
			// 3. prop에 저장된 값을 이용해서 Connection 객체 생성
			Class.forName(prop.getProperty("driver"));
			// Class.forName("oracle.jdbc.driver.OracleDriver")
			
			conn = DriverManager.getConnection( prop.getProperty("url"), // "jdbc:oracle:thin:@localhost:1521:XE"
												prop.getProperty("userName"), // "kh_ccl"
												prop.getProperty("password") ); // "oracle"
			
			// 4. 만들어진 Connection 에서 AutoCommit 끄기
			conn.setAutoCommit(false);
			
			
			
		} catch (Exception e) {
			System.out.println("커넥션 생성 중 예외 발생(JDBCTemplate의 getConnection())");
			e.printStackTrace();
		}
		return conn;
		
	}
	
	
	
	
	
	
	
	
}














public class JDBCTemplate2 {

	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
	
	}

}
