package teck.me.license.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teck.me.license.model.dto.CustomerDto;
import teck.me.license.model.dto.ListCustomerDto;
import teck.me.license.service.imp.CustomerServiceImp;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerServiceImp customerService;

    @Autowired
    public CustomerController(CustomerServiceImp customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<ListCustomerDto>> getAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int number) {
        List<ListCustomerDto> customers = customerService.getAllCustomers(page, number);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable String name) throws IllegalAccessException {
        CustomerDto customer = customerService.getCustomerById(name);
        return new ResponseEntity<>(customer, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) throws IllegalAccessException {
        CustomerDto createdCustomer = customerService.createCustomer(customerDto);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable String name, @RequestBody CustomerDto updatedCustomer) {
        CustomerDto customer = customerService.updateCustomer(name, updatedCustomer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String namee) {
        customerService.deleteCustomer(namee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
