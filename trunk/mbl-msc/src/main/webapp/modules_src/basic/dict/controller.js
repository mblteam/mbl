'use strict';

define(['controllers','base/common','jquery','jqjstree','underscore'], function (kmpmAppControllers,common,$,jqjstree,underscore) {

	///////////////品牌设置////////////////////
	kmpmAppControllers.controller('DictCtrl', ['$rootScope','$scope','$location','$timeout','msJson','msObj','modalService','$stateParams','dictService',
	  function($rootScope,$scope,$location,$timeout,msJson,msObj,modalService,$stateParams,dictService) {
			
		$scope.search = {};
		$scope.dataList = {data:[]};
		//获得字典数据 
		$scope.onsearch = function() {
			$scope.dataList = msObj.post({
				md : 'basic',
				use : 'dict',
				opt : 'findDictHeadList'
			}, $scope.search, function(obj) {
				$scope.dataList = obj;
			});
		};
		
		$scope.onsearch();
		
		$scope.getDictLine = function(dict,showtype){
			$scope.chooseDict = dict;
			if(showtype=='detail'){
				$scope.showtype = 'detail';
			}else{
				$scope.showtype = null;
			}
			$scope.chooseDictId = dict.dictId; 
			$scope.dictLines = msObj.get({
				md : 'basic',
				use : 'dict',
				opt : 'findDictLinesByHeadId',
				id : dict.dictId,
				LOADING:(new Date).getTime()
			},function(obj) {
				$scope.dictLines = obj.result;
			});
		}
		 // 添加
	    $scope.pushLoop = function (array,obj){
		  if(angular.isArray(array)){
			  $scope.isAdd = true;
	          array.unshift(obj);
	          $scope.showtype = null;
	      }
	    };
	    
	    // 添加
	    $scope.pushLoop2 = function (array,obj){
	    	if(angular.isArray(array)){
	    		array.unshift(obj);
	    	}
	    };
	    
	    //添加 --> 保存
	    $scope.saveLoop =function(array,ind){
	   
	        if (array[ind].dictName == '' || array[ind].dictName == null) {
	            common.dialogFailure("请填写字典名称！");
	            return ;
	        } 
	        if (array[ind].headCode == '' || array[ind].headCode == null) {
	        	common.dialogFailure("请填写字典头编码！");
	        	return ;
	        } 
	        delete array[ind]['edit'];
	        delete array[ind]['$$hashKey'];
	        //新增保存  
	        msObj.post({
		        md: 'basic',
		        use: 'dict',
		        opt: 'saveOrUpdateDict'
		    },array[ind],function(obj) {
		        if (obj.errorCode === 'success') {
		        	 $scope.obj = obj.result;
					common.noticeSuccess("提示","操作成功！",false);
					 $scope.onsearch();
				} else {
					common.dialogError("操作失败，"+ obj.errorMsg);
					array[ind]['edit']=true;
				}
		        
		    });
	    };
	    //添加 --> 保存
	    $scope.saveLoop2 =function(array,ind){
	    	
	    	if (array[ind].dictCode == '' || array[ind].dictCode == null) {
	    		common.dialogFailure("请填写字典编码！");
	    		return ;
	    	} 
	    	if (array[ind].dictValue == '' || array[ind].dictValue == null) {
	    		common.dialogFailure("请填写字典值！");
	    		return ;
	    	} 
	    	delete array[ind]['edit'];
	    	delete array[ind]['$$hashKey'];
	    	//新增保存  
	    	msObj.post({
	    		md: 'basic',
	    		use: 'dict',
	    		opt: 'saveOrUpdateDictLine'
	    	},array[ind],function(obj) {
	    		if (obj.errorCode === 'success') {
	    			$scope.obj = obj.result;
	    			common.noticeSuccess("提示","新增成功！",false);
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
    				 md: 'basic',
    			     use: 'dict',
    			     opt :'deleteDictHead',
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
		//删除字典行信息
		$scope.remove2 = function(id){
			common.dialogSuccess("确定删除信息？",function(){
				msObj.remove({
					md: 'basic',
					use: 'dict',
					opt :'deleteDictLine',
					id:id
				},function(obj) {
					if (obj.errorCode==='success'){
						common.noticeSuccess("提示","删除成功！",false);
						$scope.getDictLine($scope.chooseDict,$scope.showtype);
					}else{
						common.dialogFailure("删除失败！！！错误原因:"+obj.errorMsg);
					}
				});
			});
		};
	    $scope.editLoop = function(array,ind){
	        if(angular.isArray(array)){
	          if(array[ind]['ctId']){
	        	  $scope.isAdd = false;
	          }else{
	        	  $scope.isAdd = true;
	          }
	          if(array.length > ind){
	            array[ind]['edit'] = true;
	          }
	        }
	    };
	    $scope.editLoop2 = function(array,ind){
	    	if(angular.isArray(array)){
	    		if(array.length > ind){
	    			array[ind]['edit'] = true;
	    		}
	    	}
	    };
	    //取消
	    $scope.popLoop = function (array,ind) {
	    	if(angular.isArray(array)){
	           if(array.length > ind){
	              array.splice(ind,1);
	              $scope.onsearch();
	           }
	       }
	    };
	    $scope.popLoop2 = function (array,ind) {
	    	if(angular.isArray(array)){
	    		if(array.length > ind){
	    			array.splice(ind,1);
	    			$scope.getDictLine($scope.chooseDict,$scope.showtype);
	    		}
	    	}
	    };
	    
	}]);

  	return kmpmAppControllers;

});



