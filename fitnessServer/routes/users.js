var express = require('express');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');

const Users = require('../models/user');

//definimos um endpoint novo
const userRouter = express.Router();

userRouter.use(bodyParser.json());

userRouter.route('/')
.get((req, res, next) => {
  Users.find({})
  .then((users) => {
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(users);
  }, (err) => next(err))
  .catch((err) => next(err));
})
.post((req,res,next) => {
  Users.create(req.body)
  .then((user) => {
      console.log('User Created', user);
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(user);
  }, (err) => next(err))
  .catch((err) => next(err));
})
.put((req,res,next) => {
  res.statusCode = 403;
  res.end('PUT operation not supported on /users');
})
.delete((req, res, next) => {
  Users.remove({})
  .then((resp) => {
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(resp);
  }, (err) => next(err))
  .catch((err) => next(err));
});

userRouter.route('/:userId')
.get((req, res, next) => {
  Users.findById(req.params.userId)
  .then((user) => {
      console.log('User Found', user);
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(user);
  }, (err) => next(err))
  .catch((err) => next(err));
})
.post((req,res,next) => {
  res.statusCode = 403;
  res.end('POST operation not supported on /users/' + req.params.userId);
})
.put((req,res,next) => {
  Users.findByIdAndUpdate(req.params.userId, {
      $set: req.body
  }, {new: true})
  .then((user) => {
      console.log('User Updated', user);
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(user);
  }, (err) => next(err))
  .catch((err) => next(err));
})
.delete((req, res, next) => {
  Users.findByIdAndRemove(req.params.userId)
  .then((resp) => {
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(resp);
  }, (err) => next(err))
  .catch((err) => next(err));
});

module.exports = userRouter;
