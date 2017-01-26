package com.example.springrestexample.controller;

import com.example.springrestexample.entity.Address;
import com.example.springrestexample.entity.Employee;
import com.example.springrestexample.repository.AddressRepository;
import com.example.springrestexample.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/employees")
@SessionAttributes(types = {Employee.class, Address.class})
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAll() {
        List<Employee> employees = employeeRepository.findAll();
        return new ModelAndView("employee-list", "employees", employees);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getEmployee(@PathVariable("id") Integer id,
                              @RequestParam(value = "for-update", defaultValue = "false") boolean forUpdate,
                              Model model) {

        Employee employee = employeeRepository.findById(id);
        model.addAttribute(employee);
        return forUpdate ? "employee-edit" : "employee-details";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getNewEmployee(Model model) {
        model.addAttribute(new Employee());
        return "employee-edit";
    }

    @RequestMapping(params = "create", method = RequestMethod.POST)
    public String createEmployee(@Valid Employee employee,
                                 BindingResult result,
                                 SessionStatus status,
                                 RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "employee-edit";
        }

        Employee savedEmployee = employeeRepository.save(employee);
        status.setComplete();

        redirectAttributes.addFlashAttribute("msg",
                String.format("Employee '%s' added successfully", employee));

        return "redirect:employees/" + savedEmployee.getId();
    }

    @RequestMapping(value = "/{id}/addresses")
    public String getEmployeeAddresses(@PathVariable("id") Integer id,
                                       Model model) {

        Employee employee = employeeRepository.findById(id);
        model.addAttribute(employee.getAddress());
        return "address-list";
    }

    @RequestMapping(value = "/{id}/addresses/new", method = RequestMethod.GET)
    public String getNewAddressForEmployee(Model model) {
        model.addAttribute(new Address());
        return "address-edit";
    }
}
