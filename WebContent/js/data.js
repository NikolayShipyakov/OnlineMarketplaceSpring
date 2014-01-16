// файл с локольными данными и методы работы с ними

var people = [ {
	"id" : "0",
	"firstName" : "Miroshin",
	"lastName" : "Vasya",
	"email" : "Director"

}, {
	"id" : "1",
	"firstName" : "Lapshin",
	"lastName" : "Ilya",
	"email" : "Programmer"

}, {
	"id" : "2",
	"firstName" : "Alexey",
	"lastName" : "Kalinin",
	"email" : "Manager"

} ];

// local index for unique id
var people_id = people.length;

function getAllEmp_remote(func) {
	func();
}

function saveEmp_remote(m, func) {
	var id = m.id;
	if (id == "") {
		id = people_id++;
	}
	func(id);
}

function deleteEmp_remote(m) {
}
