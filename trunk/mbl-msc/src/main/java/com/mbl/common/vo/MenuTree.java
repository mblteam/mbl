package com.mbl.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.mbl.common.bean.Menu;

/**
 * 菜单树
 * 功能详细描述
 * @author zl
 * @create 2015年12月19日 下午10:06:46 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class MenuTree {

	 /** 菜单编号 */
    private String menuId;
    
    /** 菜单代码 */
    private String menuCode;
    
    /** 菜单名称 */
    private String menuName;
    
    /** 状态1-有效 0-失效 */
    private String menuStatus;
    
    /** url */
    private String menuUrl;
    
    /** 上级菜单编号 */
    private String parentMenuId;
    
    /** 菜单级别 **/
    private String grade;
    
    private Menu menu;
    
    private List<MenuTree> children = new ArrayList<MenuTree>();

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuStatus() {
		return menuStatus;
	}

	public void setMenuStatus(String menuStatus) {
		this.menuStatus = menuStatus;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public List<MenuTree> getChildren() {
		return children;
	}

	public void setChildren(List<MenuTree> children) {
		this.children = children;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public MenuTree(Menu menu){
		this.menuId = menu.getMenuId();
		this.menuCode = menu.getMenuCode();
		this.menuName = menu.getMenuName();
		this.menuUrl = menu.getMenuUrl();
		this.grade = menu.getGrade();
		
		this.menu = menu;
	}
	
	@Override
	public String toString() {
		return "MenuTree [menuId=" + menuId + ", menuCode=" + menuCode + ", menuName=" + menuName
				+ ", menuUrl=" + menuUrl + ", grade=" + grade + ", children=" + children + "]";
	}
	
}
