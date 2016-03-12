package com.mbl.common.util;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传工具类
 * 功能详细描述
 * @author zl
 * @create 2015年12月12日 下午1:23:25 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class UploadUtil {
	
	//保存路径
	public static String savePath = null;
	//显示路径
	public static String showPath = null;
	
	static{
		Properties props = new Properties();
		//这里封装了读取配置文件的方法 配置文件中有图片的存放地址和获取地址
		try {
			props.load(UploadUtil.class.getResourceAsStream("/application.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        savePath = props.getProperty("saveUploadUrl");
        showPath = props.getProperty("getUploadUrl");
	}

	/**
	 * 图片上传
	 * 功能详细描述
	 * @param myfile
	 * @param cartype
	 * @return
	 * @throws IOException
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public static String uploadImg(MultipartFile myfile,FileCatePath catePath) throws IOException{
		//相对存储路径
		String relaPath = null;
		//文件名
		String fileName = null;
		relaPath = FileCategory.IMAGE.getValue()+File.separator+catePath.getValue()+File.separator;
		fileName = UUID.randomUUID().toString()+myfile.getOriginalFilename().substring(myfile.getOriginalFilename().indexOf("."));  
		File targetFile = new File(savePath+relaPath,fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        //保存  
        myfile.transferTo(targetFile);  
        return showPath+relaPath+fileName;
	}
	
	/**
	 * 文件上传
	 * 功能详细描述
	 * @param myfile
	 * @param cartype
	 * @return
	 * @throws IOException
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public static String uploadFile(MultipartFile myfile,FileCatePath catePath) throws IOException{
		//相对存储路径
		String relaPath = null;
		//文件名
		String fileName = null;
		relaPath = FileCategory.FILE.getValue()+File.separator+catePath.getValue()+File.separator;
		fileName = UUID.randomUUID().toString()+myfile.getOriginalFilename().substring(myfile.getOriginalFilename().indexOf("."));  
		File targetFile = new File(savePath+relaPath,fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        //保存  
        myfile.transferTo(targetFile);  
        return showPath+relaPath+fileName;
	}
	
	/**
	 * 文件上传
	 * 功能详细描述
	 * @param myfile
	 * @param fileCate
	 * @param cartype
	 * @return
	 * @throws IOException
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public static String uploadFile(MultipartFile myfile,FileCategory fileCate, FileCatePath catePath) throws IOException{
		//相对存储路径
		String relaPath = null;
		//文件名
		String fileName = null;
		relaPath = fileCate.getValue()+File.separator+catePath.getValue()+File.separator;
		fileName = UUID.randomUUID().toString()+myfile.getOriginalFilename().substring(myfile.getOriginalFilename().indexOf("."));  
		File targetFile = new File(savePath+relaPath,fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        //保存  
        myfile.transferTo(targetFile);  
        return showPath+relaPath+fileName;
	}
	
	public static FileCategory getFileCategory(String fileCate){
		try {
			if(StringUtils.isNotEmpty(fileCate)){
				return FileCategory.valueOf(fileCate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FileCategory.OTHER;
	}
	
	public static FileCatePath getFileCatePath(String fileCatePath){
		try {
			if(StringUtils.isNotEmpty(fileCatePath)){
				return FileCatePath.valueOf(fileCatePath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FileCatePath.Other;
	}

}
