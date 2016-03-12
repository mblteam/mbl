package com.mbl.msc.auth.ctrl;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mbl.common.bean.User;
import com.mbl.common.constant.ConstantClass;
import com.mbl.common.constant.UrlConfig;
import com.mbl.common.framework.vo.datagrid.ResultVO;
import com.mbl.common.util.CommonUtils;
import com.mbl.common.util.SSOUtil;
import com.mbl.common.util.ValidateCode;
import com.mbl.common.vo.AccountVO;
import com.mbl.msc.account.account.biz.AccountBiz;
import com.mbl.msc.auth.biz.AuthLoginBiz;
import com.mbl.msc.auth.biz.AuthMenuBiz;
import com.mbl.msc.auth.biz.AuthRoleBiz;
import com.mbl.msc.auth.biz.AuthRoleMenuBiz;
import com.mbl.rest.account.biz.LoginBiz;

/**
 * 登录
 * 功能详细描述
 * @author zl
 * @create 2015年12月13日 下午5:41:34 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
@RequestMapping("/auth/login")
public class AuthLoginCtrl {
	
	/**
	 * 日志输出类
	 */
	private static final Logger LOGGER = Logger.getLogger(AuthLoginCtrl.class);
	
	@Resource
	private LoginBiz loginBiz;
	
	/**
	 * 
	 * 〈生成验证码〉 
     *  生成验证码
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping(value = "/validateCode")
	@ResponseBody
	public void validateCode(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 设置页面不缓存
		response.setHeader("Cache-Control", "no-cache");
		String verifyCode = ValidateCode.generateTextCode(
				ValidateCode.TYPE_NUM_ONLY, 4, null);

		// 将验证码放到HttpSession里面
		request.getSession().setAttribute("validateCode", verifyCode);
		// 设置输出的内容的类型为JPEG图像
		response.setContentType("image/jpeg");
		BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 30,
				3, true, Color.WHITE, Color.BLACK, null);

		// 写给浏览器
		ImageIO.write(bim, "JPEG", response.getOutputStream());
	}
	
	@Resource
	private UrlConfig urlConfig;
	
	@RequestMapping(value = "/toLogin")
	public String toLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		if(StringUtils.isNotBlank(request.getParameter("alertMsg"))){
			if("alreadyLogin".equals(request.getParameter("alertMsg"))){
				request.setAttribute("alertMsg","您的账号已在其它地方登陆");
			}else if("loginOutOfTime".equals(request.getParameter("alertMsg"))){
				request.setAttribute("alertMsg","登陆超时");
			}
		}
		
		return "/login";
	}

	/**
	 * 请求登录，验证用户
	 * 功能详细描述
	 * @param request
	 * @param accountCode
	 * @param password
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping("/loginPost")
	@ResponseBody
	public ResultVO loginPost(HttpServletRequest request,HttpServletResponse response) {
		ResultVO rvo = new ResultVO();
		try {
			HttpSession session = request.getSession();
			Map<String,Object> loginMap = new HashMap<String, Object>();
			loginMap.put("accountCode", request.getParameter("name"));
			String password = request.getParameter("password");
			password = CommonUtils.md5Encode(password);
			loginMap.put("password", password);
			LOGGER.info(loginMap);
			String validateCode = null != session.getAttribute("validateCode")
					?session.getAttribute("validateCode").toString():"";
			if(!validateCode.equals(request.getParameter("validateCode").toString())){
				request.setAttribute("errorMsg", "验证码错误");
				rvo.setErrorCode(ResultVO.FAIL);
				rvo.setErrorMsg("验证码错误");
				return rvo;
			}
			AccountVO avo = loginBiz.login(loginMap);
			if(null==loginBiz.login(loginMap)){
				request.setAttribute("errorMsg", "用户或密码不匹配");
				rvo.setErrorCode(ResultVO.FAIL);
				rvo.setErrorMsg("用户或密码不匹配");
				return rvo;
			}
			
			
			//判断是否已经登陆成功
			/*Cookie userIdCookie  = SSOUtil.getSsoCookie((HttpServletRequest)request, SSOUtil.SSO_LOGIN_USER_COOKIE);
			Cookie sesIdCookie = SSOUtil.getSsoCookie((HttpServletRequest)request, SSOUtil.SSO_LOGIN_COOKIE);
			Cookie actIdCookie = SSOUtil.getSsoCookie((HttpServletRequest)request, SSOUtil.SSO_CURRENT_ACCOUNT_COOKIE);
			
			if(null != userIdCookie && StringUtils.isNotBlank(userIdCookie.getValue()) 
					&& null != sesIdCookie && StringUtils.isNotBlank(sesIdCookie.getValue()) 
					&& null != actIdCookie && StringUtils.isNotBlank(actIdCookie.getValue())
					&& userIdCookie.getValue().equals(avo.getUserId())){
				rvo.setErrorCode(ResultVO.SUCCESS);
				rvo.setResult("1");
				return rvo;
			}*/
			
			//登录
			request.getSession().setAttribute(SSOUtil.SESSION_ACCOUNTID, avo.getAccountId());
			request.getSession().setAttribute(SSOUtil.SSO_ACCOUNTS_COOKIE, avo);
			
			/*//植入登录 cookie (sessionid)
			SSOUtil.putCookie(SSOUtil.SSO_LOGIN_COOKIE, session.getId(),response );
			//植入当前登录USERID
			SSOUtil.putCookie(SSOUtil.SSO_LOGIN_USER_COOKIE, userId,response );			
			//植入当前账号ID cookie
			SSOUtil.putCookie(SSOUtil.SSO_CURRENT_ACCOUNT_COOKIE, avo.getAccountId(),response );*/
			
			rvo.setErrorCode(ResultVO.SUCCESS);
			rvo.setResult("1");
			return rvo;
		} catch (Exception e) {
			e.printStackTrace();
			rvo.setErrorCode(ResultVO.FAIL);
			rvo.setErrorMsg(e.getMessage());
			return rvo;
		}
	}
	
	/**
	 * 获取登录地址
	 * 功能详细描述
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping(value = "/getLoginUrl", method = RequestMethod.GET)
	@ResponseBody
	public ResultVO getLoginUrl(){
		ResultVO rvo = new ResultVO();
		try {
			rvo.setResult(urlConfig.getLoginUrl());
			rvo.setErrorCode(ResultVO.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("获取登录地址失败",e);
			rvo.setErrorCode(ResultVO.FAIL);
			rvo.setErrorMsg(e.getMessage());
		}
		return rvo;
	}
	
	@Resource
	private AuthMenuBiz authMenuBiz;
	
	@Resource
	private AuthRoleBiz authRoleBiz;
	
	@Resource
	private AccountBiz accountBiz;
	
	@Resource
	private AuthLoginBiz authLoginBiz;
	
	/**
	 * 登录初始化
	 * 功能详细描述
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping(value = "/loginInit", method = RequestMethod.GET)
	@ResponseBody
	public ResultVO getCurUserAccounts(HttpServletRequest request){
		ResultVO rvo = new ResultVO();
		try {
			//String actId = SSOUtil.getSsoCookie(request, SSOUtil.SSO_CURRENT_ACCOUNT_COOKIE).getValue();
			if(null!=request.getSession().getAttribute(SSOUtil.SESSION_ACCOUNTID)){
				String accountId = request.getSession().getAttribute(SSOUtil.SESSION_ACCOUNTID).toString();
				AccountVO account = authLoginBiz.getAccountByAccountId(accountId);
				User user = authLoginBiz.getUserByUserId(account.getUserId());
				Map<String,Object> userAccountMap = new HashMap<String,Object>();
				//当前用户
				userAccountMap.put("CURRENT_USER", user);
				//当前账号
				userAccountMap.put("CURRENT_ACCOUNT", account);
				userAccountMap.put("MENU_TREE", authRoleBiz.getMenuRightByAccountId(account.getAccountId()));
				rvo.setResult(userAccountMap);
			}	
			rvo.setErrorCode(ResultVO.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("获取当前用户账号信息或权限失败",e);
			rvo.setErrorCode(ResultVO.FAIL);
			rvo.setErrorMsg(e.getMessage());
		}
		return rvo;
	}
	
	@RequestMapping(value = "/loginOut")
	public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException {
   	 	HttpSession session = request.getSession();
   	 	session.invalidate();
		response.sendRedirect(urlConfig.getLoginUrl());
	}
	
	private String forwardUrl(String gotoUrl,HttpServletRequest request,HttpServletResponse response){
		try {
			request.getRequestDispatcher(gotoUrl).forward(request, response);
		} catch (Exception e) {
			LOGGER.error("error",e);
		}
		return null;
	}
	
	private String redirectUrl(String gotoUrl,HttpServletResponse response){
		try {
			response.sendRedirect(gotoUrl);
		} catch (IOException e) {
			LOGGER.error("error",e);
		}
		return null;
	}
	
	
	/**
	 * 退出
	 * 功能详细描述
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	@RequestMapping("/loginOut")
	@ResponseBody
	public ResultVO loginOut(@RequestBody HttpServletRequest request) {
		ResultVO rvo = new ResultVO();
		try {
			HttpSession session = request.getSession();
			session.removeAttribute(ConstantClass.SESSION_USER);
			rvo.setErrorCode(ResultVO.SUCCESS);
			rvo.setErrorMsg("退出成功");
		} catch (Exception e) {
			rvo.setErrorCode(ResultVO.FAIL);
			rvo.setErrorMsg("退出异常");
		}
		return rvo;
	}*/
	
	/**
	 * 请求登录，验证用户
	 * 功能详细描述
	 * @param request
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@RequestMapping("check")
	@ResponseBody
	public ResultVO check(@RequestBody HttpServletRequest request) {
		ResultVO rvo = new ResultVO();
		HttpSession session = request.getSession();
		AccountVO av = null!=session.getAttribute(ConstantClass.SESSION_USER)
				?(AccountVO) session.getAttribute(ConstantClass.SESSION_USER):null;
		if (av == null) {
			rvo.setErrorCode(ResultVO.FAIL);
			rvo.setErrorMsg("请登录");
			return rvo;
		}
		rvo.setErrorCode(ResultVO.SUCCESS);
		rvo.setErrorMsg("已登录");
		return rvo;
	}
}
