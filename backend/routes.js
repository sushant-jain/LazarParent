// Importing express
var express = require('express');
var request = require("request");
PDFDocument = require('pdfkit');
blobStream  = require('blob-stream')
fs = require('fs')
var route = express.Router();
var client = require('twilio')('ACe7cdf1cec256c369dc641220aa082d4f','02c059879ff5931df43865448d37dc00');


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
		qs: { where: '{"LocationID":"Chj1202"}' },
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

route.get('/call', function(req, res) {
	client.calls.create({
		url: 'http://k003.kiwi6.com/hotlink/tmvhcviq4u/voice.xml',
		to: '+918368706829',
		from: '+12052360143',
	  })
	  .then(call => process.stdout.write(call.sid));
})

route.get('/message', function(req, res) {
	client.messages.create({
		// you can use request.params to pass the number you want to send
		to:'+918368706829', // Any number Twilio can deliver to
		from: '+12052360143', // A number you bought from Twilio and can use for outbound communication
		body: 'टीकाकरण की अगली तारीख 31-3-2018 है' // body of the SMS message

		}, {
		function(error, responseData) {
		  if (error) {
			 console.log(error);
			 res.send(error);
		  } else {
			 console.log(responseData);
			 res.send(responseData);
		  }
		}
	   });
})

route.get('/email', function(request, response) {
	var sg = require('sendgrid')('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');

  // Create the SendGrid Request
  var reqSG = sg.emptyRequest({
    method: 'POST',
    path: '/v3/mail/send',
    body: {
      personalizations: [
        {
          to: [
            {
              // This field is the "to" in the email
              email: 'nkmishra1997@gmail.com',
            },
          ],
          // This field is the "subject" in the email
          subject: 'Test E-mail',
        },
      ],
      // This field contains the "from" information
      from: {
        email: 'info@lazar.com',
        name: 'Lazar Reminder',
      },
      // This contains info about the "reply-to"
      // Note that the "reply-to" may be different than the "from"
      reply_to: {
        email: 'info@lazar.com',
        name: 'Team Lazar',
      },
      content: [
        {
          // You may want to leave this in text/plain,
          // Although some email providers may accept text/html
          type: 'text/plain',
          // This field is the body of the email
          value: 'टीकाकरण की अगली तारीख 31-3-2018 है',
        },
      ],
    },
  });

  // Make a SendGrid API Call
  sg.API(reqSG, function(SGerror, SGresponse) {
    // Testing if some error occurred
    if (SGerror) {
      // Ops, something went wrong
      console.error('Error response received');
      console.error('StatusCode=' + SGresponse.statusCode);
      console.error(JSON.stringify(SGresponse.body));
      response.send('Error = ' + JSON.stringify(SGresponse.body));
    }
    else {
      // Everything went fine
      console.log('Email sent!');
      response.send('Email sent!');
    }
  });
})
module.exports = route;
