angular.module("escola").controller("professorController", function ($scope, $http) {
    $scope.app = "Professores";
    $scope.professores = [];
    $scope.turmas = [];

    var carregarTurmas = function () {
        $http.get("http://localhost:8080/turmas/all").then(function (data) {
            $scope.turmas = data.data;
        });
    };

    $scope.isEditable = function () {
        return condition;
    };

    var condition = true;

    $scope.editable = function () {
        condition = !condition;

    };

    $scope.carregarProfessoresByTurma = function (id) {

        if (isNaN(id)) {
            $http.get("http://localhost:8080/professores/all").then(function (data) {
                $scope.professores = data.data;
                console.log($scope.professores);
            });
        } else {
            $http.get("http://localhost:8080/alunos/allByTurma/" + id).then(function (data) {
                $scope.professores = data.data;
            });
        }

    };
    $scope.deletarProfessor = function (id, turmid) {
        $http.delete("http://localhost:8080/professores/" + id).then(function (data, status) {
            $scope.carregarProfessoresByTurma(turmid);
        });
    };
    carregarTurmas();
});