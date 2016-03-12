'use strict';

define(['controllers','base/common','jquery','jqjstree','underscore'], function (kmpmAppControllers,common,$,jqjstree,underscore) {

	///////////////套餐设置////////////////////
	kmpmAppControllers.controller('PkgCtrl', ['$rootScope','$scope','$location','$timeout','$filter','msObj','modalService','$stateParams','dictService',
	  function($rootScope,$scope,$location,$timeout,$filter,msObj,modalService,$stateParams,dictService) {
			
		//字典加载
		dictService($scope,[{'pkg_type':'dict_pkg_type'}],function(){
			$scope.dictMap_pkg_type = $filter('treeJSON')($scope.dict_pkg_type,['dictCode']);
		});
		
		$scope.search = {};
		$scope.dataList = {data:[]};
		//获得字典数据 
		$scope.onsearch = function() {
			$scope.dataList = msObj.post({
				md : 'basic',
				use : 'pkg',
				opt : 'findPkgList',
				LOADING:(new Date).getTime()
			}, $scope.search, function(obj) {
				$scope.dataList = obj;
			});
		};
		
		$scope.onsearch();
		
		//添加标识
		$scope.addFlag = true;
		
		$scope.packageVO = {};
		
		//添加
		$scope.add = function(){
			$scope.addFlag = true;
			$("#myLargeModalLabel").text("添加套餐");
			$scope.packageVO = {};
			$("#pkgEditModal").modal("show");
		};
		
		//修改
		$scope.edit = function(id){
			$scope.addFlag = false;
			$("#myLargeModalLabel").text("修改套餐");
			$scope.packageVO = {};
			msObj.post({ md:'basic', use:'pkg', opt:'getPackageById'}, {id:id}, function(obj){
            	$scope.hideMask();
            	if (obj.errorCode==='success'){		
            		$scope.packageVO = obj.result;
                }
			});   
			$("#pkgEditModal").modal("show");
		};
		
		//删除
		$scope.remove = function(id){
			$scope.addFlag = false;
			$scope.packageVO = {};
			$scope.showMask();
			msObj.post({ md:'basic', use:'pkg', opt:'delPackageById'}, {id:id}, function(obj){
            	$scope.hideMask();
            	if (obj.errorCode==='success'){	
            		$scope.onsearch();
            		common.noticeSuccess("提示","成功！",false);
                }else {
                	common.dialogFailure("失败，"+obj.errorMsg); 
                }
			});   
		};
		
		//确定
		$scope.saveOk = function(){
			if($("#baseinfo-form").valid()){
				common.dialogSuccess("确认提交？",function(){
					$scope.showMask();
					msObj.post({ md:'basic', use:'pkg', opt:'saveOrUpdatePkg'}, $scope.packageVO, function(obj){
	                	$scope.hideMask();
	                	if (obj.errorCode==='success'){
	                		common.noticeSuccess("提示",($scope.addFlag?"添加":"修改")+"成功！",false);
	                		$scope.onsearch();
	                    }else {
	  	                	common.dialogFailure(($scope.addFlag?"添加":"修改")+"失败，"+obj.errorMsg); 
	  	                }
					});   	
					$("#pkgEditModal").modal("hide");
				});
			}
		};
	}]);

  	return kmpmAppControllers;

});



