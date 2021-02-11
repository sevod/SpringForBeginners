package org.sevod.spring.rest.service;


import org.sevod.spring.rest.dao.EmpolyeeDAO;
import org.sevod.spring.rest.entity.Employee;
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
