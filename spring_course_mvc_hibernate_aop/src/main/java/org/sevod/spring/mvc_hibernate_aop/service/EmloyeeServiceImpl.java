package org.sevod.spring.mvc_hibernate_aop.service;

import org.sevod.spring.mvc_hibernate_aop.dao.EmployeeDAOImpl;
import org.sevod.spring.mvc_hibernate_aop.dao.EmpolyeeDAO;
import org.sevod.spring.mvc_hibernate_aop.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmloyeeServiceImpl implements EmployeeService {

    @Autowired
    private EmpolyeeDAO empolyeeDAO;

    @Override
    @Transactional
    public void deleteEmployee(int id) {
        empolyeeDAO.deleteEmployee(id);
    }

    @Override
    @Transactional
    public Employee getEmployee(int id) {
        return empolyeeDAO.getEmployee(id);
    }

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        return empolyeeDAO.getAllEmployees();
    }

    @Override
    @Transactional
    public void saveEmployee(Employee employee) {
        empolyeeDAO.saveEmployee(employee);
    }
}
