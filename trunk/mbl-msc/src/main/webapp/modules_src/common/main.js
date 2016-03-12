'use strict';

define(['router','common/controller'], function (platMgrAppUiVeiw) {
 
 
  /* ui states */ 
    platMgrAppUiVeiw.config(['$stateProvider',
        function ($stateProvider) { 

          //////////////////////////
          // State Configurations //
          //////////////////////////

          // Use $stateProvider to configure your states.
          $stateProvider
            /*商机管理,列表查询页面*/
            .state("app.common", {
              url: "/common",
              templateUrl: "partials/common/common.html",
              controller:"CommonCtrl"
            });
          
          $stateProvider
          /*商机管理,列表查询页面*/
          .state("app.commonInner", {
            url: "/commonInner",
            templateUrl: "partials/common/commonInner.html",
            controller:"CommonInnerCtrl"
          });
         
        }]);   

  return platMgrAppUiVeiw;

});
