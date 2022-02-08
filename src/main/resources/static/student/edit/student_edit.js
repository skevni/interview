angular.module('hw7-app').controller('studentsEditController', function ($scope, $http, $routeParams, $location, $rootScope) {
    const applicationPath = 'http://localhost:8189/students/'

    $scope.prepareStudent = function () {
        $http.get(applicationPath + $routeParams.studentId)
            .then(function successCallback(response) {
                $scope.student = response.data;
            }, function failureCallback(response) {
                alert(response.data.messages);
                $location.path('/students');
            });
    }



    $scope.edit = function (id) {
        $http({
            url: applicationPath,
            method: 'PUT',
            data: $scope.student
        })
            .then(function successCallback(response) {
                $location.path("/students");
            }, function failureCallback(response) {
                alert(response.data.messages);
            });
    };

    $scope.prepareStudent();
});