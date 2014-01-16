//
var sendAjaxRequest = function(url,method, func,json) {
    $.ajax({
        type: method,
        dataType: 'json',
        url: url,
        contentType : "application/json; charset=utf-8",
        data : json,

        success: function (request) {
            func(request);
        }
    });
};