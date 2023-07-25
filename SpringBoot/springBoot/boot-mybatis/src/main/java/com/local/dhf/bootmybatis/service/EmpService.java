package com.local.dhf.bootmybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.local.dhf.bootmybatis.mapper.EmpMapper;
import com.local.dhf.bootmybatis.pojo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 党
 * @version 1.0
 * 2022/6/30   15:19
 */
@Service
public class EmpService extends ServiceImpl<EmpMapper,Emp> implements IService<Emp> {

}
