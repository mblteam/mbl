package com.mbl.msc.demo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.mbl.msc.demo.bean.Test;

public interface TestMapper {
	
	public List<Test> findTestList(RowBounds rowBounds);
	
	public Long getTestSum(Map<String,Object> param);
}
