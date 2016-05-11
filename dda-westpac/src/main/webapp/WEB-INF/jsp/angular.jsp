<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Spring MVC AngularJS demo</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js"></script>
<script>

    var app = angular.module('myApp', []);
    
    function MyController($scope, $http){
        
        $scope.getPersonDataFromServer = function() {           
            $http({method: 'GET', url: 'springAngularJS'}).
            success(function(data, status, headers, config) {
                $scope.person = data;
            }).
            error(function(data, status, headers, config) {
              // called asynchronously if an error occurs
              // or server returns response with an error status.
            });
        
        };
        
        $scope.getDataFromRestService = function Hello() {
            $http.get('http://localhost:8080/westpac-dda/greeting/Akshay').
                success(function(data) {
                    $scope.greeting = data;
                });
        };
    };
   
</script>
</head>
<body>
    <div data-ng-app="myApp">
        <div data-ng-controller="MyController">
            <button data-ng-click="getPersonDataFromServer()">Get Person data from server</button>
            <p>Name : {{person.userName}}</p>
            <p>Email : {{person.emailId}}</p>
        </div>
   
        <div data-ng-controller="MyController">
            <button data-ng-click="getDataFromRestService()">Get Data from Rest Service</button>
            <p>Id : {{greeting.id}}</p>
            <p>Content : {{greeting.content}}</p>
        </div>
    </div>
</body>
</html>