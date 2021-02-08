package org.sevod.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
//@RequestMapping("/employee")
public class MyController {
    @RequestMapping("/")
    public String showFirstView(){
        return "first-view";
    }

    @RequestMapping("/askDetails")
    public String askEmployeeDetails(Model model){
        Employee emp = new Employee();
        emp.setName("Ivan");
        emp.setSurname("Ivanov");
        emp.setSalary(1000);
        model.addAttribute("employee", emp);
        return "ask-emp-details-view";
    }

//    @RequestMapping("/showDetails")
//    public String showEmployeeDetails(){
//        return "show-emp-details-view";
//    }
//    @RequestMapping("/showDetails")
//    public String showEmployeeDetails(HttpServletRequest request, Model model){
//        String empName = request.getParameter("emloyeeName");
//        empName = "Mr. " + empName;
//
//        model.addAttribute("nameAttribute", empName);
//        model.addAttribute("myAttribute", "It's test!");
//
//        return "show-emp-details-view";
//    }

//    @RequestMapping("/showDetails")
//    public String showEmployeeDetails(@RequestParam("emloyeeName") String empName, Model model){
//        empName = "Mr. " + empName + "!";
//
//        model.addAttribute("nameAttribute", empName);
//        model.addAttribute("myAttribute", "It's test!");
//
//        return "show-emp-details-view";
//    }

    @RequestMapping("/showDetails")
    public String showEmployeeDetails(@ModelAttribute("employee") Employee emp){
        String name = emp.getName();
        emp.setName("Mr. " + name);

        return "show-emp-details-view";
    }
}
