package com.mbl.msc.account.user.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbl.common.bean.User;
import com.mbl.common.framework.vo.PageVO;
import com.mbl.common.framework.vo.datagrid.ResultVO;
import com.mbl.common.vo.AccountVO;
import com.mbl.msc.account.user.biz.UserBiz;

/**
 * 用户信息
 *〈一句话功能简述〉
 * 功能详细描述
 * @author fangxiaowei
 * @create 2015年11月21日 下午3:23:01 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping("/account/user")
public class UserCtrl {
	/**
	 * 日志输出类
	 */
	private static final Logger LOGGER = Logger.getLogger(UserCtrl.class);
	@Resource
	private UserBiz userBiz;

	
	/****
	 * 删除
	 * @param list
	 * @return
	 */
	@RequestMapping(value = "/delById")
	public ResultVO delById(@RequestBody Map<String,Object> map) {
		ResultVO resultUser = new ResultVO();
		try {
			String id = (String) map.get("id");
			userBiz.delById(id);
			resultUser.setErrorCode(ResultVO.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultUser.setErrorCode(ResultVO.FAIL);
			resultUser.setErrorMsg(e.getMessage());
		}
		return resultUser;
	}
	
	/**
	 * 查询列表
	 * 
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/findUserList")
	public PageVO<User> findUserList(PageVO<User> page,
			@RequestBody(required = false) Map<String, Object> query) {
			page.setSearch("md", "account");
			page.setSearch("use", "user");
			page.setSearch("opt", "findUserList");
		try {
			if (null == query) {
				query = new HashMap<String, Object>();
			}
			if (null != query.get("pageSize") && StringUtils.isNotEmpty(query.get("pageSize").toString())) {
				page.setSize(Integer.parseInt(query.get("pageSize").toString()));
			}
			page.setQuery(query);
			page.setTotalSize(userBiz.getCountByParams(query));
			List<User> userList = userBiz.findList(query, page.getPage(), page.getPageSize());
			page.setData(userList);
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
	@RequestMapping(value = "/getById")
	public ResultVO getById(@RequestBody(required = false) Map<String, Object> query) {
		ResultVO resultUser = new ResultVO();
		try {
			User user = userBiz.getById((String) query.get("id"));

			resultUser.setErrorCode(ResultVO.SUCCESS);
			resultUser.setResult(user);
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultUser.setErrorCode(ResultVO.FAIL);
			resultUser.setErrorMsg(e.getMessage());
		}
		return resultUser;
	}

	/****
	 * 保存套餐信息
	 * 
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdateUser")
	public ResultVO saveOrUpdateUser(@RequestBody AccountVO accountVo) {
		ResultVO resultVO = new ResultVO();
		try {
			userBiz.saveUser(accountVo);
			resultVO.setErrorCode(ResultVO.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}

}
