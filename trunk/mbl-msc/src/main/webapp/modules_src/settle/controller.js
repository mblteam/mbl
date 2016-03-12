'use strict';

define(['controllers','base/common','jquery','jqjstree','underscore'], function (kmpmAppControllers,common,$,jqjstree,underscore) {

	///////////////品牌设置////////////////////
	kmpmAppControllers.controller('SettleCtrl', ['$rootScope','$scope','$location','$timeout','$filter','msObj','modalService','$stateParams','dictService',
	  function($rootScope,$scope,$location,$timeout,$filter,msObj,modalService,$stateParams,dictService) {
		dictService($scope,[{'balance_type':'dict_balance_type'}]);
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
			$scope.search = {};
			$scope.dataList = msObj.post({
				use : 'settle',
				opt : 'findOrderList',
				LOADING:(new Date).getTime()
			}, $scope.search, function(obj) {
				$scope.dataList = obj;
			});
		};
		$scope.settleVO = {};
		$scope.onsearch();
		
		//查看
		$scope.settle = function(){
			modalService.showShopMutiAssist({},function(obj){
				console.log(obj.result);
			});
			 //$scope.settleVO = {};
			 //$("#settleModal").modal("show");
		};
		
		//确定
		$scope.saveSettle = function(){
			if($("#baseinfo-settle-form").valid()){
				common.dialogSuccess("确认提交？",function(){
					$scope.showMask();
					msObj.post({ use:'settle', opt:'settleByParams'}, $scope.settleVO, function(obj){
	                	$scope.hideMask();
	                	if (obj.errorCode==='success'){
	                		common.noticeSuccess("提示","结算成功！",false);
	                		$scope.onsearch();
	                    }else {
	  	                	common.dialogFailure("结算失败，"+obj.errorMsg); 
	  	                }
					});
					$("#settleModal").modal("hide");
					
					$scope.onsearch();
				});
			}
		};
		
	}]);

  	return kmpmAppControllers;

});



