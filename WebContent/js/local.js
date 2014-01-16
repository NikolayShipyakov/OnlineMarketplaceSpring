// ��������� ������ ���� ���������
function getAllEmp() {
	// ��������� ������� �� ��, ��� ������ ����� �������� ���� ��������
	// �����������
	var func = function() {
		showPeople();
	};
	getAllEmp_remote(func);
	newEmp();
}

// ��������� (��������/��������) ���������
function saveEmp() {
	getManFromForm();
	var m = man;
	// ��������� ������� �� ��, ��� ������ ����� ���������� ���� ���
	// �����������
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

// ������� ���������
function deleteEmp(m) {
	// we cant edit deleted element
	if (man == m) {
		newEmp();
	}
	deleteEmp_remote(m);
	people.splice(people.indexOf(m), 1);
	showPeople();
}

// ��������� ����� � ������� ������������� ���������
function newEmp() {
	man = {};
	getManFromForm();
	man.id = "";
	setManToForm();
}