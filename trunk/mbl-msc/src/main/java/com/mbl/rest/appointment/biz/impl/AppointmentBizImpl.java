package com.mbl.rest.appointment.biz.impl;

import java.beans.IntrospectionException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.mbl.common.bean.Appointment;
import com.mbl.common.bean.AppointmentPackage;
import com.mbl.common.bean.AppointmentService;
import com.mbl.common.bean.DictLine;
import com.mbl.common.bean.Order;
import com.mbl.common.bean.OrderDetail;
import com.mbl.common.bean.Shop;
import com.mbl.common.bean.UserCar;
import com.mbl.common.bean.UserPkg;
import com.mbl.common.constant.ConstantClass;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.framework.vo.DictCode;
import com.mbl.common.framework.vo.DictType;
import com.mbl.common.mapper.AppointmentMapper;
import com.mbl.common.mapper.AppointmentPackageMapper;
import com.mbl.common.mapper.AppointmentSerMapper;
import com.mbl.common.mapper.OrderDetailMapper;
import com.mbl.common.mapper.OrderMapper;
import com.mbl.common.mapper.PackageMapper;
import com.mbl.common.mapper.ShopMapper;
import com.mbl.common.mapper.ShopPackageMapper;
import com.mbl.common.mapper.UserCarMapper;
import com.mbl.common.mapper.UserPkgMapper;
import com.mbl.common.util.BaseFunction;
import com.mbl.common.util.CommonUtils;
import com.mbl.common.util.JsonUtil;
import com.mbl.common.util.alipay.AlipayGetSign;
import com.mbl.common.util.wx2.SignModel;
import com.mbl.common.util.wx2.WxPayUtils;
import com.mbl.common.vo.AppointmentServiceVO;
import com.mbl.common.vo.AppointmentVO;
import com.mbl.common.vo.DictVO;
import com.mbl.common.vo.OrderDetailVO;
import com.mbl.common.vo.OrderVO;
import com.mbl.common.vo.ShopPackageVO;
import com.mbl.rest.appointment.biz.AppointmentBiz;
import com.mbl.rest.appointment.vo.AppointmentRequestVo;
import com.mbl.rest.dict.DictService;
import com.mbl.rest.order.biz.OrderRestBiz;
import com.mbl.rest.order.biz.OrderRestDetailBiz;
import com.mbl.rest.pkg.vo.UserPackageVo;


/**
 * 维修预约
 * @author xjs
 * @create 2015年12月05日 下午1:12:04
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Transactional
@Service(value = "appointmentBiz")
public class AppointmentBizImpl implements AppointmentBiz{

    @Resource
    private AppointmentMapper appointmentMapper;

    @Resource
    private AppointmentSerMapper appointmentSerMapper;

    @Resource
    private AppointmentPackageMapper appointmentPackageMapper;

    @Resource
    private UserCarMapper userCarMapper;

    @Resource
    private ShopPackageMapper shopPackageMapper;
    
    @Resource
    private PackageMapper packageMapper;
    
    @Resource
    private ShopMapper shopMapper;

    @Resource
    private DictService dictService;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderDetailMapper orderDetailMapper;

    @Resource
    private UserPkgMapper userPkgMapper;

    @Resource
    private OrderRestBiz orderRestBiz;

    @Resource
    private OrderRestDetailBiz orderRestDetailBiz;

    /**
     * 新增维修预约
     */
    private Map<String,Object> saveAppointment(AppointmentRequestVo requestVo) throws BizException{

        //新增预约
        Appointment appointment=requestVo.getAppointment();
        addAppointment(appointment);

        //批量新增预约服务
        List<String> serviceCodes = requestVo.getServiceCodes();
        if(CollectionUtils.isNotEmpty(serviceCodes)){
            insertbatchAppointmentSer(serviceCodes, appointment);
        }

        //查询店铺套餐信息
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("spIds", requestVo.getSpIds());
        Shop shop = shopMapper.getById(appointment.getShopId());
        List<ShopPackageVO> shopPackages = shopPackageMapper.findShopPackageListByParams(map);
        if(requestVo.getSpIds().contains("000000000000000000000000000000000000")){
        	ShopPackageVO spVo = new ShopPackageVO();
        	spVo.setSpId("000000000000000000000000000000000000");
        	spVo.setShopId(appointment.getShopId());
        	spVo.setShopName(shop.getShopName());
        	spVo.setPkgType(DictCode.MAINTENANCE);
        	spVo.setPkgTypeName("维修");
        	spVo.setPkgId("000000000000000000000000000000000000");
        	spVo.setPkgName("维修");
        	spVo.setPkgPrice(BigDecimal.valueOf(0));
        	shopPackages.add(spVo);
        }

        if(CollectionUtils.isEmpty(shopPackages)){
            throw new BizException("","请选择有效的预约套餐！");
        }

        //新增预约套餐
        insertbatchAppointmentPackage(shopPackages, appointment);

        //新增订单信息
        Map<String,Object> resultOrder= insertOrder(appointment,shopPackages,requestVo.getUserId());

        //更新用户车辆预约标示
        updateAppointmentFlag(appointment);

        return resultOrder;
    }


    /**
     * 更新用户套餐使用次数
     * @throws BizException
     */
    private void updateUserPkgNum(Order order,List<OrderDetail> detailList) throws BizException{
        for(OrderDetail detail: detailList){

            Map<String,Object> map = new HashMap<String,Object>();
            map.put("accountId", order.getAccountId());
            map.put("shopId", order.getShopId());
            map.put("pkgId", detail.getPkgId());
            List<UserPackageVo>  userPackageList = userPkgMapper.findUserPackageListByParams(map);
            for(UserPackageVo userPackageVo : userPackageList){
                if(DictCode.COUNT_CARD.equals(detail.getPkgType())){//次数卡
                    UserPkg userPkg = new UserPkg();
                    userPkg.setUpId(userPackageVo.getUpId());
                    userPkg.setUseNum(1+userPackageVo.getUseNum());
                    userPkg.setLeftNum(userPackageVo.getPkgNum()-userPkg.getUseNum());
                    if(null!=userPackageVo.getLeftNum()&&userPackageVo.getLeftNum()==0){
                    	userPkg.setStatus(ConstantClass.DISABLE);
                    }
                    userPkgMapper.update(userPkg);
                }
            }
        }
    }

    /**
     * 更新用户车辆的预约标示
     * @param appointment
     */
    private void updateAppointmentFlag(Appointment appointment){
        UserCar usreCar=new UserCar();
        usreCar.setCarId(appointment.getCarId());
        usreCar.setAppointmentFlag("1");
        userCarMapper.update(usreCar);
    }
    
    public static void main(String[] args) {
    	BigDecimal price = new BigDecimal("0");
    	price.add(null);
	}

    private List<OrderDetail> getOrderDetail(List<ShopPackageVO> packages,Order order){
        BigDecimal price = new BigDecimal("0");
        //订单明细
        List<OrderDetail> detailList = new LinkedList<OrderDetail>();
        for(ShopPackageVO shopPackage : packages){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderDetailId(UUID.randomUUID().toString());
            orderDetail.setOrderId(order.getOrderId());
            orderDetail.setCreationDate(new Timestamp(new Date().getTime()));
            orderDetail.setLastUpdateDate(new Timestamp(new Date().getTime()));
            orderDetail.setDescription(shopPackage.getDescription());
            orderDetail.setPkgId(shopPackage.getPkgId());
            orderDetail.setPkgStartDate(shopPackage.getPkgStartDate());
            orderDetail.setPkgEndDate(shopPackage.getPkgEndDate());
            orderDetail.setPkgName(shopPackage.getPkgName());
            orderDetail.setPkgType(shopPackage.getPkgType());
            orderDetail.setPkgNum(shopPackage.getPkgNum());
            orderDetail.setPkgDiscount(order.getDiscount());
            orderDetail.setPkgPrice(shopPackage.getPkgPrice());
            detailList.add(orderDetail);
            //计算订单总金额
            price = price.add(shopPackage.getPkgPrice());
        }
        //设置订单总金额
        if(DictCode.SET_THE_ORDER.equals(order.getOrderType())){
            order.setPrice(price);
            order.setPaid(price.multiply(order.getDiscount()).setScale(2,BigDecimal.ROUND_UP));
        }

        return detailList;
    }

    /**
     * 获取订单信息
     * @return
     */
    private Order getOrder(Appointment appointment,String userId,BigDecimal pkgDiscount,String pkgType){
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setUserId(userId);
        order.setCarId(appointment.getCarId());
        order.setOrderTime(new Timestamp(new Date().getTime()));
        order.setShopId(appointment.getShopId());
        order.setCreationDate(new Timestamp(new Date().getTime()));
        order.setLastUpdateDate(new Timestamp(new Date().getTime()));
        order.setIsPay(DictCode.NON_PAYMENT);
        order.setDiscount(pkgDiscount);
        order.setIsUseCoupon(DictCode.UN_USE_COUPON);
        order.setIsUsePoint(DictCode.UN_USE_POINT);
        order.setPayType(DictCode.ALIPAY_PAYMENT);
        order.setSettleTime(null);
        order.setIsSettle(DictCode.NOT_SETTLE);
        order.setStatus(ConstantClass.ODER_STATUS_TWO);
        order.setAppointmentId(appointment.getAppointmentId());
        order.setAccountId(appointment.getAccountId());

        if(DictCode.MAINTENANCE.equals(pkgType)){
        	order.setStatus(ConstantClass.ODER_STATUS_ONE); //对于维修订单一开始并没有价格，设置为生效状态，商户维修订单那里设置价格后，状态才变为2
            order.setOrderType(DictCode.VEHICLE_MAINTENANCE_ORDER);//维修订单
        }else{
            order.setOrderType(DictCode.SET_THE_ORDER);//套餐订单
        }
        //获取最大订单号
        String maxOrderNo = orderMapper.findMaxOrderNo();
        order.setOrderNo(CommonUtils.generatorNo(maxOrderNo));
        return order;
    }

    /**
     * 将用户选择的套餐分为维修和非维修（维修的套餐要生成新的订单）
     * @param packages
     * @return
     */
    private Map<String,List<ShopPackageVO>> getPkgTypeMap(List<ShopPackageVO> packages){
        Map<String,List<ShopPackageVO>> pkgTypeMap = new HashMap<String, List<ShopPackageVO>>();
        for(ShopPackageVO shopPackage : packages){
            if(DictCode.MAINTENANCE.equals(shopPackage.getPkgType())){
                if(!pkgTypeMap.containsKey(DictCode.MAINTENANCE)){
                    pkgTypeMap.put(DictCode.MAINTENANCE, new LinkedList<ShopPackageVO>());
                }
                pkgTypeMap.get(DictCode.MAINTENANCE).add(shopPackage);
            }else{
                if(!pkgTypeMap.containsKey(DictCode.NOT_MAINTENANCE)){
                    pkgTypeMap.put(DictCode.NOT_MAINTENANCE, new LinkedList<ShopPackageVO>());
                }
                pkgTypeMap.get(DictCode.NOT_MAINTENANCE).add(shopPackage);
            }
        }
        return pkgTypeMap;
    }
    
    @Resource
	PlatformTransactionManager transaction;

    /**
     * 新增订单
     * @param appointment
     * @param packages
     * @param userId
     * @throws BizException
     */
    private Map<String,Object> insertOrder(Appointment appointment, List<ShopPackageVO> packages,String userId) throws BizException {
    	
    	TransactionDefinition td = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
    	TransactionStatus ts = null;
    	//将用户选择的套餐分为维修和非维修（维修的套餐要生成新的订单）
        Map<String,List<ShopPackageVO>> pkgTypeMap = getPkgTypeMap(packages);

        Map<String,Object> resultOrder = new HashMap<String,Object>();
        //判断订单生成形态
        int orderFlag = 0;
        //维修订单，维修订单明细
        Order wxOrder = null;
        List<OrderDetail> wxDetailList = null;
        for(Map.Entry<String, List<ShopPackageVO>> entry : pkgTypeMap.entrySet()){
        	synchronized (AppointmentBizImpl.class){
	    		ts = transaction.getTransaction(td);
	        	//计算非维修订单折扣
	        	BigDecimal pkgDiscount = arithmeticalDiscount(appointment.getShopId(),packages,entry.getKey());
	        	
	            //获取设置的订单信息
	            Order order = getOrder(appointment, userId, pkgDiscount,entry.getKey());
	
	            //获取设置的订单详情信息
	            List<OrderDetail> detailList = getOrderDetail(entry.getValue(), order);
	
	            //新增订单
	            orderMapper.save(order);
	
	            //新增订单明细信息
	            orderDetailMapper.insertbatch(detailList);
	
	            //更新用户套餐使用次数
	            updateUserPkgNum(order, detailList);
	
	            //查询订单相关信息
	            if(DictCode.SET_THE_ORDER.equals(order.getOrderType())){
	            	orderFlag += 1;
	                Map<String,Object> query = new HashMap<String,Object>();
	                query.put("orderId", order.getOrderId());
	                OrderVO orderVO = orderRestBiz.getOrderById(query);
	                List<OrderDetailVO> orderDetailList = orderRestDetailBiz.findOrderDetailList(query);
	                try {
						String discountStr = null;
						if(null!=order.getDiscount()){
							discountStr = new DecimalFormat("#.00").format(order.getDiscount().multiply(BigDecimal.valueOf(10)));
						}else{
							discountStr = "";
						}
						Map map = BaseFunction.convertBean(orderVO);
						map.put("discount", discountStr);
						resultOrder.put("order", map);
					} catch (Exception e) {
						e.printStackTrace();
					}
	                resultOrder.put("orderDetailList", orderDetailList);
	            }else{
	            	orderFlag += 2;
	            	wxOrder = order;
	            	wxDetailList = detailList;
	            }
	            transaction.commit(ts);
        	}
        }
        if(2 == orderFlag){
        	resultOrder.put("order", wxOrder);
        	resultOrder.put("orderDetailList", wxDetailList);
        }
        resultOrder.put("orderFlag", orderFlag);
        return resultOrder;
    }

    /**
     * 计算折扣
     * @return
     */
    private synchronized BigDecimal arithmeticalDiscount(String shopId,List<ShopPackageVO> packages,String pkgType){
        //获取店铺的折扣上线，折扣下线
        Shop shop = shopMapper.getById(shopId);
        
        BigDecimal price = new BigDecimal("0");
        for(ShopPackageVO shopPackage : packages){
            price = price.add(shopPackage.getPkgPrice());//计算订单总金额
        }

        BigDecimal pkgDiscount = new BigDecimal("1");

        //非维修订单时计算折扣，维修订单不计算折扣
        if(DictCode.NOT_MAINTENANCE.equals(pkgType)){
        	
        	//折扣上线
        	BigDecimal upperLimit = new BigDecimal("0.95");
        	//折扣下线(折扣上线-订单金额/奖池 )
        	BigDecimal lowerLimit = upperLimit.subtract(shop.getPond().divide(price,4)).setScale(2,BigDecimal.ROUND_UP);
        	
        	//当奖池金额超过订单金额时设置折扣下线为0，既“免单”
        	if(lowerLimit.compareTo(BigDecimal.valueOf(0))<0){
        		lowerLimit = BigDecimal.valueOf(0);
        	}
        	
        	//随机生成两个折扣,取平均值(折扣  = （上线-下线）* 0到1的随机数 + 下线)
        	BigDecimal pkgDiscountOne = upperLimit.subtract(lowerLimit).multiply(BigDecimal.valueOf(RandomUtils.nextDouble())).add(lowerLimit);
        	BigDecimal pkgDiscountTwo = upperLimit.subtract(lowerLimit).multiply(BigDecimal.valueOf(RandomUtils.nextDouble())).add(lowerLimit);
        	pkgDiscount = pkgDiscountOne.add(pkgDiscountTwo).divide(BigDecimal.valueOf(2)).setScale(2,BigDecimal.ROUND_UP);

        	//计算订单优惠金额（95折之后根据奖金池优惠的金额）
        	BigDecimal diff = price.subtract(price.multiply(pkgDiscount.add(BigDecimal.valueOf(0.05)))).setScale(2,BigDecimal.ROUND_UP);
      		//奖池中剩余的金额  = 原奖池金额 - 订单优惠金额 
      		shop.setPond(shop.getPond().subtract(diff));
      		shop.setLastUpdateDate(new Timestamp(new Date().getTime()));
      		shopMapper.update(shop);
        }
        
        return pkgDiscount;
    }
    
    /**
     * 批量新增预约服务信息
     * @param appointment
     */
    private void insertbatchAppointmentPackage(List<ShopPackageVO> packages,Appointment appointment){

        List<AppointmentPackage> appointmentPackages = new LinkedList<AppointmentPackage>();
        for(ShopPackageVO shopPackage:packages){
            AppointmentPackage appointmentPackage = new AppointmentPackage();
            appointmentPackage.setApId(UUID.randomUUID().toString());
            appointmentPackage.setAppointmentId(appointment.getAppointmentId());
            appointmentPackage.setCreationDate(new Timestamp(new Date().getTime()));
            appointmentPackage.setLastUpdateDate(new Timestamp(new Date().getTime()));
            appointmentPackage.setPkgName(shopPackage.getPkgName());
            appointmentPackage.setPkgId(shopPackage.getPkgId());
            appointmentPackage.setDescription(shopPackage.getDescription());

            appointmentPackages.add(appointmentPackage);
        }
        appointmentPackageMapper.insertbatch(appointmentPackages);
    }

    /**
     * 批量新增预约服务信息
     * @param appointment
     */
    private void addAppointment(Appointment appointment){
        appointment.setAppointmentId(UUID.randomUUID().toString());
        appointment.setCreationDate(new Timestamp(new Date().getTime()));
        appointment.setLastUpdateDate(new Timestamp(new Date().getTime()));
        appointment.setStatus(DictCode.VALID_STATUS);
        String maxAppNo = appointmentMapper.findMaxAppNo();
        appointment.setAppointmentNo(CommonUtils.generatorNo(maxAppNo));
        appointmentMapper.save(appointment);
    }

    /**
     * 新增预约信息
     * @param appointment
     */
    private void insertbatchAppointmentSer(List<String> serviceCodes,Appointment appointment){
        List<AppointmentService> serviceList = new ArrayList<AppointmentService>();
        for(String sc:serviceCodes){
            AppointmentService service = new AppointmentService();
            service.setServiceCode(sc);
            service.setOsId(UUID.randomUUID().toString());
            service.setAppointmentId(appointment.getAppointmentId());
            service.setCreationDate(new Timestamp(new Date().getTime()));
            service.setLastUpdateDate(new Timestamp(new Date().getTime()));
            serviceList.add(service);
        }
        appointmentSerMapper.insertbatch(serviceList);
    }

    /**
     * 更新用户套餐使用次数
     * @param appointment
     * @throws BizException
     */
    private void updateUserPkgNum(AppointmentVO appointment,List<AppointmentPackage> packages){
        for(AppointmentPackage pkg: packages){

            Map<String,Object> map = new HashMap<String,Object>();
            map.put("accountId", appointment.getAccountId());
            map.put("shopId", appointment.getShopId());
            map.put("pkgId", pkg.getPkgId());
            List<UserPackageVo>  userPackageList = userPkgMapper.findUserPackageListByParams(map);
            for(UserPackageVo userPackageVo : userPackageList){
                if(DictCode.COUNT_CARD.equals(userPackageVo.getPkgType())){//次数卡
                    UserPkg userPkg = new UserPkg();
                    userPkg.setUpId(userPackageVo.getUpId());
                    userPkg.setUseNum(userPackageVo.getUseNum()-1);
                    userPkg.setLeftNum(userPackageVo.getPkgNum()-userPkg.getUseNum());
                    if(null==userPackageVo.getLeftNum()&&userPackageVo.getLeftNum()==0){
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
     * 获取预约服务
     */
    @Override
    public List<DictLine> searchAppointmentServices() {
        return dictService.findDictListByHeadCode(DictType.APPOINTMENT_SERVICE);
    }

    /**
     * 查询改用户选择的套餐是否已存在付款的预约套餐
     * @param requestVo
     * @return
     */
    public List<UserPackageVo> searchUserPackageList(AppointmentRequestVo requestVo) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userId", requestVo.getUserId());
        map.put("shopId", requestVo.getAppointment().getShopId());
        map.put("pkgIds", requestVo.getSpIds());
        map.put("status", ConstantClass.ABLE);
        return userPkgMapper.findUserPackageListByParams(map);
    }

    /**
     * 检查套餐是否已经支付（0：全部未支付  1：部分支付  2：全部支付）
     * @return
     */
    public int checkPackageIsPay(AppointmentRequestVo requestVo,List<UserPackageVo> userPackageList){

        int flag = 0;
        if(requestVo.isOrderAllflag()){//如果点击全部订购，前端传入的标示为true
            return flag;
        }

        int spIdsSize = requestVo.getSpIds().size();
        int userPackageSize = userPackageList.size();

        if(spIdsSize != userPackageSize && userPackageSize != 0){
            flag = 1;
        }else if(spIdsSize == userPackageSize){
            flag = 2;
        }

        return flag;
    }

    /**
     * 新增维修预约
     */
    @Override
    public Map<String, Object> addAppointment(AppointmentRequestVo requestVo) throws BizException {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        //查询改用户选择的套餐是否已存在付款的预约套餐
        List<UserPackageVo> userPackageList = searchUserPackageList(requestVo);

        //检查套餐是否已经支付（0：全部未支付  1：部分支付  2：全部支付）
        int flag = checkPackageIsPay(requestVo,userPackageList);
        resultMap.put("flag", flag);

        if(flag == 0){
            Map<String,Object> resultOrder = saveAppointment(requestVo);
            resultMap.put("message", "全部未支付！");
            resultMap.putAll(resultOrder);
        }else if(flag == 1){
            resultMap.put("message", "部分支付！");
            resultMap.put("userPackages", userPackageList);
        }else if(flag == 2){
            resultMap.put("message", "全部支付！");
        }

        return  resultMap;
    }

    /**
     * 查询预约信息
     */
    @Override
    public List<AppointmentVO> searchAppointments(AppointmentRequestVo requestVo) {

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("accountId", requestVo.getAccountId());
        return appointmentMapper.findAppointmentListByParams(map);
    }

    /**
     * 获取签名
     * @throws BizException
     */
    @Override
    public Map<String, Object> alipayGetSign(AppointmentRequestVo requestVo) throws BizException {
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("orderNo", requestVo.getOrderNo());
        OrderVO orderVO = orderMapper.findListPageByParams(params).get(0);
        try {
            String sign = AlipayGetSign.getSign(orderVO);
            params.put("sign", sign);
        } catch (UnsupportedEncodingException e) {
            throw new BizException("001","获取签名发生错误！");
        }
        params.remove("orderId");
        return params;
    }
    
    

    @Override
	public Map<String, Object> wxpayGetSign(AppointmentRequestVo requestVo, String ip)
			throws BizException {
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("orderNo", requestVo.getOrderNo());
        OrderVO orderVO = orderMapper.findListPageByParams(params).get(0);
		String outTradeNo = orderVO.getOrderNo();
		int totalFee = orderVO.getPaid().multiply(new BigDecimal(100)).intValue();
		totalFee = 1;
		String body = "微信支付订单";
		String detail = null;
		SignModel sign = WxPayUtils.getSign(ip, outTradeNo, totalFee, body, detail,requestVo.getPlat());
		params.put("wxSign", sign);
        params.remove("orderId");
        return params;
    }


	/**
     * 获取预约明细
     */
    @Override
    public Map<String, Object> searchAppointmentDetail(AppointmentRequestVo requestVo) {
        Map<String, Object> resultMap = new HashMap<String,Object>();
        AppointmentVO appointment = appointmentMapper.getAppointmentById(requestVo.getAppointmentId());
        List<AppointmentServiceVO> services = appointmentSerMapper.findAppointmentServices(requestVo.getAppointmentId());
        List<AppointmentPackage> packages = appointmentPackageMapper.findAppointmentPackagebyParams(requestVo.getAppointmentId());

        resultMap.put("appointment", appointment);
        resultMap.put("services", services);
        resultMap.put("packages", packages);

        return resultMap;
    }


    /**
     * 取消预约
     */
    @Override
    public void cancelAppointment(Map<String, String> query) {

        appointmentMapper.updateAppointmentStatusById(query.get("appointmentId"));

        AppointmentVO appointment = appointmentMapper.getAppointmentById(query.get("appointmentId"));
        List<AppointmentPackage> packages = appointmentPackageMapper.findAppointmentPackagebyParams(query.get("appointmentId"));

        updateUserPkgNum(appointment, packages);

    }
}
