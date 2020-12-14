angular.module('app').controller('adminController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';


    $scope.getCategory = function() {
            $http({
                        url: contextPath + '/api/v1/categories',
                        method: 'GET'
                    })
                        .then(function (response) {
                            $scope.Category = response.data;
                        });
        }

    $scope.submitCreateNewCategory = function () {
        $http({
                url: contextPath + '/api/v1/categories',
                method: 'POST',
                data: $scope.newCategory,
            })
            .then( function successCallback(response) {
                        $scope.newCategory = null;
                        $scope.getCategory();
                        alert('Добавлена новая категория');
                 }, function errorCallback(response){
                    $scope.newCategory = null;
                    alert('Category add fail. Wrong input data.');
                });
    };


    $scope.submitCreateNewProduct = function () {
        $http({
                url: contextPath + '/api/v1/products',
                method: 'POST',
                data: $scope.newProduct,
                params:{
                    categories:  $scope.categories
                }
            })
            .then(function (response) {
                $scope.newProduct = null;
                alert('Добавлен новый продукт');
            });
    };


    $scope.fillOrderTable = function () {
            $http({
                    url: contextPath + '/api/v1/orders/get',
                    method: 'POST',
                    params:{
                        status:  $scope.status
                    }
                })
                .then(function (response) {
                    $scope.adminOrders = response.data;
                   });
        };


    $scope.setNewStatus = function(orderId, selectedStatus){
               $http({
                    url: contextPath + '/api/v1/orders/set_status',
                    method: 'PUT',
                    params:{
                        id: orderId,
                        status: selectedStatus
                    }
                })
                .then( function successCallback(response) {
                        alert('Статус заказа ' + orderId + ' обновлен')
                        $scope.status == null;
                        $scope.fillOrderTable();
                    }, function errorCallback(response){
                          alert('Wrong input data');
                });
    };



    $scope.getCategory();
});