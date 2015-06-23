package com.wxservice.framework.components.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 随机码图像处理类
 * 
 * 
 */
public class ImageCode {

	public String sRand = "";

	public Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
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

	public BufferedImage createNewImage() {
		// 验证码图片的宽度。
		int width = 60;
		// 验证码图片的高度。
		int height = 20;
		int codeCount = 4;
		int x = 0;
		int fontHeight;
		int codeY;
		x = width / (codeCount + 1);
		fontHeight = height - 2;
		codeY = height - 4;

		char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
				'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
				'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6',
				'7', '8', '9' };
		BufferedImage buffImg = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 创建完一个BufferedImage后，你需要得到图形环境进行绘制，一个Graphics或者Graphics2D对象：Graphics g
		// = image.getGraphics();
		Graphics2D g = buffImg.createGraphics();

		// 创建一个随机数生成器类。
		Random random = new Random();
		// 设置背景颜色
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);

		// 创建字体，字体的大小应该根据图片的高度来定。
		Font font = new Font("楷体_GB2312", Font.PLAIN, fontHeight);
		// 设置字体。
		g.setFont(font);

		// 画边框。
//		g.setColor(Color.BLACK);
//		g.drawRect(0, 0, width - 1, height - 1);

		// 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。
		g.setColor(Color.GRAY);
		for (int i=0;i<5;i++)
        {
            int xx = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(5);
            int yl = random.nextInt(5);
            g.drawLine(xx,y,x+xl,y+yl);
        }
       //randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode=new StringBuffer();
        int red=0,green=0,blue=0;
        //随机产生4位数字的验证码。
        for (int i=0;i<codeCount;i++)
        {
            //得到随机产生的验证码数字。
            String strRand=String.valueOf(codeSequence[random.nextInt(36)]);
           
            System.out.println(strRand);
       //产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
            red=random.nextInt(110);
            green=random.nextInt(50);
            blue=random.nextInt(50);
           
            //用随机产生的颜色将验证码绘制到图像中。
            g.setColor(new Color(red,green,blue));
            g.drawString(strRand,(i+1)*x,codeY);
           
            //将产生的四个随机数组合在一起。
            randomCode.append(strRand);
        }
    	g.dispose();
		return buffImg;

	}

	public BufferedImage creatImage() {
		// 在内存中创建图象
		int width = 60, height = 20;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 生成随机类
		Random random = new Random();
		// 设定背景色
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		// 设定字体
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		// 画边框
		 g.setColor(Color.BLACK);
		 g.drawRect(0,0,width-1,height-1);
		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 40; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// 取随机产生的认证码(4位数字)
		// String rand = request.getParameter("rand");
		// rand = rand.substring(0,rand.indexOf("."));
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.drawString(rand, 13 * i + 6, 16);
		}
		// 图象生效
		g.dispose();
		return image;
	}

	/**
	 * @return Returns the sRand.
	 */
	public String getSRand() {
		return sRand;
	}

	public BufferedImage creatImage(int number) {
		// 在内存中创建图象
		String num = String.valueOf(number);
		char[] vs = num.toCharArray();

		int width = vs.length * 60 / 4, height = 18;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 生成随机类
		Random random = new Random();
		// 设定背景色
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		// 设定字体
		g.setFont(new Font("Impact", Font.PLAIN, 14));
		// 画边框
		// g.setColor(new Color());
		// g.drawRect(0,0,width-1,height-1);
		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		/*
		 * g.setColor(getRandColor(160, 200)); for (int i = 0; i < 155; i++) {
		 * int x = random.nextInt(width); int y = random.nextInt(height); int xl =
		 * random.nextInt(12); int yl = random.nextInt(12); g.drawLine(x, y, x +
		 * xl, y + yl); }
		 */
		// 取随机产生的认证码(4位数字)
		// String rand = request.getParameter("rand");
		// rand = rand.substring(0,rand.indexOf("."));
		for (int i = 0; i < vs.length; i++) {
			String rand = String.valueOf(vs[i]);
			sRand += rand;
			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.drawString(rand, 13 * i + 6, 16);

		}
		// 图象生效
		g.dispose();
		return image;
	}

	public BufferedImage creatImage3D(int number, int max) {
		// 在内存中创建图象
		int width = 60, height = 10;
		int x = number * width / max;
		System.out.println(x);
		BufferedImage image = new BufferedImage(x, height,
				BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文

		Graphics g = image.getGraphics();
		// g.fillRect(0, 0, width, height);

		g.setColor(this.getRandColor(100, 200));
		g.draw3DRect(0, 0, x, height, true);
		g.fill3DRect(0, 0, x, height, true);
		// 图象生效
		g.dispose();
		return image;
	}
}
