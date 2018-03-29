var monthlyReminderCall = require('./monthlyReminderCall.js');

Parse.Cloud.define("ANMAsendMessage",function(request,response){
	//require the Twilio module and create a REST client
	var client = require('twilio')('ACe7cdf1cec256c369dc641220aa082d4f','02c059879ff5931df43865448d37dc00');
	//Send an SMS text message
	client.sendMessage({
	// you can use request.params to pass the number you want to send
	to:'+918368706829', // Any number Twilio can deliver to
	from: '+12052360143', // A number you bought from Twilio and can use for outbound communication
	body: 'SMS From ANMA' // body of the SMS message

	}, {
	function(error, responseData) {
	  if (error) {
		 // console.log(error);
		 response.error(error);
	  } else {
		 // console.log(responseData);
		 response.success(responseData);
	  }
	}
   }
   );
   });