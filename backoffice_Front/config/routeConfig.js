angular.module("escola").config(function ($routeProvider) {   

    $routeProvider.when("/boletim", {
        templateUrl: "boletim/boletimView.html",
        controller: "boletimController"
    })
    .when("/aluno", {
        templateUrl: "aluno/alunoView.html",
        controller: "alunoController"
    }).otherwise({ redirectTo: "/index.html" });
    

});