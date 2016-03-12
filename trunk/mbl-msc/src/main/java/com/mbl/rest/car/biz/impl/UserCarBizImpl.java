package com.mbl.rest.car.biz.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbl.common.bean.CarBrand;
import com.mbl.common.bean.CarSeries;
import com.mbl.common.bean.UserCar;
import com.mbl.common.constant.ConstantClass;
import com.mbl.common.mapper.CarBrandMapper;
import com.mbl.common.mapper.CarSeriesMapper;
import com.mbl.common.mapper.UserCarMapper;
import com.mbl.common.vo.CarTypeVO;
import com.mbl.rest.car.biz.UserCarBiz;
import com.mbl.rest.car.vo.UserCarRequestVo;

/**
 * 用户车辆信息
 * @author xjs
 * @create 2015年12月02日 下午1:12:04 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service(value = "userCarBiz")
@Transactional
public class UserCarBizImpl implements UserCarBiz{

	@Resource
	UserCarMapper userCarMapper;
	
	@Resource
	CarBrandMapper carBrandMapper;
	
	@Resource
	CarSeriesMapper carSeriesMapper;
	
	//失效状态
	public final static String INVALIDE_STATUS="0";
	
	/**
	 * 新增用户车辆信息
	 * @author xjs
	 * @create 2015年12月03日 上午2:25:29 
	 */
	@Override
	public void saveUserCar(UserCar userCar) {
		userCar.setCreationDate(new Timestamp(new Date().getTime()));
		userCar.setLastUpdateDate(new Timestamp(new Date().getTime()));
		userCar.setCarId(UUID.randomUUID().toString());
		userCar.setCarStatus(ConstantClass.ABLE);
		
		userCarMapper.save(userCar);
	}

	/**
	 * 查询用户车辆信息
	 * @author xjs
	 * @create 2015年12月03日 上午2:25:29 
	 */
	@Override
	public List<UserCar> searchUserCar(String accountId) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("accountId", accountId);
		List<UserCar> userCar=userCarMapper.findListByParams(map);
		return userCar;
	}

	/**
	 * 修改用户车辆信息
	 * @author xjs
	 * @create 2015年12月03日 上午2:25:29 
	 */
	@Override
	public void updateUserCar(UserCarRequestVo requestVo) {
		UserCar userCar=requestVo.getUserCar();
		userCarMapper.update(userCar);
	}

	/**
	 * 删除用户车辆信息
	 * @author xjs
	 * @create 2015年12月03日 上午2:25:29 
	 */
	public void deleteUserCar(String carId) {
		UserCar userCar=new UserCar();
		userCar.setCarId(carId);
		userCar.setCarStatus(INVALIDE_STATUS);
		userCarMapper.update(userCar);
	}

	/**
	 * 查询车辆品牌信息
	 */
	@Override
	public List<CarBrand> searchCarBrand() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", ConstantClass.ABLE);
		return carBrandMapper.findListByParams(map);
	}

	/**
	 * 查询车辆型号信息
	 */
	@Override
	public List<CarSeries> searchCarSeries(String cbId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cbId", cbId);
		return carSeriesMapper.findListByParams(map);
	}
	
	/**
	 * 车牌及车系查询
	 * @return  
	 * @see com.mbl.rest.car.biz.UserCarBiz#searchCarType()
	 */
	@Override
	public List<CarTypeVO> searchCarType() {
		List<CarBrand> cbList = carBrandMapper.findListByParams(null);
		CarTypeVO ctVO = null;
		Map<String,Object> map = null;
		List<CarTypeVO> ctVOList = new ArrayList<CarTypeVO>();
		for (CarBrand carBrand : cbList) {
			ctVO = new CarTypeVO();
			BeanUtils.copyProperties(carBrand, ctVO);
			map = new HashMap<String,Object>();
			map.put("cbId", carBrand.getCbId());
			ctVO.setCarSeriesList(carSeriesMapper.findListByParams(map));
			ctVOList.add(ctVO);
		}
		return ctVOList;
	}
}
