package com.mbl.rest.account.biz;

import com.mbl.rest.account.vo.RegisterRequestVo;


/**
 * 注册接口
 * @author xjs
 * @create 2015年12月01日 下午22:02:37 
 * @version 1.0.0
 */
public interface RegisterBiz {

	/**
	 * 用户注册
	 * @return
	 */
	boolean register(RegisterRequestVo requestVo);

	/**
	 * 判断账号是否已存在
	 * @return
	 */
	boolean existUser(String account);
	
	String validate(RegisterRequestVo requestVo);

	/**
	 * 保存验证码
	 * @param requestVo
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	int sendCode(RegisterRequestVo requestVo) throws Exception;

	/**
	 * 修改手机号
	 * @param requestVo
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	void updateTel(RegisterRequestVo requestVo);
	
}
