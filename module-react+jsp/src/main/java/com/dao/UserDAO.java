package com.dao;

import com.constant.Service;
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

    private UserDAO() {
        try {
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:comp/env/" + Service.DATABASE_NAME);
        } catch (Exception ex) {
            System.out.println("DB연결 실패: " + ex);
        }
    }

    private static class LazyHolder {
        private static final UserDAO INSTANCE = new UserDAO();
    }

    public static UserDAO getInstance() {
        return LazyHolder.INSTANCE;
    }

    // 회원 정보 조회
    public boolean isUser(UserBean user) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();

            String sql = "select * from user where kakao_id = ?;";

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, user.getKakao_id());
            rs = pstmt.executeQuery();

            if (rs.next())
                return true;
            else
                return false;
        } catch (Exception ex) {
            throw new Exception(ex);
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

    // 회원 가입
    public void userInsert(UserBean user) throws Exception {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql;
        int result;
        try {
            con = ds.getConnection();

            // 사용자 존재
            if (isUser(user)) {
                sql = "update user set kakao_nickname=?, kakao_email=?, kakao_access_token=?, kakao_refresh_token=? where kakao_id = ?";

                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, user.getKakao_nickname());
                pstmt.setString(2, user.getKakao_email());
                pstmt.setString(3, user.getKakao_access_token());
                pstmt.setString(4, user.getKakao_refresh_token());
                pstmt.setInt(5, user.getKakao_id());

                result = pstmt.executeUpdate();
            } else {
                sql = "insert into user values (null, ?, ?, ?, ?, ?, null, null, null)";

                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, user.getKakao_id());
                pstmt.setString(2, user.getKakao_nickname());
                pstmt.setString(3, user.getKakao_email());
                pstmt.setString(4, user.getKakao_access_token());
                pstmt.setString(5, user.getKakao_refresh_token());

                result = pstmt.executeUpdate();
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
