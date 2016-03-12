package com.mbl.rest.biz;

import javax.annotation.Resource;

import org.junit.Test;

import com.mbl.common.mapper.OrderMapper;
import com.mbl.common.util.AbstractTestBase;
import com.mbl.common.util.CommonUtils;

public class AppointmentBizTest extends AbstractTestBase{

	@Resource
	private OrderMapper orderMapper; 
	
	@Test
	public void testMaxOrderNo(){
		//获取最大订单号
        //String maxOrderNo = orderMapper.findMaxOrderNo();
		System.out.println(CommonUtils.generatorNo(orderMapper.findMaxOrderNo()));
	}
}
