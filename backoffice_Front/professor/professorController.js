angular.module("escola").controller("professorController", function ($scope, $http, alertService) {
    $scope.app = "Professores";
    $scope.professores = [];
    $scope.turmas = [];
    $scope.materias = ["MATEMATICA", "GEOGRAFIA", "HISTORIA", "BIOLOGIA", "FISICA", "QUIMICA"];

    var carregarTurmas = function () {
        $http.get("http://localhost:8080/turmas/all").then(function (data) {
            $scope.turmas = data.data;
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
        console.log($scope.professores);

    };

    $scope.carregarProfessoresByTurma = function (id) {

        if (isNaN(id)) {
            $http.get("http://localhost:8080/professores/all").then(function (data) {
                $scope.professores = data.data;
            }).catch(function(status, response){
                alertService.showAlertId($scope,"Ops, ocorreu algum problema")
            });;
        } else {
            $http.get("http://localhost:8080/professores/allByTurma/" + id).then(function (data) {
                $scope.professores = data.data;
            }).catch(function(status, response){
                alertService.showAlertId($scope,"Ops, ocorreu algum problema")
            });;
        }

    };
    $scope.deletarProfessor = function (id, turmid) {
        $http.delete("http://localhost:8080/professores/" + id).then(function (data, status) {
            $scope.carregarProfessoresByTurma(turmid);
        }).catch(function(status, response){
            alertService.showAlertId($scope,"Ops, ocorreu algum problema")
        });;
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
        console.log(professor);
        if(!turma || !professor.nome || !professor.materia){            
            alertService.showAlertId($scope, "Por favor preencha todos os campos");
        }else{
            professor.turmas = [turma.id];
            $http.post("http://localhost:8080/professores/", professor).then(function (data, status) {
                $scope.carregarProfessoresByTurma(turma.id);
                alertService.showAlertSuccess($scope, "Sucesso ao cadastrar professor");
            }).catch(function(status, response){
                alertService.showAlertId($scope,"Ops, ocorreu algum problema")
            });;
        }

    };
    $scope.salvarProfessores = function (professores, idTurma) {
        condition = true;
        $http.put("http://localhost:8080/professores/updateAll/", professores).then(function (data, status) {
            $scope.carregarProfessoresByTurma(idTurma);
            alertService.showAlertSuccess($scope, "Sucesso ao salvar os professores");
        }).catch(function(status, response){
            alertService.showAlertId($scope,"Ops, ocorreu algum problema")
        });;

    };
    $scope.mostrarProfessores = function (index) {
        let x = document.getElementsByClassName("divProf")[index];
        if (x.style.display === "none") {
            x.style.display = "block";
        } else {
            x.style.display = "none";
        }
    }

   
    carregarTurmas();
});