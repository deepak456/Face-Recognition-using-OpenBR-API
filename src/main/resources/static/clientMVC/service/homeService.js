nlpApp.service('homeService', function($resource, NLPFactory) {
	
	this.doRegistration = function(registration) {
        return NLPFactory.doRegistration.register(registration);
 
    };           
                   
                    
                });

nlpApp.factory("NLPFactory", function($resource) {
    return {
    	doRegistration : $resource("register", {}, {
            'register' : {
                method : 'POST',
                isArray : false
            }
        })
    };
});
                   