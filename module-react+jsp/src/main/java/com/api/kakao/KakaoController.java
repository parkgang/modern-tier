package com.api.kakao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "KakaoController", urlPatterns = "/auth/callback")
public class KakaoController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 인가 코드 확인
            String code = request.getParameter("code");
            System.out.println("code: " + code);

            // 토큰 발급 받기
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
