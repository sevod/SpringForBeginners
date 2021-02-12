package org.sevod.spring.rest.controller;

import org.sevod.spring.rest.entity.Employee;
import org.sevod.spring.rest.exception_handling.EmployeeIncorrectData;
import org.sevod.spring.rest.exception_handling.NoSuchEmployeeException;
import org.sevod.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees(){
        List<Employee> allEmployee = employeeService.getAllEmployees();
        return allEmployee;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id){
        Employee employee = employeeService.getEmployee(id);
        if (employee == null){
            throw  new NoSuchEmployeeException("There is no imployee with ID = " + id + " in Database");
        }
        return employee;
    }

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        return employee; //тут возвращается объект уже с id который приходит с БД
    }

    @PutMapping("/employees")
    public Employee updateEmployee (@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id){
        Employee employee = employeeService.getEmployee(id);
        if (employee == null)
            throw new NoSuchEmployeeException("There id no employee with ID = " + id + " in Database");

        employeeService.deleteEmployee(id);
        return "Employee with ID=" + id + " was deleted";
    }
}
