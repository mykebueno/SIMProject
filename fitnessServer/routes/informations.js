var express = require('express');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');

const Informations = require('../models/information');

//definimos um endpoint novo
const informationRouter = express.Router();

informationRouter.use(bodyParser.json());

informationRouter.route('/')
.get((req, res, next) => {
  Informations.find({})
  .then((informations) => {
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(informations);
  }, (err) => next(err))
  .catch((err) => next(err));
})
.post((req,res,next) => {
  Informations.create(req.body)
  .then((information) => {
      console.log('Dish Created', information);
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(information);
  }, (err) => next(err))
  .catch((err) => next(err));
})
.put((req,res,next) => {
  res.statusCode = 403;
  res.end('PUT operation not supported on /dishes');
})
.delete((req, res, next) => {
  Informations.remove({})
  .then((resp) => {
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(resp);
  }, (err) => next(err))
  .catch((err) => next(err));
});


informationRouter.route('/:userId')
.get((req, res, next) => {
  Informations.findById(req.params.userId)
  .then((information) => {
      console.log('Information Created', information);
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(information);
  }, (err) => next(err))
  .catch((err) => next(err));
})
.post((req,res,next) => {
  res.statusCode = 403;
  res.end('POST operation not supported on /informations/' + req.params.userId);
})
.put((req,res,next) => {
  Informations.findByIdAndUpdate(req.params.userId, {
      $set: req.body
  }, {new: true})
  .then((information) => {
      console.log('Information Updated', information);
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(information);
  }, (err) => next(err))
  .catch((err) => next(err));
})
.delete((req, res, next) => {
  Informations.findByIdAndRemove(req.params.userId)
  .then((resp) => {
      res.statusCode = 200;
      res.setHeader('Content-Type', 'application/json');
      res.json(resp);
  }, (err) => next(err))
  .catch((err) => next(err));
});

module.exports = informationRouter;
