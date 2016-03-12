package com.mbl.msc.basic.biz;

import java.util.List;
import java.util.Map;

import com.mbl.common.bean.CarBrand;
import com.mbl.common.bean.CarSeries;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.vo.CarTypeVO;

/**
 * 车品牌业务逻辑类
 * @author zl
 * @create 2015年12月10日 上午12:22:30 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface CarTypeBiz {
	
	/***
	 * 根据id查品牌
	 * @param id
	 * @return
	 */
	public CarBrand getById(String id);
	/***
	 * 统计总数
	 * @param query
	 * @return
	 */
	public Long countCarTypeList(Map<String, Object> query) ;
	
	/***
	 * 保存品牌
	 * 
	 * @param carTypeVo
	 * @throws BizException
	 */
	public CarBrand saveCarType(CarBrand carTypeVo) throws BizException;

	/***
	 * 删除品牌
	 * 
	 * @param carTypeVo
	 * @throws BizException
	 */
	public void deleteCarType(String id) throws BizException;

	/****
	 * 查询
	 * @param query
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<CarBrand> findCarTypeList(Map<String, Object> query,Integer page,Integer pageSize);
	
	/**
	 * 根据条件查询车系
	 * 功能详细描述
	 * @param params
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public List<CarSeries> findCarSeriesByParams(Map<String, Object> params);
	
	/**
	 * 保存修改车系
	 * 功能详细描述
	 * @param carTypeVO
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public void mergeCarSeriesWithCbId(CarTypeVO carTypeVO);
}
