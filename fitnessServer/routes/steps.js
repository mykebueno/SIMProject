var express = require('express');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');

const Steps = require('../models/steps');

//definimos um endpoint novo
const stepsRouter = express.Router();

stepsRouter.use(bodyParser.json());

stepsRouter.route('/')
.get((req, res, next) => {
  Steps.find({})
  .then((steps) => {
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(steps);
  }, (err) => next(err))
  .catch((err) => next(err));
})
.post((req,res,next) => {
  Steps.create(req.body)
  .then((step) => {
      console.log('Dish Created', step);
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(step);
  }, (err) => next(err))
  .catch((err) => next(err));
})
.put((req,res,next) => {
  res.statusCode = 403;
  res.end('PUT operation not supported on /dishes');
})
.delete((req, res, next) => {
  Steps.remove({})
  .then((resp) => {
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(resp);
  }, (err) => next(err))
  .catch((err) => next(err));
});

stepsRouter.route('/:userId')
.get((req, res, next) => {
  Steps.find({ userId: req.params.userId })
  .then((step) => {
      console.log('calorie Created', step);
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(step);
  }, (err) => next(err))
  .catch((err) => next(err));
})
.post((req,res,next) => {
  res.statusCode = 403;
  res.end('POST operation not supported on /steps/' + req.params.userId);
})
.put((req,res,next) => {
  Steps.findByIdAndUpdate(req.params.userId, {
      $set: req.body
  }, {new: true})
  .then((step) => {
      console.log('steps Updated', step);
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(step);
  }, (err) => next(err))
  .catch((err) => next(err));
})
.delete((req, res, next) => {
  Steps.findByIdAndRemove(req.params.userId)
  .then((resp) => {
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(resp);
  }, (err) => next(err))
  .catch((err) => next(err));
});

module.exports = stepsRouter;