package com.mbl.msc.account.accountRole.ctrl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mbl.common.bean.AccountRole;
import com.mbl.common.bean.Role;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.framework.vo.datagrid.ResultVO;
import com.mbl.common.vo.AccountRoleVO;
import com.mbl.msc.account.accountRole.biz.AccountRoleBiz;

/**
 * 账户角色关联控制层
 * @author fangxiaowei
 * @create 2015年12月24日 下午4:55:43 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping(value = "/account/accountRole")
public class AccountRoleCtrl {
	/**
	 * 日志输出类
	 */
	private static final Logger LOGGER = Logger.getLogger(AccountRoleCtrl.class);

	@Resource
	private AccountRoleBiz accountRoleBiz;
	
	@RequestMapping(value = "/saveByAccountId")
	public ResultVO saveByAccountId(@RequestBody AccountRoleVO accountRoleVO) {
		ResultVO resultVO = new ResultVO();
		LOGGER.info("新增或修改账户");
		try {
			accountRoleBiz.saveByAccountId(accountRoleVO);
			resultVO.setErrorCode(ResultVO.SUCCESS);
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
	
	@RequestMapping(value = "/deleteAccountRole",method = RequestMethod.DELETE)
	public ResultVO deleteAccountRole(String accountId) {
		ResultVO resultVO = new ResultVO();
		LOGGER.info("删除账户");
		try {
			if (accountId != null&&StringUtils.isNotEmpty(accountId)) {
				accountRoleBiz.deleteByAccountId(accountId);
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
	
	@RequestMapping(value = "/getByAccountId",method = RequestMethod.GET)
	public ResultVO getAccountRoleByAccountId(String accountId) {
		ResultVO resultVO = new ResultVO();
		LOGGER.info("删除账户");
		try {
			if (StringUtils.isNotBlank(accountId)) {
				List<AccountRole> accountRoles = accountRoleBiz.getByAccountId(accountId);
				resultVO.setErrorCode(ResultVO.SUCCESS);
				resultVO.setResult(accountRoles);
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
	 * 删除账户
	 * 
	 * @param MenuVo
	 * @return
	 *//*
	@RequestMapping(value = "/getAccountRoleById")
	public ResultVO getAccountRoleById(@RequestParam("id") String id) {
		ResultVO resultVO = new ResultVO();
		LOGGER.info("删除账户");
		try {
			if (StringUtils.isNotBlank(id)) {
				AccountRole accountRole = accountRoleBiz.getById(id);
				resultVO.setErrorCode(ResultVO.SUCCESS);
				resultVO.setResult(accountRole);
			}
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
			return resultVO;
		}
		return resultVO;
	} 
	*//**
	 * 查询账户列表
	 * 
	 * @param MenuVo
	 * @return
	 *//*
	@RequestMapping(value = "/findAccountRoleList")
	public PageVO<AccountRole> findAccountRoleList(PageVO<AccountRole> page, @RequestBody(required = false) Map<String, Object> query) {
		try {
			if (null == query) {
				query = new HashMap<String, Object>();
			}
			if (null != query.get("pageSize") && StringUtils.isNotEmpty(query.get("pageSize").toString())) {
				page.setSize(Integer.parseInt(query.get("pageSize").toString()));
			}
			page.setQuery(query);
			page.setTotalSize(accountRoleBiz.countAccountRoleList(query));
			List<AccountRole> testList = accountRoleBiz.findAccountRoleList(query, page.getPage(), page.getPageSize());
			
			page.setData(testList);
			return page;
		} catch (Exception e) {
			LOGGER.error("e:", e);
			return null;
		}
	}

	*//**
	 * 删除账户
	 * 
	 * @param MenuVo
	 * @return
	 *//*
	@RequestMapping(value = "/deleteAccountRole",method = RequestMethod.DELETE)
	public ResultVO deleteAccountRole(String id) {
		ResultVO resultVO = new ResultVO();
		LOGGER.info("删除账户");
		try {
			if (id != null&&StringUtils.isNotEmpty(id)) {
				accountRoleBiz.deleteAccountRole(id);
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

	*//**
	 * 新增或修改账户
	 * 
	 * @param MenuVo
	 * @return
	 *//*
	@RequestMapping(value = "/saveOrUpdateAccountRole")
	public ResultVO saveOrUpdateAccountRole(@RequestBody AccountRole accountRole) {
		ResultVO resultVO = new ResultVO();
		LOGGER.info("新增或修改账户");
		try {
			if (accountRole != null) {
				AccountRole accountRole = accountRoleBiz.saveAccountRole(accountRole);
				resultVO.setErrorCode(ResultVO.SUCCESS);
				resultVO.setResult(accountRole);
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
	}*/
}
