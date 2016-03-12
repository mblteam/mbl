package com.mbl.rest.upload.biz;

import org.springframework.web.multipart.MultipartFile;


/**
 * 图片上传
 * @author xjs
 * @create 2015年12月05日 下午22:02:37 
 * @version 1.0.0
 */
public interface UploadBiz {

	/**
	 * 图片上传
	 * @return
	 * @throws Exception 
	 */
	String upload(MultipartFile file) throws Exception;

}
