'use strict';

define(['router','auth/role/controller'], function (platMgrAppUiVeiw) {
 
 
  /* ui states */ 
    platMgrAppUiVeiw.config(['$stateProvider',
        function ($stateProvider) { 

          //////////////////////////
          // State Configurations //
          //////////////////////////

          // Use $stateProvider to configure your states.
          $stateProvider
            /*菜单管理,列表查询页面*/
            .state("app.authRole", {
              url: "/authRole",
              templateUrl: "partials/auth/role.html",
              controller:"AuthRoleCtrl"
            })
        }]);   

  return platMgrAppUiVeiw;

});
