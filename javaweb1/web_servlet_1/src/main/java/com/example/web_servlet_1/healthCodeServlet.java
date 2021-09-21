package com.example.web_servlet_1;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "healthCodeServlet", value = "/healthCodeServlet")
public class healthCodeServlet extends HttpServlet {
    private static MultiFormatWriter mutiWriter = new MultiFormatWriter();

    public static void encode(String content, int width, int height, int color,String destImagePath) {
        try {
            //生成图片文件
            ImageIO.write(genBarcode(content, width, height,color), "jpg", new File(destImagePath));
            System.out.println("二维码生成成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String stuid= request.getParameter("stuid");
        String idnumber = request.getParameter("idnumber");
        String phone = request.getParameter("phone");
        String check0 = request.getParameter("0");
        String check1 = request.getParameter("1");
        String check2 = request.getParameter("2");
        String check3 = request.getParameter("3");
        String []check4 = request.getParameterValues("4");
        Calendar calendar = Calendar.getInstance();
        Date time = calendar.getTime();
        healthcode hcode = new healthcode(name);
        String info = "姓名："+name+"\n"+"学号或工号："+stuid+"\n"+"生成时间："+time;
        System.out.println(info);
        if(check0.equals("yes")||check1.equals("yes")||(check4.length==1&&(!check4[0].equals("checkbox0"))))
        {
            healthCodeServlet.encode(info, 300, 300, 2,"D:\\JetBrains\\IntelliJ IDEA 2020.3.3\\ideaProjects\\web_servlet_1\\src\\main\\webapp\\photo\\qrcode.jpg");
        }
        else if(check2.equals("yes")||check3.equals("yes")||check4.length>1)
        {
            healthCodeServlet.encode(info, 300, 300, 3,"D:\\JetBrains\\IntelliJ IDEA 2020.3.3\\ideaProjects\\web_servlet_1\\src\\main\\webapp\\photo\\qrcode.jpg");
        }
        else
        {
            healthCodeServlet.encode(info, 300, 300, 1,"D:\\JetBrains\\IntelliJ IDEA 2020.3.3\\ideaProjects\\web_servlet_1\\src\\main\\webapp\\photo\\qrcode.jpg");
        }

        HttpSession session = request.getSession();
        synchronized(session) {
            session.setAttribute("healthcode", hcode);
        }
        RequestDispatcher rd =
                request.getRequestDispatcher("/showHealthcode.jsp");
        rd.forward(request,response);
    }

    private static BufferedImage genBarcode(String content, int width, int height,int colour) throws WriterException, IOException {
        //定义二维码内容参数
        Map<EncodeHintType, Object> hints = new HashMap<>();
        //设置字符集编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        //设置容错等级，在这里我们使用M级别
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        // 生成二维码，参数顺序分别为：编码内容，编码类型，生成图片宽度，生成图片高度，设置参数
        BitMatrix matrix = mutiWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);

        // 二维矩阵转为一维像素数组
        int halfW = matrix.getWidth() / 2;
        int halfH = matrix.getHeight() / 2;
        int[] pixels = new int[width * height];
        if(colour==1) {
//            绿
            for (int y = 0; y < matrix.getHeight(); y++) {
                for (int x = 0; x < matrix.getWidth(); x++) {
                    // 二维码颜色（RGB）
                    Color color = new Color(0, 95, 1);
                    int colorInt = color.getRGB();
                    // 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色；
                    pixels[y * width + x] = matrix.get(x, y) ? colorInt : 16777215;
                    // 0x000000:0xffffff
                }
            }
        }
        else if(colour==2)
        {
//            黄
            for (int y = 0; y < matrix.getHeight(); y++) {
                for (int x = 0; x < matrix.getWidth(); x++) {
                    // 二维码颜色（RGB）
                    Color color = new Color(238, 193, 58);
                    int colorInt = color.getRGB();
                    // 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色；
                    pixels[y * width + x] = matrix.get(x, y) ? colorInt : 16777215;
                    // 0x000000:0xffffff
                }
            }
        }
        else if(colour==3)
        {
//            红
            for (int y = 0; y < matrix.getHeight(); y++) {
                for (int x = 0; x < matrix.getWidth(); x++) {
                    // 二维码颜色（RGB）
                    Color color = new Color(247, 3, 3);
                    int colorInt = color.getRGB();
                    // 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色；
                    pixels[y * width + x] = matrix.get(x, y) ? colorInt : 16777215;
                    // 0x000000:0xffffff
                }
            }
        }

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        image.getRaster().setDataElements(0, 0, width, height, pixels);

        return image;
    }
}
