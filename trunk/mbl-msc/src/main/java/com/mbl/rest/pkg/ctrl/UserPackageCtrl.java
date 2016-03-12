package com.mbl.rest.pkg.ctrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mbl.common.framework.vo.datagrid.ResultVO;
import com.mbl.common.util.BaseFunction;
import com.mbl.rest.personageinfo.ctrl.PersonageInfoCtrl;
import com.mbl.rest.pkg.biz.UserPackageBiz;
import com.mbl.rest.pkg.vo.UserPackageRequestVo;
import com.mbl.rest.pkg.vo.UserPackageVo;


/**
 * 用户已支付套餐
 * @author xjs
 * @create 2015年12月02日 上午2:25:29
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping(value = "/rest/userPackage")
public class UserPackageCtrl {

	@Resource
	private UserPackageBiz userPackageBiz;

	/**
	 * 日志输出类
	 */
	private static final Logger LOGGER = Logger.getLogger(UserPackageCtrl.class);

	/**
	 * 用户已支付套餐
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/searchUserPackageList",method=RequestMethod.POST)
	@ResponseBody
	public ResultVO searchUserPackageList(@RequestBody UserPackageRequestVo requestVo) {
		ResultVO resultVO=new ResultVO();
		
		LOGGER.info("用户已支付套餐");
		
		try {
			List<Map> list = new ArrayList<Map>();
			List<UserPackageVo> userPackagevo = userPackageBiz.searchUserPackageList(requestVo);
			for (UserPackageVo upv : userPackagevo) {
				list.add(BaseFunction.convertBean(upv));
			}
			resultVO.setErrorCode(ResultVO.SUCCESS);
			resultVO.setResult(list);
		}  catch (Exception e) {
			LOGGER.error("e:",e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}

}
