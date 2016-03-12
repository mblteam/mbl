package com.mbl.common.mapper;

import java.util.List;
import java.util.Map;

import com.mbl.common.bean.UserPkg;
import com.mbl.rest.pkg.vo.UserPackageVo;
import com.mbl.common.framework.mapper.BaseMapper;

public interface UserPkgMapper extends BaseMapper<UserPkg>{

	List<UserPackageVo> findUserPackageListByParams(Map<String,Object> map);

	void insertbatch(List<UserPkg> userPkgList);
	
}