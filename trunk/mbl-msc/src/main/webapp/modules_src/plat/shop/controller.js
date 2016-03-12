'use strict';

define(['controllers','base/common','jquery','jqjstree','underscore'], function (kmpmAppControllers,common,$,jqjstree,underscore) {

	///////////////商店设置////////////////////
	kmpmAppControllers.controller('PlatShopCtrl', ['$rootScope','$scope','$location','$timeout','$filter','$state','msObj','modalService','$stateParams','dictService','$modal',
	  function($rootScope,$scope,$location,$timeout,$filter,$state,msObj,modalService,$stateParams,dictService) {
			
		//字典加载
		dictService($scope,[{'cooperate_status':'dict_cooperate_status'}]);
		dictService($scope,[{'balance_type':'dict_balance_type'}]);
		dictService($scope,[{'shop_status':'dict_shop_status'}]);
		dictService($scope,[{'shop_type':'dict_shop_type'}]);
		dictService($scope,[{'week':'dict_week'}]);
		dictService($scope,[{'day':'dict_day'}]);
		
		$scope.search = {};
		$scope.dataList = {data:[]};
		$scope.pkgList = {data:[]};
		$scope.brandList = {data:[]};
		
		$scope.shopPkgDataList = {data:[]};
		$scope.shopCarDataList = {data:[]};
		//查询门店列表
		$scope.onsearch = function() {
			$scope.dataList = msObj.post({
				md : 'plat',
				use : 'shop',
				opt : 'findShopList',
				LOADING:(new Date).getTime()
			}, $scope.search, function(obj) {
				$scope.dataList = obj;
			});
		};
		//门店维护车辆，查询车辆品牌信息
		$scope.onAddShopCarOpen = function(){
			$scope.brandList = msObj.post({
				md : 'plat',
				use : 'shop',
				opt : 'getBrandList',
				LOADING:(new Date).getTime()
			}, $scope.search, function(obj) {
				if(obj.errorCode == "success"){
					$scope.brandList = obj.result;
				}else{
					common.dialogSuccess("获取门店品牌失败！");
				}
			});
		};
		
		$scope.onsearch();
		
		//添加标识
		$scope.addFlag = true;
		///门店对象
		$scope.shopVO = {};
		//门店车辆对象,
		$scope.shopCarVO = {};
		
		//添加门店
		$scope.add = function(){
			$scope.addFlag = true;
			$scope.shopVO = {'shopStatus':'1','cooperateStatus':'1'};
			
			$("#shopEditModal").modal("show");
		};
		
		//修改门店
		$scope.edit = function(shopId){
			$scope.addFlag = false;
			$scope.packageVO = {};
			msObj.post({ md:'plat', use:'shop', opt:'getShopById'}, {shopId:shopId}, function(obj){
            	$scope.hideMask();
            	if (obj.errorCode==='success'){		
            		$scope.shopVO = obj.result;
                }
			});
			$("#shopEditModal").modal("show");
			$("#baseinfo-form").valid();
			$('.has-error').each(function(index){
				$(this).removeClass("has-error");
			});
			
			var balanceType = $scope.shopVO.balanceType;
			if(balanceType == 1) {//日
				$scope.dict_object = {};
			} else if(balanceType == 2) {//周
				$scope.dict_object = $scope.dict_week;
			} else if(balanceType == 3) {//月
				$scope.dict_object =  $scope.dict_day;
			} else{
				$scope.dict_object = {};
			}
		};
		
		//删除门店
		$scope.delByShopId = function(shopId){
			common.dialogSuccess("确认删除？",function(){
				msObj.post({ md:'plat', use:'shop', opt:'delShopById'}, {shopId:shopId}, function(obj){
					if (obj.errorCode==='success'){
	            		common.noticeSuccess("提示","删除成功！",false);
	            		$scope.onsearch();
	                }else {
	                	common.dialogFailure("删除失败，"+obj.errorMsg); 
		            }
				});
			});
		};
		
		//维护门店车辆
		$scope.editShopCar = function(scId){
			$scope.addFlag = false;
			$scope.shopPackageVO = {};
			msObj.post({ md:'plat', use:'shop', opt:'getShopCarById'}, {scId:scId}, function(obj){
            	$scope.hideMask();
            	if (obj.errorCode==='success'){		
            		$scope.shopCarVO = obj.result;
                }
			});
		};
		//删除门店车辆
		$scope.deleteShopCar = function(scId){
			common.dialogSuccess("确认删除？",function(){
				$scope.showMask();
				msObj.post({ md:'plat', use:'shop', opt:'deleteShopCar'},{scId:scId}, function(obj){
                	$scope.hideMask();
                	if (obj.errorCode==='success'){
                		common.noticeSuccess("提示","删除成功！",false);
                		$scope.addShopCarType($scope.shopCarVO.shopId);
                    }else {
  	                	common.dialogFailure("删除失败，"+obj.errorMsg); 
  	                }
				});   	
			});
		};
		//情况form
		$scope.clearForm = function(shopId){
			$scope.shopPackageVO = {'shopId':shopId};
			$scope.shopCarVO = {'shopId':shopId};
		};
		//去设置门店套餐
		$scope.addpkg = function(shopId){
			$state.go("app.platShop.pkg",{shopId:shopId});
		};
		//维护门店车辆
		$scope.addShopCarType = function(shopId){
			$scope.addFlag = true;
			$scope.shopCarVO = {};
			$scope.search = {shopId:shopId};
			$scope.shopCarDataList = 
				msObj.post({ 
					md:'plat', 
					use:'shop', 
					opt:'findShopCarList',
					LOADING:(new Date).getTime()}, 
					{shopId:shopId}, 
					function(obj){
		            	$scope.hideMask();
	            		$scope.shopCarDataList = obj;
	            		$scope.onAddShopCarOpen();
	        			$scope.shopCarVO = {'shopId':shopId};
					});   
			$("#shopCarEditModal").modal("show");
		};
		//保存门店车辆
		$scope.saveShopCar = function(){
			if($("#baseinfo-shopcar-form").valid()){
				common.dialogSuccess("确认提交？",function(){
					$scope.showMask();
					msObj.post({ md:'plat', use:'shop', opt:'saveOrUpdateShopCar'}, $scope.shopCarVO, function(obj){
	                	$scope.hideMask();
	                	if (obj.errorCode==='success'){
	                		common.noticeSuccess("提示",($scope.addFlag?"添加":"修改")+"成功！",false);
	                		$scope.addShopCarType($scope.shopCarVO.shopId);
	                    }else {
	  	                	common.dialogFailure(($scope.addFlag?"添加":"修改")+"失败，"+obj.errorMsg); 
	  	                }
					});   	
				});
			}
		};
		//图片上传
		$scope.uploadPhoto = function(){
			modalService.showUploadFileAssist({uploadType:'IMAGE',uploadPath:'shop',uploadTitle:'图片上传'},function(filePath){
				$scope.shopVO.url = filePath;
        	});
		};
		$scope.dict_object = {};
		$scope.changeRebateDate =  function(){
			var balanceType = $scope.shopVO.balanceType;
			if(balanceType == 1) {//日
				$scope.dict_object = {};
			} else if(balanceType == 2) {//周
				$scope.dict_object = $scope.dict_week;
			} else if(balanceType == 3) {//月
				$scope.dict_object =  $scope.dict_day;
			} else{
				$scope.dict_object = {};
			}
		};
		//确定
		$scope.saveOk = function(){
			if($("#baseinfo-form").valid()){
				msObj.post({
					md : "plat",
					use : "shop",
					opt : 'checkShopCodeIsExsist'
				},{'shopId':$scope.shopVO.shopId,'shopCode':$scope.shopVO.shopCode,'accountCode':$scope.shopVO.accountCode,'accountId':$scope.shopVO.accountId},function(obj){
					if (obj.errorCode==='success'){
						common.dialogSuccess("确认提交？",function(){
							$scope.showMask();
							msObj.post({ md:'plat', use:'shop', opt:'saveOrUpdateShop'}, $scope.shopVO, function(obj){
			                	$scope.hideMask();
			                	if (obj.errorCode==='success'){
			                		common.noticeSuccess("提示",($scope.addFlag?"添加":"修改")+"成功！",false);
			                		$scope.onsearch();
			                    }else {
			  	                	common.dialogFailure(($scope.addFlag?"添加":"修改")+"失败，"+obj.errorMsg); 
			  	                }
							});   	
							$("#shopEditModal").modal("hide");
						});
					} else {
						common.dialogSuccess(obj.errorMsg);
					}
				});
			}
		};
	}]);
	
	kmpmAppControllers.controller('PlatShopPkgCtrl', ['$rootScope','$scope','$location','$timeout','$filter','$state','msObj','modalService','$stateParams','dictService','$modal',
	                                         	  function($rootScope,$scope,$location,$timeout,$filter,$state,msObj,modalService,$stateParams,dictService) {
	                                         			
 		//字典加载
 		dictService($scope,[{'cooperate_status':'dict_cooperate_status'}]);
 		dictService($scope,[{'balance_type':'dict_balance_type'}]);
 		dictService($scope,[{'shop_status':'dict_shop_status'}]);
 		dictService($scope,[{'shop_type':'dict_shop_type'}]);
 		dictService($scope,[{'pkg_type':'dict_pkg_type'}]);
 		
 		$scope.search = {shopId:$stateParams.shopId};
 		
 		$scope.shopPkgDataList = {data:[]};
 		//查询门店列表
 		$scope.onsearch = function() {
			$scope.shopPkgDataList = 
			msObj.post({ 
				md:'plat', 
				use:'shop', 
				opt:'findShopPackageList'}, 
				$scope.search, 
				function(obj){
            		$scope.shopPkgDataList = obj;
			});   
		};
		$scope.onsearch();
 
		//添加标识
		$scope.addFlag = true;
		
		$scope.shopPackageVO = {shopId:$stateParams.shopId};
		
		//添加
		$scope.addShopPkg = function(){
			$scope.addFlag = true;
			$("#myLargeModalLabel").text("添加套餐");
			$scope.shopPackageVO = {shopId:$stateParams.shopId};
			$("#shopPkgEditModal").modal("show");
		};
		
		//修改
		$scope.editShopPkg = function(id){
			$scope.addFlag = false;
			$("#myLargeModalLabel").text("修改套餐");
			$scope.shopPackageVO = {shopId:$stateParams.shopId};
			msObj.post({ md:'basic', use:'pkg', opt:'getPackageById'}, {id:id}, function(obj){
            	$scope.hideMask();
            	if (obj.errorCode==='success'){		
            		$scope.shopPackageVO = obj.result;
                }
			});   
			$("#shopPkgEditModal").modal("show");
		};
		
		//维护门店套餐
		$scope.editShopPkg = function(spId){
			$scope.addFlag = false;
			$("#myLargeModalLabel").text("修改套餐");
			$scope.shopPackageVO = {shopId:$stateParams.shopId};
			msObj.post({ md:'plat', use:'shop', opt:'getShopPackage'}, {spId:spId}, function(obj){
            	$scope.hideMask();
            	if (obj.errorCode==='success'){		
            		$scope.shopPackageVO = obj.result;
                }
			});
			$("#shopPkgEditModal").modal("show");
		};
		//查看门店套餐
		$scope.viewShopPkg = function(spId){
			$scope.viewFlag = true;
			$("#myLargeModalLabel").text("套餐查看");
			$scope.shopPackageVO = {shopId:$stateParams.shopId};
			msObj.post({ md:'plat', use:'shop', opt:'getShopPackage'}, {spId:spId}, function(obj){
            	if (obj.errorCode==='success'){		
            		$scope.shopPackageVO = obj.result;
                }
			});
			$("#shopPkgEditModal").modal("show");
		};
		//删除门店套餐
		$scope.deleteShopPkg = function(spId){
			common.dialogSuccess("确认删除？",function(){
				$scope.showMask();
				msObj.post({ md:'plat', use:'shop', opt:'delShopPackage'},{spId:spId}, function(obj){
                	$scope.hideMask();
                	if (obj.errorCode==='success'){
                		common.noticeSuccess("提示","删除成功！",false);
                		$scope.onsearch();
                    }else {
  	                	common.dialogFailure("删除失败，"+obj.errorMsg); 
  	                }
				});   	
			});
		};
		
		//确定
		$scope.saveOk = function(){
			if($("#baseinfo-shoppkg-form").valid()){
				common.dialogSuccess("确认提交？",function(){
					$scope.showMask();
					msObj.post({ md:'plat', use:'shop', opt:'saveOrUpdateShopPackage'}, $scope.shopPackageVO, function(obj){
	                	$scope.hideMask();
	                	if (obj.errorCode==='success'){
	                		common.noticeSuccess("提示",($scope.addFlag?"添加":"修改")+"成功！",false);
	                		$scope.onsearch();
	                    }else {
	  	                	common.dialogFailure(($scope.addFlag?"添加":"修改")+"失败，"+obj.errorMsg); 
	  	                }
					});
					$("#shopPkgEditModal").modal("hide");
				});
			}
		};
		
 	}]);

  	return kmpmAppControllers;

});



