angular.module('app').controller('regController', function ($scope, $http, $location) {

    const contextPath = 'http://localhost:8189/market';



        $scope.trySignUp = function() {
            $http.post(contextPath + '/reg', $scope.newUser)
                .then(function successCallback(response) {
                      alert('Вы успешно зарегистрированы');
                            $scope.newUser = null;
                            $location.url('/auth');
                      }, function errorCallback(response){
                            if(!angular.equals(response.data.message,"")){
                                alert(response.data.message);
                            }
                            $scope.newUser = null;
            });
    };
});