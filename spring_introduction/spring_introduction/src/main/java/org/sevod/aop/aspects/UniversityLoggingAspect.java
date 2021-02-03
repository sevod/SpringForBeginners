package org.sevod.aop.aspects;

import org.aspectj.lang.annotation.*;
import org.sevod.aop.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
public class UniversityLoggingAspect {

    @AfterThrowing(pointcut = "execution (* getStudents())", throwing = "exeption")
    public void afterThrowingGetStudentsAdvice(Throwable exeption){
        System.out.println("afterThrowingGetStudentsAdvice: логируем выброс исключения " + exeption);
    }

//    @Before("execution(* getStudents())")
//    public void beforeGetStudentsLoggingAdvice(){
//        System.out.println("beforeGetStudentsLoggingAdvice: логируем получение списка студентов перед методом getStudents");
//        System.out.println("------------------------------------------------------------------");
//    }
//
//    @AfterReturning(pointcut = "execution(* getStudents())", returning = "students")
//    public void afterReturningStudentsLoggingAdvice(List<Student> students){
//        Student firstStudent = students.get(0);
//        String nameSurname = firstStudent.getNameSurname();
//        nameSurname = "Mr. " + nameSurname;
//        firstStudent.setNameSurname(nameSurname);
//        double avgGrade = firstStudent.getAvgGrade();
//        avgGrade++;
//        firstStudent.setAvgGrade(avgGrade);
//
//        System.out.println("afterReturningStudentsLoggingAdvice: логируем получение списка студентов после метода getStudents");
//        System.out.println("------------------------------------------------------------------");
//    }


}
