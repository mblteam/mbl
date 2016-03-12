package com.mbl.common.mapper;

import java.util.List;

import com.mbl.common.bean.CarSeries;
import com.mbl.common.framework.mapper.BaseMapper;

/**
 * 系列数据访问类
 * @author zl
 * @create 2015年12月10日 上午12:18:34 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface CarSeriesMapper extends BaseMapper<CarSeries> {

	/**
	 * 批量更新车系
	 * 功能详细描述
	 * @param carSeriesList
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	void batchUpdateCarSeries(List<CarSeries> carSeriesList);

	/**
	 * 批量新增车系
	 * 功能详细描述
	 * @param saveList
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	void batchSaveCarSeries(List<CarSeries> saveList);

	void delByCbId(String cbId);

}
