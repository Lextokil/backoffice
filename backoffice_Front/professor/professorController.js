angular.module("escola").controller("professorController", function ($scope, $http) {
    $scope.app = "Professores";
    $scope.professores = [];
    $scope.turmas = [];
    $scope.materias = ["MATEMATICA", "GEOGRAFIA", "HISTORIA", "BIOLOGIA", "FISICA", "QUIMICA"];

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
        console.log($scope.professores);

    };

    $scope.carregarProfessoresByTurma = function (id) {

        if (isNaN(id)) {
            $http.get("http://localhost:8080/professores/all").then(function (data) {
                $scope.professores = data.data;
            });
        } else {
            $http.get("http://localhost:8080/professores/allByTurma/" + id).then(function (data) {
                $scope.professores = data.data;
            });
        }

    };
    $scope.deletarProfessor = function (id, turmid) {
        $http.delete("http://localhost:8080/professores/" + id).then(function (data, status) {
            $scope.carregarProfessoresByTurma(turmid);
        });
    };

    $scope.adicionarTurma = function (professor) {
        professor.turmas.push(null);
    };

    $scope.deletarTurma = function (professor, index) {
        professor.turmas.splice(index, 1);
    };
    $scope.adicionarTurmaAoArray = function (professor, turma, index) {
        professor.turmas.splice(index, 1, turma);
    }

    $scope.cadastrarProfessor = function (professor, turma) {
        condition = true;
        professor.turmas = [turma.id];
        console.log(professor);
        $http.post("http://localhost:8080/professores/", professor).then(function (data, status) {
            $scope.carregarProfessoresByTurma(turma.id);
        });

    };
    $scope.salvarProfessores = function (professores, idTurma) {
        condition = true;
        console.log($scope.professores);
        console.log(professores);
        $http.put("http://localhost:8080/professores/updateAll/", professores).then(function (data, status) {
            $scope.carregarProfessoresByTurma(idTurma);
        });

    };
    $scope.deletarProfessor = function (id, turmid) {
        $http.delete("http://localhost:8080/professores/" + id).then(function (data, status) {
            console.log(id);
            $scope.carregarAlunosByTurma(turmid);
        });
    };

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
    carregarTurmas();
});