var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var Steps = new Schema({
    steps: {
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

var Stepss = mongoose.model('Steps', Steps);

module.exports = Stepss;