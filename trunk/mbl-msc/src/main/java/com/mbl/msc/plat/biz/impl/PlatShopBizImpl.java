package com.mbl.msc.plat.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbl.common.bean.Account;
import com.mbl.common.bean.Shop;
import com.mbl.common.framework.exception.excepts.BizException;
import com.mbl.common.framework.page.RowBounsUtil;
import com.mbl.common.framework.vo.datagrid.ResultVO;
import com.mbl.common.mapper.AccountMapper;
import com.mbl.common.mapper.ShopCarMapper;
import com.mbl.common.mapper.ShopMapper;
import com.mbl.common.mapper.ShopPackageMapper;
import com.mbl.common.util.CommonUtils;
import com.mbl.common.vo.AccountVO;
import com.mbl.common.vo.ShopVO;
import com.mbl.msc.plat.biz.PlatShopBiz;

/**
 * 门店逻辑类
 * 
 * @author jjj
 * @create 2015年12月10日 上午12:22:01
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service(value = "platShopBiz")
@Transactional
public class PlatShopBizImpl implements PlatShopBiz {

	@Resource
	private ShopMapper shopMapper;
	
	@Resource
	private AccountMapper accountMapper;
	
	@Resource
	private ShopPackageMapper shopPackageMapper;
	
	@Resource
	private ShopCarMapper shopCarMapper;
	
	/****
	 * 根据code获得门店详情
	 * @param shopCode
	 * @return
	 */
	public ShopVO getShopByCode(String shopCode) {
		return shopMapper.getShopByCode(shopCode);
	}
	
	/****
	 * 根据id获得门店详情
	 * @param shopId
	 * @return
	 */
	public ShopVO getShopById(String shopId) {
		return shopMapper.getShopById(shopId);
	}
	
	/**
	 * 通过店铺id删除店铺 
	 * @param shopId  
	 * @throws Exception 
	 * @see com.mbl.msc.plat.biz.PlatShopBiz#delShopById(java.lang.String)
	 */
	@Override
	public void delShopById(String shopId) throws BizException {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("shopId", shopId);
		long i = shopPackageMapper.countShopPackageListByParams(params);
		if(i>0){
			throw new BizException("-1","存在套餐信息,不能删除");
		}
		shopCarMapper.delByShopId(shopId);
		shopMapper.delById(shopId);
	}
	
	/****
	 * 根据code查询门店编号是否存在
	 * @param shopCode
	 * @return
	 */
	public String checkShopCodeIsExists(String shopCode,String shopId) {
		ShopVO checkShop = shopMapper.getShopByCode(shopCode);
		
		if(checkShop!=null && StringUtils.equals(shopId, checkShop.getShopId()) == false) {
			return ResultVO.FAIL;//"门店编号为" + shopCode +"的门店已存在"
		} else {
			return ResultVO.SUCCESS;
		}
	}
	
	/***
	 * 查询accountCode是否存在
	 * @param accountCode
	 * @return
	 */
	public String checkAccountCodeIsExists(String accountCode,String accountId) {
		AccountVO vo =  accountMapper.findAccountByCode(accountCode);
		if(vo!=null && StringUtils.equals(accountId, vo.getAccountId()) == false) {
			return ResultVO.FAIL;//"门店编号为" + shopCode +"的门店已存在"
		} else {
			return ResultVO.SUCCESS;
		}
	}
	
	
	/****
	 * 保存门店信息
	 * @param shopVO
	 * @return
	 * @throws Exception 
	 */
	public int saveShop(ShopVO shopVO) throws Exception {
//		if(shopVO == null) {
//			throw new BizException(ResultVO.FAIL,"门店为空");
//		}
//		ShopVO checkShop = shopMapper.getShopByCode(shopVO.getShopCode());
//		
//		if(checkShop!=null && StringUtils.equals(shopVO.getShopId(), checkShop.getShopId()) == false) {
//			throw new BizException(ResultVO.FAIL,"门店编号为" + shopVO.getShopCode() +"的门店已存在");
//		}
		
		Shop shop = new Shop();
		BeanUtils.copyProperties(shopVO, shop);
		
		if(StringUtils.isNotEmpty(shop.getShopId())) {
			if(StringUtils.isNotEmpty(shopVO.getAccountId())) {
				Account account = new Account();
				account.setAccountId(shopVO.getAccountId());
				account.setAccountCode(shopVO.getAccountCode());
				accountMapper.update(account);
			} else {
				Account account = new Account();
				account.setAccountId(UUID.randomUUID().toString());
				account.setAccountCode(shopVO.getAccountCode());
				account.setPwd(CommonUtils.md5Encode("123456"));
				account.setShopId(shopVO.getShopId());
				account.setStatus("1");
				account.setAccountType("2");
				accountMapper.save(account);
			}
			return shopMapper.update(shop);
		} else {
			shop.setShopId(UUID.randomUUID().toString());
			
			Account account = new Account();
			account.setAccountId(UUID.randomUUID().toString());
			account.setAccountCode(shopVO.getAccountCode());
			account.setPwd(CommonUtils.md5Encode("123456"));
			account.setShopId(shopVO.getShopId());
			account.setStatus("1");
			account.setAccountType("3");
			accountMapper.save(account);
			return shopMapper.save(shop);
		}
	}

	/****
	 * 分页查询
	 * @param map
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<ShopVO> findShopListByParams(Map<String, Object> map, Integer page, Integer pageSize) {
		return shopMapper.findShopListByParams(map, RowBounsUtil.getRowBounds(page, pageSize));
	}
	
	/***
	 * 分页查询总数
	 * @param map
	 * @return
	 */
	public Long countShopListByParams(Map<String, Object> map) {
		return shopMapper.countShopListByParams(map);
	}
}
