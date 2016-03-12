'use strict';

define(['jqvalidate', 'base/validator'], function () {
 
	var handleCouponValidate = function(){
		$('#baseinfo-form').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules: {
            	couponName: {
                    required: [true, "优惠券名称"],
                    maxLength: 50
                },
                couponType: {
                	required: [true, "优惠券类型"]
                },
                couponStartDate: {
                	required: [true, "开始日期"]
                },
                couponEndDate: {
                	required: [true, "结束日期"]
                },
                effectiveDay: {
                	required: [true, "有效天数"],
                	number: true
                },
                couponWorth: {
                	required: [true, "价值"],
                	number: true
                },
                description: {
                	maxLength: 100
                },
                couponStatus: {
                	required: [true, "状态"]
                }
            },
 
            messages: {
            	couponName: {
                    required: "请输入{1}",
                    maxLength: "超长"
                },
                couponType: {
                	required: "请输入{1}"
                },
                couponStartDate: {
                	required: "请输入{1}"
                },
                couponEndDate: {
                	required: "请输入{1}"
                },
                effectiveDay: {
                	required: "请输入{1}",
                	number: "请输入数字"
                },
                couponWorth: {
                	required: "请输入{1}",
                	number: "请输入数字"
                },
                description: {
                	maxLength: "超长"
                },
                couponSatus: {
                	required: "请选择{1}"
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

    return {
        //main function to initiate the module
        init: function () {
        	handleCouponValidate();
        }
    };
 

});
