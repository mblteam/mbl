package com.mbl.common.framework.vo.jqgrid;

/**
 * 〈一句话功能简述〉 功能详细描述
 * 
 * @author HuangSiwei
 * @create 2015年1月6日 下午5:22:26
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class JqGridQueryParam {

	private int page;
	private int rows;

	private String sidx; // 排序字段
	private String sord; // 排序方向

	// treeGrid
	private String nodeid;
	private int n_level = -1;// 规定根节点为-1

	/**
	 * 返回 page 的值
	 * 
	 * @return page
	 */

	public int getPage() {
		return page;
	}

	/**
	 * 设置 page 的值
	 * 
	 * @param page
	 */

	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * 返回 rows 的值
	 * 
	 * @return rows
	 */

	public int getRows() {
		return rows;
	}

	/**
	 * 设置 rows 的值
	 * 
	 * @param rows
	 */

	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * 返回 sidx 的值
	 * 
	 * @return sidx
	 */

	public String getSidx() {
		return sidx;
	}

	/**
	 * 设置 sidx 的值
	 * 
	 * @param sidx
	 */

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	/**
	 * 返回 sord 的值
	 * 
	 * @return sord
	 */

	public String getSord() {
		return sord;
	}

	/**
	 * 设置 sord 的值
	 * 
	 * @param sord
	 */

	public void setSord(String sord) {
		this.sord = sord;
	}

	/**
	 * 返回 nodeid 的值
	 * 
	 * @return nodeid
	 */

	public String getNodeid() {
		return nodeid;
	}

	/**
	 * 设置 nodeid 的值
	 * 
	 * @param nodeid
	 */

	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}

	/**
	 * 返回 n_level 的值
	 * 
	 * @return n_level
	 */

	public int getN_level() {
		return n_level;
	}

	/**
	 * 设置 n_level 的值
	 * 
	 * @param n_level
	 */

	public void setN_level(int n_level) {
		this.n_level = n_level;
	}

}
