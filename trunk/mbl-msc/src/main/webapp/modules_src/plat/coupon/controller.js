'use strict';

define(['controllers','base/common','jquery','jqjstree','underscore'], function (kmpmAppControllers,common,$,jqjstree,underscore) {

	///////////////商店设置////////////////////
	kmpmAppControllers.controller('CouponCtrl', ['$rootScope','$scope','$location','$timeout','$filter','msObj','modalService','$stateParams','dictService','$modal',
	  function($rootScope,$scope,$location,$timeout,$filter,msObj,modalService,$stateParams,dictService) {
			
		//字典加载
		dictService($scope,[{'coupon_type':'dict_coupon_type'}]);
		dictService($scope,[{'status':'dict_status'}]);
		
		$scope.search = {};
		$scope.dataList = {data:[]};
		//获得字典数据 
		$scope.onsearch = function() {
			$scope.dataList = msObj.post({
				md : 'plat',
				use : 'platcoupon',
				opt : 'findCouponList',
				LOADING:(new Date).getTime()
			}, $scope.search, function(obj) {
				$scope.dataList = obj;
			});
		};
		
		$scope.onsearch();
		
		//添加标识
		$scope.addFlag = true;
		
		$scope.couponVO = {};
		//添加
		$scope.add = function(){
			$scope.addFlag = true;
			$scope.couponVO = {'couponStatus':'1'};
			
			$("#couponEditModal").modal("show");
		};
		
		//修改
		$scope.edit = function(couponId){
			$scope.addFlag = false;
			$scope.packageVO = {};
			msObj.post({ md:'plat', use:'platcoupon', opt:'getCouponById'}, {couponId:couponId}, function(obj){
            	$scope.hideMask();
            	if (obj.errorCode==='success'){		
            		$scope.couponVO = obj.result;
                }
			});
			$("#couponEditModal").modal("show");
			$("#baseinfo-form").valid();
			$('.has-error').each(function(index){
				$(this).removeClass("has-error");
			});
		};
		
		//确定
		$scope.saveOk = function(){
			if($("#baseinfo-form").valid()){
				
				common.dialogSuccess("确认提交？",function(){
					$scope.showMask();
					msObj.post({ md:'plat', use:'platcoupon', opt:'saveCoupon',LOADING:(new Date).getTime()}, $scope.couponVO, function(obj){
	                	$scope.hideMask();
	                	if (obj.errorCode==='success'){
	                		common.noticeSuccess("提示",($scope.addFlag?"添加":"修改")+"成功！",false);
	                		$scope.onsearch();
	                    }else {
	  	                	common.dialogFailure(($scope.addFlag?"添加":"修改")+"失败，"+obj.errorMsg); 
	  	                }
					});
					$("#couponEditModal").modal("hide");
				});
			}
		};
		
		$scope.disabled = function(couponId){
			common.dialogSuccess("确认失效？",function(){
				$scope.showMask();
				msObj.post({ md:'plat', use:'platcoupon', opt:'disabledCoupon',LOADING:(new Date).getTime()}, {couponId:couponId}, function(obj){
                	$scope.hideMask();
                	if (obj.errorCode==='success'){
                		common.noticeSuccess("提示","失效成功！",false);
                		$scope.onsearch();
                    }else {
  	                	common.dialogFailure("失效失败，"+obj.errorMsg); 
  	                }
				});
			});
		}
	}]);

  	return kmpmAppControllers;

});



