<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>AngularJS  Example</title>  
    <style>
      .username.ng-valid {
          background-color: lightgreen;
      }
      .username.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .username.ng-dirty.ng-invalid-minlength {
          background-color: yellow;
      }
      .email.ng-valid {
          background-color: lightgreen;
      }
      .email.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .email.ng-dirty.ng-invalid-email {
          background-color: yellow;
      }
    </style>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <link href="<c:url value='css/app.css' />" rel="stylesheet"></link>
  </head>
  <body ng-app="crudApp" class="ng-cloak">
      <div class="generic-container" ng-controller="CardController as ctrl">
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">Lista de tarjetas </span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>PAN</th>
                              <th>Tipo de tarjeta</th>
                              <th>Número de validación</th>
                              <th>Estado de la tarjeta</th>
                              <th>Titular</th>
                              <th>Cedula</th>
                              <th>Telefono</th>
                              <th width="20%"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="u in ctrl.cards">
                              <td><span ng-bind="u.pan"></span></td>
                              <td><span ng-bind="u.cardType"></span></td>
                              <td><span ng-bind="u.validationNumber"></span></td>
                              <td><span ng-bind="u.state"></span></td>
                              <td><span ng-bind="u.personDTO.fullName"></span></td>
                              <td><span ng-bind="u.personDTO.identification"></span></td>
                              <td><span ng-bind="u.personDTO.phoneNumber"></span></td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
      
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="<c:url value='js/app.js' />"></script>
      <script src="<c:url value='js/service/card_service.js' />"></script>
      <script src="<c:url value='js/controller/card_controller.js' />"></script>
  </body>
</html>