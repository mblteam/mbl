package com.mbl.msc.basic.biz.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbl.common.bean.DictHead;
import com.mbl.common.bean.DictLine;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.framework.page.RowBounsUtil;
import com.mbl.common.mapper.DictLineMapper;
import com.mbl.common.mapper.DictMapper;
import com.mbl.msc.basic.biz.DictBiz;

/**
 * 字典业务逻辑类
 * @author fangxiaowei
 * @create 2015年12月13日 上午22:48:47 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service(value = "dictBiz")
@Transactional
public class DictBizImpl implements DictBiz {
	@Resource
	private DictMapper dictMapper;
	
	@Resource
	private DictLineMapper dictLineMapper;
	/**
	 * 获取字典头条数
	 * @param query
	 * @return  
	 * @see com.mbl.msc.basic.biz.DictBiz#countDictHeadList(java.util.Map)
	 */
	@Override
	public Long countDictHeadList(Map<String, Object> query) {
		return dictMapper.getCountByParams(query);
	}
	/**
	 * 获取字典头信息
	 * @param query
	 * @param page
	 * @param pageSize
	 * @return  
	 * @see com.mbl.msc.basic.biz.DictBiz#findDictList(java.util.Map, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<DictHead> findDictList(Map<String, Object> query,Integer page,Integer pageSize){
		return dictMapper.findListByParams(query, RowBounsUtil.getRowBounds(page, pageSize));
	}
	/**
	 * 保存字典头信息
	 * @param dictHeadVo
	 * @return
	 * @throws BizException  
	 * @see com.mbl.msc.basic.biz.DictBiz#saveDictHead(com.mbl.common.bean.DictHead)
	 */
	@Override
	public DictHead saveDictHead(DictHead dictHeadVo) throws BizException {
		try {
			DictHead dictHead = new DictHead();
			BeanUtils.copyProperties(dictHeadVo, dictHead);
			if(StringUtils.isEmpty(dictHeadVo.getDictId())){
				dictHead.setDictId(UUID.randomUUID().toString());
				dictMapper.save(dictHead);
			} else {
				dictMapper.update(dictHead);
			}
			return dictHead;
		} catch (Exception e) {
			throw new BizException(e);
		}
	}
	/**
	 * 删除字典头信息
	 * @param id
	 * @throws BizException  
	 * @see com.mbl.msc.basic.biz.DictBiz#deleteDictHead(java.lang.String)
	 */
	@Override
	public void deleteDictHead(String id) throws BizException {
		dictMapper.delById(id);
	}
	/**
	 * 获取字典
	 * @param id
	 * @return  
	 * @see com.mbl.msc.basic.biz.DictBiz#getByHeadId(java.lang.String)
	 */
	@Override
	public List<DictLine> getDictLinesByHeadId(String headId) {
		return dictLineMapper.findListByHeadId(headId);
	}
	/**
	 * 删除字典头信息是判断是否有关联字典行信息
	 * @param id
	 * @throws BizException  
	 * @see com.mbl.msc.basic.biz.DictBiz#isExistDictLine(java.lang.String)
	 */
	@Override
	public void isExistDictLine(String dictHeadId) throws BizException {
		Long count = dictLineMapper.getCountByDictHeadId(dictHeadId);
		if(count>0){
			throw new BizException("-1","请先删除该字典头信息关联的字典行信息!");
		}
	}
	/**
	 * 保存字典行信息
	 * @param dictLineVo
	 * @return
	 * @throws BizException  
	 * @see com.mbl.msc.basic.biz.DictBiz#saveDictLine(com.mbl.common.bean.DictLine)
	 */
	@Override
	public DictLine saveDictLine(DictLine dictLineVo) throws BizException {
		try {
			DictLine dictLine = new DictLine();
			BeanUtils.copyProperties(dictLineVo, dictLine);
			if(StringUtils.isEmpty(dictLineVo.getDictLineId())){
				dictLine.setDictLineId(UUID.randomUUID().toString());
				dictLineMapper.save(dictLine);
			} else {
				dictLineMapper.update(dictLine);
			}
			return dictLine;
		} catch (Exception e) {
			throw new BizException(e);
		}
	}
	
	/**
	 * 删除字典行信息
	 * @param id
	 * @throws BizException  
	 * @see com.mbl.msc.basic.biz.DictBiz#deleteDictHead(java.lang.String)
	 */
	@Override
	public void deleteDictLineByLineId(String id) throws BizException {
		dictLineMapper.delById(id);
	}
}
