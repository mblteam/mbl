/**    
 * Copyright (C),Kingmed
 * @FileName: LoginRequestVo.java  
 * @Package: com.kingmed.lb.authc.vo  
 * @Description: //模块目的、功能描述  
 * @Author HuangSiwei  
 * @Date 2015年4月7日 下午6:20:16  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
 */

package com.mbl.rest.personageinfo.vo;



/**
 * 
 * 个人信息请求参数
 * @author xjs
 * @create 2015年12月05日 下午22:19
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class PersonageInfoRequestVo {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String userName;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
