/**    
 * Copyright (C),Kingmed
 * @FileName: GroupBizImpl.java  
 * @Package: com.kingmed.ms.customer.biz.impl  
 * @Description: //模块目的、功能描述  
 * @Author jerryhuang  
 * @Date 2015年3月13日 下午4:48:23  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
 */

package com.mbl.msc.auth.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbl.common.bean.Menu;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.mapper.AuthMenuMapper;
import com.mbl.common.util.MenuTreeUtil;
import com.mbl.common.vo.TreeNode;
import com.mbl.msc.auth.biz.AuthMenuBiz;
import com.mbl.msc.auth.vo.MenuTreeVO;

/**
 * 菜单业务层
 * @author zl
 * @create 2015年11月30日 下午1:12:04 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service(value = "authMenuBiz")
@Transactional
public class AuthMenuBizImpl implements AuthMenuBiz {
	@Resource
	private AuthMenuMapper authMenuMapper;
	
	@Override
	public Menu getMenuById(String id) {
		if(id!=null && !"".equals(id)){
			return authMenuMapper.getById(id);
		}
		return null;
	};
	
	/**  
	 * 结点查询
	 */
	public List<MenuTreeVO> findChildById(String id) {
		
		Map<String, Object> searchParams = new HashMap<String, Object>();
		
		if(id!=null&&!"#".equals(id)&&StringUtils.isNotEmpty(id)){
			searchParams.put("parentMenuId", id);
		}else{
			id = null;
			List<Menu> roots = authMenuMapper.findRootMenu();
			if(null!=roots&&roots.size()>0){
				searchParams.put("menuId", roots.get(0).getMenuId());
			}
		}
		List<Menu> menuList=authMenuMapper.findListByParams(searchParams);
		
		List<MenuTreeVO> menuTreeVoList = new ArrayList<MenuTreeVO>();
		MenuTreeVO vo = null;
		for (Menu menu : menuList) {
			vo = new MenuTreeVO();
			vo.setParentId(id);
			vo.setId(menu.getMenuId());
			vo.setText(menu.getMenuName());
			vo.setMenu(menu);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("parentMenuId", menu.getMenuId());
			Long count = authMenuMapper.getCountByParams(params);
			if(count>0){
				vo.setChildren(true);
				vo.setHasChildrenFlag("true");
			}
			menuTreeVoList.add(vo);
		}
		return menuTreeVoList;
	}
	
	/**  
	 * 新增结点业务
	 * @param targetMenuVo
	 */
	@Override
	public Menu addMenu(Menu menuVO) throws BizException{
		Menu menu=null;
		Long count=0l;
		String menuId=null;//当前系列ID
		String oldMenuId=null;//库里ID
		boolean addTag=true;//新增还是修改

		if(menuVO!=null){
			
			//判断下
			if(StringUtils.isNotEmpty(menuVO.getMenuId())){
				menu=authMenuMapper.getById(menuVO.getMenuId());
			}
			//赋值ID
			if (menu==null){//查询不到
				menu=new Menu();
				menuId=UUID.randomUUID().toString();
			}else{
				menuId=menuVO.getMenuId();
				addTag = false;
			}
			
			if(null==menuVO.getParentMenuId()){
				//为根结点
				List<Menu> roots = null;
				roots = authMenuMapper.findRootMenu();
				
				if(null!=roots&&roots.size()>0){
					oldMenuId = roots.get(0).getMenuId();
				}
				
				if (oldMenuId != null){
					//库中存在，判断是否相等，不相等时，库中同一集团或分公司存在多条
					if (oldMenuId.equals(menuId)){
						//更新，
						saveMenu(menuVO, menu, menuId);
					}
				}else{
					saveMenu(menuVO, menu, menuId);
				}
	
			}else{
				//为子结点
				//menuVO.getParentMenuId(), menuVO.getMenuName()
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("parentMenuId", menuVO.getParentMenuId());
				params.put("menuCode", menuVO.getMenuCode());
				params.put("menuName", menuVO.getMenuName());
				params.put("seq", menuVO.getSeq());
				params.put("menuId", menuVO.getMenuId());
	
				//判断菜单名称是否存在
				if (addTag){
					count=authMenuMapper.getCountByParams(params);
				}else{
					count=authMenuMapper.getCountByParamsNotMenuId(params);
				}
				if(count==0){
					saveMenu(menuVO, menu, menuId);
				}else{
					throw new BizException("-1", "当前添加的菜单已存在！");
				}
			}
		}
		return menuVO;
	}
	private void saveMenu(Menu menuVO,
			Menu menu, String menuId) {
		
		menu.setMenuName(menuVO.getMenuName());
		menu.setMenuId(menuId);
		menu.setMenuStatus("1");
		menu.setParentMenuId(menuVO.getParentMenuId());
		menu.setMenuUrl(menuVO.getMenuUrl());
		menu.setMenuCode(menuVO.getMenuCode());
		menu.setSeq(menuVO.getSeq());
		Menu menu1 =authMenuMapper.getById(menuVO.getMenuId());
		if(null!=menu1){
			authMenuMapper.update(menu);
		}else{
			authMenuMapper.save(menu);
		}
	}
	@Override
	public int delMenu(String id) throws BizException{
		if(id!=null && !"".equals(id)){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("parentMenuId", id);
			Long count=authMenuMapper.getCountByParams(params);
			if(count>0){//是否有子结点
				throw new BizException("-1", "当前菜单存在子菜单！");
			}else{
				authMenuMapper.delById(id);
				return 1;
			}
		}
		return 0;
	}
	
	
	@Override
	public boolean isHasRootMenu() {
		List<Menu> roots = authMenuMapper.findRootMenu();
		return roots.size()>0?true:false;
	}
	
	@Override
	public String isExistMenu(Menu menuVO) throws BizException {
		
		//判断菜单是否存在
		//menuVO.getMenuId(),menuVO.getMenuName()
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentMenuId", menuVO.getParentMenuId());
		params.put("menuCode", menuVO.getMenuCode());
		long count = 0;
		if(StringUtils.isNotEmpty(menuVO.getMenuId())){
			params.put("menuId", menuVO.getMenuId());
			count = authMenuMapper.getCountByParamsNotMenuId(params);
		}else{
			count = authMenuMapper.getCountByParams(params);
		}
		if(count>0){
			return menuVO.getMenuName();
		}
		return null;
	}
	
	@Override
	public List<TreeNode> loadAllMenu() {
		List<Menu> roots = authMenuMapper.findRootMenu();
		if(null==roots||roots.size()==0){
			return new ArrayList<TreeNode>();
		}
		Menu root = roots.get(0);
		TreeNode tn = new TreeNode();
		tn.setId(root.getMenuId());
		tn.setCode(root.getMenuCode());
		tn.setText(root.getMenuName());
		//tn.setState(MenuTreeUtil.STATE_SELETED);
		tn.setParentId("#");
		tn.setPath(root.getMenuUrl());
		fetchMenu(tn);
		List<TreeNode> tnns = new ArrayList<TreeNode>();
		tnns.add(tn);
		return tnns;
	}
	
	private void fetchMenu(TreeNode node){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentMenuId", node.getId());
		List<Menu> menus = authMenuMapper.findListByParams(params);
		if(null!=menus&&menus.size()>0){
			List<TreeNode> tnChildList = new ArrayList<TreeNode>();
			for (Menu menu : menus) {
				TreeNode tnChild = new TreeNode();
				tnChild.setId(menu.getMenuId());
				tnChild.setCode(menu.getMenuCode());
				tnChild.setText(menu.getMenuName());
				tnChild.setPath(menu.getMenuUrl());
				//tnChild.setState(MenuTreeUtil.STATE_SELETED);
				tnChild.setParentId(node.getId());
				fetchMenu(tnChild);
				tnChildList.add(tnChild);
			}
			node.setChildren(tnChildList);
		}
	}
}
