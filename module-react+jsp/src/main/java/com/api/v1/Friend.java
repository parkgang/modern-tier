package com.api.v1;

import com.dao.FriendDAO;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/v1/friend")
public class Friend {

    @GET
    @Path("/add")
    // 친구를 추가합니다
    public Response add(@Context HttpServletRequest req, @QueryParam("friendKakaoId") int friendKakaoId) {
        try {
            int kakaoId = (int) req.getSession().getAttribute("kakao_id");

            FriendDAO friendDAO = FriendDAO.getInstance();
            friendDAO.friendInsert(kakaoId, friendKakaoId);

            return Response.status(Response.Status.OK).build();
        } catch (Exception ex) {
            System.out.println("/v1/friend/add 에러: " + ex);
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Path("/del")
    // 친구를 삭제합니다
    public Response del(@Context HttpServletRequest req, @QueryParam("friendKakaoId") int friendKakaoId) {
        try {
            int kakaoId = (int) req.getSession().getAttribute("kakao_id");

            FriendDAO friendDAO = FriendDAO.getInstance();
            friendDAO.friendDelete(kakaoId, friendKakaoId);

            return Response.status(Response.Status.OK).build();
        } catch (Exception ex) {
            System.out.println("/v1/friend/del 에러: " + ex);
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
