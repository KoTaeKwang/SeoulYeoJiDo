var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var Location_mongoSchema = Schema({
	loca_guNum : Number,
	loca_review :[{user_id:String, user_name:String, review_content : String, date:String}],
	loca_category : [{loca_guNum:Number,loca_categorynum:String}],
	loca_name : String,
	loca_address : String,
	loca_latitude : String,
	loca_longitude : String,
	loca_tel : String,
	loca_description : String,
	loca_url : String,
	loca_photo : [{photo_id:Number,photo_url:String}],
	loca_checkin : [{user_id:String , date:String}]
});

module.exports = Location_mongoSchema;