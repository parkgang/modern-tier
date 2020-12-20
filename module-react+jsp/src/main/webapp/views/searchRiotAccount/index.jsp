<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="index.css"/>
    <title>소환사 계정 등록</title>
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
        <span>소환사 계정을 등록해주세요</span>
    </div>
    <div>
        <div>
            <span>League of Legends에서 사용하는 자신의 계정을 등록해주세요.</span>
        </div>
        <div>
            <span>해당 계정의 리그정보를 토대로 랭킹이 매겨집니다.</span>
        </div>
    </div>
    <form name="summonerForm" onSubmit="return nullCheck();" method="get" action="/api/v1/riot/search">
        <input type="text" name="summonerName" placeholder="소환사명">
        <div>
            <button type="submit" style="cursor:pointer">
                <img src="lol_search_button.png">
            </button>
        </div>
    </form>
</div>
</body>
</html>
