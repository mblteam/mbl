package com.mbl.msc.plat.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbl.common.framework.vo.PageVO;
import com.mbl.common.framework.vo.datagrid.ResultVO;
import com.mbl.common.vo.PackageVO;
import com.mbl.msc.plat.biz.PkgBiz;

/**
 * 套餐类
 * 
 * @author jj
 * @create 2015年12月12日 下午11:43:33
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping("/basic/pkg")
public class PkgCtrl {
	/**
	 * 日志输出类
	 */
	private static final Logger LOGGER = Logger.getLogger(PkgCtrl.class);
	@Resource
	private PkgBiz pkgBiz;

	
	/****
	 * 删除套餐，软删除
	 * @param list
	 * @return
	 */
	@RequestMapping(value = "/delPackageById")
	public ResultVO delPackageById(@RequestBody Map<String,Object> map) {
		ResultVO resultVO = new ResultVO();
		try {
			String id = (String) map.get("id");
			pkgBiz.delPackageById(id);
			resultVO.setErrorCode(ResultVO.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	/**
	 * 查询套餐列表
	 * 
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/findAllPkgList")
	public ResultVO findAllPkgList(@RequestBody(required = false) Map<String, Object> query) {
		ResultVO resultVO = new ResultVO();
		try {
			resultVO.setResult(pkgBiz.findListPageByParams(query));
			resultVO.setErrorCode(ResultVO.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}

	/**
	 * 查询套餐列表
	 * 
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/findPkgList")
	public PageVO<PackageVO> findPkgList(PageVO<PackageVO> page,
			@RequestBody(required = false) Map<String, Object> query) {
		try {
			page.setSearch("md", "basic");
			page.setSearch("use", "pkg");
			page.setSearch("opt", "findPkgList");
			if (null == query) {
				query = new HashMap<String, Object>();
			}
			/*if (null != query.get("pageSize") && StringUtils.isNotEmpty(query.get("pageSize").toString())) {
				page.setSize(Integer.parseInt(query.get("pageSize").toString()));
			}*/
			page.setQuery(query);
			page.setTotalSize(pkgBiz.getCountByParams(query));
			List<PackageVO> pkgList = pkgBiz.findPackageList(query, page.getPage(), page.getPageSize());
			page.setData(pkgList);
			return page;
		} catch (Exception e) {
			LOGGER.error("e:", e);
			return null;
		}
	}

	/****
	 * 查询套餐详情
	 * 
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/getPackageById")
	public ResultVO getPackageById(@RequestBody(required = false) Map<String, Object> query) {
		ResultVO resultVO = new ResultVO();
		try {
			PackageVO packageVO = pkgBiz.getPackageById((String) query.get("id"));

			resultVO.setErrorCode(ResultVO.SUCCESS);
			resultVO.setResult(packageVO);
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}

	/****
	 * 保存套餐信息
	 * 
	 * @param pkg
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdatePkg")
	public ResultVO saveOrUpdatePkg(@RequestBody PackageVO pkg) {
		ResultVO resultVO = new ResultVO();
		try {
			pkgBiz.savePkg(pkg);
			resultVO.setErrorCode(ResultVO.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}

}
