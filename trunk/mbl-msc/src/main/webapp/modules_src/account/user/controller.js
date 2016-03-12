'use strict';

define(['controllers','base/common','jquery','jqjstree','underscore'], function (kmpmAppControllers,common,$,jqjstree,underscore) {

	///////////////用户信息设置////////////////////
	kmpmAppControllers.controller('UserCtrl', ['$rootScope','$scope','$location','$timeout','$filter','msObj','modalService','$stateParams','dictService',
	  function($rootScope,$scope,$location,$timeout,$filter,msObj,modalService,$stateParams,dictService) {
		//字典状态
		dictService($scope,[{'status':'dict_status'}],function(){
			$scope.dictMap_status = $filter('treeJSON')($scope.dict_status,['dictCode']);
		});
		//字典 性别
		dictService($scope,[{'sex':'dict_sex'}],function(){
			$scope.dictMap_sex = $filter('treeJSON')($scope.dict_sex,['dictCode']);
		});
		$scope.search = {};
		$scope.dataList = {data:[]};
		//获得字典数据 
		$scope.onsearch = function() {
			$scope.dataList = msObj.post({
				md : 'account',
				use : 'user',
				opt : 'findUserList',
				LOADING:(new Date).getTime()
			}, $scope.search, function(obj) {
				$scope.dataList = obj;
			});
		};
		
		$scope.onsearch();
		
		//添加标识
		$scope.addFlag = true;
		
		$scope.user = {};
		
		//添加
		$scope.add = function(){
			$scope.addFlag = true;
			$("#myLargeModalLabel").text("添加用户信息");
			$scope.user = {};
			$("#userEditModal").modal("show");
		};
		
		//修改
		$scope.edit = function(id){
			$scope.addFlag = false;
			$("#myLargeModalLabel").text("修改用户信息");
			$scope.user = {};
			msObj.post({ md:'account', use:'user', opt:'getById'}, {id:id}, function(obj){
            	$scope.hideMask();
            	if (obj.errorCode==='success'){		
            		$scope.user = obj.result;
                }
			});   
			$("#userEditModal").modal("show");
		};
		
		//删除
		$scope.remove = function(id){
			common.dialogSuccess("确定删除信息？",function(){
				$scope.addFlag = false;
				$scope.user = {};
				$scope.showMask();
				msObj.post({ md:'account', use:'user', opt:'delById'}, {id:id}, function(obj){
	            	$scope.hideMask();
	            	if (obj.errorCode==='success'){	
	            		$scope.onsearch();
	            		common.noticeSuccess("提示","删除成功！",false);
	                }else {
	                	common.dialogFailure("删除失败,"+obj.errorMsg); 
	                }
				});   
			});
		};
		
		//确定
		$scope.saveOk = function(){
			if($("#baseinfo-form").valid()){
				common.dialogSuccess("确认提交？",function(){
					$scope.showMask();
					msObj.post({ md:'account', use:'user', opt:'saveOrUpdateUser'}, $scope.user, function(obj){
	                	$scope.hideMask();
	                	if (obj.errorCode==='success'){	
	                		common.noticeSuccess("提示",($scope.addFlag?"添加":"修改")+"成功！",false);
	                		$scope.onsearch();
	                    }else {
	  	                	common.dialogFailure(($scope.addFlag?"添加":"修改")+"失败，"+obj.errorMsg); 
	  	                }
					});   	
					$("#userEditModal").modal("hide");
				});
			}
		};
	}]);

  	return kmpmAppControllers;

});



