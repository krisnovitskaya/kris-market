angular.module('app').controller('regController', function ($scope, $http, $location) {

    const contextPath = 'http://localhost:8189/market';



        $scope.trySignUp = function () {
            $http.post(contextPath + '/reg', $scope.newUser)
                .then(function (response) {
                      $scope.newUser = null;
                      $location.url('/auth');
                });
        }
});