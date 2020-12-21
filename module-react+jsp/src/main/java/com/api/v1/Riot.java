package com.api.v1;

import com.constant.RiotApp;
import com.dao.UserDAO;
import com.dto.SummonerDTO;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

@Path("/v1/riot")
public class Riot {

    public void resModal(@Context HttpServletResponse res, String content, String href) {
        try {
            res.setContentType("text/html; charset=UTF-8");
            PrintWriter out = res.getWriter();
            out.println("<script>");
            out.println("alert('" + content + "');");
            out.println("location.href='" + href + "';");
            out.println("</script>");
            out.flush();
        } catch (Exception ex) {
            System.out.println("/v1/riot의 resModal 에러: " + ex);
        }
    }

    @GET
    @Path("/search")
    // riot 계정을 검색합니다
    public void search(@Context HttpServletRequest req, @Context HttpServletResponse res, @QueryParam("summonerName") String summonerName) {
        final String RIOT_SUMMONER_V4_URI = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/";

        // System.out.println("검색 계정 이름: " + summonerName);
        try {
            String urlEncoder_SummonerName = URLEncoder.encode(summonerName, "UTF-8");

            URL url = new URL(RIOT_SUMMONER_V4_URI + urlEncoder_SummonerName);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("X-Riot-Token", RiotApp.REST_API_KEY);

            int responseCode = conn.getResponseCode();
            switch (responseCode) {
                // 소환사 찾음
                case 200:
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line = "", result = "";

                    while ((line = br.readLine()) != null) {
                        result += line;
                    }
                    // System.out.println("response body : " + result);

                    // Response JSON 파싱
                    JSONObject jObject = new JSONObject(result);

                    String id = jObject.getString("id");
                    String name = jObject.getString("name");
                    int profileIconId = jObject.getInt("profileIconId");
                    int summonerLevel = jObject.getInt("summonerLevel");

                    SummonerDTO summonerDTO = new SummonerDTO();

                    summonerDTO.setId(id);
                    summonerDTO.setName(name);
                    summonerDTO.setProfileIconId(profileIconId);
                    summonerDTO.setSummonerLevel(summonerLevel);

                    // 이미 등록된 소환사 여부 체크
                    UserDAO userDAO = UserDAO.getInstance();
                    if (userDAO.isUniqueSummoner(id) == false)
                        resModal(res, "이미 등록된 소환사 입니다.", "/views/searchRiotAccount/");
                    else {
                        // 소환사 선택 페이지로 이동
                        req.setAttribute("summonerDTO", summonerDTO);
                        req.getRequestDispatcher("/views/selectRiotAccount/").forward(req, res);
                    }
                    break;
                // 소환사를 찾을 수 없음
                case 404:
                    resModal(res, "소환사를 찾을 수 없습니다.", "/views/searchRiotAccount/");
                    break;
                default:
                    System.out.println("예상하지 못한 상태 코드 입니다. 로그를 확인해 주세요.");
                    break;
            }
        } catch (Exception ex) {
            System.out.println("/v1/riot/search 에러: " + ex);
        }
    }

    @POST
    @Path("/registerAccount")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    // riot 계정을 등록합니다
    public Response registerAccount(@Context HttpServletRequest req,
                                    @FormParam("id") String id,
                                    @FormParam("name") String name,
                                    @FormParam("profileIconId") int profileIconId,
                                    @FormParam("summonerLevel") int summonerLevel) {

        try {
            int kakao_id = (int) req.getSession().getAttribute("kakao_id");

            SummonerDTO summonerDTO = new SummonerDTO();
            summonerDTO.setId(id);
            summonerDTO.setName(name);
            summonerDTO.setProfileIconId(profileIconId);
            summonerDTO.setSummonerLevel(summonerLevel);

            System.out.println("소환사 등록");
            System.out.println("Kakao_id: " + kakao_id);
            System.out.println("riot_id: " + id);
            System.out.println("riot_name: " + name);
            System.out.println("riot_profileIconId: " + profileIconId);
            System.out.println("riot_summonerLevel: " + summonerLevel);
            System.out.println("");

            // 롤 계정 등록
            UserDAO userDAO = UserDAO.getInstance();
            userDAO.userRiotInsert(kakao_id, summonerDTO);

            // 홈 화면인 react으로 이동
            URI uri = new URI("/react/dist/");
            return Response.seeOther(uri).build();

        } catch (Exception ex) {
            System.out.println("/v1/riot/registerAccount 에러: " + ex);
        }

        return null;
    }
}
