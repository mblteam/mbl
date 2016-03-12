package com.mbl.common.util;

/*
 * 文件类型枚举
 * 功能详细描述
 * @author zl
 * @create 2015年12月12日 下午12:48:01 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public enum FileCategory {
	IMAGE("images"), FILE("file"), OTHER("other");
	
	private final String value;
	
	 //构造器默认也只能是private, 从而保证构造函数只能在内部使用
	FileCategory(String value) {
        this.value = value;
    }
	
	public String getValue() {
        return value;
    }
}
