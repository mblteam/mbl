'use strict';

define(['controllers','base/common','jquery','jqjstree','underscore'], function (kmpmAppControllers,common,$,jqjstree,underscore) {

	///////////////菜单设置////////////////////
	kmpmAppControllers.controller('AccountCtrl', ['$rootScope','$scope','$location','$timeout','msJson','msObj','modalService','$stateParams','dictService','$filter',
	  function($rootScope,$scope,$location,$timeout,msJson,msObj,modalService,$stateParams,dictService,$filter) {
		$scope.search = {};
		$scope.dataList =[];
		$scope.user = [];
		$scope.accountRoleIds = [];
		$scope.password = {};
		$scope.sysAdmin=false;
		//字典账号类型
		dictService($scope,[{'account_type':'dict_account_type'}],function(){
			$scope.dictMap_account_type = $filter('treeJSON')($scope.dict_account_type,['dictCode']);
		});
		//字典账户状态
		dictService($scope,[{'status':'dict_account_status'}],function(){
			$scope.dictMap_account_status = $filter('treeJSON')($scope.dict_account_status,['dictCode']);
		});
		
		$scope.onsearch = function() {
			$scope.accountList = msObj.post({
				md : 'account',
				use : 'account',
				opt : 'findAccountList',
				LOADING:(new Date).getTime()
			}, $scope.search, function(obj) {
				if(obj.data){
					$scope.accountList = obj;
					if(angular.isArray($scope.accountList.data)){
						$($scope.accountList.data).each(function(index,item){
							$scope.user[index] = {};
							$scope.user[index]['userName'] = item['userName'];
						});
					}
				}else{
					$scope.accountList = {data:[]};
				}
			});
		};
		
		msObj.get({
        	md : 'auth',
			use : 'login',
			opt : 'loginInit',
			LOADING:(new Date).getTime()
        },function(data) {
        	if(data.result){
        		var loginAccount = data.result.CURRENT_ACCOUNT;
        		if(loginAccount){
        			if(loginAccount.accountCode=='admin'){
        				$scope.sysAdmin = true;
        			}else{
        				$scope.sysAdmin = false;
        			}
            	}
        	}
        });
		
		$scope.onsearch();
		 // 添加
	    $scope.addButton = function (array,obj){
		  if(angular.isArray(array)){
			  $scope.isAdd = true;
	          array.unshift(obj);
	      }
	    };
	    
	 // 添加 --> 保存
	    $scope.add =function(array,ind){
	    	if (array[ind].accountCode == '' || array[ind].accountCode == null) {
	            common.dialogFailure("请填写账号！");
	            return ;
	        } else if (array[ind].accountType == '' || array[ind].accountType == null) {
	            common.dialogFailure("请填写账户类型！");
	            return ;
	        } else if (array[ind].status == null||_.isEmpty(array[ind].status+"")) {
	            common.dialogFailure("请选择状态!");
	            return ;
	        };
	        delete array[ind]['edit'];
	        delete array[ind]['$$hashKey'];
	        //新增保存  
	        msObj.post({
		        md: 'account',
		        use: 'account',
		        opt: 'saveOrUpdateAccount'
		    },array[ind],function(obj) {
		        if (obj.errorCode === 'success') {
		        	 $scope.obj = obj.result;
		        	 if(array[ind]['accountId']){
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
					md : "account",
					use : "account",
					opt : 'deleteAccount',
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
	          if(array[ind]['accountId']){
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
	    //查找角色列表
	    $scope.searchRoleList = function() {
			$scope.roleList = msObj.post({
				md : 'auth',
				use : 'role',
				opt : 'findRoleList',
				LOADING:(new Date).getTime()
			}, {}, function(obj) {
				if(obj.data){
					$scope.roleList = obj;
					for (var i = 0; i < $scope.roleList.data.length; i++) {
						$scope.roleList.data[i]['checked']= null;
						if($scope.accountRoleIds.indexOf($scope.roleList.data[i]['roleId'])!=-1){
							$scope.roleList.data[i]['checked']=true;
						}
					}
				}else{
					$scope.roleList = {data:[]};
				}
			});
		};
		
	    //设置角色
	    $scope.settingRole = function(array,ind){
	    	$scope.accountRoleIds = [];
	    	$scope.accountId = array[ind]['accountId'];
	    	msObj.get({
 	        	md : 'account',
 				use : 'accountRole',
 				opt : 'getByAccountId',
 			accountId : array[ind]['accountId'],
 				LOADING:(new Date).getTime()
 	        },
 	        function(data) {
        		if(data.result.length>0){
 	        		$(data.result).each(function(index,item){
 	        			$scope.accountRoleIds.push(item['roleId']);
 	        		});
 	        	}
 	        });
	    	$("#roleModal").modal("show");
	    	$scope.searchRoleList();
	    };
	    
	    //checkbox选择框的on/off动作
		  $scope.toggleCheckbox = function(checked, dList, chkColNm){
	          for (var x in dList) {
	          	dList[x][chkColNm] = checked;
	          };
		  };
	      
	      	//点击【选择】按钮时，关闭窗口并返回记录
  		  	$scope.ok = function () {
  		  	  var roleList = [];
  			  $($scope.roleList.data).each(function(index,item){
	    		  if(item['checked']==true){
	    			  delete item['checked'];
	    			  roleList.push(item);
	    		  }
	    	  });
  			  $scope.accountRole = {};
  			  $scope.accountRole.roles = roleList;
  			  $scope.accountRole.accountId = $scope.accountId;
	  			 //新增保存  
	  	        msObj.post({
	  		        md: 'account',
	  		        use: 'accountRole',
	  		        opt: 'saveByAccountId',
	  		        accountId:$scope.accountId
	  		    },$scope.accountRole,function(obj) {
	  		        if (obj.errorCode === 'success') {
	  					common.noticeSuccess("提示","角色设置成功！",false);
	  					$("#roleModal").modal("hide");
	  				} else {
	  					common.dialogError("角色设置失败，"+ obj.errorMsg);
	  				}
	  		    });
  		  	}
  		 //点击【关闭】按钮时，关闭窗口
  		  $scope.cancel = function () {
  			$("#roleModal").modal("hide");
  		  };
	  		  
	  	
  		  $scope.editPsw = function(array,ind){
  			  $scope.password ={};
  			  $scope.password.accountCode = array[ind]['accountCode'];
  			  $scope.password.accountId = array[ind]['accountId'];
  			  $("#accountPswModal").modal("show");
  		  }
  		  
  		 $scope.savePsw = function(){
  			
  			 if($("#baseinfo-form").valid()){
  				var pswParam = {'accountId':$scope.password.accountId,'oldAccountPsw':$scope.password.oldAccountPsw,'newAccountPsw':$scope.password.newAccountPsw};
  				 //修改密码  
  	  	        msObj.post({
  	  		        md: 'account',
  	  		        use: 'account',
  	  		        opt: 'updatePsw'
  	  		    },pswParam,function(obj) {
  	  		        if (obj.errorCode === 'success') {
  	  		        	if(obj.result=='fail'){
  	  		        		common.noticeSuccess("提示","原密码输入错误！",false);
  	  		        	}else{
  	  		        		common.noticeSuccess("提示","密码修改成功！",false);
		  					$("#accountPswModal").modal("hide");
  	  		        	}
  	  				} else {
  	  					common.dialogError("密码修改失败，"+ obj.errorMsg);
  	  				}
  	  		    });
  	  		    
  			};
  		 }
  		 
  		 $scope.reSetPsw = function(array,ind){
  			var pswParam = {'accountId':$scope.password.accountId};
			 //修改密码  
  	        msObj.post({
  		        md: 'account',
  		        use: 'account',
  		        opt: 'reSetPsw'
  		    },{'accountId':array[ind]['accountId']},function(obj) {
  		        if (obj.errorCode === 'success') {
	        		common.noticeSuccess("提示","重置密码成功！",false);
  				} else {
  					common.dialogError("重置密码失败，"+ obj.errorMsg);
  				}
  		    });
  		 }
  		 

 	    //设置4s店信息
 		$scope.settingShop = function(array,ind){
 			modalService.showShopSingleAssist({},function(obj){
 				console.log(obj.result);
 				array[ind]['shopCode'] = obj.shopCode;
 				array[ind]['shopId']=obj.shopId;
 			});
 		};
 	  		  
	}]);

  	return kmpmAppControllers;

});



