package org.sevod.spring.rest;

import org.sevod.spring.rest.configuration.MyConfig;
import org.sevod.spring.rest.entity.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = context.getBean("communication", Communication.class);
//        List<Employee> allEmployees = communication.getAllEmployees();
//        System.out.println(allEmployees);

//        Employee empById = communication.getEmloyee(1);
//        System.out.println(empById);
//        Employee employee = new Employee("Sveta", "Sokolova", "HR", "900");
//        communication.saveEmployee(employee);

//        Employee employee = new Employee("Sveta", "Sokolova", "IT", "1500");
//        employee.setId(12);
//        communication.saveEmployee(employee);

        communication.deleteEmployee(12);
    }
}
