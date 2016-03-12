package com.mbl.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mbl.common.bean.IdentifyingCode;
import com.mbl.common.bean.UserCar;
import com.mbl.common.framework.mapper.BaseMapper;

/**
 * 验证码数据访问mapper
 * @author zl
 * @create 2015年12月5日 下午4:10:34 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface IdentifyingCodeMapper extends BaseMapper<IdentifyingCode>{
	
	List<IdentifyingCode> findByTelAndFlag(@Param("tel") String tel,@Param("flag")String flag);
	
    int save(UserCar record);
    
    int saveSelective(UserCar record);

}