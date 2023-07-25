package com.local.controller;

import com.local.dao.EmployeeDao;
import com.local.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.Collection;

/**
 * @author 党
 * @version 1.0
 * 2022/5/19   21:26
 */
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeDao employeeDao;

    @RequestMapping(
            value = "/employee",
            method = RequestMethod.GET
    )
    public ModelAndView getEmployeeList(){
        ModelAndView modelAndView = new ModelAndView();
        Collection<Employee> all = employeeDao.getAll();
        modelAndView.addObject("employeeList",all);
        modelAndView.setViewName("employee_list");
        return modelAndView;
    }
    @RequestMapping(
            value = "/employee/{id}",
            method = RequestMethod.DELETE
    )
    public String deleteById(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/employee";
    }
    @RequestMapping(
            value = "/employee",
            method = RequestMethod.POST
    )
    public String add(Employee e){//添加
        employeeDao.save(e);
        return "redirect:/employee";//get
    }
    @RequestMapping(
            value = "/employee/{id}",
            method = RequestMethod.GET)
    public ModelAndView getEmployeeById(@PathVariable("id") Integer id){
        Employee employee = employeeDao.get(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("employee", employee);
        modelAndView.setViewName("employee_update");
        return modelAndView;
    }
    @RequestMapping(
            value = "/employee",
            method = RequestMethod.PUT)
    public String updateEmployee(Employee employee){
        employeeDao.save(employee);
        return "redirect:/employee";
    }
}
