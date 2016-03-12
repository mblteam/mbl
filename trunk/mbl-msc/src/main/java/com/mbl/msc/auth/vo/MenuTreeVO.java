package com.mbl.msc.auth.vo;

import com.mbl.common.bean.Menu;


public class MenuTreeVO {

	private String parentId;
	private String id;
	private String text;
	private Boolean children;
	private Menu menu;
	private String hasChildrenFlag;
	
	public String getHasChildrenFlag() {
		return hasChildrenFlag;
	}
	public void setHasChildrenFlag(String hasChildrenFlag) {
		this.hasChildrenFlag = hasChildrenFlag;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Boolean getChildren() {
		return children;
	}
	public void setChildren(Boolean children) {
		this.children = children;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
}
