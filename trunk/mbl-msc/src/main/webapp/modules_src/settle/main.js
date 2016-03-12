'use strict';

define(['router','settle/controller'], function (platMgrAppUiVeiw) {
 
 
  /* ui states */ 
    platMgrAppUiVeiw.config(['$stateProvider',
        function ($stateProvider) { 

          //////////////////////////
          // State Configurations //
          //////////////////////////

          // Use $stateProvider to configure your states.
          $stateProvider
            /*菜单管理,列表查询页面*/
            .state("app.settle", {
              url: "/settle",
              templateUrl: "partials/settle/settleList.html",
              controller:"SettleCtrl"
            })
        }]);   

  return platMgrAppUiVeiw;

});
