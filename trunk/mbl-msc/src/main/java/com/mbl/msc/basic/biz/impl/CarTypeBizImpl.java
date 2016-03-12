package com.mbl.msc.basic.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbl.common.bean.CarBrand;
import com.mbl.common.bean.CarSeries;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.framework.page.RowBounsUtil;
import com.mbl.common.mapper.CarBrandMapper;
import com.mbl.common.mapper.CarSeriesMapper;
import com.mbl.common.vo.CarTypeVO;
import com.mbl.msc.basic.biz.CarTypeBiz;

/**
 * 车品牌业务逻辑类
 * @author zl
 * @create 2015年12月10日 上午12:22:01 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service(value = "carTypeBiz")
@Transactional
public class CarTypeBizImpl implements CarTypeBiz {
	@Resource
	private CarBrandMapper carBrandMapper;
	@Resource
	private CarSeriesMapper carSeriesMapper;
	
	public CarBrand getById(String id){
		return carBrandMapper.getById(id);
	}
	
	public Long countCarTypeList(Map<String, Object> query) {
		return carBrandMapper.getCountByParams(query);
	}
	
	public List<CarBrand> findCarTypeList(Map<String, Object> query,Integer page,Integer pageSize) {
		return carBrandMapper.findListByParams(query, RowBounsUtil.getRowBounds(page, pageSize));
	}
	
	/***
	 * 保存品牌
	 * @param carTypeVo
	 * @param carType
	 * @throws BizException
	 */
	public CarBrand saveCarType(CarBrand carTypeVo) throws BizException {
		try {
			CarBrand carType = new CarBrand();
			BeanUtils.copyProperties(carTypeVo, carType);
			if(StringUtils.isEmpty(carTypeVo.getCbId())){
				carType.setCbId(UUID.randomUUID().toString());
				carBrandMapper.save(carType);
			} else {
				carBrandMapper.update(carType);
			}
			return carType;
		} catch (Exception e) {
			throw new BizException(e);
		}
	}
	
	/***
	 * 删除品牌
	 * @param carTypeVo
	 * @throws BizException
	 */
	public void deleteCarType(String id) throws BizException {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("cbId", id);
		Long seriesCount = carSeriesMapper.getCountByParams(params);
		if(seriesCount>0){
			throw new BizException("-1","只有清空车系方能删除");
		}else{
			carBrandMapper.delById(id);
		}
	}
	
	/**
	 * 根据条件查询车系 
	 * @param params
	 * @return  
	 * @see com.mbl.msc.basic.biz.CarTypeBiz#findCarSeriesByParams(java.util.Map)
	 */
	@Override
	public List<CarSeries> findCarSeriesByParams(Map<String, Object> params) {
		return carSeriesMapper.findListByParams(params);
	}
	
	/**
	 * 保存修改车系
	 * @param carTypeVO
	 * @return  
	 * @see com.mbl.msc.basic.biz.CarTypeBiz#mergeCarSeriesWithCbId(com.mbl.common.vo.CarTypeVO)
	 */
	@Override
	public void mergeCarSeriesWithCbId(CarTypeVO carTypeVO) {
		//先删除
		carSeriesMapper.delByCbId(carTypeVO.getCbId());
		for (CarSeries cs : carTypeVO.getCarSeriesList()) {
			cs.setCbId(carTypeVO.getCbId());
			cs.setCsId(StringUtils.isNotEmpty(cs.getCsId())?cs.getCsId():UUID.randomUUID().toString());
		}
		if(CollectionUtils.isNotEmpty(carTypeVO.getCarSeriesList())){
			//后保存
			carSeriesMapper.batchSaveCarSeries(carTypeVO.getCarSeriesList());
		}
	}
}
