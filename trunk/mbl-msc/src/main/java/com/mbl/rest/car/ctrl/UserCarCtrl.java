package com.mbl.rest.car.ctrl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mbl.common.bean.CarBrand;
import com.mbl.common.bean.CarSeries;
import com.mbl.common.bean.UserCar;
import com.mbl.common.framework.vo.datagrid.ResultVO;
import com.mbl.common.util.BaseFunction;
import com.mbl.common.vo.CarTypeVO;
import com.mbl.rest.car.biz.UserCarBiz;
import com.mbl.rest.car.vo.UserCarRequestVo;
import com.mbl.rest.coupon.vo.UserCouponVo;

/**
 * 用户车辆信息
 * @author xjs
 * @create 2015年12月03日 上午2:25:29 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping(value = "/rest/car")
public class UserCarCtrl {

	@Resource
	UserCarBiz userCarBiz;
	
	/**
	 * 日志输出类
	 */
	private static final Logger LOGGER = Logger.getLogger(UserCarCtrl.class);
	
	/**
	 * 新增用户车辆信息
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/saveUserCar",method=RequestMethod.POST)
	@ResponseBody
	public ResultVO saveUserCar(@RequestBody UserCarRequestVo requestVo) {
		ResultVO resultVO=new ResultVO();
		
		LOGGER.info("用户车辆新增");
		
		try {
			userCarBiz.saveUserCar(requestVo.getUserCar());
			resultVO.setErrorCode(ResultVO.SUCCESS);
			resultVO.setErrorMsg("保存成功!");
		}  catch (Exception e) {
			LOGGER.error("e:",e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	/**
	 * 查询用户车辆信息
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/searchUserCarList",method=RequestMethod.POST)
	@ResponseBody
	public ResultVO searchUserCarList(@RequestBody UserCarRequestVo requestVo) {
		ResultVO resultVO=new ResultVO();
		
		LOGGER.info("用户车辆查询");
		
		try {
			List resultList = new ArrayList();
			List<UserCar> userCarList=userCarBiz.searchUserCar(requestVo.getAccountId());
			for (UserCar uc : userCarList) {
				resultList.add(BaseFunction.convertBean(uc));
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
	
	/**
	 * 修改用户车辆信息
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/updateUserCar",method=RequestMethod.POST)
	@ResponseBody
	public ResultVO updateUserCar(@RequestBody UserCarRequestVo requestVo) {
		ResultVO resultVO=new ResultVO();
		
		LOGGER.info("用户车辆修改");
		
		try {
			userCarBiz.updateUserCar(requestVo);
			resultVO.setErrorCode(ResultVO.SUCCESS);
			resultVO.setErrorMsg("修改成功!");
		}  catch (Exception e) {
			LOGGER.error("e:",e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	/**
	 * 删除用户车辆信息
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/deleteUserCar",method=RequestMethod.POST)
	@ResponseBody
	public ResultVO deleteUserCar(@RequestBody UserCarRequestVo requestVo) {
		ResultVO resultVO=new ResultVO();
		
		LOGGER.info("用户车辆删除");
		
		try {
			userCarBiz.deleteUserCar(requestVo.getCarId());
			resultVO.setErrorCode(ResultVO.SUCCESS);
			resultVO.setErrorMsg("删除成功!");
		}  catch (Exception e) {
			LOGGER.error("e:",e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	/**
	 * 查询车辆品牌信息
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/searchCarBrand",method=RequestMethod.POST)
	@ResponseBody
	public ResultVO searchCarBrand() {
		ResultVO resultVO=new ResultVO();
		
		LOGGER.info("车辆品牌查询");
		
		try {
			List<CarBrand> carBrandList=userCarBiz.searchCarBrand();
			resultVO.setErrorCode(ResultVO.SUCCESS);
			resultVO.setResult(carBrandList);
		}  catch (Exception e) {
			LOGGER.error("e:",e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	
	/**
	 * 查询车辆型号信息
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/searchCarSeries",method=RequestMethod.POST)
	@ResponseBody
	public ResultVO searchCarSeries(@RequestBody UserCarRequestVo requestVo) {
		ResultVO resultVO=new ResultVO();
		
		LOGGER.info("车辆品牌查询");
		
		try {
			List<CarSeries> carSeriesList=userCarBiz.searchCarSeries(requestVo.getCbId());
			resultVO.setErrorCode(ResultVO.SUCCESS);
			resultVO.setResult(carSeriesList);
		}  catch (Exception e) {
			LOGGER.error("e:",e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	/**
	 * 查询车辆品牌及车系信息
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/searchCarType",method=RequestMethod.POST)
	@ResponseBody
	public ResultVO searchCarType() {
		ResultVO resultVO=new ResultVO();
		
		LOGGER.info("车辆品牌及车系查询");
		
		try {
			List<CarTypeVO> carBrandList=userCarBiz.searchCarType();
			resultVO.setErrorCode(ResultVO.SUCCESS);
			resultVO.setResult(carBrandList);
		}  catch (Exception e) {
			LOGGER.error("e:",e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
}
