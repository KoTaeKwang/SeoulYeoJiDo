var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var Gu_mongoSchema = Schema({
	gu_Info : [{gu_name:String, gu_num : Number}]
});

module.exports = Gu_mongoSchema;