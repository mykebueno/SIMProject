var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var Water = new Schema({
    water: {
        type: Number,
        required: true,
    },
    date: {
        type: Date,
        required: true
    },
    userId: {
        type: Schema.Types.ObjectId,
        required: true
    }
});

var Waters = mongoose.model('Water', Water);

module.exports = Waters;