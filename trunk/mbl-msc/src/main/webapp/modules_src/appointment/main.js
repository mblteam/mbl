'use strict';

define(['router','appointment/controller'], function (platMgrAppUiVeiw) {
 
 
  /* ui states */ 
    platMgrAppUiVeiw.config(['$stateProvider',
        function ($stateProvider) { 

          //////////////////////////
          // State Configurations //
          //////////////////////////

          // Use $stateProvider to configure your states.
          $stateProvider
            /*菜单管理,列表查询页面*/
            .state("app.platAppointment", {
              url: "/platAppointment",
              templateUrl: "partials/appointment/appointmentList.html",
              controller:"PlatAppointmentCtrl"
            })
        }]);   

  return platMgrAppUiVeiw;

});
