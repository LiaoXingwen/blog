package cc.bblog.tools;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;  

/**   
 *  图片压缩  ,只向下压缩
 * 项目名称：blog   
 * 类名称：ImgCompress   
 * 类描述：   
 * 创建人：廖兴文
 * 创建时间：2017年5月16日 下午10:39:09   
 * 修改人：lxw   
 * 修改时间：2017年5月16日 下午10:39:09   
 * 修改备注：   
 * @version 1.0.0   
 *    
 */
public class ImgCompress {  
	private Image img;  
	private int width;  
	private int height;  
	private String outPath ; //输出路径
	public static void main(String[] args) throws Exception {  
		ImgCompress imgCom = new ImgCompress("F:\\html\\a.jpg","F:\\html\\result.jpg");  
		imgCom.resizeByWidth(60);
	}  
	/** 
	 * 构造函数 
	 */  
	public ImgCompress(String fileName,String outPath) throws IOException {  
		File file = new File(fileName);// 读入文件  
		img = ImageIO.read(file);      // 构造Image对象  
		width = img.getWidth(null);    // 得到源图宽  
		height = img.getHeight(null);  // 得到源图长  
		this.outPath = outPath ; 
	}  
	/** 
	 * 构造函数 
	 */  
	public ImgCompress(File file,String outPath) throws IOException {  
		img = ImageIO.read(file);      // 构造Image对象  
		width = img.getWidth(null);    // 得到源图宽  
		height = img.getHeight(null);  // 得到源图长  
		this.outPath = outPath ; 
	}  
	/** 
	 * 构造函数 
	 */  
	public ImgCompress(InputStream file,String outPath) throws IOException {  
		img = ImageIO.read(file);      // 构造Image对象  
		width = img.getWidth(null);    // 得到源图宽  
		height = img.getHeight(null);  // 得到源图长  
		this.outPath = outPath ; 
	} 
	/** 
	 * 按照宽度还是高度进行压缩 
	 * @param w int 最大宽度 
	 * @param h int 最大高度 
	 */  
	public float resizeFix(int w, int h) throws IOException {  
		if (width / height > w / h) {  
			return resizeByWidth(w);  
		} else {  
			return resizeByHeight(h);  
		}  
	}  
	/** 
	 * 以宽度为基准，等比例放缩图片 
	 * @param w int 新宽度 
	 */  
	public float resizeByWidth(int w) throws IOException { 
		if (w<width) {
			int h = (int) (height * w / width);  
			return resize(w, h);  
		}else {
			return resize(width, height);  
		}
	}  
	/** 
	 * 以高度为基准，等比例缩放图片 
	 * @param h int 新高度 
	 */  
	public float resizeByHeight(int h) throws IOException { 
		if (h<height) {
			int w = (int) (width * h / height);  
			return resize(w, h);
		}else {
			return resize(width, height);
		}
	}  
	/** 
	 * 强制压缩/放大图片到固定的大小 
	 * @param w int 新宽度 
	 * @param h int 新高度 
	 */  
	public float resize(int w, int h) throws IOException {  
		BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );   
		image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图  
		// 可以正常实现bmp、png、gif转jpg  
//		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
//		encoder.encode(image); // JPEG编码  
		RenderedImage resultImage = (RenderedImage) image;
		ImageIO.write(resultImage,  "PNG", new File(outPath));
		return Float.valueOf(String.format("%.1f",new File(outPath).length()/1024.0));
	}  
}  