package com.mbl.common.constant;

import org.springframework.stereotype.Component;

@Component
public class UrlConfig {
	
	 private String loginDns;
	 private String loginUrl;
	 private String port;
	 private String domain;
	 private String bpmUrl;
	 
	 private String indexUrl;
	 
	 private String saveUploadUrl; //保存路径
	 private String getUploadUrl; //获取路径
	 
	 public String getBpmUrl() {
		return bpmUrl;
	}
	public void setBpmUrl(String bpmUrl) {
		this.bpmUrl = bpmUrl;
	}
	//平台
	 private String plDns;
	 private String plContext;
	 
	public String getLoginDns() {
		return loginDns;
	}
	public void setLoginDns(String loginDns) {
		this.loginDns = loginDns;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getPlDns() {
		return plDns;
	}
	public void setPlDns(String plDns) {
		this.plDns = plDns;
	}
	public String getPlContext() {
		return plContext;
	}
	public void setPlContext(String plContext) {
		this.plContext = plContext;
	}
	public String getLoginUrl() {
		this.loginUrl =  this.getPlDns()+this.getPort()+this.getPlContext()+"login.jsp";
		return loginUrl;
	}
	public String getIndexUrl() {
		this.indexUrl =  this.getPlDns()+this.getPort()+this.getPlContext()+"index.html";
		return indexUrl;
	}
	public void setIndexUrl(String indexUrl) {
		this.indexUrl = indexUrl;
	}
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getSaveUploadUrl() {
		return saveUploadUrl;
	}
	public void setSaveUploadUrl(String saveUploadUrl) {
		this.saveUploadUrl = saveUploadUrl;
	}
	public String getGetUploadUrl() {
		return getUploadUrl;
	}
	public void setGetUploadUrl(String getUploadUrl) {
		this.getUploadUrl = getUploadUrl;
	}
	
}
