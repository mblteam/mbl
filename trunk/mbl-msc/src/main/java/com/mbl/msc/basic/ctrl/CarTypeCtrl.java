package com.mbl.msc.basic.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mbl.common.bean.CarBrand;
import com.mbl.common.bean.CarSeries;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.framework.vo.PageVO;
import com.mbl.common.framework.vo.datagrid.ResultVO;
import com.mbl.common.util.FileCatePath;
import com.mbl.common.util.UploadUtil;
import com.mbl.common.vo.CarTypeVO;
import com.mbl.msc.basic.biz.CarTypeBiz;

/**
 * 品牌控制类
 * @author zl
 * @create 2015年12月9日 下午11:59:28 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping(value = "/basic/carType")
public class CarTypeCtrl {

	/**
	 * 日志输出类
	 */
	private static final Logger LOGGER = Logger.getLogger(CarTypeCtrl.class);

	@Resource
	private CarTypeBiz carTypeBiz;

	/**
	 * 通过id查询汽车品牌
	 * 功能详细描述
	 * @param id
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping(value = "/getCarTypeById")
	public ResultVO getCarTypeById(@RequestParam("id") String id) {
		ResultVO resultVO = new ResultVO();
		LOGGER.info("删除品牌");
		try {
			if (StringUtils.isNotBlank(id)) {
				CarBrand role = carTypeBiz.getById(id);
				resultVO.setErrorCode(ResultVO.SUCCESS);
				resultVO.setResult(role);
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
	 * 查询品牌列表
	 * 
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/findCarTypeList")
	public PageVO<CarBrand> findCarTypeList(PageVO<CarBrand> page, @RequestBody(required = false) Map<String, Object> query) {
		try {
			page.setSearch("md", "basic");
			page.setSearch("use", "carType");
			page.setSearch("opt", "findCarTypeList");
			if (null == query) {
				query = new HashMap<String, Object>();
			}
			if (null != query.get("pageSize") && StringUtils.isNotEmpty(query.get("pageSize").toString())) {
				page.setPageSize(Integer.parseInt(query.get("pageSize").toString()));
			}
			page.setQuery(query);
			page.setTotalSize(carTypeBiz.countCarTypeList(query));
			List<CarBrand> testList = carTypeBiz.findCarTypeList(query, page.getPage(), page.getPageSize());
			page.setData(testList);
			return page;
		} catch (Exception e) {
			LOGGER.error("e:", e);
			return null;
		}
	}

	/**
	 * 删除品牌
	 * 功能详细描述
	 * @param id
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping(value = "/deleteCarType",method = RequestMethod.DELETE)
	public ResultVO deleteCarType(String id) {
		ResultVO resultVO = new ResultVO();
		LOGGER.info("删除品牌");
		try {
			if (id != null&&StringUtils.isNotEmpty(id)) {
				carTypeBiz.deleteCarType(id);
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
	 * 新增或修改品牌
	 * 
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdateCarType")
	public ResultVO saveOrUpdateCarType(@RequestBody CarBrand roleVo) {
		ResultVO resultVO = new ResultVO();
		LOGGER.info("新增或修改品牌");
		try {
			if (roleVo != null) {
				CarBrand role = carTypeBiz.saveCarType(roleVo);
				resultVO.setErrorCode(ResultVO.SUCCESS);
				resultVO.setResult(role);
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
	 * 通过品牌id查询车系
	 * 功能详细描述
	 * @param id
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping(value = "/mergeCarSeriesWithCbId")
	public ResultVO mergeCarSeriesWithCbId(@RequestBody CarTypeVO carTypeVO) {
		ResultVO resultVO = new ResultVO();
		try {
			LOGGER.info("保存车系");
			if (null!=carTypeVO&&null!=carTypeVO.getCbId()) {
				carTypeBiz.mergeCarSeriesWithCbId(carTypeVO);
				resultVO.setErrorCode(ResultVO.SUCCESS);
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
	 * 通过品牌id查询车系
	 * 功能详细描述
	 * @param id
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping(value = "/findCarSeriesByCbId")
	public ResultVO findCarSeriesByCbId(@RequestParam("cbId") String cbId) {
		ResultVO resultVO = new ResultVO();
		try {
			LOGGER.info("通过品牌id查询车系");
			if (cbId != null) {
				Map<String,Object> params = new HashMap<String,Object>();
				params.put("cbId", cbId);
				List<CarSeries> role = carTypeBiz.findCarSeriesByParams(params);
				resultVO.setErrorCode(ResultVO.SUCCESS);
				resultVO.setResult(role);
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
	 * 单文件上传
	 * @param myfile
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping(value = "/uploadCarType", method = RequestMethod.POST)
	public void uploadFile(@RequestParam MultipartFile myfile,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String showImg = "error";
		boolean tag = true;
		try {
			showImg = UploadUtil.uploadImg(myfile, FileCatePath.carType);
		} catch (Exception e) {
			LOGGER.error("e:",e);
			tag = false;
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
	    if (tag == true) {  
	        out.println("<script>parent.callbackFilePath('" + ((showImg).replace("'", "\\'")).replace("\\", "/") + "')</script>");  
	    } else {  
	        out.println("<script>parent.callbackFilePath('error')</script>");  
	    }  
	}
}
