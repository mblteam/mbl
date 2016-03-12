package com.mbl.rest.coupon.ctrl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mbl.common.framework.vo.datagrid.ResultVO;
import com.mbl.common.util.BaseFunction;
import com.mbl.rest.coupon.biz.UserCouponBiz;
import com.mbl.rest.coupon.vo.UserCouponRequestVo;
import com.mbl.rest.coupon.vo.UserCouponVo;

/**
 * 优惠券接口
 * 
 * @author xjs
 * @create 2015年12月05日 上午2:25:29
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping(value = "/rest/coupon")
public class UserCouponCtrl {
	
	@Resource
	UserCouponBiz userCouponBiz;
	
	/**
	 * 日志输出类
	 */
	private static final Logger LOGGER = Logger.getLogger(UserCouponCtrl.class);
	
	/**
	 * 用户优惠券查询
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/searchUserCouponList",method=RequestMethod.POST)
	@ResponseBody
	public ResultVO searchUserCouponList(@RequestBody UserCouponRequestVo requestVo) {
		ResultVO resultVO=new ResultVO();
		
		LOGGER.info("用户优惠券查询");
		
		try {
			List resultList = new ArrayList();
			List<UserCouponVo> userCouponList=userCouponBiz.searchUserCouponList(requestVo.getUserId());
			for (UserCouponVo ucv : userCouponList) {
				resultList.add(BaseFunction.convertBean(ucv));
			}
			resultVO.setErrorCode(ResultVO.SUCCESS);
			resultVO.setResult(resultList);
		}  catch (Exception e) {
			LOGGER.error("e:",e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
}
