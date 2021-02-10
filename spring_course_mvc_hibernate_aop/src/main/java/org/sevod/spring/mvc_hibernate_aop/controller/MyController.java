package org.sevod.spring.mvc_hibernate_aop.controller;

import org.sevod.spring.mvc_hibernate_aop.dao.EmpolyeeDAO;
import org.sevod.spring.mvc_hibernate_aop.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MyController {

    @Autowired
    private EmpolyeeDAO empolyeeDAO;

    @RequestMapping("/")
    public String showAllEmployees(Model model){
        List<Employee> allEmployees = empolyeeDAO.getAllEmployees();
        model.addAttribute("allEmps", allEmployees);
        return "all-employess";
    }
}
