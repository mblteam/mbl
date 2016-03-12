'use strict';

define(['controllers','base/common','jquery','jqjstree','underscore'], function (kmpmAppControllers,common,$,jqjstree,underscore) {

	///////////////品牌设置////////////////////
	kmpmAppControllers.controller('RepairOrderCtrl', ['$rootScope','$scope','$location','$timeout','$filter','msObj','modalService','$stateParams','dictService',
	  function($rootScope,$scope,$location,$timeout,$filter,msObj,modalService,$stateParams,dictService) {
			
		//字典加载
		dictService($scope,[{'order_status':'dict_order_status'},{'pkg_type':'dict_pkg_type'},{'order_type':'dict_order_type'}],function(){
			$scope.dictMap_order_status = $filter('treeJSON')($scope.dict_order_status,['dictCode']);
			$scope.dictMap_pkg_type = $filter('treeJSON')($scope.dict_pkg_type,['dictCode']);
			$scope.dictMap_order_type = $filter('treeJSON')($scope.dict_order_type,['dictCode']);
		});
		
		$scope.search = {};
		$scope.dataList = {data:[]};
		//获得字典数据 
		$scope.onsearch = function() {
			$scope.dataList = msObj.post({
				md: 'merchant',
				use : 'order',
				opt : 'findRepairOrderList',
				LOADING:(new Date).getTime()
			}, $scope.search, function(obj) {
				$scope.dataList = obj;
			});
		};
		
		$scope.onsearch();
		
		$scope.settingPrice = function(array,ind){
			common.dialogSettingPrice(function(price){
		  		msObj.post({md:"merchant",use:"order",opt:'setRepairOrderPrice'},{price:price,orderId:array[ind]['orderId']},function(obj){
		  			if (obj.errorCode==='success'){		
		  				array[ind]['price'] = obj.result.price;
		  				array[ind]['paid'] = obj.result.paid;
		  				array[ind]['discount'] = obj.result.discount;
		  				array[ind]['status'] = obj.result.status;
		  				common.noticeSuccess("提示","设置价格成功,等待车主支付!",false);
		            }else {
		                common.dialogFailure("设置失败，"+obj.errorMsg); 
		            }
		        });
		  	});
		};
		
		//订单详情
		$scope.showOrderDetail = function(orderId){
			$("#orderDetailModal").modal("show");
			$scope.search1 = {orderId:orderId};
			$scope.orderDetailList = {data:[]};
			//获得字典数据 
			$scope.orderDetailList = msObj.post({
				use : 'order',
				opt : 'findOrderDetail',
				LOADING:(new Date).getTime()
			}, $scope.search1, function(obj) {
				$scope.orderDetailList = obj.result["orderDetailList"];
			});
		}
	}]);

  	return kmpmAppControllers;

});



