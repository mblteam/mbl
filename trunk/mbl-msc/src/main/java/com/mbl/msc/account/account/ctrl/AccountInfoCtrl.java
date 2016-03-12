package com.mbl.msc.account.account.ctrl;

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

import com.mbl.common.bean.Account;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.framework.vo.PageVO;
import com.mbl.common.framework.vo.datagrid.ResultVO;
import com.mbl.common.vo.AccountVO;
import com.mbl.msc.account.account.biz.AccountBiz;
import com.mbl.rest.account.ctrl.AccountCtrl;

/**
 * 账户控制层
 *〈一句话功能简述〉
 * 功能详细描述
 * @author fangxiaowei
 * @create 2015年12月22日 下午4:33:57 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping(value = "/account/account")
public class AccountInfoCtrl {
	/**
	 * 日志输出类
	 */
	private static final Logger LOGGER = Logger.getLogger(AccountCtrl.class);

	@Resource
	private AccountBiz accountBiz;

	/**
	 * 通过id获取账户信息
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param id
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping(value = "/getAccountById")
	public ResultVO getAccountById(@RequestParam("id") String id) {
		ResultVO resultVO = new ResultVO();
		LOGGER.info("删除账户");
		try {
			if (StringUtils.isNotBlank(id)) {
				Account account = accountBiz.getById(id);
				resultVO.setErrorCode(ResultVO.SUCCESS);
				resultVO.setResult(account);
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
	 * 查询账户列表
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param page
	 * @param query
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping(value = "/findAccountList")
	public PageVO<AccountVO> findAccountList(PageVO<AccountVO> page, @RequestBody(required = false) Map<String, Object> query) {
		try {
			page.setSearch("md", "account");
			page.setSearch("use", "account");
			page.setSearch("opt", "findAccountList");
			if (null == query) {
				query = new HashMap<String, Object>();
			}
			if (null != query.get("pageSize") && StringUtils.isNotEmpty(query.get("pageSize").toString())) {
				page.setSize(Integer.parseInt(query.get("pageSize").toString()));
			}
			page.setQuery(query);
			page.setTotalSize(accountBiz.countAccountList(query));
			List<AccountVO> testList = accountBiz.findAccountList(query, page.getPage(), page.getPageSize());
			
			page.setData(testList);
			return page;
		} catch (Exception e) {
			LOGGER.error("e:", e);
			return null;
		}
	}

	/**
	 * 删除账户
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param id
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping(value = "/deleteAccount",method = RequestMethod.DELETE)
	public ResultVO deleteAccount(String id) {
		ResultVO resultVO = new ResultVO();
		LOGGER.info("删除账户");
		try {
			if (id != null&&StringUtils.isNotEmpty(id)) {
				accountBiz.deleteAccount(id);
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
	 * 新增或修改账户
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param accountVo
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping(value = "/saveOrUpdateAccount")
	public ResultVO saveOrUpdateAccount(@RequestBody AccountVO accountVo) {
		ResultVO resultVO = new ResultVO();
		LOGGER.info("新增或修改账户");
		try {
			if (accountVo != null) {
				Account account = accountBiz.saveAccount(accountVo);
				resultVO.setErrorCode(ResultVO.SUCCESS);
				resultVO.setResult(account);
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
	 * 修改密码
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param query
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping(value = "/updatePsw")
	public ResultVO updatePsw(@RequestBody(required = false) Map<String, Object> query) {
		ResultVO resultVO = new ResultVO();
		LOGGER.info("通过原密码查找账户");
		try {
			String accountId = query.get("accountId").toString();
			String oldAccountPsw = query.get("oldAccountPsw").toString();
			if (StringUtils.isNotBlank(accountId)&&StringUtils.isNotBlank(oldAccountPsw)) {
				String result = accountBiz.updatePsw(query);
				resultVO.setErrorCode(ResultVO.SUCCESS);
				if("-1".equals(result)){
					resultVO.setResult(ResultVO.FAIL);
				}else{
					resultVO.setResult(ResultVO.SUCCESS);
				}
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
	 * 重置密码
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param query
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping(value = "/reSetPsw")
	public ResultVO reSetPsw(@RequestBody(required = false) Map<String, Object> query) {
		ResultVO resultVO = new ResultVO();
		LOGGER.info("重置账户密码");
		try {
			String accountId = query.get("accountId").toString();
			if (StringUtils.isNotBlank(accountId)) {
				accountBiz.reSetPsw(accountId);
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
	
}
