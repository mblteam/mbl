'use strict';

define(['router','account/account/controller'], function (platMgrAppUiVeiw) {
 
 
  /* ui states */ 
    platMgrAppUiVeiw.config(['$stateProvider',
        function ($stateProvider) { 

          //////////////////////////
          // State Configurations //
          //////////////////////////

          // Use $stateProvider to configure your states.
          $stateProvider
            /*菜单管理,列表查询页面*/
            .state("app.account", {
              url: "/account",
              templateUrl: "partials/account/account/account.html",
              controller:"AccountCtrl"
            })
        }]);   

  return platMgrAppUiVeiw;

});
