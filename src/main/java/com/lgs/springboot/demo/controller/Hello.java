package com.lgs.springboot.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Hello {
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    @ResponseBody
    public String SayHello(){

        return "Hello,SpringBoot!";
    }

}
