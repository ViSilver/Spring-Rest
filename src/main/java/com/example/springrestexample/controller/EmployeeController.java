package com.example.springrestexample.controller;

import com.example.springrestexample.dto.EmployeeAddressDto;
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
@SessionAttributes(types = {Address.class, Employee.class, EmployeeAddressDto.class})
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
        EmployeeAddressDto employeeAddressDto = new EmployeeAddressDto(employee);
        employeeAddressDto.setCity(employee.getAddress().getCity());
        employeeAddressDto.setStreet(employee.getAddress().getStreet());
        employeeAddressDto.setNumber(employee.getAddress().getNumber());
        model.addAttribute(employeeAddressDto);

        return forUpdate ? "employee-edit" : "employee-details";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getNewEmployee(Model model) {
        model.addAttribute(new EmployeeAddressDto());
        return "employee-edit";
    }

    @RequestMapping(params = "create", method = RequestMethod.POST)
    public String createEmployee(@Valid EmployeeAddressDto employeeAddressDto,
                                 BindingResult result,
                                 SessionStatus status,
                                 RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "employee-edit";
        }

        Employee savedEmployee = parseFromDto(employeeAddressDto, false);

        status.setComplete();

        redirectAttributes.addFlashAttribute("msg",
                String.format("Employee '%s' with address '%s' added successfully",
                        savedEmployee,
                        savedEmployee.getAddress()));

        return "redirect:employees/" + savedEmployee.getId();
    }

    @RequestMapping(params = "update", method = RequestMethod.POST)
    public String updateEmployee(@Valid EmployeeAddressDto employeeAddressDto,
                                 BindingResult result,
                                 SessionStatus status,
                                 RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "employee-edit";
        }

        Employee savedEmployee = parseFromDto(employeeAddressDto, true);

        status.setComplete();

        redirectAttributes.addFlashAttribute("msg",
                String.format("Employee '%s' with address '%s' updated successfully",
                        savedEmployee,
                        savedEmployee.getAddress()));

        return "redirect:employees/" + savedEmployee.getId();
    }

    private Employee parseFromDto(EmployeeAddressDto employeeAddressDto, boolean isForUpdate) {

        Address savedAddress = null;
        Employee employee = null;

        if (isForUpdate) {
            employee = employeeRepository.findById(employeeAddressDto.getEmployeeId());
        } else {
            employee = new Employee();
        }

        employee.setFirstName(employeeAddressDto.getFirstName());
        employee.setLastName(employeeAddressDto.getLastName());

        if (!(employeeAddressDto.getCity().equals("")
                && employeeAddressDto.getStreet().equals("")
                && employeeAddressDto.getNumber().equals(""))) {

            Address address = new Address();
            address.setCity(employeeAddressDto.getCity());
            address.setStreet(employeeAddressDto.getStreet());
            address.setNumber(employeeAddressDto.getNumber());

            savedAddress = addressRepository.save(address);
            employee.setAddress(savedAddress);
        }

        return employeeRepository.save(employee);
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

    @RequestMapping(params = "delete", method = RequestMethod.POST)
    public String deleteEmployee(@ModelAttribute("employeeAddressDto") EmployeeAddressDto employeeAddressDto,
                                 SessionStatus status,
                                 RedirectAttributes redirectAttributes) {

        Employee employee = employeeRepository.findById(employeeAddressDto.getEmployeeId());
        Address address = employee.getAddress();

        employeeRepository.delete(employee);

        if (address != null) {
            addressRepository.delete(address);
        }

        status.setComplete();

        redirectAttributes.addFlashAttribute("msg", String.format("Employee '%s' removed successfully",
                employee.toString()));

        return "redirect:employees/";
    }
}
