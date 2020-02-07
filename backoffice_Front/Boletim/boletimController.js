
angular.module("escola").controller("boletimController", function ($scope, $http, alertService) {
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
            alertService.showAlertId($scope, "Por favor Selecione um aluno!");
        } else {
            console.log(id);
            $http.get("http://localhost:8080/boletins/all/" + id).then(function (data, status) {
                $scope.boletins = data.data;
            }).catch(function(status, response){
                alertService.showAlertId($scope,"Ops, ocorreu algum problema")
            });;
        }

    };



    $scope.exportarBoletim = function (id) {
        if (isNaN(id)) {
            alertService.showAlertId($scope, "Por favor Selecione um aluno!");
        } else {
            window.location.href = "http://localhost:8080/boletins/export/" + id;
            alertService.showAlertSuccess($scope, "Boletim exportado com sucesso!");

        }

    };
    var carregarAlunos = function () {
        $http.get("http://localhost:8080/alunos/all").then(function (data) {
            $scope.alunos = data.data;

        }).catch(function(status, response){
            alertService.showAlertId($scope,"Ops, ocorreu algum problema")
        });;
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
            alertService.showAlertId($scope, "Por favor Selecione um aluno!");
        }else{
            $http.put("http://localhost:8080/boletins/updateAll/" + id, boletins).then(function (data) {
            $scope.carregarBoletinsDoAluno(id);
            alertService.showAlertSuccess($scope, "Boletim salvo com sucesso!")
        }).catch(function(status, response){
            alertService.showAlertId($scope,"Ops, ocorreu algum problema")
        });;
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


    carregarAlunos();
});
