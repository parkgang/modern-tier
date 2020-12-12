package com.dao;

import com.dto.UserBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

public class UserDAO {

    private DataSource ds;

    public UserDAO() throws Exception {
        try {
            String dbName = "jdbc/modern_tier";
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:comp/env/" + dbName);
        } catch (Exception ex) {
            throw new Exception("DB연결 실패: ", ex);
        }
    }

    // 회원 가입
    public boolean userInsert(UserBean user) throws Exception {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            int result;

            con = ds.getConnection();

            String sql = "insert into user values (null, ?, ?, ?, ?, ?, null, null, null)";

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, user.getKakao_id());
            pstmt.setString(2, user.getKakao_nickname());
            pstmt.setString(3, user.getKakao_email());
            pstmt.setString(4, user.getKakao_access_token());
            pstmt.setString(5, user.getKakao_refresh_token());

            result = pstmt.executeUpdate();

            if (result == 0) {
                return false;
            } else {
                System.out.println("삽입 성공");
                return true;
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new SQLIntegrityConstraintViolationException("중복되는 아이디가 존재합니다: ", ex);
        } catch (Exception ex) {
            throw new Exception("userInsert 에러: ", ex);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (con != null)
                    con.close();
            } catch (Exception ex) {
                throw new Exception("DB종료 실패: ", ex);
            }
        }
    }
}
