package com.mbl.common.vo;

import java.util.HashMap;
import java.util.Map;

public class TreeNode {
	
	private String id;
	private String parentId;
	private String path;
	private String text;
	private String code;
	private String icon;
	private String nodeType;
	private Integer seq;
	private Object children; 
	private Map<String,Object> state = new HashMap<String, Object>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getNodeType() {
		return nodeType;
	}
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	public Object getChildren() {
		return children;
	}
	public void setChildren(Object children) {
		this.children = children;
	}
	public Map<String, Object> getState() {
		return state;
	}
	public void setState(Map<String, Object> state) {
		this.state = state;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
}
