package com.mbl.msc.auth.ctrl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mbl.common.bean.Menu;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.framework.vo.datagrid.ResultVO;
import com.mbl.common.vo.TreeNode;
import com.mbl.msc.auth.biz.AuthMenuBiz;
import com.mbl.msc.auth.vo.MenuTreeVO;

/**
 * 菜单管理接口
 * @author zl
 * @create 2015年11月30日 上午2:25:29 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping(value = "/auth/menu")
public class AuthMenuCtrl{

	@Resource
	private AuthMenuBiz authMenuBiz;

	/**
	 * 日志输出类
	 */
	private static final Logger LOGGER = Logger
			.getLogger(AuthMenuCtrl.class);

	/**
	 * 查询结点
	 * @return
	 */
	@RequestMapping(value = "/menuTreelist")
	@ResponseBody
	public List<MenuTreeVO> listSerie(@RequestParam("id") String id) {
		try {
			return authMenuBiz.findChildById(id);
		} catch (Exception e) {
			LOGGER.error("查询菜单错误",e);
			return new ArrayList<MenuTreeVO>();
		}
	}
	
	/**
	 * 新增或修改菜单
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/updateMenu")
	public ResultVO addMenu(String type, @RequestBody Menu menuVO) {
		ResultVO resultVO=new ResultVO();
		LOGGER.info("新增菜单");
		try {
			if (menuVO != null) {
				Menu menu = authMenuBiz.addMenu(menuVO);
				resultVO.setErrorCode(ResultVO.SUCCESS);
				resultVO.setResult(menu);
			}	
		} catch (BizException e) {
			LOGGER.error("e:",e);
			ResultVO rvo = new ResultVO();
			rvo.setErrorCode(ResultVO.FAIL);
			rvo.setErrorMsg(e.getMessage());
			return rvo;
		} catch (Exception e) {
			LOGGER.error("e:",e);
			ResultVO rvo = new ResultVO();
			rvo.setErrorCode(ResultVO.FAIL);
			rvo.setErrorMsg(e.getMessage());
			return rvo;
		}
		return resultVO;
	}
	
	/**
	 * 删除结点
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delMenu")
	public ResultVO addMenu(@RequestParam("id") String id) {
		ResultVO resultVO=new ResultVO();
		try {
			LOGGER.info("删除菜单");
			if (id != null && !"".equals(id)) {
				resultVO.setErrorCode(ResultVO.SUCCESS);
				resultVO.setResult(authMenuBiz.delMenu(id));
			}	
		} catch (Exception e) {
			LOGGER.error("e:",e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	/**
	 * 单结点查询
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getMenuById")
	public ResultVO findMenuById(@RequestParam("id") String id) {
		ResultVO resultVO=new ResultVO();

		try {
			if (id != null && !"".equals(id)) {
				Menu menuVO = authMenuBiz.getMenuById(id);
				resultVO.setErrorCode(ResultVO.SUCCESS);
				resultVO.setErrorMsg("");
				resultVO.setResult(menuVO);
			}	
		} catch (Exception e) {
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
			resultVO.setResult(1);
		}
		return resultVO;
	}
	
	
	@RequestMapping(value = "/isHasRootMenu")
	public ResultVO isHasRootMenu(){
		ResultVO resultVo = new ResultVO();
		try {
			resultVo.setResult(authMenuBiz.isHasRootMenu());
			resultVo.setErrorCode(ResultVO.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("e:",e);
			resultVo.setErrorCode(ResultVO.FAIL);
			resultVo.setResult(e.getMessage());
		}
		return resultVo;
	}
	
	@RequestMapping(value = "/isExistMenu")
	@ResponseBody
	public ResultVO isExistMenuItem(@RequestBody Menu menuVO){
				
		ResultVO resultVO=new ResultVO();
		LOGGER.info("判断菜单名称是否重复");
		try {
			resultVO.setErrorCode(ResultVO.SUCCESS);
			String isExistStr = authMenuBiz.isExistMenu(menuVO);
			if(null!=isExistStr){
				resultVO.setResult(1);
				resultVO.setErrorMsg(isExistStr);
			}
		} catch (Exception e) {
			LOGGER.error("e:",e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	//加载所有菜单
	@RequestMapping(value = "/loadAllMenu")
	public List<TreeNode> loadAllMenu(){
		List<TreeNode> treeNodeList = authMenuBiz.loadAllMenu();
		return treeNodeList;
	}
}
