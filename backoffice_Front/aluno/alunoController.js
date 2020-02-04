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

        if (isNaN(id)) {
            $http.get("http://localhost:8080/alunos/all").then(function (data) {
                $scope.alunos = data.data;
            });
        } else {
            $http.get("http://localhost:8080/alunos/allByTurma/" + id).then(function (data) {
                $scope.alunos = data.data;
            });
        }

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
        $http.put("http://localhost:8080/alunos/updateAll/", alunos).then(function (data, status) {
            $scope.carregarAlunosByTurma(id);
        });

    };
    $scope.deletarAluno = function (id, turmid) {
        $http.delete("http://localhost:8080/alunos/" + id).then(function (data, status) {
            console.log(id);
            $scope.carregarAlunosByTurma(turmid);
        });
    };
    $scope.cadastrarAluno = function (aluno, turma) {
        condition = true;
        aluno.turma = turma.id;
        $http.post("http://localhost:8080/alunos/", aluno).then(function (data, status) {
            $scope.carregarAlunosByTurma(aluno.turma);
        });

    };

    $scope.turnoDoAluno = function(turmaid){
        var index = $scope.turmas.findIndex(turma => turma.id == turmaid);
        return $scope.turmas[index].turno;
    }

    carregarTurmas();
});