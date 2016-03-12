'use strict';

define(['router','order/controller'], function (platMgrAppUiVeiw) {
 
 
  /* ui states */ 
    platMgrAppUiVeiw.config(['$stateProvider',
        function ($stateProvider) { 

          //////////////////////////
          // State Configurations //
          //////////////////////////

          // Use $stateProvider to configure your states.
          $stateProvider
            /*菜单管理,列表查询页面*/
            .state("app.order", {
              url: "/order",
              templateUrl: "partials/order/orderList.html",
              controller:"OrderCtrl"
            })
        }]);   

  return platMgrAppUiVeiw;

});
