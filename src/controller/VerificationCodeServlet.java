package controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/verificationCode")
public class VerificationCodeServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Controller","No-cache");
		response.setDateHeader("Expires", -1);
		OutputStream out = response.getOutputStream();
		int width = 80,height = 20;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Random random = new Random();
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		String sRand = "";
		for(int i = 0;i<4;i++){
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
			g.drawString(rand, 20*i+6, 16);
		}
//		把生成好的验证码 放到session 其他想要拿这个验证码 就可以通过session去哪
		HttpSession session = request.getSession();
		session.setAttribute("code", sRand);
		g.dispose();
		ImageIO.write(image, "JPEG", out);
	}

}
