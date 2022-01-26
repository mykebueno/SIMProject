var express = require('express');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');

const Waters = require('../models/water');

//definimos um endpoint novo
const waterRouter = express.Router();

waterRouter.use(bodyParser.json());

waterRouter.route('/')
.get((req, res, next) => {
  Waters.find({})
  .then((waters) => {
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(waters);
  }, (err) => next(err))
  .catch((err) => next(err));
})
.post((req,res,next) => {
  Waters.create(req.body)
  .then((water) => {
      console.log('Dish Created', water);
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(water);
  }, (err) => next(err))
  .catch((err) => next(err));
})
.put((req,res,next) => {
  res.statusCode = 403;
  res.end('PUT operation not supported on /dishes');
})
.delete((req, res, next) => {
  Waters.remove({})
  .then((resp) => {
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(resp);
  }, (err) => next(err))
  .catch((err) => next(err));
});

waterRouter.route('/:userId')
.get((req, res, next) => {
  Waters.find({ userId: req.params.userId })
  .then((water) => {
      console.log('Water Created', water);
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(water);
  }, (err) => next(err))
  .catch((err) => next(err));
})
.post((req,res,next) => {
  res.statusCode = 403;
  res.end('POST operation not supported on /Waters/' + req.params.userId);
})
.put((req,res,next) => {
  Waters.findByIdAndUpdate(req.params.userId, {
      $set: req.body
  }, {new: true})
  .then((water) => {
      console.log('water Updated', water);
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(water);
  }, (err) => next(err))
  .catch((err) => next(err));
})
.delete((req, res, next) => {
  Waters.findByIdAndRemove(req.params.userId)
  .then((resp) => {
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(resp);
  }, (err) => next(err))
  .catch((err) => next(err));
});

module.exports = waterRouter;
