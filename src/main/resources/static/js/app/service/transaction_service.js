'use strict';

app.factory('TransactionService', ['$http', '$q', function($http, $q) {

	return {
		fetchAllTransactions: function() {
			return $http.get('http://localhost:8090/card-api/api/v1/transaction/allTransactions')
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