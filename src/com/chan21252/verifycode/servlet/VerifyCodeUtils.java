package com.chan21252.verifycode.servlet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 图形验证码工具类
 */
public class VerifyCodeUtils {
    private static int width;
    private static int height;
    private static Random random;
    private static String[] fontNames;
    private static String codes;
    private static Color bgColor;

    static {
        width = 100;
        height = 50;
        random = new Random();
        fontNames = new String[]{"宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312"};
        codes = "0123456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";
        bgColor = new Color(255, 255, 255);
    }

    private String text;

    //随机生成颜色
    private Color randomColor() {
        int r = random.nextInt(150);
        int g = random.nextInt(150);
        int b = random.nextInt(150);

        return new Color(r, g, b);
    }

    //随机生成字体
    private Font radomFont() {
        int index = random.nextInt(fontNames.length);
        String fontName = fontNames[index];
        int style = random.nextInt(4);
        int size = random.nextInt(5) + 24;

        return new Font(fontName, style, size);
    }

    //画干扰线
    private void drawLine(BufferedImage image) {
        int num = 3;    //3条干扰线
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        for (int i = 0; i < num; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);

            g2.setStroke(new BasicStroke(1.5F));
            g2.setColor(Color.BLUE);
            g2.drawLine(x1, y1, x2, y2);
        }
    }

    //随机生成一个字符
    private char randomChar() {
        int index = random.nextInt(codes.length());

        return codes.charAt(index);
    }

    //创建绘制图片
    private BufferedImage createImage() {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        g2.setColor(bgColor);
        g2.fillRect(0, 0, width, height);

        return image;
    }

    //生成验证码，并绘制
    public BufferedImage generateCaptcha() {
        BufferedImage image = createImage();    //创建图片缓冲区
        Graphics2D g2 = (Graphics2D) image.getGraphics();    //得到绘制环境
        StringBuilder sb = new StringBuilder();     //装载验证码字符

        for (int i = 0; i < 4; i++) {
            String s = randomChar() + "";
            sb.append(s);
            float x = i * 1.0F * width / 4;
            g2.setColor(randomColor());
            g2.setFont(radomFont());
            g2.drawString(s, x, height - 5);
        }

        this.text = sb.toString();

        drawLine(image);

        return image;
    }

    //返回验证码文本
    public String getText() {
        return text;
    }

    //保存图片
    public void output(BufferedImage image, OutputStream out)
            throws IOException {
        ImageIO.write(image, "JPEG", out);
    }
}
