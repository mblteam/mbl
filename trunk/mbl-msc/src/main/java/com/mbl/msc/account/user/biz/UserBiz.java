package com.mbl.msc.account.user.biz;

import java.util.List;
import java.util.Map;

import com.mbl.common.bean.User;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.vo.AccountVO;
/**
 * 用户业务层
 *〈一句话功能简述〉
 * 功能详细描述
 * @author fangxiaowei
 * @create 2015年12月19日 下午5:19:43 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface UserBiz {
	
	/****
	 * 保存、修改
	 * @param user
	 * @return
	 * @throws Exception 
	 * @throws BizException 
	 */
	int saveUser(AccountVO accountVo) throws BizException, Exception;
	
	/**
	 * 查询条数
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param map
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	Long getCountByParams(Map<String, Object> map) ;
	
	/****
	 * 详情查询
	 * @param userId
	 * @return
	 */
	User getById(String userId);
	
	/****
	 * 删除
	 * @param id
	 * @return
	 * @throws BizException 
	 */
	int delById(String id) throws BizException;
	/**
	 * 查询
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param query
	 * @param page
	 * @param pageSize
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	List<User> findList(Map<String, Object> query, Integer page,
			Integer pageSize);
	
	
}
