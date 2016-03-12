package com.mbl.rest.upload.ctrl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mbl.common.bean.UserCar;
import com.mbl.common.bean.UserCoupon;
import com.mbl.common.framework.vo.datagrid.ResultVO;
import com.mbl.rest.car.ctrl.UserCarCtrl;
import com.mbl.rest.car.vo.UserCarRequestVo;
import com.mbl.rest.coupon.biz.UserCouponBiz;
import com.mbl.rest.coupon.vo.UserCouponRequestVo;
import com.mbl.rest.upload.biz.UploadBiz;

/**
 * 图片上传接口
 * 
 * @author xjs
 * @create 2015年12月05日 上午2:25:29
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping(value = "/rest/img")
public class UploadCtrl {
	
	@Resource
	UploadBiz uploadBiz;
	
	/**
	 * 日志输出类
	 */
	private static final Logger LOGGER = Logger.getLogger(UploadCtrl.class);
	
	/**
	 * 用户优惠券查询
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/upload",method=RequestMethod.POST)  
	@ResponseBody
	public ResultVO upload(@RequestParam(value = "file", required = false) MultipartFile file) {
		ResultVO resultVO=new ResultVO();
		
		LOGGER.info("图片上传");
		
		try {
			//图片上传
			String path=uploadBiz.upload(file);
			resultVO.setErrorCode(ResultVO.SUCCESS);
			resultVO.setResult(path);
		}  catch (Exception e) {
			LOGGER.error("e:",e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
}
