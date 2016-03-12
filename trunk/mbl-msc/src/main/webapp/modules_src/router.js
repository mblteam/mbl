 'use strict';

define(['angular','angularUIRouter','controllers'], function (angular,router,controllers) {

  /* ui states */

  // Make sure to include the `ui.router` module as a dependency.
  var platMgrAppUiVeiw = angular.module('kmpmAppUIRouter',['ui.router'])
    .config(
      [          '$stateProvider', '$urlRouterProvider',
        function ($stateProvider,   $urlRouterProvider) { 

          /////////////////////////////
          // Redirects and Otherwise //
          /////////////////////////////
   
          $urlRouterProvider

            // The `when` method says if the url is ever the 1st param, then redirect to the 2nd param
            // Here we are just setting up some convenience urls.
            .when('/', '/home')
            //暂时这样用，这个需要修改 
            // .when('/login.html', '/')

            // If the url is ever invalid, e.g. '/asdf', then redirect to '/' aka the home state
            .otherwise("/home");


          //////////////////////////
          // State Configurations //
          //////////////////////////

          // Use $stateProvider to configure your states.
          $stateProvider
      

            //////////
            // main //
            //////////
            .state("app", {
              abstract:true,
              // Use a url of "/" to set a states as the "index".
              url: "",

              views: {
                      "top": {
                          templateUrl: "partials/framework/top.html",
                          controller:'InitTopMenuCtrl'
                      },
                      "left": {
                          templateUrl: "partials/framework/left.html",
                          controller:'InitLeftMenuCtrl'
                      },
                      "main": {
                          controller:'InitMainCtrl',
                          templateUrl: "partials/framework/main.html"
                      },
                      "foot": {
                          templateUrl: "partials/framework/foot.html"
                      }
              }

            })
            .state("app.home", {
              url: "/home",
              templateUrl: "partials/home.html"
            })
            
         
         
              ////////////////////
              /// 周龙do begin ///
              ///////////////////
             //////////促销价格 ~ 促销价格
            /* .state("app.promotprice", {
              url: "/promotprice",
              controller:'PromotpriceCtrl',
              templateUrl: "partials/promot/promotprice.html"
            })
            //////////促销价格 ~ 体检单
            .state("app.promotexam", {
              url: "/promotexam",
              controller:'PromotexamCtrl',
              templateUrl: "partials/promot/promotexam.html"
            })*/
            /*//销售目标管理 ~ 总销售目标
            .state("app.saletargetall", {
              url: "/saletargetall",
              controller:'SaletargetallCtrl',
              templateUrl: "partials/saletarget/saletargetall.html"
            })
            //////////销售目标管理 ~ 产品系列销售目标
            .state("app.saletargetpro", {
              url: "/saletargetpro",
              controller:'SaletargetproCtrl',
              templateUrl: "partials/saletarget/saletargetpro.html"
            })
            //////////销售目标管理 ~ 子公司销售目标
            .state("app.saletargetallChild", {
              url: "/saletargetallChild",
              controller:'SaletargetallChildCtrl',
              templateUrl: "partials/saletarget/saletargetallChild.html"
            })
            //////////销售目标管理 ~ 子公司产品系列目标
            .state("app.saletargetproChild", {
              url: "/saletargetproChild",
              controller:'SaletargetproChildCtrl',
              templateUrl: "partials/saletarget/saletargetproChild.html"
            })
            //////////销售目标管理 ~ 全国日销售报表
            .state("app.salereportcountry", {
              url: "/salereportcountry",
              templateUrl: "partials/saletarget/salereportcountry.html"
            })
            //////////销售目标管理 ~ 系列设置
            .state("app.saleseries", {
              url: "/saleseries",
              controller:"SaleseriesCtrl",
              templateUrl: "partials/saletarget/saleseries.html"
            })*/
            //////////非常规结算管理
            /*.state("app.settleun", {
              url: "/settleun",
              controller:'SettleunCtrl',
              templateUrl: "partials/settleun/settleun.html"
            })*/

            //////////
            // Home //
            //////////
            // .state("app.home", {
            //   url: "/home",
            //   templateUrl: "partials/home.html"
            // })
            // /*集团客户*/
            // .state("app.groupcompany", {
            //   url: "/groupcompany",
            //   controller:'BlocListCtrl',
            //   templateUrl: "partials/groupcompany/bloclist.html" 
            // })
            // .state("app.groupcompany.add", {
            //   url: "/add",
            //   views:{
            //     "@app" : {  
            //       templateUrl: "partials/groupcompany/baseinfo.html"
            //     }
            //   }
            // })
            // .state("app.groupcompany.edit", {
            //   abstract:true,
            //   url: "/edit",
            //   views:{
            //     "@app" : { 
            //       templateUrl: "partials/groupcompany/editbloc.html"
            //     }
            //   }
            // })
            // .state("app.groupcompany.edit.baseinfo", {/*基础信息*/
            //   url: "/baseinfo",
            //   templateUrl: "partials/groupcompany/baseinfo.html"
            // })
            // .state("app.groupcompany.edit.hospital", {/*下属医院*/
            //   url: "/hospital",
            //   templateUrl: "partials/groupcompany/hospital.html"
            // })

            // /*医院机构*/
            // .state("app.hospital", {
            //   url: "/hospital",
            //   controller:'HospListCtrl',
            //   templateUrl: "partials/hospital/hospitallist.html"
            // })
            // .state("app.hospital.add", {
            //   url: "/add",
            //   views:{
            //     "@app" : { 
            //       templateUrl: "partials/hospital/baseinfo.html"
            //     }
            //   }
            // })
            // .state("app.hospital.edit", {
            //   abstract:true,
            //   url: "/edit",
            //   views:{
            //     "@app" : { 
            //       templateUrl: "partials/hospital/edithospital.html"
            //     }
            //   }
            // })
            // .state("app.hospital.edit.baseinfo", {/*基础信息*/
            //   url: "/baseinfo",
            //   templateUrl: "partials/hospital/baseinfo.html"
            // })
            // .state("app.hospital.edit.business", {/*业务开展*/
            //   url: "/business",
            //   templateUrl: "partials/hospital/business.html"
            // })
            // .state("app.hospital.edit.logistics", {/*医检物流信息*/
            //   url: "/logistics",
            //   templateUrl: "partials/hospital/logistics.html"
            // })
            // .state("app.hospital.edit.logisticskmct", {/*KMCT物流信息*/
            //   url: "/logisticskmct",
            //   templateUrl: "partials/hospital/logistics.html"
            // })
            // .state("app.hospital.edit.detection", {/*医检检测要求*/
            //   url: "/detection",
            //   templateUrl: "partials/hospital/detection.html"
            // })
            // .state("app.hospital.edit.special", {/*医检特殊要求*/
            //   url: "/special",
            //   templateUrl: "partials/hospital/special.html"
            // })
            // .state("app.hospital.edit.special.add", {/*医检特殊要求*/
            //   url: "/add",
            //   views:{
            //     "@app.hospital.edit" : { 
            //       templateUrl: "partials/hospital/detection.html"
            //     }
            //   }
            // })
            // .state("app.hospital.edit.department", {/*科室信息*/
            //   url: "/department",
            //   templateUrl: "partials/hospital/department.html"
            // })
            // .state("app.hospital.edit.contacts", {/*通讯录*/
            //   url: "/contacts",
            //   templateUrl: "partials/hospital/contacts.html"
            // })
            // .state("app.hospital.edit.custinfo", {/*客服信息*/
            //   url: "/custinfo",
            //   templateUrl: "partials/hospital/custinfo.html"
            // })
            // /*科室档案*/
            // .state("app.department", {
            //   url: "/department",
            //   templateUrl: "partials/department/departmentlist.html"
            // })
            // .state("app.department.add", {
            //   url: "/add",
            //   views:{
            //     "@app" : {
            //       templateUrl: "partials/department/baseinfo.html"
            //     }
            //   }
            // })
            // .state("app.department.edit", {
            //   abstract:true,
            //   url: "/edit",
            //   views:{
            //     "@app" : { 
            //       templateUrl: "partials/department/editdepartment.html"
            //     }
            //   }
            // })
            // .state("app.department.edit.baseinfo", {/*基础信息*/
            //   url: "/baseinfo",
            //   templateUrl: "partials/department/baseinfo.html"
            // })
            // .state("app.department.edit.business", {/*业务开展*/
            //   url: "/business",
            //   templateUrl: "partials/department/business.html"
            // })
            // .state("app.department.edit.logistics", {/*科室物流信息*/
            //   url: "/logistics",
            //   templateUrl: "partials/department/logistics.html"
            // })
            // .state("app.department.edit.logisticskmct", {/*KMCT物流信息*/
            //   url: "/logisticskmct",
            //   templateUrl: "partials/department/logistics.html"
            // })
            // .state("app.department.edit.detection", {/*科室检测要求*/
            //   url: "/detection",
            //   templateUrl: "partials/department/detection.html"
            // })
            // .state("app.department.edit.special", {/*科室特殊要求*/
            //   url: "/special",
            //   templateUrl: "partials/department/special.html"
            // })
            // .state("app.department.edit.special.add", {/*科室添加特殊要求*/
            //   url: "/add",
            //   views:{
            //     "@app.department.edit" : { 
            //       templateUrl: "partials/department/detection.html"
            //     }
            //   }
            // })
            // .state("app.department.edit.contacts", {/*通讯录*/
            //   url: "/contacts",
            //   templateUrl: "partials/department/contacts.html"
            // })
            // .state("app.department.edit.custinfo", {/*客服信息*/
            //   url: "/custinfo",
            //   templateUrl: "partials/department/custinfo.html"
            // })
            // /*医务人员档案*/
            // .state("app.medical", {
            //   url: "/medical",
            //   templateUrl: "partials/medical/medicallist.html"
            // })
            // .state("app.medical.add", {
            //   url: "/add",
            //   views:{
            //     "@app" : {
            //       templateUrl: "partials/medical/baseinfo.html"
            //     }
            //   }
            // })
            // .state("app.medical.edit", {
            //   abstract:true,
            //   url: "/edit",
            //   views:{
            //     "@app" : { 
            //       templateUrl: "partials/medical/editmedical.html"
            //     }
            //   }
            // })
            // .state("app.medical.edit.baseinfo", {/*基础信息*/
            //   url: "/baseinfo",
            //   templateUrl: "partials/medical/baseinfo.html"
            // })
            // .state("app.medical.edit.business", {/*业务开展*/
            //   url: "/business",
            //   templateUrl: "partials/medical/business.html"
            // })
            // .state("app.medical.edit.custinfo", {/*客服信息*/
            //   url: "/custinfo",
            //   templateUrl: "partials/medical/custinfo.html"
            // })
            // /*个人客户*/
            // .state("app.personal", {
            //   url: "/personal",
            //   templateUrl: "partials/personal/personallist.html"
            // })
            // .state("app.personal.add", {
            //   url: "/add",
            //   views:{
            //     "@app" : {
            //       templateUrl: "partials/personal/baseinfo.html"
            //     }
            //   }
            // })
            // .state("app.personal.edit", {
            //   abstract:true,
            //   url: "/edit",
            //   views:{
            //     "@app" : { 
            //       templateUrl: "partials/personal/editpersonal.html"
            //     }
            //   }
            // })
            // .state("app.personal.edit.baseinfo", {/*基础信息*/
            //   url: "/baseinfo",
            //   templateUrl: "partials/personal/baseinfo.html"
            // })
            // .state("app.personal.edit.compact", {/*合同信息*/
            //   url: "/compact",
            //   templateUrl: "partials/personal/compact.html"
            // })
            // .state("app.personal.edit.custinfo", {/*客服纪录*/
            //   url: "/custinfo",
            //   templateUrl: "partials/personal/custinfo.html"
            // })
            // .state("app.personal.edit.workorder", {/*客服工单*/
            //   url: "/workorder",
            //   templateUrl: "partials/personal/workorder.html"
            // })
            // /*公司客户*/
            // .state("app.company", {
            //   url: "/company",
            //   templateUrl: "partials/company/companylist.html"
            // })
            // .state("app.company.add", {
            //   url: "/add",
            //   views:{
            //     "@app" : {
            //       templateUrl: "partials/company/baseinfo.html"
            //     }
            //   }
            // })
            // .state("app.company.edit", {
            //   abstract:true,
            //   url: "/edit",
            //   views:{
            //     "@app" : { 
            //       templateUrl: "partials/company/editcompany.html"
            //     }
            //   }
            // })
            // .state("app.company.edit.baseinfo", {/*基础信息*/
            //   url: "/baseinfo",
            //   templateUrl: "partials/company/baseinfo.html"
            // })
            // .state("app.company.edit.contacts", {/*基础信息*/
            //   url: "/contacts",
            //   templateUrl: "partials/company/contacts.html"
            // })
            // .state("app.company.edit.compact", {/*合同信息*/
            //   url: "/compact",
            //   templateUrl: "partials/company/compact.html"
            // })
            // .state("app.company.edit.custinfo", {/*客服纪录*/
            //   url: "/custinfo",
            //   templateUrl: "partials/company/custinfo.html"
            // })
            // .state("app.company.edit.workorder", {/*客服工单*/
            //   url: "/workorder",
            //   templateUrl: "partials/company/workorder.html"
            // })

            // /*合同管理*/
            // .state("app.compact", {
            //   url: "/compact",
            //   templateUrl: "partials/compact/compactlist.html"
            // })
            // .state("app.compact.add", {
            //   url: "/add",
            //   views:{
            //     "@app" : {
            //       templateUrl: "partials/compact/baseinfo.html"
            //     }
            //   }
            // })
            // .state("app.compact.edit", {
            //   abstract:true,
            //   url: "/edit",
            //   views:{
            //     "@app" : { 
            //       templateUrl: "partials/compact/editcompact.html"
            //     }
            //   }
            // })
            // .state("app.compact.edit.baseinfo", {/*基本信息*/
            //   url: "/baseinfo",
            //   templateUrl: "partials/compact/baseinfo.html"
            // })
            // .state("app.compact.edit.logistics", {/*结算和物流信息*/
            //   url: "/logistics",
            //   templateUrl: "partials/compact/logistics.html"
            // })

            // .state("app.compact.edit.mscompact", {/*主子合同设置*/
            //   url: "/mscompact",
            //   templateUrl: "partials/compact/mscompact.html"
            // })
            // .state("app.compact.edit.litigation", {/*诉讼案件记录*/
            //   url: "/litigation",
            //   templateUrl: "partials/compact/litigation.html"
            // })
            // .state("app.compact.edit.discountsettle", {/*按扣率结算项目价格表*/
            //   url: "/discountsettle",
            //   templateUrl: "partials/compact/discountsettle.html"
            // })
            // .state("app.compact.edit.pricesettle", {/*按实收价格结算项目价格表*/
            //   url: "/pricesettle",
            //   templateUrl: "partials/compact/pricesettle.html"
            // })
            // .state("app.compact.edit.promotion", {/*按业务量促销*/
            //   url: "/promotion",
            //   templateUrl: "partials/compact/promotion.html"
            // })
            // /*物价管理*/
            // .state("app.priceCodeManage", { // 物价代码管理
            //   url: "/priceCodeManage",
            //   controller:'PriceCodeManage',
            //   templateUrl: "partials/projectPriceManage/priceCodeManage.html"
            // })
            // .state("app.priceLevelManage", { // 物价级别管理
            //   url: "/priceLevelManage",
            //   controller:'PriceLevelManage',
            //   templateUrl: "partials/projectPriceManage/priceLevelManage.html"
            // })
            // .state("app.priceVersionManage", { // 物价版本管理
            //   url: "/priceVersionManage",
            //   controller:'PriceVersionManage',
            //   templateUrl: "partials/projectPriceManage/priceVersionManage.html"
            // })
            // .state("app.projectGroupManage", { // 项目集团管理
            //   url: "/projectGroupManage",
            //   controller:'ProjectGroupManageListCtrl',
            //   templateUrl: "partials/projectPriceManage/projectGroupManage.html"
            // })
            //   ////////////////////
            //   /// 周龙do begin ///
            //   ///////////////////
            //  //////////促销价格 ~ 促销价格
            //  .state("app.promotprice", {
            //   url: "/promotprice",
            //   controller:'PromotpriceCtrl',
            //   templateUrl: "partials/promot/promotprice.html"
            // })
            // .state("app.promotprice.add", {
            //   url: "/add",
            //   controller:'promotpriceAddControl',
            //   views: {
            //       "@app": {
            //           templateUrl: "partials/promot/promotpriceAdd.html"
            //       }
            //     }
            // })
            // //////////促销价格 ~ 体检单
            // .state("app.promotexam", {
            //   url: "/promotexam",
            //   controller:'PromotexamCtrl',
            //   templateUrl: "partials/promot/promotexam.html"
            // })
            // .state("app.promotexam.add", {
            //   url: "/add",
            //   views: {
            //       "@app": {
            //           templateUrl: "partials/promot/promotexamAdd.html"
            //       }
            //   }
            // })
            // //////////销售目标管理 ~ 总销售目标
            // .state("app.saletargetall", {
            //   url: "/saletargetall",
            //   controller:'SaletargetallCtrl',
            //   templateUrl: "partials/saletarget/saletargetall.html"
            // })
            // //////////销售目标管理 ~ 产品系列销售目标
            // .state("app.saletargetpro", {
            //   url: "/saletargetpro",
            //   controller:'SaletargetproCtrl',
            //   templateUrl: "partials/saletarget/saletargetpro.html"
            // })
            // //////////销售目标管理 ~ 子公司销售目标
            // .state("app.saletargetallChild", {
            //   url: "/saletargetallChild",
            //   controller:'SaletargetallChildCtrl',
            //   templateUrl: "partials/saletarget/saletargetallChild.html"
            // })
            // //////////销售目标管理 ~ 子公司产品系列目标
            // .state("app.saletargetproChild", {
            //   url: "/saletargetproChild",
            //   controller:'SaletargetproChildCtrl',
            //   templateUrl: "partials/saletarget/saletargetproChild.html"
            // })
            // //////////销售目标管理 ~ 全国日销售报表
            // .state("app.salereportcountry", {
            //   url: "/salereportcountry",
            //   templateUrl: "partials/saletarget/salereportcountry.html"
            // })
            // //////////销售目标管理 ~ 系列设置
            // .state("app.saleseries", {
            //   url: "/saleseries",
            //   controller:"SaleseriesCtrl",
            //   templateUrl: "partials/saletarget/saleseries.html"
            // })
            // //////////非常规结算管理
            // .state("app.settleun", {
            //   url: "/settleun",
            //   controller:'SettleunCtrl',
            //   templateUrl: "partials/settleun/settleun.html"
            // })
            // .state("app.settleun.add", {
            //   url: "/add",
            //   views: {
            //       "@app": {
            //           templateUrl: "partials/settleun/settleunAdd.html"
            //       }
            //   }
            // })
            ////////////////////
            /// 周龙do end ///
            ///////////////////
            /*end off*/
   
            ;
    
         
        }]);  
  return platMgrAppUiVeiw;

});
