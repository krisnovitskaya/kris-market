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

//        $httpProvider.interceptors.push(function ($q, $location) {
//            return {
//                'responseError': function (rejection, $localStorage, $http) {
//                    var defer = $q.defer();
//                    if (rejection.status == 401 || rejection.status == 403) {
//                        console.log('error: 401-403');
//                        $location.path('/auth');
//                        if (!(localStorage.getItem("localUser") === null)) {
//                            delete $localStorage.currentUser;
//                            $http.defaults.headers.common.Authorization = '';
//                            console.log('zxc');
//                        }
//                        console.log(rejection.data);
//                        var answer = JSON.parse(rejection.data);
//                        console.log(answer);
//                        // window.alert(answer.message);
//                    }
//                    defer.reject(rejection);
//                    return defer.promise;
//                }
//            };
//        });
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