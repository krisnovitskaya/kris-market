angular.module('app').controller('regController', function ($scope, $http, $window) {
//$location
    const contextPath = 'http://localhost:8189/market';

    $scope.trySignUp = function () {
        $http({
         url: contextPath + '/reg',
                            method: 'POST',
                            params: {
                                username: $scope.newUser.username,
                                password: $scope.newUser.password,
                                email: $scope.newUser.email
                            }
        })
            .then(function (response) {
                  $scope.newUser = null;
                  $window.location.href = '#!/auth'
            });
    }
});