package org.sevod.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyController {
    @RequestMapping("/")
    public String showFirstView(){
        return "first-view";
    }

    @RequestMapping("/askDetails")
    public String askEmployeeDetails(){
        return "ask-emp-details-view";
    }

//    @RequestMapping("/showDetails")
//    public String showEmployeeDetails(){
//        return "show-emp-details-view";
//    }
    @RequestMapping("/showDetails")
    public String showEmployeeDetails(HttpServletRequest request, Model model){
        String empName = request.getParameter("emloyeeName");
        empName = "Mr. " + empName;

        model.addAttribute("nameAttribute", empName);
        model.addAttribute("myAttribute", "It's test!");


        return "show-emp-details-view";
    }
}
