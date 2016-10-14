var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var User_mongoSchema = Schema({
	us_id : String,
	us_checkin_loca :[{loca_id:String, date:String, loca_guNum:Number}],
	us_bookmark : [{loca_id:String,date:String, loca_guNum:Number}]
});

module.exports = User_mongoSchema;