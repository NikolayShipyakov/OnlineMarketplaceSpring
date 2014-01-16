// ����, ����������� ����� ������������� � �������
var man = {
	"id" : "",
	"firstName" : "",
	"lastName" : "",
	"email" : ""
};

// ����� ����������, ��������� ������
function showPeople() {
	$.get('template/emptable.tmpl', {}, function(temp) {
		// �������������� �����, ����� ��������� ����� ����������� ��� ������� ������
		showPeople = function() {
			_showPeople(temp);
		};
		// �������� ���������� ������������� �� �������
		showPeople();
	}, 'text');
	// ��������� ��������� ����� ������ �� �������� �������
	showPeople = function() {
	};
};

// ����� ���������� ����� �������� �������
// � ������� _ ���������� ��������� ������, �.�. ��� �� �������� ������������, 
// ��� �������� ���� ����� �� �������
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
