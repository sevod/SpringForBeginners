package org.sevod.hibernate_test_2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.sevod.hibernate_test_2.entity.Detail;
import org.sevod.hibernate_test_2.entity.Employee;

public class Test2 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();
        try {
//Это создание detail and Empoyee

//            Employee employee = new Employee("Nikolay", "Ivanov", "HR", 500);
//            Detail detail = new Detail("New-York", "42978922", "Nikolay@gmail.com");
//            employee.setEmpDetail(detail);
//            detail.setEmployee(employee);
//            session.beginTransaction();
//            session.save(detail);
//            session.getTransaction().commit();

//Чтение данных

//            session.beginTransaction();
//            Detail detail = session.get(Detail.class, 4);
//            System.out.println(detail.getEmployee());
//            session.getTransaction().commit();

//Удаление данных

//            session.beginTransaction();
//            Detail detail = session.get(Detail.class, 4);
//            session.delete(detail);
//            session.getTransaction().commit();

//Удаление только detail
            session.beginTransaction();
            Detail detail = session.get(Detail.class, 1);
            detail.getEmployee().setEmpDetail(null); //зануляем ссылку на detail перед удалением
            session.delete(detail);
            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            session.close();
            factory.close();
        }
    }
}