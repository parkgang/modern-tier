package com.api.v1;

import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @GET
    @Path("/logout")
    // 로그아웃
    public void logout(@Context HttpServletRequest req) {
        try {
            req.getSession().removeAttribute("kakao_id");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // 연결끊기
}
