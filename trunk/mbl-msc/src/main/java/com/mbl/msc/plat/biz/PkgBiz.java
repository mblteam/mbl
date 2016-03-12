package com.mbl.msc.plat.biz;

import java.util.List;
import java.util.Map;

import com.mbl.common.vo.PackageVO;

public interface PkgBiz {
	
	/****
	 * 保存、修改
	 * @param pkg
	 * @return
	 */
	int savePkg(PackageVO pkg);
	
	/****
	 * 分页查询
	 * @param map
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List<PackageVO> findPackageList(Map<String, Object> map,Integer page,Integer pageSize);
	/****
	 * 查询所有
	 * @param map
	 * @return
	 */
	List<PackageVO> findListPageByParams(Map<String, Object> map);
	
	Long getCountByParams(Map<String, Object> map) ;
	
	/****
	 * 详情查询
	 * @param pkgId
	 * @return
	 */
	PackageVO getPackageById(String pkgId);
	
	/****
	 * 删除，软删除
	 * @param pkgId
	 * @return
	 */
	int delPackageById(String id);
}
