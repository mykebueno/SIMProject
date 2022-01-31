var express = require('express');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');

const Users = require('../models/user');

//definimos um endpoint novo
const usersRegisterRouter = express.Router();

usersRegisterRouter.use(bodyParser.json());

usersRegisterRouter.route('/')
.post((req,res,next) => {
  Users.create(req.body)
  .then((user) => {
      console.log('User Created', user);
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(user);
  }, (err) => next(err))
  .catch((err) => next(err));
});

module.exports = usersRegisterRouter;
