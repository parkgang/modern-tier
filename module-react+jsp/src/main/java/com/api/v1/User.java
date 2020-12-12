package com.api.v1;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

@Path("/v1/user")
public class User {

    @GET
    @Path("/login")
    // 로그인 확인
    public String login(@Context HttpServletRequest req) {
        try {
            return req.getSession().getAttribute("kakao_id").toString();
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
