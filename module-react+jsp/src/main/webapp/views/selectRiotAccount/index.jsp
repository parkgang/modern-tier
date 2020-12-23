<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.dto.SummonerDTO" %>
<%
    final String resourcesPath = "/views/selectRiotAccount";

    SummonerDTO summonerDTO = (SummonerDTO) request.getAttribute("summonerDTO");
%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
    <%--REST API를 통해 이동된 페이지 때문인지는 몰라도 상대 경로로 콘텐츠를 로드하지 못하는 이슈가 존재합니다. 때문에 절대 경로로 지정합니다.--%>
    <link rel="stylesheet" href="<%=resourcesPath%>/index.css"/>
    <title>소환사 계정 선택</title>
</head>
<script>
    const nullCheck = () => {
        const f = document.summonerForm;
        if (f.summonerName.value == "") {
            alert("소환사명을 입력해 주세요.");
            f.summonerName.focus();
            return false;
        }
        return true;
    }
</script>
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
        <span>님이 맞으신가요?</span>
    </div>
    <div>
        <form name="summonerForm" onSubmit="return nullCheck();" method="get" action="/api/v1/riot/search">
            <input type="text" name="summonerName" placeholder="소환사명으로 재검색">
        </form>
    </div>
    <div>
        <button type="submit" style="cursor:pointer" onclick="nullCheck() && document.summonerForm.submit()">
            <img src="<%=resourcesPath%>/lol_research_button.png">
        </button>
        <button type="submit" style="cursor:pointer" onclick="document.reqHiddenForm.submit()">
            <img src="<%=resourcesPath%>/lol_select_button.png">
        </button>
        <form name="reqHiddenForm" method="post" action="/api/v1/riot/registerAccount">
            <input type="hidden" name="id" value="<%=summonerDTO.getId()%>">
            <input type="hidden" name="name" value="<%=summonerDTO.getName()%>">
            <input type="hidden" name="profileIconId" value="<%=summonerDTO.getProfileIconId()%>">
            <input type="hidden" name="summonerLevel" value="<%=summonerDTO.getSummonerLevel()%>">
        </form>
    </div>
</div>
</body>
</html>
