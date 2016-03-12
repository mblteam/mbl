'use strict';

define([

	'angular', 
	'controllers',
	'directives',
	'filters',
	'router',
	'services'
 
	], function (angular) { 
	/* App Module */
	var kmpmApp = angular.module('kmpmApp', [
		'ngRoute',
		'kmpmAppDirectives',
		'kmpmAppControllers',
		'kmpmAppFilters',
		'kmpmAppServices',
		'kmpmAppUIRouter'
	]); 

	return kmpmApp;

});

 
