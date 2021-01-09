package com.denisyevseyenko.spring.mvc_hibernate_aop.controller;

import com.denisyevseyenko.spring.mvc_hibernate_aop.dao.EmployeeDAO;
import com.denisyevseyenko.spring.mvc_hibernate_aop.entity.Employee;
import com.denisyevseyenko.spring.mvc_hibernate_aop.service.EmployeeService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import java.util.List;

@Controller
public class MyController
{

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/")
    public String showAllEmployees(Model model)
    {
        List<Employee> allEmployees = employeeService.getAllEmployees();

        model.addAttribute("allEmps",allEmployees);

        return "all-employees";
    }

    @RequestMapping("/addNewEmployee")
    public String addNewEmployee(Model model)
    {
        Employee employee = new Employee();

        model.addAttribute("employee",employee);

        return "employee-info";
    }

    ///when we press button "OK"
    @RequestMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee)
    {
        employeeService.saveEmployee(employee);

        return "redirect:/";
    }

    @RequestMapping("/updateInfo")
    public String updateEmployee(@RequestParam("empId") int id,Model model)
    {
        Employee employee = employeeService.getEmployee(id);

        model.addAttribute("employee",employee);

        return "employee-info";
    }

    @RequestMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam("empId") int id)  //@RequestParam - служит для получения элемента из view
    {
        Employee employee = employeeService.getEmployee(id);

        employeeService.deleteEmployee(employee);

        return "redirect:/";
    }



}
