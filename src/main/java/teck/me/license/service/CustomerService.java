package teck.me.license.service;

import teck.me.license.model.dto.CreateCustomerDto;
import teck.me.license.model.dto.CustomerDto;
import teck.me.license.model.dto.ListCustomerDto;

import java.util.List;

public interface CustomerService {

    void deleteCustomer(String name);

    CreateCustomerDto updateCustomer(String name, CreateCustomerDto updatedCustomer);

    CreateCustomerDto createCustomer(CreateCustomerDto customerDto);

    CustomerDto getCustomerById(String name) throws IllegalAccessException;

    List<ListCustomerDto> getAllCustomers(int page, int number);
}
