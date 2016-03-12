package com.mbl.rest.account.ctrl;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mbl.common.bean.Account;
import com.mbl.common.constant.ConstantClass;
import com.mbl.common.framework.vo.datagrid.ResultVO;
import com.mbl.common.util.CommonUtils;
import com.mbl.common.util.JsonUtil;
import com.mbl.common.util.TokenUtil;
import com.mbl.common.vo.AccountVO;
import com.mbl.msc.account.account.AccountConstant;
import com.mbl.msc.account.account.biz.AccountBiz;
import com.mbl.rest.account.biz.LoginBiz;
import com.mbl.rest.account.biz.RegisterBiz;
import com.mbl.rest.account.vo.LoginRequestVo;
import com.mbl.rest.account.vo.RegisterRequestVo;
import com.mbl.rest.account.vo.UpdatePwdRequestVo;

/**
 * 用户登录接口
 * 
 * @author xjs
 * @create 2015年12月02日 上午2:25:29
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping(value = "/rest/account")
public class AccountCtrl {

	@Resource
	private LoginBiz loginBiz;
	
	@Resource
	private RegisterBiz registerBiz;

	/**
	 * 日志输出类
	 */
	private static final Logger LOGGER = Logger.getLogger(AccountCtrl.class);
	
	/**
	 * 接收手机号，发送验证码
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/sendCode",method=RequestMethod.POST)
	@ResponseBody
	public ResultVO sendCode(@RequestBody RegisterRequestVo requestVo) {
		ResultVO resultVO=new ResultVO();
		
		LOGGER.info("发送验证码");
		
		try {
			//注册
			if(StringUtils.equals(ConstantClass.VERIFY_CODE_REGISTER, requestVo.getFlag())|| 
					StringUtils.equals(ConstantClass.VERIFY_CODE_UPDATETEL, requestVo.getFlag())) {
				//校验数据合法性
				String errorMsg=registerBiz.validate(requestVo);
				if(StringUtils.isNotBlank(errorMsg)){
					resultVO.setErrorCode(ResultVO.FAIL);
					resultVO.setErrorMsg(errorMsg);
					return resultVO;
				}
			} else if(StringUtils.equals(ConstantClass.VERIFY_CODE_FORGETPASS, requestVo.getFlag()) ){//2-找回密码  3-修改手机号
				//验证手机号码是否合法
				if(!CommonUtils.isTel(requestVo.getTel())){
					resultVO.setErrorCode(ResultVO.FAIL);
					resultVO.setErrorMsg("手机号码不合法！");
					return resultVO;
				}
			} else {
				resultVO.setErrorCode(ResultVO.FAIL);
				resultVO.setErrorMsg("非法参数！");
				return resultVO;
			}
		
			registerBiz.sendCode(requestVo);
			
			resultVO.setErrorCode(ResultVO.SUCCESS);
			resultVO.setErrorMsg("发送验证码成功！");
		}  catch (Exception e) {
			LOGGER.error("e:",e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}

	/**
	 * 用户注册
	 * @param MenuVo
	 * @return
	 */
	@RequestMapping(value = "/register",method=RequestMethod.POST)
	@ResponseBody
	public ResultVO register(@RequestBody RegisterRequestVo requestVo) {
		ResultVO resultVO=new ResultVO();
		
		LOGGER.info("用户注册");
		
		try {
			requestVo.setFlag(ConstantClass.VERIFY_CODE_REGISTER);
			//校验数据合法性
			String errorMsg=registerBiz.validate(requestVo);
			if(StringUtils.isNotBlank(errorMsg)){
				resultVO.setErrorCode(ResultVO.FAIL);
				resultVO.setErrorMsg(errorMsg);
				return resultVO;
			}
			
			//注册
			if(registerBiz.register(requestVo)){
				resultVO.setErrorCode(ResultVO.SUCCESS);
				resultVO.setErrorMsg("注册成功！");
				return resultVO;
			}
			
		}  catch (Exception e) {
			LOGGER.error("e:",e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}
		return resultVO;
	}
	
	/**
	 * 用户登录
	 * 
	 * @param LoginRequestVo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO login(@RequestBody LoginRequestVo requestVo) {
		LOGGER.info("用户登录");

		ResultVO resultVO = new ResultVO();
		Map<String,Object> map=JsonUtil.jsonObjToObj(requestVo, Map.class);
		try {
			map.put("accountType", AccountConstant.ACCOUNT_TYPE_NORMAL);
			AccountVO accountVo=loginBiz.login(map);
			if(accountVo!=null){
				Map<String,Object> loginReturnMap = new HashMap<String, Object>();
				loginReturnMap.put("userId", accountVo.getUserId());
				loginReturnMap.put("userName", accountVo.getUserName());
				loginReturnMap.put("accountId", accountVo.getAccountId());
				loginReturnMap.put("accountCode", accountVo.getAccountCode());
				loginReturnMap.put("tel", accountVo.getTel());
				//token生成器生成token
				String tokenStr = TokenUtil.createToken(accountVo.getAccountId(),new GregorianCalendar());
				loginReturnMap.put("token", tokenStr);
				resultVO.setErrorCode(ResultVO.SUCCESS);
				resultVO.setResult(loginReturnMap);
			} else {
				resultVO.setErrorCode(ResultVO.FAIL);
				resultVO.setErrorMsg("对不起,你的用户名或者密码不正确 - 请重试。");
			}
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}

		return resultVO;
	}

	
	/**
	 * 修改密码
	 * 
	 * @param LoginRequestVo
	 * @return
	 */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO updatePassword(@RequestBody UpdatePwdRequestVo requestVo) {
		LOGGER.info("修改密码");

		ResultVO resultVO = new ResultVO();
		try {
			loginBiz.updatePassword(requestVo);

			resultVO.setErrorCode(ResultVO.SUCCESS);
			resultVO.setResult("修改成功！");
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}

		return resultVO;
	}
	
	/**
	 * 忘记密码
	 * 
	 * @param LoginRequestVo
	 * @return
	 */
	@RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO forgetPassword(@RequestBody UpdatePwdRequestVo requestVo) {
		LOGGER.info("忘记密码");

		ResultVO resultVO = new ResultVO();
		try {
			RegisterRequestVo rvo = new RegisterRequestVo();
			rvo.setFlag(ConstantClass.VERIFY_CODE_FORGETPASS);
			rvo.setTel(requestVo.getAccountCode());
			rvo.setSendCode(requestVo.getSendCode());
			//校验数据合法性
			String errorMsg=registerBiz.validate(rvo);
			if(StringUtils.isNotBlank(errorMsg)){
				resultVO.setErrorCode(ResultVO.FAIL);
				resultVO.setErrorMsg(errorMsg);
				return resultVO;
			}
			
			loginBiz.forgetPassword(requestVo);
			
			resultVO.setErrorCode(ResultVO.SUCCESS);
			resultVO.setResult("修改成功！");
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}

		return resultVO;
	}
	
	@Resource
	private AccountBiz accountBiz;
	
	/**
	 * 更换手机号码
	 * 
	 * @param LoginRequestVo
	 * @return
	 */
	@RequestMapping(value = "/updateTel", method = RequestMethod.POST)
	@ResponseBody
	public ResultVO updateTel(@RequestBody RegisterRequestVo requestVo) {
		LOGGER.info("更换手机号码");

		ResultVO resultVO = new ResultVO();
		try {
			if(null==requestVo.getAccountId()){
				resultVO.setErrorCode(ResultVO.FAIL);
				resultVO.setErrorMsg("账号标识不能为空");
				return resultVO;
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("accountId",requestVo.getAccountId());
			Account account = null;
			if(null==(account=accountBiz.getById(requestVo.getAccountId()))){
				resultVO.setErrorCode(ResultVO.FAIL);
				resultVO.setErrorMsg("账号不存在");
				return resultVO;
			}
			if(!StringUtils.equals(account.getPwd(), requestVo.getPassword())){
				resultVO.setErrorCode(ResultVO.FAIL);
				resultVO.setErrorMsg("密码错误,更换号码失败");
				return resultVO;
			}
			requestVo.setFlag(ConstantClass.VERIFY_CODE_UPDATETEL);
			//校验数据合法性
			String errorMsg=registerBiz.validate(requestVo);
			if(StringUtils.isNotBlank(errorMsg)){
				resultVO.setErrorCode(ResultVO.FAIL);
				resultVO.setErrorMsg(errorMsg);
				return resultVO;
			}
			registerBiz.updateTel(requestVo);

			resultVO.setErrorCode(ResultVO.SUCCESS);
			resultVO.setResult("更换手机号码！");
		} catch (Exception e) {
			LOGGER.error("e:", e);
			resultVO.setErrorCode(ResultVO.FAIL);
			resultVO.setErrorMsg(e.getMessage());
		}

		return resultVO;
	}
	
	
}
