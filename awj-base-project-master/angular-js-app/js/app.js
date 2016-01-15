
var app = angular.module('blog', [ ]);

app.controller('HomeController', ['$scope', '$http', function($scope, $http) {
  $scope.helloWorld = 'Aplicatii Web cu suport Java!';

  $scope.persoane = [];
  $scope.magazine = [];
  $scope.produse = [];
  
  $scope.keys = [];

  $scope.person = {};
  $scope.magazin = {};
  $scope.produs = {};
  $scope.editPerson = {};
  $scope.editProdus = {};
  $scope.editMagazin = {};

	//get magazin
	$http.get('http://localhost:8080/magazin').then(
		function successCallback(response) {
		$scope.magazine = response;
		$scope.keys = Object.keys(response.data[0]);
    });
  
  //get persoana
    $http.get('http://localhost:8080/persoana').then(
		function successCallback(response) {
		$scope.persoane = response;
		$scope.keys = Object.keys(response.data[0]);
    });
  
  //get produs
	$http.get('http://localhost:8080/produs').then(
		function successCallback(response) {
		$scope.produse = response;
		$scope.keys = Object.keys(response.data[0]);
	});
  
  //delete persoana
   $scope.deletePersoana = function(id) {
    $http.delete('http://localhost:8080/persoana/' + id)
    .then(
      function successCallback(response) {
		console.log("Am sters");
      },
      function errorCallback(response) {
        angular.element('[data-id=' + id + ']').remove();
    });
  };
  
  //delete magazin
   $scope.deleteMagazin = function(id) {
    $http.delete('http://localhost:8080/magazin/' + id)
    .then(
      function successCallback(response) {
		console.log("Am sters magazinul");
      },
      function errorCallback(response) {
        angular.element('[data-id=' + id + ']').remove();
    });
  };
  
  //delete produs
    $scope.deleteProdus = function(id) {
    $http.delete('http://localhost:8080/produs/' + id)
    .then(
      function successCallback(response) {
		console.log("Am sters produsul");
      },
      function errorCallback(response) {
        angular.element('[data-id=' + id + ']').remove();
    });
  };
  
    //add magazin
    $scope.addMagazin = function(magazin) {
    $scope.magazine.data.push(magazin);
	var url ="http://localhost:8080/magazin";
	$http.post(url+"?"+"name="+magazin.name+"&address="+magazin.address);
 
    $scope.magazin = {};
  };
  
  //add produs
   $scope.addProdus = function(produs) {
	  $scope.produse.data.push(produs);
	var url ="http://localhost:8080/produs";
	$http.post(url+"?"+"name="+produs.name);
 
    $scope.produs = {};
    
    };
	
	// add persoana
	$scope.addPersoana = function(persoana) {
	$scope.persoane.data.push(persoana);
	console.log(persoana);
	var url ="http://localhost:8080/persoana";
	$http.post(url+"?"+"name="+persoana.name + '&' + 'oras='+persoana.address.oras + 'strada=' + persoana.address.strada + 'numar='+persoana.address.numar);

    $scope.persoana = {};
    
    };
	
	//update produs
  $scope.setUpdateProdus = function(produs) {
    $scope.editProdus = produs;
  };

  $scope.updateProdus = function() {
	event.preventDefault();
    $http.put('http://localhost:8080/produs', $scope.editProdus);
    $scope.editProdus = {};
  };
	
	//update magazin
	$scope.setUpdateMagazin = function(magazin) {
        $scope.editMagazin = magazin;
    };

    var url3 ="http://localhost:8080/magazin";
    $scope.updateMagazin= function() {
		event.preventDefault();
        $http({
            method: 'PUT',
            url: url3,
            data: $scope.editMagazin
        }).then(function successCallback(response) {
            $scope.editMagazin = {};
            console.log(response);
     
        }, function errorCallback(response) {
            $scope.editMagazin = {};
            console.log(response);
        });
    };
	
	//update persoana
	$scope.setUpdatePersoana = function(persoana) {
    $scope.editPerson = persoana;
	};

  $scope.updatePerson = function() {
	event.preventDefault();
    $http.put('http://localhost:8080/persoana', $scope.editPerson);
    $scope.editPerson = {};
  };
  
  //show produs
   $scope.showProdus = function(produs) {
    $scope.isOpen = true;
    $http.get('http://localhost:8080/produs/'.concat(produs.id)).then(
    function successCallback(response) {
      $scope.produsShow = response.data;
    });
    
  };
  //show magazin
    $scope.showMagazin = function(magazin) {
    $scope.isOpen = true;
    $http.get('http://localhost:8080/magazin/'.concat(magazin.id)).then(
    function successCallback(response) {
      $scope.magazinShow = response.data;
    });
    
  };
  
  //show persoana
  
    $scope.showPersoana = function(persoana) {
    $scope.isOpen = true;
    $http.get('http://localhost:8080/persoana/'.concat(persoana.id)).then(
    function successCallback(response) {
      $scope.persoanaShow = response.data;
    });
    
  };
  
  
}]);
