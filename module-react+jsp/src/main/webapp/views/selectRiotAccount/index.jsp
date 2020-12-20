<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.dto.SummonerDTO" %>
<%
    SummonerDTO summonerDTO = (SummonerDTO) request.getAttribute("summonerDTO");
    System.out.println("소환사 검색 결과");
    System.out.println("Id: " + summonerDTO.getId());
    System.out.println("Name: " + summonerDTO.getName());
    System.out.println("ProfileIconId: " + summonerDTO.getProfileIconId());
    System.out.println("SummonerLevel: " + summonerDTO.getSummonerLevel());
    System.out.println("");
%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <%--REST API를 통해 이동된 페이지 때문인지는 몰라도 상대 경로로 콘텐츠를 로드하지 못하는 이슈가 존재합니다. 때문에 절대 경로로 지정합니다.--%>
    <link rel="stylesheet" href="/views/selectRiotAccount/index.css"/>
    <title>소환사 계정 선택</title>
</head>
<body>
<div id="root">
    <div>
        <img src="http://ddragon.leagueoflegends.com/cdn/10.25.1/img/profileicon/<%=summonerDTO.getProfileIconId()%>.png"/>
    </div>
    <div>
        <span><%=summonerDTO.getSummonerLevel()%></span>
    </div>
    <div>
        <span><%=summonerDTO.getName()%></span>
    </div>
    <div>
        <input type="text">
    </div>
    <div>
        <button>다시 검색</button>
        <button>선택</button>
    </div>
</div>
</body>
</html>
