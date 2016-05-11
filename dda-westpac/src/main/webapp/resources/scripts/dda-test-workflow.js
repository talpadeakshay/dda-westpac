'use strict';

var app = angular.module('dda.test-workflow', [])

.controller("testWorkflowCtrl", function($scope,$http,$location) {
	alert($location.absUrl().substr(0, $location.absUrl().lastIndexOf("#")));
	$scope.data = {
		roleOptions : [ {
			id : 'ROLE_USER',
			name : 'ROLE_USER'
		}, {
			id : 'ROLE_APPROVER',
			name : 'ROLE_APPROVER'
		} ],
		selectedRole : {
			id : 'ROLE_USER',
			name : 'ROLE_USER'
		}
	// This sets the default value of the select in the ui
	};

	$scope.loadMessages = function() {
		alert('in ' + $scope.data.selectedRole);
	}
	
	$scope.startWorkflow = function() {
		var startWorkflowUrl = 'http://localhost:8080/westpac-dda/startTestWorkflow';
		alert('workflow ' + $scope.data.selectedRole);
		var selections = {
	            "selectedRole": $scope.data.selectedRole
	        };
		var data = JSON.stringify(selections);
		alert(data);
		$http.post(startWorkflowUrl,data).
            success(function(data) {
                // $scope.greeting = data;
                alert("its a success");
            }).
            error(function(data) {
            	alert("its a error");
                // called asynchronously if an error occurs
                // or server returns response with an error status.
              });
	}
	
});