package teck.me.license.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teck.me.license.model.Customer;
import teck.me.license.model.dto.CreateCustomerDto;
import teck.me.license.model.dto.CustomerDto;
import teck.me.license.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImp {
    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers=customerRepository.findAll();
        List<CustomerDto> customerDtos= new ArrayList<>();
        for (Customer customer:customers){
            customerDtos.add(new CustomerDto(customer));
        }
        return customerDtos;
    }

    public CustomerDto getCustomerById(long id) {
        return new CustomerDto(customerRepository.findById(id).get());
    }

    public CreateCustomerDto createCustomer(CreateCustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customerRepository.save(customer);
        return customerDto;
    }

    public CustomerDto updateCustomer(long id, CustomerDto updatedCustomer) {
        Customer existingCustomer = customerRepository.findById(id).get();
        if (existingCustomer != null) {
            existingCustomer.setName(updatedCustomer.getName());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());

            customerRepository.save(existingCustomer);
            return updatedCustomer;
        }
        throw new NullPointerException();
    }

    public void deleteCustomer(long id) {
        customerRepository.deleteById(id);
    }

    //use in other services
    public Customer getCustomer(Long id){
        return customerRepository.findById(id).get();
    }
}
