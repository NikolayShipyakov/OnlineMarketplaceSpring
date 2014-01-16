package com.mkyong.common.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

@Controller
@SessionAttributes("a")
public class HelloController {

	@RequestMapping(value = "/welcome",method = RequestMethod.GET)
    @ResponseBody
	public String printWelcome(@RequestParam(value = "r", required = false) String r, @ModelAttribute(value = "a") String a, HttpSession httpSession,ModelMap model) {

		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "hello " + r + a;

	}

    @RequestMapping(value = "/session", method = RequestMethod.GET)
    public String home(HttpSession httpSession ) {
        httpSession.setAttribute("a", "Lalala" );
        return "home";
    }
	
}