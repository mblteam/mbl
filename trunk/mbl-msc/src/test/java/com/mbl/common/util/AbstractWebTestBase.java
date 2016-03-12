package com.mbl.common.util;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockMvcClientHttpRequestFactory;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.ServletWebRequest;

import com.alibaba.druid.support.json.JSONUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationMVC.xml","classpath:applicationContext.xml" })
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class AbstractWebTestBase extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	protected WebApplicationContext wac; // cached @Autowired
	@Autowired
	protected MockServletContext servletContext; // cached @Autowired
	@Autowired
	protected MockHttpSession session;
	@Autowired
	protected MockHttpServletRequest request;
	@Autowired
	protected MockHttpServletResponse response;
	@Autowired
	protected ServletWebRequest webRequest;
	
	protected void postRequest(String url,Map<String,Object> params) throws Exception{
		ResultActions act = this.mockMvc.perform(
				RestfulTest.postJSON(url,params)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
		)
		.andExpect(status().isOk())
		.andDo(print()) 
		.andExpect(content().contentType("application/json;charset=UTF-8"));
		//.andExpect(jsonPath("$.size").value(10))
	}
	
	protected void getForObject(String url,Object obj){
		Object result = this.restTemplate.getForObject(url, obj.getClass());
		System.out.println(JSONUtils.toJSONString(result));
		//Assert.assertEquals("bar", result);
	}

	
	private MockMvc mockMvc;
	private RestTemplate restTemplate;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		this.restTemplate = new RestTemplate(new MockMvcClientHttpRequestFactory(mockMvc));
	}
	
	
}
