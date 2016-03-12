package com.mbl.msc.auth.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbl.common.bean.Menu;
import com.mbl.common.framework.vo.datagrid.ResultVO;
import com.mbl.common.vo.RoleMenuVO;
import com.mbl.common.vo.TreeNode;
import com.mbl.msc.auth.biz.AuthRoleMenuBiz;

/**
 * 角色菜单控制类
 * @author zl
 * @create 2015年12月2日 上午10:43:21 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping(value = "/auth/roleMenu")
public class AuthRoleMenuCtrl {
	
	/**
	 * 日志输出类
	 */
	private static final Logger LOGGER = Logger.getLogger(AuthRoleCtrl.class);

	@Resource
	private AuthRoleMenuBiz authRoleMenuBiz;
	
	/***
	 * 查询列表
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/findRoleMenuList")
	public ResultVO findRoleMenuList(@RequestBody Map<String, Object> query) {
		ResultVO resultVO = new ResultVO();
		LOGGER.info("查询角色");
		if(query == null) {
			query = new HashMap<String, Object>();
		}
		try {
			List<RoleMenuVO> list = authRoleMenuBiz.findRoleMenuList(query);
			resultVO.setErrorCode(ResultVO.SUCCESS);
			resultVO.setResult(list);
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
			return resultVO;
		}
		return resultVO;
	}
	
	
	/**
	 * 设置菜单信息
	 * 功能详细描述
	 * @param postObj
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping(value = "/batchSaveRoleMenu")
	public ResultVO batchSaveRoleMenu(@RequestBody Map<String,Object> postObj) {
		ResultVO resultVO = new ResultVO();
		try {
			LOGGER.info("新增角色菜单关系");
			String roleId = null;
			if(null==postObj){
				resultVO.setErrorCode(ResultVO.FAIL);
				resultVO.setErrorMsg("参数不能为空");
				return resultVO; 
			}
			if(null!=postObj.get("roleId")&&StringUtils.isEmpty(roleId=postObj.get("roleId").toString())) {
				resultVO.setErrorCode(ResultVO.FAIL);
				resultVO.setErrorMsg("角色不能为空");
				return resultVO; 
			}
			if(null!=postObj.get("menuList")){
				List<Map<String,Object>> menuVoList = (List<Map<String,Object>>) postObj.get("menuList");
				List<Menu> menuList = new ArrayList<Menu>();
				for (Map<String,Object> menuVo : menuVoList) {
					Menu menu = new Menu();
					menu.setMenuId(menuVo.get("menuId").toString());
					menuList.add(menu);
				}
				authRoleMenuBiz.batchSaveRoleMenu(roleId, menuList);
				resultVO.setErrorCode(ResultVO.SUCCESS);
			}else{
				resultVO.setErrorCode(ResultVO.FAIL);
				resultVO.setErrorMsg("菜单为空");
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
	 * 获取角色菜单信息
	 * 功能详细描述
	 * @param postObj
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping(value = "/findRoleMenuTreeByRoleId")
	public List<TreeNode> findRoleMenuByRoleId(@RequestBody Map<String,Object> postObj) {
		List<TreeNode> treeNodeList = new ArrayList<TreeNode>();
		try {
			String roleId = null;
			if(null!=postObj&&null!=postObj.get("roleId")&&StringUtils.isNotEmpty(roleId=postObj.get("roleId").toString())) {
				return authRoleMenuBiz.findRoleMenuTreeByRoleId(roleId);
			}
		} catch (Exception e) {
			return treeNodeList;
		}
		return treeNodeList;
	}
	
	/**
	 * 获取角色菜单信息
	 * 功能详细描述
	 * @param postObj
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping(value = "/getRoleMenuTreeByRoleId")
	public ResultVO getRoleMenuByRoleId(@RequestBody Map<String,Object> postObj) {
		ResultVO rvo = new ResultVO();
		try {
			List<TreeNode> treeNodeList = new ArrayList<TreeNode>();
			String roleId = null;
			if(null!=postObj&&null!=postObj.get("roleId")&&StringUtils.isNotEmpty(roleId=postObj.get("roleId").toString())) {
				treeNodeList = authRoleMenuBiz.findRoleMenuTreeByRoleId(roleId);
			}
			rvo.setResult(treeNodeList);
			rvo.setErrorCode(ResultVO.FAIL);
		} catch (Exception e) {
			rvo.setErrorMsg(e.getMessage());
			rvo.setErrorCode(ResultVO.FAIL);
		}
		return rvo;
	}
}
