angular.module('app').controller('profileController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.getProfile = function() {
            $http({
                        url: contextPath + '/api/v1/profile',
                        method: 'GET'
                    })
                        .then(function (response) {
                            $scope.Profile = response.data;
                        });
        }

//    $scope.updateProfile = function() {
//            $http({
//                        url: contextPath + '/api/v1/profile',
//                        method: 'POST',
//                        params: {
//                            profile: $scope.Profile,
//                            changeRequest: $scope.user
//                        }
//                    })
//                      .then(function (response) {
//                                $scope.Profile = response.data;
//                                $scope.user.password = null;
//                            });
//            }



    $scope.updateProfile = function() {
            $http({
                        url: contextPath + '/api/v1/profile',
                        method: 'POST',
                        params: {
                            firstname: $scope.Profile.firstname,
                            lastname:  $scope.Profile.lastname,
                            phone:     $scope.Profile.phone,
                            birthYear: $scope.Profile.birthYear,
                            sex:       $scope.Profile.sex,
                            town:      $scope.Profile.town,
                            password:  $scope.user.password
                        }
                    })
                      .then(function (response) {
                                $scope.Profile = response.data;
                                $scope.user.password = null;
                            });
            }




//    $scope.updateProfile = function() {
//          $http.post(contextPath + '/api/v1/profile', $scope.Profile, $scope.user)
//                          .then(function (response) {
//                                $scope.Profile = response.data;
//                                $scope.password = null;
//                            });
//            }



    $scope.getProfile();

});