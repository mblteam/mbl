'use strict';

define(['router','account/user/controller'], function (platMgrAppUiVeiw) {
 
 
  /* ui states */ 
    platMgrAppUiVeiw.config(['$stateProvider',
        function ($stateProvider) { 

          //////////////////////////
          // State Configurations //
          //////////////////////////

          // Use $stateProvider to configure your states.
          $stateProvider
            /*菜单管理,列表查询页面*/
            .state("app.user", {
              url: "/user",
              templateUrl: "partials/account/user/userList.html",
              controller:"UserCtrl"
            })
            .state("app.user.edit", {
              url: "/edit",
              views:{
                "@app" : { 
                  templateUrl: "partials/account/user/userEdit.html",
                  controller:'UserEditCtrl'
                }
              }
            })
        }]);   

  return platMgrAppUiVeiw;

});
