<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.sql.*" %>
<%@page import="javax.sql.*" %>
<%@page import="javax.naming.*" %>
<html>
<head>
    <title>DataBase Connection Pool</title>
</head>
<body>
<%
    // 연결을 위한 Connection 객체
    Connection con = null;
    // 통신하기 위한 PreparedStatement 객체
    PreparedStatement pstmt = null;
    // select 결과 값을 담기 위한 ResultSet 객체
    ResultSet rs = null;

    // 실제로 DB에 접근하는 부분
    try
    {
        // 연결하려는 DB이름 (context.xml에서 해당 이름과 일치하는 DB 가져옴)
        // String dbName = "Server-Mysql";
        String dbName = "jdbc/modern_tier";

        // Connection Pool을 찾는 과정
        Context init = new InitialContext();
        // context.xml에서 설정한 Resource Name 부분
        DataSource ds = (DataSource) init.lookup("java:comp/env/" + dbName);
        // DataSource로 부터 Connection을 가져옴
        con = ds.getConnection();

        out.print("DBCP Success");
        System.out.println("DBCP Success");
    }
    catch (Exception ex)
    {
        out.print("DBCP Fail");
        System.out.println("DBCP Fail");
        System.out.println("Exception: " + ex.getMessage());
    }
    finally
    {
        // 자원 해제는 똑같이 해주어야 한다.
        if (rs != null)
            rs.close();
        if (pstmt != null)
            pstmt.close();
        if (con != null)
            con.close();
    }
%>
</body>
</html>