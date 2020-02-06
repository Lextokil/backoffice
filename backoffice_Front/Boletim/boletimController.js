
angular.module("escola").controller("boletimController", function ($scope, $http, $timeout) {
    $scope.app = "Boletim";
    $scope.boletins = [];
    $scope.alunos = [];
    $scope.adicionarBoletim = function (boletim) {
        $scope.boletins.push(angular.copy(boletim));
        delete $scope.boletim;
        $scope.boletimForm.$setPristine();
    };

    $scope.carregarBoletinsDoAluno = function (id) {

        if (isNaN(id)) {
            showAlertId();
        } else {
            console.log(id);
            $http.get("http://localhost:8080/boletins/all/" + id).then(function (data, status) {
                $scope.boletins = data.data;
            });
        }

    };



    $scope.exportarBoletim = function (id) {
        if (isNaN(id)) {
            showAlertId();
        } else {
            window.location.href = "http://localhost:8080/boletins/export/" + id;
            showAlertSuccess();

        }

    };
    var carregarAlunos = function () {
        $http.get("http://localhost:8080/alunos/all").then(function (data) {
            $scope.alunos = data.data;

        });
    };

    $scope.isEditable = function () {
        return condition;
    };

    var condition = true;

    $scope.editable = function () {
        condition = !condition;

    };



    $scope.salvarBoletins = function (boletins, id) {
        $scope.boletins = boletins;
        condition = true;
        if(isNaN(id)){
            showAlertId();
        }else{
            $http.put("http://localhost:8080/boletins/updateAll/" + id, boletins).then(function (data) {
            $scope.carregarBoletinsDoAluno(id);
        });
        }
        

    }

    $scope.apagarBoletins = function (boletins) {
        $scope.boletins = boletins.filter(function (boletim) {
            if (!boletim.selecionado) return boletim;
        });
    };
    $scope.onChange = function (boletim) {
        return boletim
    }
    $scope.media = function (boletim) {
        return ((parseFloat(boletim.bim1) + parseFloat(boletim.bim2) + parseFloat(boletim.bim3) + parseFloat(boletim.bim4)) / 4).toFixed(2);

    }
    
    var showAlertId = function () {
        var alert = document.getElementById("alertId");
        alert.style.display = "block";
        $timeout(hideAlertId, 4000);

    }
    var hideAlertId = function () {
        var alert = document.getElementById("alertId");
        alert.style.display = "none";
    }

    var showAlertSuccess = function () {
        var alert = document.getElementById("alertSuccess");
        alert.style.display = "block";
        $timeout(hideAlertSuccess, 4000);

    }
    var hideAlertSuccess = function () {
        var alert = document.getElementById("alertSuccess");
        alert.style.display = "none";
    }




    carregarAlunos();
});
