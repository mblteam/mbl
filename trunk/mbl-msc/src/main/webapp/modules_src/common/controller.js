'use strict';

define(['controllers','base/common'], function (kmpmAppControllers,common) {
	
	 kmpmAppControllers.controller('CommonCtrl', ['$rootScope','$scope','$filter','modalService', 'orgDictService','dictService','msObj', 'superCache', '$state',
	     function($rootScope,$scope,$filter, modalService, orgDictService, dictService, msObj, superCache, $state) {
		 
		 	
		 	$scope.$on("loginInit",function(event,data){
		 		//获取账号所属部门 取 orgCode/orgName 
		 		$scope.curCompany = $rootScope.curCompany;
		 		//获取账号所属子公司 取 orgCode/orgName
		 		$scope.curDept = $rootScope.curDept;
		 	});
		 
		 	//获得子公司 
		 	orgDictService($scope,[{company:'companys',cache:true}]);
		    
		 	orgDictService($scope,[{officeLB:'officeLB'}],function(){
		 		console.log($scope.officeLB);
		 	});
		 	
		    //获得当前子公司下的部门
		 	$scope.initDept = function(){
		    	$scope.deptIdMap = $filter("treeJSON")($scope.DEPARTMENT,['orgCode']);
		    };
		 	orgDictService($scope,[{department:'DEPARTMENT',cache:true,callback:'initDept'}]);
		 	
		 	$scope.initSomeThing = function(){
		 		
		 	};
		 	//字典缓存
		 	dictService($scope,[{MS_CUSTOMER_GRADE:'MS_CUSTOMER_GRADE',callback:'initSomeThing'},{MS_REPORT_HEAD:'MS_REPORT_HEAD'}]);
	 	
		 	//选择客户
	    	$scope.selectCustomerDialog = function(){
	    		modalService.showCustomerAssist({},function(customer){
	    			console.log(customer);
	    			$scope.customer = customer;
	    		});
	    	};
	    	
	    	//选择检测项目
	    	$scope.selectMpTestItemDialog = function(){
	    		modalService.showMptestitemAssist({},function(mpTestItem){
	    			console.log(mpTestItem);
	    		});
	    	};
	    	
	    	//选择营销套餐
	    	$scope.selectMarketingDialog = function(){
	    		modalService.showMarketingAssist({},function(marketing){
	    			console.log(marketing);
	    			$scope.mpInfo = marketing;
	    		});
	    	};
	    	
	    	//选择合同
	    	$scope.selectContractDialog = function(){
	    		modalService.showContractAssist({},function(contract){
	    			$scope.contract = contract;
	    		});
	    	};
	    	
	    	//选择耗材
	    	$scope.selectSuppliesDialog = function(){
	    		modalService.showChoiceSuppliesAssist({},function(supplies){
	    			$scope.supplies = supplies;
	    		});
	    	};
	    	
	    	//选择工单
	    	$scope.selectTicketDialog = function(){
	    		modalService.showChoiceOrderAssist({},function(ticket){
	    			$scope.ticket = tiket;
	    		});
	    	};
	    	
	    	//选择部门
	    	$scope.selectDeptDialog = function(){
	    		modalService.showBizTreeAssist({},function(dept){
	    			$scope.dept = dept;
	    		});
	    	};
	    	
	    	//选择人
	    	$scope.selectPersonDialog = function(){
	    		modalService.showUserBizTreeAssist({},function(person){
	    			$scope.person = person['accountName'];
	    		});
	    	};
	    	
	    	
	    	
	   
	    	
	    	//通过客户id获取合同信息
	    	$scope.selectContractByCustomerId = function(){
	    		if($scope.customer&&$scope.customer.customerId){
	    			msObj.get({
	    				md : 'common',
						use : 'assist',
						opt : 'getContractByCustomerId',
						customerId : $scope.customer.customerId
	    			},function(obj){
	    				if(obj.errorCode=="success"){
	    					$scope.contract = obj.result;
	    					console.log(obj);
	    				}else{
	    					alert("查询出错,错误原因:"+obj.errorMsg);
	    				}
	    			});
	    		}else{
	    			alert("请选择客户");
	    		}
	    	};
	    	
	    	//根据用户id,营销套餐id查询出检测项目
	    	$scope.selectMpTestItemByCustomerIdAndMpId = function(){
	    		if($scope.mpInfo&&$scope.mpInfo.mpId){
	    			msObj.get({
	    				md : 'common',
						use : 'assist',
						opt : 'findMpTestItemByCustomerIdAndMpId',
						customerId : $scope.customer?$scope.customer.customerId:"",
						mpId : $scope.mpInfo.mpId
	    			},function(obj){
	    				if(obj.errorCode=="success"){
	    					$scope.mpTestItemList = obj.result;
	    				}else{
	    					alert("查询出错,错误原因:"+obj.errorMsg);
	    				}
	    			});
	    		}else{
	    			alert("请选择套餐");
	    		}
	    	};
	    	
	    	//根据客户id获取客户开展信息
	    	$scope.getCustomerBinfoByCustomerId = function(){
	    		if($scope.customer&&$scope.customer.customerId){
	    			msObj.get({
	    				md : 'common',
						use : 'assist',
						opt : 'getCustomerBinfoByCustomerId',
						customerId : $scope.customer?$scope.customer.customerId:""
	    			},function(obj){
	    				if(obj.errorCode=="success"){
	    					$scope.customerBinfo = obj.result;
	    				}else{
	    					alert("查询出错,错误原因:"+obj.errorMsg);
	    				}
	    			});
	    		}else{
	    			alert("请选择客户");
	    		}
	    	};
	     }
	 ]);
	 
	 kmpmAppControllers.controller('CommonInnerCtrl', ['$rootScope','$scope','modalService', 'orgDictService','dictService','msObj', 'superCache', '$state',
	                                     	     function($rootScope,$scope, modalService, orgDictService, dictService, msObj, superCache, $state) {
		 //选择kmct项目档案
		 $scope.selectKmctProjectDialog = function(){
			 modalService.showProjectAssist({projectType:'KMCT'},function(project){
				 $scope.kmctProject = project;
			 });
		 };
    	
		 //选择大客户项目档案
		 $scope.selectDkhProjectDialog = function(){
			 modalService.showProjectAssist({projectType:'DKH'},function(project){
				 $scope.dkhProject = project;
			 });
		 };
		 
		 //选择科研项目档案
		 $scope.selectKyzhProjectDialog = function(){
			 modalService.showProjectAssist({projectType:'KYZH'},function(project){
				 $scope.kyzhProject = project;
			 });
		 };
    	
     }]);

    return kmpmAppControllers;

});



