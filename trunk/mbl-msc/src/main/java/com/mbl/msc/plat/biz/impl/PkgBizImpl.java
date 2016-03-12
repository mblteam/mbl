package com.mbl.msc.plat.biz.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbl.common.bean.Package;
import com.mbl.common.framework.page.RowBounsUtil;
import com.mbl.common.mapper.PackageMapper;
import com.mbl.common.vo.PackageVO;
import com.mbl.msc.plat.biz.PkgBiz;

/**
 * 套餐逻辑类
 * 
 * @author jjj
 * @create 2015年12月10日 上午12:22:01
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service(value = "pkgBiz")
@Transactional
public class PkgBizImpl implements PkgBiz {

	@Resource
	private PackageMapper packageMapper;
	/***
	 * 查询翻页
	 * @param map
	 * @return
	 */
	public List<PackageVO> findListPageByParams(Map<String, Object> map) {
		return packageMapper.findListPageByParams(map);
	}
	/***
	 * 查询翻页
	 * @param map
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<PackageVO> findPackageList(Map<String, Object> map,Integer page,Integer pageSize){
		return packageMapper.findListPageByParams(map, RowBounsUtil.getRowBounds(page, pageSize));
	}
	
	public Long getCountByParams(Map<String, Object> map) {
		return packageMapper.getCountByParams(map);
	}
	
	public PackageVO getPackageById(String pkgId) {
		return packageMapper.getPackageById(pkgId);
	}
	/***
	 * 保存套餐
	 */
	public int savePkg(PackageVO pkgVo) {
		if(pkgVo!= null) {
			Package pkg = new Package();
			BeanUtils.copyProperties(pkgVo, pkg);
			if(StringUtils.isNotEmpty(pkg.getPkgId())) {
				return packageMapper.update(pkg);
			} else {
				pkg.setPkgId(UUID.randomUUID().toString());
				return packageMapper.save(pkg);
			}
		}
		return 0;
	}

	@Override
	public int delPackageById(String id) {
		// TODO Auto-generated method stub
		return packageMapper.updatePackageStatusById(id);
	}
}
