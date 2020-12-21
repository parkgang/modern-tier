package com.api.v1;

import com.constant.RiotApp;
import com.dao.UserDAO;
import com.dto.RankingDTO;
import com.dto.SummonerDTO;
import org.json.JSONArray;
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
import java.util.List;
import java.util.Objects;

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

    @GET
    @Path("/ranking")
    @Produces(MediaType.APPLICATION_JSON)
    // 랭킹 추출
    public Response ranking(@Context HttpServletRequest req) {
        final String RIOT_LEAGUE_V4_URI = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/";

        try {
            int kakaoId = 0;
            try {
                kakaoId = (int) req.getSession().getAttribute("kakao_id");
            } catch (NullPointerException ex) {
                System.out.println("/v1/riot/ranking 에러 (세션 값이 없습니다): " + ex);
            }

            UserDAO userDAO = UserDAO.getInstance();
            List rankingList = userDAO.userFriend(kakaoId);

            // 라이엇에 티어 조회하기
            for (int i = 0; i < rankingList.size(); i++) {
                RankingDTO user = (RankingDTO) rankingList.get(i);

                // 티어조회
                URL url = new URL(RIOT_LEAGUE_V4_URI + user.getRiot_id());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("X-Riot-Token", RiotApp.REST_API_KEY);

                int responseCode = conn.getResponseCode();
                if (responseCode != 200) {
                    System.out.println("예상하지 못한 상태 코드 입니다. 로그를 확인해 주세요.");
                }
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = "", result = "";

                while ((line = br.readLine()) != null) {
                    result += line;
                }
                // System.out.println("response body : " + result);

                // json 파싱
                JSONArray jArray = new JSONArray(result);

                /*
                int exploreIndex = -1;
                for (i = 0; i < jArray.length(); i++) {
                    if (jArray.getJSONObject(i).getString("queueType") == "RANKED_SOLO_5x5") {
                        exploreIndex = i;
                        break;
                    }
                }
                */

                int exploreIndex = 0;
                if (jArray.length() != 1)
                    exploreIndex = 1;

                // 솔로 랭크 한번도 안돌린 사용자
                if (jArray.length() == 0) {

                    rankingList.remove(i);
                } else {
                    JSONObject obj = jArray.getJSONObject(exploreIndex);
                    String tier = obj.getString("tier");
                    String rank = obj.getString("rank");

                    RankingDTO rankingDTO = new RankingDTO();

                    rankingDTO.setKakao_id(user.getKakao_id());
                    rankingDTO.setKakao_nickname(user.getKakao_nickname());
                    rankingDTO.setKakao_profile_image_url(user.getKakao_profile_image_url());
                    rankingDTO.setRiot_id(user.getRiot_id());
                    rankingDTO.setRiot_name(user.getRiot_name());
                    rankingDTO.setRiot_summonerLevel(user.getRiot_summonerLevel());
                    rankingDTO.setTier(tier);
                    rankingDTO.setRank(rank);

                    rankingList.set(i, rankingDTO);
                }
            }

            JSONArray resJSON = new JSONArray();

            String[] tierSortMask = {
                    "CHALLENGER",
                    "GRANDMASTER",
                    "MASTER",
                    "DIAMOND",
                    "PLATINUM",
                    "GOLD",
                    "SILVER",
                    "BRONZE",
                    "IRON",
            };

            String[] rankSortMask = {
                    "I",
                    "II",
                    "III",
                    "IV",
            };

            // 정렬
            for (int tierIndex = 0; tierIndex < tierSortMask.length; tierIndex++) {
                for (int rankIndex = 0; rankIndex < rankSortMask.length; rankIndex++) {
                    for (int i = 0; i < rankingList.size(); i++) {
                        RankingDTO user = (RankingDTO) rankingList.get(i);
                        String tempTier = user.getTier();
                        String tempRank = user.getRank();

                        if (Objects.equals(tempTier, tierSortMask[tierIndex]) && Objects.equals(tempRank, rankSortMask[rankIndex])) {
                            JSONObject jsonObject = new JSONObject();

                            jsonObject.put("kakao_id", user.getKakao_id());
                            jsonObject.put("kakao_nickname", user.getKakao_nickname());
                            jsonObject.put("kakao_profile_image_url", user.getKakao_profile_image_url());
                            jsonObject.put("riot_id", user.getRiot_id());
                            jsonObject.put("riot_name", user.getRiot_name());
                            jsonObject.put("riot_summonerLevel", user.getRiot_summonerLevel());
                            jsonObject.put("tier", user.getTier());
                            jsonObject.put("rank", user.getRank());

                            resJSON.put(jsonObject);
                        }
                    }
                }
            }

            return Response.status(Response.Status.OK).entity(resJSON.toString()).build();

        } catch (Exception ex) {
            System.out.println("/v1/riot/ranking 에러: " + ex);
        }
        return null;
    }
}
