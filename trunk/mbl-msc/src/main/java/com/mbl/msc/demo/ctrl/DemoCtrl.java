package com.mbl.msc.demo.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mbl.common.framework.vo.PageVO;
import com.mbl.common.framework.vo.datagrid.ResultVO;
import com.mbl.msc.demo.bean.Test;
import com.mbl.msc.demo.biz.DemoBiz;

/**
 * demo示例
 * 功能详细描述
 * @author zl
 * @create 2015年10月28日 下午11:10:52 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping(value = "/demo/info")
public class DemoCtrl {
	
	private static final Logger LOGGER = Logger.getLogger(DemoCtrl.class);
	
	@Resource
	private DemoBiz demoBiz;

	/**
	 * 测试
	 * @param id
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ResultVO test() {

		ResultVO rvo = new ResultVO();

		try {
			LOGGER.info("demo测试");
			rvo.setResult(demoBiz.test());
			rvo.setErrorCode(ResultVO.SUCCESS);
			return rvo;
		} catch (Exception e) {
			LOGGER.error("demo测试失败:", e);
			rvo.setErrorCode(ResultVO.FAIL);
			rvo.setErrorMsg(e.getMessage());
			return rvo;
		}
	}
	
	@RequestMapping(value = "/list")
	public PageVO<Test> listCustomerInfo(
			PageVO<Test> page,
			@RequestBody(required = false) Map<String, Object> query) {
		try {
			page.setSearch("md", "demo");
			page.setSearch("use", "info");
			page.setSearch("opt", "list");
			if(null==query){
				query = new HashMap<String,Object>();
			}
			if(null!=query.get("pageSize")&&StringUtils.isNotEmpty(query.get("pageSize").toString())){
				page.setSize(Integer.parseInt(query.get("pageSize").toString()));
			}
			page.setQuery(query);
			page.setTotalSize(demoBiz.getTestSum(query));
			List<Test> testList = demoBiz.queryTest(query, page.getPage(), page.getSize());
			page.setData(testList);
			return page;
		} catch (Exception e) {
			LOGGER.error("e:",e);
			return null;
		}
	}
}
