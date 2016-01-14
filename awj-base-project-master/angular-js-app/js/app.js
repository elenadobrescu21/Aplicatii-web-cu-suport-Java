
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
        /* code goes here */
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
        /* code goes here */
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
        /* code goes here */
		console.log("Am sters produsul");
      },
      function errorCallback(response) {
        angular.element('[data-id=' + id + ']').remove();
    });
  };
  
  
     $scope.addMagazin = function(magazin) {
    $scope.persoane.data.push(magazin);
	var url ="http://localhost:8080/magazin";
	$http.post(url+"?"+"name="+magazin.name+"&address="+magazin.address);
 
    $scope.magazin = {};
  };
  
  
  
  
}]);
