package com.chan21252.verifycode.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet(
        name = "VerifyCodeGenServlet",
        urlPatterns = "/verifyCode"
)
public class VerifyCodeGenServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VerifyCodeUtils verifyCodeUtils = new VerifyCodeUtils();
        BufferedImage codeImage = verifyCodeUtils.generateCaptcha();
        String codeStr = verifyCodeUtils.getText();

        System.out.println("生成新的验证码：" + codeStr);

        HttpSession session = request.getSession();
        session.setAttribute("code", codeStr);
        verifyCodeUtils.output(codeImage, response.getOutputStream());
    }
}
