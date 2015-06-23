package com.wxservice.framework.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.ReflectionUtils;

import com.wxservice.database.po.system.Tsysimg;
import com.wxservice.framework.components.License.LicenseService;
import com.wxservice.framework.dao.impl.Operators;
import com.wxservice.framework.engine.support.SysConfig;

public class PicUtil {
	private static final Log log = LogFactory.getLog(PicUtil.class);
	public static final String _systemDefaultPic = "default";
	/**图片格式：JPG*/
    private static final String PICTRUE_FORMATE_JPG = "jpg";
    public static final String extnameofpng = ".png";
    public static final String _systemTladadminPic = "picture/tladadmin";
    public static final String _tladadminmodulename = _systemTladadminPic;
    public static final String _systemTcompetionPic = "picture/tcompetion";
    public static final String _tcompetionmodulename = _systemTcompetionPic;
    public static final String _systemTcompetiondtlPic = "picture/tcompetiondtl";
    public static final String _tcompetiondtlmodulename = _systemTcompetiondtlPic;

    public static String getSystemDir(String webpath) {
		StringBuffer path = new StringBuffer("");
		path.append(LicenseService.systemPath).append("/").append(webpath)
				.append("/");
		File directory = new File(path.toString());
		if (!directory.exists()) {// 目录不存在,必须强制建立
			try {
				FileUtils.forceMkdir(directory);
			} catch (Exception e) {
				ReflectionUtils.handleReflectionException(e);
			}
		}
		return path.toString();
	}
	
	public static void writeJPG(InputStream in, String filename)
			throws IOException, FileNotFoundException {

		OutputStream out = new BufferedOutputStream(new FileOutputStream(
				filename));
		byte[] tmp = new byte[10];
		while (in.read(tmp) != -1) {
			out.write(tmp);
		}
		in.close();
		out.close();
		
	}

	public static boolean forceDelete(File f) {
		boolean result = false;
		int tryCount = 0;
		while (!result && tryCount++ < 10) {

			System.gc();
			result = f.delete();
		}
		return result;
	}
	
	 /**
     * 添加文字水印
     * @param targetImg 目标图片路径，如：C://myPictrue//1.jpg
     * @param pressText 水印文字， 如：中国证券网
     * @param fontName 字体名称，    如：宋体
     * @param fontStyle 字体样式，如：粗体和斜体(Font.BOLD|Font.ITALIC)
     * @param fontSize 字体大小，单位为像素
     * @param color 字体颜色
     * @param x 水印文字距离目标图片左侧的偏移量，如果x<0, 则在正中间
     * @param y 水印文字距离目标图片上侧的偏移量，如果y<0, 则在正中间
     * @param alpha 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
*/
    public static void pressText(String originalImg,String targetImg, String pressText1,String pressText2,String fontName, int fontStyle, int fontSize, Color color, int x, int y, float alpha) {
        try {
            File file = new File(originalImg);
            File targetfile = new File(targetImg);
            Image image = ImageIO.read(file);
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, width, height, null);
            g.setFont(new Font(fontName, fontStyle, fontSize));
            g.setColor(color);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            
            int width_1 = fontSize * getLength(pressText1);
            int height_1 = fontSize;
            int widthDiff = width - width_1;
            int heightDiff = height - height_1;
            if(x < 0){
                x = widthDiff / 2;
            }else if(x > widthDiff){
                x = widthDiff;
            }
            if(y < 0){
                y = heightDiff / 2;
            }else if(y > heightDiff){
                y = heightDiff;
            }
            
            g.drawString(pressText1, x, y + height_1);
            g.drawString(pressText2, x, y+20 + height_1);
            g.dispose();
            ImageIO.write(bufferedImage, PICTRUE_FORMATE_JPG, targetfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取字符长度，一个汉字作为 1 个字符, 一个英文字母作为 0.5 个字符
     * @param text
     * @return 字符长度，如：text="中国",返回 2；text="test",返回 2；text="中国ABC",返回 4.
*/
    public static int getLength(String text) {
        int textLength = text.length();
        int length = textLength;
        for (int i = 0; i < textLength; i++) {
            if (String.valueOf(text.charAt(i)).getBytes().length > 1) {
                length++;
            }
        }
        return (length % 2 == 0) ? length / 2 : length / 2 + 1;
    }
    public static void main(String[] args) throws IOException {
    	//pressText("d:/1.jpg","d:/2.jpg", "aaa","bbb", "楷体", Font.BOLD, 20, Color.BLACK, 5, 180, 1.0f);
    }
   
   
    public static void saveTsysimg(String module,String filename,Integer moduleid){
    	Tsysimg po=new Tsysimg();
    	po.setModule(module);
    	po.setModuleid(moduleid);
    	po.setName(filename);
    	WebUtil.getDao().addSave(po);
    }
    public static void deleteTsysimg(String filename){
    	StringBuffer hql=new StringBuffer();
    	hql.append("delete from tsysimg where name='").append(filename).append("'");
    	Operators os=new Operators();
    	os.addScriptObject(hql.toString());
    	WebUtil.getDao().execute(os);
    }
    public static Integer getTsysimg(String filename){
    	Integer tsysimgid=0;
    	StringBuffer hql=new StringBuffer();
    	hql.append(" from Tsysimg where name='").append(filename).append("'");    	
    	Tsysimg img=(Tsysimg)WebUtil.getDao().get(hql.toString());
    	if(img!=null){
    		tsysimgid=img.getTsysimgid();
    	}
    	return tsysimgid;
    }
    
    public static String getBaseName(Integer id,String file){
    	Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		long stamp = cal.getTimeInMillis();
    	
		StringBuffer filename = new StringBuffer();
		filename.append(file);
		filename.append(id);
		filename.append("_");
		filename.append(stamp);
		filename.append(extnameofpng);
		
		return filename.toString();
    }
    
    public static String getTladadminJPGName(Integer ladadminid) {
		return getBaseName(ladadminid,"tladadmin_");
	}
    public static String getTcompetionJPGName(Integer competionid) {
    	
    	
    	return getBaseName(competionid,"tcompetion_");
    	
	}
    public static String getTcompetiondtlJPGName(Integer competiondtl) {
    	return getBaseName(competiondtl,"tcompetiondtl_");
	}
    
    public static String getPiccUrl(String imgsrc, Integer modelid, String syspic,String modulename, String filename) {
		MyBase64 decoder = new MyBase64();
		String picurl = "";
		try {
			byte[] bytes = decoder.decode(imgsrc);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {
					bytes[i] += 256;
				}
			}
			System.out.println(imgsrc);
			System.out.println(bytes);
			String path = PicUtil.getSystemDir(syspic) + filename;
			InputStream in = new ByteArrayInputStream(bytes);
			PicUtil.deleteTsysimg(filename);
			PicUtil.writeJPG(in, path);
			PicUtil.saveTsysimg(modulename, filename, modelid);
			picurl = syspic + "/" + filename;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return picurl;
	}
    public static String  getPicStr(String pic){
   	 StringBuffer s = new StringBuffer();
   	 if(StringUtil.isNotBlank(pic)){
				String picstr = SysConfig.getStringConfig("ucard.path.name")+pic;
				s.append(" <a href='").append(picstr).append("' data-lightbox='roadtrip'>查看图片</a>");
			}else{
				s.append("无图片");
			}
   	 return s.toString();
    }
    
    
	
	
    
    
    
}
