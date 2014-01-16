//файл, описывающий ajax запросы к серверу

function getAjaxUrl() {
	return url = "json.servlet?timeStamp=" + new Date().getTime();
}

function getAllEmp_remote(f) {
	func = function(json) {
		people = json;// JSON.parse(json);
		f();
	};
	$.get(getAjaxUrl(), func);
}

function saveEmp_remote(man, f) {
	man.index = undefined;
	var json = JSON.stringify(man);
	var func = function(json) {
		f(JSON.parse(json));
	};
	$.ajax({
		headers : {
			'Content-Type' : 'application/json'
		},
		url : getAjaxUrl(),
		data : json,
		type : "POST",
		success : func
	});
	//$.post(getAjaxUrl(), json, func);
}

function deleteEmp_remote(man, f) {
	var json = JSON.stringify(man.id);
	var func = function(json) {
		// nothing to do
	};
	$.ajax({
		headers : {
			'Content-Type' : 'application/json'
		},
		url : getAjaxUrl(),
		data : json,
		type : "DELETE",
		success : func
	});
}