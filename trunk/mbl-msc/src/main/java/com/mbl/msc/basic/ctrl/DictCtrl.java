package com.mbl.msc.basic.ctrl;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mbl.common.bean.DictHead;
import com.mbl.common.bean.DictLine;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.framework.vo.PageVO;
import com.mbl.common.framework.vo.datagrid.ResultVO;
import com.mbl.msc.basic.biz.DictBiz;
import com.mbl.rest.dict.DictService;

/**
 * 字典
 *〈一句话功能简述〉
 * 功能详细描述
 * @author fangxiaowei
 * @create 2015年12月13日 下午5:20:39 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping(value = "/basic/dict")
public class DictCtrl {
	/**
	 * 日志输出类
	 */
	private static final Logger LOGGER = Logger.getLogger(DictCtrl.class);
	@Resource
	private DictService dictService;
	@Resource
	private DictBiz dictBiz;
	/**
	 * 前端字典调用公用类
	 * 功能详细描述
	 * @param cates
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping(value = "/findByCates", method = RequestMethod.GET)
	@ResponseBody
	public ResultVO findByCates(String cates) {
		
		ResultVO vo = new ResultVO();
		try {
			Map<String,List<DictLine>> dictsMap = new HashMap<String, List<DictLine>>();
			List<DictLine> dicts = null;
			if(StringUtils.isNotEmpty(cates)){
				String[] categoryArr = cates.split(",");
				for (String category : categoryArr) {
					dicts = dictService.findDictListByHeadCode(category);   
					//Collections.sort(dicts,comparator);
					dictsMap.put(category, dicts);
				}
			}
			vo.setErrorCode(ResultVO.SUCCESS);
			vo.setResult(dictsMap);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setErrorCode(e.getMessage());
			vo.setErrorCode(ResultVO.FAIL);
		}
		return vo;
	}
	
	
	//根据序列号进行排序   Collections.sort(a,comparator);
	private Comparator<DictLine> comparator = new Comparator<DictLine>() {
        public int compare(DictLine s1, DictLine s2) {
        	if(null!=s1.getDictCode()&&null!=s2.getDictValue()){
        		return Integer.valueOf(s1.getDictCode()).intValue() - Integer.valueOf(s2.getDictCode()).intValue();
        	}
            return 0;
        }
    };
    
    /**
	 * 查询字典列表
	 * 
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/findDictHeadList")
	public PageVO<DictHead> findDictHeadList(PageVO<DictHead> page, @RequestBody(required = false) Map<String, Object> query) {
		try {
			page.setSearch("md", "basic");
			page.setSearch("use", "dict");
			page.setSearch("opt", "findDictHeadList");
			if (null == query) {
				query = new HashMap<String, Object>();
			}
			if (null != query.get("pageSize") && StringUtils.isNotEmpty(query.get("pageSize").toString())) {
				page.setPageSize(Integer.parseInt(query.get("pageSize").toString()));
			}
			page.setQuery(query);
			page.setTotalSize(dictBiz.countDictHeadList(query));
			List<DictHead> testList = dictBiz.findDictList(query, page.getPage(), page.getPageSize());
			page.setData(testList);
			return page;
		} catch (Exception e) {
			LOGGER.error("e:", e);
			return null;
		}
	}
	
	/**
	 * 增加或修改字段头信息
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param dictHead
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping(value = "/saveOrUpdateDict")
	public ResultVO saveOrUpdateDict(@RequestBody DictHead dictHead) {
		ResultVO resultVO = new ResultVO();
		LOGGER.info("新增或修改字典头信息");
		try {
			if (dictHead != null) {
				DictHead dictHead2 = dictBiz.saveDictHead(dictHead);
				resultVO.setErrorCode(ResultVO.SUCCESS);
				resultVO.setResult(dictHead2);
			}
		} catch (BizException e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
			return resultVO;
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
			return resultVO;
		}
		return resultVO;
	}
	
	@RequestMapping(value = "/saveOrUpdateDictLine")
	public ResultVO saveOrUpdateDict(@RequestBody DictLine dictLine) {
		ResultVO resultVO = new ResultVO();
		LOGGER.info("新增或修改字典行信息");
		try {
			if (dictLine.getDictHeadId() != null) {
				DictLine dictLine2 = dictBiz.saveDictLine(dictLine);
				resultVO.setErrorCode(ResultVO.SUCCESS);
				resultVO.setResult(dictLine2);
			}
		} catch (BizException e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
			return resultVO;
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
			return resultVO;
		}
		return resultVO;
	}
	
	/**
	 * 删除字典
	 * @param id
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping(value = "/deleteDictHead",method = RequestMethod.DELETE)
	public ResultVO deleteDictHead(String id) {
		ResultVO resultVO = new ResultVO();
		LOGGER.info("删除字典头信息");
		try {
			if (id != null&&StringUtils.isNotEmpty(id)) {
				dictBiz.isExistDictLine(id);
				dictBiz.deleteDictHead(id);
				resultVO.setErrorCode(ResultVO.SUCCESS);
			}
		} catch (BizException e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
			return resultVO;
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
			return resultVO;
		}
		return resultVO;
	}
	
	/**
	 * 查询字典头
	 * 
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/findDictLinesByHeadId" , method=RequestMethod.GET)
	public ResultVO findDictLinesByHeadId(String id) {
		ResultVO resultVO = new ResultVO();
		LOGGER.info("根据字典头id查询字典行信息");
		try {
			if (StringUtils.isNotBlank(id)) {
				List<DictLine> dictLines = dictBiz.getDictLinesByHeadId(id);
				resultVO.setErrorCode(ResultVO.SUCCESS);
				resultVO.setResult(dictLines);
			}
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
			return resultVO;
		}
		return resultVO;
	} 
	
	/**
	 * 删除字典行信息
	 * @param id
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping(value = "/deleteDictLine",method = RequestMethod.DELETE)
	public ResultVO deleteDictLine (String id) {
		ResultVO resultVO = new ResultVO();
		LOGGER.info("删除字典头信息");
		try {
			if (id != null&&StringUtils.isNotEmpty(id)) {
				dictBiz.deleteDictLineByLineId(id);
				resultVO.setErrorCode(ResultVO.SUCCESS);
			}
		} catch (BizException e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
			return resultVO;
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
			return resultVO;
		}
		return resultVO;
	}
	
}
