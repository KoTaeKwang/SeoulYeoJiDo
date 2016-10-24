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

module.exports = router;
