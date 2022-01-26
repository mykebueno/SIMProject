var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var Weight = new Schema({
    weight: {
        type: Number,
        required: true,
    },
    date: {
        type: Date,
        required: true
    },
    userId: {
        type: ObjectId,
        required: true
    }
});

var Weights = mongoose.model('Weight', Weight);

module.exports = Weights;