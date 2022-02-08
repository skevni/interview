angular.module('hw7-app').controller('studentsCreateController', function ($scope, $http, $routeParams, $location, $rootScope) {
    const applicationPath = 'http://localhost:8189/students/'

    $scope.create = function (id) {
        $http({
            url: applicationPath,
            method: 'POST',
            data: $scope.student
        })
            .then(function successCallback(response) {
                $location.path("/students");
            }, function failureCallback(response) {
                alert(response.data.messages);
            });
    };
});