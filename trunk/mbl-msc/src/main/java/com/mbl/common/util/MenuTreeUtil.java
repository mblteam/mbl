
/**    
 * Copyright (C),Kingmed
 * @FileName: MenuTreeUtil.java  
 * @Package: com.kingmed.platform.modules.permission.service.util  
 * @Description: //模块目的、功能描述  
 * @Author linjiarong  
 * @Date 2014年9月10日 下午5:02:49  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.mbl.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mbl.common.bean.Menu;
import com.mbl.common.vo.TreeNode;

/**
 * 权限树Util类
 * 功能详细描述
 * @author zl
 * @create 2015年12月19日 下午10:12:50 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class MenuTreeUtil {
	
	public static Map<String,Object> STATE_SELETED = new HashMap<String,Object>();
	static{
		STATE_SELETED.put("selected", true);
	}
	
	/**
	 * 
	 * 根据PermList构造MenuTree
	 * @param rootPath
	 * @param perms 
	 * @return 如果无法找到rootPath节点，返回null
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public static TreeNode buildByPerms1(String rootPath, Collection<Menu> perms){
		//建立以Perm的父ID为Key， 其子节点为Value的Map对象，同时找出rootPath节点
		Map<String, List<TreeNode>> permChildMap = new HashMap<String, List<TreeNode>>(perms.size());
		TreeNode rootPerm = null;
		for(Menu p : perms){
			List<TreeNode> permList = null;
			if(!permChildMap.containsKey(p.getParentMenuId())){
				permList = new ArrayList<TreeNode>();
				TreeNode tn = new TreeNode();
				tn.setId(p.getMenuId());
				tn.setCode(p.getMenuCode());
				tn.setText(p.getMenuName());
				tn.setPath(p.getMenuUrl());
				tn.setSeq(p.getSeq());
				tn.setParentId(p.getParentMenuId());
				permList.add(tn);
				permChildMap.put(p.getParentMenuId(), permList);
			}else{
				permList = permChildMap.get(p.getParentMenuId());
				TreeNode tn = new TreeNode();
				tn.setId(p.getMenuId());
				tn.setCode(p.getMenuCode());
				tn.setText(p.getMenuName());
				tn.setPath(p.getMenuUrl());
				tn.setSeq(p.getSeq());
				tn.setParentId(p.getParentMenuId());
				permList.add(tn);
			}
			if(p.getMenuCode().equals(rootPath)){
				TreeNode tn = new TreeNode();
				tn.setId(p.getMenuId());
				tn.setCode(p.getMenuCode());
				tn.setText(p.getMenuName());
				tn.setPath(p.getMenuUrl());
				tn.setSeq(p.getSeq());
				rootPerm = tn;
			}
		}
		//构建MenuTree
		if(rootPerm!=null){
			buildMenuTreeChild(rootPerm, permChildMap);
			return rootPerm;
		}
		
		return null;
	}
	
	private static void buildMenuTreeChild(TreeNode permTreeRoot,
			Map<String, List<TreeNode>> permChildMap) {
		if(permChildMap.containsKey(permTreeRoot.getId())){
			List<TreeNode> permChildList = permChildMap.get(permTreeRoot.getId());
			
			//根据权限进行排序
			Collections.sort(permChildList, new Comparator<TreeNode>(){
				@Override
				public int compare(TreeNode o1, TreeNode o2) {
					return (o1.getSeq() - o2.getSeq());
				}
			});
			
			List<TreeNode> permTreeList = new ArrayList<TreeNode>();
			
			for(TreeNode menu : permChildList){
				permTreeList.add(menu);
				buildMenuTreeChild(menu, permChildMap);
			}
			permTreeRoot.setChildren(permTreeList);
		}else{
			permTreeRoot.setChildren(null);
		}
	}

	    /**
	     * @param 角色
	     * @return
	     *//*
	    public final static List<RoleTree> buildRoleTree(List<Role> roleList) {
	        List<RoleTree> treeList  = new ArrayList<RoleTree>();
	        for (Role role : roleList) {
	            if(role.getParentRoleId() == null) {
	                //获取父节点下的子节点
	            	RoleTree rt = new RoleTree();
	            	ObjectUtils.copyObjValue(role, rt);
	            	rt.setChildren(getChildrenNode(role.getRoleId(),roleList));
	                treeList.add(rt);
	            }
	        }
	        return treeList;
	    }
	     
	    private final static List<RoleTree> getChildrenNode(String pid , List<Role> roleList) {
	        List<RoleTree> treeList = new ArrayList<RoleTree>();
	        for (int i=0; i<roleList.size(); i++ ) {
	        	Role role = roleList.get(i);
	        	if(pid.equals(role.getParentRoleId())){
	        		RoleTree rt = new RoleTree();
	        		ObjectUtils.copyObjValue(role, rt);
	        		rt.setChildren(getChildrenNode(rt.getRoleId(),roleList));
	        		treeList.add(rt);
	        	}
	        }
	        return treeList;
	    }*/
	
	
	
	
	

}
