angular.module("escola").controller("alunoController", function ($scope, $http) {
    $scope.app = "Alunos";
    $scope.alunos = [];
    $scope.turmas = [];

    var carregarTurmas = function () {
        $http.get("http://localhost:8080/turmas/all").then(function (data) {
            $scope.turmas = data.data;
        });
    };

    $scope.carregarAlunosByTurma = function (id) {
        $http.get("http://localhost:8080/alunos/allByTurma/" + id).then(function (data) {
            $scope.alunos = data.data;
        });
        
        console.log($scope.alunos);
    };

    $scope.isEditable = function () {
        return condition;
    };

    var condition = true;

    $scope.editable = function () {
        condition = !condition;

    };

    $scope.salvarAlunos = function (alunos, id) {
        condition = true;
        console.log(alunos);
        $http.put("http://localhost:8080/alunos/updateAll/", alunos).then(function (data) {

        });
        $scope.carregarAlunosByTurma(id);
    }

    carregarTurmas();
});