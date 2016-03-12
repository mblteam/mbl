package com.mbl.rest.pkg.biz;

import java.util.List;

import com.mbl.rest.pkg.vo.UserPackageRequestVo;
import com.mbl.rest.pkg.vo.UserPackageVo;


/**
 * 用户已支付套餐
 * @author xjs
 * @create 2015年12月05日 下午1:12:04 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface UserPackageBiz {

	/**
	 * 查询用户已支付套餐
	 * @param userId
	 * @return
	 */
	List<UserPackageVo> searchUserPackageList(UserPackageRequestVo requestVo);

}
