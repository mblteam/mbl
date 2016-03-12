package com.mbl.msc.demo.biz.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbl.common.framework.page.RowBounsUtil;
import com.mbl.msc.demo.bean.Test;
import com.mbl.msc.demo.biz.DemoBiz;
import com.mbl.msc.demo.mapper.TestMapper;

/**
 * demo业务逻辑实现类
 * @author zl
 * @create 2015年10月28日 下午11:52:43 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service(value = "demoBiz")
@Transactional
public class DemoBizImpl implements DemoBiz {
	
	@Autowired
    private TestMapper mapper;

	public List<Test> test() {
		return mapper.findTestList(RowBounsUtil.getRowBounds(1, 2));
	}
	
	public List<Test> queryTest(Map<String,Object> query,Integer page,Integer pageSize) {
		return mapper.findTestList(RowBounsUtil.getRowBounds(page, pageSize));
	}
	
	public Long getTestSum(Map<String, Object> query) {
		return mapper.getTestSum(query);
	}

}
