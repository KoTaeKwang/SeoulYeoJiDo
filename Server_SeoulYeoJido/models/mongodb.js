var mongoose = require('mongoose');
var async = require('async');

var url = 'mongodb://localhost/test';
var options = {
	server : {poolSize:200},

}

var mongodb = mongoose.createConnection(url,options);

mongodb.on('error',function(err){
	if(err) console.error('db error',err);
});

mongodb.on('open',function callback(){
	console.info('mongo db connected successfully');
});

var Location_mongoSchema = require('../models/Location_mongo');
var LocationMongo = mongodb.model('LocationMongo',Location_mongoSchema);
var locacount=0;

exports.saveLoca = function(data,callback){

	async.waterfall([
		function(callback){  //구 번호 구하기
			var gunum=0;
			if(data.address!=undefined){
				var str = data.address;
				console.log("---->",str);
				console.log("-->",typeof(str));
				if(str.includes("은평구")||str.includes("서대문구"))
						gunum=1;

				if(str.includes("마포구")||str.includes("용산구"))
						gunum=2;

				if(str.includes("종로구")||str.includes("중구"))
					  gunum=3;

				if(str.includes("성북구")||str.includes("동대문구")||str.includes("성동구"))
						gunum=4;

				if(str.includes("강북구")||str.includes("도봉구")||str.includes("노원구"))
						gunum=5;

				if(str.includes("중랑구")||str.includes("광진구"))
						gunum=6;

				if(str.includes("송파구")||str.includes("강동구"))
						gunum=7;

				if(str.includes("서초구")||str.includes("강남구"))
						gunum=8;

				if(str.includes("관악구")||str.includes("금천구"))
						gunum=9;

				if(str.includes("영등포구")||str.includes("동작구"))
						gunum=10;

				if(str.includes("강서구")||str.includes("양천구")||str.includes("구로구"))
						gunum=11;

					callback(null,gunum);
				}
				else
					callback(null,0);

		},
		function(gunum,callback){ //save

			var LocationMongos = new LocationMongo({
				loca_guNum : gunum,
				loca_name : data.title,
				loca_address : data.address,
				loca_latitude : data.lat,
				loca_longitude : data.lng,
				loca_tel : data.tel,
				loca_description : data.description,
				loca_url : data.url,
			});
			LocationMongos.save(function(err,conn){ //save하는거고
				if(err){console.log('err',err);return;}
				callback(null,gunum);
			});
		},function(gunum,callback){ //category 넣기
			console.log(data.title);

			LocationMongo.update({loca_name:data.title},{ $push: {loca_category :  {loca_guNum:gunum,loca_categorynum:4} } },function(err){
					if(err){console.log(err);}
			}); //category 넣기

			callback(null);

		},function(callback){ //사진 넣기

				data.photo.forEach(function(val,index){
					LocationMongo.update({loca_name:data.title},{ $push: {loca_photo : {photo_id:index,photo_url:val} }  },function(err){
						if(err){console.log(err)}
					});    //photo 배열넣기
				});

				callback(null,0);
		}
		],function(err,result){
			if(err){console.log(err); return;}
			console.log(locacount++);
			callback(result);
	})
}