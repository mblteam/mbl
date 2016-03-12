'use strict';

define(['jqvalidate', 'base/validator'], function () {
 
	var handlePkgValidate = function(){
		$('#baseinfo-form').validate({
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
                //备注
                remark: {
                    maxLength: 300
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
                	number: "请输入数字"
                },
                //营销内容
                pkgContent: {
                	required: "请输入{1}"
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
        	handlePkgValidate();   
        }
    };
 

});
