package com.codegym.cms.service;

import com.codegym.cms.model.Customer;
import com.codegym.cms.model.Province;

public interface CustomerService {
    Iterable<Customer> findAll();

    Iterable<Customer> findAllByProvince(Province province);

    Customer findById(Long id);

    void save (Customer customer);

    void remote(Long Id);
}
