<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <style>
        #root {
            text-align: center;
            background-color: #00D8FF;
            border: 1px solid #5D5D5D;
            position: absolute;
            height: 300px;
            width: 400px;
            margin: -150px 0px 0px -200px;
            top: 50%;
            left: 50%;
            padding: 5px;
        }
    </style>
</head>
<body>
<div id="root">
    <span style="font-size: 24px; margin: 10px 10px 10px 10px">Hello, world! 현재 페이지는 jsp입니다!</span>
    <form action="/api" method="get">
        ID: <input type="text" name="id" value=""><br/>
        PW: <input type="text" name="pw" value=""><br/>
        <input type="submit" value="Submit">
    </form>
</div>
</body>
</html>
