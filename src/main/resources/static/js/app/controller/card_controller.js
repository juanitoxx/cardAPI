'use strict';

app.controller('CardController', ['$scope', 'CardService', function($scope, CardService) {
	var self = this;
	self.cards = [];

	self.fetchAllCards = function() {
		CardService.fetchAllCards()
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