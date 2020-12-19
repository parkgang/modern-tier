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
import java.util.ArrayList;
import java.util.List;

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

    // 중복되는 사용자를 구별하기 위해 회원 정보 조회
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
            throw new Exception("isUser 에러: ", ex);
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

            // 사용자 존재시 정보 업데이트
            if (isUser(user)) {
                sql = "update user set kakao_nickname=?, kakao_email=?, kakao_profile_image_url=? ,kakao_access_token=?, kakao_refresh_token=? where kakao_id = ?";

                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, user.getKakao_nickname());
                pstmt.setString(2, user.getKakao_email());
                pstmt.setString(3, user.getKakao_profile_image_url());
                pstmt.setString(4, user.getKakao_access_token());
                pstmt.setString(5, user.getKakao_refresh_token());
                pstmt.setInt(6, user.getKakao_id());

                result = pstmt.executeUpdate();
            }
            // 존재하지 않으면 새로 생성
            else {
                sql = "insert into user (kakao_id, kakao_nickname, kakao_email, kakao_profile_image_url, kakao_access_token, kakao_refresh_token) values (?, ?, ?, ?, ?, ?)";

                pstmt = con.prepareStatement(sql);

                pstmt.setInt(1, user.getKakao_id());
                pstmt.setString(2, user.getKakao_nickname());
                pstmt.setString(3, user.getKakao_email());
                pstmt.setString(4, user.getKakao_profile_image_url());
                pstmt.setString(5, user.getKakao_access_token());
                pstmt.setString(6, user.getKakao_refresh_token());

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

    // 서비스 탈퇴
    public String userWithdrawal(int kakao_id) throws Exception {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql;
        String kakao_access_token = "";

        try {
            con = ds.getConnection();

            // 조회
            sql = "select kakao_access_token from user where kakao_id = ?";

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, kakao_id);
            rs = pstmt.executeQuery();

            // 토큰 저장
            if (rs.next()) {
                kakao_access_token = rs.getString("kakao_access_token");
            }

            // 삭제
            sql = "delete from user where kakao_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, kakao_id);
            int result = pstmt.executeUpdate();

            return kakao_access_token;
        } catch (Exception ex) {
            throw new Exception("userWithdrawal 에러: ", ex);
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

    // kakao_access_token 조회
    public String userKakaoAccessToken(int kakao_id) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();

            String sql = "select kakao_access_token from user where kakao_id = ?;";

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, kakao_id);
            rs = pstmt.executeQuery();

            if (rs.next())
                return rs.getString("kakao_access_token");
        } catch (Exception ex) {
            throw new Exception("userKakaoAccessToken 에러: ", ex);
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
        return null;
    }

    // 사용자 조회
    public List userSearch(String kakaoNickname) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List list = new ArrayList();

        try {
            con = ds.getConnection();

            String sql = "select * from user where kakao_nickname like ?";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + kakaoNickname + "%");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                UserBean userBean = new UserBean();

                userBean.setKakao_id(rs.getInt("kakao_id"));
                userBean.setKakao_nickname(rs.getString("kakao_nickname"));
                userBean.setKakao_profile_image_url(rs.getString("kakao_profile_image_url"));

                list.add(userBean);
            }

            return list;
        } catch (Exception ex) {
            throw new Exception("userSearch 에러: ", ex);
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
