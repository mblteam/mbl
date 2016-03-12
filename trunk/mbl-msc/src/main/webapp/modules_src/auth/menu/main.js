'use strict';

define(['router','auth/menu/controller'], function (platMgrAppUiVeiw) {
 
 
  /* ui states */ 
    platMgrAppUiVeiw.config(['$stateProvider',
        function ($stateProvider) { 

          //////////////////////////
          // State Configurations //
          //////////////////////////

          // Use $stateProvider to configure your states.
          $stateProvider
            /*菜单管理,列表查询页面*/
            .state("app.authMenu", {
              url: "/authMenu",
              templateUrl: "partials/auth/menu.html",
              controller:"AuthMenuCtrl"
            })
        }]);   

  return platMgrAppUiVeiw;

});
