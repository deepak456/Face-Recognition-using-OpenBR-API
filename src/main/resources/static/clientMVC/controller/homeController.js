nlpApp.controller(
                'homeController',
                function($scope, $rootScope, $route,$http,$upload,$location, $window,
                        $routeParams,homeService) {
                    
                    $scope.init = function() {
                        if ($route.current.templateUrl == "./resources/CDMSClientMVC/partials/activeFiles.html") {}
                        $scope.flas=false;
                    	$scope.notFound=false;
                    };
                   
                    $scope.doRegistration = function(){
                    	$scope.flas=false;
                    	$scope.notFound=false;
                    	var file = $scope.myFile;
                    	console.dir(file);
                    	$upload
						.upload(
								{
									url : 'uploadFile',
									 headers: { 'Content-Type': 'multipart/form-data'},
									file : file
								})
                    	.success(function(data,
								status,
								headers,
								config) {
                    		
                    		if(!angular.isUndefined(data) && !angular.equals(data,"") && !angular.equals(data,null)){
                    			
                    			if(typeof Object.keys(data)[0] === 'undefined'){
                    				$scope.notFound=true;
                        			$scope.flas=false;
                    			}else{
                    			$scope.flas=true;
                    			$scope.notFound=false;
                    			$scope.result = data;
                    		}
                    		}
                    		
                    	}).error(function() {
                    	console.log('error');
                    	});
                    }
                    
                    $scope.init()
                    
                });
                   