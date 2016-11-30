package com.xxcb.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import com.sun.image.codec.jpeg.*;
import com.xxcb.weixin.ValidateServlet;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * 图片处理类
 * @author lidu
 * @data 2015.11.09
 *
 */
public class ImageUtil {
	public static void main(String[] a) {

		ImageUtil.createStringMark("D://F.jpg", "人生如梦","ABCDEF",Color.white, 100,"d://B.jpg");
		bigToSmall("d://big.jpg", "d://small.jpg", 60, 60);
	}

		/**
		 * 在图片上面添加昵称和邀请码
		 * @param filePath
		 * @param nickname
		 * @param code
		 * @param markContentColor
		 * @param qualNum
		 * @param outPath
		 * @return boolean
		 */
		@SuppressWarnings("restriction")
		public static boolean createStringMark(String filePath,String nickname, String code ,Color markContentColor,float qualNum ,String outPath) 
		{ 
			//根据活动需要添加的文字
			ImageIcon imgIcon=new ImageIcon(filePath); 
			Image theImg =imgIcon.getImage(); 
			int width=theImg.getWidth(null)==-1?200:theImg.getWidth(null); 
			int height= theImg.getHeight(null)==-1?200:theImg.getHeight(null); 
			BufferedImage bimage = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB); 
			
			Graphics2D g=bimage.createGraphics(); 
			
			g.setColor(markContentColor); 
			g.setBackground(Color.red); 
			g.drawImage(theImg, 0, 0, null ); 
			g.setFont(new Font(null,Font.BOLD,22)); //字体、字型、字号 
			
			
			if (hasFullChar(nickname))
			{
				//包含中文
				g.drawString(nickname,(300 - (22*(nickname.length())))/2,85); //画文字
			}
			else
			{
				g.drawString(nickname,(300 - (15*(nickname.length())))/2,85); //画文字
			}
			g.drawString("邀请码: "+code,65,115); //画文字 
			g.dispose(); 
					
			try 
			{ 
				FileOutputStream out=new FileOutputStream(outPath); //先用一个特定的输出文件名 
				JPEGImageEncoder encoder =JPEGCodec.createJPEGEncoder(out); 
				JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage); 
				param.setQuality(qualNum, true); 
				encoder.encode(bimage, param); 
				out.close(); 
			} 
			catch(Exception e) 
			{ return false; } 
			return true; 
		}
		
		/**
		 * 
		 * @param filePath1
		 * @param filePath2
		 * @return
		 */
		public static boolean imageMerge(String filePath1, String filePath2)
		{
			ImageIcon imgIcon1=new ImageIcon(filePath1); 
			Image theImg1 =imgIcon1.getImage();
			ImageIcon imgIcon2=new ImageIcon(filePath2); 
			Image theImg2 =imgIcon2.getImage();
			return false;
		}
		
		/**
		 * 判断昵称为是否包含中文
		 * @param str
		 * @return
		 */
		public static boolean hasFullChar(String str) {
	        if (str.getBytes().length == str.length()) {
	            return false;
	        } 
	        return true;    
	    }
		
		/**
		 * 从网上下载头像到本地
		 * @param urlString
		 * @param filename
		 * @param savePath
		 * @throws Exception
		 */
		public static void download(String urlString, String filename,String savePath) throws Exception 
		{  
	        // 构造URL  
	    	URL url = new URL(urlString);  
	        // 打开连接  
	        URLConnection con = url.openConnection();  
	        //设置请求超时为5s  
	        con.setConnectTimeout(5*1000);  
	        // 输入流  
	        InputStream is = con.getInputStream();  
	      
	        // 1K的数据缓冲  
	        byte[] bs = new byte[1024];  
	        // 读取到的数据长度  
	        int len;  
	        // 输出的文件流  
	        File sf=new File(savePath);  
	        if(!sf.exists()){  
	            sf.mkdirs();  
	        }  
	        OutputStream os = new FileOutputStream(sf.getPath()+"\\"+filename);  
	        // 开始读取  
	        while ((len = is.read(bs)) != -1) {  
	            os.write(bs, 0, len);  
	        }  
	        // 完毕，关闭所有链接  
	        os.close();  
	        is.close();  
	    }   
		
		/**
		 * 将下载的头像转为小图
		 * @param inFilePath
		 * @param outFilePath
		 * @param width
		 * @param height
		 */
		public static void bigToSmall(String inFilePath, String outFilePath, int width, int height){  
			PropertyConfigurator.configure( "C:/Program Files/Apache Software Foundation/Tomcat 6.0/webapps/weixin/WEB-INF/log4j.properties" );
			final Logger logger  =  Logger.getLogger(ImageUtil.class );
			logger.warn("bigToSmall");
	        File tempFile = new File(inFilePath);  
	        Image image = null;  
	        try {  
	            image = ImageIO.read(tempFile);  
	        } catch (IOException e) {  
	            System.out.println("file path error...");  
	        }  
	          
	        int originalImageWidth = image.getWidth(null);  
	        int originalImageHeight = image.getHeight(null);  
	        logger.warn("originalImageWidth:"+originalImageWidth);  
	        logger.warn("originalImageHeight:"+originalImageHeight);  
	        
	        BufferedImage originalImage = new BufferedImage(  
	                originalImageWidth,  
	                originalImageHeight,  
	                BufferedImage.TYPE_3BYTE_BGR);  
	        Graphics2D g2d = originalImage.createGraphics();  
	        g2d.drawImage(image, 0, 0, null);  
	          
	        BufferedImage changedImage =  
	            new BufferedImage(  
	                    width,  
	                    height,  
	                    BufferedImage.TYPE_3BYTE_BGR);  
	          
	        double widthBo = (double)width/originalImageWidth;  
	        double heightBo = (double)width/originalImageHeight;  
	        logger.warn("heightBo:"+heightBo);  
	        
	        AffineTransform transform = new AffineTransform();  
	        transform.setToScale(widthBo, heightBo);  
	          
	        AffineTransformOp ato = new AffineTransformOp(transform, null);  
	        ato.filter(originalImage, changedImage);  
	          
	        File fo = new File(outFilePath); //将要转换出的小图文件   
	        logger.warn("change done");  
	        try {  
	            ImageIO.write(changedImage, "jpeg", fo);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }  
		
		/**
		 * 将头像贴附到大图
		 * @param bigPath
		 * @param smallPath
		 */
		public static  void overlapImage(String bigPath, String smallPath) 
		{
			 try
			 {
			      BufferedImage big = ImageIO.read(new File(bigPath));
			      BufferedImage small = ImageIO.read(new File(smallPath));
			      Graphics2D g = big.createGraphics();
			      int x = (big.getWidth() - small.getWidth()) / 2;
			      int y = (big.getHeight() - small.getHeight()) / 2;
			      g.drawImage(small, x, 5, small.getWidth(), small.getHeight(), null);
			      g.dispose();
			      File file = new File("C:" + File.separator +"Program Files" + File.separator +"Apache Software Foundation" + File.separator+"Tomcat 6.0" + File.separator+"webapps" + File.separator+"weixin" + File.separator+"pages" + File.separator+"msgImage" + File.separator+  "result.jpg");
			      ImageIO.write(big, "jpg", file);
			 } catch (Exception e) {
			      e.printStackTrace();
			 }
		}
}
