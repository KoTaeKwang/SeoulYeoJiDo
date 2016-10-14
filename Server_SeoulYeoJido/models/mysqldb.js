var mysql = require('mysql');

var pool = mysql.createPool({
	connectionLimit : 100,
	host : 'localhost',
	user : 'seoulyeojido',
	password : '1234',
	database : 'seoulyeojido'
});

module.exports = pool;