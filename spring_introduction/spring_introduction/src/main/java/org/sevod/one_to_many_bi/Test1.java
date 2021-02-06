package org.sevod.one_to_many_bi;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.sevod.one_to_many_bi.entity.Department;
import org.sevod.one_to_many_bi.entity.Employee;

import javax.swing.text.html.parser.Entity;

public class Test1 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class)
                .buildSessionFactory();

        Session session = null;
        try {

//            session = factory.getCurrentSession();
//            Department dep = new Department("Sails", 1500, 300);
//            Employee emp1 = new Employee("Zaur", "Tregulov", 800);
//            Employee emp2 = new Employee("Elena", "Smirnova", 1500);
//            Employee emp3 = new Employee("Anton", "Sidorov", 1200);
//            dep.addEmployeeToDepartment(emp1);
//            dep.addEmployeeToDepartment(emp2);
//            dep.addEmployeeToDepartment(emp3);
//            session.beginTransaction();
//            session.save(dep);
//            session.getTransaction().commit();
//
            session = factory.getCurrentSession();
            session.beginTransaction();
            Department department = session.get(Department.class, 1);
            System.out.println(department);
            System.out.println(department.getEmps());

//            Employee employee = session.get(Employee.class, 1);
//            System.out.println(employee);
//            System.out.println(employee.getDepartment());

//            Employee employee = session.get(Employee.class, 4);
//            session.delete(employee);

            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            session.close();
            factory.close();
        }
    }
}