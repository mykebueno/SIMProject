var express = require('express');
var path = require('path');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');

const registerRouter = express.Router();

/* GET home page. */
registerRouter.get('/', function(req, res, next) {
  console.log("Sending register");
  
  res.statusCode = 200;
  res.sendFile(path.join(__dirname,'../public/register.html'));
  
});

module.exports = registerRouter;
