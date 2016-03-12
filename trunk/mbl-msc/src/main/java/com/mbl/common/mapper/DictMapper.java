package com.mbl.common.mapper;

import java.util.List;

import com.mbl.common.bean.DictHead;
import com.mbl.common.bean.DictLine;
import com.mbl.common.framework.mapper.BaseMapper;

public interface DictMapper extends BaseMapper<DictHead>{

	  
	/****
	  * 字典信息
	  * @param map
	  * @return
	  */
	List<DictLine> findListByHeadCode(String headCode);
	 
}