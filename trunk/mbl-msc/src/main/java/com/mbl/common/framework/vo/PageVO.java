package com.mbl.common.framework.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mbl.common.vo.AccountVO;

/**
 * 分页对象
 * @author zl
 * @create 2015年11月28日 下午8:57:38 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class PageVO<T> implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6590930208977243607L;

	/**
	 * data:[0,1,2,3,4,5,6,7,8,9,10], -- 数据 search:{}, --查询对象 pageSize:10, --
	 * 页大小 currentPage:1, -- 当前页 totalSize:101, -- 总数据数 totalPage:11 -- 总页数
	 **/
	private Map<String, Object> search = new HashMap<String, Object>();

	@JsonIgnore
	private Map<String, Object> query = new HashMap<String, Object>();
	
	/**
	 * 错误编码
	 */
	private String errorCode;
	
	/**
	 * 错误描述
	 */
	private String errorMsg;

	public Map<String, Object> getQuery() {
		return query;
	}

	public Object getQueryObject(String key) {
		return query.get(key);
	}

	public void setQueryObject(String key, Object value) {
		this.query.put(key, value);
	}

	public void setQuery(Map<String, Object> query) {
		this.query = query;
	}

	private Integer page = 1;
	private Integer pageSize = 10;
	private Long totalSize = 0l;
	private Long totalPage = 0l;
	private Integer size;
	private Integer currentPage;

	/**
	 * 
	 * 从第0页开始 功能详细描述
	 * 
	 * @param page
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public void setPage(Integer page) {
		this.currentPage = page;
		this.page = page;
	}
	
	public Integer getPage() {
		return page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Map<String, Object> getSearch() {
		return search;
	}

	public void setSearch(String key, Object value) {
		this.search.put(key, value);
	}

	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> T getQueryObject(String key,Class<T> t) {
		if(query.get(key) !=null){
			return (T)query.get(key);
		}
		return null;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	private List<T> data = new ArrayList<T>();
	
	public void setData(List<T> data){
		this.data = data;
	}
	public List<T> getData(){
		return data;
	}

	public Long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Long totalSize) {
		this.totalPage 
			= totalSize/this.getPageSize()+(totalSize%this.getPageSize()>0?1:0);
		this.totalSize = totalSize;
	}

	public Long getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}
}
