package com.mbl.rest.personageinfo.ctrl;

import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mbl.common.bean.User;
import com.mbl.common.framework.vo.datagrid.ResultVO;
import com.mbl.common.util.BaseFunction;
import com.mbl.rest.coupon.vo.UserCouponRequestVo;
import com.mbl.rest.personageinfo.biz.PersonageInfoBiz;
import com.mbl.rest.personageinfo.vo.PersonageInfoRequestVo;
import com.mbl.rest.personageinfo.vo.PersonageInfoResponseVo;

/**
 * 个人信息接口
 * 
 * @author xjs
 * @create 2015年12月02日 上午2:25:29
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping(value = "/rest/personageinfo")
public class PersonageInfoCtrl {

	@Resource
	private PersonageInfoBiz personageInfoBiz;

	/**
	 * 日志输出类
	 */
	private static final Logger LOGGER = Logger.getLogger(PersonageInfoCtrl.class);
	
	/**
	 * 个人信息查询
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/searchList",method=RequestMethod.POST)
	@ResponseBody
	public ResultVO searchPersonageInfoList(@RequestBody UserCouponRequestVo requestVo) {
		ResultVO resultVO=new ResultVO();
		
		LOGGER.info("个人信息查询");
		
		try {
			PersonageInfoResponseVo responseVo=personageInfoBiz.searchPersonageInfoList(requestVo.getUserId());
			resultVO.setErrorCode(ResultVO.SUCCESS);
			resultVO.setResult(BaseFunction.convertBean(responseVo));
		}  catch (Exception e) {
			LOGGER.error("e:",e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	/**
	 * 个人信息查询
	 * @param requestVo
	 * @return
	 */
	@RequestMapping(value = "/searchPersonageInfo",method=RequestMethod.POST)
	@ResponseBody
	public ResultVO searchPersonageInfo(@RequestBody UserCouponRequestVo requestVo) {
		ResultVO resultVO=new ResultVO();
		
		LOGGER.info("个人信息查询");	
		try {
			User User=personageInfoBiz.searchPersonageInfo(requestVo.getUserId());
			resultVO.setErrorCode(ResultVO.SUCCESS);
			resultVO.setResult(BaseFunction.convertBean(User));
		}  catch (Exception e) {
			LOGGER.error("e:",e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	/**
	 * 个人信息修改
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/updatePersonageInfo",method=RequestMethod.POST)
	@ResponseBody
	public ResultVO updatePersonageInfo(@RequestBody PersonageInfoRequestVo requestVo) {
		ResultVO resultVO=new ResultVO();
		LOGGER.info("个人信息查询");
		try {
			if(null!=requestVo&&null!=requestVo.getUserId()){
				if(null!=requestVo.getUserName()&&Pattern.matches("^[\u4E00-\u9FA5A-Za-z0-9_]+$", requestVo.getUserName())){
					User user = new User();
					user.setUserId(requestVo.getUserId());
					user.setUserName(requestVo.getUserName());
					personageInfoBiz.updateUser(user);
					resultVO.setErrorCode(ResultVO.SUCCESS);
					resultVO.setResult("修改成功！");
				}else{
					resultVO.setErrorCode(ResultVO.FAIL);
					resultVO.setErrorMsg("昵称不合法");
					return resultVO;
				}
			}else{
				resultVO.setErrorCode(ResultVO.FAIL);
				resultVO.setErrorMsg("参数错误");
				return resultVO;
			}
		}  catch (Exception e) {
			LOGGER.error("e:",e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}

}
