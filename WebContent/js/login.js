//Controller
// Sign button listener
var sign = function(){
    var userInfo = getLoginPassword();
    var errors = User.checkUserData(userInfo);
    if(errors.length == 0){
        auth(userInfo);
        //User.setUserLogin(userInfo.login);
        window.location = "/showItems.spring";
        //submit();
    } else {
        showErrorAuth(errors);
    }
};

var submit = function (){
    document.getElementById("loginForm").submit();
};

var auth = function (userInfo) {
    var func = function(json) {
        if(json){
            // To show All Items
        } else {
           var errors = ["Error login or password"];
           showErrorAuth(errors);
        }
    };
    sendAjaxRequest("/login.spring?", "POST", func,JSON.stringify(userInfo));
};

//View
// Getting authorisation data
var getLoginPassword = function(){
    var login = document.getElementById("login").value;
    var pass = document.getElementById("password").value;
    return {
        login:login,
        password:pass
    }
};

// Show error messages
var showErrorAuth = function(errors){
    var warningText = "";
    for(var i = 0; i < errors.length; i++){
        warningText += errors[i]+"</br>";
    }
    document.getElementById("details").innerHTML = warningText;
};

