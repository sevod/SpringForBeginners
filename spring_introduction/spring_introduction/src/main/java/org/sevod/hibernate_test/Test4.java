package org.sevod.hibernate_test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.sevod.hibernate_test.entity.Employee;

import java.util.List;

public class Test4 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

//            Employee employee = session.get(Employee.class, 2);
//            employee.setName("Sidr");
//            employee.setSurname("Sidorov");
//            employee.setSalary(1000);

            session.createQuery("update Employee set salary=1000 where name = 'Elena' ").executeUpdate();

            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}