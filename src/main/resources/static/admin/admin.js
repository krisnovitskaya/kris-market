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



    $scope.getCategory();
});