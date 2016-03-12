'use strict';

define(['controllers','base/common','jquery','jqjstree','underscore'], function (kmpmAppControllers,common,$,jqjstree,underscore) {

	///////////////菜单设置////////////////////
	kmpmAppControllers.controller('AuthRoleCtrl', ['$rootScope','$scope','$location','$timeout','msJson','msObj','modalService','$stateParams','dictService','$filter',
	  function($rootScope,$scope,$location,$timeout,msJson,msObj,modalService,$stateParams,dictService,$filter) {
		$scope.search = {};
		$scope.dataList =[];
		//获得字典数据 
	    $scope.init = function(){
	    	//字典账户状态
			dictService($scope,[{'status':'dict_role_status'}],function(){
				$scope.dictMap_role_status = $filter('treeJSON')($scope.dict_role_status,['dictCode']);
			});
	    	$scope.onsearch();
	    };
		$scope.onsearch = function() {
			$scope.roleList = msObj.post({
				md : 'auth',
				use : 'role',
				opt : 'findRoleList',
				LOADING:(new Date).getTime()
			}, $scope.search, function(obj) {
				if(obj.data){
					$scope.roleList = obj;
				}else{
					$scope.roleList = {data:[]};
				}
			});
		};
		
		$scope.init();
		 // 添加
	    $scope.addButton = function (array,obj){
	      /*if(array==undefined){
	    	  array = [];
	    	  var objData ={roleName:"",roleStatus:""};
	    	  array.push(objData);
	      }*/
		  if(angular.isArray(array)){
			  $scope.isAdd = true;
	          array.unshift(obj);
	      }
	    };
	    
	 // 添加 --> 保存
	    $scope.add =function(array,ind){
	    	if (array[ind].roleCode == '' || array[ind].roleCode == null) {
	            common.dialogFailure("请填写角色代码！");
	            return ;
	        } else if (array[ind].roleName == '' || array[ind].roleName == null) {
	            common.dialogFailure("请填写角色名称！");
	            return ;
	        } else if (array[ind].roleStatus == null||_.isEmpty(array[ind].roleStatus+"")) {
	            common.dialogFailure("请选择角色状态!");
	            return ;
	        };
	        delete array[ind]['edit'];
	        delete array[ind]['$$hashKey'];
	        //新增保存  
	        msObj.post({
		        md: 'auth',
		        use: 'role',
		        opt: 'saveOrUpdateRole'
		    },array[ind],function(obj) {
		        if (obj.errorCode === 'success') {
		        	 $scope.obj = obj.result;
		        	 if(array[ind]['roleId']){
		        		 common.noticeSuccess("提示","修改成功！",false);
		        	 }else{
		        		 common.noticeSuccess("提示","新增成功！",false);
		        	 }
					 $scope.onsearch();
				} else {
					common.dialogError("新增失败，"+ obj.errorMsg);
					array[ind]['edit']=true;
				}
		        
		    });
	    };
	    
	  //删除
		$scope.remove = function(id){
			common.dialogSuccess("确定删除信息？",function(){
    			msObj.remove({
					md : "auth",
					use : "role",
					opt : 'deleteRole',
					id:id
				},function(obj) {
					if (obj.errorCode==='success'){
						common.noticeSuccess("提示","删除成功！",false);
						$scope.onsearch();
					}else{
						common.dialogFailure("删除失败！！！错误原因:"+obj.errorMsg);
					}
				});
			});
		};
	    $scope.editLoop = function(array,ind){
	        if(angular.isArray(array)){
	          if(array[ind]['roleId']){
	        	  $scope.isAdd = false;
	          }else{
	        	  $scope.isAdd = true;
	          }
	          if(array.length > ind){
	            array[ind]['edit'] = true;
	          }
	        }
	    };
	    //取消
	    $scope.cancel = function (array,ind) {
	    	if(angular.isArray(array)){
	           if(array.length > ind){
	              array.splice(ind,1);
	           }
	       }
	    }
	    
	    
	    //设置菜单
	    $scope.settingMenu = function(array,ind){
	    	$scope.role = array[ind];
	    	$("#roleMenuModal").modal("show");
	    	$("#jstree_menu").jstree("destroy");
	    	/*msObj.post({
	    		md:'auth',
	    		use:'roleMenu',
	    		opt:'getRoleMenuTreeByRoleId'
	    	},{roleId:$scope.role.roleId},function(obj){
	    		if(obj.errorCode=="success"){*/
	    			$("#jstree_menu").jstree({
	    	            "core" : {
	    	                "check_callback" : true,
	    	                "multiple" : true,
	    	                'data' : {
	    	                	'type': "POST",
	    						'url' : "./auth/roleMenu/findRoleMenuTreeByRoleId",
	    						'dataType' : "json",
	    						'contentType' : "text/javascript",
	    						'cache' : false,
	    						'data' : JSON.stringify({roleId:$scope.role.roleId})
	    					}
	    	            },
	    	            "plugins" : ["dnd","types","search","checkbox"]
	    	    	});
	    		/*}
	    	});*/
	    	
	    };
	    
	    
	    $scope.settingMenuOk = function(){
	    	var nodes=$("#jstree_menu").jstree("get_checked"); //使用get_checked方法 
	    	if(angular.isArray(nodes)&&nodes.length>0){
	    		var menuList = [];
	    		for (var i = 0; i < nodes.length; i++) {
	    			menuList.push({menuId:nodes[i]});
				}
		    	//新增保存  
		        msObj.post({
			        md: 'auth',
			        use: 'roleMenu',
			        opt: 'batchSaveRoleMenu'
			    },{roleId:$scope.role.roleId,menuList:menuList},function(obj) {
			        if (obj.errorCode === 'success') {
			        	$("#roleMenuModal").modal("hide");
						 common.noticeSuccess("提示","设置菜单成功！",false);
					} else {
						common.dialogError("设置菜单失败，"+ obj.errorMsg);
					}
			        
			    });
	    	}else{
	    		common.dialogError("未选择任何菜单");
	    	}
	    };
	    
	}]);

  	return kmpmAppControllers;

});



