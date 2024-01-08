package teck.me.license.service;

import teck.me.license.model.dto.CustomerDto;
import teck.me.license.model.dto.ListCustomerDto;

import java.util.List;

public interface CustomerService {

    void deleteCustomer(String name);

    ListCustomerDto updateCustomer(String name, ListCustomerDto updatedCustomer);

    ListCustomerDto createCustomer(ListCustomerDto customerDto) throws IllegalAccessException;

    CustomerDto getCustomerById(String name) throws IllegalAccessException;

    List<ListCustomerDto> getAllCustomers(int page, int number);
}
