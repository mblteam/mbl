package com.mbl.common.framework.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

public interface BaseMapper<T extends BaseEntity> {

	 Integer save(T t);

	 Integer update(T t);

	 Integer delById(Object id);

	 T getById(Object id);

	 /****
	  * 列表用的list
	  * @param map
	  * @return
	  */
	 List<T> findListByParams(Map<String, Object> map);
	 
	 /****
	  * 分页用的list
	  * @param map
	  * @param rowBounds
	  * @return
	  */
	 List<T> findListByParams(Map<String, Object> map, RowBounds rowBounds);

	 Long getCountByParams(Map<String, Object> params);

}
