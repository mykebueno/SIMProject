const { ObjectId } = require('mongodb');
var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var Information = new Schema({
    age: {
        type: Number,
        required: true,
    },
    height: {
        type: Number,
        required: true
    },
    gender: {
        type: String,
        required: true
    },
    caloriesIntake: {
        type: Number,
        required: true
    },
    waterIntake:{
        type: Number,
        required: true
    },
    userId:{
        type: Schema.Types.ObjectId,
        required: true
    }
});

var Informations = mongoose.model('Information', Information);

module.exports = Informations;