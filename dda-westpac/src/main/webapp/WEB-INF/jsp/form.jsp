<html lang="en">
<head>
<meta charset="UTF-8">
<title>Example - example-example98-production</title>
<script
	src="//ajax.googleapis.com/ajax/libs/angularjs/1.3.0-beta.19/angular.min.js"></script>
<style type="text/css">
.css-form input.ng-invalid.ng-dirty {
	background-color: #FA787E;
}

.css-form input.ng-valid.ng-dirty {
	background-color: #78FA89;
}
</style>
</head>
<body ng-app="formExample">
	<div ng-controller="ExampleController">
		<form novalidate name="formx" class="css-form">
			<table>
				<tr>
					<td align='right'>Name</td>
					<td align='left'><input type='text' name='name'
						ng-model='user.userName' required value=''></td>
				</tr>
				<tr>
					<td align='right'>Email</td>
					<td align='left'><input type='email' name='email'
						ng-model='user.emailId' value=''></td>
				</tr>
			
			</table>
			<button ng-click="reset()">RESET</button>
			<button ng-click="update(user)">SAVE</button>
		</form>
	</div>
	<script>
	
	  
	
	      
	    
	
		angular.module('formExample', []).controller('ExampleController',
				[ '$scope','$http', function($scope, $http) {
					$scope.master = {};
					$scope.update = function(user) {
						alert(user);	
						var data = JSON.stringify(user);
						alert(data);
						$http.post('http://localhost:8080/westpac-dda/formSubmit',data).
			                success(function(data) {
			                    //$scope.greeting = data;
			                    alert("its a success");
			                }).
			                error(function(data) {
			                	alert("its a error");
			                    // called asynchronously if an error occurs
			                    // or server returns response with an error status.
			                  });
					};
					$scope.reset = function() {
						$scope.user = angular.copy($scope.master);
					};
					$scope.reset();
				} ]);
	</script>
</body>
</html>