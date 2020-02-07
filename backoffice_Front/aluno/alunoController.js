angular.module("escola").controller("alunoController", function ($scope, $http, alertService) {
    $scope.app = "Alunos";
    $scope.alunos = [];
    $scope.turmas = [];

    var carregarTurmas = function () {
        $http.get("http://localhost:8080/turmas/all").then(function (data) {
            $scope.turmas = data.data;
        }).catch(function (status, response) {
            alertService.showAlertId($scope, "Ops, ocorreu algum problema ao carregar as turmas")
        });
    };

    $scope.carregarAlunosByTurma = function (id) {

        if (isNaN(id)) {
            $http.get("http://localhost:8080/alunos/all").then(function (data) {
                $scope.alunos = data.data;
            }).catch(function (status, response) {
                alertService.showAlertId($scope, "Ops, ocorreu algum problema")
            });
        } else {
            $http.get("http://localhost:8080/alunos/allByTurma/" + id).then(function (data) {
                $scope.alunos = data.data;
            }).catch(function (status, response) {
                alertService.showAlertId($scope, "Ops, ocorreu algum problema")
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
        $http.put("http://localhost:8080/alunos/updateAll/", alunos).then(function (data, response) {
            $scope.carregarAlunosByTurma(id);
            alertService.showAlertSuccess($scope, "Aluno salvo com sucesso!")
        }).catch(function (status, response) {
            alertService.showAlertId($scope, "Ops, ocorreu algum problema")
        });

    };
    $scope.deletarAluno = function (id, turmid) {
        $http.delete("http://localhost:8080/alunos/" + id).then(function (data, status) {
            console.log(id);
            $scope.carregarAlunosByTurma(turmid);
            alertService.showAlertSuccess($scope, "Aluno deletado com sucesso!")
        }).catch(function (status, response) {
            alertService.showAlertId($scope, "Ops, ocorreu algum problema")
        });
    };
    $scope.cadastrarAluno = function (aluno, turma) {
        condition = true;
        if (!turma || !aluno) {
            alertService.showAlertId($scope, "Por favor preencha os campos")
        } else {
            aluno.turma = turma.id;
            $http.post("http://localhost:8080/alunos/", aluno).then(function (data, status) {
                $scope.carregarAlunosByTurma(aluno.turma);
                alertService.showAlertSuccess($scope, "Aluno cadastrado com sucesso!")
            }).catch(function (status, response) {
                alertService.showAlertId($scope, "Ops, ocorreu algum problema")
            });
        }


    };

    $scope.turnoDoAluno = function (turmaid) {
        var index = $scope.turmas.findIndex(turma => turma.id == turmaid);
        return $scope.turmas[index].turno;
    }

    carregarTurmas();
});