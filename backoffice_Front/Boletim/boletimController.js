 
        angular.module("escola").controller("escolaController", function ($scope, $http) {
            $scope.app = "Boletim";
            $scope.boletins = [];
            $scope.alunos = [];
            $scope.adicionarBoletim = function (boletim) {
                $scope.boletins.push(angular.copy(boletim));
                delete $scope.boletim;
                $scope.boletimForm.$setPristine();
            };
            $scope.carregarBoletinsDoAluno = function (id) {
                $http.get("http://localhost:8080/boletins/all/" + id).then(function (data, status) {
                    $scope.boletins = data.data;
                });
            };



            $scope.exportarBoletim = function (id) {
                window.location.href = "http://localhost:8080/boletins/export/" + id;
            };
            var carregarAlunos = function () {
                $http.get("http://localhost:8080/alunos/all").then(function (data, status) {
                    $scope.alunos = data.data;

                });
            };

            $scope.isEditable = function () {
                return condition;
            };

            var condition = true;

            $scope.editable = function (param) {
                condition = param;

            };

            $scope.salvarBoletins = function (boletins, id) {
                $scope.boletins = boletins;
                $scope.editable(true);
                console.log(boletins);
                $http.put("http://localhost:8080/boletins/updateAll/" + id, boletins).then(function (data) {
                    carregarAlunos();
                });
            }

            $scope.apagarBoletins = function (boletins) {
                $scope.boletins = boletins.filter(function (boletim) {
                    if (!boletim.selecionado) return boletim;
                });
            };
            $scope.onChange = function (boletim) {
                return boletim
            }
            $scope.media = function (boletim) {
                return ((parseFloat(boletim.bim1) + parseFloat(boletim.bim2) + parseFloat(boletim.bim3) + parseFloat(boletim.bim4)) / 4);

            }


            carregarAlunos();
        });