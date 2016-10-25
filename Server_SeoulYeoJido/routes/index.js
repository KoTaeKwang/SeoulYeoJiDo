var express = require('express');
var request = require('request');
var xml2js = require('xml2js');
var xml2js_parser = new xml2js.Parser();
var striptags = require('striptags');
var fs = require('fs');
var async = require('async');
// var parser = require('xml2json');
var urlencode = require('urlencode');//위도, 경도 뽑기 위해 새로 추가한 모듈
var mongodb = require('../models/mongodb');

var router = express.Router();

router.get('/', function(req, res, next) {
  res.json("ddddd");
});

//api 인증기 : 6669707970726878393341417a576f
//http://openAPI.seoul.go.kr:8088/6669707970726878393341417a576f/xml/VisitSeoulKr/1/5
/* GET home page. */
/*
"row": [
      { 유적지
        "CATEGORY4": "유적지·종교성지",
        "URL": "http://contents.visitseoul.net/file_save/rss/0004003002002kr.xml"
      },
      { 문화
        "CATEGORY4": "박물관·기념관",
        "URL": "http://contents.visitseoul.net/file_save/rss/0004003002005kr.xml"
      },
      { 문화
        "CATEGORY4": "미술관",
        "URL": "http://contents.visitseoul.net/file_save/rss/0004003002006kr.xml"
      },
      { 랜드마크
        "CATEGORY4": "랜드마크",
        "URL": "http://contents.visitseoul.net/file_save/rss/0004003002003kr.xml"
      },
      { 유적지
        "CATEGORY4": "고궁",
        "URL": "http://contents.visitseoul.net/file_save/rss/0004003002001kr.xml"
      },
      { 공원
        "CATEGORY4": "공원",
        "URL": "http://contents.visitseoul.net/file_save/rss/0004003002017kr.xml"
      },
      { 공원
        "CATEGORY4": "테마파크",
        "URL": "http://contents.visitseoul.net/file_save/rss/0004003002020kr.xml"
      },
      { 시장
        "CATEGORY4": "전통시장",
        "URL": "http://contents.visitseoul.net/file_save/rss/0003001005003kr.xml"
      },
      { 쇼피
        "CATEGORY4": "쇼핑센터",
        "URL": "http://contents.visitseoul.net/file_save/rss/0003001005005kr.xml"
      },
      { 쇼핑
        "CATEGORY4": "쇼핑거리",
        "URL": "http://contents.visitseoul.net/file_save/rss/0003001005007kr.xml"
      },
      { 시장
        "CATEGORY4": "벼룩시장",
        "URL": "http://contents.visitseoul.net/file_save/rss/0003001005012kr.xml"
      },
*/
//http://contents.visitseoul.net/file_save/rss/0004003002005kr.xml

router.get('/makeUser',function(req,res,next){
		mongodb.saveUser(function(success){
				res.json(success);
		})
})

router.get('/makeGu',function(req,res,next){
	console.log("왔니");
	mongodb.saveGu(function(success){
		res.json(success);
	})

})

router.get('/xmlapi',function(req,res,next){

	request({uri:"http://contents.visitseoul.net/file_save/rss/0003001005012kr.xml"},function(err,response,body){
		if(err){
		   return console.error('err',err);
		}
		// // 다시 살려
		// body = body.replace(/<!\[CDATA\[/gi,"");
		// body = body.replace(/\]\]>/gi,"");
		var locationarray = [];
		xml2js_parser.parseString(body, function(err, result) {

			var size = result.rss.channel[0].item.length ;
			for(var index =0;index<size;index++)
			{
				// console.log(index + '번째 ##########' + result.rss.channel[0].item[index].title);

			var obj = {};
			var description = result.rss.channel[0].item[index].description;

			var qwert= striptags(description[0]);
			var img = striptags(description[0],'<img>');


			obj.description = qwert;
			obj.title = result.rss.channel[0].item[index].title;
			obj.category = result.rss.channel[0].item[index].category;

			/* 사진짜르기 */
			var img_split = img.split('src="');
			var img_real =[];

			for(var i = 1; i<img_split.length; i++){
				img_real.push(img_split[i].split('.jpg"')[0]+'.jpg');
			}
			obj.photo = img_real;  // /* 사진짜르기 */


			/* 주소, 전화, url 뽑아내기 */
			var ad_yes = qwert.includes("Address");
			var tel_yes = qwert.includes("Tel");
			var url_yes = qwert.includes("HomePage URL");

			var real_ad = obj.title.toString();
			var real_tel, real_homepage;

			if(ad_yes){
				var tmp = qwert.split("Address : ");
				real_ad = tmp[1].split("&lt;br&gt;")[0];
			}
			if(tel_yes){
				var tmp = qwert.split("Tel : ");
				real_tel = tmp[1].split("&lt;br&gt;")[0];
			}
			if(url_yes){
				var tmp = qwert.split("HomePage URL : ");
				real_homepage = tmp[1].split("&lt;br&gt;")[0];
			}

			obj.address = real_ad;
			obj.tel = real_tel;
			obj.url = real_homepage;

			//위도,경도 구하기 - 비동기라서 명소 한개씩 몽고 디비에 저장해야 할듯...
	   	get(obj);
			/* 주소, 전화, url 뽑아내기 */
			locationarray.push(obj);
			}
			res.json(locationarray);
		});
	});
});


function get(obj){
	  	var getPositionUrl = "https://maps.googleapis.com/maps/api/geocode/json?address="+urlencode(obj.address);//이게 핵심!
	  	var options = {
	  		uri : getPositionUrl
	  		// encoding : 'binary'
	  	};

	  	request(options, function(err,response,body){
	  		if(err){
	  		   return console.error('err',err);
	  		}else{
	  			if(JSON.parse(body).results[0] == null){
	  				get(obj);//구글 api가 1초에 요청 개수 제한이 있다함...그래서 실패할 경우 무한 반복!
	  			}else{

	  				obj.lat = JSON.parse(body).results[0].geometry.location.lat; //위도
	  				obj.lng = JSON.parse(body).results[0].geometry.location.lng; //경도
	  				//console.log(obj);

	  				//console.log(obj);
	  				//몽고디비 save
	  				mongodb.saveLoca(obj,function(success){})
	  			}
	  		}
	  	});
}


module.exports = router;
