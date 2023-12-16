package teck.me.license.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import teck.me.license.model.Customer;
import teck.me.license.model.dto.CustomerDto;
import teck.me.license.model.dto.ListCustomerDto;
import teck.me.license.repository.CustomerRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import teck.me.license.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<ListCustomerDto> getAllCustomers(int page, int number) {
        Pageable pageable = PageRequest.of(page, number);
        Page<Customer> customerPage = customerRepository.findAll(pageable);
        List<ListCustomerDto> listCustomerDtos = new ArrayList<>();

        for (Customer customer : customerPage.getContent()) {
            listCustomerDtos.add(new ListCustomerDto(customer));
        }

        return listCustomerDtos;
    }


    public ListCustomerDto getCustomerById(long id) {
        return new ListCustomerDto(customerRepository.findById(id).get());
    }

    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        if (customerRepository.existsByName(customerDto.getName())){
            //for unique name
            throw new RuntimeException();
        }
        customer.setName(customerDto.getName());
        customer.setDescription(customerDto.getDescription());
        customer.setAddress(customerDto.getAddress());
        customer.setEmail(customerDto.getEmail());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setLicenses(customerDto.getLicenses());

        customerRepository.save(customer);
        return customerDto;
    }

    public CustomerDto updateCustomer(long id, CustomerDto updatedCustomer) {
        Customer existingCustomer = customerRepository.findById(id).get();
        if (existingCustomer != null) {

            if (customerRepository.existsByName(updatedCustomer.getName())){
                //for unique name
                throw new RuntimeException();
            }
            existingCustomer.setName(updatedCustomer.getName());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
            existingCustomer.setAddress(updatedCustomer.getAddress());
            existingCustomer.setDescription(updatedCustomer.getDescription());
            existingCustomer.setLicenses(existingCustomer.getLicenses());

            customerRepository.save(existingCustomer);
            return updatedCustomer;
        }
        throw new NullPointerException();
    }

    public void deleteCustomer(long id) {
        customerRepository.deleteById(id);
    }

    //use in other services
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).get();
    }
}
