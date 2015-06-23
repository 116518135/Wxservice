package com.wxservice.framework.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.font.FontRenderContext;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.lowagie.text.pdf.Barcode128;
import com.wxservice.framework.engine.support.SysConfig;

public class BarcodeUtil {
	private static final int HEIGHT_SPACE = 20;// 图片之间的间隔


	public static void createBarcodeImage(int barCodeWidth, int barCodeHeight,
			List<String> codeList) throws Exception {
		// list不能为空
		// 图片宽度
		int imageWidth = (barCodeWidth + barCodeWidth / 2) * codeList.size()
				+ barCodeWidth / 2;
		// 图片高度
		int imageHeight = barCodeHeight + HEIGHT_SPACE * 2;
		BufferedImage img = new BufferedImage(imageWidth, imageHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) img.getGraphics();
		g.fillRect(0, 0, imageWidth, imageHeight);
		Font font = new java.awt.Font("", java.awt.Font.PLAIN, 12);
		Barcode128 barcode128 = new Barcode128();
		FontRenderContext fontRenderContext = g.getFontRenderContext();
		// 条形码（文字）的高度
		int stringHeight = (int) font.getStringBounds("", fontRenderContext)
				.getHeight();
		// 图片横坐标开始位置
		int startX = barCodeWidth / 2;
		// 图片纵坐标开始位置
		int imageStartY = (imageHeight - barCodeHeight - stringHeight) / 2;
		int stringStartY = imageStartY * 2 + barCodeHeight;// 条形码（文字）开始位置
		for (String code : codeList) {
			int codeWidth = (int) font.getStringBounds(code, fontRenderContext)
					.getWidth();
			barcode128.setCode(code);
			Image codeImg = barcode128.createAwtImage(Color.black, Color.white);
			g.drawImage(codeImg, startX, imageStartY, barCodeWidth,
					barCodeHeight, Color.white, null);
			// 为图片添加条形码（文字），位置为条形码图片的下部居中
			AttributedString ats = new AttributedString(code);
			ats.addAttribute(TextAttribute.FONT, font, 0, code.length());
			AttributedCharacterIterator iter = ats.getIterator();
			// 设置条形码（文字）的颜色为蓝色
			g.setColor(Color.BLUE);
			// 绘制条形码（文字）
			g.drawString(iter, startX + (barCodeWidth - codeWidth) / 2,
					stringStartY);
			// 更改图片横坐标开始位置，图片之间的空隙为条形码的宽度的一半
			startX = startX + barCodeWidth / 2 + barCodeWidth;
		}
		g.dispose();
		OutputStream os = new BufferedOutputStream(new FileOutputStream(
				"/home/admin/codeList.png"));
		ImageIO.write(img, "PNG", os);
	}

	public static BufferedImage createBarcodeImage(String code) throws Exception {

		int barCodeWidth=MapUtil.getInteger(SysConfig.getProps(), "ucard.barcode.width", false);
		
		int barCodeHeight=MapUtil.getInteger(SysConfig.getProps(), "ucard.barcode.height", false);
		if(barCodeWidth==0){
			barCodeWidth=200;
		}
		if(barCodeHeight==0){
			barCodeHeight=50;
		}
		
		BufferedImage img = new BufferedImage(barCodeWidth, barCodeHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) img.getGraphics();
		g.fillRect(0, 0, barCodeWidth, barCodeHeight);
		Font font = new java.awt.Font("", java.awt.Font.PLAIN, 12);
		Barcode128 barcode128 = new Barcode128();
		FontRenderContext fontRenderContext = g.getFontRenderContext();
		// 条形码（文字）的高度
		int stringHeight = (int) font.getStringBounds("", fontRenderContext)
				.getHeight();
		// 图片横坐标开始位置
		int startX = barCodeWidth / 2;
		// 图片纵坐标开始位置
		int imageStartY = (barCodeHeight - stringHeight) / 2;
		int stringStartY = imageStartY * 2 + barCodeHeight;// 条形码（文字）开始位置
		int codeWidth = (int) font.getStringBounds(code, fontRenderContext)
				.getWidth();
		barcode128.setCode(code);
		Image codeImg = barcode128.createAwtImage(Color.black, Color.white);
		g.drawImage(codeImg, startX, imageStartY, barCodeWidth, barCodeHeight,
				Color.white, null);
		// 为图片添加条形码（文字），位置为条形码图片的下部居中
		AttributedString ats = new AttributedString(code);
		ats.addAttribute(TextAttribute.FONT, font, 0, code.length());
		AttributedCharacterIterator iter = ats.getIterator();
		// 设置条形码（文字）的颜色为蓝色
		g.setColor(Color.BLUE);
		// 绘制条形码（文字）
		g.drawString(iter, startX + (barCodeWidth - codeWidth) / 2,
				stringStartY);
		// 更改图片横坐标开始位置，图片之间的空隙为条形码的宽度的一半
		startX = startX + barCodeWidth / 2 + barCodeWidth;

		g.dispose();
		return img;
	}

}
