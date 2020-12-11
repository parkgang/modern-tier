package com.api.v1;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/v1/user")
public class User {
    @GET
    @Path("/login")
    public void login() {
        // 콜백된 access_token을 기반으로 실제 DB에 넣는 작업
        // 이미 데이터 있다면 소환사 등록 여부에 따라 다른 페이지로 이동
        // 인가코드 받고 -> 사용자 조회하고 -> db에 저장
        // ㄴㄴ 이거는 세션 검증 용도로 사용하기
        System.out.println("user");
    }
}
