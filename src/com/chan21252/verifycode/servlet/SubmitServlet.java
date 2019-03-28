package com.chan21252.verifycode.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "SubmitServlet",
        urlPatterns = "/submit.do"
)
public class SubmitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("开始验证...");

        request.setCharacterEncoding("utf-8");

        String userInputStr = request.getParameter("verifyText");
        String codeStr = (String) request.getSession().getAttribute("code");

        System.out.println("用户输入：" + userInputStr);
        System.out.println("正确的验证码：" + codeStr);

        String msg;
        if (userInputStr.equalsIgnoreCase(codeStr)) {
            msg = "验证通过";
        } else {
            msg = "验证失败";
        }

        System.out.println(msg);

        request.getSession().setAttribute("msg", msg);
        response.sendRedirect("/verify.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
