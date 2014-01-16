// файл, описывающий связь представления с моделью
var man = {
	"id" : "",
	"firstName" : "",
	"lastName" : "",
	"email" : ""
};

// метод обновления, загружает шаблон
function showPeople() {
	$.get('template/emptable.tmpl', {}, function(temp) {
		// переопределяем метод, чтобы следующий вызов использовал уже готовый шаблон
		showPeople = function() {
			_showPeople(temp);
		};
		// вызываем обновление представления по шаблону
		showPeople();
	}, 'text');
	// запрещаем повторный вызов метода до загрузки шаблона
	showPeople = function() {
	};
};

// метод обновления после загрузки шаблона
// с символа _ начинаются приватные методы, т.е. так мы сообщаем пользователю, 
// что вызывать этот метод не следует
function _showPeople(emp_table_template) {
	var view = {};
	view.people = people;
	for ( var i = 0; i < people.length; i++) {
		people[i].index = i;
	}
	var output = Mustache.render(emp_table_template, view);
	$('#empTable').html(output);
}

function deleteMust(index) {
	deleteEmp(people[index]);
}

function editMust(index) {
	man = people[index];
	setManToForm();
}

function getManFromForm() {
	$("#manForm>input").each(function() {
		man[this.name] = $(this).val();
	});
}

function setManToForm() {
	$("#manForm>input").each(function() {
		$(this).val(man[this.name]);
	});
}
