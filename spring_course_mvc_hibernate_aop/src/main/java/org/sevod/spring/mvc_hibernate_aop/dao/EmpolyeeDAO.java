package org.sevod.spring.mvc_hibernate_aop.dao;

import org.sevod.spring.mvc_hibernate_aop.entity.Employee;

import java.util.List;

public interface EmpolyeeDAO {
    public List<Employee> getAllEmployees();

    public void saveEmployee(Employee employee);

    public Employee getEmployee(int id);
}
