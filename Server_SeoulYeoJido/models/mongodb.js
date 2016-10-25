var mongoose = require('mongoose');
var async = require('async');
var geolib = require('geolib');
//var geolocation = require('node-geolocation');
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

var Location_mongoSchema = require('../models/Location_mongo');   //장소
var LocationMongo = mongodb.model('LocationMongo',Location_mongoSchema);

var User_mongoSchema = require('../models/User_mongo'); //사용자
var UserMongo = mongodb.model('UserMongo',User_mongoSchema);

var Gu_mongoSchema = require('../models/Gu_mongo'); //구
var GuMongo = mongodb.model('GuMongo',Gu_mongoSchema);


exports.saveUser = function(callback){
	var id = ['bake','han','hey','kim','kk','ko','lee','na','sam','so','yo'];

	id.forEach(function(val){

		var UserMongos = new UserMongo({
			us_id:val
		});
		UserMongos.save(function(err,conn){
			if(err){console.log(err);return;}
		})
	})
	callback(0);  //user 저장
}

exports.saveGu = function(callback){   //gu 저장

	/*var GuMongos = new GuMongo({
		id : 1
	});
	GuMongos.save(function(err,conn){if(err){console.log(err);return;}})
*/
	GuMongo.update({id:1},{ $push: { gu_Info: {$each: [ {gu_name:"은평구",gu_num:1}, {gu_name:"서대문구",gu_num:1}, {gu_name:"마포구",gu_num:2},
	{gu_name:"용산구",gu_num:2}, {gu_name:"종로구",gu_num:3}, {gu_name:"중구",gu_num:3}, {gu_name:"성북구",gu_num:4}, {gu_name:"동대문구",gu_num:4},
	{gu_name:"성동구",gu_num:4}, {gu_Name:"강북구",gu_num:5}, {gu_name:"도봉구",gu_num:5}, {gu_name:"노원구",gu_num:5}, {gu_name:"중랑구",gu_num:6},
	{gu_name:"광진구",gu_num:6}, {gu_Name:"송파구",gu_num:7}, {gu_name:"강동구",gu_num:7}, {gu_name:"서초구",gu_num:8}, {gu_name:"강남구",gu_num:8},
	{gu_name:"관악구",gu_num:9}, {gu_Name:"금천구",gu_num:9}, {gu_name:"영등포구",gu_num:10}, {gu_name:"동작구",gu_num:10}, {gu_name:"강서구",gu_num:11},
	{gu_name:"양천구",gu_num:11}, {gu_Name:"구로구",gu_num:11}  ]}}},function(err){
		if(err){console.log(err);return;}
		callback(0);
	})
}

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
			callback(result);
	})  //loca 저장
}


exports.showLoca = function(callback){  //location 정보   첫화면 list

	var arr=[];
	async.waterfall([
		function(callback){
			LocationMongo.find({},{_id:0,loca_name:1,loca_category:1,loca_photo:1,loca_checkin:1,loca_guNum:1,loca_review:1},function(err,result){
				callback(null,result);
			})
		},function(result,callback){
			async.each(result,function(item,cb){
				var obj ={};
				obj.loca_name = item.loca_name;
				obj.loca_photo = item.loca_photo[0];

				if(obj.loca_photo!=null){
					obj.loca_photo=obj.loca_photo.photo_url;
				}
				obj.loca_categorynum =0;
				obj.loca_guNum=0;

				if(item.loca_category[0]!=null){
				obj.loca_categorynum = item.loca_category[0].loca_categorynum;
				category(obj);
				}

				if(item.loca_guNum!=null){
				obj.loca_guNum = item.loca_guNum;
				gu(obj);
				}

				obj.loca_checkincount = item.loca_checkin.length;
				obj.loca_reviewcount = item.loca_review.length;
				arr.push(obj);
				cb();
			},function(err){
				callback(null,arr);
			})
		}
		],function(err,result){
			var obj = {};
			obj.location = result;
			callback(obj);
	})

}

exports.searchLocaName = function(data,callback){
	var arr=[];
		LocationMongo.find({},{_id:1,loca_name:1},function(err,results){

			async.each(results,function(item,cb){
					if(item.loca_name.includes(data)){
						arr.push(item);
					}
					cb();
			},function(err){
				var obj ={};
				obj.location=arr;
					callback(obj);
				}
			)
		})
}

exports.searchLocaAddress = function(data,callback){
	var arr=[];
		LocationMongo.find({},{_id:1,loca_address:1},function(err,results){

			async.each(results,function(item,cb){
					if(item.loca_address.includes(data)){
						arr.push(item);
					}
					cb();
			},function(err){
				var obj={};
				obj.location=arr;
					callback(obj);
				}
			)
		})
}

exports.showGuLoca = function(data,callback){

	var arr=[];
	async.waterfall([
		function(callback){
			LocationMongo.find({loca_guNum:data},{_id:0,loca_name:1,loca_category:1,loca_photo:1,loca_checkin:1,loca_guNum:1,loca_review:1},function(err,result){
				callback(null,result);
			})
		},function(result,callback){
			async.each(result,function(item,cb){
				var obj ={};
				obj.loca_name = item.loca_name;
				obj.loca_photo = item.loca_photo[0];

				if(obj.loca_photo!=null){
					obj.loca_photo=obj.loca_photo.photo_url;
				}
				obj.loca_categorynum =0;
				obj.loca_guNum=0;

				if(item.loca_category[0]!=null){
				obj.loca_categorynum = item.loca_category[0].loca_categorynum;
				category(obj);
				}

				if(item.loca_guNum!=null){
				obj.loca_guNum = item.loca_guNum;
				gu(obj);
				}

				obj.loca_checkincount = item.loca_checkin.length;
				obj.loca_reviewcount = item.loca_review.length;
				arr.push(obj);
				cb();
			},function(err){
				callback(null,arr);
			})
		}
		],function(err,result){
			var obj = {};
			obj.location = result;
			callback(obj);
	})

}

exports.showCategoryLoca = function(data,callback){
	var arr=[];
	async.waterfall([
		function(callback){
			LocationMongo.find({},{_id:0,loca_name:1,loca_category:1,loca_photo:1,loca_checkin:1,loca_guNum:1,loca_review:1},function(err,result){
				callback(null,result);
			})
		},function(result,callback){
			async.each(result,function(item,cb){
				if(item.loca_category[0]!=null&&item.loca_category[0].loca_categorynum==data)
				{
						var obj ={};
						obj.loca_name = item.loca_name;
						obj.loca_photo = item.loca_photo[0];

						if(obj.loca_photo!=null){
							obj.loca_photo=obj.loca_photo.photo_url;
						}
						obj.loca_categorynum =0;
						obj.loca_guNum=0;

						if(item.loca_category[0]!=null){
						obj.loca_categorynum = item.loca_category[0].loca_categorynum;
						category(obj);
						}

						if(item.loca_guNum!=null){
						obj.loca_guNum = item.loca_guNum;
						gu(obj);
						}

						obj.loca_checkincount = item.loca_checkin.length;
						obj.loca_reviewcount = item.loca_review.length;
						arr.push(obj);
				}
				cb();
			},function(err){
				callback(null,arr);
			})
		}
		],function(err,result){
			var obj = {};
			obj.location = result;
			callback(obj);
	})

}

exports.showDetailLoca = function(data,callback){

	LocationMongo.findOne({loca_name:data},{},function(err,result){
		var obj ={};
		var arr=[];
		if(result.loca_photo[0]!=null){
			async.each(result.loca_photo,function(item,cb){
					arr.push(item.photo_url);
					cb();
			},function(err){
				obj.loca_photo=arr
			})
		}
		obj.loca_name = result.loca_name;
		obj.loca_address = result.loca_address;
		obj.loca_latitude = result.loca_latitude;
		obj.loca_longitude = result.loca_longitude;
		obj.loca_tel = result.loca_tel;
		obj.loca_description = result.loca_description;
		obj.loca_checkincount = result.loca_checkin.length;
		obj.loca_review = result.loca_review;
		obj.loca_url = result.loca_url;


		callback(obj);

	})
}

exports.checkin = function(data,callback){
	LocationMongo.findOne({loca_name:data.loca_name},{_id:0,loca_latitude:1,loca_longitude:1},function(err,result){

	var distance = geolib.getPathLength([
	    {latitude: result.loca_latitude, longitude: result.loca_longitude},
	    {latitude: data.loca_lat, longitude: data.loca_lon}
	]);

	if(distance<500)
		callback(1);
	else
		callback(0);
		//meter;
	})
}

exports.addReview = function(data,callback){

	LocationMongo.update({loca_name:data.loca_name},{ $push: {loca_review :  {user_id:data.user_id,review_content:data.content,date:data.date} } },function(err){
			if(err){console.log(err);}
			callback(1);
	}); //category 넣기
}

//loca_checkin : [{user_id:String , date:String}]
exports.addCheckin = function(data,callback){
	LocationMongo.update({loca_name:data.loca_name},{ $push: {loca_checkin :  {user_id:data.user_id,date:data.date} } },function(err){
			if(err){console.log(err);}
			callback(1);
	}); //category 넣기
}

exports.showCheckin = function(data,callback){
	var arr=[];
		async.waterfall([
				function(callback){
					LocationMongo.find({},{_id:0,loca_name:1,loca_category:1,loca_photo:1,loca_checkin:1,loca_guNum:1,loca_review:1},function(err,result){
						callback(null,result);
					})
				},function(result,callback){
					async.each(result,function(item,cb){
						if(item.loca_checkin.length!=0){
							async.each(item.loca_checkin,function(items,cb){
								if(items.user_id==data){
									var obj ={};
									obj.loca_name = item.loca_name;
									obj.loca_photo = item.loca_photo[0];

									if(obj.loca_photo!=null){
										obj.loca_photo=obj.loca_photo.photo_url;
									}
									obj.loca_categorynum =0;
									obj.loca_guNum=0;

									if(item.loca_category[0]!=null){
									obj.loca_categorynum = item.loca_category[0].loca_categorynum;
									category(obj);
									}

									if(item.loca_guNum!=null){
									obj.loca_guNum = item.loca_guNum;
									gu(obj);
									}
									obj.loca_checkincount = item.loca_checkin.length;
									obj.loca_reviewcount = item.loca_review.length;
									arr.push(obj);
								}
								cb();
							})
						}
						cb();
					},function(err){
						callback(null,arr);
					})
				}//
			],function(err,result){
				var obj = {};
				obj.location = result;
				callback(obj);
		})
}

function category(obj){
	if(obj.loca_categorynum==1){
		obj.loca_categorynum="쇼핑";
	}else if(obj.loca_categorynum==2){
		obj.loca_categorynum="문화";
	}else if(obj.loca_categorynum==3){
		obj.loca_categorynum="공원";
	}else if(obj.loca_categorynum==4){
		obj.loca_categorynum="전통시장";
	}else if(obj.loca_categorynum==5){
		obj.loca_categorynum="랜드마크";
	}else if(obj.loca_categorynum==6){
		obj.loca_categorynum="유적지";
	}
}

function gu(obj){
		if(obj.loca_guNum==1){
				obj.loca_guNum="은평·서대문구"
		}else if(obj.loca_guNum==2){
				obj.loca_guNum="마포·용산구"
		}else if(obj.loca_guNum==3){
			obj.loca_guNum="종로·중구"
		}else if(obj.loca_guNum==4){
			obj.loca_guNum="성북·동대문·성동구"
		}else if(obj.loca_guNum==5){
			obj.loca_guNum="강북·도봉·노원구"
		}else if(obj.loca_guNum==6){
			obj.loca_guNum="중랑·광진구"
		}else if(obj.loca_guNum==7){
			obj.loca_guNum="송파·강동구"
		}else if(obj.loca_guNum==8){
			obj.loca_guNum="서초·강남구"
		}else if(obj.loca_guNum==9){
			obj.loca_guNum="관악·금천구"
		}else if(obj.loca_guNum==10){
			obj.loca_guNum="영등포·동작구"
		}else if(obj.loca_guNum==11){
			obj.loca_guNum="강서·양천·구로구"
		}

}