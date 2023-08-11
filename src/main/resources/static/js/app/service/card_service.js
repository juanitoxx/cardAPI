'use strict';

app.factory('CardService', ['$http', '$q', function($http, $q) {

	return {

		fetchAllCards: function() {
			return $http.get('http://localhost:8090/card-api/api/v1/card/allCards/')
				.then(
					function(response) {
						return response.data;
					},
					function(errResponse) {
						console.error('Error while fetching cards');
						return $q.reject(errResponse);
					}
				);
		}

	};

}]);