package com.codegym.cms.controller;

import com.codegym.cms.model.Customer;
import com.codegym.cms.model.Province;
import com.codegym.cms.service.CustomerService;
import com.codegym.cms.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProvinceController {
    //    Province
    @Autowired
    ProvinceService provinceService;
    @Autowired
    CustomerService customerService;

    @GetMapping("/provinces")
    public ModelAndView listProvince() {
        Iterable<Province> provinces = provinceService.findAll();
        ModelAndView modelAndView = new ModelAndView("province/list");
        modelAndView.addObject("provinces", provinces);
        return modelAndView;
    }

    @GetMapping("/create-province")
    public ModelAndView showCreateProvince() {
        ModelAndView modelAndView = new ModelAndView("province/create");
        modelAndView.addObject("province", new Province());
        return modelAndView;
    }

    @PostMapping("/create-province")
    public ModelAndView createProvince(@ModelAttribute("province") Province province) {
        provinceService.save(province);
        ModelAndView modelAndView = new ModelAndView("province/create");
        modelAndView.addObject("province", new Province());
        modelAndView.addObject("message", "New Province create successfully");
        return modelAndView;
    }

    @GetMapping("/edit-province/{id}")
    public ModelAndView showEditProvince(@PathVariable Long id) {
        Province province = provinceService.findById(id);
        if (province != null) {
            ModelAndView modelAndView = new ModelAndView("province/edit");
            modelAndView.addObject("province", province);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-province")
    public ModelAndView editProvince(@ModelAttribute("province") Province province) {
        provinceService.save(province);
        ModelAndView modelAndView = new ModelAndView("province/edit");
        modelAndView.addObject("province", province);
        modelAndView.addObject("message", "Province update successfully");
        return modelAndView;
    }

    @GetMapping("/delete-province/{id}")
    public ModelAndView showDeleteProvince(@PathVariable Long id) {
        Province province = provinceService.findById(id);
        if (province != null) {
            ModelAndView modelAndView = new ModelAndView("province/delete");
            modelAndView.addObject("province", province);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-province")
    public String deleteProvince(@ModelAttribute("province") Province province) {
        provinceService.remove(province.getId());
        return "redirect:provinces";
    }

    @GetMapping("/view-province/{id}")
    public ModelAndView viewProvince(@PathVariable Long id){
        Province province=provinceService.findById(id);
        if (province!=null){
            Iterable<Customer> customers=customerService.findAllByProvince(province);
            ModelAndView modelAndView=new ModelAndView("province/view");
            modelAndView.addObject("customer",customers);
            modelAndView.addObject("province",province);
            return modelAndView;
        }else {
            ModelAndView modelAndView= new ModelAndView("error.404");
            return modelAndView;
        }
    }
}

