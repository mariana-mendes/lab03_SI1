var app = angular.module('app', []);
app.controller('postcontroller', function($scope, $http, $location) {
	$scope.submitForm = function(){
		var serv = "http://localhost:8080/"
		
		var url = serv + "postUser";
		console.log(url);
		var config = {
                headers : {
                    'Content-Type': 'application/json;charset=utf-8;'
                }
        }
		var data = {
            login: $scope.login,
            senha: $scope.senha
        };
		
		
		$http.post(url, data, config).then(function (response) {

			$scope.postResultMessage = "Sucessful!";
		}, function (response) {
			$scope.postResultMessage = "Fail!";
		});
		
		$scope.login = "";
		$scope.senha = "";
	}
});
 
app.controller('getcontroller', function($scope, $http, $location) {
	$scope.getfunction = function(){
		var serv = "http://localhost:8080/"
		var url = serv + "getUsers";
		
		var config = {
                headers : {
                    'Content-Type': 'application/json;charset=utf-8;'
                }
        }
		
		$http.get(url, config).then(function (response) {
			$scope.response = response.data
		}, function (response) {
			$scope.getResultMessage = "Fail!";
		});
	}
});