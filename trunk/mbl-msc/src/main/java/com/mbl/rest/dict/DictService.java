package com.mbl.rest.dict;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbl.common.bean.DictLine;
import com.mbl.common.mapper.DictMapper;
/**
 * 字典业务层
 *〈一句话功能简述〉
 * 功能详细描述
 * @author fangxiaowei
 * @create 2015年12月13日 下午5:20:57 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service(value = "dictService")
@Transactional
public class DictService {

	@Resource
	DictMapper dictMapper;
	
	/**
	 * 根据headCode获取字典map
	 * @param headCode
	 * @return
	 */
	public Map<String,String> findDictMapByHeadCode(String headCode){
		List<DictLine> lineList = dictMapper.findListByHeadCode(headCode);
		
		Map<String,String> map = new HashMap<String, String>();
		if(lineList!=null && !lineList.isEmpty()){
			for(DictLine dictLine:lineList){
				map.put(dictLine.getDictCode(), dictLine.getDictValue());
			}
		}
		
		return map;
	}
	
	/**
	 * 根据headCode获取字典list
	 * @param headCode
	 * @return
	 */
	public List<DictLine> findDictListByHeadCode(String headCode){
		return dictMapper.findListByHeadCode(headCode);
	}
}
