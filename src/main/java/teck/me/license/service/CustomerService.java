package teck.me.license.service;

import teck.me.license.model.dto.CreateCustomerDto;
import teck.me.license.model.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    void deleteCustomer(long id);

    CustomerDto updateCustomer(long id, CustomerDto updatedCustomer);

    CreateCustomerDto createCustomer(CreateCustomerDto customerDto);

    CustomerDto getCustomerById(long id);

    List<CustomerDto> getAllCustomers();
}
