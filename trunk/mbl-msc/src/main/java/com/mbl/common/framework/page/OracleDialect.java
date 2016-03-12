package com.mbl.common.framework.page;

/**
 * 
 * @author guoliangliang
 *
 */
public class OracleDialect extends Dialect {

	public String getLimitString(String sql, int offset, int limit) {

		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		
		pagingSelect.append("select * from (select row_.*,rownum rownum_ from (");
		
		pagingSelect.append(sql);
		
		pagingSelect.append(") row_) where rownum_ > "+offset+" and rownum_ <= "+(offset + limit));

		return pagingSelect.toString();
	}
}
