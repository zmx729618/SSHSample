package org.nercita.core.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ValidateCodeGeneratorUtil implements Serializable{
	
	private static final long serialVersionUID = -849843225203618000L;
	
	//设置图形验证码中的字符串的字体和大小
	private static Font mFont = new Font("Arial Black", Font.PLAIN, 16);

	
	 //生成随机颜色
	public static Color getRandColor(int fc, int bc) {
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
	
	/**
	 * 生成验证码和图像
	 * @param request
	 * @param response
	 * @param width
	 * @param height
	 * @return
	 */
	public static  void createBufferedImage(HttpServletRequest request,HttpServletResponse response, Integer width, Integer height)throws IOException {
		//防止生成页面的内容被缓存，保证每次重新生成随机验证
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		//指定验证随机码的图片的大小
		width = width!=null? width: 120; 
		height = height!=null? height: 30;
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
		//添加验证码到session中
		session.setAttribute("randomCode", sRand);
		g.dispose();
		//输出
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}
	
	//获得随机字符串
	private static String getRandomChar() {
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
