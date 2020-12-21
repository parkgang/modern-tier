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
            System.out.println("DB연결 실패: " + ex);
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
            throw new Exception("friendInsert 에러: ", ex);
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
