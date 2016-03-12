package com.mbl.common.mapper;

import com.mbl.common.framework.mapper.BaseMapper;
import com.mbl.common.vo.PackageVO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.mbl.common.bean.Package;

public interface PackageMapper extends BaseMapper<Package>{
	
	List<PackageVO> findListPageByParams(Map<String, Object> map,RowBounds rowBounds);
	
	List<PackageVO> findListPageByParams(Map<String, Object> map);
	
	PackageVO getPackageById(String pkgId);
	
	int updatePackageStatusById(@Param(value="id") String id);
}