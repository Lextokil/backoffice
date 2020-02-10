angular.module("escola").controller("turmaController", function ($scope, $http, alertService) {
    $scope.app = "Turmas";
    $scope.turmas = [];
    $scope.alunos = [];
    $scope.professores = [];
    $scope.turnos = ["VESPERTINO", "MATUTINO", "NOTURNO"];


    $scope.carregarTurmas = function () {

        $http.get("http://localhost:8080/turmas/all").then(function (data) {
            $scope.turmas = data.data;
        }).catch(function (status, response) {
            alertService.showAlertId($scope, "Ops, ocorreu algum problema ao carregar as turmas")
        });

    };

    var carregarAlunos = function () {
        $http.get("http://localhost:8080/alunos/all").then(function (data) {
            $scope.alunos = data.data;
        }).catch(function (status, response) {
            alertService.showAlertId($scope, "Ops, ocorreu algum problema ao carregar os alunos")
        });
    };

    var carregarProfessores = function () {
        $http.get("http://localhost:8080/professores/all").then(function (data) {
            $scope.professores = data.data;
        }).catch(function (status, response) {
            alertService.showAlertId($scope, "Ops, ocorreu algum problema ao carregar os professores")
        });
    };
    $scope.adicionarAluno = function (turma) {
        console.log(turma);
        turma.alunos.push(null);

    }
    $scope.adicionarProfessor = function (turma) {
        turma.professores.push(null);

    }

    $scope.adicionarAlunoAoArray = function (turma, aluno, index) {
        turma.alunos.splice(index, 1, aluno);
    }
    $scope.adicionarProfessorAoArray = function (turma, professor, index) {
        turma.professores.splice(index, 1, professor);
        console.log(turma);
    }
    $scope.mostrarAlunos = function (index) {
        let x = document.getElementsByClassName("divAlunos")[index];
        if (x.style.display === "none") {
            x.style.display = "block";
        } else {
            x.style.display = "none";
        }
    }
    $scope.mostrarProfessores = function (index) {
        var x = document.getElementsByClassName("divProf")[index];
        if (x.style.display === "none") {
            x.style.display = "block";
        } else {
            x.style.display = "none";
        }
    }


    $scope.isEditable = function () {
        return condition;
    };

    var condition = true;

    $scope.editable = function () {
        condition = !condition;
        console.log($scope.professores);

    };

    $scope.salvarTurmas = function (turmas, idTurma) {
        condition = true;
        $http.put("http://localhost:8080/turmas/updateAll/", turmas).then(function (data, status) {
            $scope.carregarTurmas();
            carregarAlunos();
            alertService.showAlertSuccess($scope, "Turma salva com sucesso!")
        }).catch(function (status, response) {
            alertService.showAlertId($scope, "Ops, ocorreu algum problema")
        });

    };
    $scope.cadastrarTurma = function (turma) {
        condition = true;
        $http.post("http://localhost:8080/turmas/", turma).then(function (data, status) {
            $scope.carregarTurmas();
            alertService.showAlertSuccess($scope, "Turma cadastrada com sucesso!")
        }).catch(function (status, response) {
            alertService.showAlertId($scope, "Ops, ocorreu algum problema")
        });

    };
    $scope.deletarAluno = function (turma, index) {
        turma.alunos.splice(index, 1);
    };

    $scope.removerProfessore = function (turma, index) {
        console.log(turma);
        turma.professores.splice(index, 1);
        console.log(turma);
    };
    $scope.deletarTurma = function (idTurma){
        $http.delete("http://localhost:8080/turmas/"+idTurma).then(function (data, status) {
            $scope.carregarTurmas();
            alertService.showAlertSuccess($scope, "Turma deletada!")
        }).catch(function (status, response) {
            alertService.showAlertId($scope, "Não é possivel deletar uma turma que tem alunos ou professores")
        });
    }

    carregarAlunos();
    carregarProfessores();
});
