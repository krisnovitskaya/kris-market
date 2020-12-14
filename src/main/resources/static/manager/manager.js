angular.module('app').controller('managerController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

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

});