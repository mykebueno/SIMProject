var express = require('express');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');

const Calories = require('../models/calories');

//definimos um endpoint novo
const caloriesRouter = express.Router();

caloriesRouter.use(bodyParser.json());

caloriesRouter.route('/')
.get((req, res, next) => {
  Calories.find({})
  .then((calories) => {
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(calories);
  }, (err) => next(err))
  .catch((err) => next(err));
})
.post((req,res,next) => {
  Calories.create(req.body)
  .then((calorie) => {
      console.log('Dish Created', calorie);
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(calorie);
  }, (err) => next(err))
  .catch((err) => next(err));
})
.put((req,res,next) => {
  res.statusCode = 403;
  res.end('PUT operation not supported on /dishes');
})
.delete((req, res, next) => {
  Calories.remove({})
  .then((resp) => {
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(resp);
  }, (err) => next(err))
  .catch((err) => next(err));
});

caloriesRouter.route('/:userId')
.get((req, res, next) => {
  Calories.find({ userId: req.params.userId })
  .then((calorie) => {
      console.log('calorie Created', calorie);
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(calorie);
  }, (err) => next(err))
  .catch((err) => next(err));
})
.post((req,res,next) => {
  res.statusCode = 403;
  res.end('POST operation not supported on /calories/' + req.params.userId);
})
.put((req,res,next) => {
  Calories.findByIdAndUpdate(req.params.userId, {
      $set: req.body
  }, {new: true})
  .then((calorie) => {
      console.log('calorie Updated', calorie);
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(calorie);
  }, (err) => next(err))
  .catch((err) => next(err));
})
.delete((req, res, next) => {
  Calories.findByIdAndRemove(req.params.userId)
  .then((resp) => {
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(resp);
  }, (err) => next(err))
  .catch((err) => next(err));
});

module.exports = caloriesRouter;
