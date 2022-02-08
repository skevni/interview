angular.module('hw7-app').controller('studentsController', function ($scope, $http, $routeParams, $location, $rootScope) {
    const applicationPath = 'http://localhost:8189/students/'

    $scope.edit = function (studentId) {
        $location.path("/students/edit/" + studentId);
    };
    $scope.create = function () {
        $location.path("/students/create");
    };
    $scope.delete = function (id) {
        $http({
            url: applicationPath + id,
            method: 'DELETE'
        })
            .then(function successCallback(response) {
                $location.path("/students");
                $scope.getAll();
            }, function failureCallback(response) {
                alert(response.data.messages);
            });
    };

    $scope.getAll = function () {
        $http({
            url: applicationPath,
            method: 'GET'

        }).then(function (response) {
//            console.log(response.data);
            $scope.students = response.data;
        });
    };

    $scope.getAll();
});