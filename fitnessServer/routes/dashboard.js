const express = require('express');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');
var path = require('path');

const dashboardRouter = express.Router();

/* GET home page. */
dashboardRouter.get('/', function(req, res, next) {
  console.log("Sending dashboard...");
  res.statusCode = 200;
  res.sendFile(path.join(__dirname,'../public/dashboard.html'));
});

module.exports = dashboardRouter;
