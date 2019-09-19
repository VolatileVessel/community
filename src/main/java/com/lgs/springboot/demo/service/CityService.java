package com.lgs.springboot.demo.service;

import com.lgs.springboot.demo.domain.City;

import java.util.List;

public interface CityService {
    /**
     * 获取城市信息列表
     *
     * @return
     */

    List<City> findAllCity();

    City findById(Long id);
}
