angular.module('app').controller('storeController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';


    $scope.fillTable = function () {
        console.log('fill');
        $http.get(contextPath + '/api/v1/products')
            .then(function (response) {
                $scope.Products = response.data;
            });
    };

     $scope.applyFilter = function () {
         $http({
             url: contextPath + '/api/v1/products/prod',
             method: "GET",
             params: {title: $scope.productFilter.title, min_price: $scope.productFilter.minPrice, max_price: $scope.productFilter.maxPrice}
         }).then(function (response) {
             $scope.Products = response.data;
         });
     }


    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
            .then(function (response) {
                // $scope.Products.push(response.data);
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };



    $scope.fillTable();

});