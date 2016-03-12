'use strict';

define(['jqvalidate', 'base/validator'], function () {
 
	var handleUserValidate = function(){
		$('#baseinfo-form').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules: {
            	//姓名
            	userName: {
                    required: [true, "姓名"],
                    maxLength: 20
                },
                //账号
                accountCode: {
                	required: [true, "账号"],
                	maxLength: 30
                },
                //性别
                sex: {
                	required: [true, "性别"]
                },
                //电话
                tel: {
                	mobile: true
                },
                //备用电话
                tel2: {
                	phoneOrMobile: true
                },
                //地址
                address: {
                	required: [true, "地址"],
                	maxLength: 166
                }
            },
 
            messages: {
                //姓名
            	userName: {
                    required: "请输入{1}"
                },
                //账号
                accountCode: {
                	required: "请输入{1}",
                	maxLength: "长度不能超过{0}个字"
                },
                //性别
                sex: {
                	required: "请输入{1}"
                },
                address: {
                	required: "请输入{1}",
                	maxLength: "长度不能超过{0}个字"
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
        	handleUserValidate();   
        }
    };
 

});
