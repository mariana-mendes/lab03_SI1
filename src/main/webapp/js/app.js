angular.module('tabsDemoDynamicHeight', ['ngMaterial']);


const app = angular.module("seriesApp",['ui.router','ngMaterial']);

app.config(function($stateProvider, $urlRouterProvider){
	$urlRouterProvider.otherwise('/home');
	$stateProvider
		.state('main',{
			url: '',
			abstract: true,
			template: '<div ui-view></div>',
			controller: 'seriesAppCtrl'

		
		})
		.state('main.home', {
			url:'/home',
			templateUrl:'home.html',
		})

		.state('main.login', {
			url:'/login',
			templateUrl:'login.html',
		});

});