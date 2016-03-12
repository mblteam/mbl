package com.mbl.rest.account.biz;

import java.util.Map;

import com.mbl.common.bean.Account;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.vo.AccountVO;
import com.mbl.rest.account.vo.UpdatePwdRequestVo;


/**
 * 登录接口
 * @author xjs
 * @create 2015年12月01日 下午22:02:37 
 * @version 1.0.0
 */
public interface LoginBiz {

	/**
	 * 用户登录
	 * @return
	 */
	AccountVO login(Map<String,Object> map);

	/**
	 * 修改密码
	 * @param requestVo
	 * @throws BizException 
	 */
	void updatePassword(UpdatePwdRequestVo requestVo) throws BizException;


	/**
	 * 是否存在
	 * 功能详细描述
	 * @param map
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	boolean existAccount(Map<String, Object> map);


	/**
	 * 忘记密码
	 * @param requestVo
	 */
	void forgetPassword(UpdatePwdRequestVo requestVo) throws BizException;

	
}
