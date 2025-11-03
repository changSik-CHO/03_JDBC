package edu.kh.jdbc.model.dao;

import static edu.kh.jdbc.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.kh.jdbc.model.dto.Student;

public class StudentDAO {

    // JDBC 객체(필드로 선언하면 finally에서 닫기 편함)
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    /** 1. 학생 등록 */
    public int insertStudent(Connection conn, Student std) throws Exception {

        int result = 0;

        try {
            String sql =
                "INSERT INTO KH_STUDENT "
              + "(STD_NO, STD_NAME, STD_AGE, MAJOR, ENT_DATE, GENDER, ADDRESS, PHONE, GRADE) "
              + "VALUES(SEQ_STD_NO.NEXTVAL, ?, ?, ?, DEFAULT, ?, ?, ?, ?)";

            pstmt = conn.prepareStatement(sql);

            // 바인딩 순서 정확히!
         // 바인딩 순서 정확히!
            pstmt.setString(1, std.getStdName()); // 이름
            pstmt.setInt(2,    std.getStdAge());  // 나이
            pstmt.setString(3, std.getMajor());   // 전공
            pstmt.setString(4, std.getGender());  // 성별
            pstmt.setString(5, std.getAddress()); // 주소
            pstmt.setString(6, std.getPhone());   // 전화
            pstmt.setInt(7,    std.getGrade());   // 학년


            result = pstmt.executeUpdate();
        } finally {
            close(pstmt);
        }

        return result;
    }

	    /** 2. 전체 학생 조회 */
	    public List<Student> selectAll(Connection conn) throws Exception {

        List<Student> studentList = new ArrayList<>();

        try {
            String sql =
                "SELECT STD_NO, STD_NAME, STD_AGE, MAJOR, ENT_DATE, "
              + " GENDER, ADDRESS, PHONE, GRADE "
              + "FROM KH_STUDENT "
              + "ORDER BY STD_NO";

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int stdNo = rs.getInt("STD_NO");
                String stdName = rs.getString("STD_NAME");
                int stdAge = rs.getInt("STD_AGE");
                String major = rs.getString("MAJOR");
                java.sql.Date entDate = rs.getDate("ENT_DATE");
                String gender = rs.getString("GENDER");
                String address = rs.getString("ADDRESS");
                String phone = rs.getString("PHONE");
                int grade = rs.getInt("GRADE");


               
            }

        } finally {
            close(rs);
            close(pstmt);
        }

        return studentList;
    }

}
