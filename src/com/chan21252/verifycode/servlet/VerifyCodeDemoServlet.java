package com.chan21252.verifycode.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(
        name = "VerifyCodeDemoServlet",
        urlPatterns = "/verifyCodeDemo"
)
public class VerifyCodeDemoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 100;
        int height = 50;

        //画布
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //填充背景色
        Graphics g = image.getGraphics();   //画笔对象
        g.setColor(Color.PINK);     //设置背景色
        g.fillRect(0, 0, width, height);    //填充

        //绘制边框
        g.setColor(Color.BLUE);
        g.drawRect(0, 0, width - 1, height - 1);

        //绘制验证码
        String str = "ABCDEFGHIJKMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";      //验证码字符样本

        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            //随机生成字符，绘制验证码
            int index = random.nextInt(str.length());
            char ch = str.charAt(index);
            g.drawString(ch + "", width / 5 * i + 20, height / 2);
        }

        //绘制干扰线
        g.setColor(Color.GREEN);
        for (int i = 0; i < 6; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            g.drawLine(x1, y1, x2, y2);
        }

        //输出图片
        ImageIO.write(image, "jpg", response.getOutputStream());
    }
}
