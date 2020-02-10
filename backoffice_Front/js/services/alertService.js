angular.module("escola").factory("alertService", function ($timeout){

    var _showAlertId = function ($scope, message) {         
        $scope.messageAlert = message;  
        var alert = document.getElementById("alertId");        
        alert.style.display = "block";
        $timeout(function(){hideAlertId($scope);}, 4000);        
       

    }
    var hideAlertId = function ($scope) {
        var alert = document.getElementById("alertId");
        alert.style.display = "none";
        $scope.messageAlert = "";
    }

    var _showAlertSuccess = function ($scope, message) {        
        $scope.messageSuccess = message;
        var alert = document.getElementById("alertSuccess");
        alert.style.display = "block";
        $timeout(function(){hideAlertSuccess($scope);}, 4000);        
        

    }
    var hideAlertSuccess = function ($scope) {
        var alert = document.getElementById("alertSuccess");
        alert.style.display = "none";
        $scope.messageSuccess = "";
    }


    return{
        showAlertId: _showAlertId,
        showAlertSuccess: _showAlertSuccess
    };
});