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
            login: $scope.loginCadastro,
            senha: $scope.senhaCadastro
        };
		
		
		$http.post(url, data, config).then(function (response) {

			$scope.postResultMessage = "Sucessful!";
		}, function (response) {
			$scope.postResultMessage = "Fail!";
		});
		
		$scope.loginCadastro = "";
		$scope.senhaCadastro = "";
	}
});
 


app.controller('logincontroller',function($scope, $http, $location){
	$scope.getfunction = function(){
		var serv = "http://localhost:8080/"
		var url = serv + "loginUsuario";
		
		var config = {
                headers : {
                    'Content-Type': 'application/json;charset=utf-8;'
                }
        }

        var data ={
        	login: $scope.login,
        	senha: $scope.senha
        };


		
		$http.post(url, data, config).then(function (response) {

			$scope.result = "Sucessful!";
		}, function (response) {
			$scope.result = "Dados inválidos!";
		});
		
		$scope.login = "";
		$scope.senha = "";
	}

});

