'use strict';

define(['controllers','base/common','jquery','jqjstree','underscore'], function (kmpmAppControllers,common,$,jqjstree,underscore) {

	///////////////品牌设置////////////////////
	kmpmAppControllers.controller('OrderCtrl', ['$rootScope','$scope','$location','$timeout','$filter','msObj','modalService','$stateParams','dictService',
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
				use : 'order',
				opt : 'findOrderList',
				LOADING:(new Date).getTime()
			}, $scope.search, function(obj) {
				$scope.dataList = obj;
			});
		};
		
		$scope.onsearch();
		
		 // 添加
	    $scope.pushLoop = function (array,obj){
		  if(angular.isArray(array)){
			  $scope.isAdd = true;
	          array.unshift(obj);
	      }
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



