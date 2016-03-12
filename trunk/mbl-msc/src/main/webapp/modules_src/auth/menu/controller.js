'use strict';

define(['controllers','base/common','jquery','jqjstree','underscore'], function (kmpmAppControllers,common,$,jqjstree,underscore) {

	///////////////菜单设置////////////////////
	kmpmAppControllers.controller('AuthMenuCtrl', ['$rootScope','$scope','$location','$timeout','msJson','msObj','modalService','$stateParams','dictService',
	  function($rootScope,$scope,$location,$timeout,msJson,msObj,modalService,$stateParams,dictService) {
			
			$scope.orgniztionType = $stateParams.type;
			$scope.addDataList = [];
			$scope.isLeaf = false;
		    
			//初始化选择节点赋值对象
			$scope.menuItem = {};

	  		//初始化菜单树，并绑定选择节点事件
          	$("#jstree_demo2").jstree({
		          "core" : {
		              "check_callback" : true,
		              'data' : {
		            	  "url" : "auth/menu/menuTreelist.json",
			  			  "data" : function (node) {
			  				  return { "id" : node.id, TIMESTAMP:(new Date()).getTime()};
			  			   }
			  		   }
		          },
		          "plugins" : ["search", "state", "types", "wholerow"]
		      })
		      .bind("loaded.jstree", function (event, data) { 
		    	  
		      })
		      .bind("select_node.jstree", function (event, data) { 
		    	  	console.log(data);
		    	  	//获得节点菜单信息
		    	  	$scope.menuItem = data.node.original;
		    	  	
		    	  	//是否有子节点，删除时判断
		    	  	$scope.menuItem.haschildren = data.node.children.length>0?true:false;
		    	  	
		    	  	if($scope.menuItem.hasChildrenFlag=="true"){
		    	  		$scope.isLeaf = false;
		    	  		$scope.menuItem.haschildren = true;
		    	  	}else{
		    	  		$scope.isLeaf = true;
		    	  		$scope.menuItem.haschildren = false;
		    	  	}
		      });
          	
          	//菜单树搜索
          	var to = false;
			$('#demo_q').keyup(function () {
				if(to) { clearTimeout(to); }
				to = setTimeout(function () {
					var v = $('#demo_q').val();
					$('#jstree_demo2').jstree(true).search(v);
				}, 250);
			});
		    
			//菜单添加弹出框
		    $scope.showAddMenuModal = function(){
		    	$scope.editMenuItem = {};
		    	$scope.isRoot = false;
		    	if(!$scope.menuItem||!$scope.menuItem.menu){
		    		var isWrong = true;
		    		msObj.get({ md : 'auth', use : 'menu', opt : 'isHasRootMenu', LOADING:(new Date).getTime()},function(obj) {
		  				if(obj.errorCode=='success'){
		  					isWrong = obj.result;
		  					if(isWrong){
				    			common.dialogFailure("未选择菜单？");
					    		return false;
				    		}else{
				    			$scope.editMenuItem = {parentMenuId:$scope.menuItem.id};
				    			$("#myLargeModalLabel").text("添加菜单");
						    	$("#modal-table").modal("show");
				    			$scope.isRoot = true;
				    		}
		  				}
		  			});
		    	}else{
		    		$scope.editMenuItem = {parentMenuId:$scope.menuItem.id};
		    		$("#myLargeModalLabel").text("添加菜单");
			    	$("#modal-table").modal("show");
		    	}
		    	
		    };
		    
		    //菜单修改弹出框
		    $scope.showEditMenuModal = function(){
		    	if(null==$scope.menuItem){
		    		common.dialogFailure("未选择菜单？");
		    		return false;
		    	}
		    	$scope.editMenuItem = {};
		    	if(null!=$scope.menuItem.id){
		    		$("#myLargeModalLabel").text("修改菜单");
		    		$scope.isRoot = true;
		    		msObj.get({ md : 'auth', use : 'menu', opt : 'getMenuById', id : $scope.menuItem.id, LOADING:(new Date).getTime()
					}, function(obj) {
						if(obj.errorCode=='success'){
							$scope.editMenuItem = obj.result;
						}else{
							common.dialogFailure("菜单数据加载失败！！！错误原因:"+obj.errorMsg);
						}
					});
		    	}else{
		    		$("#myLargeModalLabel").text("修改菜单");
		    	}
		    	$("#modal-table").modal({backdrop: 'static', keyboard: false});
		    };
		    
		    //保存或修改菜单
		    $scope.save = function(){
		    	if(!$('#createMenu-form').valid()) {
					common.dialogFailure("画面录入的数据有错误，请参照错误提示修改后重新提交。");
					return;
				}
		    	
				msObj.post({ md : 'auth', use : 'menu', opt : 'isExistMenu',
				}, $scope.editMenuItem, function(data) {
					if(data.errorCode=='success'){
						if(data.result=="1"){
							common.dialogFailure("无法保存！菜单名称已存在："+data.errorMsg);
							return;
						}
						common.dialogSuccess("确定保存？",function() {
					    	msObj.post({ md : 'auth', use : 'menu', opt : 'updateMenu', type: $stateParams.type
							}, $scope.editMenuItem, function(obj) {
								if(obj.errorCode=='success'){
									$("#modal-table").modal("hide");
										if($scope.isRoot){
											var tree = $.jstree.reference("#jstree_demo2");
					                        tree.refresh();
										}else{
											var tree = $.jstree.reference("#" + $scope.editMenuItem.parentMenuId);
					                        tree.refresh();
										}
								}else{
									common.dialogFailure("保存失败！！！错误原因:"+obj.errorMsg);
								}
							});
						});
					}else{
						common.dialogFailure("保存失败！！！错误原因:"+data.errorMsg);
					}
				});
		    };
		    
		    
		    //删除菜单
		    $scope.deleteMenu = function(){
		    	if(null==$scope.menuItem){
		    		common.dialogFailure("未选择菜单？");
		    		return false;
		    	}
		    	if($scope.menuItem.haschildren){
		    		common.dialogFailure("存在子菜单,不能删除？");
		    		return false;
		    	}
		    	common.dialogSuccess("确定删除该菜单？",function() {
		    		msObj.get({ md : 'auth', use : 'menu', opt : 'delMenu', id : $scope.menuItem.id,LOADING:(new Date).getTime()
					}, function(obj) {
						if(obj.errorCode=='success'){
							common.noticeSuccess("提示","删除成功！",false);
							var tree = $.jstree.reference("#" + $scope.menuItem.id);
	                        tree.refresh();
						}else{
							common.dialogFailure("删除失败！！！错误原因:"+obj.errorMsg);
						}
					});
				});
		    	
		    };

	}]);

  	return kmpmAppControllers;

});



