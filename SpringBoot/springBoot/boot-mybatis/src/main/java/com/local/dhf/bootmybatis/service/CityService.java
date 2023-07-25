package com.local.dhf.bootmybatis.service;

import com.local.dhf.bootmybatis.mapper.CityMapper;
import com.local.dhf.bootmybatis.pojo.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 党
 * @version 1.0
 * 2022/6/30   13:24
 */
@Service
public class CityService {
    @Autowired
    CityMapper cityMapper;
    public City getCityById(Integer id){
        return cityMapper.getCityById(id);
    }
}
