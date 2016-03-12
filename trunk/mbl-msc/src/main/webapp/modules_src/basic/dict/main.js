'use strict';

define(['router','basic/dict/controller'], function (platMgrAppUiVeiw) {
 
 
  /* ui states */ 
    platMgrAppUiVeiw.config(['$stateProvider',
        function ($stateProvider) { 

          //////////////////////////
          // State Configurations //
          //////////////////////////

          // Use $stateProvider to configure your states.
          $stateProvider
            /*菜单管理,列表查询页面*/
            .state("app.dict", {
              url: "/dict",
              templateUrl: "partials/basic/dict/dict.html",
              controller:"DictCtrl"
            })
        }]);   

  return platMgrAppUiVeiw;

});
