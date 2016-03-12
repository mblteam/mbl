'use strict';

define(['angular','angularUIRouter','services','jqjstree','base/common'], function (angular,router,services,jqjstree,common) {
/* Controllers */

	var kmpmAppControllers = angular.module('kmpmAppControllers', []);
	
	//top.html
  	kmpmAppControllers.controller('InitTopMenuCtrl', ['$rootScope','$scope','$window','$state','msObj',
  	     function($rootScope,$scope,$window,$state,msObj) {
	  	
  		$scope.changeAccount = function(){
  			$rootScope.leftMenuObject ={text:'免不了后台',
  				children:[
  				    //平台管理角色
  				    {text:'用户管理',
						children:[
						    {path:"app.user",text:'用户管理'},
						    {path:"app.account",text:'账号管理'}
					  	]
					},
  					{text:'权限管理',
	  					children:[
	  					    {path:"app.authMenu",text:'功能管理'},
	  					    {path:"app.authRole",text:'角色管理'}
	  				  	]
  					},
  					{text:'基础数据维护',
	  					children:[
	  					    {path:"app.dict",text:'字典管理'},
	  					    {path:"app.carType",text:'车品牌管理'},
	  					    {path:"app.pkg",text:'套餐管理'},
	  					    {path:"app.coupon",text:'优惠券管理'}
	  				  	]
  					},
  					//平台业务员角色
  					{text:'4S店信息维护',path:"app.platShop"},//
  					{text:'财务结算',path:"app.settle"},
  					//商家角色
  					{text:'订单管理',path:"app.order"},
  					{text:'商户维修订单',path:"app.repairOrder"},
  					{text:'预约查看',path:"app.platAppointment"},
  					/*{path:'demo',
	  					children:[
			  			   {path:"app.consult",path:'demo'}
			  		    ]
  					}*/
  				]
  			};
  		};
  		//$scope.changeAccount();
  		
  		//获取登录地址
        $.ajax({
            type: "GET",
            url: "./auth/login/getLoginUrl.json",
            dataType: "JSON",
            data: {LOADING:(new Date).getTime()},
            async:false,           
            success: function(obj) {  
            	if(obj.errorCode == "success" ){
            		$rootScope.LOGIN_URL = obj.result;
            	}
            },
            error: function(error){
            	alert("获取登录地址失败，页面关闭！");
            	window.close();
            }
        });
         
  		$.ajax({
            type: "GET",
            url: "./auth/login/loginInit.json",
            dataType: "JSON",
            data: {LOADING:(new Date).getTime()},
            async:false,           
            success: function(obj) {  
            	$rootScope.CURRENT_ACCOUNT = $scope.defaultAccount = obj.result['CURRENT_ACCOUNT'];
            	$rootScope.CURRENT_USER = obj.result['CURRENT_USER'];
     		   	$rootScope.leftMenuObject = obj.result['MENU_TREE'];
     		    //$scope.changeAccount();
            },
            error: function(error){
            	alert("获取账号信息失败，页面关闭！");
            	window.close();
            }
        });
  		
  		//退出
  		$scope.loginOut = function(){
  			common.dialogPrompt("退出提示","确定退出？",function(){
  				window.location.href = $rootScope.LOGIN_URL.substring(0,$rootScope.LOGIN_URL.lastIndexOf('/')+1)+'auth/login/loginOut';
  			});
  		};
  		
  	}]);

  	kmpmAppControllers.controller('InitMainCtrl', ['$scope','$window','$state',
                                                 function($scope, $window, $state) {

  		//后退
		$scope.goBack = function() {
			$window.history.back();
		};
		
		// 显示遮罩层
	    $scope.showMask = function() {
	        $("#cover").show();
	    };
	      
	    // 隐藏遮罩层
	    $scope.hideMask = function() {
	        $("#cover").hide();
	    };

		$scope.goParent = function(obj) {
			var p = [ '^' ];
			var ps = p.join('');
			if (obj !== '') {
				p.push(obj);
				ps = p.join('.');
			}
			console.info(ps);
			if ($state.get(ps).name !== 'app') {
				if ($state.get(ps).abstract) {
					$scope.goParent(p);
				} else {
					$state.go(ps);
				}
			}

		};
		$scope.isApp = function() {
			return $state.get('^').name !== 'app';
		};
  	}]);

  	kmpmAppControllers.controller('InitLeftMenuCtrl', ['$rootScope','$scope','$state','$location','$timeout','$stateParams',
  	                                                   function($rootScope,$scope,$state,$location,$timeout,$stateParams) {
  		$scope.isActive = function(uri){
  		  if(null!=uri&&-1!=uri.indexOf("(")){
  			  return $state.includes(uri.substring(0,uri.indexOf("(")),eval("("+uri.substring(uri.indexOf("(")+1,uri.length-1)+")"));
  		  }else{
  			  return $state.includes(uri);
  		  }
  		};
  		
  		$rootScope.$state = $state;
		$rootScope.$stateParams = $stateParams;
		var updateRootTime = function() {
			$timeout(function() {
				$rootScope.currentTime = new Date();
				updateRootTime();
			}, 1000);
		};

		$rootScope.currentTime = new Date();
		updateRootTime();

		$rootScope.valiPath = function(path) {

			var reg = new RegExp('^' + path + '.*', 'i');
			var lp = $location.path();
			lp = "#" + lp;
			return reg.test(lp);
		};

    }]);
  	
  	//组织机构树选择控制器
    kmpmAppControllers.controller('BizTreeAssistCtrl', ['$scope', '$modalInstance', 'msObj', 'ipParams', 'modalService', function($scope, $modalInstance, msObj, ipParams, modalService){
        //变量：当前选中的数据行index
        $scope.currentRow = -1;
        
        $scope.orgTypeMap = {};
        
		if(ipParams.orgId){
			$scope.orgId = ipParams.orgId;
		}
		if(ipParams.orgType){
			$scope.orgType = ipParams.orgType;
		}
        
        //初始化
	    $scope.init = function(){
	    	$scope.bizTreeItem = {};
			//初始化组织树，并绑定选择节点事件
          	$("#jstree_bizTree").jstree({
		          "core" : {
		              "check_callback" : true,
		              'data' : {
		            	  "url" : "../bizOrg/findChildrenBizTreeByCode.json",
			  			  "data" : function (node) {
			  				  return { "code" : null!==node.id?node.id:$scope.orgId, "orgType" : $scope.orgType, TIMESTAMP:(new Date()).getTime()};
			  			   }
			  		   }
		          },
		          "plugins" : ["search", "state", "types", "wholerow"]
		      })
		      .bind("loaded.jstree", function (event, data) { 
		    	  console.log(data);
		      })
		      .bind("select_node.jstree", function (event, data) { 
		    	  	console.log(data);
		    	  	//获得节点系列信息
		    	  	$scope.bizTreeItem = data.node.original;
		    	  	$scope.currentRow = 1;
		    	  	//是否有子节点，删除时判断
		    	  	//$scope.seriesItem.haschildren = data.node.children.length>0?true:false;
		      });
	    };
        
        //点击【选择】按钮时，关闭窗口并返回记录
	    $scope.ok = function () {
		    //选中行的数据回传给调用画面
		    $modalInstance.close($scope.bizTreeItem);
	    };
	  
	    //点击【关闭】按钮时，关闭窗口
	    $scope.cancel = function () {
	    	$modalInstance.dismiss('cancel');
	    };
	    
	    //初始化
	    setTimeout(function(){
	    	$scope.init();
		},500);
    }]);
    
    //用户树
    kmpmAppControllers.controller('UserBizTreeAssistCtrl', ['$scope', '$modalInstance', 'msObj', 'ipParams', 'modalService', function($scope, $modalInstance, msObj, ipParams, modalService){
        //变量：当前选中的数据行index
        $scope.currentRow = -1;
        
		if(ipParams.orgId){
			$scope.orgId = ipParams.orgId;
		}
		//机构节点类型定义
		if(ipParams.orgType){
			$scope.orgType = ipParams.orgType;
		}
        
        //初始化
	    $scope.init = function(){
	    	$scope.bizTreeItem = {};
	    	$scope.userItem = {};
			//初始化系列树，并绑定选择节点事件
          	$("#jstree_userBizTree").jstree({
		          "core" : {
		              "check_callback" : true,
		              'data' : {
		            	  "url" : "../bizOrg/findChildrenBizTreeByCode.json",
			  			  "data" : function (node) {
			  				  return { "code" : null!==node.id?node.id:$scope.orgId, orgType:$scope.orgType, TIMESTAMP:(new Date()).getTime()};
			  			   }
			  		   }
		          },
		          "plugins" : ["search", "state", "types", "wholerow"]
		      })
		      .bind("loaded.jstree", function (event, data) { 
		    	  console.log(data);
		      })
		      .bind("select_node.jstree", function (event, data) { 
		    	  	//获得节点系列信息
		    	  	$scope.bizTreeItem = data.node.original;
			  		$scope.onsearch();
		      });
	    };
	    $scope.onsearch = function() {
	    	if($scope.bizTreeItem.id){
	    		$scope.currentRow = -1;
	    		if(!$scope.search){
	    			$scope.search = {};
	    		}
	    		console.log($scope.bizTreeItem);
	    		$scope.userListPage = msObj.post({ use : 'bizOrg', opt : 'findAccountsByTreeOrgCode',orgId: $scope.bizTreeItem.bizTree.id
	  			},$scope.search,function(obj) {
	  				if(obj.errorCode=='success'){
	  					$scope.userListPage = obj;
	  				}
	  			});
	    	}
  		};
  		$scope.onEnterSearch = function(){
  			if(event.keyCode == 13){
  				event.returnValue = false;
  				event.cancel = true;
    			$scope.onsearch();
    		}
  		};
  		
  		//选择行前面的radio按钮时，记录当前选中的行index
        $scope.updateCurrentRow = function(userItem){
    	    $scope.currentRow = 1;
    	    $scope.userItem = userItem;
        };
        
        //点击【选择】按钮时，关闭窗口并返回记录
	    $scope.ok = function () {
	    	//选中行的数据回传给调用画面
			$modalInstance.close($scope.userItem);
	    };
	  
	    //点击【关闭】按钮时，关闭窗口
	    $scope.cancel = function () {
	    	$modalInstance.dismiss('cancel');
	    };
	    
	    //初始化
	    setTimeout(function(){
	    	$scope.init();
		},500);
    }]);
    
    //上传文件控制器
    kmpmAppControllers.controller('UploadFileAssistCtrl', ['$scope', '$modalInstance', 'msObj', 'ipParams', 'modalService', function($scope, $modalInstance, msObj, ipParams, modalService){
        //变量：当前选中的数据行index
        $scope.currentRow = -1;
        
		$scope.uploadType = ipParams.uploadType;
		$scope.uploadTitle = ipParams.uploadTitle;
        $scope.uploadPath = ipParams.uploadPath;
        //点击【选择】按钮时，关闭窗口并返回记录
	    $scope.ok = function () {
		    //选中行的数据回传给调用画面
		    $modalInstance.close($("#fileInfoId").val()); 
	    };
	  
	    //点击【关闭】按钮时，关闭窗口
	    $scope.cancel = function () {
		    $modalInstance.dismiss('cancel');
	    };
	 
    }]);
    
    kmpmAppControllers.controller('ShopMutiAssistCtrl', ['$scope', '$filter', '$modalInstance', 'msObj', 'orgDictService', 'ipParams', 'dictService',
                                                         function($scope, $filter, $modalInstance, msObj, orgDictService, ipParams, dictService){
	      //变量：当前选中的数据行index
	      $scope.currentRow = -1;
	  
	      //接收传入的参数，查询条件部各项目初始化
		  $scope.search = {};
		  $scope.search.pageSize = 10; //一页显示5条
		  
		  //字典加载
		  dictService($scope,[{'cooperate_status':'dict_cooperate_status'}]);
		  dictService($scope,[{'balance_type':'dict_balance_type'}]);
		  dictService($scope,[{'shop_status':'dict_shop_status'}]);
		  dictService($scope,[{'shop_type':'dict_shop_type'}]);
		  
	      //点击【查询】按钮时执行查询
	      $scope.query = function(){
	    	  $scope.list = msObj.post({md : 'plat',
					use : 'shop',
					opt : 'findShopList'}, $scope.search, function(obj){
		          $scope.list = obj;
		          //重置选中行index
		          $scope.currentRow = -1;
		      });
	      };
	      
	      //选择行前面的radio按钮时，记录当前选中的行index
	      $scope.updateCurrentRow = function(data,index){
	    	  if(data['checked']==true){
	    		  data['checked'] = false;
	    	  }else{
	    		  data['checked'] = true;
	    	  }
	    	  
	    	  var customerCount = 0;
	    	  $($scope.list.data).each(function(index,item){
	    		  if(item['checked']==true){
	    			  customerCount = customerCount + 1;
	    		  }
	    	  });
	    	  if(customerCount>0){
	    		  $scope.currentRow = index;
	    	  }else{
	    		$scope.currentRow = -1;
	    	  }
	      };
	      
	      //点击【选择】按钮时，关闭窗口并返回记录
		  $scope.ok = function () {
			  var objList = [];
			  $($scope.list.data).each(function(index,item){
	    		  if(item['checked']==true){
	    			  objList.push(item);
	    		  }
	    	  });
			  //选中行的数据回传给调用画面
			  $modalInstance.close({result:objList});
		  };
		  
		  //点击【关闭】按钮时，关闭窗口
		  $scope.cancel = function () {
			  $modalInstance.dismiss('cancel');
		  };
		  
		  //checkbox选择框的on/off动作
		  $scope.toggleCheckbox = function(checked, dList, chkColNm){
            for (var x in dList) {
            	dList[x][chkColNm] = checked;
            };
            var customerCount = 0;
	    	  $($scope.list.data).each(function(index,item){
	    		  if(item['checked']==true){
	    			  customerCount = customerCount + 1;
	    		  }
	    	  });
	    	  if(customerCount>0){
	    		  $scope.currentRow = customerCount;
	    	  }else{
	    		$scope.currentRow = -1;
	    	  }
		  };
		
		  $scope.toggleCheckboxNo = function(){
          $scope.customerAssistCheckbox = false;
		  };
		  
		  //初始化时执行查询
		  $scope.query();
		  
		  //监视当前页的变化，重置选中行index
		  $scope.$watch(
			  //function(){return $scope.list.currentPage;}, 
			  function(newVal, oldVal){
				  if(newVal !== undefined && oldVal !== undefined && newVal !== oldVal){
					  //切换当前页时，重置选中行index
			          $scope.currentRow = -1;
				  }
			  }
		  );
	  }
  ]);
    kmpmAppControllers.controller('ShopSingleAssistCtrl', ['$scope', '$filter', '$modalInstance', 'msObj', 'orgDictService', 'ipParams', 'dictService',
                                                         function($scope, $filter, $modalInstance, msObj, orgDictService, ipParams, dictService){
    	//变量：当前选中的数据行index
    	$scope.currentRow = -1;
    	
    	//接收传入的参数，查询条件部各项目初始化
    	$scope.search = {};
    	$scope.search.pageSize = 10; //一页显示5条
    	
    	//字典加载
    	dictService($scope,[{'cooperate_status':'dict_cooperate_status'}]);
    	dictService($scope,[{'balance_type':'dict_balance_type'}]);
    	dictService($scope,[{'shop_status':'dict_shop_status'}]);
    	dictService($scope,[{'shop_type':'dict_shop_type'}]);
    	
    	//点击【查询】按钮时执行查询
    	$scope.query = function(){
    		$scope.list = msObj.post({md : 'plat',
    			use : 'shop',
    			opt : 'findShopList'}, $scope.search, function(obj){
    				$scope.list = obj;
    				//重置选中行index
    				$scope.currentRow = -1;
    			});
    	};
    	
    	//选择行前面的radio按钮时，记录当前选中的行index
    	$scope.updateCurrentRow = function(index){
    		$scope.currentRow = index;
    	};
    	
    	//点击【选择】按钮时，关闭窗口并返回记录
    	$scope.ok = function () {
    		var objList = [];
    		$($scope.list.data).each(function(index,item){
    			if(item['checked']==true){
    				objList.push(item);
    			}
    		});
    		//选中行的数据回传给调用画面
    		$modalInstance.close($scope.list.data[$scope.currentRow]);
    	};
    	
    	//点击【关闭】按钮时，关闭窗口
    	$scope.cancel = function () {
    		$modalInstance.dismiss('cancel');
    	};
    	
    	//初始化时执行查询
    	$scope.query();
    	
    	//监视当前页的变化，重置选中行index
    	$scope.$watch(
    			//function(){return $scope.list.currentPage;}, 
    			function(newVal, oldVal){
    				if(newVal !== undefined && oldVal !== undefined && newVal !== oldVal){
    					//切换当前页时，重置选中行index
    					$scope.currentRow = -1;
    				}
    			}
    	);
    }
    ]);
    
    return kmpmAppControllers;
});