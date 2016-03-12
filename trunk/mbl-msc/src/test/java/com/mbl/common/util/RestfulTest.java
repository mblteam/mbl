package com.mbl.common.util;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

public class RestfulTest {
	public static MockHttpServletRequestBuilder postJSON(String urlTemplate, Map<String,Object> map,Object ... urlVariables){
		return post( urlTemplate, urlVariables).contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)) ;
	}
}
