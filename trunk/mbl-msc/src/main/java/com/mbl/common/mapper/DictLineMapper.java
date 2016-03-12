package com.mbl.common.mapper;

import java.util.List;

import com.mbl.common.bean.DictLine;
import com.mbl.common.framework.mapper.BaseMapper;

public interface DictLineMapper extends BaseMapper<DictLine>{

	  
	/****
	  * 字典头id获取字典行信息
	  * @param map
	  * @return
	  */
	List<DictLine> findListByHeadId(String headId);
	
	/**
	 *  根据字典头id获取字典行条数
	 * @param dictLineId
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	Long getCountByDictHeadId(String dictHeadId);
	 
}