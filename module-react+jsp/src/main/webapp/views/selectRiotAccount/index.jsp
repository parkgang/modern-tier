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
    <link rel="stylesheet" href="./index.css"/>
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
