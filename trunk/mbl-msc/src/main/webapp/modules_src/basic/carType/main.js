'use strict';

define(['router','basic/carType/controller'], function (platMgrAppUiVeiw) {
 
 
  /* ui states */ 
    platMgrAppUiVeiw.config(['$stateProvider',
        function ($stateProvider) { 

          //////////////////////////
          // State Configurations //
          //////////////////////////

          // Use $stateProvider to configure your states.
          $stateProvider
            /*菜单管理,列表查询页面*/
            .state("app.carType", {
              url: "/carType",
              templateUrl: "partials/basic/carType/carType.html",
              controller:"CarTypeCtrl"
            })
        }]);   

  return platMgrAppUiVeiw;

});
