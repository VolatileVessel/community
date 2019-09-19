package com.lgs.springboot.demo.controller;

import com.lgs.springboot.demo.domain.City;
import com.lgs.springboot.demo.service.CityService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 城市Controller实现restFul HTTP接口
 *
 */
@RestController
public class CityController {
    @Autowired
    private CityService cityService;
   /** 查询所有的City
   */

    @RequestMapping(value = "/api/city",method = RequestMethod.GET)
    public List<City> findAllCity(){
        return cityService.findAllCity();
    }
    @RequestMapping(value = "/api/city/{id}",method = RequestMethod.GET)
    public City findById(@PathVariable("id") Long id){
        return cityService.findById(id);
    }

}
