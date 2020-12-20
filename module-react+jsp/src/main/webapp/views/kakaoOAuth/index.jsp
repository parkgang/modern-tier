<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.constant.KakaoApp" %>
<%@ page import="com.constant.Service" %>
<%
    final String KAKAO_OAUTH_URI = "https://kauth.kakao.com/oauth/authorize?client_id=" + KakaoApp.REST_API_KEY + "&redirect_uri=" + Service.USE_DOMAIN + "/api/oauth/kakao&response_type=code&scope=profile,account_email,friends,talk_message";
%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="index.css"/>
    <title>로그인</title>
</head>
<body>
<div id="root">
    <div>
        <span>안녕하세요</span>
    </div>
    <div>
        <div>
            <div>
                <span>modern-tier는 실제 LOL 전적 데이터를 기반하여</span>
            </div>
            <div>
                <span>등록된 친구들의 랭킹을 한눈에 보여주는 서비스 입니다.</span>
            </div>
        </div>
        <div>
            <span>실제 랭킹이 변경되면 해당 내용이 kakao talk으로 전송됩니다.</span>
        </div>
        <div>
            <span>아직 가입이 되어있지 않으시면 아래 버튼을 통해 가입을 진행해주세요.</span>
        </div>
    </div>
    <div>
        <a href="<%=KAKAO_OAUTH_URI%>">
            <img src="kakao_login_medium.png">
        </a>
    </div>
</div>
</body>
</html>