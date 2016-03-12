package com.mbl.msc.demo.biz;

import java.util.List;
import java.util.Map;

import com.mbl.msc.demo.bean.Test;

/**
 * demo业务逻辑接口
 * @author zl
 * @create 2015年10月28日 下午11:52:43 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface DemoBiz {
	
	/**
	 * demo测试
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	List<Test> test();
	
	/**
	 * demo测试
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	List<Test> queryTest(Map<String,Object> query,Integer page,Integer pageSize);

	Long getTestSum(Map<String, Object> query);
}
