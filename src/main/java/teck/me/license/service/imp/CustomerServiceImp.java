package teck.me.license.service.imp;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import teck.me.license.exception.ConflictException;
import teck.me.license.exception.NotFoundException;
import teck.me.license.model.Customer;
import teck.me.license.model.License;
import teck.me.license.model.dto.CreateCustomerDto;
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

    private final CustomerRepository customerRepository;
    private final LicenseServiceImp licenseServiceImp;

    public CustomerServiceImp(CustomerRepository customerRepository, LicenseServiceImp licenseServiceImp) {
        this.customerRepository = customerRepository;
        this.licenseServiceImp = licenseServiceImp;
    }

    public List<ListCustomerDto> getAllCustomers(int page, int number) {
        Pageable pageable = PageRequest.of(page, number);
        Page<Customer> customerPage = customerRepository.findAll(pageable);
        List<ListCustomerDto> listCustomerDtos = new ArrayList<>();

        for (Customer customer : customerPage.getContent()) {
            listCustomerDtos.add(new ListCustomerDto(customer));
        }

        return listCustomerDtos;
    }


    public CustomerDto getCustomerById(String name) {
        if (customerRepository.findByName(name).isPresent()) {
            Customer customer=customerRepository.findByName(name).get();
            List<String> licenseId=new ArrayList<>();
            for (int i=0;i<customer.getLicenses().size();i++){
                licenseId.add(customer.getLicenses().get(i).getUuid());
            }
            return new CustomerDto(customer.getName(),customer.getEmail(),customer.getPhoneNumber(),customer.getAddress(), licenseId);
        }
        throw new NotFoundException("No Customer with this id");
    }

    public CreateCustomerDto createCustomer(CreateCustomerDto customerDto) {
        Customer customer = new Customer();
        if (customerRepository.existsByName(customerDto.getName())) {
            //for unique name
            throw new ConflictException("Name already exist");
        }
        customer.setName(customerDto.getName());
        customer.setAddress(customerDto.getAddress());
        customer.setEmail(customerDto.getEmail());
        customer.setPhoneNumber(customerDto.getPhoneNumber());

        customerRepository.save(customer);
        return customerDto;
    }


    public CreateCustomerDto updateCustomer(String name, CreateCustomerDto updatedCustomer) {

        if (customerRepository.findByName(name).isPresent()) {
            Customer existingCustomer = customerRepository.findByName(name).get();
            if (customerRepository.existsByName(updatedCustomer.getName())) {
                //for unique name
                throw new ConflictException("Name already exist");
            }
            existingCustomer.setName(updatedCustomer.getName());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
            existingCustomer.setAddress(updatedCustomer.getAddress());
            List<License> licenses=new ArrayList<>();
            for (int i=0;i<updatedCustomer.getLicensesId().size();i++){
                licenses.add(licenseServiceImp.getLicense(updatedCustomer.getLicensesId().get(i)));
            }
            existingCustomer.setLicenses(licenses);

            customerRepository.save(existingCustomer);
            return updatedCustomer;
        }
        throw new NotFoundException("No Customer with this id");
    }

    public void deleteCustomer(String name) {
        customerRepository.deleteByName(name);
    }

    //use in other services
    public Customer getCustomer(String name) {
        return customerRepository.findByName(name).get();
    }
}
