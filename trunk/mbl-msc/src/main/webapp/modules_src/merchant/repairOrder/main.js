'use strict';

define(['router','merchant/repairOrder/controller'], function (platMgrAppUiVeiw) {
 
 
  /* ui states */ 
    platMgrAppUiVeiw.config(['$stateProvider',
        function ($stateProvider) { 

          //////////////////////////
          // State Configurations //
          //////////////////////////

          // Use $stateProvider to configure your states.
          $stateProvider
            /*菜单管理,列表查询页面*/
            .state("app.repairOrder", {
              url: "/repairOrder",
              templateUrl: "partials/merchat/repairOrderList.html",
              controller:"RepairOrderCtrl"
            })
        }]);   

  return platMgrAppUiVeiw;

});
