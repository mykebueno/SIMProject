var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var Calories = new Schema({
    calories: {
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

var Caloriess = mongoose.model('Calories', Calories);

module.exports = Caloriess;