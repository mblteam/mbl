'use strict';

define(['router','plat/coupon/controller'], function (platMgrAppUiVeiw) {
 
 
  /* ui states */ 
    platMgrAppUiVeiw.config(['$stateProvider',
        function ($stateProvider) { 

          //////////////////////////
          // State Configurations //
          //////////////////////////

          // Use $stateProvider to configure your states.
          $stateProvider
            /*菜单管理,列表查询页面*/
            .state("app.coupon", {
              url: "/platCoupon",
              templateUrl: "partials/plat/coupon/couponList.html",
              controller:"CouponCtrl"
            })
        }]);   

  return platMgrAppUiVeiw;

});
