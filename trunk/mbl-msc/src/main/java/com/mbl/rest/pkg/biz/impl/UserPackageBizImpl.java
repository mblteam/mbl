package com.mbl.rest.pkg.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbl.common.constant.ConstantClass;
import com.mbl.common.mapper.UserPkgMapper;
import com.mbl.rest.pkg.biz.UserPackageBiz;
import com.mbl.rest.pkg.vo.UserPackageRequestVo;
import com.mbl.rest.pkg.vo.UserPackageVo;

/**
 * SetMealBiz
 * @author xjs
 * @create 2015年12月05日 下午1:12:04 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service(value = "setMealBiz")
@Transactional
public class UserPackageBizImpl implements UserPackageBiz {

	@Resource
	UserPkgMapper userPkgMapper;
	
	@Override
	public List<UserPackageVo> searchUserPackageList(UserPackageRequestVo requestVo) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", requestVo.getUserId());
		map.put("shopId", requestVo.getShopId());
		map.put("pkgId", requestVo.getPkgId());
		map.put("accountId", requestVo.getAccountId());
		return userPkgMapper.findUserPackageListByParams(map); //查全部（用完和没用完的）
	}

}
