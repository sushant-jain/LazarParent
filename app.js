// Helper modules that will be used
var path = require('path');
var bodyParser = require('body-parser')
var express = require('express');
// This imports the Router that uses the template engine
var index = require('./routes.js');

app = express()
app.use(bodyParser.urlencoded({ extended: true }))
app.use(bodyParser.json())
// Sets the template engine as EJS
app.set('view engine', 'ejs');

// This defines that the 'views' folder contains the templates
app.set('views', path.join(__dirname, '/views'));

// These options are necessary to

// This bind the Router to the / route
app.use('/', index)

app.listen(3000, () => console.log('Example app listening on port 3000!'))