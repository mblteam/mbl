'use strict';

define(['controllers','base/common','jquery','jqjstree','underscore'], function (kmpmAppControllers,common,$,jqjstree,underscore) {

	///////////////品牌设置////////////////////
	kmpmAppControllers.controller('CarTypeCtrl', ['$rootScope','$scope','$location','$timeout','msJson','msObj','modalService','$stateParams','dictService',
	  function($rootScope,$scope,$location,$timeout,msJson,msObj,modalService,$stateParams,dictService) {
			
		$scope.search = {};
		$scope.dataList = {data:[]};
		//获得字典数据 
		$scope.onsearch = function() {
			$scope.dataList = msObj.post({
				md : 'basic',
				use : 'carType',
				opt : 'findCarTypeList',
				LOADING:(new Date).getTime()
			}, $scope.search, function(obj) {
				$scope.dataList = obj;
			});
		};
		
		$scope.onsearch();
		
		 // 添加
	    $scope.pushLoopFront = function (array,obj){
		  if(angular.isArray(array)){
			  $scope.isAdd = true;
	          array.unshift(obj);
	      }
	    };
	    
	    //添加 --> 保存 品牌
	    $scope.saveCarType =function(array,ind){
	        if (array[ind].brandCode == '' || array[ind].brandCode == null) {
	            common.dialogFailure("请填写品牌编号！");
	            return ;
	        } else if (array[ind].brandName == null||_.isEmpty(array[ind].brandName+"")) {
	            common.dialogFailure("请选择品牌名称!");
	            return ;
	        };
	        delete array[ind]['edit'];
	        delete array[ind]['$$hashKey'];
	        //新增保存  
	        msObj.post({
		        md: 'basic',
		        use: 'carType',
		        opt: 'saveOrUpdateCarType'
		    },array[ind],function(obj) {
		        if (obj.errorCode === 'success') {
		        	$scope.obj = obj.result;
					common.noticeSuccess("提示",($scope.isAdd?"新增":"修改")+"成功！",false);
					$scope.onsearch();
				} else {
					common.noticeError("错误",($scope.isAdd?"新增":"修改")+"失败，"+ obj.errorMsg,false);
					array[ind]['edit']=true;
				}
		    });
	    };
	    
	    //删除品牌
		$scope.removeCarType = function(id){
			common.dialogSuccess("确定删除信息？",function(){
    			msObj.remove({
    				md: 'basic',
    				use: 'carType',
    				opt : 'deleteCarType',
					id: id
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
		//编辑品牌
	    $scope.editCarType = function(array,ind){
	        if(angular.isArray(array)){
	        	if(array[ind]['cbId']){
	        		$scope.isAdd = false;
	        		$scope.origCarType = angular.copy(array[ind]);
	        	}else{
	        		$scope.isAdd = true;
	        	}
	        	if(array.length > ind){
	        		array[ind]['edit'] = true;
	        	}
	        }
	    };
	    
	    //图片上传
		$scope.uploadPhoto = function(array,ind){
			modalService.showUploadFileAssist({uploadType:'IMAGE',uploadPath:'carType',uploadTitle:'图片上传'},function(filePath){
				if(angular.isArray(array)){
		           if(array.length > ind){
		        	   array[ind].url = filePath;
		           }
				}
        	});
		};
		
		//车系弹出框
		$scope.settingCarSeries = function(array,ind){
			if(angular.isArray(array)){
				$scope.addFlag = true;
				$scope.carSeriesList = [];
				$scope.brandId = array[ind]['cbId'];
				msObj.get({
    				md: 'basic',
    				use: 'carType',
    				opt: 'findCarSeriesByCbId',
					cbId: array[ind]['cbId']
				},function(obj) {	
					if(obj.errorCode == "success"){
						$scope.carSeriesList = obj.result;
						$("#carSeriesModal").modal("show");
					}else{
						common.dialogFailure("系统错误！！！错误原因:"+obj.errorMsg);
					}
				});
			}
		};
		//确认车系
		$scope.settingCarSeriesOk = function(){
			common.dialogSuccess("确定车系信息？",function(){
				for(var i=0; i<$scope.carSeriesList.length; i++){
					if($scope.carSeriesList[i]['edit']){
						common.dialogError("请保存每一项");
						return;
					};
				}
				for(var i=0; i<$scope.carSeriesList.length; i++){
					delete $scope.carSeriesList[i]['edit'];
				}
				msObj.post({
					md: 'basic',
					use: 'carType',
					opt : 'mergeCarSeriesWithCbId'
				},{cbId: $scope.brandId,carSeriesList:$scope.carSeriesList},function(obj) {	
					if (obj.errorCode === 'success') {
						common.noticeSuccess("提示","成功！",false);
						$("#carSeriesModal").modal("hide");
					} else {
						common.noticeError("错误","失败，原因："+ obj.errorMsg,false);
					}
				});
			});
		};
		//取消车系
		$scope.settingCarSeriesCancel = function(){
			$("#carSeriesModal").modal("hide");
		};
		
		//公共列表增删改查方法
	 	$scope.pushLoop = function(array,obj){
	 		if(angular.isArray(array)){
	        	array.push(obj);
	        }
	    };
	    
	    //取消品牌
	    $scope.popLoopCarType = function (array,ind) {
	    	if(angular.isArray(array)){
	           if(array.length > ind){
	        	   if(null!=array[ind]['cbId']&&""!=array[ind]['cbId']){
	        		   array[ind] = $scope.origCarType;
	        		   array[ind]['edit'] = false;
	        	   }else{
	        		   array.splice(ind,1);
	        	   }
	           }
	       }
	    };
	    
	    //取消车系
	    $scope.popLoopCarSeries = function (array,ind) {
	    	if(angular.isArray(array)){
	           if(array.length > ind){
	        	   if(null!=array[ind]['csId']&&""!=array[ind]['csId']){
	        		   array[ind] = $scope.origCarSeries;
	        		   array[ind]['edit'] = false;
	        	   }else{
	        		   array.splice(ind,1);
	        	   }
	           }
	       }
	    };
	    
	    //公共删除
	    $scope.popLoop = function (array,ind) {
	    	if(angular.isArray(array)){
	           if(array.length > ind){
	        	   array.splice(ind,1);
	           }
	       }
	    };
	    
	    //编辑
	    $scope.editLoop = function(array,ind){
	        if(angular.isArray(array)){
	        	if(array.length > ind){
	        		array[ind]['edit'] = true;
	        	}
	        }
	    };
	    $scope.editCarSeries = function(array,ind){
	        if(angular.isArray(array)){
	        	if(array[ind]['csId']){
	        		$scope.origCarSeries = angular.copy(array[ind]);
	        	}
	        	if(array.length > ind){
	        		array[ind]['edit'] = true;
	        	}
	        }
	    };
	    
	    //验证
	    var VALID_METHOD_MAP = {
	    	qtyOfSingle:{method:"^\\d{0,9}$",tip:'单套数量必须为合理正整数'},
	    	barcodeBegin:{method:"^\\d{0,30}$",tip:'条码号必须为数字'},
	    	barcodeEnd:{method:"^\\d{0,30}$",tip:'条码号必须为数字'},
	    	amount:{method:"^\\d{0,30}$",tip:'数量必须为数字'},
	    	contactTelephone:{method:"^(((\(\\d{2,3}\))|(\\d{3}\-))?(\(0\\d{2,3}\)|0\\d{2,3}-)?[1-9]\\d{6,7}(\-\\d{1,4})?)|((13|15|17|18)\d{9})$",tip:'联系方式错误'},
	    	customerContactEmail:{method:"^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$",tip:'邮箱地址错误'},
	    
	    };
	    
	    
	    $scope.saveLoop = function(array,ind,mustMap,validMap,specialMap){
	    	if(angular.isArray(array)){
	    		if(array.length > ind){
	    			//必填验证
	    			if(angular.isArray(mustMap)&&mustMap.length>0){
	    				for (var i = 0; i < mustMap.length; i++) {
	    					if(null==array[ind][mustMap[i]]||_.isEmpty(array[ind][mustMap[i]]+"")){
	    						common.noticeError('错误','请完成必填项！',false);
	    						return;
	    					}
	    				}
	    			}
	    			//规则校验
	    			if(angular.isArray(validMap)&&validMap.length>0){
	    				for (var i = 0; i < validMap.length; i++) {
    						if(VALID_METHOD_MAP[validMap[i]]){
    							var patt1=new RegExp(VALID_METHOD_MAP[validMap[i]].method);
    							if(null!=array[ind][validMap[i]]&&""!=array[ind][validMap[i]]&&!patt1.test(array[ind][validMap[i]])){
    								common.noticeError('错误',VALID_METHOD_MAP[validMap[i]].tip,false);
    								return;
    							}
    						}
	    				}
	    			}
	    			//后台验证
	    			if(specialMap){
	    					
	    				//车系是否重复
    					if(specialMap["series"]){
    						var isExit = false;
    						angular.forEach(array, function(data,j){
    							if(j!=ind&&data.series==array[ind].series){
    								isExit = true;
    							}
    						});
    						if(isExit){
    							common.noticeError('错误',specialMap["series"],false);
    							return;
    						}
    						if(specialMap["series"]){
    							msObj.post({
    								md : "basic",
    								use : "carType",
    								opt : 'existOrNotSeries'
    							},{cbId:specialMap["series"]},function(obj) {
    								if (obj.errorCode=='success'){
    									if(obj.result==true){
    										common.noticeError('错误','该车系已存在！',false);
    									}else{
    										array[ind]['edit'] = false;
    									}
    								}else{
    									common.noticeError('错误','系统错误！',false);
    								}
    							});
    						}else{
    							array[ind]['edit'] = false;
    						}
    					}else{
    						array[ind]['edit'] = false;
    					}
	    			}else{
	    				array[ind]['edit'] = false;
	    			}
	    		}
	    	}
	    };
	}]);

  	return kmpmAppControllers;

});



