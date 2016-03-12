'use strict';

define(['angular','services'], function (angular) {
	/* Directives */
	//var kmpmAppDirectives = angular.module('kmpmAppDirectives', []);

	function $watchCurrentPage($scope,msObj){
		$scope.$watch(
		  		function (){if($scope.pageBean !== undefined) return $scope.pageBean.currentPage;}, 
		  		function (newVal, oldVal) {
					if(newVal!== undefined && oldVal!== undefined && newVal !== oldVal){	
						$scope.pageBean.search.page = $scope.pageBean.currentPage;
						$scope.pageBean.search.pageSize = $scope.pageBean.pageSize;

						$scope.isNotClick = true;
						if($scope.form !== undefined){ 
		  					msObj.post($scope.pageBean.search,$scope.form,
		  						function(pageBean){
		  							$scope.isNotClick = false;
									if((pageBean.currentPage - 1) * pageBean.pageSize > pageBean.totalSize){
										$scope.pageBean.currentPage = 1;
										$scope.pageBean.search.currentPage = 1;
										msObj.post($scope.pageBean.search,$scope.form,function(pageBean){$scope.isNotClick = false;$scope.pageBean = pageBean;});
									}else{
										$scope.pageBean = pageBean;
									}
		  						}
		  					);
						}else{
		  					msObj.post($scope.pageBean.search,{},function(pageBean){
		  						$scope.isNotClick = false;
		  						$scope.pageBean = pageBean;
		  					});
						}
					
		  			}
		  			flushUIBar(newVal,oldVal,$scope);
	    		}
	    , true);
	};
	
	function $watchTotalSize($scope){
		$scope.$watch(
		  		function (){if($scope.pageBean !== undefined) return $scope.pageBean.totalSize;}, 
		  		function (newVal, oldVal) {
		  			flushUIBar(newVal,oldVal,$scope);
	    		}
	    , true);
	};

	function flushUIBar(newVal, oldVal,$scope) {

		//计算分页页脚
		$scope.pageIndexs = [];
		var pageIndLength = 6; 
		if($scope.pageBean === undefined) $scope.pageBean = {};
		var i = $scope.pageBean.currentPage - 2;
		if(i > (($scope.pageBean.totalPage + 1) - pageIndLength)){
			i = ($scope.pageBean.totalPage + 1) - pageIndLength;
		}
		for (var j = 1; i <= $scope.pageBean.totalPage && j <= pageIndLength; i++) {
			if(i > 0){
				$scope.pageIndexs.push(i);
				j++;
			}
		};
		$scope.prePage = false;
		$scope.preByPage = false;
		$scope.nextPage = false;
		$scope.nextByPage = false;
		if($scope.pageIndexs[0] == 1){
			$scope.prePage = false;
		}else if($scope.pageIndexs[0] == 2){
			$scope.prePage = true;
			$scope.preByPage = false;
		}else if($scope.pageIndexs[0] > 2){
			$scope.prePage = true;
			$scope.preByPage = true;
		}
		if($scope.pageIndexs[$scope.pageIndexs.length - 1] == $scope.pageBean.totalPage){
			$scope.nextPage = false;
		}else if($scope.pageIndexs[$scope.pageIndexs.length - 1] == $scope.pageBean.totalPage - 1){
			$scope.nextPage = true;
			$scope.nextByPage = false;
		}else if($scope.pageIndexs[$scope.pageIndexs.length - 1] < $scope.pageBean.totalPage - 1){
			$scope.nextPage = true;
			$scope.nextByPage = true;
		}
	};

	function adapter(pageBean){

	};
	/* 分页组件指令 */
	/* 定义分页对象
	 * pageBean = {
	 * data:[0,1,2,3,4,5,6,7,8,9,10], -- 数据
	 * search:{}, --查询对象
	 * pageSize:10, -- 页大小
	 * currentPage:1, -- 当前页
	 * totalSize:101, -- 总数据数
	 * totalPage:11 -- 总页数
	 * }
	 */

	var kmpmAppDirectives = angular.module('kmpmAppDirectives', []).directive('platPaging',['msObj',function(msObj) {

		function pagingCtrl($scope,$element,$attrs){
			//console.info($element);

		};
	 	function link(scope, element, attr) {
		    scope.bindData = function(currentPage){
		    	if(scope.isNotClick){
		    		return;
		    	}
		     	scope.pageBean.currentPage = currentPage;
		     	if(scope.pageDo){
		     		scope.pageDo();
		    	}
		    };
		    /*用户点击当前页发生的变化*/
		  	$watchCurrentPage(scope,msObj);
		  	//$watchCurrentDataSize(scope);
			$watchTotalSize(scope);
		  	/*用户分页发生变化的时候组装分页属性 开发前端用*/

		  	//getPageBean(scope);
		 	//$watchPageDataTest(scope);
		 	//$watchPageBeanTest(scope);

	    };
	    return {
			templateUrl:"partials/framework/paging.html",
			restrict: 'AE',
			scope:{
				pageBean:"=platPaging",
				form:"=platForm",
				pageDo:"=pageDo"
			},
			link:link,
			controller:pagingCtrl
	    };
	}]);
	
	//默认值指令
	kmpmAppDirectives.directive('myDefaultValue',function() { 
		 return {      
			scope:{
				originalObj:"=myDefaultValue",
				defaultValue:"@"
			},
			link: function(scope, ele, attrs) {             
				if (!scope.originalObj){
					scope.originalObj = scope.defaultValue;
				}
			}     
		};
	});
	
	//日期指令
	// 日期格式转换
	kmpmAppDirectives.directive('myDatepickerModel', ['$filter',function($filter) { 
		 return {  
			require: '?ngModel',
			scope:{
				dateFormat:"@"
			},
			link: function(scope, ele, attrs, ngModel) {
				scope.eleValue = ele.val();
				scope.$watch('eleValue', function(newVal,oldVal) {
					if(ngModel){
						if(angular.isNumber(ngModel.$viewValue)){ 
							if(!scope.dateFormat){
								scope.dateFormat = 'yyyy-MM-dd';
							}
							var date = $filter('date')(ngModel.$viewValue,scope.dateFormat);
							ngModel.$setViewValue(date); 
							ele.val(date); 
						}else{
							ngModel.$setViewValue(ele.val()); 
						}
					};	
				}); 
				$(function() { 
					var whileFun = function(){
						scope.eleValue = ele.val();
						setTimeout(function(){
							whileFun();
						},100);
					};
					whileFun();  
				});
			} 
		};
	}]);

	//输入 input ，下拉 select ，多选 checkbox ，单选 radio , range
	kmpmAppDirectives.directive('platTable',['msObj',function(msObj) {

		var data = {
			find:{md:"customer",use:"department",opt:'doorcyceeditlist'},
			put:{md:"customer",use:"department",opt:'doorCyceEdit'},
			head:["周期ID","周期时间","物流1","物流2","物流3"],
			width:["8%","","","8%",""],
			cell:["cyceId","cyceTime","logisticsId","logisticsId2","attribute1"],
			type:["input","select","checkbox","radio","range"],
			input:"abc",
			select:{},
			checkbox:{},
			radio:{},
			data:[
					{cyceId:"1234",cyceTime:"1231",logisticsId:"1236",logisticsId2:"1237",attribute1:"1237"},
					{cyceId:"1235",cyceTime:"1232",logisticsId:"1237",logisticsId2:"1236",attribute1:"1236"},
					{cyceId:"1236",cyceTime:"1233",logisticsId:"1238",logisticsId2:"1235",attribute1:"1235"},
					{cyceId:"1237",cyceTime:"1234",logisticsId:"1239",logisticsId2:"1234",attribute1:"1234"},
					{cyceId:"1238",cyceTime:"1235",logisticsId:"1230",logisticsId2:"1233",attribute1:"1233"},
				]
		};

		function pagingCtrl($scope,$element,$attrs){
			$scope.platdata = data;

		};
	 	function link(scope, element, attr) {
 
	    };
	    return {
			templateUrl:'partials/framework/table.html',
			restrict: 'AE',
			scope:{ 
				//platdata:"=platTable"
			},
			link:link,
			controller:pagingCtrl
	    };
	}]);
	
	return kmpmAppDirectives;
});

