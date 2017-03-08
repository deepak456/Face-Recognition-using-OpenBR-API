var nlpApp = angular.module('nlpApp', [ 'ngResource','ngRoute','ngSanitize','angularFileUpload']);
 
nlpApp.config(function($routeProvider,$locationProvider) {
            $routeProvider
                    .when(
                            "/",
                            {
                                title : 'NLP...',
                                templateUrl : "/clientMVC/partial/login.html",
                                controller : "homeController",
                            });
                   
        });

nlpApp.run(function($rootScope,$route) {

            $rootScope.$on("$routeChangeError", function(event, current,
                    previous, error) {
            });
            $rootScope.$on("$routeChangeStart", function(event, next, current) {
                 
            });
            $rootScope.$on('$routeChangeSuccess', function(event, current,
                    previous, error) {
            });
        
           // $rootScope.$watch('');
 
        });