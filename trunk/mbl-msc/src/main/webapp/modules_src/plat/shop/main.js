'use strict';

define(['router','plat/shop/controller'], function (platMgrAppUiVeiw) {
 
 
  /* ui states */ 
    platMgrAppUiVeiw.config(['$stateProvider',
        function ($stateProvider) { 

          //////////////////////////
          // State Configurations //
          //////////////////////////

          // Use $stateProvider to configure your states.
          $stateProvider
            /*菜单管理,列表查询页面*/
            .state("app.platShop", {
              url: "/platShop",
              templateUrl: "partials/plat/shop/shopList.html",
              controller:"PlatShopCtrl"
            })
		    .state("app.platShop.pkg", {
                url: "/pkg?shopId",
                views: {
                    "@app": {
                        templateUrl: "partials/plat/shop/shopPkgList.html",
                        controller: "PlatShopPkgCtrl"
                    }
                }
            });
            
        }]);   

  return platMgrAppUiVeiw;

});
