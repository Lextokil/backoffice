angular.module("escola").config(function ($routeProvider) {   

    $routeProvider.when("/boletim", {
        templateUrl: "boletim/boletimView.html",
        controller: "boletimController"
    })
    .when("/aluno", {
        templateUrl: "aluno/alunoView.html",
        controller: "alunoController"
    })
    .when("/professor", {
        templateUrl: "professor/professorView.html",
        controller: "professorController"
    }).when("/turma", {
        templateUrl: "turma/turmaView.html",
        controller: "turmaController"
    }).when("/main", {
        templateUrl: "mainview/main.html"
    }).otherwise({ redirectTo: "/mainview/main.html" });
    

});