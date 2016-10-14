var mongoose = require('mongoose');

var url = 'mongodb://localhost/test';
var options = {
	server : {poolSize:100}
}

var mongodb = mongoose.createConnection(url,options);

mongodb.on('error',function(err){
	if(err) console.error('db error',err);
});

mongodb.on('open',function callback(){
	console.info('mongo db connected successfully');
});

module.exports = mognodb;