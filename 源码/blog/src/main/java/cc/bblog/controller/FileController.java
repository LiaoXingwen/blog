package cc.bblog.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import cc.bblog.dao.BaseDao;
import cc.bblog.entity.FileMapping;
import cc.bblog.entity.LoginMessage;
import cc.bblog.entity.ResponseMessage;
import cc.bblog.tools.CookieTool;
import cc.bblog.tools.FileUploadTools;


@Controller()
@RequestMapping("fileupload")
public class FileController {
	
	BaseDao baseDao ; 
	@Autowired
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	
	@RequestMapping("/uploadImg")
	@ResponseBody 
	public String upload(MultipartFile file,Model model,HttpServletRequest request) throws IOException{
		LoginMessage loginMessage =CookieTool.unlockCookie(request);
		if (loginMessage!=null) {
			ResponseMessage message = FileUploadTools.uploadimg(file, request,150,loginMessage.getId());
			if (message.getState()==10000) {
				FileMapping mapping = new Gson().fromJson(message.getMessage(), FileMapping.class);
				baseDao.save(mapping);
				return  new ResponseMessage(10000, mapping.getUrl()).toString();
			}
			return message.toString() ; 
		}else {
			return  new ResponseMessage(10001, "没有找到你登陆的信息，请先登陆").toString();
		}
	}
	
	/**
	 * 上传头像
	 * @param file
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/uploadImg/head")
	@ResponseBody 
	public String uploadHead(MultipartFile file,Model model,HttpServletRequest request) throws IOException{
		LoginMessage loginMessage =CookieTool.unlockCookie(request);
		if (loginMessage!=null) {
			ResponseMessage message = FileUploadTools.uploadimg(file, request,150,loginMessage.getId());
			if (message.getState()==10000) {
				FileMapping mapping = new Gson().fromJson(message.getMessage(), FileMapping.class);
				baseDao.save(mapping);
				return  new ResponseMessage(10000, mapping.getUrl()).toString();
			}
			return message.toString() ; 
		}else {
			return  new ResponseMessage(10001, "没有找到你登陆的信息，请先登陆").toString();
		}
	}
	
		@RequestMapping("/editor/uploadImg")
		@ResponseBody 
		public String editorUpload(@RequestParam("editormd-image-file")MultipartFile file,Model model,HttpServletRequest request) throws IOException{
			LoginMessage loginMessage =CookieTool.unlockCookie(request);
			if (loginMessage!=null) {
				ResponseMessage message = FileUploadTools.uploadimg(file, request,1200,loginMessage.getId());
				if (message.getState()==10000) {
					FileMapping mapping = new Gson().fromJson(message.getMessage(), FileMapping.class);
					baseDao.save(mapping);
					return "{\"success\":1,\"message\":\"上传成功\",\"url\":\""+mapping.getUrl()+ "\"}";
				}
				 return "{\"success\":0,\"message\":\""+message.getMessage()+"\"}";
			}else {
				 return "{\"success\":0,\"message\":\"上传失败，请先登陆\"}";
			}
			
	}
}
