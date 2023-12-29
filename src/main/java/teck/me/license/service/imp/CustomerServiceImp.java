package teck.me.license.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import teck.me.license.exception.ConflictException;
import teck.me.license.exception.NotFoundException;
import teck.me.license.model.Customer;
import teck.me.license.model.License;
import teck.me.license.model.dto.CustomerDto;
import teck.me.license.model.dto.LicenseDto;
import teck.me.license.model.dto.ListCustomerDto;
import teck.me.license.repository.CustomerRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import teck.me.license.service.CustomerService;
import teck.me.license.utill.Converter;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    private Converter<License, LicenseDto> convertLicenseToDto;
    private Converter<LicenseDto, License> convertDtoToLicense;

    public List<ListCustomerDto> getAllCustomers(int page, int number) {
        Pageable pageable = PageRequest.of(page, number);
        Page<Customer> customerPage = customerRepository.findAll(pageable);
        List<ListCustomerDto> listCustomerDtos = new ArrayList<>();

        for (Customer customer : customerPage.getContent()) {
            listCustomerDtos.add(new ListCustomerDto(customer));
        }

        return listCustomerDtos;
    }


    public CustomerDto getCustomerById(String name) throws IllegalAccessException {
        if (customerRepository.existsByName(name)) {
            Customer customer=customerRepository.findByName(name).get();
            return new CustomerDto(customer.getName(),customer.getEmail(),customer.getPhoneNumber(),customer.getAddress(), convertLicenseToDto.convertList(customer.getLicenses(),new LicenseDto()));
        }
        throw new NotFoundException("No Customer with this id");
    }

    public CustomerDto createCustomer(CustomerDto customerDto) throws IllegalAccessException {
        Customer customer = new Customer();
        if (customerRepository.existsByName(customerDto.getName())) {
            //for unique name
            throw new ConflictException("Name already exist");
        }
        customer.setName(customerDto.getName());
        customer.setAddress(customerDto.getAddress());
        customer.setEmail(customerDto.getEmail());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setLicenses(convertDtoToLicense.convertList(customerDto.getLicenses(),new License()));

        customerRepository.save(customer);
        return customerDto;
    }


    public CustomerDto updateCustomer(String name, CustomerDto updatedCustomer) {

        if (customerRepository.existsByName(name)) {
            Customer existingCustomer = customerRepository.findByName(name).get();
            if (customerRepository.existsByName(updatedCustomer.getName())) {
                //for unique name
                throw new ConflictException("Name already exist");
            }
            existingCustomer.setName(updatedCustomer.getName());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
            existingCustomer.setAddress(updatedCustomer.getAddress());
            existingCustomer.setLicenses(existingCustomer.getLicenses());

            customerRepository.save(existingCustomer);
            return updatedCustomer;
        }
        throw new NotFoundException("No Customer with this id");
    }

    public void deleteCustomer(String name) {
        customerRepository.deleteByName(name);
    }

    //use in other services
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).get();
    }
}
