package com.mbl.rest.car.biz;

import java.util.List;

import com.mbl.common.bean.CarBrand;
import com.mbl.common.bean.CarSeries;
import com.mbl.common.bean.UserCar;
import com.mbl.common.vo.CarTypeVO;
import com.mbl.rest.car.vo.UserCarRequestVo;

public interface UserCarBiz {
	//新增用户车辆信息
	void saveUserCar(UserCar userCar);
	
	//查询用户车辆信息
	List<UserCar> searchUserCar(String accountId);
	
	//修改用户车辆信息
	void updateUserCar(UserCarRequestVo requestVo);
	
	//删除用户车辆信息
	void deleteUserCar(String carId) ;

	//查询车辆品牌信息
	List<CarBrand> searchCarBrand();

	//查询车辆型号信息
	List<CarSeries> searchCarSeries(String cbId);

	//查询品牌及车系
	List<CarTypeVO> searchCarType();
}
