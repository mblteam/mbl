'use strict';

define(['jqvalidate', 'base/validator'], function () {
 
	var handleSettleValidate = function(){
		$('#baseinfo-settle-form').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules: {
            	orderTimeBegin: {
                    required: [true, "订单开始日期"]
                },
                orderTimeEnd: {
                	required: [true, "订单结束日期"]
                }
            },
 
            messages: {
            	orderTimeBegin: {
            		required: "请输入{1}"
                },
                orderTimeEnd: {
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

        $('#baseinfo-settle-form input').keypress(function (e) {
            if (e.which == 13) {
                if ($('#baseinfo-settle-form').validate().form()) {
                    $('#baseinfo-settle-form [type=submit]').submit();
                }
                return false;
            }
        });
	};

    return {
        //main function to initiate the module
        init: function () {
        	handleSettleValidate();
        }
    };
 

});
