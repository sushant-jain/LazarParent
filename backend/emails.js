Parse.Cloud.job("sendEmail", function(request, response) {

  // Import SendGrid module and call with your SendGrid API Key
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
              email: 'utkarshmailing@gmail.com',
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
          value: 'request.params.body',
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
      response.error('Error = ' + JSON.stringify(SGresponse.body));
    }
    else {
      // Everything went fine
      console.log('Email sent!');
      response.success('Email sent!');
    }
  });

});
