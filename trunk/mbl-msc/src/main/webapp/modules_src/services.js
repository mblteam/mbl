 'use strict';

define(['angular','angularResource','uiBootstrapTpls'], function (angular,ngresource,uiBootstrapTpls) {

	/* Services */

	var kmpmAppServices = angular.module('kmpmAppServices', ['ngResource','ui.bootstrap']);

	//本地文件资源服务
	kmpmAppServices.factory('msJson', ['$resource',
		function($resource) {
			/*return $resource('jsonFile/:dir/:fileName.json', {*/
			return $resource('jsonFile/:use/:opt.json', {
				use: '@use',
				opt: '@opt'
			}, {
				query: {
					method: 'GET',
					params: {},
					isArray: true
				},
				post: {
					method: 'POST',
					params: {},
				},
			});
		}
	]);

	//服务器资源服务
	kmpmAppServices.factory('msObj', ['$resource',
		function($resource) {
			//return $resource('/:use/:opt.json', {use:'@use',opt:'@opt'}, {
			return $resource('./:md/:use/:opt.json', {
				md: '@md',
				use: '@use',
				opt: '@opt'
			}, {
				query: {
					method: 'GET',
					params: {},
					isArray: true
				},
				post: {
					method: 'POST',
					params: {},
					interceptor: {
					    'responseError': function(rejection) { 
					      	if(rejection.status != 200){
					        	$window.location.href = $rootScope.LOGIN_URL;
					      	}
					    }
					} 
				},
				update: {
					method: 'PUT',
					params: {}
				}
			});
		}
	]); 
	
	//公共弹出框服务
	kmpmAppServices.factory("modalService", ["$modal", function($modal){
		return {
			//选择组织机构:可传参orgId,表示组织机构id
			showBizTreeAssist : function(p, callbackFunc){
				var modalInstance = $modal.open({
					templateUrl: "partials/assist/bizTreeAssist.html",
					controller: "BizTreeAssistCtrl",
					size: "lg",
					resolve: {
						ipParams: function(){
							return p;
						}
					}
				});
				
				modalInstance.result.then(function(opParams){
					return callbackFunc(opParams);
				});
			},
			
			//选择组织机构:可传参orgId,表示组织机构id
			showUserBizTreeAssist : function(p, callbackFunc){
				var modalInstance = $modal.open({
					templateUrl: "partials/assist/userBizTreeAssist.html",
					controller: "UserBizTreeAssistCtrl",
					size: "lg",
					resolve: {
						ipParams: function(){
							return p;
						}
					}
				});
				
				modalInstance.result.then(function(opParams){
					return callbackFunc(opParams);
				});
			},
				
			//上传文件呼出画面
			showUploadFileAssist : function(p, callbackFunc){
				var modalInstance = $modal.open({
					templateUrl: "partials/assist/uploadFileAssist.html",
					controller: "UploadFileAssistCtrl",
					size: "lg",
					resolve: {
						ipParams: function(){
							return p;
						}
					}
				});
				
				modalInstance.result.then(function(opParams){
					return callbackFunc(opParams);
				});

			},
			
			//选择门店
			showShopMutiAssist : function(p, callbackFunc){
				var modalInstance = $modal.open({
					templateUrl: "partials/assist/shopMutiAssist.html",
					controller: "ShopMutiAssistCtrl",
					size: "lg",
					resolve: {
						ipParams: function(){
							return p;
						}
					}
				});
				
				modalInstance.result.then(function(opParams){
					return callbackFunc(opParams);
				});

			},
			
			//选择单个门店
			showShopSingleAssist : function(p, callbackFunc){
				var modalInstance = $modal.open({
					templateUrl: "partials/assist/shopSingleAssist.html",
					controller: "ShopSingleAssistCtrl",
					size: "lg",
					resolve: {
						ipParams: function(){
							return p;
						}
					}
				});
				
				modalInstance.result.then(function(opParams){
					return callbackFunc(opParams);
				});
				
			}
			
			
		};
	}]);

 
   	kmpmAppServices.factory('superCache', ['$cacheFactory', function($cacheFactory) {
     return $cacheFactory('super-cache');
   	}]);

   	//组织机构等信息查询服务
   	kmpmAppServices.factory('orgDictService', ['msObj','superCache', function(msObj,superCache) {
   		function getDict($scope,keys){
	   		if(keys.length > 0){
	
	   			var objs = keys.pop();
	
				for(var key in objs){ 
		 			if(objs.cache && superCache.get(objs[key]) != undefined && superCache.get(objs[key]) != null){
		 				$scope[objs[key]] = superCache.get(objs[key]);
		 				if(objs.callback){
		 					$scope[objs.callback]();
		 				}
		 				getDict($scope,keys);
		 				break;
		 			}
				 	msObj.get({use:"bizOrg",opt:'dict',key:key},function(obj){
					  if(obj.errorCode == 'success'){
					    $scope[objs[key]] = obj.result;
					    if(objs.callback){
		 					$scope[objs.callback]();
		 				}
					    if(objs.cache){
					    	superCache.put(objs[key],obj.result);
					    }
					    getDict($scope,keys);
	
					  }
					});
					break;
		     	}
	
	   		}
	   	}
   		return getDict;
   	}]);
   	
   	//字典数据查询服务,
   	kmpmAppServices.factory('dictService', ['msObj','superCache', function(msObj,superCache) {
   		function getDict($scope,keys,callbackMethod){
	   		if(keys.length > 0){
	   			var dictArr = [];
	   			var showDictArr = [];
	   			var methodArr = [];
	   			while(keys.length>0){
	   				var objs = keys.pop();
	   				for(var key in objs){
	   					dictArr.push(key);
	   					showDictArr.push(objs[key]);
	   					if(objs.callback){
	   						methodArr.push(objs.callback);
	   					}
	   					break;
	   				}
	   			}
	   			if(dictArr.length>0){
	   				var dictStr = "";
	   				for (var i = 0; i < dictArr.length; i++) {
	   					dictStr += dictArr[i]+",";
					}
	   				msObj.get({
	   					md : 'basic',
						use : "dict",
						opt : 'findByCates',
						cates : dictStr.substring(0,dictStr.length-1)
					},function(obj){
						if(obj!=null&&obj.result!=null){
							for(var i=0;i<dictArr.length;i++){
								$scope[showDictArr[i]] = obj.result[dictArr[i]];
							};
						}
						for (var i = 0; i < methodArr.length; i++) {
							$scope[methodArr[i]]();
						}
						if(callbackMethod){
							callbackMethod();
						}	
					}); 
	   			}
	   		}
	   	}
   		return getDict;
   	}]);

	return kmpmAppServices;

});