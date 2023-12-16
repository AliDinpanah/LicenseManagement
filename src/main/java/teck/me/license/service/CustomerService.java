package teck.me.license.service;

import teck.me.license.model.dto.CustomerDto;
import teck.me.license.model.dto.ListCustomerDto;

import java.util.List;

public interface CustomerService {

    void deleteCustomer(long id);

    CustomerDto updateCustomer(long id, CustomerDto updatedCustomer);

    CustomerDto createCustomer(CustomerDto customerDto);

    ListCustomerDto getCustomerById(long id);

    List<ListCustomerDto> getAllCustomers(int page, int number);
}
