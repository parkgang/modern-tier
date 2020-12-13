package com.api.v1;

import com.dao.UserDAO;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

@Path("/v1/user")
public class User {

    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    // 로그인 확인
    public Response login(@Context HttpServletRequest req) {
        try {
            int kakao_id = (int) req.getSession().getAttribute("kakao_id");

            JSONObject object = new JSONObject();
            object.put("kakao_id", kakao_id);
            return Response.status(Response.Status.OK).entity(object.toString()).build();
        } catch (NullPointerException ex) {
            System.out.println("알려진 예외: 로그인 되어있지 않은 사용자");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @GET
    @Path("/logout")
    // 로그아웃
    public Response logout(@Context HttpServletRequest req) {
        try {
            req.getSession().removeAttribute("kakao_id");
            URI uri = new URI("/react/dist/");
            return Response.seeOther(uri).build();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @GET
    @Path("/unlink")
    // 연결끊기
    public Response unlink(@Context HttpServletRequest req) {
        final String URI = "https://kapi.kakao.com/v1/user/unlink";

        try {

            int kakao_id = (int) req.getSession().getAttribute("kakao_id");

            UserDAO userDAO = UserDAO.getInstance();
            String access_token = userDAO.userWithdrawal(kakao_id);

            URL url = new URL(URI);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            // 요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_token);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
            if (responseCode != 200) {
                System.out.println("에러입니다. 로그를 확인해주세요.");
            }

            req.getSession().removeAttribute("kakao_id");
            URI uri = new URI("/react/dist/");
            return Response.seeOther(uri).build();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
