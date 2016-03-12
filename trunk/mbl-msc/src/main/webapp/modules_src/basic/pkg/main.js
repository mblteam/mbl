'use strict';

define(['router','basic/pkg/controller'], function (platMgrAppUiVeiw) {
 
 
  /* ui states */ 
    platMgrAppUiVeiw.config(['$stateProvider',
        function ($stateProvider) { 

          //////////////////////////
          // State Configurations //
          //////////////////////////

          // Use $stateProvider to configure your states.
          $stateProvider
            /*菜单管理,列表查询页面*/
            .state("app.pkg", {
              url: "/pkg",
              templateUrl: "partials/basic/pkg/pkgList.html",
              controller:"PkgCtrl"
            })
            .state("app.pkg.edit", {
              url: "/edit",
              views:{
                "@app" : { 
                  templateUrl: "partials/basic/pkg/pkgEdit.html",
                  controller:'PkgEditCtrl'
                }
              }
            })
        }]);   

  return platMgrAppUiVeiw;

});
