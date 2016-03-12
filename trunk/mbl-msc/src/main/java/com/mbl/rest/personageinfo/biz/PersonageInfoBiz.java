package com.mbl.rest.personageinfo.biz;

import com.mbl.common.bean.User;
import com.mbl.rest.personageinfo.vo.PersonageInfoResponseVo;


/**
 * 个人信息接口
 * @author xjs
 * @create 2015年12月05日 下午22:02:37 
 * @version 1.0.0
 */
public interface PersonageInfoBiz {

	/**
	 * 个人信息查询
	 * @param userId
	 * @return
	 */
	PersonageInfoResponseVo searchPersonageInfoList(String userId);

	/**
	 * 修改用户个人信息
	 * @param user
	 */
	void updateUser(User user);

	/**
	 * 个人信息查询
	 * @param userId
	 * @return
	 */
	User searchPersonageInfo(String userId);

}
