package com.api.v1;

import com.dao.FriendDAO;
import com.dao.UserDAO;
import com.dto.UserDTO;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.List;

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
            System.out.println("알려진 예외: 세션 값이 없습니다. (로그인 되어있지 않은 사용자 접속)");
        } catch (Exception ex) {
            System.out.println("/v1/user/login 에러: " + ex);
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
            System.out.println("/v1/user/logout 에러: " + ex);
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
            if (responseCode != 200) {
                System.out.println("에러입니다. 로그를 확인해주세요.");
            }

            req.getSession().removeAttribute("kakao_id");
            URI uri = new URI("/react/dist/");
            return Response.seeOther(uri).build();
        } catch (Exception ex) {
            System.out.println("/v1/user/unlink 에러: " + ex);
        }
        return null;
    }

    @GET
    @Path("/profile")
    @Produces(MediaType.APPLICATION_JSON)
    // 프로필 조회
    public Response profile(@Context HttpServletRequest req, @QueryParam("kakaoId") String kakaoId) {
        final String Kakao_UserInfo_Req_URI = "https://kapi.kakao.com/v2/user/me";

        try {
            // 토큰값 가져오기
            UserDAO userDAO = UserDAO.getInstance();

            String accessToken = userDAO.userKakaoAccessToken(Integer.parseInt(kakaoId));

            // 토큰값으로 프로필 조회
            URL url = new URL(Kakao_UserInfo_Req_URI);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                System.out.println("에러입니다. 로그를 확인해주세요.");
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            // Response JSON 파싱
            JSONObject jObject = new JSONObject(result);
            JSONObject profileObject = jObject.getJSONObject("kakao_account").getJSONObject("profile");

            String nickname = profileObject.getString("nickname");

            String profile_image_url = null;
            if (profileObject.isNull("profile_image_url") == false)
                profile_image_url = profileObject.getString("profile_image_url");

            // json 조립후 responce
            JSONObject resJSON = new JSONObject();

            resJSON.put("nickName", nickname);
            resJSON.put("profileImage", profile_image_url);

            return Response.status(Response.Status.OK).entity(resJSON.toString()).build();
        } catch (Exception ex) {
            System.out.println("/v1/user/profile 에러: " + ex);
        }
        return null;
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    // 사용자 검색
    public Response search(@Context HttpServletRequest req, @QueryParam("kakaoNickname") String kakaoNickname) {
        try {
            int kakaoId = (int) req.getSession().getAttribute("kakao_id");

            FriendDAO friendDAO = FriendDAO.getInstance();

            UserDAO userDAO = UserDAO.getInstance();
            List userList = userDAO.userSearch(kakaoNickname);

            JSONArray resJSON = new JSONArray();

            for (int i = 0; i < userList.size(); i++) {
                UserDTO user = (UserDTO) userList.get(i);

                // 로그인 사용자는 친구목록에 포함되지 않습니다.
                if (kakaoId != user.getKakao_id()) {
                    boolean isFriend = friendDAO.isWithFriend(kakaoId, user.getKakao_id());

                    JSONObject jsonObject = new JSONObject();

                    jsonObject.put("kakaoId", user.getKakao_id());
                    jsonObject.put("nickname", user.getKakao_nickname());
                    jsonObject.put("profileImage", user.getKakao_profile_image_url());
                    jsonObject.put("isFriend", isFriend);

                    resJSON.put(jsonObject);
                }
            }

            return Response.status(Response.Status.OK).entity(resJSON.toString()).build();
        } catch (Exception ex) {
            System.out.println("/v1/user/search 에러: " + ex);
        }
        return null;
    }
}
