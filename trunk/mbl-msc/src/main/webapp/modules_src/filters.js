'use strict';

define(['angular'], function (angular) {

	  
/* Filters */
var kmpmAppFilters = angular.module('kmpmAppFilters', []);

	// 物价管理查询过滤器
	kmpmAppFilters.filter('myfile',function() {
		return function(input) {
			
	    	var args = Array.prototype.slice.call(arguments);
	    	var obj1 = [];
	    	if(args[1] !== undefined){ // 如果第一个条件不空
	    		angular.forEach(input,function(v,k){
	    			if (v.id.contains(args[1])){
	    				obj1.push(v);
	    			}
	    		});

	    		if(args[2] !== undefined){ // 如果第二个条件也不为空
	    			var obj2 = [];
					angular.forEach(obj1,function(v,k){
		    			if (v.name.contains(args[2])){
		    				obj2.push(v);
		    			}
		    		});

		    		if(args[3] !== undefined){ // 如果第三个条件也不为空
		    			var obj3 = [];
						angular.forEach(obj2,function(v,k){
			    			if (v.state.contains(args[3])){
			    				obj3.push(v);
			    			}
			    		});
	    				return obj3;
	    			}
	    			return obj2;
	    		}

	    		if(args[3] !== undefined){ // 如果第三个条件也不为空
	    			var obj4 = [];
					angular.forEach(obj1,function(v,k){
		    			if (v.state.contains(args[3])){
		    				obj4.push(v);
		    			}
		    		});
	    			return obj4;
	    		}
	    		return obj1;
	    	} else if (args[2] !== undefined){
	    		angular.forEach(input,function(v,k){
	    			if (v.name.contains(args[2])){
	    				obj1.push(v);
	    			}
	    		});

	    		if(args[3] !== undefined){ // 如果第三个条件也不为空
	    			var obj5 = [];
					angular.forEach(obj1,function(v,k){
		    			if (v.state.contains(args[3])){
		    				obj5.push(v);
		    			}
		    		});
		    		return obj5;
	    		}
	    		return obj1;
	    	} else if (args[3] !== undefined) {
	    		angular.forEach(input,function(v,k){
	    			if (v.state.contains(args[3])){
	    				obj1.push(v);
	    			}
	    		});
	    		return obj1;
	    	}
	        return input;
	  	};
	});


	kmpmAppFilters.filter('checkmark', function() {
	  return function(input) {
	    return input ? '\u2713' : '\u2718';
	  };
	});
	
	kmpmAppFilters.filter('rangTime', function() {
	  return function(input) {
	  	if(angular.isArray(input) && input.length >= 2){
	  		var s = input[0];
	  		var e = input[1];
	  		if(s < 10){
	  			s = '0' + s;
	  		}
	  		if(e < 10){
	  			e = '0' + e;
	  		}
	  		return s + ':00-' + e + ':00';
	  	}
	    return '';
	  };
	});

	kmpmAppFilters.filter('rangWeek', function() {
	  return function(input) { 
	  	if(angular.isArray(input) && input.length >= 7){
	  		var week = "";
	  		input[0] ? week += '周一,' : '';
	  		input[1] ? week += '周二,' : '';
	  		input[2] ? week += '周三,' : '';
	  		input[3] ? week += '周四,' : '';
	  		input[4] ? week += '周五,' : '';
	  		input[5] ? week += '周六,' : '';
	  		input[6] ? week += '周日,' : '';
	  		return week;
	  	}
	    return '';
	  };
	});

	kmpmAppFilters.filter('attrSum', function() {
		  return function(input,args) {
		    if(input === undefined){
		      return "";
		    }
		    if(angular.isArray(input)){
		      var sum = 0;
		      for(var ind in input){
		        var obj = input[ind];
		        var val = obj[args];
		        if(angular.isNumber(val)){
		          sum += val;
		        }
		      }
		      return sum;
		    }
		    return 0;
		  };
		});
	kmpmAppFilters.filter('treeJSON', function() {
		  return function(input,args) {
		    if(input === undefined){
		      return {};
		    }
		    var tree = {};
		    if(angular.isArray(input)){
			  for(var ind in input){
		      	var obj = input[ind];
		      	var temp = tree;
		      	for(var ar in args){
		      		if(temp[obj[args[ar]]] === undefined){
			     		temp[obj[args[ar]]]={};
			     		if(parseInt(ar)+1 >= args.length){
			     			temp[obj[args[ar]]]=[];
			     		}
			     	}
			     	temp = temp[obj[args[ar]]];
		      	}
		      	temp.push(obj);
		      }
		      return tree;
		    }
		    return {};
		  };
		});
	kmpmAppFilters.filter('jsonSize', function() {
		  return function(input,args) {
		    if(input === undefined){
		      return 0;
		    } 
		    var size = 0 ;
		    for(var i in input){
		    	++size;
		    }
		    return size;
		  };
		});



	kmpmAppFilters.filter('searchFilter', function() {
		  return function(input,args) {
		  	if(input === undefined){
		  		return;
		  	}
		  	var arg = args;
		  	var reg = {};
		  	var size = 0;
		  	if(angular.isArray(args)){
		  		//有优化
		  		arg = args[0];
		  		reg = new RegExp(arg, "i");
		  		size = 0;
			 	for(var obj in input){
			 		if(input[obj] === undefined){
			 			continue;
			 		}
					if(reg.test(obj) || reg.test(angular.toJson(input[obj]))){
						input[obj][args[1]] = true;
						++size;
					}else{
						input[obj][args[1]] = false;
					}
			 	}
			 	input.size = size;
			    return input;
		  	}else{
		  		//无优化
		  		reg = new RegExp(arg, "i");
			  	var newObj = {};
			  	size = 0;
			 	for(var obj1 in input){
			 		if(input[obj1] === undefined){
			 			continue;
			 		}
					if(reg.test(obj1) || reg.test(angular.toJson(input[obj1]))){
						newObj[obj1] = input[obj1];
						++size;
					}
			 	}
			 	newObj.size = size;
			 	input.size = size;
			    return newObj;
		  	}
		  };
		});

	kmpmAppFilters.filter('boolFormat', function() { //0-否,1是  boolFormat:['是','否']
		  return function(input,args) {
		    if(args === undefined || args[0] === undefined || args[1] === undefined){
		      return "";
		    }
		    if(input == 0 || input === false || input === 'false'){
		      return args[1];
		    }else if(input == 1 || input === true || input === 'true'){
		      return args[0];
		    }else{
		      return "";
		    }
		  };
		});
 
	kmpmAppFilters.filter('mapFormat', function() {
		  return function(input,args) { 
		    if(args === undefined || args === undefined){
		      return "";
		    }
		    return args[input];
		  };
	});
	kmpmAppFilters.filter('valiNumber', function() {
		  return function(input,args) {
		    if(angular.isNumber(input) && input !== undefined && !isNaN(input)){
		      return input;
		    }
		    return 0;
		  };
		});
	
	kmpmAppFilters.filter('numberFormat', function() {
		  return function(input,args) {
		    if(input !== undefined && !isNaN(input)){
		    	return parseFloat(input).toFixed(args);
		    }
		    return null;
		  };
	});
	
	kmpmAppFilters.filter("transferCodeToName", function(){
		return function(input,list,code,name,codeValue){
			if(list != null && list.length > 0){
				for(var i = 0; i < list.length; i++){
					if(list[i][code] == input){
						return list[i][name];
					}
				}
			}else{
				return codeValue;
			}
		};
	});

	return kmpmAppFilters;
});

 
 

 
