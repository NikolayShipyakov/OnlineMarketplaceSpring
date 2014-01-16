// загрузить список всех работиков
function getAllEmp() {
	// отдельная функция на то, что делать после загрузки если загрузка
	// асинхронная
	var func = function() {
		showPeople();
	};
	getAllEmp_remote(func);
	newEmp();
}

// сохранить (добавить/изменить) работника
function saveEmp() {
	getManFromForm();
	var m = man;
	// отдельная функция на то, что делать после добавления если оно
	// асинхронное
	var func = function(id) {
		m.id = id;
		showPeople();
	};
	if (m.id == "") {
		people.push(m);
		newEmp();
	}
	saveEmp_remote(m, func);
	showPeople();
}

// удалить работника
function deleteEmp(m) {
	// we cant edit deleted element
	if (man == m) {
		newEmp();
	}
	deleteEmp_remote(m);
	people.splice(people.indexOf(m), 1);
	showPeople();
}

// разрываем связь с текущим редактируемым элементом
function newEmp() {
	man = {};
	getManFromForm();
	man.id = "";
	setManToForm();
}