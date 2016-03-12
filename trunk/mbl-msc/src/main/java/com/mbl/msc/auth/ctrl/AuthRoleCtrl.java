package com.mbl.msc.auth.ctrl;

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
import org.springframework.web.bind.annotation.RestController;

import com.mbl.common.bean.Role;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.framework.vo.PageVO;
import com.mbl.common.framework.vo.datagrid.ResultVO;
import com.mbl.msc.auth.biz.AuthRoleBiz;

/**
 * 角色控制类
 * @author fangxiaowei
 * @create 2015年12月10日 下午5:18:03 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping(value = "/auth/role")
public class AuthRoleCtrl {
	/**
	 * 日志输出类
	 */
	private static final Logger LOGGER = Logger.getLogger(AuthRoleCtrl.class);

	@Resource
	private AuthRoleBiz authRoleBiz;

	/**
	 * 删除角色
	 * 
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/getRoleById")
	public ResultVO getRoleById(@RequestParam("id") String id) {
		ResultVO resultVO = new ResultVO();
		LOGGER.info("删除角色");
		try {
			if (StringUtils.isNotBlank(id)) {
				Role role = authRoleBiz.getById(id);
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
	 * 查询角色列表
	 * 
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/findRoleList")
	public PageVO<Role> findRoleList(PageVO<Role> page, @RequestBody(required = false) Map<String, Object> query) {
		page.setSearch("md", "auth");
		page.setSearch("use", "role");
		page.setSearch("opt", "findRoleList");
		try {
			if (null == query) {
				query = new HashMap<String, Object>();
			}
			if (null != query.get("pageSize") && StringUtils.isNotEmpty(query.get("pageSize").toString())) {
				page.setSize(Integer.parseInt(query.get("pageSize").toString()));
			}
			page.setQuery(query);
			page.setTotalSize(authRoleBiz.countRoleList(query));
			List<Role> testList = authRoleBiz.findRoleList(query, page.getPage(), page.getPageSize());
			page.setData(testList);
			return page;
		} catch (Exception e) {
			LOGGER.error("e:", e);
			return null;
		}
	}

	/**
	 * 删除角色
	 * 
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/deleteRole",method = RequestMethod.DELETE)
	public ResultVO deleteRole(String id) {
		ResultVO resultVO = new ResultVO();
		LOGGER.info("删除角色");
		try {
			if (id != null&&StringUtils.isNotEmpty(id)) {
				authRoleBiz.deleteRole(id);
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
	 * 新增或修改角色
	 * 
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdateRole")
	public ResultVO saveOrUpdateRole(@RequestBody Role roleVo) {
		ResultVO resultVO = new ResultVO();
		LOGGER.info("新增或修改角色");
		try {
			if (roleVo != null) {
				Role role = authRoleBiz.saveRole(roleVo);
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
}
