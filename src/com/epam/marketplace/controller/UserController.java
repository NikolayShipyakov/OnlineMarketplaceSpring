package com.epam.marketplace.controller;

import com.epam.marketplace.beans.UserTransfer;
import com.epam.marketplace.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @RequestMapping(value = "/login.spring", method = RequestMethod.POST)
    @ResponseBody
    public Boolean auhtorisation(@RequestBody UserTransfer user, HttpSession httpSession) {
        //Вызов ДАО и проверка наличия такого юзера
        // Ниже заглушка
        UserTransfer userTransfer = new UserTransfer(user.getLogin(), user.getPassword());
        httpSession.setAttribute(Constants.USER_TRANSFER, userTransfer);
        return true;
    }
}
