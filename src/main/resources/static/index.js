(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngStorage', 'angular-jwt', 'ui.bootstrap'])
        .config(config)
        .run(run);



    function config($routeProvider, $httpProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'main/main.html'
            })
            .when('/auth', {
                templateUrl: 'auth/auth.html',
                controller: 'authController'
            })
            .when('/reg', {
                templateUrl: 'reg/reg.html',
                controller: 'regController'
            })
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            })
            .when('/profile', {
                 templateUrl: 'profile/profile.html',
                 controller: 'profileController'
            })
            .when('/admin', {
                templateUrl: 'admin/admin.html',
                controller: 'adminController'
            })
            .when('/orders', {
                 templateUrl: 'orders/orders.html',
                 controller: 'ordersController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartController'
            });

    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.currentUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.currentUser.token;
        }
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
                    let publicPages = ['/auth', '/', '/store', '/registration'];
                    let restrictedPage = publicPages.indexOf($location.path()) === -1;
                    if (restrictedPage && !$localStorage.currentUser) {
                        $location.path('/auth');
                    }
                });
    }
})();