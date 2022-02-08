(function () {
    angular.module('hw7-app', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run)

    function config($routeProvider) {
        $routeProvider
            .when('/students', {
                templateUrl: "student/student.html",
                controller: "studentsController"
            })
            .when('/students/create', {
                            templateUrl: "student/create/student_cr.html",
                            controller: "studentsCreateController"
                        })
            .when('/students/edit/:studentId', {
                                        templateUrl: "student/edit/student_edit.html",
                                        controller: "studentsEditController"
                                    })
            .otherwise({
                redirectTo: '/'
            });

    }

    function run($rootScope, $http, $localStorage) {
    }
})();

angular.module('hw7-app').controller('indexController', function ($scope, $http, $location) {

});