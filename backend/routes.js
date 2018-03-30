// Importing express
var express = require('express');
var request = require("request");
// var jsPDF = require('jspdf');
var pdfMake = require('pdfmake');
var pdfMakePrinter = require('./node_modules/pdfmake/src/printer');
var printer = new pdfMakePrinter(fontDescriptors);
var doc = printer.createPdfKitDocument(pdfDoc);
// var pdfFonts = require('virtual-fs');
// import pdfMake from 'pdfmake/build/pdfmake.js';
// import pdfFonts from 'pdfmake/build/vfs_fonts.js';

// pdfMake.vfs = pdfFonts.pdfMake.vfs;

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
		// var doc = new jsPDF();
	  // valuePDF.forEach(function(val, i){
	  //     doc.text(20, 10 + (i * 10),
		// 				"LocationID: " + val.LocationID +
	  //         "Father's Name: " + val.NameFather +
	  //         "Mother's Name: " + val.NameMother +
		// 				"Phone: " + val.Phone +
		// 				"Address" + val.Address +
		// 				"Mother's Aadhar: " + val.AdharMother +
		// 				"Email: " + val.Email
		// 			);
	  // });
	  // doc.save('Test.pdf');
		const document = { content: [{text: 'Area-Wise Data', fontStyle: 15, lineHeight: 2}] }
		valuePDF.forEach(val => {
		    document.content.push({
		        columns: [
		            { text: 'LocationID', width: 60 },
		            { text: ':', width: 10 },
		            { text: val.LocationID, width: 50 },
		            { text: 'Fathers Name:', width: 60 },
		            { text: ':', width: 10 },
								{ text: val.NameFather, width: 50},
								{ text: 'Mothers Name:', width: 60 },
		            { text: ':', width: 10 },
		            { text: val.NameMother, width: 50 },
		            { text: 'Phone:', width: 60 },
		            {text: ':', width: 10 },
								{ text: val.Phone, width: 50},
								{ text: 'Address:', width: 60 },
		            { text: ':', width: 10 },
		            { text: val.Address, width: 50 },
		            { text: 'Mothers Aadhar:', width: 60 },
		            {text: ':', width: 10 },
								{ text: val.AdharMother, width: 50},
								{ text: 'Email:', width: 60 },
		            {text: ':', width: 10 },
								{ text: val.Email, width: 50}
		        ],
		        lineHeight: 2
		    });
		});
		pdfMake.createPdf(document).download();
	});
	res.send("Done!");
});


module.exports = route;
