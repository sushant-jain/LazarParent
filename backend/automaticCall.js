Parse.Cloud.job('automaticCall', function(request,response){
	//require the Twilio module and create a REST client
	var client = require('twilio')('ACe7cdf1cec256c369dc641220aa082d4f','02c059879ff5931df43865448d37dc00');

	client.calls.create({
	  url: 'https://raw.githubusercontent.com/sushant-jain/LazarParent/Backend/backend/voice.xml',
	  to: '+918368706829',
	  from: '+12052360143',
	})
	.then(call => process.stdout.write(call.sid));
});