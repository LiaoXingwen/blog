package cc.bblog.tools;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import cc.bblog.entity.FileMapping;
import cc.bblog.entity.ResponseMessage;

public class FileUploadTools {
	public FileUploadTools() {
		// TODO 自动生成的构造函数存根
	}
	/**
	 * 上传图片的处理
	 * @param myPhoto 
	 * @param request
	 * @param maxwidth 最大宽度
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static ResponseMessage uploadimg(MultipartFile myPhoto,HttpServletRequest request,int maxwidth,int uid) throws UnsupportedEncodingException{

		try {
			if (myPhoto!=null) {// 判断上传的文件是否为空

				String path=null;// 文件路径
				String type=null;// 文件类型
				String fileName=myPhoto.getOriginalFilename();// 文件原名称
				// 判断文件类型
				type=fileName.indexOf(".")!=10001?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
				if (type!=null) {// 判断文件类型是否为空
					if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())
							||"JPEG".equals(type.toUpperCase())) {
						// 项目在容器中实际发布运行的根路径
						String realPath=request.getSession().getServletContext().getRealPath("/");
						File filepath = new File(realPath);

						realPath = filepath.getParent()+"\\file\\image\\blog\\user\\" ; 
						//文件目录不存在，创建
						File realdir = new File(realPath);
						if(!realdir.exists()){
							realdir.mkdirs();
						}
						// 自定义的文件名称,因为使用ImgCompress进行格式转换为jpg，所以格式都是jpg
						String trueFileName=String.valueOf(System.currentTimeMillis())+""+StringTools.getRandomString(5)+".png";
						// 设置存放图片文件的路径
						path=realPath+trueFileName;
						// 转存文件到指定的路径
						//						myPhoto.transferTo(new File(path));
						ImgCompress imgCom = new ImgCompress(myPhoto.getInputStream(),realPath+trueFileName);  
						float size = imgCom.resizeByWidth(maxwidth);
						FileMapping mapping = new FileMapping(uid, "/file/image/blog/user/"+trueFileName, size);
						
						return new ResponseMessage(10000,mapping.toString());
					}else {
						return new ResponseMessage(10001,"类型不支持");
					}
				}else {
					return new ResponseMessage(10001,"类型不支持");
				}
			}else {
				return new ResponseMessage(10001,"没有文件被上传");
			}

		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseMessage(10001,"保存异常");
		}
	}
}
