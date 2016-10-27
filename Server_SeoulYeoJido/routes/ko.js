//태광만 작성 가능 다른 사람은 눈팅 !
var express = require('express');
var router = express.Router();
var mongodb = require('../models/mongodb');
/* GET users listing. */
router.get('/', function(req, res, next) {
  res.send('respond with a resource');
});


router.get('/showloca',function(req,res,next){
	mongodb.showLoca(function(success){
		res.json(success);
	})
});

router.post('/searchLocaName',function(req,res,next){
		var searchtext = req.body.searchtext;
			mongodb.searchLocaName(searchtext,function(success){
					res.json(success);
			})

})

router.post('/searchLocaAddress',function(req,res,next){
		var searchtext = req.body.searchtext;
			mongodb.searchLocaAddress(searchtext,function(success){
					res.json(success);
			})
})

router.post('/showGuLoca',function(req,res,next){
	var guNum = req.body.guNum;
	mongodb.showGuLoca(guNum,function(success){
		res.json(success);
	})
})

router.post('/showCategoryLoca',function(req,res,next){
	var categoryNum = req.body.categoryNum;
	mongodb.showCategoryLoca(categoryNum,function(success){
		res.json(success);
	})
})


router.post('/showDetailLoca',function(req,res,next){
	var loca_name = req.body.loca_name;
	mongodb.showDetailLoca(loca_name,function(success){
		res.json(success);
	})
})

router.post('/checkin',function(req,res,next){

	var data={};
	data.loca_name = req.body.loca_name;
	data.loca_lat = req.body.loca_lat;
	data.loca_lon = req.body.loca_lon;

	mongodb.checkin(data,function(success){
		res.json(success);
	})
})

router.post('/addReview',function(req,res,next){

	var data = {};
	data.loca_name = req.body.loca_name;
	data.user_id = req.body.user_id;
	data.content = req.body.content;
	data.date = req.body.date;

	mongodb.addReview(data,function(success){
		res.json(success);
	})
})
/*
router.post('/addCheckin',function(req,res,next){
	var data = {};
	data.loca_name = req.body.loca_name;
	data.user_id = req.body.user_id;
	data.date = req.body.date;

	mongodb.addCheckin(data,function(success){
		res.json(success);
	})
})*/

router.post('/showCheckin',function(req,res,next){
	var user_id = req.body.user_id;
	mongodb.showCheckin(user_id,function(success){
		res.json(success);
	})
});
//mongoexcel
router.get('/mongoexcel',function(req,res,next){
	mongodb.temp(function(success){
		res.json(success);
	})
})

router.get('/removeLocation',function(req,res,next){
	mongodb.removeLocation(function(success){
		res.json(success);
	})
})

router.post('/updatepicture',function(req,res,next){
	var title = req.body.title;
	mongodb.updatepicture(title,function(success){
		res.json(success);
	})
});
module.exports = router;
