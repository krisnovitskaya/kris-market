angular.module('app').controller('cartController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';


        $scope.createOrder = function(){
            $http({
                        url: contextPath + '/api/v1/orders/create',
                        method: 'POST',
                        params: {
                            phone: $scope.newOrder.phone,
                            address: $scope.newOrder.address
                        }
                    })
                        .then(function (response) {
                            console.log("ok");
                            $scope.newOrder = null;
                            $scope.cartContentRequest();
                        });
        };


    $scope.cartContentRequest = function () {
        $http({
            url: contextPath + '/api/v1/cart',
            method: 'GET'
        })
            .then(function (response) {
                console.log(response.data);
                $scope.cart = response.data;
            });
    };

    $scope.decrementItem = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/dec/' + productId,
            method: 'GET'
        })
            .then(function (response) {
                $scope.cartContentRequest();
            });
    };

    $scope.removeItem = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/remove/' + productId,
            method: 'GET'
        })
            .then(function (response) {
                $scope.cartContentRequest();
            });
    };

    $scope.incrementItem = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/add/' + productId,
            method: 'GET'
        })
            .then(function (response) {
                $scope.cartContentRequest();
            });
    };


    $scope.isUserLoggedIn = function () {
        if ($localStorage.currentUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.cartContentRequest();

});