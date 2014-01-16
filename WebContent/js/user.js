// User info
var User = {
    getUserLogin : function(){
        return sessionStorage["login"];
    },

    setUserLogin : function(login){
        sessionStorage["login"] = login;
    },

    // Check user login / password
    checkUserData : function(userData){
        var login = userData.login;
        var password = userData.password;
        var errors = [];
        var notNullLogin = (login.length >= Constants.loginLength) ? true : false;
        var notNullPassword = (password.length >= Constants.passwordLength) ? true : false;
        if(!notNullLogin){
            errors.push("The minimum length of Login is"+ Constants.loginLength);
        }
        if(!notNullPassword){
            errors.push("The minimum length of Password is "+ Constants.passwordLength);
        }
        return errors;
    },

    // Check user data
    valideUserData : function(userData){
        var name = userData.name;
        var adress = userData.adress;
        var phone = userData.phone;
        var login = userData.login;
        var password = userData.password;
        var repassword = userData.repassword;
        var errors = [];
        if ((name == "") || (adress == "") || (phone == "") || (login == "")
            || (password == "") || (repassword == "")) {
            errors.push(Constants.emptysFields);
        }
        if ((name == login) && (name != "")) {
            errors.push(Constants.concideNameLogin);
        }
        if (password != repassword) {
            errors.push(Constants.noRepeartPassword);
        }
        if (password.length < Constants.passwordLength) {
            errors.push(Constants.minimumLengthOfPassword);
        }
        var reg = new RegExp(Constants.regPhone);
        if (!reg.test(phone)) {
            errors.push(Constants.formatPhone);
        }

        return errors;
    }

};