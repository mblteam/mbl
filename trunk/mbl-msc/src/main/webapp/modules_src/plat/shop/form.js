'use strict';

define(['jqvalidate', 'base/validator'], function () {
 
	var handleShopValidate = function(){
		$('#baseinfo-form').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules: {
            	shopCode: {
                    required: [true, "店铺编号"],
                    maxLength: 10
                },
                shopName: {
                	required: [true, "店铺名称"],
                    maxLength: 100
                },
                address: {
                	required: [true, "店铺地址"],
                    maxLength: 200
                },
                longitude: {
                	required: [true, "经度"],
                	number: true
                },
                latitude: {
                	required: [true, "维度"],
                	number: true
                },
                discountUpperLimit: {
                	required: [true, "合同折扣上限"],
                	number: true,
                	max :100,
                	min : 1
                },
                discountLowerLimit: {
                	required: [true, "合同折扣下限"],
                	number: true,
                	max :100,
                	min : 1
                },
                cooperateStatus: {
                	required: [true, "合作状态"]
                },
                cooperateStartDate: {
                	required: [true, "合作开始日期"]
                },
                cooperateEndDate: {
                	required: [true, "合作结束日期"]
                },
                level: {
                	required: [true, "推荐等级"],
                	number: true
                },
                balanceType: {
                	required: [true, "结算方式"]
                },
                shopStatus: {
                	required: [true, "店铺状态"]
                },
                tel: {
                	required: [true, "联系方式"]
                },
                introduce: {
                	required: [true, "店铺介绍"],
                	maxLength: 500
                },
                shopType: {
                	required: [true, "店铺类型"]
                },
                url: {
                	required: [true, "店铺图片"]
                },
                accountCode : {
                	required: [true, "登录系统账号"],
                	maxLength: 20
                },
                rebate: {
                	required: [true, "合同返点"],
                	number: true,
                	max :100,
                	min : 1
                }
            },
 
            messages: {
            	shopCode: {
                    required: "请输入{1}",
                    maxLength: "超长"
                },
                shopName: {
                	required: "请输入{1}",
                    maxLength: "超长"
                },
                address: {
                	required:"请输入{1}",
                    maxLength: "超长"
                },
                longitude: {
                	required:"请输入{1}",
                	number: "请输入数字"
                },
                latitude: {
                	required:"请输入{1}",
                	number: "请输入数字"
                },
                discountUpperLimit: {
                	required:"请输入{1}",
                	number: "请输入数字",
                	max : "不能大于100",
                	min : "不能小于1"
                },
                discountLowerLimit: {
                	required:"请输入{1}",
                	number: "请输入数字",
                	max : "不能大于100",
                	min : "不能小于1"
                },
                cooperateStatus: {
                	required:"请输入{1}"
                },
                cooperateStartDate: {
                	required:"请输入{1}"
                },
                cooperateEndDate: {
                	required:"请输入{1}"
                },
                level: {
                	required:"请输入{1}",
                	number: "请输入数字"
                },
                balanceType: {
                	required:"请输入{1}"
                },
                shopStatus: {
                	required:"请输入{1}"
                },
                tel: {
                	required:"请输入{1}"
                },
                introduce: {
                	required:"请输入{1}",
                	maxLength: "超长"
                },
                shopType: {
                	required: "请输入{1}",
                },
                url: {
                	required: "请上传{1}",
                },
                accountCode : {
                	required:"请输入{1}",
                	maxLength:"超长"
                },
                rebate: {
                	required:"请输入{1}",
                	number: "请输入数字",
                	max : "不能大于100",
                	min : "不能小于1"
                }
            },

            invalidHandler: function (event, validator) { //display error alert on form submit   
                
            },

            highlight: function (element) { // hightlight error inputs
                $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function (label) {
            	label.closest('.form-group').removeClass('has-error');
            	label.remove();
            },

            errorPlacement: function (error, element) {
                error.insertAfter(element.closest('.error-span'));
            },

            submitHandler: function (form) {
                //form.submit();
            }
        });

        $('#baseinfo-form input').keypress(function (e) {
            if (e.which == 13) {
                if ($('#baseinfo-form').validate().form()) {
                    $('#baseinfo-form [type=submit]').submit();
                }
                return false;
            }
        });
	};

	
	var handleShopPkgValidate = function(){
		$('#baseinfo-shoppkg-form').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules: {
            	//套餐简称
            	pkgName: {
                    required: [true, "套餐简称"],
                    maxLength: 50
                },
                //套餐类型
                pkgType: {
                	required: [true, "套餐类型"]
                },
                //套餐限制数
                pkgNum: {
                	required: [true, "套餐限制数"],
                	number: true
                },
                //套餐内容
                pkgContent: {
                	required: [true, "套餐内容"],
                	maxLength: 300
                },
                pkgPrice: {
                	required: [true, "套餐金额"],
                	number: true
                },
                pkgStartDate: {
                	required: [true, "套餐开始日期"]
                },
                pkgEndDate: {
                	required: [true, "套餐结束日期"]
                },
                description: {
                	required: [true, "套餐描述"],
                	maxLength: 200
                }
            },
 
            messages: {
            	//营销简称
            	pkgName: {
                    required: "请输入{1}"
                },
                //营销类型
                pkgType: {
                	required: "请输入{1}"
                },
                //套餐限制数
                pkgNum: {
                	required: "请输入{1}",
                	number: "请输入数字"
                },
                //营销内容
                pkgContent: {
                	required: "请输入{1}"
                },
                pkgPrice: {
                	required:"请输入{1}",
                	number: "请输入数字"
                },
                pkgStartDate: {
                	required: "请输入{1}",
                },
                pkgEndDate: {
                	required:"请输入{1}",
                },
                description: {
                	required:"请输入{1}",
                	maxLength:"超长"
                }
            },

            invalidHandler: function (event, validator) { //display error alert on form submit   
                
            },

            highlight: function (element) { // hightlight error inputs
                $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function (label) {
            	label.closest('.form-group').removeClass('has-error');
            	label.remove();
            },

            errorPlacement: function (error, element) {
                error.insertAfter(element.closest('.error-span'));
            },

            submitHandler: function (form) {
                //form.submit();
            }
        });
		 $('#baseinfo-shoppkg-form input').keypress(function (e) {
            if (e.which == 13) {
                if ($('#baseinfo-form').validate().form()) {
                    $('#baseinfo-form [type=submit]').submit();
                }
                return false;
            }
        });
	};
	
	var handleShopCarValidate = function(){
		$('#baseinfo-shopcar-form').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules: {
            	brand: {
                    required: [true, "车辆品牌"]
                }
            },
 
            messages: {
            	brand: {
                    required: "请输入{1}",
                }
            },

            invalidHandler: function (event, validator) { //display error alert on form submit   
                
            },

            highlight: function (element) { // hightlight error inputs
                $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function (label) {
            	label.closest('.form-group').removeClass('has-error');
            	label.remove();
            },

            errorPlacement: function (error, element) {
                error.insertAfter(element.closest('.error-span'));
            },

            submitHandler: function (form) {
                //form.submit();
            }
        });
		 $('#baseinfo-shopcar-form input').keypress(function (e) {
            if (e.which == 13) {
                if ($('#baseinfo-form').validate().form()) {
                    $('#baseinfo-form [type=submit]').submit();
                }
                return false;
            }
        });
	};
	
    return {
        //main function to initiate the module
        init: function () {
        	handleShopValidate();
        	handleShopPkgValidate();
        	handleShopCarValidate();
        }
    };
 

});
