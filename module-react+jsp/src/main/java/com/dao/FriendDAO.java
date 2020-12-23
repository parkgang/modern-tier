package com.dao;

import com.constant.Service;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FriendDAO {
    private DataSource ds;

    private FriendDAO() {
        try {
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:comp/env/" + Service.DATABASE_NAME);
        } catch (Exception ex) {
            System.out.println("FriendDAO DB연결 실패: " + ex);
        }
    }

    private static class LazyHolder {
        private static final FriendDAO INSTANCE = new FriendDAO();
    }

    public static FriendDAO getInstance() {
        return FriendDAO.LazyHolder.INSTANCE;
    }

    // 친구 추가
    public void friendInsert(int kakaoId, int friendKakaoId) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;

        try {
            con = ds.getConnection();

            sql = "insert into friend (kakao_id, friend_kakao_id) values (?, ?);";

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, kakaoId);
            pstmt.setInt(2, friendKakaoId);

            pstmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("friendInsert 에러: " + ex);
            throw new Exception("friendInsert 에러");
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (con != null)
                    con.close();
            } catch (Exception ex) {
                System.out.println("friendInsert DB종료 실패: " + ex);
                throw new Exception("friendInsert DB종료 실패");
            }
        }
    }

    // 친구 삭제
    public void friendDelete(int kakaoId, int friendKakaoId) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;

        try {
            con = ds.getConnection();

            sql = "delete from friend where kakao_id = ? and friend_kakao_id = ?;";

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, kakaoId);
            pstmt.setInt(2, friendKakaoId);

            pstmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("friendDelete 에러: " + ex);
            throw new Exception("friendDelete 에러");
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (con != null)
                    con.close();
            } catch (Exception ex) {
                System.out.println("friendDelete DB종료 실패: " + ex);
                throw new Exception("friendDelete DB종료 실패");
            }
        }
    }

    // 친구 관계 확인
    public boolean isWithFriend(int kakaoId, int friendKakaoId) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;

        try {
            con = ds.getConnection();

            sql = "select * from friend where kakao_id = ? and friend_kakao_id = ?;";

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, kakaoId);
            pstmt.setInt(2, friendKakaoId);

            rs = pstmt.executeQuery();

            return rs.next();
        } catch (Exception ex) {
            System.out.println("isWithFriend 에러: " + ex);
            throw new Exception("isWithFriend 에러");
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (con != null)
                    con.close();
            } catch (Exception ex) {
                System.out.println("isWithFriend DB종료 실패: " + ex);
                throw new Exception("isWithFriend DB종료 실패");
            }
        }
    }
}
