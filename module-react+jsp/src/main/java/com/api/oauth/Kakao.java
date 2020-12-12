package com.api.oauth;

import com.constant.KakaoApp;
import com.constant.Service;
import com.dao.UserDAO;
import com.dto.UserBean;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;

@Path("oauth/kakao")
public class Kakao {

    private final String OAuth_Redirect_URI = "http://" + Service.LOCAL_IP + "/api/oauth/kakao";

    private UserBean userBean;

    // 인가 코드를 받은 뒤, 인가 코드로 액세스 토큰과 리프레시 토큰을 발급 받는 메소드
    public String getAccessToken(String authorize_code) {
        final String Kakao_Token_Req_URI = "https://kauth.kakao.com/oauth/token";

        try {
            String access_token;
            String refresh_token;

            URL url = new URL(Kakao_Token_Req_URI);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=" + "authorization_code");
            sb.append("&client_id=" + KakaoApp.REST_API_KEY);
            sb.append("&redirect_uri=" + OAuth_Redirect_URI);
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();

            // 결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode: " + responseCode);
            if (responseCode != 200) {
                System.out.println("에러입니다. 로그를 확인해주세요.");
            }

            // 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body: " + result);

            // Response JSON 파싱
            JSONObject jObject = new JSONObject(result);
            access_token = jObject.getString("access_token");
            refresh_token = jObject.getString("refresh_token");
            System.out.println("access_token: " + access_token);
            System.out.println("refresh_token: " + refresh_token);

            userBean.setKakao_access_token(access_token);
            userBean.setKakao_refresh_token(refresh_token);

            br.close();
            bw.close();

            return access_token;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // 로그인한 사용자의 정보를 불러옵니다
    public HashMap<String, Object> getUserInfo(String access_token) {
        // 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
        HashMap<String, Object> userInfo = new HashMap<>();
        final String Kakao_UserInfo_Req_URI = "https://kapi.kakao.com/v2/user/me";

        try {
            URL url = new URL(Kakao_UserInfo_Req_URI);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            // 요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_token);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
            if (responseCode != 200) {
                System.out.println("에러입니다. 로그를 확인해주세요.");
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            // Response JSON 파싱
            JSONObject jObject = new JSONObject(result);
            int id = jObject.getInt("id");

            JSONObject propertiesObject = jObject.getJSONObject("properties");
            JSONObject kakao_accountObject = jObject.getJSONObject("kakao_account");

            String nickname = propertiesObject.getString("nickname");
            String email = kakao_accountObject.getString("email");

            System.out.println("id: " + id);
            System.out.println("nickname: " + nickname);
            System.out.println("email: " + email);

            userBean.setKakao_id(id);
            userBean.setKakao_nickname(nickname);
            userBean.setKakao_email(email);

            userInfo.put("id", id);
            userInfo.put("nickname", nickname);
            userInfo.put("email", email);
            System.out.println("login Controller : " + userInfo);

            return userInfo;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @GET
    public Response login(@QueryParam("code") String code) {
        try {
            userBean = new UserBean();
            // 인가 코드 확인
            System.out.println("code: " + code);

            String access_token = getAccessToken(code);

            HashMap<String, Object> userInfo = getUserInfo(access_token);

            System.out.println("UserBean 출력");
            System.out.println(userBean.getKakao_id());
            System.out.println(userBean.getKakao_nickname());
            System.out.println(userBean.getKakao_email());
            System.out.println(userBean.getKakao_access_token());
            System.out.println(userBean.getKakao_refresh_token());

            UserDAO userDAO = UserDAO.getInstance();
            userDAO.userInsert(userBean);

            // react page forward test
            // request.getRequestDispatcher("/react/dist/").forward(request, response);
            // response.sendRedirect("/react/dist/");
            // URI uri = new URI("https://www.naver.com");
            URI uri = new URI("/react/dist/");
            return Response.seeOther(uri).build();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
