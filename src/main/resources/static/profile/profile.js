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

    $scope.updateProfile = function() {
          $http({
                    url: contextPath + '/api/v1/profile',
                    method: 'PUT',
                    data: $scope.Profile,
                    params:{
                        password:  $scope.user.password
                    }
                })
                   .then(
                            function successCallback(response) {
                                $scope.getProfile();
                                $scope.user.password = null;
                                alert('Profile has been updated');
                            }, function errorCallback(response){
                                if(!angular.equals(response.data.message,"")){
                                    alert(response.data.message);
                                }
                                $scope.user.password = null;
                                $scope.getProfile();
            });
    };


    $scope.getProfile();

});