var express = require('express');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');

const Weights = require('../models/weight');

//definimos um endpoint novo
const weightsRouter = express.Router();

weightsRouter.use(bodyParser.json());

weightsRouter.route('/')
.get((req, res, next) => {
  Weights.find({})
  .then((weights) => {
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(weights);
  }, (err) => next(err))
  .catch((err) => next(err));
})
.post((req,res,next) => {
  Weights.create(req.body)
  .then((weight) => {
      console.log('Dish Created', weight);
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(weight);
  }, (err) => next(err))
  .catch((err) => next(err));
})
.put((req,res,next) => {
  res.statusCode = 403;
  res.end('PUT operation not supported on /dishes');
})
.delete((req, res, next) => {
  Weights.remove({})
  .then((resp) => {
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(resp);
  }, (err) => next(err))
  .catch((err) => next(err));
});

weightsRouter.route('/:userId')
.get((req, res, next) => {
  Weights.find({ userId: req.params.userId })
  .then((wheight) => {
      console.log('Wheight Found', wheight);
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(wheight);
  }, (err) => next(err))
  .catch((err) => next(err));
})
.post((req,res,next) => {
  res.statusCode = 403;
  res.end('POST operation not supported on /Wheights/' + req.params.userId);
})
.put((req,res,next) => {
  Weights.findByIdAndUpdate(req.params.userId, {
      $set: req.body
  }, {new: true})
  .then((wheight) => {
      console.log('Wheight Updated', wheight);
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(wheight);
  }, (err) => next(err))
  .catch((err) => next(err));
})
.delete((req, res, next) => {
  Weights.findByIdAndRemove(req.params.userId)
  .then((resp) => {
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(resp);
  }, (err) => next(err))
  .catch((err) => next(err));
});

module.exports = weightsRouter;
