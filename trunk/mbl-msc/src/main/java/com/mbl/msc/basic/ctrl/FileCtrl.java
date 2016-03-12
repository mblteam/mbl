package com.mbl.msc.basic.ctrl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mbl.common.util.UploadUtil;
import com.mbl.msc.basic.biz.CarTypeBiz;

/**
 * 文件上传类
 * @author zl
 * @create 2015年12月10日 下午11:43:33 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
@RequestMapping("/basic/upload")
public class FileCtrl {
	
	private static final Logger LOGGER = Logger.getLogger(FileCtrl.class);
	
	@Resource
	private CarTypeBiz carTypeBiz;

	/**
	 * 单文件上传
	 * @param myfile
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public void uploadFile(@RequestParam MultipartFile myfile,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		boolean tag = true;
		String showPath = "error";
		try{
			showPath = UploadUtil.uploadFile(myfile, 
				UploadUtil.getFileCategory(request.getParameter("FILE_CATE")), 
				UploadUtil.getFileCatePath(request.getParameter("FILE_PATH")));
		} catch (Exception e) {
			LOGGER.error("e:",e);
			tag = false;
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
	    if (tag == true) {  
	        out.println("<script>parent.callbackFilePath('" + (showPath.replace("'", "\\'")).replace("\\", "/") + "')</script>");  
	    } else {  
	        out.println("<script>parent.callbackFilePath('error')</script>");  
	    }  
	}
}
