package com.api.v1;

import com.constant.RiotApp;
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
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Path("/v1/riot")
public class Riot {

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

                    // 소환사 선택 페이지로 이동
                    req.setAttribute("summonerDTO", summonerDTO);
                    req.getRequestDispatcher("/views/selectRiotAccount/").forward(req, res);
                    break;
                // 소환사를 찾을 수 없음
                case 404:
                    res.setContentType("text/html; charset=UTF-8");
                    PrintWriter out = res.getWriter();
                    out.println("<script>alert('소환사를 찾을 수 없습니다'); location.href='/views/searchRiotAccount/';</script>");
                    out.flush();
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
                                    @FormParam("profileIconId") String profileIconId,
                                    @FormParam("summonerLevel") String summonerLevel) {

        try {
            System.out.println("apu run");
            System.out.println("id: " + id);
            System.out.println("name: " + name);
            System.out.println("profileIconId: " + profileIconId);
            System.out.println("summonerLevel: " + summonerLevel);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
