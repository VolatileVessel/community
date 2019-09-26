package com.lgs.springboot.demo.controller;

import com.lgs.springboot.demo.domain.City;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Hello {
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    //@ResponseBody
    public String SayHello(City city, HttpServletRequest request){
		System.out.println("hello word11");
		System.out.println("hello word2");
		city.setId(1L);
		city.setCityName("湖北");
		city.setDescription("湖北描述");
		city.setProvinceId(11L);
        request.setAttribute(city.getCityName(),"cityName");

        return "index";
    }

}
