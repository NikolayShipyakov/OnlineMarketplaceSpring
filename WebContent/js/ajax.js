//файл, описывающий ajax запросы к серверу

function getXmlHttp() {
	var xmlhttp;
	try {
		xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
	} catch (e) {
		try {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (E) {
			xmlhttp = false;
		}
	}
	if (!xmlhttp) {
		if (typeof XMLHttpRequest != 'undefined') {
			xmlhttp = new XMLHttpRequest();
		}
	}
	return xmlhttp;
}

function sendAjaxRequest(method, func, json) {
	var url = "json.servlet?timeStamp=" + new Date().getTime();
	request = getXmlHttp();
	request.open(method, url, true);
	request.onreadystatechange = onResponse(func);
	request.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	request.send(json);
}

function getAllEmp_remote(f) {
	func = function(json) {
		people = JSON.parse(json);
		f();
	};
	sendAjaxRequest("GET", func);
}

function saveEmp_remote(man, f) {
	man.index = undefined;
	var json = JSON.stringify(man);
	var func = function(json) {
		f(JSON.parse(json));
	};
	sendAjaxRequest("POST", func, json);
}

function deleteEmp_remote(man, f) {
	var json = JSON.stringify(man.id);
	var func = function(json) {
		// nothing to do
	};
	sendAjaxRequest("DELETE", func, json);
}

function onResponse(func) {
	return function() {
		if (request.readyState == 4) {
			if (request.status == 200) {
				func(request.responseText);
			} else {
				alert("request.status: " + request.status);
			}
		}
	};
}

