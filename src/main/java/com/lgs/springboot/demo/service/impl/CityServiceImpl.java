package com.lgs.springboot.demo.service.impl;

import com.lgs.springboot.demo.dao.CityDao;
import com.lgs.springboot.demo.domain.City;
import com.lgs.springboot.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityDao cityDao;

    @Override
    public List<City> findAllCity() {
        return cityDao.findAllCity();
    }

    @Override
    public City findById(Long id) {
        return cityDao.findById(id);
    }
}
