package org.sevod.spring.rest.dao;



import org.sevod.spring.rest.entity.Employee;

import java.util.List;

public interface EmpolyeeDAO {
    public List<Employee> getAllEmployees();

    public void saveEmployee(Employee employee);

    public Employee getEmployee(int id);

    public void deleteEmployee(int id);
}
