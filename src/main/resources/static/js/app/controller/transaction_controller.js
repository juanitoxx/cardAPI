'use strict';

app.controller('TransactionController', ['$scope', 'TransactionService', function($scope, TransactionService) {
	var self = this;
	self.transactions = [];

	self.fetchAllTransactions = function() {
		TransactionService.fetchAllTransactions()
			.then(
				function(d) {
					self.transactions = d;
				},
				function(errResponse) {
					console.error('Error while fetching Currencies');
				}
			);
	};
	self.fetchAllTransactions();
}]);