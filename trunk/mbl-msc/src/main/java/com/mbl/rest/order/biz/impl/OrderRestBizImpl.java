package com.mbl.rest.order.biz.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbl.common.bean.Order;
import com.mbl.common.bean.OrderDetail;
import com.mbl.common.bean.Shop;
import com.mbl.common.bean.UserPkg;
import com.mbl.common.constant.ConstantClass;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.framework.page.RowBounsUtil;
import com.mbl.common.framework.vo.DictCode;
import com.mbl.common.mapper.AppointmentMapper;
import com.mbl.common.mapper.OrderDetailMapper;
import com.mbl.common.mapper.OrderMapper;
import com.mbl.common.mapper.ShopMapper;
import com.mbl.common.mapper.UserPkgMapper;
import com.mbl.common.util.JsonUtils;
import com.mbl.common.util.alipay.AlipayNotify;
import com.mbl.common.util.wx2.HttpUtils;
import com.mbl.common.vo.OrderDetailVO;
import com.mbl.common.vo.OrderVO;
import com.mbl.rest.order.biz.OrderRestBiz;
import com.mbl.rest.order.biz.OrderRestDetailBiz;
import com.mbl.rest.pkg.vo.UserPackageVo;

@Service
@Transactional
public class OrderRestBizImpl implements OrderRestBiz {
	
	final Logger log=Logger.getLogger(OrderRestBizImpl.class);
	
	@Resource
	private OrderMapper orderMapper;
	
	@Resource
	private OrderDetailMapper orderDetailMapper;
	
	@Resource
	private OrderRestDetailBiz orderRestDetailBiz;
	
	@Resource
	private UserPkgMapper userPkgMapper;
	
	@Resource
	private AppointmentMapper appointmentMapper;
	
	@Resource
	private ShopMapper shopMapper;
	
	/***
	 * 查询订单翻页
	 * @param map
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@Override
	public List<OrderVO> findListPageByParams(Map<String, Object> map,Integer page,Integer pageSize){
		return orderMapper.findListPageByParams(map,RowBounsUtil.getRowBounds(page, pageSize));
	}

	@Override
	public Long countListByParams(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return orderMapper.countListByParams(params);
	}
	
	@Override
	public OrderVO getOrderById(Map<String, Object> params) {
		return orderMapper.getOrderById(params);
	}
	
	@Override
	public OrderVO getOrderByOrderNo(Map<String, Object> query) {
		return orderMapper.getOrderByOrderNo(query);
	}
	
	/****
	 * 更新订单状态
	 * @param params
	 * @return
	 */
	@Override
	public int updateOrderStatus(Map<String, Object> params) {
		return orderMapper.updateOrderStatus(params);
	}
	
	/**
	 * 修改店铺的奖金池（将订单金额的 2%添加到奖池中）
	 * @param order
	 */
	private void updateShopPond(OrderVO order){
		Shop shop = shopMapper.getById(order.getShopId());
		//奖池中剩余的金额  = 原奖池金额 - 订单优惠金额 + 店铺放入奖池金额
		BigDecimal pond = shop.getPond().add(BigDecimal.valueOf(0.02).multiply(order.getPrice()));
		shop.setPond(pond);
		shop.setLastUpdateDate(new Timestamp(new Date().getTime()));
		shopMapper.update(shop);
	}
	
	/**
	 * 新增用户套餐信息
	 * @param args
	 * @throws BizException 
	 */
	private void insertUserPkg(Map<String, Object> args) throws BizException{
		List<OrderVO> orderList = orderMapper.findListPageByParams(args);
		if(CollectionUtils.isEmpty(orderList)){
			throw new BizException("001","没有查询到对应的订单信息");
		}
		
		OrderVO orderVO = orderList.get(0);
		List<OrderDetailVO> orderDetailList = orderRestDetailBiz.findOrderDetailList(args);
		
		//更新店铺奖池
		updateShopPond(orderVO);
		
		List<UserPkg> userPkgList = new ArrayList<UserPkg>();
		for(OrderDetailVO detailVO:orderDetailList){
			UserPkg userPkg = new UserPkg();
			userPkg.setUpId(UUID.randomUUID().toString());
			userPkg.setCreationDate(new Timestamp(new Date().getTime()));
			userPkg.setLastUpdateDate(new Timestamp(new Date().getTime()));
			userPkg.setUserId(orderVO.getUserId());
			userPkg.setPkgId(detailVO.getPkgId());
			userPkg.setStartDate(detailVO.getPkgStartDate());
			userPkg.setEndDate(detailVO.getPkgEndDate());
			userPkg.setShopId(orderVO.getShopId());
			userPkg.setPkgType(detailVO.getPkgType());
			userPkg.setPkgNum(Integer.parseInt(detailVO.getPkgNum()));
			userPkg.setUseNum(1);
			userPkg.setLeftNum(Integer.parseInt(detailVO.getPkgNum())-userPkg.getUseNum());
			userPkg.setOrderId(detailVO.getOrderId());
			userPkg.setStatus(DictCode.VALID_STATUS);
			userPkg.setPrice(detailVO.getPkgPrice());
			userPkg.setDiscount(detailVO.getPkgDiscount());
			userPkg.setPaid(detailVO.getPkgPrice().multiply(detailVO.getPkgDiscount().divide(BigDecimal.valueOf(100))));
			userPkg.setAccountId(orderVO.getAccountId());
			
			args.put("userId", orderVO.getAccountId());
			args.put("shopId", orderVO.getShopId());
			args.put("pkgId", detailVO.getPkgId());
			List<UserPackageVo>  userPackageList = userPkgMapper.findUserPackageListByParams(args);
			if(CollectionUtils.isNotEmpty(userPackageList)){
				userPkgList.add(userPkg);
			}
			
		}
		if(CollectionUtils.isNotEmpty(userPkgList)){
			userPkgMapper.insertbatch(userPkgList);
		}
	}
	
	/**
	 * 更新用户套餐使用次数
	 * @param appointment
	 * @throws BizException 
	 */
	private void updateUserPkgNum(Order order,List<OrderDetail> detailList) throws BizException{
		for(OrderDetail detail: detailList){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("accountId", order.getAccountId());
			map.put("shopId", order.getShopId());
			map.put("pkgId", detail.getPkgId());
			List<UserPackageVo> userPackageList = userPkgMapper.findUserPackageListByParams(map);
			for(UserPackageVo userPackageVo : userPackageList){
				if(DictCode.COUNT_CARD.equals(detail.getPkgType())){//次数卡
					UserPkg userPkg = new UserPkg();
					userPkg.setUpId(userPackageVo.getUpId());
					userPkg.setUseNum(userPackageVo.getUseNum()-1);
					userPkg.setLeftNum(userPackageVo.getPkgNum()-userPkg.getUseNum());
					if(null!=userPackageVo.getPkgNum()&&(int)userPackageVo.getPkgNum()==userPkg.getUseNum()){
						userPkg.setStatus(ConstantClass.DISABLE);
					}else{
						userPkg.setStatus(ConstantClass.ABLE);
					}
					userPkgMapper.update(userPkg);
				}
			}
		}
	}

	/**
	 * 取消订单
	 * @throws BizException 
	 */
	@Override
	public void cancelOrder(Map<String, Object> query) throws BizException {
		
		String orderId = (String)query.get("orderId");
		
		Order order = orderMapper.getById(orderId);
		if(order.getIsPay().equals(DictCode.ALREADY_PAID)){
			throw new BizException("001","已支付的订单不能取消！");
		}
		
		query.put("status", "0");
		orderMapper.updateOrderStatus(query);
		
		appointmentMapper.updateAppointmentStatusById(order.getAppointmentId());
		
		List<OrderDetail> detailList = orderDetailMapper.findListByParams(query);
		updateUserPkgNum(order, detailList);
		
//		Shop shop = shopMapper.getById(order.getShopId());
//		//奖池中剩余的金额  = 原奖池金额 + 订单优惠金额 
//		BigDecimal pond = shop.getPond().add(order.getPrice().subtract(order.getPaid()));
//		shop.setPond(pond);
//		shop.setLastUpdateDate(new Timestamp(new Date().getTime()));
//		shopMapper.update(shop);
	}
	
	/****
	 * 支付完成后更新订单状态
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws BizException 
	 */
	@Override
	public void payAfterSuccess(HttpServletRequest request) throws Exception {
		
		//获取支付宝POST过来反馈信息
		Map<String,String>params=new HashMap<String,String>();
		Map requestParams=request.getParameterMap();
		for(Iterator iter=requestParams.keySet().iterator();iter.hasNext();){
			String name=(String)iter.next();
			String[]values=(String[])requestParams.get(name);
			String valueStr="";
			for(int i=0;i<values.length;i++){
				valueStr=(i==values.length-1)?valueStr+values[i]
						:valueStr+values[i]+",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr=newString(valueStr.getBytes("ISO-8859-1"),"gbk");
			params.put(name,valueStr);
			log.error(name+"==="+valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号

		String out_trade_no=new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		log.error("out_trade_no==="+out_trade_no);
		//支付宝交易号
		String trade_no=new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		log.error("trade_no==="+trade_no);
		//交易状态
		String trade_status=new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		log.error("trade_status==="+trade_status);
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		if(AlipayNotify.verify(params)){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码

			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			
			if(trade_status.equals("TRADE_FINISHED")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
				
				//新增已付款的用户套餐数据
				Map<String, Object> args = new HashMap<String,Object>();
				args.put("tradeNo", trade_no);
				args.put("orderNo", out_trade_no);
				insertUserPkg(args);
				
				args.put("payType", DictCode.ALIPAY_PAYMENT);
				//修改支付后订单状态
				orderMapper.payAfterUpdateOrderStatus(args);
				
				//注意：
				//该种交易状态只在两种情况下出现
				//1、开通了普通即时到账，买家付款成功后。
				//2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
			}else if(trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
				
				Map<String, Object> args = new HashMap<String,Object>();
				args.put("tradeNo", trade_no);
				args.put("orderNo", out_trade_no);
				insertUserPkg(args);
				
				args.put("payType", DictCode.ALIPAY_PAYMENT);
				
				//修改支付后订单状态
				orderMapper.payAfterUpdateOrderStatus(args);
				
				//注意：
				//该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。
			}
		}
	}
	
	/**
	 * 微信支付完成后更新订单状态
	 * @param request  
	 * @throws BizException 
	 * @throws UnsupportedEncodingException 
	 * @see com.mbl.rest.order.biz.OrderRestBiz#wxPayAfterSuccess(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void wxPayAfterSuccess(HttpServletRequest request) throws Exception {
		//获取微信POST过来反馈信息
		String xml = HttpUtils.readResult(request.getInputStream());
		String jsonStr = JsonUtils.parseXml2Json(xml);
		log.error("json=" + jsonStr);
		JSONObject jsonObj = new JSONObject(jsonStr);
		String returnCode = jsonObj.getString("return_code");
		String resultCode = jsonObj.getString("result_code");
		if ("SUCCESS".equalsIgnoreCase(returnCode)
				&& "SUCCESS".equalsIgnoreCase(resultCode)) { // 通信正常
			String outTradeNo = jsonObj.getString("out_trade_no");
			log.error("out_trade_no="+outTradeNo);
			System.out.println("支付成功");
		}
		
		//获取微信的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号
		String out_trade_no=jsonObj.getString("out_trade_no");
		log.error("out_trade_no==="+out_trade_no);
		
		//微信交易号
		String transaction_id=jsonObj.getString("transaction_id");
		log.error("transaction_id==="+transaction_id);
		//交易状态
		String return_code=jsonObj.getString("return_code");
		log.error("return_code==="+return_code);
		
		//获取微信的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		if(return_code.equals("SUCCESS")){
			//交易成功
			Map<String, Object> args = new HashMap<String,Object>();
			args.put("tradeNo", transaction_id);
			args.put("orderNo", out_trade_no);
			insertUserPkg(args);
			args.put("payType", DictCode.WECHAT_PAYMENT);
			//修改支付后订单状态
			orderMapper.payAfterUpdateOrderStatus(args);
			
		}else{
			throw new BizException("-1","FAIL");
		}
	}
}
	
