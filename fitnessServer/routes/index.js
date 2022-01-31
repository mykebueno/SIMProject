var express = require('express');
var path = require('path');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');

const indexRouter = express.Router();

/* GET home page. */
indexRouter.get('/', function(req, res, next) {
  console.log("Sending index");
  
  if(req.signedCookies.user)
  {
    res.statusCode = 200;
    res.redirect('dashboard');
    console.log('trying to redirect');
  }
  else
  {
    res.statusCode = 200;
    res.sendFile(path.join(__dirname,'../public/index.html'));
  }
});

module.exports = indexRouter;
