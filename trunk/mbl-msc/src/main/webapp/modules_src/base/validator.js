'use strict';
/**
 * 有关校验规则制定和用法
 * 
 * 页面上需校验的input名称必须与form.js中校验对象一致
 * 校验内容名称必须与校验规则制定的名称一致
 * 具体校验规则说明如下：
 * 
 * 身份证号码验证 ：idcard
 * 
 * 中文输入验证 ： chinese
 * 
 * 英文输入验证 ： english
 * 
 * 传真号码输入验证 ： faxno
 * 
 * QQ号码输入验证 ：qq
 * 
 * 手机号码输入验证 ：mobile
 * 
 * 固话或手机号码输入验证 ：phoneOrMobile
 * 
 * 邮箱输入验证 ：emailer
 * 
 * 固话输入验证 ：phone
 * 
 * 整数输入验证 ：integer
 * 
 * 非法字符验证 ：unnormal
 * 
 * 邮政编码输入验证 ：zip
 * 
 * IP输入验证 ：ip
 * 
 * 24小时格式输入验证 ：timeTwentyFour
 * 
 * 小数或整数输入验证 ：floatOrInt
 * 
 * 如有需要请自行进行扩展方法
 * 
 * 安哥拉JS内部定义验证如下：
 * 
 * 必填验证 ：required
 * 
 * 最小长度验证 ：minlength ： 3(最少输入3个字符)
 * 
 * 最大长度验证 ：maxlength ： 4(最多输入4个字符)
 * 
 * 输入范围验证 ：rangelength : [2, 6] (输入2-6个字符)
 * 
 * 更多请参考http://jqueryvalidation.org/documentation/
 */

define(['jqvalidate','jquery'], function () {
	
	jQuery.validator.addMethod("idcard", function(value, element) {
		return this.optional(element) || /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value);
	},"请按照格式输入身份证");
	
	jQuery.validator.addMethod("chinese", function(value, element) {
		return this.optional(element) || /^[\u0391-\uFFE5]+$/i.test(value);
	},"请输入中文");
	
	jQuery.validator.addMethod("english", function(value, element) {
		return this.optional(element) || /^[A-Za-z]+$/i.test(value);
	},"请输入英文");
	
	jQuery.validator.addMethod("faxno", function(value, element) {
		return this.optional(element) || /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
	},"请输入正确的传真号码");
	
	jQuery.validator.addMethod("qq", function(value, element) {
		return this.optional(element) || /^[1-9]\d{4,9}$/i.test(value);
	},"请输入正确的QQ号码");
	
	jQuery.validator.addMethod("mobile", function(value, element) {
		return this.optional(element) || /^(13|15|18)\d{9}$/i.test(value);
	},"请输入正确的手机号码");
	
	jQuery.validator.addMethod("phoneOrMobile", function(value, element) {
		return this.optional(element) || /^(13|15|18)\d{9}$/i.test(value) || /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
	},"请输入正确的电话号码或者手机号码");
	
	jQuery.validator.addMethod("emailer", function(value, element) {
		return this.optional(element) || /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
	},"请输入正确的邮箱");
	
	jQuery.validator.addMethod("phone", function(value, element) {
		return this.optional(element) || /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
	},"格式不正确:020-88888888");
	
	jQuery.validator.addMethod("integer", function(value, element) {
		return this.optional(element) || /^[+]?[1-9]+\d*$/i.test(value);
	},"请输入整数");
	
	jQuery.validator.addMethod("unnormal", function(value, element) {
		return this.optional(element) || /.+/i.test(value);
	},"输入值不能为空和包含其他非法字符");
	
	jQuery.validator.addMethod("zip", function(value, element) {
		return this.optional(element) || /^[1-9]\d{5}$/i.test(value);
	},"邮政编码格式不正确");
	
	jQuery.validator.addMethod("ip", function(value, element) {
		return this.optional(element) || /d+.d+.d+.d+/i.test(value);
	},"IP地址格式不正确");
	
	jQuery.validator.addMethod("timeTwentyFour", function(value, element) {
		return this.optional(element) || /^([0-1][0-9]|2?[0-3]):([0-5][0-9]):([0-5][0-9])$/.test(value);
	},"小时:分钟:秒(08:30:00)");
	
	jQuery.validator.addMethod("floatOrInt", function(value, element) {
		return this.optional(element) || /^(\d{1,3}(,\d\d\d)*(\.\d{1,3}(,\d\d\d)*)?|\d+(\.\d+))?$/i.test(value);
	},"请输入数字，并保证格式正确");
	
	jQuery.validator.addMethod("floatOrIntWithPcs", function(value, element, param) {
		var reg = new RegExp("^((\\d){1," + param[0] + "}(\\.(\\d){1," + param[1] + "})?)?$", "i");
		return this.optional(element) || reg.test(value);
	}, 
    $.validator.format("请输入数字(整数{0}位，小数{1}位)"));
	
    //验证控件中输入内容的字节数是否超过指定的最大长度
	jQuery.validator.addMethod("maxByteLength", function(value, element, param) {
  	  var length = value.length;
  	  for(var i = 0; i < value.length; i++){
  		  if(value.charCodeAt(i) > 127){
  			  length += 2;
  	      }
  	  }
  	  return this.optional(element) || (length <= param);
    }, 
    $.validator.format("输入不能超过{0}个字节"));
	
	jQuery.validator.addMethod("maxLength", function(value, element, param) {
  	  	var length = value.length;
  	  	return this.optional(element) || (length <= param);
	},
    $.validator.format("输入不能超过{0}个字"));
	
	jQuery.validator.addMethod("compareGeDate", function(value, element, param) {
		var startDate = jQuery(param).val();
		if(startDate&&value){
			var date1 = new Date(Date.parse(startDate.replace(/-/g, "/")));
	        var date2 = new Date(Date.parse(value.replace(/-/g, "/")));
	        return date1 <= date2;
		}
		return true;
	},"");
});
