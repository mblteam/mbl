'use strict';

define(['controllers','base/common','jquery','jqjstree','underscore'], function (kmpmAppControllers,common,$,jqjstree,underscore) {

	///////////////预约单////////////////////
	kmpmAppControllers.controller('PlatAppointmentCtrl', ['$rootScope','$scope','$location','$timeout','$filter','msObj','modalService','$stateParams','dictService',
	  function($rootScope,$scope,$location,$timeout,$filter,msObj,modalService,$stateParams,dictService) {
			
		//字典加载
		dictService($scope,[{'appointment_status':'dict_appointment_status'}]);
		
		$scope.search = {};
		$scope.appointmentPkgList = {};
		$scope.appointmentSerList = {};
		
		$scope.dataList = {data:[]};
		//查询
		$scope.onsearch = function() {
			$scope.dataList = {};
			$scope.dataList = msObj.post({
				md : 'plat',
				use : 'platAppointment',
				opt : 'findAppointmentList',
				LOADING:(new Date).getTime()
			}, $scope.search, function(obj) {
				$scope.dataList = obj;
			});
		};
		
		$scope.onsearch();
		
		//预约详情
		$scope.showAppointmentDetail = function(appointmentId){
			
			$scope.search1 = {appointmentId:appointmentId};
			$scope.appointmentPkgList = {};
			//获得预约套餐
			$scope.appointmentPkgList = msObj.post({
				md : 'plat',
				use : 'platAppointment',
				opt : 'getAppointmentPkgById',
				LOADING:(new Date).getTime()
			}, $scope.search1, function(obj) {
				$scope.appointmentPkgList = obj.result;
			});
			
			$scope.appointmentSerList = {};
			//获得预约服务
			$scope.appointmentSerList = msObj.post({
				md : 'plat',
				use : 'platAppointment',
				opt : 'getAppointmentSerById',
				LOADING:(new Date).getTime()
			}, $scope.search1, function(obj) {
				$scope.appointmentSerList = obj.result;
			});
			$("#appointmentModal").modal("show");
		};
		
		$scope.disabledAppointment = function(appointmentId){
			common.dialogSuccess("确认取消？",function(){
				$scope.showMask();
				msObj.post({md:'plat', use:'platAppointment', opt:'updateAppointmentStatusById'},{appointmentId:appointmentId}, function(obj){
                	$scope.hideMask();
                	if (obj.errorCode==='success'){
                		common.noticeSuccess("提示","取消成功！",false);
                		$scope.onsearch();
                    }else {
  	                	common.dialogFailure("取消失败，"+obj.errorMsg); 
  	                }
				});   	
			});
		};
		
		
	}]);

  	return kmpmAppControllers;

});



