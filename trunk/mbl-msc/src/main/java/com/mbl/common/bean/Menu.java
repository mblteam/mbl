package com.mbl.common.bean;

import com.mbl.common.framework.mapper.BaseEntity;
/**
 * 菜单(M_MENU)
 * 
 * @author bianj
 * @version 1.0.0 2015-12-08
 */
public class Menu extends BaseEntity  implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -1581606213661668378L;
    
    /** 菜单编号 **/
    private String menuId;
    
    /** 菜单代码 **/
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
    
    /** 顺序  **/
    private Integer seq;
    
    /**
     * 获取菜单编号
     * 
     * @return 菜单编号
     */
    public String getMenuId() {
        return this.menuId;
    }
     
    /**
     * 设置菜单编号
     * 
     * @param menuId
     *          菜单编号
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
    
    /**
     * 获取菜单名称
     * 
     * @return 菜单名称
     */
    public String getMenuName() {
        return this.menuName;
    }
     
    /**
     * 设置菜单名称
     * 
     * @param menuName
     *          菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    
    /**
     * 获取状态1-有效 0-失效
     * 
     * @return 状态1-有效 0-失效
     */
    public String getMenuStatus() {
        return this.menuStatus;
    }
     
    /**
     * 设置状态1-有效 0-失效
     * 
     * @param menuStatus
     *          状态1-有效 0-失效
     */
    public void setMenuStatus(String menuStatus) {
        this.menuStatus = menuStatus;
    }
    
    /**
     * 获取url
     * 
     * @return url
     */
    public String getMenuUrl() {
        return this.menuUrl;
    }
     
    /**
     * 设置url
     * 
     * @param menuUrl
     *          url
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }
    
    /**
     * 获取上级菜单编号
     * 
     * @return 上级菜单编号
     */
    public String getParentMenuId() {
        return this.parentMenuId;
    }
     
    /**
     * 设置上级菜单编号
     * 
     * @param parentMenuId
     *          上级菜单编号
     */
    public void setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    /**
     * 获取菜单级别
     * 功能详细描述
     * @return
     * @see [相关类/方法]（可选）
     * @since [产品/模块版本] （可选）
     */
	public String getGrade() {
		return grade;
	}

	/**
	 * 设置菜单级别
	 * 功能详细描述
	 * @param grade
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	
	
}