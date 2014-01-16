// файл, описывающий связь представления с моделью

var man = {
	"id" : "",
	"firstName" : "",
	"lastName" : "",
	"email" : ""
};

function showPeople() {
	var template = document.getElementById('emp_table_template').innerHTML;
	var view = {};
	view.people = people;
	for ( var i = 0; i < people.length; i++) {
		people[i].index = i;
	}
	var output = Mustache.render(template, view);
	document.getElementById('empTable').innerHTML = output;
}

function deleteMust(index) {
	var m = people[index];
	deleteEmp(m);
}

function editMust(index) {
	man = people[index];
	setManToForm();
}

function getManFromForm() {
	var form = document.getElementById("manForm");
	man.id = form.id.value;
	man.firstName = form.firstName.value;
	man.lastName = form.lastName.value;
	man.email = form.email.value;
}

function setManToForm() {
	var form = document.getElementById("manForm");
	form.id.value = man.id;
	form.firstName.value = man.firstName;
	form.lastName.value = man.lastName;
	form.email.value = man.email;
}
