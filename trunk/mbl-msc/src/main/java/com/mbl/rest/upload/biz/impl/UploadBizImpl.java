package com.mbl.rest.upload.biz.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mbl.common.bean.UserCoupon;
import com.mbl.common.framework.vo.DictType;
import com.mbl.common.mapper.DictMapper;
import com.mbl.common.util.CommonUtils;
import com.mbl.rest.dict.DictService;
import com.mbl.rest.upload.biz.UploadBiz;

/**
 * 图片上传
 * @author xjs
 * @create 2015年12月07日 下午1:12:04 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service(value = "uploadBiz")
@Transactional
public class UploadBizImpl implements UploadBiz {

	@Resource
	DictService dictService;
	
	@Override
	public String upload(MultipartFile file) throws Exception {
		
		//获取图片上传地址
		Map<String,String> map=dictService.findDictMapByHeadCode(DictType.IMG_UPLOAD_ADDRESS);
		String path = map.get("1");
        String fileName = new Date().getTime() + file.getOriginalFilename();  
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        
        //保存  
        file.transferTo(targetFile);  
		
        return targetFile.getPath();
	}

}
