angular.module("escola").controller("turmaController", function ($scope, $http) {
    $scope.app = "Turmas";
    $scope.turmas = [];
    $scope.alunos = [];
    $scope.professores = [];
    $scope.turnos = ["VESPERTINO", "MATUTINO", "NOTURNO"];


    $scope.carregarTurmas = function () {

        $http.get("http://localhost:8080/turmas/all").then(function (data) {
            $scope.turmas = data.data;
        });

    };

    var carregarAlunos = function () {
        $http.get("http://localhost:8080/alunos/all").then(function (data) {
            $scope.alunos = data.data;
        });
    };

    var carregarProfessores = function () {
        $http.get("http://localhost:8080/professores/all").then(function (data) {
            $scope.professores = data.data;

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
        });

    };
    $scope.cadastrarTurma = function (turma) {
        condition = true;
        $http.post("http://localhost:8080/turmas/", turma).then(function (data, status) {
            $scope.carregarTurmas();
        });

    };
    $scope.deletarAluno = function (turma, index) {
        turma.alunos.splice(index, 1);
    };
    $scope.deletarProfessores = function (turma, index) {s
        turma.professores.splice(index, 1);
    };
    $scope.deletarTurma = function (idTurma){
        $http.delete("http://localhost:8080/turmas/"+idTurma).then(function (data, status) {
            $scope.carregarTurmas();
        });
    }

    carregarAlunos();
    carregarProfessores();
});
