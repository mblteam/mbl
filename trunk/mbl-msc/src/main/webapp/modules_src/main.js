require.config({
	baseUrl: "modules_src",
	packages: [
		"base",
		"common",
		
		"auth/menu",//功能菜单管理
		"auth/role",//功能菜单管理
		
		"basic/carType",//品牌管理
		"basic/dict",//数据字典
		"basic/pkg",//套餐管理
		
		"plat/shop",//4S店管理

		"plat/coupon",//优惠券管理
		"order",//订单管理
		"account/user",//用户管理
		"account/account",//账户管理
		
		"merchant/repairOrder",//商户维修订单管理
		"appointment",//预约单
		"settle",//提现
	],
	paths: {
		angular: "../lib/angular/angular.min",
		angularRoute: "../lib/angular-route/angular-route.min",
		angularResource: "../lib/angular-resource/angular-resource.min",
		angularUIRouter: "../lib/ui-router/release/angular-ui-router.min",
		bootstrap:"../assets/js/bootstrap.min",
		jquery: "../lib/jquery/dist/jquery.min",
		jqeasypiechart:"../assets/js/jquery.easypiechart.min",
		jqsparkline:"../assets/js/jquery.sparkline.min",
		jquicustom:"../assets/js/jquery-ui.custom.min",
		jqui:"../assets/js/jquery-ui.min",
		uiBootstrapTpls:"../assets/js/ui-bootstrap-tpls.min",
		jqflot:"../assets/js/flot/jquery.flot.min",
		jqflotpie:"../assets/js/flot/jquery.flot.pie.min",
		jqflotresize:"../assets/js/flot/jquery.flot.resize.min",
		jqvalidate:"../assets/js/jquery.validate.min",
		jqchosen:"../assets/js/chosen.jquery.min",
		jqjstree:"../lib/jstree/dist/jstree.min", 
		bootbox:"../assets/plugins/bootbox/bootbox.min",
		lunr:"../lib/lunr.js/lunr.min",
		WdatePicker:"../assets/plugins/My97DatePicker/WdatePicker",
		underscore:"../lib/underscore/underscore-min",
		dropzone:"../assets/js/dropzone.min.js",
		d3:"../lib/d3/d3.min",
		gritter:'../assets/js/jquery.gritter.min'
		
	},
	shim: {

		"angular" : {
			deps:["jquery"],
			"exports" : "angular"
		},
		"bootstrap" :{
			deps:["jquery"],
			"exports" : "bootstrap"
		},
		"angularUIRouter": {
			deps:["angular","angularRoute"],
			"exports":"uirouter"
		},
		"angularResource": {
			deps:["angular"],
			"exports":"ngresource"
		},
		"underscore": {
            exports: "_"
        },
        "WdatePicker": {
            exports: "WdatePicker"
        },
        "jqjstree": {
        	exports: "jqjstree"
        },
        "jqchosen": {
        	exports: "jqchosen"
        },
		"angularRoute": ["angular"],
		"uiBootstrapTpls": ["angular","bootstrap"],
		"bootbox":["jquery"],
		"jquicustom":["jquery"],
		"jqui":["jquery"],
		"jqeasypiechart":["jquery"]
	},
	priority: [
		"angular",
		"jquery",
		"kmpmApp"
	]
});

//http://code.angularjs.org/1.2.1/docs/guide/bootstrap#overview_deferred-bootstrap
window.name = "NG_DEFER_BOOTSTRAP!";

require( [
	"jquery",
	"angular",
	"kmpmApp",
	
	"common",
	"auth/menu",//功能菜单管理
	"auth/role",//功能菜单管理
	
	"basic/carType",//品牌管理
	"basic/dict",//数据字典
	"basic/pkg",//套餐管理
	
	"plat/shop",//4S店管理
	"plat/coupon",//优惠券管理
	"order",//订单管理
	"account/user",//用户管理
	"account/account",//账户管理
	
	"merchant/repairOrder",//商户维修订单管理
	"appointment",//预约单
	"settle",//提现
], function($,angular,kmpmApp) {

	angular.element().ready(function() { 
		angular.resumeBootstrap([kmpmApp["name"]]);
	});
	

});
