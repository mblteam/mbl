package com.mbl.rest.ctrl;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.client.MockMvcClientHttpRequestFactory;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.ServletWebRequest;

import com.mbl.common.util.AbstractTestBase;
import com.mbl.common.util.AbstractWebTestBase;
import com.mbl.common.util.RestfulTest;

/**
 * 账户管理接口ctrl测试类
 * @author zl
 * @create 2015年12月5日 下午11:37:04 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class AccountCtrlTest extends AbstractWebTestBase{

	//发送验证码测试
	@Test
	public void testSendCode() throws Exception{
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("tel", "18888888888");
		System.out.println(JSONObject.toJSONString(params));
		postRequest("/rest/account/sendCode.json",params);
	}
	
	//注册
	@Test
	public void testRegister() throws Exception{

		Map<String,Object> params = new HashMap<String, Object>();
		params.put("tel", "18888888888");
		params.put("sendCode", "123456");
		System.out.println(JSONObject.toJSONString(params));
		postRequest("/rest/account/register.json",params);
	}
	
	
	
}
