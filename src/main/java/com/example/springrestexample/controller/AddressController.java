package com.example.springrestexample.controller;

import com.example.springrestexample.entity.Address;
import com.example.springrestexample.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/addresses")
@SessionAttributes(types = Address.class)
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setAllowedFields("city", "street", "number");
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return new ModelAndView("address-list", "addresses", addresses);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getAddress(@PathVariable("id") Integer id,
                             @RequestParam(value = "for-update", defaultValue = "false") boolean forUpdate,
                             Model model) {

        Address address = addressRepository.findById(id);
        model.addAttribute(address);
        return forUpdate ? "address-edit" : "address-details";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getNewAddress(Model model) {
        model.addAttribute(new Address());
        return "address-edit";
    }

    @RequestMapping(params = "create", method = RequestMethod.POST)
    public String createAddress(@Valid Address address,
                                BindingResult result,
                                SessionStatus status,
                                RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "address-edit";
        }

        Address savedAddress = addressRepository.save(address);
        status.setComplete();

        redirectAttributes.addFlashAttribute("msg", String.format("Address '%s' added successfully", address.toString()));

        return "redirect:addresses/" + savedAddress.getId();
    }

    @RequestMapping(params = "update", method = RequestMethod.POST)
    public String updateAddress(@Valid Address address,
                                BindingResult result,
                                SessionStatus status,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "address-edit";
        }

        Address savedAddress = addressRepository.save(address);
        status.setComplete();

        redirectAttributes.addFlashAttribute("msg", String.format("Address '%s' updated successfully", address.toString()));

        return "redirect:addresses/" + savedAddress.getId();
    }

    @RequestMapping(params = "delete", method = RequestMethod.DELETE)
    public String deleteAddress(@ModelAttribute("address") Address address,
                                SessionStatus status,
                                RedirectAttributes redirectAttributes) {

        addressRepository.delete(address);
        status.setComplete();

        redirectAttributes.addFlashAttribute("msg", String.format("Address '%s' removed successfully", address.toString()));

        return "redirect:addresses/";
    }
}
