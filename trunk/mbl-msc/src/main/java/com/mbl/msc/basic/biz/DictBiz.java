package com.mbl.msc.basic.biz;

import java.util.List;
import java.util.Map;

import com.mbl.common.bean.DictHead;
import com.mbl.common.bean.DictHead;
import com.mbl.common.bean.DictLine;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.vo.DictVO;
/**
 * 字典业务逻辑类
 * @author fangxiaowei
 * @create 2015年12月13日 下午11:20:16 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface DictBiz {
	/**
	 * 获取字典头数据
	 * 功能详细描述
	 * @param query
	 * @param page
	 * @param pageSize
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public List<DictHead> findDictList(Map<String, Object> query,Integer page,Integer pageSize);
	
	
	/***
	 * 统计字典头总数
	 * @param query
	 * @return
	 */
	public Long countDictHeadList(Map<String, Object> query) ;

	/**
	 * 保存字典头信息
	 * @param dictHeadVo
	 * @return
	 * @throws BizException
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public DictHead saveDictHead(DictHead dictHeadVo) throws BizException;
	
	/**
	 * 删除字典头信息
	 * @param id
	 * @throws BizException
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public void deleteDictHead(String id) throws BizException;
	
	/**
	 * 根据字典头id获取字典行信息
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param id
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public List<DictLine> getDictLinesByHeadId(String id);
	
	/**
	 * 删除字典头信息是判断是否有关联字典行信息
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param id
	 * @throws BizException
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public void isExistDictLine(String id) throws BizException;
	
	/**
	 * 保存字典行信息
	 * @param dictLine
	 * @return
	 * @throws BizException
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public DictLine saveDictLine(DictLine dictLine) throws BizException;
	
	/**
	 * 删除字典行信息
	 * @param id
	 * @throws BizException
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public void deleteDictLineByLineId(String id) throws BizException;
	
}
