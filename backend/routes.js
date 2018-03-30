// Importing express
var express = require('express');
var request = require("request");
// const MessagingResponse = require('twilio').twiml.MessagingResponse;
// Creating a Router
var route = express.Router();

// Defining a route that binds the GET method
route.post('/offlineData', function(req, res) {
	// const twiml = new MessagingResponse();;
	console.log(req.body.Body)
	var options = { method: 'GET',
		url: 'https://parseapi.back4app.com/classes/Mother',
		qs: { where: '{"AdharMother":"' + req.body.Body + '"}' },
		headers:
		{'x-parse-rest-api-key': 'ovpPF8ytV4BicURhTrUScFawAuCoG0SZIKtdG1p1',
		'x-parse-application-id': 'tdmZYblELna1qEfzXCbWVPkLT6meo8rGBeS6hqFe' }
	};

	request(options, function (error, response, body){
		if (error) throw new Error(error);
		value = JSON.parse(body)
		motherId = value.results[0].objectId
		console.log(motherId)

		var options2 = { method: 'GET',
		url: 'https://parseapi.back4app.com/classes/Pending',
		qs: { where: '{"Person":{"__type":"Pointer","className":"Mother","objectId":"' + motherId + '"}}' },
		headers:
		{'x-parse-rest-api-key': 'ovpPF8ytV4BicURhTrUScFawAuCoG0SZIKtdG1p1',
		'x-parse-application-id': 'tdmZYblELna1qEfzXCbWVPkLT6meo8rGBeS6hqFe' }
	};
		request(options2, function (error, response, body){
			if (error) throw new Error(error);
			value2 = JSON.parse(body)
			value2 = value2.results[0];
			console.log(value2)
			ans = {"Vaccine": value2.Vaccine, "DueDate": value2.DueDate.iso.slice(0, 10	)}
			res.json(ans)

		})
	});
});

module.exports = route;