// Importing express
var express = require('express');
var request = require("request");
PDFDocument = require('pdfkit');
blobStream  = require('blob-stream')
fs = require('fs')
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

route.get('/genPDF', function(req, res) {
	var options = {
		method: 'GET',
		url: 'https://parseapi.back4app.com/classes/Mother',
		qs: { where: '{"LocationID":"cde"}' },
		headers:
		{'x-parse-rest-api-key': 'ovpPF8ytV4BicURhTrUScFawAuCoG0SZIKtdG1p1',
		'x-parse-application-id': 'tdmZYblELna1qEfzXCbWVPkLT6meo8rGBeS6hqFe' }
	};

	request(options, function (error, response, body){
		if (error) throw new Error(error);
		valuePDF = JSON.parse(body);
		valuePDF = valuePDF.results;
		console.log(valuePDF)
		doc = new PDFDocument;
		doc.pipe(fs.createWriteStream('report.pdf'));

		valuePDF.forEach(function(val, i){
			doc.text("LocationID: " + val.LocationID +
			" Father's Name: " + val.NameFather +
			" Mother's Name: " + val.NameMother +
						" Phone: " + val.Phone +
						" Address" + val.Address +
						" Mother's Aadhar: " + val.AdharMother +
						" Email: " + val.Email, 20, 10 + (i * 10)
						);
		});
		doc.end();
		res.send("PDF Saved at backend/report.pdf!");
	});
});


module.exports = route;
