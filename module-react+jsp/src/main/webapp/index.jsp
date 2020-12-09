<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="index.css"/>
</head>
<body>
<div id="root">
    <div>
        <span>안녕하세요</span>
    </div>
    <div>
        <pre>
modern-tier는 실제 LOL 전적 데이터를 기반하여
등록된 친구들의 랭킹을 한눈에 보여주는 서비스 입니다.

실제 랭킹이 변경되면 해당 내용이 kakao talk으로 전송됩니다.

아직 가입이 되어있지 않으시면 아래 버튼을 통해 가입을 진행해주세요
        </pre>
    </div>
    <div>
        <button id="kakaoLogin"
                onclick="window.open('https://kauth.kakao.com/oauth/authorize?client_id=3888fb0f3d1eb5652a4f2ec494a1d3a7&redirect_uri=http://localhost:8080/auth/callback&response_type=code&scope=friends,talk_message', 'kakao', 'width=420', 'height=600')">
            kakao login
        </button>
    </div>
</div>
</body>
</html>

<%--    <form action="/api" method="get">--%>
<%--        ID: <input type="text" name="id" value=""><br/>--%>
<%--        PW: <input type="text" name="pw" value=""><br/>--%>
<%--        <input type="submit" value="Submit">--%>
<%--    </form>--%>