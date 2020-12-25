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
            .then( function successCallback(response) {
                        $scope.newProduct = null;
                        alert('Добавлен новый продукт');
                 }, function errorCallback(response){
                    $scope.newProduct = null;
                    if(!angular.equals(response.data.message,"")){
                         alert(response.data.message);
                    }
                });
    };

        $scope.showProduct = function () {
            $http({
                url: contextPath + '/api/v1/products/' + $scope.productId,
                method: 'GET'
            })
            .then( function successCallback(response) {
                    $scope.ChangingProduct = response.data;
                 }, function errorCallback(response){
                    alert(response.data.message);
                });
        };

    $scope.updateProduct = function () {
        $http({
                url: contextPath + '/api/v1/products',
                method: 'PUT',
                data: $scope.ChangingProduct,
                params:{
                    categories:  $scope.updateCategories
                }
            })
            .then( function successCallback(response) {
                    $scope.productId = $scope.ChangingProduct.id;
                    $scope.showProduct();
                 }, function errorCallback(response){
                    $scope.productId = $scope.ChangingProduct.id;
                    $scope.showProduct();
                    alert(response.data.message);
                });
    };

    $scope.getCategory();
});