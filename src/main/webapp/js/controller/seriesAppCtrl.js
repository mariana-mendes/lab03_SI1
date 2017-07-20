angular.module("seriesApp").controller("seriesAppCtrl", function ($scope,seriesAPI, $http, $mdDialog,$state) {
	$scope.app = "Banco de Series"
	$scope.watchlist = [];
	$scope.arrayExibido = [];
	$scope.minhasSeries = [];
	$scope.idSerie ="";
	$scope.mostraBusca = true;
  $scope.estaNaWatchlist = true;

  var removeSerie = function(array, serie){
    var indice = array.indexOf(serie);
    if(indice > -1){
       array.splice(indice, 1);};
   };


  var getImdbID = function(array,serie){
    var index = array.indexOf(serie);
    return array[index].imdbID;
  } 
  

  var watchlistToPerfil = function(serie){
    var url = "/watchlistToPerfil/" + $scope.usuarioLogado.id; 
    var id =   getImdbID($scope.watchlist, serie);
 
    $http.put(url, id).then(function(response){
    },function(response){});
  };



 $scope.watchlistParaPerfil = function(ev, serie) {
    var confirm = $mdDialog.confirm()
    .textContent("Tem certeza que quer remover "+ serie.Title + " da sua Watchlist e adicionar ao perfil?")
    .targetEvent(ev)
    .ok('Sim, por favor')
    .cancel('Não!');  
    $mdDialog.show(confirm).then(function() {
      $scope.minhasSeries.push(serie);  
      watchlistToPerfil(serie);
      removeSerie($scope.watchlist, serie);
      $scope.status = true;
    },function() {
      $scope.status = false;
    });
  };


 var removeSerieBd = function(serie,id){
    var url = "/" + $scope.usuarioLogado.id +"/removeSerie"
    $http.put(url, id).then(function(response){
        console.log("removido :)");  
    }, function(response){
    });
  };

  $scope.confirmRemoveWatchlist = function(ev, serie) {
      var confirm = $mdDialog.confirm()
          .title("Espere um pouco!")
          .textContent("Tem certeza que quer remover "+ serie.Title + " da sua Watchlist?")
          .targetEvent(ev)
          .ok('Sim, por favor')
          .cancel('Não!');

    $mdDialog.show(confirm).then(function() {
       removeSerieBd(serie,getImdbID($scope.watchlist,serie));
      removeSerie($scope.watchlist, serie);
     
    },function() {});
  };



  $scope.removeMinhasSeries = function(serie){
    var indice = $scope.minhasSeries.indexOf(serie);
    if(indice > -1){
      $scope.minhasSeries.splice(indice, 1);
    };
  };

  $scope.confirmRemoveMinhasSeries = function(ev, serie) {
        var confirm = $mdDialog.confirm()
          .textContent('Você gostaria mesmo de remover ' + serie.Title +' do seu perfil?')
          .targetEvent(ev)
          .ok('Sim, por favor')
          .cancel('Não!');
      $mdDialog.show(confirm).then(function() {
      removeSerieBd(serie, getImdbID($scope.minhasSeries, serie));
      $scope.removeMinhasSeries(serie);
      },function(){});
  };


 var carregaSeriesUsuario = function(){
  $scope.minhasSeries = [];
  $scope.watchlist = []; 
  var url = '/getSeries/' + $scope.usuarioLogado.id
      $http.get(url).then(function(response){

      var seriesEncontradas = response.data;


        for (var i = seriesEncontradas.length - 1; i >= 0; i--){
            if(seriesEncontradas[i].watchlist){
                $scope.buscaInfoSerie(seriesEncontradas[i].idIMDB).then(function(responseAPI){
                $scope.watchlist.push(responseAPI.data);
              });
            }else{
               $scope.buscaInfoSerie(seriesEncontradas[i].idIMDB).then(function(responseAPI){
                $scope.minhasSeries.push(responseAPI.data);
              });

            }
        }
    });
     
  };


  $scope.buscaInfoSerie = function(key){
    return seriesAPI.getSeriesPlot("?i="+key+ "&apikey=93330d3c").then(function(response){
          $scope.idSerie = response.data; 
          return response  
      }).catch(function(data, status){
      alert("Algo deu errado");
    });
 
  };



  $scope.carregaSeries = function (nome,ev){
    seriesAPI.getSeries("?s=" + nome+"&apikey=93330d3c&type=series").then(function(data, status){
      checaAPI(data.data,ev);
    }).catch(function(data, status){ 
        alert("Algo deu errado");
    });
  };

  $scope.notFoundSerieAlert = function(ev) {
      $mdDialog.show(
      $mdDialog.alert()
        .parent(angular.ele1ment(document.querySelector('#popupContainer')))
        .clickOutsideToClose(true)
        .title('')
        .textContent('Nenhum resultado')
        .ariaLabel('')
        .ok('Ok!')
        .targetEvent(ev)
      );
      $scope.escondeBusca();
  };

  $scope.addMinhasSeries = function(ev, serie){
    if(checaSerieRepetida(serie, $scope.minhasSeries)){
        alert("Essa série já está no seu perfil!");
    }else if(checaSerieRepetida(serie, $scope.watchlist)){
         $scope.watchlistParaPerfil(ev, serie);
    }else{
         $scope.minhasSeries.push(serie);
         $scope.showDialogConfirm(ev, serie );
         $scope.estaNaWatchlist = false;
         addSerieBd(serie);
    };
  };


  var addSerieBd = function(serie){
    var serieBd = {
          idIMDB: serie.imdbID,
          description: " ",
          idUsuario: $scope.usuarioLogado.id,
          watchlist: $scope.estaNaWatchlist,
          nota: null,
          episodio: null,
      }
    var url =  "/addSerie";
    $http.post(url, serieBd).then(function (response) {
     }, function (response) {
    });
  };


  $scope.addWatchlist = function(ev, serie){
    if(checaSerieRepetida(serie, $scope.minhasSeries)){
       alert('Essa série ja está no seu perfil!');
    }else if(!checaSerieRepetida(serie, $scope.watchlist)){
      $scope.estaNaWatchlist = true;
      $scope.watchlist.push(serie);
      addSerieBd(serie);
      $scope.showDialogConfirm(ev, serie);
    }else{
      alert("Essa série já está na sua watchlist!");
    }
  };

  $scope.getSinopse = function(){
    return $scope.idSerie;
  };

  $scope.showDialogConfirm = function(ev, serie ) {
      $mdDialog.show(
      $mdDialog.alert()
        .parent(angular.element(document.querySelector('#popupContainer')))
        .clickOutsideToClose(true)
        .textContent(serie.Title + ' foi adicionada com sucesso')
        .ok('Ok!')
        .targetEvent(ev));
  };
 

	$scope.escondeBusca = function(){
		$scope.mostraBusca = false;
	};

 

	var checaAPI = function(resultado,ev){
		if(resultado.Response == "False"){
			$scope.notFoundSerieAlert(ev);
		}else{
			$scope.mostraBusca = true;
			$scope.arrayExibido = resultado.Search;
    }
  };
  
var checaSerieRepetida = function(idSerie, array){
    for (var i = array.length - 1; i >= 0; i--) {
          if(array[i].imdbID == idSerie.imdbID){
            return true;
          }
    }

    return false;
    };

 
  $scope.submitForm = function(loginCadastro, senhaCadastro){
    var url = "/postUser";
    var data = {
            login: loginCadastro,
            senha: senhaCadastro
        };

    $http.post(url, data).then(function (response) {
        $scope.postResultMessage = "Sucessful!";
        }, function (response) {
      $scope.postResultMessage = "Fail!";
    });
    
  };

  $scope.logar = function(login,senha){
  
    var url =  "/loginUsuario";
    var data ={
          login: login,
          senha: senha
        };

    $http.post(url, data).then(function (response) {
      $state.go('main.home');
      $scope.usuarioLogado = response.data;
      carregaSeriesUsuario();
     
    }, function (response) {
      $scope.result = "Dados inválidos!";
    });

  };



  (function(){
    if(!$scope.usuarioLogado){
        $state.go('main.login');
      }
  })();


  $scope.logout = function(){
     $state.go('main.login');
    }

});
