angular.module('app').controller('authController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.currentUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;

                    console.log($localStorage.currentUser);
                }
            }, function errorCallback(response) {
                window.alert(response.data.message);
                $scope.clearUser();
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
        $window.location.href = '#!/main'
    };

    $scope.clearUser = function () {
        delete $localStorage.currentUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function () {
//        $scope.getUserRole();
        if ($localStorage.currentUser) {
            return true;
        } else {
            return false;
        }
    };

//    $scope.isAdmin = function () {
//            $scope.getUserRole();
//            if ($scope.userRoles.includes('ROLE_ADMIN')) {
//                return true;
//            } else {
//                return false;
//            }
//        };

//    $scope.getUserRole = function() {
//            tokenPayload = jwtHelper.decodeToken($localStorage.currentUser.token);
//            $scope.userRoles = tokenPayload.roles;
//            alert($scope.userRoles);
//        };
});