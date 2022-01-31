var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
var cors = require('cors');

var indexRouter = require('./routes/index');
var registerRouter = require('./routes/register');
var dashboardRouter = require('./routes/dashboard');
var usersRouter = require('./routes/users');
var usersRegisterRouter = require('./routes/usersRegister');
var dishRouter = require('./routes/dishRouter')
var informationsRouter = require('./routes/informations');
var caloriesRouter = require('./routes/calories');
var weightsRouter = require('./routes/weights');
var watersRouter = require('./routes/waters');
var stepsRouter = require('./routes/steps');

const mongoose = require('mongoose');

const Users = require('./models/user');

const url = 'mongodb://localhost:27017/fitness'
const connect = mongoose.connect(url, { useNewUrlParser: true, useUnifiedTopology: true});

connect.then((db) => {
  console.log("Connected correctly to server");
}, (err) => { console.log(err); });

var app = express();

app.use(cors({origin: "http://127.0.0.1:3001", }))
app.disable('etag');

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser('12345-67890-09876-54321'));

async function auth (req, res, next) {
  console.log(req.signedCookies);

  if(!req.signedCookies.user)
  {
    var authHeader = req.headers.authorization;
    if (!authHeader) {
        var err = new Error('You are not authenticated!');
        //res.setHeader('WWW-Authenticate', 'Basic');
        err.status = 401;
        next(err);
        return;
    }

    var auth = new Buffer.from(authHeader.split(' ')[1], 'base64').toString().split(':');
    var user = auth[0];
    var pass = auth[1];

    var userFound = false;

    let idk = await Users.find({ username: user }, function(err, docs)
    {
      if (err){
        console.log(err);
      }
      else{
          if(docs.length !== 0)
          {
            console.log("First function call : ", docs);
            console.log("Doc : ", docs[0].toObject());
            console.log("Password : ", docs[0].toObject().password);
            
            if(docs[0].toObject().password === pass)
            {
              console.log("User found...");
              userFound = true;
              next();
            }
          }
      }
    });
    
    if(userFound)
    {
      console.log("Signing user...");
      res.cookie('user', user, { signed: true });
      next();
    }

    if (user == 'admin' && pass == 'password') {
        res.cookie('user', 'admin', { signed: true })
        next(); // authorized
    }
    else if(!userFound)
    {
        console.log("Entrou no erro!");
        var err = new Error('You are not authenticated!');
        //res.setHeader('WWW-Authenticate', 'Basic');      
        err.status = 401;
        next(err);
    } 
  }
  else
   {

    var userFound = false;

    let idk = await Users.find({ username: req.signedCookies.user }, function(err, docs)
    {
      if (err){
        console.log(err);
      }
      else{
          if(docs.length !== 0)
          {
            console.log("User found...");
            userFound = true;
            next();
          }
      }
    });

     if(req.signedCookies.user == 'admin')
     {
        next();
     }
     else if(!userFound)
     {
       console.log("Entrou no erro!");
        var err = new Error('You are not authenticated!');
        err.status = 401;
        next(err);
     }
   }
}

//app.use(express.static(path.join(__dirname, 'public')));

app.get('/javascripts/script.js',
  function(req,res,next){
    res.sendFile(path.join(__dirname+'/public/javascripts/script.js'));
  }
);

app.get('/stylesheets/signin.css',
  function(req,res,next){
    res.sendFile(path.join(__dirname+'/public/stylesheets/signin.css'));
  }
);

app.use('/',indexRouter);
app.use('/register', registerRouter);
app.use('/userRegister', usersRegisterRouter);

app.use(auth);

app.use('/dashboard', dashboardRouter);
app.use('/users', usersRouter);
app.use('/dishes', dishRouter);
app.use('/informations', informationsRouter);
app.use('/calories', caloriesRouter);
app.use('/steps', stepsRouter);
app.use('/weights', weightsRouter);
app.use('/waters', watersRouter);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error', {title: 'errorPage'});
});

module.exports = app;
