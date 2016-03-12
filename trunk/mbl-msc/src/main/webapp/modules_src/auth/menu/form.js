'use strict';

define(['jqvalidate','jquery','base/validator'], function () {

	var handleCreateMenu = function(){
		$('#createMenu-form').validate({
		 
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules: {
            	menuCode: {
            		required: true
            	},
                menuName: {
                    required: true
                },
                seq: {
                    required: true
                }
            },
 
            messages: {
            	menuCode: {
                    required: "请输入菜单代码."
                },
            	menuName: {
                    required: "请输入菜单名称."
                },
            	seq: {
            		required: "请输入菜单序号."
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

	};

    return {
        //main function to initiate the module
        init: function () {
        	handleCreateMenu();  
        }

    };
 

});
