'use strict';

App.controller('CardController', ['$scope', 'CardService', function($scope, CardService) {
	var self = this;
	self.cards = [];

	self.fetchAllCards = function() {
		CardService.fetchAllUsers()
			.then(
				function(d) {
					self.cards = d;
				},
				function(errResponse) {
					console.error('Error while fetching Currencies');
				}
			);
	};
	self.fetchAllCards();
}]);