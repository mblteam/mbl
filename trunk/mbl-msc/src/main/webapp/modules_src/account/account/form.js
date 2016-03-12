'use strict';

define(['jqvalidate','jquery','base/validator'], function () {
	
	jQuery.validator.addMethod("sameOriginalPsw", function(value, element, param) {
		var originalPsw = jQuery(param).val();
		if(originalPsw&&value){
			if(originalPsw==value){
				return false;
			}
		}
		return true;
	},"新密码和原密码不能一致");
	
	jQuery.validator.addMethod("sureNewPsw", function(value, element, param) {
		var newPsw = jQuery(param).val();
		if(newPsw&&value){
			if(newPsw!=value){
				return false;
			}
		}
		return true;
	},"输入密码和新密码不一致");

	var handleCreateMenu = function(){
		$('#baseinfo-form').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules: {
            	oldAccountPsw: {
                    required: [true, "原密码"],
                    rangelength:[6,14]
                },
                newAccountPsw: {
                	required: [true, "新密码"],
                	rangelength:[6,14],
                	sameOriginalPsw: "#oldAccountPsw"
                },
                sureAccountPsw: {
                	required: [true, "确认密码"],
                	rangelength:[6,14],
                	sureNewPsw: "#newAccountPsw"
                }
            },
 
            messages: {
            	oldAccountPsw: {
                    required: "请输入{1}",
                    rangelength: "长度在{0}~{1}之间"
                },
                newAccountPsw: {
                	required: "请输入{1}",
                	rangelength: "长度在{0}~{1}之间"
                },
                sureAccountPsw: {
                	required:"请输入{1}",
                	rangelength: "长度在{0}~{1}之间"
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
        	handleCreateMenu();  
        }

    };
 

});
