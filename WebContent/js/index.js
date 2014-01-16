// файл, описывающий связь представления с моделью

var man = {
	"id" : "",
	"firstName" : "",
	"lastName" : "",
	"email" : ""
};

function showPeople() {
	var tbody = document.getElementById("empTable");
	tbody.innerHTML = "";
	for ( var i = 0; i < people.length; i++) {
		var tr = document.createElement('TR');
		tbody.appendChild(tr);

		var m = people[i];
		var td = document.createElement('TD');
		tr.appendChild(td);
		td.innerHTML = m.id;
		td = document.createElement('TD');
		tr.appendChild(td);
		td.innerHTML = m.firstName;
		td = document.createElement('TD');
		tr.appendChild(td);
		td.innerHTML = m.lastName;
		td = document.createElement('TD');
		tr.appendChild(td);
		td.innerHTML = m.email;
		td = document.createElement('TD');
		tr.appendChild(td);
		var del = document.createElement('img');
		del.src = "delete.png";
		del.width = 32;
		var local = function(m) {
			del.onclick = function() {
				deleteEmp(m);
			};
		};
		local(m);

		td.appendChild(del);
		var upd = document.createElement('img');
		upd.src = "edit.png";
		upd.width = 32;
		local = function(m) {
			upd.onclick = function() {
				man = m;
				setManToForm();
			};
		};
		local(m);
		td.appendChild(upd);
	}
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
