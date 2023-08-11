'use strict';
var app = angular.module('crudApp',["ngRoute"]);
 
app.config(function ($routeProvider) {
  $routeProvider
    .when("/CardManagment", {
      templateUrl: "/CardManagment.html",
      controller: "card_controller",
    })
    .when("/TransactionManagment", {
      templateUrl: "/TransactionManagment.html",
      controller: "transaction_controller",
    })
    .otherwise({
      redirectTo: "/",
    });
});