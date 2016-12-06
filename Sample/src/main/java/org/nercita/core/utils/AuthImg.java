package org.nercita.core.utils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.imageio.*;

/**
 * 生成验证码的Servlet<br><br>
 * 参考配置：<br>
 *  <@servlet><br>
 * 		<@servlet-name>imageCheck<@/servlet-name><br>
 * 		<@servlet-class>org.nercita.core.util.AuthImg<@/servlet-class><br>
 * 	<@/servlet><br>
 * 	<@servlet-mapping><br>
 * 		<@servlet-name>imageCheck<@/servlet-name><br>
 * 		<@url-pattern>/imageCheck<@/url-pattern><br>
 * 	<@/servlet-mapping><br>
 * 
 */
public class AuthImg extends HttpServlet {

	private static final long serialVersionUID = -849843225203618000L;
	
	//设置图形验证码中的字符串的字体和大小
	private Font mFont = new Font("Arial Black", Font.PLAIN, 16);

	public void init() throws ServletException {
		super.init();
	}
	
	//生成随机颜色
	Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
	
	//生成服务器相应的service方法
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//防止生成页面的内容被缓存，保证每次重新生成随机验证码
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		
		//指定验证随机码的图片的大小
		int width = 70, height = 20;
		//生成一张图片
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		//在图片中绘制内容
		Graphics g = image.getGraphics();
		Random random = new Random();
		g.setColor(getRandColor(200, 250));
		g.fillRect(1, 1, width - 1, height - 1);
		g.setColor(new Color(102, 102, 102));
		g.drawRect(0, 0, width - 1, height - 1);
		g.setFont(mFont);
		//随机生成线条，让图片看起来更杂乱
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int xl = random.nextInt(6) + 1;
			int yl = random.nextInt(12) + 1;
			g.drawLine(x, y, x + xl, y + yl);
		}
		for (int i = 0; i < 70; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int xl = random.nextInt(12) + 1;
			int yl = random.nextInt(6) + 1;
			g.drawLine(x, y, x - xl, y - yl);
		}
		
		//该变量用于保存系统生成的随机字符串
		String sRand = "";
		//i，生成字符串的位数
		for (int i = 0; i < 4; i++) {
			String tmp = getRandomChar();
			sRand += tmp;
			//将生成的随机字符串添加到图片上
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(tmp, 15 * i + 10, 15);
		}

		HttpSession session = request.getSession(true);
		//添加验证码到图片上
		session.setAttribute("randomCode", sRand);
		g.dispose();
		//输出
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}
	
	//获得随机字符串
	private String getRandomChar() {
		int rand = (int) Math.round(Math.random() * 2);
		long itmp = 0;
		char ctmp = '\u0000';
		switch (rand) {
		case 1:
			itmp = Math.round(Math.random() * 25 + 65);
			ctmp = (char) itmp;
			return String.valueOf(ctmp);
		case 2:
			itmp = Math.round(Math.random() * 25 + 97);
			ctmp = (char) itmp;
			return String.valueOf(ctmp);
		default:
			itmp = Math.round(Math.random() * 9);
			return String.valueOf(itmp);
		}
	}
}
